package com.kathsoft.kathpos.app.model.articulo;

public class ArticuloByCodigo extends Articulo {

	private static final long serialVersionUID = 1L;
	private int existencia;

	public ArticuloByCodigo(int idArticulo, int idProveedor, int idCategoria, String codigoArticulo, String codigoSat,
			String unidadSat, String nombre, String descripcion, boolean exento, double costoUnitario, boolean activo,
			int existencia) {
		super(idArticulo, idProveedor, idCategoria, codigoArticulo, codigoSat, unidadSat, nombre, descripcion, exento,
				costoUnitario);
		this.setActivo(activo);
		this.existencia = existencia;
	}

	public ArticuloByCodigo() {
		super();
	}

	/**
	 * Alias con la nomenclatura correcta para conservar la API específica de este
	 * modelo. {@link Articulo} mantiene históricamente el método getIdProvedor().
	 */
	public int getIdProveedor() {
		return this.getIdProvedor();
	}

	public void setIdProveedor(int idProveedor) {
		this.setIdProvedor(idProveedor);
	}

	public int getExistencia() {
		return existencia;
	}

	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	@Override
	public String toString() {
		return "ArticuloByCodigo [idArticulo=" + getIdArticulo() + ", idProveedor=" + getIdProveedor()
				+ ", idCategoria=" + getIdCategoria() + ", codigoArticulo=" + getCodigoArticulo() + ", codigoSat="
				+ getCodigoSat() + ", unidadSat=" + getUnidadSat() + ", nombre=" + getNombre() + ", descripcion="
				+ getDescripcion() + ", exento=" + isExento() + ", costoUnitario=" + getCostoUnitario() + ", activo="
				+ isActivo() + ", existencia=" + existencia + "]";
	}

}
