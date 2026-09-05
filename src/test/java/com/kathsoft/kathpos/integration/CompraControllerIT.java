package com.kathsoft.kathpos.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.kathsoft.kathpos.app.model.compra.ArticuloPorCompra;
import com.kathsoft.kathpos.app.model.compra.Compra;
import com.kathsoft.kathpos.app.model.compra.CompraById;
import com.kathsoft.kathpos.app.model.compra.CompraConDetalle;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

@Testcontainers
class CompraControllerIT {

    private static final String DATABASE_NAME = "kath_erp";
    private static final int ID_SUCURSAL_COMPRA = 1;
    private static final int ID_SUCURSAL_CONTROL = 2;
    private static final int ID_ARTICULO_EXISTENTE = 100;
    private static final int ID_ARTICULO_NUEVO = 101;

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
    void prepararExistenciasIniciales() throws SQLException {
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
    void registraCompraCompletaConDetallesExistenciasYTotalCorrectos() throws SQLException {
        CompraController controller = new CompraController();
        ArticuloPorCompra articuloExistente = crearDetalle(ID_ARTICULO_EXISTENTE, 2, 200.00);
        ArticuloPorCompra articuloNuevo = crearDetalle(ID_ARTICULO_NUEVO, 3, 150.00);
        CompraConDetalle compra = new CompraConDetalle(
                crearCompra("FAC-CTRL-001", 350.00, 56.00),
                List.of(articuloExistente, articuloNuevo));

        SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL_COMPRA, compra);

        assertAll(
                () -> assertTrue(respuesta.id() > 0, respuesta.message()),
                () -> assertEquals("Compra registrada correctamente", respuesta.message()),
                () -> assertEquals(respuesta.id(), articuloExistente.getIdCompra()),
                () -> assertEquals(respuesta.id(), articuloNuevo.getIdCompra()));

        CompraById compraConsultada = controller.getCompraById(respuesta.id());
        assertNotNull(compraConsultada);
        assertAll(
                () -> assertEquals("FAC-CTRL-001", compraConsultada.getFolioFactura()),
                () -> assertEquals(350.00, compraConsultada.getSubtotal(), 0.001),
                () -> assertEquals(56.00, compraConsultada.getIva(), 0.001),
                () -> assertEquals(406.00, compraConsultada.getImporteTotal(), 0.001));

        assertAll(
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE id_compra = ? AND id_sucursal = ?",
                        respuesta.id(), ID_SUCURSAL_COMPRA)),
                () -> assertEquals(2, consultarEntero(
                        "SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = ?",
                        respuesta.id())),
                () -> assertEquals(350.00, consultarDouble(
                        "SELECT SUM(subtotal) FROM articulo_x_compra WHERE id_compra = ?",
                        respuesta.id()), 0.001),
                () -> assertEquals(2, consultarEntero(
                        "SELECT cantidad FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                        respuesta.id(), ID_ARTICULO_EXISTENTE)),
                () -> assertEquals(3, consultarEntero(
                        "SELECT cantidad FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                        respuesta.id(), ID_ARTICULO_NUEVO)));

        assertAll(
                () -> assertEquals(7, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(3, consultarExistencia(ID_ARTICULO_NUEVO, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_CONTROL)),
                () -> assertEquals(25, consultarExistencia(ID_ARTICULO_NUEVO, ID_SUCURSAL_CONTROL)));
    }

    @Test
    void revierteTodaLaCompraCuandoFallaUnoDeLosDetalles() throws SQLException {
        CompraController controller = new CompraController();
        CompraConDetalle compra = new CompraConDetalle(
                crearCompra("FAC-CTRL-002", 300.00, 48.00),
                List.of(
                        crearDetalle(ID_ARTICULO_EXISTENTE, 2, 200.00),
                        crearDetalle(999_999, 1, 100.00)));

        SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL_COMPRA, compra);

        assertAll(
                () -> assertEquals(500, respuesta.id()),
                () -> assertTrue(respuesta.message().contains("no existe o está inactivo")),
                () -> assertEquals(0, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE folio_factura = ?", "FAC-CTRL-002")),
                () -> assertEquals(0, consultarEntero("SELECT COUNT(*) FROM articulo_x_compra")),
                () -> assertEquals(5, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_CONTROL)));
    }

    @Test
    void editaCabeceraPartidaExistenteYAgregaUnaPartidaNueva() throws SQLException {
        CompraController controller = new CompraController();
        int idCompra = registrarCompraInicial(controller, "FAC-EDIT-001");
        int idDetalleExistente = consultarIdDetalle(idCompra, ID_ARTICULO_EXISTENTE);

        ArticuloPorCompra detalleActualizado = new ArticuloPorCompra.ArticuloPorCompraBuilder()
                .id(idDetalleExistente)
                .idCompra(idCompra)
                .idArticulo(ID_ARTICULO_EXISTENTE)
                .cantidad(1)
                .subtotal(100.00)
                .build();
        ArticuloPorCompra detalleNuevo = crearDetalle(ID_ARTICULO_NUEVO, 3, 150.00);
        CompraConDetalle compraEditada = new CompraConDetalle(
                crearCompraEditada(idCompra, "FAC-EDIT-002", 250.00, 40.00),
                List.of(detalleActualizado, detalleNuevo));

        SpResponseModel respuesta = controller.updateCompra(ID_SUCURSAL_COMPRA, compraEditada);

        assertAll(
                () -> assertEquals(idCompra, respuesta.id(), respuesta.message()),
                () -> assertEquals("Compra actualizada correctamente", respuesta.message()),
                () -> assertEquals(idCompra, detalleNuevo.getIdCompra()));

        CompraById compraConsultada = controller.getCompraById(idCompra);
        assertNotNull(compraConsultada);
        assertAll(
                () -> assertEquals("FAC-EDIT-002", compraConsultada.getFolioFactura()),
                () -> assertTrue(compraConsultada.isTipoCompra()),
                () -> assertEquals(250.00, compraConsultada.getSubtotal(), 0.001),
                () -> assertEquals(40.00, compraConsultada.getIva(), 0.001),
                () -> assertEquals(290.00, compraConsultada.getImporteTotal(), 0.001));

        assertAll(
                () -> assertEquals(2, consultarEntero(
                        "SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = ?", idCompra)),
                () -> assertEquals(1, consultarEntero(
                        "SELECT cantidad FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                        idCompra, ID_ARTICULO_EXISTENTE)),
                () -> assertEquals(3, consultarEntero(
                        "SELECT cantidad FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                        idCompra, ID_ARTICULO_NUEVO)),
                () -> assertEquals(250.00, consultarDouble(
                        "SELECT SUM(subtotal) FROM articulo_x_compra WHERE id_compra = ?", idCompra), 0.001));

        assertAll(
                () -> assertEquals(6, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(3, consultarExistencia(ID_ARTICULO_NUEVO, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_CONTROL)),
                () -> assertEquals(25, consultarExistencia(ID_ARTICULO_NUEVO, ID_SUCURSAL_CONTROL)));
    }

    @Test
    void revierteLaEdicionCompletaCuandoFallaUnaPartidaNueva() throws SQLException {
        CompraController controller = new CompraController();
        int idCompra = registrarCompraInicial(controller, "FAC-EDIT-003");
        int idDetalleExistente = consultarIdDetalle(idCompra, ID_ARTICULO_EXISTENTE);

        ArticuloPorCompra detalleActualizado = new ArticuloPorCompra.ArticuloPorCompraBuilder()
                .id(idDetalleExistente)
                .idCompra(idCompra)
                .idArticulo(ID_ARTICULO_EXISTENTE)
                .cantidad(4)
                .subtotal(400.00)
                .build();
        CompraConDetalle compraEditada = new CompraConDetalle(
                crearCompraEditada(idCompra, "FAC-EDIT-004", 500.00, 80.00),
                List.of(detalleActualizado, crearDetalle(999_999, 1, 100.00)));

        SpResponseModel respuesta = controller.updateCompra(ID_SUCURSAL_COMPRA, compraEditada);

        assertAll(
                () -> assertEquals(500, respuesta.id()),
                () -> assertTrue(respuesta.message().contains("no existe o está inactivo")));

        CompraById compraConsultada = controller.getCompraById(idCompra);
        assertNotNull(compraConsultada);
        assertAll(
                () -> assertEquals("FAC-EDIT-003", compraConsultada.getFolioFactura()),
                () -> assertEquals(200.00, compraConsultada.getSubtotal(), 0.001),
                () -> assertEquals(32.00, compraConsultada.getIva(), 0.001),
                () -> assertEquals(2, consultarEntero(
                        "SELECT cantidad FROM articulo_x_compra WHERE id = ?", idDetalleExistente)),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = ?", idCompra)),
                () -> assertEquals(7, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_COMPRA)));
    }

    @Test
    void rechazaEditarUnaCompraDesdeOtraSucursal() throws SQLException {
        CompraController controller = new CompraController();
        int idCompra = registrarCompraInicial(controller, "FAC-EDIT-005");
        int idDetalleExistente = consultarIdDetalle(idCompra, ID_ARTICULO_EXISTENTE);

        ArticuloPorCompra detalleExistente = new ArticuloPorCompra.ArticuloPorCompraBuilder()
                .id(idDetalleExistente)
                .idCompra(idCompra)
                .idArticulo(ID_ARTICULO_EXISTENTE)
                .cantidad(2)
                .subtotal(200.00)
                .build();
        CompraConDetalle compraEditada = new CompraConDetalle(
                crearCompraEditada(idCompra, "FAC-EDIT-006", 200.00, 32.00),
                List.of(detalleExistente));

        SpResponseModel respuesta = controller.updateCompra(ID_SUCURSAL_CONTROL, compraEditada);

        assertAll(
                () -> assertEquals(500, respuesta.id()),
                () -> assertTrue(respuesta.message().contains("no pertenece a la sucursal actual")),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE id_compra = ? AND folio_factura = ? AND id_sucursal = ?",
                        idCompra, "FAC-EDIT-005", ID_SUCURSAL_COMPRA)),
                () -> assertEquals(7, consultarExistencia(ID_ARTICULO_EXISTENTE, ID_SUCURSAL_COMPRA)));
    }

    private int registrarCompraInicial(CompraController controller, String folio) {
        SpResponseModel respuesta = controller.insertCompra(
                ID_SUCURSAL_COMPRA,
                new CompraConDetalle(
                        crearCompra(folio, 200.00, 32.00),
                        List.of(crearDetalle(ID_ARTICULO_EXISTENTE, 2, 200.00))));
        assertTrue(respuesta.id() > 0, respuesta.message());
        return respuesta.id();
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
                .build();
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
                .build();
    }

    private ArticuloPorCompra crearDetalle(int idArticulo, int cantidad, double subtotal) {
        return new ArticuloPorCompra.ArticuloPorCompraBuilder()
                .idArticulo(idArticulo)
                .cantidad(cantidad)
                .subtotal(subtotal)
                .build();
    }

    private Connection nuevaConexion() throws SQLException {
        return DriverManager.getConnection(MYSQL.getJdbcUrl(), MYSQL.getUsername(), MYSQL.getPassword());
    }

    private int consultarExistencia(int idArticulo, int idSucursal) throws SQLException {
        return consultarEntero(
                "SELECT existencia FROM existencia_x_sucursal WHERE id_articulo = ? AND id_sucursal = ?",
                idArticulo, idSucursal);
    }

    private int consultarIdDetalle(int idCompra, int idArticulo) throws SQLException {
        return consultarEntero(
                "SELECT id FROM articulo_x_compra WHERE id_compra = ? AND id_articulo = ?",
                idCompra, idArticulo);
    }

    private int consultarEntero(String sql, Object... parametros) throws SQLException {
        try (Connection connection = nuevaConexion();
                PreparedStatement statement = prepararConsulta(connection, sql, parametros);
                ResultSet resultSet = statement.executeQuery()) {
            assertTrue(resultSet.next(), "La consulta debe devolver una fila: " + sql);
            return resultSet.getInt(1);
        }
    }

    private double consultarDouble(String sql, Object... parametros) throws SQLException {
        try (Connection connection = nuevaConexion();
                PreparedStatement statement = prepararConsulta(connection, sql, parametros);
                ResultSet resultSet = statement.executeQuery()) {
            assertTrue(resultSet.next(), "La consulta debe devolver una fila: " + sql);
            return resultSet.getDouble(1);
        }
    }

    private PreparedStatement prepararConsulta(Connection connection, String sql, Object... parametros)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        for (int index = 0; index < parametros.length; index++) {
            statement.setObject(index + 1, parametros[index]);
        }
        return statement;
    }
}
