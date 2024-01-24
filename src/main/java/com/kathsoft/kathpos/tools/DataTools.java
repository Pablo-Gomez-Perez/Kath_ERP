package com.kathsoft.kathpos.tools;

import java.awt.Component;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
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
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, null, er.getMessage());
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
	
	/**
	 * Exporta el contenido de un Jtable a un fichero de tipo CSV
	 * @param model
	 * @param ruta
	 * @throws Exception
	 */
	public static void exportarTablaExcel(DefaultTableModel model, Component parent) throws Exception {									
		
		new Thread(() -> {			
			try {
				var fc = new JFileChooser();
				fc.setFileFilter(new FileNameExtensionFilter("csv","txt"));				
				int optionVal = fc.showSaveDialog(parent);				
				if(optionVal == JFileChooser.APPROVE_OPTION) {					
					var documento = new FileWriter(fc.getSelectedFile());
					var contenido = new PrintWriter(documento);
					String data = GenerarContenidoCsv(model);					
					contenido.println(data);
					
					contenido.close();
					fc.cancelSelection();
					MessageHandler.displayMessage(MessageHandler.CREATE_SUCCESS_MESSAGE, parent, fc.getSelectedFile().getCanonicalPath());					
				}
				if(optionVal == JFileChooser.ERROR_OPTION) {
					return;
				}
			}catch(Exception er) {
				er.printStackTrace();
			}
		}).start();			
				
	}
	
	public static String GenerarContenidoCsv(DefaultTableModel model) throws Exception {
		StringBuilder sb = new StringBuilder();
		
		if(model.getDataVector().size() < 1) {
			throw new Exception("No existen datos a exportar");			
		}
		
		model.getDataVector().forEach(v -> {
			sb.append("\n");
			for(int i = 0; i < v.size(); i++) {
				sb.append(String.valueOf(v.get(i)));
				sb.append(",");
			}			
		});
		
		return sb.toString();
	}

}
