# Módulo de retiros de efectivo

## Origen del contrato

La implementación se basa en `database/schema.sql` de la rama `dev`
vigente al crear la rama:

| Columna | Tipo | Uso |
|---|---|---|
| `id_retiro` | INT UNSIGNED AUTO_INCREMENT | Identificador interno |
| `id_sucursal` | BIGINT UNSIGNED | Sucursal autenticada |
| `id_empleado` | INT UNSIGNED | Empleado responsable |
| `folio` | VARCHAR(10) UNIQUE | Folio único global obligatorio |
| `fecha` | DATE | La asigna el servidor MySQL |
| `descripcion` | VARCHAR(255) | Motivo obligatorio |
| `importe` | DOUBLE | Importe del retiro |
| `activo` | TINYINT(1) | TRUE al registrar; FALSE al inhabilitar |

Las claves foráneas relacionan retiro con `empleados.id_empleado` y
`sucursal.id_sucursar`. **No existe una relación suficiente con el saldo
de caja para bloquear retiros por fondos disponibles o modificar un
flujo contable automáticamente**. Incorporar ese comportamiento requiere
diseñar y autorizar el modelo de caja antes de extender los SP.

## Instalación MANUAL de procedimientos

Por las normas de Kath ERP no se modifica ningún archivo `.sql` sin
autorización expresa. Los **cuatro procedimientos** se publicaron íntegros
en la conversación en la que se desarrolló este módulo. Deben agregarse
manualmente a MySQL y al dump versionado, por el propietario:

| SP | Parámetros | Respuesta |
|---|---|---|
| `registrarRetiroDeEfectivo` | `p_id_sucursal BIGINT UNSIGNED, p_id_empleado INT UNSIGNED, p_folio VARCHAR(10), p_descripcion VARCHAR(255), p_importe DECIMAL(18,2)` | `id=200/500, id_retiro (alta exitosa), message` |
| `inhabilitarRetiroDeEfectivo` | `p_id_retiro INT UNSIGNED, p_id_sucursal BIGINT UNSIGNED` | `id=200/500, message` |
| `getRetiroDeEfectivoById` | `p_id_retiro INT UNSIGNED, p_id_sucursal BIGINT UNSIGNED` | `id_retiro, id_sucursal, id_empleado, empleado, folio, fecha, descripcion, importe, activo` |
| `listRetirosDeEfectivo` | `p_id_sucursal BIGINT UNSIGNED, p_id_empleado INT UNSIGNED nullable, p_fecha_inicial DATE nullable, p_fecha_final DATE nullable, p_ordenamiento TINYINT UNSIGNED` | Mismas nueve columnas de detalle |

Se reutilizan SP ya instalados en otros módulos:

- `listCmbEmpleadosGasto(idSucursal)`: empleados activos de la sucursal
  para el **formulario de registro**.
- `ver_rfc_empleado_por_sucursal(idSucursal)`: empleados de la sucursal,
  incluidos inactivos, para el **filtro histórico del panel**.

**Importante:** `folio` es único en TODA la tabla, no únicamente
dentro de la sucursal. El formulario lo exige, pero no genera una
secuencia porque el esquema no define una regla de numeración. La
restricción UNIQUE de MySQL garantiza la unicidad incluso si dos
sucursales intentan registrar simultáneamente el mismo folio.

### Reglas operativas

- Alta sólo si sucursal activa, empleado activo de esa misma sucursal,
  folio y descripción válidos, e importe positivo de hasta dos decimales.
- `fecha=CURDATE()` desde la sesión MySQL; no se acepta una fecha
  suministrada por el cliente ni se permite la edición del retiro.
- Un retiro **sólo puede inhabilitarse durante el día de registro**.
  El SP bloquea la fila con `FOR UPDATE`, verifica fecha, sucursal y
  estado y ejecuta una baja lógica (`activo=FALSE`). Las bajas de días
  anteriores son rechazadas, incluso si el equipo cliente marca otra fecha.
- Consulta individual y listado **obligatoriamente filtrados por
  `id_sucursal`**. Ambos incluyen registros activos e inactivos para
  preservar el historial.
- `p_id_empleado=NULL` y fechas nulas equivalen a «Todos». Rangos
  opcionales permiten filtrar desde una fecha sin límite final o viceversa.
  Ordenamientos: **1** fecha reciente, **2** antigua, **3** empleado.

**Zona horaria:** `CURDATE()` sigue la zona de la sesión MySQL. Si la
instalación opera en husos horarios diferentes, se necesita una política
explícita por sucursal antes de garantizar el corte diario de
inhabilitación.

