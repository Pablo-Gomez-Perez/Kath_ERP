package com.kathsoft.kathpos.app.model.articulo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class PrecioTipoCliente implements Serializable {

	private static final long serialVersionUID = 5030387884091563259L;

	private int idTipoCliente;
	private BigDecimal precio;
	private BigDecimal precioEspecial;
	private Integer cantidadPrecioEspecial;

	public PrecioTipoCliente() {
		super();
	}

	public PrecioTipoCliente(int idTipoCliente, BigDecimal precio, BigDecimal precioEspecial,
			Integer cantidadPrecioEspecial) {
		super();
		this.idTipoCliente = idTipoCliente;
		this.precio = precio;
		this.precioEspecial = precioEspecial;
		this.cantidadPrecioEspecial = cantidadPrecioEspecial;
	}

	public int getIdTipoCliente() {
		return idTipoCliente;
	}

	public void setIdTipoCliente(int idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public BigDecimal getPrecioEspecial() {
		return precioEspecial;
	}

	public void setPrecioEspecial(BigDecimal precioEspecial) {
		this.precioEspecial = precioEspecial;
	}

	public Integer getCantidadPrecioEspecial() {
		return cantidadPrecioEspecial;
	}

	public void setCantidadPrecioEspecial(Integer cantidadPrecioEspecial) {
		this.cantidadPrecioEspecial = cantidadPrecioEspecial;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Integer.valueOf(idTipoCliente));
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrecioTipoCliente other = (PrecioTipoCliente) obj;
		return idTipoCliente == other.idTipoCliente;
	}

	@Override
	public String toString() {
		return "PrecioTipoCliente [idTipoCliente=" + idTipoCliente + ", precio=" + precio + ", precioEspecial="
				+ precioEspecial + ", cantidadPrecioEspecial=" + cantidadPrecioEspecial + "]";
	}
}
