package com.kathsoft.kathpos.app.model.compra;

import java.util.ArrayList;
import java.util.List;

public class CompraConDetalle implements java.io.Serializable {

	private static final long serialVersionUID = 139916726433564831L;

	private Compra compra;
	private List<ArticuloPorCompra> articulosPorCompra;

	public CompraConDetalle() {
		super();
		this.articulosPorCompra = new ArrayList<>();
	}

	public CompraConDetalle(Compra compra, List<ArticuloPorCompra> articulosPorCompra) {
		super();
		this.compra = compra;
		this.articulosPorCompra = articulosPorCompra == null ? new ArrayList<>() : articulosPorCompra;
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
				.append(articulosPorCompra).append("]");
		return builder.toString();
	}
}
