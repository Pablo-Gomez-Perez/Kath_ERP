package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Objects;

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
	private int fkIdRubro;
	private String clave;
	private String nombre;
	private String descripcion;
	private short nivel;
	private boolean ultimoNivel;
	private double cargo;
	private double abono;

	public CuentaContable(int idCuenta, int idCuentaPadre, int fkIdRubro, String clave, String nombre,
			String descripcion, short nivel, boolean ultimoNivel, double cargo, double abono) {
		super();
		this.idCuenta = idCuenta;
		this.idCuentaPadre = idCuentaPadre;
		this.fkIdRubro = fkIdRubro;
		this.clave = clave;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.nivel = nivel;
		this.ultimoNivel = ultimoNivel;
		this.cargo = cargo;
		this.abono = abono;
	}

	public CuentaContable() {
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public int getIdCuentaPadre() {
		return idCuentaPadre;
	}

	public void setIdCuentaPadre(int idCuentaPadre) {
		this.idCuentaPadre = idCuentaPadre;
	}

	public int getFkIdRubro() {
		return fkIdRubro;
	}

	public void setFkIdRubro(int fkIdRubro) {
		this.fkIdRubro = fkIdRubro;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
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

	public short getNivel() {
		return nivel;
	}

	public void setNivel(short nivel) {
		this.nivel = nivel;
	}

	public boolean isUltimoNivel() {
		return ultimoNivel;
	}

	public void setUltimoNivel(boolean ultimoNivel) {
		this.ultimoNivel = ultimoNivel;
	}

	public double getCargo() {
		return cargo;
	}

	public void setCargo(double cargo) {
		this.cargo = cargo;
	}

	public double getAbono() {
		return abono;
	}

	public void setAbono(double abono) {
		this.abono = abono;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CuentaContable [idCuenta=").append(idCuenta).append(", idCuentaPadre=").append(idCuentaPadre)
				.append(", fkIdRubro=").append(fkIdRubro).append(", clave=").append(clave).append(", nombre=")
				.append(nombre).append(", descripcion=").append(descripcion).append(", nivel=").append(nivel)
				.append(", ultimoNivel=").append(ultimoNivel).append(", cargo=").append(cargo).append(", abono=")
				.append(abono).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(abono, cargo, clave, descripcion, fkIdRubro, idCuenta, idCuentaPadre, nivel, nombre,
				ultimoNivel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CuentaContable other = (CuentaContable) obj;
		return Double.doubleToLongBits(abono) == Double.doubleToLongBits(other.abono)
				&& Double.doubleToLongBits(cargo) == Double.doubleToLongBits(other.cargo)
				&& Objects.equals(clave, other.clave) && Objects.equals(descripcion, other.descripcion)
				&& fkIdRubro == other.fkIdRubro && idCuenta == other.idCuenta && idCuentaPadre == other.idCuentaPadre
				&& nivel == other.nivel && Objects.equals(nombre, other.nombre) && ultimoNivel == other.ultimoNivel;
	}
	
	
	
}
