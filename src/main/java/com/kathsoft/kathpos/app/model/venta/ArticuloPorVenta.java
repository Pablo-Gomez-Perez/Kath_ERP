package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;

public class ArticuloPorVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idVenta;
    private int idArticulo;
    private int cantidad;
    private double subtotal;

    public ArticuloPorVenta() {
        super();
    }

    public ArticuloPorVenta(int id, int idVenta, int idArticulo, int cantidad, double subtotal) {
        this.id = id;
        this.idVenta = idVenta;
        this.idArticulo = idArticulo;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdArticulo() { return idArticulo; }
    public void setIdArticulo(int idArticulo) { this.idArticulo = idArticulo; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    @Override
    public String toString() {
        return "ArticuloPorVenta [id=" + id + ", idVenta=" + idVenta + ", idArticulo=" + idArticulo + ", cantidad="
                + cantidad + ", subtotal=" + subtotal + "]";
    }

    public static class ArticuloPorVentaBuilder {

        private final ArticuloPorVenta articulo;

        public ArticuloPorVentaBuilder() {
            this.articulo = new ArticuloPorVenta();
        }

        public ArticuloPorVentaBuilder id(int value) { articulo.id = value; return this; }
        public ArticuloPorVentaBuilder idVenta(int value) { articulo.idVenta = value; return this; }
        public ArticuloPorVentaBuilder idArticulo(int value) { articulo.idArticulo = value; return this; }
        public ArticuloPorVentaBuilder cantidad(int value) { articulo.cantidad = value; return this; }
        public ArticuloPorVentaBuilder subtotal(double value) { articulo.subtotal = value; return this; }

        public ArticuloPorVenta build() { return articulo; }
    }
}
