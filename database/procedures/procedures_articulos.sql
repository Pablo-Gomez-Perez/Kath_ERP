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

CREATE PROCEDURE `kath_erp`.`eliminar_articulo`(
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

CREATE PROCEDURE `kath_erp`.`getArticuloById`(
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

CREATE PROCEDURE `kath_erp`.`insertArticulo`(
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

CREATE PROCEDURE `kath_erp`.`insertExistenciaArticuloSucursal`(
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

CREATE PROCEDURE `kath_erp`.`listExistenciaGlobalArticulo`(
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

CREATE PROCEDURE `kath_erp`.`updateArticulo`(
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

CREATE PROCEDURE `kath_erp`.`ver_articulos`(
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

CREATE PROCEDURE `kath_erp`.`ver_codigos_articulos`()
BEGIN
	
    SELECT articulo.codigo_articulo
    FROM articulo;
    
END;