package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.lang.reflect.Proxy;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Map;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.retiros.EstadoCorteDiario;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoFiltro;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoRegistro;

/**
 * Validaciones del retiro independientes de MySQL.
 */
class RetiroDeEfectivoControllerTest {

    @Test
    void registroCompletoEsAceptado() {
        assertNull(RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-00000001", "Reposición de caja", "125.50")));
    }

    @Test
    void laCapturaDistingueRetiroParcialYCorteFinal() {
        RetiroDeEfectivoRegistro retiro = new RetiroDeEfectivoRegistro(
                4, 9, "R-001", "Retiro parcial", new BigDecimal("100.00"), false);
        RetiroDeEfectivoRegistro corte = new RetiroDeEfectivoRegistro(
                4, 9, "R-002", "Corte Z", new BigDecimal("125.50"), true);
        assertNull(RetiroDeEfectivoController.validarRegistro(retiro));
        assertNull(RetiroDeEfectivoController.validarRegistro(corte));
        org.junit.jupiter.api.Assertions.assertFalse(retiro.esRetiroFinal());
        org.junit.jupiter.api.Assertions.assertTrue(corte.esRetiroFinal());
    }

    @Test
    void proyectaElEstadoYElTipoSinCambiarElOrdenDelListado() {
        var fecha = LocalDate.of(2026, 9, 29);
        var corte = new com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle(
                15, 4, 9, "Empleado", "R-002", fecha, "Cierre",
                new BigDecimal("125.50"), true, false);
        var parcial = new com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle(
                16, 4, 9, "Empleado", "R-003", fecha, "Parcial",
                new BigDecimal("50.00"), false, true);
        Object[] filaCorte = RetiroDeEfectivoController.proyectarFila(corte);
        Object[] filaParcial = RetiroDeEfectivoController.proyectarFila(parcial);
        assertEquals(8, filaCorte.length);
        assertEquals("Corte final", filaCorte[6]);
        assertEquals("Inactivo", filaCorte[7]);
        assertEquals("Retiro parcial", filaParcial[6]);
        assertEquals("Activo", filaParcial[7]);
    }

    @Test
    void sucursalYEmpleadoSonObligatorios() {
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(0, 9, "R-00000001", "Reposición de caja", "100.00")).id());
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 0, "R-00000001", "Reposición de caja", "100.00")).id());
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(null).id());
    }

    @Test
    void folioPorSucursalEsObligatorioYDeDiezCaracteresMaximo() {
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "  ", "Retiro de caja", "100.00")).id());
        assertNull(RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "1234567890", "Retiro de caja", "100.00")));
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "12345678901", "Retiro de caja", "100.00")).id());
    }

    @Test
    void descripcionObligatoriaAdmiteHasta255Caracteres() {
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", " ", "100.00")).id());
        assertNull(RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "X".repeat(255), "100.00")));
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "X".repeat(256), "100.00")).id());
    }

    @Test
    void importeDebeSerPositivoConMaximoDosDecimales() {
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "Retiro", "0.00")).id());
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "Retiro", "-1.00")).id());
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "Retiro", "1.001")).id());
        assertNull(RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "Retiro", "0.01")));
    }

    @Test
    void validaLimiteDePrecisionDeImporte() {
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "Retiro", "12345678901234567.01")).id());
        assertNull(RetiroDeEfectivoController.validarRegistro(
                registro(4, 9, "R-1", "Retiro", "1234567890123456.99")));
    }

    @Test
    void mapeaCorrectamenteCorteFinalYEstadoDesdeLasColumnasDelSp() throws SQLException {
        Map<String, Object> valores = Map.of(
                "id_retiro", 42,
                "id_sucursal", 9L,
                "id_empleado", 5,
                "empleado", "Responsable",
                "folio", "Z-0001",
                "fecha", Date.valueOf(LocalDate.of(2026, 9, 29)),
                "descripcion", "Corte del día",
                "importe", new BigDecimal("250.50"),
                "es_retiro_final", true,
                "activo", false);
        ResultSet simulado = (ResultSet) Proxy.newProxyInstance(
                ResultSet.class.getClassLoader(), new Class<?>[]{ResultSet.class},
                (proxy, method, args) -> {
                    String nombre = method.getName();
                    if (nombre.equals("getInt") || nombre.equals("getLong")
                            || nombre.equals("getString") || nombre.equals("getDate")
                            || nombre.equals("getBigDecimal") || nombre.equals("getBoolean")) {
                        return valores.get((String) args[0]);
                    }
                    throw new UnsupportedOperationException("Acceso inesperado: " + nombre);
                });

        var detalle = RetiroDeEfectivoController.mapearDetalle(simulado);
        assertEquals(42, detalle.idRetiro());
        assertEquals(9L, detalle.idSucursal());
        assertEquals("Z-0001", detalle.folio());
        assertEquals(new BigDecimal("250.50"), detalle.importe());
        org.junit.jupiter.api.Assertions.assertTrue(detalle.esRetiroFinal());
        org.junit.jupiter.api.Assertions.assertFalse(detalle.activo());
    }

    @Test
    void estadoDiarioDistingueAbiertoCerradoYCorreccionPendiente() {
        LocalDate fecha = LocalDate.of(2026, 9, 30);
        var parcialActivo = new com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle(
                1, 4, 9, "Empleado", "R-1", fecha, "Parcial",
                new BigDecimal("25.00"), false, true);
        var corteInactivo = new com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle(
                2, 4, 9, "Empleado", "Z-1", fecha, "Corte corregible",
                new BigDecimal("100.00"), true, false);
        var corteActivo = new com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle(
                3, 4, 9, "Empleado", "Z-2", fecha, "Corte vigente",
                new BigDecimal("125.00"), true, true);

        assertEquals(EstadoCorteDiario.ABIERTO,
                RetiroDeEfectivoController.determinarEstadoCorteDiario(List.of()));
        assertEquals(EstadoCorteDiario.ABIERTO,
                RetiroDeEfectivoController.determinarEstadoCorteDiario(
                        List.of(parcialActivo)));
        assertEquals(EstadoCorteDiario.CORTE_FINAL_PENDIENTE_DE_REEMPLAZO,
                RetiroDeEfectivoController.determinarEstadoCorteDiario(
                        List.of(parcialActivo, corteInactivo)));
        assertEquals(EstadoCorteDiario.CORTE_FINAL_ACTIVO,
                RetiroDeEfectivoController.determinarEstadoCorteDiario(
                        List.of(parcialActivo, corteInactivo, corteActivo)));
    }

    @Test
    void filtrosPorDefectoIncluyenTodosLosRegistros() {
        RetiroDeEfectivoFiltro filtro = RetiroDeEfectivoFiltro.sinFiltros();
        assertNull(filtro.idEmpleado());
        assertNull(filtro.fechaInicial());
        assertNull(filtro.fechaFinal());
        assertEquals(RetiroDeEfectivoFiltro.Orden.FECHA_RECIENTE, filtro.orden());
    }

    @Test
    void ordenesSqlCoincidenConLosProcedimientos() {
        assertEquals(1, RetiroDeEfectivoFiltro.Orden.FECHA_RECIENTE.codigoSql());
        assertEquals(2, RetiroDeEfectivoFiltro.Orden.FECHA_ANTIGUA.codigoSql());
        assertEquals(3, RetiroDeEfectivoFiltro.Orden.EMPLEADO.codigoSql());
        assertEquals("Empleado", RetiroDeEfectivoFiltro.Orden.EMPLEADO.toString());
    }

    @Test
    void filtrosRechazanEmpleadoInvalidoEIntervaloInvertido() {
        assertThrows(IllegalArgumentException.class,
                () -> new RetiroDeEfectivoFiltro(0, null, null, null));
        assertThrows(IllegalArgumentException.class,
                () -> new RetiroDeEfectivoFiltro(null,
                        LocalDate.of(2026, 9, 29),
                        LocalDate.of(2026, 9, 28), null));
    }

    private static RetiroDeEfectivoRegistro registro(
            long sucursal, int empleado, String folio, String descripcion, String importe) {
        return new RetiroDeEfectivoRegistro(
                sucursal, empleado, folio, descripcion, new BigDecimal(importe), false);
    }
}
