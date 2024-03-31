package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;

import com.kathsoft.kathpos.app.model.TipoCliente;
import com.kathsoft.kathpos.tools.Conexion;

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
	
	public Vector<Object[]> listarTipoCliente(String nombre){
		
		var data = new Vector<Object[]>();
		CallableStatement stm = null;
		ResultSet rset = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_tipo_clientes(?)");
			stm.setString(1, nombre);
			rset = stm.executeQuery();
			
			while(rset.next()) {
				Object[] row = {
					rset.getInt(1), //id
					rset.getString(2), //nombre de la categoria
					rset.getString(3), //Descripcion de la categoria
					rset.getShort(4) == 0 ? "Inactivo" : "Activo" //Estatus de la categoria
				};
				data.add(row);
			}
			
			return data;
		}catch(SQLException er) {
			er.printStackTrace();
			return null;
		}catch(Exception er) {
			er.printStackTrace();
			return null;
		}
		
	}
	
	public static void insertarNuevoTipoCliente(TipoCliente data) {
		
	}

}
