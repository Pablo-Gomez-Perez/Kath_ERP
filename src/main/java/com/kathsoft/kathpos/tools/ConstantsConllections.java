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
			150 // activo o inactivo
	};
	
	public static final int[] tablaTipoClienteColumnsWidth = { 40, // id
			150, // nombre de categoria
			400, // descripcion
			150 // estatus de la categoria
	};
	
	// Array que define el ancho de cada columna de la tabla de Articulos
	public static final int[] tablaArticulosColumnsWidth = { 40, // id
			200, // proveedor
			180, // categoria
			150, // codigo articulo
			300, // nombre
			90, // es exento
			120, // costo unitario
			120, // precio
			100, // existencia
			100 // activo
	};
	
	public static final int[] tablaPreciosArticuloColumnsWidth = { 60, // id tipo cliente
			240, // tipo cliente
			120, // precio
			150, // precio especial
			170 // cantidad precio especial
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
	
	/**
	 * define el ancho de columnas de la tabla en el formulario de selección de articulos
	 */
	public static int[] tablaArticulosListadoColumnsWidth = { 40, /* id */
			150, /* codigo */
			200, /* proveedor */
			180, /* categoría */
			100, /* codigo sat */
			300, /* Nombre */
			450, /* descripcion */
			100, /* Existencia */
			100, /* Precio g */
			100 /* Precio m */
	};
	
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
	
	public static final int[] tablaComprasColumnsWidth = { 50, // id compra
			120, // empleado
			120, // proveedor
			140, // folio factura
			120, // fecha factura
			120, // fecha compra
			110, // tipo compra
			120, // subtotal
			120, // iva
			120, // total
			100 // activo o inactivo
	};
	
	public static final int[] tablaArticulosCompraColumnsWidth = { 120, // codigo articulo
			260, // descripcion
			80, // cantidad
			120, // costo unitario
			120 // subtotal
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
			150, // clave			
			200, // Nombre de la cuenta
			200, // Nombre de la supercuenta
			200, // Rubro
			40, // Nivel
			50, // Ultimo nivel ?
			150, //Cargo
			150, // Abono
			150, //Saldo
			100, //Activa
	};
	
	public static final int[] tablaCuentasContablesDialogColumnsWidth = {
			40, //Id
			150, // clave			
			200 // Nombre de la cuenta
	};
}
