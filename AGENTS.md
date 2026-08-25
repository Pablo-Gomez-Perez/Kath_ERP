# Kath ERP — Codex Instructions

Este archivo define las reglas operativas obligatorias para trabajar en Kath ERP con Codex.
Aplica a todo el repositorio salvo que exista un `AGENTS.md` más específico en un subdirectorio o que el usuario dé una instrucción explícita para una tarea concreta.

## 1. Proyecto

Kath ERP es una aplicación de escritorio para pequeñas y medianas empresas, principalmente PYMES mexicanas. Integra inventarios, compras, ventas, gastos, empleados, flujos de efectivo y procesos administrativos/contables.

Repositorio: `Pablo-Gomez-Perez/Kath_ERP`

Stack principal:

- Java 21
- Java Swing
- JDBC
- Maven
- JUnit 5
- MySQL / MariaDB
- Stored Procedures
- POO
- MVC clásico
- Git / GitHub

No introduzcas frameworks, ORMs o arquitecturas nuevas sin autorización explícita del usuario.

## 2. Principio arquitectónico obligatorio

Toda operación de negocio que acceda o modifique datos debe ejecutarse mediante procedimientos almacenados.

El flujo obligatorio para implementar una operación de negocio es:

1. Verificar la estructura de la tabla principal.
2. Verificar tablas relacionadas, claves foráneas, índices y restricciones relevantes.
3. Inspeccionar los procedimientos almacenados existentes relacionados.
4. Crear o modificar primero el procedimiento almacenado.
5. Aplicar y validar el procedimiento en la base de datos local autorizada.
6. Actualizar los archivos SQL correspondientes dentro de `database/`.
7. Crear o modificar modelos, entidades, Java Records o ViewModels necesarios para mapear la respuesta.
8. Crear o modificar el controlador.
9. Consumir el controlador desde la vista.
10. Ejecutar las pruebas correspondientes.

No empieces implementando la operación directamente en Java si el procedimiento almacenado requerido todavía no existe.

## 3. Acceso a base de datos desde Java

Para operaciones de negocio:

- Usa `CallableStatement`.
- Usa `CALL nombreProcedimiento(...)`.
- Mapea explícitamente parámetros de entrada y resultados.
- Cierra `Connection`, `CallableStatement` y `ResultSet` correctamente, preferiblemente con try-with-resources en código nuevo o refactorizado.

Está prohibido introducir SQL de negocio directo dentro de controladores o vistas, incluyendo:

```sql
SELECT ...
INSERT ...
UPDATE ...
DELETE ...
```

La consulta o mutación debe encapsularse en un procedimiento almacenado.

Si encuentras SQL directo legado, no lo amplíes. Si la tarea requiere tocar esa operación, migra primero la operación a Stored Procedure cuando el alcance lo permita.

## 4. Reglas de procedimientos almacenados

Respeta el estilo y contratos ya utilizados por el proyecto.

### Errores SQL

Para handlers de errores usa `GET DIAGNOSTICS CONDITION 1` cuando sea necesario obtener información del error.

No uses funciones inexistentes como:

```sql
SQLEXCEPTION_MESSAGE()
```

### Transacciones

Usa transacciones solamente cuando una operación necesite atomicidad entre dos o más escrituras relacionadas.

No agregues `START TRANSACTION`, `COMMIT` o `ROLLBACK` a procedimientos que solamente ejecutan lecturas.

Ante errores dentro de una operación transaccional, realiza `ROLLBACK` antes de devolver el error.

### Respuestas de procedimientos

Cuando el procedimiento forme parte de un flujo que ya devuelve una respuesta estándar, conserva el contrato existente, normalmente:

```sql
SELECT id, message;
```

No cambies unilateralmente ese contrato si existen controladores que lo consumen.

### Eliminación lógica

Kath ERP utiliza eliminación lógica en entidades que disponen de la columna `activo`.

Regla general:

- INSERT: nuevo registro activo.
- DELETE lógico: `activo = FALSE`.
- UPDATE de un registro previamente dado de baja: debe rehabilitarse con `activo = TRUE` cuando corresponda al flujo existente.

No sustituyas eliminación lógica por `DELETE FROM` salvo que el usuario lo solicite explícitamente o se trate de datos temporales/de prueba.

### Sucursal

Cuando una operación tenga contexto de sucursal, respeta la sucursal explícitamente proporcionada por la sesión, formulario o controlador.

No infieras silenciosamente la sucursal a partir de empleado, proveedor u otra entidad cuando el flujo ya recibe `idSucursal` explícitamente.

## 5. Estructura SQL del repositorio

Actualmente el repositorio contiene:

