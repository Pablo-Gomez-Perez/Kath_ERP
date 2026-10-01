package com.kathsoft.kathpos.app.model.configuracion;

/** Estado real de la BD; instalar la aplicación en otro equipo no reinicia datos. */
public record EstadoInicializacion(boolean requiereInicializacion,
        int sucursales, int sucursalesActivas) {
    public boolean necesitaCrearSucursal() {
        return requiereInicializacion && sucursales == 0;
    }

    public boolean necesitaSeleccionarSucursal() {
        return requiereInicializacion && sucursales > 0 && sucursalesActivas > 0;
    }

    public boolean requiereIntervencion() {
        return requiereInicializacion && sucursales > 0 && sucursalesActivas == 0;
    }
}
