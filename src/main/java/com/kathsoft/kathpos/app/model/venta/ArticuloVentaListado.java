package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;

public class ArticuloVentaListado implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idVenta;
    private int idArticulo;
    private String codigoArticulo;
    private String codigoSat;
    private String unidadSat;
    private String nombre;
    private String descripcion;
    private boolean exento;
    private int cantidad;
    private double subtotal;
    private double iva;
    private double total;

    public ArticuloVentaListado() {
        super();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdArticulo() { return idArticulo; }
    public void setIdArticulo(int idArticulo) { this.idArticulo = idArticulo; }
    public String getCodigoArticulo() { return codigoArticulo; }
    public void setCodigoArticulo(String codigoArticulo) { this.codigoArticulo = codigoArticulo; }
    public String getCodigoSat() { return codigoSat; }
    public void setCodigoSat(String codigoSat) { this.codigoSat = codigoSat; }
    public String getUnidadSat() { return unidadSat; }
    public void setUnidadSat(String unidadSat) { this.unidadSat = unidadSat; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public boolean isExento() { return exento; }
    public void setExento(boolean exento) { this.exento = exento; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "ArticuloVentaListado [id=" + id + ", idVenta=" + idVenta + ", idArticulo=" + idArticulo
                + ", codigoArticulo=" + codigoArticulo + ", codigoSat=" + codigoSat + ", unidadSat=" + unidadSat
                + ", nombre=" + nombre + ", descripcion=" + descripcion + ", exento=" + exento + ", cantidad="
                + cantidad + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + "]";
    }

    public static class ArticuloVentaListadoBuilder {

        private final ArticuloVentaListado articulo;

        public ArticuloVentaListadoBuilder() {
            this.articulo = new ArticuloVentaListado();
        }

        public ArticuloVentaListadoBuilder id(int value) { articulo.id = value; return this; }
        public ArticuloVentaListadoBuilder idVenta(int value) { articulo.idVenta = value; return this; }
        public ArticuloVentaListadoBuilder idArticulo(int value) { articulo.idArticulo = value; return this; }
        public ArticuloVentaListadoBuilder codigoArticulo(String value) { articulo.codigoArticulo = value; return this; }
        public ArticuloVentaListadoBuilder codigoSat(String value) { articulo.codigoSat = value; return this; }
        public ArticuloVentaListadoBuilder unidadSat(String value) { articulo.unidadSat = value; return this; }
        public ArticuloVentaListadoBuilder nombre(String value) { articulo.nombre = value; return this; }
        public ArticuloVentaListadoBuilder descripcion(String value) { articulo.descripcion = value; return this; }
        public ArticuloVentaListadoBuilder exento(boolean value) { articulo.exento = value; return this; }
        public ArticuloVentaListadoBuilder cantidad(int value) { articulo.cantidad = value; return this; }
        public ArticuloVentaListadoBuilder subtotal(double value) { articulo.subtotal = value; return this; }
        public ArticuloVentaListadoBuilder iva(double value) { articulo.iva = value; return this; }
        public ArticuloVentaListadoBuilder total(double value) { articulo.total = value; return this; }

        public ArticuloVentaListado build() { return articulo; }
    }
}
