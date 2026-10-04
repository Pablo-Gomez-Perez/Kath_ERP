# Alta inicial de configuración fiscal

## Problema corregido

`getConfiguracionFiscal()` consulta únicamente la configuración activa. En
una base recién creada no existe ninguna fila, por lo que el formulario
recibía `null`, deshabilitaba Guardar y sólo ofrecía la ruta de
`updateConfiguracionFiscal`.

La configuración fiscal NO forma parte del bootstrap de sucursal/ADMIN.
El formulario `Fr_ConfiguracionFiscal` conserva su layout y decide entre:

- **Alta**: `idConfiguracionFiscal == 0` -> `createConfiguracionFiscal`.
- **Edición**: `idConfiguracionFiscal > 0` -> `updateConfiguracionFiscal`.

El ID cero sólo existe como estado de la UI; MySQL asigna el ID real.

## Procedimiento nuevo

El procedimiento `createConfiguracionFiscal` se entrega manualmente en
la conversación. No se modifican ficheros `.sql` del repositorio.

Parámetros:

1. `p_rfc_emisor VARCHAR(13)`
2. `p_nombre_razon_social VARCHAR(255)`
3. `p_nombre_comercial VARCHAR(255)`
4. `p_regimen_fiscal_clave CHAR(3)`
5. `p_regimen_fiscal_descripcion VARCHAR(150)`
6. `p_numero_registro_sistema VARCHAR(100)`

Retorna `id` y `message`. El `id` exitoso es el
`LAST_INSERT_ID()`.

El SP permite el alta únicamente cuando no existe otra configuración
**activa**. Esto mantiene el contrato de `getConfiguracionFiscal` y de
los procesos de tickets, que esperan una sola configuración activa. Si
existieran filas históricas inactivas, se permite crear una nueva activa.

Para impedir dos altas simultáneas en una tabla inicialmente vacía, el
procedimiento utiliza un bloqueo nombrado de MySQL
`kath_erp_configuracion_fiscal_activa` y vuelve a comprobar el estado
antes del INSERT.

## Java

`ConfiguracionFiscalController.createConfiguracionFiscal` llama sólo al
SP; no contiene SQL de negocio. Alta y actualización reutilizan la misma
validación Java antes de abrir JDBC.

Cuando `getConfiguracionFiscal()` no retorna fila,
`Fr_ConfiguracionFiscal`:

- fija `idConfiguracionFiscal=0`;
- limpia los campos;
- mantiene Guardar habilitado;
- al guardar llama al SP de alta;
- después de éxito vuelve a cargar el registro y en operaciones futuras
  utiliza el SP de actualización.

No se agrega ningún componente ni se modifica el GroupLayout.

## Pruebas

```bash
./mvnw -Dtest=ConfiguracionFiscalControllerTest test
```

Pruebas manuales recomendadas:

1. tabla vacía -> abrir configuración -> capturar -> alta exitosa;
2. volver a abrir -> datos cargados -> actualización exitosa;
3. intentar ejecutar `createConfiguracionFiscal` otra vez -> rechazo;
4. dos clientes intentando la primera alta simultáneamente -> sólo uno
   debe confirmar el INSERT;
5. fila inactiva sin filas activas -> permitir nueva configuración activa;
6. validar generación de ticket después del alta: debe existir exactamente
   una configuración activa.
