# Módulo de retiros de efectivo

## Origen del contrato

La implementación sigue el `database/schema.sql` vigente: el folio tiene
unicidad por sucursal y la tabla distingue los cortes finales de los retiros
parciales mediante `es_retiro_final`.

| Columna | Tipo | Uso |
|---|---|---|
| `id_retiro` | INT UNSIGNED AUTO_INCREMENT | Identificador interno |
| `id_sucursal` | BIGINT UNSIGNED | Sucursal autenticada |
| `id_empleado` | INT UNSIGNED | Empleado responsable |
| `folio` | VARCHAR(10) | Único dentro de cada sucursal mediante `UNIQUE(folio,id_sucursal)` |
| `fecha` | DATE | La asigna el servidor MySQL |
| `descripcion` | VARCHAR(255) | Motivo obligatorio |
| `importe` | DOUBLE | Importe del retiro |
| `es_retiro_final` | TINYINT(1) DEFAULT 0 NOT NULL | TRUE para corte Z del día; FALSE para retiro parcial |
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
| `registrarRetiroDeEfectivo` | `p_id_sucursal BIGINT UNSIGNED, p_id_empleado INT UNSIGNED, p_folio VARCHAR(10), p_descripcion VARCHAR(255), p_importe DECIMAL(18,2), p_es_retiro_final BOOLEAN` | `id=200/500, id_retiro (alta exitosa), message` |
| `inhabilitarRetiroDeEfectivo` | `p_id_retiro INT UNSIGNED, p_id_sucursal BIGINT UNSIGNED` | `id=200/500, message` |
| `getRetiroDeEfectivoById` | `p_id_retiro INT UNSIGNED, p_id_sucursal BIGINT UNSIGNED` | `id_retiro, id_sucursal, id_empleado, empleado, folio, fecha, descripcion, importe, es_retiro_final, activo` |
| `listRetirosDeEfectivo` | `p_id_sucursal BIGINT UNSIGNED, p_id_empleado INT UNSIGNED nullable, p_fecha_inicial DATE nullable, p_fecha_final DATE nullable, p_ordenamiento TINYINT UNSIGNED` | Mismas diez columnas de detalle |

Se reutilizan SP ya instalados en otros módulos:

- `listCmbEmpleadosGasto(idSucursal)`: empleados activos de la sucursal
  para el **formulario de registro**.
- `ver_rfc_empleado_por_sucursal(idSucursal)`: empleados de la sucursal,
  incluidos inactivos, para el **filtro histórico del panel**.

**Importante:** la clave compuesta del esquema es
`UNIQUE(folio,id_sucursal)`: permite repetir un folio en **sucursales
distintas** y rechaza duplicados en una misma sucursal, independientemente
de fecha o estado. El formulario solicita el folio; no inventa un
generador de secuencias ajeno al modelo actual.

### Reglas operativas

- Alta sólo si sucursal activa, empleado activo de esa misma sucursal,
  folio y descripción válidos, e importe positivo de hasta dos decimales.
- `p_es_retiro_final=FALSE`: retiro parcial; `TRUE`: corte final (Z).
  **Un único corte final ACTIVO por fecha y sucursal**: el SP
  `registrarRetiroDeEfectivo` bloquea la fila de la sucursal con `FOR UPDATE`,
  determina `CURDATE()` después de adquirir el bloqueo y comprueba si existe
  un corte final activo. Mientras exista, rechaza cualquier retiro nuevo.
  Si ese corte se inhabilita durante el mismo día, permite registrar un nuevo
  corte final de reemplazo.
- Después de que exista cualquier corte final del día, aunque el anterior
  ya esté inhabilitado, **no se permiten nuevos retiros parciales**. La única
  operación de alta permitida tras inhabilitar el corte es registrar un
  nuevo corte final de reemplazo. Esto conserva el significado de cierre Z.
- **Inhabilitar un corte final sí permite corregir su importe**: el registro
  anterior conserva su historia como inactivo y puede registrarse un nuevo
  corte final el mismo día. Mientras el corte final vigente esté activo,
  cualquier alta adicional queda bloqueada.
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

- `RetiroDeEfectivoRegistro`: comando inmutable de registro; incluye el
  booleano `esRetiroFinal`, no expone ID, fecha ni estado editables y
  valida longitudes e importe.
- `RetiroDeEfectivoDetalle`: record completo de los diez campos del
  ResultSet, incluido `esRetiroFinal`, sucursal y estado histórico.
- `RetiroDeEfectivoFiltro`: record de criterios con rango válido y
  enum `Orden`, etiquetas legibles y códigos SQL.
- `RetiroDeEfectivoController`: llama exclusivamente a los cuatro
  procedimientos de negocio y reutiliza los dos procedimientos de
  empleados. También ofrece la proyección de ocho columnas
  `verRetirosEnTabla` para el panel.
- `AppContext.retiroDeEfectivoController`: acceso compartido.
- `PanelRetirosDeEfectivo`: mantiene la distribución visual de
  `PanelGastos` (encabezado azul oscuro, barra dorada de botones,
  tabla central con scroll y barra inferior azul).
  `DefaultTableModel` de ocho columnas no editables:
  **ID, Folio, Fecha, Empleado, Descripción, Importe, Tipo de retiro y Activo**.
  Se aplica `DataTools.removerEditorDeTabla` y
  `DataTools.definirTamanioDeColumnas` con
  `ConstantsConllections.tablaRetirosDeEfectivoColumnsWidth`.
  Los `JComboBox` permiten seleccionar todos los empleados o uno
  específico y los tres criterios de ordenamiento. Los dos
  `JFormattedTextField` incorporan máscara `##/##/####`
  (dd/MM/aaaa) y validación calendárica estricta.
