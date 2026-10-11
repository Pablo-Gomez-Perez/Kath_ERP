package com.kathsoft.kathpos.app.model.inventario;

import java.io.Serial;
import java.io.Serializable;

/**
 * Representa una categoría utilizada para clasificar salidas de inventario.
 */
public class CategoriaSalidaInventario implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private int idCategoria;
    private String nombre;
    private String descripcion;
    private boolean activo = true;

    public CategoriaSalidaInventario() {
    }

    public CategoriaSalidaInventario(
            int idCategoria,
            String nombre,
            String descripcion,
            boolean activo) {
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

    public void validarParaGuardar() {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException(
                    "El nombre de la categoría de salida es obligatorio");
        }

        String nombreLimpio = nombre.trim();
        if (nombreLimpio.codePointCount(0, nombreLimpio.length()) > 255) {
            throw new IllegalArgumentException(
                    "El nombre no puede superar 255 caracteres");
        }

        if (descripcion != null
                && descripcion.codePointCount(0, descripcion.length()) > 555) {
            throw new IllegalArgumentException(
                    "La descripción no puede superar 555 caracteres");
        }
    }

    @Override
    public String toString() {
        return nombre == null ? "" : nombre;
    }
}
