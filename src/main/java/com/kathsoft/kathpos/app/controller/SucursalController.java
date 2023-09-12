package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Sucursal;

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

	/*
	 * public void consultarSucursales(JComboBox<String> cmb) {
	 * 
	 * CallableStatement stm = null; ResultSet rset = null;
	 * 
	 * try {
	 * 
	 * cn = Conexion.establecerConexionLocal("kath_erp"); stm =
	 * cn.prepareCall("CALL ver_nombres_sucursal();");
	 * 
	 * rset = stm.executeQuery();
	 * 
	 * while(rset.next()) { cmb.addItem(rset.getString(2)); }
	 * 
	 * }catch(SQLException er) { er.printStackTrace(); }catch(Exception er) {
	 * er.printStackTrace(); }finally { try { if(rset != null) { rset.close(); }
	 * if(stm != null) { stm.close(); } if(cn != null) { cn.close(); }
	 * }catch(Exception er) { er.printStackTrace(); } }
	 * 
	 * }
	 */

	public Sucursal consultarSucursalPorId(int id) {

		Sucursal sc = new Sucursal();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
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

			cn = Conexion.establecerConexionLocal("kath_erp");

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
				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	public void verSucursalesEnTabla(DefaultTableModel model) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_sucursales();");
			rset = stm.executeQuery();

			while (rset.next()) {
				Object[] fila = { rset.getString(1), rset.getString(2), rset.getString(3), rset.getString(4),
						rset.getString(5), rset.getString(6), rset.getString(7), rset.getString(8) };
				model.addRow(fila);
			}

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}
				if (rset != null) {
					rset.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}

	}

}
