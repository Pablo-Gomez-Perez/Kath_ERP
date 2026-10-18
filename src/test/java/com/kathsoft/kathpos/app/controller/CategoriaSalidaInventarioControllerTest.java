package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.inventario.CategoriaSalidaInventario;

class CategoriaSalidaInventarioControllerTest {

    private final CategoriaSalidaInventarioController controller =
            new CategoriaSalidaInventarioController();

    @Test
    void nombreEsObligatorio() {
        CategoriaSalidaInventario categoria = nuevaCategoria(" ", null);

        assertThrows(
                IllegalArgumentException.class,
                categoria::validarParaGuardar);
        assertEquals(500, controller.crearCategoria(categoria).id());
    }

    @Test
    void nombreRespetaLimiteDe255Caracteres() {
        CategoriaSalidaInventario valida =
                nuevaCategoria("A".repeat(255), null);
        CategoriaSalidaInventario invalida =
                nuevaCategoria("A".repeat(256), null);

        assertDoesNotThrow(valida::validarParaGuardar);
        assertThrows(
                IllegalArgumentException.class,
                invalida::validarParaGuardar);
        assertEquals(500, controller.crearCategoria(invalida).id());
    }

    @Test
    void descripcionRespetaLimiteDe555Caracteres() {
        CategoriaSalidaInventario valida =
                nuevaCategoria("Merma", "X".repeat(555));
        CategoriaSalidaInventario invalida =
                nuevaCategoria("Merma", "X".repeat(556));

        assertDoesNotThrow(valida::validarParaGuardar);
        assertThrows(
                IllegalArgumentException.class,
                invalida::validarParaGuardar);
    }

    @Test
    void descripcionEsOpcional() {
        CategoriaSalidaInventario categoria =
                nuevaCategoria("Consumo interno", null);

        assertDoesNotThrow(categoria::validarParaGuardar);

        categoria.setDescripcion(" ");
        assertDoesNotThrow(categoria::validarParaGuardar);
    }

    @Test
    void actualizacionRequiereIdentificador() {
        CategoriaSalidaInventario categoria =
                nuevaCategoria("Daño", "Producto dañado");

        assertEquals(
                500,
                controller.actualizarCategoria(categoria).id());
    }

    @Test
    void eliminacionRechazaIdentificadoresInvalidosSinConexion() {
        assertEquals(500, controller.eliminarCategoria(0).id());
        assertEquals(500, controller.eliminarCategoria(-1).id());
    }

    @Test
    void consultaPorIdRechazaIdentificadorInvalidoSinConexion() {
        assertThrows(
                IllegalArgumentException.class,
                () -> controller.getCategoriaSalidaInventarioById(0));
    }

    @Test
    void modeloIniciaActivoYToStringUsaNombre() {
        CategoriaSalidaInventario categoria =
                nuevaCategoria("Caducidad", null);

        assertTrue(categoria.isActivo());
        assertEquals("Caducidad", categoria.toString());

        categoria.setActivo(false);
        assertFalse(categoria.isActivo());
    }

    @Test
    void validacionCuentaCaracteresUnicode() {
        CategoriaSalidaInventario categoria =
                nuevaCategoria("😀".repeat(255), null);

        assertDoesNotThrow(categoria::validarParaGuardar);

        categoria.setNombre("😀".repeat(256));
        assertThrows(
                IllegalArgumentException.class,
                categoria::validarParaGuardar);
    }

    private static CategoriaSalidaInventario nuevaCategoria(
            String nombre,
            String descripcion) {

        CategoriaSalidaInventario categoria =
                new CategoriaSalidaInventario();

        categoria.setNombre(nombre);
        categoria.setDescripcion(descripcion);

        return categoria;
    }
}
