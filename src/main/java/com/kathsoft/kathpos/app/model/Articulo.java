package com.kathsoft.kathpos.app.model;

public class Articulo implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7086377815477726807L;
	/**
	 * 
	 * 
	 */
	private int idArticulo;
	private String codigoArticulo;
	private int idProvedor;
	private String nombreProveedor;
	private int idCategoria;
	private String nombreCategoria;
	private String codigoSat;
	private String nombre;
	private String descripcion;
	private int existencia;
	private boolean exento;
	private double costoUnitario;
	private double precioGeneral;
	private double precioMayoreo;
		
	
	/**
	 * @param idArticulo
	 * @param codigoArticulo
	 * @param idProvedor
	 * @param nombreProveedor
	 * @param idCategoria
	 * @param nombreCategoria
	 * @param codigoSat
	 * @param nombre
	 * @param descripcion
	 * @param existencia
	 * @param exento
	 * @param costoUnitario
	 * @param precioGeneral
	 * @param precioMayoreo
	 */
	public Articulo(int idArticulo, String codigoArticulo, int idProvedor, String nombreProveedor, int idCategoria,
			String nombreCategoria, String codigoSat, String nombre, String descripcion, int existencia, boolean exento,
			double costoUnitario, double precioGeneral, double precioMayoreo) {
		super();
		this.idArticulo = idArticulo;
		this.codigoArticulo = codigoArticulo;
		this.idProvedor = idProvedor;
		this.nombreProveedor = nombreProveedor;
		this.idCategoria = idCategoria;
		this.nombreCategoria = nombreCategoria;
		this.codigoSat = codigoSat;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.existencia = existencia;
		this.exento = exento;
		this.costoUnitario = costoUnitario;
		this.precioGeneral = precioGeneral;
		this.precioMayoreo = precioMayoreo;
	}

	public Articulo() {}
	
	
	
	/**
	 * @return the idArticulo
	 */
	public int getIdArticulo() {
		return idArticulo;
	}

	/**
	 * @return the codigoArticulo
	 */
	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	/**
	 * @return the idProvedor
	 */
	public int getIdProvedor() {
		return idProvedor;
	}

	/**
	 * @return the nombreProveedor
	 */
	public String getNombreProveedor() {
		return nombreProveedor;
	}

	/**
	 * @return the idCategoria
	 */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * @return the codigoSat
	 */
	public String getCodigoSat() {
		return codigoSat;
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
	 * @return the existencia
	 */
	public int getExistencia() {
		return existencia;
	}

	/**
	 * @return the exento
	 */
	public boolean isExento() {
		return exento;
	}

	/**
	 * @return the costoUnitario
	 */
	public double getCostoUnitario() {
		return costoUnitario;
	}

	/**
	 * @return the precioGeneral
	 */
	public double getPrecioGeneral() {
		return precioGeneral;
	}

	/**
	 * @return the precioMayoreo
	 */
	public double getPrecioMayoreo() {
		return precioMayoreo;
	}

	/**
	 * @param idArticulo the idArticulo to set
	 */
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	/**
	 * @param codigoArticulo the codigoArticulo to set
	 */
	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	/**
	 * @param idProvedor the idProvedor to set
	 */
	public void setIdProvedor(int idProvedor) {
		this.idProvedor = idProvedor;
	}

	/**
	 * @param nombreProveedor the nombreProveedor to set
	 */
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * @param codigoSat the codigoSat to set
	 */
	public void setCodigoSat(String codigoSat) {
		this.codigoSat = codigoSat;
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

	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	/**
	 * @param exento the exento to set
	 */
	public void setExento(boolean exento) {
		this.exento = exento;
	}

	/**
	 * @param costoUnitario the costoUnitario to set
	 */
	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	/**
	 * @param precioGeneral the precioGeneral to set
	 */
	public void setPrecioGeneral(double precioGeneral) {
		this.precioGeneral = precioGeneral;
	}

	/**
	 * @param precioMayoreo the precioMayoreo to set
	 */
	public void setPrecioMayoreo(double precioMayoreo) {
		this.precioMayoreo = precioMayoreo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + idArticulo;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Articulo)) {
			return false;
		}
		Articulo other = (Articulo) obj;
		if (descripcion == null) {
			if (other.descripcion != null) {
				return false;
			}
		} else if (!descripcion.equals(other.descripcion)) {
			return false;
		}
		if (idArticulo != other.idArticulo) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Articulo [getIdArticulo()=");
		builder.append(getIdArticulo());
		builder.append(", getCodigoArticulo()=");
		builder.append(getCodigoArticulo());
		builder.append(", getIdProvedor()=");
		builder.append(getIdProvedor());
		builder.append(", getNombreProveedor()=");
		builder.append(getNombreProveedor());
		builder.append(", getIdCategoria()=");
		builder.append(getIdCategoria());
		builder.append(", getNombreCategoria()=");
		builder.append(getNombreCategoria());
		builder.append(", getCodigoSat()=");
		builder.append(getCodigoSat());
		builder.append(", getNombre()=");
		builder.append(getNombre());
		builder.append(", getDescripcion()=");
		builder.append(getDescripcion());
		builder.append(", getExistencia()=");
		builder.append(getExistencia());
		builder.append(", isExento()=");
		builder.append(isExento());
		builder.append(", getCostoUnitario()=");
		builder.append(getCostoUnitario());
		builder.append(", getPrecioGeneral()=");
		builder.append(getPrecioGeneral());
		builder.append(", getPrecioMayoreo()=");
		builder.append(getPrecioMayoreo());
		builder.append(", hashCode()=");
		builder.append(hashCode());
		builder.append("]");
		return builder.toString();
	}
	
}
