package com.kathsoft.kathpos.tools;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class DataTools {

	public static final String DATA_BASE = "kath_erp";

	public static int getIndiceElementoSeleccionado(JTable tabla, DefaultTableModel model, int columna) {

		int filaSeleccionada = -1;
		int indiceFilaSeleccionada = -1;

		try {

			filaSeleccionada = tabla.getSelectedRow();
			indiceFilaSeleccionada = (int) model.getValueAt(filaSeleccionada, columna);
			return indiceFilaSeleccionada;

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(tabla, "Ha ocurrido un error: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return -1;
		}

	}

	/**
	 * Asigna un tamaño a las columnas de un JTable específicado en un Array de
	 * valores enteros
	 * 
	 * @param medidas -> arreglo con los valores de las medidas de cada columna
	 * @param tabla   -> tabla a la que se le asignan las medidas
	 */
	public static void definirTamanioDeColumnas(int[] medidas, JTable tabla) {

		TableColumnModel model = tabla.getColumnModel();

		try {

			for (int i = 0; i < medidas.length; i++) {
				model.getColumn(i).setPreferredWidth(medidas[i]);
				model.getColumn(i).setMinWidth(medidas[i]);
			}

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(tabla, "Ha ocurrido un error: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	/**
	 * Convierte los campos de una columna en estáticos y no editables, solo lectura
	 * 
	 * @param tabla -> tabla a modificar
	 */
	public static void removerEditorDeTabla(JTable tabla, DefaultTableModel model) {

		try {

			for (int i = 0; i < model.getColumnCount(); i++) {
				Class<?> colClass = tabla.getColumnClass(i);
				tabla.setDefaultEditor(colClass, null);
			}

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(tabla, "Ha ocurrido un error: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
