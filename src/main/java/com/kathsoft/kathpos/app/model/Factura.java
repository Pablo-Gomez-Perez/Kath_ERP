package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * 
 * @author PABLO
 *
 */
public class Factura implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 695239369394640071L;
	/**
	 * 
	 * 
	 */

	private int idFactura;
	private String folioFiscal;
	private Date fechaEmision;
	private Date fechaCertificacion;
	private int idVenta;
	
	/**
	 * 
	 * @param idFactura
	 * @param folioFiscal
	 * @param fechaEmision
	 * @param FechaCertificacion
	 * @param idVenta
	 */
	public Factura(int idFactura, String folioFiscal, Date fechaEmision, Date FechaCertificacion, int idVenta) {
		super();
		this.setIdFactura(idFactura);
		this.setFolioFiscal(folioFiscal);
		this.setFechaEmision(fechaEmision);
		this.setFechaCertificacion(FechaCertificacion);
		this.setIdVenta(idVenta);
	}

	public Factura() {

	}

	/**
	 * @return the idFactura
	 */
	public int getIdFactura() {
		return idFactura;
	}

	/**
	 * @return the folioFiscal
	 */
	public String getFolioFiscal() {
		return folioFiscal;
	}

	/**
	 * @return the fechaEmision
	 */
	public Date getFechaEmision() {
		return fechaEmision;
	}

	/**
	 * @return the fechaCertificacion
	 */
	public Date getFechaCertificacion() {
		return fechaCertificacion;
	}

	/**
	 * @return the idVenta
	 */
	public int getIdVenta() {
		return idVenta;
	}

	/**
	 * @param idFactura the idFactura to set
	 */
	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	/**
	 * @param folioFiscal the folioFiscal to set
	 */
	public void setFolioFiscal(String folioFiscal) {
		this.folioFiscal = folioFiscal;
	}

	/**
	 * @param fechaEmision the fechaEmision to set
	 */
	public void setFechaEmision(Date fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	/**
	 * @param fechaCertificacion the fechaCertificacion to set
	 */
	public void setFechaCertificacion(Date fechaCertificacion) {
		this.fechaCertificacion = fechaCertificacion;
	}

	/**
	 * @param idVenta the idVenta to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}

	@Override
	public int hashCode() {
		return Objects.hash(folioFiscal, idFactura, idVenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Factura)) {
			return false;
		}
		Factura other = (Factura) obj;
		return Objects.equals(folioFiscal, other.folioFiscal) && idFactura == other.idFactura
				&& idVenta == other.idVenta;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Factura [getIdFactura()=");
		builder.append(getIdFactura());
		builder.append(", getFolioFiscal()=");
		builder.append(getFolioFiscal());
		builder.append(", getFechaEmision()=");
		builder.append(getFechaEmision());
		builder.append(", getFechaCertificacion()=");
		builder.append(getFechaCertificacion());
		builder.append(", getIdVenta()=");
		builder.append(getIdVenta());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
	

}
