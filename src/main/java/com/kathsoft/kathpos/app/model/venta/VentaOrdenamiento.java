package com.kathsoft.kathpos.app.model.venta;

public enum VentaOrdenamiento {
    EMPLEADO("EMPLEADO", "Empleado"),
    CLIENTE("CLIENTE", "Cliente"),
    TIPO("TIPO", "Tipo de venta"),
    FECHA("FECHA", "Fecha"),
    STATUS("STATUS", "Estado");

    private final String valor;
    private final String descripcion;

    VentaOrdenamiento(String valor, String descripcion) {
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
