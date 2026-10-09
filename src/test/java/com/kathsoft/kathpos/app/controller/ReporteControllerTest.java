package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.reporte.CobroResumenDia;
import com.kathsoft.kathpos.app.model.reporte.RetiroEfectivoDia;
import com.kathsoft.kathpos.app.model.reporte.VentaTotalPorFecha;

class ReporteControllerTest {

    @Test
    void parametrosValidosAceptanIntervaloDeUnSoloDia() {
        LocalDate fecha = LocalDate.of(2026, 10, 4);

        ReporteController.validarParametros(1L, fecha, fecha);
    }

    @Test
    void rechazaSucursalInvalida() {
        assertThrows(IllegalArgumentException.class,
                () -> ReporteController.validarParametros(
                        0L,
                        LocalDate.of(2026, 10, 1),
                        LocalDate.of(2026, 10, 4)));
    }

    @Test
    void rechazaFechasAusentes() {
        assertThrows(IllegalArgumentException.class,
                () -> ReporteController.validarParametros(
                        1L,
                        null,
                        LocalDate.of(2026, 10, 4)));

        assertThrows(IllegalArgumentException.class,
                () -> ReporteController.validarParametros(
                        1L,
                        LocalDate.of(2026, 10, 1),
                        null));
    }

    @Test
    void rechazaIntervaloInvertido() {
        assertThrows(IllegalArgumentException.class,
                () -> ReporteController.validarParametros(
                        1L,
                        LocalDate.of(2026, 10, 5),
                        LocalDate.of(2026, 10, 4)));
    }

    @Test
    void detalleDiarioValidaSucursalYFecha() {
        ReporteController.validarSucursalYFecha(
                1L,
                LocalDate.of(2026, 10, 7));

        assertThrows(IllegalArgumentException.class,
                () -> ReporteController.validarSucursalYFecha(
                        0L,
                        LocalDate.of(2026, 10, 7)));

        assertThrows(IllegalArgumentException.class,
                () -> ReporteController.validarSucursalYFecha(1L, null));
    }

    @Test
    void modelosDeDetalleNormalizanImportes() {
        CobroResumenDia cobro = new CobroResumenDia(
                "Efectivo",
                new BigDecimal("125.555"));
        RetiroEfectivoDia retiro = new RetiroEfectivoDia(
                "RET-001",
                new BigDecimal("80"));

        assertEquals(new BigDecimal("125.56"), cobro.total());
        assertEquals(new BigDecimal("80.00"), retiro.importe());
    }

    @Test
    void modeloNormalizaImportesADosDecimales() {
        VentaTotalPorFecha fila = new VentaTotalPorFecha(
                LocalDate.of(2026, 10, 4),
                3,
                new BigDecimal("100.125"),
                new BigDecimal("16"),
                new BigDecimal("116.125"));

        assertEquals(new BigDecimal("100.13"), fila.subtotal());
        assertEquals(new BigDecimal("16.00"), fila.iva());
        assertEquals(new BigDecimal("116.13"), fila.total());
    }
}
