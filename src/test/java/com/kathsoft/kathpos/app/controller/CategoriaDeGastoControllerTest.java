package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.gastos.CategoriaDeGasto;

/**
 * Reglas previas a JDBC. No necesita BD ni instalar los SP manuales.
 */
class CategoriaDeGastoControllerTest {

    private final CategoriaDeGastoController controller = new CategoriaDeGastoController();

    @Test
    void nombreEsObligatorio() {
        CategoriaDeGasto categoria = new CategoriaDeGasto();
        categoria.setNombre(" ");
        assertThrows(IllegalArgumentException.class, categoria::validarParaGuardar);
        assertEquals(500, controller.crearCategoria(categoria).id());
    }

    @Test
    void rechazaNombreDeMasDe255Caracteres() {
        CategoriaDeGasto categoria = nuevaCategoria("A".repeat(256), null);
        assertThrows(IllegalArgumentException.class, categoria::validarParaGuardar);
        assertEquals(500, controller.crearCategoria(categoria).id());
    }

    @Test
    void aceptaNombreDe255Caracteres() {
        CategoriaDeGasto categoria = nuevaCategoria("A".repeat(255), null);
        assertDoesNotThrow(categoria::validarParaGuardar);
    }

    @Test
    void rechazaDescripcionSuperiorA550Caracteres() {
        CategoriaDeGasto categoria = nuevaCategoria("Servicios", "X".repeat(551));
        assertThrows(IllegalArgumentException.class, categoria::validarParaGuardar);
        assertEquals(500, controller.crearCategoria(categoria).id());
    }

    @Test
    void aceptaDescripcionOpcionalDe550Caracteres() {
        CategoriaDeGasto categoria = nuevaCategoria("Servicios", "X".repeat(550));
        assertDoesNotThrow(categoria::validarParaGuardar);
        categoria.setDescripcion(null);
        assertDoesNotThrow(categoria::validarParaGuardar);
    }

    @Test
    void validarLongitudesEnCaracteresUnicode() {
        CategoriaDeGasto categoria = nuevaCategoria("😀".repeat(255), null);
        assertDoesNotThrow(categoria::validarParaGuardar);
        categoria.setNombre("😀".repeat(256));
        assertThrows(IllegalArgumentException.class, categoria::validarParaGuardar);
    }

    @Test
    void actualizarRequiereIdentificadorValidoSinConectarse() {
        CategoriaDeGasto categoria = nuevaCategoria("Alquiler", null);
        assertEquals(500, controller.actualizarCategoria(categoria).id());
    }

    @Test
    void eliminarRechazaIdInvalidoSinConectarse() {
        assertEquals(500, controller.eliminarCategoria(0).id());
        assertEquals(500, controller.eliminarCategoria(-1).id());
    }

    @Test
    void nuevoModeloSeInicializaActivoYToStringEsElNombre() {
        CategoriaDeGasto categoria = nuevaCategoria("Servicios", null);
        assertTrue(categoria.isActivo());
        assertEquals("Servicios", categoria.toString());
        categoria.setActivo(false);
        assertFalse(categoria.isActivo());
    }

    private static CategoriaDeGasto nuevaCategoria(String nombre, String descripcion) {
        CategoriaDeGasto categoria = new CategoriaDeGasto();
        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);
        return categoria;
    }
}
