package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;

public class VentaFinalizada implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;
    private String message;
    private double subtotal;
    private double iva;
    private double total;
    private double pagos;
    private String tipoVenta;

    public VentaFinalizada() {
        super();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public double getPagos() { return pagos; }
    public void setPagos(double pagos) { this.pagos = pagos; }
    public String getTipoVenta() { return tipoVenta; }
    public void setTipoVenta(String tipoVenta) { this.tipoVenta = tipoVenta; }

    @Override
    public String toString() {
        return "VentaFinalizada [id=" + id + ", message=" + message + ", subtotal=" + subtotal + ", iva=" + iva
                + ", total=" + total + ", pagos=" + pagos + ", tipoVenta=" + tipoVenta + "]";
    }

    public static class VentaFinalizadaBuilder {

        private final VentaFinalizada venta;

        public VentaFinalizadaBuilder() {
            this.venta = new VentaFinalizada();
        }

        public VentaFinalizadaBuilder id(int value) { venta.id = value; return this; }
        public VentaFinalizadaBuilder message(String value) { venta.message = value; return this; }
        public VentaFinalizadaBuilder subtotal(double value) { venta.subtotal = value; return this; }
        public VentaFinalizadaBuilder iva(double value) { venta.iva = value; return this; }
        public VentaFinalizadaBuilder total(double value) { venta.total = value; return this; }
        public VentaFinalizadaBuilder pagos(double value) { venta.pagos = value; return this; }
        public VentaFinalizadaBuilder tipoVenta(String value) { venta.tipoVenta = value; return this; }

        public VentaFinalizada build() { return venta; }
    }
}
