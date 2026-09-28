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
        FECHA_RECIENTE(1),
        FECHA_ANTIGUA(2),
        EMPLEADO(3),
        CATEGORIA(4);

        private final int codigoSql;

        Orden(int codigoSql) {
            this.codigoSql = codigoSql;
        }

        public int codigoSql() {
            return codigoSql;
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
