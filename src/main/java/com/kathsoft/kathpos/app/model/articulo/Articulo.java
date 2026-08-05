package com.kathsoft.kathpos.app.model.articulo;

import java.util.Objects;

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
	private int idProveedor;
	private int idCategoria;
	private String codigoArticulo;
	private String codigoSat;
	private String unidadSat;
	private String nombre;
	private String descripcion;
	private boolean exento;
	private double costoUnitario;

	public Articulo() {
		super();
	}

	public Articulo(int idArticulo, int idProvedor, int idCategoria, String codigoArticulo, String codigoSat,
			String unidadSat, String nombre, String descripcion, boolean exento, double costoUnitario) {
		super();
		this.idArticulo = idArticulo;
		this.idProveedor = idProvedor;
		this.idCategoria = idCategoria;
		this.codigoArticulo = codigoArticulo;
		this.codigoSat = codigoSat;
		this.unidadSat = unidadSat;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.exento = exento;
		this.costoUnitario = costoUnitario;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getIdProvedor() {
		return idProveedor;
	}

	public void setIdProvedor(int idProvedor) {
		this.idProveedor = idProvedor;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Articulo [idArticulo=" + idArticulo + ", idProvedor=" + idProveedor + ", idCategoria=" + idCategoria
				+ ", codigoArticulo=" + codigoArticulo + ", codigoSat=" + codigoSat + ", unidadSat=" + unidadSat
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + ", exento=" + exento + ", costoUnitario="
				+ costoUnitario + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigoArticulo, codigoSat, Integer.valueOf(idArticulo), Integer.valueOf(idCategoria),
				Integer.valueOf(idProveedor));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Articulo other = (Articulo) obj;
		return Objects.equals(codigoArticulo, other.codigoArticulo) && Objects.equals(codigoSat, other.codigoSat)
				&& idArticulo == other.idArticulo && idCategoria == other.idCategoria && idProveedor == other.idProveedor;
	}

	public static class ArticuloBuilder {

		private final Articulo articulo;

		public ArticuloBuilder() {
			this.articulo = new Articulo();
		}

		public ArticuloBuilder idArticulo(int idArticulo) {
			this.articulo.idArticulo = idArticulo;
			return this;
		}

		public ArticuloBuilder idProvedor(int idProvedor) {
			this.articulo.idProveedor = idProvedor;
			return this;
		}

		public ArticuloBuilder idCategoria(int idCategoria) {
			this.articulo.idCategoria = idCategoria;
			return this;
		}

		public ArticuloBuilder codigoArticulo(String codigoArticulo) {
			this.articulo.codigoArticulo = codigoArticulo;
			return this;
		}

		public ArticuloBuilder codigoSat(String codigoSat) {
			this.articulo.codigoSat = codigoSat;
			return this;
		}

		public ArticuloBuilder unidadSat(String unidadSat) {
			this.articulo.unidadSat = unidadSat;
			return this;
		}

		public ArticuloBuilder nombre(String nombre) {
			this.articulo.nombre = nombre;
			return this;
		}

		public ArticuloBuilder descripcion(String descripcion) {
			this.articulo.descripcion = descripcion;
			return this;
		}

		public ArticuloBuilder exento(boolean exento) {
			this.articulo.exento = exento;
			return this;
		}

		public ArticuloBuilder costoUnitario(double costoUnitario) {
			this.articulo.costoUnitario = costoUnitario;
			return this;
		}

		public Articulo build() {
			return this.articulo;
		}
	}

}
