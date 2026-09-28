CREATE PROCEDURE `kath_erp`.`createGasto`(
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_id_categoria INT,
    IN p_id_empleado INT UNSIGNED,
    IN p_id_forma_pago INT,
    IN p_descripcion VARCHAR(255),
    IN p_importe DECIMAL(18,2),
    IN p_iva DECIMAL(18,2)
)
    MODIFIES SQL DATA
BEGIN
    DECLARE v_existe INT DEFAULT 0;
    DECLARE v_id_gasto INT UNSIGNED;
    DECLARE v_error TEXT;
    DECLARE v_errno INT;
    DECLARE v_sqlstate CHAR(5);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_errno = MYSQL_ERRNO,
            v_sqlstate = RETURNED_SQLSTATE,
            v_error = MESSAGE_TEXT;

        ROLLBACK;

        SELECT 500 AS id,
               CONCAT('Error ', v_errno, ' (', v_sqlstate,
                      '): ', v_error) AS message;
    END;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0
       OR p_id_categoria IS NULL OR p_id_categoria <= 0
       OR p_id_empleado IS NULL OR p_id_empleado <= 0
       OR p_id_forma_pago IS NULL OR p_id_forma_pago <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La sucursal, categoría, empleado y forma de pago son obligatorios';
    END IF;

    IF p_descripcion IS NULL OR TRIM(p_descripcion) = ''
       OR CHAR_LENGTH(TRIM(p_descripcion)) > 255 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La descripción del gasto es inválida';
    END IF;

    IF p_importe IS NULL OR p_importe <= 0
       OR p_iva IS NULL OR p_iva < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El importe debe ser positivo y el IVA no puede ser negativo';
    END IF;

    START TRANSACTION;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal AND activo = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal no existe o está inactiva';
    END IF;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
      AND id_sucursal = p_id_sucursal
      AND activo = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El empleado no pertenece a la sucursal o está inactivo';
    END IF;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.categoria_de_gasto
    WHERE id_categoria = p_id_categoria AND ACTIVO = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La categoría no existe o está inactiva';
    END IF;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.formas_de_pago
    WHERE id = p_id_forma_pago AND activo = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La forma de pago no existe o está inactiva';
    END IF;

    INSERT INTO kath_erp.gastos (
        id_categoria,
        id_empleado,
        id_sucursal,
        id_forma_pago,
        fecha_operacion,
        descripcion,
        importe,
        iva,
        activo
    )
    VALUES (
        p_id_categoria,
        p_id_empleado,
        p_id_sucursal,
        p_id_forma_pago,
        CURDATE(),
        TRIM(p_descripcion),
        p_importe,
        p_iva,
        TRUE
    );

    SET v_id_gasto = LAST_INSERT_ID();

    COMMIT;

    SELECT 200 AS id,
           CONCAT('Gasto registrado correctamente. Folio: ',
                  v_id_gasto) AS message;
END;

CREATE PROCEDURE `kath_erp`.`deleteCategoriaDeGasto`(
    IN p_id_categoria INT
)
    MODIFIES SQL DATA
    COMMENT 'Inhabilita una categoría sin eliminar sus referencias históricas'
BEGIN

    DECLARE v_activo BOOLEAN DEFAULT NULL;

    DECLARE v_error TEXT;
    DECLARE v_errno INT;
    DECLARE v_sqlstate CHAR(5);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_errno = MYSQL_ERRNO,
            v_sqlstate = RETURNED_SQLSTATE,
            v_error = MESSAGE_TEXT;

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_error
            ) AS message;

    END;

    -- Validar identificador

    IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El identificador de categoria es invalido';

    END IF;

    START TRANSACTION;

    -- Consultar y bloquear el registro

    SELECT ACTIVO
    INTO v_activo
    FROM kath_erp.categoria_de_gasto
    WHERE id_categoria = p_id_categoria
    FOR UPDATE;

    IF v_activo IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La categoria indicada no existe';

    END IF;

    IF v_activo = FALSE THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La categoria ya se encuentra inactiva';

    END IF;

    -- Baja lógica

    UPDATE kath_erp.categoria_de_gasto
    SET ACTIVO = FALSE
    WHERE id_categoria = p_id_categoria;

    COMMIT;

    SELECT
        200 AS id,
        'Categoria inhabilitada correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`deleteGasto`(
    IN p_id_gasto INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED
)
    MODIFIES SQL DATA
