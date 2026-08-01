package com.kathsoft.kathpos.app.model.proveedor;

/**
 * Representa un numero telefonico asociado a un proveedor.
 *
 * @author PABLO
 */
public class TelefonoProveedor {

	private int idTelefono;
	private int idProveedor;
	private String telefono;

	public TelefonoProveedor() {
		super();
	}

	public TelefonoProveedor(int idTelefono, int idProveedor, String telefono) {
		super();
		this.idTelefono = idTelefono;
		this.idProveedor = idProveedor;
		this.telefono = telefono;
	}

	public int getIdTelefono() {
		return idTelefono;
	}

	public void setIdTelefono(int idTelefono) {
		this.idTelefono = idTelefono;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString() {
		return "TelefonoProveedor [idTelefono=" + idTelefono + ", idProveedor=" + idProveedor + ", telefono="
				+ telefono + "]";
	}
}
