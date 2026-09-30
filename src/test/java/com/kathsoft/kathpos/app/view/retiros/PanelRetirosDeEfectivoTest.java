package com.kathsoft.kathpos.app.view.retiros;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.tools.ConstantsConllections;

/**
 * Contratos Swing y parseo sin instanciar ventanas ni usar MySQL.
 */
class PanelRetirosDeEfectivoTest {

    @Test
    void modeloTieneOchoColumnasSinEditor() {
        DefaultTableModel model = PanelRetirosDeEfectivo.crearModeloTabla();
        assertEquals(8, model.getColumnCount());
        assertEquals("ID", model.getColumnName(0));
        assertEquals("Folio", model.getColumnName(1));
        assertEquals("Fecha", model.getColumnName(2));
        assertEquals("Empleado", model.getColumnName(3));
        assertEquals("Descripción", model.getColumnName(4));
        assertEquals("Importe", model.getColumnName(5));
        assertEquals("Tipo de retiro", model.getColumnName(6));
        assertEquals("Activo", model.getColumnName(7));

        model.addRow(new Object[] {1, "R-00000001", LocalDate.of(2026, 9, 28),
                "Empleado", "Retiro", new BigDecimal("45.00"), "Retiro parcial", "Activo"});
        assertEquals(model.getColumnCount(),
                ConstantsConllections.tablaRetirosDeEfectivoColumnsWidth.length);
        for (int i = 0; i < model.getColumnCount(); i++) {
            assertFalse(model.isCellEditable(0, i));
            assertTrue(ConstantsConllections.tablaRetirosDeEfectivoColumnsWidth[i] > 0);
        }
    }

    @Test
    void fechasSonOpcionalesYRespetanAniosBisiestos() {
        assertNull(PanelRetirosDeEfectivo.interpretarFechaFiltro(null));
        assertNull(PanelRetirosDeEfectivo.interpretarFechaFiltro(""));
        assertNull(PanelRetirosDeEfectivo.interpretarFechaFiltro("__/__/____"));
        assertEquals(LocalDate.of(2024, 2, 29),
                PanelRetirosDeEfectivo.interpretarFechaFiltro("29/02/2024"));
    }

    @Test
    void fechasParcialesOImposiblesSeRechazan() {
        assertThrows(IllegalArgumentException.class,
                () -> PanelRetirosDeEfectivo.interpretarFechaFiltro("01/0_/2026"));
        assertThrows(IllegalArgumentException.class,
                () -> PanelRetirosDeEfectivo.interpretarFechaFiltro("31/02/2026"));
        assertThrows(IllegalArgumentException.class,
                () -> PanelRetirosDeEfectivo.interpretarFechaFiltro("29/02/2025"));
        assertThrows(IllegalArgumentException.class,
                () -> PanelRetirosDeEfectivo.interpretarFechaFiltro("09-28-2026"));
    }

    @Test
    void obtieneElIdDelModeloAunqueLaVistaEsteOrdenada() {
        DefaultTableModel model = PanelRetirosDeEfectivo.crearModeloTabla();
        model.addRow(new Object[] {10, "R-10", null, null, null, null, "Corte final", "Activo"});
        model.addRow(new Object[] {2, "R-2", null, null, null, null, "Retiro parcial", "Activo"});
        JTable tabla = new JTable(model);
        tabla.setAutoCreateRowSorter(true);
        assertEquals(-1, PanelRetirosDeEfectivo.idRetiroSeleccionado(tabla, model));

        tabla.getRowSorter().toggleSortOrder(0);
        int filaVista = tabla.convertRowIndexToView(1);
        tabla.setRowSelectionInterval(filaVista, filaVista);
        assertEquals(2, PanelRetirosDeEfectivo.idRetiroSeleccionado(tabla, model));
    }
}
