package com.kathsoft.kathpos.app.report.ticket.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketArticuloDTO {

    private String codigoArticulo;
    private String unidad;
    private String descripcion;
    private int cantidad;
    private BigDecimal precioUnitario = BigDecimal.ZERO;
    private BigDecimal importe = BigDecimal.ZERO;

    public String getCodigoArticulo() { return codigoArticulo; }
    public void setCodigoArticulo(String codigoArticulo) { this.codigoArticulo = codigoArticulo; }
    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = normalizar(precioUnitario); }
    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) { this.importe = normalizar(importe); }

    public String getCantidadTexto() { return String.valueOf(this.cantidad); }
    public String getPrecioUnitarioTexto() { return "$" + normalizar(this.precioUnitario).toPlainString(); }
    public String getImporteTexto() { return "$" + normalizar(this.importe).toPlainString(); }

    private static BigDecimal normalizar(BigDecimal valor) {
        return valor == null ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : valor.setScale(2, RoundingMode.HALF_UP);
    }
}
