package com.kathsoft.kathpos.integration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.controller.CompraController;
import com.kathsoft.kathpos.app.controller.PagoProveedorController;
import com.kathsoft.kathpos.app.model.compra.ArticuloPorCompra;
import com.kathsoft.kathpos.app.model.compra.Compra;
import com.kathsoft.kathpos.app.model.compra.CompraConDetalle;
import com.kathsoft.kathpos.app.model.compra.PagoProveedor;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

/**
 * Verifica el ciclo operativo de una compra sin depender del módulo contable.
 */
class CompraLifecycleIT extends CompraDatabaseIT {

    private static final int ID_SUCURSAL_COMPRA = 1;
    private static final int ID_SUCURSAL_CONTROL = 2;
    private static final int ID_ARTICULO = 100;
    private static final int ID_FORMA_PAGO_EFECTIVO = 1;

    @BeforeAll
    static void configurarConexionDelControlador() {
        System.setProperty("db.host", host());
        System.setProperty("db.port", String.valueOf(port()));
        System.setProperty("db.name", DATABASE_NAME);
        System.setProperty("db.user", username());
        System.setProperty("db.password", password());
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
            statement.executeUpdate("DELETE FROM pago_proveedor");
            statement.executeUpdate("DELETE FROM articulo_x_compra");
            statement.executeUpdate("DELETE FROM existencia_x_sucursal");
            statement.executeUpdate("DELETE FROM compras");
            statement.executeUpdate("""
                    INSERT INTO existencia_x_sucursal (id_articulo, id_sucursal, existencia)
                    VALUES (100, 1, 5), (100, 2, 40)
                    """);
        }
    }

    @Test
    void registraCompraDeContadoConPagoYExistenciaEnUnaMismaOperacion() throws SQLException {
        CompraController controller = new CompraController();
        Compra compra = crearCompra("FAC-LIFE-001", false, 200.00, 32.00);
        CompraConDetalle compraConDetalle = new CompraConDetalle(
                compra,
                List.of(crearDetalle(ID_ARTICULO, 2, 200.00)),
                new PagoProveedor(0, ID_FORMA_PAGO_EFECTIVO, new BigDecimal("232.00")));

        SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL_COMPRA, compraConDetalle);

        assertAll(
                () -> assertTrue(respuesta.id() > 0, respuesta.message()),
                () -> assertEquals("Compra registrada correctamente", respuesta.message()),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM pago_proveedor WHERE id_compra = ? AND id_forma_pago = ?",
                        respuesta.id(), ID_FORMA_PAGO_EFECTIVO)),
                () -> assertEquals(232.00, consultarDouble(
                        "SELECT importe FROM pago_proveedor WHERE id_compra = ?", respuesta.id()), 0.001),
                () -> assertEquals(7, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_CONTROL)));
    }

    @Test
    void revierteCompraCanceladaMantieneDetalleYNoAfectaOtraSucursal() throws SQLException {
        CompraController controller = new CompraController();
        int idCompra = registrarCompraCredito(controller, "FAC-LIFE-002");

        SpResponseModel respuesta = controller.deleteCompra(ID_SUCURSAL_COMPRA, idCompra);

        assertAll(
                () -> assertEquals(idCompra, respuesta.id(), respuesta.message()),
                () -> assertEquals("Compra cancelada correctamente", respuesta.message()),
                () -> assertEquals(0, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE id_compra = ? AND activo = TRUE", idCompra)),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM articulo_x_compra WHERE id_compra = ?", idCompra)),
                () -> assertEquals(5, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_CONTROL)));
    }

    @Test
    void rechazaCancelarCompraDesdeOtraSucursalSinCambiarla() throws SQLException {
        CompraController controller = new CompraController();
        int idCompra = registrarCompraCredito(controller, "FAC-LIFE-003");

        SpResponseModel respuesta = controller.deleteCompra(ID_SUCURSAL_CONTROL, idCompra);

        assertAll(
                () -> assertEquals(500, respuesta.id()),
                () -> assertTrue(respuesta.message().contains("no pertenece a la sucursal actual")),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE id_compra = ? AND activo = TRUE", idCompra)),
                () -> assertEquals(7, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_COMPRA)),
                () -> assertEquals(40, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_CONTROL)));
    }

    @Test
    void rechazaCancelarCompraConPagosYConservaLaOperacion() throws SQLException {
        CompraController controller = new CompraController();
        int idCompra = registrarCompraCredito(controller, "FAC-LIFE-004");

        PagoProveedorController pagos = new PagoProveedorController();
        SpResponseModel pago = registrarPago(pagos, idCompra, new BigDecimal("100.00"));
        SpResponseModel cancelacion = controller.deleteCompra(ID_SUCURSAL_COMPRA, idCompra);

        assertAll(
                () -> assertTrue(pago.id() > 0, pago.message()),
                () -> assertEquals(500, cancelacion.id()),
                () -> assertTrue(cancelacion.message().contains("pagos asociados")),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE id_compra = ? AND activo = TRUE", idCompra)),
                () -> assertEquals(1, consultarEntero(
                        "SELECT COUNT(*) FROM pago_proveedor WHERE id_compra = ?", idCompra)),
                () -> assertEquals(7, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_COMPRA)));
    }

    @Test
    void revierteCompraDeContadoSiElPagoNoEsValido() throws SQLException {
        CompraController controller = new CompraController();
        Compra compra = crearCompra("FAC-LIFE-005", false, 200.00, 32.00);
        CompraConDetalle compraConDetalle = new CompraConDetalle(
                compra,
                List.of(crearDetalle(ID_ARTICULO, 2, 200.00)),
                new PagoProveedor(0, 999, new BigDecimal("232.00")));

        SpResponseModel respuesta = controller.insertCompra(ID_SUCURSAL_COMPRA, compraConDetalle);

        assertAll(
                () -> assertEquals(500, respuesta.id()),
                () -> assertTrue(respuesta.message().contains("no existe o está inactiva")),
                () -> assertEquals(0, consultarEntero(
                        "SELECT COUNT(*) FROM compras WHERE folio_factura = ?", "FAC-LIFE-005")),
                () -> assertEquals(0, consultarEntero("SELECT COUNT(*) FROM articulo_x_compra")),
                () -> assertEquals(5, consultarExistencia(ID_ARTICULO, ID_SUCURSAL_COMPRA)));
    }

    private int registrarCompraCredito(CompraController controller, String folio) {
        SpResponseModel respuesta = controller.insertCompra(
                ID_SUCURSAL_COMPRA,
                new CompraConDetalle(
                        crearCompra(folio, true, 200.00, 32.00),
                        List.of(crearDetalle(ID_ARTICULO, 2, 200.00))));
        assertTrue(respuesta.id() > 0, respuesta.message());
        return respuesta.id();
    }

    private SpResponseModel registrarPago(PagoProveedorController controller, int idCompra, BigDecimal importe)
            throws SQLException {
        try (Connection connection = nuevaConexion()) {
            return controller.registrarPago(connection,
                    new PagoProveedor(idCompra, ID_FORMA_PAGO_EFECTIVO, importe));
        }
    }

    private Compra crearCompra(String folio, boolean credito, double subtotal, double iva) {
        Date hoy = Date.valueOf(LocalDate.now(ZoneOffset.UTC));
        return new Compra.CompraBuilder()
                .idEmpleado(1)
                .idProveedor(1)
                .folioFactura(folio)
                .fechaFactura(hoy)
                .fechaCompra(hoy)
                .tipoCompra(credito)
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

    private Connection nuevaConexion() throws SQLException {
        return DriverManager.getConnection(jdbcUrl(), username(), password());
    }

    private int consultarExistencia(int idArticulo, int idSucursal) throws SQLException {
        return consultarEntero(
                "SELECT existencia FROM existencia_x_sucursal WHERE id_articulo = ? AND id_sucursal = ?",
                idArticulo, idSucursal);
    }

    private int consultarEntero(String sql, Object... parametros) throws SQLException {
        try (Connection connection = nuevaConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                statement.setObject(i + 1, parametros[i]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next(), "La consulta debe devolver una fila: " + sql);
                return resultSet.getInt(1);
            }
        }
    }

    private double consultarDouble(String sql, Object... parametros) throws SQLException {
        try (Connection connection = nuevaConexion(); PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < parametros.length; i++) {
                statement.setObject(i + 1, parametros[i]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next(), "La consulta debe devolver una fila: " + sql);
                return resultSet.getDouble(1);
            }
        }
    }
}
