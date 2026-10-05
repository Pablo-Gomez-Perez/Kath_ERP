package com.kathsoft.kathpos.tools;

import java.awt.Component;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

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
					var documento = new FileWriter(fc.getSelectedFile().getCanonicalPath().concat(".csv"));
					var contenido = new PrintWriter(documento);
					String data = GenerarContenidoCsv(model);					
					
					contenido.println(data);
					
					contenido.close();
					fc.cancelSelection();
					MessageHandler.displayMessage(MessageHandler.FILE_SUCCESS_MESSAGE, parent, fc.getSelectedFile().getCanonicalPath());					
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

	/**
	 * Exporta exclusivamente el contenido visible de un {@link JTable} a CSV.
	 * La validación de filas se realiza antes de abrir el selector de archivos.
	 *
	 * @param tabla tabla cuyo contenido será exportado
	 * @param parent componente padre para los diálogos
	 * @throws IOException si el archivo no puede escribirse
	 * @throws IllegalArgumentException si la tabla es nula o no contiene filas
	 */
	public static void exportarJTableCsv(JTable tabla, Component parent) throws IOException {
		validarTablaParaExportar(tabla);

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Guardar archivo CSV");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Archivo CSV (*.csv)", "csv"));

		if (chooser.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		Path ruta = asegurarExtensionCsv(chooser.getSelectedFile().toPath());

		if (Files.exists(ruta)) {
			int respuesta = JOptionPane.showConfirmDialog(
					parent,
					"El archivo ya existe. ¿Desea reemplazarlo?",
					"Confirmar reemplazo",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);

			if (respuesta != JOptionPane.YES_OPTION) {
				return;
			}
		}

		Files.writeString(ruta, generarContenidoCsv(tabla), StandardCharsets.UTF_8);
		MessageHandler.displayMessage(
				MessageHandler.FILE_SUCCESS_MESSAGE,
				parent,
				ruta.toAbsolutePath().toString());
	}


	/**
	 * Exporta el contenido visible de un {@link JTable} a un archivo de texto,
	 * incluyendo un encabezado descriptivo y líneas de resumen posteriores.
	 *
	 * @param tabla tabla cuyo contenido será exportado
	 * @param parent componente padre para los diálogos
	 * @param encabezado título que identifica el reporte
	 * @param resumen líneas adicionales que se escribirán después de la tabla
	 * @throws IOException si el archivo no puede escribirse
	 * @throws IllegalArgumentException si la tabla está vacía o el encabezado es inválido
	 */
	public static void exportarJTableTxt(
			JTable tabla,
			Component parent,
			String encabezado,
			String... resumen) throws IOException {

		validarTablaParaExportar(tabla);

		if (encabezado == null || encabezado.isBlank()) {
			throw new IllegalArgumentException("El encabezado del reporte es obligatorio");
		}

		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Guardar archivo TXT");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(new FileNameExtensionFilter("Archivo de texto (*.txt)", "txt"));

		if (chooser.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION) {
			return;
		}

		Path ruta = asegurarExtension(chooser.getSelectedFile().toPath(), ".txt");

		if (Files.exists(ruta)) {
			int respuesta = JOptionPane.showConfirmDialog(
					parent,
					"El archivo ya existe. ¿Desea reemplazarlo?",
					"Confirmar reemplazo",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);

			if (respuesta != JOptionPane.YES_OPTION) {
				return;
			}
		}

		Files.writeString(
				ruta,
				generarContenidoTxt(tabla, encabezado, resumen),
				StandardCharsets.UTF_8);

		MessageHandler.displayMessage(
				MessageHandler.FILE_SUCCESS_MESSAGE,
				parent,
				ruta.toAbsolutePath().toString());
	}

	static String generarContenidoTxt(JTable tabla, String encabezado, String... resumen) {
		validarTablaParaExportar(tabla);

		if (encabezado == null || encabezado.isBlank()) {
			throw new IllegalArgumentException("El encabezado del reporte es obligatorio");
		}

		StringBuilder texto = new StringBuilder();
		texto.append(encabezado.trim()).append(System.lineSeparator()).append(System.lineSeparator());

		for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
			if (columna > 0) {
				texto.append('\t');
			}
			texto.append(normalizarTextoPlano(tabla.getColumnName(columna)));
		}
		texto.append(System.lineSeparator());

		for (int fila = 0; fila < tabla.getRowCount(); fila++) {
			for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
				if (columna > 0) {
					texto.append('\t');
				}

				Object valor = tabla.getValueAt(fila, columna);
				texto.append(normalizarTextoPlano(valor == null ? "" : String.valueOf(valor)));
			}
			texto.append(System.lineSeparator());
		}

		if (resumen != null && resumen.length > 0) {
			texto.append(System.lineSeparator());
			for (String linea : resumen) {
				if (linea != null && !linea.isBlank()) {
					texto.append(linea.trim()).append(System.lineSeparator());
				}
			}
		}

		return texto.toString();
	}

	static String generarContenidoCsv(JTable tabla) {
		validarTablaParaExportar(tabla);

		StringBuilder csv = new StringBuilder();

		for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
			if (columna > 0) {
				csv.append(',');
			}
			csv.append(escaparCsv(tabla.getColumnName(columna)));
		}
		csv.append("\r\n");

		for (int fila = 0; fila < tabla.getRowCount(); fila++) {
			for (int columna = 0; columna < tabla.getColumnCount(); columna++) {
				if (columna > 0) {
					csv.append(',');
				}
				Object valor = tabla.getValueAt(fila, columna);
				csv.append(escaparCsv(valor == null ? "" : String.valueOf(valor)));
			}

			if (fila < tabla.getRowCount() - 1) {
				csv.append("\r\n");
			}
		}

		return csv.toString();
	}

	private static void validarTablaParaExportar(JTable tabla) {
		if (tabla == null) {
			throw new IllegalArgumentException("La tabla a exportar es obligatoria");
		}
		if (tabla.getRowCount() == 0) {
			throw new IllegalArgumentException("No existen datos a exportar");
		}
	}

	private static Path asegurarExtensionCsv(Path ruta) {
		return asegurarExtension(ruta, ".csv");
	}

	private static Path asegurarExtension(Path ruta, String extension) {
		String nombre = ruta.getFileName().toString();
		if (nombre.toLowerCase(Locale.ROOT).endsWith(extension.toLowerCase(Locale.ROOT))) {
			return ruta;
		}
		return Path.of(ruta.toString() + extension);
	}

	private static String normalizarTextoPlano(String valor) {
		if (valor == null) {
			return "";
		}

		return valor
				.replace('\t', ' ')
				.replace('\r', ' ')
				.replace('\n', ' ');
	}

	private static String escaparCsv(String valor) {
		if (valor == null) {
			return "";
		}

		boolean requiereComillas = valor.indexOf(',') >= 0
				|| valor.indexOf('"') >= 0
				|| valor.indexOf('\n') >= 0
				|| valor.indexOf('\r') >= 0;

		if (!requiereComillas) {
			return valor;
		}

		return "\"" + valor.replace("\"", "\"\"") + "\"";
	}

}
