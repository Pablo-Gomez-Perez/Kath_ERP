package com.kathsoft.kathpos.app.report.ticket;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.kathsoft.kathpos.app.controller.TicketVentaController;
import com.kathsoft.kathpos.app.report.JasperReportService;
import com.kathsoft.kathpos.app.report.ReportDirectoryResolver;
import com.kathsoft.kathpos.app.report.ticket.dto.TicketPagoDTO;
import com.kathsoft.kathpos.app.report.ticket.dto.TicketVentaDTO;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/** Genera el comprobante PDF de una venta ya confirmada en base de datos. */
public class TicketVentaReportService {

    private static final String TEMPLATE = "/reports/ticket_venta.jrxml";
    private static final DateTimeFormatter FECHA_ARCHIVO = DateTimeFormatter.BASIC_ISO_DATE;

    private final TicketVentaController ticketController;
    private final JasperReportService jasperReportService;

    public TicketVentaReportService() {
        this(new TicketVentaController(), new JasperReportService());
    }

    TicketVentaReportService(TicketVentaController ticketController, JasperReportService jasperReportService) {
        this.ticketController = ticketController;
        this.jasperReportService = jasperReportService;
    }

    public Path generarTicket(int idVenta, BigDecimal totalRecibido, BigDecimal cambio) throws Exception {
        TicketVentaDTO ticket = this.ticketController.getTicketVentaById(idVenta);
        this.validarTicket(ticket);

        ticket.setTotalRecibido(totalRecibido);
        ticket.setCambio(cambio);

        Map<String, Object> parametros = this.construirParametros(ticket);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(ticket.getArticulos(), false);
        Path archivo = this.construirRuta(ticket);
        int pageHeight = this.calcularAltoPagina(ticket);

        return this.jasperReportService.exportPdf(TEMPLATE, parametros, dataSource, archivo, pageHeight);
    }

    private Map<String, Object> construirParametros(TicketVentaDTO ticket) {
        Map<String, Object> p = new HashMap<>();
        p.put("EMISOR_NOMBRE_COMERCIAL", texto(ticket.getEmisorNombreComercial()));
        p.put("EMISOR_RAZON_SOCIAL", texto(ticket.getEmisorRazonSocial()));
        p.put("EMISOR_RFC", texto(ticket.getEmisorRfc()));
        p.put("EMISOR_REGIMEN_FISCAL", texto(ticket.getEmisorRegimenFiscal()));
        p.put("SUCURSAL", texto(ticket.getSucursalNombre()));
        p.put("DOMICILIO", domicilio(ticket));
        p.put("LUGAR_EXPEDICION", lugarExpedicion(ticket));
        p.put("CONTACTO", contacto(ticket));
        p.put("FOLIO", String.valueOf(ticket.getFolio()));
        p.put("FECHA", ticket.getFechaVenta() == null ? "" : ticket.getFechaVenta().toLocalDate().toString());
        p.put("EMPLEADO", texto(ticket.getEmpleado()));
        p.put("CLIENTE", texto(ticket.getCliente()));
        p.put("CLIENTE_RFC", texto(ticket.getClienteRfc()));
        p.put("TIPO_VENTA", texto(ticket.getTipoVenta()));
        p.put("STATUS_VENTA", texto(ticket.getStatusVenta()));
        p.put("SUBTOTAL", moneda(ticket.getSubtotal()));
        p.put("IVA", moneda(ticket.getIva()));
        p.put("TOTAL", moneda(ticket.getTotal()));
        p.put("PAGOS", pagos(ticket.getPagos()));
        p.put("TOTAL_RECIBIDO", moneda(ticket.getTotalRecibido()));
        p.put("CAMBIO", moneda(ticket.getCambio()));
        p.put("REGISTRO_SISTEMA", registroSistema(ticket));
        return p;
    }

    private Path construirRuta(TicketVentaDTO ticket) throws Exception {
        String fecha = ticket.getFechaVenta() == null ? "sin-fecha"
                : FECHA_ARCHIVO.format(ticket.getFechaVenta().toLocalDate());
        String nombre = String.format("ticket_venta_%08d_%s.pdf", ticket.getFolio(), fecha);
        return ReportDirectoryResolver.ticketsDirectory().resolve(nombre);
    }

    private int calcularAltoPagina(TicketVentaDTO ticket) {
        int articulos = ticket.getArticulos() == null ? 0 : ticket.getArticulos().size();
        int pagos = ticket.getPagos() == null ? 0 : ticket.getPagos().size();
        return Math.max(560, Math.min(14000, 500 + articulos * 30 + pagos * 14));
    }

    private void validarTicket(TicketVentaDTO ticket) {
        if (ticket == null || ticket.getFolio() <= 0) {
            throw new IllegalStateException("No existe información válida de la venta para generar el ticket");
        }
        if (esVacio(ticket.getEmisorRfc())) {
            throw new IllegalStateException("Falta configurar el RFC del emisor");
        }
        if (esVacio(ticket.getEmisorRazonSocial())) {
            throw new IllegalStateException("Falta configurar el nombre o razón social del emisor");
        }
        if (esVacio(ticket.getEmisorRegimenFiscal())) {
            throw new IllegalStateException("Falta configurar el régimen fiscal del emisor");
        }
        if (esVacio(ticket.getSucursalDireccion()) || esVacio(ticket.getSucursalCodigoPostal())) {
            throw new IllegalStateException("La sucursal no tiene domicilio y código postal suficientes para el ticket");
        }
        if (ticket.getArticulos() == null || ticket.getArticulos().isEmpty()) {
            throw new IllegalStateException("La venta no contiene partidas para generar el ticket");
        }
    }

    private String domicilio(TicketVentaDTO ticket) {
        return unir(" ", texto(ticket.getSucursalDireccion()), "C.P. " + texto(ticket.getSucursalCodigoPostal()));
    }

    private String lugarExpedicion(TicketVentaDTO ticket) {
        return unir(", ", texto(ticket.getSucursalCiudad()), texto(ticket.getSucursalEstado()));
    }

    private String contacto(TicketVentaDTO ticket) {
        return unir(" | ", prefijo("Tel. ", ticket.getSucursalTelefono()), prefijo("Email: ", ticket.getSucursalEmail()));
    }

    private String registroSistema(TicketVentaDTO ticket) {
        return esVacio(ticket.getNumeroRegistroSistema()) ? ""
                : "Registro del sistema: " + ticket.getNumeroRegistroSistema().trim();
    }

    private String pagos(List<TicketPagoDTO> pagos) {
        if (pagos == null || pagos.isEmpty()) {
            return "Sin pago inicial (venta a crédito)";
        }
        return pagos.stream()
                .map(p -> texto(p.getFormaPago()) + ": " + p.getImporteTexto())
                .collect(Collectors.joining("\n"));
    }

    private String moneda(BigDecimal valor) {
        BigDecimal normalizado = valor == null ? BigDecimal.ZERO : valor.setScale(2, RoundingMode.HALF_UP);
        return "$" + normalizado.toPlainString();
    }

    private static String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }

    private static String prefijo(String prefijo, String valor) {
        return esVacio(valor) ? "" : prefijo + valor.trim();
    }

    private static String unir(String separador, String... partes) {
        return java.util.Arrays.stream(partes).filter(parte -> !esVacio(parte)).collect(Collectors.joining(separador));
    }

    private static boolean esVacio(String valor) {
        return valor == null || valor.isBlank();
    }
}
