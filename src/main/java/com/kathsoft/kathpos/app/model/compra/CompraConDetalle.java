package com.kathsoft.kathpos.app.model.compra;

import java.util.ArrayList;
import java.util.List;

public class CompraConDetalle implements java.io.Serializable {

	private static final long serialVersionUID = 139916726433564831L;

	private Compra compra;
	private List<ArticuloPorCompra> articulosPorCompra;
	private PagoProveedor pagoProveedor;

	public CompraConDetalle() {
		super();
		this.articulosPorCompra = new ArrayList<>();
	}

	public CompraConDetalle(Compra compra, List<ArticuloPorCompra> articulosPorCompra) {
		super();
		this.compra = compra;
		this.articulosPorCompra = articulosPorCompra == null ? new ArrayList<>() : articulosPorCompra;
	}

	/**
	 * Constructor de compra con pago inicial opcional.
	 * Las compras de contado requieren un pago confirmado antes de persistir.
	 */
	public CompraConDetalle(Compra compra, List<ArticuloPorCompra> articulosPorCompra, PagoProveedor pagoProveedor) {
		this(compra, articulosPorCompra);
		this.pagoProveedor = pagoProveedor;
	}

	public PagoProveedor getPagoProveedor() {
		return pagoProveedor;
	}

	public void setPagoProveedor(PagoProveedor pagoProveedor) {
		this.pagoProveedor = pagoProveedor;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<ArticuloPorCompra> getArticulosPorCompra() {
		return articulosPorCompra;
	}

	public void setArticulosPorCompra(List<ArticuloPorCompra> articulosPorCompra) {
		this.articulosPorCompra = articulosPorCompra == null ? new ArrayList<>() : articulosPorCompra;
	}

	public void addArticuloPorCompra(ArticuloPorCompra articuloPorCompra) {
		if (articuloPorCompra != null) {
			this.articulosPorCompra.add(articuloPorCompra);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompraConDetalle [compra=").append(compra).append(", articulosPorCompra=")
				.append(articulosPorCompra).append(", pagoProveedor=").append(pagoProveedor).append("]");
		return builder.toString();
	}
}
