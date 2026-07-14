package com.kathsoft.kathpos.tools;

import javax.swing.JComboBox;

import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;

public class UiTools {
	
	/**
	 * Busca un elemento dentro de un JComboBox mediante su id y lo selecciona
	 * @param jcmb
	 * @param index
	 */
	public static void jComboboxSetSelectedIndex(JComboBox<JComboboxDataViewModel> jcmb, int index) {
		
		for (int i = 0; i < jcmb.getItemCount(); i++) {
			JComboboxDataViewModel item = jcmb.getItemAt(i);
			if (item != null && item.id() == index) {
				jcmb.setSelectedIndex(i);
				break;
			}
		}
		
	}
	
}
