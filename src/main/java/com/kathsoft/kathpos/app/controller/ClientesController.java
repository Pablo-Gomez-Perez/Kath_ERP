package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class ClientesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private Connection cn = null;
	
	
	public void verClientesEnTabla(DefaultTableModel tabla) {
		
		CallableStatement stm = null;
		ResultSet rset = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_clientes();");
			rset = stm.executeQuery();
			
			while(rset.next()) {
				
				Object[] fila = {
					rset.getString(1),
					rset.getString(2),
					rset.getString(3),
					rset.getString(4),
					rset.getString(5),
					rset.getString(6),
					rset.getString(7),
					rset.getString(8),
					rset.getString(9)
				};
				
				tabla.addRow(fila);
				
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
				
			}catch(SQLException er) {
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
		
	}
	
}
