package com.kathsoft.kathpos.tools;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class MessageHandler {

	public static final short INSERT_SUCCESS_MESSAGE = 1;
	public static final short DELETE_SUCCESS_MESSAGE = 2;
	public static final short UPDATE_SUCCESS_MESSAGE = 3;
	public static final short ERROR_MESSAGE = 4;
	public static final short WARN_MESSAGE = 5;
	public static final short DELETE_DATA_QUESTION_MESSAGE = 6;

	public static int displayMessage(int type, Component parentComponent, String... message) {

		var sb = new StringBuilder();
		
		Arrays.asList(message).stream().forEach(m -> {
			sb.append(m);
		});
						
		var messageCompleted = sb.toString();

		switch (type) {
		case INSERT_SUCCESS_MESSAGE: {
			JOptionPane.showMessageDialog(parentComponent, "Registro almacenado con éxito " + messageCompleted,
					"Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
		case DELETE_SUCCESS_MESSAGE: {
			JOptionPane.showMessageDialog(parentComponent, "Registro eliminado con éxito " + messageCompleted,
					"Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
		case UPDATE_SUCCESS_MESSAGE: {
			JOptionPane.showMessageDialog(parentComponent, "Registro actualizado con éxito " + messageCompleted,
					"Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
		case ERROR_MESSAGE: {
			JOptionPane.showMessageDialog(parentComponent, "Ha ocurrido un error: " + messageCompleted, "Error",
					JOptionPane.ERROR_MESSAGE);
			return 0;
		}
		case WARN_MESSAGE: {
			JOptionPane.showMessageDialog(parentComponent, messageCompleted, "Atención", JOptionPane.WARNING_MESSAGE);
			return 0;
		}
		case DELETE_DATA_QUESTION_MESSAGE: {
			return JOptionPane.showConfirmDialog(parentComponent, "Desea Eliminar el registro" + messageCompleted,
					"Eliminar Registro", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		}
		default:
			return 0;
		}

	}

}
