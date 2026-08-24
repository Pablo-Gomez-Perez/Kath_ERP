package com.kathsoft.kathpos.app.model.articulo;

import java.util.Objects;

public class ArticuloByCodigo {

	private int idArticulo;
	private int idProveedor;
	private int idCategoria;
	private String codigoArticulo;
	private String codigoSat;
	private String unidadSat;
	private String nombre;
	private String descripcion;
	private boolean exento;
	private double costoUnitario;
	private boolean activo;
	private int existencia;

	public ArticuloByCodigo(int idArticulo, int idProveedor, int idCategoria, String codigoArticulo, String codigoSat,
			String unidadSat, String nombre, String descripcion, boolean exento, double costoUnitario, boolean activo,
			int existencia) {
		super();
		this.idArticulo = idArticulo;
		this.idProveedor = idProveedor;
		this.idCategoria = idCategoria;
		this.codigoArticulo = codigoArticulo;
		this.codigoSat = codigoSat;
		this.unidadSat = unidadSat;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exento = exento;
		this.costoUnitario = costoUnitario;
		this.activo = activo;
		this.existencia = existencia;
	}

	public ArticuloByCodigo() {
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	public String getCodigoSat() {
		return codigoSat;
	}

	public void setCodigoSat(String codigoSat) {
		this.codigoSat = codigoSat;
	}

	public String getUnidadSat() {
		return unidadSat;
	}

	public void setUnidadSat(String unidadSat) {
		this.unidadSat = unidadSat;
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

	public boolean isExento() {
		return exento;
	}

	public void setExento(boolean exento) {
		this.exento = exento;
	}

	public double getCostoUnitario() {
		return costoUnitario;
	}

	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Boolean.valueOf(activo), codigoArticulo, codigoSat, Double.valueOf(costoUnitario),
				descripcion, Boolean.valueOf(exento), Integer.valueOf(idArticulo), Integer.valueOf(idCategoria),
				Integer.valueOf(idProveedor), nombre, unidadSat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticuloByCodigo other = (ArticuloByCodigo) obj;
		return activo == other.activo && Objects.equals(codigoArticulo, other.codigoArticulo)
				&& Objects.equals(codigoSat, other.codigoSat)
				&& Double.doubleToLongBits(costoUnitario) == Double.doubleToLongBits(other.costoUnitario)
				&& Objects.equals(descripcion, other.descripcion) && exento == other.exento
				&& idArticulo == other.idArticulo && idCategoria == other.idCategoria
				&& idProveedor == other.idProveedor && Objects.equals(nombre, other.nombre)
				&& Objects.equals(unidadSat, other.unidadSat);
	}

	@Override
	public String toString() {
		return "ArticuloByCodigo [idArticulo=" + idArticulo + ", idProveedor=" + idProveedor + ", idCategoria="
				+ idCategoria + ", codigoArticulo=" + codigoArticulo + ", codigoSat=" + codigoSat + ", unidadSat="
				+ unidadSat + ", nombre=" + nombre + ", descripcion=" + descripcion + ", exento=" + exento
				+ ", costoUnitario=" + costoUnitario + ", activo=" + activo + "]";
	}

}
