# Configuración de conexión Kath ERP

Kath ERP busca la configuración de MySQL/MariaDB al iniciarse. Si no existe o no se puede establecer la conexión, `Fr_LogIn` abre `Fr_ConfiguracionConexionDB` para registrar o revisar los parámetros.

## Primera ejecución

1. Indique servidor o IP, puerto TCP, nombre de la base de datos, usuario, contraseña y parámetros JDBC.
2. Utilice **Probar conexión**. El registro situado en la parte inferior indica si se estableció la comunicación o, en caso de error, el `SQLState` y código JDBC, sin imprimir credenciales.
3. Pulse **Guardar**. Se crea automáticamente el archivo cifrado `database.properties` y una clave aleatoria independiente `connection.key`. La aplicación utiliza inmediatamente los nuevos parámetros.
4. **Cancelar** cierra el formulario sin escribir archivos ni modificar la conexión actual.

El campo de contraseña inicia vacío deliberadamente: **Kath ERP no distribuye contraseñas predeterminadas**. Durante la instalación debe crearse un usuario de MySQL/MariaDB con permisos mínimos para el funcionamiento del ERP, en lugar de utilizar `root` para operación normal.

## Rutas persistentes

Para localizar el archivo de forma inequívoca en cada arranque, no se utiliza un `JFileChooser` ni la carpeta `Documentos`. Documentos puede estar compartida o sincronizada por herramientas de nube.

| Plataforma | Carpeta por usuario |
| --- | --- |
| Windows | `%APPDATA%/KathERP/` |
| macOS | `~/Library/Application Support/KathERP/` |
| Linux | `$XDG_CONFIG_HOME/kath-erp/` o `~/.config/kath-erp/` |

El directorio puede ajustarse mediante la propiedad JVM `-Dkath.config.dir=/ruta/privada` en despliegues administrados. Los archivos **no deben incluirse en respaldos compartidos** ni versionarse en Git.

Las propiedades JVM `-Ddb.host`, `-Ddb.port`, `-Ddb.name`, `-Ddb.user`, `-Ddb.password` y `-Ddb.params` siguen disponibles. También se conservan las variables de entorno `KATH_DB_HOST`, `KATH_DB_PORT`, `KATH_DB_NAME`, `KATH_DB_USER`, `KATH_DB_PASSWORD` y `KATH_DB_PARAMS`. Las propiedades JVM tienen prioridad, seguidas por el entorno y el archivo guardado.

## Seguridad y copias de respaldo

`database.properties` **no es un archivo de texto plano**: sus propiedades se cifran íntegramente con AES-256-GCM, usando un nonce aleatorio diferente cada vez que se guarda. `connection.key` tiene una clave de 256 bits generada localmente. En sistemas POSIX se utilizan permisos restrictivos de propietario (directorio `0700`, archivos `0600`).

**Limitación:** quien tenga acceso a la cuenta del usuario y pueda leer ambos archivos puede reconstruir la contraseña. La medida impide la lectura casual de `database.properties`; no equivale a un almacén de secretos nativo del sistema operativo. La protección física del equipo y los permisos del usuario siguen siendo necesarios. Para rotar una clave expuesta, cambie primero la contraseña del usuario MySQL, elimine de forma controlada ambos archivos y vuelva a configurar el programa.

Para instalaciones remotas, evite exponer MySQL públicamente. Use VPN o reglas de red restrictivas, mínimo privilegio de usuario y configure TLS correctamente en `db.params`; en Connector/J, `sslMode=VERIFY_IDENTITY` requiere certificados válidos para el nombre del servidor.

## Compatibilidad con versiones anteriores

El antiguo `config/database.properties` relativo a la carpeta desde la que se ejecutaba el programa deja de ser la fuente de configuración. Por seguridad no se importa automáticamente el antiguo archivo en texto plano: reintroduzca los datos una vez desde el nuevo formulario. El archivo antiguo debe eliminarse de las distribuciones tras la migración.

`Conexion.DATA_BASE` se mantiene como alias para conservar los controladores existentes, pero ahora utiliza el nombre configurado en `db.name`. Tenga en cuenta que los procedimientos almacenados que incluyan referencias internas a `kath_erp` siguen requiriendo ese esquema en el servidor de destino: generalizar sus nombres exige una migración SQL independiente.

## Pruebas

Las pruebas de almacenamiento no necesitan un servidor MySQL:

```bash
mvn -Dtest=ConfiguracionConexionDBServiceTest test
```

Validaciones de aceptación manual: instalación sin archivo; prueba positiva/negativa de conexión; cancelar sin modificación; guardar y reiniciar; abrir formulario con configuración existente; comprobar que no aparece contraseña en la consola ni en texto plano en el archivo; ejecución desde otra carpeta de trabajo; conexión a servidor remoto con TLS.
