package com.kathsoft.kathpos.app.model.viewmodel;

/**
 * Representa los datos de una cuenta contable obtenidos por clave.
 *
 * @param idCuenta          identificador de la cuenta
 * @param idCuentaPadre     identificador de la cuenta padre
 * @param fkIdRubro         identificador del rubro contable
 * @param fkIdGrupoContable identificador del grupo contable al que pertenecea
 * @param clave             clave contable
 * @param nombre            nombre de la cuenta
 * @param descripcion       descripción de la cuenta
 * @param nivel             nivel jerárquico
 * @param esUltimoNivel     indica si la cuenta acepta movimientos
 */
public record CuentaContableFormDetails(int idCuenta, int idCuentaPadre, int fkIdRubro, int fkIdGrupoContable,
		String clave, String nombre, String descripcion, short nivel, boolean esUltimoNivel) {

}
