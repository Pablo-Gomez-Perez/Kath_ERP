package com.kathsoft.kathpos.app.model.interfaces;

import com.kathsoft.kathpos.app.model.ArticulosPorVentas;

/**
 * Define el contrato que deben implementar los formularios que requieren
 * recibir artículos seleccionados desde {@link Fr_ListaArticulos}.
 * <p>
 * Esta interfaz permite reutilizar el formulario auxiliar de consulta de
 * artículos en distintos módulos del sistema, por ejemplo punto de venta
 * y compras, sin acoplar {@code Fr_ListaArticulos} a una tabla, modelo o
 * flujo específico.
 * </p>
 * <p>
 * Cada formulario que invoque {@code Fr_ListaArticulos} decide cómo procesar
 * el artículo seleccionado: agregarlo a una lista de venta, agregarlo a una
 * lista de compra, actualizar una tabla temporal, calcular importes o aplicar
 * cualquier otra regla propia del módulo.
 * </p>
 *
 * @author Pablo Gómez Pérez
 */
public interface IListadoArticulosAcciones {
	
	/**
	 * Recibe el artículo seleccionado desde el formulario auxiliar
	 * {@link Fr_ListaArticulos} y delega al formulario invocador la forma
	 * concreta de agregarlo o procesarlo.
	 * <p>
	 * Este método es llamado por {@code Fr_ListaArticulos} cuando el usuario
	 * selecciona un artículo de la consulta. El formulario que implementa esta
	 * interfaz debe encargarse de adaptar la información recibida a su propio
	 * modelo de tabla o flujo de negocio.
	 * </p>
	 * <p>
	 * Por ejemplo, en punto de venta el artículo puede agregarse a una tabla
	 * de artículos vendidos; mientras que en compras puede agregarse a una
	 * tabla de artículos comprados. La diferencia de comportamiento queda
	 * encapsulada en la implementación de cada formulario.
	 * </p>
	 *
	 * @param articulo arreglo con los datos visuales o tabulares del artículo
	 *                 seleccionado. Su estructura depende de las columnas que
	 *                 maneje el formulario auxiliar de consulta.
	 * @param art objeto auxiliar con la información del artículo seleccionado,
	 *            usado por el formulario invocador para mapearlo a su propio
	 *            flujo de operación.
	 */
	public void listarArticuloDesdeConsulta(Object[] articulo, ArticulosPorVentas art);
	
}
