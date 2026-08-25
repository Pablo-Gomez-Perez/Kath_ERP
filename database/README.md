# Base de datos de Kath ERP

Estos scripts describen la estructura de base de datos compatible con MySQL
8.0.46. No contienen información de clientes ni datos operativos.

Para las pruebas de integración de artículos y compras, el orden de ejecución
es:

1. `schema.sql`
2. `procedures/procedures_articulos.sql`
3. `procedures/procedures_compras.sql`

Los scripts deben ejecutarse seleccionando previamente la base `kath_erp`:

```shell
mysql -u <usuario> -p kath_erp < database/schema.sql
mysql -u <usuario> -p kath_erp < database/procedures/procedures_articulos.sql
mysql -u <usuario> -p kath_erp < database/procedures/procedures_compras.sql
```

Cada procedimiento modular tiene un único archivo propietario. Los
procedimientos que operan partidas de compra pertenecen a
`procedures_compras.sql`, aunque también consulten o actualicen artículos.

`procedures.sql` se conserva como el volcado general histórico. No debe
ejecutarse junto con los scripts modulares porque contiene procedimientos con
los mismos nombres.

Los datos artificiales utilizados por las pruebas están separados en
`src/test/resources/db/fixtures` y nunca deben instalarse en una base de datos
real.