- `database/schema.sql`: snapshot/esquema de la base de datos.
- `database/procedures.sql`: dump agregado de procedimientos.
- `database/procedures/procedures_articulos.sql`: procedimientos del módulo de artículos.
- `database/procedures/procedures_compras.sql`: procedimientos del módulo de compras.

Antes de crear un nuevo archivo de procedimientos, verifica si el módulo ya dispone de uno.

Cuando modifiques o crees un procedimiento:

1. Actualiza el archivo modular correspondiente dentro de `database/procedures/`.
2. Aplica la misma definición en la base local autorizada.
3. Valida su firma y comportamiento.
4. Mantén `database/procedures.sql` sincronizado cuando la tarea incluya actualizar el dump agregado o cuando pueda regenerarse de forma segura desde la base local.

No reemplaces ciegamente `database/procedures.sql` con un dump proveniente de una base desconocida.

Para procedimientos ya existentes, inspecciona primero la definición actual con `SHOW CREATE PROCEDURE` y compara con la versión versionada antes de sobrescribirla.

## 6. Base de datos local para Codex

Nunca guardes credenciales dentro de este repositorio ni dentro de `AGENTS.md`.

Codex debe obtener la conexión mediante variables de entorno o mediante un login-path de MySQL.

Variables soportadas/recomendadas:

```text
KATH_DB_HOST
KATH_DB_PORT
KATH_DB_NAME
KATH_DB_USER
KATH_DB_PASSWORD
KATH_DB_LOGIN_PATH
```

Valores típicos de host/puerto pueden ser `127.0.0.1` y `3306`, pero no los asumas si existen variables configuradas.

### Opción preferida: mysql_config_editor

Si existe `KATH_DB_LOGIN_PATH`, usa:

```bash
mysql --login-path="$KATH_DB_LOGIN_PATH" "$KATH_DB_NAME"
```

### Opción por variables de entorno

Si no existe login-path, usa las variables `KATH_DB_HOST`, `KATH_DB_PORT`, `KATH_DB_USER`, `KATH_DB_PASSWORD` y `KATH_DB_NAME`.

No imprimas ni incluyas `KATH_DB_PASSWORD` en logs, commits, PRs o respuestas.

Antes de aplicar cambios SQL, verifica como mínimo:

```sql
SELECT DATABASE();
SELECT VERSION();
```

y confirma que la base activa corresponde a `KATH_DB_NAME`.

### Seguridad de la base local

Está prohibido, salvo autorización explícita del usuario:

- `DROP DATABASE`.
- Eliminar una base completa.
- Vaciar tablas de desarrollo con `TRUNCATE`.
- Borrar masivamente datos reales para preparar tests.
- Modificar bases diferentes de `KATH_DB_NAME`.
- Usar credenciales de `root` si existe un usuario específico de desarrollo/pruebas.

Si una migración o cambio de esquema puede provocar pérdida de datos, detente y solicita autorización antes de ejecutarlo sobre la base de desarrollo.

Las operaciones destructivas necesarias para pruebas deben ejecutarse únicamente sobre una base desechable/de test.

## 7. Flujo para crear o modificar un Stored Procedure local

Antes de editar Java, sigue este proceso:

```text
schema/tablas relacionadas
        -> SP actual
        -> definición nueva del SP
        -> archivo SQL del módulo
        -> aplicar SP en BD local
        -> ejecutar consulta/prueba del SP
        -> modelos Java
        -> controlador
        -> vista
        -> tests
```

Para reemplazar un procedimiento existente, utiliza el patrón compatible con el archivo SQL del módulo, por ejemplo `DROP PROCEDURE IF EXISTS` + `CREATE PROCEDURE` cuando corresponda.

Después de aplicar el procedimiento, verifica:

- firma y número de parámetros;
- tipos de parámetros;
- columnas y aliases del ResultSet;
- comportamiento con datos existentes;
- errores esperados;
- efectos sobre tablas relacionadas;
- transacción y rollback si existen múltiples escrituras.

No inventes columnas, nombres de tablas ni relaciones. Inspecciona el esquema real.

## 8. MVC

Respeta MVC clásico.

### Model

Los modelos representan datos y respuestas. No introduzcas lógica de UI dentro del modelo.

### Controller

Los controladores coordinan acceso a Stored Procedures y mapeo de resultados.

No pongas componentes Swing dentro de nuevos controladores.

Evita introducir lógica visual en controladores nuevos. Si el código legado ya contiene `JOptionPane` dentro de un controlador, no uses ese hecho como patrón para código nuevo.

### View

La vista:

- captura interacción del usuario;
- valida datos de presentación;
- llama al controlador;
- actualiza componentes visuales.

No implementes SQL en la vista.

## 9. Reglas estrictas de Swing/UI

NO modifiques la UI salvo que el usuario lo solicite de forma tácita y explícita para componentes concretos.

