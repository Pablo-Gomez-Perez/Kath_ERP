package com.kathsoft.kathpos.app.view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class PanelCuentasContables extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiqueta;
	private JTable tableCuentasContables;

	/**
	 * Create the panel.
	 */
	public PanelCuentasContables() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setBackground(new Color(255, 215, 0));
		setLayout(new BorderLayout(0, 0));
		
		panelEtiqueta = new JPanel();
		panelEtiqueta.setBorder(new EmptyBorder(0, 0, 5, 0));
		panelEtiqueta.setBackground(new Color(25, 25, 112));
		add(panelEtiqueta, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Contabilidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panelEtiqueta.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBackground(new Color(255, 215, 0));
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTablaConta = new JScrollPane();
		panelCentral.add(scrollPaneTablaConta, BorderLayout.CENTER);
		
		tableCuentasContables = new JTable();
		scrollPaneTablaConta.setViewportView(tableCuentasContables);

	}

}
