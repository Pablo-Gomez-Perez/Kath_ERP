package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

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
	
	public void consultarSucursales(JComboBox<String> cmb) {
		
		CallableStatement stm = null;
		ResultSet rset = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_nombres_sucursal();");
			
			rset = stm.executeQuery();
			
			while(rset.next()) {
				cmb.addItem(rset.getString(2));
			}			
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
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
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
		
	}
	
}
