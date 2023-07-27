package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;

public class Fr_DatosArticulo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1528483064591725560L;
	/**
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	
	
	
	/**
	 * Create the frame.
	 */
	public Fr_DatosArticulo(int tipoOperacion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		panelCentralFormulario = new JPanel();
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		
		panelInferiorBotones = new JPanel();
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
	}

}