- `Fr_DatosRetiroDeEfectivo`: un `JFrame` compatible con Eclipse
  WindowBuilder, con `GroupLayout` explícito. Modo registro:
  folio, empleado, descripción e importe; agrega un
  `JCheckBox` explícito «Corte final del día (corte Z)». Si se marca,
  solicita confirmación **antes** de solicitar la contraseña del empleado;
  los retiros parciales mantienen su flujo habitual. La fecha la
  asigna el servidor. En modo detalle muestra el tipo de retiro en
  el mismo checkbox **deshabilitado**, sin posibilidad de actualización.
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
alta en cada sucursal, folio repetido aceptado en sucursales distintas
pero rechazado en la misma sucursal,
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

## Ampliación: corte final diario (Z)

Este cambio añade `es_retiro_final` al DTO de alta, al DTO de detalle,
al mapeo JDBC, al listado, al `DefaultTableModel` y al `JCheckBox` del
formulario. **El nombre real de la columna es `es_retiro_final`** según
`schema.sql`, a pesar de que en la petición se mencionó
`es_corte_final`. El formulario de detalle presenta el estado del check
sin ofrecer edición. El nuevo ancho de columnas en
`ConstantsConllections.tablaRetirosDeEfectivoColumnsWidth` tiene ocho
entradas; la columna de estado se desplaza del índice 6 al 7 y el
control de inhabilitación se adapta al nuevo índice. La contraseña del
empleado sigue siendo obligatoria tanto en altas como en bajas.

### Contrato SQL de la ampliación

Instalar manualmente las cuatro nuevas definiciones entregadas en la
conversación, reemplazando los procedimientos del dump. **No se toca
ningún fichero SQL versionado.** `registrarRetiroDeEfectivo` ahora
requiere **seis** parámetros; el sexto es `p_es_retiro_final BOOLEAN`.
`getRetiroDeEfectivoById` y `listRetirosDeEfectivo` ahora devuelven
diez columnas, con `es_retiro_final` inmediatamente antes de `activo`.
`inhabilitarRetiroDeEfectivo` conserva su contrato y permite dar de
baja el cierre final únicamente durante el día del registro, sin borrar la
clasificación. Una vez inhabilitado, el día permanece cerrado para retiros
parciales, pero puede registrarse un nuevo corte final de reemplazo.

### Matriz de pruebas funcionales con MySQL

1. Dos retiros parciales en la misma sucursal y fecha: permitidos, con
   folios distintos.
2. Primer corte final de la sucursal hoy: permitido.
3. Segundo corte final ACTIVO de la misma sucursal hoy: rechazado. Si el
   primero se inhabilita ese mismo día, se permite un nuevo corte final de
   reemplazo.
4. Primer corte final de otra sucursal hoy: permitido.
5. Después de un corte final activo: cualquier retiro parcial o segundo corte
   final se rechaza. Tras inhabilitar el corte, sigue rechazándose un retiro
   parcial y únicamente se acepta otro corte final. Mismo folio en sucursales
   distintas: permitido; mismo folio en una sucursal: rechazado.
6. Dos solicitudes simultáneas de corte final en una sucursal: sólo
   una debe confirmar `id=200`; la otra debe recibir error.
7. Dos fechas distintas: se permite un cierre por cada fecha.
8. Bajas lógicas y contraseña: conservar rechazo de contraseña incorrecta,
   empleado de otra sucursal, fecha anterior y retiro ya inactivo.
9. Listado y detalle: ambos reflejan el nuevo booleano, para registros
   parciales y finales, activos e inactivos.
10. Abrir ambas vistas en WindowBuilder; comprobar el checkbox, las ocho
    columnas y la selección de la columna de estado al inhabilitar.

**Limitación:** el esquema actual `importe DOUBLE` no garantiza precisión
contable en almacenamiento, y esta ampliación no modifica movimientos
de caja o cuentas contables. `CURDATE()` usa la zona horaria configurada
en la conexión MySQL. Antes de usar el módulo en múltiples husos horarios,
se requiere definir el día comercial por sucursal.

### Corrección del corte Z

La regla definitiva es:

- Antes del primer corte Z del día: se permiten retiros parciales y el corte final.
- Con un corte Z activo: no se permite ninguna nueva alta de retiro.
- Si el corte Z se inhabilita el mismo día: se permite únicamente otro corte Z;
  no se reabre la posibilidad de retiros parciales.
- El historial conserva todos los cortes corregidos como registros inactivos.
- La vista realiza una prevalidación con `listRetirosDeEfectivo` para mejorar
  la UX, pero `registrarRetiroDeEfectivo` vuelve a comprobar la regla dentro
  de la transacción y es la autoridad frente a concurrencia. La prevalidación
  distingue `ABIERTO`, `CORTE_FINAL_ACTIVO` y
  `CORTE_FINAL_PENDIENTE_DE_REEMPLAZO`; en este último estado el formulario
  se abre con el `JCheckBox` de corte final seleccionado y deshabilitado,
  de modo que la corrección no puede convertirse en un retiro parcial.
