package com.kathsoft.kathpos.app.view.gastos;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.tools.ConstantsConllections;

/**
 * Verifica el contrato entre las cuatro columnas SQL y la tabla Swing
 * sin mostrar ventanas ni necesitar una conexión a la base de datos.
 */
class PanelCategoriaDeGastoTest {

    @Test
    void modeloCoincideConLasCuatroColumnasDelListado() {
        DefaultTableModel model = PanelCategoriaDeGasto.crearModeloTabla();

        assertEquals(4, model.getColumnCount());
        assertArrayEquals(new Object[] {"Id", "Nombre", "Descripción", "Activo"},
                new Object[] {model.getColumnName(0), model.getColumnName(1),
                        model.getColumnName(2), model.getColumnName(3)});
        assertEquals(model.getColumnCount(),
                ConstantsConllections.tablaCategoriasDeGastoColumnsWidth.length);
    }

    @Test
    void todasLasCeldasSonSoloLecturaYLosAnchosSonPositivos() {
        DefaultTableModel model = PanelCategoriaDeGasto.crearModeloTabla();
        model.addRow(new Object[] {1, "Servicios", "Operación", "Activo"});

        for (int col = 0; col < model.getColumnCount(); col++) {
            assertFalse(model.isCellEditable(0, col));
            assertFalse(model.isCellEditable(1, col));
            org.junit.jupiter.api.Assertions.assertTrue(
                    ConstantsConllections.tablaCategoriasDeGastoColumnsWidth[col] > 0);
        }
    }
}
