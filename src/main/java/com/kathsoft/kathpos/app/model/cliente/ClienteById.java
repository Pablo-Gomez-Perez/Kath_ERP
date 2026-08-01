package com.kathsoft.kathpos.app.model.cliente;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class ClienteById implements Serializable {

	private static final long serialVersionUID = 1L;

	private int idCliente;
	private int idTipoCliente;
	private int idCuentaContable;
	private String claveCuentaContable;
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

	public int getIdCuentaContable() {
		return idCuentaContable;
	}

	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	public String getClaveCuentaContable() {
		return claveCuentaContable;
	}

	public void setClaveCuentaContable(String claveCuentaContable) {
		this.claveCuentaContable = claveCuentaContable;
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
	public int hashCode() {
		return Objects.hash(Boolean.valueOf(activo), ciudad, codigoPostal, correoElectronico, direccion, estado,
				fechaNac, Integer.valueOf(idCliente), Integer.valueOf(idCuentaContable), Integer.valueOf(idTipoCliente),
				claveCuentaContable, nombreCompleto, nombreCorto, rfc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ClienteById)) {
			return false;
		}
		ClienteById other = (ClienteById) obj;
		return activo == other.activo && idCliente == other.idCliente && idTipoCliente == other.idTipoCliente
				&& idCuentaContable == other.idCuentaContable && Objects.equals(claveCuentaContable, other.claveCuentaContable)
				&& Objects.equals(rfc, other.rfc) && Objects.equals(nombreCompleto, other.nombreCompleto)
				&& Objects.equals(nombreCorto, other.nombreCorto) && Objects.equals(fechaNac, other.fechaNac)
				&& Objects.equals(correoElectronico, other.correoElectronico) && Objects.equals(estado, other.estado)
				&& Objects.equals(ciudad, other.ciudad) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(codigoPostal, other.codigoPostal);
	}
}
