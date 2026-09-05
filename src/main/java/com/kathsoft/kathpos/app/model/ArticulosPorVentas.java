package com.kathsoft.kathpos.app.model;

/**
 * Modelo legado utilizado por las vistas actuales.
 *
 * @deprecated El nuevo flujo utiliza
 *             {@code com.kathsoft.kathpos.app.model.venta.ArticuloPorVenta}.
 */
@Deprecated
public class ArticulosPorVentas {

    private int id;
    private int id_venta;
    private int id_articulo;
    private int cantidad;
    private double subtotal;

    public ArticulosPorVentas(int id, int id_venta, int id_articulo, int cantidad, double subtotal) {
        this.id = id;
        this.id_venta = id_venta;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public ArticulosPorVentas() {
        super();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getId_venta() { return id_venta; }
    public void setId_venta(int id_venta) { this.id_venta = id_venta; }
    public int getId_articulo() { return id_articulo; }
    public void setId_articulo(int id_articulo) { this.id_articulo = id_articulo; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    @Override
    public String toString() {
        return "ArticulosPorVentas [id=" + id + ", id_venta=" + id_venta + ", id_articulo=" + id_articulo
                + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
    }

    public static ArticuloVentaBuilder builder() {
        return new ArticuloVentaBuilder();
    }

    public static class ArticuloVentaBuilder {

        private final ArticulosPorVentas articulo;

        public ArticuloVentaBuilder() {
            this.articulo = new ArticulosPorVentas();
        }

        public ArticuloVentaBuilder id(int value) { articulo.id = value; return this; }
        public ArticuloVentaBuilder idVenta(int value) { articulo.id_venta = value; return this; }
        public ArticuloVentaBuilder idArticulo(int value) { articulo.id_articulo = value; return this; }
        public ArticuloVentaBuilder cantidad(int value) { articulo.cantidad = value; return this; }
        public ArticuloVentaBuilder subtotal(double value) { articulo.subtotal = value; return this; }

        public ArticulosPorVentas build() { return articulo; }
    }
}
