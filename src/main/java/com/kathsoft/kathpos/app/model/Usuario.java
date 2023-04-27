package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Usuario implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -60673317657507062L;
	/**
	 * 
	 * 
	 */
	private int id;
	private String rfc;
	private int idCuentaContable;
	private String nombre;
	private Date fechaNacimiento;
	private String email;
	private String estado;
	private String ciudad;
	private String direccion;
	private String codigoPostal;
	
	/**
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
	public Usuario(int id, String rfc, int idCuentaContable, String nombre, Date fechaNacimiento, String email, String estado, String ciudad,
			String direccion, String codigoPostal) {
		super();
		this.setId(id);
		this.setRfc(rfc);
		this.setIdCuentaContable(idCuentaContable);
		this.setNombre(nombre);
		this.setFechaNacimiento(fechaNacimiento);
		this.setEmail(email);
		this.setEstado(estado);
		this.setCiudad(ciudad);
		this.setDireccion(direccion);
		this.setCodigoPostal(codigoPostal);
	}
	
	public Usuario() {}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the rfc
	 */
	public String getRfc() {
		return rfc;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}
	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param rfc the rfc to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, rfc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Usuario)) {
			return false;
		}
		Usuario other = (Usuario) obj;
		return id == other.id && Objects.equals(rfc, other.rfc);
	}
	
	

	/**
	 * @return the idCuentaContable
	 */
	public int getIdCuentaContable() {
		return idCuentaContable;
	}

	/**
	 * @param idCuentaContable the idCuentaContable to set
	 */
	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [getId()=");
		builder.append(getId());
		builder.append(", getRfc()=");
		builder.append(getRfc());
		builder.append(", getNombre()=");
		builder.append(getNombre());
		builder.append(", getFechaNacimiento()=");
		builder.append(getFechaNacimiento());
		builder.append(", getEmail()=");
		builder.append(getEmail());
		builder.append(", getEstado()=");
		builder.append(getEstado());
		builder.append(", getCiudad()=");
		builder.append(getCiudad());
		builder.append(", getDireccion()=");
		builder.append(getDireccion());
		builder.append(", getCodigoPostal()=");
		builder.append(getCodigoPostal());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}

	
}
