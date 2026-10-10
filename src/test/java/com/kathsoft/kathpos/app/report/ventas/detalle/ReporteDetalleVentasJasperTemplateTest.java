package com.kathsoft.kathpos.app.report.ventas.detalle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

class ReporteDetalleVentasJasperTemplateTest {

    @Test
    void plantillaCompilaEnCartaYPermiteMultiplesPaginas() throws Exception {
        List<List<String>> ventas = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            ventas.add(List.of(
                    String.valueOf(i),
                    "100.00",
                    "16.00",
                    "116.00",
                    "Vigente"));
        }

        List<ReporteDetalleVentasSeccion> secciones = List.of(
                new ReporteDetalleVentasSeccion(
                        "Detalle de ventas del dia",
                        List.of("Folio", "Sub total", "IVA", "Total", "Estado"),
                        ventas),
                new ReporteDetalleVentasSeccion(
                        "Detalle por forma de pago",
                        List.of("Forma de pago", "Total"),
                        List.of(List.of("Efectivo", "11600.00"))),
                new ReporteDetalleVentasSeccion(
                        "Detalle por empleados",
                        List.of("Empleado", "Total"),
                        List.of(List.of("Empleado Uno", "11600.00"))),
                new ReporteDetalleVentasSeccion(
                        "Retiros de efectivo",
                        List.of("Folio", "Importe"),
                        List.of(List.of("RET-001", "500.00"))));

        List<ReporteDetalleVentasPdfRow> filas =
                ReporteDetalleVentasExportService.construirFilasPdf(secciones);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put(
                "TITULO",
                "Reporte de ventas a detalle del dia 10/10/2026");

        try (InputStream input = ReporteDetalleVentasJasperTemplateTest.class
                .getResourceAsStream("/reports/reporte_detalle_ventas.jrxml")) {

            assertTrue(input != null, "La plantilla reporte_detalle_ventas.jrxml debe existir");

            JasperDesign design = JRXmlLoader.load(input);

            assertEquals(612, design.getPageWidth(), "El reporte debe usar ancho carta");
            assertEquals(792, design.getPageHeight(), "El reporte debe usar alto carta");

            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(
                    report,
                    parametros,
                    new JRBeanCollectionDataSource(filas, false));

            assertEquals(612, print.getPageWidth());
            assertEquals(792, print.getPageHeight());
            assertTrue(
                    print.getPages().size() > 1,
                    "El detalle debe continuar en páginas adicionales cuando sea necesario");

            byte[] pdf = JasperExportManager.exportReportToPdf(print);
            assertTrue(pdf.length > 1000, "El PDF generado no debe estar vacío");
            assertTrue(
                    pdf[0] == '%' && pdf[1] == 'P' && pdf[2] == 'D' && pdf[3] == 'F',
                    "El contenido debe tener cabecera PDF válida");
        }
    }
}
