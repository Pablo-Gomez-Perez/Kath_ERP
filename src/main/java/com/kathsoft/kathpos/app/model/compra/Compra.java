package com.kathsoft.kathpos.app.model.compra;

import java.sql.Date;
import java.util.Objects;

public class Compra implements java.io.Serializable {

	private static final long serialVersionUID = -1960658958115915096L;

	private int idCompra;
	private int idEmpleado;
	private int idProveedor;
	private String folioFactura;
	private Date fechaFactura;
	private Date fechaCompra;
	private boolean tipoCompra;
	private double subtotal;
	private double iva;
	private boolean activo;

	public Compra() {
		super();
	}

	public Compra(int idCompra, int idEmpleado, int idProveedor, String folioFactura, Date fechaFactura,
			Date fechaCompra, boolean tipoCompra, double subtotal, double iva, boolean activo) {
		super();
		this.idCompra = idCompra;
		this.idEmpleado = idEmpleado;
		this.idProveedor = idProveedor;
		this.folioFactura = folioFactura;
		this.fechaFactura = fechaFactura;
		this.fechaCompra = fechaCompra;
		this.tipoCompra = tipoCompra;
		this.subtotal = subtotal;
		this.iva = iva;
		this.activo = activo;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getFolioFactura() {
		return folioFactura;
	}

	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
	}

	public Date getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(Date fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public boolean isTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(boolean tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getIva() {
		return iva;
	}

	public void setIva(double iva) {
		this.iva = iva;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public double getImporteTotal() {
		return this.subtotal + this.iva;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folioFactura, idCompra, idEmpleado, idProveedor);
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
		Compra other = (Compra) obj;
		return Objects.equals(folioFactura, other.folioFactura) && idCompra == other.idCompra
				&& idEmpleado == other.idEmpleado && idProveedor == other.idProveedor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compra [idCompra=").append(idCompra).append(", idEmpleado=").append(idEmpleado)
				.append(", idProveedor=").append(idProveedor).append(", folioFactura=").append(folioFactura)
				.append(", fechaFactura=").append(fechaFactura).append(", fechaCompra=").append(fechaCompra)
				.append(", tipoCompra=").append(tipoCompra).append(", subtotal=").append(subtotal).append(", iva=")
				.append(iva).append(", activo=").append(activo).append("]");
		return builder.toString();
	}

	public static class CompraBuilder {

		private final Compra compra;

		public CompraBuilder() {
			this.compra = new Compra();
		}

		public CompraBuilder idCompra(int idCompra) {
			this.compra.idCompra = idCompra;
			return this;
		}

		public CompraBuilder idEmpleado(int idEmpleado) {
			this.compra.idEmpleado = idEmpleado;
			return this;
		}

		public CompraBuilder idProveedor(int idProveedor) {
			this.compra.idProveedor = idProveedor;
			return this;
		}

		public CompraBuilder folioFactura(String folioFactura) {
			this.compra.folioFactura = folioFactura;
			return this;
		}

		public CompraBuilder fechaFactura(Date fechaFactura) {
			this.compra.fechaFactura = fechaFactura;
			return this;
		}

		public CompraBuilder fechaCompra(Date fechaCompra) {
			this.compra.fechaCompra = fechaCompra;
			return this;
		}

		public CompraBuilder tipoCompra(boolean tipoCompra) {
			this.compra.tipoCompra = tipoCompra;
			return this;
		}

		public CompraBuilder subtotal(double subtotal) {
			this.compra.subtotal = subtotal;
			return this;
		}

		public CompraBuilder iva(double iva) {
			this.compra.iva = iva;
			return this;
		}

		public CompraBuilder activo(boolean activo) {
			this.compra.activo = activo;
			return this;
		}

		public Compra build() {
			return this.compra;
		}
	}
}
