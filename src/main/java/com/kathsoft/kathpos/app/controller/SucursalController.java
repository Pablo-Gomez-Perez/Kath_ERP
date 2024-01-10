package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.tools.Conexion;
import com.kathsoft.kathpos.tools.DataTools;

public class SucursalController implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6758797455027410479L;
	/**
	 * 
	 * 
	 * 
	 */
	private static Connection cn = null;

	public Sucursal consultarSucursalPorId(int id) {

		Sucursal sc = new Sucursal();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_sucursal_por_id(?)");
			stm.setInt(1, id);

			rset = stm.executeQuery();

			if (rset.next()) {
				sc.setIdSucursal(rset.getInt(1));
				sc.setNombre(rset.getString(2));
				sc.setDescripcion(rset.getString(3));
				sc.setTelefono(rset.getString(4));
				sc.setEstado(rset.getString(5));
				sc.setCiudad(rset.getString(6));
				sc.setDireccion(rset.getString(7));
				sc.setCodigoPostal(rset.getString(8));
			}

			return sc;

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
	 * consulta únicamente los nombres de las sucursales registradas
	 * 
	 * @param cmb
	 */
	public void consultarNombreSucursales(JComboBox<String> cmb) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);

			stm = cn.prepareCall("CALL ver_sucursales_nombres();");
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * consulta todos lo registros de las sucursales en la base de datos y los
	 * agrega a un table model para ser mostrado en la vista
	 * 
	 * @param model
	 */
	public void verSucursalesEnTabla(DefaultTableModel model) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_sucursales();");
			rset = stm.executeQuery();

			while (rset.next()) {
				model.addRow(new Object[] { rset.getInt(1), // indice
						rset.getString(2), // nombre
						rset.getString(3), // descripcion
						rset.getString(4), // telefono
						rset.getString(5), // email
						rset.getString(6), // estado
						rset.getString(7), // ciudad
						rset.getString(8), // direccion
						rset.getString(9), // codigo postal
						rset.getShort(10) == 1 ? "Activo" : "Inactivo" // sucursar activa o inactiva
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

	/**
	 * inserta una nueva sucursal en la base de datos
	 * 
	 * @param sucursal
	 */
	public void insertarNuevaSucursal(Sucursal sucursal) {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insert_nueva_sucursal(?,?,?,?,?,?,?,?);");

			stm.setString(1, sucursal.getNombre());
			stm.setString(2, sucursal.getDescripcion());
			stm.setString(3, sucursal.getTelefono());
			stm.setString(4, sucursal.getEmail());
			stm.setString(5, sucursal.getEstado());
			stm.setString(6, sucursal.getCiudad());
			stm.setString(7, sucursal.getDireccion());
			stm.setString(8, sucursal.getCodigoPostal());

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
			}
		}

	}

	public void actualizarSucursal(Sucursal sucursal) {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL update_sucursal(?,?,?,?,?,?,?,?,?);");

			stm.setInt(1, sucursal.getIdSucursal());
			stm.setString(2, sucursal.getNombre());
			stm.setString(3, sucursal.getDescripcion());
			stm.setString(4, sucursal.getTelefono());
			stm.setString(5, sucursal.getEmail());
			stm.setString(6, sucursal.getEstado());
			stm.setString(7, sucursal.getCiudad());
			stm.setString(8, sucursal.getDireccion());
			stm.setString(9, sucursal.getCodigoPostal());

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
			}
		}

	}

	public Sucursal consultarSucursal(int idSucursal) {

		Sucursal sucursal = new Sucursal();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL buscar_sucursal_por_id(?);");

			stm.setInt(1, idSucursal);

			rset = stm.executeQuery();

			if (rset.next()) {
				sucursal.setIdSucursal(rset.getInt(1));
				sucursal.setNombre(rset.getString(2));
				sucursal.setDescripcion(rset.getString(3));
				sucursal.setTelefono(rset.getString(4));
				sucursal.setEmail(rset.getString(5));
				sucursal.setEstado(rset.getString(6));
				sucursal.setCiudad(rset.getString(7));
				sucursal.setDireccion(rset.getString(8));
				sucursal.setCodigoPostal(rset.getString(9));
				sucursal.setActivo(rset.getShort(10) == 1 ? true : false);
			}

			return sucursal;

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

	public void eliminarSucursal(int idSucursal) {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL eliminar_sucursal(?);");

			stm.setInt(1, idSucursal);

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
			}
		}

	}

}

//
