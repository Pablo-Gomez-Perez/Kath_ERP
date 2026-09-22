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

CREATE DEFINER=`root`@`localhost` PROCEDURE `kath_erp`.`getListadoEmpleados`(
	IN nombre_empleado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Obtiene un listado completo de todos los empleados registrados en la bd y filtra por nombres'
BEGIN
	
	SELECT 
		
		em.id_empleado,		
		cc.clave,		
		em.rfc,
		em.curp,
		em.nombre_completo,
		em.nombre_corto,	
		em.correo_electronico,		
		em.activo
	
	FROM kath_erp.empleados AS em
	INNER JOIN kath_erp.cuentas_contables AS cc ON em.id_cuenta_contable = cc.id_cuenta
	WHERE em.nombre_completo LIKE CONCAT('%',nombre_empleado,'%');
	
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