BEGIN
    DECLARE v_sucursal BIGINT UNSIGNED DEFAULT NULL;
    DECLARE v_activo BOOLEAN DEFAULT NULL;
    DECLARE v_error TEXT;
    DECLARE v_errno INT;
    DECLARE v_sqlstate CHAR(5);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_errno = MYSQL_ERRNO,
            v_sqlstate = RETURNED_SQLSTATE,
            v_error = MESSAGE_TEXT;

        ROLLBACK;

        SELECT 500 AS id,
               CONCAT('Error ', v_errno, ' (', v_sqlstate,
                      '): ', v_error) AS message;
    END;

    IF p_id_gasto IS NULL OR p_id_gasto <= 0
       OR p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El gasto y la sucursal son obligatorios';
    END IF;

    START TRANSACTION;

    SELECT id_sucursal, activo
    INTO v_sucursal, v_activo
    FROM kath_erp.gastos
    WHERE id_gasto = p_id_gasto
    FOR UPDATE;

    IF v_sucursal IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El gasto indicado no existe';
    END IF;

    IF v_sucursal <> p_id_sucursal THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El gasto no pertenece a la sucursal actual';
    END IF;

    IF v_activo = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El gasto ya se encuentra inhabilitado';
    END IF;

    UPDATE kath_erp.gastos
    SET activo = FALSE
    WHERE id_gasto = p_id_gasto
      AND id_sucursal = p_id_sucursal;

    COMMIT;

    SELECT 200 AS id,
           'Gasto inhabilitado correctamente' AS message;
END;

CREATE PROCEDURE `kath_erp`.`getCategoriaGastoById`(
    IN p_id_categoria INT
)
    READS SQL DATA
    COMMENT 'Consulta los detalles de una categoría de gasto por ID'
BEGIN

    SELECT
        c.id_categoria,
        c.nombre,
        c.descripcion,
        c.ACTIVO AS activo
    FROM kath_erp.categoria_de_gasto AS c
    WHERE c.id_categoria = p_id_categoria;

END;

CREATE PROCEDURE `kath_erp`.`insertCategoriaDeGasto`(
    IN p_nombre VARCHAR(255),
    IN p_descripcion VARCHAR(550)
)
    MODIFIES SQL DATA
    COMMENT 'Registra una nueva categoría de gasto activa'
BEGIN

    DECLARE v_id_categoria INT;
    DECLARE v_duplicados INT DEFAULT 0;

    DECLARE v_error TEXT;
    DECLARE v_errno INT;
    DECLARE v_sqlstate CHAR(5);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_errno = MYSQL_ERRNO,
            v_sqlstate = RETURNED_SQLSTATE,
            v_error = MESSAGE_TEXT;

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_error
            ) AS message;

    END;

    -- Validaciones

    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El nombre de la categoria es obligatorio';

    END IF;

    IF CHAR_LENGTH(TRIM(p_nombre)) > 255
       OR CHAR_LENGTH(COALESCE(p_descripcion, '')) > 550
    THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La longitud de los datos supera el limite permitido';

    END IF;

    START TRANSACTION;

    -- Evitar categorías duplicadas

    SELECT COUNT(*)
    INTO v_duplicados
    FROM kath_erp.categoria_de_gasto AS c
    WHERE
        c.nombre COLLATE utf8mb4_general_ci =
        TRIM(p_nombre) COLLATE utf8mb4_general_ci;

    IF v_duplicados > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Ya existe una categoria con ese nombre';

    END IF;

    -- Inserción

    INSERT INTO kath_erp.categoria_de_gasto (
        nombre,
        descripcion,
        ACTIVO
    )
    VALUES (
        TRIM(p_nombre),
        NULLIF(TRIM(p_descripcion), ''),
        TRUE
    );

    SET v_id_categoria = LAST_INSERT_ID();

    COMMIT;

    SELECT
        200 AS id,
        CONCAT(
            'Categoria registrada correctamente. ID: ',
            v_id_categoria
        ) AS message;

END;

CREATE PROCEDURE `kath_erp`.`listCategoriasDeGasto`(
    IN p_nombre VARCHAR(255)
)
    READS SQL DATA
    COMMENT 'Lista categorías activas e inactivas con filtro opcional por nombre'
BEGIN

    SELECT
        c.id_categoria,
        c.nombre,
        c.descripcion,
        c.ACTIVO AS activo
    FROM kath_erp.categoria_de_gasto AS c
    WHERE
        p_nombre IS NULL

        OR TRIM(p_nombre) = ''

        OR c.nombre COLLATE utf8mb4_general_ci
           LIKE CONCAT(
               '%',
               TRIM(p_nombre),
               '%'
           ) COLLATE utf8mb4_general_ci

    ORDER BY
        c.nombre ASC,
        c.id_categoria ASC;

END;

CREATE PROCEDURE `kath_erp`.`listCmbCategoriaDeGasto`()
    READS SQL DATA
    COMMENT 'Obtiene ID y nombre de categorías activas para el ComboBox de gastos'
