# Kath ERP — instrucciones para agentes

Estas instrucciones aplican a todo el repositorio. Una petición explícita del usuario
puede cambiar el alcance de una tarea concreta, pero no autoriza implícitamente cambios
destructivos, rediseños de interfaz, publicación en GitHub ni reescritura de historial.

## Contexto del proyecto

Kath ERP es una aplicación de escritorio para pequeños negocios que debe poder operar
en una red local sin depender de Internet. El stack vigente es:

- Java 21, Java Swing y JDBC;
- Maven Wrapper;
- MySQL 8 para los scripts y pruebas de integración actuales;
- procedimientos almacenados;
- JUnit 5 y Testcontainers.

La aplicación está en modernización gradual. Conserva la arquitectura y el estilo
existentes; no introduzcas frameworks, ORM, servicios en la nube, nuevas dependencias o
una reescritura general sin que formen parte expresa de la tarea.

## Mapa del repositorio

- `src/main/java/com/kathsoft/kathpos/app/model`: modelos y ViewModels.
- `src/main/java/com/kathsoft/kathpos/app/controller`: acceso JDBC, llamadas a
  procedimientos y coordinación de transacciones.
- `src/main/java/com/kathsoft/kathpos/app/view`: formularios Swing.
- `src/main/java/com/kathsoft/kathpos/tools`: utilidades compartidas y conexión.
- `database/schema.sql`: esquema usado para construir la base de pruebas.
- `database/procedures/`: procedimientos separados por módulo.
- `database/procedures.sql`: volcado general histórico; no es la fuente modular de las
  pruebas.
- `src/test/java`: pruebas unitarias (`*Test`) y de integración (`*IT`).
- `src/test/resources/db/fixtures`: datos artificiales deterministas.
- `.github/workflows`: validaciones de CI; el proyecto no tiene flujo de despliegue.

Consulta también `database/README.md` antes de modificar scripts SQL.

## Dirección arquitectónica

Mantén el flujo existente:

```text
Stored Procedure -> Model/ViewModel -> Controller -> View
```

- Las vistas capturan interacción, validan datos de presentación, llaman al controlador
  y actualizan componentes. No agregues JDBC ni SQL a una vista.
- Los modelos transportan datos. No agregues componentes Swing, conexiones ni lógica de
  persistencia a los modelos.
- Los controladores conservan las llamadas a procedimientos almacenados, el mapeo de
  resultados y la transacción JDBC cuando una operación coordina varias escrituras.
- Para código JDBC nuevo o modificado, usa `CallableStatement`, parámetros explícitos y
  try-with-resources. Si una transacción falla, ejecuta `rollback` y restaura el estado de
  la conexión.
- No conviertas automáticamente varias operaciones por tabla en un procedimiento
  monolítico. Cuando el flujo existente usa varios procedimientos, coordínalos en una
  única transacción desde el controlador salvo que la tarea defina otro contrato.
- Extrae la lógica incrustada de forma incremental y sólo dentro del alcance solicitado.
  No uses una tarea puntual como pretexto para refactorizar todo un formulario o módulo.

El código legado puede incumplir estas reglas. No lo tomes como ejemplo para código
nuevo, pero tampoco lo modernices fuera del alcance de la tarea.

## Procedimientos almacenados y esquema

- Inspecciona `database/schema.sql`, las claves y el archivo modular antes de asumir
  tablas, columnas, tipos, relaciones o nombres.
- Cada procedimiento debe tener un único archivo propietario. Los procedimientos de
  partidas, totales o existencias del flujo de compras pertenecen a
  `database/procedures/procedures_compras.sql`, aunque usen tablas de artículos.
- `database/procedures/procedures_articulos.sql` contiene las operaciones propias del
  módulo de artículos.
- No cargues `database/procedures.sql` junto con los archivos modulares: contiene nombres
  duplicados. No lo sincronices ni regeneres salvo que el usuario solicite actualizar el
  volcado histórico.
- Todo cambio de esquema o procedimiento debe quedar versionado en el archivo canónico
  correspondiente; no dejes la única copia en una base local.
- Conserva las firmas, aliases y contratos consumidos por Java. Antes de cambiarlos,
  localiza todos sus consumidores y las pruebas relacionadas.
- Evita `DEFINER`, credenciales, datos de clientes y órdenes repetidas como
  `ALTER DATABASE` en scripts versionados. Mantén UTF-8/`utf8mb4`.
- No inventes reglas contables o fiscales. Para lógica monetaria nueva usa
  preferentemente `BigDecimal` y redondeo explícito; no migres usos legados de `double`
  si no forman parte del cambio.
- Respeta siempre la sucursal recibida por el flujo. No la infieras de otra entidad si el
  controlador ya recibe `idSucursal`.

La aplicación usa una base local en operación normal, pero las pruebas automatizadas no
deben depender de ella. No apliques cambios ni datos de prueba a la base del usuario sin
autorización explícita y sin comprobar primero la base seleccionada. Nunca guardes ni
muestres credenciales.

## Swing y WindowBuilder

No cambies la presentación visual si el usuario no lo solicita explícitamente. Esto
incluye layouts, posiciones, tamaños, colores, fuentes, iconos, bordes y reorganización
de paneles.

Cuando el alcance sí incluya un formulario:

