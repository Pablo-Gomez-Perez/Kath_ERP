package com.kathsoft.kathpos.app.model.retiros;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Mapeo común del ResultSet de listRetirosDeEfectivo y
 * getRetiroDeEfectivoById, incluidos registros inhabilitados.
 */
public record RetiroDeEfectivoDetalle(
        int idRetiro,
        long idSucursal,
        int idEmpleado,
        String empleado,
        String folio,
        LocalDate fecha,
        String descripcion,
        BigDecimal importe,
        boolean activo) {
}
