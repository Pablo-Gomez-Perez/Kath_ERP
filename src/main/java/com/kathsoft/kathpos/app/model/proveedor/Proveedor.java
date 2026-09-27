/**
 * 
 */
package com.kathsoft.kathpos.app.model.proveedor;

/**
 * Modelo operativo de proveedor.
 *
 * @author PABLO
 */
public class Proveedor {

	private int idProveedor;
	private String rfc;
	private String nombre;
	private String descripcion;
	private String correoElectronico;
	private String estado;
	private String ciudad;
	private String direccion;
	private String codigoPostal;
	private boolean activo;

	public Proveedor(int idProveedor, String rfc, String nombre, String descripcion,
			String correoElectronico, String estado, String ciudad, String direccion, String codigoPostal,
			boolean activo) {
		this.idProveedor = idProveedor;
		this.rfc = rfc;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.activo = activo;
	}

	public Proveedor() {
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
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
		return "Proveedor [idProveedor=" + idProveedor + ", rfc=" + rfc + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", correoElectronico=" + correoElectronico + ", estado=" + estado
				+ ", ciudad=" + ciudad + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal
				+ ", activo=" + activo + "]";
	}

	/**
	 * Constructor fluido para crear instancias de {@link Proveedor}.
	 */
	public static class ProveedorBuilder {

		private final Proveedor proveedor;

		public ProveedorBuilder() {
			this.proveedor = new Proveedor();
		}

		public ProveedorBuilder idProveedor(int idProveedor) {
			this.proveedor.idProveedor = idProveedor;
			return this;
		}

		public ProveedorBuilder rfc(String rfc) {
			this.proveedor.rfc = rfc;
			return this;
		}

		public ProveedorBuilder nombre(String nombre) {
			this.proveedor.nombre = nombre;
			return this;
		}

		public ProveedorBuilder descripcion(String descripcion) {
			this.proveedor.descripcion = descripcion;
			return this;
		}

		public ProveedorBuilder correoElectronico(String correoElectronico) {
			this.proveedor.correoElectronico = correoElectronico;
			return this;
		}

		public ProveedorBuilder estado(String estado) {
			this.proveedor.estado = estado;
			return this;
		}

		public ProveedorBuilder ciudad(String ciudad) {
			this.proveedor.ciudad = ciudad;
			return this;
		}

		public ProveedorBuilder direccion(String direccion) {
			this.proveedor.direccion = direccion;
			return this;
		}

		public ProveedorBuilder codigoPostal(String codigoPostal) {
			this.proveedor.codigoPostal = codigoPostal;
			return this;
		}

		public ProveedorBuilder activo(boolean activo) {
			this.proveedor.activo = activo;
			return this;
		}

		public Proveedor build() {
			return this.proveedor;
		}
	}
}
