package com.kathsoft.kathpos.app.model.viewmodel;

import com.kathsoft.kathpos.app.view.contabilidad.ConsultaCuentaContableDialog;

/**
 * <summary> Objeto usado para obtener el ide y clave de la cuenta contable buscada </summary>
 * <p> Este objeto es invocado principalmente en el Dialog {@link ConsultaCuentaContableDialog} para la asignación de la clave constable a otro objeto </p>
 * 
 */
public record CuentaContableResponseViewModel(int idCuentaContable, String claveCuentaContable) {
	
	@Override
	public String toString() {
		return this.claveCuentaContable;
	}

}
