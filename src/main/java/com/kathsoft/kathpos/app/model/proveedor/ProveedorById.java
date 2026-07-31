package com.kathsoft.kathpos.app.model.proveedor;

/**
 * Modelo de lectura para consultar un proveedor por identificador.
 *
 * <p>Representa la respuesta del procedimiento almacenado
 * {@code getProveedorById}.</p>
 *
 * @author PABLO
 */
public class ProveedorById {

	private int idProveedor;
	private int idCuentaContable;
	private String claveCuentaContable;
	private String nombre;
	private String descripcion;
	private String correoElectronico;
	private String estado;
	private String ciudad;
	private String direccion;
	private String codigoPostal;
	private boolean activo;

	public ProveedorById() {
		super();
	}

	public ProveedorById(int idProveedor, int idCuentaContable, String claveCuentaContable, String nombre,
			String descripcion, String correoElectronico, String estado, String ciudad, String direccion,
			String codigoPostal, boolean activo) {
		super();
		this.idProveedor = idProveedor;
		this.idCuentaContable = idCuentaContable;
		this.claveCuentaContable = claveCuentaContable;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.activo = activo;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
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
		return "ProveedorById [idProveedor=" + idProveedor + ", idCuentaContable=" + idCuentaContable
				+ ", claveCuentaContable=" + claveCuentaContable + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", correoElectronico=" + correoElectronico + ", estado=" + estado + ", ciudad=" + ciudad
				+ ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", activo=" + activo + "]";
	}

	/**
	 * Constructor fluido para crear instancias de {@link ProveedorById}.
	 */
	public static class ProveedorByIdBuilder {

		private final ProveedorById proveedor;

		public ProveedorByIdBuilder() {
			this.proveedor = new ProveedorById();
		}

		public ProveedorByIdBuilder idProveedor(int idProveedor) {
			this.proveedor.idProveedor = idProveedor;
			return this;
		}

		public ProveedorByIdBuilder idCuentaContable(int idCuentaContable) {
			this.proveedor.idCuentaContable = idCuentaContable;
			return this;
		}

		public ProveedorByIdBuilder claveCuentaContable(String claveCuentaContable) {
			this.proveedor.claveCuentaContable = claveCuentaContable;
			return this;
		}

		public ProveedorByIdBuilder nombre(String nombre) {
			this.proveedor.nombre = nombre;
			return this;
		}

		public ProveedorByIdBuilder descripcion(String descripcion) {
			this.proveedor.descripcion = descripcion;
			return this;
		}

		public ProveedorByIdBuilder correoElectronico(String correoElectronico) {
			this.proveedor.correoElectronico = correoElectronico;
			return this;
		}

		public ProveedorByIdBuilder estado(String estado) {
			this.proveedor.estado = estado;
			return this;
		}

		public ProveedorByIdBuilder ciudad(String ciudad) {
			this.proveedor.ciudad = ciudad;
			return this;
		}

		public ProveedorByIdBuilder direccion(String direccion) {
			this.proveedor.direccion = direccion;
			return this;
		}

		public ProveedorByIdBuilder codigoPostal(String codigoPostal) {
			this.proveedor.codigoPostal = codigoPostal;
			return this;
		}

		public ProveedorByIdBuilder activo(boolean activo) {
			this.proveedor.activo = activo;
			return this;
		}

		public ProveedorById build() {
			return this.proveedor;
		}
	}
}
