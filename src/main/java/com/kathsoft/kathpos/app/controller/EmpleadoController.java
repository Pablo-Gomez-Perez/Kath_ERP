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

import com.kathsoft.kathpos.app.model.Empleado;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class EmpleadoController implements Serializable {

	private static final long serialVersionUID = -1598821464656008533L;
	private static Connection cn = null;

	public EmpleadoController() {
	}

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

	public Empleado consultarEmpleadoPorId(int id) {
		Empleado empl = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getEmpleadoById(?)");
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

	public Empleado consultarEmpleadoPorRfc(String rfc) {
		Empleado empl = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getEmpleadoByRFC(?)");
			stm.setString(1, rfc);
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
	
	public SpResponseModel insertarNuevoEmpleado(Empleado empl) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
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

	public SpResponseModel actualizarEmpleado(Empleado empl) {
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
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

	public void actualizarContrasenia(Empleado empl) {
		CallableStatement stm = null;
		if (empl == null || empl.getPassword() == null || empl.getPassword().isEmpty()) {
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

	private SpResponseModel leerRespuestaSp(ResultSet rset) throws SQLException {
		if (rset != null && rset.next()) {
			return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
		}
		return new SpResponseModel(500, "Sin respuesta del procedimiento almacenado");
	}

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
				empleado.setId(rset.getInt(1));
				empleado.setNombre(rset.getString(2));
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
