package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Objects;

public class Sucursal implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4720965943518948196L;
	/**
	 * 
	 * 
	 * 
	 */
	private int idSucursal;
	private String nombre;
	private String descripcion;
	private String telefono;
	private String email;
	private String estado;
	private String ciudad;
	private String direccion;
	private String codigoPostal;
	
	/**
	 * @param idSucursal
	 * @param nombre
	 * @param descripcion
	 * @param telefono
	 * @param email
	 * @param estado
	 * @param ciudad
	 * @param direccion
	 * @param codigoPostal
	 */
	public Sucursal(int idSucursal, String nombre, String descripcion, String telefono, String email, String estado,
			String ciudad, String direccion, String codigoPostal) {
		super();
		this.idSucursal = idSucursal;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.telefono = telefono;
		this.email = email;
		this.estado = estado;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
	}
	
	public Sucursal() {
		
	}

	/**
	 * @return the idSucursal
	 */
	public int getIdSucursal() {
		return idSucursal;
	}

	/**
	 * @param idSucursal the idSucursal to set
	 */
	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the codigoPostal
	 */
	public String getCodigoPostal() {
		return codigoPostal;
	}

	/**
	 * @param codigoPostal the codigoPostal to set
	 */
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idSucursal, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Sucursal)) {
			return false;
		}
		Sucursal other = (Sucursal) obj;
		return idSucursal == other.idSucursal && Objects.equals(nombre, other.nombre);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sucursal [idSucursal=");
		builder.append(idSucursal);
		builder.append(", nombre=");
		builder.append(nombre);
		builder.append(", descripcion=");
		builder.append(descripcion);
		builder.append(", telefono=");
		builder.append(telefono);
		builder.append(", email=");
		builder.append(email);
		builder.append(", estado=");
		builder.append(estado);
		builder.append(", ciudad=");
		builder.append(ciudad);
		builder.append(", direccion=");
		builder.append(direccion);
		builder.append(", codigoPostal=");
		builder.append(codigoPostal);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
