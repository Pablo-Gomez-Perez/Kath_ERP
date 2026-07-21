package com.kathsoft.kathpos.app.model;

public class TelefonoEmpleado {

	private int idTelefono;
	private int idEmpleado;
	private String telefono;

	/**
	 * @param idTelefono
	 * @param idEmpleado
	 * @param telefono
	 */
	public TelefonoEmpleado(int idTelefono, int idEmpleado, String telefono) {
		super();
		this.idTelefono = idTelefono;
		this.idEmpleado = idEmpleado;
		this.telefono = telefono;
	}

	public int getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TelefonoEmpleado [idTelefono=").append(idTelefono).append(", idEmpleado=").append(idEmpleado)
				.append(", telefono=").append(telefono).append("]");
		return builder.toString();
	}

}
