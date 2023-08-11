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
	private Empleado empleado;
	private Clientes cliente;
	private double subTotal;
	private double iva;
	private double total;
	private boolean statusVenta; //describe si la venta está vigente o cancelada;
	
	
	
	/**
	 * @param idVenta
	 * @param fechaVenta
	 * @param tipoVenta
	 * @param empleado
	 * @param cliente
	 * @param subTotal
	 * @param iva
	 * @param total
	 * @param statusVenta
	 */
	public Ventas(int idVenta, Date fechaVenta, boolean tipoVenta, Empleado empleado, Clientes cliente, double subTotal,
			double iva, double total, boolean statusVenta) {
		super();
		this.idVenta = idVenta;
		this.fechaVenta = fechaVenta;
		this.tipoVenta = tipoVenta;
		this.empleado = empleado;
		this.cliente = cliente;
		this.subTotal = subTotal;
		this.iva = iva;
		this.total = total;
		this.statusVenta = statusVenta;
	}
	
	/**
	 * void constructor
	 */
	public Ventas() {
		
	}
	
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
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}
	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	/**
	 * @return the cliente
	 */
	public Clientes getCliente() {
		return cliente;
	}
	/**
	 * @param cliente the cliente to set
	 */
	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
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
	
	
		
	
}
