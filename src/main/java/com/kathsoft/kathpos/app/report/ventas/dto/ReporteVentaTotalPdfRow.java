package com.kathsoft.kathpos.app.report.ventas.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Fila preparada para la plantilla Jasper del reporte de ventas totales.
 */
public final class ReporteVentaTotalPdfRow {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final String fecha;
    private final Long numeroVentas;
    private final String subtotal;
    private final String iva;
    private final String total;

    public ReporteVentaTotalPdfRow(
            LocalDate fecha,
            long numeroVentas,
            BigDecimal subtotal,
            BigDecimal iva,
            BigDecimal total) {

        this.fecha = Objects.requireNonNull(fecha, "La fecha es obligatoria").format(FORMATO_FECHA);
        this.numeroVentas = numeroVentas;
        this.subtotal = importe(subtotal);
        this.iva = importe(iva);
        this.total = importe(total);
    }

    public String getFecha() {
        return fecha;
    }

    public Long getNumeroVentas() {
        return numeroVentas;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public String getIva() {
        return iva;
    }

    public String getTotal() {
        return total;
    }

    private static String importe(BigDecimal valor) {
        BigDecimal normalizado = valor == null
                ? BigDecimal.ZERO
                : valor;
        return normalizado.setScale(2, RoundingMode.HALF_UP).toPlainString();
    }
}
