package com.kathsoft.kathpos.app.report.ventas;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kathsoft.kathpos.app.model.reporte.VentaTotalPorFecha;
import com.kathsoft.kathpos.app.report.JasperReportService;
import com.kathsoft.kathpos.app.report.ventas.dto.ReporteVentaTotalPdfRow;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Genera el reporte PDF de ventas totales usando la última consulta presentada
 * al usuario.
 */
public class ReporteVentasTotalesPdfService {

    static final String TEMPLATE = "/reports/reporte_ventas_totales.jrxml";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final JasperReportService jasperReportService;

    public ReporteVentasTotalesPdfService() {
        this(new JasperReportService());
    }

    ReporteVentasTotalesPdfService(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    public Path generarReporte(
            List<VentaTotalPorFecha> ventas,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Path archivoDestino) throws Exception {

        validar(ventas, fechaInicio, fechaFin, archivoDestino);

        List<ReporteVentaTotalPdfRow> filas = new ArrayList<>(ventas.size());
        BigDecimal ventasTotales = BigDecimal.ZERO;
        BigDecimal ivaCobrado = BigDecimal.ZERO;

        for (VentaTotalPorFecha venta : ventas) {
            if (venta == null) {
                throw new IllegalArgumentException("El reporte contiene una fila inválida");
            }

            filas.add(new ReporteVentaTotalPdfRow(
                    venta.fecha(),
                    venta.numeroVentas(),
                    venta.subtotal(),
                    venta.iva(),
                    venta.total()));

            ventasTotales = ventasTotales.add(venta.total());
            ivaCobrado = ivaCobrado.add(venta.iva());
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("FECHA_INICIO", fechaInicio.format(FORMATO_FECHA));
        parametros.put("FECHA_FIN", fechaFin.format(FORMATO_FECHA));
        parametros.put("VENTAS_TOTALES", importe(ventasTotales));
        parametros.put("IVA_COBRADO", importe(ivaCobrado));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(filas, false);

        return this.jasperReportService.exportPdf(
                TEMPLATE,
                parametros,
                dataSource,
                archivoDestino,
                0);
    }

    static void validar(
            List<VentaTotalPorFecha> ventas,
            LocalDate fechaInicio,
            LocalDate fechaFin,
            Path archivoDestino) {

        if (ventas == null || ventas.isEmpty()) {
            throw new IllegalArgumentException("No existen datos a exportar");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("El intervalo del reporte es obligatorio");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
        }
        if (archivoDestino == null) {
            throw new IllegalArgumentException("La ruta de salida es obligatoria");
        }
    }

    private static String importe(BigDecimal valor) {
        BigDecimal normalizado = valor == null ? BigDecimal.ZERO : valor;
        return normalizado.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
