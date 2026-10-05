package com.kathsoft.kathpos.app.report.ventas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.kathsoft.kathpos.app.model.reporte.VentaTotalPorFecha;

class ReporteVentasTotalesPdfServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void generaPdfConDatosValidos() throws Exception {
        ReporteVentasTotalesPdfService service = new ReporteVentasTotalesPdfService();

        List<VentaTotalPorFecha> ventas = List.of(
                new VentaTotalPorFecha(
                        LocalDate.of(2026, 10, 1),
                        3,
                        new BigDecimal("300.00"),
                        new BigDecimal("48.00"),
                        new BigDecimal("348.00")),
                new VentaTotalPorFecha(
                        LocalDate.of(2026, 10, 2),
                        2,
                        new BigDecimal("200.00"),
                        new BigDecimal("32.00"),
                        new BigDecimal("232.00")));

        Path destino = tempDir.resolve("ventas.pdf");

        Path generado = service.generarReporte(
                ventas,
                LocalDate.of(2026, 10, 1),
                LocalDate.of(2026, 10, 2),
                destino);

        assertEquals(destino.toAbsolutePath().normalize(), generado);
        assertTrue(Files.exists(generado));
        assertTrue(Files.size(generado) > 1000);
    }

    @Test
    void rechazaReporteSinDatos() {
        assertThrows(
                IllegalArgumentException.class,
                () -> ReporteVentasTotalesPdfService.validar(
                        List.of(),
                        LocalDate.of(2026, 10, 1),
                        LocalDate.of(2026, 10, 2),
                        tempDir.resolve("ventas.pdf")));
    }

    @Test
    void rechazaIntervaloInvertido() {
        List<VentaTotalPorFecha> ventas = List.of(
                new VentaTotalPorFecha(
                        LocalDate.of(2026, 10, 2),
                        1,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO,
                        BigDecimal.ZERO));

        assertThrows(
                IllegalArgumentException.class,
                () -> ReporteVentasTotalesPdfService.validar(
                        ventas,
                        LocalDate.of(2026, 10, 3),
                        LocalDate.of(2026, 10, 2),
                        tempDir.resolve("ventas.pdf")));
    }
}
