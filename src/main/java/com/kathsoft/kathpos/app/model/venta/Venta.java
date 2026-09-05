package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;
import java.sql.Date;

public class Venta implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idVenta;
    private int idEmpleado;
    private int idCliente;
    private long idSucursal;
    private Date fecha;
    private boolean tipoVenta;
    private double subtotal;
    private double iva;
    private double importeTotal;
    private boolean statusVenta;

    public Venta() {
        super();
    }

    public Venta(int idVenta, int idEmpleado, int idCliente, long idSucursal, Date fecha, boolean tipoVenta,
            double subtotal, double iva, double importeTotal, boolean statusVenta) {
        this.idVenta = idVenta;
        this.idEmpleado = idEmpleado;
        this.idCliente = idCliente;
        this.idSucursal = idSucursal;
        this.fecha = fecha;
        this.tipoVenta = tipoVenta;
        this.subtotal = subtotal;
        this.iva = iva;
        this.importeTotal = importeTotal;
        this.statusVenta = statusVenta;
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public long getIdSucursal() { return idSucursal; }
    public void setIdSucursal(long idSucursal) { this.idSucursal = idSucursal; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public boolean isTipoVenta() { return tipoVenta; }
    public void setTipoVenta(boolean tipoVenta) { this.tipoVenta = tipoVenta; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getImporteTotal() { return importeTotal; }
    public void setImporteTotal(double importeTotal) { this.importeTotal = importeTotal; }
    public boolean isStatusVenta() { return statusVenta; }
    public void setStatusVenta(boolean statusVenta) { this.statusVenta = statusVenta; }

    @Override
    public String toString() {
        return "Venta [idVenta=" + idVenta + ", idEmpleado=" + idEmpleado + ", idCliente=" + idCliente
                + ", idSucursal=" + idSucursal + ", fecha=" + fecha + ", tipoVenta=" + tipoVenta + ", subtotal="
                + subtotal + ", iva=" + iva + ", importeTotal=" + importeTotal + ", statusVenta=" + statusVenta + "]";
    }

    public static class VentaBuilder {

        private final Venta venta;

        public VentaBuilder() {
            this.venta = new Venta();
        }

        public VentaBuilder idVenta(int value) { venta.idVenta = value; return this; }
        public VentaBuilder idEmpleado(int value) { venta.idEmpleado = value; return this; }
        public VentaBuilder idCliente(int value) { venta.idCliente = value; return this; }
        public VentaBuilder idSucursal(long value) { venta.idSucursal = value; return this; }
        public VentaBuilder fecha(Date value) { venta.fecha = value; return this; }
        public VentaBuilder tipoVenta(boolean value) { venta.tipoVenta = value; return this; }
        public VentaBuilder subtotal(double value) { venta.subtotal = value; return this; }
        public VentaBuilder iva(double value) { venta.iva = value; return this; }
        public VentaBuilder importeTotal(double value) { venta.importeTotal = value; return this; }
        public VentaBuilder statusVenta(boolean value) { venta.statusVenta = value; return this; }

        public Venta build() { return venta; }
    }
}
