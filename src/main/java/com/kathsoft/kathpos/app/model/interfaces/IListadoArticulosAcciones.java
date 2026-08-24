package com.kathsoft.kathpos.app.model.interfaces;

import java.math.BigDecimal;

import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.articulo.ArticuloByCodigo;

/**
 * Define el contrato que deben implementar los formularios que requieren
 * recibir artículos seleccionados desde el formulario compartido de listado.
 * <p>
 * El contrato genérico recibe el artículo, la cantidad seleccionada y el precio
 * mostrado por el listado. Cada formulario invocador decide cómo adaptar esos
 * datos a su propio flujo de negocio.
 * </p>
 *
 * @author Pablo Gómez Pérez
 */
public interface IListadoArticulosAcciones {

	/**
	 * Recibe una selección de artículo sin acoplarla a ventas o compras.
	 * <p>
	 * La implementación por defecto conserva compatibilidad con el flujo actual de
	 * punto de venta, que todavía utiliza {@link ArticulosPorVentas}. Los nuevos
	 * consumidores pueden sobrescribir este método y manejar directamente el
	 * artículo seleccionado.
	 * </p>
	 *
	 * @param articulo artículo seleccionado
	 * @param cantidad cantidad indicada por el usuario
	 * @param precio precio mostrado para el artículo en el listado
	 */
	default void listarArticuloDesdeConsulta(ArticuloByCodigo articulo, int cantidad, BigDecimal precio) {
		double precioUnitario = precio == null ? 0.0 : precio.doubleValue();
		double subtotal = precioUnitario * cantidad;

		Object[] fila = {
				articulo.getCodigoArticulo(),
				articulo.getDescripcion(),
				precioUnitario,
				cantidad,
				0,
				subtotal
		};

		ArticulosPorVentas articuloVenta = new ArticulosPorVentas();
		articuloVenta.setId_articulo(articulo.getIdArticulo());
		articuloVenta.setCantidad(cantidad);
		articuloVenta.setSubtotal(subtotal);

		this.listarArticuloDesdeConsulta(fila, articuloVenta);
	}

	/**
	 * Contrato legado utilizado por punto de venta.
	 *
	 * @param articulo fila adaptada al modelo de tabla de ventas
	 * @param art detalle del artículo vendido
	 */
	public void listarArticuloDesdeConsulta(Object[] articulo, ArticulosPorVentas art);

}
