package com.kathsoft.kathpos.app.view.empleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.swing.Box;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.app.model.empleado.Empleado;
import com.kathsoft.kathpos.app.model.empleado.EmpleadoById;
import com.kathsoft.kathpos.app.model.telefono_x_empleado.TelefonoEmpleado;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
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
	private static final DateTimeFormatter FECHA_VISIBLE = DateTimeFormatter.ofPattern("dd/MM/uuuu")
			.withResolverStyle(ResolverStyle.STRICT);
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
	private DefaultTableModel modelTablaTelefonoEmpleado;;
	private boolean operacionEjecutada = false;

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

		frmtdtxtfldFechanacimientoempleado = new JFormattedTextField(crearCampoFechaNacimiento());
		frmtdtxtfldFechanacimientoempleado.setFocusLostBehavior(JFormattedTextField.PERSIST);
		frmtdtxtfldFechanacimientoempleado.setToolTipText("dd/MM/yyyy");

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
		txfClaveCuentaContable.setText("");
		txfClaveCuentaContable.setEditable(false);
		txfClaveCuentaContable.setEnabled(false);

		lblNmerosDeContacto = new JLabel("Números de contacto");

		scrollPaneNumerosTelefonicos = new JScrollPane();

		btnAgregarTelefono = new JButton("Nuevo");
		btnAgregarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createTelefonoEmpleado(idEmpleado);
			}
		});
		btnAgregarTelefono.setBackground(new Color(0, 255, 51));
		btnAgregarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));

		btnEliminarTelefono = new JButton("Borrar");
		btnEliminarTelefono.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eliminarTelefonoEmpleado();
				listarTelefonoDeEmpleado(idEmpleado);
			}
		});
		btnEliminarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEliminarTelefono.setBackground(new Color(255, 102, 102));

		lblSucursal = new JLabel("Sucursal");

		cmbSucursalEmpleado = new JComboBox<JComboboxDataViewModel>();

		lblContrasea = new JLabel("Contraseña");

		passwordFieldContraseniaEmpleado = new JPasswordField();

		lblNewLabel_1 = new JLabel("Verificar contraseña");

		passwordFieldVerificarContraseniaEmpleado = new JPasswordField();

		JButton btnFormConsultaCuentaContable = new JButton("");
		btnFormConsultaCuentaContable.setEnabled(false);
		btnFormConsultaCuentaContable.setIcon(new ImageIcon(
				Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/assets/cuentas_contables.png")));
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.DEFAULT_SIZE, 536,
										Short.MAX_VALUE)
								.addComponent(scrollPaneTxaDireccionEmpleado, GroupLayout.DEFAULT_SIZE, 536,
										Short.MAX_VALUE)
								.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblForRfc)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, 142,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblForCurp)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txfCurpEmpleado, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
										.addComponent(lblForNombreCompleto).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txfNombreCompletoEmpleado, GroupLayout.PREFERRED_SIZE, 210,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblForAlias)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(
												txfNombreCortoEmpleado, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE))
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
										.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelCentralFormulario.createSequentialGroup()
														.addComponent(lblFNacimiento)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(frmtdtxtfldFechanacimientoempleado,
																GroupLayout.PREFERRED_SIZE, 133,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panelCentralFormulario.createSequentialGroup()
														.addComponent(lblEstado)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(txfEstadoEmpleado, GroupLayout.PREFERRED_SIZE,
																176, GroupLayout.PREFERRED_SIZE)))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
												.addComponent(lblEmail).addComponent(lblCiudad))
										.addPreferredGap(ComponentPlacement.RELATED)
										.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
												.addComponent(txfCiudadEmpleado, GroupLayout.DEFAULT_SIZE, 241,
														Short.MAX_VALUE)
												.addComponent(frmtxfCorreoElectronico, 241, 241, 241)))
								.addComponent(lblDireccion)
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
										.addComponent(lblCodigoPostal).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblClaveContable)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(txfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, 140,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 68,
												Short.MAX_VALUE))
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
										.addComponent(lblNmerosDeContacto).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnAgregarTelefono).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 63,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblSucursal)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(cmbSucursalEmpleado, 0, 190, Short.MAX_VALUE))
								.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblContrasea)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(passwordFieldContraseniaEmpleado, GroupLayout.PREFERRED_SIZE, 134,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNewLabel_1)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(passwordFieldVerificarContraseniaEmpleado,
												GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panelCentralFormulario.setVerticalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblForRfc)
								.addComponent(txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblForCurp).addComponent(txfCurpEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblForNombreCompleto)
								.addComponent(txfNombreCompletoEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblForAlias)
								.addComponent(txfNombreCortoEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblFNacimiento)
								.addComponent(frmtdtxtfldFechanacimientoempleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmtxfCorreoElectronico, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblEstado)
								.addComponent(txfEstadoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCiudad).addComponent(txfCiudadEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblDireccion)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPaneTxaDireccionEmpleado, GroupLayout.PREFERRED_SIZE, 74,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCodigoPostal)
										.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblClaveContable)
										.addComponent(txfClaveCuentaContable, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 21,
												GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNmerosDeContacto)
								.addComponent(btnAgregarTelefono, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSucursal).addComponent(cmbSucursalEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.PREFERRED_SIZE, 113,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblContrasea)
								.addComponent(passwordFieldContraseniaEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordFieldVerificarContraseniaEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1))
						.addContainerGap(57, Short.MAX_VALUE)));

		this.modelTablaTelefonoEmpleado = this.setModelTablaTelefonos();

		tableNumerosTelefonicos = new JTable();
		tableNumerosTelefonicos.setModel(modelTablaTelefonoEmpleado);
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
			this.listarTelefonoDeEmpleado(idEmpleado);

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
		frmtdtxtfldFechanacimientoempleado.setText(formatearFechaVisible(empleado.getFechaNac()));
		frmtxfCorreoElectronico.setText(empleado.getCorreoElectronico());
		txfEstadoEmpleado.setText(empleado.getEstado());
		txfCiudadEmpleado.setText(empleado.getCiudad());
		textAreaDireccionEmpleado.setText(empleado.getDireccion());
		txfCodigoPostal.setText(empleado.getCodigoPostal());
		txfClaveCuentaContable.setText("");
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
		empleado.setIdSucursal(((JComboboxDataViewModel) cmbSucursalEmpleado.getSelectedItem()).id());
		empleado.setRfc(txfRfcEmpleado.getText().trim());
		empleado.setCurp(txfCurpEmpleado.getText().trim());
		empleado.setNombreCompleto(txfNombreCompletoEmpleado.getText().trim());
		empleado.setNombreCorto(txfNombreCortoEmpleado.getText().trim());
		empleado.setFechaNac(convertirFechaSql(frmtdtxtfldFechanacimientoempleado.getText().trim()));
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
			this.operacionEjecutada = true;
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
		empleado.setIdSucursal(((JComboboxDataViewModel) cmbSucursalEmpleado.getSelectedItem()).id());
		empleado.setRfc(txfRfcEmpleado.getText().trim());
		empleado.setCurp(txfCurpEmpleado.getText().trim());
		empleado.setNombreCompleto(txfNombreCompletoEmpleado.getText().trim());
		empleado.setNombreCorto(txfNombreCortoEmpleado.getText().trim());
		empleado.setFechaNac(convertirFechaSql(frmtdtxtfldFechanacimientoempleado.getText().trim()));
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
			this.operacionEjecutada = true;
			this.dispose();
		}
	}

	private boolean validarCamposVacios() {
		String rfc = txfRfcEmpleado.getText().trim();
		String curp = txfCurpEmpleado.getText().trim();
		String fechaNacimiento = frmtdtxtfldFechanacimientoempleado.getText().trim();

		if (rfc.isEmpty() || curp.isEmpty()
				|| txfNombreCompletoEmpleado.getText().trim().isEmpty()
				|| txfNombreCortoEmpleado.getText().trim().isEmpty()
				|| fechaNacimiento.isEmpty() || fechaNacimiento.contains("_")
				|| frmtxfCorreoElectronico.getText().trim().isEmpty()
				|| txfEstadoEmpleado.getText().trim().isEmpty()
				|| txfCiudadEmpleado.getText().trim().isEmpty()
				|| textAreaDireccionEmpleado.getText().trim().isEmpty()
				|| txfCodigoPostal.getText().trim().isEmpty()
				|| cmbSucursalEmpleado.getSelectedItem() == null) {
			JOptionPane.showMessageDialog(this, "Existen campos obligatorios vacíos", "Validación",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (rfc.length() != 13) {
			JOptionPane.showMessageDialog(this,
					"El RFC del empleado debe contener exactamente 13 caracteres", "Validación",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		if (curp.length() != 18) {
			JOptionPane.showMessageDialog(this,
					"La CURP del empleado debe contener exactamente 18 caracteres", "Validación",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		try {
			LocalDate fecha = LocalDate.parse(fechaNacimiento, FECHA_VISIBLE);
			if (fecha.isAfter(LocalDate.now())) {
				JOptionPane.showMessageDialog(this,
						"La fecha de nacimiento no puede ser posterior a la fecha actual", "Validación",
						JOptionPane.WARNING_MESSAGE);
				return false;
			}
		} catch (DateTimeParseException er) {
			JOptionPane.showMessageDialog(this,
					"La fecha de nacimiento debe tener el formato dd/MM/yyyy y ser una fecha válida", "Validación",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}

		return true;
	}

	/**
	 * Crea la máscara de captura para la fecha de nacimiento.
	 *
	 * @return máscara con formato {@code dd/MM/yyyy}
	 */
	private MaskFormatter crearCampoFechaNacimiento() {
		try {
			MaskFormatter formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
			formatter.setValidCharacters("0123456789");
			return formatter;
		} catch (ParseException er) {
			throw new IllegalStateException("No se pudo configurar el formato de fecha", er);
		}
	}

	/**
	 * Convierte la fecha visible del formulario a {@link Date}.
	 *
	 * @param fechaVisible fecha en formato {@code dd/MM/yyyy}
	 * @return fecha SQL correspondiente
	 * @throws DateTimeParseException si el valor no representa una fecha válida
	 */
	private Date convertirFechaSql(String fechaVisible) {
		return Date.valueOf(LocalDate.parse(fechaVisible, FECHA_VISIBLE));
	}

	/**
	 * Convierte una fecha SQL al formato utilizado por el formulario.
	 *
	 * @param fecha fecha almacenada en la base de datos
	 * @return fecha en formato {@code dd/MM/yyyy}, o una cadena vacía si es
	 *         {@code null}
	 */
	private String formatearFechaVisible(Date fecha) {
		return fecha == null ? "" : fecha.toLocalDate().format(FECHA_VISIBLE);
	}

	private void llenarCmbSucursales() {

		this.cmbSucursalEmpleado.removeAllItems();
		this.cmbSucursalEmpleado.updateUI();

		AppContext.sucursalController.consultarNombreSucursales().forEach(cmbSucursalEmpleado::addItem);

	}

	/**
	 * Indica si el formulario completó correctamente una operación de alta o
	 * actualización de empleado.
	 *
	 * @return {@code true} si la persistencia terminó exitosamente; {@code false}
	 *         si el formulario se cerró sin completar la operación
	 */
	public boolean isOperacionEjecutada() {
		return this.operacionEjecutada;
	}

	private void listarTelefonoDeEmpleado(int idEmpleado) {

		this.modelTablaTelefonoEmpleado.getDataVector().removeAllElements();
		this.tableNumerosTelefonicos.updateUI();

		var list = AppContext.telefonoEmpleadoController.listTelefonoPorIdEmpleado(idEmpleado);

		if (list == null || list.size() <= 0)
			return;

		list.forEach(modelTablaTelefonoEmpleado::addRow);

	}

	private DefaultTableModel setModelTablaTelefonos() {

		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("id");
		model.addColumn("Telefono");

		return model;

	}

	private void createTelefonoEmpleado(int idEmpleado) {
		
		String telefono = JOptionPane.showInputDialog(this, "Indique el número a registrar:", "Telefonos", JOptionPane.INFORMATION_MESSAGE);
		
		if(telefono == null || telefono.length() <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Indique el número telefonico");
		}
		
		var result = AppContext.telefonoEmpleadoController.createTelefonoEmpleado(new TelefonoEmpleado(0, idEmpleado, telefono));
		
		MessageHandler.displayMessage(result.id() == 200 ? MessageHandler.CREATE_SUCCESS_MESSAGE: MessageHandler.ERROR_MESSAGE, this, result.message());
		
		listarTelefonoDeEmpleado(idEmpleado);
		
	}
	
	
	private void eliminarTelefonoEmpleado() {
		
		int idTelefono = DataTools.getIndiceElementoSeleccionado(tableNumerosTelefonicos, modelTablaTelefonoEmpleado, 0);
		
		if(idTelefono < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar un elemento");
			return;
		}
		
		var result = AppContext.telefonoEmpleadoController.deleteTelefonoEmpleado(idTelefono);
		
		MessageHandler.displayMessage(result.id() == 200 ? MessageHandler.DELETE_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE, this, result.message());
		
	}
}
