package com.kathsoft.kathpos.app.model.viewmodel;

/**
 * Representa la respuesta devuelta por un procedimiento almacenado.
 *
 * @param id identificador o código de respuesta
 * @param message mensaje de la operación
 */
public record SpResponseModel(int id, String message) {
}
