package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;
import java.sql.Date;

public class VentaById implements Serializable {

    private static final long serialVersionUID = 1L;

    private int idVenta;
    private int idEmpleado;
    private String nombreEmpleado;
    private String nombreCortoEmpleado;
    private int idCliente;
    private String nombreCliente;
    private String nombreCortoCliente;
    private long idSucursal;
    private Date fecha;
    private boolean tipoVenta;
    private String tipoVentaDescripcion;
    private double subtotal;
    private double iva;
    private double importeTotal;
    private boolean statusVenta;
    private String statusVentaDescripcion;

    public VentaById() {
        super();
    }

    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }
    public int getIdEmpleado() { return idEmpleado; }
    public void setIdEmpleado(int idEmpleado) { this.idEmpleado = idEmpleado; }
    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }
    public String getNombreCortoEmpleado() { return nombreCortoEmpleado; }
    public void setNombreCortoEmpleado(String nombreCortoEmpleado) { this.nombreCortoEmpleado = nombreCortoEmpleado; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombreCliente() { return nombreCliente; }
    public void setNombreCliente(String nombreCliente) { this.nombreCliente = nombreCliente; }
    public String getNombreCortoCliente() { return nombreCortoCliente; }
    public void setNombreCortoCliente(String nombreCortoCliente) { this.nombreCortoCliente = nombreCortoCliente; }
    public long getIdSucursal() { return idSucursal; }
    public void setIdSucursal(long idSucursal) { this.idSucursal = idSucursal; }
    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }
    public boolean isTipoVenta() { return tipoVenta; }
    public void setTipoVenta(boolean tipoVenta) { this.tipoVenta = tipoVenta; }
    public String getTipoVentaDescripcion() { return tipoVentaDescripcion; }
    public void setTipoVentaDescripcion(String tipoVentaDescripcion) { this.tipoVentaDescripcion = tipoVentaDescripcion; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }
    public double getImporteTotal() { return importeTotal; }
    public void setImporteTotal(double importeTotal) { this.importeTotal = importeTotal; }
    public boolean isStatusVenta() { return statusVenta; }
    public void setStatusVenta(boolean statusVenta) { this.statusVenta = statusVenta; }
    public String getStatusVentaDescripcion() { return statusVentaDescripcion; }
    public void setStatusVentaDescripcion(String statusVentaDescripcion) { this.statusVentaDescripcion = statusVentaDescripcion; }

    @Override
    public String toString() {
        return "VentaById [idVenta=" + idVenta + ", idEmpleado=" + idEmpleado + ", nombreEmpleado=" + nombreEmpleado
                + ", nombreCortoEmpleado=" + nombreCortoEmpleado + ", idCliente=" + idCliente + ", nombreCliente="
                + nombreCliente + ", nombreCortoCliente=" + nombreCortoCliente + ", idSucursal=" + idSucursal
                + ", fecha=" + fecha + ", tipoVenta=" + tipoVenta + ", tipoVentaDescripcion=" + tipoVentaDescripcion
                + ", subtotal=" + subtotal + ", iva=" + iva + ", importeTotal=" + importeTotal + ", statusVenta="
                + statusVenta + ", statusVentaDescripcion=" + statusVentaDescripcion + "]";
    }

    public static class VentaByIdBuilder {

        private final VentaById venta;

        public VentaByIdBuilder() {
            this.venta = new VentaById();
        }

        public VentaByIdBuilder idVenta(int value) { venta.idVenta = value; return this; }
        public VentaByIdBuilder idEmpleado(int value) { venta.idEmpleado = value; return this; }
        public VentaByIdBuilder nombreEmpleado(String value) { venta.nombreEmpleado = value; return this; }
        public VentaByIdBuilder nombreCortoEmpleado(String value) { venta.nombreCortoEmpleado = value; return this; }
        public VentaByIdBuilder idCliente(int value) { venta.idCliente = value; return this; }
        public VentaByIdBuilder nombreCliente(String value) { venta.nombreCliente = value; return this; }
        public VentaByIdBuilder nombreCortoCliente(String value) { venta.nombreCortoCliente = value; return this; }
        public VentaByIdBuilder idSucursal(long value) { venta.idSucursal = value; return this; }
        public VentaByIdBuilder fecha(Date value) { venta.fecha = value; return this; }
        public VentaByIdBuilder tipoVenta(boolean value) { venta.tipoVenta = value; return this; }
        public VentaByIdBuilder tipoVentaDescripcion(String value) { venta.tipoVentaDescripcion = value; return this; }
        public VentaByIdBuilder subtotal(double value) { venta.subtotal = value; return this; }
        public VentaByIdBuilder iva(double value) { venta.iva = value; return this; }
        public VentaByIdBuilder importeTotal(double value) { venta.importeTotal = value; return this; }
        public VentaByIdBuilder statusVenta(boolean value) { venta.statusVenta = value; return this; }
        public VentaByIdBuilder statusVentaDescripcion(String value) { venta.statusVentaDescripcion = value; return this; }

        public VentaById build() { return venta; }
    }
}
