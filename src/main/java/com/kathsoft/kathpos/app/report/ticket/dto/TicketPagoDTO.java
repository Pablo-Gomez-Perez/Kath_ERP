package com.kathsoft.kathpos.app.report.ticket.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TicketPagoDTO {

    private String formaPago;
    private BigDecimal importe = BigDecimal.ZERO;

    public String getFormaPago() { return formaPago; }
    public void setFormaPago(String formaPago) { this.formaPago = formaPago; }
    public BigDecimal getImporte() { return importe; }
    public void setImporte(BigDecimal importe) {
        this.importe = importe == null ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : importe.setScale(2, RoundingMode.HALF_UP);
    }
    public String getImporteTexto() { return "$" + this.importe.setScale(2, RoundingMode.HALF_UP).toPlainString(); }
}
