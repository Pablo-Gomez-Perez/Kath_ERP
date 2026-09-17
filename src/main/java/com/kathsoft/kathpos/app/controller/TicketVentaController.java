package com.kathsoft.kathpos.app.controller;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.report.ticket.dto.TicketArticuloDTO;
import com.kathsoft.kathpos.app.report.ticket.dto.TicketPagoDTO;
import com.kathsoft.kathpos.app.report.ticket.dto.TicketVentaDTO;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Recupera la información de un ticket de venta mediante el contrato almacenado
 * {@code getTicketVentaById}. No contiene SQL de negocio ni reconstruye importes
 * en Java; únicamente mapea los result sets devueltos por el procedimiento.
 */
public class TicketVentaController {

    public TicketVentaDTO getTicketVentaById(int idVenta) {
        if (idVenta <= 0) {
            throw new IllegalArgumentException("La venta es obligatoria para generar el ticket");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL getTicketVentaById(?)")) {
            stm.setInt(1, idVenta);

            if (!stm.execute()) {
                throw new IllegalStateException("El procedimiento del ticket no devolvió la cabecera de la venta");
            }

            TicketVentaDTO ticket;
            try (ResultSet rs = stm.getResultSet()) {
                if (rs == null || !rs.next()) {
                    throw new IllegalStateException("No existe información para generar el ticket de la venta " + idVenta);
                }
                ticket = mapCabecera(rs);
            }

            List<TicketArticuloDTO> articulos = new ArrayList<>();
            if (stm.getMoreResults()) {
                try (ResultSet rs = stm.getResultSet()) {
                    while (rs != null && rs.next()) {
                        articulos.add(mapArticulo(rs));
                    }
                }
            }
            ticket.setArticulos(articulos);

            List<TicketPagoDTO> pagos = new ArrayList<>();
            if (stm.getMoreResults()) {
                try (ResultSet rs = stm.getResultSet()) {
                    while (rs != null && rs.next()) {
                        pagos.add(mapPago(rs));
                    }
                }
            }
            ticket.setPagos(pagos);
            return ticket;
        } catch (SQLException er) {
            er.printStackTrace(System.err);
            throw new IllegalStateException("No fue posible consultar la información del ticket: " + er.getMessage(), er);
        }
    }

    private TicketVentaDTO mapCabecera(ResultSet rs) throws SQLException {
        TicketVentaDTO ticket = new TicketVentaDTO();
        ticket.setFolio(rs.getInt("folio"));
        ticket.setFechaVenta(rs.getDate("fecha_venta"));
        ticket.setTipoVenta(rs.getString("tipo_venta"));
        ticket.setStatusVenta(rs.getString("status_venta"));
        ticket.setEmpleado(rs.getString("empleado"));
        ticket.setCliente(rs.getString("cliente"));
        ticket.setClienteRfc(rs.getString("cliente_rfc"));
        ticket.setEmisorRfc(rs.getString("rfc_emisor"));
        ticket.setEmisorRazonSocial(rs.getString("emisor_razon_social"));
        ticket.setEmisorNombreComercial(rs.getString("emisor_nombre_comercial"));
        ticket.setEmisorRegimenFiscal(rs.getString("emisor_regimen_fiscal"));
        ticket.setNumeroRegistroSistema(rs.getString("numero_registro_sistema"));
        ticket.setSucursalNombre(rs.getString("sucursal_nombre"));
        ticket.setSucursalDireccion(rs.getString("sucursal_direccion"));
        ticket.setSucursalCiudad(rs.getString("sucursal_ciudad"));
        ticket.setSucursalEstado(rs.getString("sucursal_estado"));
        ticket.setSucursalCodigoPostal(rs.getString("sucursal_codigo_postal"));
        ticket.setSucursalTelefono(rs.getString("sucursal_telefono"));
        ticket.setSucursalEmail(rs.getString("sucursal_email"));
        ticket.setSubtotal(decimal(rs, "subtotal"));
        ticket.setIva(decimal(rs, "iva"));
        ticket.setTotal(decimal(rs, "total"));
        return ticket;
    }

    private TicketArticuloDTO mapArticulo(ResultSet rs) throws SQLException {
        TicketArticuloDTO articulo = new TicketArticuloDTO();
        articulo.setCodigoArticulo(rs.getString("codigo_articulo"));
        articulo.setUnidad(rs.getString("unidad"));
        articulo.setDescripcion(rs.getString("descripcion"));
        articulo.setCantidad(rs.getInt("cantidad"));
        articulo.setPrecioUnitario(decimal(rs, "precio_unitario"));
        articulo.setImporte(decimal(rs, "importe"));
        return articulo;
    }

    private TicketPagoDTO mapPago(ResultSet rs) throws SQLException {
        TicketPagoDTO pago = new TicketPagoDTO();
        pago.setFormaPago(rs.getString("forma_pago"));
        pago.setImporte(decimal(rs, "importe"));
        return pago;
    }

    private BigDecimal decimal(ResultSet rs, String columna) throws SQLException {
        BigDecimal valor = rs.getBigDecimal(columna);
        return valor == null ? BigDecimal.ZERO : valor;
    }
}
