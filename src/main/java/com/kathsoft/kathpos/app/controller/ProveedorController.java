package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Proveedor;

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
	public void verProveedoresEnTabla(DefaultTableModel tabla) {
		ResultSet rset = null;
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_proveedores();");
			rset = stm.executeQuery();

			while (rset.next()) {
				
				tabla.addRow(new Object[] {
						rset.getInt(1),	//Id
						rset.getString(2), // RFC
						rset.getString(3), // clave contable
						rset.getString(4), // Nombre
						rset.getString(5), // Descripcion
						rset.getString(6), // Correo Electronico
						rset.getString(7), // Estado
						rset.getString(8), // Ciudad
						rset.getString(9), // Direccion
						rset.getString(10), // Codigo Postal;
						rset.getShort(11) == 1 ? "Activo" : "Inactivo" //status
				});
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
	
	public void eliminarProveedor(int idProveedor) {
		
		CallableStatement stm = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL eliminar_proveedor(?);");
			stm.setInt(1, idProveedor);
			stm.execute();
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			}catch(SQLException er) {
				er.printStackTrace();
			}
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

	public void consultarNombresProveedor(JComboBox<String> cmb) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_nombres_proveedor();");
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

	/**
	 * inserta un nuevo registro en la base de datos
	 * 
	 * @param prv
	 * @throws Exception
	 */
	public void insertarNuevoProveedor(Proveedor prv) throws Exception, SQLException {

		// System.out.println(prv.toString());

		CallableStatement stm = null;

		if (prv.getRfc().isEmpty() || prv.getNombre().isEmpty()) {
			throw new Exception("campos vacios");
		}

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_nuevo_proveedor(?,?,?,?,?,?,?,?);");

			stm.setString(1, prv.getRfc());
			stm.setString(2, prv.getNombre());
			stm.setString(3, prv.getDescripcion());
			stm.setString(4, prv.getEmail());
			stm.setString(5, prv.getEstado());
			stm.setString(6, prv.getCiudad());
			stm.setString(7, prv.getDireccion());
			stm.setString(8, prv.getCodigoPostal());

			stm.execute();

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				Conexion.cerrarConexion(cn, null, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	public void actualizarProveedor(Proveedor prv) throws Exception, SQLException {

		CallableStatement stm = null;

		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL update_proveedor(?,?,?,?,?,?,?,?);");

			stm.setInt(1, prv.getId());
			stm.setString(2, prv.getNombre());
			stm.setString(3, prv.getDescripcion());
			stm.setString(4, prv.getEmail());
			stm.setString(5, prv.getEstado());
			stm.setString(6, prv.getCiudad());
			stm.setString(7, prv.getDireccion());
			stm.setString(8, prv.getCodigoPostal());

			stm.execute();
		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
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
				prv.setId(rset.getInt(1));
				prv.setIdCuentaContable(rset.getInt(2));
				prv.setClaveCuentaContable(rset.getString(3));
				prv.setNombre(rset.getString(4));
				prv.setDescripcion(rset.getString(5));
				prv.setEmail(rset.getString(6));
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
				prv.setId(rset.getInt(1));
				prv.setRfc(rset.getString(2));
				prv.setIdCuentaContable(rset.getInt(3));
				prv.setClaveCuentaContable(rset.getString(4));
				prv.setNombre(rset.getString(5));
				prv.setDescripcion(rset.getString(6));
				prv.setEmail(rset.getString(7));
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