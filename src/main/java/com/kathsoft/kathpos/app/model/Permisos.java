package com.kathsoft.kathpos.app.model;

import java.io.Serializable;

/**
 * 
 * consulta los permisos agregados a la base de datos y valida su existencia,
 * administra los permisos de cada empleado y verifica si está habilitado o no.
 * 
 * @author PABLO
 *
 */
public class Permisos implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7401903789707538104L;
	/**
	 * 
	 * 
	 */
	private int id_permiso;
	private String nombre;
	private String descripcion;
	
	public Permisos(int id_permiso, String nombre, String descripcion) {
		super();
		this.setId_permiso(id_permiso);
		this.setNombre(nombre);
		this.setDescripcion(descripcion);
	}
	
	public Permisos() {}
	
	/**
	 * @return the id_permiso
	 */
	public int getId_permiso() {
		return id_permiso;
	}
	/**
	 * @param id_permiso the id_permiso to set
	 */
	public void setId_permiso(int id_permiso) {
		this.id_permiso = id_permiso;
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
	
	
}
