package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Compras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8924351278749112566L;
	/**
	 * 
	 * 
	 */
	private int idCompra;
	private String folioFactura;
	private Date fechaCompra;
	private boolean tipoCompra; //indica si la compra fué a credito o a contado
	private int idEmpleado;
	private int idProveedor;
	private double subtotal;
	private double iva;
	
	public Compras(int idCompra, String folioFactura, Date fechaCompra, boolean tipoCompra, int idEmpleado,
			int idProveedor, double subtotal, double iva) {
		super();
		this.setIdCompra(idCompra);
		this.setFolioFactura(folioFactura);
		this.setFechaCompra(fechaCompra);
		this.setTipoCompra(tipoCompra);
		this.setIdEmpleado(idEmpleado);
		this.setIdProveedor(idProveedor);
		this.setSubtotal(subtotal);
		this.setIva(iva);
	}
	
	public Compras() {
		
	}
	
	/**
	 * @return the idCompra
	 */
	public int getIdCompra() {
		return idCompra;
	}
	/**
	 * @param idCompra the idCompra to set
	 */
	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}
	/**
	 * @return the folioFactura
	 */
	public String getFolioFactura() {
		return folioFactura;
	}
	/**
	 * @param folioFactura the folioFactura to set
	 */
	public void setFolioFactura(String folioFactura) {
		this.folioFactura = folioFactura;
	}
	/**
	 * @return the fechaCompra
	 */
	public Date getFechaCompra() {
		return fechaCompra;
	}
	/**
	 * @param fechaCompra the fechaCompra to set
	 */
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	/**
	 * @return the tipoCompra
	 */
	public boolean isTipoCompra() {
		return tipoCompra;
	}
	/**
	 * @param tipoCompra the tipoCompra to set
	 */
	public void setTipoCompra(boolean tipoCompra) {
		this.tipoCompra = tipoCompra;
	}
	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}
	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	/**
	 * @return the idProveedor
	 */
	public int getIdProveedor() {
		return idProveedor;
	}
	/**
	 * @param idProveedor the idProveedor to set
	 */
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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
	/**
	 * @return the iva
	 */
	public double getIva() {
		return iva;
	}
	/**
	 * @param iva the iva to set
	 */
	public void setIva(double iva) {
		this.iva = iva;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCompra);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Compras)) {
			return false;
		}
		Compras other = (Compras) obj;
		return idCompra == other.idCompra;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Compras [getIdCompra()=");
		builder.append(getIdCompra());
		builder.append(", getFolioFactura()=");
		builder.append(getFolioFactura());
		builder.append(", getFechaCompra()=");
		builder.append(getFechaCompra());
		builder.append(", isTipoCompra()=");
		builder.append(isTipoCompra());
		builder.append(", getIdEmpleado()=");
		builder.append(getIdEmpleado());
		builder.append(", getIdProveedor()=");
		builder.append(getIdProveedor());
		builder.append(", getSubtotal()=");
		builder.append(getSubtotal());
		builder.append(", getIva()=");
		builder.append(getIva());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
	
}
