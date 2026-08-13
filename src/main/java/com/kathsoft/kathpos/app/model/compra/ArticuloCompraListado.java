package com.kathsoft.kathpos.app.model.compra;

import java.util.Objects;

public class ArticuloCompraListado implements java.io.Serializable {

	private static final long serialVersionUID = -5425132224203848424L;

	private int id;
	private int idCompra;
	private int idArticulo;
	private String codigoArticulo;
	private String nombreArticulo;
	private int cantidad;
	private double subtotal;

	public ArticuloCompraListado() {
		super();
	}

	public ArticuloCompraListado(int id, int idCompra, int idArticulo, String codigoArticulo, String nombreArticulo,
			int cantidad, double subtotal) {
		super();
		this.id = id;
		this.idCompra = idCompra;
		this.idArticulo = idArticulo;
		this.codigoArticulo = codigoArticulo;
		this.nombreArticulo = nombreArticulo;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getNombreArticulo() {
		return nombreArticulo;
	}

	public void setNombreArticulo(String nombreArticulo) {
		this.nombreArticulo = nombreArticulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, idArticulo, idCompra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ArticuloCompraListado other = (ArticuloCompraListado) obj;
		return id == other.id && idArticulo == other.idArticulo && idCompra == other.idCompra;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticuloCompraListado [id=").append(id).append(", idCompra=").append(idCompra)
				.append(", idArticulo=").append(idArticulo).append(", codigoArticulo=").append(codigoArticulo)
				.append(", nombreArticulo=").append(nombreArticulo).append(", cantidad=").append(cantidad)
				.append(", subtotal=").append(subtotal).append("]");
		return builder.toString();
	}
}
