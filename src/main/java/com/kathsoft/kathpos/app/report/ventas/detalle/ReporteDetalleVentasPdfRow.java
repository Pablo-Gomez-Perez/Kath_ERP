package com.kathsoft.kathpos.app.report.ventas.detalle;

import java.io.Serializable;

/**
 * Fila plana utilizada por JasperReports para representar las distintas
 * secciones del reporte diario.
 */
public final class ReporteDetalleVentasPdfRow implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String SECCION = "SECCION";
    public static final String CABECERA_5 = "CABECERA_5";
    public static final String DATO_5 = "DATO_5";
    public static final String CABECERA_2 = "CABECERA_2";
    public static final String DATO_2 = "DATO_2";
    public static final String SEPARADOR = "SEPARADOR";

    private final String tipoFila;
    private final String columna1;
    private final String columna2;
    private final String columna3;
    private final String columna4;
    private final String columna5;

    public ReporteDetalleVentasPdfRow(
            String tipoFila,
            String columna1,
            String columna2,
            String columna3,
            String columna4,
            String columna5) {

        this.tipoFila = texto(tipoFila);
        this.columna1 = texto(columna1);
        this.columna2 = texto(columna2);
        this.columna3 = texto(columna3);
        this.columna4 = texto(columna4);
        this.columna5 = texto(columna5);
    }

    public String getTipoFila() {
        return tipoFila;
    }

    public String getColumna1() {
        return columna1;
    }

    public String getColumna2() {
        return columna2;
    }

    public String getColumna3() {
        return columna3;
    }

    public String getColumna4() {
        return columna4;
    }

    public String getColumna5() {
        return columna5;
    }

    private static String texto(String valor) {
        return valor == null ? "" : valor;
    }
}
