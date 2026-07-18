package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.cliente.TipoCliente;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;
import com.mysql.cj.protocol.Resultset;

public class TipoClienteController {

	private static Connection cn = null;

	public Vector<TipoCliente> cmbTipoCliente() {

		var data = new Vector<TipoCliente>();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL cmb_tipoCliente");
			rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new TipoCliente(rset.getInt(1), rset.getString(2)));
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	public Vector<Object[]> listarTipoCliente(String nombre) {

		var data = new Vector<Object[]>();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_tipo_clientes(?)");
			stm.setString(1, nombre);
			rset = stm.executeQuery();

			while (rset.next()) {
				Object[] row = { rset.getInt(1), // id
						rset.getString(2), // nombre de la categoria
						rset.getString(3), // Descripcion de la categoria
						rset.getShort(4) == 0 ? "Inactivo" : "Activo" // Estatus de la categoria
				};
				data.add(row);
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public SpResponseModel insertarNuevoTipoCliente(TipoCliente data) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insert_nuevo_tipoCliente(?,?)");
			stm.setString(1, data.getNombre());
			stm.setString(2, data.getDescripcion());
			rset = stm.executeQuery();
			
			if(rset.next()) {
				return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
			}
			
			return new SpResponseModel(500,"Ocurrio un error desconocido");
			
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500,er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500,er.getMessage());
		} finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			} catch (Exception er) { 
				er.printStackTrace();
			}
		}

	}

	public SpResponseModel actualizarTipoCliente(TipoCliente data) {

		CallableStatement stm = null;
		ResultSet rset = null;

		System.out.println(data.printData());

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL update_tipoCliente(?,?,?);");
			stm.setInt(1, data.getIdTipoCliente());
			stm.setString(2, data.getNombre());
			stm.setString(3, data.getDescripcion());
			rset = stm.executeQuery();
			
			if(rset.next()) {
				return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
			}
			
			return new SpResponseModel(500,"Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500,er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500,er.getMessage());
		} finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public TipoCliente buscarPorId(int idTipoCliente) {

		var data = new TipoCliente();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL buscar_tipoCliente_por_id(?);");
			stm.setInt(1, idTipoCliente);
			rset = stm.executeQuery();

			if (rset.next()) {
				data.setId_tipoCliente(rset.getInt(1));
				data.setNombre(rset.getString(2));
				data.setDescripcion(rset.getString(3));
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}
	
	public SpResponseModel eliminarTipoCliente(int idTipoCliente) {
			
		ResultSet rset = null;
		CallableStatement stm = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL eliminar_tipoCliente(?)");
			stm.setInt(1, idTipoCliente);
			rset = stm.executeQuery();
			
			if(rset.next()) {
				return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
			}
			
			return new SpResponseModel(500,"Ocurrio un error desconocido");
			
		} catch (SQLException e) {
			e.printStackTrace();
			return new SpResponseModel(500,e.getMessage());
		}finally {
			try {
				
				Conexion.cerrarConexion(cn, rset, stm);
				
			} catch (Exception er) {
				
				er.printStackTrace();
				
			}
		}		
		
	}
	
}
