package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Clientes;
import com.kathsoft.kathpos.tools.Conexion;

public class ClientesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Connection cn = null;

	public void verClientesEnTabla(String nombre ,DefaultTableModel tabla) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_clientes(?);");
			stm.setString(1, nombre);
			rset = stm.executeQuery();
			
			while (rset.next()) {

				tabla.addRow(new Object[] { rset.getInt(1), // indice
						rset.getString(2), // rfc
						rset.getString(3), //tipo de cliente
						rset.getString(4), // cuenta contable
						rset.getString(5), // nombre completo
						rset.getString(6), // nombre corto
						rset.getString(7), // correo electronico
						rset.getString(8), // estado
						rset.getString(9), // ciudad
						rset.getString(10), // direccion
						rset.getString(11), // codigo postal
						rset.getShort(12) == 1 ? "Activo" : "Inactivo" // status
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}
	
	/*public void buscarClintePorNombre(String nombre,DefaultTableModel tabla) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_cliente_por_nombre(?);");
			stm.setString(1, nombre);
			rset = stm.executeQuery();

			while (rset.next()) {

				tabla.addRow(new Object[] { rset.getInt(1), // indice
						rset.getString(2), // rfc
						rset.getString(3), //Tipo de cliente
						rset.getString(4), // cuenta contable
						rset.getString(5), // nombre completo
						rset.getString(6), // nombre corto
						rset.getString(7), // correo electronico
						rset.getString(8), // estado
						rset.getString(9), // ciudad
						rset.getString(10), // direccion
						rset.getString(11), // codigo postal
						rset.getShort(12) == 1 ? "Activo" : "Inactivo" // status
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}*/
	

	public void eliminarCliente(int idCliente) throws SQLException {

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal("kath_erp");
		stm = cn.prepareCall("CALL eliminar_cliente(?);");
		stm.setInt(1, idCliente);

		stm.execute();

		Conexion.cerrarConexion(cn, stm);

	}

	public void consultarRFCClientes(JComboBox<String> cmb) throws SQLException, Exception {
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_rfc_clientes();");
			rset = stm.executeQuery();

			while (rset.next()) {
				cmb.addItem(rset.getString(2));
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

	public Clientes buscarClientePorRFC(String rfc) throws SQLException, Exception {

		Clientes cl = new Clientes();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_cliente_por_rfc(?);");
			stm.setString(1, rfc);

			rset = stm.executeQuery();

			if (rset.next()) {
				cl.setId(rset.getInt(1));
				cl.setRfc(rset.getString(2));
				cl.setClaveCuentaContable(rset.getString(3));
				cl.setDescripcion(rset.getString(4));
				cl.setNombre(rset.getString(5));
				cl.setNombreCorto(rset.getString(6));
				cl.setFechaNacimiento(rset.getDate(7));
				cl.setEmail(rset.getString(8));
				cl.setEstado(rset.getString(9));
				cl.setCiudad(rset.getString(10));
				cl.setDireccion(rset.getString(11));
				cl.setCodigoPostal(rset.getString(12));
			}

			return cl;
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	public Clientes buscarClientePorId(int idCliente) {

		Clientes cl = new Clientes();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_cliente_por_id(?);");
			stm.setInt(1, idCliente);

			rset = stm.executeQuery();

			if (rset.next()) {
				cl.setId(rset.getInt(1));
				cl.setRfc(rset.getString(2));
				cl.setClaveCuentaContable(rset.getString(3));
				cl.setDescripcion(rset.getString(4));
				cl.setNombre(rset.getString(5));
				cl.setNombreCorto(rset.getString(6));
				cl.setFechaNacimiento(rset.getDate(7));
				cl.setEmail(rset.getString(8));
				cl.setEstado(rset.getString(9));
				cl.setCiudad(rset.getString(10));
				cl.setDireccion(rset.getString(11));
				cl.setCodigoPostal(rset.getString(12));
			}

			return cl;
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public void insertarNuevoCliente(Clientes cl) throws SQLException, Exception {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_nuevo_cliente(?,?,?,?,?,?,?,?,?,?,?);");

			stm.setString(1, cl.getRfc());
			stm.setInt(2, cl.getIdTipoCliente());
			stm.setString(3, cl.getNombre());
			stm.setString(4, cl.getNombreCorto());
			stm.setString(5, cl.getDescripcion());
			stm.setDate(6, cl.getFechaNacimiento());
			stm.setString(7, cl.getEmail());
			stm.setString(8, cl.getEstado());
			stm.setString(9, cl.getCiudad());
			stm.setString(10, cl.getDireccion());
			stm.setString(11, cl.getCodigoPostal());

			stm.executeUpdate();

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [Generic] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
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

	public void actualizarCliente(Clientes cl) throws SQLException, Exception {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL update_cliente(?,?,?,?,?,?,?,?,?,?,?,?);");

			stm.setInt(1, cl.getId());
			stm.setInt(2, cl.getIdTipoCliente());
			stm.setString(3, cl.getClaveCuentaContable());
			stm.setString(4, cl.getNombre());
			stm.setString(5, cl.getNombreCorto());
			stm.setString(6, cl.getDescripcion());
			stm.setDate(7, cl.getFechaNacimiento());
			stm.setString(8, cl.getEmail());
			stm.setString(9, cl.getEstado());
			stm.setString(10, cl.getCiudad());
			stm.setString(11, cl.getDireccion());
			stm.setString(12, cl.getCodigoPostal());

			stm.executeUpdate();

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

}
