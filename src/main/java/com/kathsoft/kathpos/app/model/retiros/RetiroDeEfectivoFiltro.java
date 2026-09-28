package com.kathsoft.kathpos.app.model.retiros;

import java.time.LocalDate;

/**
 * Filtro opcional por empleado y fechas. La sucursal se recibe de la
 * sesión y se aporta al controlador por separado.
 */
public record RetiroDeEfectivoFiltro(
        Integer idEmpleado,
        LocalDate fechaInicial,
        LocalDate fechaFinal,
        Orden orden) {

    public enum Orden {
        FECHA_RECIENTE(1, "Fecha más reciente"),
        FECHA_ANTIGUA(2, "Fecha más antigua"),
        EMPLEADO(3, "Empleado");

        private final int codigoSql;
        private final String etiqueta;

        Orden(int codigoSql, String etiqueta) {
            this.codigoSql = codigoSql;
            this.etiqueta = etiqueta;
        }

        public int codigoSql() {
            return codigoSql;
        }

        @Override
        public String toString() {
            return etiqueta;
        }
    }

    public RetiroDeEfectivoFiltro {
        if (idEmpleado != null && idEmpleado <= 0) {
            throw new IllegalArgumentException("El ID de empleado del filtro es inválido");
        }
        if (fechaInicial != null && fechaFinal != null
                && fechaInicial.isAfter(fechaFinal)) {
            throw new IllegalArgumentException(
                    "La fecha inicial no puede ser posterior a la fecha final");
        }
        if (orden == null) {
            orden = Orden.FECHA_RECIENTE;
        }
    }

    public static RetiroDeEfectivoFiltro sinFiltros() {
        return new RetiroDeEfectivoFiltro(null, null, null, Orden.FECHA_RECIENTE);
    }
}
