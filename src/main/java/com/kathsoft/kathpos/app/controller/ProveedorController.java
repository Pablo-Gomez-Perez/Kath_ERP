package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.proveedor.Proveedor;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class ProveedorController implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5820147766336769662L;
	/**
	 * 
	 * 
	 * 
	 */
	private static Connection cn = null;

	/**
	 * 
	 * @param tabla
	 */
	public Vector<Object[]> verProveedoresEnTabla(String nombre) {

		var data = new Vector<Object[]>();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listProveedores(?);")
		) {

			stm.setString("p_nombre_proveedor", nombre);

			try (ResultSet rset = stm.executeQuery()) {

				while (rset.next()) {
					data.add(new Object[] {
							rset.getInt("id_proveedor"),
							rset.getString("rfc"),
							rset.getString("clave"),
							rset.getString("nombre"),
							rset.getString("descripcion"),
							rset.getString("correo_electronico"),
							rset.getString("estado"),
							rset.getString("ciudad"),
							rset.getString("direccion"),
							rset.getString("codigo_postal"),
							rset.getShort("activo") == 1 ? "Activo" : "Inactivo"
					});
				}

			}

		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}

		return data;
	}	

	public SpResponseModel eliminarProveedor(int idProveedor) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL deleteProveedor(?);")
		) {

			stm.setInt("p_id_proveedor", idProveedor);

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}

	}

	/**
	 * consulta la tabla de proveedores en la base de datos el listado completo de
	 * proveedores registrados y extrae el RFC correspondiente agregandolo al
	 * ComboBox que se le pasa como parámetro
	 * 
	 * @param cmb
	 */
	public void consultarRFCProveedor(JComboBox<String> cmb) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_rfcProveedores();");
			rset = stm.executeQuery();

			while (rset.next()) {
				cmb.addItem(rset.getString(1));
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public Vector<Proveedor> consultarNombresProveedor() {
		
		var data = new Vector<Proveedor>();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_nombres_proveedor();");
			rset = stm.executeQuery();

			while (rset.next()) {
				var prov = new Proveedor();
				prov.setIdProveedor(rset.getInt(1));
				prov.setNombre(rset.getString(2));
				data.add(prov);
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	/**
	 * inserta un nuevo registro en la base de datos
	 * 
	 * @param prv
	 * @throws Exception
	 */
	public SpResponseModel insertarNuevoProveedor(Proveedor prv) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL insertProveedor(?, ?, ?, ?, ?, ?, ?, ?, ?);")
		) {

			stm.setInt("p_id_cuenta_contable", prv.getIdCuentaContable());
			stm.setString("p_rfc", prv.getRfc());
			stm.setString("p_nombre", prv.getNombre());
			stm.setString("p_descripcion", prv.getDescripcion());
			stm.setString("p_correo_electronico", prv.getCorreoElectronico());
			stm.setString("p_estado", prv.getEstado());
			stm.setString("p_ciudad", prv.getCiudad());
			stm.setString("p_direccion", prv.getDireccion());
			stm.setString("p_codigo_postal", prv.getCodigoPostal());

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}

	}

	public SpResponseModel actualizarProveedor(Proveedor prv) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL updateProveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")
		) {

			stm.setInt("p_id_proveedor", prv.getIdProveedor());
			stm.setInt("p_id_cuenta_contable", prv.getIdCuentaContable());
			stm.setString("p_rfc", prv.getRfc());
			stm.setString("p_nombre", prv.getNombre());
			stm.setString("p_descripcion", prv.getDescripcion());
			stm.setString("p_correo_electronico", prv.getCorreoElectronico());
			stm.setString("p_estado", prv.getEstado());
			stm.setString("p_ciudad", prv.getCiudad());
			stm.setString("p_direccion", prv.getDireccion());
			stm.setString("p_codigo_postal", prv.getCodigoPostal());
			stm.setBoolean("p_activo", prv.isActivo());

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}

	}

	/**
	 * 
	 * @param rfc
	 * @throws Exception
	 * @throws SQLException
	 */
	public Proveedor buscarProveedorPorRFC(String rfc) throws Exception, SQLException {

		Proveedor prv = new Proveedor();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_proveedor_por_rfc(?);");
			stm.setString(1, rfc);

			rset = stm.executeQuery();

			if (rset.next()) {
				prv.setIdProveedor(rset.getInt(1));
				prv.setIdCuentaContable(rset.getInt(2));				
				prv.setNombre(rset.getString(4));
				prv.setDescripcion(rset.getString(5));
				prv.setCorreoElectronico(rset.getString(6));
				prv.setEstado(rset.getString(7));
				prv.setCiudad(rset.getString(8));
				prv.setDireccion(rset.getString(9));
				prv.setCodigoPostal(rset.getString(10));
			}

			return prv;

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			try {

				Conexion.cerrarConexion(cn, rset, stm);

			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public Proveedor buscarProveedorPorId(int idProveedor) {

		Proveedor prv = new Proveedor();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_proveedor_por_id(?);");
			stm.setInt(1, idProveedor);

			rset = stm.executeQuery();

			if (rset.next()) {
				prv.setIdProveedor(rset.getInt(1));
				prv.setRfc(rset.getString(2));
				prv.setIdCuentaContable(rset.getInt(3));				
				prv.setNombre(rset.getString(5));
				prv.setDescripcion(rset.getString(6));
				prv.setCorreoElectronico(rset.getString(7));
				prv.setEstado(rset.getString(8));
				prv.setCiudad(rset.getString(9));
				prv.setDireccion(rset.getString(10));
				prv.setCodigoPostal(rset.getString(11));
			}

			return prv;

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			try {

				Conexion.cerrarConexion(cn, rset, stm);

			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}
}