package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;

public class ArticuloVentaRegistrado implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String message;
    private double precioUnitario;
    private double subtotal;
    private double iva;
    private double total;

    public ArticuloVentaRegistrado() {
        super();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    @Override
    public String toString() {
        return "ArticuloVentaRegistrado [id=" + id + ", message=" + message + ", precioUnitario=" + precioUnitario
                + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total + "]";
    }

    public static class ArticuloVentaRegistradoBuilder {

        private final ArticuloVentaRegistrado articulo;

        public ArticuloVentaRegistradoBuilder() {
            this.articulo = new ArticuloVentaRegistrado();
        }

        public ArticuloVentaRegistradoBuilder id(int value) { articulo.id = value; return this; }
        public ArticuloVentaRegistradoBuilder message(String value) { articulo.message = value; return this; }
        public ArticuloVentaRegistradoBuilder precioUnitario(double value) { articulo.precioUnitario = value; return this; }
        public ArticuloVentaRegistradoBuilder subtotal(double value) { articulo.subtotal = value; return this; }
        public ArticuloVentaRegistradoBuilder iva(double value) { articulo.iva = value; return this; }
        public ArticuloVentaRegistradoBuilder total(double value) { articulo.total = value; return this; }

        public ArticuloVentaRegistrado build() { return articulo; }
    }
}
