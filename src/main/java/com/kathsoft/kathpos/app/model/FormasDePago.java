package com.kathsoft.kathpos.app.model;

public class FormasDePago implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2513778784977038105L;
	/**
	 * 
	 * 
	 * 
	 */
	private int id;
	private String tipoDePago;
	
	public FormasDePago(int id, String tipoDePago) {
		this.id = id;
		this.tipoDePago = tipoDePago;
	}
	
	public FormasDePago() {
		
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the tipoDePago
	 */
	public String getTipoDePago() {
		return tipoDePago;
	}

	/**
	 * @param tipoDePago the tipoDePago to set
	 */
	public void setTipoDePago(String tipoDePago) {
		this.tipoDePago = tipoDePago;
	}

	@Override
	public String toString() {
		return "FormasDePago [id=" + id + ", tipoDePago=" + tipoDePago + "]";
	}
	
}
