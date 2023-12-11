package com.kathsoft.kathpos.app.model;

public class ArticulosPorVentas {

	private int id;
	private int id_venta;
	private int id_articulo;
	private int cantidad;
	private double subtotal;

	/**
	 * @param id
	 * @param id_venta
	 * @param id_articulo
	 * @param cantidad
	 * @param subtotal
	 */
	public ArticulosPorVentas(int id, int id_venta, int id_articulo, int cantidad, double subtotal) {
		super();
		this.id = id;
		this.id_venta = id_venta;
		this.id_articulo = id_articulo;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public ArticulosPorVentas() {

	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id_venta
	 */
	public int getId_venta() {
		return id_venta;
	}

	/**
	 * @param id_venta the id_venta to set
	 */
	public void setId_venta(int id_venta) {
		this.id_venta = id_venta;
	}

	/**
	 * @return the id_articulo
	 */
	public int getId_articulo() {
		return id_articulo;
	}

	/**
	 * @param id_articulo the id_articulo to set
	 */
	public void setId_articulo(int id_articulo) {
		this.id_articulo = id_articulo;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the subtotal
	 */
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * @param subtotal the subtotal to set
	 */
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "ArticulosPorVentas [id=" + id + ", id_venta=" + id_venta + ", id_articulo=" + id_articulo
				+ ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
	}

}
