/**
 * 
 */
package com.kathsoft.kathpos.app.model;

import java.util.Date;

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
	private String curp;
	private String nombreCorto;
	private String password;
	
	/**
	 * constructor propio de la clase
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
	 * @param curp
	 * @param nombreCorto
	 */
	public Empleado(int id, String rfc, int idCuentaContable, String nombre, Date fechaNacimiento, String email,
			String estado, String ciudad, String direccion, String codigoPostal, String curp, String nombreCorto) {
		super(id, rfc, idCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);
		this.setCurp(curp);
		this.setNombreCorto(nombreCorto);
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
	public Empleado(int id, String rfc, int idCuentaContable, String nombre, Date fechaNacimiento, String email, String estado, String ciudad,
			String direccion, String codigoPostal) {
		super(id, rfc, idCuentaContable, nombre, fechaNacimiento, email, estado, ciudad, direccion, codigoPostal);		
	}

	/**
	 * 
	 */
	public Empleado() {
		
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
		builder.append("Empleado [getCurp()=");
		builder.append(getCurp());
		builder.append(", getNombreCorto()=");
		builder.append(getNombreCorto());
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}
	
	
}
