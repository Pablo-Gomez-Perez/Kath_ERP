package com.kathsoft.kathpos.app.model.cliente;

import java.sql.Date;

/**
 * DTO con el resultado de getClienteParaVentaById.
 */
public class ClienteEnVentaById {

	private int idCliente;
	private int idTipoCliente;
	private String tipoCliente;
	private String rfc;
	private String nombreCompleto;
	private String nombreCorto;
	private Date fechaNac;
	private String correoElectronico;
	private String estado;
	private String ciudad;
	private String direccion;
	private String codigoPostal;
	private boolean activo;

	public ClienteEnVentaById(int idCliente, int idTipoCliente, String tipoCliente, String rfc,
			String nombreCompleto, String nombreCorto, Date fechaNac, String correoElectronico,
			String estado, String ciudad, String direccion, String codigoPostal, boolean activo) {
		this.idCliente = idCliente;
		this.idTipoCliente = idTipoCliente;
		this.tipoCliente = tipoCliente;
		this.rfc = rfc;
		this.nombreCompleto = nombreCompleto;
		this.nombreCorto = nombreCorto;
		this.fechaNac = fechaNac;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.activo = activo;
	}

	public ClienteEnVentaById() {
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "ClienteEnVentaById [idCliente=" + idCliente + ", idTipoCliente=" + idTipoCliente
				+ ", tipoCliente=" + tipoCliente + ", rfc=" + rfc + ", nombreCompleto=" + nombreCompleto
				+ ", nombreCorto=" + nombreCorto + ", fechaNac=" + fechaNac + ", correoElectronico="
				+ correoElectronico + ", estado=" + estado + ", ciudad=" + ciudad + ", direccion=" + direccion
				+ ", codigoPostal=" + codigoPostal + ", activo=" + activo + "]";
	}
}
