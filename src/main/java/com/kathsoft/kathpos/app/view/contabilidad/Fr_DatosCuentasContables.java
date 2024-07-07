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
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class Fr_DatosCuentasContables extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorbotones;
	private JLabel lblNewLabel;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JFormattedTextField fmtxfClaveContable;
	private Component horizontalStrut_1;
	private JLabel lblNewLabel_2;
	private JTextField txfNombreCuenta;
	private Component verticalStrut_1;
	private Box verticalBox;
	private JPanel panel;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JTextArea txaDescripcionCuenta;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnGuardar;
	private Box horizontalBox_1;
	private Box verticalBox_1;
	private Component horizontalStrut_3;
	private Box verticalBox_2;
	private JRadioButton rdbtnCtaAgrupadora;
	private Component verticalStrut_2;
	private JRadioButton rdbtnCtaDetalle;
	private ButtonGroup btnRadioGroup;
	private Box horizontalBox_2;

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
		this.setBounds(100, 100, 780, 632);
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
		
		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);
		
		horizontalBox = Box.createHorizontalBox();
		//panelCentralFormulario.add(horizontalBox);
		
		lblNewLabel_1 = new JLabel("Clave de cuenta");
		horizontalBox.add(lblNewLabel_1);
		
		horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);
		
		fmtxfClaveContable = new JFormattedTextField(formatoClave());		
		fmtxfClaveContable.setColumns(25);
		this.fmtxfClaveContable.setMaximumSize(this.fmtxfClaveContable.getPreferredSize());
		horizontalBox.add(fmtxfClaveContable);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);
		
		lblNewLabel_2 = new JLabel("Nombre");
		horizontalBox.add(lblNewLabel_2);
		
		txfNombreCuenta = new JTextField();
		txfNombreCuenta.setColumns(50);
		this.txfNombreCuenta.setMaximumSize(this.txfNombreCuenta.getPreferredSize());
		horizontalBox.add(txfNombreCuenta);
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);
		
		verticalBox = Box.createVerticalBox();
		//panelCentralFormulario.add(verticalBox);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		verticalBox.add(panel);
		
		lblNewLabel_3 = new JLabel("Descripción");		
		panel.add(lblNewLabel_3);
		
		scrollPane = new JScrollPane();
		verticalBox.add(scrollPane);
		
		txaDescripcionCuenta = new JTextArea();
		scrollPane.setViewportView(txaDescripcionCuenta);
		
		this.verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		this.verticalBox_1.add(horizontalBox);
		this.verticalBox_1.add(verticalBox);
		
		this.horizontalBox_1.add(verticalBox_1);
		
		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3);
		
		verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo de cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		horizontalBox_1.add(verticalBox_2);
		
		btnRadioGroup = new ButtonGroup();
		
		rdbtnCtaAgrupadora = new JRadioButton("Cuenta Agrupadora");
		rdbtnCtaAgrupadora.setBackground(new Color(255, 215, 0));
		verticalBox_2.add(rdbtnCtaAgrupadora);
		
		verticalStrut_2 = Box.createVerticalStrut(5);
		verticalBox_2.add(verticalStrut_2);
		
		rdbtnCtaDetalle = new JRadioButton("Cuenta de detalle");
		rdbtnCtaDetalle.setBackground(new Color(255, 215, 0));
		verticalBox_2.add(rdbtnCtaDetalle);
		
		btnRadioGroup.add(this.rdbtnCtaAgrupadora);
		this.btnRadioGroup.add(this.rdbtnCtaDetalle);
		
		horizontalBox_2 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_2);
		
		//this.horizontalBox_1.add(horizontalBox);
		//this.horizontalBox_1.add(verticalBox);
		
		panelInferiorbotones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferiorbotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelInferiorbotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panelInferiorbotones.setBackground(new Color(30,144,255));
		panelPrincipal.add(panelInferiorbotones, BorderLayout.SOUTH);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Fr_DatosCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnCancelar.setBackground(new Color(205,92,92));
		panelInferiorbotones.add(btnCancelar);
		
		horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelInferiorbotones.add(horizontalStrut_2);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(Fr_DatosCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnGuardar.setBackground(new Color(144,238,144));
		
		panelInferiorbotones.add(btnGuardar);
		
		if(opcion == 1) {
			this.lblNewLabel.setText("Registrar cuenta");
		}else {
			this.lblNewLabel.setText("Modificar cuenta");
		}
	}
	
	private MaskFormatter formatoClave() {
		
		var formato = new MaskFormatter();
		
		try {			
			formato.setMask("####-####-####");
			return formato;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
