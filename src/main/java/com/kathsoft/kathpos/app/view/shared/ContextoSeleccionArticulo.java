package com.kathsoft.kathpos.app.view.shared;

/**
 * Define las reglas operativas con las que se usa el selector compartido
 * de artículos.
 */
public enum ContextoSeleccionArticulo {

    /**
     * Una venta sólo puede seleccionar artículos con existencia disponible.
     */
    VENTA,

    /**
     * Una compra puede seleccionar artículos aun cuando su existencia actual
     * sea cero, porque precisamente puede estar reponiendo ese inventario.
     */
    COMPRA
}
