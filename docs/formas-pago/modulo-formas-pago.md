# Módulo de formas de pago

## Refactor de acceso y refresco

El controlador deja de conocer componentes Swing. El listado principal se
obtiene mediante:

```java
List<FormasDePago> listarFormasDePago() throws SQLException
```

El procedimiento almacenado utilizado continúa siendo
`ver_formas_de_pago()`. El `PanelFormasDePago` transforma los modelos
de dominio en filas de su `DefaultTableModel`. El formulario de cobro
`Fr_FormasDePago` reutiliza la misma lista y proyecta únicamente ID,
nombre e importe editable.

Todos los accesos JDBC de `FormasDePagoController` utilizan
try-with-resources:

- listado;
- alta;
- actualización;
- inhabilitación;
- consulta por ID.

Se elimina la conexión estática compartida y no se usa
`Conexion.cerrarConexion(...)` dentro del controlador.

## Actualización del panel

La tabla administrativa mantiene sus tres columnas:

- Id;
- Forma de pago;
- Activo.

`DataTools.removerEditorDeTabla` continúa aplicándose a la tabla
principal. El listado se reemplaza únicamente después de obtener una lista
válida; ante un error de consulta no se borra primero el contenido visible.

El panel se refresca después de cada operación exitosa:

- alta: `Fr_DatosFormaDePago.isOperacionEjecutada()` queda en `true`,
  se cierra el diálogo y `windowClosed` recarga la tabla;
- actualización: mismo flujo;
- baja lógica: tras completar el SP sin `SQLException`, se muestra el
  mensaje de éxito y se recarga inmediatamente la tabla.

Cancelar el formulario o recibir una excepción no refresca ni muestra una
operación como exitosa.

## Alcance SQL

No se modifican procedimientos almacenados en esta rama. El código actual
usa las firmas que ya consume el módulo actualizado:

```sql
CALL insert_forma_de_pago(?, ?);
CALL update_forma_de_pago(?, ?, ?);
CALL eliminar_forma_pago(?);
CALL bucar_forma_pago_por_id(?);
CALL ver_formas_de_pago();
```

El `database/procedures.sql` versionado todavía muestra firmas antiguas
para alta y actualización, mientras que Java ya utiliza el parámetro
`es_flujo_efectivo`. Esta discrepancia debe regularizarse por separado con
autorización explícita para editar SQL.

## Pruebas

```bash
./mvnw -Dtest=FormasDePagoControllerTest test
```

También debe comprobarse manualmente:

1. abrir el módulo y listar registros;
2. crear una forma de pago y confirmar refresco inmediato;
3. editar nombre/`es_flujo_efectivo` y confirmar refresco;
4. inhabilitar y confirmar cambio a «Inactivo»;
5. cancelar alta/edición y confirmar que no se reporta operación exitosa;
6. abrir el formulario de cobro de ventas y comprobar que la migración del
   listado no alteró captura de importes.
