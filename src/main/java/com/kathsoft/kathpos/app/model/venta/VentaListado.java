package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;
import java.sql.Date;

public class VentaListado implements Serializable {

    private static final long serialVersionUID = 1L;

    private int folio;
    private Date fecha;
    private String tipo;
    private String atendio;
    private String cliente;
    private double subtotal;
    private double iva;
    private double total;
    private String vigente;

    public VentaListado() {
        super();
    }

    public int getFolio() { return folio; }
    public void setFolio(int folio) { this.folio = folio; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getAtendio() { return atendio; }
    public void setAtendio(String atendio) { this.atendio = atendio; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public String getVigente() { return vigente; }
    public void setVigente(String vigente) { this.vigente = vigente; }

    @Override
    public String toString() {
        return "VentaListado [folio=" + folio + ", fecha=" + fecha + ", tipo=" + tipo + ", atendio=" + atendio
                + ", cliente=" + cliente + ", subtotal=" + subtotal + ", iva=" + iva + ", total=" + total
                + ", vigente=" + vigente + "]";
    }

    public static class VentaListadoBuilder {

        private final VentaListado venta;

        public VentaListadoBuilder() {
            this.venta = new VentaListado();
        }

        public VentaListadoBuilder folio(int value) { venta.folio = value; return this; }
        public VentaListadoBuilder fecha(Date value) { venta.fecha = value; return this; }
        public VentaListadoBuilder tipo(String value) { venta.tipo = value; return this; }
        public VentaListadoBuilder atendio(String value) { venta.atendio = value; return this; }
        public VentaListadoBuilder cliente(String value) { venta.cliente = value; return this; }
        public VentaListadoBuilder subtotal(double value) { venta.subtotal = value; return this; }
        public VentaListadoBuilder iva(double value) { venta.iva = value; return this; }
        public VentaListadoBuilder total(double value) { venta.total = value; return this; }
        public VentaListadoBuilder vigente(String value) { venta.vigente = value; return this; }

        public VentaListado build() { return venta; }
    }
}
