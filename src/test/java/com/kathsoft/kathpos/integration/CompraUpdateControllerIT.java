package com.kathsoft.kathpos.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.MountableFile;

import com.kathsoft.kathpos.app.controller.CompraController;
import com.kathsoft.kathpos.app.model.compra.ArticuloCompraListado;
import com.kathsoft.kathpos.app.model.compra.ArticuloPorCompra;
import com.kathsoft.kathpos.app.model.compra.Compra;
import com.kathsoft.kathpos.app.model.compra.CompraConDetalle;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

@Testcontainers
class CompraUpdateControllerIT {

    private static final String DATABASE_NAME = "kath_erp";
    private static final int ID_SUCURSAL_COMPRA = 1;
    private static final int ID_SUCURSAL_CONTROL = 2;
    private static final int ID_ARTICULO_EXISTENTE = 100;
    private static final int ID_ARTICULO_A_ELIMINAR = 101;

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

    @BeforeAll
    static void configurarConexionDelControlador() {
        System.setProperty("db.host", MYSQL.getHost());
        System.setProperty("db.port", String.valueOf(MYSQL.getMappedPort(3306)));
        System.setProperty("db.name", DATABASE_NAME);
        System.setProperty("db.user", MYSQL.getUsername());
        System.setProperty("db.password", MYSQL.getPassword());
        System.setProperty("db.params", "serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
    }

    @AfterAll
    static void limpiarConfiguracionDelControlador() {
        System.clearProperty("db.host");
        System.clearProperty("db.port");
        System.clearProperty("db.name");
        System.clearProperty("db.user");
        System.clearProperty("db.password");
        System.clearProperty("db.params");
    }

    @BeforeEach
    void prepararDatos() throws SQLException {
        try (Connection connection = nuevaConexion(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM articulo_x_compra");
            statement.executeUpdate("DELETE FROM existencia_x_sucursal");
            statement.executeUpdate("DELETE FROM compras");
            statement.executeUpdate("""
                    INSERT INTO existencia_x_sucursal (id_articulo, id_sucursal, existencia)
                    VALUES (100, 1, 5), (100, 2, 40), (101, 2, 25)
                    """);
        }
    }

    @Test
    void actualizaPartidaConservadaEliminaPartidaAusenteYAjustaExistencias() throws SQLException {
        CompraController controller = new CompraController();

        ArticuloPorCompra detalleExistente = crearDetalle(ID_ARTICULO_EXISTENTE, 2, 200.00);
        ArticuloPorCompra detalleAEliminar = crearDetalle(ID_ARTICULO_A_ELIMINAR, 3, 150.00);
        CompraConDetalle compraInicial = new CompraConDetalle(
                crearCompra("FAC-UPD-001", 350.00, 56.00),
                List.of(detalleExistente, detalleAEliminar));

        SpResponseModel registro = controller.insertCompra(ID_SUCURSAL_COMPRA, compraInicial);
        assertTrue(registro.id() > 0, registro.message());

        int idCompra = registro.id();
        ArticuloCompraListado detalleGuardado = controller.listArticulosCompraById(idCompra).stream()
                .filter(detalle -> detalle.getIdArticulo() == ID_ARTICULO_EXISTENTE)
                .findFirst()
                .orElseThrow();

        ArticuloPorCompra detalleActualizado = new ArticuloPorCompra.ArticuloPorCompraBuilder()
                .id(detalleGuardado.getId())
                .idCompra(idCompra)
                .idArticulo(ID_ARTICULO_EXISTENTE)
                .cantidad(1)
                .subtotal(100.00)
                .build();

        CompraConDetalle compraEditada = new CompraConDetalle(
                crearCompraEditada(idCompra, "FAC-UPD-002", 100.00, 16.00),
                List.of(detalleActualizado));

        SpResponseModel respuesta = controller.updateCompra(ID_SUCURSAL_COMPRA, compraEditada);

        assertAll(
                () -> assertEquals(idCompra, respuesta.id(), respuesta.message()),
                () -> assertEquals("Compra actualizada correctamente", respuesta.message()),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = ?", idCompra)),
                () -> assertEquals(1, consultarEntero(
                        "SELECT cantidad FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                        idCompra, ID_ARTICULO_EXISTENTE)),
                () -> assertEquals(0, consultarEntero(
                        "SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                        idCompra, ID_ARTICULO_A_ELIMINAR)),
                () -> assertEquals(6, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(0, consultarExistencia(ID_ARTICULO_A_ELIMINAR, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_CONTROL)),
                () -> assertEquals(25, consultarExistencia(ID_ARTICULO_A_ELIMINAR, ID_SUCURSAL_CONTROL)));
    }

    private Compra crearCompra(String folio, double subtotal, double iva) {
        return new Compra.CompraBuilder()
                .idEmpleado(1)
                .idProveedor(1)
                .folioFactura(folio)
                .fechaFactura(Date.valueOf("2026-08-20"))
                .fechaCompra(Date.valueOf("2026-08-21"))
                .tipoCompra(false)
                .subtotal(subtotal)
                .iva(iva)
                .activo(true)
                .build();
    }

    private Compra crearCompraEditada(int idCompra, String folio, double subtotal, double iva) {
        return new Compra.CompraBuilder()
                .idCompra(idCompra)
                .idEmpleado(1)
                .idProveedor(1)
                .folioFactura(folio)
                .fechaFactura(Date.valueOf("2026-08-22"))
                .fechaCompra(Date.valueOf("2026-08-23"))
                .tipoCompra(true)
                .subtotal(subtotal)
                .iva(iva)
                .activo(true)
                .build();
    }

    private ArticuloPorCompra crearDetalle(int idArticulo, int cantidad, double subtotal) {
        return new ArticuloPorCompra.ArticuloPorCompraBuilder()
                .idArticulo(idArticulo)
                .cantidad(cantidad)
                .subtotal(subtotal)
                .build();
    }

    private int consultarExistencia(int idArticulo, int idSucursal) throws SQLException {
        return consultarEntero(
                "SELECT COALESCE(MAX(existencia), 0) FROM existencia_x_sucursal WHERE id_articulo = ? AND id_sucursal = ?",
                idArticulo, idSucursal);
    }

    private int consultarEntero(String sql, Object... parametros) throws SQLException {
        try (Connection connection = nuevaConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                statement.setObject(i + 1, parametros[i]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                return resultSet.getInt(1);
            }
        }
    }

    private Connection nuevaConexion() throws SQLException {
        return DriverManager.getConnection(MYSQL.getJdbcUrl(), MYSQL.getUsername(), MYSQL.getPassword());
    }
}
