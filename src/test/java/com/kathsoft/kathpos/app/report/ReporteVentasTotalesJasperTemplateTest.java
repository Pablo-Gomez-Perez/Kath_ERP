package com.kathsoft.kathpos.app.report;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.report.ventas.dto.ReporteVentaTotalPdfRow;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

class ReporteVentasTotalesJasperTemplateTest {

    @Test
    void plantillaCompilaEnCartaYPaginaCuandoHayMuchosRegistros() throws Exception {
        List<ReporteVentaTotalPdfRow> filas = new ArrayList<>();

        LocalDate fechaBase = LocalDate.of(2026, 1, 1);
        for (int i = 0; i < 80; i++) {
            filas.add(new ReporteVentaTotalPdfRow(
                    fechaBase.plusDays(i),
                    i + 1L,
                    new BigDecimal("100.00"),
                    new BigDecimal("16.00"),
                    new BigDecimal("116.00")));
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("FECHA_INICIO", "01/01/2026");
        parametros.put("FECHA_FIN", "21/03/2026");
        parametros.put("VENTAS_TOTALES", "9280.00");
        parametros.put("IVA_COBRADO", "1280.00");

        try (InputStream input = ReporteVentasTotalesJasperTemplateTest.class
                .getResourceAsStream("/reports/reporte_ventas_totales.jrxml")) {

            assertTrue(input != null, "La plantilla reporte_ventas_totales.jrxml debe existir");

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
            assertTrue(print.getPages().size() > 1,
                    "El reporte debe generar múltiples páginas cuando los registros exceden una hoja");

            byte[] pdf = JasperExportManager.exportReportToPdf(print);
            assertTrue(pdf.length > 1000, "El PDF generado no debe estar vacío");
            assertTrue(pdf[0] == '%' && pdf[1] == 'P' && pdf[2] == 'D' && pdf[3] == 'F',
                    "El contenido generado debe tener cabecera PDF válida");
        }
    }
}
