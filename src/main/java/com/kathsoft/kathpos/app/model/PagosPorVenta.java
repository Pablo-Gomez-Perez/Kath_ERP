package com.kathsoft.kathpos.app.model;

public class PagosPorVenta {
	
	private int id;
	private int idVenta;
	private int idFormaDePago;
	private double importe;
	
	/**
	 * constructor dinámico para la clase
	 */
	public static class Builder{
		
		private PagosPorVenta fpago;
		
		private Builder() {
			this.fpago = new PagosPorVenta();
		}
		
		public Builder idVenta(int idVenta) {
			this.fpago.setIdVenta(idVenta);
			return this;
		}
		
		public Builder idFormaDePago(int idFormaDePago) {
			this.fpago.setIdFormaDePago(idFormaDePago);
			return this;
		}
		
		public Builder importe(double importe) {
			this.fpago.setImporte(importe);
			return this;
		}
		
		public PagosPorVenta build() {
			return this.fpago;
		}
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	/**
	 * @param id
	 * @param idVenta
	 * @param idFormaDePago
	 * @param importe
	 */
	public PagosPorVenta(int id, int idVenta, int idFormaDePago, double importe) {
		super();
		this.id = id;
		this.idVenta = idVenta;
		this.idFormaDePago = idFormaDePago;
		this.importe = importe;
	}
	
	public PagosPorVenta() {
		
	}	
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the idVenta
	 */
	public int getIdVenta() {
		return idVenta;
	}
	/**
	 * @param idVenta the idVenta to set
	 */
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	/**
	 * @return the idFormaDePago
	 */
	public int getIdFormaDePago() {
		return idFormaDePago;
	}
	/**
	 * @param idFormaDePago the idFormaDePago to set
	 */
	public void setIdFormaDePago(int idFormaDePago) {
		this.idFormaDePago = idFormaDePago;
	}
	/**
	 * @return the importe
	 */
	public double getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(double importe) {
		this.importe = importe;
	}

	@Override
	public String toString() {
		return "PagosPorVenta [id=" + id + ", idVenta=" + idVenta + ", idFormaDePago=" + idFormaDePago + ", importe="
				+ importe + "]";
	}
	
	
}
