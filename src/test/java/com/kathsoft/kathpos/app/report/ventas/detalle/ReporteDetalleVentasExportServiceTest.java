package com.kathsoft.kathpos.app.report.ventas.detalle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ReporteDetalleVentasExportServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void txtConservaTituloSeccionesYContenido() {
        LocalDate fecha = LocalDate.of(2026, 10, 10);
        List<ReporteDetalleVentasSeccion> secciones = seccionesEjemplo();

        String txt = ReporteDetalleVentasExportService.generarContenidoTxt(fecha, secciones);

        assertTrue(txt.startsWith("Reporte de ventas a detalle del dia 10/10/2026"));
        assertTrue(txt.contains("Detalle de ventas del dia"));
        assertTrue(txt.contains("Detalle por forma de pago"));
        assertTrue(txt.contains("Detalle por empleados"));
        assertTrue(txt.contains("Retiros de efectivo"));
        assertTrue(txt.contains("V-001"));
        assertTrue(txt.contains("Efectivo"));
        assertTrue(txt.contains("Empleado Uno"));
        assertTrue(txt.contains("RET-001"));
    }

    @Test
    void csvUsaUnSoloEncabezadoGlobalYSinEtiquetasDeSeccion() {
        LocalDate fecha = LocalDate.of(2026, 10, 10);
        String csv = ReporteDetalleVentasExportService.generarContenidoCsv(
                fecha,
                seccionesEjemplo());

        assertTrue(csv.startsWith("Reporte de ventas a detalle del dia 10/10/2026\r\n\r\n"));
        assertTrue(csv.contains("Folio,Sub total,IVA,Total,Estado"));
        assertTrue(csv.contains("Forma de pago,Total"));
        assertTrue(csv.contains("Empleado,Total"));
        assertTrue(csv.contains("Folio,Importe"));
        assertFalse(csv.contains("Detalle de ventas del dia"));
        assertFalse(csv.contains("Detalle por forma de pago"));
        assertFalse(csv.contains("Detalle por empleados"));
        assertFalse(csv.contains("Retiros de efectivo"));
    }

    @Test
    void generaLosTresArchivos() throws Exception {
        ReporteDetalleVentasExportService service = new ReporteDetalleVentasExportService();
        LocalDate fecha = LocalDate.of(2026, 10, 10);
        List<ReporteDetalleVentasSeccion> secciones = seccionesEjemplo();

        Path pdf = service.generarPdf(fecha, secciones, tempDir.resolve("reporte.pdf"));
        Path txt = service.generarTxt(fecha, secciones, tempDir.resolve("reporte.txt"));
        Path csv = service.generarCsv(fecha, secciones, tempDir.resolve("reporte.csv"));

        assertTrue(Files.exists(pdf));
        assertTrue(Files.size(pdf) > 1000);
        assertTrue(Files.exists(txt));
        assertTrue(Files.size(txt) > 0);
        assertTrue(Files.exists(csv));
        assertTrue(Files.size(csv) > 0);
    }

    @Test
    void rechazaReporteSinDatos() {
        List<ReporteDetalleVentasSeccion> vacias = List.of(
                new ReporteDetalleVentasSeccion(
                        "Detalle de ventas del dia",
                        List.of("Folio", "Sub total", "IVA", "Total", "Estado"),
                        List.of()),
                new ReporteDetalleVentasSeccion(
                        "Retiros de efectivo",
                        List.of("Folio", "Importe"),
                        List.of()));

        assertThrows(
                IllegalArgumentException.class,
                () -> ReporteDetalleVentasExportService.validar(
                        LocalDate.of(2026, 10, 10),
                        vacias,
                        tempDir.resolve("reporte.pdf")));
    }

    @Test
    void filasPdfRespetanSeccionesDeCincoYDosColumnas() {
        List<ReporteDetalleVentasPdfRow> filas =
                ReporteDetalleVentasExportService.construirFilasPdf(seccionesEjemplo());

        assertEquals(16, filas.size());
        assertEquals(ReporteDetalleVentasPdfRow.SECCION, filas.get(0).tipoFila());
        assertEquals(ReporteDetalleVentasPdfRow.CABECERA_5, filas.get(1).tipoFila());
        assertEquals(ReporteDetalleVentasPdfRow.DATO_5, filas.get(2).tipoFila());
        assertEquals(ReporteDetalleVentasPdfRow.SEPARADOR, filas.get(3).tipoFila());
        assertEquals(ReporteDetalleVentasPdfRow.CABECERA_2, filas.get(5).tipoFila());
    }

    private static List<ReporteDetalleVentasSeccion> seccionesEjemplo() {
        return List.of(
                new ReporteDetalleVentasSeccion(
                        "Detalle de ventas del dia",
                        List.of("Folio", "Sub total", "IVA", "Total", "Estado"),
                        List.of(List.of("V-001", "100.00", "16.00", "116.00", "Vigente"))),
                new ReporteDetalleVentasSeccion(
                        "Detalle por forma de pago",
                        List.of("Forma de pago", "Total"),
                        List.of(List.of("Efectivo", "116.00"))),
                new ReporteDetalleVentasSeccion(
                        "Detalle por empleados",
                        List.of("Empleado", "Total"),
                        List.of(List.of("Empleado Uno", "116.00"))),
                new ReporteDetalleVentasSeccion(
                        "Retiros de efectivo",
                        List.of("Folio", "Importe"),
                        List.of(List.of("RET-001", "50.00"))));
    }
}
