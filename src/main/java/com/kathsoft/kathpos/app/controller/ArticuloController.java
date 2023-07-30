package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Articulo;

public class ArticuloController implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8759492279100460054L;
	/**
	 * 
	 * 
	 * 
	 */
	private static Connection cn = null;

	public void verArticulosEnTabla(DefaultTableModel tabla) {

		ResultSet rset = null;
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_articulos();");
			rset = stm.executeQuery();

			while (rset.next()) {

				Object[] fila = { rset.getInt(1), // indice del articulo
						rset.getString(2), // codigo del articulo
						rset.getString(3), // Nombre del proveedor
						rset.getString(4), // nombre de la categoría
						rset.getString(5), // codigo SAT del articulo
						rset.getString(6), // nombre del articulo
						rset.getString(7), // descripcion
						rset.getString(8), // existencia
						rset.getString(9), // precio general
						rset.getString(10), // precio mayoreo
				};

				tabla.addRow(fila);
			}

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {

				if (cn != null) {
					cn.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (rset != null) {
					rset.close();
				}

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	/**
	 * consulta mediante un procedimiento almacenado en la bd el listado completo de
	 * los códigos de cada artículo y los agrega a un JComboBox
	 * 
	 * @param cmb
	 */
	public void consultarCodigosArticulos(JComboBox<String> cmb) {

		ResultSet rset = null;
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_codigos_articulos();");
			rset = stm.executeQuery();

			while (rset.next()) {
				cmb.addItem(rset.getString(1));
			}

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {

				if (cn != null) {
					cn.close();
				}
				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * Inserta un nuevo registro en la base de datos
	 * 
	 * @param art
	 * @throws SQLException
	 * @throws Exception
	 */
	public void insertarNuevoArticulo(Articulo art) throws SQLException, Exception {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_nuevo_articulo(?,?,?,?,?,?,?,?,?,?,?);");
			
			stm.setString(1, art.getCodigoArticulo());
			stm.setString(2, art.getNombreProveedor());
			stm.setString(3, art.getNombreCategoria());
			stm.setString(4, art.getCodigoSat());
			stm.setString(5, art.getNombre());
			stm.setString(6, art.getDescripcion());
			stm.setInt(7, art.isExento() == true ? 1 : 0);
			stm.setDouble(8, art.getCostoUnitario());
			stm.setDouble(9, art.getPrecioGeneral());
			stm.setDouble(10, art.getPrecioMayoreo());
			stm.setInt(11, art.getCantidadMayoreo());
			
			stm.execute();
			
		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		}finally {
			try {
				
				if(cn != null) {
					cn.close();
				}
				if(stm != null) {
					stm.close();
				}
			}catch(SQLException er) {
				er.printStackTrace();
			}catch(Exception er) {
				er.printStackTrace();
			}
		}

	}

}
