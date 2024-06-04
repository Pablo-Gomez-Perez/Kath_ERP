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
	
	public static final int[] tablaClientesColumnsWidth = { 30, // indice
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
	
	public static final int[] tablaTipoClienteColumnsWidth = { 40, // id
			150, // nombre de categoria
			400, // descripcion
			150 // estatus de la categoria
	};
	
	// Array que define el ancho de cada columna de la tabla de Articulos
	public static final int[] tablaArticulosColumnsWidth = { 40, /* id */
			150, /* codigo */
			200, /* proveedor */
			180, /* categoría */
			100, /* codigo sat */
			300, /* Nombre */
			450, /* descripcion */
			100, /* Existencia */
			100, /* Precio G */
			100, /* Precio E */
			100, /* Despues de */
			100 /* activo o inactivo */
	};
	
	// Array que define el ancho de cada columna de la tabla de empleados
	public static final int[] tableEmpleadosColumnsWidth = { 40, // id
			150, // sucursal
			180, // RFC
			180, // Curp
			180, // Nombre completo
			100, // nombre corto
			200, // email
			150 // activo o inactivo
	};
}
