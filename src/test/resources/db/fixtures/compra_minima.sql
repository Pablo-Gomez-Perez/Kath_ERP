INSERT INTO grupo_contable (
    id_grupo,
    nombre_grupo
) VALUES (
    1,
    'Activo de pruebas'
);

INSERT INTO rubro_cuenta_contable (
    id_rubro,
    fk_id_grupo_contable,
    nombre,
    descripcion,
    naturaleza
) VALUES (
    1,
    1,
    'Cuentas de prueba',
    'Rubro exclusivo para las pruebas de integración',
    TRUE
);

INSERT INTO cuentas_contables (
    id_cuenta,
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
) VALUES
    (
        1,
        NULL,
        1,
        'IT-EMPLEADO',
        'Empleado de pruebas',
        'Cuenta ficticia para pruebas de integración',
        1,
        TRUE,
        0,
        0,
        TRUE,
        '2026-01-01'
    ),
    (
        2,
        NULL,
        1,
        'IT-PROVEEDOR',
        'Proveedor de pruebas',
        'Cuenta ficticia para pruebas de integración',
        1,
        TRUE,
        0,
        0,
        TRUE,
        '2026-01-01'
    );

INSERT INTO sucursal (
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
) VALUES (
    1,
    'Sucursal de pruebas',
    'Sucursal ficticia para pruebas de integración',
    '9610000000',
    'sucursal@kath.test',
    'Chiapas',
    'Tuxtla Gutiérrez',
    'Dirección ficticia 1',
    '29000',
    TRUE
);

INSERT INTO empleados (
    id_empleado,
    id_cuenta_contable,
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
    1,
    1,
    1,
    'XEXX010101000',
    'XEXX010101HNEXXXA4',
    'Empleado Integración',
    'Emp IT',
    '1990-01-01',
    'empleado@kath.test',
    'Chiapas',
    'Tuxtla Gutiérrez',
    'Dirección ficticia 2',
    '29000',
    'hash-de-prueba',
    TRUE
);

INSERT INTO proveedor (
    id_proveedor,
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
    1,
    2,
    'XAXX010101000',
    'Proveedor Integración',
    'Proveedor ficticio para pruebas de integración',
    'proveedor@kath.test',
    'Chiapas',
    'Tuxtla Gutiérrez',
    'Dirección ficticia 3',
    '29000',
    TRUE
);

INSERT INTO categoria_producto (
    id_categoria,
    nombre,
    descripcion,
    activo
) VALUES (
    1,
    'Categoría de pruebas',
    'Categoría ficticia para pruebas de integración',
    TRUE
);

INSERT INTO articulo (
    id_articulo,
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
    100,
    1,
    1,
    'ART-IT-001',
    '01010101',
    'H87',
    'Artículo de integración',
    'Artículo ficticio para pruebas de integración',
    FALSE,
    100.00,
    TRUE
);
