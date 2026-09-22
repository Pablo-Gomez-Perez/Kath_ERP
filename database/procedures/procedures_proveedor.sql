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
BEGIN
	
    DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_proveedor_activo BOOLEAN DEFAULT FALSE;
	DECLARE v_id_cuenta_contable INT DEFAULT 0;

	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

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
	FROM proveedor
	WHERE id_proveedor = idProveedor;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor indicado no existe';
	END IF;

	SELECT
		activo,
		id_cuenta_contable
	INTO
		v_proveedor_activo,
		v_id_cuenta_contable
	FROM proveedor
	WHERE id_proveedor = idProveedor
	FOR UPDATE;

	IF v_proveedor_activo = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor ya se encuentra inactivo';
	END IF;

	SELECT
		cargo,
		abono
	INTO
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = v_id_cuenta_contable
	FOR UPDATE;

	SET v_saldo = ROUND(
		COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
		2
	);

	IF v_saldo <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede inhabilitar el proveedor porque su cuenta contable tiene saldo';
	END IF;

	UPDATE proveedor
	SET activo = FALSE
	WHERE id_proveedor = idProveedor;

	UPDATE cuentas_contables
	SET
		activa = FALSE,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = v_id_cuenta_contable;

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
    COMMENT 'Consulta los detalles de un proveedor por su ID'
BEGIN
	
    SELECT
		pr.id_proveedor, 
        pr.id_cuenta_contable,
        cc.clave,
        pr.rfc,
        pr.nombre,                
        pr.descripcion,
        pr.correo_electronico,
        pr.estado,
        pr.ciudad,
        pr.direccion,
        pr.codigo_postal,
        pr.activo 
	FROM kath_erp.proveedor AS pr
    INNER JOIN kath_erp.cuentas_contables AS cc ON pr.id_cuenta_contable = cc.id_cuenta 
	WHERE pr.id_proveedor = idProveedor;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`insertProveedor`(
	IN p_id_cuenta_contable INT,
	IN p_rfc VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_correo_electronico VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo proveedor'
BEGIN
	
    DECLARE v_id_proveedor INT UNSIGNED DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_cuenta_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_existe_rfc INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;

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

	IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable del proveedor es obligatoria';
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
	INTO v_existe_cuenta
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable;

	IF v_existe_cuenta = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada no existe';
	END IF;

	SELECT
		activa,
		ultimo_nivel
	INTO
		v_cuenta_activa,
		v_cuenta_ultimo_nivel
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable
	FOR UPDATE;

	IF v_cuenta_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada se encuentra inactiva';
	END IF;

	IF v_cuenta_ultimo_nivel = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada no es de ultimo nivel';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_rfc
	FROM proveedor
	WHERE rfc COLLATE utf8mb4_general_ci = TRIM(p_rfc);

	IF v_existe_rfc > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe un proveedor registrado con el RFC indicado';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_asignada
	FROM proveedor
	WHERE id_cuenta_contable = p_id_cuenta_contable;

	IF v_cuenta_asignada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada ya se encuentra asignada a un proveedor';
	END IF;

	INSERT INTO proveedor (
		id_cuenta_contable,
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
		p_id_cuenta_contable,
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

	SET v_id_proveedor = LAST_INSERT_ID();

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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`listProveedores`(
	IN p_nombre_proveedor VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Lista proveedores por nombre'
BEGIN


	 SELECT
		p.id_proveedor,
		p.rfc,
		p.nombre,
		cc.clave,
		p.descripcion,
		p.correo_electronico,
		p.estado,
		p.ciudad,
		p.direccion,
		p.codigo_postal,
		p.activo
	FROM proveedor AS p
	INNER JOIN cuentas_contables AS cc
		ON cc.id_cuenta = p.id_cuenta_contable
	WHERE
		(
			p_nombre_proveedor IS NULL
			OR TRIM(p_nombre_proveedor) = ''
			OR p.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_proveedor), '%')
			OR p.rfc COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_proveedor), '%')
		)
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
	IN p_id_cuenta_contable INT,
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
    COMMENT 'Actualiza los datos de un proveedor'
