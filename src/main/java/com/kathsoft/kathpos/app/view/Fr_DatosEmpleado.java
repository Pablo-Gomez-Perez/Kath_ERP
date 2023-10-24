package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.model.Empleado;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JComboBox;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fr_DatosEmpleado extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2754103795838559070L;
	/**
	 * 
	 * 
	 */
	private EmpleadoController empleadoController = new EmpleadoController();
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private JComboBox<String> cmbRFCEmpleado;
	private Component verticalStrut_1;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_2;
	private JTextField txfCurpEmpleado;
	private Component horizontalStrut;
	private JLabel lblNewLabel_3;
	private JTextField txfNombreCortoEmpleado;
	private Component verticalStrut_2;
	private Box horizontalBox_2;
	private JLabel lblNewLabel_4;
	private JTextField txfNombreCompletoEmpleado;
	private Component verticalStrut_3;
	private Box horizontalBox_3;
	private JLabel lblNewLabel_5;
	private JTextField txfFechaNacEmpleadoDD;
	private Component horizontalStrut_1;
	private JTextField txfFechaNacEmpleadoMM;
	private Component horizontalStrut_2;
	private JTextField txfFechaNacEmpleadoYY;
	private Component horizontalStrut_3;
	private JLabel lblNewLabel_6;
	private JTextField txfEmailEmpleado;
	private Component verticalStrut_4;
	private Box horizontalBox_4;
	private JLabel lblNewLabel_7;
	private JTextField txfEstadoEmpleado;
	private Component horizontalStrut_4;
	private JLabel lblNewLabel_8;
	private JTextField txfCiudadEmpleado;
	private Component verticalStrut_5;
	private Box horizontalBox_5;
	private JLabel lblNewLabel_9;
	private JTextField txfDireccionEmpleado;
	private Component horizontalStrut_5;
	private JLabel lblNewLabel_10;
	private JTextField txfCodigoPostalEmpleado;
	private Component verticalStrut_6;
	private Box horizontalBox_6;
	private JLabel lblNewLabel_11;
	private JPasswordField txpsContraseniaEmpleado;
	private Component horizontalStrut_6;
	private JButton btnNuevaContraseniaEmpleado;
	private Component verticalStrut_7;
	private Box horizontalBox_7;
	private JButton btnCancelar;
	private Component horizontalStrut_7;
	private JButton btnAgregarEmpleado;
	private JTextField txfRfcEmpleado;
	private Component horizontalStrut_8;
	private JLabel lblNewLabel_12;
	private JComboBox<String> cmbSucursalEmpleado;
	private SucursalController sucursalController = new SucursalController();

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_DatosEmpleado frame = new
	 * Fr_DatosEmpleado(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_DatosEmpleado(int opcion, int idEmpleado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		if (opcion == 0) {
			this.setTitle("Nuevo Empleado");
			lblNewLabel.setText("Agregar Nuevo Empleado");
		} else if (opcion == 1) {
			this.setTitle("Actualizar Empleado");
			lblNewLabel.setText("Actualizar Empleado");
		}

		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));

		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);

		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("RFC Empleado");
		horizontalBox.add(lblNewLabel_1);

		this.txfRfcEmpleado = new JTextField();
		this.txfRfcEmpleado.setColumns(60);
		this.txfRfcEmpleado.setMaximumSize(this.txfRfcEmpleado.getPreferredSize());
		horizontalBox.add(txfRfcEmpleado);

		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);

		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);

		lblNewLabel_2 = new JLabel("CURP");
		horizontalBox_1.add(lblNewLabel_2);

		txfCurpEmpleado = new JTextField();
		txfCurpEmpleado.setMaximumSize(new Dimension(166, 20));
		txfCurpEmpleado.setColumns(35);
		this.txfCurpEmpleado.setMaximumSize(this.txfCurpEmpleado.getPreferredSize());
		horizontalBox_1.add(txfCurpEmpleado);

		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_8);

		lblNewLabel_12 = new JLabel("Sucursal");
		horizontalBox_1.add(lblNewLabel_12);

		cmbSucursalEmpleado = new JComboBox<String>();
		horizontalBox_1.add(cmbSucursalEmpleado);

		verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);

		horizontalBox_2 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_2);

		lblNewLabel_4 = new JLabel("Nombre completo");
		horizontalBox_2.add(lblNewLabel_4);

		txfNombreCompletoEmpleado = new JTextField();
		txfNombreCompletoEmpleado.setMaximumSize(new Dimension(286, 20));
		txfNombreCompletoEmpleado.setColumns(60);
		this.txfNombreCompletoEmpleado.setMaximumSize(this.txfNombreCompletoEmpleado.getPreferredSize());
		horizontalBox_2.add(txfNombreCompletoEmpleado);

		lblNewLabel_3 = new JLabel("Nombre Corto");
		horizontalBox_2.add(lblNewLabel_3);

		horizontalStrut = Box.createHorizontalStrut(10);
		horizontalBox_2.add(horizontalStrut);

		txfNombreCortoEmpleado = new JTextField();
		horizontalBox_2.add(txfNombreCortoEmpleado);
		txfNombreCortoEmpleado.setMaximumSize(new Dimension(86, 20));
		txfNombreCortoEmpleado.setColumns(40);
		this.txfNombreCortoEmpleado.setMaximumSize(this.txfNombreCortoEmpleado.getPreferredSize());

		verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);

		horizontalBox_3 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_3);

		lblNewLabel_5 = new JLabel("Fecha de n.");
		horizontalBox_3.add(lblNewLabel_5);

		txfFechaNacEmpleadoDD = new JTextField();
		txfFechaNacEmpleadoDD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0' || ch > '9') || txfFechaNacEmpleadoDD.getText().length() >= 2) {
					e.consume();
				}
			}
		});
		txfFechaNacEmpleadoDD.setToolTipText("Día");
		txfFechaNacEmpleadoDD.setMaximumSize(new Dimension(22, 20));
		txfFechaNacEmpleadoDD.setColumns(4);
		this.txfFechaNacEmpleadoDD.setMaximumSize(this.txfFechaNacEmpleadoDD.getPreferredSize());
		horizontalBox_3.add(txfFechaNacEmpleadoDD);

		horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_1);

		txfFechaNacEmpleadoMM = new JTextField();
		txfFechaNacEmpleadoMM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0' || ch > '9') || txfFechaNacEmpleadoMM.getText().length() >= 2) {
					e.consume();
				}
			}
		});
		txfFechaNacEmpleadoMM.setToolTipText("Mes");
		txfFechaNacEmpleadoMM.setMaximumSize(new Dimension(22, 20));
		txfFechaNacEmpleadoMM.setColumns(4);
		this.txfFechaNacEmpleadoMM.setMaximumSize(this.txfFechaNacEmpleadoMM.getPreferredSize());
		horizontalBox_3.add(txfFechaNacEmpleadoMM);

		horizontalStrut_2 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_2);

		txfFechaNacEmpleadoYY = new JTextField();
		txfFechaNacEmpleadoYY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch < '0' || ch > '9' || txfFechaNacEmpleadoYY.getText().length() >= 4) {
					e.consume();
				}
			}
		});
		txfFechaNacEmpleadoYY.setToolTipText("Año");
		txfFechaNacEmpleadoYY.setMaximumSize(new Dimension(38, 20));
		txfFechaNacEmpleadoYY.setColumns(8);
		this.txfFechaNacEmpleadoYY.setMaximumSize(this.txfFechaNacEmpleadoYY.getPreferredSize());
		horizontalBox_3.add(txfFechaNacEmpleadoYY);

		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_3);

		lblNewLabel_6 = new JLabel("Email");
		horizontalBox_3.add(lblNewLabel_6);

		txfEmailEmpleado = new JTextField();
		txfEmailEmpleado.setMaximumSize(new Dimension(166, 20));
		txfEmailEmpleado.setColumns(40);
		this.txfEmailEmpleado.setMaximumSize(this.txfEmailEmpleado.getPreferredSize());
		horizontalBox_3.add(txfEmailEmpleado);

		verticalStrut_4 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_4);

		horizontalBox_4 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_4);

		lblNewLabel_7 = new JLabel("Estado");
		horizontalBox_4.add(lblNewLabel_7);

		txfEstadoEmpleado = new JTextField();
		txfEstadoEmpleado.setMaximumSize(new Dimension(126, 20));
		txfEstadoEmpleado.setColumns(30);
		this.txfEstadoEmpleado.setMaximumSize(this.txfEstadoEmpleado.getPreferredSize());
		horizontalBox_4.add(txfEstadoEmpleado);

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_4);

		lblNewLabel_8 = new JLabel("Ciudad");
		horizontalBox_4.add(lblNewLabel_8);

		txfCiudadEmpleado = new JTextField();
		txfCiudadEmpleado.setMaximumSize(new Dimension(126, 20));
		txfCiudadEmpleado.setColumns(40);
		this.txfCiudadEmpleado.setMaximumSize(this.txfCiudadEmpleado.getPreferredSize());
		horizontalBox_4.add(txfCiudadEmpleado);

		verticalStrut_5 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_5);

		horizontalBox_5 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_5);

		lblNewLabel_9 = new JLabel("Direccion");
		horizontalBox_5.add(lblNewLabel_9);

		txfDireccionEmpleado = new JTextField();
		txfDireccionEmpleado.setMaximumSize(new Dimension(166, 20));
		txfDireccionEmpleado.setColumns(50);
		this.txfDireccionEmpleado.setMaximumSize(this.txfDireccionEmpleado.getPreferredSize());
		horizontalBox_5.add(txfDireccionEmpleado);

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_5);

		lblNewLabel_10 = new JLabel("Codigo Postal");
		horizontalBox_5.add(lblNewLabel_10);

		txfCodigoPostalEmpleado = new JTextField();
		txfCodigoPostalEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch < '0' || ch > '9' || txfCodigoPostalEmpleado.getText().length() >= 5) {
					e.consume();
				}
			}
		});
		txfCodigoPostalEmpleado.setMaximumSize(new Dimension(70, 20));
		txfCodigoPostalEmpleado.setColumns(30);
		horizontalBox_5.add(txfCodigoPostalEmpleado);

		verticalStrut_6 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_6);

		horizontalBox_6 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_6);

		lblNewLabel_11 = new JLabel("Contraseña");
		horizontalBox_6.add(lblNewLabel_11);

		txpsContraseniaEmpleado = new JPasswordField();
		txpsContraseniaEmpleado.setMaximumSize(new Dimension(166, 20));
		txpsContraseniaEmpleado.setEnabled(false);
		txpsContraseniaEmpleado.setEditable(false);
		txpsContraseniaEmpleado.setColumns(60);
		this.txpsContraseniaEmpleado.setMaximumSize(this.txpsContraseniaEmpleado.getPreferredSize());

		horizontalBox_6.add(txpsContraseniaEmpleado);

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_6.add(horizontalStrut_6);

		btnNuevaContraseniaEmpleado = new JButton("Nueva");
		btnNuevaContraseniaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPasswordEmpleado();
			}
		});
		btnNuevaContraseniaEmpleado.setIcon(
				new ImageIcon(Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/resources/lapiz.png")));
		btnNuevaContraseniaEmpleado.setBackground(new Color(0, 128, 128));
		if (opcion == 0) {
			this.btnNuevaContraseniaEmpleado.setEnabled(false);
		} else if (opcion == 1) {
			this.btnNuevaContraseniaEmpleado.setEnabled(true);
		}

		horizontalBox_6.add(btnNuevaContraseniaEmpleado);

		verticalStrut_7 = Box.createVerticalStrut(80);
		panelCentralFormulario.add(verticalStrut_7);

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		horizontalBox_7 = Box.createHorizontalBox();
		panelInferiorBotones.add(horizontalBox_7);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btnCancelar.setIcon(
				new ImageIcon(Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		btnCancelar.setBackground(new Color(205, 92, 92));
		horizontalBox_7.add(btnCancelar);

		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_7.add(horizontalStrut_7);

		btnAgregarEmpleado = new JButton("Agregar");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opcion == 0) {
					insertarEmpleado();
				} else if (opcion == 1) {
					actualizarEmpleado(idEmpleado);
				}
			}
		});
		btnAgregarEmpleado.setIcon(new ImageIcon(
				Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		btnAgregarEmpleado.setBackground(new Color(144, 238, 144));
		horizontalBox_7.add(btnAgregarEmpleado);

		if (opcion == 1) {

		}

		this.llenarCmbSucursales();
		if (opcion == 1) {
			this.consultarEmpleadoPorId(idEmpleado);
		}
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void cerrarForm() {
		this.dispose();
	}

	/**
	 * llena el JCombobox del panel de rfc de empleados con todos los elemtnos
	 * retornados por la vista almacenada
	 *
	 * private void llenarCmbRfcEmpleados() { this.cmbRFCEmpleado.removeAllItems();
	 * this.cmbRFCEmpleado.updateUI();
	 * empleadoController.consultarRfcEmpleado(this.cmbRFCEmpleado);
	 * cmbRFCEmpleado.setSelectedIndex(1); }
	 */

	/**
	 * busca los datos del empleado en la bd de acuerdo al rfc que se le pase como
	 * parametro y asigna los valores correspondientes a sus respectivos campos en
	 * el formulario
	 * 
	 * @param rfc
	 */
	private void consultarEmpleadoPorId(int idEmpleado) {
		Empleado empl = empleadoController.consultarEmpleadoPorId(idEmpleado);
		String dia = "";
		String mes = "";
		String anio = "";

		if (empl.getFechaNacimiento() != null) {
			String[] fecha = empl.getFechaNacimiento().toString().split("-");
			dia = fecha[2];
			mes = fecha[1];
			anio = fecha[0];
		}

		System.out.println(empl.toString());

		this.txfRfcEmpleado.setText(empl.getRfc());
		this.txfCurpEmpleado.setText(empl.getCurp());
		this.cmbSucursalEmpleado.setSelectedIndex(empl.getIdSucursal());
		this.txfNombreCortoEmpleado.setText(empl.getNombreCorto());
		this.txfNombreCompletoEmpleado.setText(empl.getNombre());
		this.txfFechaNacEmpleadoDD.setText(dia);
		this.txfFechaNacEmpleadoMM.setText(mes);
		this.txfFechaNacEmpleadoYY.setText(anio);
		this.txfEmailEmpleado.setText(empl.getEmail());
		this.txfEstadoEmpleado.setText(empl.getEstado());
		this.txfCiudadEmpleado.setText(empl.getCiudad());
		this.txfDireccionEmpleado.setText(empl.getDireccion());
		this.txfCodigoPostalEmpleado.setText(empl.getCodigoPostal());
		this.txpsContraseniaEmpleado.setText(empl.getPassword());
	}

	/**
	 * inserta un registro de un nuevo empleado en la base de datos
	 */
	private void insertarEmpleado() {

		if (!this.validarCamposVacios()) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Empleado empl = new Empleado();

		// formato de fecha para el estandar SQL
		String dia = this.txfFechaNacEmpleadoDD.getText();
		String mes = this.txfFechaNacEmpleadoMM.getText();
		String anio = this.txfFechaNacEmpleadoYY.getText();
		String fecha = anio + "-" + mes + "-" + dia;

		try {

			empl.setIdSucursal(this.cmbSucursalEmpleado.getSelectedIndex());
			empl.setRfc(this.txfRfcEmpleado.getText());
			empl.setCurp(this.txfCurpEmpleado.getText());
			empl.setNombre(this.txfNombreCompletoEmpleado.getText());
			empl.setNombreCorto(this.txfNombreCortoEmpleado.getText());
			empl.setFechaNacimiento(Date.valueOf(fecha));
			empl.setEmail(this.txfEmailEmpleado.getText());
			empl.setEstado(this.txfEstadoEmpleado.getText());
			empl.setCiudad(this.txfCiudadEmpleado.getText());
			empl.setDireccion(this.txfDireccionEmpleado.getText());
			empl.setCodigoPostal(this.txfCodigoPostalEmpleado.getText());

			this.empleadoController.insertarNuevoEmpleado(empl);

			JOptionPane.showMessageDialog(this, "Registro almacenado", "Operacion exitosa",
					JOptionPane.INFORMATION_MESSAGE);

			this.cerrarForm();

		} catch (Exception er) {
			System.out.println("Error en vista :-> " + er.getMessage() + "\n");
			er.printStackTrace();
		}

	}

	/**
	 * actualiza un registro existente de un empleado en la base de datos
	 */
	private void actualizarEmpleado(int idEmpleado) {

		if (!this.validarCamposVacios()) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Empleado empl = new Empleado();

		// formato de fecha para el estandar SQL
		String dia = this.txfFechaNacEmpleadoDD.getText();
		String mes = this.txfFechaNacEmpleadoMM.getText();
		String anio = this.txfFechaNacEmpleadoYY.getText();
		String fecha = anio + "-" + mes + "-" + dia;

		try {

			empl.setIdSucursal(this.cmbSucursalEmpleado.getSelectedIndex());
			empl.setRfc(this.txfRfcEmpleado.getText());
			empl.setCurp(this.txfCurpEmpleado.getText());
			empl.setNombre(this.txfNombreCompletoEmpleado.getText());
			empl.setNombreCorto(this.txfNombreCortoEmpleado.getText());
			empl.setFechaNacimiento(Date.valueOf(fecha));
			empl.setEmail(this.txfEmailEmpleado.getText());
			empl.setEstado(this.txfEstadoEmpleado.getText());
			empl.setCiudad(this.txfCiudadEmpleado.getText());
			empl.setDireccion(this.txfDireccionEmpleado.getText());
			empl.setCodigoPostal(this.txfCodigoPostalEmpleado.getText());

			this.empleadoController.actualizarEmpleado(empl);

			JOptionPane.showMessageDialog(this, "Registro actualizado", "Operación Exitosa",
					JOptionPane.INFORMATION_MESSAGE);

			this.cerrarForm();

		} catch (Exception er) {
			System.out.println("Error en vista :-> " + er.getMessage() + "\n");
			er.printStackTrace();
		}

	}

	private boolean validarCamposVacios() {

		if (this.cmbRFCEmpleado != null) {
			if (((String) this.cmbRFCEmpleado.getSelectedItem()).isEmpty()
					|| ((String) this.cmbRFCEmpleado.getSelectedItem()).length() < 1) {
				return false;
			}
		}

		if (this.txfRfcEmpleado != null) {
			if (this.txfRfcEmpleado.getText().isEmpty() || this.txfRfcEmpleado.getText().length() < 1) {
				return false;
			}
		}

		if (this.txfCurpEmpleado.getText().isEmpty() || this.txfCurpEmpleado.getText().length() < 1) {
			return false;
		}

		if (((String) this.cmbSucursalEmpleado.getSelectedItem()).isEmpty()
				|| ((String) this.cmbSucursalEmpleado.getSelectedItem()).length() < 1) {
			return false;
		}

		if (this.txfFechaNacEmpleadoDD.getText().isEmpty() || this.txfFechaNacEmpleadoDD.getText().length() < 1) {
			return false;
		}

		if (this.txfFechaNacEmpleadoMM.getText().isEmpty() || this.txfFechaNacEmpleadoMM.getText().length() < 1) {
			return false;
		}

		if (this.txfFechaNacEmpleadoYY.getText().isEmpty() || this.txfFechaNacEmpleadoYY.getText().length() < 1) {
			return false;
		}

		if (this.txfEmailEmpleado.getText().isEmpty() || this.txfEmailEmpleado.getText().length() < 1) {
			return false;
		}

		if (this.txfEstadoEmpleado.getText().isEmpty() || this.txfEstadoEmpleado.getText().length() < 1) {
			return false;
		}

		if (this.txfCiudadEmpleado.getText().isEmpty() || this.txfCiudadEmpleado.getText().length() < 1) {
			return false;
		}

		if (this.txfDireccionEmpleado.getText().isEmpty() || this.txfDireccionEmpleado.getText().length() < 1) {
			return false;
		}

		if (this.txfCodigoPostalEmpleado.getText().isEmpty() || this.txfCodigoPostalEmpleado.getText().length() < 1) {
			return false;
		}

		return true;
	}

	private void llenarCmbSucursales() {
		this.cmbSucursalEmpleado.removeAllItems();
		this.cmbSucursalEmpleado.updateUI();
		this.sucursalController.consultarNombreSucursales(cmbSucursalEmpleado);
	}

	private void abrirVentanaPasswordEmpleado() {

		if (this.txfRfcEmpleado.getText() == null) {
			return;
		}

		String rfcEmpleado = this.txfRfcEmpleado.getText();
		String nombreCortoEmpleado = this.txfNombreCortoEmpleado.getText();
		Component cmp = this;

		if (rfcEmpleado.equals("") || rfcEmpleado.isEmpty() || rfcEmpleado.length() < 1) {
			JOptionPane.showMessageDialog(this, "Error", "No se ha seleccionado empleado", JOptionPane.ERROR_MESSAGE);
			return;
		}

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Fr_NewPasswordEmpleado frame = new Fr_NewPasswordEmpleado(rfcEmpleado, nombreCortoEmpleado);
				frame.setLocationRelativeTo(cmp);
				frame.setVisible(rootPaneCheckingEnabled);
			}
		});

	}

}
