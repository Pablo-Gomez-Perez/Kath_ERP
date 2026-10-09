package com.kathsoft.kathpos.app.view.reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

class Fr_ReporteDetalleVentasTest {

    @Test
    void modeloVentasTieneContratoEsperadoYNoEsEditable() {
        DefaultTableModel modelo = Fr_ReporteDetalleVentas.crearModeloVentas();

        assertEquals(9, modelo.getColumnCount());
        assertEquals("Folio", modelo.getColumnName(0));
        assertEquals("Fecha", modelo.getColumnName(1));
        assertEquals("Tipo", modelo.getColumnName(2));
        assertEquals("Atendió", modelo.getColumnName(3));
        assertEquals("Cliente", modelo.getColumnName(4));
        assertEquals("Sub total", modelo.getColumnName(5));
        assertEquals("IVA", modelo.getColumnName(6));
        assertEquals("Total", modelo.getColumnName(7));
        assertEquals("Estado", modelo.getColumnName(8));

        modelo.addRow(new Object[] {
                1, "07/10/2026", "Contado", "Empleado", "Cliente",
                100.00, 16.00, 116.00, "Vigente"
        });

        for (int columna = 0; columna < modelo.getColumnCount(); columna++) {
            assertFalse(modelo.isCellEditable(0, columna));
        }
    }

    @Test
    void modelosDeResumenTienenDosColumnasNoEditables() {
        DefaultTableModel formas = Fr_ReporteDetalleVentas.crearModeloFormasDePago();
        DefaultTableModel empleados = Fr_ReporteDetalleVentas.crearModeloDetallePorEmpleado();
        DefaultTableModel retiros = Fr_ReporteDetalleVentas.crearModeloRetiros();

        assertEquals(2, formas.getColumnCount());
        assertEquals("Forma de pago", formas.getColumnName(0));
        assertEquals("Total", formas.getColumnName(1));

        assertEquals(2, empleados.getColumnCount());
        assertEquals("Empleado", empleados.getColumnName(0));
        assertEquals("Total", empleados.getColumnName(1));

        assertEquals(2, retiros.getColumnCount());
        assertEquals("Folio", retiros.getColumnName(0));
        assertEquals("Importe", retiros.getColumnName(1));

        formas.addRow(new Object[] { "Efectivo", 100.00 });
        empleados.addRow(new Object[] { "Empleado", 100.00 });
        retiros.addRow(new Object[] { "RET-001", 50.00 });

        assertFalse(formas.isCellEditable(0, 0));
        assertFalse(empleados.isCellEditable(0, 0));
        assertFalse(retiros.isCellEditable(0, 0));
    }
}
