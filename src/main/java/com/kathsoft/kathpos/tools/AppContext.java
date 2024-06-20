package com.kathsoft.kathpos.tools;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.controller.CuentaContableController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.controller.TipoClienteController;
import com.kathsoft.kathpos.app.controller.VentasController;

public class AppContext implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7624242325677554996L;
	/**
	 * 
	 * 
	 * 
	 */
	
	public static ClientesController clientesController = new ClientesController();
	public static CategoriaController categoriaController = new CategoriaController();
	public static EmpleadoController empleadoController = new EmpleadoController();
	public static ProveedorController proveedorController = new ProveedorController();
	public static ArticuloController articuloController = new ArticuloController();
	public static VentasController ventasController = new VentasController();
	public static SucursalController sucursalController = new SucursalController();
	public static FormasDePagoController formasDePagoController = new FormasDePagoController();
	public static TipoClienteController tipoClienteController = new TipoClienteController();
	public static CuentaContableController cuentaContableController = new CuentaContableController();
}
