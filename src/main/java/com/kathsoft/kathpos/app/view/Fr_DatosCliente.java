package com.kathsoft.kathpos.app.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.model.Clientes;

import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fr_DatosCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4955801027954256510L;
	/**
	 * 
	 * 
	 * 
	 */
	private ClientesController clientesController = new ClientesController();
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private Component horizontalStrut;
	private JButton btnGuardar;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut_1;
	private JTextField txfRfcCliente;
	//private JComboBox<String> cmbRfcCliente;
	private Component horizontalStrut_2;
	private JLabel lblNewLabel_2;
	private Component horizontalStrut_3;
	private JTextField txfCtaContableCliente;
	private Component verticalStrut_1;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut_4;
	private JTextField txfNombreCompleto;
	private Component horizontalStrut_5;
	private JLabel lblNewLabel_4;
	private Component horizontalStrut_6;
	private JTextField txfNombreCorto;
	private Component verticalStrut_2;
	private Box verticalBox;
	private JPanel panel;
	private JLabel lblNewLabel_5;
	private Box horizontalBox_2;
	private JTextArea txaDescripcion;
	private Component verticalStrut_3;
	private Box horizontalBox_3;
	private JLabel lblNewLabel_6;
	private Component horizontalStrut_7;
	private JTextField txfDiaNac;
	private Component horizontalStrut_8;
	private JTextField txfMesNac;
	private Component horizontalStrut_9;
	private JTextField txfAnioNac;
	private Component horizontalStrut_10;
	private JLabel lblNewLabel_7;
	private Component horizontalStrut_11;
	private JTextField txfEmail;
	private Component verticalStrut_4;
	private Box horizontalBox_4;
	private JLabel lblEstado;
	private Component horizontalStrut_12;
	private JTextField txfEstado;
	private Component horizontalStrut_13;
	private JLabel lblNewLabel_8;
	private JTextField txfCiudad;
	private Component horizontalStrut_14;
	private JLabel lblNewLabel_9;
	private JTextField txfCodigoPostal;
	private Component verticalStrut_5;
	private Box verticalBox_1;
	private JPanel panel_1;
	private JLabel lblNewLabel_10;
	private JTextArea txaDireccion;
	private Component verticalStrut_6;
	private int indiceCliente;
	private Component horizontalStrut_15;
	private JButton btnHistorialCred;

	/**
	 * Create the frame.
	 */
	public Fr_DatosCliente(int tipoOperacion, int indiceCliente) {
		
		this.indiceCliente = indiceCliente;
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fr_DatosCliente.class.getResource(
				"/com/kathsoft/kathpos/app/resources/pngtree-call-center-customer-icon-png-image_4746069.jpg")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel("");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		if (tipoOperacion == 0) {
			this.setTitle("Agregar Cliente");
			this.lblNewLabel.setText("Agregar Nuevo Cliente");
		} else if (tipoOperacion == 1) {
			this.setTitle("Actualizar Cliente");
			this.lblNewLabel.setText("Actualizar Cliente");
		}

		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));

		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);

		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("RFC");
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_1);
		
		txfRfcCliente = new JTextField();
		horizontalBox.add(this.txfRfcCliente);
		txfRfcCliente.setColumns(30);
		txfRfcCliente.setMaximumSize(this.txfRfcCliente.getPreferredSize());

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		lblNewLabel_2 = new JLabel("Cta Contable");
		horizontalBox.add(lblNewLabel_2);

		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_3);

		txfCtaContableCliente = new JTextField();
		txfCtaContableCliente.setEditable(false);
		horizontalBox.add(txfCtaContableCliente);
		txfCtaContableCliente.setColumns(20);
		this.txfCtaContableCliente.setMaximumSize(this.txfCtaContableCliente.getPreferredSize());

		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);

		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);

		lblNewLabel_3 = new JLabel("Nombre");
		horizontalBox_1.add(lblNewLabel_3);

		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_4);

		txfNombreCompleto = new JTextField();
		horizontalBox_1.add(txfNombreCompleto);
		txfNombreCompleto.setColumns(50);
		this.txfNombreCompleto.setMaximumSize(this.txfNombreCompleto.getPreferredSize());

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_5);

		lblNewLabel_4 = new JLabel("Nombre corto");
		horizontalBox_1.add(lblNewLabel_4);

		horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_6);

		txfNombreCorto = new JTextField();
		horizontalBox_1.add(txfNombreCorto);
		txfNombreCorto.setColumns(20);
		this.txfNombreCorto.setMaximumSize(this.txfNombreCorto.getPreferredSize());

		verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);

		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);

		panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		verticalBox.add(panel);

		lblNewLabel_5 = new JLabel("Descripcion");
		panel.add(lblNewLabel_5);

		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		txaDescripcion = new JTextArea();
		txaDescripcion.setLineWrap(true);
		horizontalBox_2.add(txaDescripcion);

		verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);

		horizontalBox_3 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_3);

		lblNewLabel_6 = new JLabel("Fecha de n.");
		horizontalBox_3.add(lblNewLabel_6);

		horizontalStrut_7 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_7);

		txfDiaNac = new JTextField();
		horizontalBox_3.add(txfDiaNac);
		txfDiaNac.setColumns(2);
		this.txfDiaNac.setMaximumSize(this.txfDiaNac.getPreferredSize());

		horizontalStrut_8 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_8);

		txfMesNac = new JTextField();
		horizontalBox_3.add(txfMesNac);
		txfMesNac.setColumns(2);
		this.txfMesNac.setMaximumSize(this.txfMesNac.getPreferredSize());

		horizontalStrut_9 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_9);

		txfAnioNac = new JTextField();
		horizontalBox_3.add(txfAnioNac);
		txfAnioNac.setColumns(4);
		this.txfAnioNac.setMaximumSize(this.txfAnioNac.getPreferredSize());

		horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_10);

		lblNewLabel_7 = new JLabel("Correo Electrónico");
		horizontalBox_3.add(lblNewLabel_7);

		horizontalStrut_11 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_11);

		txfEmail = new JTextField();
		horizontalBox_3.add(txfEmail);
		txfEmail.setColumns(35);
		this.txfEmail.setMaximumSize(this.txfEmail.getPreferredSize());

		verticalStrut_4 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_4);

		horizontalBox_4 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_4);

		lblEstado = new JLabel("Estado");
		horizontalBox_4.add(lblEstado);

		horizontalStrut_12 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_12);

		txfEstado = new JTextField();
		horizontalBox_4.add(txfEstado);
		txfEstado.setColumns(20);
		this.txfEstado.setMaximumSize(this.txfEstado.getPreferredSize());

		horizontalStrut_13 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_13);

		lblNewLabel_8 = new JLabel("Ciudad");
		horizontalBox_4.add(lblNewLabel_8);

		txfCiudad = new JTextField();
		horizontalBox_4.add(txfCiudad);
		txfCiudad.setColumns(20);
		this.txfCiudad.setMaximumSize(this.txfCiudad.getPreferredSize());

		horizontalStrut_14 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_14);

		lblNewLabel_9 = new JLabel("Codigo P.");
		horizontalBox_4.add(lblNewLabel_9);

		txfCodigoPostal = new JTextField();
		horizontalBox_4.add(txfCodigoPostal);
		txfCodigoPostal.setColumns(10);
		this.txfCodigoPostal.setMaximumSize(this.txfCodigoPostal.getPreferredSize());

		verticalStrut_5 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_5);

		verticalBox_1 = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox_1);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panel_1.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		verticalBox_1.add(panel_1);

		lblNewLabel_10 = new JLabel("Direccion");
		panel_1.add(lblNewLabel_10);

		txaDireccion = new JTextArea();
		verticalBox_1.add(txaDireccion);

		verticalStrut_6 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_6);

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		btnHistorialCred = new JButton("Historial");
		btnHistorialCred.setBackground(new Color(0, 255, 255));
		btnHistorialCred.setIcon(new ImageIcon(Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/resources/creditoLogo2.png")));
		panelInferiorBotones.add(btnHistorialCred);
		
		horizontalStrut_15 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_15);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btnCancelar.setBackground(new Color(205, 92, 92));
		btnCancelar.setIcon(new ImageIcon(Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		panelInferiorBotones.add(btnCancelar);

		horizontalStrut = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tipoOperacion == 0) {
					insertarNuevoCliente();
				}else if(tipoOperacion == 1) {
					actualizarCliente();
				}
			}
		});
		btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.setIcon(new ImageIcon(
				Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelInferiorBotones.add(btnGuardar);
		
		if(tipoOperacion == 1) {
			this.consultarClientePorId();
		}
	}
	
	/*
	private void llenarCmbRfcClientes() {

		this.limpiarCmbClientes();

		try {
			clientesController.consultarRFCClientes(cmbRfcCliente);
		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void limpiarCmbClientes() {
		this.cmbRfcCliente.removeAllItems();
		this.cmbRfcCliente.updateUI();
	}*/
	
	private void consultarClientePorId() {

		String dia = "";
		String mes = "";
		String anio = "";

		try {

			Clientes cl = clientesController.buscarClientePorId(this.indiceCliente);

			if (cl.getFechaNacimiento() != null) {
				String[] fecha = cl.getFechaNacimiento().toString().split("-");
				dia = fecha[2];
				mes = fecha[1];
				anio = fecha[0];
			}

			this.indiceCliente = cl.getId();
			this.txfRfcCliente.setText(cl.getRfc());
			this.txfCtaContableCliente.setText(cl.getClaveCuentaContable());
			this.txfNombreCompleto.setText(cl.getNombre());
			this.txfNombreCorto.setText(cl.getNombreCorto());
			this.txaDescripcion.setText(cl.getDescripcion());
			this.txfDiaNac.setText(dia);
			this.txfMesNac.setText(mes);
			this.txfAnioNac.setText(anio);
			this.txfEmail.setText(cl.getEmail());
			this.txfEstado.setText(cl.getEstado());
			this.txfCiudad.setText(cl.getCiudad());
			this.txfCodigoPostal.setText(cl.getCodigoPostal());
			this.txaDireccion.setText(cl.getDireccion());
			
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [Generic] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * inserta un nuevo registro en la bd
	 */
	private void insertarNuevoCliente() {

		Clientes cl = new Clientes();
		String fecha = null;

		if (this.txfRfcCliente.getText().length() < 10 || this.txfRfcCliente.getText().isEmpty()
				|| this.txfRfcCliente.getText().equals(null) || this.txfRfcCliente.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar el RFC del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfNombreCompleto.getText().length() < 1 || this.txfNombreCompleto.getText().isEmpty()
				|| this.txfNombreCompleto.getText().equals("") || this.txfNombreCompleto.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Debe indicar el nombre del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfNombreCorto.getText().length() < 1 || this.txfNombreCorto.getText().isEmpty()
				|| this.txfNombreCorto.getText().equals(null) || this.txfNombreCorto.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un Alias para el cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfDiaNac.getText().length() < 1 || this.txfMesNac.getText().length() < 1
				|| this.txfAnioNac.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "Error al indicar fecha", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfEmail.getText().length() < 1 || this.txfEmail.getText().isEmpty()
				|| this.txfEmail.getText().equals(null) || this.txfEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Indique un correo electrónico", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		fecha = this.txfAnioNac.getText() + "-" + this.txfMesNac.getText() + "-" + this.txfDiaNac.getText();

		if (fecha.isBlank() || fecha.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Indique un correo electrónico", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfEstado.getText().isEmpty() || this.txfEstado.getText().isBlank()
				|| this.txfEstado.getText().length() < 1 || this.txfEstado.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique el estado del cliente", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfCiudad.getText().isEmpty() || this.txfCiudad.getText().isBlank()
				|| this.txfCiudad.getText().length() < 1 || this.txfCiudad.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique la ciudad del cliente", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txaDireccion.getText().isEmpty() || this.txaDireccion.getText().isBlank()
				|| this.txaDireccion.getText().length() < 1 || this.txaDireccion.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique la direccion del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfCodigoPostal.getText().isEmpty() || this.txfCodigoPostal.getText().isBlank()
				|| this.txfCodigoPostal.getText().length() < 1 || this.txfCodigoPostal.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique el codigo postal del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {

			cl.setRfc(this.txfRfcCliente.getText());
			cl.setNombre(this.txfNombreCompleto.getText());
			cl.setNombreCorto(this.txfNombreCorto.getText());
			cl.setDescripcion(this.txaDescripcion.getText());
			cl.setFechaNacimiento(Date.valueOf(fecha));
			cl.setEmail(this.txfEmail.getText());
			cl.setEstado(this.txfEstado.getText());
			cl.setCiudad(this.txfCiudad.getText());
			cl.setDireccion(this.txaDireccion.getText());
			cl.setCodigoPostal(this.txfCodigoPostal.getText());

			clientesController.insertarNuevoCliente(cl);
			
			JOptionPane.showMessageDialog(this, "Cliente registrado", "Exito", JOptionPane.INFORMATION_MESSAGE);
			
			this.limpiarCamposFormulario();

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [Generic] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void actualizarCliente() {
		
		Clientes cl = new Clientes();
		String fecha = null;

		if (this.txfNombreCompleto.getText().length() < 1 || this.txfNombreCompleto.getText().isEmpty()
				|| this.txfNombreCompleto.getText().equals("") || this.txfNombreCompleto.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Debe indicar el nombre del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfNombreCorto.getText().length() < 1 || this.txfNombreCorto.getText().isEmpty()
				|| this.txfNombreCorto.getText().equals(null) || this.txfNombreCorto.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Debe indicar un Alias para el cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfDiaNac.getText().length() < 1 || this.txfMesNac.getText().length() < 1
				|| this.txfAnioNac.getText().length() < 1) {
			JOptionPane.showMessageDialog(this, "Error al indicar fecha", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfEmail.getText().length() < 1 || this.txfEmail.getText().isEmpty()
				|| this.txfEmail.getText().equals(null) || this.txfEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Indique un correo electrónico", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		fecha = this.txfAnioNac.getText() + "-" + this.txfMesNac.getText() + "-" + this.txfDiaNac.getText();

		if (fecha.isBlank() || fecha.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Indique un correo electrónico", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfEstado.getText().isEmpty() || this.txfEstado.getText().isBlank()
				|| this.txfEstado.getText().length() < 1 || this.txfEstado.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique el estado del cliente", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfCiudad.getText().isEmpty() || this.txfCiudad.getText().isBlank()
				|| this.txfCiudad.getText().length() < 1 || this.txfCiudad.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique la ciudad del cliente", "Error", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txaDireccion.getText().isEmpty() || this.txaDireccion.getText().isBlank()
				|| this.txaDireccion.getText().length() < 1 || this.txaDireccion.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique la direccion del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfCodigoPostal.getText().isEmpty() || this.txfCodigoPostal.getText().isBlank()
				|| this.txfCodigoPostal.getText().length() < 1 || this.txfCodigoPostal.getText().equals(null)) {
			JOptionPane.showMessageDialog(this, "Indique el codigo postal del cliente", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		try {
			
			cl.setId(this.indiceCliente);
			cl.setClaveCuentaContable(this.txfCtaContableCliente.getText());
			cl.setNombre(this.txfNombreCompleto.getText());
			cl.setNombreCorto(this.txfNombreCorto.getText());
			cl.setDescripcion(this.txaDescripcion.getText());
			cl.setFechaNacimiento(Date.valueOf(fecha));
			cl.setEmail(this.txfEmail.getText());
			cl.setEstado(this.txfEstado.getText());
			cl.setCiudad(this.txfCiudad.getText());
			cl.setDireccion(this.txaDireccion.getText());
			cl.setCodigoPostal(this.txfCodigoPostal.getText());
			
			clientesController.actualizarCliente(cl);
			
			JOptionPane.showMessageDialog(this, "Cliente actualizado","Exito" , JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}catch(Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [Generic] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * borra el contenido de todos los campos del formulario
	 */
	private void limpiarCamposFormulario() {
		
		this.txfRfcCliente.setText("");
		this.txfNombreCompleto.setText("");
		this.txfNombreCorto.setText("");
		this.txaDescripcion.setText("");
		this.txfDiaNac.setText("");
		this.txfMesNac.setText("");
		this.txfAnioNac.setText("");
		this.txfEmail.setText("");
		this.txfEstado.setText("");
		this.txfCiudad.setText("");
		this.txaDireccion.setText("");
		this.txfCodigoPostal.setText("");
		
	}
	
	private void cerrarForm() {
		this.dispose();
	}

}
