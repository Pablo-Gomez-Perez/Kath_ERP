CREATE  PROCEDURE `kath_erp`.`buscar_ultima_venta`()
BEGIN
	
    SELECT 
		ventas.id_venta
	FROM ventas ORDER BY ventas.id_venta DESC LIMIT 1;
    
END;

CREATE  PROCEDURE `kath_erp`.`cancelVenta`(
    IN p_id_venta INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Cancela una venta y reincorpora sus existencias'
BEGIN

    DECLARE v_existe_venta INT DEFAULT 0;

    DECLARE v_status_venta BOOLEAN;
    DECLARE v_tipo_venta BOOLEAN;
    DECLARE v_id_sucursal BIGINT UNSIGNED;

    DECLARE v_facturas INT DEFAULT 0;
    DECLARE v_num_articulos INT DEFAULT 0;
    DECLARE v_num_existencias INT DEFAULT 0;

    DECLARE v_pagos_venta DECIMAL(18,2) DEFAULT 0;
    DECLARE v_cobros_cliente DECIMAL(18,2) DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_venta IS NULL OR p_id_venta <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La venta es obligatoria';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_venta
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta;


    IF v_existe_venta = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta indicada no existe';
    END IF;


    /*
     * Bloqueo de cabecera.
     * Evita que la venta sea modificada concurrentemente mientras
     * se procesa la cancelación.
     */

    SELECT
        status_venta,
        tipo_venta,
        id_sucursal
    INTO
        v_status_venta,
        v_tipo_venta,
        v_id_sucursal
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta
    LIMIT 1
    FOR UPDATE;


    IF v_status_venta = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta ya se encuentra cancelada';
    END IF;


    /* Una venta facturada no se cancela */

    SELECT COUNT(*)
    INTO v_facturas
    FROM kath_erp.factura
    WHERE id_venta = p_id_venta;


    IF v_facturas > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta no puede cancelarse porque tiene una factura relacionada';
    END IF;


    /*
     * Para una venta a crédito verificamos:
     *
     * 1. pagos realizados al momento de la venta;
     * 2. cobros posteriores registrados al cliente.
     */

    IF v_tipo_venta = FALSE THEN

        SELECT
            ROUND(
                COALESCE(
                    SUM(
                        CAST(importe AS DECIMAL(18,2))
                    ),
                    0
                ),
                2
            )
        INTO v_pagos_venta
        FROM kath_erp.pagos_x_venta
        WHERE id_venta = p_id_venta;


        SELECT
            ROUND(
                COALESCE(
                    SUM(
                        CAST(total AS DECIMAL(18,2))
                    ),
                    0
                ),
                2
            )
        INTO v_cobros_cliente
        FROM kath_erp.cobro_clientes
        WHERE id_venta = p_id_venta;


        IF v_pagos_venta > 0
           OR v_cobros_cliente > 0 THEN

            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT =
                    'La venta a crédito no puede cancelarse porque ya tiene pagos relacionados';

        END IF;

    END IF;


    /*
     * Antes de modificar nada comprobamos que todos los artículos
     * tengan registro de existencia en la sucursal.
     */

    SELECT COUNT(DISTINCT id_articulo)
    INTO v_num_articulos
    FROM kath_erp.articulo_x_venta
    WHERE id_venta = p_id_venta;


    IF v_num_articulos = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta no contiene artículos registrados';
    END IF;


    SELECT COUNT(DISTINCT exs.id_articulo)
    INTO v_num_existencias
    FROM kath_erp.existencia_x_sucursal AS exs

    INNER JOIN (
        SELECT
            id_articulo,
            SUM(cantidad) AS cantidad
        FROM kath_erp.articulo_x_venta
        WHERE id_venta = p_id_venta
        GROUP BY id_articulo
    ) AS detalle
        ON exs.id_articulo = detalle.id_articulo

    WHERE exs.id_sucursal = v_id_sucursal;


    IF v_num_existencias <> v_num_articulos THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No todos los artículos de la venta tienen existencia registrada en la sucursal';
    END IF;


    /*
     * Reincorporar existencia.
     *
     * Se agrupan artículos para tolerar ventas históricas que
     * pudieran contener más de una partida del mismo artículo.
     */

    UPDATE kath_erp.existencia_x_sucursal AS exs

    INNER JOIN (
        SELECT
            id_articulo,
            SUM(cantidad) AS cantidad
        FROM kath_erp.articulo_x_venta
        WHERE id_venta = p_id_venta
        GROUP BY id_articulo
    ) AS detalle
        ON exs.id_articulo = detalle.id_articulo

    SET exs.existencia =
        COALESCE(exs.existencia, 0) + detalle.cantidad

    WHERE exs.id_sucursal = v_id_sucursal;


    /*
     * Finalmente cancelar la venta.
     */

    UPDATE kath_erp.ventas
    SET status_venta = FALSE
    WHERE id_venta = p_id_venta;


    SELECT
        p_id_venta AS id,
        'Venta cancelada y existencias reincorporadas correctamente'
            AS message;

END;

CREATE  PROCEDURE `kath_erp`.`finalizarVenta`(
    IN p_id_venta INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Calcula totales y determina automáticamente si la venta es de contado o crédito'
BEGIN

    DECLARE v_existe_venta INT DEFAULT 0;
    DECLARE v_num_detalles INT DEFAULT 0;

    DECLARE v_subtotal DECIMAL(18,2) DEFAULT 0;
    DECLARE v_iva DECIMAL(18,2) DEFAULT 0;
    DECLARE v_total DECIMAL(18,2) DEFAULT 0;

    DECLARE v_total_pagos DECIMAL(18,2) DEFAULT 0;

    DECLARE v_tipo_venta BOOLEAN DEFAULT FALSE;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_venta IS NULL OR p_id_venta <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La venta es obligatoria';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_venta
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta
      AND status_venta = TRUE;


    IF v_existe_venta = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta indicada no existe o está cancelada';
    END IF;


    /* Bloquea la cabecera durante la finalización */

    SELECT id_venta
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta
    FOR UPDATE;


    SELECT COUNT(*)
    INTO v_num_detalles
    FROM kath_erp.articulo_x_venta
    WHERE id_venta = p_id_venta;


    IF v_num_detalles = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede finalizar una venta sin artículos';
    END IF;


    /*
     * El subtotal ya fue calculado por partida.
     * Solamente los artículos no exentos generan IVA.
     */

    SELECT

        ROUND(
            COALESCE(
                SUM(
                    CAST(axv.subtotal AS DECIMAL(18,2))
                ),
                0
            ),
            2
        ),

        ROUND(
            COALESCE(
                SUM(
                    CASE
                        WHEN a.es_exento = TRUE THEN 0

                        ELSE ROUND(
                            CAST(
                                axv.subtotal AS DECIMAL(18,2)
                            ) * 0.16,
                            2
                        )
                    END
                ),
                0
            ),
            2
        )

    INTO
        v_subtotal,
        v_iva

    FROM kath_erp.articulo_x_venta AS axv

    INNER JOIN kath_erp.articulo AS a
        ON axv.id_articulo = a.id_articulo

    WHERE axv.id_venta = p_id_venta;


    SET v_total =
        ROUND(v_subtotal + v_iva, 2);


    /*
     * Se convierte a DECIMAL antes de comparar porque actualmente
     * pagos_x_venta.importe e importe_total son DOUBLE.
     */

    SELECT
        ROUND(
            COALESCE(
                SUM(
                    CAST(importe AS DECIMAL(18,2))
                ),
                0
            ),
            2
        )
    INTO v_total_pagos
    FROM kath_erp.pagos_x_venta
    WHERE id_venta = p_id_venta;


    /* Un pago mayor al importe es un error */

    IF v_total_pagos > v_total THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La suma de pagos supera el importe total de la venta';
    END IF;


    /*
     * 1 = CONTADO
     * 0 = CRÉDITO
     */

    IF v_total_pagos = v_total THEN
        SET v_tipo_venta = TRUE;
    ELSE
        SET v_tipo_venta = FALSE;
    END IF;


    UPDATE kath_erp.ventas
    SET
        subtotal = v_subtotal,
        iva = v_iva,
        importe_total = v_total,
        tipo_venta = v_tipo_venta
    WHERE id_venta = p_id_venta;


    SELECT
        p_id_venta AS id,

        CASE
            WHEN v_tipo_venta = TRUE
                THEN 'Venta de contado registrada correctamente'
            ELSE
                'Venta a crédito registrada correctamente'
        END AS message,

        v_subtotal AS subtotal,
        v_iva AS iva,
        v_total AS total,
        v_total_pagos AS pagos,

        CASE
            WHEN v_tipo_venta = TRUE THEN 'Contado'
            ELSE 'Crédito'
        END AS tipo_venta;

END;

CREATE  PROCEDURE `kath_erp`.`getVentaById`(
    IN p_id_venta INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Obtiene la cabecera y los artículos correspondientes a una venta'
BEGIN

    DECLARE v_existe_venta INT DEFAULT 0;


    IF p_id_venta IS NULL OR p_id_venta <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La venta es obligatoria';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_venta
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta;


    IF v_existe_venta = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La venta indicada no existe';
    END IF;


    /* RESULT SET 1: cabecera */

    SELECT
        v.id_venta,
        v.id_empleado,
        emp.nombre_completo AS nombre_empleado,
        emp.nombre_corto AS nombre_corto_empleado,

        v.id_cliente,
        cli.nombre_completo AS nombre_cliente,
        cli.nombre_corto AS nombre_corto_cliente,

        v.id_sucursal,
        v.fecha,

        v.tipo_venta,

        CASE
            WHEN v.tipo_venta = TRUE THEN 'Contado'
            ELSE 'Crédito'
        END AS tipo_venta_descripcion,

        v.subtotal,
        v.iva,
        v.importe_total,

        v.status_venta,

        CASE
            WHEN v.status_venta = TRUE THEN 'Vigente'
            ELSE 'Cancelada'
        END AS status_venta_descripcion

    FROM kath_erp.ventas AS v

    INNER JOIN kath_erp.empleados AS emp
        ON v.id_empleado = emp.id_empleado

    INNER JOIN kath_erp.cliente AS cli
        ON v.id_cliente = cli.id_cliente

    WHERE v.id_venta = p_id_venta

    LIMIT 1;


    /* RESULT SET 2: artículos */

    SELECT
        axv.id,
        axv.id_venta,
        axv.id_articulo,

        a.codigo_articulo,
        a.codigo_sat,
        a.unidad_sat,
        a.nombre,
        a.descripcion,

        a.es_exento,

        axv.cantidad,
        axv.subtotal,

        CASE
            WHEN a.es_exento = TRUE THEN 0
            ELSE ROUND(
                CAST(axv.subtotal AS DECIMAL(18,2)) * 0.16,
                2
            )
        END AS iva,

        CASE
            WHEN a.es_exento = TRUE
                THEN ROUND(
                    CAST(axv.subtotal AS DECIMAL(18,2)),
                    2
                )

            ELSE ROUND(
                CAST(axv.subtotal AS DECIMAL(18,2))
                +
                ROUND(
                    CAST(axv.subtotal AS DECIMAL(18,2))
                    * 0.16,
                    2
                ),
                2
            )
        END AS total

    FROM kath_erp.articulo_x_venta AS axv

    INNER JOIN kath_erp.articulo AS a
        ON axv.id_articulo = a.id_articulo

    WHERE axv.id_venta = p_id_venta

    ORDER BY axv.id ASC;

END;

CREATE  PROCEDURE `kath_erp`.`insertArticuloVenta`(
    IN p_id_venta INT UNSIGNED,
    IN p_id_articulo INT UNSIGNED,
    IN p_cantidad INT
)
    MODIFIES SQL DATA
    COMMENT 'Inserta un artículo en una venta y calcula su subtotal considerando IVA y tipo de cliente'
BEGIN

    DECLARE v_existe_venta INT DEFAULT 0;
    DECLARE v_existe_articulo INT DEFAULT 0;
    DECLARE v_existe_detalle INT DEFAULT 0;
    DECLARE v_existe_existencia INT DEFAULT 0;
    DECLARE v_existe_precio INT DEFAULT 0;

    DECLARE v_id_detalle INT UNSIGNED DEFAULT 0;

    DECLARE v_id_sucursal BIGINT UNSIGNED;
    DECLARE v_id_tipo_cliente INT;

    DECLARE v_existencia_actual INT DEFAULT 0;
    DECLARE v_es_exento BOOLEAN DEFAULT FALSE;

    DECLARE v_precio DECIMAL(18,2);
    DECLARE v_precio_especial DECIMAL(18,2);
    DECLARE v_cantidad_precio_especial INT;

    DECLARE v_precio_unitario DECIMAL(18,2);
    DECLARE v_importe_bruto DECIMAL(18,2);
    DECLARE v_subtotal_linea DECIMAL(18,2);
    DECLARE v_iva_linea DECIMAL(18,2);

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_venta IS NULL OR p_id_venta <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La venta es obligatoria';
    END IF;


    IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo es obligatorio';
    END IF;


    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero';
    END IF;


    /* Venta vigente */

    SELECT COUNT(*)
    INTO v_existe_venta
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta
      AND status_venta = TRUE;

    IF v_existe_venta = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta indicada no existe o está cancelada';
    END IF;


    SELECT
        v.id_sucursal,
        c.id_tipoCliente
    INTO
        v_id_sucursal,
        v_id_tipo_cliente
    FROM kath_erp.ventas AS v

    INNER JOIN kath_erp.cliente AS c
        ON v.id_cliente = c.id_cliente

    WHERE v.id_venta = p_id_venta
    LIMIT 1;


    /* Artículo activo */

    SELECT COUNT(*)
    INTO v_existe_articulo
    FROM kath_erp.articulo
    WHERE id_articulo = p_id_articulo
      AND activo = TRUE;

    IF v_existe_articulo = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El artículo indicado no existe o está inactivo';
    END IF;


    SELECT es_exento
    INTO v_es_exento
    FROM kath_erp.articulo
    WHERE id_articulo = p_id_articulo
    LIMIT 1;


    /* No repetir el mismo artículo */

    SELECT COUNT(*)
    INTO v_existe_detalle
    FROM kath_erp.articulo_x_venta
    WHERE id_venta = p_id_venta
      AND id_articulo = p_id_articulo;

    IF v_existe_detalle > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El artículo ya está registrado en esta venta';
    END IF;


    /* Existencia en la sucursal */

    SELECT COUNT(*)
    INTO v_existe_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = p_id_articulo
      AND id_sucursal = v_id_sucursal;

    IF v_existe_existencia = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El artículo no tiene existencia registrada en esta sucursal';
    END IF;


    SELECT COALESCE(existencia, 0)
    INTO v_existencia_actual
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = p_id_articulo
      AND id_sucursal = v_id_sucursal
    LIMIT 1;


    IF v_existencia_actual <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El artículo no tiene existencia disponible';
    END IF;


    IF p_cantidad > v_existencia_actual THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La cantidad solicitada supera la existencia disponible';
    END IF;


    /* Precio correspondiente al tipo de cliente */

    SELECT COUNT(*)
    INTO v_existe_precio
    FROM kath_erp.precios_x_tipocliente AS pxt
    WHERE pxt.id_articulo = p_id_articulo
      AND pxt.id_tipoCliente = v_id_tipo_cliente;


    IF v_existe_precio = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El artículo no tiene precio definido para este tipo de cliente';
    END IF;


    IF v_existe_precio > 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Existe más de un precio para el artículo y tipo de cliente';
    END IF;


    SELECT
        precio,
        precios_especial,
        cant_p_precioEspecial
    INTO
        v_precio,
        v_precio_especial,
        v_cantidad_precio_especial
    FROM kath_erp.precios_x_tipocliente
    WHERE id_articulo = p_id_articulo
      AND id_tipoCliente = v_id_tipo_cliente
    LIMIT 1;


    IF v_precio_especial IS NOT NULL
       AND v_cantidad_precio_especial IS NOT NULL
       AND p_cantidad >= v_cantidad_precio_especial THEN

        SET v_precio_unitario = v_precio_especial;

    ELSE

        SET v_precio_unitario = v_precio;

    END IF;


    SET v_importe_bruto =
        ROUND(v_precio_unitario * p_cantidad, 2);


    /*
     * Se conserva la convención actual del POS:
     * los precios mostrados al usuario son precios finales.
     *
     * Artículo gravado:
     *   base = total / 1.16
     *
     * Artículo exento:
     *   base = total
     */

    IF v_es_exento = TRUE THEN

        SET v_subtotal_linea = v_importe_bruto;
        SET v_iva_linea = 0;

    ELSE

        SET v_subtotal_linea =
            ROUND(v_importe_bruto / 1.16, 2);

        SET v_iva_linea =
            ROUND(v_importe_bruto - v_subtotal_linea, 2);

    END IF;


    INSERT INTO kath_erp.articulo_x_venta (
        id_venta,
        id_articulo,
        cantidad,
        subtotal
    )
    VALUES (
        p_id_venta,
        p_id_articulo,
        p_cantidad,
        v_subtotal_linea
    );


    SET v_id_detalle = LAST_INSERT_ID();


    SELECT
        v_id_detalle AS id,
        'Artículo agregado a la venta correctamente' AS message,
        v_precio_unitario AS precio_unitario,
        v_subtotal_linea AS subtotal,
        v_iva_linea AS iva,
        v_importe_bruto AS total;

END;

CREATE  PROCEDURE `kath_erp`.`insertPagoVenta`(
    IN p_id_venta INT UNSIGNED,
    IN p_id_forma_pago INT,
    IN p_importe DECIMAL(18,2)
)
    MODIFIES SQL DATA
    COMMENT 'Registra un pago asociado a una venta'
BEGIN

    DECLARE v_existe_venta INT DEFAULT 0;
    DECLARE v_existe_forma_pago INT DEFAULT 0;

    DECLARE v_id_pago INT UNSIGNED DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_venta IS NULL OR p_id_venta <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La venta es obligatoria';
    END IF;


    IF p_id_forma_pago IS NULL
       OR p_id_forma_pago <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La forma de pago es obligatoria';
    END IF;


    IF p_importe IS NULL OR p_importe <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El importe del pago debe ser mayor a cero';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_venta
    FROM kath_erp.ventas
    WHERE id_venta = p_id_venta
      AND status_venta = TRUE;

    IF v_existe_venta = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta indicada no existe o está cancelada';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_forma_pago
    FROM kath_erp.formas_de_pago
    WHERE id = p_id_forma_pago
      AND activo = TRUE;

    IF v_existe_forma_pago = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La forma de pago no existe o está inactiva';
    END IF;


    INSERT INTO kath_erp.pagos_x_venta (
        id_venta,
        id_forma_pago,
        importe
    )
    VALUES (
        p_id_venta,
        p_id_forma_pago,
        p_importe
    );


    SET v_id_pago = LAST_INSERT_ID();


    SELECT
        v_id_pago AS id,
        'Pago registrado correctamente' AS message;

END;

CREATE  PROCEDURE `kath_erp`.`insertVenta`(
    IN p_id_empleado INT UNSIGNED,
    IN p_id_cliente INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_fecha DATE
)
    MODIFIES SQL DATA
    COMMENT 'Crea la cabecera provisional de una venta'
BEGIN

    DECLARE v_id_venta INT UNSIGNED DEFAULT 0;

    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_existe_cliente INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;

    DECLARE v_sucursal_empleado BIGINT UNSIGNED DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado es obligatorio';
    END IF;


    IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El cliente es obligatorio';
    END IF;


    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;


    IF p_fecha IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de venta es obligatoria';
    END IF;


    /* Empleado activo */

    SELECT COUNT(*)
    INTO v_existe_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
      AND activo = TRUE;

    IF v_existe_empleado = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El empleado indicado no existe o está inactivo';
    END IF;


    SELECT id_sucursal
    INTO v_sucursal_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
    LIMIT 1;


    IF v_sucursal_empleado <> p_id_sucursal THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El empleado no pertenece a la sucursal de la venta';
    END IF;


    /* Cliente activo */

    SELECT COUNT(*)
    INTO v_existe_cliente
    FROM kath_erp.cliente
    WHERE id_cliente = p_id_cliente
      AND activo = TRUE;

    IF v_existe_cliente = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El cliente indicado no existe o está inactivo';
    END IF;


    /* Sucursal activa */

    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal
      AND activo = TRUE;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La sucursal indicada no existe o está inactiva';
    END IF;


    /*
     * tipo_venta queda provisionalmente en Crédito.
     *
     * subtotal, iva e importe_total se calculan posteriormente
     * mediante finalizarVenta().
     *
     * Ninguno de estos valores debe provenir de la UI.
     */

    INSERT INTO kath_erp.ventas (
        id_empleado,
        id_cliente,
        id_sucursal,
        fecha,
        tipo_venta,
        subtotal,
        iva,
        importe_total,
        status_venta
    )
    VALUES (
        p_id_empleado,
        p_id_cliente,
        p_id_sucursal,
        p_fecha,
        FALSE,
        0,
        0,
        0,
        TRUE
    );


    SET v_id_venta = LAST_INSERT_ID();


    SELECT
        v_id_venta AS id,
        'Cabecera de venta registrada correctamente' AS message;

END;

CREATE  PROCEDURE `kath_erp`.`listVentas`(
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_tipo_busqueda VARCHAR(20)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_texto_busqueda VARCHAR(255)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_ordenar_por VARCHAR(20)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_inicial DATE,
    IN p_fecha_final DATE
)
    READS SQL DATA
    COMMENT 'Lista las ventas de una sucursal con búsqueda, ordenamiento y rango de fechas'
BEGIN

    DECLARE v_tipo_busqueda VARCHAR(20)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE v_texto_busqueda VARCHAR(255)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE v_ordenar_por VARCHAR(20)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE v_existe_sucursal INT DEFAULT 0;


    SET v_tipo_busqueda =
        UPPER(TRIM(COALESCE(p_tipo_busqueda, 'TODOS')));

    SET v_texto_busqueda =
        TRIM(COALESCE(p_texto_busqueda, ''));

    SET v_ordenar_por =
        UPPER(TRIM(COALESCE(p_ordenar_por, 'FECHA')));


    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal indicada no existe';
    END IF;


    IF v_tipo_busqueda NOT IN (
        'TODOS',
        'EMPLEADO',
        'CLIENTE'
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El criterio de búsqueda no es válido';
    END IF;


    IF v_tipo_busqueda <> 'TODOS'
       AND v_texto_busqueda = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Debe indicar un texto para realizar la búsqueda';
    END IF;


    IF v_ordenar_por NOT IN (
        'EMPLEADO',
        'CLIENTE',
        'TIPO',
        'FECHA',
        'STATUS'
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El criterio de ordenamiento no es válido';
    END IF;


    IF p_fecha_inicial IS NOT NULL
       AND p_fecha_final IS NOT NULL
       AND p_fecha_inicial > p_fecha_final THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La fecha inicial no puede ser posterior a la fecha final';
    END IF;


    SELECT
        v.id_venta AS folio,
        v.fecha,

        CASE
            WHEN v.tipo_venta = TRUE THEN 'Contado'
            ELSE 'Crédito'
        END AS tipo,

        emp.nombre_completo AS atendio,
        cli.nombre_completo AS cliente,

        v.subtotal,
        v.iva,
        v.importe_total AS total,

        CASE
            WHEN v.status_venta = TRUE THEN 'Vigente'
            ELSE 'Cancelada'
        END AS vigente

    FROM kath_erp.ventas AS v

    INNER JOIN kath_erp.empleados AS emp
        ON v.id_empleado = emp.id_empleado

    INNER JOIN kath_erp.cliente AS cli
        ON v.id_cliente = cli.id_cliente

    WHERE
        v.id_sucursal = p_id_sucursal

        AND (
            v_tipo_busqueda = 'TODOS'

            OR (
                v_tipo_busqueda = 'EMPLEADO'
                AND (
                    emp.nombre_completo LIKE
                        CONCAT('%', v_texto_busqueda, '%')

                    OR emp.nombre_corto LIKE
                        CONCAT('%', v_texto_busqueda, '%')
                )
            )

            OR (
                v_tipo_busqueda = 'CLIENTE'
                AND (
                    cli.nombre_completo LIKE
                        CONCAT('%', v_texto_busqueda, '%')

                    OR cli.nombre_corto LIKE
                        CONCAT('%', v_texto_busqueda, '%')
                )
            )
        )

        AND (
            p_fecha_inicial IS NULL
            OR v.fecha >= p_fecha_inicial
        )

        AND (
            p_fecha_final IS NULL
            OR v.fecha <= p_fecha_final
        )

    ORDER BY

        CASE
            WHEN v_ordenar_por = 'EMPLEADO'
            THEN emp.nombre_completo
        END ASC,

        CASE
            WHEN v_ordenar_por = 'CLIENTE'
            THEN cli.nombre_completo
        END ASC,

        CASE
            WHEN v_ordenar_por = 'TIPO'
            THEN v.tipo_venta
        END DESC,

        CASE
            WHEN v_ordenar_por = 'FECHA'
            THEN v.fecha
        END DESC,

        CASE
            WHEN v_ordenar_por = 'STATUS'
            THEN v.status_venta
        END DESC,

        v.fecha DESC,
        v.id_venta DESC;

END;

CREATE  PROCEDURE `kath_erp`.`restarExistenciaSucursalVenta`(
    IN p_id_detalle_venta INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Descuenta de la sucursal la existencia correspondiente a un detalle de venta'
BEGIN

    DECLARE v_existe_detalle INT DEFAULT 0;

    DECLARE v_id_articulo INT UNSIGNED;
    DECLARE v_id_sucursal BIGINT UNSIGNED;
    DECLARE v_cantidad INT;

    DECLARE v_id_existencia INT;
    DECLARE v_existencia_actual INT DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_detalle_venta IS NULL
       OR p_id_detalle_venta <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El detalle de venta es obligatorio';
    END IF;


    SELECT COUNT(*)
    INTO v_existe_detalle
    FROM kath_erp.articulo_x_venta AS axv

    INNER JOIN kath_erp.ventas AS v
        ON axv.id_venta = v.id_venta

    INNER JOIN kath_erp.articulo AS a
        ON axv.id_articulo = a.id_articulo

    WHERE axv.id = p_id_detalle_venta
      AND v.status_venta = TRUE
      AND a.activo = TRUE;


    IF v_existe_detalle = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El detalle no existe, la venta está cancelada o el artículo está inactivo';
    END IF;


    SELECT
        axv.id_articulo,
        v.id_sucursal,
        axv.cantidad
    INTO
        v_id_articulo,
        v_id_sucursal,
        v_cantidad
    FROM kath_erp.articulo_x_venta AS axv

    INNER JOIN kath_erp.ventas AS v
        ON axv.id_venta = v.id_venta

    WHERE axv.id = p_id_detalle_venta
    LIMIT 1;


    SELECT
        id,
        COALESCE(existencia, 0)
    INTO
        v_id_existencia,
        v_existencia_actual
    FROM kath_erp.existencia_x_sucursal

    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal

    LIMIT 1
    FOR UPDATE;


    IF v_id_existencia IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No existe registro de existencia para el artículo y sucursal';
    END IF;


    IF v_existencia_actual < v_cantidad THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La venta dejaría la existencia del artículo en negativo';
    END IF;


    UPDATE kath_erp.existencia_x_sucursal
    SET existencia = v_existencia_actual - v_cantidad
    WHERE id = v_id_existencia;


    SELECT
        p_id_detalle_venta AS id,
        'Existencia actualizada correctamente' AS message;

END;

CREATE  PROCEDURE `kath_erp`.`ver_indice_venta_actual`()
BEGIN

    SELECT
		ventas.id_venta
	FROM ventas
    ORDER BY ventas.id_venta DESC LIMIT 1;

END;