BEGIN
	
    DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_id_cuenta_actual INT DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_cuenta_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_existe_rfc INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;

	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

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

	IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable del proveedor es obligatoria';
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
	FROM proveedor
	WHERE id_proveedor = p_id_proveedor;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor indicado no existe';
	END IF;

	SELECT id_cuenta_contable
	INTO v_id_cuenta_actual
	FROM proveedor
	WHERE id_proveedor = p_id_proveedor
	FOR UPDATE;

	SELECT COUNT(*)
	INTO v_existe_rfc
	FROM proveedor
	WHERE
		rfc COLLATE utf8mb4_general_ci = TRIM(p_rfc)
		AND id_proveedor <> p_id_proveedor;

	IF v_existe_rfc > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe otro proveedor registrado con el RFC indicado';
	END IF;

	IF v_id_cuenta_actual <> p_id_cuenta_contable THEN

		SELECT
			cargo,
			abono
		INTO
			v_cargo,
			v_abono
		FROM cuentas_contables
		WHERE id_cuenta = v_id_cuenta_actual
		FOR UPDATE;

		SET v_saldo = ROUND(
			COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
			2
		);

		IF v_saldo <> 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede cambiar la cuenta contable del proveedor porque la cuenta actual tiene saldo';
		END IF;

		SELECT COUNT(*)
		INTO v_existe_cuenta
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable;

		IF v_existe_cuenta = 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada no existe';
		END IF;

		SELECT
			activa,
			ultimo_nivel
		INTO
			v_cuenta_activa,
			v_cuenta_ultimo_nivel
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable
		FOR UPDATE;

		IF v_cuenta_activa = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada se encuentra inactiva';
		END IF;

		IF v_cuenta_ultimo_nivel = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada no es de ultimo nivel';
		END IF;

		SELECT COUNT(*)
		INTO v_cuenta_asignada
		FROM proveedor
		WHERE
			id_cuenta_contable = p_id_cuenta_contable
			AND id_proveedor <> p_id_proveedor;

		IF v_cuenta_asignada > 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada ya se encuentra asignada a otro proveedor';
		END IF;

	END IF;

	IF p_activo = FALSE THEN

		SELECT
			cargo,
			abono
		INTO
			v_cargo,
			v_abono
		FROM cuentas_contables
		WHERE id_cuenta = v_id_cuenta_actual
		FOR UPDATE;

		SET v_saldo = ROUND(
			COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
			2
		);

		IF v_saldo <> 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede inhabilitar el proveedor porque su cuenta contable tiene saldo';
		END IF;

	END IF;

	UPDATE proveedor
	SET
		id_cuenta_contable = p_id_cuenta_contable,
		rfc = UPPER(TRIM(p_rfc)),
		nombre = TRIM(p_nombre),
		descripcion = NULLIF(TRIM(p_descripcion), ''),
		correo_electronico = TRIM(p_correo_electronico),
		estado = NULLIF(TRIM(p_estado), ''),
		ciudad = NULLIF(TRIM(p_ciudad), ''),
		direccion = NULLIF(TRIM(p_direccion), ''),
		codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
		activo = p_activo
	WHERE id_proveedor = p_id_proveedor;

	COMMIT;

	SELECT
		200 AS id,
		'Proveedor actualizado correctamente' AS message;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_proveedor_por_rfc`(
	IN rfc_p VARCHAR(13)
)
BEGIN
	
	SELECT
		proveedor.id_proveedor,
        proveedor.id_cuenta_contable,
        sub_cuentas_tercer_nivel.clave,
        proveedor.nombre,
        proveedor.descripcion,
        proveedor.correo_electronico,
        proveedor.estado,
        proveedor.ciudad,
        proveedor.direccion,
        proveedor.codigo_postal
	FROM proveedor
    INNER JOIN sub_cuentas_tercer_nivel ON proveedor.id_cuenta_contable = sub_cuentas_tercer_nivel.id_cuenta
	WHERE proveedor.rfc = rfc_p;
    
END;

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`ver_rfcProveedores`()
BEGIN
	select
		proveedor.rfc
	from proveedor;
END;