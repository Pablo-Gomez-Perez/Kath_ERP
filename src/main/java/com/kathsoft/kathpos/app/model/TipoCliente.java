package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Objects;

public class TipoCliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 788542100825201380L;
	/**
	 * 
	 * 
	 */
	private int idTipoCliente;
	private String nombre;
	private String descripcion;

	/**
	 * @param id_tipoCliente
	 * @param nombre
	 * @param descripcion
	 */
	public TipoCliente(int idTipoCliente, String nombre, String descripcion) {
		super();
		this.idTipoCliente = idTipoCliente;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	public TipoCliente(int idTipoCliente, String nombre) {
		this.idTipoCliente = idTipoCliente;
		this.nombre = nombre;
	}
	
	public TipoCliente() {

	}

	/**
	 * @return the id_tipoCliente
	 */
	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	/**
	 * @param id_tipoCliente the id_tipoCliente to set
	 */
	public void setId_tipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
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

	@Override
	public int hashCode() {
		return Objects.hash(idTipoCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof TipoCliente)) {
			return false;
		}
		TipoCliente other = (TipoCliente) obj;
		return idTipoCliente == other.idTipoCliente;
	}

	@Override
	public String toString() {
		return "TipoCliente [id_tipoCliente=" + idTipoCliente + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ "]";
	}

}
