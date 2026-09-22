package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.kathsoft.kathpos.app.model.empleado.Empleado;
import com.kathsoft.kathpos.app.model.empleado.EmpleadoById;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;
import com.kathsoft.kathpos.tools.PasswordHashService;

/**
 * Controlador encargado de las operaciones de acceso y mantenimiento de empleados.
 * <p>
 * Centraliza la comunicación entre las vistas del módulo de empleados y los
 * procedimientos almacenados responsables de consultar, registrar, actualizar y
 * desactivar empleados. También transforma los resultados JDBC en modelos y
 * estructuras consumibles por la interfaz gráfica.
 * </p>
 * <p>
 * Las operaciones de persistencia se ejecutan mediante procedimientos almacenados;
 * el controlador no contiene lógica SQL de negocio embebida. Las contraseñas se
 * procesan mediante {@link PasswordHashService} antes de enviarse a la base de datos.
 * </p>
 *
 * @see Empleado
 * @see EmpleadoById
 * @see SpResponseModel
 * @see Conexion
 */
public class EmpleadoController implements Serializable {

	private static final long serialVersionUID = -1598821464656008533L;
	private static Connection cn = null;

	/**
	 * Crea una nueva instancia del controlador de empleados.
	 */
	public EmpleadoController() {
	}

	/**
	 * Obtiene los empleados asociados a una sucursal para su representación en
	 * componentes de selección.
	 * <p>
	 * Ejecuta el procedimiento almacenado
	 * {@code ver_rfc_empleado_por_sucursal} y transforma cada registro en un
	 * {@link JComboboxDataViewModel}, utilizando el identificador del empleado como
	 * valor y su nombre corto como texto visible.
	 * </p>
	 *
	 * @param id_sucursal identificador de la sucursal cuyos empleados se consultarán
	 * @return lista de empleados disponibles para selección; si ocurre un error,
	 *         devuelve la lista acumulada hasta ese momento, que puede estar vacía
	 */
	public List<JComboboxDataViewModel> consultaNombresCortosEmpleados(int id_sucursal) {
		CallableStatement stm = null;
		ResultSet rset = null;
		List<JComboboxDataViewModel> data = new ArrayList<>();

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_rfc_empleado_por_sucursal(?)");
			stm.setInt(1, id_sucursal);
			rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new JComboboxDataViewModel(rset.getInt(1), rset.getString(2)));
			}
			return data;
		} catch (SQLException er) {
			er.printStackTrace();
			return data;
		} catch (Exception er) {
			er.printStackTrace();
			return data;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Carga en un {@link JComboBox} los RFC de los empleados registrados.
	 * <p>
	 * La consulta se realiza mediante {@code getListadoEmpleados} sin aplicar filtro
	 * por nombre. Cada RFC recuperado se agrega al componente recibido.
	 * </p>
	 *
	 * @param jcmb componente que recibirá los RFC consultados
	 */
	@Deprecated
	public void consultarRfcEmpleado(JComboBox<String> jcmb) {
		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getListadoEmpleados(?)");
			stm.setString(1, "");
			rset = stm.executeQuery();
			while (rset.next()) {
				jcmb.addItem(rset.getString("rfc"));
			}
		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Consulta los datos de un empleado mediante su identificador.
	 * <p>
	 * Ejecuta {@code getEmpleadoById} y mapea el primer registro obtenido a
	 * {@link EmpleadoById}.
	 * </p>
	 *
	 * @param id identificador del empleado que se desea consultar
	 * @return empleado encontrado; devuelve una instancia vacía de
	 *         {@link EmpleadoById} cuando la consulta no produce registros, o
	 *         {@code null} si ocurre un error durante el acceso a datos
	 */
	public EmpleadoById consultarEmpleadoPorId(int id) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getEmpleadoById(?)");
			stm.setInt(1, id);
			rset = stm.executeQuery();
			if (rset.next()) {
				return new EmpleadoById.EmpleadoBuilder().idEmpleado(rset.getInt("id_empleado"))
						.idSucursal(rset.getInt("id_sucursal"))
						.rfc(rset.getString("rfc")).curp(rset.getString("curp"))
						.nombreCompleto(rset.getString("nombre_completo")).nombreCorto(rset.getString("nombre_corto"))
						.fechaNac(rset.getDate("fecha_nac")).correoElectronico(rset.getString("correo_electronico"))
						.estado(rset.getString("estado")).ciudad(rset.getString("ciudad"))
						.direccion(rset.getString("direccion")).codigoPostal(rset.getString("codigo_postal"))
						.activo(rset.getBoolean("activo")).build();
			}
			return new EmpleadoById();
		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Consulta los datos de un empleado mediante su RFC.
	 * <p>
	 * Ejecuta {@code getEmpleadoByRFC} y mapea el primer registro obtenido a
	 * {@link EmpleadoById}.
	 * </p>
	 *
	 * @param rfc RFC del empleado que se desea consultar
	 * @return empleado encontrado; devuelve una instancia vacía de
	 *         {@link EmpleadoById} cuando la consulta no produce registros, o
	 *         {@code null} si ocurre un error durante el acceso a datos
	 */
	public EmpleadoById consultarEmpleadoPorRfc(String rfc) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getEmpleadoByRFC(?)");
			stm.setString(1, rfc);
			rset = stm.executeQuery();
			if (rset.next()) {
				return new EmpleadoById.EmpleadoBuilder().idEmpleado(rset.getInt("id_empleado"))
						.idSucursal(rset.getInt("id_sucursal"))
						.rfc(rset.getString("rfc")).curp(rset.getString("curp"))
						.nombreCompleto(rset.getString("nombre_completo")).nombreCorto(rset.getString("nombre_corto"))
						.fechaNac(rset.getDate("fecha_nac")).correoElectronico(rset.getString("correo_electronico"))
						.estado(rset.getString("estado")).ciudad(rset.getString("ciudad"))
						.direccion(rset.getString("direccion")).codigoPostal(rset.getString("codigo_postal"))
						.activo(rset.getBoolean("activo")).build();
			}
			return new EmpleadoById();
		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene el listado de empleados utilizado por la tabla principal del módulo.
	 * <p>
	 * Ejecuta {@code getListadoEmpleados} aplicando el texto recibido como criterio
	 * de búsqueda por nombre y convierte cada registro en un arreglo compatible con
	 * el modelo de tabla de la vista.
	 * </p>
	 *
	 * @param nombreEmpleado texto utilizado para filtrar empleados por nombre; puede
	 *                       ser una cadena vacía para obtener el listado completo
	 * @return filas preparadas para el modelo de tabla; si ocurre un error, devuelve
	 *         la colección acumulada hasta ese momento
	 */
	public Vector<Object[]> verEmpleadosEnTabla(String nombreEmpleado) {
		ResultSet rset = null;
		CallableStatement stm = null;
		Vector<Object[]> data = new Vector<>();
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getListadoEmpleados(?)");
			stm.setString(1, nombreEmpleado);
			rset = stm.executeQuery();
			while (rset.next()) {
				data.add(new Object[] { rset.getInt("id_empleado"), rset.getString("rfc"),
						rset.getString("curp"), rset.getString("nombre_completo"), rset.getString("nombre_corto"),
						rset.getString("correo_electronico"), rset.getInt("activo") == 1 ? "Activo" : "Inactivo" });
			}
			return data;
		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
			return data;
		} catch (Exception er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
			return data;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Registra un nuevo empleado.
	 * <p>
	 * Antes de invocar {@code insert_empleado}, la contraseña contenida en el modelo
	 * se procesa mediante {@link PasswordHashService#hashIfPlain(String)}. La
	 * respuesta del procedimiento se transforma en un {@link SpResponseModel}.
	 * </p>
	 *
	 * @param empl empleado con los datos requeridos para el registro
	 * @return respuesta generada por el procedimiento almacenado; ante un error de
	 *         acceso o procesamiento devuelve una respuesta con código {@code 500}
	 */
	public SpResponseModel insertarNuevoEmpleado(Empleado empl) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			String contraseniaHash = PasswordHashService.hashIfPlain(empl.getContrasenia());

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insert_empleado(?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, empl.getIdSucursal());
			stm.setString(2, empl.getRfc());
			stm.setString(3, empl.getCurp());
			stm.setString(4, empl.getNombreCompleto());
			stm.setString(5, empl.getNombreCorto());
			stm.setDate(6, empl.getFechaNac());
			stm.setString(7, empl.getCorreoElectronico());
			stm.setString(8, empl.getEstado());
			stm.setString(9, empl.getCiudad());
			stm.setString(10, empl.getDireccion());
			stm.setString(11, empl.getCodigoPostal());
			stm.setString(12, contraseniaHash);
			rset = stm.executeQuery();
			return leerRespuestaSp(rset);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza los datos de un empleado existente.
	 * <p>
	 * Si el modelo contiene una contraseña no vacía, esta se procesa mediante
	 * {@link PasswordHashService#hashIfPlain(String)} antes de ejecutar
	 * {@code update_empleado}. Cuando no se proporciona una nueva contraseña, se
	 * envía {@code null} para conservar la contraseña almacenada según el contrato
	 * del procedimiento.
	 * </p>
	 *
	 * @param empl empleado con el identificador y los datos que se actualizarán
	 * @return respuesta generada por el procedimiento almacenado; ante un error de
	 *         acceso o procesamiento devuelve una respuesta con código {@code 500}
	 */
	public SpResponseModel actualizarEmpleado(Empleado empl) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			String contraseniaHash = null;
			if (empl.getContrasenia() != null && !empl.getContrasenia().isBlank()) {
				contraseniaHash = PasswordHashService.hashIfPlain(empl.getContrasenia());
			}

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL update_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, empl.getIdEmpleado());
			stm.setInt(2, empl.getIdSucursal());
			stm.setString(3, empl.getRfc());
			stm.setString(4, empl.getCurp());
			stm.setString(5, empl.getNombreCompleto());
			stm.setString(6, empl.getNombreCorto());
			stm.setDate(7, empl.getFechaNac());
			stm.setString(8, empl.getCorreoElectronico());
			stm.setString(9, empl.getEstado());
			stm.setString(10, empl.getCiudad());
			stm.setString(11, empl.getDireccion());
			stm.setString(12, empl.getCodigoPostal());
			stm.setString(13, contraseniaHash);
			stm.setBoolean(14, empl.isActivo());
			rset = stm.executeQuery();
			return leerRespuestaSp(rset);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Desactiva un empleado mediante su identificador.
	 * <p>
	 * Ejecuta el procedimiento almacenado {@code delete_empleado}. La eliminación
	 * es lógica y su resultado se devuelve utilizando {@link SpResponseModel}.
	 * </p>
	 *
	 * @param idEmpleado identificador del empleado que se desea desactivar
	 * @return respuesta generada por el procedimiento almacenado; ante un error de
	 *         acceso o procesamiento devuelve una respuesta con código {@code 500}
	 */
	public SpResponseModel eliminarEmpleado(int idEmpleado) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL delete_empleado(?)");
			stm.setInt(1, idEmpleado);
			rset = stm.executeQuery();
			return leerRespuestaSp(rset);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Actualiza la contraseña de un empleado.
	 * <p>
	 * La operación no se ejecuta si el empleado es {@code null} o si la contraseña
	 * no contiene datos. En caso contrario, la contraseña se procesa mediante
	 * {@link PasswordHashService#hashIfPlain(String)} y se envía al procedimiento
	 * {@code actualizarPassWordEmpleado}.
	 * </p>
	 *
	 * @param empl empleado cuyo RFC identifica el registro y cuya contraseña contiene
	 *             el nuevo valor
	 */
	public void actualizarContrasenia(Empleado empl) {
		CallableStatement stm = null;
		if (empl == null || empl.getContrasenia() == null || empl.getContrasenia().isEmpty()) {
			return;
		}
		try {
			String contraseniaHash = PasswordHashService.hashIfPlain(empl.getContrasenia());
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL actualizarPassWordEmpleado(?,?);");
			stm.setString(1, empl.getRfc());
			stm.setString(2, contraseniaHash);
			stm.execute();
		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Convierte la respuesta estándar de un procedimiento almacenado en un
	 * {@link SpResponseModel}.
	 * <p>
	 * El conjunto de resultados debe exponer las columnas {@code id} y
	 * {@code message}. Si no existe una fila disponible, se genera una respuesta de
	 * error con código {@code 500}.
	 * </p>
	 *
	 * @param rset conjunto de resultados devuelto por el procedimiento almacenado
	 * @return respuesta normalizada con identificador y mensaje
	 * @throws SQLException si ocurre un error al leer el conjunto de resultados
	 */
	private SpResponseModel leerRespuestaSp(ResultSet rset) throws SQLException {
		if (rset != null && rset.next()) {
			return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
		}
		return new SpResponseModel(500, "Sin respuesta del procedimiento almacenado");
	}

	/**
	 * Consulta un empleado mediante su nombre corto.
	 * <p>
	 * Ejecuta {@code buscar_empleado_por_nombre}. Cuando existe coincidencia, el
	 * modelo devuelto contiene el identificador y el nombre completo recuperados por
	 * el procedimiento.
	 * </p>
	 *
	 * @param nombre nombre corto del empleado que se desea buscar
	 * @return empleado encontrado; devuelve un modelo vacío si no existe
	 *         coincidencia, o {@code null} si ocurre un error
	 */
	public Empleado consultarEmpleadoPorNombre(String nombre) {
		Empleado empleado = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL buscar_empleado_por_nombre(?);");
			stm.setString(1, nombre);
			rset = stm.executeQuery();
			if (rset.next()) {
				empleado.setIdEmpleado(rset.getInt(1));
				empleado.setNombreCompleto(rset.getString(2));
			}
			return empleado;
		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Busca empleados por nombre y prepara los resultados para una vista tabular.
	 * <p>
	 * Ejecuta el procedimiento almacenado {@code buscar_empleado} y transforma cada
	 * registro en un arreglo de valores. El estado lógico se representa como
	 * {@code "Activo"} o {@code "Inactivo"}.
	 * </p>
	 *
	 * @param nombre texto utilizado como criterio de búsqueda
	 * @return filas obtenidas para presentación; si ocurre un error, devuelve la
	 *         colección acumulada hasta ese momento
	 */
	public Vector<Object[]> buscarEmpleadoPorNombre(String nombre) {
		ResultSet rset = null;
		CallableStatement stm = null;
		Vector<Object[]> data = new Vector<>();
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL buscar_empleado(?)");
			stm.setString(1, nombre);
			rset = stm.executeQuery();
			while (rset.next()) {
				data.add(new Object[] { rset.getInt(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7),
						rset.getInt(8) == 1 ? "Activo" : "Inactivo" });
			}
			return data;
		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return data;
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return data;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}
}
