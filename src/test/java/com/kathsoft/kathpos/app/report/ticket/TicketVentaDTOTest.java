package com.kathsoft.kathpos.app.report.ticket;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.report.ticket.dto.TicketVentaDTO;

class TicketVentaDTOTest {

    @Test
    void normalizesMonetaryValuesToTwoDecimals() {
        TicketVentaDTO ticket = new TicketVentaDTO();
        ticket.setSubtotal(new BigDecimal("100"));
        ticket.setIva(new BigDecimal("16"));
        ticket.setTotal(new BigDecimal("116"));

        assertEquals(new BigDecimal("100.00"), ticket.getSubtotal());
        assertEquals(new BigDecimal("16.00"), ticket.getIva());
        assertEquals(new BigDecimal("116.00"), ticket.getTotal());
    }
}
