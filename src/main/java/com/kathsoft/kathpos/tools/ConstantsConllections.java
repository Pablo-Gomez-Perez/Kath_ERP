package com.kathsoft.kathpos.tools;

public class ConstantsConllections implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7508780033837412654L;
	/**
	 * 
	 * 
	 * 
	 */
	
	public static int[] tablaClientesColumnsWidth = { 30, // indice
			150, // Rfc
			100, // tipo de cliente
			100, // cuenta contable
			300, // nombre completo
			100, // nombre corto
			180, // correo electrónico
			100, // estado
			100, // ciudad
			400, // direccion
			100, // codigo postal
			150 // activo o inactivo
	};
	
	public static int[] tablaTipoClienteColumnsWidth = { 40, // id
			150, // nombre de categoria
			400, // descripcion
			150 // estatus de la categoria
	};
	
}
