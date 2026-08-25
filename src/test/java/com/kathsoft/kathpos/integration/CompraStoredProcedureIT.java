package com.kathsoft.kathpos.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.MountableFile;

@Testcontainers
class CompraStoredProcedureIT {

    private static final String DATABASE_NAME = "kath_erp";
    private static final int ID_EMPLEADO = 1;
    private static final int ID_PROVEEDOR = 1;
    private static final long ID_SUCURSAL = 1L;
    private static final int ID_ARTICULO = 100;

    @Container
    private static final MySQLContainer MYSQL = new MySQLContainer("mysql:8.0.46")
            .withDatabaseName(DATABASE_NAME)
            .withUsername("kath_test")
            .withPassword("kath_test")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/init/schema.sql"),
                    "/docker-entrypoint-initdb.d/01-schema.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/init/procedures/procedures_articulos.sql"),
                    "/docker-entrypoint-initdb.d/02-procedures-articulos.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/init/procedures/procedures_compras.sql"),
                    "/docker-entrypoint-initdb.d/03-procedures-compras.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/fixtures/compra_minima.sql"),
                    "/docker-entrypoint-initdb.d/04-compra-minima.sql");

    @BeforeEach
    void limpiarOperacionesDeCompra() throws SQLException {
        try (Connection connection = nuevaConexion(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM articulo_x_compra");
            statement.executeUpdate("DELETE FROM existencia_x_sucursal");
            statement.executeUpdate("DELETE FROM compras");
        }
    }

    @Test
    void cargaLosProcedimientosModularesDeArticulosYCompras() throws SQLException {
        Set<String> esperados = Set.of(
                "deleteArticuloCompra",
                "getArticuloById",
                "getIdUltimaCompra",
                "insertCompra",
                "insertArticuloCompra",
                "insertArticulo",
                "sumarExistenciaSucursalCompra",
                "listCompras",
                "listArticulosCompraById",
                "getCompraById",
                "updateArticulo",
                "updateArticuloCompra",
                "updateCompra");

        Set<String> encontrados = new HashSet<>();
        String sql = """
                SELECT ROUTINE_NAME
                FROM information_schema.ROUTINES
                WHERE ROUTINE_SCHEMA = ?
                  AND ROUTINE_TYPE = 'PROCEDURE'
                """;

        try (Connection connection = nuevaConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, DATABASE_NAME);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    encontrados.add(resultSet.getString("ROUTINE_NAME"));
                }
            }
        }

        assertTrue(encontrados.containsAll(esperados),
                () -> "Faltan procedimientos: " + esperados.stream()
                        .filter(nombre -> !encontrados.contains(nombre))
                        .sorted()
                        .toList());
    }

    @Test
    void insertCompraPersisteUnaCompraValida() throws SQLException {
        int idCompra;

        try (Connection connection = nuevaConexion()) {
            ProcedureResponse response = insertarCompra(connection, "FAC-IT-001");
            idCompra = response.id();

            assertTrue(idCompra > 0);
            assertNotEquals(500, idCompra);
            assertEquals("Compra registrada correctamente", response.message());
        }

        String sql = """
                SELECT id_empleado, id_proveedor, id_sucursal, folio_factura,
                       subtotal, iva, activo
                FROM compras
                WHERE id_compra = ?
                """;

        try (Connection connection = nuevaConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCompra);

            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                assertEquals(ID_EMPLEADO, resultSet.getInt("id_empleado"));
                assertEquals(ID_PROVEEDOR, resultSet.getInt("id_proveedor"));
                assertEquals(ID_SUCURSAL, resultSet.getLong("id_sucursal"));
                assertEquals("FAC-IT-001", resultSet.getString("folio_factura"));
                assertEquals(100.00, resultSet.getDouble("subtotal"), 0.001);
                assertEquals(16.00, resultSet.getDouble("iva"), 0.001);
                assertTrue(resultSet.getBoolean("activo"));
            }
        }
    }

    @Test
    void insertCompraRechazaUnFolioDuplicadoSinDuplicarLaFila() throws SQLException {
        try (Connection connection = nuevaConexion()) {
            ProcedureResponse primera = insertarCompra(connection, "FAC-IT-002");
            ProcedureResponse duplicada = insertarCompra(connection, "FAC-IT-002");

            assertTrue(primera.id() > 0);
            assertEquals(500, duplicada.id());
            assertTrue(duplicada.message().contains("El folio de factura ya está registrado"));
        }

        assertEquals(1, contar("SELECT COUNT(*) FROM compras WHERE folio_factura = 'FAC-IT-002'"));
    }

    @Test
    void detalleYExistenciaParticipanEnLaTransaccionDeCompra() throws SQLException {
        int idCompra;

        try (Connection connection = nuevaConexion()) {
            connection.setAutoCommit(false);
            idCompra = insertarCompra(connection, "FAC-IT-003").id();

            ProcedureResponse detalle = insertarArticuloCompra(connection, idCompra, ID_ARTICULO, 3, 300.00);
            ProcedureResponse existencia = sumarExistencia(connection, idCompra, ID_ARTICULO, 3);

            assertTrue(detalle.id() > 0);
            assertEquals(200, existencia.id());
            connection.commit();
        }

        assertEquals(1, contar("SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = " + idCompra));
        assertEquals(3, contar("""
                SELECT existencia
                FROM existencia_x_sucursal
                WHERE id_articulo = 100 AND id_sucursal = 1
                """));
    }

    @Test
    void rollbackEliminaLaCompraCuandoFallaUnDetalle() throws SQLException {
        try (Connection connection = nuevaConexion()) {
            connection.setAutoCommit(false);
            int idCompra = insertarCompra(connection, "FAC-IT-004").id();

            ProcedureResponse detalleInvalido = insertarArticuloCompra(connection, idCompra, 999_999, 1, 100.00);

            assertEquals(500, detalleInvalido.id());
            assertTrue(detalleInvalido.message().contains("no existe o está inactivo"));
            connection.rollback();
        }

        assertEquals(0, contar("SELECT COUNT(*) FROM compras WHERE folio_factura = 'FAC-IT-004'"));
        assertEquals(0, contar("SELECT COUNT(*) FROM articulo_x_compra"));
        assertEquals(0, contar("SELECT COUNT(*) FROM existencia_x_sucursal"));
    }

    private Connection nuevaConexion() throws SQLException {
        return DriverManager.getConnection(MYSQL.getJdbcUrl(), MYSQL.getUsername(), MYSQL.getPassword());
    }

    private ProcedureResponse insertarCompra(Connection connection, String folio) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("CALL insertCompra(?,?,?,?,?,?,?,?,?)")) {
            statement.setInt(1, ID_EMPLEADO);
            statement.setInt(2, ID_PROVEEDOR);
            statement.setLong(3, ID_SUCURSAL);
            statement.setString(4, folio);
            statement.setDate(5, Date.valueOf("2026-08-01"));
            statement.setDate(6, Date.valueOf("2026-08-02"));
            statement.setBoolean(7, false);
            statement.setDouble(8, 100.00);
            statement.setDouble(9, 16.00);
            return ejecutarConRespuesta(statement);
        }
    }

    private ProcedureResponse insertarArticuloCompra(
            Connection connection,
            int idCompra,
            int idArticulo,
            int cantidad,
            double subtotal) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("CALL insertArticuloCompra(?,?,?,?)")) {
            statement.setInt(1, idCompra);
            statement.setInt(2, idArticulo);
            statement.setInt(3, cantidad);
            statement.setDouble(4, subtotal);
            return ejecutarConRespuesta(statement);
        }
    }

    private ProcedureResponse sumarExistencia(
            Connection connection,
            int idCompra,
            int idArticulo,
            int cantidad) throws SQLException {
        try (CallableStatement statement = connection.prepareCall("CALL sumarExistenciaSucursalCompra(?,?,?)")) {
            statement.setInt(1, idCompra);
            statement.setInt(2, idArticulo);
            statement.setInt(3, cantidad);
            return ejecutarConRespuesta(statement);
        }
    }

    private ProcedureResponse ejecutarConRespuesta(CallableStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            assertTrue(resultSet.next(), "El procedimiento debe devolver id y message");
            return new ProcedureResponse(resultSet.getInt("id"), resultSet.getString("message"));
        }
    }

    private int contar(String sql) throws SQLException {
        try (Connection connection = nuevaConexion();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)) {
            assertTrue(resultSet.next());
            return resultSet.getInt(1);
        }
    }

    private record ProcedureResponse(int id, String message) {
    }
}
