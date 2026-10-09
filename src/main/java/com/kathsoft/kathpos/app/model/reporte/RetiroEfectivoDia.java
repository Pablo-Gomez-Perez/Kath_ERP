package com.kathsoft.kathpos.app.model.reporte;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * Proyección mínima de un retiro activo utilizado por el reporte diario.
 */
public record RetiroEfectivoDia(
        String folio,
        BigDecimal importe) implements Serializable {

    private static final long serialVersionUID = 1L;

    public RetiroEfectivoDia {
        folio = Objects.requireNonNull(folio, "El folio es obligatorio").trim();
        if (folio.isEmpty()) {
            throw new IllegalArgumentException("El folio es obligatorio");
        }

        importe = importe == null
                ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : importe.setScale(2, RoundingMode.HALF_UP);
    }
}
