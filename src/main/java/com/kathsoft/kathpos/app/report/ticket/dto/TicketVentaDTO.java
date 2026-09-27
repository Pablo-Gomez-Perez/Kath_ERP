package com.kathsoft.kathpos.app.report.ticket.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class TicketVentaDTO {

    private int folio;
    private Date fechaVenta;
    private String tipoVenta;
    private String statusVenta;
    private String empleado;
    private String cliente;
    private String clienteRfc;
    private String emisorRfc;
    private String emisorRazonSocial;
    private String emisorNombreComercial;
    private String emisorRegimenFiscal;
    private String numeroRegistroSistema;
    private String sucursalNombre;
    private String sucursalDireccion;
    private String sucursalCiudad;
    private String sucursalEstado;
    private String sucursalCodigoPostal;
    private String sucursalTelefono;
    private String sucursalEmail;
    private BigDecimal subtotal = BigDecimal.ZERO;
    private BigDecimal iva = BigDecimal.ZERO;
    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal totalRecibido = BigDecimal.ZERO;
    private BigDecimal cambio = BigDecimal.ZERO;
    private List<TicketArticuloDTO> articulos = new ArrayList<>();
    private List<TicketPagoDTO> pagos = new ArrayList<>();

    public int getFolio() { return folio; }
    public void setFolio(int folio) { this.folio = folio; }
    public Date getFechaVenta() { return fechaVenta; }
    public void setFechaVenta(Date fechaVenta) { this.fechaVenta = fechaVenta; }
    public String getTipoVenta() { return tipoVenta; }
    public void setTipoVenta(String tipoVenta) { this.tipoVenta = tipoVenta; }
    public String getStatusVenta() { return statusVenta; }
    public void setStatusVenta(String statusVenta) { this.statusVenta = statusVenta; }
    public String getEmpleado() { return empleado; }
    public void setEmpleado(String empleado) { this.empleado = empleado; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getClienteRfc() { return clienteRfc; }
    public void setClienteRfc(String clienteRfc) { this.clienteRfc = clienteRfc; }
    public String getEmisorRfc() { return emisorRfc; }
    public void setEmisorRfc(String emisorRfc) { this.emisorRfc = emisorRfc; }
    public String getEmisorRazonSocial() { return emisorRazonSocial; }
    public void setEmisorRazonSocial(String emisorRazonSocial) { this.emisorRazonSocial = emisorRazonSocial; }
    public String getEmisorNombreComercial() { return emisorNombreComercial; }
    public void setEmisorNombreComercial(String emisorNombreComercial) { this.emisorNombreComercial = emisorNombreComercial; }
    public String getEmisorRegimenFiscal() { return emisorRegimenFiscal; }
    public void setEmisorRegimenFiscal(String emisorRegimenFiscal) { this.emisorRegimenFiscal = emisorRegimenFiscal; }
    public String getNumeroRegistroSistema() { return numeroRegistroSistema; }
    public void setNumeroRegistroSistema(String numeroRegistroSistema) { this.numeroRegistroSistema = numeroRegistroSistema; }
    public String getSucursalNombre() { return sucursalNombre; }
    public void setSucursalNombre(String sucursalNombre) { this.sucursalNombre = sucursalNombre; }
    public String getSucursalDireccion() { return sucursalDireccion; }
    public void setSucursalDireccion(String sucursalDireccion) { this.sucursalDireccion = sucursalDireccion; }
    public String getSucursalCiudad() { return sucursalCiudad; }
    public void setSucursalCiudad(String sucursalCiudad) { this.sucursalCiudad = sucursalCiudad; }
    public String getSucursalEstado() { return sucursalEstado; }
    public void setSucursalEstado(String sucursalEstado) { this.sucursalEstado = sucursalEstado; }
    public String getSucursalCodigoPostal() { return sucursalCodigoPostal; }
    public void setSucursalCodigoPostal(String sucursalCodigoPostal) { this.sucursalCodigoPostal = sucursalCodigoPostal; }
    public String getSucursalTelefono() { return sucursalTelefono; }
    public void setSucursalTelefono(String sucursalTelefono) { this.sucursalTelefono = sucursalTelefono; }
    public String getSucursalEmail() { return sucursalEmail; }
    public void setSucursalEmail(String sucursalEmail) { this.sucursalEmail = sucursalEmail; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = normalizar(subtotal); }
    public BigDecimal getIva() { return iva; }
    public void setIva(BigDecimal iva) { this.iva = normalizar(iva); }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = normalizar(total); }
    public BigDecimal getTotalRecibido() { return totalRecibido; }
    public void setTotalRecibido(BigDecimal totalRecibido) { this.totalRecibido = normalizar(totalRecibido); }
    public BigDecimal getCambio() { return cambio; }
    public void setCambio(BigDecimal cambio) { this.cambio = normalizar(cambio); }
    public List<TicketArticuloDTO> getArticulos() { return articulos; }
    public void setArticulos(List<TicketArticuloDTO> articulos) { this.articulos = articulos == null ? new ArrayList<>() : articulos; }
    public List<TicketPagoDTO> getPagos() { return pagos; }
    public void setPagos(List<TicketPagoDTO> pagos) { this.pagos = pagos == null ? new ArrayList<>() : pagos; }

    private static BigDecimal normalizar(BigDecimal valor) {
        return valor == null ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : valor.setScale(2, RoundingMode.HALF_UP);
    }
}
