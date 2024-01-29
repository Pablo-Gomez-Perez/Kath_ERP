/**
 * 
 */
package com.kathsoft.kathpos.app.model;

import java.sql.Date;

/**
 * @author PABLO
 *
 */
public class Clientes extends Usuario {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8677714515131885067L;
	/**
	 * 
	 */
	private String nombreCorto;
	private String descripcion;
	private int idTipoCliente;
	

	/**
	 * 
	 * @param id
	 * @param rfc
	 * @param idCuentaContable
	 * @param claveCuentaContable
	 * @param nombre
	 * @param fechaNacimiento
	 * @param email
	 * @param estado
	 * @param ciudad
	 * @param direccion
	 * @param codigoPostal
	 * @param nombreCorto
	 */
	public Clientes(int id, String rfc, int idCuentaContable, String claveCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal, String nombreCorto, String descripcion, int idTipoCliente) {
		super(id, rfc, idCuentaContable, claveCuentaContable,nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
		this.nombreCorto = nombreCorto;
		this.descripcion = descripcion;
		this.idTipoCliente = idTipoCliente;
	}



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
	 */
	public Clientes(int id, String rfc, int idCuentaContable, String claveCuentaContable,String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal) {
		super(id, rfc, idCuentaContable, claveCuentaContable,nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
	}

	/**
	 * constructor vacío
	 */
	public Clientes() {
		
	}

	/**
	 * @return the nombreCorto
	 */
	public String getNombreCorto() {
		return nombreCorto;
	}

	/**
	 * @param nombreCorto the nombreCorto to set
	 */
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
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
	
	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	
	public int getIdTipoCliente() {
		return this.idTipoCliente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Clientes [getNombreCorto()=");
		builder.append(getNombreCorto());
		builder.append(", getDescripcion()=");
		builder.append(getDescripcion());
		builder.append("]");
		return builder.toString();
	}

	

}
