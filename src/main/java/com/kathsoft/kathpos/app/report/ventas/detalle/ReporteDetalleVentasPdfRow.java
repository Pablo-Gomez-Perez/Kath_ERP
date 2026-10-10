package com.kathsoft.kathpos.app.report.ventas.detalle;

import java.io.Serializable;

/**
 * Fila plana utilizada por JasperReports para representar las distintas
 * secciones del reporte diario.
 */
public record ReporteDetalleVentasPdfRow(
        String tipoFila,
        String columna1,
        String columna2,
        String columna3,
        String columna4,
        String columna5) implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SECCION = "SECCION";
    public static final String CABECERA_5 = "CABECERA_5";
    public static final String DATO_5 = "DATO_5";
    public static final String CABECERA_2 = "CABECERA_2";
    public static final String DATO_2 = "DATO_2";
    public static final String SEPARADOR = "SEPARADOR";

    public ReporteDetalleVentasPdfRow {
        tipoFila = texto(tipoFila);
        columna1 = texto(columna1);
        columna2 = texto(columna2);
        columna3 = texto(columna3);
        columna4 = texto(columna4);
        columna5 = texto(columna5);
    }

    private static String texto(String valor) {
        return valor == null ? "" : valor;
    }
}