- conserva la compatibilidad con Eclipse WindowBuilder;
- evita reformatear masivamente clases generadas o reescribir `GroupLayout`;
- declara como campos de clase los componentes que WindowBuilder necesite reconocer;
- limita listeners, validaciones y modelos de tabla a la funcionalidad solicitada;
- recalcula importes desde el estado actual en vez de mantener acumuladores frágiles.

`Fr_principal` es especialmente sensible: modifícalo sólo cuando el usuario pida esa
integración. Las pruebas automatizadas no sustituyen la validación manual de apariencia e
interacción de un formulario Swing.

## Estrategia de pruebas

### Pruebas unitarias

- Prueba con JUnit 5 la lógica determinista que no requiere JDBC, Swing ni una base de
  datos.
- Nombra estas clases `*Test`; Maven Surefire las ejecuta con `./mvnw test`.
- No simules una base completa con mocks si el comportamiento depende realmente de SQL,
  restricciones o procedimientos: en ese caso escribe una prueba de integración.

### Pruebas de integración

- Nombra las clases `*IT`; Maven Failsafe las ejecuta durante `./mvnw verify`.
- Usa Testcontainers con la versión de MySQL establecida en las pruebas, actualmente
  `mysql:8.0.46`.
- Construye el contenedor desde `database/schema.sql`, los procedimientos modulares
  necesarios y fixtures artificiales. Nunca uses datos reales ni el estado arbitrario de
  la base de desarrollo.
- Las pruebas directas de procedimientos validan firma, respuesta, restricciones y
  efectos en tablas.
- Las pruebas de controlador validan llamadas, mapeo, orden de operaciones, transacción,
  `commit`/`rollback` y efectos completos del caso de negocio.
- Para compras, comprueba como mínimo cabecera, partidas, total, existencias de la
  sucursal correcta, aislamiento de otras sucursales y rollback ante un detalle inválido,
  tanto al registrar como al editar cuando el cambio afecte esos flujos.

Si cambia intencionalmente el contrato o comportamiento de un procedimiento, actualiza
las pruebas para reflejar la nueva regla aceptada. Si sólo cambia su implementación, las
pruebas de comportamiento deberían seguir pasando sin relajarlas.

## Comandos de verificación

Usa el Maven Wrapper del repositorio:

```bash
# Cambios Java sin JDBC
./mvnw --batch-mode --no-transfer-progress test

# Cambios JDBC, schema, procedimientos o pruebas de integración; requiere Docker
./mvnw --batch-mode --no-transfer-progress verify
```

En Windows usa `mvnw.cmd`. No afirmes que una comprobación pasó si no la ejecutaste;
indica el comando omitido y el motivo. Para cambios sólo de documentación ejecuta al
menos `git diff --check` y revisa el diff.

## GitHub Actions

- GitHub Actions se usa únicamente para CI, no para desplegar ni publicar Kath ERP.
- El workflow actual se ejecuta en PR hacia `dev` y corre `./mvnw ... verify` sobre Linux
  con Java 21 y Docker disponible.
- Mantén permisos mínimos y fija acciones de terceros a un SHA completo.
- Un resultado verde de CI confirma las comprobaciones automatizadas incluidas; no
  garantiza por sí solo la apariencia o interacción de Swing ni casos no cubiertos.

## Git y límites de autorización

- Parte del HEAD actual de `dev` y trabaja en una rama nueva, salvo que el usuario pida
  continuar una rama existente. No trabajes directamente en `main` ni `dev`.
- Usa nombres de rama convencionales (`feat/`, `fix/`, `refactor/`, `test/`, `chore/` o
  `docs/`) y Conventional Commits.
- No mezcles cambios no relacionados ni hagas refactors oportunistas.
- Stage, commit, push, creación o modificación de PR y merge requieren autorización
  explícita del usuario. Una autorización para una acción no implica las posteriores.
- Por defecto, una PR autorizada se crea como draft hacia `dev`, salvo que el usuario
  indique otra base o estado.
- No uses force-push, rebase de una rama publicada, reset destructivo ni reescritura de
  historial remoto sin autorización explícita.

## Code Review Rules

Al revisar cambios, señala como defectos de alta prioridad:

- SQL o acceso JDBC nuevo dentro de vistas o modelos;
- transacciones parciales, sin rollback o que actualizan la sucursal incorrecta;
- cambios de contrato SQL no reflejados en controladores y pruebas;
- un procedimiento duplicado entre archivos modulares o cargado desde el dump histórico;
- pruebas de integración dependientes de una base real, datos no deterministas o Internet;
- cálculos de importes inconsistentes entre cabecera y partidas;
- cambios visuales o refactors amplios que no forman parte de la petición.

No conviertas preferencias de formato en bloqueos de revisión si Maven y el código no
tienen una regla automática equivalente.

## Definition of Done

Antes de declarar una tarea terminada:

1. Revisa `git diff` y confirma que sólo contiene cambios del alcance solicitado.
2. Ejecuta `git diff --check` y las pruebas aplicables.
3. Si cambió SQL, confirma que está en su archivo modular canónico y que las pruebas
   construyen una base desechable desde cero.
4. Si cambió un controlador, verifica firma, mapeo, transacción y manejo de errores.
5. Si cambió Swing, conserva WindowBuilder y documenta la validación manual pendiente o
   realizada.
6. Resume archivos modificados, comportamiento cubierto, comandos ejecutados y cualquier
   limitación real.
