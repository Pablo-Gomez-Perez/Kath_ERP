package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.FormasDePago;

class FormasDePagoControllerTest {

    @Test
    void altaValidaNoRequiereId() {
        FormasDePago forma = nuevaForma(0, "Efectivo");

        assertDoesNotThrow(
                () -> FormasDePagoController.validarFormaDePago(forma, false));
    }

    @Test
    void actualizacionRequiereIdValido() {
        FormasDePago forma = nuevaForma(0, "Tarjeta");

        assertThrows(IllegalArgumentException.class,
                () -> FormasDePagoController.validarFormaDePago(forma, true));
    }

    @Test
    void nombreEsObligatorioYRespetaLongitudDelSchema() {
        assertThrows(IllegalArgumentException.class,
                () -> FormasDePagoController.validarFormaDePago(
                        nuevaForma(1, "   "), true));

        assertDoesNotThrow(
                () -> FormasDePagoController.validarFormaDePago(
                        nuevaForma(1, "X".repeat(18)), true));

        assertThrows(IllegalArgumentException.class,
                () -> FormasDePagoController.validarFormaDePago(
                        nuevaForma(1, "X".repeat(19)), true));
    }

    @Test
    void rechazaObjetoNuloAntesDeAbrirJdbc() {
        assertThrows(IllegalArgumentException.class,
                () -> FormasDePagoController.validarFormaDePago(null, false));
    }

    private static FormasDePago nuevaForma(int id, String nombre) {
        FormasDePago forma = new FormasDePago();
        forma.setId(id);
        forma.setTipoDePago(nombre);
        forma.setEsFlujoEfectivo(true);
        return forma;
    }
}