BEGIN

    SELECT
        c.id_categoria AS id,
        c.nombre
    FROM kath_erp.categoria_de_gasto AS c
    WHERE c.ACTIVO = TRUE
    ORDER BY c.nombre ASC;

END;

CREATE PROCEDURE `kath_erp`.`listCmbEmpleadosGasto`(
    IN p_id_sucursal BIGINT UNSIGNED
)
    READS SQL DATA
BEGIN
    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    SELECT
        e.id_empleado AS id,
        e.nombre_corto AS nombre
    FROM kath_erp.empleados AS e
    WHERE e.id_sucursal = p_id_sucursal
      AND e.activo = TRUE
    ORDER BY e.nombre_corto, e.id_empleado;
END;

CREATE PROCEDURE `kath_erp`.`listGastos`(
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_id_empleado INT UNSIGNED,
    IN p_id_categoria INT,
    IN p_fecha_inicial DATE,
    IN p_fecha_final DATE,
    IN p_ordenamiento TINYINT UNSIGNED
)
    READS SQL DATA
BEGIN
    DECLARE v_orden TINYINT UNSIGNED DEFAULT 1;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    IF p_fecha_inicial IS NOT NULL
       AND p_fecha_final IS NOT NULL
       AND p_fecha_inicial > p_fecha_final THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El rango de fechas es inválido';
    END IF;

    SET v_orden = COALESCE(p_ordenamiento, 1);

    IF v_orden NOT IN (1, 2, 3, 4) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El ordenamiento solicitado es inválido';
    END IF;

    SELECT
        g.id_gasto,
        g.id_sucursal,
        g.id_categoria,
        c.nombre AS categoria,
        g.id_empleado,
        e.nombre_completo AS empleado,
        g.id_forma_pago,
        fp.tipo_de_pago AS forma_pago,
        g.fecha_operacion,
        g.descripcion,
        g.importe,
        g.iva,
        ROUND(
            CAST(g.importe AS DECIMAL(18,2)) +
            CAST(g.iva AS DECIMAL(18,2)),
            2
        ) AS total,
        g.activo
    FROM kath_erp.gastos AS g
    INNER JOIN kath_erp.categoria_de_gasto AS c
        ON c.id_categoria = g.id_categoria
    INNER JOIN kath_erp.empleados AS e
        ON e.id_empleado = g.id_empleado
    LEFT JOIN kath_erp.formas_de_pago AS fp
        ON fp.id = g.id_forma_pago
    WHERE g.id_sucursal = p_id_sucursal
      AND (p_id_empleado IS NULL OR g.id_empleado = p_id_empleado)
      AND (p_id_categoria IS NULL OR g.id_categoria = p_id_categoria)
      AND (p_fecha_inicial IS NULL OR g.fecha_operacion >= p_fecha_inicial)
      AND (p_fecha_final IS NULL OR g.fecha_operacion <= p_fecha_final)
    ORDER BY
        CASE WHEN v_orden = 1 THEN g.fecha_operacion END DESC,
        CASE WHEN v_orden = 2 THEN g.fecha_operacion END ASC,
        CASE WHEN v_orden = 3 THEN e.nombre_completo END ASC,
        CASE WHEN v_orden = 4 THEN c.nombre END ASC,
        g.id_gasto DESC;
END;

CREATE PROCEDURE `kath_erp`.`updateCategoriaDeGasto`(
    IN p_id_categoria INT,
    IN p_nombre VARCHAR(255),
    IN p_descripcion VARCHAR(550)
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos de una categoría y establece ACTIVO = TRUE'
BEGIN

    DECLARE v_id_bloqueado INT DEFAULT NULL;
    DECLARE v_duplicados INT DEFAULT 0;

    DECLARE v_error TEXT;
    DECLARE v_errno INT;
    DECLARE v_sqlstate CHAR(5);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_errno = MYSQL_ERRNO,
            v_sqlstate = RETURNED_SQLSTATE,
            v_error = MESSAGE_TEXT;

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_error
            ) AS message;

    END;

    -- Validaciones

    IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El identificador de categoria es invalido';

    END IF;

    IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El nombre de la categoria es obligatorio';

    END IF;

    IF CHAR_LENGTH(TRIM(p_nombre)) > 255
       OR CHAR_LENGTH(COALESCE(p_descripcion, '')) > 550
    THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La longitud de los datos supera el limite permitido';

    END IF;

    START TRANSACTION;

    -- Verificar y bloquear el registro

    SELECT id_categoria
    INTO v_id_bloqueado
    FROM kath_erp.categoria_de_gasto
    WHERE id_categoria = p_id_categoria
    FOR UPDATE;

    IF v_id_bloqueado IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La categoria indicada no existe';

    END IF;

    -- Verificar que el nombre no pertenezca a otra categoría

    SELECT COUNT(*)
    INTO v_duplicados
    FROM kath_erp.categoria_de_gasto AS c
    WHERE
        c.nombre COLLATE utf8mb4_general_ci =
        TRIM(p_nombre) COLLATE utf8mb4_general_ci
        AND c.id_categoria <> p_id_categoria;

    IF v_duplicados > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Ya existe otra categoria con ese nombre';

    END IF;

    -- Actualización y reactivación

    UPDATE kath_erp.categoria_de_gasto
    SET
        nombre = TRIM(p_nombre),
        descripcion = NULLIF(TRIM(p_descripcion), ''),
        ACTIVO = TRUE
    WHERE id_categoria = p_id_categoria;

    COMMIT;

    SELECT
        200 AS id,
        'Categoria actualizada correctamente' AS message;

