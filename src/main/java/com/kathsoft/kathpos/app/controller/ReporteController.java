package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.model.reporte.CobroResumenDia;
import com.kathsoft.kathpos.app.model.reporte.RetiroEfectivoDia;
import com.kathsoft.kathpos.app.model.reporte.VentaTotalPorFecha;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Controlador de consultas agregadas utilizadas por los reportes de Kath ERP.
 */
public class ReporteController implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Obtiene los totales diarios de ventas vigentes correspondientes a una sucursal
     * dentro de un intervalo inclusivo de fechas.
     *
     * @param idSucursal sucursal cuyo historial será consultado
     * @param fechaInicio primer día incluido en el reporte
     * @param fechaFin último día incluido en el reporte
     * @return filas agregadas por fecha, ordenadas por el procedimiento almacenado
     * @throws SQLException si falla la comunicación con MySQL
     * @throws IllegalArgumentException si los parámetros del reporte son inválidos
     */
    public List<VentaTotalPorFecha> getVentasTotalesByFechas(
            long idSucursal,
            LocalDate fechaInicio,
            LocalDate fechaFin) throws SQLException {

        validarParametros(idSucursal, fechaInicio, fechaFin);

        List<VentaTotalPorFecha> resultado = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL getVentasTotalesByFechas(?,?,?)")) {

            stm.setLong(1, idSucursal);
            stm.setDate(2, Date.valueOf(fechaInicio));
            stm.setDate(3, Date.valueOf(fechaFin));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultado.add(mapearVentaTotalPorFecha(rs));
                }
            }
        }

        return resultado;
    }

    /**
     * Lista el importe cobrado por forma de pago en una sucursal y fecha.
     * Incluye pagos de venta y cobros posteriores registrados ese día.
     */
    public List<CobroResumenDia> listDetalleCobrosPorFormaDePago(
            long idSucursal,
            LocalDate fecha) throws SQLException {

        validarSucursalYFecha(idSucursal, fecha);
        List<CobroResumenDia> resultado = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL listDetalleCobrosPorFormaDePago(?,?)")) {

            stm.setLong(1, idSucursal);
            stm.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new CobroResumenDia(
                            rs.getString("nombre"),
                            leerImporte(rs, "total")));
                }
            }
        }

        return resultado;
    }

    /**
     * Lista el importe cobrado por empleado en una sucursal y fecha.
     * La atribución usa el empleado de la venta para pagos iniciales y el
     * empleado del cobro para abonos posteriores.
     */
    public List<CobroResumenDia> listDetalleCobradoVentasPorEmpleado(
            long idSucursal,
            LocalDate fecha) throws SQLException {

        validarSucursalYFecha(idSucursal, fecha);
        List<CobroResumenDia> resultado = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL listDetalleCobradoVentasPorEmpleado(?,?)")) {

            stm.setLong(1, idSucursal);
            stm.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new CobroResumenDia(
                            rs.getString("nombre"),
                            leerImporte(rs, "total")));
                }
            }
        }

        return resultado;
    }

    /**
     * Lista los retiros activos realizados en una sucursal y fecha.
     */
    public List<RetiroEfectivoDia> listRetirosDeEfectivoDelDia(
            long idSucursal,
            LocalDate fecha) throws SQLException {

        validarSucursalYFecha(idSucursal, fecha);
        List<RetiroEfectivoDia> resultado = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL listRetirosDeEfectivoDelDia(?,?)")) {

            stm.setLong(1, idSucursal);
            stm.setDate(2, Date.valueOf(fecha));

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new RetiroEfectivoDia(
                            rs.getString("folio"),
                            leerImporte(rs, "importe")));
                }
            }
        }

        return resultado;
    }

    static void validarSucursalYFecha(long idSucursal, LocalDate fecha) {
        if (idSucursal <= 0) {
            throw new IllegalArgumentException("El identificador de sucursal es inválido");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("Debe indicar la fecha del reporte");
        }
    }

    static void validarParametros(long idSucursal, LocalDate fechaInicio, LocalDate fechaFin) {
        if (idSucursal <= 0) {
            throw new IllegalArgumentException("El identificador de sucursal es inválido");
        }
        if (fechaInicio == null || fechaFin == null) {
            throw new IllegalArgumentException("Debe indicar la fecha inicial y la fecha final");
        }
        if (fechaInicio.isAfter(fechaFin)) {
            throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
        }
    }

    private static VentaTotalPorFecha mapearVentaTotalPorFecha(ResultSet rs) throws SQLException {
        Date fecha = rs.getDate("fecha");
        if (fecha == null) {
            throw new SQLException("El procedimiento devolvió una fila sin fecha");
        }

        return new VentaTotalPorFecha(
                fecha.toLocalDate(),
                rs.getLong("numero_ventas"),
                leerImporte(rs, "subtotal"),
                leerImporte(rs, "iva"),
                leerImporte(rs, "total"));
    }

    private static BigDecimal leerImporte(ResultSet rs, String columna) throws SQLException {
        BigDecimal importe = rs.getBigDecimal(columna);
        return importe == null
                ? BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)
                : importe.setScale(2, RoundingMode.HALF_UP);
    }
}
