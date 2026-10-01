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
) VALUES
    (
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
    ),
    (
        2,
        'Sucursal de control',
        'Sucursal usada para comprobar aislamiento de existencias',
        '9610000001',
        'sucursal-control@kath.test',
        'Chiapas',
        'Tuxtla Gutiérrez',
        'Dirección ficticia 4',
        '29000',
        TRUE
    );

INSERT INTO empleados (
    id_empleado,
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
) VALUES
    (
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
    ),
    (
        101,
        1,
        1,
        'ART-IT-002',
        '01010101',
        'H87',
        'Segundo artículo de integración',
        'Artículo ficticio para probar compras con varios detalles',
        FALSE,
        50.00,
        TRUE
    );

INSERT INTO formas_de_pago (
    id,
    tipo_de_pago,
    activo
) VALUES (
    1,
    'Efectivo',
    TRUE
);