Sin autorización expresa está prohibido:

- cambiar la distribución de componentes;
- mover componentes;
- cambiar `GroupLayout`;
- cambiar layouts existentes;
- cambiar tamaños;
- cambiar colores;
- cambiar fuentes;
- cambiar iconos;
- cambiar bordes;
- rediseñar formularios;
- crear una nueva distribución visual;
- reorganizar paneles;
- modificar componentes no relacionados con la tarea.

Sí puedes, cuando la tarea lo requiere:

- agregar `ActionListener`;
- agregar `KeyListener`, InputMap/ActionMap o listeners de modelos;
- agregar validaciones;
- cambiar texto dinámicamente;
- cargar modelos de tabla o combo;
- habilitar/deshabilitar componentes;
- agregar comportamiento funcional sin alterar su posición/distribución.

Si una funcionalidad solicitada exige inevitablemente una modificación de layout, informa al usuario antes de hacerla.

## 10. Compatibilidad con Eclipse WindowBuilder

Los formularios Swing deben permanecer editables con Eclipse WindowBuilder.

Cuando agregues componentes Swing que pertenezcan al formulario:

- decláralos como campos de clase antes del constructor;
- inicialízalos dentro del constructor o métodos de construcción ya existentes;
- conserva el patrón estructural del formulario;
- evita convertir componentes existentes en variables locales si WindowBuilder espera campos;
- no reescribas el `GroupLayout` si no es necesario.

No reformatees masivamente clases Swing generadas o mantenidas por WindowBuilder.

## 11. `Fr_principal`

`Fr_principal` es un archivo sensible por su alcance y tamaño.

No lo modifiques salvo que:

- el usuario lo pida explícitamente; o
- una tarea requiera necesariamente registrar/mostrar un nuevo módulo y el usuario haya autorizado esa integración.

Si el usuario indica que hará la integración manualmente en `Fr_principal`, no lo toques.

## 12. Tablas Swing

Cuando una tabla tenga columnas no editables, no habilites edición global.

Si solo una columna debe ser editable, implementa esa regla explícitamente mediante el `TableModel` y/o editores por columna.

No uses utilidades que deshabiliten todos los editores si la tarea requiere conservar una columna editable.

Al modificar cantidades, precios o importes, evita acumuladores incrementales frágiles cuando sea posible. Prefiere recalcular los totales desde el estado actual de la tabla para evitar inconsistencias.

## 13. Reglas Git

### Rama base

Por defecto, todo trabajo nuevo parte de `dev`.

No trabajes directamente sobre `main`.
No trabajes directamente sobre `dev`.

Antes de crear una rama, verifica que `dev` exista y utiliza su HEAD actual.

### Nueva rama

Cada trabajo nuevo debe realizarse en una rama nueva, usando nomenclatura Git convencional, por ejemplo:

```text
feat/modulo-descripcion
fix/modulo-descripcion
refactor/modulo-descripcion
test/modulo-descripcion
chore/descripcion
docs/descripcion
```

Excepción: si el usuario dice explícitamente que continúes sobre una rama existente, trabaja sobre esa misma rama.

### Commits

Usa Conventional Commits:

```text
feat(...): ...
fix(...): ...
refactor(...): ...
test(...): ...
chore(...): ...
docs(...): ...
```

Mantén los commits cohesionados. No mezcles cambios no relacionados.

### Pull Requests

Por defecto, abre la PR contra `dev`.

No abras una PR duplicada si ya existe una PR con la misma rama head/base. Actualiza la existente.

No cambies la base de una PR existente sin una razón explícita.

No hagas merge automáticamente salvo que el usuario lo solicite.

### Historial

No uses force-push, reset destructivo ni reescritura de historial remoto salvo autorización explícita del usuario.

No introduzcas commits temporales/no-op en `main` o `dev` para crear ramas.

## 14. Alcance de los cambios

Modifica únicamente los archivos necesarios para la tarea.

No hagas refactors oportunistas.
No renombres clases, métodos, variables, tablas o procedimientos sin que sea necesario.
No corrijas estilo general del proyecto durante una tarea funcional.
No agregues dependencias porque "serían mejores" si el problema puede resolverse con el stack actual.

Si detectas deuda técnica fuera del alcance, repórtala por separado; no la mezcles silenciosamente con la implementación.

## 15. Código Java

Usa Java 21, pero conserva compatibilidad con el estilo existente del proyecto.

Reglas generales:

- nombres descriptivos;
- evita `null` ambiguos cuando exista un contrato más claro;
- usa tipos numéricos adecuados para dinero cuando se implemente lógica nueva; preferir `BigDecimal` para cálculos monetarios nuevos;
- no cambies automáticamente código existente basado en `double` si no forma parte del alcance;
- no introduzcas APIs obsoletas;
- no captures `Exception` genérica en código nuevo si puede manejarse un tipo específico razonablemente;
- no suprimas errores silenciosamente.

