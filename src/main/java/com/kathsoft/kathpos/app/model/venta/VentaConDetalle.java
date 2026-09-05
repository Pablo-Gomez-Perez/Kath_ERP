package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VentaConDetalle implements Serializable {

    private static final long serialVersionUID = 1L;

    private Venta venta;
    private List<ArticuloPorVenta> articulos;
    private List<PagoPorVenta> pagos;

    public VentaConDetalle() {
        this.articulos = new ArrayList<>();
        this.pagos = new ArrayList<>();
    }

    public VentaConDetalle(Venta venta, List<ArticuloPorVenta> articulos, List<PagoPorVenta> pagos) {
        this.venta = venta;
        this.articulos = articulos;
        this.pagos = pagos;
    }

    public Venta getVenta() { return venta; }
    public void setVenta(Venta venta) { this.venta = venta; }
    public List<ArticuloPorVenta> getArticulos() { return articulos; }
    public void setArticulos(List<ArticuloPorVenta> articulos) { this.articulos = articulos; }
    public List<PagoPorVenta> getPagos() { return pagos; }
    public void setPagos(List<PagoPorVenta> pagos) { this.pagos = pagos; }

    @Override
    public String toString() {
        return "VentaConDetalle [venta=" + venta + ", articulos=" + articulos + ", pagos=" + pagos + "]";
    }
}
