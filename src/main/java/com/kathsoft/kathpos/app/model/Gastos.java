package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Gastos implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6306406954406205258L;
	/**
	 * 
	 */
	
	private int idGasto;
	private Date fechaOperacion;
	private int idEmpleado; //indice en tabla del empleado que realiza el gasto
	private String descripcion;
	private int idCuentaContable;
	private double importe;
	private double iva;
	
	public Gastos(int idGasto, Date fechaOperacion, int idEmpleado, String descripcion, int idCuentaContable, double importe,
			double iva) {
		super();
		this.setIdGasto(idGasto);
		this.setFechaOperacion(fechaOperacion);
		this.setIdEmpleado(idEmpleado);
		this.setDescripcion(descripcion);
		this.setIdCuentaContable(idCuentaContable);
		this.setImporte(importe);
		this.setIva(iva);
	}
	
	public Gastos() {
		
	}
	
	/**
	 * @return the idGasto
	 */
	public int getIdGasto() {
		return idGasto;
	}
	/**
	 * @return the fechaOperacion
	 */
	public Date getFechaOperacion() {
		return fechaOperacion;
	}
	/**
	 * @return the idEmpleado
	 */
	public int getIdEmpleado() {
		return idEmpleado;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @return the idCuentaContable
	 */
	public int getIdCuentaContable() {
		return idCuentaContable;
	}
	/**
	 * @return the importe
	 */
	public double getImporte() {
		return importe;
	}
	/**
	 * @return the iva
	 */
	public double getIva() {
		return iva;
	}
	/**
	 * @param idGasto the idGasto to set
	 */
	public void setIdGasto(int idGasto) {
		this.idGasto = idGasto;
	}
	/**
	 * @param fechaOperacion the fechaOperacion to set
	 */
	public void setFechaOperacion(Date fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}
	/**
	 * @param idEmpleado the idEmpleado to set
	 */
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @param idCuentaContable the idCuentaContable to set
	 */
	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(double importe) {
		this.importe = importe;
	}
	/**
	 * @param iva the iva to set
	 */
	public void setIva(double iva) {
		this.iva = iva;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idGasto, importe, iva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Gastos)) {
			return false;
		}
		Gastos other = (Gastos) obj;
		return idGasto == other.idGasto && Double.doubleToLongBits(importe) == Double.doubleToLongBits(other.importe)
				&& Double.doubleToLongBits(iva) == Double.doubleToLongBits(other.iva);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Gastos [getIdGasto()=");
		builder.append(getIdGasto());
		builder.append(", getFechaOperacion()=");
		builder.append(getFechaOperacion());
		builder.append(", getIdEmpleado()=");
		builder.append(getIdEmpleado());
		builder.append(", getDescripcion()=");
		builder.append(getDescripcion());
		builder.append(", getIdCuentaContable()=");
		builder.append(getIdCuentaContable());
		builder.append(", getImporte()=");
		builder.append(getImporte());
		builder.append(", getIva()=");
		builder.append(getIva());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
	

}
