package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.gastos.GastoFiltro;
import com.kathsoft.kathpos.app.model.gastos.GastoRegistro;

/**
 * Contratos de gasto independientes de JDBC y de los SP instalados localmente.
 */
class GastoControllerTest {

    @Test
    void altaValidaPermiteImportePositivoEIvaCero() {
        assertNull(GastoController.validar(nuevoGasto("Servicios", "100.50", "0.00"), false));
    }

    @Test
    void actualizacionValidaRequiereFolio() {
        GastoRegistro nuevo = nuevoGasto("Servicios", "100.50", "16.08");
        assertEquals(500, GastoController.validar(nuevo, true).id());
        GastoRegistro edicion = new GastoRegistro(
                23, nuevo.idSucursal(), nuevo.idCategoria(), nuevo.idEmpleado(),
                nuevo.idFormaPago(), nuevo.descripcion(), nuevo.importe(), nuevo.iva());
        assertNull(GastoController.validar(edicion, true));
        assertEquals(500, GastoController.validar(edicion, false).id());
    }

    @Test
    void validaSeleccionDeSucursalCategoriaEmpleadoYFormaDePago() {
        GastoRegistro gasto = nuevoGasto("Papelería", "20.00", "3.20");
        assertEquals(500, GastoController.validar(
                new GastoRegistro(0, 0, 3, 5, 2, gasto.descripcion(), gasto.importe(), gasto.iva()),
                false).id());
        assertEquals(500, GastoController.validar(
                new GastoRegistro(0, 7, 3, 5, 0, gasto.descripcion(), gasto.importe(), gasto.iva()),
                false).id());
    }

    @Test
    void rechazaImportesNegativosOCero() {
        assertEquals(500, GastoController.validar(nuevoGasto("Gasolina", "0.00", "0.00"), false).id());
        assertEquals(500, GastoController.validar(nuevoGasto("Gasolina", "-1.00", "0.00"), false).id());
        assertEquals(500, GastoController.validar(nuevoGasto("Gasolina", "10.00", "-0.01"), false).id());
    }

    @Test
    void rechazaImportesConFraccionesMenoresAlCentavo() {
        assertEquals(500, GastoController.validar(
                nuevoGasto("Papelería", "23.001", "0.00"), false).id());
        assertEquals(500, GastoController.validar(
                nuevoGasto("Papelería", "23.00", "0.001"), false).id());
    }

    @Test
    void rechazaDescripcionVaciaOExcesiva() {
        assertEquals(500, GastoController.validar(nuevoGasto(" ", "100.00", "0.00"), false).id());
        assertEquals(500, GastoController.validar(
                nuevoGasto("A".repeat(256), "100.00", "0.00"), false).id());
        assertNull(GastoController.validar(
                nuevoGasto("A".repeat(255), "100.00", "0.00"), false));
    }

    @Test
    void elFiltroDeFechasExigeIntervaloCronologicoValido() {
        assertThrows(IllegalArgumentException.class, () -> new GastoFiltro(
                null, null, LocalDate.of(2026, 9, 28),
                LocalDate.of(2026, 9, 27), GastoFiltro.Orden.FECHA_RECIENTE));
    }

    @Test
    void filtroSinParametrosOrdenaPorFechaReciente() {
        GastoFiltro filtro = GastoFiltro.sinFiltros();
        assertNotNull(filtro);
        assertNull(filtro.idEmpleado());
        assertNull(filtro.idCategoria());
        assertEquals(GastoFiltro.Orden.FECHA_RECIENTE, filtro.orden());
        assertEquals(1, filtro.orden().codigoSql());
        assertEquals(2, GastoFiltro.Orden.FECHA_ANTIGUA.codigoSql());
        assertEquals(3, GastoFiltro.Orden.EMPLEADO.codigoSql());
        assertEquals(4, GastoFiltro.Orden.CATEGORIA.codigoSql());
    }

    @Test
    void filtroRechazaIdentificadoresNegativos() {
        assertThrows(IllegalArgumentException.class,
                () -> new GastoFiltro(-1, null, null, null, null));
        assertThrows(IllegalArgumentException.class,
                () -> new GastoFiltro(null, 0, null, null, null));
    }

    private static GastoRegistro nuevoGasto(String descripcion, String importe, String iva) {
        return new GastoRegistro(0, 7, 3, 5, 2,
                descripcion, new BigDecimal(importe), new BigDecimal(iva));
    }
}
