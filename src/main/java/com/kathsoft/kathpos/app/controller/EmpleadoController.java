package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Empleado;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.tools.Conexion;

import java.sql.CallableStatement;
public class EmpleadoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1598821464656008533L;
	/**
	 * 
	 * 
	 */

	// private Empleado empleado;
	private static Connection cn = null;
	/*
	 * public EmpleadoController(Empleado empleado) { this.empleado = empleado; }
	 */

	public EmpleadoController() {

	}

	/**
	 * @return the empleado
	 *
	 *         public Empleado getEmpleado() { return empleado; }
	 * 
	 *         /**
	 * @param empleado the empleado to set
	 *
	 *                 public void setEmpleado(Empleado empleado) { this.empleado =
	 *                 empleado; }
	 */
	/**
	 * Metodo para validar el ingreso al sistema.
	 * <p>
	 * El acceso solo es valido cuando el procedimiento almacenado retorna una fila
	 * con un resultado significativo. Si no hay fila, si el resultado viene vacio,
	 * o si el resultado representa un valor falso, el acceso se rechaza.
	 *
	 * @param empl empleado con nombre corto y contrasenia capturados desde la vista
	 * @return {@code true} si las credenciales son validas; {@code false} en caso
	 *         contrario
	 */
	public boolean validarIngreso(Empleado empl) {

		CallableStatement stm = null;
		ResultSet rset = null;

		if (empl == null || empl.getNombreCorto() == null || empl.getNombreCorto().isBlank()
				|| empl.getPassword() == null || empl.getPassword().isBlank()) {
			return false;
		}

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL validar_entrada(?,?)");
			stm.setString(1, empl.getNombreCorto());
			stm.setString(2, empl.getPassword());
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

	// CODEX.TODO: El método debe retornar un List<JComboboxDataViewModel> y ser invocado desde la vista. ajustar esta misma mecánica en las demas clases donde este método sea invocado.
	/**
	 * Consulta una vista almacenada en la base de datos que retorna unicamente el
	 * campo nombre_corto de la tabla empleados, y estos datos son cargados al
	 * combobox que se le pase como parámetro
	 * 
	 * @param jcmb
	 */
	public void consultaNombresCortosEmpleados(JComboBox<JComboboxDataViewModel> jcmb, int id_sucursal) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_rfc_empleado_por_sucursal(?)");
			stm.setInt(1, id_sucursal);

			rset = stm.executeQuery();

			while (rset.next()) {
				jcmb.addItem(new JComboboxDataViewModel(rset.getInt(1), rset.getString(2)));
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
	 * consulta una vista almacenada en la base de datos que retorna unicamente el
	 * campo RFC de la tabla de mpleados, estos datos son cargados al combobox que
	 * se le pasa como parámetro
	 * 
	 * @param jcmb
	 */
	public void consultarRfcEmpleado(JComboBox<String> jcmb) {

		Statement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
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
	
	//CODEX.TODO: Ajustar este método para mapear la respuesta del procedimiento almacenado getEmpleadoById, este método debe retornar un objeto EmpleadoDTO o equivalente. para información en formularios.
	public Empleado consultarEmpleadoPorId(int id) {
		Empleado empl = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;
		try {

			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL buscar_empleado_por_id(?)");
			stm.setInt(1, id);

			rset = stm.executeQuery();

			if (rset.next()) {
				empl.setId(rset.getInt("id_empleado"));
				empl.setIdCuentaContable(rset.getInt("id_cuenta_contable"));
				empl.setIdSucursal(rset.getInt("id_sucursal"));
				empl.setRfc(rset.getString("rfc"));
				empl.setCurp(rset.getString("curp"));
				empl.setNombre(rset.getString("nombre_completo"));
				empl.setNombreCorto(rset.getString("nombre_corto"));
				empl.setFechaNacimiento(rset.getDate("fecha_nac"));
				empl.setEmail(rset.getString("correo_electronico"));
				empl.setEstado(rset.getString("estado"));
				empl.setCiudad(rset.getString("ciudad"));
				empl.setDireccion(rset.getString("direccion"));
				empl.setCodigoPostal(rset.getString("codigo_postal"));
				empl.setPassword(rset.getString("contrasenia"));
			}

			return empl;

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

	//CODEX.TODO: Mapear la respuesta del procedimiento almacenado getEmpleadoByRFC() y retornar un objeto DTO.
	public Empleado consultarEmpleadoPorRfc(String rfc) {
		Empleado empl = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;
		try {

			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL sp_consultarEmpleadoPorRFC(?)");
			stm.setString(1, rfc);

			rset = stm.executeQuery();

			if (rset.next()) {
				empl.setCurp(rset.getString(1));
				empl.setNombre(rset.getString(2));
				empl.setNombreCorto(rset.getString(3));
				empl.setFechaNacimiento(rset.getDate(4));
				empl.setEmail(rset.getString(5));
				empl.setEstado(rset.getString(6));
				empl.setCiudad(rset.getString(7));
				empl.setDireccion(rset.getString(8));
				empl.setCodigoPostal(rset.getString(9));
				empl.setPassword(rset.getString(10));
			}
			return empl;
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
	
	//CODEX.TODO: Este método debe retornar un Vector<Object[]> y ser invocado desde la vista para llenar la tabla. debe de mapear la respuesta del sp getListadoEmpleados(VARCHAR nombre)
	public void verEmpleadosEnTabla(DefaultTableModel tabla) {
		ResultSet rset = null;
		CallableStatement stm = null;

		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL spGetListadoEmpleados");
			rset = stm.executeQuery();

			while (rset.next()) {
				Object[] fila = { rset.getInt(1), // id
						rset.getString(2), rset.getString(3), // rfc
						rset.getString(4), // curp
						rset.getString(5), // nombre completo
						rset.getString(6), // nombre corto
						rset.getString(7), // correo
						rset.getInt(8) == 1 ? "Activo" : "Inactivo" // activo
				};
				tabla.addRow(fila);
			}
		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
		} catch (Exception er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}
	
	// CODEX.TODO: Este tipo de métodos retornan un objeto de tipo SpResponseModel, y es mostrado en la vista una vez ejecutada la acción. (JOptionPane.showMessageDialog)
	/**
	 * inserta un nuevo empleado en la bd recibe como paramatetro un objeto de tipo
	 * {@code Empleado} e inserta las datos en la bd como nuevo registro
	 * 
	 * @param empl
	 */
	public void insertarNuevoEmpleado(Empleado empl) {
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, empl.getIdCuentaContable());
			stm.setInt(2, empl.getIdSucursal());
			stm.setString(3, empl.getRfc());
			stm.setString(4, empl.getCurp());
			stm.setString(5, empl.getNombre());
			stm.setString(6, empl.getNombreCorto());
			stm.setDate(7, empl.getFechaNacimiento());
			stm.setString(8, empl.getEmail());
			stm.setString(9, empl.getEstado());
			stm.setString(10, empl.getCiudad());
			stm.setString(11, empl.getDireccion());
			stm.setString(12, empl.getCodigoPostal());
			stm.setString(13, empl.getPassword());
			stm.execute();
						

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), er.toString(), JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), er.toString(), JOptionPane.ERROR_MESSAGE);
		} finally {

			try {

				Conexion.cerrarConexion(cn, stm);

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}
	
	// CODEX.TODO: Este tipo de métodos retornan un objeto de tipo SpResponseModel, y es mostrado en la vista una vez ejecutada la acción. (JOptionPane.showMessageDialog)
	/**
	 * actualiza los datos de un empleado específico en la bd recibe como parametro
	 * un objeto de tipo {@code Empleado} y actualiza los respectivos valores
	 * modificados en la bd
	 * 
	 * @param empl
	 */
	public void actualizarEmpleado(Empleado empl) {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL update_empleado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			stm.setInt(1, empl.getId());
			stm.setInt(2, empl.getIdCuentaContable());
			stm.setInt(3, empl.getIdSucursal());
			stm.setString(4, empl.getRfc());
			stm.setString(5, empl.getCurp());
			stm.setString(6, empl.getNombre());
			stm.setString(7, empl.getNombreCorto());
			stm.setDate(8, empl.getFechaNacimiento());
			stm.setString(9, empl.getEmail());
			stm.setString(10, empl.getEstado());
			stm.setString(11, empl.getCiudad());
			stm.setString(12, empl.getDireccion());
			stm.setString(13, empl.getCodigoPostal());
			stm.setString(14, empl.getPassword());
			stm.setBoolean(15, true);
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
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}
	
	// CODEX.TODO: Este tipo de métodos retornan un objeto de tipo SpResponseModel, y es mostrado en la vista una vez ejecutada la acción. (JOptionPane.showMessageDialog)
	public void eliminarEmpleado(int idEmpleado) throws SQLException {

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal("kath_erp");
		stm = cn.prepareCall("CALL delete_empleado(?)");
		stm.setInt(1, idEmpleado);

		stm.execute();

		Conexion.cerrarConexion(cn, stm);

	}
	
	// CODEX.TODO: Este método deja de er necesario dado de que la contraseña se actualiza desde el método actualizar empleado.
	public void actualizarContrasenia(Empleado empl) {
		CallableStatement stm = null;

		if (empl.getPassword() == null || empl.getPassword().equals("") || empl.getPassword().length() < 1) {
			return;
		}

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL actualizarPassWordEmpleado(?,?);");
			stm.setString(1, empl.getRfc());
			stm.setString(2, empl.getPassword());
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
	
	
	public Empleado consultarEmpleadoPorNombre(String nombre) {

		// System.out.println("En el controlador: " + nombre);

		Empleado empleado = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_empleado_por_nombre(?);");
			stm.setString(1, nombre);
			rset = stm.executeQuery();

			if (rset.next()) {
				empleado.setId(rset.getInt(1));
				empleado.setNombre(rset.getString(2));
			}

			// System.out.println(empleado.toString());

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
	
	// CODEX.TODO: Este método deja de ser necesario. 
	public void buscarEmpleadoPorNombre(String nombre, DefaultTableModel tabla) {
		ResultSet rset = null;
		CallableStatement stm = null;

		try {
			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL buscar_empleado(?)");
			stm.setString(1, nombre);
			rset = stm.executeQuery();

			while (rset.next()) {
				Object[] fila = { rset.getInt(1), // id
						rset.getString(2), // nombre
						rset.getString(3), // rfc
						rset.getString(4), // curp
						rset.getString(5), // nombre completo
						rset.getString(6), // nombre corto
						rset.getString(7), // correo
						rset.getInt(8) == 1 ? "Activo" : "Inactivo" // activo
				};
				tabla.addRow(fila);
			}
		} catch (SQLException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
		} catch (Exception er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

}
