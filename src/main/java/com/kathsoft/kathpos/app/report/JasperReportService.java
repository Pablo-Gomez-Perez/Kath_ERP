package com.kathsoft.kathpos.app.report;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/** Servicio común para compilar plantillas JRXML y exportarlas a PDF. */
public class JasperReportService {

    public Path exportPdf(String templateResource, Map<String, Object> parameters, JRDataSource dataSource,
            Path output, int pageHeight) throws IOException, JRException {
        if (templateResource == null || templateResource.isBlank()) {
            throw new IllegalArgumentException("La plantilla Jasper es obligatoria");
        }
        if (output == null) {
            throw new IllegalArgumentException("La ruta de salida es obligatoria");
        }

        try (InputStream template = JasperReportService.class.getResourceAsStream(templateResource)) {
            if (template == null) {
                throw new IOException("No se encontró la plantilla Jasper: " + templateResource);
            }

            JasperDesign design = JRXmlLoader.load(template);
            if (pageHeight > 0) {
                design.setPageHeight(pageHeight);
            }
            JasperReport report = JasperCompileManager.compileReport(design);
            JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

            Path parent = output.toAbsolutePath().normalize().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            JasperExportManager.exportReportToPdfFile(print, output.toAbsolutePath().normalize().toString());
            return output.toAbsolutePath().normalize();
        }
    }
}
