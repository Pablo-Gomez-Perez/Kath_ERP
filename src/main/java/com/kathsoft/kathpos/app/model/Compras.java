package com.kathsoft.kathpos.app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Compras implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8924351278749112566L;
	/**
	 * 
	 * 
	 */
	private int idCompra;
	private String folioFactura;
	private Date fechaCompra;
	private boolean tipoCompra; //indica si la compra fué a credito o a contado
	private int idEmpleado;
	private int idProveedor;
	private double subtotal;
	private double iva;
	
	
	
	
}
