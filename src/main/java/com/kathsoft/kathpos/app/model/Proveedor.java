/**
 * 
 */
package com.kathsoft.kathpos.app.model;

import java.sql.Date;

/**
 * @author PABLO
 *
 */
public class Proveedor extends Usuario {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = -7256299954773476546L;
	/**
	 * 
	 * 
	 */
	
	private String descripcion;
	
	/**
	 * 
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
	 * @param descripcion
	 */
	public Proveedor(int id, String rfc, int idCuentaContable, String claveCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal, String descripcion) {
		super(id, rfc, idCuentaContable, claveCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
		this.setDescripcion(descripcion);
	}

	/**
	 * 
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
	 */
	public Proveedor(int id, String rfc, int idCuentaContable, String claveCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal) {
		super(id, rfc, idCuentaContable, claveCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
		
		
		
	}	

	/**
	 * 
	 */
	public Proveedor() {
		
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}
		
}
