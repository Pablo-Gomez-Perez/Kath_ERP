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