END;

CREATE PROCEDURE `kath_erp`.`updateGasto`(
    IN p_id_gasto INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_id_categoria INT,
    IN p_id_empleado INT UNSIGNED,
    IN p_id_forma_pago INT,
    IN p_descripcion VARCHAR(255),
    IN p_importe DECIMAL(18,2),
    IN p_iva DECIMAL(18,2)
)
    MODIFIES SQL DATA
BEGIN
    DECLARE v_sucursal BIGINT UNSIGNED DEFAULT NULL;
    DECLARE v_fecha DATE DEFAULT NULL;
    DECLARE v_activo BOOLEAN DEFAULT NULL;
    DECLARE v_existe INT DEFAULT 0;
    DECLARE v_error TEXT;
    DECLARE v_errno INT;
    DECLARE v_sqlstate CHAR(5);

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_errno = MYSQL_ERRNO,
            v_sqlstate = RETURNED_SQLSTATE,
            v_error = MESSAGE_TEXT;

        ROLLBACK;

        SELECT 500 AS id,
               CONCAT('Error ', v_errno, ' (', v_sqlstate,
                      '): ', v_error) AS message;
    END;

    IF p_id_gasto IS NULL OR p_id_gasto <= 0
       OR p_id_sucursal IS NULL OR p_id_sucursal <= 0
       OR p_id_categoria IS NULL OR p_id_categoria <= 0
       OR p_id_empleado IS NULL OR p_id_empleado <= 0
       OR p_id_forma_pago IS NULL OR p_id_forma_pago <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Los identificadores del gasto son obligatorios';
    END IF;

    IF p_descripcion IS NULL OR TRIM(p_descripcion) = ''
       OR CHAR_LENGTH(TRIM(p_descripcion)) > 255 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La descripción del gasto es inválida';
    END IF;

    IF p_importe IS NULL OR p_importe <= 0
       OR p_iva IS NULL OR p_iva < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El importe o el IVA son inválidos';
    END IF;

    START TRANSACTION;

    SELECT id_sucursal, fecha_operacion, activo
    INTO v_sucursal, v_fecha, v_activo
    FROM kath_erp.gastos
    WHERE id_gasto = p_id_gasto
    FOR UPDATE;

    IF v_sucursal IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El gasto indicado no existe';
    END IF;

    IF v_sucursal <> p_id_sucursal THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El gasto no pertenece a la sucursal actual';
    END IF;

    IF v_activo = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede modificar un gasto inhabilitado';
    END IF;

    IF v_fecha <> CURDATE() THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El gasto únicamente puede modificarse el día de su registro';
    END IF;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
      AND id_sucursal = p_id_sucursal
      AND activo = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El empleado no pertenece a la sucursal o está inactivo';
    END IF;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.categoria_de_gasto
    WHERE id_categoria = p_id_categoria AND ACTIVO = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La categoría no existe o está inactiva';
    END IF;

    SELECT COUNT(*) INTO v_existe
    FROM kath_erp.formas_de_pago
    WHERE id = p_id_forma_pago AND activo = TRUE;

    IF v_existe = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La forma de pago no existe o está inactiva';
    END IF;

    UPDATE kath_erp.gastos
    SET id_categoria = p_id_categoria,
        id_empleado = p_id_empleado,
        id_forma_pago = p_id_forma_pago,
        descripcion = TRIM(p_descripcion),
        importe = p_importe,
        iva = p_iva
    WHERE id_gasto = p_id_gasto
      AND id_sucursal = p_id_sucursal;

    COMMIT;

    SELECT 200 AS id,
           'Gasto actualizado correctamente' AS message;
END;