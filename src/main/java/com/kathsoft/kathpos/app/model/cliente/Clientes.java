/**
 * 
 */
package com.kathsoft.kathpos.app.model.cliente;

import java.sql.Date;
import java.util.Objects;

/**
 * @author PABLO
 *
 */
public class Clientes {

	/**
	 * 
	 */

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

	public Clientes(int idCliente, int idTipoCliente, int idCuentaContable, String rfc, String nombreCompleto,
			String nombreCorto, Date fechaNac, String correoElectronico, String estado, String ciudad, String direccion,
			String codigoPostal, boolean activo) {
		super();
		this.idCliente = idCliente;
		this.idTipoCliente = idTipoCliente;
		this.idCuentaContable = idCuentaContable;
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

	public Clientes() {

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

	public int getIdCuentaContable() {
		return idCuentaContable;
	}

	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	public int getId() {
		return getIdCliente();
	}

	public void setId(int id) {
		setIdCliente(id);
	}

	public String getClaveCuentaContable() {
		return claveCuentaContable;
	}

	public void setClaveCuentaContable(String claveCuentaContable) {
		this.claveCuentaContable = claveCuentaContable;
	}

	public String getNombre() {
		return getNombreCompleto();
	}

	public void setNombre(String nombre) {
		setNombreCompleto(nombre);
	}

	public Date getFechaNacimiento() {
		return getFechaNac();
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		setFechaNac(fechaNacimiento);
	}

	public String getEmail() {
		return getCorreoElectronico();
	}

	public void setEmail(String email) {
		setCorreoElectronico(email);
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
		return "Clientes [idCliente=" + idCliente + ", idTipoCliente=" + idTipoCliente + ", idCuentaContable="
				+ idCuentaContable + ", rfc=" + rfc + ", nombreCompleto=" + nombreCompleto + ", nombreCorto="
				+ nombreCorto + ", fechaNac=" + fechaNac + ", correoElectronico=" + correoElectronico + ", estado="
				+ estado + ", ciudad=" + ciudad + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal
				+ ", activo=" + activo + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(Boolean.valueOf(activo), ciudad, codigoPostal, correoElectronico, direccion, estado,
				fechaNac, Integer.valueOf(idCliente), Integer.valueOf(idCuentaContable), Integer.valueOf(idTipoCliente),
				nombreCompleto, nombreCorto, rfc);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Clientes other = (Clientes) obj;
		return activo == other.activo && Objects.equals(ciudad, other.ciudad)
				&& Objects.equals(codigoPostal, other.codigoPostal)
				&& Objects.equals(correoElectronico, other.correoElectronico)
				&& Objects.equals(direccion, other.direccion) && Objects.equals(estado, other.estado)
				&& Objects.equals(fechaNac, other.fechaNac) && idCliente == other.idCliente
				&& idCuentaContable == other.idCuentaContable && idTipoCliente == other.idTipoCliente
				&& Objects.equals(nombreCompleto, other.nombreCompleto)
				&& Objects.equals(nombreCorto, other.nombreCorto) && Objects.equals(rfc, other.rfc);
	}

}
