package com.kathsoft.kathpos.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

class DataToolsCsvTest {

    @Test
    void rechazaTablaSinFilas() {
        JTable tabla = new JTable(new DefaultTableModel(
                new Object[] { "Fecha", "Total" }, 0));

        assertThrows(IllegalArgumentException.class,
                () -> DataTools.generarContenidoCsv(tabla));
    }

    @Test
    void exportaEncabezadosYFilasDelJTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Fecha", "Número de ventas", "Total" }, 0);
        model.addRow(new Object[] { "01/10/2026", 3, "348.00" });
        model.addRow(new Object[] { "02/10/2026", 2, "232.00" });

        JTable tabla = new JTable(model);

        assertEquals(
                "Fecha,Número de ventas,Total\r\n"
                        + "01/10/2026,3,348.00\r\n"
                        + "02/10/2026,2,232.00",
                DataTools.generarContenidoCsv(tabla));
    }

    @Test
    void aplicaEscapeCsvAValoresEspeciales() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Descripción", "Total" }, 0);
        model.addRow(new Object[] { "Cliente, \"Especial\"\nTuxtla", "116.00" });

        JTable tabla = new JTable(model);

        assertEquals(
                "Descripción,Total\r\n"
                        + "\"Cliente, \"\"Especial\"\"\nTuxtla\",116.00",
                DataTools.generarContenidoCsv(tabla));
    }

    @Test
    void exportaSoloLasColumnasPresentesEnElJTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Fecha", "Interna", "Total" }, 0);
        model.addRow(new Object[] { "01/10/2026", "NO_EXPORTAR", "116.00" });

        JTable tabla = new JTable(model);
        tabla.removeColumn(tabla.getColumnModel().getColumn(1));

        assertEquals(
                "Fecha,Total\r\n01/10/2026,116.00",
                DataTools.generarContenidoCsv(tabla));
    }
}
