package com.kathsoft.kathpos.app.model;

import java.io.Serializable;

public class CuentaContable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5783735452212456949L;
	/**
	 * 
	 * 
	 */

	private int idCuenta;
	private int idCuentaPadre;
	private String nombreCuentaPadre;
	private String rubroCuenta;
	private String clave;
	private String nombre;
	private String descripcion;
	private short nivel;
	private boolean ultimoNivel;
	private double cargo;
	private double abono;
	private double saldo;
	private boolean naturaleza;

	/**
	 * 
	 * @param idCuenta
	 * @param idCuentaPadre
	 * @param nombreCuentaPadre
	 * @param rubroCuenta
	 * @param clave
	 * @param nombre
	 * @param descripcion
	 * @param nivel
	 * @param ultimoNivel
	 * @param cargo
	 * @param abono
	 * @param saldo
	 * @param naturaleza
	 */
	public CuentaContable(int idCuenta, int idCuentaPadre, String nombreCuentaPadre, String rubroCuenta, String clave,
			String nombre, String descripcion, short nivel, boolean ultimoNivel, double cargo, double abono,
			double saldo, boolean naturaleza) {
		super();
		this.idCuenta = idCuenta;
		this.idCuentaPadre = idCuentaPadre;
		this.nombreCuentaPadre = nombreCuentaPadre;
		this.rubroCuenta = rubroCuenta;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.ultimoNivel = ultimoNivel;
		this.cargo = cargo;
		this.abono = abono;
		this.saldo = saldo;
		this.naturaleza = naturaleza;
	}

	/**
	 * @param idCuenta
	 * @param idCuentaPadre
	 * @param nombreCuentaPadre
	 * @param clave
	 * @param nombre
	 * @param descripcion
	 * @param nivel
	 * @param ultimoNivel
	 * @param cargo
	 * @param abono
	 * @param saldo
	 */
	public CuentaContable(int idCuenta, int idCuentaPadre, String nombreCuentaPadre, String clave, String nombre,
			String descripcion, short nivel, boolean ultimoNivel, double cargo, double abono, double saldo,
			boolean naturaleza) {
		super();
		this.idCuenta = idCuenta;
		this.idCuentaPadre = idCuentaPadre;
		this.nombreCuentaPadre = nombreCuentaPadre;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.ultimoNivel = ultimoNivel;
		this.cargo = cargo;
		this.abono = abono;
		this.saldo = saldo;
		this.naturaleza = naturaleza;
	}

	public CuentaContable() {

	}

	/**
	 * @return the idCuenta
	 */
	public int getIdCuenta() {
		return idCuenta;
	}

	/**
	 * @param idCuenta the idCuenta to set
	 */
	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	/**
	 * @return the idCuentaPadre
	 */
	public int getIdCuentaPadre() {
		return idCuentaPadre;
	}

	/**
	 * @param idCuentaPadre the idCuentaPadre to set
	 */
	public void setIdCuentaPadre(int idCuentaPadre) {
		this.idCuentaPadre = idCuentaPadre;
	}

	/**
	 * @return the clave
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(String clave) {
		this.clave = clave;
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
	 * @return the nivel
	 */
	public short getNivel() {
		return nivel;
	}

	/**
	 * @return the nombreCuentaPadre
	 */
	public String getNombreCuentaPadre() {
		return nombreCuentaPadre;
	}

	/**
	 * @param nombreCuentaPadre the nombreCuentaPadre to set
	 */
	public void setNombreCuentaPadre(String nombreCuentaPadre) {
		this.nombreCuentaPadre = nombreCuentaPadre;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(short nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the ultimoNivel
	 */
	public boolean isUltimoNivel() {
		return ultimoNivel;
	}

	/**
	 * @param ultimoNivel the ultimoNivel to set
	 */
	public void setUltimoNivel(boolean ultimoNivel) {
		this.ultimoNivel = ultimoNivel;
	}

	/**
	 * @return the cargo
	 */
	public double getCargo() {
		return cargo;
	}

	/**
	 * @param cargo the cargo to set
	 */
	public void setCargo(double cargo) {
		this.cargo = cargo;
	}

	/**
	 * @return the abono
	 */
	public double getAbono() {
		return abono;
	}

	/**
	 * @param abono the abono to set
	 */
	public void setAbono(double abono) {
		this.abono = abono;
	}

	/**
	 * @return the saldo
	 */
	public double getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public void setNaturaleza(boolean naturaleza) {
		this.naturaleza = naturaleza;
	}

	public String getRubroCuenta() {
		return rubroCuenta;
	}

	public void setRubroCuenta(String rubroCuenta) {
		this.rubroCuenta = rubroCuenta;
	}

	/**
	 * la naturaleza de una cuenta contable define su comportamiento. Una cuenta
	 * contable puede ser Acreedora o de naturaleza Deudora. Este atributo define su
	 * ubicación en balance general.
	 * 
	 * @return the naturaleza
	 */
	public boolean isNaturaleza() {
		return this.naturaleza;
	}

	@Override
	public String toString() {
		return this.nombre;
	}

	public String printData() {
		return "CuentaContable [idCuenta=" + idCuenta + ", idCuentaPadre=" + idCuentaPadre + ", clave=" + clave
				+ ", nombre=" + nombre + ", descripcion=" + descripcion + ", nivel=" + nivel + ", ultimoNivel="
				+ ultimoNivel + ", cargo=" + cargo + ", abono=" + abono + ", saldo=" + saldo + ", naturaleza="
				+ this.naturaleza + "]";
	}

}
