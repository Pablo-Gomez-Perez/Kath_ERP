# Catálogo de categorías de gasto

## Alcance

El módulo se fundamenta en `database/schema.sql` de la rama `dev`:

| Columna | Tipo | Regla |
| --- | --- | --- |
| `id_categoria` | INT autoincremental | Identificador |
| `nombre` | VARCHAR(255) | Obligatorio |
| `descripcion` | VARCHAR(550) | Opcional |
| `ACTIVO` | TINYINT(1) | `TRUE` por defecto |

`gastos.id_categoria` tiene una clave foránea con `ON DELETE RESTRICT`.
Por tanto, **eliminar es una baja lógica** y conserva los gastos históricos.
**Actualizar reactiva automáticamente la categoría**, incluso si antes estaba
inactiva, porque así está definido el contrato funcional de este módulo.

## Instalación manual previa

Por instrucción del responsable del repositorio, **NO se modifica ningún
archivo SQL** y los procedimientos almacenados se entregan íntegros en la
conversación correspondiente. Deben instalarse manualmente en la base local y
después incorporarse al futuro dump por su propietario.

| Procedimiento | Parámetros | Respuesta |
| --- | --- | --- |
| `insertCategoriaDeGasto` | `p_nombre VARCHAR(255)`, `p_descripcion VARCHAR(550)` | `id, message` |
| `updateCategoriaDeGasto` | `p_id_categoria INT`, `p_nombre VARCHAR(255)`, `p_descripcion VARCHAR(550)` | `id, message` |
| `deleteCategoriaDeGasto` | `p_id_categoria INT` | `id, message` |
| `listCategoriasDeGasto` | `p_nombre VARCHAR(255)` nullable | `id_categoria, nombre, descripcion, activo` |
| `getCategoriaGastoById` | `p_id_categoria INT` | `id_categoria, nombre, descripcion, activo` |
| `listCmbCategoriaDeGasto` | Sin parámetros | `id, nombre` |

Las escrituras usan **`id=200`** para confirmar y **`id=500`** para
errores, no retornan el ID de la entidad como código de éxito. El listado
incluye **activas e inactivas**; el ComboBox únicamente **activas**. La
consulta por ID devuelve también categorías inactivas para poder editarlas.

La validación de nombres duplicados es operativa, no una garantía de unicidad
bajo concurrencia: para ello se necesitaría un índice único (un cambio del
esquema que no se autoriza en esta tarea).

**Nota de compatibilidad:** la columna `nombre` del esquema actual utiliza
`utf8mb4_0900_ai_ci`, colación propia de MySQL 8. Para una instalación en
MariaDB, deberá corregirse el esquema de manera expresa antes de importarlo.

## Integración Java

- `CategoriaDeGasto`: mapea las cuatro columnas y valida longitud.
- `CategoriaDeGastoController`: invoca únicamente los seis SP indicados.
- `AppContext.categoriaDeGastoController`: punto de acceso compartido.
- `Fr_DatosCategoriaDeGasto`: ventana con `GroupLayout`, siguiendo el
  patrón de diseño de `Fr_DatosEmpleado` y el catálogo de productos.

### Contrato del formulario para el futuro panel

```java
new Fr_DatosCategoriaDeGasto(
        Fr_DatosCategoriaDeGasto.OPCION_CREAR, 0);
new Fr_DatosCategoriaDeGasto(
        Fr_DatosCategoriaDeGasto.OPCION_EDITAR, idSeleccionado);
```

El formulario implementa `isOperacionEjecutada()`. El panel que desarrollará
el propietario puede escuchar `windowClosed` para refrescar sólo si devuelve
`true`. Cancelar, cerrar o recibir `id=500` no activa la bandera.

La edición de una categoría inactiva reactiva automáticamente el registro;
no existe control `activo` en el formulario por decisión funcional.

El método `verCategoriasEnTabla(nombre)` entrega columnas en orden:
`id_categoria, nombre, descripcion, activo` (el último como Activo/Inactivo).
Propaga `SQLException` para que el panel distinga una lista vacía de un
error JDBC. `listCmbCategoriaDeGasto()` entrega una colección de
`JComboboxDataViewModel(id, nombre)`.

## Verificación

Pruebas unitarias sin necesidad de base de datos:

```bash
mvn -Dtest=CategoriaDeGastoControllerTest test
```

Pruebas manuales que requieren los seis SP instalados:

1. Crear categoría con y sin descripción: verificar que queda activa.
2. Intentar nombre vacío o de más de 255 caracteres.
3. Crear categoría con nombre duplicado: recibir `id=500`.
4. Listar con filtro y sin filtro: incluir registros activos e inactivos.
5. Consultar por ID, editar y comprobar que la actualización fuerza
   `ACTIVO=TRUE`.
6. Inhabilitar categoría: `ACTIVO=FALSE` sin eliminar las referencias
   históricas de la tabla `gastos`.
7. Reintentar inhabilitar: rechazar porque ya está inactiva.
8. Comprobar que `listCmbCategoriaDeGasto()` devuelve exclusivamente
   `id, nombre` de categorías activas.

Las pruebas automatizadas de integración SQL no se pueden considerar válidas
hasta que el propietario publique los seis SP, porque no se autoriza agregar
sus definiciones al repositorio en esta tarea.
