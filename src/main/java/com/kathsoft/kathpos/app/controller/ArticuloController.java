package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.articulo.Articulo;
import com.kathsoft.kathpos.tools.Conexion;

public class ArticuloController implements java.io.Serializable {

	private static final long serialVersionUID = 8759492279100460054L;
	private static Connection cn = null;

	public Vector<Object[]> verArticulosEnTabla(int idSucursal, int idTipoCliente) {
		return this.verArticulosEnTabla(idSucursal, "TODOS", "NOMBRE", "", idTipoCliente);
	}

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

	public void insertarNuevoArticulo(Articulo art) throws SQLException, Exception {

		String sql = "INSERT INTO articulo ("
				+ "id_proveedor, id_categoria, codigo_articulo, codigo_sat, unidad_sat, "
				+ "nombre, descripcion, es_exento, costo_unitario, activo"
				+ ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				PreparedStatement stm = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
		) {
			stm.setInt(1, art.getIdProvedor());
			stm.setInt(2, art.getIdCategoria());
			stm.setString(3, art.getCodigoArticulo());
			stm.setString(4, art.getCodigoSat());
			stm.setString(5, art.getUnidadSat());
			stm.setString(6, art.getNombre());
			stm.setString(7, art.getDescripcion());
			stm.setBoolean(8, art.isExento());
			stm.setDouble(9, art.getCostoUnitario());
			stm.setBoolean(10, art.isActivo());

			stm.executeUpdate();

			try (ResultSet generatedKeys = stm.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					art.setIdArticulo(generatedKeys.getInt(1));
				}
			}
		}
	}

	public void actualizarArticulo(Articulo art) throws SQLException, Exception {

		String sql = "UPDATE articulo SET "
				+ "id_proveedor = ?, "
				+ "id_categoria = ?, "
				+ "codigo_sat = ?, "
				+ "unidad_sat = ?, "
				+ "nombre = ?, "
				+ "descripcion = ?, "
				+ "es_exento = ?, "
				+ "costo_unitario = ?, "
				+ "activo = ? "
				+ "WHERE id_articulo = ?;";

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				PreparedStatement stm = cn.prepareStatement(sql)
		) {
			stm.setInt(1, art.getIdProvedor());
			stm.setInt(2, art.getIdCategoria());
			stm.setString(3, art.getCodigoSat());
			stm.setString(4, art.getUnidadSat());
			stm.setString(5, art.getNombre());
			stm.setString(6, art.getDescripcion());
			stm.setBoolean(7, art.isExento());
			stm.setDouble(8, art.getCostoUnitario());
			stm.setBoolean(9, art.isActivo());
			stm.setInt(10, art.getIdArticulo());

			stm.executeUpdate();
		}
	}

	public Articulo consultarArticuloPorCodigo(String codigo, int idSucursal) throws SQLException, Exception {
		String sql = "SELECT "
				+ "art.id_articulo, art.id_proveedor, art.id_categoria, art.codigo_articulo, "
				+ "art.codigo_sat, art.unidad_sat, art.nombre, art.descripcion, "
				+ "art.es_exento, art.costo_unitario, art.activo "
				+ "FROM articulo AS art "
				+ "WHERE art.codigo_articulo = ?;";

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				PreparedStatement stm = cn.prepareStatement(sql)
		) {
			stm.setString(1, codigo);

			try (ResultSet rset = stm.executeQuery()) {
				if (rset.next()) {
					return this.mapArticulo(rset);
				}
			}
		}

		return null;
	}

	public void consultarExistenciasPorSucursal(int idArticulo, DefaultTableModel tabla) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
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
		String sql = "SELECT "
				+ "art.id_articulo, art.id_proveedor, art.id_categoria, art.codigo_articulo, "
				+ "art.codigo_sat, art.unidad_sat, art.nombre, art.descripcion, "
				+ "art.es_exento, art.costo_unitario, art.activo "
				+ "FROM articulo AS art "
				+ "WHERE art.id_articulo = ?;";

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				PreparedStatement stm = cn.prepareStatement(sql)
		) {
			stm.setInt(1, id);

			try (ResultSet rset = stm.executeQuery()) {
				if (rset.next()) {
					return this.mapArticulo(rset);
				}
			}
		}

		return null;
	}

	public void eliminarArticulo(int idArticulo) throws SQLException {

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
		stm = cn.prepareCall("CALL eliminar_articulo(?)");
		stm.setInt(1, idArticulo);
		stm.execute();

		Conexion.cerrarConexion(cn, stm);
	}

	private Articulo mapArticulo(ResultSet rset) throws SQLException {
		Articulo art = new Articulo();

		art.setIdArticulo(rset.getInt("id_articulo"));
		art.setIdProvedor(rset.getInt("id_proveedor"));
		art.setIdCategoria(rset.getInt("id_categoria"));
		art.setCodigoArticulo(rset.getString("codigo_articulo"));
		art.setCodigoSat(rset.getString("codigo_sat"));
		art.setUnidadSat(rset.getString("unidad_sat"));
		art.setNombre(rset.getString("nombre"));
		art.setDescripcion(rset.getString("descripcion"));
		art.setExento(rset.getBoolean("es_exento"));
		art.setCostoUnitario(rset.getDouble("costo_unitario"));
		art.setActivo(rset.getBoolean("activo"));

		return art;
	}
}
