package com.kathsoft.kathpos.app.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Conexion {
	
	/**
	 * bloque estatico para carga del controlador al momento de instanciar la clase
	 */
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null,"Error al cargar controlador: " + er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
		}
		
	}
	
	/**
	 * establece la conexión al servidor de la base de datos que se le pase como argumento
	 * @param nombreBBDD
	 * @return
	 * @throws SQLException
	 */
	public static Connection establecerConexionLocal(String nombreBBDD) throws SQLException{
		final String url = "jdbc:mysql://localhost:3306/" + nombreBBDD;
		final String user = "root";
		return DriverManager.getConnection(url,user,"");
	}
	
	/**
	 * Retorna un objeto de tipo resulset para las consultas efectuadas.
	 * @param conexion
	 * @param query
	 * @return 
	 * @throws SQLException
	 */
	public static ResultSet queryResulset(Connection conexion, String query) throws SQLException{
		Statement stm = conexion.createStatement();
		return stm.executeQuery(query);
	}
	
}
