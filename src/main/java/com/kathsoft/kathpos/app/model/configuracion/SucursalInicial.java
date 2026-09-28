package com.kathsoft.kathpos.app.model.configuracion;

/** Una sucursal activa que puede recibir el primer empleado. */
public record SucursalInicial(long id, String nombre, String estado,
        String ciudad, String direccion, String codigoPostal) {
    @Override
    public String toString() {
        return nombre + " (#" + id + ")";
    }
}
