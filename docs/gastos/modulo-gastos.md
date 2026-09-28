# Módulo de gastos

El módulo se construye sobre `gastos`, `categoria_de_gasto`, `empleados`,
`sucursal` y `formas_de_pago`, según `database/schema.sql` en `dev`.
**Este cambio no modifica archivos SQL**. La migración y los procedimientos
necesarios fueron publicados íntegramente en la conversación del proyecto y
deben instalarse manualmente antes de probar funcionalmente la aplicación.

## Migración obligatoria antes de los SP

El esquema original `gastos` no contiene `id_forma_pago`. Sin almacenarlo
sería imposible conservar la selección del ComboBox de la nueva vista.

La migración manual agrega:

- `gastos.id_forma_pago INT NULL`;
- índice `idx_gastos_forma_pago`;
- clave foránea `fk_gastos_forma_pago` hacia `formas_de_pago(id)`,
  `ON DELETE RESTRICT ON UPDATE CASCADE`.

`NULL` permite conservar gastos anteriores sin un medio de pago conocido.
**Los gastos nuevos y las actualizaciones sí requieren la forma de pago**.
Después de regularizar las filas históricas, se puede establecer `NOT NULL`
con una migración posterior autorizada. No inventar un valor para pagos
anteriores sin evidencia contable.

## Contrato de los procedimientos almacenados

| SP | Parámetros | Respuesta |
| --- | --- | --- |
| `createGasto` | `p_id_sucursal BIGINT UNSIGNED, p_id_categoria INT, p_id_empleado INT UNSIGNED, p_id_forma_pago INT, p_descripcion VARCHAR(255), p_importe DECIMAL(18,2), p_iva DECIMAL(18,2)` | `id, message` |
| `updateGasto` | `p_id_gasto INT UNSIGNED` más los siete parámetros de `createGasto` | `id, message` |
| `deleteGasto` | `p_id_gasto INT UNSIGNED, p_id_sucursal BIGINT UNSIGNED` | `id, message` |
| `listGastos` | `p_id_sucursal BIGINT UNSIGNED, p_id_empleado INT UNSIGNED, p_id_categoria INT, p_fecha_inicial DATE, p_fecha_final DATE, p_ordenamiento TINYINT UNSIGNED` | Detalle + nombres relacionados + total + estado |
| `getGastoByID` | `p_id_gasto INT UNSIGNED, p_id_sucursal BIGINT UNSIGNED` | Detalle completo del gasto |
| `listCmbEmpleadosGasto` | `p_id_sucursal BIGINT UNSIGNED` | `id, nombre` (activos de la sucursal) |

`listCmbCategoriaDeGasto()` y `ver_formas_de_pago()` ya existen, por lo
que se reutilizan directamente para los otros dos JComboBox.

En escrituras, `id=200` significa éxito e `id=500` significa error,
incluidos los rechazos de negocio. Un `ResultSet` vacío de listado no es
un error: el controlador propaga `SQLException` para distinguirlo.

### Reglas de negocio

1. El ID de sucursal es **obligatorio** y proviene del contexto de sesión:
   el formulario nunca permite modificarlo. Alta exige sucursal, categoría,
   empleado y forma de pago activos; empleado de la misma sucursal.
2. `fecha_operacion` se establece con `CURDATE()` en MySQL. No se acepta
   fecha desde Java ni es editable en el formulario.
3. Sólo se puede **actualizar un gasto activo registrado durante el día
   actual de la sesión MySQL**. `updateGasto` bloquea la fila con
   `FOR UPDATE` y vuelve a comprobar fecha, sucursal y estado.
4. `deleteGasto` inhabilita la fila, conservando el registro histórico,
   también después del día de alta. La inhabilitación es distinta de la
   regla de edición; si la política del negocio cambiara, habría que
   autorizar otra regla SQL.
5. `listGastos` devuelve gastos activos e inactivos **sólo de la sucursal**
   indicada. Los filtros por empleado, categoría y dos fechas son
   opcionales. Los códigos de orden son: 1 = reciente, 2 = antiguo,
   3 = empleado y 4 = categoría.