Cuando mapees un ResultSet, usa los aliases/nombres reales retornados por el SP.

## 16. Dinero e impuestos

No inventes reglas contables o fiscales.

Para cálculos monetarios nuevos, preferir `BigDecimal` y redondeo explícito.

Si el costo/precio ya incluye IVA y debe separarse, el cálculo general para una tasa del 16% es:

```text
base = importeConIVA / 1.16
iva = importeConIVA - base
```

Los artículos marcados como exentos no generan IVA.

Esta regla solo debe aplicarse donde el flujo de negocio indique que el importe incluye IVA; no la generalices a todos los módulos sin verificar el contexto.

## 17. Tests

Después de cambios Java ejecuta como mínimo:

```bash
./mvnw test
```

Cuando existan cambios en Stored Procedures, schema o integración JDBC, ejecuta además las pruebas de integración correspondientes y preferiblemente:

```bash
./mvnw verify
```

En Windows puede utilizarse `mvnw.cmd`.

No afirmes que las pruebas pasaron si no fueron ejecutadas.

Si una prueba falla:

1. determina si el fallo proviene del cambio;
2. corrige el problema si está dentro del alcance;
3. vuelve a ejecutar la prueba;
4. si no puede resolverse, reporta exactamente qué falló y por qué.

## 18. Tests con base de datos

Las pruebas de integración deben utilizar una base desechable/de test siempre que puedan modificar datos destructivamente.

No adaptes una prueba para que dependa del estado arbitrario de la base de desarrollo del usuario.

Los fixtures deben ser deterministas.

Cuando una prueba valida una operación de negocio con Stored Procedures, comprueba también los efectos relevantes, por ejemplo:

- registro principal;
- detalle;
- existencias;
- estado activo/inactivo;
- sucursal correcta;
- totales/importes cuando corresponda.

## 19. Sincronización entre BD local y repositorio

Cuando Codex cree o modifique un Stored Procedure en la base local, la tarea no está terminada hasta que la definición versionada correspondiente también se actualice.

Antes de finalizar:

1. compara el SP local con el archivo SQL versionado;
2. confirma que las firmas coinciden;
3. confirma que los aliases/columnas retornados coinciden con el controlador Java;
4. asegura que no queden cambios SQL únicamente en la base local;
5. incluye el SQL correspondiente en el commit/PR.

Nunca dejes como única fuente de verdad una modificación realizada manualmente en MySQL.

## 20. Schema

Antes de modificar tablas:

- inspecciona `database/schema.sql`;
- inspecciona el schema real local;
- revisa claves foráneas;
- revisa tipos signed/unsigned;
- revisa UNIQUE e índices;
- revisa `AUTO_INCREMENT`;
- revisa nombres reales de columnas, incluso si contienen errores históricos de nomenclatura.

No "corrijas" nombres históricos de columnas solamente por estética, porque puede romper procedimientos y Java existentes.

Si se modifica el schema local autorizado, actualiza `database/schema.sql` dentro del mismo trabajo cuando ese archivo represente el schema vigente del proyecto.

## 21. Revisión antes de terminar

Antes de declarar una tarea completa:

1. revisa `git diff`;
2. verifica que no haya cambios ajenos al alcance;
3. ejecuta `git diff --check` cuando sea posible;
4. ejecuta tests relevantes;
5. verifica Stored Procedures locales si fueron modificados;
6. verifica sincronización SQL repositorio/BD;
7. confirma rama y base de PR;
8. confirma que no se modificó UI/layout sin autorización;
9. confirma que no se introdujo SQL de negocio directo en Java;
10. resume claramente lo implementado y las pruebas ejecutadas.

## 22. Definition of Done

Una tarea se considera terminada solamente cuando:

- respeta la arquitectura Stored Procedure -> Model -> Controller -> View;
- el procedimiento existe y ha sido validado cuando la tarea lo requiere;
- el SQL queda versionado;
- Java compila;
- las pruebas relevantes pasan o los fallos están documentados;
- no existen cambios de UI no autorizados;
- no existen cambios ajenos al alcance;
- los commits siguen Conventional Commits;
- la PR apunta a la rama solicitada, por defecto `dev`.

## 23. Regla de prioridad

Si el usuario da una instrucción explícita que contradice una regla operativa de este archivo para una tarea concreta, sigue la instrucción del usuario siempre que sea técnicamente segura.

No interpretes una petición funcional como permiso implícito para rediseñar UI, cambiar arquitectura, eliminar datos o reescribir historial Git.
