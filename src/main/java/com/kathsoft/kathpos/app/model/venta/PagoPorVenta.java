package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;

public class PagoPorVenta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private int idVenta;
    private int idFormaPago;
    private double importe;

    public PagoPorVenta() {
        super();
    }

    public PagoPorVenta(int id, int idVenta, int idFormaPago, double importe) {
        this.id = id;
        this.idVenta = idVenta;
        this.idFormaPago = idFormaPago;
        this.importe = importe;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdFormaPago() { return idFormaPago; }
    public void setIdFormaPago(int idFormaPago) { this.idFormaPago = idFormaPago; }
    public double getImporte() { return importe; }
    public void setImporte(double importe) { this.importe = importe; }

    @Override
    public String toString() {
        return "PagoPorVenta [id=" + id + ", idVenta=" + idVenta + ", idFormaPago=" + idFormaPago + ", importe="
                + importe + "]";
    }

    public static class PagoPorVentaBuilder {

        private final PagoPorVenta pago;

        public PagoPorVentaBuilder() {
            this.pago = new PagoPorVenta();
        }

        public PagoPorVentaBuilder id(int value) { pago.id = value; return this; }
        public PagoPorVentaBuilder idVenta(int value) { pago.idVenta = value; return this; }
        public PagoPorVentaBuilder idFormaPago(int value) { pago.idFormaPago = value; return this; }
        public PagoPorVentaBuilder importe(double value) { pago.importe = value; return this; }

        public PagoPorVenta build() { return pago; }
    }
}
