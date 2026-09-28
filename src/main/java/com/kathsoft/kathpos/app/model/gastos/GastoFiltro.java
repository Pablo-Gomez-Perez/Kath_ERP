package com.kathsoft.kathpos.app.model.gastos;

import java.time.LocalDate;

/**
 * Filtros del listado de gastos de una sucursal.
 *
 * <p>Los valores null representan ausencia del filtro.</p>
 */
public record GastoFiltro(
        Integer idEmpleado,
        Integer idCategoria,
        LocalDate fechaInicial,
        LocalDate fechaFinal,
        Orden orden) {

    public enum Orden {
        FECHA_RECIENTE(1, "Fecha más reciente"),
        FECHA_ANTIGUA(2, "Fecha más antigua"),
        EMPLEADO(3, "Empleado"),
        CATEGORIA(4, "Categoría de gasto");

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

    public GastoFiltro {
        if (idEmpleado != null && idEmpleado <= 0) {
            throw new IllegalArgumentException("El filtro de empleado es inválido");
        }
        if (idCategoria != null && idCategoria <= 0) {
            throw new IllegalArgumentException("El filtro de categoría es inválido");
        }
        if (fechaInicial != null && fechaFinal != null && fechaInicial.isAfter(fechaFinal)) {
            throw new IllegalArgumentException("La fecha inicial no puede superar la fecha final");
        }
        if (orden == null) {
            orden = Orden.FECHA_RECIENTE;
        }
    }

    public static GastoFiltro sinFiltros() {
        return new GastoFiltro(null, null, null, null, Orden.FECHA_RECIENTE);
    }
}
