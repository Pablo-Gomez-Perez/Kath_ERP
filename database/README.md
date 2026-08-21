# Base de datos de Kath ERP

Estos scripts describen la estructura de base de datos compatible con MySQL
8.0.46. No contienen información de clientes ni datos operativos.

El orden de ejecución es:

1. `schema.sql`
2. `procedures.sql`

Los scripts deben ejecutarse seleccionando previamente la base `kath_erp`:

```shell
mysql -u <usuario> -p kath_erp < database/schema.sql
mysql -u <usuario> -p kath_erp < database/procedures.sql
```

Los datos artificiales utilizados por las pruebas están separados en
`src/test/resources/db/fixtures` y nunca deben instalarse en una base de datos
real.
