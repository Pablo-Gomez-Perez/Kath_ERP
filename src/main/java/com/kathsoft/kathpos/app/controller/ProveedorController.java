package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class ProveedorController implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5820147766336769662L;
	/**
	 * 
	 * 
	 * 
	 */
	private static Connection cn = null;
	
	public void verProveedoresEnTabla(DefaultTableModel tabla) {
		ResultSet rset = null;
		CallableStatement stm = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_proveedores();");
			rset = stm.executeQuery();
			
			while(rset.next()) {
				Object[] fila = {
					rset.getString(1), //RFC
					rset.getString(2), //clave contable
					rset.getString(3), //Nombre
					rset.getString(4), //Descripcion
					rset.getString(5), //Correo Electronico
					rset.getString(6), //Estado
					rset.getString(7), //Ciudad
					rset.getString(8), //Direccion
					rset.getString(9), //Codigo Postal;
				};				
				tabla.addRow(fila);
			}
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}
	}
}
//144,238,144