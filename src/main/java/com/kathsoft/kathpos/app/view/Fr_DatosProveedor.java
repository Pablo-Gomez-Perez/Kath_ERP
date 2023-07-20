package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.ProveedorController;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Fr_DatosProveedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374525545404673239L;
	/**
	 * 
	 * 
	 */
	
	private JPanel contentPane;
	private ProveedorController proveedorController = new ProveedorController();
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JComboBox<String> cmbRFCProveedor;
	private Component horizontalStrut_1;
	private JLabel lblNewLabel_2;
	private JTextField txfCtaContableProveedor;
	private Component horizontalStrut_2;
	private Component verticalStrut_1;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut_3;
	private JTextField txfNombreProveedor;
	private Component verticalStrut_2;
	private Box verticalBox;
	private Box horizontalBox_2;
	private JPanel panel;
	private JLabel lblNewLabel_4;
	private Box horizontalBox_3;
	private JTextArea txaDescripcionProveedor;
	private Component verticalStrut_3;
	private Box horizontalBox_4;
	private JLabel lblNewLabel_5;
	private Component horizontalStrut_4;
	private JTextField txfEmailProveedor;
	private Component verticalStrut_4;
	private Box horizontalBox_5;
	private JLabel lblNewLabel_6;
	private Component horizontalStrut_5;
	private JTextField txfEstadoProveedor;
	private Component horizontalStrut_6;
	private JLabel lblNewLabel_7;
	private Component horizontalStrut_7;
	private JTextField txfCiudadProveedor;
	private Component horizontalStrut_8;
	private JLabel lblNewLabel_8;
	private Component horizontalStrut_9;
	private JTextField txfCodigoPostalProveedor;
	private Component verticalStrut_5;
	private Box verticalBox_1;
	private JPanel panel_1;
	private JLabel lblNewLabel_9;
	private Box horizontalBox_6;
	private JTextArea txaDireccionProveedor;
	private JPanel panelInferiorBotones;
	private JButton btn_Cancelar;
	private Component horizontalStrut_10;
	private JButton btn_Guardar;
	/**
	 * Create the frame.
	 */
	public Fr_DatosProveedor(int tipoOperacion) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25,25,112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel();		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panelSuperiorEtiqueta.add(lblNewLabel);
		
		if(tipoOperacion == 0) {
			lblNewLabel.setText("Agregar nuevo Proveedor");
		}else if(tipoOperacion == 1) {
			lblNewLabel.setText("Editar Proveedor");
		}
		
		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));
		
		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);
		
		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);
		
		lblNewLabel_1 = new JLabel("RFC");
		horizontalBox.add(lblNewLabel_1);
		
		horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);
		
		cmbRFCProveedor = new JComboBox<String>();
		horizontalBox.add(cmbRFCProveedor);
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);
		
		lblNewLabel_2 = new JLabel("Cta Contable");
		horizontalBox.add(lblNewLabel_2);
		
		horizontalStrut_2 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_2);
		
		txfCtaContableProveedor = new JTextField();
		horizontalBox.add(txfCtaContableProveedor);
		txfCtaContableProveedor.setColumns(20);
		this.txfCtaContableProveedor.setMaximumSize(this.txfCtaContableProveedor.getPreferredSize());
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);
		
		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);
		
		lblNewLabel_3 = new JLabel("Nombre");
		horizontalBox_1.add(lblNewLabel_3);
		
		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_3);
		
		txfNombreProveedor = new JTextField();
		horizontalBox_1.add(txfNombreProveedor);
		txfNombreProveedor.setColumns(80);
		this.txfNombreProveedor.setMaximumSize(this.txfNombreProveedor.getPreferredSize());
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);
		
		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);
		
		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(255, 215, 0));
		horizontalBox_2.add(panel);
		
		lblNewLabel_4 = new JLabel("Descripcion");
		panel.add(lblNewLabel_4);
		
		horizontalBox_3 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_3);
		
		txaDescripcionProveedor = new JTextArea();
		horizontalBox_3.add(txaDescripcionProveedor);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);
		
		horizontalBox_4 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_4);
		
		lblNewLabel_5 = new JLabel("Email");
		horizontalBox_4.add(lblNewLabel_5);
		
		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_4);
		
		txfEmailProveedor = new JTextField();
		horizontalBox_4.add(txfEmailProveedor);
		txfEmailProveedor.setColumns(80);
		this.txfEmailProveedor.setMaximumSize(this.txfEmailProveedor.getPreferredSize());
		
		verticalStrut_4 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_4);
		
		horizontalBox_5 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_5);
		
		lblNewLabel_6 = new JLabel("Estado");
		horizontalBox_5.add(lblNewLabel_6);
		
		horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_5);
		
		txfEstadoProveedor = new JTextField();
		horizontalBox_5.add(txfEstadoProveedor);
		txfEstadoProveedor.setColumns(20);
		this.txfEstadoProveedor.setMaximumSize(this.txfEstadoProveedor.getPreferredSize());
		
		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_6);
		
		lblNewLabel_7 = new JLabel("Ciudad");
		horizontalBox_5.add(lblNewLabel_7);
		
		horizontalStrut_7 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_7);
		
		txfCiudadProveedor = new JTextField();
		horizontalBox_5.add(txfCiudadProveedor);
		txfCiudadProveedor.setColumns(20);
		this.txfCiudadProveedor.setMaximumSize(this.txfCiudadProveedor.getPreferredSize());
		
		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_8);
		
		lblNewLabel_8 = new JLabel("C.P.");
		horizontalBox_5.add(lblNewLabel_8);
		
		horizontalStrut_9 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_9);
		
		txfCodigoPostalProveedor = new JTextField();
		horizontalBox_5.add(txfCodigoPostalProveedor);
		txfCodigoPostalProveedor.setColumns(15);
		this.txfCodigoPostalProveedor.setMaximumSize(this.txfCodigoPostalProveedor.getPreferredSize());
		
		verticalStrut_5 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_5);
		
		verticalBox_1 = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox_1);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		verticalBox_1.add(panel_1);
		
		lblNewLabel_9 = new JLabel("Direccion");
		panel_1.add(lblNewLabel_9);
		
		horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_6);
		
		txaDireccionProveedor = new JTextArea();
		horizontalBox_6.add(txaDireccionProveedor);
		
		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.setIcon(new ImageIcon(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/resources/CancelarIcon.png")));
		btn_Cancelar.setBackground(new Color(205,92,92));
		panelInferiorBotones.add(btn_Cancelar);
		
		horizontalStrut_10 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_10);
		
		btn_Guardar = new JButton("Guardar");
		btn_Guardar.setBackground(new Color(144, 238, 144));
		btn_Guardar.setIcon(new ImageIcon(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelInferiorBotones.add(btn_Guardar);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
