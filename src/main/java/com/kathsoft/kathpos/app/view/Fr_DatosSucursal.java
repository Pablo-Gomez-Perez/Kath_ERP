package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fr_DatosSucursal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfTelefonoSucursal;
	private JTextField txfEmailSucursal;
	private JTextField txfEstadoSucursal;
	private JTextField txfCiudadSucursal;
	private JTextField txfCodigoPostalSucursal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_DatosSucursal frame = new Fr_DatosSucursal();
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
	public Fr_DatosSucursal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setForeground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Nueva Sucursal");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panelSuperiorEtiqueta.add(lblNewLabel);
		
		JPanel panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);
		
		Box horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre");
		horizontalBox.add(lblNewLabel_1);
		
		Component horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);
		
		JComboBox cmbNombreSucursal = new JComboBox();
		horizontalBox.add(cmbNombreSucursal);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);
		
		Box verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		horizontalBox_1.add(panel);
		
		JLabel lblNewLabel_2 = new JLabel("Descripcion");
		panel.add(lblNewLabel_2);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		JTextArea txaDescripcionSucursal = new JTextArea();
		txaDescripcionSucursal.setLineWrap(true);
		horizontalBox_2.add(txaDescripcionSucursal);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_3);
		
		JLabel lblNewLabel_3 = new JLabel("Telefono");
		horizontalBox_3.add(lblNewLabel_3);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_1);
		
		txfTelefonoSucursal = new JTextField();
		horizontalBox_3.add(txfTelefonoSucursal);
		txfTelefonoSucursal.setColumns(30);
		this.txfTelefonoSucursal.setMaximumSize(this.txfTelefonoSucursal.getPreferredSize());
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_2);
		
		JLabel lblNewLabel_4 = new JLabel("Email");
		horizontalBox_3.add(lblNewLabel_4);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_3);
		
		txfEmailSucursal = new JTextField();
		horizontalBox_3.add(txfEmailSucursal);
		txfEmailSucursal.setColumns(30);
		this.txfEmailSucursal.setMaximumSize(this.txfEmailSucursal.getPreferredSize());
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);
		
		Box horizontalBox_4 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_4);
		
		JLabel lblNewLabel_5 = new JLabel("Estado");
		horizontalBox_4.add(lblNewLabel_5);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_4);
		
		txfEstadoSucursal = new JTextField();
		horizontalBox_4.add(txfEstadoSucursal);
		txfEstadoSucursal.setColumns(20);
		this.txfEstadoSucursal.setMaximumSize(this.txfEstadoSucursal.getPreferredSize());
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_5);
		
		JLabel lblNewLabel_6 = new JLabel("Ciudad");
		horizontalBox_4.add(lblNewLabel_6);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_6);
		
		txfCiudadSucursal = new JTextField();
		horizontalBox_4.add(txfCiudadSucursal);
		txfCiudadSucursal.setColumns(25);
		this.txfCiudadSucursal.setMaximumSize(this.txfCiudadSucursal.getPreferredSize());
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_7);
		
		JLabel lblNewLabel_7 = new JLabel("C.P.");
		horizontalBox_4.add(lblNewLabel_7);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_8);
		
		txfCodigoPostalSucursal = new JTextField();
		horizontalBox_4.add(txfCodigoPostalSucursal);
		txfCodigoPostalSucursal.setColumns(15);
		this.txfCodigoPostalSucursal.setMaximumSize(this.txfCodigoPostalSucursal.getPreferredSize());
		
		Component verticalStrut_4 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_4);
		
		Box verticalBox_1 = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox_1);
		
		Box horizontalBox_5 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_5);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		horizontalBox_5.add(panel_1);
		
		JLabel lblNewLabel_8 = new JLabel("Direccion");
		panel_1.add(lblNewLabel_8);
		
		Box horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_6);
		
		JTextArea txaDireccionSucursal = new JTextArea();
		horizontalBox_6.add(txaDireccionSucursal);
		
		JPanel panelInferiorBotones = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		JButton btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btn_Cancelar.setBackground(new Color(205, 92, 92));
		btn_Cancelar.setIcon(new ImageIcon(Fr_DatosSucursal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		panelInferiorBotones.add(btn_Cancelar);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_9);
		
		JButton btn_Guardar = new JButton("Guardar");
		btn_Guardar.setIcon(new ImageIcon(Fr_DatosSucursal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelInferiorBotones.add(btn_Guardar);
	}

	private void cerrarForm() {
		this.dispose();
	}

}
