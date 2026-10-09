package com.kathsoft.kathpos.app.model.reporte;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Resume un importe cobrado por una dimensión del reporte diario,
 * por ejemplo forma de pago o empleado.
 */
public record CobroResumenDia(
        String nombre,
        BigDecimal total) implements Serializable {

    private static final long serialVersionUID = 1L;

    public CobroResumenDia {
        nombre = Objects.requireNonNull(nombre, "El nombre es obligatorio").trim();
        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        total = total == null
                ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : total.setScale(2, RoundingMode.HALF_UP);
    }
}
