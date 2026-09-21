DROP PROCEDURE IF EXISTS `kath_erp`.`buscar_cliente_por_nombre`;

CREATE PROCEDURE `kath_erp`.`buscar_cliente_por_nombre`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`buscar_tipoCliente_por_id`;

CREATE PROCEDURE `kath_erp`.`buscar_tipoCliente_por_id`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`cmb_tipoCliente`;

CREATE PROCEDURE `kath_erp`.`cmb_tipoCliente`()
BEGIN
	SELECT
		tipo_cliente.id,
        tipo_cliente.nombre
	FROM tipo_cliente;
END;

DROP PROCEDURE IF EXISTS `kath_erp`.`deleteCliente`;

CREATE PROCEDURE `kath_erp`.`deleteCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`deleteTelefonoCliente`;

CREATE PROCEDURE `kath_erp`.`deleteTelefonoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`eliminar_tipoCliente`;

CREATE PROCEDURE `kath_erp`.`eliminar_tipoCliente`(
	IN id_tipoCliente INT
)
BEGIN
	
    UPDATE tipo_cliente SET
		tipo_cliente.activo = 0
	WHERE tipo_cliente.id = id_tipoCliente;
    
    SELECT 200 AS id, 'Tipo Cliente inhabilitado exitosamente' AS message;
    
END;

DROP PROCEDURE IF EXISTS `kath_erp`.`getArticuloByCodigo`;

CREATE PROCEDURE `kath_erp`.`getArticuloByCodigo`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`getClienteById`;

CREATE PROCEDURE `kath_erp`.`getClienteById`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`getClienteParaVentaById`;

CREATE PROCEDURE `kath_erp`.`getClienteParaVentaById`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`insertArticuloVenta`;

CREATE PROCEDURE `kath_erp`.`insertArticuloVenta`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`insertCliente`;

CREATE PROCEDURE `kath_erp`.`insertCliente`(
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

	DECLARE v_id_cliente INT UNSIGNED DEFAULT 0;
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

	SET v_id_cliente = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Cliente registrado correctamente' AS message;

END;

DROP PROCEDURE IF EXISTS `kath_erp`.`insertPrecioArticuloTipoCliente`;

CREATE PROCEDURE `kath_erp`.`insertPrecioArticuloTipoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`insertTelefonoCliente`;

CREATE PROCEDURE `kath_erp`.`insertTelefonoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`insert_nuevo_tipoCliente`;

CREATE PROCEDURE `kath_erp`.`insert_nuevo_tipoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`listArticulos`;

CREATE PROCEDURE `kath_erp`.`listArticulos`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`listClientes`;

CREATE PROCEDURE `kath_erp`.`listClientes`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`listCmbClientes`;

CREATE PROCEDURE `kath_erp`.`listCmbClientes`()
    READS SQL DATA
    COMMENT 'Listado de nombre cortos de clientes para ComboBox'
BEGIN
	
	SELECT
		c.id_cliente AS id,
		c.nombre_corto AS nombre
	FROM
		kath_erp.cliente AS c;
	
END;

DROP PROCEDURE IF EXISTS `kath_erp`.`listPreciosArticuloTipoCliente`;

CREATE PROCEDURE `kath_erp`.`listPreciosArticuloTipoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`listTelefonosCliente`;

CREATE PROCEDURE `kath_erp`.`listTelefonosCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`updateCliente`;

CREATE PROCEDURE `kath_erp`.`updateCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`updatePrecioPorTipoCliente`;

CREATE PROCEDURE `kath_erp`.`updatePrecioPorTipoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`update_tipoCliente`;

CREATE PROCEDURE `kath_erp`.`update_tipoCliente`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`ver_cliente_por_rfc`;

CREATE PROCEDURE `kath_erp`.`ver_cliente_por_rfc`(
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

DROP PROCEDURE IF EXISTS `kath_erp`.`ver_rfc_clientes`;

CREATE PROCEDURE `kath_erp`.`ver_rfc_clientes`()
BEGIN
	SELECT
		cliente.id_cliente,
		cliente.rfc
	FROM cliente 
    ORDER BY id_cliente ASC;
END;

DROP PROCEDURE IF EXISTS `kath_erp`.`ver_tipo_clientes`;

CREATE PROCEDURE `kath_erp`.`ver_tipo_clientes`(
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
