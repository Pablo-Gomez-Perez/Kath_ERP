package com.kathsoft.kathpos.app.view.inventario;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.table.DefaultTableModel;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.tools.ConstantsConllections;

class PanelCategoriaSalidaDeINventarioTest {

    @Test
    void modeloCoincideConContratoDeCategoriaSalidas() {
        DefaultTableModel model =
                PanelCategoriaSalidaDeINventario.crearModeloTabla();

        assertEquals(4, model.getColumnCount());

        assertArrayEquals(
                new Object[] {
                    "id_categoria",
                    "nombre",
                    "descripcion",
                    "activo"
                },
                new Object[] {
                    model.getColumnName(0),
                    model.getColumnName(1),
                    model.getColumnName(2),
                    model.getColumnName(3)
                });

        assertEquals(
                model.getColumnCount(),
                ConstantsConllections
                        .tablaCategoriaSalidaInventarioColumnsWidth.length);
    }

    @Test
    void modeloEsSoloLecturaYAnchosSonValidos() {
        DefaultTableModel model =
                PanelCategoriaSalidaDeINventario.crearModeloTabla();

        model.addRow(new Object[] {
            1,
            "Merma",
            "Pérdidas de producto",
            "Activo"
        });

        for (int columna = 0; columna < model.getColumnCount(); columna++) {
            assertFalse(model.isCellEditable(0, columna));
            assertFalse(model.isCellEditable(1, columna));
            assertTrue(
                    ConstantsConllections
                            .tablaCategoriaSalidaInventarioColumnsWidth[columna]
                            > 0);
        }
    }

    @Test
    void panelPuedeConstruirseSinConsultarBaseDeDatos() {
        assertDoesNotThrow(PanelCategoriaSalidaDeINventario::new);
    }
}
