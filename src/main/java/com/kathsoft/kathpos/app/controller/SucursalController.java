package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class SucursalController implements java.io.Serializable {

	private static final long serialVersionUID = -6758797455027410479L;
	private static Connection cn = null;

	/**
	 * Lista las sucursales activas para controles de selección.
	 *
	 * @return sucursales disponibles con id y nombre
	 */
	public List<JComboboxDataViewModel> consultarNombreSucursales() {
		
		var sucursales = new ArrayList<JComboboxDataViewModel>();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);

			stm = cn.prepareCall("CALL ver_sucursales_nombres();");
			rset = stm.executeQuery();

			while (rset.next()) {

				sucursales.add(new JComboboxDataViewModel(rset.getInt("id"), rset.getString("nombre")));
			}
			
			return sucursales;
			
		} catch (SQLException er) {
						
			er.printStackTrace();
			return sucursales;
			
		} catch (Exception er) {
			
			er.printStackTrace();
			return sucursales;
			
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Consulta las sucursales registradas para mostrarlas en tabla.
	 *
	 * @return filas con el detalle de cada sucursal
	 */
	public Vector<Object[]> verSucursalesEnTabla() {

		CallableStatement stm = null;
		ResultSet rset = null;
		var data = new Vector<Object[]>();

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_sucursales();");
			rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new Object[] { rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getString(5),
						rset.getString(6),
						rset.getString(7),
						rset.getString(8),
						rset.getString(9),
						rset.getShort(10) == 1 ? "Activo" : "Inactivo"
				});
			}

			return data;
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
	 * Registra una sucursal usando el SP vigente y devuelve su respuesta.
	 *
	 * @param sucursal datos de la sucursal a registrar
	 * @return respuesta estándar del procedimiento almacenado
	 */
	public SpResponseModel insertarNuevaSucursal(Sucursal sucursal) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insertSucursal(?,?,?,?,?,?,?,?);");

			stm.setString(1, sucursal.getNombre());
			stm.setString(2, sucursal.getDescripcion());
			stm.setString(3, sucursal.getTelefono());
			stm.setString(4, sucursal.getEmail());
			stm.setString(5, sucursal.getEstado());
			stm.setString(6, sucursal.getCiudad());
			stm.setString(7, sucursal.getDireccion());
			stm.setString(8, sucursal.getCodigoPostal());

			if (stm.execute()) {
				rset = stm.getResultSet();
				return buildSpResponse(rset);
			}

			return new SpResponseModel(500, "El procedimiento insertSucursal no devolvió respuesta");

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
			}
		}

	}

	/**
	 * Actualiza una sucursal usando el SP vigente y devuelve su respuesta.
	 *
	 * @param sucursal datos actualizados de la sucursal
	 * @return respuesta estándar del procedimiento almacenado
	 */
	public SpResponseModel actualizarSucursal(Sucursal sucursal) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL updateSucursal(?,?,?,?,?,?,?,?,?);");

			stm.setInt(1, sucursal.getIdSucursal());
			stm.setString(2, sucursal.getNombre());
			stm.setString(3, sucursal.getDescripcion());
			stm.setString(4, sucursal.getTelefono());
			stm.setString(5, sucursal.getEmail());
			stm.setString(6, sucursal.getEstado());
			stm.setString(7, sucursal.getCiudad());
			stm.setString(8, sucursal.getDireccion());
			stm.setString(9, sucursal.getCodigoPostal());

			if (stm.execute()) {
				rset = stm.getResultSet();
				return buildSpResponse(rset);
			}

			return new SpResponseModel(500, "El procedimiento updateSucursal no devolvió respuesta");

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

	public void eliminarSucursal(int idSucursal) throws SQLException {

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
		stm = cn.prepareCall("CALL eliminar_sucursal(?);");

		stm.setInt(1, idSucursal);

		stm.execute();

		Conexion.cerrarConexion(cn, stm);

	}

	/**
	 * Construye la respuesta estándar devuelta por procedimientos de escritura.
	 *
	 * @param rset resultado con columnas id y message
	 * @return respuesta del SP o error genérico si no hubo fila
	 * @throws SQLException si falla la lectura del resultado
	 */
	private SpResponseModel buildSpResponse(ResultSet rset) throws SQLException {
		if (rset != null && rset.next()) {
			return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
		}

		return new SpResponseModel(500, "El procedimiento almacenado no devolvió respuesta");
	}

}
