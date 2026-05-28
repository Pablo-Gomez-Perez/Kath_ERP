package com.kathsoft.kathpos.app.model.viewmodel;



public record JComboboxDataViewModel(int id, String nombre) {	
	
	public int id() {
		return id;
	}

	public String nombre() {
		return nombre;
	}

	@Override
	public final String toString() {		
		return nombre;
	}
}
