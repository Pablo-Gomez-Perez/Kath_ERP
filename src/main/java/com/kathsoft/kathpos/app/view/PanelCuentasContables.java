package com.kathsoft.kathpos.app.view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;

public class PanelCuentasContables extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiqueta;

	/**
	 * Create the panel.
	 */
	public PanelCuentasContables() {
		setBackground(new Color(255, 215, 0));
		setLayout(new BorderLayout(0, 0));
		
		panelEtiqueta = new JPanel();
		panelEtiqueta.setBackground(new Color(25, 25, 112));
		add(panelEtiqueta, BorderLayout.NORTH);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(255, 215, 0));
		add(panelCentral, BorderLayout.CENTER);

	}

}
