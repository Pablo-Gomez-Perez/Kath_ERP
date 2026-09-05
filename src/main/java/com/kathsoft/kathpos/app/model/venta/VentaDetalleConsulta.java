package com.kathsoft.kathpos.app.model.venta;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VentaDetalleConsulta implements Serializable {

    private static final long serialVersionUID = 1L;

    private VentaById venta;
    private List<ArticuloVentaListado> articulos;

    public VentaDetalleConsulta() {
        this.articulos = new ArrayList<>();
    }

    public VentaDetalleConsulta(VentaById venta, List<ArticuloVentaListado> articulos) {
        this.venta = venta;
        this.articulos = articulos;
    }

    public VentaById getVenta() { return venta; }
    public void setVenta(VentaById venta) { this.venta = venta; }
    public List<ArticuloVentaListado> getArticulos() { return articulos; }
    public void setArticulos(List<ArticuloVentaListado> articulos) { this.articulos = articulos; }

    @Override
    public String toString() {
        return "VentaDetalleConsulta [venta=" + venta + ", articulos=" + articulos + "]";
    }
}
