package com.kathsoft.kathpos.app.model.gastos;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Detalle proyectado por listGastos y getGastoByID.
 *
 * <p>Para registros anteriores a la migración de gastos, idFormaPago y
 * formaPago pueden ser nulos.</p>
 */
public record GastoDetalle(
        int idGasto,
        long idSucursal,
        int idCategoria,
        String categoria,
        int idEmpleado,
        String empleado,
        Integer idFormaPago,
        String formaPago,
        LocalDate fechaOperacion,
        String descripcion,
        BigDecimal importe,
        BigDecimal iva,
        BigDecimal total,
        boolean activo) {
}
