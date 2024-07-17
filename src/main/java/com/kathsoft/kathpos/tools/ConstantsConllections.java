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
	
	// Array que define el ancho de cada columna de la tabla de Proveedores
	public static final int[] tablaProveedoresColumnsWidth = { 30, // Indice
			150, // Rfc
			150, // Clave contable
			180, // Nombre
			400, // Descripcion
			200, // Correo
			100, // Estado
			100, // Ciudad
			300, // Direccion
			90, // Codigo postal
			150 // activo o inactivo
	};
	
	// Array que define el ancho de cada columna de la tabla de categoría
	public static final int[] tablaCategoriaColumnsWidth = { 40, 180, 400 };
	
	public static final int[] tablaVentasColumnsWidth = { 50, // i venta
			120, // fecha venta
			100, // tipo venta
			210, // empleado
			210, // Cliente
			120, // sub total
			120, // Iva
			120, // Total
			90, // Accion
	};
	
	public static final int[] tablaSucursalesColumnWidth = { 40, // indice
			150, // nombre
			300, // descripcion
			200, // telefono
			200, // email
			150, // estado
			200, // ciudad
			300, // direccion
			120, // codigo postal
			150 // activo o inactivo

	};
	
	public static final int[] tablaCuentasContablesColumnsWidth = {
			40, //id
			200, // nombre cuenta superior
			250, //Rubro de la cuenta
			200, //Naturaleza
			150, // clave
			200, // Nombre de la cuenta
			270, //Descripcion
			40, //nivel
			100, // Es de nivel de detalle
			150, // Cargos
			150, //Abonos
			150, // Saldo de la cuenta
			150, //Estatus
			200, //Ultima Modificacion
	};
}
