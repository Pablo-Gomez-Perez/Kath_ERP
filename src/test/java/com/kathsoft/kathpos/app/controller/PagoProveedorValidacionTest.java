package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.compra.Compra;
import com.kathsoft.kathpos.app.model.compra.CompraConDetalle;
import com.kathsoft.kathpos.app.model.compra.PagoProveedor;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

/**
 * Verifica las reglas de pago previas a abrir una transacción de compra.
 */
class PagoProveedorValidacionTest {

    private final CompraController controller = new CompraController();

    @Test
    void creditoNoExigePagoInicial() {
        assertNull(controller.validarPagoInicial(compra(true, null)));
    }

    @Test
    void contadoRechazaCompraSinPago() {
        SpResponseModel resultado = controller.validarPagoInicial(compra(false, null));
        assertEquals(500, resultado.id());
        assertTrue(resultado.message().contains("pago"));
    }

    @Test
    void contadoAceptaPagoUnicoPorElTotal() {
        PagoProveedor pago = new PagoProveedor(0, 1, new BigDecimal("116.00"));
        assertNull(controller.validarPagoInicial(compra(false, pago)));
    }

    @Test
    void contadoRechazaPagoParcial() {
        PagoProveedor pago = new PagoProveedor(0, 1, new BigDecimal("115.99"));
        assertEquals(500, controller.validarPagoInicial(compra(false, pago)).id());
    }

    @Test
    void contadoRechazaPagoMayorAlTotal() {
        PagoProveedor pago = new PagoProveedor(0, 1, new BigDecimal("116.01"));
        assertEquals(500, controller.validarPagoInicial(compra(false, pago)).id());
    }

    @Test
    void contadoRechazaFormaPagoInvalida() {
        PagoProveedor pago = new PagoProveedor(0, 0, new BigDecimal("116.00"));
        assertEquals(500, controller.validarPagoInicial(compra(false, pago)).id());
    }

    @Test
    void contadoRechazaIdCompraPrematuro() {
        PagoProveedor pago = new PagoProveedor(99, 1, new BigDecimal("116.00"));
        assertEquals(500, controller.validarPagoInicial(compra(false, pago)).id());
    }

    @Test
    void creditoRechazaPagoDeContadoAccidental() {
        PagoProveedor pago = new PagoProveedor(0, 1, new BigDecimal("116.00"));
        assertEquals(500, controller.validarPagoInicial(compra(true, pago)).id());
    }

    private CompraConDetalle compra(boolean credito, PagoProveedor pago) {
        Compra cabecera = new Compra.CompraBuilder()
                .tipoCompra(credito)
                .subtotal(100.00)
                .iva(16.00)
                .build();
        return new CompraConDetalle(cabecera, List.of(), pago);
    }
}
