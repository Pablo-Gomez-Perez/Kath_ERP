package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class EmpleadoController implements Serializable {

	private static final long serialVersionUID = -1598821464656008533L;
	private static Connection cn = null;

	public EmpleadoController() {
	}

	/**
	 * Valida el acceso de un empleado contra el procedimiento almacenado de
	 * autenticación.
	 *
	 * @param empl empleado con nombre corto y contraseña
	 * @return {@code true} si las credenciales son válidas; {@code false} en caso
	 *         contrario
	 */
	public boolean validarIngreso(Empleado empl) {

		CallableStatement stm = null;
		ResultSet rset = null;

		if (empl == null || empl.getNombreCorto() == null || empl.getNombreCorto().isBlank()
				|| empl.getContrasenia() == null || empl.getContrasenia().isBlank()) {
			return false;
		}

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL validar_entrada(?,?)");
			stm.setString(1, empl.getNombreCorto());
			stm.setString(2, empl.getContrasenia());
			rset = stm.executeQuery();

			if (!rset.next()) {
				return false;
			}

			String resultado = rset.getString(1);
			if (resultado == null) {
				return false;
			}
			resultado = resultado.trim();
			return !resultado.isEmpty() && !"0".equals(resultado) && !"false".equalsIgnoreCase(resultado);

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Obtiene los empleados de una sucursal para alimentar controles tipo combo.
	 *
	 * @param id_sucursal identificador de la sucursal
	 * @return lista de elementos id-texto para la UI
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
	 * Carga en un combo box los RFC disponibles desde la vista de empleados.
	 *
	 * @param jcmb combo a poblar
	 */
	public void consultarRfcEmpleado(JComboBox<String> jcmb) {
		Statement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.createStatement();
			rset = stm.executeQuery("SELECT * FROM vw_rfcempleados");
			while (rset.next()) {
				jcmb.addItem(rset.getString(1));
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
	 * Busca un empleado por su identificador y devuelve su detalle completo.
	 *
	 * @param id identificador del empleado
	 * @return modelo con el detalle del empleado, o un objeto vacío si no existe
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
				return new EmpleadoById.EmpleadoBuilder()
						.idEmpleado(rset.getInt("id_empleado"))
						.idCuentaContable(rset.getInt("id_cuenta_contable"))
						.claveCuentaContable(rset.getString("clave"))
						.idSucursal(rset.getInt("id_sucursal"))
						.rfc(rset.getString("rfc"))
						.curp(rset.getString("curp"))
						.nombreCompleto(rset.getString("nombre_completo"))
						.nombreCorto(rset.getString("nombre_corto"))
						.fechaNac(rset.getDate("fecha_nac"))
						.correoElectronico(rset.getString("correo_electronico"))
						.estado(rset.getString("estado"))
						.ciudad(rset.getString("ciudad"))
						.direccion(rset.getString("direccion"))
						.codigoPostal(rset.getString("codigo_postal"))
						.activo(rset.getBoolean("activo"))
						.build();
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
	 * Busca un empleado por RFC y devuelve su detalle completo.
	 *
	 * @param rfc RFC del empleado
	 * @return modelo con el detalle del empleado, o un objeto vacío si no existe
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
				return new EmpleadoById.EmpleadoBuilder()
						.idEmpleado(rset.getInt("id_empleado"))
						.idCuentaContable(rset.getInt("id_cuenta_contable"))
						.claveCuentaContable(rset.getString("clave"))
						.idSucursal(rset.getInt("id_sucursal"))
						.rfc(rset.getString("rfc"))
						.curp(rset.getString("curp"))
						.nombreCompleto(rset.getString("nombre_completo"))
						.nombreCorto(rset.getString("nombre_corto"))
						.fechaNac(rset.getDate("fecha_nac"))
						.correoElectronico(rset.getString("correo_electronico"))
						.estado(rset.getString("estado"))
						.ciudad(rset.getString("ciudad"))
						.direccion(rset.getString("direccion"))
						.codigoPostal(rset.getString("codigo_postal"))
						.activo(rset.getBoolean("activo"))
						.build();
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
	 * Obtiene el listado de empleados para mostrarlo en una tabla.
	 *
	 * @param nombreEmpleado filtro por nombre
	 * @return filas listas para enlazarse a una tabla
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
				data.add(new Object[] { rset.getInt("id_empleado"), rset.getString("clave"), rset.getString("rfc"), rset.getString("curp"),
					rset.getString("nombre_completo"), rset.getString("nombre_corto"), rset.getString("correo_electronico"),
					rset.getInt("activo") == 1 ? "Activo" : "Inactivo" });
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
	 * Inserta un nuevo empleado mediante el procedimiento almacenado.
	 *
	 * @param empl datos del empleado a registrar
	 * @return respuesta estándar del procedimiento almacenado
	 */
	public SpResponseModel insertarNuevoEmpleado(Empleado empl) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insert_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, empl.getIdCuentaContable());
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
			stm.setString(13, empl.getContrasenia());
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
	 *
	 * @param empl datos actualizados del empleado
	 * @return respuesta estándar del procedimiento almacenado
	 */
	public SpResponseModel actualizarEmpleado(Empleado empl) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL update_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, empl.getIdEmpleado());
			stm.setInt(2, empl.getIdCuentaContable());
			stm.setInt(3, empl.getIdSucursal());
			stm.setString(4, empl.getRfc());
			stm.setString(5, empl.getCurp());
			stm.setString(6, empl.getNombreCompleto());
			stm.setString(7, empl.getNombreCorto());
			stm.setDate(8, empl.getFechaNac());
			stm.setString(9, empl.getCorreoElectronico());
			stm.setString(10, empl.getEstado());
			stm.setString(11, empl.getCiudad());
			stm.setString(12, empl.getDireccion());
			stm.setString(13, empl.getCodigoPostal());
			stm.setString(14, empl.getContrasenia());
			stm.setBoolean(15, true);
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
	 * Elimina un empleado por su identificador.
	 *
	 * @param idEmpleado identificador del empleado
	 * @return respuesta estándar del procedimiento almacenado
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
	 * Actualiza la contraseña de un empleado a partir de su RFC.
	 *
	 * @param empl empleado con RFC y nueva contraseña
	 */
	public void actualizarContrasenia(Empleado empl) {
		CallableStatement stm = null;
		if (empl == null || empl.getContrasenia() == null || empl.getContrasenia().isEmpty()) {
			return;
		}
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL actualizarPassWordEmpleado(?,?);");
			stm.setString(1, empl.getRfc());
			stm.setString(2, empl.getContrasenia());
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
	 * Convierte la primera fila de respuesta del procedimiento en un modelo de
	 * salida.
	 *
	 * @param rset resultado devuelto por el procedimiento almacenado
	 * @return respuesta tipada o un error genérico si no hay datos
	 * @throws SQLException si ocurre un error al leer el resultado
	 */
	private SpResponseModel leerRespuestaSp(ResultSet rset) throws SQLException {
		if (rset != null && rset.next()) {
			return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
		}
		return new SpResponseModel(500, "Sin respuesta del procedimiento almacenado");
	}

	/**
	 * Busca un empleado por nombre y devuelve una coincidencia simple.
	 *
	 * @param nombre nombre a buscar
	 * @return empleado con datos básicos, o {@code null} si falla la consulta
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
	 * Busca empleados por nombre para uso en resultados tabulares.
	 *
	 * @param nombre texto de búsqueda
	 * @return filas con el resultado de la consulta
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
