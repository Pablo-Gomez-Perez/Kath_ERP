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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listCmbProveeodor`()
BEGIN
	
    SELECT 
    	p.id_proveedor AS id,
    	p.nombre 
    FROM kath_erp.proveedor  AS p
    WHERE p.activo = true;
    
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`updateProveedor`(
    IN p_id_proveedor INT UNSIGNED,
    IN p_rfc VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_correo_electronico VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
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