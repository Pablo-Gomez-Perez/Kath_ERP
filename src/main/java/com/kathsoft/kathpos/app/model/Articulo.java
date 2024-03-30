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
	private double precioEspecial;
	private int cantidadParaPrecioEspecial;

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
	 * @param precioEspecial
	 * @param cantidadParaPrecioEspecial
	 */
	public Articulo(int idArticulo, String codigoArticulo, int idProvedor, String nombreProveedor, int idCategoria,
			String nombreCategoria, String codigoSat, String nombre, String descripcion, int existencia, boolean exento,
			double costoUnitario, double precioGeneral, double precioEspecial, int cantidadParaPrecioEspecial) {
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
		this.precioEspecial = precioEspecial;
		this.cantidadParaPrecioEspecial = cantidadParaPrecioEspecial;
	}
	
	public Articulo() {
		
	}
	
	/**
	 * @return the idArticulo
	 */
	public int getIdArticulo() {
		return idArticulo;
	}

	/**
	 * @param idArticulo the idArticulo to set
	 */
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	/**
	 * @return the codigoArticulo
	 */
	public String getCodigoArticulo() {
		return codigoArticulo;
	}

	/**
	 * @param codigoArticulo the codigoArticulo to set
	 */
	public void setCodigoArticulo(String codigoArticulo) {
		this.codigoArticulo = codigoArticulo;
	}

	/**
	 * @return the idProvedor
	 */
	public int getIdProvedor() {
		return idProvedor;
	}

	/**
	 * @param idProvedor the idProvedor to set
	 */
	public void setIdProvedor(int idProvedor) {
		this.idProvedor = idProvedor;
	}

	/**
	 * @return the nombreProveedor
	 */
	public String getNombreProveedor() {
		return nombreProveedor;
	}

	/**
	 * @param nombreProveedor the nombreProveedor to set
	 */
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}

	/**
	 * @return the idCategoria
	 */
	public int getIdCategoria() {
		return idCategoria;
	}

	/**
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * @return the codigoSat
	 */
	public String getCodigoSat() {
		return codigoSat;
	}

	/**
	 * @param codigoSat the codigoSat to set
	 */
	public void setCodigoSat(String codigoSat) {
		this.codigoSat = codigoSat;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the existencia
	 */
	public int getExistencia() {
		return existencia;
	}

	/**
	 * @param existencia the existencia to set
	 */
	public void setExistencia(int existencia) {
		this.existencia = existencia;
	}

	/**
	 * @return the exento
	 */
	public boolean isExento() {
		return exento;
	}

	/**
	 * @param exento the exento to set
	 */
	public void setExento(boolean exento) {
		this.exento = exento;
	}

	/**
	 * @return the costoUnitario
	 */
	public double getCostoUnitario() {
		return costoUnitario;
	}

	/**
	 * @param costoUnitario the costoUnitario to set
	 */
	public void setCostoUnitario(double costoUnitario) {
		this.costoUnitario = costoUnitario;
	}

	/**
	 * @return the precioGeneral
	 */
	public double getPrecioGeneral() {
		return precioGeneral;
	}

	/**
	 * @param precioGeneral the precioGeneral to set
	 */
	public void setPrecioGeneral(double precioGeneral) {
		this.precioGeneral = precioGeneral;
	}

	/**
	 * @return the precioEspecial
	 */
	public double getPrecioEspecial() {
		return precioEspecial;
	}

	/**
	 * @param precioEspecial the precioEspecial to set
	 */
	public void setPrecioEspecial(double precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	/**
	 * @return the cantidadParaPrecioEspecial
	 */
	public int getCantidadParaPrecioEspecial() {
		return cantidadParaPrecioEspecial;
	}

	/**
	 * @param cantidadParaPrecioEspecial the cantidadParaPrecioEspecial to set
	 */
	public void setCantidadParaPrecioEspecial(int cantidadParaPrecioEspecial) {
		this.cantidadParaPrecioEspecial = cantidadParaPrecioEspecial;
	}

}
