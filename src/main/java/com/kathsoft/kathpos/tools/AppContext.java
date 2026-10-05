package com.kathsoft.kathpos.tools;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.CategoriaDeGastoController;
import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.controller.CompraController;
import com.kathsoft.kathpos.app.controller.CuentaContableController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.controller.GastoController;
import com.kathsoft.kathpos.app.controller.InicializacionSistemaController;
import com.kathsoft.kathpos.app.controller.LoginController;
import com.kathsoft.kathpos.app.controller.PagoProveedorController;
import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.controller.ReporteController;
import com.kathsoft.kathpos.app.controller.RetiroDeEfectivoController;
import com.kathsoft.kathpos.app.controller.RubroCuentaContableController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.controller.TelefonoClienteController;
import com.kathsoft.kathpos.app.controller.TelefonoEmpleadoController;
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
	public static CategoriaDeGastoController categoriaDeGastoController = new CategoriaDeGastoController();
	public static GastoController gastoController = new GastoController();
	public static RetiroDeEfectivoController retiroDeEfectivoController = new RetiroDeEfectivoController();
	public static EmpleadoController empleadoController = new EmpleadoController();
	public static LoginController loginController = new LoginController();
	public static InicializacionSistemaController inicializacionSistemaController = new InicializacionSistemaController();
	public static ProveedorController proveedorController = new ProveedorController();
	public static ArticuloController articuloController = new ArticuloController();
	public static CompraController compraController = new CompraController();
	public static PagoProveedorController pagoProveedorController = new PagoProveedorController();
	public static VentasController ventasController = new VentasController();
	public static ReporteController reporteController = new ReporteController();
	public static SucursalController sucursalController = new SucursalController();
	public static FormasDePagoController formasDePagoController = new FormasDePagoController();
	public static TipoClienteController tipoClienteController = new TipoClienteController();
	public static CuentaContableController cuentaContableController = new CuentaContableController();
	public static RubroCuentaContableController rubroCuentaContableController = new RubroCuentaContableController();
	public static TelefonoEmpleadoController telefonoEmpleadoController = new TelefonoEmpleadoController();
	public static TelefonoClienteController telefonoClienteController = new TelefonoClienteController();
}
