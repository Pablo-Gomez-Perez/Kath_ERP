package com.kathsoft.kathpos.tools;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DataTools {
	
	public static final String DATA_BASE = "kath_erp";
	
	public static int getIndiceElementoSeleccionado(JTable tabla, DefaultTableModel model, int columna) {
		
		int filaSeleccionada = -1;
		int indiceFilaSeleccionada = -1;
		
		try {
			
			filaSeleccionada = tabla.getSelectedRow();
			indiceFilaSeleccionada = (int)model.getValueAt(filaSeleccionada, columna);			
			return indiceFilaSeleccionada;
			
		}catch(Exception er) {			
			er.printStackTrace();
			JOptionPane.showMessageDialog(tabla, "Ha ocurrido un error: " + er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return -1;
		}
		
	}
	
}
