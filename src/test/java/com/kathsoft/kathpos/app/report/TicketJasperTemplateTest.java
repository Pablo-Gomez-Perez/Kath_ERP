package com.kathsoft.kathpos.app.report;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.report.ticket.dto.TicketArticuloDTO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

class TicketJasperTemplateTest {

    @Test
    void ticketTemplateCompilesAndExportsPdf() throws Exception {
        TicketArticuloDTO articulo = new TicketArticuloDTO();
        articulo.setCodigoArticulo("ABC-001");
        articulo.setUnidad("PZA");
        articulo.setDescripcion("Artículo de prueba");
        articulo.setCantidad(2);
        articulo.setPrecioUnitario(new BigDecimal("58.00"));
        articulo.setImporte(new BigDecimal("116.00"));

        Map<String, Object> params = new HashMap<>();
        params.put("EMISOR_NOMBRE_COMERCIAL", "Kath ERP Demo");
        params.put("EMISOR_RAZON_SOCIAL", "Empresa de prueba");
        params.put("EMISOR_RFC", "AAA010101AAA");
        params.put("EMISOR_REGIMEN_FISCAL", "626 - Régimen Simplificado de Confianza");
        params.put("SUCURSAL", "Sucursal Centro");
        params.put("DOMICILIO", "Dirección de prueba C.P. 29000");
        params.put("LUGAR_EXPEDICION", "Tuxtla Gutiérrez, Chiapas");
        params.put("CONTACTO", "Tel. 0000000000");
        params.put("FOLIO", "1");
        params.put("FECHA", "2026-09-14");
        params.put("EMPLEADO", "Empleado de prueba");
        params.put("CLIENTE", "Público en general");
        params.put("CLIENTE_RFC", "");
        params.put("TIPO_VENTA", "Contado");
        params.put("STATUS_VENTA", "Vigente");
        params.put("SUBTOTAL", "$100.00");
        params.put("IVA", "$16.00");
        params.put("TOTAL", "$116.00");
        params.put("PAGOS", "Efectivo: $116.00");
        params.put("TOTAL_RECIBIDO", "$120.00");
        params.put("CAMBIO", "$4.00");
        params.put("REGISTRO_SISTEMA", "");

        try (InputStream input = TicketJasperTemplateTest.class.getResourceAsStream("/reports/ticket_venta.jrxml")) {
            assertTrue(input != null, "La plantilla ticket_venta.jrxml debe existir");
            JasperDesign design = JRXmlLoader.load(input);
            design.setPageHeight(650);
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(report, params,
                    new JRBeanCollectionDataSource(List.of(articulo), false));
            byte[] pdf = JasperExportManager.exportReportToPdf(print);

            assertTrue(pdf.length > 1000, "El PDF generado no debe estar vacío");
            assertTrue(pdf[0] == '%' && pdf[1] == 'P' && pdf[2] == 'D' && pdf[3] == 'F',
                    "El contenido generado debe ser un PDF válido");
        }
    }
}
