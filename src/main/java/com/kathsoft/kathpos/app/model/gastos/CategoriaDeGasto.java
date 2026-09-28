package com.kathsoft.kathpos.app.model.gastos;

import java.io.Serial;
import java.io.Serializable;

/**
 * Entidad que representa una fila de {@code categoria_de_gasto}.
 *
 * <p>La columna SQL {@code ACTIVO} se mapea como {@code activo}. Las
 * altas y actualizaciones siempre activan la categoría desde el procedimiento
 * almacenado; su valor en este modelo se usa para consultas y listados.</p>
 */
public class CategoriaDeGasto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private boolean activo = true;

    public CategoriaDeGasto() {
    }

    public CategoriaDeGasto(int idCategoria, String nombre, String descripcion, boolean activo) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = activo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Valida el contrato de columnas antes de llamar al SP de alta o edición.
     * El SP repite las validaciones porque el controlador no es el único
     * cliente posible de la base de datos.
     *
     * @throws IllegalArgumentException si el nombre falta o algún valor excede
     *                                  los límites definidos por el esquema
     */
    public void validarParaGuardar() {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la categoría es obligatorio");
        }

        String nombreLimpio = nombre.trim();
        if (nombreLimpio.codePointCount(0, nombreLimpio.length()) > 255) {
            throw new IllegalArgumentException("El nombre no puede superar 255 caracteres");
        }

        if (descripcion != null && descripcion.codePointCount(0, descripcion.length()) > 550) {
            throw new IllegalArgumentException("La descripción no puede superar 550 caracteres");
        }
    }

    @Override
    public String toString() {
        return nombre == null ? "" : nombre;
    }
}
