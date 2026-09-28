package com.kathsoft.kathpos.app.model.gastos;

import java.math.BigDecimal;

/**
 * Comando de alta o actualización de gastos.
 *
 * <p>La sucursal proviene de la sesión actual, nunca de un campo editable.
 * La fecha de operación se asigna exclusivamente en MySQL al crear el gasto.</p>
 *
 * @param idGasto cero para alta; ID válido para actualizar
 * @param idSucursal sucursal desde la que se accedió al ERP
 * @param idCategoria categoría del gasto
 * @param idEmpleado empleado responsable
 * @param idFormaPago forma en la que se cubrió el gasto
 * @param descripcion descripción de la operación
 * @param importe importe anterior al IVA
 * @param iva IVA correspondiente, cero para operaciones sin IVA
 */
public record GastoRegistro(
        int idGasto,
        long idSucursal,
        int idCategoria,
        int idEmpleado,
        int idFormaPago,
        String descripcion,
        BigDecimal importe,
        BigDecimal iva) {

    public void validar(boolean actualizacion) {
        if (actualizacion && idGasto <= 0) {
            throw new IllegalArgumentException("El gasto a actualizar debe tener un ID válido");
        }
        if (!actualizacion && idGasto != 0) {
            throw new IllegalArgumentException("Una alta de gasto aún no debe tener ID");
        }
        if (idSucursal <= 0 || idCategoria <= 0 || idEmpleado <= 0 || idFormaPago <= 0) {
            throw new IllegalArgumentException(
                    "Sucursal, categoría, empleado y forma de pago son obligatorios");
        }
        if (descripcion == null || descripcion.isBlank()
                || descripcion.trim().codePointCount(0, descripcion.trim().length()) > 255) {
            throw new IllegalArgumentException(
                    "La descripción es obligatoria y no puede superar 255 caracteres");
        }
        if (importe == null || importe.signum() <= 0 || !centavosValidos(importe)) {
            throw new IllegalArgumentException(
                    "El importe debe ser mayor que cero y contener como máximo dos decimales");
        }
        if (iva == null || iva.signum() < 0 || !centavosValidos(iva)) {
            throw new IllegalArgumentException(
                    "El IVA no puede ser negativo ni contener más de dos decimales");
        }
    }

    private static boolean centavosValidos(BigDecimal valor) {
        try {
            valor.setScale(2, java.math.RoundingMode.UNNECESSARY);
            return true;
        } catch (ArithmeticException ex) {
            return false;
        }
    }
}
