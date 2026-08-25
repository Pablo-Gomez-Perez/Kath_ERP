package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.kathsoft.kathpos.app.model.compra.ArticuloCompraListado;
import com.kathsoft.kathpos.app.model.compra.ArticuloPorCompra;
import com.kathsoft.kathpos.app.model.compra.Compra;
import com.kathsoft.kathpos.app.model.compra.CompraById;
import com.kathsoft.kathpos.app.model.compra.CompraConDetalle;
import com.kathsoft.kathpos.app.model.compra.CompraFiltro;
import com.kathsoft.kathpos.app.model.compra.CompraListado;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class CompraController implements java.io.Serializable {

	private static final long serialVersionUID = -4974480297011718553L;
	private static Connection cn = null;

	public List<CompraListado> listCompras(int idSucursal) {
		return this.listCompras(idSucursal, new CompraFiltro());
	}

	public int getSiguienteIdCompra(int idSucursal) {
		return this.listCompras(idSucursal).stream().mapToInt(CompraListado::getIdCompra).max().orElse(0) + 1;
	}

	public List<CompraListado> listCompras(int idSucursal, CompraFiltro filtro) {
		CallableStatement stm = null;
		ResultSet rset = null;
		List<CompraListado> compras = new ArrayList<>();

		try {
			CompraFiltro filtroCompras = filtro == null ? new CompraFiltro() : filtro;
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL listCompras(?,?,?,?,?,?)");
			stm.setLong(1, idSucursal);
			setNullableInt(stm, 2, filtroCompras.getIdProveedor());
			stm.setDate(3, filtroCompras.getFechaFacturaInicio());
			stm.setDate(4, filtroCompras.getFechaFacturaFin());
			stm.setString(5, filtroCompras.getFolioFactura());
			setNullableBoolean(stm, 6, filtroCompras.getTipoCompra());
			rset = stm.executeQuery();

			while (rset.next()) {
				compras.add(mapCompraListado(rset));
			}
			return compras;
		} catch (SQLException er) {
			er.printStackTrace();
			return compras;
		} catch (Exception er) {
			er.printStackTrace();
			return compras;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	public CompraById getCompraById(int idCompra) {
		CallableStatement stm = null;
		ResultSet rset = null;

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL getCompraById(?)");
			stm.setInt(1, idCompra);
			rset = stm.executeQuery();

			if (rset.next()) {
				return mapCompraById(rset);
			}
			return null;
		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	public List<ArticuloCompraListado> listArticulosCompraById(int idCompra) {
		CallableStatement stm = null;
		ResultSet rset = null;
		List<ArticuloCompraListado> articulos = new ArrayList<>();

		try {
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL listArticulosCompraById(?)");
			stm.setInt(1, idCompra);
			rset = stm.executeQuery();

			while (rset.next()) {
				articulos.add(mapArticuloCompraListado(rset));
			}
			return articulos;
		} catch (SQLException er) {
			er.printStackTrace();
			return articulos;
		} catch (Exception er) {
			er.printStackTrace();
			return articulos;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	public CompraConDetalle getCompraConDetalleById(int idCompra) {
		CompraById compraById = this.getCompraById(idCompra);
		if (compraById == null) {
			return null;
		}

		Compra compra = new Compra.CompraBuilder().idCompra(compraById.getIdCompra())
				.idEmpleado(compraById.getIdEmpleado()).idProveedor(compraById.getIdProveedor())
				.folioFactura(compraById.getFolioFactura()).fechaFactura(compraById.getFechaFactura())
				.fechaCompra(compraById.getFechaCompra()).tipoCompra(compraById.isTipoCompra())
				.subtotal(compraById.getSubtotal()).iva(compraById.getIva()).activo(compraById.isActivo()).build();

		List<ArticuloPorCompra> articulosPorCompra = new ArrayList<>();
		for (ArticuloCompraListado articulo : this.listArticulosCompraById(idCompra)) {
			articulosPorCompra.add(new ArticuloPorCompra.ArticuloPorCompraBuilder().id(articulo.getId())
					.idCompra(articulo.getIdCompra()).idArticulo(articulo.getIdArticulo())
					.cantidad(articulo.getCantidad()).subtotal(articulo.getSubtotal()).build());
		}

		return new CompraConDetalle(compra, articulosPorCompra);
	}

	public SpResponseModel insertCompra(int idSucursal, Compra compra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.insertCompra(connection, idSucursal, compra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel insertCompra(int idSucursal, CompraConDetalle compraConDetalle) {
		if (compraConDetalle == null || compraConDetalle.getCompra() == null) {
			return new SpResponseModel(500, "La compra es obligatoria");
		}

		List<ArticuloPorCompra> articulos = compraConDetalle.getArticulosPorCompra() == null ? Collections.emptyList()
				: compraConDetalle.getArticulosPorCompra();

		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			boolean autoCommitOriginal = connection.getAutoCommit();
			connection.setAutoCommit(false);

			try {
				SpResponseModel respuestaCompra = this.insertCompra(connection, idSucursal,
						compraConDetalle.getCompra());
				if (!isSuccess(respuestaCompra)) {
					connection.rollback();
					return respuestaCompra;
				}

				int idCompra = respuestaCompra.id();
				for (ArticuloPorCompra articuloPorCompra : articulos) {
					articuloPorCompra.setIdCompra(idCompra);

					SpResponseModel respuestaDetalle = this.insertArticuloCompra(connection, articuloPorCompra);
					if (!isSuccess(respuestaDetalle)) {
						connection.rollback();
						return respuestaDetalle;
					}

					SpResponseModel respuestaExistencia = this.sumarExistenciaSucursalCompra(connection, idCompra,
							articuloPorCompra.getIdArticulo(), articuloPorCompra.getCantidad());
					if (!isSuccess(respuestaExistencia)) {
						connection.rollback();
						return respuestaExistencia;
					}
				}

				connection.commit();
				return respuestaCompra;
			} catch (SQLException er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(500, er.getMessage());
			} catch (Exception er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(500, er.getMessage());
			} finally {
				connection.setAutoCommit(autoCommitOriginal);
			}
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel insertArticuloCompra(ArticuloPorCompra articuloPorCompra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.insertArticuloCompra(connection, articuloPorCompra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel sumarExistenciaSucursalCompra(int idCompra, int idArticulo, int cantidad) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.sumarExistenciaSucursalCompra(connection, idCompra, idArticulo, cantidad);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel updateCompra(int idSucursal, Compra compra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.updateCompra(connection, idSucursal, compra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel updateArticuloCompra(ArticuloPorCompra articuloPorCompra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.updateArticuloCompra(connection, articuloPorCompra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel updateCompra(int idSucursal, CompraConDetalle compraConDetalle) {
		if (compraConDetalle == null || compraConDetalle.getCompra() == null) {
			return new SpResponseModel(500, "La compra es obligatoria");
		}

		List<ArticuloPorCompra> articulos = compraConDetalle.getArticulosPorCompra() == null ? Collections.emptyList()
				: compraConDetalle.getArticulosPorCompra();

		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			boolean autoCommitOriginal = connection.getAutoCommit();
			connection.setAutoCommit(false);

			try {
				SpResponseModel respuestaCompra = this.updateCompra(connection, idSucursal,
						compraConDetalle.getCompra());
				if (!isSuccess(respuestaCompra)) {
					connection.rollback();
					return respuestaCompra;
				}

				for (ArticuloPorCompra articuloPorCompra : articulos) {
					if (articuloPorCompra.getId() <= 0) {
						articuloPorCompra.setIdCompra(compraConDetalle.getCompra().getIdCompra());
					}

					SpResponseModel respuestaDetalle = articuloPorCompra.getId() > 0
							? this.updateArticuloCompra(connection, articuloPorCompra)
							: this.insertArticuloCompra(connection, articuloPorCompra);

					if (!isSuccess(respuestaDetalle)) {
						connection.rollback();
						return respuestaDetalle;
					}

					if (articuloPorCompra.getId() <= 0) {
						SpResponseModel respuestaExistencia = this.sumarExistenciaSucursalCompra(connection,
								compraConDetalle.getCompra().getIdCompra(), articuloPorCompra.getIdArticulo(),
								articuloPorCompra.getCantidad());
						if (!isSuccess(respuestaExistencia)) {
							connection.rollback();
							return respuestaExistencia;
						}
					}
				}

				connection.commit();
				return respuestaCompra;
			} catch (SQLException er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(500, er.getMessage());
			} catch (Exception er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(500, er.getMessage());
			} finally {
				connection.setAutoCommit(autoCommitOriginal);
			}
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	public SpResponseModel deleteArticuloCompra(int idDetalleCompra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = connection.prepareCall("CALL deleteArticuloCompra(?)")) {
			stm.setInt(1, idDetalleCompra);
			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(500, er.getMessage());
		}
	}

	private SpResponseModel insertCompra(Connection connection, int idSucursal, Compra compra) throws SQLException {
		if (compra == null) {
			return new SpResponseModel(500, "La compra es obligatoria");
		}

		try (CallableStatement stm = connection.prepareCall("CALL insertCompra(?,?,?,?,?,?,?,?,?)")) {
			stm.setInt(1, compra.getIdEmpleado());
			stm.setInt(2, compra.getIdProveedor());
			stm.setLong(3, idSucursal);
			stm.setString(4, compra.getFolioFactura());
			stm.setDate(5, compra.getFechaFactura());
			stm.setDate(6, compra.getFechaCompra());
			stm.setBoolean(7, compra.isTipoCompra());
			stm.setDouble(8, compra.getSubtotal());
			stm.setDouble(9, compra.getIva());

			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private SpResponseModel insertArticuloCompra(Connection connection, ArticuloPorCompra articuloPorCompra)
			throws SQLException {
		if (articuloPorCompra == null) {
			return new SpResponseModel(500, "El artículo de compra es obligatorio");
		}

		try (CallableStatement stm = connection.prepareCall("CALL insertArticuloCompra(?,?,?,?)")) {
			stm.setInt(1, articuloPorCompra.getIdCompra());
			stm.setInt(2, articuloPorCompra.getIdArticulo());
			stm.setInt(3, articuloPorCompra.getCantidad());
			stm.setDouble(4, articuloPorCompra.getSubtotal());

			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private SpResponseModel sumarExistenciaSucursalCompra(Connection connection, int idCompra, int idArticulo,
			int cantidad) throws SQLException {
		try (CallableStatement stm = connection.prepareCall("CALL sumarExistenciaSucursalCompra(?,?,?)")) {
			stm.setInt(1, idCompra);
			stm.setInt(2, idArticulo);
			stm.setInt(3, cantidad);

			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private SpResponseModel updateCompra(Connection connection, int idSucursal, Compra compra) throws SQLException {
		if (compra == null) {
			return new SpResponseModel(500, "La compra es obligatoria");
		}

		try (CallableStatement stm = connection.prepareCall("CALL updateCompra(?,?,?,?,?,?,?,?,?,?)")) {
			stm.setInt(1, compra.getIdCompra());
			stm.setInt(2, compra.getIdEmpleado());
			stm.setInt(3, compra.getIdProveedor());
			stm.setLong(4, idSucursal);
			stm.setString(5, compra.getFolioFactura());
			stm.setDate(6, compra.getFechaFactura());
			stm.setDate(7, compra.getFechaCompra());
			stm.setBoolean(8, compra.isTipoCompra());
			stm.setDouble(9, compra.getSubtotal());
			stm.setDouble(10, compra.getIva());

			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private SpResponseModel updateArticuloCompra(Connection connection, ArticuloPorCompra articuloPorCompra)
			throws SQLException {
		if (articuloPorCompra == null) {
			return new SpResponseModel(500, "El artículo de compra es obligatorio");
		}

		try (CallableStatement stm = connection.prepareCall("CALL updateArticuloCompra(?,?,?)")) {
			stm.setInt(1, articuloPorCompra.getId());
			stm.setInt(2, articuloPorCompra.getCantidad());
			stm.setDouble(3, articuloPorCompra.getSubtotal());

			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private CompraListado mapCompraListado(ResultSet rset) throws SQLException {
		return new CompraListado(rset.getInt("id_compra"), rset.getInt("id_empleado"), rset.getInt("id_proveedor"),
				rset.getString("folio_factura"), rset.getDate("fecha_factura"), rset.getDate("fecha_compra"),
				rset.getBoolean("tipo_compra"), rset.getString("tipo_compra_descripcion"), rset.getDouble("subtotal"),
				rset.getDouble("iva"), rset.getDouble("importe_total"), rset.getBoolean("activo"));
	}

	private CompraById mapCompraById(ResultSet rset) throws SQLException {
		return new CompraById(rset.getInt("id_compra"), rset.getInt("id_empleado"), rset.getString("nombre_empleado"),
				rset.getString("nombre_corto_empleado"), rset.getInt("id_sucursal"), rset.getInt("id_proveedor"),
				rset.getString("folio_factura"), rset.getDate("fecha_factura"), rset.getDate("fecha_compra"),
				rset.getBoolean("tipo_compra"), rset.getString("tipo_compra_descripcion"), rset.getDouble("subtotal"),
				rset.getDouble("iva"), rset.getDouble("importe_total"), rset.getBoolean("activo"));
	}

	private ArticuloCompraListado mapArticuloCompraListado(ResultSet rset) throws SQLException {
		return new ArticuloCompraListado(rset.getInt("id"), rset.getInt("id_compra"), rset.getInt("id_articulo"),
				rset.getString("codigo_articulo"), rset.getString("nombre_articulo"), rset.getInt("cantidad"),
				rset.getDouble("subtotal"));
	}

	private SpResponseModel buildSpResponse(ResultSet rset) throws SQLException {
		if (rset != null && rset.next()) {
			return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
		}
		return new SpResponseModel(500, "Sin respuesta del procedimiento almacenado");
	}

	private boolean isSuccess(SpResponseModel response) {
		return response != null && response.id() > 0 && response.id() != 500;
	}

	private void setNullableInt(CallableStatement stm, int index, int value) throws SQLException {
		if (value <= 0) {
			stm.setNull(index, Types.INTEGER);
			return;
		}
		stm.setInt(index, value);
	}

	private void setNullableBoolean(CallableStatement stm, int index, Boolean value) throws SQLException {
		if (value == null) {
			stm.setNull(index, Types.BOOLEAN);
			return;
		}
		stm.setBoolean(index, value.booleanValue());
	}
}
