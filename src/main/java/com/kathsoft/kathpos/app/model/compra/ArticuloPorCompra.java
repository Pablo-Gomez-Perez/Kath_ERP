package com.kathsoft.kathpos.app.model.compra;

import java.util.Objects;

public class ArticuloPorCompra implements java.io.Serializable {

	private static final long serialVersionUID = 659299551608162385L;

	private int id;
	private int idCompra;
	private int idArticulo;
	private int cantidad;
	private double subtotal;

	public ArticuloPorCompra() {
		super();
	}

	public ArticuloPorCompra(int id, int idCompra, int idArticulo, int cantidad, double subtotal) {
		super();
		this.id = id;
		this.idCompra = idCompra;
		this.idArticulo = idArticulo;
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
		ArticuloPorCompra other = (ArticuloPorCompra) obj;
		return id == other.id && idArticulo == other.idArticulo && idCompra == other.idCompra;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArticuloPorCompra [id=").append(id).append(", idCompra=").append(idCompra)
				.append(", idArticulo=").append(idArticulo).append(", cantidad=").append(cantidad).append(", subtotal=")
				.append(subtotal).append("]");
		return builder.toString();
	}

	public static class ArticuloPorCompraBuilder {

		private final ArticuloPorCompra articuloPorCompra;

		public ArticuloPorCompraBuilder() {
			this.articuloPorCompra = new ArticuloPorCompra();
		}

		public ArticuloPorCompraBuilder id(int id) {
			this.articuloPorCompra.id = id;
			return this;
		}

		public ArticuloPorCompraBuilder idCompra(int idCompra) {
			this.articuloPorCompra.idCompra = idCompra;
			return this;
		}

		public ArticuloPorCompraBuilder idArticulo(int idArticulo) {
			this.articuloPorCompra.idArticulo = idArticulo;
			return this;
		}

		public ArticuloPorCompraBuilder cantidad(int cantidad) {
			this.articuloPorCompra.cantidad = cantidad;
			return this;
		}

		public ArticuloPorCompraBuilder subtotal(double subtotal) {
			this.articuloPorCompra.subtotal = subtotal;
			return this;
		}

		public ArticuloPorCompra build() {
			return this.articuloPorCompra;
		}
	}
}
