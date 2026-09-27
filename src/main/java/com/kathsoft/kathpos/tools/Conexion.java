package com.kathsoft.kathpos.tools;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kathsoft.kathpos.app.model.configuracion.ParametrosDeConexion;

/**
 * Punto único de conexión JDBC para todos los módulos de Kath ERP.
 *
 * <p>Lee la configuración cifrada del perfil del usuario y conserva las
 * propiedades JVM / variables de entorno como alternativas para CI y despliegues
 * administrados. El literal {@link #DATA_BASE} se mantiene únicamente como
 * alias de compatibilidad: la base efectiva procede de {@code db.name}.</p>
 */
public final class Conexion {

    /**
     * Alias legado utilizado por controladores existentes. Pasarlo a
     * {@link #establecerConexionLocal(String)} selecciona la base configurada,
     * incluso cuando el usuario eligió un nombre diferente de kath_erp.
     */
    public static final String DATA_BASE = "kath_erp";

    private static final ConfiguracionConexionDBService ALMACEN =
            new ConfiguracionConexionDBService();

    private static volatile ParametrosDeConexion configuracionGuardada;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.err.println("No se encontró el controlador JDBC de MySQL");
        }
    }

    private Conexion() {
    }

    /**
     * Devuelve la ruta fija del archivo de conexión del usuario.
     *
     * @return ruta de database.properties cifrado
     */
    public static java.nio.file.Path rutaConfiguracion() {
        return ALMACEN.rutaArchivo();
    }

    /**
     * Detecta una configuración instalada o una configuración externa de
     * administrador. La mera existencia del archivo no garantiza su validez.
     *
     * @return {@code true} si existe una fuente de configuración
     */
    public static boolean hayConfiguracionDisponible() {
        return ALMACEN.existe()
                || System.getProperties().containsKey("db.user")
                || System.getenv("KATH_DB_USER") != null;
    }

    /**
     * Recarga el archivo cifrado tras guardarlo o al iniciar sesión.
     *
     * @throws IOException si el archivo no existe o no puede autenticarse
     */
    public static synchronized void recargarConfiguracion() throws IOException {
        configuracionGuardada = ALMACEN.cargar();
    }

    /**
     * Conserva en memoria los parámetros que acaba de confirmar el instalador.
     * Los controladores posteriores usan inmediatamente la nueva configuración.
     *
     * @param parametros configuración previamente validada y guardada
     */
    public static void activarConfiguracion(ParametrosDeConexion parametros) {
        parametros.validar();
        configuracionGuardada = parametros;
    }

    /**
     * Devuelve los valores guardados para editar la conexión desde su formulario.
     * Nunca se presenta la contraseña fuera de su componente enmascarado.
     *
     * @return configuración guardada, o {@code null} si no hay archivo
     * @throws IOException si existe pero está dañado o no puede descifrarse
     */
    public static ParametrosDeConexion obtenerConfiguracionGuardada() throws IOException {
        if (!ALMACEN.existe()) {
            return null;
        }
        ParametrosDeConexion actual = configuracionGuardada;
        if (actual == null) {
            recargarConfiguracion();
            actual = configuracionGuardada;
        }
        return actual;
    }

    /**
     * Abre una conexión con parámetros en memoria; la prueba no persiste nada
     * ni cambia la conexión del resto del sistema.
     *
     * @param parametros datos introducidos en el formulario
     * @return conexión JDBC válida para usar con try-with-resources
     * @throws SQLException si falla la autenticación, la red o el servidor
     */
    public static Connection probarConexion(ParametrosDeConexion parametros) throws SQLException {
        try {
            parametros.validar();
        } catch (IllegalArgumentException ex) {
            throw new SQLException(ex.getMessage(), "08001", ex);
        }
        return abrirConexion(parametros, parametros.name());
    }

    /**
     * Establece la conexión JDBC leyendo configuración cifrada o sobreescrituras.
     * Las propiedades JVM prevalecen sobre variables de entorno y sobre el
     * archivo. Las sobreescrituras vacías de contraseña son válidas.
     *
     * @param nombreBBDD nombre explícito, o {@link #DATA_BASE} / vacío para
     *                   utilizar la base de datos realmente configurada
     * @return conexión JDBC activa
     * @throws SQLException si falta configuración o no se puede conectar
     */
    public static Connection establecerConexionLocal(String nombreBBDD) throws SQLException {
        ParametrosDeConexion parametros = obtenerParametrosEfectivos();
        String base = nombreBBDD == null || nombreBBDD.isBlank()
                || DATA_BASE.equals(nombreBBDD)
                ? parametros.name() : nombreBBDD;
        if (!base.matches("[a-zA-Z0-9_-]+")) {
            throw new SQLException("Nombre de base de datos inválido");
        }
        return abrirConexion(parametros, base);
    }

    private static Connection abrirConexion(ParametrosDeConexion parametros, String base) throws SQLException {
        String url = "jdbc:mysql://" + parametros.host() + ":" + parametros.port() + "/" + base;
        if (!parametros.params().isBlank()) {
            url += "?" + parametros.params();
        }
        return DriverManager.getConnection(url, parametros.user(), parametros.password());
    }

    private static ParametrosDeConexion obtenerParametrosEfectivos() throws SQLException {
        ParametrosDeConexion guardada = configuracionGuardada;
        if (guardada == null && ALMACEN.existe()) {
            synchronized (Conexion.class) {
                guardada = configuracionGuardada;
                if (guardada == null) {
                    try {
                        recargarConfiguracion();
                        guardada = configuracionGuardada;
                    } catch (IOException ex) {
                        throw new SQLException("No fue posible cargar el archivo de conexión cifrado", "08001", ex);
                    }
                }
            }
        }

        String host = valor("db.host", "KATH_DB_HOST",
                guardada == null ? "localhost" : guardada.host());
        String port = valor("db.port", "KATH_DB_PORT",
                guardada == null ? "3306" : Integer.toString(guardada.port()));
        String nombre = valor("db.name", "KATH_DB_NAME",
                guardada == null ? DATA_BASE : guardada.name());
        String user = valor("db.user", "KATH_DB_USER",
                guardada == null ? null : guardada.user());
        String password = valor("db.password", "KATH_DB_PASSWORD",
                guardada == null ? null : guardada.password());
        String params = valor("db.params", "KATH_DB_PARAMS",
                guardada == null ? "serverTimezone=UTC" : guardada.params());

        if (user == null || user.isBlank() || password == null) {
            throw new SQLException("Debe configurar usuario y contraseña de la conexión a la base de datos");
        }
        try {
            ParametrosDeConexion efectiva = new ParametrosDeConexion(
                    host, Integer.parseInt(port), nombre, user, password, params);
            efectiva.validar();
            return efectiva;
        } catch (IllegalArgumentException ex) {
            throw new SQLException("La configuración de conexión es inválida: " + ex.getMessage(), "08001", ex);
        }
    }

    private static String valor(String propiedad, String entorno, String porDefecto) {
        String jvm = System.getProperty(propiedad);
        if (jvm != null) {
            return jvm; // Una contraseña vacía es un valor explícito válido.
        }
        String env = System.getenv(entorno);
        return env != null ? env : porDefecto;
    }

    /**
     * Realiza una prueba de conexión inicial utilizando la configuración
     * efectiva del sistema, sin depender de credenciales del empleado.
     *
     * @throws SQLException si los datos guardados son erróneos o la BD no responde
     */
    public static void verificarConexionInicial() throws SQLException {
        try (Connection ignorada = establecerConexionLocal(DATA_BASE)) {
            // DriverManager verifica el acceso antes de devolver la conexión.
        }
    }

    /**
     * Retorna un objeto de tipo resultset para las consultas efectuadas.
     *
     * @param conexion conexión abierta
     * @param query consulta aportada por el consumidor
     * @return resultado JDBC
     * @throws SQLException si falla la consulta
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
