package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import com.kathsoft.kathpos.tools.Conexion;

public class CuentaContableController {
	
	private static Connection cn;
	
	public CuentaContableController() {
		
	}
	
	public Vector<Object[]> verCuentasContables(String cuenta){
		var data = new Vector<Object[]>();
		CallableStatement stm = null;
		ResultSet rset = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_cuentas_contables(?)");
			stm.setString(1, cuenta);
			rset = stm.executeQuery();
			
			while(rset.next()) {
				data.add(new Object[] {
					rset.getInt(1), //id_cuenta
					rset.getString(2), //Nombre de la cuenta contable superior
					rset.getString(3), //clave de la cuenta contable
					rset.getString(4), //Nombre cuenta
					rset.getString(5), //descripcion
					rset.getShort(6), // nivel
					rset.getBoolean(7) ? "Si" : "No",
					rset.getDouble(8), //cargos
					rset.getDouble(9), //abonos
					rset.getDouble(10) //saldo de la cuenta					
				});
			}
			
			return data;
			
		}catch(SQLException er) {
			er.printStackTrace();
			return null;
		}catch(Exception er) {
			er.printStackTrace();
			return null;
		}finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			}catch(Exception er){
				er.printStackTrace();
			}
		}
		
	}
	
}
