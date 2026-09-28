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

La implementación inicial no incluía `PanelGastos`. La ampliación posterior lo integra funcionalmente manteniendo su diseño visual, sin modificar ficheros `.sql`.


## Ampliación: PanelGastos funcional

La rama `feat/modulo-gastos` se actualizó mediante avance rápido al último
`dev` tras haberse fusionado la PR inicial. El usuario creó
`PanelGastos` con Eclipse WindowBuilder; la ampliación conecta todos sus
componentes existentes **sin modificar ni reordenar su GroupLayout**.

- `PanelGastos(Sucursal sucursal)`: constructor de ejecución recomendado,
  equivalente al de `PanelVentas`. Se conserva `PanelGastos()` para
  WindowBuilder; cuando se use ese constructor, el módulo padre debe llamar
  a `establecerSucursalActual(sucursal)` antes de mostrar el panel.
  En ningún caso se debe permitir que el usuario elija una sucursal en el
  propio panel.
- Tabla de 10 columnas, en el mismo orden de
  `GastoController.verGastosEnTabla`: Folio, Fecha, Empleado, Categoría,
  Forma de pago, Descripción, Importe, IVA, Total, Activo.
  `DefaultTableModel.isCellEditable()` siempre devuelve `false`; además
  se usa `DataTools.removerEditorDeTabla`. Los diez anchos se definen en
  `ConstantsConllections.tablaGastosColumnsWidth` y se aplican mediante
  `DataTools.definirTamanioDeColumnas`.
- Filtro de empleados: `GastoController.listCmbEmpleadosFiltroGastos(idSucursal)`,
  precedido por la opción «Todos». Reutiliza el procedimiento existente
  `ver_rfc_empleado_por_sucursal(?)`, que incluye empleados **activos e
  inactivos de la sucursal**, de modo que también permite localizar sus gastos
  históricos. El formulario de registro y edición conserva
  `listCmbEmpleadosGasto(idSucursal)`, que presenta sólo empleados activos.
- Filtro de categorías: se consulta
  `CategoriaDeGastoController.listarCategorias("")`, que devuelve **todas
  las categorías, incluidas las inactivas**; así se pueden filtrar los gastos
  históricos. También se agrega «Todos».
- Ordenamiento: reutiliza `GastoFiltro.Orden` con los cuatro códigos
  del SP (fecha reciente, fecha antigua, empleado y categoría). El enum
  incorpora etiquetas legibles sin cambiar sus códigos.
- Rango de fechas: los dos `JFormattedTextField` conservan las posiciones
  originales del layout y reciben máscara `##/##/####`, separadores fijos y
  marcadores `_`. La conversión usa `LocalDate` con
  `ResolverStyle.STRICT`, admite ambos extremos opcionales y rechaza
  fechas imposibles e intervalos invertidos antes de consultar.
- `btnBuscarGasto` compone `GastoFiltro` y llama a
  `GastoController.verGastosEnTabla`. Si todos los controles están en
  «Todos», con fechas vacías, lista **todos** los registros de la sucursal.
  La consulta se ejecuta en `SwingWorker` para no bloquear el EDT;
  el modelo anterior se conserva si falla la consulta. Una marca de
  versión evita sobrescrituras de resultados por consultas anteriores.
- Agregar y Modificar abren `Fr_DatosGasto` con la sucursal actual
  y el ID seleccionado. El listener `windowClosed` se registra antes
  de mostrar el formulario. **Sólo se refresca la tabla si
  `isOperacionEjecutada()` es verdadero**. Los gastos inactivos no
  pueden abrirse en edición; la regla de fecha de edición sigue siendo
  responsabilidad de `updateGasto` en MySQL, por posibles diferencias de
  huso horario entre el cliente y el servidor.
- Eliminar solicita confirmación explícita e invoca
  `GastoController.eliminarGasto(idGasto,idSucursal)`. Sólo ante
  `SpResponseModel.id()==200` refresca el listado conservando los
  filtros vigentes. La operación también se realiza en segundo plano.
- Exportar Excel reutiliza `DataTools.exportarTablaExcel`, que genera
  **CSV compatible con Excel**, exactamente con las filas visibles de
  la última consulta exitosa.
- `PanelGastosTest` incluye pruebas para máscara/parseo, encabezados y
  anchos, estado no editable, ordenamientos y selección correcta con
  `convertRowIndexToModel`.

### Dependencia SQL detectada en el dump

**Advertencia:** a pesar de que `GastoController.getGastoByID` exige el SP
`getGastoByID(id_gasto,id_sucursal)`, la revisión de
`database/procedures/procedures_gastos.sql` y de
`database/procedures.sql` en el `dev` actualizado mostró que **ninguno
incluye ese procedimiento**. No se agregó porque el usuario reservó para sí
la incorporación de SQL. Sin instalarlo en el ambiente en ejecución,
`Fr_DatosGasto` no podrá precargar un registro para su edición, aunque
el resto del panel pueda funcionar. La definición SQL debe añadirse al
dump por el responsable del repositorio y desplegarse previamente.

### Verificación

```bash
./mvnw -Dtest=PanelGastosTest,GastoControllerTest test
```

Realizar además pruebas manuales con datos reales: carga inicial,
«Todos» sin fechas, filtro combinado y límites abiertos de fecha,
los cuatro ordenamientos, agregar, editar hoy, rechazo de edición fuera
de fecha, inhabilitar y verificar que no reaparezca como activo.
Validar `PanelGastos` visualmente en Eclipse WindowBuilder y comprobar
que el módulo padre inyecta la `Sucursal` autenticada.
