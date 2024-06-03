package com.kathsoft.kathpos.tools;

import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.ClientesController;

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
	
}
