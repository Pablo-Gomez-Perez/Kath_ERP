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


## Reautenticación obligatoria antes de los movimientos

A partir de la ampliación de esta rama, **registrar e inhabilitar exige
volver a introducir la contraseña del empleado responsable**. La autorización
se comprueba en `RetiroDeEfectivoController`; no consiste únicamente en
mostrar un diálogo visual.

- **Registro:** después de validar folio, empleado, descripción e importe,
  `Fr_DatosRetiroDeEfectivo` solicita la contraseña mediante un
  `JPasswordField` enmascarado. El ID autorizado corresponde exactamente al
  empleado seleccionado en el ComboBox. Cancelar o introducir una contraseña
  vacía **no inicia ningún procedimiento almacenado de escritura**.
- **Inhabilitación:** después de seleccionar y confirmar el retiro,
  `PanelRetirosDeEfectivo` solicita la contraseña de quien figura como
  **empleado responsable del retiro original**, no la de una persona
  arbitraria que haya iniciado sesión. El controlador consulta de nuevo
  `getRetiroDeEfectivoById(idRetiro,idSucursal)` para obtener la identidad
  autorizante de MySQL, en lugar de confiar en el texto de una fila Swing.
- `AutorizacionRetiroService` utiliza los procedimientos YA EXISTENTES
  `getEmpleadoById(idEmpleado)` para resolver ID, alias, sucursal y estado,
  y `getEmpleadoLogin(nombreCorto)` mediante `LoginController.iniciarSesion`.
  El mecanismo de contraseña sigue siendo el mismo
  `PasswordHashService.verifyPassword` (PBKDF2) que el inicio de sesión.
  Compara **ID, sucursal y estado activo** después de verificar el hash.
- `registrarRetiro(retiro,char[])` e
  `inhabilitarRetiro(id,sucursal,char[])` reemplazan los métodos que
  admitían ejecutar movimientos sin contraseña. La falta de contraseña
  o una autorización fallida devuelve `SpResponseModel.id()=401` y
  **no llega a invocar el procedimiento de escritura**. Errores de acceso
  SQL/criptografía provocan rechazo sin realizar la operación.
- La contraseña **no se guarda en DTO, no se convierte a `String`,
  no se registra en logs y no se envía a la base de datos**. El diálogo
  devuelve un arreglo `char[]`; el `SwingWorker` lo borra con
  `Arrays.fill` tras completar la operación. Nunca se conserva una
  contraseña para autorizar movimientos posteriores.
- Se exige que el empleado original continúe activo para autorizar una
  inhabilitación, como exige el SP de autenticación existente. Casos de
  empleados dados de baja que requieran anulaciones demandan definir
  previamente un flujo explícito de autorización de supervisores.

**Límite de seguridad conocido:** la verificación se realiza en el
controlador de la aplicación de escritorio; los cuatro SP de retiros
permanecen sin parámetros de autorización. Una cuenta MySQL con permiso
directo de ejecutar los procedimientos podría omitir la aplicación y
evitar la solicitud de contraseña. Para una autorización estrictamente
obligatoria ante clientes o usuarios que puedan conectarse directamente
a MySQL sería necesario diseñar e implantar un control de privilegios
y autorización del lado servidor, sujeto a aprobación SQL independiente.
El mecanismo actual es **reautenticación de la aplicación**, no una
garantía contra uso directo de credenciales de la BD.

### Pruebas adicionales

```bash
./mvnw -Dtest=AutorizacionRetiroServiceTest,RetiroDeEfectivoControllerTest,PanelRetirosDeEfectivoTest test
```

Además de las pruebas automáticas, verificar manualmente ambos diálogos:
cancelar, contraseña vacía, contraseña incorrecta, contraseña de otro
empleado, empleado inactivo o de otra sucursal, contraseña correcta,
repetición de la autorización en cada movimiento y refresco de tabla
únicamente después de `id=200`. La regla temporal de inhabilitación
sigue verificándose exclusivamente en MySQL con `CURDATE()`.
