package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Proveedor;

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
	
	/**
	 * consulta la tabla de proveedores en la base de datos el listado completo de proveedores
	 * registrados y extrae el RFC correspondiente agregandolo al ComboBox que se le pasa como parámetro 
	 * @param cmb
	 */
	public void consultarRFCProveedor(JComboBox<String> cmb) {
		
		CallableStatement stm = null;
		ResultSet rset = null;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_rfcProveedores();");
			rset = stm.executeQuery();
			
			while(rset.next()) {
				cmb.addItem(rset.getString(1));
			}
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}finally {
			try {

				if(cn != null) {
					cn.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(rset != null) {
					rset.close();
				}
				
			}catch(SQLException er) {
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
		
	}
	
	/**
	 * inserta un nuevo registro en la base de datos
	 * @param prv
	 * @throws Exception
	 */
	public void insertarNuevoProveedor(Proveedor prv) throws Exception{
		
		System.out.println(prv.toString());
		
		CallableStatement stm = null;
		
		if(prv.getRfc().isEmpty() || prv.getNombre().isEmpty()) {
			throw new Exception("campos vacios");
		}
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");			
			stm = cn.prepareCall("CALL insert_nuevo_proveedor(?,?,?,?,?,?,?,?);");
			
			stm.setString(1, prv.getRfc());
			stm.setString(2, prv.getNombre());
			stm.setString(3, prv.getDescripcion());
			stm.setString(4, prv.getEmail());
			stm.setString(5, prv.getEstado());
			stm.setString(6, prv.getCiudad());
			stm.setString(7, prv.getDireccion());
			stm.setString(8, prv.getCodigoPostal());
			
			stm.execute();
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}
	}
}
//144,238,144