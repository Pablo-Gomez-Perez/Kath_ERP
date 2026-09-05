package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	private static final int ERROR_VALIDACION = 500;
	private static final double TOLERANCIA_IMPORTE = 0.01;
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
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.listArticulosCompraById(connection, idCompra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new ArrayList<>();
		} catch (Exception er) {
			er.printStackTrace();
			return new ArrayList<>();
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
		SpResponseModel validacion = this.validarCabeceraNuevaCompra(idSucursal, compra);
		if (validacion != null) {
			return validacion;
		}

		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.insertCompra(connection, idSucursal, compra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel insertCompra(int idSucursal, CompraConDetalle compraConDetalle) {
		SpResponseModel validacion = this.validarNuevaCompra(idSucursal, compraConDetalle);
		if (validacion != null) {
			return validacion;
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
				return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
			} catch (Exception er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
			} finally {
				connection.setAutoCommit(autoCommitOriginal);
			}
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel insertArticuloCompra(ArticuloPorCompra articuloPorCompra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.insertArticuloCompra(connection, articuloPorCompra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel sumarExistenciaSucursalCompra(int idCompra, int idArticulo, int cantidad) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.sumarExistenciaSucursalCompra(connection, idCompra, idArticulo, cantidad);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel updateCompra(int idSucursal, Compra compra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.updateCompra(connection, idSucursal, compra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel updateArticuloCompra(ArticuloPorCompra articuloPorCompra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.updateArticuloCompra(connection, articuloPorCompra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel updateCompra(int idSucursal, CompraConDetalle compraConDetalle) {
		SpResponseModel validacion = this.validarCompraActualizada(idSucursal, compraConDetalle);
		if (validacion != null) {
			return validacion;
		}

		Compra compra = compraConDetalle.getCompra();
		List<ArticuloPorCompra> articulos = compraConDetalle.getArticulosPorCompra() == null ? Collections.emptyList()
				: compraConDetalle.getArticulosPorCompra();

		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			boolean autoCommitOriginal = connection.getAutoCommit();
			connection.setAutoCommit(false);

			try {
				List<ArticuloCompraListado> detallesActuales = this.listArticulosCompraById(connection,
						compra.getIdCompra());
				SpResponseModel validacionDetalles = this.validarDetallesActualizacion(compra.getIdCompra(), articulos,
						detallesActuales);
				if (validacionDetalles != null) {
					connection.rollback();
					return validacionDetalles;
				}

				SpResponseModel respuestaCompra = this.updateCompra(connection, idSucursal, compra);
				if (!isSuccess(respuestaCompra)) {
					connection.rollback();
					return respuestaCompra;
				}

				Set<Integer> idsDetallesConservados = new HashSet<>();
				for (ArticuloPorCompra articuloPorCompra : articulos) {
					if (articuloPorCompra.getId() > 0) {
						idsDetallesConservados.add(articuloPorCompra.getId());
					}
				}

				for (ArticuloCompraListado detalleActual : detallesActuales) {
					if (!idsDetallesConservados.contains(detalleActual.getId())) {
						SpResponseModel respuestaEliminar = this.deleteArticuloCompra(connection, detalleActual.getId());
						if (!isSuccess(respuestaEliminar)) {
							connection.rollback();
							return respuestaEliminar;
						}
					}
				}

				for (ArticuloPorCompra articuloPorCompra : articulos) {
					articuloPorCompra.setIdCompra(compra.getIdCompra());

					if (articuloPorCompra.getId() > 0) {
						SpResponseModel respuestaDetalle = this.updateArticuloCompra(connection, articuloPorCompra);
						if (!isSuccess(respuestaDetalle)) {
							connection.rollback();
							return respuestaDetalle;
						}
						continue;
					}

					SpResponseModel respuestaDetalle = this.insertArticuloCompra(connection, articuloPorCompra);
					if (!isSuccess(respuestaDetalle)) {
						connection.rollback();
						return respuestaDetalle;
					}

					SpResponseModel respuestaExistencia = this.sumarExistenciaSucursalCompra(connection, compra.getIdCompra(),
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
				return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
			} catch (Exception er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
			} finally {
				connection.setAutoCommit(autoCommitOriginal);
			}
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	public SpResponseModel deleteArticuloCompra(int idDetalleCompra) {
		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			return this.deleteArticuloCompra(connection, idDetalleCompra);
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	/**
	 * Cancela lógicamente una compra y revierte las existencias mediante el
	 * procedimiento almacenado deleteCompra. La transacción se controla desde Java
	 * porque el procedimiento devuelve errores de negocio como un result set.
	 */
	public SpResponseModel deleteCompra(int idSucursal, int idCompra) {
		if (idSucursal <= 0) {
			return new SpResponseModel(ERROR_VALIDACION, "La sucursal es obligatoria");
		}
		if (idCompra <= 0) {
			return new SpResponseModel(ERROR_VALIDACION, "La compra es obligatoria");
		}

		try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
			boolean autoCommitOriginal = connection.getAutoCommit();
			connection.setAutoCommit(false);

			try {
				SpResponseModel respuesta = this.deleteCompra(connection, idSucursal, idCompra);
				if (!isDeleteCompraSuccess(respuesta, idCompra)) {
					connection.rollback();
					return respuesta;
				}

				connection.commit();
				return respuesta;
			} catch (SQLException er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
			} catch (Exception er) {
				connection.rollback();
				er.printStackTrace();
				return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
			} finally {
				connection.setAutoCommit(autoCommitOriginal);
			}
		} catch (SQLException er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace();
			return new SpResponseModel(ERROR_VALIDACION, er.getMessage());
		}
	}

	private SpResponseModel validarCompraActualizada(int idSucursal, CompraConDetalle compraConDetalle) {
		if (compraConDetalle == null || compraConDetalle.getCompra() == null) {
			return new SpResponseModel(ERROR_VALIDACION, "La compra es obligatoria");
		}
		if (compraConDetalle.getCompra().getIdCompra() <= 0) {
			return new SpResponseModel(ERROR_VALIDACION, "El ID de la compra es obligatorio para actualizar");
		}
		return this.validarNuevaCompra(idSucursal, compraConDetalle);
	}

	private SpResponseModel validarDetallesActualizacion(int idCompra, List<ArticuloPorCompra> articulos,
			List<ArticuloCompraListado> detallesActuales) {
		Map<Integer, ArticuloCompraListado> existentesPorId = new HashMap<>();
		for (ArticuloCompraListado detalle : detallesActuales) {
			existentesPorId.put(detalle.getId(), detalle);
		}

		Set<Integer> idsDetalleRecibidos = new HashSet<>();
		for (ArticuloPorCompra articulo : articulos) {
			if (articulo.getIdCompra() > 0 && articulo.getIdCompra() != idCompra) {
				return new SpResponseModel(ERROR_VALIDACION, "Existe una partida asociada a otra compra");
			}
			if (articulo.getId() <= 0) {
				continue;
			}
			if (!idsDetalleRecibidos.add(articulo.getId())) {
				return new SpResponseModel(ERROR_VALIDACION, "Existe un detalle de compra repetido");
			}

			ArticuloCompraListado detalleActual = existentesPorId.get(articulo.getId());
			if (detalleActual == null || detalleActual.getIdCompra() != idCompra) {
				return new SpResponseModel(ERROR_VALIDACION,
						"Uno de los detalles no pertenece a la compra que se intenta actualizar");
			}
			if (detalleActual.getIdArticulo() != articulo.getIdArticulo()) {
				return new SpResponseModel(ERROR_VALIDACION,
						"No se puede sustituir el artículo de una partida existente");
			}
		}
		return null;
	}

	private SpResponseModel validarNuevaCompra(int idSucursal, CompraConDetalle compraConDetalle) {
		if (compraConDetalle == null || compraConDetalle.getCompra() == null) {
			return new SpResponseModel(ERROR_VALIDACION, "La compra es obligatoria");
		}

		SpResponseModel validacionCabecera = this.validarCabeceraNuevaCompra(idSucursal, compraConDetalle.getCompra());
		if (validacionCabecera != null) {
			return validacionCabecera;
		}

		List<ArticuloPorCompra> articulos = compraConDetalle.getArticulosPorCompra();
		if (articulos == null || articulos.isEmpty()) {
			return new SpResponseModel(ERROR_VALIDACION, "La compra debe contener al menos un artículo");
		}

		Set<Integer> articulosProcesados = new HashSet<>();
		double subtotalDetalle = 0;
		for (ArticuloPorCompra articulo : articulos) {
			if (articulo == null) {
				return new SpResponseModel(ERROR_VALIDACION, "El detalle de la compra contiene un artículo inválido");
			}
			if (articulo.getIdArticulo() <= 0) {
				return new SpResponseModel(ERROR_VALIDACION, "El artículo de compra es obligatorio");
			}
			if (!articulosProcesados.add(articulo.getIdArticulo())) {
				return new SpResponseModel(ERROR_VALIDACION, "No se puede registrar el mismo artículo más de una vez");
			}
			if (articulo.getCantidad() <= 0) {
				return new SpResponseModel(ERROR_VALIDACION, "La cantidad de cada artículo debe ser mayor a cero");
			}
			if (!Double.isFinite(articulo.getSubtotal()) || articulo.getSubtotal() < 0) {
				return new SpResponseModel(ERROR_VALIDACION, "El subtotal de cada artículo debe ser válido y no negativo");
			}
			subtotalDetalle += articulo.getSubtotal();
		}

		double tolerancia = TOLERANCIA_IMPORTE * Math.max(1, articulos.size());
		if (Math.abs(subtotalDetalle - compraConDetalle.getCompra().getSubtotal()) > tolerancia) {
			return new SpResponseModel(ERROR_VALIDACION,
					"El subtotal de la compra no coincide con la suma de los artículos");
		}

		return null;
	}

	private SpResponseModel validarCabeceraNuevaCompra(int idSucursal, Compra compra) {
		if (compra == null) {
			return new SpResponseModel(ERROR_VALIDACION, "La compra es obligatoria");
		}
		if (idSucursal <= 0) {
			return new SpResponseModel(ERROR_VALIDACION, "La sucursal es obligatoria");
		}
		if (compra.getIdEmpleado() <= 0) {
			return new SpResponseModel(ERROR_VALIDACION, "El empleado que recibe la compra es obligatorio");
		}
		if (compra.getIdProveedor() <= 0) {
			return new SpResponseModel(ERROR_VALIDACION, "El proveedor es obligatorio");
		}
		if (compra.getFolioFactura() == null || compra.getFolioFactura().isBlank()) {
			return new SpResponseModel(ERROR_VALIDACION, "El folio de factura es obligatorio");
		}
		if (compra.getFolioFactura().trim().length() > 13) {
			return new SpResponseModel(ERROR_VALIDACION, "El folio de factura no puede exceder 13 caracteres");
		}
		if (compra.getFechaFactura() == null) {
			return new SpResponseModel(ERROR_VALIDACION, "La fecha de factura es obligatoria");
		}
		if (compra.getFechaCompra() == null) {
			return new SpResponseModel(ERROR_VALIDACION, "La fecha de compra es obligatoria");
		}
		if (!Double.isFinite(compra.getSubtotal()) || compra.getSubtotal() < 0) {
			return new SpResponseModel(ERROR_VALIDACION, "El subtotal de la compra debe ser válido y no negativo");
		}
		if (!Double.isFinite(compra.getIva()) || compra.getIva() < 0) {
			return new SpResponseModel(ERROR_VALIDACION, "El IVA de la compra debe ser válido y no negativo");
		}
		return null;
	}

	private SpResponseModel insertCompra(Connection connection, int idSucursal, Compra compra) throws SQLException {
		if (compra == null) {
			return new SpResponseModel(ERROR_VALIDACION, "La compra es obligatoria");
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
			return new SpResponseModel(ERROR_VALIDACION, "El artículo de compra es obligatorio");
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
			return new SpResponseModel(ERROR_VALIDACION, "La compra es obligatoria");
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
			return new SpResponseModel(ERROR_VALIDACION, "El artículo de compra es obligatorio");
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

	private SpResponseModel deleteArticuloCompra(Connection connection, int idDetalleCompra) throws SQLException {
		try (CallableStatement stm = connection.prepareCall("CALL deleteArticuloCompra(?)")) {
			stm.setInt(1, idDetalleCompra);
			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private SpResponseModel deleteCompra(Connection connection, int idSucursal, int idCompra) throws SQLException {
		try (CallableStatement stm = connection.prepareCall("CALL deleteCompra(?,?)")) {
			stm.setInt(1, idCompra);
			stm.setLong(2, idSucursal);
			try (ResultSet rset = stm.executeQuery()) {
				return buildSpResponse(rset);
			}
		}
	}

	private boolean isDeleteCompraSuccess(SpResponseModel response, int idCompra) {
		return response != null && response.id() == idCompra && "Compra cancelada correctamente".equals(response.message());
	}

	private List<ArticuloCompraListado> listArticulosCompraById(Connection connection, int idCompra) throws SQLException {
		List<ArticuloCompraListado> articulos = new ArrayList<>();
		try (CallableStatement stm = connection.prepareCall("CALL listArticulosCompraById(?)")) {
			stm.setInt(1, idCompra);
			try (ResultSet rset = stm.executeQuery()) {
				while (rset.next()) {
					articulos.add(mapArticuloCompraListado(rset));
				}
			}
		}
		return articulos;
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
		return new SpResponseModel(ERROR_VALIDACION, "Sin respuesta del procedimiento almacenado");
	}

	private boolean isSuccess(SpResponseModel response) {
		return response != null && response.id() > 0 && response.id() != ERROR_VALIDACION;
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
