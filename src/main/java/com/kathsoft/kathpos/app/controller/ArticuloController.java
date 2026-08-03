package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.articulo.Articulo;
import com.kathsoft.kathpos.tools.Conexion;

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

	/**
	 * Lista los articulos usando los parametros base para la carga inicial de tabla.
	 *
	 * @param idSucursal identificador de la sucursal desde la que se consulta
	 * @param idTipoCliente identificador del tipo de cliente usado para obtener precio
	 * @return vector con las filas de articulos
	 */
	public Vector<Object[]> verArticulosEnTabla(int idSucursal, int idTipoCliente) {
		return this.verArticulosEnTabla(idSucursal, "TODOS", "NOMBRE", "", idTipoCliente);
	}

	/**
	 * Lista los articulos registrados usando el procedimiento almacenado listArticulos.
	 *
	 * @param idSucursal identificador de la sucursal desde la que se consulta la existencia
	 * @param tipoBusqueda criterio de busqueda: TODOS, CODIGO, NOMBRE, PROVEEDOR, CATEGORIA o DESCRIPCION
	 * @param ordenarPor criterio de ordenamiento: CODIGO, NOMBRE, PROVEEDOR o CATEGORIA
	 * @param textoBusqueda texto usado para filtrar la consulta
	 * @param idTipoCliente identificador del tipo de cliente usado para obtener precio
	 * @return vector con las filas de articulos
	 */
	public Vector<Object[]> verArticulosEnTabla(
			int idSucursal,
			String tipoBusqueda,
			String ordenarPor,
			String textoBusqueda,
			int idTipoCliente
	) {

		var articulos = new Vector<Object[]>();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listArticulos(?, ?, ?, ?, ?);")
		) {

			stm.setInt(1, idSucursal);
			stm.setString(2, tipoBusqueda);
			stm.setString(3, ordenarPor);
			stm.setString(4, textoBusqueda);
			stm.setInt(5, idTipoCliente);

			try (ResultSet rset = stm.executeQuery()) {

				while (rset.next()) {
					Object[] fila = {
							rset.getInt("id_articulo"),
							rset.getString("nombre_proveedor"),
							rset.getString("nombre_categoria"),
							rset.getString("codigo_articulo"),
							rset.getString("nombre"),
							rset.getBoolean("es_exento") ? "Exento" : "Gravado",
							rset.getBigDecimal("costo_unitario"),
							rset.getBigDecimal("precio"),
							rset.getInt("existencia"),
							rset.getInt("activo") == 1 ? "Activo" : "Inactivo"
					};

					articulos.add(fila);
				}

			}

		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}

		return articulos;
	}

	/**
	 * Inserta un nuevo registro en la base de datos
	 * 
	 * @param art
	 * @throws SQLException
	 * @throws Exception
	 */
	public void insertarNuevoArticulo(Articulo art) throws SQLException, Exception {

		System.out.println(art);

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal("kath_erp");
		stm = cn.prepareCall("CALL insert_nuevo_articulo(?,?,?,?,?,?,?,?,?,?,?);");

		stm.setString(1, art.getCodigoArticulo());
		stm.setInt(2, art.getIdProvedor());
		stm.setInt(3, art.getIdCategoria());
		stm.setString(4, art.getCodigoSat());
		stm.setString(5, art.getNombre());
		stm.setString(6, art.getDescripcion());
		stm.setInt(7, art.isExento() == true ? 1 : 0);
		stm.setDouble(8, art.getCostoUnitario());
		// stm.setDouble(9, art.getPrecioGeneral());
		// stm.setDouble(10, art.getPrecioMayoreo());
		// stm.setInt(11, art.getCantidadMayoreo());

		stm.execute();

		Conexion.cerrarConexion(cn, stm);

	}

	/**
	 * 
	 * @param art
	 * @throws SQLException
	 * @throws Exception
	 */
	public void actualizarArticulo(Articulo art) throws SQLException, Exception {



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
				art.setCodigoArticulo(rset.getString(2));
				art.setNombre(rset.getString(5));
				art.setCodigoSat(rset.getString(6));
				art.setDescripcion(rset.getString(7));				
				art.setExento((rset.getInt(9) == 1) ? true : false);
				art.setCostoUnitario(rset.getDouble(10));


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

	public Articulo consultarArticuloPorId(int id, int idSucursal) throws SQLException, Exception {
		Articulo art = new Articulo();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_articulo_por_id(?,?);");
			stm.setInt(1, id);
			stm.setInt(2, idSucursal);
			rset = stm.executeQuery();

			if (rset.next()) {

				art.setIdArticulo(rset.getInt(1));
				art.setCodigoArticulo(rset.getString(2));				
				art.setNombre(rset.getString(5));
				art.setCodigoSat(rset.getString(6));
				art.setDescripcion(rset.getString(7));				
				art.setExento((rset.getInt(9) == 1) ? true : false);
				art.setCostoUnitario(rset.getDouble(10));
				// art.setPrecioGeneral(rset.getDouble(11));
				// art.setPrecioMayoreo(rset.getDouble(12));
				// art.setCantidadMayoreo(rset.getInt(13));

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

	public void eliminarArticulo(int idArticulo) throws SQLException {

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
		stm = cn.prepareCall("CALL eliminar_articulo(?)");
		stm.setInt(1, idArticulo);
		stm.execute();

		Conexion.cerrarConexion(cn, stm);
	}

}
