package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.articulo.Articulo;
import com.kathsoft.kathpos.app.model.articulo.PrecioTipoCliente;
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

	public int insertarNuevoArticulo(Articulo art) throws SQLException, Exception {
		try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.insertarArticulo(cn, art);
		}
	}

	public int insertarNuevoArticulo(Articulo art, List<PrecioTipoCliente> preciosTipoCliente)
			throws SQLException, Exception {

		List<PrecioTipoCliente> precios = preciosTipoCliente == null ? Collections.emptyList() : preciosTipoCliente;

		try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			boolean autoCommitOriginal = cn.getAutoCommit();
			cn.setAutoCommit(false);

			try {
				int idArticulo = this.insertarArticulo(cn, art);
				this.insertarExistenciaArticuloEnTodasLasSucursales(cn, idArticulo);

				for (PrecioTipoCliente precioTipoCliente : precios) {
					this.insertarPrecioArticuloTipoCliente(cn, idArticulo, precioTipoCliente);
				}

				cn.commit();
				return idArticulo;
			} catch (SQLException er) {
				cn.rollback();
				throw er;
			} catch (Exception er) {
				cn.rollback();
				throw er;
			} finally {
				cn.setAutoCommit(autoCommitOriginal);
			}
		}
	}

	@Deprecated
	public int insertarNuevoArticulo(Articulo art, int idSucursal, int existencia,
			List<PrecioTipoCliente> preciosTipoCliente) throws SQLException, Exception {
		return this.insertarNuevoArticulo(art, preciosTipoCliente);
	}

	private int insertarArticulo(Connection cn, Articulo art) throws SQLException {
		try (CallableStatement stm = cn.prepareCall("CALL insertArticulo(?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
			stm.setInt(1, art.getIdProvedor());
			stm.setInt(2, art.getIdCategoria());
			stm.setString(3, art.getCodigoArticulo());
			stm.setString(4, art.getCodigoSat());
			stm.setString(5, art.getUnidadSat());
			stm.setString(6, art.getNombre());
			stm.setString(7, art.getDescripcion());
			stm.setInt(8, art.isExento() ? 1 : 0);
			stm.setDouble(9, art.getCostoUnitario());

			try (ResultSet rset = stm.executeQuery()) {
				if (rset.next()) {
					return rset.getInt("id");
				}
			}
		}

		throw new SQLException("No se pudo obtener el identificador del articulo registrado");
	}

	private void insertarExistenciaArticuloEnTodasLasSucursales(Connection cn, int idArticulo) throws SQLException {
		List<Integer> idsSucursales = this.obtenerIdsSucursales(cn);

		if (idsSucursales.isEmpty()) {
			throw new SQLException("No existen sucursales registradas para asignar existencia al articulo");
		}

		for (Integer idSucursal : idsSucursales) {
			this.insertarExistenciaArticuloSucursal(cn, idArticulo, idSucursal.intValue(), 0);
		}
	}

	private List<Integer> obtenerIdsSucursales(Connection cn) throws SQLException {
		var idsSucursales = new ArrayList<Integer>();

		try (CallableStatement stm = cn.prepareCall("CALL ver_sucursales_nombres();");
				ResultSet rset = stm.executeQuery()) {
			while (rset.next()) {
				idsSucursales.add(Integer.valueOf(rset.getInt("id")));
			}
		}

		return idsSucursales;
	}

	private void insertarExistenciaArticuloSucursal(Connection cn, int idArticulo, int idSucursal, int existencia)
			throws SQLException {
		try (CallableStatement stm = cn.prepareCall("CALL insertExistenciaArticuloSucursal(?, ?, ?);")) {
			stm.setInt(1, idArticulo);
			stm.setInt(2, idSucursal);
			stm.setInt(3, existencia);
			stm.execute();
		}
	}

	private void insertarPrecioArticuloTipoCliente(Connection cn, int idArticulo, PrecioTipoCliente precioTipoCliente)
			throws SQLException {
		try (CallableStatement stm = cn.prepareCall("CALL insertPrecioArticuloTipoCliente(?, ?, ?, ?, ?);")) {
			stm.setInt(1, idArticulo);
			stm.setInt(2, precioTipoCliente.getIdTipoCliente());
			stm.setBigDecimal(3, precioTipoCliente.getPrecio());

			if (precioTipoCliente.getPrecioEspecial() == null) {
				stm.setNull(4, Types.DECIMAL);
			} else {
				stm.setBigDecimal(4, precioTipoCliente.getPrecioEspecial());
			}

			if (precioTipoCliente.getCantidadPrecioEspecial() == null) {
				stm.setNull(5, Types.INTEGER);
			} else {
				stm.setInt(5, precioTipoCliente.getCantidadPrecioEspecial().intValue());
			}

			stm.execute();
		}
	}

	public void actualizarArticulo(Articulo art) throws SQLException, Exception {

		CallableStatement stm = null;
		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL update_articulo(?,?,?,?,?,?,?,?,?,?,?);");

			stm.setInt(1, art.getIdArticulo());
			stm.setInt(2, art.getIdProvedor());
			stm.setInt(3, art.getIdCategoria());
			stm.setString(4, art.getCodigoArticulo());
			stm.setString(5, art.getCodigoSat());
			stm.setString(6, art.getUnidadSat());
			stm.setString(7, art.getNombre());
			stm.setString(8, art.getDescripcion());
			stm.setInt(9, art.isExento() ? 1 : 0);
			stm.setDouble(10, art.getCostoUnitario());
			stm.setInt(11, art.isActivo() ? 1 : 0);

			stm.execute();
		} finally {
			Conexion.cerrarConexion(cn, stm);
		}
	}

	public Articulo consultarArticuloPorCodigo(String codigo, int idSucursal) throws SQLException, Exception {

		Articulo art = new Articulo();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
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
				art.setExento(rset.getInt(9) == 1);
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

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listExistenciaGlobalArticulo(?);")
		) {
			stm.setInt(1, idArticulo);

			try (ResultSet rset = stm.executeQuery()) {
				while (rset.next()) {
					Object[] fila = {
							rset.getInt("id_sucursar"),
							rset.getString("nombre"),
							rset.getString("direccion"),
							rset.getInt("existencia")
					};
					tabla.addRow(fila);
				}
			}
		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}
	}

	public Articulo consultarArticuloPorId(int id, int idSucursal) throws SQLException, Exception {
		Articulo art = new Articulo();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL getArticuloById(?);")
		) {
			stm.setInt(1, id);

			try (ResultSet rset = stm.executeQuery()) {
				if (rset.next()) {
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
				}
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
