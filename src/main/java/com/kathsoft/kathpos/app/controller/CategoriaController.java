package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Categoria;
import com.kathsoft.kathpos.tools.Conexion;

public class CategoriaController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6835247986143695345L;
	/**
	 * 
	 */
	private Categoria categoria;
	private static Connection cn;
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
	
	/**
	 * conecta a la base de datos e imprime en un JTable de un formulario
	 * todos los datos recolectados.
	 * @param tabla
	 */
	public void verCategoriasEnTabla(DefaultTableModel tabla) {
		
		ResultSet rset = null;
		CallableStatement stm = null;
		
		try {
			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL ver_marcas()");
			rset = stm.executeQuery();
			
			while(rset.next()) {
				Object[] fila = {rset.getInt(1), rset.getString(2), rset.getString(3)};
				tabla.addRow(fila);
			}
			
		}catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			}catch(SQLException er) {
				er.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void obtenerIndicesDeCategorias(JComboBox<String> jcmb) {
		
		ResultSet rset = null;
		CallableStatement stm = null;
				
		try {
			
			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL ver_nombres_categorias()");
			rset = stm.executeQuery();
			
			while(rset.next()) {
				jcmb.addItem(rset.getString(1));
			}
			
		}catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			}catch(SQLException er) {
				er.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * busca el nombre y la descripción de una categoria en concreto en función del indice
	 * y coloca los valores en sus respetivos campos de fomulario
	 * @param txf
	 * @param txa
	 */
	public Categoria buscarCategoriaPorNombre(String nombre) {
		CallableStatement stm = null; 
		ResultSet rset= null;
		Categoria cta = new Categoria();
		try {
			
			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL buscar_categoria_por_nombre(?)");
			stm.setString(1, nombre);
			
			rset = stm.executeQuery();
			
			if(rset.next()) {
				cta.setIdCategoria(rset.getInt(1));
				cta.setDescripcion(rset.getString(2));
			}
			
			return cta;
		}catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			}catch(SQLException er) {
				er.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void insertarNuevaCategoria(Categoria categoria) {
		
		CallableStatement stm= null;
		
		if(categoria.getNombre().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Campo Vacio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_nva_categoria(?,?);");
			stm.setString(1, categoria.getNombre());
			stm.setString(2, categoria.getDescripcion());
			stm.execute();
			
		}catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
			try {
				Conexion.cerrarConexion(cn, stm);		
			}catch(SQLException er) {
				er.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void actualizarCategoria(Categoria categoria) {
		CallableStatement stm = null;
		
		if(categoria.getNombre().isEmpty()) {
			return;
		}
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL update_categoria(?,?,?);");
			stm.setInt(1, categoria.getIdCategoria());
			stm.setString(2, categoria.getNombre());
			stm.setString(3, categoria.getDescripcion());
			stm.execute();
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			}catch(SQLException er){
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}
		
	}
	
}
