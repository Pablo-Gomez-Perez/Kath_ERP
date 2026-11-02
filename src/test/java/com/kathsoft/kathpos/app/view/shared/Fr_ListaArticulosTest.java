package com.kathsoft.kathpos.app.view.shared;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class Fr_ListaArticulosTest {

    @Test
    void ventaRechazaArticuloSinExistencia() {
        assertFalse(Fr_ListaArticulos.permiteSeleccionarExistencia(
                ContextoSeleccionArticulo.VENTA, 0));
        assertFalse(Fr_ListaArticulos.permiteSeleccionarExistencia(
                ContextoSeleccionArticulo.VENTA, -1));
        assertTrue(Fr_ListaArticulos.permiteSeleccionarExistencia(
                ContextoSeleccionArticulo.VENTA, 1));
    }

    @Test
    void compraPermiteArticuloAunqueExistenciaSeaCero() {
        assertTrue(Fr_ListaArticulos.permiteSeleccionarExistencia(
                ContextoSeleccionArticulo.COMPRA, 0));
        assertTrue(Fr_ListaArticulos.permiteSeleccionarExistencia(
                ContextoSeleccionArticulo.COMPRA, 25));
    }

    @Test
    void contextoNuloConservaReglaSeguraDeVenta() {
        assertFalse(Fr_ListaArticulos.permiteSeleccionarExistencia(null, 0));
        assertTrue(Fr_ListaArticulos.permiteSeleccionarExistencia(null, 3));
    }

    @Test
    void conservaTipoClienteExplicito() {
        assertEquals(
                8,
                Fr_ListaArticulos.seleccionarIdTipoCliente(8, 2));
    }

    @Test
    void usaIdDinamicoDePublicoGeneralCuandoNoRecibeTipo() {
        assertEquals(
                2,
                Fr_ListaArticulos.seleccionarIdTipoCliente(0, 2));
    }

    @Test
    void fallaSiPublicoGeneralNoEstaConfigurado() {
        assertThrows(
                IllegalStateException.class,
                () -> Fr_ListaArticulos.seleccionarIdTipoCliente(0, 0));
    }
}
