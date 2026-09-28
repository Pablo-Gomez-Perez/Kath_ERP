package com.kathsoft.kathpos.app.view.gastos;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.gastos.GastoFiltro;
import com.kathsoft.kathpos.tools.ConstantsConllections;

/**
 * Pruebas de contrato del panel sin construir ventanas ni acceder a MySQL.
 */
class PanelGastosTest {

    @Test
    void modeloListaDiezColumnasNoEditablesConDiezAnchos() {
        DefaultTableModel modelo = PanelGastos.crearModeloTabla();

        assertEquals(10, modelo.getColumnCount());
        assertEquals(10, ConstantsConllections.tablaGastosColumnsWidth.length);
        assertEquals("Folio", modelo.getColumnName(0));
        assertEquals("Fecha", modelo.getColumnName(1));
        assertEquals("Empleado", modelo.getColumnName(2));
        assertEquals("Categoría", modelo.getColumnName(3));
        assertEquals("Forma de pago", modelo.getColumnName(4));
        assertEquals("Descripción", modelo.getColumnName(5));
        assertEquals("Importe", modelo.getColumnName(6));
        assertEquals("IVA", modelo.getColumnName(7));
        assertEquals("Total", modelo.getColumnName(8));
        assertEquals("Activo", modelo.getColumnName(9));

        modelo.addRow(new Object[] {5, LocalDate.of(2026, 9, 27), "Empleado",
                "Servicios", "Efectivo", "Papelería", 100, 16, 116, "Activo"});
        for (int columna = 0; columna < modelo.getColumnCount(); columna++) {
            assertFalse(modelo.isCellEditable(0, columna));
            assertTrue(ConstantsConllections.tablaGastosColumnsWidth[columna] > 0);
        }
    }

    @Test
    void fechasSonOpcionalesYAdmitenDiaMesAnio() {
        assertNull(PanelGastos.interpretarFechaFiltro(null));
        assertNull(PanelGastos.interpretarFechaFiltro(""));
        assertNull(PanelGastos.interpretarFechaFiltro("__/__/____"));
        assertEquals(LocalDate.of(2024, 2, 29),
                PanelGastos.interpretarFechaFiltro("29/02/2024"));
        assertEquals(LocalDate.of(2026, 9, 27),
                PanelGastos.interpretarFechaFiltro("27/09/2026"));
    }

    @Test
    void fechasIncompletasONoExistentesSonRechazadas() {
        assertThrows(IllegalArgumentException.class,
                () -> PanelGastos.interpretarFechaFiltro("01/0_/2026"));
        assertThrows(IllegalArgumentException.class,
                () -> PanelGastos.interpretarFechaFiltro("31/02/2026"));
        assertThrows(IllegalArgumentException.class,
                () -> PanelGastos.interpretarFechaFiltro("29/02/2025"));
        assertThrows(IllegalArgumentException.class,
                () -> PanelGastos.interpretarFechaFiltro("09-27-2026"));
    }

    @Test
    void seleccionUtilizaElIndiceRealDelModeloInclusoTrasOrdenar() {
        DefaultTableModel modelo = PanelGastos.crearModeloTabla();
        modelo.addRow(new Object[] {10, null, null, null, null, null, null, null, null, null});
        modelo.addRow(new Object[] {2, null, null, null, null, null, null, null, null, null});

        JTable tabla = new JTable(modelo);
        tabla.setAutoCreateRowSorter(true);
        assertEquals(-1, PanelGastos.idGastoDeFila(tabla, modelo));

        tabla.getRowSorter().toggleSortOrder(0);
        int vista = tabla.convertRowIndexToView(1); // ID 2
        tabla.setRowSelectionInterval(vista, vista);
        assertEquals(2, PanelGastos.idGastoDeFila(tabla, modelo));
    }

    @Test
    void enumeracionContieneLosCuatroCriteriosDelProcedimiento() {
        assertEquals(4, GastoFiltro.Orden.values().length);
        assertEquals(1, GastoFiltro.Orden.FECHA_RECIENTE.codigoSql());
        assertEquals(2, GastoFiltro.Orden.FECHA_ANTIGUA.codigoSql());
        assertEquals(3, GastoFiltro.Orden.EMPLEADO.codigoSql());
        assertEquals(4, GastoFiltro.Orden.CATEGORIA.codigoSql());
        assertEquals("Fecha más reciente", GastoFiltro.Orden.FECHA_RECIENTE.toString());
    }
}
