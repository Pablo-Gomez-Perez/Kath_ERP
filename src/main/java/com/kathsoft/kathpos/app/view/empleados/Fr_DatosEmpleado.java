package com.kathsoft.kathpos.app.view.empleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.app.model.empleado.Empleado;
import com.kathsoft.kathpos.app.model.empleado.EmpleadoById;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.UiTools;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;

public class Fr_DatosEmpleado extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2754103795838559070L;
	/**
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel;
	private Box horizontalBox_7;
	private JButton btnCancelar;
	private Component horizontalStrut_7;
	private JButton btnAgregarEmpleado;
	private JTextField txfRfcEmpleado;
	private JTextField txfCurpEmpleado;
	private JTextField txfNombreCompletoEmpleado;
	private JTextField txfNombreCortoEmpleado;
	private JLabel lblFNacimiento;
	private JTextField txfEstadoEmpleado;
	private JTextField txfCiudadEmpleado;
	private JTextField txfCodigoPostal;
	private JTextField txfClaveCuentaContable;
	private JTable tableNumerosTelefonicos;
	private JLabel lblSucursal;
	private JLabel lblForRfc;
	private JLabel lblForCurp;
	private JLabel lblForNombreCompleto;
	private JLabel lblForAlias;
	private JFormattedTextField frmtdtxtfldFechanacimientoempleado;
	private JLabel lblEmail;
	private JFormattedTextField frmtxfCorreoElectronico;
	private JLabel lblEstado;
	private JLabel lblCiudad;
	private JLabel lblDireccion;
	private JScrollPane scrollPaneTxaDireccionEmpleado;
	private JLabel lblCodigoPostal;
	private JLabel lblClaveContable;
	private JLabel lblNmerosDeContacto;
	private JScrollPane scrollPaneNumerosTelefonicos;
	private JButton btnAgregarTelefono;
	private JButton btnEliminarTelefono;
	private JComboBox<JComboboxDataViewModel> cmbSucursalEmpleado;
	private JTextArea textAreaDireccionEmpleado;
	private JLabel lblContrasea;
	private JPasswordField passwordFieldContraseniaEmpleado;
	private JPasswordField passwordFieldVerificarContraseniaEmpleado;
	private JLabel lblNewLabel_1;

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
		setBounds(100, 100, 570, 550);
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

		lblForRfc = new JLabel("RFC");

		txfRfcEmpleado = new JTextField();
		txfRfcEmpleado.setColumns(10);

		lblForCurp = new JLabel("CURP");

		txfCurpEmpleado = new JTextField();
		txfCurpEmpleado.setColumns(10);

		lblForNombreCompleto = new JLabel("Nombre Completo");

		txfNombreCompletoEmpleado = new JTextField();
		txfNombreCompletoEmpleado.setColumns(10);

		lblForAlias = new JLabel("Alias");

		txfNombreCortoEmpleado = new JTextField();
		txfNombreCortoEmpleado.setColumns(10);

		lblFNacimiento = new JLabel("F. Nacimiento");

		frmtdtxtfldFechanacimientoempleado = new JFormattedTextField();

		lblEmail = new JLabel("Email");

		frmtxfCorreoElectronico = new JFormattedTextField();

		lblEstado = new JLabel("Estado");

		txfEstadoEmpleado = new JTextField();
		txfEstadoEmpleado.setColumns(10);

		lblCiudad = new JLabel("Ciudad");

		txfCiudadEmpleado = new JTextField();
		txfCiudadEmpleado.setColumns(10);

		lblDireccion = new JLabel("Dirección");

		scrollPaneTxaDireccionEmpleado = new JScrollPane();

		lblCodigoPostal = new JLabel("Codigo Postal");

		txfCodigoPostal = new JTextField();
		txfCodigoPostal.setColumns(10);

		lblClaveContable = new JLabel("Clave Contable");

		txfClaveCuentaContable = new JTextField();
		txfClaveCuentaContable.setColumns(10);

		lblNmerosDeContacto = new JLabel("Números de contacto");

		scrollPaneNumerosTelefonicos = new JScrollPane();

		btnAgregarTelefono = new JButton("Nuevo");
		btnAgregarTelefono.setBackground(new Color(0, 255, 51));
		btnAgregarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));

		btnEliminarTelefono = new JButton("Borrar");
		btnEliminarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEliminarTelefono.setBackground(new Color(255, 102, 102));

		lblSucursal = new JLabel("Sucursal");

		cmbSucursalEmpleado = new JComboBox<JComboboxDataViewModel>();

		lblContrasea = new JLabel("Contraseña");

		passwordFieldContraseniaEmpleado = new JPasswordField();

		lblNewLabel_1 = new JLabel("Verificar contraseña");

		passwordFieldVerificarContraseniaEmpleado = new JPasswordField();
		
		JButton btnFormConsultaCuentaContable = new JButton("");
		btnFormConsultaCuentaContable.setIcon(new ImageIcon(Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/assets/cuentas_contables.png")));
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addComponent(scrollPaneTxaDireccionEmpleado, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblForRfc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblForCurp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfCurpEmpleado, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblForNombreCompleto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombreCompletoEmpleado, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblForAlias)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombreCortoEmpleado, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
									.addComponent(lblFNacimiento)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(frmtdtxtfldFechanacimientoempleado, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
									.addComponent(lblEstado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txfEstadoEmpleado, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblCiudad))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addComponent(txfCiudadEmpleado, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
								.addComponent(frmtxfCorreoElectronico, 241, 241, 241)))
						.addComponent(lblDireccion)
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblCodigoPostal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblClaveContable)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 68, Short.MAX_VALUE))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblNmerosDeContacto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAgregarTelefono)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblSucursal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cmbSucursalEmpleado, 0, 190, Short.MAX_VALUE))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblContrasea)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordFieldContraseniaEmpleado, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(passwordFieldVerificarContraseniaEmpleado, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblForRfc)
						.addComponent(txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblForCurp)
						.addComponent(txfCurpEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblForNombreCompleto)
						.addComponent(txfNombreCompletoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblForAlias)
						.addComponent(txfNombreCortoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFNacimiento)
						.addComponent(frmtdtxtfldFechanacimientoempleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtxfCorreoElectronico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(txfEstadoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCiudad)
						.addComponent(txfCiudadEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDireccion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTxaDireccionEmpleado, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigoPostal)
						.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClaveContable)
						.addComponent(txfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNmerosDeContacto)
						.addComponent(btnAgregarTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSucursal)
						.addComponent(cmbSucursalEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.PREFERRED_SIZE, 113, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblContrasea)
						.addComponent(passwordFieldContraseniaEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordFieldVerificarContraseniaEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addContainerGap(57, Short.MAX_VALUE))
		);

		tableNumerosTelefonicos = new JTable();
		scrollPaneNumerosTelefonicos.setViewportView(tableNumerosTelefonicos);

		textAreaDireccionEmpleado = new JTextArea();
		scrollPaneTxaDireccionEmpleado.setViewportView(textAreaDireccionEmpleado);
		panelCentralFormulario.setLayout(gl_panelCentralFormulario);

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
				new ImageIcon(Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
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
		btnAgregarEmpleado.setIcon(
				new ImageIcon(Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
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
	 * busca los datos del empleado en la bd de acuerdo al id que se le pase como
	 * parametro y asigna los valores correspondientes a sus respectivos campos en
	 * el formulario
	 * 
	 * @param idEmpleado
	 */
	private void consultarEmpleadoPorId(int idEmpleado) {
		EmpleadoById empleado = AppContext.empleadoController.consultarEmpleadoPorId(idEmpleado);

		if (empleado == null) {
			JOptionPane.showMessageDialog(this, "No fue posible cargar el empleado", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		txfRfcEmpleado.setText(empleado.getRfc());
		txfCurpEmpleado.setText(empleado.getCurp());
		txfNombreCompletoEmpleado.setText(empleado.getNombreCompleto());
		txfNombreCortoEmpleado.setText(empleado.getNombreCorto());
		frmtdtxtfldFechanacimientoempleado.setText(empleado.getFechaNac() == null ? ""
				: empleado.getFechaNac().toString());
		frmtxfCorreoElectronico.setText(empleado.getCorreoElectronico());
		txfEstadoEmpleado.setText(empleado.getEstado());
		txfCiudadEmpleado.setText(empleado.getCiudad());
		textAreaDireccionEmpleado.setText(empleado.getDireccion());
		txfCodigoPostal.setText(empleado.getCodigoPostal());
		txfClaveCuentaContable.setText(String.valueOf(empleado.getIdCuentaContable()));
		passwordFieldContraseniaEmpleado.setText("");
		passwordFieldVerificarContraseniaEmpleado.setText("");
		UiTools.jComboboxSetSelectedIndex(this.cmbSucursalEmpleado, empleado.getIdSucursal());
		
	}

	/**
	 * inserta un registro de un nuevo empleado en la base de datos
	 */
	private void insertarEmpleado() {
		if (!validarCamposVacios()) {
			return;
		}

		Empleado empleado = new Empleado();
		empleado.setIdCuentaContable(Integer.parseInt(txfClaveCuentaContable.getText().trim()));
		empleado.setIdSucursal(((JComboboxDataViewModel) cmbSucursalEmpleado.getSelectedItem()).id());
		empleado.setRfc(txfRfcEmpleado.getText().trim());
		empleado.setCurp(txfCurpEmpleado.getText().trim());
		empleado.setNombreCompleto(txfNombreCompletoEmpleado.getText().trim());
		empleado.setNombreCorto(txfNombreCortoEmpleado.getText().trim());
		empleado.setFechaNac(Date.valueOf(frmtdtxtfldFechanacimientoempleado.getText().trim()));
		empleado.setCorreoElectronico(frmtxfCorreoElectronico.getText().trim());
		empleado.setEstado(txfEstadoEmpleado.getText().trim());
		empleado.setCiudad(txfCiudadEmpleado.getText().trim());
		empleado.setDireccion(textAreaDireccionEmpleado.getText().trim());
		empleado.setCodigoPostal(txfCodigoPostal.getText().trim());
		empleado.setContrasenia(new String(passwordFieldContraseniaEmpleado.getPassword()));

		if (!empleado.getContrasenia().equals(new String(passwordFieldVerificarContraseniaEmpleado.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		SpResponseModel respuesta = AppContext.empleadoController.insertarNuevoEmpleado(empleado);
		JOptionPane.showMessageDialog(this, respuesta.message(), respuesta.id() == 500 ? "Error" : "Aviso",
				respuesta.id() == 500 ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
		if (respuesta.id() != 500) {
			this.dispose();
		}
	}

	/**
	 * actualiza un registro existente de un empleado en la base de datos
	 */
	private void actualizarEmpleado(int idEmpleado) {
		if (!validarCamposVacios()) {
			return;
		}

		Empleado empleado = new Empleado();
		empleado.setIdEmpleado(idEmpleado);
		empleado.setIdCuentaContable(Integer.parseInt(txfClaveCuentaContable.getText().trim()));
		empleado.setIdSucursal(((JComboboxDataViewModel) cmbSucursalEmpleado.getSelectedItem()).id());
		empleado.setRfc(txfRfcEmpleado.getText().trim());
		empleado.setCurp(txfCurpEmpleado.getText().trim());
		empleado.setNombreCompleto(txfNombreCompletoEmpleado.getText().trim());
		empleado.setNombreCorto(txfNombreCortoEmpleado.getText().trim());
		empleado.setFechaNac(Date.valueOf(frmtdtxtfldFechanacimientoempleado.getText().trim()));
		empleado.setCorreoElectronico(frmtxfCorreoElectronico.getText().trim());
		empleado.setEstado(txfEstadoEmpleado.getText().trim());
		empleado.setCiudad(txfCiudadEmpleado.getText().trim());
		empleado.setDireccion(textAreaDireccionEmpleado.getText().trim());
		empleado.setCodigoPostal(txfCodigoPostal.getText().trim());
		empleado.setContrasenia(new String(passwordFieldContraseniaEmpleado.getPassword()));

		if (!empleado.getContrasenia().equals(new String(passwordFieldVerificarContraseniaEmpleado.getPassword()))) {
			JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		SpResponseModel respuesta = AppContext.empleadoController.actualizarEmpleado(empleado);
		JOptionPane.showMessageDialog(this, respuesta.message(), respuesta.id() == 500 ? "Error" : "Aviso",
				respuesta.id() == 500 ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
		if (respuesta.id() != 500) {
			this.dispose();
		}
	}

	private boolean validarCamposVacios() {
		return !txfRfcEmpleado.getText().trim().isEmpty() && !txfCurpEmpleado.getText().trim().isEmpty()
				&& !txfNombreCompletoEmpleado.getText().trim().isEmpty() && !txfNombreCortoEmpleado.getText().trim().isEmpty()
				&& !frmtdtxtfldFechanacimientoempleado.getText().trim().isEmpty()
				&& !frmtxfCorreoElectronico.getText().trim().isEmpty() && !txfEstadoEmpleado.getText().trim().isEmpty()
				&& !txfCiudadEmpleado.getText().trim().isEmpty() && !textAreaDireccionEmpleado.getText().trim().isEmpty()
				&& !txfCodigoPostal.getText().trim().isEmpty() && !txfClaveCuentaContable.getText().trim().isEmpty()
				&& cmbSucursalEmpleado.getSelectedItem() != null && passwordFieldContraseniaEmpleado.getPassword().length > 0
				&& passwordFieldVerificarContraseniaEmpleado.getPassword().length > 0;
	}

	private void llenarCmbSucursales() {

		this.cmbSucursalEmpleado.removeAllItems();
		this.cmbSucursalEmpleado.updateUI();

		AppContext.sucursalController.consultarNombreSucursales().forEach(cmbSucursalEmpleado::addItem);

	}
}
