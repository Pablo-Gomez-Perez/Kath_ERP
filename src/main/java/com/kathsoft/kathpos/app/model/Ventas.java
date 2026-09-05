package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.sql.Date;

import com.kathsoft.kathpos.app.model.cliente.Clientes;
import com.kathsoft.kathpos.app.model.empleado.Empleado;

/**
 * Modelo legado utilizado por las vistas de ventas actuales.
 *
 * @deprecated El nuevo flujo de persistencia utiliza
 *             {@code com.kathsoft.kathpos.app.model.venta.Venta}.
 */
@Deprecated
public class Ventas implements Serializable {

    private static final long serialVersionUID = 6942302852728487438L;

    private int idVenta;
    private int idSucursal;
    private Date fechaVenta;
    private boolean ventaContado;
    private Empleado empleado;
    private Clientes cliente;
    private double subTotal;
    private double iva;
    private double total;
    private boolean statusVenta;

    public Ventas(int idVenta, int idSucursal, Date fechaVenta, boolean tipoVenta, Empleado empleado, Clientes cliente,
            double subTotal, double iva, double total, boolean statusVenta) {
        this.idVenta = idVenta;
        this.idSucursal = idSucursal;
        this.fechaVenta = fechaVenta;
        this.ventaContado = tipoVenta;
        this.empleado = empleado;
        this.cliente = cliente;
        this.subTotal = subTotal;
        this.iva = iva;
        this.total = total;
        this.statusVenta = statusVenta;
    }

    public Ventas() {
        super();
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdSucursal() { return idSucursal; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }
    public boolean isVentaContado() { return ventaContado; }
    public void setVentaContado(boolean ventaContado) { this.ventaContado = ventaContado; }
    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
    public boolean isTipoVenta() { return ventaContado; }
    public void setTipoVenta(boolean tipoVenta) { this.ventaContado = tipoVenta; }
    public Empleado getEmpleado() { return empleado; }
    public void setEmpleado(Empleado empleado) { this.empleado = empleado; }
    public Clientes getCliente() { return cliente; }
    public void setCliente(Clientes cliente) { this.cliente = cliente; }
    public double getSubTotal() { return subTotal; }
    public void setSubTotal(double subTotal) { this.subTotal = subTotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    public boolean isStatusVenta() { return statusVenta; }
    public void setStatusVenta(boolean statusVenta) { this.statusVenta = statusVenta; }

    @Override
    public String toString() {
        return "Ventas [idVenta=" + idVenta + ", idSucursal=" + idSucursal + ", fechaVenta=" + fechaVenta
                + ", ventaContado=" + ventaContado + ", empleado=" + empleado + ", cliente=" + cliente + ", subTotal="
                + subTotal + ", iva=" + iva + ", total=" + total + ", statusVenta=" + statusVenta + "]";
    }

    public static VentaBuilder builder() {
        return new VentaBuilder();
    }

    public static class VentaBuilder {

        private final Ventas venta;

        public VentaBuilder() {
            this.venta = new Ventas();
        }

        public VentaBuilder idVenta(int value) { venta.idVenta = value; return this; }
        public VentaBuilder idSucursal(int value) { venta.idSucursal = value; return this; }
        public VentaBuilder fechaVenta(Date value) { venta.fechaVenta = value; return this; }
        public VentaBuilder ventaContado(boolean value) { venta.ventaContado = value; return this; }
        public VentaBuilder empleado(Empleado value) { venta.empleado = value; return this; }
        public VentaBuilder cliente(Clientes value) { venta.cliente = value; return this; }
        public VentaBuilder subTotal(double value) { venta.subTotal = value; return this; }
        public VentaBuilder iva(double value) { venta.iva = value; return this; }
        public VentaBuilder total(double value) { venta.total = value; return this; }
        public VentaBuilder statusVenta(boolean value) { venta.statusVenta = value; return this; }

        public Ventas build() { return venta; }
    }
}
