package com.kathsoft.kathpos.app.report.ventas.detalle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Sección tabular del reporte diario de ventas.
 */
public record ReporteDetalleVentasSeccion(
        String titulo,
        List<String> columnas,
        List<List<String>> filas) implements Serializable {

    private static final long serialVersionUID = 1L;

    public ReporteDetalleVentasSeccion {
        titulo = Objects.requireNonNull(titulo, "El título de la sección es obligatorio").trim();
        if (titulo.isEmpty()) {
            throw new IllegalArgumentException("El título de la sección es obligatorio");
        }

        columnas = columnas == null ? List.of() : List.copyOf(columnas);
        if (columnas.isEmpty()) {
            throw new IllegalArgumentException("La sección debe contener columnas");
        }

        List<List<String>> copiaFilas = new ArrayList<>();
        if (filas != null) {
            for (List<String> fila : filas) {
                List<String> copia = fila == null ? List.of() : List.copyOf(fila);
                if (copia.size() != columnas.size()) {
                    throw new IllegalArgumentException(
                            "Cada fila debe contener el mismo número de columnas que la sección");
                }
                copiaFilas.add(copia);
            }
        }
        filas = List.copyOf(copiaFilas);
    }

    public boolean tieneDatos() {
        return !filas.isEmpty();
    }
}
