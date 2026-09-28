package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

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
    void sucursalYEmpleadoSonObligatorios() {
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(0, 9, "R-00000001", "Reposición de caja", "100.00")).id());
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(
                registro(4, 0, "R-00000001", "Reposición de caja", "100.00")).id());
        assertEquals(500, RetiroDeEfectivoController.validarRegistro(null).id());
    }

    @Test
    void folioGlobalEsObligatorioYDeDiezCaracteresMaximo() {
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
                sucursal, empleado, folio, descripcion, new BigDecimal(importe));
    }
}
