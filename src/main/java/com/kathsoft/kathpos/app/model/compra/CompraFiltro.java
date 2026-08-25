package com.kathsoft.kathpos.app.model.compra;

import java.sql.Date;

public class CompraFiltro implements java.io.Serializable {

	private static final long serialVersionUID = 3972539890065932361L;

	private int idProveedor;
	private Date fechaFacturaInicio;
	private Date fechaFacturaFin;
	private String folioFactura;
	private Boolean tipoCompra;

	public CompraFiltro() {
		super();
	}

	public CompraFiltro(int idProveedor, Date fechaFacturaInicio, Date fechaFacturaFin, String folioFactura,
			Boolean tipoCompra) {
		super();
		this.idProveedor = idProveedor;
		this.fechaFacturaInicio = fechaFacturaInicio;
		this.fechaFacturaFin = fechaFacturaFin;
		this.folioFactura = folioFactura;
		this.tipoCompra = tipoCompra;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public Date getFechaFacturaInicio() {
		return fechaFacturaInicio;
	}

	public void setFechaFacturaInicio(Date fechaFacturaInicio) {
		this.fechaFacturaInicio = fechaFacturaInicio;
	}

	public Date getFechaFacturaFin() {
		return fechaFacturaFin;
	}

	public void setFechaFacturaFin(Date fechaFacturaFin) {
		this.fechaFacturaFin = fechaFacturaFin;
	}

	public String getFolioFactura() {
		return folioFactura;
	}

	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
	}

	public Boolean getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(Boolean tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompraFiltro [idProveedor=").append(idProveedor).append(", fechaFacturaInicio=")
				.append(fechaFacturaInicio).append(", fechaFacturaFin=").append(fechaFacturaFin)
				.append(", folioFactura=").append(folioFactura).append(", tipoCompra=").append(tipoCompra).append("]");
		return builder.toString();
	}
}
