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
	
	/**
	 * consulta todos lo registros de las sucursales en la base de datos y los agrega a un
	 * table model para ser mostrado en la vista
	 * @param model
	 */
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
	
	/**
	 * inserta una nueva sucursal en la base de datos
	 * @param sucursal
	 */
	public void insertarNuevaSucursal(Sucursal sucursal) {
		
		CallableStatement stm = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_nueva_sucursal(?,?,?,?,?,?,?,?);");
			
			stm.setString(1, sucursal.getNombre());
			stm.setString(2, sucursal.getDescripcion());
			stm.setString(3, sucursal.getTelefono());
			stm.setString(4, sucursal.getEmail());
			stm.setString(5, sucursal.getEstado());
			stm.setString(6, sucursal.getCiudad());
			stm.setString(7, sucursal.getDireccion());
			stm.setString(8, sucursal.getCodigoPostal());
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}finally {
			try {
				if (cn != null) {
					cn.close();
				}
				if (stm != null) {
					stm.close();
				}
				
			}catch(SQLException er) {
				er.printStackTrace();
			}
		}
		
	}
	
	public Sucursal consultarSucursal(int idSucursal) {
		
		Sucursal sucursal = new Sucursal();
		CallableStatement stm = null;
		ResultSet rset = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_sucursal_por_id(?);");
			
			stm.setInt(1, idSucursal);
			
			rset = stm.executeQuery();
			
			if(rset.next()) {
				sucursal.setNombre(rset.getString(1));
				sucursal.setDescripcion(rset.getString(2));
				sucursal.setTelefono(rset.getString(3));
				sucursal.setEmail(rset.getString(4));
				sucursal.setEstado(rset.getString(5));
				sucursal.setCiudad(rset.getString(6));
				sucursal.setDireccion(rset.getString(7));
				sucursal.setCodigoPostal(rset.getString(8));
			}
			
			return sucursal;
			
		}catch(SQLException er) {
			er.printStackTrace();
			return null;
		}catch (Exception er) {
			er.printStackTrace();
			return null;
		}finally {
			try {
				if(rset != null) {
					rset.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(cn != null) {
					cn.close();
				}
			}catch(SQLException er) {
				er.printStackTrace();
			}
		}
	}

}
