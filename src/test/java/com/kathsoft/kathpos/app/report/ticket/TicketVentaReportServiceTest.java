package com.kathsoft.kathpos.app.report.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.report.ticket.dto.TicketArticuloDTO;

class TicketVentaReportServiceTest {

    @Test
    void ticketArticuloFormatsAmountsWithTwoDecimals() {
        TicketArticuloDTO articulo = new TicketArticuloDTO();
        articulo.setCantidad(3);
        articulo.setPrecioUnitario(new BigDecimal("10"));
        articulo.setImporte(new BigDecimal("30"));

        assertEquals("3", articulo.getCantidadTexto());
        assertEquals("$10.00", articulo.getPrecioUnitarioTexto());
        assertEquals("$30.00", articulo.getImporteTexto());
    }
}
