# Primera inicialización de Kath ERP

## Alcance

Este flujo ocurre **después de configurar una conexión a una base de datos
con el esquema y sus procedimientos instalados**, antes de que se habilite el
login. Se detecta a partir del estado de la **base de datos**, no del equipo:
reinstalar la aplicación o conectarse desde otro cliente NO crea usuarios ni
sucursales cuando ya existe cualquier empleado, incluso si está inactivo.

### Dependencias SQL — instalación manual

Se entregan por separado tres procedimientos en el chat y en el archivo
`bootstrap_kath_erp_install.sql` de la entrega (NO se versiona en la rama,
porque los archivos SQL del proyecto son responsabilidad del propietario):

- `consultarEstadoInicializacion()`: una fila
  `requiere_inicializacion,sucursales,sucursales_activas`.
- `listSucursalesInicializacion()`: sucursales activas con
  `id_sucursal,nombre,estado,ciudad,direccion,codigo_postal`.
- `inicializarKathErp(id_sucursal_existente, datos_sucursal, datos_admin, hash)`:
  operación transaccional que usa `GET_LOCK` para serializar intentos
  simultáneos, vuelve a comprobar que **no hay ningún empleado** y crea
  un primer administrador `ADMIN` y, sólo cuando no existen sucursales,
  también la primera sucursal. Devuelve `id,message,id_sucursal,id_empleado`.
  En caso de error hace `ROLLBACK` y libera el bloqueo.

Instalar los SP en la **base efectiva de la configuración JDBC** antes de
abrir Kath ERP por primera vez. El proyecto mantiene ficheros de esquema
y procedimientos separados; el asistente **no crea tablas ni instala MySQL**.

## Comportamientos

1. **Sin sucursales y sin empleados:** el asistente pide datos reales de
   sucursal (nombre, teléfono, estado, ciudad, domicilio y código postal;
   descripción y correo opcionales) y del primer administrador (RFC, CURP,
   nombre completo, fecha de nacimiento, correo y contraseña personalizada).
2. **Con sucursales activas y sin empleados:** el asistente muestra las
   sucursales existentes. Es obligatorio elegir una; no se crea otra.
3. **Con sucursales pero ninguna activa y sin empleados:** se bloquea el
   inicio de sesión y se exige corregir manualmente el estado de la BD.
4. **Con algún empleado, incluso inactivo:** no se activa el asistente;
   las recuperaciones de credenciales deben seguir un procedimiento
   administrativo diferente, no reabrir la posibilidad de crear otro ADMIN.
5. **Al completar:** el procedimiento registra al primer empleado con
   usuario `ADMIN` y contraseña PBKDF2; asigna al empleado **todos
   los permisos ya existentes en el catálogo `permisos`**. Si el catálogo
   está vacío, no se crean permisos inventados. El cliente consulta de
   nuevo el estado real antes de habilitar el login.
6. **Contraseña:** la persona define una contraseña de al menos diez
   caracteres y la confirma. No se instala con `ADMIN/ADMIN`; la
   contraseña no debe ser `ADMIN`. La API
   `PasswordHashService.hashPassword(char[])` evita crear un segundo
   String con el secreto; Swing borra los arreglos tras el procesamiento,
   y MySQL sólo recibe el hash resultante.
7. **Errores:** no se interpreta una excepción SQL como ausencia de
   empleados. Si los SP no están instalados o falla la conexión, no se
   permite omitir el asistente e iniciar sesión.
8. **UI:** `Fr_LogIn` conserva su distribución; sólo cambia su
   comportamiento durante el arranque. `Fr_InicializacionSistema` es
   un JFrame nuevo con componentes y GroupLayout declarativos aptos
   para WindowBuilder. El constructor sin parámetros no abre conexiones.

### Seguridad y concurrencia

El bloqueo nombrado `kath_erp_bootstrap_v1` evita que dos instancias
del asistente creen administradores simultáneamente. No sustituye una
política de privilegios MySQL: el usuario JDBC que puede ejecutar este
procedimiento podría inicializar una BD vacía mediante otra herramienta.
Provisionar la conexión de manera controlada, limitar EXECUTE, y no
reutilizar `root` en producción.

El SP derivará estado/ciudad/dirección/CP del administrador de la
sucursal elegida; son los valores obligatorios que el esquema espera
para su primer empleado. No se inventa RFC, CURP ni fecha de nacimiento.

**Advertencia:** si más tarde se agregan filas nuevas al catálogo
`permisos`, la asignación del primer administrador NO se actualiza sola.
Definir una migración de permisos antes de incorporar autorizaciones
nuevas.

## Validación

Pruebas unitarias sin BD:

```bash
./mvnw -Dtest=InicializacionSistemaControllerTest,PasswordHashServiceCharArrayTest test
```

Pruebas reales con MySQL 8 (preferiblemente base desechable y respaldo):

- Instalar esquema y SP existentes más los tres SP de bootstrap.
- BD sin empleados ni sucursales: comprobar wizard y alta atómica.
- Contraseñas diferentes, contraseña débil, RFC o CURP faltantes:
  no debe registrarse ni el administrador ni su sucursal.
- Terminar el asistente: entrar con usuario `ADMIN` y contraseña elegida.
- Cerrar y abrir Kath ERP o conectar una segunda PC a la misma BD: debe
  abrir el login normal, sin volver a crear cuentas.
- BD sin empleados pero con sucursales: escoger una activa y confirmar
  que no se generó una sucursal duplicada.
- BD con empleados inactivos solamente: el asistente NO debe abrirse.
- BD sin empleados y con sucursales todas inactivas: bloqueo controlado.
- Dos asistentes simultáneos: uno registra y el otro recibe un rechazo
  sin crear duplicados.

`reset_kath_erp_simulacion.sql` permite borrar todas las tablas de una
**copia desechable** sin alterar definiciones. `TRUNCATE` reinicia
los AUTO_INCREMENT; el primer ID nuevo será **1**, no 0.

## Preparación para Windows

`jpackage` permite construir MSI/EXE autocontenidos en una máquina
Windows con JDK 21 y WiX. Empaqueta un runtime Java y la app, pero NO
instala ni configura el servidor MySQL, sus tablas, ni los SP.
Antes del empaquetado hay que preparar un JAR ejecutable con las
dependencias del proyecto (actualmente el pom no lo genera por defecto)
y probar una imagen `app-image`.

**Windows 7 NO es un destino certificado para Java 21**, por lo que este
cambio no supone ni anuncia compatibilidad con ese sistema. Probar primero
Kath ERP y MySQL en un equipo con SO soportado; para equipo heredado,
revisar migración de SO o arquitectura de acceso remoto.
