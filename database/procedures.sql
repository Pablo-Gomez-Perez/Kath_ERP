CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`actualizarPassWordEmpleado`(
    IN rfcEmpl VARCHAR(13),
    IN passwordE VARCHAR(255)
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza la contraseña hasheada de un empleado'
BEGIN
    UPDATE kath_erp.empleados
    SET contrasenia = passwordE
    WHERE rfc = rfcEmpl;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`bucar_forma_pago_por_id`(
	IN idFormaDePago INT
)
BEGIN
	
    SELECT * FROM formas_de_pago WHERE formas_de_pago.id = idFormaDePago;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_categoria_por_nombre`(IN `nombre` VARCHAR(60))
BEGIN
    SELECT 
		categoria_producto.id_categoria,
        categoria_producto.nombre,
		categoria_producto.descripcion,
        categoria_producto.activo
    FROM categoria_producto WHERE categoria_producto.nombre LIKE CONCAT('%',nombre,'%');
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_cliente_por_nombre`(
	IN `nombre` VARCHAR(30)
)
    READS SQL DATA
    COMMENT 'Busca clientes por nombre sin dependencias contables'
BEGIN

    SELECT
        c.id_cliente,
        c.rfc,
        c.nombre_completo,
        c.nombre_corto,
        c.correo_electronico,
        c.estado,
        c.ciudad,
        c.direccion,
        c.codigo_postal,
        c.activo
    FROM kath_erp.cliente AS c
    WHERE c.nombre_completo LIKE CONCAT('%', nombre, '%');

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_cuenta_x_clave`(
	IN `clave_cuenta` VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'busqueda de una cuenta contable por su clave'
BEGIN
	
	SELECT
		cc.id_cuenta,
		CASE WHEN cc.id_cuenta_padre IS NULL THEN 0 ELSE cc.id_cuenta_padre END AS 'id_cuenta_padre',
		cc.fk_id_rubro,
		rcc.fk_id_grupo_contable,
		cc.clave,
		cc.nombre,
		cc.descripcion,
		cc.nivel,
		cc.ultimo_nivel 
	FROM
		kath_erp.cuentas_contables AS cc
		INNER JOIN kath_erp.rubro_cuenta_contable rcc ON cc.fk_id_rubro = rcc.id_rubro
		WHERE cc.clave LIKE CONCAT('%',`clave_cuenta`) COLLATE utf8mb4_general_ci;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_cuenta_x_id`(
	IN `id_cuenta` INT
)
BEGIN
	
	SELECT
		_cc.id_cuenta,
		_cp.nombre AS `nombre_cuenta_padre`,
		_rcta.nombre AS `rubro_cuenta`,
		_cc.nombre AS `nombre_cuenta`,
		_cc.descripcion,
		_cc.nivel,
		_cc.ultimo_nivel,
		_cc.cargo,
		_cc.abono,
		(_cc.cargo - _cc.abono) AS `saldo`,
		_rcta.naturaleza
	FROM cuentas_contables AS _cc
	INNER JOIN cuentas_contables AS _cp ON _cc.id_cuenta_padre = _cp.id_cuenta 
	INNER JOIN rubro_cuenta_contable AS _rcta ON _cc.fk_id_rubro = _rcta.id_rubro
	WHERE _cc.id_cuenta = id_cuenta;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_empleado`(
	IN nombre_e VARCHAR(30)
)
BEGIN
	
	SELECT
		empleados.id_empleado,
		sucursal.nombre,
		empleados.rfc,
		empleados.curp,
		empleados.nombre_completo,
		empleados.nombre_corto,
		empleados.correo_electronico,
		empleados.activo
	FROM empleados
	INNER JOIN sucursal ON empleados.id_sucursal = sucursal.id_sucursar
    WHERE empleados.nombre_completo LIKE CONCAT('%',nombre_e,'%') ORDER BY id_empleado;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_empleado_por_nombre`(
	IN nombre VARCHAR(10)
)
BEGIN

	SELECT
		empleados.id_empleado,
        empleados.nombre_completo
	FROM empleados WHERE empleados.nombre_corto = nombre;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_proveedor_por_nombre`(
	IN nombre_prov VARCHAR(30)
)
BEGIN

	SELECT
		proveedor.id_proveedor,
		proveedor.rfc,
		sub_cuentas_tercer_nivel.clave,
		proveedor.nombre,
		proveedor.descripcion,
		proveedor.correo_electronico,
		proveedor.estado,
		proveedor.ciudad,
		proveedor.direccion,
		proveedor.codigo_postal,
        proveedor.activo
	FROM proveedor
	INNER JOIN sub_cuentas_tercer_nivel ON proveedor.id_cuenta_contable = sub_cuentas_tercer_nivel.id_cuenta
    WHERE proveedor.nombre LIKE CONCAT('%',nombre_prov,'%');
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_sucursal_por_id`(
	IN `id_sucursal` INT
)
BEGIN
SELECT id_sucursar,
       nombre,
       descripcion,
       telefono,
       email,
       estado,
       ciudad,
       direccion,
       codigo_postal,
       activo
FROM sucursal
WHERE sucursal.id_sucursar = id_sucursal;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_tipoCliente_por_id`(
	IN id_tipoCliente INT
)
BEGIN	
    SELECT
		tipo_cliente.id,
        tipo_cliente.nombre,
        tipo_cliente.descripcion
	FROM tipo_cliente
    WHERE tipo_cliente.id = id_tipoCliente;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`buscar_ultima_venta`()
BEGIN
	
    SELECT 
		ventas.id_venta
	FROM ventas ORDER BY ventas.id_venta DESC LIMIT 1;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`cancelVenta`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`cmb_tipoCliente`()
BEGIN
	SELECT
		tipo_cliente.id,
        tipo_cliente.nombre
	FROM tipo_cliente;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteArticuloCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteCategoriaProducto`(
	IN p_id_categoria INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Inhabilita una categoria de producto'
BEGIN
	
    DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_categoria_activa BOOLEAN DEFAULT FALSE;

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

	IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la categoria no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM categoria_producto
	WHERE id_categoria = p_id_categoria;

	IF v_existe_categoria = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria indicada no existe';
	END IF;

	SELECT activo
	INTO v_categoria_activa
	FROM categoria_producto
	WHERE id_categoria = p_id_categoria
	FOR UPDATE;

	IF v_categoria_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria ya se encuentra inactiva';
	END IF;

	UPDATE categoria_producto
	SET activo = FALSE
	WHERE id_categoria = p_id_categoria;

	COMMIT;

	SELECT
		200 AS id,
		'Categoria inhabilitada correctamente' AS message;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteCliente`(
	IN p_id_cliente INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Desactiva un cliente únicamente cuando no tiene saldo insoluto en ventas a crédito'
BEGIN

	DECLARE v_existe_cliente INT DEFAULT 0;
	DECLARE v_cliente_activo BOOLEAN DEFAULT FALSE;
	DECLARE v_saldo_pendiente DECIMAL(20,2) DEFAULT 0;

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

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del cliente no es válido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cliente
	FROM kath_erp.cliente
	WHERE id_cliente = p_id_cliente;

	IF v_existe_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente indicado no existe';
	END IF;

	SELECT activo
	INTO v_cliente_activo
	FROM kath_erp.cliente
	WHERE id_cliente = p_id_cliente
	FOR UPDATE;

	IF v_cliente_activo = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente ya se encuentra inactivo';
	END IF;

	/*
	 * Saldo insoluto del cliente:
	 *
	 * total de ventas vigentes a crédito
	 * - pagos registrados al momento de cada venta
	 * - cobros posteriores registrados en cobro_clientes.
	 *
	 * GREATEST evita que un eventual sobrepago histórico produzca
	 * un saldo negativo que compense otra venta pendiente.
	 */
	SELECT
		ROUND(
			COALESCE(
				SUM(
					GREATEST(
						CAST(v.importe_total AS DECIMAL(18,2))
						- COALESCE((
							SELECT SUM(CAST(pxv.importe AS DECIMAL(18,2)))
							FROM kath_erp.pagos_x_venta AS pxv
							WHERE pxv.id_venta = v.id_venta
						), 0)
						- COALESCE((
							SELECT SUM(CAST(cc.total AS DECIMAL(18,2)))
							FROM kath_erp.cobro_clientes AS cc
							WHERE cc.id_venta = v.id_venta
						), 0),
						0
					)
				),
				0
			),
			2
		)
	INTO v_saldo_pendiente
	FROM kath_erp.ventas AS v
	WHERE v.id_cliente = p_id_cliente
	  AND v.status_venta = TRUE
	  AND v.tipo_venta = FALSE;

	IF v_saldo_pendiente > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar el cliente porque tiene saldo pendiente en ventas a crédito';
	END IF;

	UPDATE kath_erp.cliente
	SET activo = FALSE
	WHERE id_cliente = p_id_cliente;

	COMMIT;

	SELECT
		200 AS id,
		'Cliente desactivado correctamente' AS message;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteProveedor`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteTelefonoCliente`(
	IN p_id_telefono INT
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un telefono asociado a un cliente'
BEGIN
	
	DECLARE v_existe_telefono INT DEFAULT 0;

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

	IF p_id_telefono IS NULL OR p_id_telefono <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del telefono no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_cliente
	WHERE id_telefono = p_id_telefono;

	IF v_existe_telefono = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado no existe';
	END IF;

	DELETE FROM telefono_x_cliente
	WHERE id_telefono = p_id_telefono;

	COMMIT;

	SELECT
		200 AS id,
		'Telefono eliminado correctamente' AS message;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteTelefonoEmpleado`(
	IN p_id_telefono INT
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un numero telefonico asociado a un empleado'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_numero_existe INT;
	
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
	
	SELECT COUNT(*) INTO v_numero_existe FROM kath_erp.telefono_x_empleado AS txe WHERE txe.id_telefono = p_id_telefono;
	
	IF v_numero_existe = 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El numero indicado no existe o es erroneo';
	END IF;
	
	
	DELETE FROM kath_erp.telefono_x_empleado WHERE id_telefono = p_id_telefono;
	
	SELECT 200 AS id, 'Numero telefonico eliminado correctamente' AS message;
	
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`deleteTelefonoProveedor`(
	IN p_id_telefono INT
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un telefono asociado a un proveedor'
BEGIN
	
	DECLARE v_existe_telefono INT DEFAULT 0;

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

	IF p_id_telefono IS NULL OR p_id_telefono <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del telefono no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_proveedor
	WHERE id_telefono = p_id_telefono;

	IF v_existe_telefono = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado no existe';
	END IF;

	DELETE FROM telefono_x_proveedor
	WHERE id_telefono = p_id_telefono;

	COMMIT;

	SELECT
		200 AS id,
		'Telefono eliminado correctamente' AS message;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`delete_cuenta_contable`(
	IN p_id_cuenta INT
)
    MODIFIES SQL DATA
    COMMENT 'Desactiva una cuenta contable con validaciones operativas'
BEGIN
	DECLARE v_cuenta_existe INT DEFAULT 0;
	DECLARE v_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_hijas_activas INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4
		COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

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

	START TRANSACTION;

	IF p_id_cuenta IS NULL OR p_id_cuenta <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la cuenta es inválido';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_existe
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta;

	IF v_cuenta_existe = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable no existe';
	END IF;

	SELECT
		activa,
		cargo,
		abono
	INTO
		v_activa,
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta
	FOR UPDATE;

	IF v_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable ya se encuentra inactiva';
	END IF;

	/*
	 * Bloquea cuentas que ya registraron movimientos,
	 * aunque su saldo actual sea cero.
	 */
	IF v_cargo <> 0 OR v_abono <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar una cuenta con movimientos contables';
	END IF;

	SELECT COUNT(*)
	INTO v_hijas_activas
	FROM cuentas_contables
	WHERE id_cuenta_padre = p_id_cuenta
	  AND activa = TRUE;

	IF v_hijas_activas > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar una cuenta con subcuentas activas';
	END IF;

	UPDATE cuentas_contables
	SET
		activa = FALSE,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = p_id_cuenta;

	COMMIT;

	SELECT
		p_id_cuenta AS id,
		'Cuenta contable desactivada correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`delete_empleado`(
    IN p_id_empleado INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Desactiva un empleado'
BEGIN
    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_empleado_activo BOOLEAN DEFAULT FALSE;

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

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    START TRANSACTION;

    IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El identificador del empleado no es válido';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado;

    IF v_existe_empleado = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado no existe';
    END IF;

    SELECT activo
    INTO v_empleado_activo
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
    FOR UPDATE;

    IF v_empleado_activo = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado ya se encuentra inactivo';
    END IF;

    UPDATE kath_erp.empleados
    SET activo = FALSE
    WHERE id_empleado = p_id_empleado;

    COMMIT;

    SELECT
        p_id_empleado AS id,
        'Empleado desactivado correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`eliminar_articulo`(
	IN id INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := articulo.activo FROM articulo WHERE articulo.id_articulo = id;
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El articulo ya se encuentra inactivo';
    END IF;
    
    UPDATE articulo SET
		activo = 0
	WHERE articulo.id_articulo = id;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`eliminar_forma_pago`(
	IN idFormaPago INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := formas_de_pago.activo FROM formas_de_pago WHERE formas_de_pago.id = idFormaPago;
    
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000'  SET MESSAGE_TEXT = 'La forma de pago ya se encuentra inactiva';
    END IF;
    
    UPDATE formas_de_pago SET
		activo = 0
    WHERE id = idFormaPago;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`eliminar_sucursal`(
	IN idSucursal INT
)
BEGIN
	
    
    UPDATE sucursal SET
		activo = 0
	WHERE sucursal.id_sucursar = idSucursal;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`eliminar_tipoCliente`(
	IN id_tipoCliente INT
)
BEGIN
	
    UPDATE tipo_cliente SET
		tipo_cliente.activo = 0
	WHERE tipo_cliente.id = id_tipoCliente;
    
    SELECT 200 AS id, 'Tipo Cliente inhabilitado exitosamente' AS message;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`finalizarVenta`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getArticuloByCodigo`(
	IN codigo_a VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN idSucursal INT,
    IN idTipoCliente INT
)
    READS SQL DATA
    COMMENT 'Consulta el detalle de un articulo por su codigo fijando el precio por el tipo de cliente'
BEGIN
    
    SELECT     
    	exs.id_articulo,
    	a.id_proveedor,
    	a.id_categoria,
    	a.codigo_articulo,
    	a.codigo_sat,
    	a.nombre,
    	a.descripcion,
    	a.es_exento AS exento,
    	a.costo_unitario,
    	pxt.cant_p_precioEspecial,
    	pxt.precio,
    	pxt.precios_especial,
    	a.activo,
    	exs.existencia    	
    FROM
   		kath_erp.existencia_x_sucursal AS exs 
   		INNER JOIN kath_erp.articulo AS a ON exs.id_articulo = a.id_articulo
   		INNER JOIN kath_erp.precios_x_tipocliente AS pxt ON exs.id_articulo = pxt.id_articulo
    WHERE
    	a.activo = TRUE 
    	AND a.codigo_articulo = `codigo_a` 
    	AND exs.id_sucursal = `idSucursal`
    	AND pxt.id_tipoCliente = `idTipoCliente`;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getArticuloById`(
	IN p_id_articulo INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Consulta el detalle de un articulo por su id para edicion'
BEGIN
	
	IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del articulo no es valido';
	END IF;

	SELECT
		art.id_articulo,
		art.id_proveedor,		
		art.id_categoria,		
		art.codigo_articulo,
		art.codigo_sat,
		art.unidad_sat,
		art.nombre,
		art.descripcion,
		art.es_exento,
		art.costo_unitario,
		art.activo
	FROM kath_erp.articulo AS art	
	WHERE art.id_articulo = p_id_articulo;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getCategoriaById`(
	IN `p_id_categoria` INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'CONSULTA EL DETALLE DE UNA CATEGORIA DE PRODUCTO POR SU ID'
BEGIN

    SELECT
	    cp.id_categoria,
	    cp.nombre,
	    cp.descripcion,
	    cp.activo
    FROM kath_erp.categoria_producto AS cp
    WHERE cp.id_categoria = `p_id_categoria`;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getClienteById`(
	IN p_idCliente INT
)
    READS SQL DATA
    COMMENT 'Busca un cliente mediante su ID y retorna sus datos operativos'
BEGIN

	SELECT
		c.id_cliente,
		c.id_tipoCliente,
		c.rfc,
		c.nombre_completo,
		c.nombre_corto,
		c.fecha_nac,
		c.correo_electronico,
		c.estado,
		c.ciudad,
		c.direccion,
		c.codigo_postal,
		c.activo
	FROM kath_erp.cliente AS c
	WHERE c.id_cliente = p_idCliente;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getClienteParaVentaById`(
	IN id_cliente INT
)
    READS SQL DATA
    COMMENT 'Consulta los datos operativos de un cliente mediante su ID para el punto de ventas'
BEGIN

	SELECT
		c.id_cliente,
		c.id_tipoCliente,
		tc.nombre AS tipo_cliente,
		c.rfc,
		c.nombre_completo,
		c.nombre_corto,
		c.fecha_nac,
		c.correo_electronico,
		c.estado,
		c.ciudad,
		c.direccion,
		c.codigo_postal,
		c.activo
	FROM kath_erp.cliente AS c
	INNER JOIN kath_erp.tipo_cliente AS tc
		ON c.id_tipoCliente = tc.id
	WHERE c.id_cliente = id_cliente;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getCompraById`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getConfiguracionFiscal`()
    READS SQL DATA
BEGIN

    SELECT
        id_configuracion,
        rfc_emisor,
        nombre_razon_social,
        nombre_comercial,
        regimen_fiscal_clave,
        regimen_fiscal_descripcion,
        numero_registro_sistema,
        activo
    FROM kath_erp.configuracion_fiscal
    WHERE activo = TRUE
    ORDER BY id_configuracion ASC
    LIMIT 1;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getEmpleadoById`(
    IN id_empleado INT
)
    READS SQL DATA
    COMMENT 'Consulta un empleado por su id'
BEGIN

    SELECT
        em.id_empleado,
        em.id_sucursal,
        em.rfc,
        em.curp,
        em.nombre_completo,
        em.nombre_corto,
        em.fecha_nac,
        em.correo_electronico,
        em.estado,
        em.ciudad,
        em.direccion,
        em.codigo_postal,
        em.activo
    FROM kath_erp.empleados AS em
    WHERE em.id_empleado = id_empleado;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getEmpleadoByRFC`(
    IN rfc_empleado VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Consulta los datos de un empleado por su RFC'
BEGIN

    SELECT
        em.id_empleado,
        em.id_sucursal,
        em.rfc,
        em.curp,
        em.nombre_completo,
        em.nombre_corto,
        em.fecha_nac,
        em.correo_electronico,
        em.estado,
        em.ciudad,
        em.direccion,
        em.codigo_postal,
        em.activo
    FROM kath_erp.empleados AS em
    WHERE em.rfc = rfc_empleado;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getEmpleadoLogin`(
    IN p_nombre_corto VARCHAR(10)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Obtiene empleado activo por nombre corto para validación de login en Java'
BEGIN
    IF p_nombre_corto IS NULL OR TRIM(p_nombre_corto) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El usuario es obligatorio';
    END IF;

    SELECT
        e.id_empleado,
        e.id_sucursal,
        s.nombre AS nombre_sucursal,
        e.rfc,
        e.curp,
        e.nombre_completo,
        e.nombre_corto,
        e.fecha_nac,
        e.correo_electronico,
        e.estado,
        e.ciudad,
        e.direccion,
        e.codigo_postal,
        e.contrasenia AS contrasenia_hash,
        e.activo
    FROM kath_erp.empleados AS e
    INNER JOIN kath_erp.sucursal AS s
        ON e.id_sucursal = s.id_sucursar
    WHERE e.nombre_corto = TRIM(p_nombre_corto)
      AND e.activo = TRUE
    LIMIT 1;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getIdUltimaCompra`()
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getListadoEmpleados`(
    IN nombre_empleado VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Obtiene un listado de empleados registrados y filtra por nombre'
BEGIN

    SELECT
        em.id_empleado,
        em.rfc,
        em.curp,
        em.nombre_completo,
        em.nombre_corto,
        em.correo_electronico,
        em.activo
    FROM kath_erp.empleados AS em
    WHERE em.nombre_completo LIKE CONCAT('%', nombre_empleado, '%');

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getProveedorById`(
    IN idProveedor INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Consulta los detalles operativos de un proveedor por su ID'
BEGIN
    SELECT
        p.id_proveedor,
        p.rfc,
        p.nombre,
        p.descripcion,
        p.correo_electronico,
        p.estado,
        p.ciudad,
        p.direccion,
        p.codigo_postal,
        p.activo
    FROM kath_erp.proveedor AS p
    WHERE p.id_proveedor = idProveedor;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getTicketVentaById`(
    IN p_id_venta INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Obtiene la informacion fiscal, partidas y pagos necesarios para generar el ticket de una venta'
BEGIN

    DECLARE v_existe_venta INT DEFAULT 0;
    DECLARE v_configuraciones_fiscales INT DEFAULT 0;

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


    /*
     * Para evitar emitir documentos con información fiscal
     * ambigua debe existir exactamente una configuración activa.
     */
    SELECT COUNT(*)
    INTO v_configuraciones_fiscales
    FROM kath_erp.configuracion_fiscal
    WHERE activo = TRUE;


    IF v_configuraciones_fiscales = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No existe una configuracion fiscal activa para generar el ticket';
    END IF;


    IF v_configuraciones_fiscales > 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Existe mas de una configuracion fiscal activa';
    END IF;


    /*
     * RESULT SET 1
     * Cabecera de la venta, datos fiscales del emisor
     * y establecimiento de expedición.
     */
    SELECT
        v.id_venta AS folio,
        v.fecha AS fecha_venta,

        CASE
            WHEN v.tipo_venta = TRUE THEN 'Contado'
            ELSE 'Crédito'
        END AS tipo_venta,

        CASE
            WHEN v.status_venta = TRUE THEN 'Vigente'
            ELSE 'Cancelada'
        END AS status_venta,

        emp.nombre_completo AS empleado,

        cli.nombre_completo AS cliente,
        cli.rfc AS cliente_rfc,

        cf.rfc_emisor AS rfc_emisor,
        cf.nombre_razon_social AS emisor_razon_social,
        cf.nombre_comercial AS emisor_nombre_comercial,

        CONCAT(
            cf.regimen_fiscal_clave,
            ' - ',
            cf.regimen_fiscal_descripcion
        ) AS emisor_regimen_fiscal,

        cf.numero_registro_sistema AS numero_registro_sistema,

        suc.nombre AS sucursal_nombre,
        suc.direccion AS sucursal_direccion,
        suc.ciudad AS sucursal_ciudad,
        suc.estado AS sucursal_estado,
        suc.codigo_postal AS sucursal_codigo_postal,
        suc.telefono AS sucursal_telefono,
        suc.email AS sucursal_email,

        ROUND(
            CAST(v.subtotal AS DECIMAL(18,2)),
            2
        ) AS subtotal,

        ROUND(
            CAST(v.iva AS DECIMAL(18,2)),
            2
        ) AS iva,

        ROUND(
            CAST(v.importe_total AS DECIMAL(18,2)),
            2
        ) AS total

    FROM kath_erp.ventas AS v

    INNER JOIN kath_erp.empleados AS emp
        ON emp.id_empleado = v.id_empleado

    INNER JOIN kath_erp.cliente AS cli
        ON cli.id_cliente = v.id_cliente

    INNER JOIN kath_erp.sucursal AS suc
        ON suc.id_sucursar = v.id_sucursal

    INNER JOIN kath_erp.configuracion_fiscal AS cf
        ON cf.activo = TRUE

    WHERE v.id_venta = p_id_venta

    LIMIT 1;


    /*
     * RESULT SET 2
     * Partidas que integran la venta.
     *
     * articulo_x_venta.subtotal conserva la misma semántica
     * utilizada actualmente por finalizarVenta:
     *
     * - Exento: subtotal = importe final.
     * - Gravado: subtotal = base antes de IVA.
     *
     * Por ello se reconstruye el importe bruto exactamente con
     * la misma regla del módulo actual.
     */
    SELECT
        detalle.codigo_articulo,
        detalle.unidad,
        detalle.descripcion,
        detalle.cantidad,

        ROUND(
            detalle.importe / NULLIF(detalle.cantidad, 0),
            2
        ) AS precio_unitario,

        detalle.importe

    FROM (
        SELECT
            axv.id,

            a.codigo_articulo,

            COALESCE(
                a.unidad_sat,
                ''
            ) AS unidad,

            COALESCE(
                NULLIF(a.descripcion, ''),
                NULLIF(a.nombre, ''),
                a.codigo_articulo
            ) AS descripcion,

            axv.cantidad,

            CASE
                WHEN a.es_exento = TRUE THEN
                    ROUND(
                        CAST(axv.subtotal AS DECIMAL(18,2)),
                        2
                    )

                ELSE
                    ROUND(
                        CAST(axv.subtotal AS DECIMAL(18,2))
                        +
                        ROUND(
                            CAST(axv.subtotal AS DECIMAL(18,2)) * 0.16,
                            2
                        ),
                        2
                    )
            END AS importe

        FROM kath_erp.articulo_x_venta AS axv

        INNER JOIN kath_erp.articulo AS a
            ON a.id_articulo = axv.id_articulo

        WHERE axv.id_venta = p_id_venta

    ) AS detalle

    ORDER BY detalle.id ASC;


    /*
     * RESULT SET 3
     * Formas de pago efectivamente aplicadas a la venta.
     *
     * El cambio entregado al cliente no aparece aquí porque,
     * correctamente, no forma parte de pagos_x_venta.
     */
    SELECT
        fp.tipo_de_pago AS forma_pago,

        ROUND(
            CAST(pxv.importe AS DECIMAL(18,2)),
            2
        ) AS importe

    FROM kath_erp.pagos_x_venta AS pxv

    INNER JOIN kath_erp.formas_de_pago AS fp
        ON fp.id = pxv.id_forma_pago

    WHERE pxv.id_venta = p_id_venta

    ORDER BY pxv.id ASC;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getVentaById`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertArticulo`(
	IN p_id_proveedor INT UNSIGNED,
	IN p_id_categoria INT UNSIGNED,
	IN p_codigo_articulo VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_sat VARCHAR(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_unidad_sat VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(555) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_es_exento TINYINT,
	IN p_costo_unitario DECIMAL(18,2)
)
    MODIFIES SQL DATA
    COMMENT 'Inserta un articulo base y devuelve el id generado'
BEGIN
	
    DECLARE v_id_articulo INT UNSIGNED;
	DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_codigo_duplicado INT DEFAULT 0;

	IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor no es valido';
	END IF;

	IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria no es valida';
	END IF;

	IF p_codigo_articulo IS NULL OR TRIM(p_codigo_articulo) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El codigo del articulo es obligatorio';
	END IF;

	IF p_codigo_sat IS NULL OR TRIM(p_codigo_sat) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El codigo SAT es obligatorio';
	END IF;

	IF p_unidad_sat IS NULL OR TRIM(p_unidad_sat) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La unidad SAT es obligatoria';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre del articulo es obligatorio';
	END IF;

	IF p_es_exento IS NULL OR p_es_exento NOT IN (0, 1) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El valor de exento no es valido';
	END IF;

	IF p_costo_unitario IS NULL OR p_costo_unitario < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El costo unitario no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_proveedor
	FROM kath_erp.proveedor
	WHERE id_proveedor = p_id_proveedor
	  AND activo = 1;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor no existe o esta inactivo';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM kath_erp.categoria_producto
	WHERE id_categoria = p_id_categoria
	  AND activo = 1;

	IF v_existe_categoria = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria no existe o esta inactiva';
	END IF;

	SELECT COUNT(*)
	INTO v_codigo_duplicado
	FROM kath_erp.articulo
	WHERE codigo_articulo COLLATE utf8mb4_general_ci = TRIM(p_codigo_articulo) COLLATE utf8mb4_general_ci;

	IF v_codigo_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El codigo del articulo ya esta registrado';
	END IF;

	INSERT INTO kath_erp.articulo (
		id_proveedor,
		id_categoria,
		codigo_articulo,
		codigo_sat,
		unidad_sat,
		nombre,
		descripcion,
		es_exento,
		costo_unitario,
		activo
	) VALUES (
		p_id_proveedor,
		p_id_categoria,
		UPPER(TRIM(p_codigo_articulo)),
		TRIM(p_codigo_sat),
		UPPER(TRIM(p_unidad_sat)),
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		p_es_exento,
		p_costo_unitario,
		1
	);

	SET v_id_articulo = LAST_INSERT_ID();

	SELECT
		v_id_articulo AS id,
		'Articulo registrado correctamente' AS message;

	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertArticuloCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertArticuloVenta`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertCategoriaProducto`(
	IN p_nombre VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra una nueva categoria de producto'
BEGIN

	DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_id_categoria INT DEFAULT 0;

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

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la categoria es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM categoria_producto
	WHERE nombre COLLATE utf8mb4_general_ci = TRIM(p_nombre);

	IF v_existe_categoria > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe una categoria con el nombre indicado';
	END IF;

	INSERT INTO categoria_producto (
		nombre,
		descripcion,
		activo
	) VALUES (
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		TRUE
	);

	SET v_id_categoria = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Categoria registrada correctamente' AS message;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertCliente`(
	IN p_id_tipoCliente INT,
	IN p_rfc VARCHAR(13)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_completo VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_corto VARCHAR(10)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_fecha_nac DATE,
	IN p_correo_electronico VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo cliente sin dependencias contables'
BEGIN

	DECLARE v_existe_tipo_cliente INT DEFAULT 0;
	DECLARE v_rfc_duplicado INT DEFAULT 0;

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

		ROLLBACK;

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

	START TRANSACTION;

	IF p_id_tipoCliente IS NULL OR p_id_tipoCliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente es obligatorio';
	END IF;

	IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC es obligatorio';
	END IF;

	IF p_nombre_completo IS NULL
			OR TRIM(p_nombre_completo) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre completo es obligatorio';
	END IF;

	IF p_nombre_corto IS NULL
			OR TRIM(p_nombre_corto) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre corto es obligatorio';
	END IF;

	IF p_fecha_nac IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento es obligatoria';
	END IF;

	IF p_fecha_nac > CURDATE() THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
	END IF;

	IF p_correo_electronico IS NULL
			OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El correo electrónico es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_tipo_cliente
	FROM kath_erp.tipo_cliente
	WHERE id = p_id_tipoCliente;

	IF v_existe_tipo_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente indicado no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_rfc_duplicado
	FROM kath_erp.cliente
	WHERE rfc = UPPER(TRIM(p_rfc));

	IF v_rfc_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC ya está registrado';
	END IF;

	INSERT INTO kath_erp.cliente (
		id_tipoCliente,
		rfc,
		nombre_completo,
		nombre_corto,
		fecha_nac,
		correo_electronico,
		estado,
		ciudad,
		direccion,
		codigo_postal,
		activo
	)
	VALUES (
		p_id_tipoCliente,
		UPPER(TRIM(p_rfc)),
		TRIM(p_nombre_completo),
		TRIM(p_nombre_corto),
		p_fecha_nac,
		LOWER(TRIM(p_correo_electronico)),
		NULLIF(TRIM(p_estado), ''),
		NULLIF(TRIM(p_ciudad), ''),
		NULLIF(TRIM(p_direccion), ''),
		NULLIF(TRIM(p_codigo_postal), ''),
		TRUE
	);

	COMMIT;

	SELECT
		200 AS id,
		'Cliente registrado correctamente' AS message;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertExistenciaArticuloSucursal`(
	IN p_id_articulo INT UNSIGNED,
	IN p_id_sucursal BIGINT UNSIGNED,
	IN p_existencia INT
)
    MODIFIES SQL DATA
    COMMENT 'Inserta existencia inicial de un articulo por sucursal'
BEGIN
	DECLARE v_existe_articulo INT DEFAULT 0;
	DECLARE v_existe_sucursal INT DEFAULT 0;
	DECLARE v_existe_relacion INT DEFAULT 0;

	IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no es valido';
	END IF;

	IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La sucursal no es valida';
	END IF;

	IF p_existencia IS NULL OR p_existencia < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La existencia no es valida';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_articulo
	FROM kath_erp.articulo
	WHERE id_articulo = p_id_articulo;

	IF v_existe_articulo = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_sucursal
	FROM kath_erp.sucursal
	WHERE id_sucursar = p_id_sucursal;

	IF v_existe_sucursal = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La sucursal no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_relacion
	FROM kath_erp.existencia_x_sucursal
	WHERE id_articulo = p_id_articulo
	  AND id_sucursal = p_id_sucursal;

	IF v_existe_relacion > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La existencia del articulo ya existe para esta sucursal';
	END IF;

	INSERT INTO kath_erp.existencia_x_sucursal (
		id_articulo,
		id_sucursal,
		existencia
	) VALUES (
		p_id_articulo,
		p_id_sucursal,
		p_existencia
	);

	SELECT
		LAST_INSERT_ID() AS id,
		'Existencia registrada correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertPagoProveedor`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertPagoVenta`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertPrecioArticuloTipoCliente`(
	IN p_id_articulo INT UNSIGNED,
	IN p_id_tipo_cliente INT,
	IN p_precio DECIMAL(18,2),
	IN p_precio_especial DECIMAL(18,2),
	IN p_cantidad_precio_especial INT
)
    MODIFIES SQL DATA
    COMMENT 'Inserta precio de articulo por tipo de cliente'
BEGIN
	DECLARE v_existe_articulo INT DEFAULT 0;
	DECLARE v_existe_tipo_cliente INT DEFAULT 0;
	DECLARE v_existe_relacion INT DEFAULT 0;

	IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no es valido';
	END IF;

	IF p_id_tipo_cliente IS NULL OR p_id_tipo_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente no es valido';
	END IF;

	IF p_precio IS NULL OR p_precio < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El precio no es valido';
	END IF;

	IF p_precio_especial IS NOT NULL AND p_precio_especial < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El precio especial no es valido';
	END IF;

	IF p_cantidad_precio_especial IS NOT NULL AND p_cantidad_precio_especial <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cantidad minima para precio especial no es valida';
	END IF;

	IF p_precio_especial IS NULL AND p_cantidad_precio_especial IS NOT NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No debe existir cantidad minima si no existe precio especial';
	END IF;

	IF p_precio_especial IS NOT NULL AND p_cantidad_precio_especial IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Debe existir cantidad minima si existe precio especial';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_articulo
	FROM kath_erp.articulo
	WHERE id_articulo = p_id_articulo;

	IF v_existe_articulo = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_tipo_cliente
	FROM kath_erp.tipo_cliente
	WHERE id = p_id_tipo_cliente
	  AND activo = 1;

	IF v_existe_tipo_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente no existe o esta inactivo';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_relacion
	FROM kath_erp.precios_x_tipocliente
	WHERE id_articulo = p_id_articulo
	  AND id_tipoCliente = p_id_tipo_cliente;

	IF v_existe_relacion > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo ya tiene precio para este tipo de cliente';
	END IF;

	INSERT INTO kath_erp.precios_x_tipocliente (
		id_articulo,
		id_tipoCliente,
		precio,
		precios_especial,
		cant_p_precioEspecial
	) VALUES (
		p_id_articulo,
		p_id_tipo_cliente,
		p_precio,
		p_precio_especial,
		p_cantidad_precio_especial
	);

	SELECT
		LAST_INSERT_ID() AS id,
		'Precio registrado correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertProveedor`(
    IN p_rfc VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_correo_electronico VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_estado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_ciudad VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_direccion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_postal VARCHAR(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo proveedor sin dependencias contables'
BEGIN
    DECLARE v_existe_rfc INT DEFAULT 0;

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

    IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC del proveedor es obligatorio';
    END IF;

    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre del proveedor es obligatorio';
    END IF;

    IF p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El correo electronico del proveedor es obligatorio';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_rfc
    FROM kath_erp.proveedor
    WHERE rfc COLLATE utf8mb4_general_ci = UPPER(TRIM(p_rfc));

    IF v_existe_rfc > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe un proveedor registrado con el RFC indicado';
    END IF;

    INSERT INTO kath_erp.proveedor (
        rfc,
        nombre,
        descripcion,
        correo_electronico,
        estado,
        ciudad,
        direccion,
        codigo_postal,
        activo
    ) VALUES (
        UPPER(TRIM(p_rfc)),
        TRIM(p_nombre),
        NULLIF(TRIM(p_descripcion), ''),
        TRIM(p_correo_electronico),
        NULLIF(TRIM(p_estado), ''),
        NULLIF(TRIM(p_ciudad), ''),
        NULLIF(TRIM(p_direccion), ''),
        NULLIF(TRIM(p_codigo_postal), ''),
        TRUE
    );

    COMMIT;

    SELECT
        200 AS id,
        'Proveedor registrado correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertSucursal`(
	IN nombre VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN email VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN estado VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN ciudad VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN direccion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN codigo_postal VARCHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra una nueva sucurlar junto con su respectivo catalogo de productos'
BEGIN
	
    DECLARE v_id_ultima_sucursal INT;
    
    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
	    GET DIAGNOSTICS CONDITION 1
	    v_sqlstate = RETURNED_SQLSTATE,
	    v_errno = MYSQL_ERRNO,
	    v_text = MESSAGE_TEXT;
    
    	SELECT
    		500 AS id,
    		CONCAT('Error ', `v_errno`,' (', `v_sqlstate`, '): ', `v_text`) AS message;
    
		ROLLBACK;        
    END;
    
    START TRANSACTION;
		
        INSERT INTO kath_erp.sucursal(
			nombre,
			descripcion,
			telefono,
			email,
			estado,
			ciudad,
			direccion,
			codigo_postal,
            activo
		)VALUES(
			nombre,
			descripcion,
			telefono,
			email,
			estado,
			ciudad,
			direccion,
			codigo_postal,
            1
		);
        
        SELECT
			s,id_sucursar
		INTO
			v_id_ultima_sucursal
		FROM kath_erp.sucursal AS s
        ORDER BY s.id_sucursar DESC LIMIT 1;
        
        INSERT INTO kath_erp.existencia_x_sucursal(
			id_articulo,
            id_sucursal,
            existencia
        )
        SELECT id_articulo, v_id_ultima_sucursal,0 FROM kath_erp.articulo;
        
    COMMIT;
    
    SELECT 200 AS id, 'Sucursal registrada existosamente' AS message;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertTelefonoCliente`(
	IN p_id_cliente INT UNSIGNED,
	IN p_telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un telefono asociado a un cliente'
BEGIN
	
	DECLARE v_id_telefono INT DEFAULT 0;
	DECLARE v_existe_cliente INT DEFAULT 0;
	DECLARE v_existe_telefono INT DEFAULT 0;

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

	IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del cliente no es valido';
	END IF;

	IF p_telefono IS NULL OR TRIM(p_telefono) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono es obligatorio';
	END IF;

	IF CHAR_LENGTH(TRIM(p_telefono)) <> 10 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono debe contener 10 digitos';
	END IF;

	IF TRIM(p_telefono) NOT REGEXP '^[0-9]{10}$' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono solo debe contener numeros';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cliente
	FROM cliente
	WHERE id_cliente = p_id_cliente;

	IF v_existe_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente indicado no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_cliente
	WHERE telefono = TRIM(p_telefono);

	IF v_existe_telefono > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado ya se encuentra registrado';
	END IF;

	INSERT INTO telefono_x_cliente (
		id_cliente,
		telefono
	) VALUES (
		p_id_cliente,
		TRIM(p_telefono)
	);

	SET v_id_telefono = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Telefono registrado correctamente' AS message;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertTelefonoEmpleado`(
	IN p_id_empleado INT,
	IN p_telefono_empleado VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci 
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo numero telefonico asociado a un empleado'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	
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
		
	INSERT INTO kath_erp.telefono_x_empleado (
		id_empleado,
		telefono 
	)VALUES(
		p_id_empleado,
		p_telefono_empleado
	);
		
	SELECT 200 AS id, 'Numero registrado exitosamente' AS message;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertTelefonoProveedor`(
	IN p_id_proveedor INT UNSIGNED,
	IN p_telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un telefono asociado a un proveedor'
BEGIN
	
	DECLARE v_id_telefono INT DEFAULT 0;
	DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_existe_telefono INT DEFAULT 0;

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

	IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del proveedor no es valido';
	END IF;

	IF p_telefono IS NULL OR TRIM(p_telefono) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono es obligatorio';
	END IF;

	IF CHAR_LENGTH(TRIM(p_telefono)) <> 10 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono debe contener 10 digitos';
	END IF;

	IF TRIM(p_telefono) NOT REGEXP '^[0-9]{10}$' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono solo debe contener numeros';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_proveedor
	FROM proveedor
	WHERE id_proveedor = p_id_proveedor;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor indicado no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_proveedor
	WHERE telefono COLLATE utf8mb4_general_ci = TRIM(p_telefono);

	IF v_existe_telefono > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado ya se encuentra registrado';
	END IF;

	INSERT INTO telefono_x_proveedor (
		id_proveedor,
		telefono
	) VALUES (
		p_id_proveedor,
		TRIM(p_telefono)
	);

	SET v_id_telefono = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Telefono registrado correctamente' AS message;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertVenta`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insert_cuenta_contable`(
	IN p_id_cuenta_padre INT,
	IN p_id_rubro INT,
	IN p_clave VARCHAR(25)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(255)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(555)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ultimo_nivel BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Creacion de una nueva cuenta contable'
BEGIN 

DECLARE v_cuenta_existente INT DEFAULT 0;
	DECLARE v_rubro_existente INT DEFAULT 0;
	DECLARE v_nivel TINYINT DEFAULT 1;
	DECLARE v_padre_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_padre_existente INT DEFAULT 0;
	DECLARE v_id_cuenta INT DEFAULT 0;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			@sqlstate = RETURNED_SQLSTATE,
			@errno = MYSQL_ERRNO,
			@text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', @errno, ' (', @sqlstate, '): ', @text) AS message;
	END;

	START TRANSACTION;

	IF p_clave IS NULL OR TRIM(p_clave) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La clave contable es obligatoria';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la cuenta es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_existente
	FROM cuentas_contables
	WHERE clave = TRIM(p_clave);

	IF v_cuenta_existente > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable ya existe';
	END IF;

	SELECT COUNT(*)
	INTO v_rubro_existente
	FROM rubro_cuenta_contable
	WHERE id_rubro = p_id_rubro;

	IF v_rubro_existente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El rubro contable no existe';
	END IF;

	IF p_id_cuenta_padre IS NOT NULL THEN

		SELECT COUNT(*)
		INTO v_padre_existente
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_padre;

		IF v_padre_existente = 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La cuenta superior no existe';
		END IF;

		SELECT
			nivel,
			ultimo_nivel
		INTO
			v_nivel,
			v_padre_ultimo_nivel
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_padre
		FOR UPDATE;

		IF v_padre_ultimo_nivel = TRUE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La cuenta superior es de detalle y no admite subcuentas';
		END IF;

		SET v_nivel = v_nivel + 1;

	ELSE
		SET v_nivel = 1;
	END IF;

	INSERT INTO cuentas_contables (
		id_cuenta_padre,
		fk_id_rubro,
		clave,
		nombre,
		descripcion,
		nivel,
		ultimo_nivel,
		cargo,
		abono,
		activa,
		fecha_modificacion
	) VALUES (
		p_id_cuenta_padre,
		p_id_rubro,
		TRIM(p_clave),
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		v_nivel,
		p_ultimo_nivel,
		0,
		0,
		TRUE,
		CURDATE()
	);

	SET v_id_cuenta = LAST_INSERT_ID();

	COMMIT;

	SELECT
		v_id_cuenta AS id,
		'Cuenta contable registrada correctamente' AS message;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insert_empleado`(
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_rfc VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_curp VARCHAR(18)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre_completo VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre_corto VARCHAR(10)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_nac DATE,
    IN p_correo_electronico VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_estado VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_ciudad VARCHAR(40)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_direccion TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_postal VARCHAR(6)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_contrasenia_hash VARCHAR(255)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un empleado con contraseña hasheada desde Java'
BEGIN
    DECLARE v_id_empleado INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;
    DECLARE v_rfc_duplicado INT DEFAULT 0;
    DECLARE v_curp_duplicada INT DEFAULT 0;
    DECLARE v_nombre_corto_duplicado INT DEFAULT 0;

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

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    START TRANSACTION;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC es obligatorio';
    END IF;

    IF p_curp IS NULL OR TRIM(p_curp) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La CURP es obligatoria';
    END IF;

    IF p_nombre_completo IS NULL OR TRIM(p_nombre_completo) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre completo es obligatorio';
    END IF;

    IF p_nombre_corto IS NULL OR TRIM(p_nombre_corto) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre corto es obligatorio';
    END IF;

    IF p_fecha_nac IS NULL OR p_fecha_nac > CURDATE() THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
    END IF;

    IF p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El correo electrónico es obligatorio';
    END IF;

    IF p_contrasenia_hash IS NULL OR TRIM(p_contrasenia_hash) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El hash de contraseña es obligatorio';
    END IF;

    IF CHAR_LENGTH(TRIM(p_contrasenia_hash)) > 255 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El hash de contraseña excede la longitud permitida';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal indicada no existe';
    END IF;

    SELECT COUNT(*)
    INTO v_rfc_duplicado
    FROM kath_erp.empleados
    WHERE rfc = UPPER(TRIM(p_rfc));

    IF v_rfc_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC ya está registrado';
    END IF;

    SELECT COUNT(*)
    INTO v_curp_duplicada
    FROM kath_erp.empleados
    WHERE curp = UPPER(TRIM(p_curp));

    IF v_curp_duplicada > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La CURP ya está registrada';
    END IF;

    SELECT COUNT(*)
    INTO v_nombre_corto_duplicado
    FROM kath_erp.empleados
    WHERE nombre_corto = TRIM(p_nombre_corto);

    IF v_nombre_corto_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre corto ya está registrado';
    END IF;

    INSERT INTO kath_erp.empleados (
        id_sucursal,
        rfc,
        curp,
        nombre_completo,
        nombre_corto,
        fecha_nac,
        correo_electronico,
        estado,
        ciudad,
        direccion,
        codigo_postal,
        contrasenia,
        activo
    ) VALUES (
        p_id_sucursal,
        UPPER(TRIM(p_rfc)),
        UPPER(TRIM(p_curp)),
        TRIM(p_nombre_completo),
        TRIM(p_nombre_corto),
        p_fecha_nac,
        LOWER(TRIM(p_correo_electronico)),
        NULLIF(TRIM(p_estado), ''),
        NULLIF(TRIM(p_ciudad), ''),
        NULLIF(TRIM(p_direccion), ''),
        NULLIF(TRIM(p_codigo_postal), ''),
        TRIM(p_contrasenia_hash),
        TRUE
    );

    SET v_id_empleado = LAST_INSERT_ID();

    COMMIT;

    SELECT
        v_id_empleado AS id,
        'Empleado registrado correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insert_forma_de_pago`(
	IN forma_pago VARCHAR(18)
)
BEGIN
	INSERT INTO formas_de_pago(
		tipo_de_pago,
        activo
    )VALUES(
		forma_pago,
        1
    );
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insert_nuevo_categoria`(
  IN `nombre_m` VARCHAR(60),
  IN `descripcion_m` VARCHAR(255)
)
    COMMENT 'Procedimiento para insertar un nuevo registro'
BEGIN
INSERT INTO categoria_producto(nombre, descripcion)
VALUES(nombre_m, descripcion_m);
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insert_nuevo_tipoCliente`(
	IN nombre_t VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion_t VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo tipo de cliente o categoria de cliente'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	
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
	
    INSERT INTO tipo_cliente(
		nombre,
        descripcion,
        activo
    )VALUES(
		nombre_t,
        descripcion_t,
        1
    );
    
    SELECT 200 AS id, 'Tipo de cliente registrado con exito' AS message;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listArticulos`(
	IN p_id_sucursal BIGINT UNSIGNED,
	IN p_tipo_busqueda VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ordenar_por VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_texto_busqueda VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_id_tipo_cliente INT
)
    READS SQL DATA
    COMMENT 'Lista articulos registrados con precio por tipo de cliente y existencia por sucursal'
BEGIN
	
    DECLARE v_tipo_busqueda VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_ordenar_por VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_texto_busqueda VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	SET v_tipo_busqueda = UPPER(TRIM(COALESCE(p_tipo_busqueda, 'TODOS')));
	SET v_ordenar_por = UPPER(TRIM(COALESCE(p_ordenar_por, 'NOMBRE')));
	SET v_texto_busqueda = TRIM(COALESCE(p_texto_busqueda, ''));

	IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la sucursal no es valido';
	END IF;

	IF p_id_tipo_cliente IS NULL OR p_id_tipo_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del tipo de cliente no es valido';
	END IF;

	IF v_tipo_busqueda NOT IN ('TODOS', 'CODIGO', 'NOMBRE', 'PROVEEDOR', 'CATEGORIA', 'DESCRIPCION') THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de busqueda no es valido';
	END IF;

	IF v_ordenar_por NOT IN ('CODIGO', 'NOMBRE', 'PROVEEDOR', 'CATEGORIA') THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El criterio de ordenamiento no es valido';
	END IF;

	SELECT
		art.id_articulo,
		prv.nombre AS nombre_proveedor,
		cat.nombre AS nombre_categoria,
		art.codigo_articulo,
		art.nombre,
		art.es_exento,
		art.costo_unitario,
		pxc.precio,
		COALESCE(exs.existencia, 0) AS existencia,
		art.activo
	FROM articulo AS art
	INNER JOIN proveedor AS prv
		ON prv.id_proveedor = art.id_proveedor
	INNER JOIN categoria_producto AS cat
		ON cat.id_categoria = art.id_categoria
	LEFT JOIN precios_x_tipocliente AS pxc
		ON pxc.id_articulo = art.id_articulo
	   AND pxc.id_tipoCliente = p_id_tipo_cliente
	LEFT JOIN existencia_x_sucursal AS exs
		ON exs.id_articulo = art.id_articulo
	   AND exs.id_sucursal = p_id_sucursal
	WHERE
		v_texto_busqueda = ''
		OR (
			v_tipo_busqueda = 'TODOS'
			AND (
				art.codigo_articulo COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR art.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR prv.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR cat.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR art.descripcion COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
			)
		)
		OR (
			v_tipo_busqueda = 'CODIGO'
			AND art.codigo_articulo COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'NOMBRE'
			AND art.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'PROVEEDOR'
			AND prv.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'CATEGORIA'
			AND cat.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'DESCRIPCION'
			AND art.descripcion COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
	ORDER BY
		CASE WHEN v_ordenar_por = 'CODIGO' THEN art.codigo_articulo END ASC,
		CASE WHEN v_ordenar_por = 'NOMBRE' THEN art.nombre END ASC,
		CASE WHEN v_ordenar_por = 'PROVEEDOR' THEN prv.nombre END ASC,
		CASE WHEN v_ordenar_por = 'CATEGORIA' THEN cat.nombre END ASC,
		art.nombre ASC;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listArticulosCompraById`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCategoriaProducto`(
	IN p_nombre_categoria VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Lista categorias de productos filtradas por nombre'
BEGIN

	SELECT
		cp.id_categoria,
		cp.nombre,
		cp.descripcion,
		cp.activo
	FROM categoria_producto AS cp
	WHERE
		p_nombre_categoria IS NULL
		OR TRIM(p_nombre_categoria) = ''
		OR cp.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_categoria), '%')
	ORDER BY cp.nombre ASC;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listClientes`(
	IN `nombre_c` VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Listado de clientes registrados filtrado por nombre del cliente'
BEGIN

	SELECT
		c.id_cliente,
		c.rfc,
		tc.nombre,
		c.nombre_completo,
		c.nombre_corto,
		c.correo_electronico,
		c.estado,
		c.ciudad,
		c.direccion,
		c.codigo_postal,
		c.activo
	FROM kath_erp.cliente AS c
	INNER JOIN kath_erp.tipo_cliente AS tc
		ON tc.id = c.id_tipoCliente
	WHERE c.nombre_completo LIKE CONCAT('%', nombre_c, '%');

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCmbCategoriaProducto`()
    READS SQL DATA
    COMMENT 'Lista categorias activas para combo'
BEGIN

	SELECT
		cp.id_categoria,
		cp.nombre
	FROM categoria_producto AS cp
	WHERE cp.activo = TRUE
	ORDER BY cp.nombre ASC;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCmbClientes`()
    READS SQL DATA
    COMMENT 'Listado de nombre cortos de clientes para ComboBox'
BEGIN
	
	SELECT
		c.id_cliente AS id,
		c.nombre_corto AS nombre
	FROM
		kath_erp.cliente AS c;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCmbProveeodor`()
BEGIN
	
    SELECT 
    	p.id_proveedor AS id,
    	p.nombre 
    FROM kath_erp.proveedor  AS p
    WHERE p.activo = true;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCompras`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCuentasContablesEnDialog`(
	IN nombre_cuenta VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Muestra un listado reducido de columnas para ser consultado desde un dialog de seleccion rapida'
BEGIN
	
	SELECT 
		cc.id_cuenta,
		cc.clave,
		cc.nombre
	FROM
		cuentas_contables AS cc
	WHERE
		cc.nombre LIKE CONCAT('%',nombre_cuenta,'%') AND cc.ultimo_nivel = 1;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listExistenciaGlobalArticulo`(
	IN p_id_articulo INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'CONSULTA LA EXISTENCIA DE UN ARTICULO EN TODAS LAS SUCURSALES REGISTRADAS'
BEGIN
	
	SELECT 
		s.id_sucursar,
		s.nombre,
		s.direccion,
		exs.existencia 
	FROM kath_erp.existencia_x_sucursal AS exs
	INNER JOIN kath_erp.sucursal AS s on exs.id_sucursal = s.id_sucursar 
	WHERE exs.id_articulo = p_id_articulo;
		
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listPreciosArticuloTipoCliente`(
    IN p_id_articulo INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los precios registrados de un artículo por tipo de cliente activo'
BEGIN

    SELECT
        tc.id AS id_tipo_cliente,
        tc.nombre AS tipo_cliente,
        patc.precio,
        patc.precios_especial,
        patc.cant_p_precioEspecial 
    FROM kath_erp.precios_x_tipocliente AS patc
    INNER JOIN kath_erp.tipo_cliente AS tc ON patc.id_tipoCliente  = tc.id 
    WHERE patc.id_articulo = p_id_articulo
      AND tc.activo = 1
    ORDER BY tc.nombre ASC;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listProveedores`(
    IN p_nombre_proveedor VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Lista proveedores por nombre o RFC'
BEGIN
    SELECT
        p.id_proveedor,
        p.rfc,
        p.nombre,
        p.descripcion,
        p.correo_electronico,
        p.estado,
        p.ciudad,
        p.direccion,
        p.codigo_postal,
        p.activo
    FROM kath_erp.proveedor AS p
    WHERE
        p_nombre_proveedor IS NULL
        OR TRIM(p_nombre_proveedor) = ''
        OR p.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_proveedor), '%')
        OR p.rfc COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_proveedor), '%')
    ORDER BY p.nombre ASC;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listTelefonoProveedor`(
	IN p_id_proveedor INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los telefonos asociados a un proveedor'
BEGIN
	
	SELECT
		txp.id_telefono,
		txp.telefono
	FROM telefono_x_proveedor AS txp
	WHERE txp.id_proveedor = p_id_proveedor
	ORDER BY txp.id_telefono ASC;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listTelefonosCliente`(
	IN p_id_cliente INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los telefonos asociados a un cliente'
BEGIN
	
	SELECT
		txc.id_telefono,		
		txc.telefono
	FROM telefono_x_cliente AS txc
	WHERE txc.id_cliente = p_id_cliente
	ORDER BY txc.id_telefono ASC;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listTelefonosDeEmpleadoByID`(
	IN id_empleado INT
)
    READS SQL DATA
    COMMENT 'EMPLEADO PARA VER LOS TELEFONOS ASOCIADOS A UN EMPLEADO AL MOMENTO DE VISUALIZAR SUS DATOS EN FORMULARIO'
BEGIN
	
	SELECT 
		txe.id_telefono,
		txe.telefono 
	FROM
		kath_erp.telefono_x_empleado AS txe
	WHERE 
		txe.id_empleado = id_empleado;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listVentas`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`list_cmbGrupoContable`()
    READS SQL DATA
    COMMENT 'Listado de todos los grupos contables registrados para un ComboBox'
BEGIN
	
	SELECT 
		gc.id_grupo,
		gc.nombre_grupo 
	FROM
		kath_erp.grupo_contable AS gc;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`list_cmbRubroCuentasContables`(
	IN `id_grupo_contable` INT
)
    COMMENT 'LISTADO DE RUBROS CONTABLES PARA UN COMBOBOX'
BEGIN
	
	SELECT
		rcc.id_rubro,
		rcc.nombre 
	FROM
		kath_erp.rubro_cuenta_contable AS rcc
	WHERE rcc.fk_id_grupo_contable = `id_grupo_contable`;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`restarExistenciaSucursalVenta`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`sumarExistenciaSucursalCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateArticulo`(
	IN p_id_articulo INT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_id_categoria INT UNSIGNED,
    IN p_codigo_articulo VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_sat VARCHAR(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_unidad_sat VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_descripcion VARCHAR(555) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_es_exento TINYINT,
    IN p_costo_unitario DECIMAL(18,2),
    IN p_activo TINYINT
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos generales de un artículo existente'
BEGIN
	
    IF NOT EXISTS (
        SELECT 1
        FROM kath_erp.articulo AS a
        WHERE a.id_articulo = p_id_articulo
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el artículo indicado' AS message;

    ELSEIF EXISTS (
        SELECT 1
        FROM kath_erp.articulo AS a
        WHERE a.codigo_articulo = p_codigo_articulo
          AND a.id_articulo <> p_id_articulo
    ) THEN

        SELECT
            409 AS id,
            'Ya existe otro artículo registrado con el mismo código' AS message;

    ELSE

        UPDATE kath_erp.articulo AS a
        SET
            a.id_proveedor = p_id_proveedor,
            a.id_categoria = p_id_categoria,
            a.codigo_articulo = p_codigo_articulo,
            a.codigo_sat = p_codigo_sat,
            a.unidad_sat = p_unidad_sat,
            a.nombre = p_nombre,
            a.descripcion = p_descripcion,
            a.es_exento = p_es_exento,
            a.costo_unitario = p_costo_unitario,
            a.activo = p_activo
        WHERE a.id_articulo = p_id_articulo;

        SELECT
            200 AS id,
            'Artículo actualizado correctamente' AS message;

    END IF;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateArticuloCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateCategoriaProducto`(
	IN p_id_categoria INT UNSIGNED,
	IN p_nombre VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza una categoria de producto'
BEGIN
	
	
	DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_existe_nombre INT DEFAULT 0;

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

	IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la categoria no es valido';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la categoria es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM categoria_producto
	WHERE id_categoria = p_id_categoria;

	IF v_existe_categoria = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria indicada no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_nombre
	FROM categoria_producto
	WHERE nombre COLLATE utf8mb4_general_ci = TRIM(p_nombre)
	  AND id_categoria <> p_id_categoria;

	IF v_existe_nombre > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe otra categoria con el nombre indicado';
	END IF;

	UPDATE categoria_producto
	SET
		nombre = TRIM(p_nombre),
		descripcion = NULLIF(TRIM(p_descripcion), ''),
		activo = COALESCE(p_activo, TRUE)
	WHERE id_categoria = p_id_categoria;

	COMMIT;

	SELECT
		200 AS id,
		'Categoria actualizada correctamente' AS message;
	
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateCliente`(
	IN p_id_cliente INT UNSIGNED,
	IN p_id_tipoCliente INT,
	IN p_rfc VARCHAR(13)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_completo VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_corto VARCHAR(10)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_fecha_nac DATE,
	IN p_correo_electronico VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza y valida los datos operativos de un cliente sin dependencias contables'
BEGIN

	DECLARE v_existe_cliente INT DEFAULT 0;
	DECLARE v_existe_tipo_cliente INT DEFAULT 0;
	DECLARE v_rfc_duplicado INT DEFAULT 0;
	DECLARE v_cliente_activo BOOLEAN DEFAULT FALSE;
	DECLARE v_saldo_pendiente DECIMAL(20,2) DEFAULT 0;

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

		ROLLBACK;

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

	START TRANSACTION;

	IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del cliente no es válido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cliente
	FROM kath_erp.cliente
	WHERE id_cliente = p_id_cliente;

	IF v_existe_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente indicado no existe';
	END IF;

	SELECT activo
	INTO v_cliente_activo
	FROM kath_erp.cliente
	WHERE id_cliente = p_id_cliente
	FOR UPDATE;

	IF p_id_tipoCliente IS NULL OR p_id_tipoCliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente es obligatorio';
	END IF;

	IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC es obligatorio';
	END IF;

	IF p_nombre_completo IS NULL
			OR TRIM(p_nombre_completo) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre completo es obligatorio';
	END IF;

	IF p_nombre_corto IS NULL
			OR TRIM(p_nombre_corto) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre corto es obligatorio';
	END IF;

	IF p_fecha_nac IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento es obligatoria';
	END IF;

	IF p_fecha_nac > CURDATE() THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
	END IF;

	IF p_correo_electronico IS NULL
			OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El correo electrónico es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_tipo_cliente
	FROM kath_erp.tipo_cliente
	WHERE id = p_id_tipoCliente;

	IF v_existe_tipo_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente indicado no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_rfc_duplicado
	FROM kath_erp.cliente
	WHERE rfc = UPPER(TRIM(p_rfc))
	  AND id_cliente <> p_id_cliente;

	IF v_rfc_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC ya pertenece a otro cliente';
	END IF;

	/*
	 * La desactivación no puede utilizarse para evadir la regla de cobranza.
	 * Si updateCliente intenta pasar un cliente activo a inactivo, se aplica
	 * la misma validación de saldo insoluto que en deleteCliente.
	 */
	IF v_cliente_activo = TRUE AND p_activo = FALSE THEN

		SELECT
			ROUND(
				COALESCE(
					SUM(
						GREATEST(
							CAST(v.importe_total AS DECIMAL(18,2))
							- COALESCE((
								SELECT SUM(CAST(pxv.importe AS DECIMAL(18,2)))
								FROM kath_erp.pagos_x_venta AS pxv
								WHERE pxv.id_venta = v.id_venta
							), 0)
							- COALESCE((
								SELECT SUM(CAST(cc.total AS DECIMAL(18,2)))
								FROM kath_erp.cobro_clientes AS cc
								WHERE cc.id_venta = v.id_venta
							), 0),
							0
						)
					),
					0
				),
				2
			)
		INTO v_saldo_pendiente
		FROM kath_erp.ventas AS v
		WHERE v.id_cliente = p_id_cliente
		  AND v.status_venta = TRUE
		  AND v.tipo_venta = FALSE;

		IF v_saldo_pendiente > 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede desactivar el cliente porque tiene saldo pendiente en ventas a crédito';
		END IF;

	END IF;

	UPDATE kath_erp.cliente
	SET
		id_tipoCliente = p_id_tipoCliente,
		rfc = UPPER(TRIM(p_rfc)),
		nombre_completo = TRIM(p_nombre_completo),
		nombre_corto = TRIM(p_nombre_corto),
		fecha_nac = p_fecha_nac,
		correo_electronico = LOWER(TRIM(p_correo_electronico)),
		estado = NULLIF(TRIM(p_estado), ''),
		ciudad = NULLIF(TRIM(p_ciudad), ''),
		direccion = NULLIF(TRIM(p_direccion), ''),
		codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
		activo = p_activo
	WHERE id_cliente = p_id_cliente;

	COMMIT;

	SELECT
		200 AS id,
		'Cliente actualizado correctamente' AS message;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateCompra`(
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateConfiguracionFiscal`(
    IN p_id_configuracion INT UNSIGNED,
    IN p_rfc_emisor VARCHAR(13),
    IN p_nombre_razon_social VARCHAR(255),
    IN p_nombre_comercial VARCHAR(255),
    IN p_regimen_fiscal_clave CHAR(3),
    IN p_regimen_fiscal_descripcion VARCHAR(150),
    IN p_numero_registro_sistema VARCHAR(100)
)
    MODIFIES SQL DATA
BEGIN

    /*
     * Validar identificador.
     */
    IF p_id_configuracion IS NULL
       OR p_id_configuracion <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La configuracion fiscal no es valida';

    END IF;


    /*
     * Validar que el registro exista y se encuentre activo.
     */
    IF NOT EXISTS (
        SELECT 1
        FROM kath_erp.configuracion_fiscal
        WHERE id_configuracion = p_id_configuracion
          AND activo = TRUE
    ) THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No existe la configuracion fiscal indicada';

    END IF;


    /*
     * RFC.
     * Persona moral: 12 caracteres.
     * Persona fisica: 13 caracteres.
     */
    IF p_rfc_emisor IS NULL
       OR TRIM(p_rfc_emisor) = ''
       OR CHAR_LENGTH(TRIM(p_rfc_emisor)) NOT IN (12, 13) THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC del emisor debe contener 12 o 13 caracteres';

    END IF;


    /*
     * Nombre o razon social.
     */
    IF p_nombre_razon_social IS NULL
       OR TRIM(p_nombre_razon_social) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre o razon social es obligatorio';

    END IF;


    /*
     * Clave de regimen fiscal SAT.
     */
    IF p_regimen_fiscal_clave IS NULL
       OR CHAR_LENGTH(TRIM(p_regimen_fiscal_clave)) <> 3 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La clave del regimen fiscal debe contener 3 caracteres';

    END IF;


    /*
     * Descripcion del regimen.
     */
    IF p_regimen_fiscal_descripcion IS NULL
       OR TRIM(p_regimen_fiscal_descripcion) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La descripcion del regimen fiscal es obligatoria';

    END IF;


    /*
     * Actualizacion.
     *
     * nombre_comercial y numero_registro_sistema
     * son opcionales.
     */
    UPDATE kath_erp.configuracion_fiscal
    SET
        rfc_emisor = UPPER(TRIM(p_rfc_emisor)),
        nombre_razon_social = TRIM(p_nombre_razon_social),

        nombre_comercial =
            NULLIF(TRIM(p_nombre_comercial), ''),

        regimen_fiscal_clave =
            TRIM(p_regimen_fiscal_clave),

        regimen_fiscal_descripcion =
            TRIM(p_regimen_fiscal_descripcion),

        numero_registro_sistema =
            NULLIF(TRIM(p_numero_registro_sistema), '')

    WHERE id_configuracion = p_id_configuracion
      AND activo = TRUE;


    /*
     * Respuesta esperada por SpResponseModel.
     */
    SELECT
        p_id_configuracion AS id,
        'Configuracion fiscal actualizada correctamente' AS message;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updatePrecioPorTipoCliente`(
    IN p_id_articulo INT UNSIGNED,
    IN p_id_tipoCliente INT,
    IN p_precio DECIMAL(18,2),
    IN p_precios_especial DECIMAL(18,2),
    IN p_cant_p_precioEspecial INT
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza el precio de un artículo por tipo de cliente'
BEGIN

   /*
     * Validar artículo.
     */
    IF NOT EXISTS (
        SELECT 1
        FROM kath_erp.articulo AS a
        WHERE a.id_articulo = p_id_articulo
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el artículo indicado' AS message;


    /*
     * El tipo de cliente debe existir y estar activo.
     *
     * Si no existe en tipo_cliente NO debemos insertar nada en
     * precios_x_tipocliente porque estaríamos intentando crear
     * una relación contra un registro inexistente.
     */
    ELSEIF NOT EXISTS (
        SELECT 1
        FROM kath_erp.tipo_cliente AS tc
        WHERE tc.id = p_id_tipoCliente
          AND tc.activo = 1
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el tipo de cliente activo indicado' AS message;


    /*
     * Si todavía no existe una relación entre el artículo y
     * este tipo de cliente, se crea.
     *
     * Este será el caso típico cuando se acaba de registrar
     * un nuevo tipo de cliente.
     */
    ELSEIF NOT EXISTS (
        SELECT 1
        FROM kath_erp.precios_x_tipocliente AS pxt
        WHERE pxt.id_articulo = p_id_articulo
          AND pxt.id_tipoCliente = p_id_tipoCliente
    ) THEN

        INSERT INTO kath_erp.precios_x_tipocliente (
            id_articulo,
            id_tipoCliente,
            precio,
            precios_especial,
            cant_p_precioEspecial
        )
        VALUES (
            p_id_articulo,
            p_id_tipoCliente,
            p_precio,
            p_precios_especial,
            p_cant_p_precioEspecial
        );

        SELECT
            200 AS id,
            'Precio por tipo de cliente registrado correctamente' AS message;


    /*
     * Si ya existe, simplemente se actualiza.
     */
    ELSE

        UPDATE kath_erp.precios_x_tipocliente AS pxt
        SET
            pxt.precio = p_precio,
            pxt.precios_especial = p_precios_especial,
            pxt.cant_p_precioEspecial = p_cant_p_precioEspecial
        WHERE pxt.id_articulo = p_id_articulo
          AND pxt.id_tipoCliente = p_id_tipoCliente;

        SELECT
            200 AS id,
            'Precio por tipo de cliente actualizado correctamente' AS message;

    END IF;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateProveedor`(
    IN p_id_proveedor INT UNSIGNED,
    IN p_rfc VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_correo_electronico VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_estado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_ciudad VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_direccion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_postal VARCHAR(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos operativos de un proveedor sin dependencias contables'
BEGIN
    DECLARE v_existe_proveedor INT DEFAULT 0;
    DECLARE v_existe_rfc INT DEFAULT 0;
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

    IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El identificador del proveedor no es valido';
    END IF;

    IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC del proveedor es obligatorio';
    END IF;

    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre del proveedor es obligatorio';
    END IF;

    IF p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El correo electronico del proveedor es obligatorio';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = p_id_proveedor;

    IF v_existe_proveedor = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor indicado no existe';
    END IF;

    SELECT id_proveedor
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = p_id_proveedor
    FOR UPDATE;

    SELECT COUNT(*)
    INTO v_existe_rfc
    FROM kath_erp.proveedor
    WHERE rfc COLLATE utf8mb4_general_ci = UPPER(TRIM(p_rfc))
      AND id_proveedor <> p_id_proveedor;

    IF v_existe_rfc > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Ya existe otro proveedor registrado con el RFC indicado';
    END IF;

    IF p_activo = FALSE THEN
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
        WHERE c.id_proveedor = p_id_proveedor
          AND c.activo = TRUE
          AND c.tipo_compra = TRUE;

        IF v_saldo_pendiente > 0 THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'No se puede inhabilitar el proveedor porque tiene saldo pendiente en compras a crédito';
        END IF;
    END IF;

    UPDATE kath_erp.proveedor
    SET
        rfc = UPPER(TRIM(p_rfc)),
        nombre = TRIM(p_nombre),
        descripcion = NULLIF(TRIM(p_descripcion), ''),
        correo_electronico = TRIM(p_correo_electronico),
        estado = NULLIF(TRIM(p_estado), ''),
        ciudad = NULLIF(TRIM(p_ciudad), ''),
        direccion = NULLIF(TRIM(p_direccion), ''),
        codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
        activo = TRUE
    WHERE id_proveedor = p_id_proveedor;

    COMMIT;

    SELECT
        200 AS id,
        'Proveedor actualizado correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateSucursal`(
	IN id_sucursal INT,
	IN nombre VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN email VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN estado VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN ciudad VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN direccion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN codigo_postal VARCHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos de una Sucursal ya registrada'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
	    GET DIAGNOSTICS CONDITION 1
	    v_sqlstate = RETURNED_SQLSTATE,
	    v_errno = MYSQL_ERRNO,
	    v_text = MESSAGE_TEXT;
    
    	SELECT
    		500 AS id,
    		CONCAT('Error ', `v_errno`,' (', `v_sqlstate`, '): ', `v_text`) AS message;
    		
    END;
	
    UPDATE kath_erp.sucursal 
    SET
		nombre = nombre,
        descripcion = descripcion,
        telefono = telefono,
        email = email,
        estado = estado,
        ciudad = ciudad,
        direccion = direccion,
        codigo_postal = codigo_postal,
        activo = 1
    WHERE sucursal.id_sucursar = id_sucursal;
    
    SELECT 200 AS id, 'Sucursal Actualizada exitosamente' AS message;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`update_cuenta_contable`(
	IN p_id_cuenta INT,
	IN p_clave VARCHAR(25)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(255)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(555)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ultimo_nivel BOOLEAN,
	IN p_activa BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza datos permitidos de una cuenta contable'
BEGIN
	DECLARE v_cuenta_existe INT DEFAULT 0;
	DECLARE v_clave_actual VARCHAR(25);
	DECLARE v_ultimo_nivel_actual BOOLEAN;
	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_total_hijas INT DEFAULT 0;
	DECLARE v_hijas_activas INT DEFAULT 0;
	DECLARE v_clave_duplicada INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
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
			CONCAT(
				'Error ',
				v_errno,
				' (',
				v_sqlstate,
				'): ',
				v_text
			) AS message;
	END;

	START TRANSACTION;

	IF p_id_cuenta IS NULL OR p_id_cuenta <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la cuenta es inválido';
	END IF;

	IF p_clave IS NULL OR TRIM(p_clave) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La clave contable es obligatoria';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la cuenta es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_existe
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta;

	IF v_cuenta_existe = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable no existe';
	END IF;

	SELECT
		clave,
		ultimo_nivel,
		cargo,
		abono
	INTO
		v_clave_actual,
		v_ultimo_nivel_actual,
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta
	FOR UPDATE;

	SELECT COUNT(*)
	INTO v_clave_duplicada
	FROM cuentas_contables
	WHERE clave = TRIM(p_clave)
	  AND id_cuenta <> p_id_cuenta;

	IF v_clave_duplicada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La clave contable ya pertenece a otra cuenta';
	END IF;

	SELECT COUNT(*)
	INTO v_total_hijas
	FROM cuentas_contables
	WHERE id_cuenta_padre = p_id_cuenta;

	SELECT COUNT(*)
	INTO v_hijas_activas
	FROM cuentas_contables
	WHERE id_cuenta_padre = p_id_cuenta
	  AND activa = TRUE;

	/*
	 * Una cuenta con movimientos no puede cambiar de clave.
	 */
	IF TRIM(p_clave) <> v_clave_actual
	   AND (v_cargo <> 0 OR v_abono <> 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede modificar la clave de una cuenta con movimientos';
	END IF;

	/*
	 * Una cuenta con subcuentas no puede convertirse en cuenta de detalle.
	 */
	IF p_ultimo_nivel = TRUE
	   AND v_total_hijas > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Una cuenta con subcuentas no puede convertirse en cuenta de detalle';
	END IF;

	/*
	 * Una cuenta con movimientos no puede cambiar su tipo operativo.
	 */
	IF p_ultimo_nivel <> v_ultimo_nivel_actual
	   AND (v_cargo <> 0 OR v_abono <> 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede modificar el tipo de una cuenta con movimientos';
	END IF;

	/*
	 * No se puede desactivar una cuenta con saldo.
	 */
	IF p_activa = FALSE
	   AND (v_cargo - v_abono) <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede desactivar una cuenta con saldo distinto de cero';
	END IF;

	/*
	 * No se puede desactivar una cuenta con subcuentas activas.
	 */
	IF p_activa = FALSE
	   AND v_hijas_activas > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede desactivar una cuenta con subcuentas activas';
	END IF;

	UPDATE cuentas_contables
	SET
		clave = TRIM(p_clave),
		nombre = TRIM(p_nombre),
		descripcion = NULLIF(TRIM(p_descripcion), ''),
		ultimo_nivel = p_ultimo_nivel,
		activa = p_activa,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = p_id_cuenta;

	COMMIT;

	SELECT
		p_id_cuenta AS id,
		'Cuenta contable actualizada correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`update_empleado`(
    IN p_id_empleado INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_rfc VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_curp VARCHAR(18)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre_completo VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre_corto VARCHAR(10)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_nac DATE,
    IN p_correo_electronico VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_estado VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_ciudad VARCHAR(40)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_direccion TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_postal VARCHAR(6)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_contrasenia VARCHAR(255)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos permitidos de un empleado'
BEGIN
    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;
    DECLARE v_rfc_duplicado INT DEFAULT 0;
    DECLARE v_curp_duplicada INT DEFAULT 0;

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

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    START TRANSACTION;

    IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El identificador del empleado no es válido';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado;

    IF v_existe_empleado = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado no existe';
    END IF;

    SELECT id_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
    FOR UPDATE;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    IF p_rfc IS NULL OR TRIM(p_rfc) = ''
       OR p_curp IS NULL OR TRIM(p_curp) = ''
       OR p_nombre_completo IS NULL OR TRIM(p_nombre_completo) = ''
       OR p_nombre_corto IS NULL OR TRIM(p_nombre_corto) = ''
       OR p_fecha_nac IS NULL
       OR p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Faltan datos obligatorios del empleado';
    END IF;

    IF p_fecha_nac > CURDATE() THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal indicada no existe';
    END IF;

    SELECT COUNT(*)
    INTO v_rfc_duplicado
    FROM kath_erp.empleados
    WHERE rfc = UPPER(TRIM(p_rfc))
      AND id_empleado <> p_id_empleado;

    IF v_rfc_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC ya pertenece a otro empleado';
    END IF;

    SELECT COUNT(*)
    INTO v_curp_duplicada
    FROM kath_erp.empleados
    WHERE curp = UPPER(TRIM(p_curp))
      AND id_empleado <> p_id_empleado;

    IF v_curp_duplicada > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La CURP ya pertenece a otro empleado';
    END IF;

    UPDATE kath_erp.empleados
    SET
        id_sucursal = p_id_sucursal,
        rfc = UPPER(TRIM(p_rfc)),
        curp = UPPER(TRIM(p_curp)),
        nombre_completo = TRIM(p_nombre_completo),
        nombre_corto = TRIM(p_nombre_corto),
        fecha_nac = p_fecha_nac,
        correo_electronico = LOWER(TRIM(p_correo_electronico)),
        estado = NULLIF(TRIM(p_estado), ''),
        ciudad = NULLIF(TRIM(p_ciudad), ''),
        direccion = NULLIF(TRIM(p_direccion), ''),
        codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
        contrasenia = COALESCE(NULLIF(TRIM(p_contrasenia), ''), contrasenia),
        activo = TRUE
    WHERE id_empleado = p_id_empleado;

    COMMIT;

    SELECT
        200 AS id,
        'Empleado actualizado correctamente' AS message;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`update_forma_de_pago`(
	IN id_forma_pago INT,
	IN forma_pago VARCHAR(18)
)
BEGIN

	UPDATE formas_de_pago
    SET
		tipo_de_pago = forma_pago,
        activo = 1
	WHERE id = id_forma_pago;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`update_tipoCliente`(
	IN id_tipoCliente INT,
	IN nombre_t VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion_t VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos de un tipo de cliente ya registrado'
BEGIN
	
	DECLARE v_sqlState CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		
		GET DIAGNOSTICS CONDITION 1
		v_sqlState = RETURNED_SQLSTATE,
		v_errno = MYSQL_ERRNO,
		v_text = MESSAGE_TEXT;
		
		SELECT 500 AS id,
		CONCAT('Error ', v_errno, ' (', v_sqlState, ' ):', v_text) AS message;
		
	END;
	
	
    UPDATE tipo_cliente SET
		nombre = nombre_t,
        descripcion = descripcion_t,
        activo = 1
    WHERE id = id_tipoCliente;
    
    SELECT 200 AS id, 'Tipo cliente actualizado exitosamente' AS message;
        
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`validar_entrada`(IN `nombre_c` VARCHAR(10) CHARSET utf8, IN `contra_c` VARCHAR(15) CHARSET utf8)
BEGIN



DECLARE contra VARCHAR(15);



SELECT @contra := empleados.contrasenia AS pswd FROM empleados WHERE empleados.nombre_corto = nombre_c;



IF(@contra != contra_c) THEN

	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Contraseña incorrecta';

END IF;



END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_articulos`(
	IN `id_sucursal` INT,
	IN `id_tipoCliente_a` INT
)
BEGIN
SELECT articulo.id_articulo,
       articulo.codigo_articulo,
       proveedor.nombre AS proveedor,
       categoria_producto.nombre AS Categoria,
       articulo.codigo_sat,
       articulo.nombre AS Articulo,
       articulo.descripcion,
       existencia_x_sucursal.existencia,
       precios_x_tipoCliente.precio,
       precios_x_tipoCliente.precios_especial AS especial,
       precios_x_tipoCliente.cant_p_precioEspecial AS despues_de,
       articulo.activo
FROM precios_x_tipoCliente
INNER JOIN existencia_x_sucursal ON precios_x_tipoCliente.id_articulo = existencia_x_sucursal.id_articulo
INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
WHERE existencia_x_sucursal.id_sucursal = id_sucursal
  AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_a
ORDER BY id_articulo;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_cliente_por_rfc`(
	IN rfc_cl VARCHAR(13)
)
    READS SQL DATA
    COMMENT 'Consulta un cliente por RFC sin dependencias contables'
BEGIN

	SELECT
		c.id_cliente,
		c.rfc,
		c.nombre_completo,
		c.nombre_corto,
		c.fecha_nac,
		c.correo_electronico,
		c.estado,
		c.ciudad,
		c.direccion,
		c.codigo_postal
	FROM kath_erp.cliente AS c
	WHERE c.rfc = UPPER(TRIM(rfc_cl));

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_cmbRubroCuentasContables`()
BEGIN
	
	SELECT 
		_rc.id_rubro,
		_rc.nombre,
		_rc.descripcion,
		_rc.naturaleza
	FROM rubro_cuenta_contable AS _rc;
	
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_codigos_articulos`()
BEGIN
	
    SELECT articulo.codigo_articulo
    FROM articulo;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_cuentas_contables`(
	IN `nombre_cta_contable` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    COMMENT 'LISTA EL CATALOGO COMPLETO DE CUENTAS CONTABLES'
BEGIN            
	SELECT
		cc.id_cuenta,
		cc.clave,
		cc.nombre,
		cc2.nombre AS 'cuenta_padre',
		rcc.nombre AS 'rubro',
		cc.nivel,
		cc.ultimo_nivel,
		cc.cargo,
		cc.abono,
		cc.cargo - cc.abono AS 'saldo',
		cc.activa 
	FROM cuentas_contables AS cc
	LEFT JOIN cuentas_contables cc2 ON cc.id_cuenta_padre = cc2.id_cuenta
	INNER JOIN rubro_cuenta_contable AS rcc ON cc.fk_id_rubro = rcc.id_rubro
	WHERE cc.nombre LIKE CONCAT('%',nombre_cta_contable,'%') COLLATE utf8mb4_general_ci;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_formas_de_pago`()
BEGIN	
    SELECT 
		fp.id,
        fp.tipo_de_pago,
        fp.activo
    FROM formas_de_pago AS fp;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_indices_categorias`()
BEGIN

	

	SELECT categoria_producto.id_categoria FROM categoria_producto;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_indice_venta_actual`()
BEGIN

    SELECT
		ventas.id_venta
	FROM ventas
    ORDER BY ventas.id_venta DESC LIMIT 1;

END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_nombres_sucursal`()
BEGIN
	
	SELECT
		id_sucursar,
		nombre
	FROM sucursal ORDER BY id_sucursar;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_proveedor_por_rfc`(
    IN rfc_p VARCHAR(13)
)
    READS SQL DATA
    COMMENT 'Consulta un proveedor por RFC sin dependencias contables'
BEGIN
    SELECT
        p.id_proveedor,
        p.rfc,
        p.nombre,
        p.descripcion,
        p.correo_electronico,
        p.estado,
        p.ciudad,
        p.direccion,
        p.codigo_postal,
        p.activo
    FROM kath_erp.proveedor AS p
    WHERE p.rfc = UPPER(TRIM(rfc_p));
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_rfcProveedores`()
BEGIN
	select
		proveedor.rfc
	from proveedor;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_rfc_clientes`()
BEGIN
	SELECT
		cliente.id_cliente,
		cliente.rfc
	FROM cliente 
    ORDER BY id_cliente ASC;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_rfc_empleado_por_sucursal`(
	IN id_sucursal INT
)
    READS SQL DATA
    COMMENT 'Consulta el alias de los empleados, usado para JCombobox u objetos de tipo lista desplegable'
BEGIN	
    SELECT
    	empleados.id_empleado,
		empleados.nombre_corto
	FROM empleados
    WHERE empleados.id_sucursal = id_sucursal;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_sucursales`()
BEGIN
	SELECT
		id_sucursar,
		nombre,
        descripcion,
        telefono,
        email,
        estado,
        ciudad,
        direccion,
        codigo_postal,
        activo
	FROM sucursal;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_sucursales_nombres`()
    COMMENT 'Procedimeinto para el listado de las sucursales en un combobox'
BEGIN
	SELECT 
		sucursal.id_sucursar AS id,
        sucursal.nombre
	FROM sucursal ORDER BY id_sucursar;
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_tipo_clientes`(
	IN nombre_tipo_cliente VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'LISTADO COMPLETO DE TODAS LAS CATEGORIAS DE CLIENTES REGISTRADAS, FILTRADO POR NOMBRE'
BEGIN
	
    SELECT
		tipo_cliente.id,
        tipo_cliente.nombre,
        tipo_cliente.descripcion,
        tipo_cliente.activo
    FROM tipo_cliente
    WHERE tipo_cliente.nombre LIKE CONCAT('%',nombre_tipo_cliente,'%');
    
END;