**Precisión monetaria:** el SP recibe `DECIMAL(18,2)`, pero el esquema
original almacena `importe` como `DOUBLE`; migrar los datos a
`DECIMAL(18,2)` y verificar valores históricos requerirá autorización
separada.

## Implementación Java

- `RetiroDeEfectivoRegistro`: comando inmutable de registro; no expone
  id, fecha ni estado editables y valida longitudes e importe.
- `RetiroDeEfectivoDetalle`: record completo de los nueve campos del
  ResultSet, incluida la identidad de sucursal y el estado histórico.
- `RetiroDeEfectivoFiltro`: record de criterios con rango válido y
  enum `Orden`, etiquetas legibles y códigos SQL.
- `RetiroDeEfectivoController`: llama exclusivamente a los cuatro
  procedimientos de negocio y reutiliza los dos procedimientos de
  empleados. También ofrece la proyección de siete columnas
  `verRetirosEnTabla` para el panel.
- `AppContext.retiroDeEfectivoController`: acceso compartido.
- `PanelRetirosDeEfectivo`: mantiene la distribución visual de
  `PanelGastos` (encabezado azul oscuro, barra dorada de botones,
  tabla central con scroll y barra inferior azul).
  `DefaultTableModel` de siete columnas no editables:
  **ID, Folio, Fecha, Empleado, Descripción, Importe y Activo**.
  Se aplica `DataTools.removerEditorDeTabla` y
  `DataTools.definirTamanioDeColumnas` con
  `ConstantsConllections.tablaRetirosDeEfectivoColumnsWidth`.
  Los `JComboBox` permiten seleccionar todos los empleados o uno
  específico y los tres criterios de ordenamiento. Los dos
  `JFormattedTextField` incorporan máscara `##/##/####`
  (dd/MM/aaaa) y validación calendárica estricta.
- `Fr_DatosRetiroDeEfectivo`: un `JFrame` compatible con Eclipse
  WindowBuilder, con `GroupLayout` explícito. Modo registro:
  folio, empleado, descripción e importe; muestra que la fecha se
  asignará automáticamente en el servidor. Modo detalle: consulta por ID
  y sucursal; todos los controles son de **solo lectura**, sin opción
  de actualización.
- Ambos componentes cargan datos usando `SwingWorker` en
  `windowOpened` / primera visualización. El constructor vacío no
  ejecuta JDBC en WindowBuilder.

### Integración futura desde el módulo padre

```java
PanelRetirosDeEfectivo panel = new PanelRetirosDeEfectivo(sucursalAutenticada);
```

También puede usarse el constructor vacío de WindowBuilder seguido de
`panel.establecerSucursalActual(sucursalAutenticada)` antes de mostrarlo.
No se ha modificado `Fr_principal`, conforme a la restricción general
sobre sus layouts y componentes existentes.

Para abrir un formulario desde otra acción:

```java
new Fr_DatosRetiroDeEfectivo(
    Fr_DatosRetiroDeEfectivo.OPCION_CREAR, 0, idSucursalActual);

new Fr_DatosRetiroDeEfectivo(
    Fr_DatosRetiroDeEfectivo.OPCION_DETALLE, idRetiroSeleccionado, idSucursalActual);
```

El panel registra `windowClosed` antes de mostrar el formulario y
refresca la tabla **sólo si `isOperacionEjecutada()` es verdadero**.
La inhabilitación también refresca únicamente si el SP retorna
`id=200`. Una búsqueda fallida conserva el último listado válido.

El botón «Exportar Excel» reutiliza el exportador existente de
`DataTools`, que actualmente produce CSV compatible con Excel;
no se añade soporte XLSX ni se modifica esa utilidad.

## Pruebas

```bash
./mvnw -Dtest=RetiroDeEfectivoControllerTest,PanelRetirosDeEfectivoTest test
```

Pruebas funcionales pendientes de instalar los cuatro SP manualmente:
alta en cada sucursal, intento de folio duplicado entre sucursales,
empleado de otra sucursal, importe inválido, detalle histórico,
listado sin filtros, filtro por empleado activo e inactivo,
fechas abiertas/cerradas, los tres ordenamientos,
inhabilitación el mismo día, intento de repetir una baja y rechazo de
baja al día siguiente. Comprobar que el cambio de estado no altera
el importe ni elimina la información histórica.

Se requiere además inspección visual con Eclipse WindowBuilder para
validar que el diseñador puede abrir ambos nuevos componentes.
