package com.kathsoft.kathpos.app.model.compra;

public enum TipoCompraFiltro {
	TODOS(null, "Todos"), CONTADO(Boolean.FALSE, "Contado"), CREDITO(Boolean.TRUE, "Crédito");

	private final Boolean valor;
	private final String descripcion;

	TipoCompraFiltro(Boolean valor, String descripcion) {
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public Boolean getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public String toString() {
		return descripcion;
	}
}
