package com.kathsoft.kathpos.app.model.compra;

import java.sql.Date;
import java.util.Objects;

public class CompraById implements java.io.Serializable {

	private static final long serialVersionUID = 4860961685137312820L;

	private int idCompra;
	private int idEmpleado;
	private String nombreEmpleado;
	private String nombreCortoEmpleado;
	private int idSucursal;
	private int idProveedor;
	private String folioFactura;
	private Date fechaFactura;
	private Date fechaCompra;
	private boolean tipoCompra;
	private String tipoCompraDescripcion;
	private double subtotal;
	private double iva;
	private double importeTotal;
	private boolean activo;

	public CompraById() {
		super();
	}

	public CompraById(int idCompra, int idEmpleado, String nombreEmpleado, String nombreCortoEmpleado, int idSucursal,
			int idProveedor, String folioFactura, Date fechaFactura, Date fechaCompra, boolean tipoCompra,
			String tipoCompraDescripcion, double subtotal, double iva, double importeTotal, boolean activo) {
		super();
		this.idCompra = idCompra;
		this.idEmpleado = idEmpleado;
		this.nombreEmpleado = nombreEmpleado;
		this.nombreCortoEmpleado = nombreCortoEmpleado;
		this.idSucursal = idSucursal;
		this.idProveedor = idProveedor;
		this.folioFactura = folioFactura;
		this.fechaFactura = fechaFactura;
		this.fechaCompra = fechaCompra;
		this.tipoCompra = tipoCompra;
		this.tipoCompraDescripcion = tipoCompraDescripcion;
		this.subtotal = subtotal;
		this.iva = iva;
		this.importeTotal = importeTotal;
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

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getNombreCortoEmpleado() {
		return nombreCortoEmpleado;
	}

	public void setNombreCortoEmpleado(String nombreCortoEmpleado) {
		this.nombreCortoEmpleado = nombreCortoEmpleado;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
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

	public String getTipoCompraDescripcion() {
		return tipoCompraDescripcion;
	}

	public void setTipoCompraDescripcion(String tipoCompraDescripcion) {
		this.tipoCompraDescripcion = tipoCompraDescripcion;
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

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folioFactura, idCompra, idProveedor);
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
		CompraById other = (CompraById) obj;
		return Objects.equals(folioFactura, other.folioFactura) && idCompra == other.idCompra
				&& idProveedor == other.idProveedor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompraById [idCompra=").append(idCompra).append(", idEmpleado=").append(idEmpleado)
				.append(", nombreEmpleado=").append(nombreEmpleado).append(", nombreCortoEmpleado=")
				.append(nombreCortoEmpleado).append(", idSucursal=").append(idSucursal).append(", idProveedor=")
				.append(idProveedor).append(", folioFactura=").append(folioFactura).append(", fechaFactura=")
				.append(fechaFactura).append(", fechaCompra=").append(fechaCompra).append(", tipoCompra=")
				.append(tipoCompra).append(", tipoCompraDescripcion=").append(tipoCompraDescripcion).append(", subtotal=")
				.append(subtotal).append(", iva=").append(iva).append(", importeTotal=").append(importeTotal)
				.append(", activo=").append(activo).append("]");
		return builder.toString();
	}
}
