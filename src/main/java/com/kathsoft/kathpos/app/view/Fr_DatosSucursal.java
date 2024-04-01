package com.kathsoft.kathpos.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.model.Sucursal;

public class Fr_DatosSucursal extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 
	 * 
	 * 
	 */
	private SucursalController sucursalController = new SucursalController();
	private JPanel contentPane;
	private JTextField txfNombreSucursal;
	private JTextField txfTelefonoSucursal;
	private JTextField txfEmailSucursal;
	private JTextField txfEstadoSucursal;
	private JTextField txfCiudadSucursal;
	private JTextField txfCodigoPostalSucursal;
	private JComboBox<String> cmbNombreSucursal;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Box verticalBox;
	private Box horizontalBox_1;
	private JPanel panel;
	private Box verticalBox_1;
	private Box horizontalBox_5;
	private JPanel panel_1;
	private JLabel lblNewLabel_8;
	private JTextArea txaDireccionSucursal;
	private JPanel panelInferiorBotones;
	private JButton btn_Cancelar;
	private JButton btn_Guardar;
	private JTextArea txaDescripcionSucursal;
	private int idSucursal;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_DatosSucursal frame = new
	 * Fr_DatosSucursal(0); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_DatosSucursal(int opcion, int idSucursal) {

		this.idSucursal = idSucursal;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setForeground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		if (opcion == 0) {
			lblNewLabel.setText("Agregar Nueva Sucursal");
		} else if (opcion == 1) {
			lblNewLabel.setText("Editar Sucursal");
		}

		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));

		Component verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);

		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("Nombre");
		horizontalBox.add(lblNewLabel_1);

		Component horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);

		this.txfNombreSucursal = new JTextField();
		this.txfNombreSucursal.setColumns(90);
		this.txfNombreSucursal.setMaximumSize(this.txfNombreSucursal.getPreferredSize());
		horizontalBox.add(this.txfNombreSucursal);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);

		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		horizontalBox_1.add(panel);

		JLabel lblNewLabel_2 = new JLabel("Descripcion");
		panel.add(lblNewLabel_2);

		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		txaDescripcionSucursal = new JTextArea();
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

		verticalBox_1 = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox_1);

		horizontalBox_5 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_5);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		horizontalBox_5.add(panel_1);

		lblNewLabel_8 = new JLabel("Direccion");
		panel_1.add(lblNewLabel_8);

		Box horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_6);

		txaDireccionSucursal = new JTextArea();
		horizontalBox_6.add(txaDireccionSucursal);

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btn_Cancelar.setBackground(new Color(205, 92, 92));
		btn_Cancelar.setIcon(
				new ImageIcon(Fr_DatosSucursal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		panelInferiorBotones.add(btn_Cancelar);

		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_9);

		btn_Guardar = new JButton("Guardar");
		btn_Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opcion == 0) {
					insertarSucursal();
				} else if (opcion == 1) {
					actualizarSucursal();
				}
			}
		});
		btn_Guardar.setBackground(new Color(144, 238, 144));
		btn_Guardar.setIcon(new ImageIcon(
				Fr_DatosSucursal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelInferiorBotones.add(btn_Guardar);
		
		if(opcion == 1) {
			this.consultarSucursalPorId();
		}

		if (opcion == 1) {
			this.consultarSucursalPorId();
		}

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * cierra el form sin cerrar el sistema
	 */
	private void cerrarForm() {
		this.dispose();
	}

	/**
	 * inserta un registro de una nueva sucursal a la base de datos
	 */
	private void insertarSucursal() {

		Sucursal sucursal = new Sucursal();

		if (this.validarCamposVacios() == true) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {

			sucursal.setNombre(this.txfNombreSucursal.getText());
			sucursal.setDescripcion(this.txaDescripcionSucursal.getText());
			sucursal.setTelefono(this.txfTelefonoSucursal.getText());
			sucursal.setEmail(this.txfEmailSucursal.getText());
			sucursal.setEstado(this.txfEstadoSucursal.getText());
			sucursal.setCiudad(this.txfCiudadSucursal.getText());
			sucursal.setCodigoPostal(this.txfCodigoPostalSucursal.getText());
			sucursal.setDireccion(this.txaDireccionSucursal.getText());

			this.sucursalController.insertarNuevaSucursal(sucursal);

			this.borrarCampos();


			JOptionPane.showMessageDialog(this, "Registro almacenado con exito", "Registrat Sucursal",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	/**
	 * actualiza los datos de una sucursal existente en la base de datos
	 */
	private void actualizarSucursal() {

		Sucursal sucursal = new Sucursal();

		if (validarCamposVacios() == true) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {

			sucursal.setIdSucursal(this.idSucursal);
			sucursal.setNombre(this.txfNombreSucursal.getText());
			sucursal.setDescripcion(this.txaDescripcionSucursal.getText());
			sucursal.setTelefono(this.txfTelefonoSucursal.getText());
			sucursal.setEmail(this.txfEmailSucursal.getText());
			sucursal.setEstado(this.txfEstadoSucursal.getText());
			sucursal.setCiudad(this.txfCiudadSucursal.getText());
			sucursal.setCodigoPostal(this.txfCodigoPostalSucursal.getText());
			sucursal.setDireccion(this.txaDireccionSucursal.getText());

			this.sucursalController.actualizarSucursal(sucursal);

			this.borrarCampos();

			JOptionPane.showMessageDialog(this, "Registro actualizado con exito", "Actualizar Sucursal",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception er) {
			er.printStackTrace();
		}

	}


	/*
	 * private void llenarCmbSucursales() {
	 * 
	 * if (this.cmbNombreSucursal == null) { return; }
	 * 
	 * this.cmbNombreSucursal.removeAll(); this.cmbNombreSucursal.updateUI();
	 * this.sucursalController.consultarNombreSucursales(cmbNombreSucursal); }
	 */

	


	private boolean validarCamposVacios() {

		if (this.cmbNombreSucursal != null) {
			if (((String) this.cmbNombreSucursal.getSelectedItem()).length() < 1
					|| ((String) this.cmbNombreSucursal.getSelectedItem()).isEmpty()) {
				return true;
			}
		}

		if (this.txfNombreSucursal != null) {
			if (this.txfNombreSucursal.getText().isEmpty() || this.txfNombreSucursal.getText().length() < 1) {
				return true;
			}
		}

		if (this.txfTelefonoSucursal.getText().length() < 1 || this.txfTelefonoSucursal.getText().isEmpty()) {
			return true;
		}

		if (this.txfEmailSucursal.getText().length() < 1 || this.txfEmailSucursal.getText().isEmpty()) {
			return true;
		}

		if (this.txfEstadoSucursal.getText().length() < 1 || this.txfEstadoSucursal.getText().isEmpty()) {
			return true;
		}

		if (this.txfCiudadSucursal.getText().length() < 1 || this.txfCiudadSucursal.getText().isEmpty()) {
			return true;
		}

		if (this.txfCodigoPostalSucursal.getText().length() < 1 || this.txfCodigoPostalSucursal.getText().isEmpty()) {
			return true;
		}

		if (this.txaDireccionSucursal.getText().length() < 1 || this.txaDireccionSucursal.getText().isEmpty()) {
			return true;
		}

		return false;

	}

	/**
	 * borra el texto de todos los del formulario
	 */
	private void borrarCampos() {

		if (this.txfNombreSucursal != null) {
			this.txfNombreSucursal.setText("");
		}

		this.txaDescripcionSucursal.setText("");
		this.txfTelefonoSucursal.setText("");
		this.txfEmailSucursal.setText("");
		this.txfEstadoSucursal.setText("");
		this.txfCiudadSucursal.setText("");
		this.txfCodigoPostalSucursal.setText("");
		this.txaDireccionSucursal.setText("");

	}

	private void consultarSucursalPorId() {


		Sucursal sucursal = sucursalController.consultarSucursal(this.idSucursal);


		this.txfNombreSucursal.setText(sucursal.getNombre());
		this.txaDescripcionSucursal.setText(sucursal.getDescripcion());
		this.txfTelefonoSucursal.setText(sucursal.getTelefono());
		this.txfEmailSucursal.setText(sucursal.getEmail());
		this.txfEstadoSucursal.setText(sucursal.getEstado());
		this.txfCiudadSucursal.setText(sucursal.getCiudad());
		this.txfCodigoPostalSucursal.setText(sucursal.getCodigoPostal());
		this.txaDireccionSucursal.setText(sucursal.getDireccion());

	}
}
