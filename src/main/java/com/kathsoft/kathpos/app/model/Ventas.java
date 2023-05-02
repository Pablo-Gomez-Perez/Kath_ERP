package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Date;

public class Ventas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6942302852728487438L;
	/**
	 * 
	 * 
	 * 
	 */
	
	private int idVenta;
	private Date fechaVenta;
	private boolean tipoVenta; //para describir si la venta es a contado o credito;
	private int idEmpleado;
	private int idCliente;
	private double subTotal;
	private double iva;
	private double total;
	private boolean statusVenta; //describe si la venta está vigente o cancelada;
	
	/**
	 * @param idVenta
	 * @param fechaVenta
	 * @param tipoVenta
	 * @param idEmpleado
	 * @param idCliente
	 * @param subTotal
	 * @param iva
	 * @param total
	 * @param statusVenta
	 */
	public Ventas(int idVenta, Date fechaVenta, boolean tipoVenta, int idEmpleado, int idCliente, double subTotal,
			double iva, double total, boolean statusVenta) {
		super();
		this.idVenta = idVenta;
		this.fechaVenta = fechaVenta;
		this.tipoVenta = tipoVenta;
		this.idEmpleado = idEmpleado;
		this.idCliente = idCliente;
		this.subTotal = subTotal;
		this.iva = iva;
		this.total = total;
		this.statusVenta = statusVenta;
	}
	
	public Ventas() {}
	
	/**
	 * @return the idVenta
	 */
	public int getIdVenta() {
		return idVenta;
	}
	/**
	 * @param idVenta the idVenta to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	/**
	 * @return the fechaVenta
	 */
	public Date getFechaVenta() {
		return fechaVenta;
	}
	/**
	 * @param fechaVenta the fechaVenta to set
	 */
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	/**
	 * @return the tipoVenta
	 */
	public boolean isTipoVenta() {
		return tipoVenta;
	}
	/**
	 * @param tipoVenta the tipoVenta to set
	 */
	public void setTipoVenta(boolean tipoVenta) {
		this.tipoVenta = tipoVenta;
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
	 * @return the idCliente
	 */
	public int getIdCliente() {
		return idCliente;
	}
	/**
	 * @param idCliente the idCliente to set
	 */
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	/**
	 * @return the subTotal
	 */
	public double getSubTotal() {
		return subTotal;
	}
	/**
	 * @param subTotal the subTotal to set
	 */
	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
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
	/**
	 * @return the total
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/**
	 * @return the statusVenta
	 */
	public boolean isStatusVenta() {
		return statusVenta;
	}
	/**
	 * @param statusVenta the statusVenta to set
	 */
	public void setStatusVenta(boolean statusVenta) {
		this.statusVenta = statusVenta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idCliente;
		result = prime * result + idEmpleado;
		result = prime * result + idVenta;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ventas)) {
			return false;
		}
		Ventas other = (Ventas) obj;
		if (idCliente != other.idCliente) {
			return false;
		}
		if (idEmpleado != other.idEmpleado) {
			return false;
		}
		if (idVenta != other.idVenta) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ventas [idVenta=");
		builder.append(idVenta);
		builder.append(", fechaVenta=");
		builder.append(fechaVenta);
		builder.append(", tipoVenta=");
		builder.append(tipoVenta);
		builder.append(", idEmpleado=");
		builder.append(idEmpleado);
		builder.append(", idCliente=");
		builder.append(idCliente);
		builder.append(", subTotal=");
		builder.append(subTotal);
		builder.append(", iva=");
		builder.append(iva);
		builder.append(", total=");
		builder.append(total);
		builder.append(", statusVenta=");
		builder.append(statusVenta);
		builder.append("]");
		return builder.toString();
	}
		
	
}
