/**
 * 
 */
package com.kathsoft.kathpos.app.model;

import java.sql.Date;

/**
 * @author PABLO
 *
 */
public class Empleado extends Usuario {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2442006097902160313L;
	/**
	 * 
	 */
	private int idSucursal;
	private String curp;
	private String nombreCorto;
	private String password;
	
	

	/**
	 * @param id
	 * @param rfc
	 * @param idCuentaContable
	 * @param nombre
	 * @param fechaNacimiento
	 * @param email
	 * @param estado
	 * @param ciudad
	 * @param direccion
	 * @param codigoPostal
	 * @param curp
	 * @param nombreCorto
	 * @param password
	 */
	public Empleado(int id, int idSucursal, String rfc, int idCuentaContable, String claveCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal, String curp, String nombreCorto,
			String password) {
		super(id, rfc, idCuentaContable, claveCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
		this.idSucursal = idSucursal;
		this.curp = curp;
		this.nombreCorto = nombreCorto;
		this.password = password;
	}

	/**
	 * contructor heredado
	 * 
	 * @param id
	 * @param rfc
	 * @param nombre
	 * @param fechaNacimiento
	 * @param email
	 * @param estado
	 * @param ciudad
	 * @param direccion
	 * @param codigoPostal
	 */
	public Empleado(int id, String rfc, int idCuentaContable, String claveCuentaContable, String nombre, Date fechaNacimiento, String email, String estado, String ciudad,
			String direccion, String codigoPostal) {
		super(id, rfc, idCuentaContable, claveCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);		
	}

	/**
	 * 
	 */
	public Empleado() {
		
	}
	
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}
	
	public int getIdSucursal() {
		return this.idSucursal;
	}
	
	/**
	 * @return the curp
	 */
	public String getCurp() {
		return curp;
	}

	/**
	 * @return the nombreCorto
	 */
	public String getNombreCorto() {
		return nombreCorto;
	}

	/**
	 * @param curp the curp to set
	 */
	public void setCurp(String curp) {
		this.curp = curp;
	}

	/**
	 * @param nombreCorto the nombreCorto to set
	 */
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empleado [idSucursal=");
		builder.append(idSucursal);
		builder.append(", curp=");
		builder.append(curp);
		builder.append(", nombreCorto=");
		builder.append(nombreCorto);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
