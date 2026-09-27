package com.kathsoft.kathpos.app.model.compra;

import java.math.BigDecimal;

/**
 * Datos de un pago asociado a una compra.
 *
 * <p>Antes de persistir la compra, {@code idCompra} puede ser cero. El
 * identificador definitivo se asigna dentro de la transacción de compra.</p>
 *
 * @param idCompra identificador de la compra; cero mientras no se haya creado
 * @param idFormaPago identificador de la forma de pago activa
 * @param importe importe del pago en moneda nacional con precisión de centavos
 */
public record PagoProveedor(int idCompra, int idFormaPago, BigDecimal importe) {
}
