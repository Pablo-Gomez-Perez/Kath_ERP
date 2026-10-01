package com.kathsoft.kathpos.app.model.retiros;

/**
 * Estado operativo del día para una sucursal.
 */
public enum EstadoCorteDiario {
    /** Todavía no se ha registrado ningún corte final. */
    ABIERTO,

    /** Existe un corte final activo y no se permite ninguna nueva alta. */
    CORTE_FINAL_ACTIVO,

    /**
     * Existió un corte final, pero está inhabilitado. El único movimiento
     * nuevo permitido es registrar su corte final de reemplazo.
     */
    CORTE_FINAL_PENDIENTE_DE_REEMPLAZO
}
