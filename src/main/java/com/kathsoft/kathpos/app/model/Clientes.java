/**
 * 
 */
package com.kathsoft.kathpos.app.model;

import java.util.Date;

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
	 * @param nombreCorto
	 */
	public Clientes(int id, String rfc, int idCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal, String nombreCorto) {
		super(id, rfc, idCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
		this.nombreCorto = nombreCorto;
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
	public Clientes(int id, String rfc, int idCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal) {
		super(id, rfc, idCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
	}



	/**
	 * 
	 */
	public Clientes() {
		// TODO Auto-generated constructor stub
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Clientes [getIdCtaContable()=");
		builder.append(", getNombreCorto()=");
		builder.append(getNombreCorto());
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	

}
