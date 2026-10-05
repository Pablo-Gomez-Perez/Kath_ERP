package com.kathsoft.kathpos.app.model.reporte;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa el total diario de ventas vigentes devuelto por el reporte de ventas
 * dentro de un intervalo de fechas.
 *
 * @param fecha fecha a la que corresponde la agrupación
 * @param numeroVentas cantidad de ventas vigentes registradas durante el día
 * @param subtotal suma de subtotales del día
 * @param iva suma de IVA del día
 * @param total suma de importes totales del día
 */
public record VentaTotalPorFecha(
        LocalDate fecha,
        long numeroVentas,
        BigDecimal subtotal,
        BigDecimal iva,
        BigDecimal total) implements Serializable {

    private static final long serialVersionUID = 1L;

    public VentaTotalPorFecha {
        Objects.requireNonNull(fecha, "La fecha del reporte es obligatoria");
        if (numeroVentas < 0) {
            throw new IllegalArgumentException("El número de ventas no puede ser negativo");
        }

        subtotal = normalizarImporte(subtotal);
        iva = normalizarImporte(iva);
        total = normalizarImporte(total);
    }

    private static BigDecimal normalizarImporte(BigDecimal importe) {
        return importe == null
                ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : importe.setScale(2, RoundingMode.HALF_UP);
    }
}