6. `getGastoByID` requiere también la sucursal y permite consultar
   registros inactivos para auditoría. Los datos de pago históricos pueden
   ser nulos mientras se completa la migración.

### Interpretación de importes

`importe` se interpreta como **base antes de IVA** e `iva` como importe
del impuesto (cero para gasto sin IVA). `total = importe + iva`. Java
captura `BigDecimal` con máximo dos decimales; el esquema actual tiene
las dos columnas monetarias como `DOUBLE`. Para precisión monetaria
persistente sería recomendable migrarlas a `DECIMAL(18,2)` en un
cambio de esquema autorizado y con validación de datos preexistentes.

La fecha de hoy depende de la **zona horaria de la conexión MySQL**:
en despliegues multisucursal que crucen husos horarios, se requerirá
definir y registrar la zona horaria por sucursal y adaptar el SP para que
la regla de corte de día respete cada sucursal. No se usa la fecha del
reloj del cliente para permitir cambios.

## Arquitectura Java

- `GastoRegistro`: comando inmutable con sucursal, categoría, empleado,
  forma de pago, descripción, importe e IVA. ID = cero para alta.
- `GastoDetalle`: DTO inmutable con campos originales, nombres de
  categoría, empleado y forma de pago, total, fecha y estado.
- `GastoFiltro`: filtros opcionales y enumeración tipada de orden.
- `GastoController`: llamada a los seis nuevos SP y reutilización de
  `ver_formas_de_pago()`; ofrece `verGastosEnTabla(...)` para el futuro
  `PanelGastos`. No contiene SQL embebido.
- `AppContext.gastoController`: controlador compartido.
- `Fr_DatosGasto`: captura/edición, `JComboBox` de las tres relaciones,
  fecha y total de sólo lectura; carga y guardado JDBC en `SwingWorker`.
  Utiliza `GroupLayout` con componentes y grupos explícitos, siguiendo
  el patrón de los formularios Eclipse WindowBuilder ya instalados.

### Apertura desde el futuro PanelGastos

```java
Fr_DatosGasto formAlta = new Fr_DatosGasto(
        Fr_DatosGasto.OPCION_CREAR, 0, idSucursalActual);

Fr_DatosGasto formEdicion = new Fr_DatosGasto(
        Fr_DatosGasto.OPCION_EDITAR, idGastoSeleccionado, idSucursalActual);
```

El panel debe recuperar `idSucursalActual` del objeto de sesión o de la
sucursal asociada al login, no de una selección libre. Para el listado:

```java
GastoFiltro filtro = new GastoFiltro(
        idEmpleadoFiltro, idCategoriaFiltro, fechaInicial, fechaFinal,
        GastoFiltro.Orden.FECHA_RECIENTE);
var filas = AppContext.gastoController.verGastosEnTabla(idSucursalActual, filtro);
```

Las columnas proyectadas para la tabla son: folio, fecha, empleado, categoría,
forma de pago, descripción, importe, IVA, total y estado.

Registrar `windowClosed` **antes** de llamar a `form.setVisible(true)`.
Actualizar la tabla únicamente cuando `form.isOperacionEjecutada()` devuelva
`true`. El controlador dispone de `eliminarGasto(idGasto, idSucursalActual)`
para la acción de inhabilitar del futuro panel.

## Pruebas

Unitarias, independientes de MySQL:

```bash
./mvnw -Dtest=GastoControllerTest test
```

Pruebas manuales imprescindibles **después** de aplicar la migración y los
seis procedimientos: alta con cada ComboBox; importe sin IVA; cambio de
medio de pago; rechazo de empleado de otra sucursal; lectura de
`getGastoByID` sin fuga de sucursal; filtro combinado de fechas/categoría/
empleado; cuatro ordenamientos; edición del día actual; rechazo de
edición al día siguiente y tras inhabilitación; baja lógica; edición de un
registro histórico sin forma de pago; y verificación en Eclipse WindowBuilder.

Esta PR no incluye `PanelGastos` ni escrituras a ficheros `.sql`.
