package com.kathsoft.kathpos.tools;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

public class Conexion {

	private static final String CONFIG_FILE_PATH = "config/database.properties";
	private static final Properties DATABASE_PROPERTIES = cargarPropiedadesConexion();

	public static final String DATA_BASE = obtenerValorConfiguracion("KATH_DB_NAME", "db.name", "kath_erp");

	/**
	 * bloque estatico para carga del controlador al momento de instanciar la clase
	 */
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error al cargar controlador: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	/**
	 * Establece la conexion al servidor de base de datos usando configuracion externa.
	 * <p>
	 * La configuracion puede venir de variables de entorno o del archivo local
	 * {@code config/database.properties}. Las variables de entorno tienen prioridad
	 * sobre el archivo de propiedades.
	 *
	 * @param nombreBBDD nombre de la base de datos a utilizar; si viene vacio se usa
	 *                   la base configurada por defecto
	 * @return conexion JDBC activa
	 * @throws SQLException si falta configuracion obligatoria o si falla la conexion
	 */
	public static Connection establecerConexionLocal(String nombreBBDD) throws SQLException {
		String database = nombreBBDD == null || nombreBBDD.isBlank() ? DATA_BASE : nombreBBDD;
		String host = obtenerValorConfiguracion("KATH_DB_HOST", "db.host", "localhost");
		String port = obtenerValorConfiguracion("KATH_DB_PORT", "db.port", "3306");
		String params = obtenerValorConfiguracion("KATH_DB_PARAMS", "db.params", "serverTimezone=UTC");
		String user = obtenerValorConfiguracionObligatoria("KATH_DB_USER", "db.user");
		String password = obtenerValorConfiguracionObligatoria("KATH_DB_PASSWORD", "db.password");

		String url = "jdbc:mysql://" + host + ":" + port + "/" + database;

		if (!params.isBlank()) {
			url += "?" + params;
		}

		return DriverManager.getConnection(url, user, password);
	}

	private static Properties cargarPropiedadesConexion() {
		Properties properties = new Properties();
		Path configPath = Paths.get(CONFIG_FILE_PATH);

		if (!Files.exists(configPath)) {
			return properties;
		}

		try (InputStream input = Files.newInputStream(configPath)) {
			properties.load(input);
		} catch (IOException er) {
			er.printStackTrace();
		}

		return properties;
	}

	private static String obtenerValorConfiguracion(String envName, String propertyName, String defaultValue) {
		String envValue = System.getenv(envName);

		if (envValue != null && !envValue.isBlank()) {
			return envValue.trim();
		}

		String propertyValue = DATABASE_PROPERTIES.getProperty(propertyName);

		if (propertyValue != null && !propertyValue.isBlank()) {
			return propertyValue.trim();
		}

		return defaultValue;
	}

	private static String obtenerValorConfiguracionObligatoria(String envName, String propertyName) throws SQLException {
		String value = obtenerValorConfiguracion(envName, propertyName, null);

		if (value == null || value.isBlank()) {
			throw new SQLException("Configuracion de base de datos faltante: defina " + envName + " o " + propertyName
					+ " en " + CONFIG_FILE_PATH);
		}

		return value;
	}

	/**
	 * Retorna un objeto de tipo resulset para las consultas efectuadas.
	 * 
	 * @param conexion
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public static ResultSet queryResulset(Connection conexion, String query) throws SQLException {
		Statement stm = conexion.createStatement();
		return stm.executeQuery(query);
	}

	/**
	 * Cierra la conexión establecida con el servidor
	 * 
	 * @param cn
	 * @param stm
	 * @throws SQLException
	 */
	public static void cerrarConexion(Connection cn, CallableStatement stm) throws SQLException {
		if (cn != null) {
			cn.close();
		}

		if (stm != null) {
			stm.close();
		}
	}

	/**
	 * Cierra la conexión estbalcida con el servidor de la base de datos
	 * 
	 * @param cn
	 * @param rset
	 * @param stm
	 * @throws SQLException
	 */
	public static void cerrarConexion(Connection cn, ResultSet rset, CallableStatement stm) throws SQLException {

		if (cn != null) {
			cn.close();
		}

		if (stm != null) {
			stm.close();
		}

		if (rset != null) {
			rset.close();
		}

	}
	
	/**
	 * Cierra la conexión establecida con el servidor de la base de datos 
	 * 
	 * @param cn
	 * @param rset
	 * @param stm
	 * @throws SQLException
	 */
	public static void cerrarConexion(Connection cn, ResultSet rset, Statement stm) throws SQLException{
		
		if(cn != null) {
			cn.close();
		}
		
		if(rset != null) {
			rset.close();
		}
		
		if(stm != null) {
			stm.close();
		}
		
	}

}
