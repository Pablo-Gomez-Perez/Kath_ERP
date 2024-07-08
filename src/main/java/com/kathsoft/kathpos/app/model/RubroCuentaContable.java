package com.kathsoft.kathpos.app.model;

import java.io.Serializable;

public class RubroCuentaContable implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8110124364161430672L;
	/**
	 * 
	 * 
	 */
	private int idRubro;
	private String nombre;
	private String descripcion;
	private boolean naturaleza;
	public RubroCuentaContable(int idRubro, String nombre, String descripcion, boolean naturaleza) {
		super();
		this.idRubro = idRubro;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.naturaleza = naturaleza;
	}
	
	public RubroCuentaContable() {
		
	}

	public int getIdRubro() {
		return idRubro;
	}

	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isNaturaleza() {
		return naturaleza;
	}

	public void setNaturaleza(boolean naturaleza) {
		this.naturaleza = naturaleza;
	}

	@Override
	public String toString() {
		return this.nombre;
	}
	
	
	
}
