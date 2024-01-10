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
	 * metodo para validar el ingreso al sistema, hace uso de un procedimiento
	 * almacenado para validar si el usuario existe y sis sus credenciales de acceso
	 * son correctas
	 * 
	 * @return {@code false} si el server arroja un error
	 * @return {@code true} si el server no arroja nada
	 */
	public boolean validarIngreso(Empleado empl) {

		CallableStatement stm = null;
		ResultSet rset = null;

		String pswd = "";		

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL validar_entrada(?,?)");
			stm.setString(1, empl.getNombreCorto());
			stm.setString(2, empl.getPassword());
			rset = stm.executeQuery();

			if (rset.next()) {
				pswd = rset.getString(1);
				System.out.println(pswd);
			}
			
			return true;

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
	 * Consulta una vista almacenada en la base de datos que retorna unicamente el
	 * campo nombre_corto de la tabla empleados, y estos datos son cargados al
	 * combobox que se le pase como parámetro
	 * 
	 * @param jcmb
	 */
	public void consultaNombresCortosEmpleados(JComboBox<String> jcmb, int id_sucursal) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_rfc_empleado_por_sucursal(?)");
			stm.setInt(1, id_sucursal);

			rset = stm.executeQuery();

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
				empl.setId(rset.getInt(1));
				empl.setIdSucursal(rset.getInt(2));
				empl.setRfc(rset.getString(3));
				empl.setCurp(rset.getString(4));
				empl.setNombre(rset.getString(5));
				empl.setNombreCorto(rset.getString(6));
				empl.setFechaNacimiento(rset.getDate(7));
				empl.setEmail(rset.getString(8));
				empl.setEstado(rset.getString(9));
				empl.setCiudad(rset.getString(10));
				empl.setDireccion(rset.getString(11));
				empl.setCodigoPostal(rset.getString(12));
				empl.setPassword(rset.getString(13));
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

	public void verEmpleadosEnTabla(DefaultTableModel tabla) {
		ResultSet rset = null;
		CallableStatement stm = null;

		try {
			cn = Conexion.establecerConexionLocal("Kath_erp");
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

	/**
	 * inserta un nuevo empleado en la bd recibe como paramatetro un objeto de tipo
	 * {@code Empleado} e inserta las datos en la bd como nuevo registro
	 * 
	 * @param empl
	 */
	public void insertarNuevoEmpleado(Empleado empl) {
		CallableStatement stm = null;

		if (empl.getRfc().isEmpty() || empl.getCurp().isEmpty() || empl.getNombre().isEmpty()
				|| empl.getNombreCorto().isEmpty() || empl.getFechaNacimiento().equals(null)
				|| empl.getEmail().isEmpty() || empl.getEstado().isEmpty() || empl.getCiudad().isEmpty()
				|| empl.getDireccion().isEmpty() || empl.getCodigoPostal().isEmpty()) {

			return;

		}

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_nuevo_empleado(?,?,?,?,?,?,?,?,?,?,?);");
			stm.setInt(1, empl.getIdSucursal());
			stm.setString(2, empl.getRfc());
			stm.setString(3, empl.getCurp());
			stm.setString(4, empl.getNombre());
			stm.setString(5, empl.getNombreCorto());
			stm.setDate(6, empl.getFechaNacimiento());
			stm.setString(7, empl.getEmail());
			stm.setString(8, empl.getEstado());
			stm.setString(9, empl.getCiudad());
			stm.setString(10, empl.getDireccion());
			stm.setString(11, empl.getCodigoPostal());
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

	/**
	 * actualiza los datos de un empleado específico en la bd recibe como parametro
	 * un objeto de tipo {@code Empleado} y actualiza los respectivos valores
	 * modificados en la bd
	 * 
	 * @param empl
	 */
	public void actualizarEmpleado(Empleado empl) {

		CallableStatement stm = null;

		if (empl.getRfc().isEmpty() || empl.getNombre().isEmpty() || empl.getNombreCorto().isEmpty()
				|| empl.getFechaNacimiento().equals(null) || empl.getEmail().isEmpty() || empl.getEstado().isEmpty()
				|| empl.getCiudad().isEmpty() || empl.getDireccion().isEmpty() || empl.getCodigoPostal().isEmpty()) {

			return;

		}

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL update_empleado(?,?,?,?,?,?,?,?,?,?);");
			stm.setInt(1, empl.getIdSucursal());
			stm.setString(2, empl.getRfc());
			stm.setString(3, empl.getNombre());
			stm.setString(4, empl.getNombreCorto());
			stm.setDate(5, empl.getFechaNacimiento());
			stm.setString(6, empl.getEmail());
			stm.setString(7, empl.getEstado());
			stm.setString(8, empl.getCiudad());
			stm.setString(9, empl.getDireccion());
			stm.setString(10, empl.getCodigoPostal());
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

	public void eliminarEmpleado(int idEmpleado) throws SQLException{

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal("kath_erp");
		stm = cn.prepareCall("CALL eliminar_empleado(?)");
		stm.setInt(1, idEmpleado);

		stm.execute();

		Conexion.cerrarConexion(cn, stm);

	}

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
						rset.getString(2), //nombre 
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
