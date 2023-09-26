package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
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

	public void verArticulosEnTabla(DefaultTableModel tabla, int idSucursal) {

		ResultSet rset = null;
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_articulos(?);");
			stm.setInt(1, idSucursal);
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

				Conexion.cerrarConexion(cn, rset, stm);

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

				Conexion.cerrarConexion(cn, rset, stm);

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * consulta el listado de articulos registrados en la base de datos de manera
	 * dinámica, ya séa por nombre, proveedor, categoría, codigo o descripción, y
	 * los resultados encontrados los imprime en la tabla pasada como parámetro
	 * 
	 * @param nombre
	 * @param tabla
	 * @param opcion
	 */
	public void consultarArticulosPorNombre(String nombre, DefaultTableModel tabla, int opcion, int id_sucursal) {

		ResultSet rset = null;
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_articulos_por_nombre(?,?,?);");
			stm.setString(1, nombre);
			stm.setInt(2, opcion);
			stm.setInt(3, id_sucursal);
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

				Conexion.cerrarConexion(cn, rset, stm);

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
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [SQL] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [Generic] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {

				Conexion.cerrarConexion(cn, stm);

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param art
	 * @throws SQLException
	 * @throws Exception
	 */
	public void actualizarArticulo(Articulo art) throws SQLException, Exception {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");

			stm = cn.prepareCall("CALL update_articulo(?,?,?,?,?,?,?,?,?,?,?);");

			stm.setInt(1, art.getIdArticulo());
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
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [SQL] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [Generic] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} finally {
			try {

				Conexion.cerrarConexion(cn, stm);

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param codigo -> codigo del articulo;
	 * @return un objeto de tipo {@code Articulo} en función del codigo pasado como
	 *         parámetro
	 */
	public Articulo consultarArticuloPorCodigo(String codigo, int idSucursal) throws SQLException, Exception {

		Articulo art = new Articulo();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_articulo_por_codigo(?,?);");
			stm.setString(1, codigo);
			stm.setInt(2, idSucursal);
			rset = stm.executeQuery();

			if (rset.next()) {

				art.setIdArticulo(rset.getInt(1));
				art.setNombreProveedor(rset.getString(2));
				art.setNombreCategoria(rset.getString(3));
				art.setNombre(rset.getString(4));
				art.setCodigoSat(rset.getString(5));
				art.setDescripcion(rset.getString(6));
				art.setExistencia(rset.getInt(7));
				art.setExento((rset.getInt(8) == 1) ? true : false);
				art.setCostoUnitario(rset.getDouble(9));
				art.setPrecioGeneral(rset.getDouble(10));
				art.setPrecioMayoreo(rset.getDouble(11));
				art.setCantidadMayoreo(rset.getInt(12));

			}

			return art;

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [Generic] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			try {

				Conexion.cerrarConexion(cn, rset, stm);

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public void consultarExistenciasPorSucursal(int idArticulo, DefaultTableModel tabla) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_existencias_articulo_sucursal(?);");
			stm.setInt(1, idArticulo);
			rset = stm.executeQuery();

			while (rset.next()) {
				Object[] fila = { rset.getString(1), rset.getInt(2) };

				tabla.addRow(fila);
			}

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}

	}

}
