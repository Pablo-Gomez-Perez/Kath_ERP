package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Objects;

public class Categoria implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7109716269722067761L;
	/**
	 * 
	 * 
	 */
	private int idCategoria;
	private String nombre;
	private String descripcion;
	
	public Categoria(int idCategoria, String nombre, String descripcion) {
		this.setIdCategoria(idCategoria);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
	}
	
	public Categoria() {
		
	}
	
	/**
	 * @return the idCategoria
	 */
	public int getIdCategoria() {
		return idCategoria;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Categoria)) {
			return false;
		}
		Categoria other = (Categoria) obj;
		return idCategoria == other.idCategoria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [getIdCategoria()=");
		builder.append(getIdCategoria());
		builder.append(", getNombre()=");
		builder.append(getNombre());
		builder.append(", getDescripcion()=");
		builder.append(getDescripcion());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
