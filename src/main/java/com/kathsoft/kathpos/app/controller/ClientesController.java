package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;

import com.kathsoft.kathpos.app.model.cliente.ClienteById;
import com.kathsoft.kathpos.app.model.cliente.Clientes;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class ClientesController implements Serializable {

	private static final long serialVersionUID = 1L;
	private Connection cn = null;

	public Vector<Object[]> verClientesEnTabla(String nombre) {

		CallableStatement stm = null;
		ResultSet rset = null;
		var data = new Vector<Object[]>();

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL listClientes(?);");
			stm.setString("nombre_c", nombre);
			rset = stm.executeQuery();

			while (rset.next()) {

				data.add(new Object[] { rset.getInt("id_cliente"), rset.getString("rfc"),
						rset.getString("nombre"), rset.getString("clave"), rset.getString("nombre_completo"),
						rset.getString("nombre_corto"), rset.getString("correo_electronico"),
						rset.getShort("activo") == 1 ? "Activo" : "Inactivo" });

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

	public SpResponseModel eliminarCliente(int idCliente) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL deleteCliente(?);");
			stm.setInt("p_id_cliente", idCliente);

			if (stm.execute()) {
				rset = stm.getResultSet();
				if (rset != null && rset.next()) {
					return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
				}
			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
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
				cl.setIdCliente(rset.getInt(1));
				cl.setRfc(rset.getString(2));
				cl.setIdCuentaContable(rset.getInt(3));
				cl.setClaveCuentaContable(rset.getString(3));
				cl.setNombreCompleto(rset.getString(4));
				cl.setNombreCorto(rset.getString(5));
				cl.setFechaNac(rset.getDate(6));
				cl.setCorreoElectronico(rset.getString(7));
				cl.setEstado(rset.getString(8));
				cl.setCiudad(rset.getString(9));
				cl.setDireccion(rset.getString(10));
				cl.setCodigoPostal(rset.getString(11));
				cl.setActivo(true);
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

	public ClienteById buscarClientePorId(int idCliente) {

		ClienteById cl = new ClienteById();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL getClienteById(?);");
			stm.setInt(1, idCliente);

			rset = stm.executeQuery();

			if (rset.next()) {
				cl.setIdCliente(rset.getInt("id_cliente"));
				cl.setIdTipoCliente(rset.getInt("id_tipoCliente"));
				cl.setIdCuentaContable(rset.getInt("id_cuenta_contable"));
				cl.setClaveCuentaContable(rset.getString("clave"));
				cl.setRfc(rset.getString("rfc"));
				cl.setNombreCompleto(rset.getString("nombre_completo"));
				cl.setNombreCorto(rset.getString("nombre_corto"));
				cl.setFechaNac(rset.getDate("fecha_nac"));
				cl.setCorreoElectronico(rset.getString("correo_electronico"));
				cl.setEstado(rset.getString("estado"));
				cl.setCiudad(rset.getString("ciudad"));
				cl.setDireccion(rset.getString("direccion"));
				cl.setCodigoPostal(rset.getString("codigo_postal"));
				cl.setActivo(rset.getBoolean("activo"));
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

	/** Inserta cliente y devuelve `SpResponseModel`. */
	public SpResponseModel insertarNuevoCliente(Clientes cl) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insertCliente(?,?,?,?,?,?,?,?,?,?,?);");
			stm.setInt(1, cl.getIdTipoCliente());
			stm.setInt(2, cl.getIdCuentaContable());
			stm.setString(3, cl.getRfc());
			stm.setString(4, cl.getNombreCompleto());
			stm.setString(5, cl.getNombreCorto());
			stm.setDate(6, cl.getFechaNac());
			stm.setString(7, cl.getCorreoElectronico());
			stm.setString(8, cl.getEstado());
			stm.setString(9, cl.getCiudad());
			stm.setString(10, cl.getDireccion());
			stm.setString(11, cl.getCodigoPostal());

			if (stm.execute()) {
				rset = stm.getResultSet();
				if (rset != null && rset.next()) {
					return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
				}
			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	/** Actualiza cliente y devuelve estado de operación. */
	public SpResponseModel actualizarCliente(Clientes cl) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL updateCliente(?,?,?,?,?,?,?,?,?,?,?,?,?);");
			stm.setInt(1, cl.getIdCliente());
			stm.setInt(2, cl.getIdTipoCliente());
			stm.setInt(3, cl.getIdCuentaContable());
			stm.setString(4, cl.getRfc());
			stm.setString(5, cl.getNombreCompleto());
			stm.setString(6, cl.getNombreCorto());
			stm.setDate(7, cl.getFechaNac());
			stm.setString(8, cl.getCorreoElectronico());
			stm.setString(9, cl.getEstado());
			stm.setString(10, cl.getCiudad());
			stm.setString(11, cl.getDireccion());
			stm.setString(12, cl.getCodigoPostal());
			stm.setBoolean(13, cl.isActivo());

			if (stm.execute()) {
				rset = stm.getResultSet();
				if (rset != null && rset.next()) {
					return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
				}
			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

}