package com.kathsoft.kathpos.app.model.venta;

public enum VentaCriterioBusqueda {
    TODOS("TODOS", "Todos"),
    EMPLEADO("EMPLEADO", "Empleado"),
    CLIENTE("CLIENTE", "Cliente");

    private final String valor;
    private final String descripcion;

    VentaCriterioBusqueda(String valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public String getValor() {
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
