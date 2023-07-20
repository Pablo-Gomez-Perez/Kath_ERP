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
	/**
	 * Create the frame.
	 */
	public Fr_DatosProveedor(int tipoOperacion) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 587);
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
		
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}
