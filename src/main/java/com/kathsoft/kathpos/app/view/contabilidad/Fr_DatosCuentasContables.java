package com.kathsoft.kathpos.app.view.contabilidad;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;

public class Fr_DatosCuentasContables extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorbotones;
	private JLabel lblNewLabel;
	private Component verticalStrut;
	private Box horizontalBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_DatosCuentasContables frame = new Fr_DatosCuentasContables(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fr_DatosCuentasContables(int opcion) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 499);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 215, 0));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(25,25,112));
		panelPrincipal.add(panelSuperiorEtiqueta, BorderLayout.NORTH);	
		
		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSuperiorEtiqueta.add(lblNewLabel);
		
		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		panelCentralFormulario.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelPrincipal.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));
		
		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);
		
		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);
		
		panelInferiorbotones = new JPanel();
		panelInferiorbotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panelInferiorbotones.setBackground(new Color(30,144,255));
		panelPrincipal.add(panelInferiorbotones, BorderLayout.SOUTH);
		
		if(opcion == 1) {
			this.lblNewLabel.setText("Registrar cuenta");
		}else {
			this.lblNewLabel.setText("Modificar cuenta");
		}
	}
}
