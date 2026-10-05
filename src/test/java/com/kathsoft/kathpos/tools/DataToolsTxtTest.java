package com.kathsoft.kathpos.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

class DataToolsTxtTest {

    @Test
    void rechazaTablaVacia() {
        JTable tabla = new JTable(new DefaultTableModel(
                new Object[] { "Fecha", "Total" }, 0));

        assertThrows(IllegalArgumentException.class,
                () -> DataTools.generarContenidoTxt(
                        tabla,
                        "Reporte de ventas totales de 01/10/2026 a 05/10/2026",
                        "Ventas Totales: 0.00"));
    }

    @Test
    void generaEncabezadoTablaYTotales() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Fecha", "Número de ventas", "Sub total", "IVA", "Total" }, 0);
        model.addRow(new Object[] { "01/10/2026", 3, "300.00", "48.00", "348.00" });
        model.addRow(new Object[] { "02/10/2026", 2, "200.00", "32.00", "232.00" });

        JTable tabla = new JTable(model);
        String nl = System.lineSeparator();

        assertEquals(
                "Reporte de ventas totales de 01/10/2026 a 02/10/2026" + nl
                        + nl
                        + "Fecha\tNúmero de ventas\tSub total\tIVA\tTotal" + nl
                        + "01/10/2026\t3\t300.00\t48.00\t348.00" + nl
                        + "02/10/2026\t2\t200.00\t32.00\t232.00" + nl
                        + nl
                        + "Ventas Totales: 580.00" + nl
                        + "I.V.A cobrado: 80.00" + nl,
                DataTools.generarContenidoTxt(
                        tabla,
                        "Reporte de ventas totales de 01/10/2026 a 02/10/2026",
                        "Ventas Totales: 580.00",
                        "I.V.A cobrado: 80.00"));
    }

    @Test
    void exportaSoloColumnasPresentesEnJTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Fecha", "Interna", "Total" }, 0);
        model.addRow(new Object[] { "01/10/2026", "NO_EXPORTAR", "116.00" });

        JTable tabla = new JTable(model);
        tabla.removeColumn(tabla.getColumnModel().getColumn(1));

        String nl = System.lineSeparator();

        assertEquals(
                "Reporte" + nl
                        + nl
                        + "Fecha\tTotal" + nl
                        + "01/10/2026\t116.00" + nl
                        + nl
                        + "Ventas Totales: 116.00" + nl,
                DataTools.generarContenidoTxt(
                        tabla,
                        "Reporte",
                        "Ventas Totales: 116.00"));
    }

    @Test
    void normalizaSaltosDeLineaYTabulacionesDeLasCeldas() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[] { "Detalle", "Total" }, 0);
        model.addRow(new Object[] { "Venta\nEspecial\tSucursal", "116.00" });

        JTable tabla = new JTable(model);
        String nl = System.lineSeparator();

        assertEquals(
                "Reporte" + nl
                        + nl
                        + "Detalle\tTotal" + nl
                        + "Venta Especial Sucursal\t116.00" + nl,
                DataTools.generarContenidoTxt(tabla, "Reporte"));
    }
}
