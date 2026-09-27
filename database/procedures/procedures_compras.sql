CREATE PROCEDURE `kath_erp`.`deleteArticuloCompra`(
    IN p_id_detalle_compra INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un artículo del detalle de compra y descuenta su existencia si es válido'
BEGIN
    DECLARE v_id_compra INT UNSIGNED DEFAULT 0;
    DECLARE v_id_articulo INT UNSIGNED DEFAULT 0;
    DECLARE v_cantidad_actual INT DEFAULT 0;
    DECLARE v_existencia_actual INT DEFAULT 0;
    DECLARE v_id_existencia INT DEFAULT 0;
    DECLARE v_id_sucursal BIGINT UNSIGNED DEFAULT 0;
    DECLARE v_fecha_compra DATE;
    DECLARE v_ventas_posteriores INT DEFAULT 0;
    DECLARE v_registros_existencia INT DEFAULT 0;

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
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    IF p_id_detalle_compra IS NULL OR p_id_detalle_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El detalle de compra es obligatorio';
    END IF;

    SELECT
        axc.id_compra,
        axc.id_articulo,
        axc.cantidad,
        c.fecha_compra,
        emp.id_sucursal
    INTO
        v_id_compra,
        v_id_articulo,
        v_cantidad_actual,
        v_fecha_compra,
        v_id_sucursal
    FROM kath_erp.articulo_x_compra AS axc
    INNER JOIN kath_erp.compras AS c
        ON axc.id_compra = c.id_compra
    INNER JOIN kath_erp.empleados AS emp
        ON c.id_empleado = emp.id_empleado
    WHERE axc.id = p_id_detalle_compra
      AND c.activo = TRUE
    LIMIT 1
    FOR UPDATE;

    IF v_id_compra IS NULL OR v_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El detalle de compra no existe o la compra está inactiva';
    END IF;

    SELECT COUNT(*)
    INTO v_ventas_posteriores
    FROM kath_erp.articulo_x_venta AS axv
    INNER JOIN kath_erp.ventas AS v
        ON axv.id_venta = v.id_venta
    INNER JOIN kath_erp.empleados AS emp_venta
        ON v.id_empleado = emp_venta.id_empleado
    WHERE axv.id_articulo = v_id_articulo
      AND emp_venta.id_sucursal = v_id_sucursal
      AND v.fecha > v_fecha_compra
      AND v.status_venta = TRUE;

    IF v_ventas_posteriores > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede eliminar el artículo porque ya tiene ventas posteriores a la compra';
    END IF;

    SELECT COUNT(*)
    INTO v_registros_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal;

    IF v_registros_existencia = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No existe registro de existencia para el artículo y sucursal';
    END IF;

    IF v_registros_existencia > 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Existe más de un registro de existencia para el artículo y sucursal';
    END IF;

    SELECT id, COALESCE(existencia, 0)
    INTO v_id_existencia, v_existencia_actual
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal
    LIMIT 1
    FOR UPDATE;

    IF v_existencia_actual - v_cantidad_actual < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede eliminar el artículo porque la existencia quedaría negativa';
    END IF;

    DELETE FROM kath_erp.articulo_x_compra
    WHERE id = p_id_detalle_compra;

    UPDATE kath_erp.existencia_x_sucursal
    SET existencia = v_existencia_actual - v_cantidad_actual
    WHERE id = v_id_existencia;

    SELECT
        p_id_detalle_compra AS id,
        'Artículo eliminado de la compra correctamente' AS message;
END;

CREATE PROCEDURE `kath_erp`.`deleteCompra`(
    IN p_id_compra INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Cancela lógicamente una compra y revierte las existencias generadas por ella'
BEGIN

    DECLARE v_existe_compra INT DEFAULT 0;
    DECLARE v_id_sucursal_compra BIGINT UNSIGNED DEFAULT 0;
    DECLARE v_fecha_compra DATE;
    DECLARE v_activo BOOLEAN DEFAULT FALSE;

    DECLARE v_pagos_asociados INT DEFAULT 0;
    DECLARE v_total_detalles INT DEFAULT 0;
    DECLARE v_detalles_invalidos INT DEFAULT 0;

    DECLARE v_inicio_mes DATE;
    DECLARE v_inicio_mes_siguiente DATE;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    /*
     * El procedimiento NO controla la transacción.
     *
     * La transacción debe manejarse desde Java para que,
     * ante una respuesta de error, CompraController pueda
     * ejecutar rollback sobre toda la operación.
     */
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


    /*
     * VALIDACIONES BÁSICAS
     */

    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';

    END IF;


    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';

    END IF;


    /*
     * CONSULTAR Y BLOQUEAR LA COMPRA
     *
     * FOR UPDATE evita que la compra sea modificada mientras
     * se está procesando su cancelación dentro de la transacción.
     */

    SELECT COUNT(*)
    INTO v_existe_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra;


    IF v_existe_compra = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra indicada no existe';

    END IF;


    SELECT
        id_sucursal,
        fecha_compra,
        activo
    INTO
        v_id_sucursal_compra,
        v_fecha_compra,
        v_activo
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra
    LIMIT 1
    FOR UPDATE;


    /*
     * AISLAMIENTO POR SUCURSAL
     */

    IF v_id_sucursal_compra <> p_id_sucursal THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra indicada no pertenece a la sucursal actual';

    END IF;


    /*
     * EVITAR CANCELAR DOS VECES
     */

    IF v_activo = FALSE THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra indicada ya se encuentra cancelada';

    END IF;


    /*
     * VALIDAR PERIODO
     *
     * Solamente puede cancelarse una compra cuya fecha_compra
     * pertenezca al mes calendario actual.
     *
     * Se compara intervalo completo y no únicamente MONTH(),
     * para considerar también el año.
     */

    SET v_inicio_mes =
        STR_TO_DATE(
            DATE_FORMAT(CURRENT_DATE(), '%Y-%m-01'),
            '%Y-%m-%d'
        );

    SET v_inicio_mes_siguiente =
        DATE_ADD(v_inicio_mes, INTERVAL 1 MONTH);


    IF v_fecha_compra < v_inicio_mes
       OR v_fecha_compra >= v_inicio_mes_siguiente THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede cancelar una compra fuera del mes actual';

    END IF;


    /*
     * VALIDAR PAGOS A PROVEEDOR
     *
     * Basta la existencia de un pago relacionado para impedir
     * la cancelación de la compra.
     */

    SELECT COUNT(*)
    INTO v_pagos_asociados
    FROM kath_erp.pago_proveedor
    WHERE id_compra = p_id_compra;


    IF v_pagos_asociados > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede cancelar la compra porque ya tiene pagos asociados';

    END IF;


    /*
     * VALIDAR QUE EXISTAN PARTIDAS
     */

    SELECT COUNT(*)
    INTO v_total_detalles
    FROM kath_erp.articulo_x_compra
    WHERE id_compra = p_id_compra;


    IF v_total_detalles = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra no contiene artículos registrados';

    END IF;


    /*
     * VALIDACIÓN DEFENSIVA DE CANTIDADES
     */

    SELECT COUNT(*)
    INTO v_detalles_invalidos
    FROM kath_erp.articulo_x_compra
    WHERE id_compra = p_id_compra
      AND (
            cantidad IS NULL
            OR cantidad <= 0
          );


    IF v_detalles_invalidos > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra contiene cantidades de artículos inválidas';

    END IF;


    /*
     * VALIDAR EXISTENCIAS
     *
     * Se agrupa por artículo para que el procedimiento siga
     * siendo correcto aunque existieran accidentalmente varias
     * partidas para el mismo artículo.
     *
     * Cada existencia involucrada se bloquea mediante FOR UPDATE.
     */

    BEGIN

        DECLARE v_fin BOOLEAN DEFAULT FALSE;

        DECLARE v_id_articulo INT UNSIGNED;
        DECLARE v_cantidad_compra INT;

        DECLARE v_registros_existencia INT DEFAULT 0;
        DECLARE v_id_existencia INT DEFAULT 0;
        DECLARE v_existencia_actual INT DEFAULT 0;


        DECLARE cur_articulos CURSOR FOR

            SELECT
                axc.id_articulo,
                SUM(axc.cantidad) AS cantidad_compra
            FROM kath_erp.articulo_x_compra AS axc
            WHERE axc.id_compra = p_id_compra
            GROUP BY axc.id_articulo;


        DECLARE CONTINUE HANDLER FOR NOT FOUND
            SET v_fin = TRUE;


        OPEN cur_articulos;


        validar_existencias: LOOP

            FETCH cur_articulos
            INTO
                v_id_articulo,
                v_cantidad_compra;


            IF v_fin THEN
                LEAVE validar_existencias;
            END IF;


            /*
             * Debe existir exactamente un registro de existencia
             * para artículo + sucursal.
             */

            SELECT
                COUNT(*),
                COALESCE(MAX(id), 0)
            INTO
                v_registros_existencia,
                v_id_existencia
            FROM kath_erp.existencia_x_sucursal
            WHERE id_articulo = v_id_articulo
              AND id_sucursal = p_id_sucursal;


            IF v_registros_existencia = 0 THEN

                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT =
                        'No existe registro de existencia para uno de los artículos de la compra';

            END IF;


            IF v_registros_existencia > 1 THEN

                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT =
                        'Existe más de un registro de existencia para un artículo y sucursal';

            END IF;


            /*
             * Bloquear la existencia antes de comprobarla.
             */

            SELECT COALESCE(existencia, 0)
            INTO v_existencia_actual
            FROM kath_erp.existencia_x_sucursal
            WHERE id = v_id_existencia
            LIMIT 1
            FOR UPDATE;


            /*
             * Regla principal:
             *
             * existencia actual - cantidad proveniente de la compra
             * nunca puede ser negativa.
             */

            IF v_existencia_actual - v_cantidad_compra < 0 THEN

                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT =
                        'No se puede cancelar la compra porque la existencia de uno o más artículos quedaría negativa';

            END IF;


        END LOOP validar_existencias;


        CLOSE cur_articulos;

    END;


    /*
     * TODAS LAS VALIDACIONES PASARON.
     *
     * Restar de cada artículo exactamente la cantidad que
     * originalmente ingresó mediante esta compra.
     */

    UPDATE kath_erp.existencia_x_sucursal AS exs

    INNER JOIN (

        SELECT
            axc.id_articulo,
            SUM(axc.cantidad) AS cantidad_compra

        FROM kath_erp.articulo_x_compra AS axc

        WHERE axc.id_compra = p_id_compra

        GROUP BY axc.id_articulo

    ) AS detalle
        ON detalle.id_articulo = exs.id_articulo

    SET exs.existencia =
        COALESCE(exs.existencia, 0)
        - detalle.cantidad_compra

    WHERE exs.id_sucursal = p_id_sucursal;


    /*
     * CANCELACIÓN LÓGICA.
     *
     * No se elimina la compra.
     * No se eliminan articulo_x_compra.
     */

    UPDATE kath_erp.compras
    SET activo = FALSE
    WHERE id_compra = p_id_compra
      AND id_sucursal = p_id_sucursal
      AND activo = TRUE;


    IF ROW_COUNT() <> 1 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No fue posible actualizar el estado de la compra';

    END IF;


    SELECT
        p_id_compra AS id,
        'Compra cancelada correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`deleteProveedor`(
    IN idProveedor INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Inhabilita un proveedor cuando no tiene compras a crédito con saldo pendiente'
BEGIN
    DECLARE v_existe_proveedor INT DEFAULT 0;
    DECLARE v_proveedor_activo BOOLEAN DEFAULT FALSE;
    DECLARE v_saldo_pendiente DECIMAL(20,2) DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    START TRANSACTION;

    IF idProveedor IS NULL OR idProveedor <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El identificador del proveedor no es valido';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = idProveedor;

    IF v_existe_proveedor = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor indicado no existe';
    END IF;

    SELECT activo
    INTO v_proveedor_activo
    FROM kath_erp.proveedor
    WHERE id_proveedor = idProveedor
    FOR UPDATE;

    IF v_proveedor_activo = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor ya se encuentra inactivo';
    END IF;

    /*
     * Sustituye la antigua validación de saldo contable por el saldo operativo
     * real de las compras vigentes a crédito del proveedor.
     */
    SELECT
        ROUND(
            COALESCE(
                SUM(
                    GREATEST(
                        CAST(c.subtotal + c.iva AS DECIMAL(18,2))
                        - COALESCE((
                            SELECT SUM(CAST(pp.importe AS DECIMAL(18,2)))
                            FROM kath_erp.pago_proveedor AS pp
                            WHERE pp.id_compra = c.id_compra
                        ), 0),
                        0
                    )
                ),
                0
            ),
            2
        )
    INTO v_saldo_pendiente
    FROM kath_erp.compras AS c
    WHERE c.id_proveedor = idProveedor
      AND c.activo = TRUE
      AND c.tipo_compra = TRUE;

    IF v_saldo_pendiente > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede inhabilitar el proveedor porque tiene saldo pendiente en compras a crédito';
    END IF;

    UPDATE kath_erp.proveedor
    SET activo = FALSE
    WHERE id_proveedor = idProveedor;

    COMMIT;

    SELECT
        200 AS id,
        'Proveedor inhabilitado correctamente' AS message;
END;

CREATE PROCEDURE `kath_erp`.`getCompraById`(
    IN p_id_compra INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Obtiene los datos generales de una compra'
BEGIN
    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    SELECT
        c.id_compra,
        c.id_empleado,
        emp.nombre_completo AS nombre_empleado,
        emp.nombre_corto AS nombre_corto_empleado,
        emp.id_sucursal,
        c.id_proveedor,
        c.folio_factura,
        c.fecha_factura,
        c.fecha_compra,
        c.tipo_compra,
        CASE
            WHEN c.tipo_compra = TRUE THEN 'Crédito'
            ELSE 'Contado'
        END AS tipo_compra_descripcion,
        c.subtotal,
        c.iva,
        (c.subtotal + c.iva) AS importe_total,
        c.activo
    FROM kath_erp.compras AS c
    INNER JOIN kath_erp.empleados AS emp
        ON c.id_empleado = emp.id_empleado
    WHERE c.id_compra = p_id_compra
    LIMIT 1;
END;

CREATE PROCEDURE `kath_erp`.`getIdUltimaCompra`()
    READS SQL DATA
    COMMENT 'Obtiene el ID de la última compra que se haya efectuado'
BEGIN
	
	SELECT
		c.id_compra
	FROM
		kath_erp.compras AS c
	ORDER BY
		c.id_compra DESC LIMIT 1;
	
END;

CREATE PROCEDURE `kath_erp`.`insertArticuloCompra`(
    IN p_id_compra INT UNSIGNED,
    IN p_id_articulo INT UNSIGNED,
    IN p_cantidad INT,
    IN p_subtotal DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Inserta un artículo en el detalle de compra. La existencia se actualiza con otro SP'
BEGIN
    DECLARE v_existe_compra INT DEFAULT 0;
    DECLARE v_existe_articulo INT DEFAULT 0;
    DECLARE v_existe_detalle INT DEFAULT 0;
    DECLARE v_id_detalle INT UNSIGNED DEFAULT 0;

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
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo es obligatorio';
    END IF;

    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero';
    END IF;

    IF p_subtotal IS NULL OR p_subtotal < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal del artículo no puede ser negativo';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra
      AND activo = TRUE;

    IF v_existe_compra = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra indicada no existe o está inactiva';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_articulo
    FROM kath_erp.articulo
    WHERE id_articulo = p_id_articulo
      AND activo = TRUE;

    IF v_existe_articulo = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo indicado no existe o está inactivo';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_detalle
    FROM kath_erp.articulo_x_compra
    WHERE id_compra = p_id_compra
      AND id_articulo = p_id_articulo;

    IF v_existe_detalle > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo ya está registrado en esta compra';
    END IF;

    INSERT INTO kath_erp.articulo_x_compra (
        id_compra,
        id_articulo,
        cantidad,
        subtotal
    ) VALUES (
        p_id_compra,
        p_id_articulo,
        p_cantidad,
        p_subtotal
    );

    SET v_id_detalle = LAST_INSERT_ID();

    SELECT
        v_id_detalle AS id,
        'Artículo agregado a la compra correctamente' AS message;
END;

CREATE PROCEDURE `kath_erp`.`insertCompra`(
    IN p_id_empleado INT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_folio_factura VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_factura DATE,
    IN p_fecha_compra DATE,
    IN p_tipo_compra BOOLEAN,
    IN p_subtotal DOUBLE,
    IN p_iva DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Inserta cabecera de compra asociándola directamente a la sucursal donde se realiza'
BEGIN
    DECLARE v_id_compra INT UNSIGNED DEFAULT 0;

    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_existe_proveedor INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;
    DECLARE v_folio_duplicado INT DEFAULT 0;

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

    IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor es obligatorio';
    END IF;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    IF p_folio_factura IS NULL
       OR TRIM(p_folio_factura) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura es obligatorio';

    END IF;

    IF CHAR_LENGTH(TRIM(p_folio_factura)) > 13 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura no puede exceder 13 caracteres';
    END IF;

    IF p_fecha_factura IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de factura es obligatoria';
    END IF;

    IF p_fecha_compra IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de compra es obligatoria';
    END IF;

    IF p_tipo_compra IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El tipo de compra es obligatorio';
    END IF;

    IF p_subtotal IS NULL OR p_subtotal < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal no puede ser negativo';
    END IF;

    IF p_iva IS NULL OR p_iva < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El IVA no puede ser negativo';
    END IF;


    -- Validar empleado

    SELECT COUNT(*)
    INTO v_existe_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
      AND activo = TRUE;

    IF v_existe_empleado = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado indicado no existe o está inactivo';
    END IF;


    -- Validar proveedor

    SELECT COUNT(*)
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = p_id_proveedor;

    IF v_existe_proveedor = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor indicado no existe';
    END IF;


    -- Validar directamente la sucursal

    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal
      AND activo = TRUE;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal indicada no existe o está inactiva';
    END IF;


    -- Mantengo por ahora tu regla existente:
    -- folio único por proveedor.

    SELECT COUNT(*)
    INTO v_folio_duplicado
    FROM kath_erp.compras
    WHERE id_proveedor = p_id_proveedor
      AND folio_factura = TRIM(p_folio_factura)
      AND activo = TRUE;

    IF v_folio_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura ya está registrado para este proveedor';
    END IF;


    INSERT INTO kath_erp.compras (
        id_empleado,
        id_proveedor,
        id_sucursal,
        folio_factura,
        fecha_factura,
        fecha_compra,
        tipo_compra,
        subtotal,
        iva,
        activo
    )
    VALUES (
        p_id_empleado,
        p_id_proveedor,
        p_id_sucursal,
        TRIM(p_folio_factura),
        p_fecha_factura,
        p_fecha_compra,
        p_tipo_compra,
        p_subtotal,
        p_iva,
        TRUE
    );

    SET v_id_compra = LAST_INSERT_ID();

    SELECT
        v_id_compra AS id,
        'Compra registrada correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`insertPagoProveedor`(
    IN p_id_compra INT UNSIGNED,
    IN p_id_forma_pago INT,
    IN p_importe DECIMAL(18,2)
)
    MODIFIES SQL DATA
    COMMENT 'Registra un pago asociado a una compra sin exceder su saldo pendiente'
BEGIN
    DECLARE v_existe_compra INT DEFAULT 0;
    DECLARE v_existe_forma_pago INT DEFAULT 0;
    DECLARE v_id_pago INT UNSIGNED DEFAULT 0;
    DECLARE v_tipo_compra BOOLEAN;

    DECLARE v_total_compra DECIMAL(18,2) DEFAULT 0;
    DECLARE v_total_pagado DECIMAL(18,2) DEFAULT 0;
    DECLARE v_saldo_pendiente DECIMAL(18,2) DEFAULT 0;

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

    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    IF p_id_forma_pago IS NULL OR p_id_forma_pago <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La forma de pago es obligatoria';
    END IF;

    IF p_importe IS NULL OR p_importe <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El importe del pago debe ser mayor a cero';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra
      AND activo = TRUE;

    IF v_existe_compra = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra indicada no existe o está inactiva';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_forma_pago
    FROM kath_erp.formas_de_pago
    WHERE id = p_id_forma_pago
      AND activo = TRUE;

    IF v_existe_forma_pago = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La forma de pago indicada no existe o está inactiva';
    END IF;

    /*
     * Se bloquea la compra durante el cálculo del saldo.
     * Esto también serializa pagos concurrentes sobre la
     * misma compra.
     */
    SELECT
        tipo_compra,
        ROUND(
            CAST(subtotal AS DECIMAL(18,2))
            + CAST(iva AS DECIMAL(18,2)),
            2
        )
    INTO
        v_tipo_compra,
        v_total_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra
    LIMIT 1
    FOR UPDATE;

    SELECT
        ROUND(
            COALESCE(
                SUM(CAST(importe AS DECIMAL(18,2))),
                0
            ),
            2
        )
    INTO v_total_pagado
    FROM kath_erp.pago_proveedor
    WHERE id_compra = p_id_compra;

    SET v_saldo_pendiente =
        ROUND(v_total_compra - v_total_pagado, 2);

    IF v_saldo_pendiente <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra ya se encuentra completamente pagada';
    END IF;

    IF p_importe > v_saldo_pendiente THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El pago supera el saldo pendiente de la compra';
    END IF;

    /*
     * Contrato actual:
     *
     * FALSE = Contado
     * TRUE  = Crédito
     *
     * Una compra de contado debe quedar totalmente
     * liquidada mediante su pago inicial.
     */
    IF v_tipo_compra = FALSE
       AND (
            v_total_pagado > 0
            OR ROUND(p_importe, 2) <> v_total_compra
       ) THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra de contado debe liquidarse en un solo pago por el importe total';
    END IF;

    INSERT INTO kath_erp.pago_proveedor (
        id_compra,
        id_forma_pago,
        importe
    )
    VALUES (
        p_id_compra,
        p_id_forma_pago,
        p_importe
    );

    SET v_id_pago = LAST_INSERT_ID();

    SELECT
        v_id_pago AS id,
        'Pago a proveedor registrado correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`listArticulosCompraById`(
    IN p_id_compra INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los artículos registrados en una compra'
BEGIN
    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    SELECT
        axc.id,
        axc.id_compra,
        axc.id_articulo,
        a.codigo_articulo,
        a.nombre AS nombre_articulo,
        axc.cantidad,
        axc.subtotal
    FROM kath_erp.articulo_x_compra AS axc
    INNER JOIN kath_erp.articulo AS a
        ON axc.id_articulo = a.id_articulo
    WHERE axc.id_compra = p_id_compra
    ORDER BY axc.id ASC;
END;

CREATE PROCEDURE `kath_erp`.`listCompras`(
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_fecha_factura_inicio DATE,
    IN p_fecha_factura_fin DATE,
    IN p_folio_factura VARCHAR(13),
    IN p_tipo_compra BOOLEAN
)
    READS SQL DATA
    COMMENT 'Lista compras activas pertenecientes a una sucursal con filtros opcionales'
BEGIN

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria para consultar compras';
    END IF;

    SELECT
        c.id_compra,
        c.id_empleado,
        c.id_proveedor,
        c.id_sucursal,
        c.folio_factura,
        c.fecha_factura,
        c.fecha_compra,
        c.tipo_compra,
        CASE
            WHEN c.tipo_compra = TRUE THEN 'Crédito'
            ELSE 'Contado'
        END AS tipo_compra_descripcion,
        c.subtotal,
        c.iva,
        (c.subtotal + c.iva) AS importe_total,
        c.activo
    FROM kath_erp.compras AS c
    WHERE c.activo = TRUE

      -- La sucursal deja de ser opcional.
      AND c.id_sucursal = p_id_sucursal

      AND (
            p_id_proveedor IS NULL
            OR p_id_proveedor = 0
            OR c.id_proveedor = p_id_proveedor
          )

      AND (
            p_fecha_factura_inicio IS NULL
            OR c.fecha_factura >= p_fecha_factura_inicio
          )

      AND (
            p_fecha_factura_fin IS NULL
            OR c.fecha_factura <= p_fecha_factura_fin
          )

      AND (
            p_folio_factura IS NULL
            OR TRIM(p_folio_factura) = ''
            OR c.folio_factura LIKE CONCAT('%', TRIM(p_folio_factura), '%')
          )

      AND (
            p_tipo_compra IS NULL
            OR c.tipo_compra = p_tipo_compra
          )

    ORDER BY
        c.fecha_compra DESC,
        c.id_compra DESC;

END;

CREATE PROCEDURE `kath_erp`.`sumarExistenciaSucursalCompra`(
    IN p_id_compra INT UNSIGNED,
    IN p_id_articulo INT UNSIGNED,
    IN p_cantidad INT
)
    MODIFIES SQL DATA
    COMMENT 'Suma existencia usando directamente la sucursal registrada en la compra'
BEGIN
    DECLARE v_id_sucursal BIGINT UNSIGNED DEFAULT 0;
    DECLARE v_id_existencia INT DEFAULT 0;
    DECLARE v_registros_existencia INT DEFAULT 0;

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

    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo es obligatorio';
    END IF;

    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad a sumar debe ser mayor a cero';
    END IF;


    /*
     * IMPORTANTE:
     * La sucursal sale directamente de compras.id_sucursal.
     *
     * Ya NO:
     * compras -> empleados -> sucursal
     */

    SELECT c.id_sucursal
    INTO v_id_sucursal
    FROM kath_erp.compras AS c
    WHERE c.id_compra = p_id_compra
      AND c.activo = TRUE
    LIMIT 1;

    IF v_id_sucursal IS NULL OR v_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se pudo determinar la sucursal de la compra';
    END IF;


    SELECT COUNT(*)
    INTO v_registros_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = p_id_articulo
      AND id_sucursal = v_id_sucursal;

    IF v_registros_existencia > 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Existe más de un registro de existencia para el artículo y sucursal';
    END IF;


    IF v_registros_existencia = 0 THEN

        INSERT INTO kath_erp.existencia_x_sucursal (
            id_articulo,
            id_sucursal,
            existencia
        )
        VALUES (
            p_id_articulo,
            v_id_sucursal,
            p_cantidad
        );

    ELSE

        SELECT id
        INTO v_id_existencia
        FROM kath_erp.existencia_x_sucursal
        WHERE id_articulo = p_id_articulo
          AND id_sucursal = v_id_sucursal
        LIMIT 1
        FOR UPDATE;

        UPDATE kath_erp.existencia_x_sucursal
        SET existencia =
            COALESCE(existencia, 0) + p_cantidad
        WHERE id = v_id_existencia;

    END IF;


    SELECT
        200 AS id,
        'Existencia actualizada correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`updateArticuloCompra`(
    IN p_id_detalle_compra INT UNSIGNED,
    IN p_cantidad INT,
    IN p_subtotal DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza un artículo comprado y ajusta existencia en la sucursal registrada en la compra'
BEGIN
    DECLARE v_id_compra INT UNSIGNED DEFAULT 0;
    DECLARE v_id_articulo INT UNSIGNED DEFAULT 0;
    DECLARE v_cantidad_actual INT DEFAULT 0;

    DECLARE v_delta INT DEFAULT 0;

    DECLARE v_existencia_actual INT DEFAULT 0;
    DECLARE v_id_existencia INT DEFAULT 0;

    DECLARE v_id_sucursal BIGINT UNSIGNED DEFAULT 0;

    DECLARE v_fecha_compra DATE;

    DECLARE v_ventas_posteriores INT DEFAULT 0;
    DECLARE v_registros_existencia INT DEFAULT 0;

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


    IF p_id_detalle_compra IS NULL
       OR p_id_detalle_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El detalle de compra es obligatorio';

    END IF;


    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero';

    END IF;


    IF p_subtotal IS NULL OR p_subtotal < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal no puede ser negativo';

    END IF;


    /*
     * Se obtiene directamente c.id_sucursal.
     * Ya no necesitamos JOIN empleados.
     */

    SELECT
        axc.id_compra,
        axc.id_articulo,
        axc.cantidad,
        c.fecha_compra,
        c.id_sucursal
    INTO
        v_id_compra,
        v_id_articulo,
        v_cantidad_actual,
        v_fecha_compra,
        v_id_sucursal
    FROM kath_erp.articulo_x_compra AS axc
    INNER JOIN kath_erp.compras AS c
        ON axc.id_compra = c.id_compra
    WHERE axc.id = p_id_detalle_compra
      AND c.activo = TRUE
    LIMIT 1
    FOR UPDATE;


    IF v_id_compra IS NULL OR v_id_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El detalle de compra no existe o la compra está inactiva';

    END IF;


    /*
     * Esta parte sigue dependiendo de cómo ventas determina su sucursal.
     *
     * Por ahora se mantiene porque no me compartiste una relación directa
     * ventas -> sucursal.
     */

    SELECT COUNT(*)
    INTO v_ventas_posteriores
    FROM kath_erp.articulo_x_venta AS axv
    INNER JOIN kath_erp.ventas AS v
        ON axv.id_venta = v.id_venta
    INNER JOIN kath_erp.empleados AS emp_venta
        ON v.id_empleado = emp_venta.id_empleado
    WHERE axv.id_articulo = v_id_articulo
      AND emp_venta.id_sucursal = v_id_sucursal
      AND v.fecha > v_fecha_compra
      AND v.status_venta = TRUE;


    IF v_ventas_posteriores > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede modificar el artículo porque ya tiene ventas posteriores a la compra';

    END IF;


    SELECT COUNT(*)
    INTO v_registros_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal;


    IF v_registros_existencia = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No existe registro de existencia para el artículo y sucursal';

    END IF;


    IF v_registros_existencia > 1 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Existe más de un registro de existencia para el artículo y sucursal';

    END IF;


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


    SET v_delta =
        p_cantidad - v_cantidad_actual;


    IF v_existencia_actual + v_delta < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede modificar la compra porque la existencia quedaría negativa';

    END IF;


    UPDATE kath_erp.articulo_x_compra
    SET
        cantidad = p_cantidad,
        subtotal = p_subtotal
    WHERE id = p_id_detalle_compra;


    UPDATE kath_erp.existencia_x_sucursal
    SET existencia =
        v_existencia_actual + v_delta
    WHERE id = v_id_existencia;


    SELECT
        p_id_detalle_compra AS id,
        'Artículo de compra actualizado correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`updateCompra`(
    IN p_id_compra INT UNSIGNED,
    IN p_id_empleado INT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_folio_factura VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_factura DATE,
    IN p_fecha_compra DATE,
    IN p_tipo_compra BOOLEAN,
    IN p_subtotal DOUBLE,
    IN p_iva DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza compra validando que pertenezca a la sucursal de la sesión. El update rehabilita el registro'
BEGIN

    DECLARE v_existe_compra INT DEFAULT 0;
    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_existe_proveedor INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;

    DECLARE v_folio_duplicado INT DEFAULT 0;

    DECLARE v_id_sucursal_compra BIGINT UNSIGNED DEFAULT 0;


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


    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';

    END IF;


    IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado es obligatorio';

    END IF;


    IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor es obligatorio';

    END IF;


    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';

    END IF;


    IF p_folio_factura IS NULL
       OR TRIM(p_folio_factura) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura es obligatorio';

    END IF;


    IF CHAR_LENGTH(TRIM(p_folio_factura)) > 13 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El folio de factura no puede exceder 13 caracteres';

    END IF;


    IF p_fecha_factura IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de factura es obligatoria';

    END IF;


    IF p_fecha_compra IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de compra es obligatoria';

    END IF;


    IF p_tipo_compra IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El tipo de compra es obligatorio';

    END IF;


    IF p_subtotal IS NULL OR p_subtotal < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal no puede ser negativo';

    END IF;


    IF p_iva IS NULL OR p_iva < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El IVA no puede ser negativo';

    END IF;


    /*
     * Obtener la sucursal histórica directamente de la compra.
     */

    SELECT
        COUNT(*),
        COALESCE(MAX(id_sucursal), 0)
    INTO
        v_existe_compra,
        v_id_sucursal_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra;


    IF v_existe_compra = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra indicada no existe';

    END IF;


    /*
     * No permitimos editar una compra perteneciente
     * a otra sucursal.
     */

    IF v_id_sucursal_compra <> p_id_sucursal THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra indicada no pertenece a la sucursal actual';

    END IF;


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


    SELECT COUNT(*)
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = p_id_proveedor;


    IF v_existe_proveedor = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El proveedor indicado no existe';

    END IF;


    SELECT COUNT(*)
    INTO v_folio_duplicado
    FROM kath_erp.compras
    WHERE id_proveedor = p_id_proveedor
      AND folio_factura = TRIM(p_folio_factura)
      AND id_compra <> p_id_compra
      AND activo = TRUE;


    IF v_folio_duplicado > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El folio de factura ya está registrado para este proveedor';

    END IF;


    /*
     * Importante:
     * id_sucursal NO se actualiza.
     *
     * La compra permanece ligada a la sucursal
     * en la que fue originalmente realizada.
     */

    UPDATE kath_erp.compras
    SET
        id_empleado = p_id_empleado,
        id_proveedor = p_id_proveedor,
        folio_factura = TRIM(p_folio_factura),
        fecha_factura = p_fecha_factura,
        fecha_compra = p_fecha_compra,
        tipo_compra = p_tipo_compra,
        subtotal = p_subtotal,
        iva = p_iva,
        activo = TRUE
    WHERE id_compra = p_id_compra
      AND id_sucursal = p_id_sucursal;


    SELECT
        p_id_compra AS id,
        'Compra actualizada correctamente' AS message;

END;