package com.kathsoft.kathpos.app.model.retiros;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Datos necesarios para registrar un retiro. La fecha y el ID se generan
 * exclusivamente en el servidor y no pueden modificarse desde Java.
 *
 * @param idSucursal sucursal autenticada
 * @param idEmpleado empleado activo de la sucursal
 * @param folio folio único global de hasta diez caracteres
 * @param descripcion motivo del retiro
 * @param importe cantidad retirada
 */
public record RetiroDeEfectivoRegistro(
        long idSucursal,
        int idEmpleado,
        String folio,
        String descripcion,
        BigDecimal importe) {

    public void validar() {
        if (idSucursal <= 0 || idEmpleado <= 0) {
            throw new IllegalArgumentException("Sucursal y empleado son obligatorios");
        }
        if (folio == null || folio.isBlank()
                || folio.trim().codePointCount(0, folio.trim().length()) > 10) {
            throw new IllegalArgumentException("El folio es obligatorio y admite hasta 10 caracteres");
        }
        if (descripcion == null || descripcion.isBlank()
                || descripcion.trim().codePointCount(0, descripcion.trim().length()) > 255) {
            throw new IllegalArgumentException(
                    "La descripción es obligatoria y admite hasta 255 caracteres");
        }
        if (importe == null || importe.signum() <= 0) {
            throw new IllegalArgumentException("El importe debe ser mayor que cero");
        }
        try {
            importe.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException("El importe no puede tener más de dos decimales", ex);
        }
        if (importe.precision() - importe.scale() > 16) {
            throw new IllegalArgumentException("El importe supera la capacidad DECIMAL(18,2)");
        }
    }
}
