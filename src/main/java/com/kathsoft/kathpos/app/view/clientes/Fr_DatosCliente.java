package com.kathsoft.kathpos.app.view.clientes;

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
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.controller.TipoClienteController;
import com.kathsoft.kathpos.app.model.ClienteById;
import com.kathsoft.kathpos.app.model.cliente.Clientes;
import com.kathsoft.kathpos.app.model.cliente.TipoCliente;
import com.kathsoft.kathpos.app.model.viewmodel.CuentaContableResponseViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.app.view.contabilidad.ConsultaCuentaContableDialog;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fr_DatosCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4955801027954256510L;
	private static final DateTimeFormatter FECHA_VISIBLE = DateTimeFormatter.ofPattern("dd/MM/uuuu");
	private static final DateTimeFormatter FECHA_SQL = DateTimeFormatter.ofPattern("uuuu-MM-dd");
	/**
	 * 
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private Component horizontalStrut;
	private JButton btnGuardar;
	private int indiceCliente;
	private Component horizontalStrut_15;
	private JButton btnHistorialCred;
	private JTextField txfRfcCliente;
	private JTextField txfNombreCompleto;
	private JTextField txfNombreCorto;
	private JTextField txfCtaContable;
	private JTextField txfCodigoPostal;
	private JTextField txfCorreoElectronico;
	private JTextField txfEstado;
	private JTextField txfCiudad;
	private JScrollPane scrollPaneNumerosTelefonicos;
	private JTable tablaNumerosTelefonicos;
	private JLabel lblRfc;
	private JLabel lblNombre;
	private JLabel lblAlias;
	private JLabel lblCtaContable;
	private JButton btnFormConsultaCuentaContable;
	private JLabel lblCategoria;
	private JComboBox<JComboboxDataViewModel> comboBoxTipoCliente;
	private JLabel lblFNacimiento;
	private JFormattedTextField frmtdtxtfldFechaNacimiento;
	private JLabel lblMail;
	private JLabel lblCPostal;
	private JLabel lblEstado;
	private JLabel lblCiudad;
	private JLabel lblDireccion;
	private JTextArea txaDireccionCliente;
	private FlowLayout flowLayout;
	private int tipoOperacion;
	private JButton btnAgregarTelefono;
	private JButton btnEliminarTelefono;
	private CuentaContableResponseViewModel cuentaContable;
	private boolean operacionEjecutada = false;

	/**
	 * Create the frame.
	 */
	public Fr_DatosCliente(int tipoOperacion, int indiceCliente) {

		this.tipoOperacion = tipoOperacion;
		this.indiceCliente = indiceCliente;

		setIconImage(Toolkit.getDefaultToolkit().getImage(Fr_DatosCliente.class.getResource(
				"/com/kathsoft/kathpos/app/assets/pngtree-call-center-customer-icon-png-image_4746069.jpg")));

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 512);
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

		lblRfc = new JLabel("RFC");

		txfRfcCliente = new JTextField();
		txfRfcCliente.setColumns(10);

		lblNombre = new JLabel("Nombre");

		txfNombreCompleto = new JTextField();
		txfNombreCompleto.setColumns(10);

		lblAlias = new JLabel("Alias");

		txfNombreCorto = new JTextField();
		txfNombreCorto.setColumns(10);

		lblCtaContable = new JLabel("Cta. Contable");

		txfCtaContable = new JTextField();
		txfCtaContable.setColumns(10);

		btnFormConsultaCuentaContable = new JButton("");
		btnFormConsultaCuentaContable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirFormConsultaCuentaContablCliente();

			}
		});
		btnFormConsultaCuentaContable.setIcon(new ImageIcon(
				Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/assets/cuentas_contables.png")));

		lblCategoria = new JLabel("Categoria");

		comboBoxTipoCliente = new JComboBox<>();

		lblFNacimiento = new JLabel("F. Nacimiento");

		MaskFormatter formatter = this.crearCampoFechaNacimiento();
		frmtdtxtfldFechaNacimiento = new JFormattedTextField(formatter);
		frmtdtxtfldFechaNacimiento.setFocusLostBehavior(JFormattedTextField.PERSIST);

		lblCPostal = new JLabel("C. postal");

		txfCodigoPostal = new JTextField();
		txfCodigoPostal.setColumns(10);

		lblMail = new JLabel("Mail");

		txfCorreoElectronico = new JTextField();
		txfCorreoElectronico.setColumns(10);

		lblEstado = new JLabel("Estado");

		txfEstado = new JTextField();
		txfEstado.setColumns(10);

		lblCiudad = new JLabel("Ciudad");

		txfCiudad = new JTextField();
		txfCiudad.setColumns(10);

		lblDireccion = new JLabel("Direccion");

		JScrollPane scrollPaneDireccionCliente = new JScrollPane();

		JLabel lblTelefonos = new JLabel("Telefonos");

		scrollPaneNumerosTelefonicos = new JScrollPane();

		btnAgregarTelefono = new JButton("Nuevo");
		btnAgregarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAgregarTelefono.setBackground(new Color(0, 255, 51));

		btnEliminarTelefono = new JButton("Borrar");
		btnEliminarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEliminarTelefono.setBackground(new Color(255, 102, 102));
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblRfc)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfRfcCliente, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNombre)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfNombreCompleto, GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addGroup(gl_panelCentralFormulario
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblCategoria)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(comboBoxTipoCliente, 0, 129, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblFNacimiento)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(frmtdtxtfldFechaNacimiento, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblCPostal)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txfCodigoPostal, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblAlias)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txfNombreCorto, GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblCtaContable)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txfCtaContable, GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 68,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap())
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblMail)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfCorreoElectronico, GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblEstado)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfEstado, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblCiudad)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfCiudad, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE).addContainerGap())
				.addComponent(scrollPaneDireccionCliente, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addComponent(lblTelefonos)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnAgregarTelefono, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
						.addGap(367))
				.addGroup(
						gl_panelCentralFormulario.createSequentialGroup().addComponent(lblDireccion).addContainerGap())
				.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE));
		gl_panelCentralFormulario.setVerticalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE).addComponent(lblRfc)
								.addComponent(txfRfcCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre).addComponent(txfNombreCompleto, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAlias)
										.addComponent(txfNombreCorto, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCtaContable).addComponent(txfCtaContable,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblCategoria)
										.addComponent(comboBoxTipoCliente, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblFNacimiento)
										.addComponent(frmtdtxtfldFechaNacimiento, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCPostal)
										.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(
								gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE).addComponent(lblMail)
										.addComponent(txfCorreoElectronico, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblEstado)
										.addComponent(txfEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCiudad).addComponent(txfCiudad, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblDireccion)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPaneDireccionCliente, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblTelefonos).addComponent(btnAgregarTelefono,
												GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
								.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
						.addContainerGap()));

		tablaNumerosTelefonicos = new JTable();
		scrollPaneNumerosTelefonicos.setViewportView(tablaNumerosTelefonicos);

		txaDireccionCliente = new JTextArea();
		scrollPaneDireccionCliente.setViewportView(txaDireccionCliente);
		panelCentralFormulario.setLayout(gl_panelCentralFormulario);

		panelInferiorBotones = new JPanel();
		flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btnHistorialCred = new JButton("Historial");
		btnHistorialCred.setBackground(new Color(0, 255, 255));
		btnHistorialCred.setIcon(
				new ImageIcon(Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/assets/creditoLogo2.png")));
		panelInferiorBotones.add(btnHistorialCred);

		horizontalStrut_15 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_15);

		btnCancelar = new JButton("Cancelar");

		btnCancelar.setBackground(new Color(205, 92, 92));
		btnCancelar.setIcon(
				new ImageIcon(Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		panelInferiorBotones.add(btnCancelar);

		horizontalStrut = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipoOperacion == 1) {
					insertarNuevoCliente();
				} else {
					actualizarCliente();
				}
			}
		});

		btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.setIcon(
				new ImageIcon(Fr_DatosCliente.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelInferiorBotones.add(btnGuardar);

		this.llenarCmbTipoCliente();

		if (this.tipoOperacion != 1) {
			this.consultarClientePorId();
		}

	}

	private MaskFormatter crearCampoFechaNacimiento() {
		try {
			MaskFormatter maskFormatter = new MaskFormatter("##/##/####");
			maskFormatter.setPlaceholderCharacter('_');
			return maskFormatter;
		} catch (ParseException er) {
			throw new IllegalStateException("No se pudo crear la máscara de fecha", er);
		}
	}

	private void llenarCmbTipoCliente() {
		this.comboBoxTipoCliente.removeAllItems();
		Vector<TipoCliente> tipos = AppContext.tipoClienteController.cmbTipoCliente();
		tipos.forEach(tipo -> this.comboBoxTipoCliente
				.addItem(new JComboboxDataViewModel(tipo.getIdTipoCliente(), tipo.getNombre())));
		this.comboBoxTipoCliente.updateUI();
	}

	private void consultarClientePorId() {
		try {
			ClienteById cl = AppContext.clientesController.buscarClientePorId(this.indiceCliente);
			if (cl == null) {
				MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, "No se pudo cargar el cliente");
				return;
			}

			this.indiceCliente = cl.getIdCliente();
			this.txfRfcCliente.setText(cl.getRfc());
			this.txfCtaContable.setText(cl.getClaveCuentaContable());
			this.txfNombreCompleto.setText(cl.getNombreCompleto());
			this.txfNombreCorto.setText(cl.getNombreCorto());
			this.frmtdtxtfldFechaNacimiento.setText(formatearFechaVisible(cl.getFechaNac()));
			this.txfCorreoElectronico.setText(cl.getCorreoElectronico());
			this.txfEstado.setText(cl.getEstado());
			this.txfCiudad.setText(cl.getCiudad());
			this.txfCodigoPostal.setText(cl.getCodigoPostal());
			this.txaDireccionCliente.setText(cl.getDireccion());
			seleccionarTipoCliente(cl.getIdTipoCliente());
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void seleccionarTipoCliente(int idTipoCliente) {
		for (int i = 0; i < this.comboBoxTipoCliente.getItemCount(); i++) {
			JComboboxDataViewModel item = this.comboBoxTipoCliente.getItemAt(i);
			if (item.id() == idTipoCliente) {
				this.comboBoxTipoCliente.setSelectedIndex(i);
				return;
			}
		}
	}

	private void insertarNuevoCliente() {
		if (validarCamposVacios()) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Existen campos obligatorios vacios o error en formato de entrada");
			return;
		}

		try {
			Clientes cl = construirCliente();
			SpResponseModel respuesta = AppContext.clientesController.insertarNuevoCliente(cl);
			MessageHandler.displayMessage(
					respuesta.id() == 200 ? MessageHandler.INSERT_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE, this,
					respuesta.message());

			if (respuesta.id() == 200) {
				this.operacionEjecutada = true;
				this.dispose();
			}

		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void actualizarCliente() {
		if (validarCamposVacios()) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Existen campos obligatorios vacios o error en formato de entrada");
			return;
		}

		try {
			Clientes cl = construirCliente();
			cl.setId(this.indiceCliente);
			SpResponseModel respuesta = AppContext.clientesController.actualizarCliente(cl);
			MessageHandler.displayMessage(
					respuesta.id() == 200 ? MessageHandler.UPDATE_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE, this,
					respuesta.message());
			
			if (respuesta.id() == 200) {
				this.operacionEjecutada = true;
				this.dispose();
			}
			
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private Clientes construirCliente() {
		Clientes cl = new Clientes();
		cl.setRfc(this.txfRfcCliente.getText().trim());
		cl.setNombreCompleto(this.txfNombreCompleto.getText().trim());
		cl.setNombreCorto(this.txfNombreCorto.getText().trim());
		cl.setIdCuentaContable(this.cuentaContable != null ? this.cuentaContable.idCuentaContable() : 0);
		cl.setClaveCuentaContable(this.txfCtaContable.getText().trim());
		cl.setFechaNac(convertirFechaSql(this.frmtdtxtfldFechaNacimiento.getText().trim()));
		cl.setCorreoElectronico(this.txfCorreoElectronico.getText().trim());
		cl.setEstado(this.txfEstado.getText().trim());
		cl.setCiudad(this.txfCiudad.getText().trim());
		cl.setCodigoPostal(this.txfCodigoPostal.getText().trim());
		cl.setDireccion(this.txaDireccionCliente.getText().trim());
		cl.setIdTipoCliente(obtenerTipoClienteSeleccionado());
		cl.setActivo(true);
		return cl;
	}

	private int obtenerTipoClienteSeleccionado() {
		JComboboxDataViewModel item = (JComboboxDataViewModel) this.comboBoxTipoCliente.getSelectedItem();
		return item == null ? -1 : item.id();
	}

	private java.sql.Date convertirFechaSql(String fechaVisible) {
		LocalDate localDate = LocalDate.parse(fechaVisible, FECHA_VISIBLE);
		return java.sql.Date.valueOf(localDate.format(FECHA_SQL));
	}

	private String formatearFechaVisible(java.sql.Date fecha) {
		return fecha == null ? "" : fecha.toLocalDate().format(FECHA_VISIBLE);
	}

	private boolean validarCamposVacios() {
		if (this.txfRfcCliente.getText().trim().isEmpty()) {
			return true;
		}
		if (this.txfNombreCompleto.getText().trim().isEmpty()) {
			return true;
		}
		if (this.txfNombreCorto.getText().trim().isEmpty()) {
			return true;
		}
		if (this.frmtdtxtfldFechaNacimiento.getText().trim().isEmpty()
				|| this.frmtdtxtfldFechaNacimiento.getText().contains("_")) {
			return true;
		}
		if (this.comboBoxTipoCliente.getSelectedItem() == null) {
			return true;
		}
		if (this.txfCorreoElectronico.getText().trim().isEmpty()) {
			return true;
		}
		if (this.txfEstado.getText().trim().isEmpty()) {
			return true;
		}
		if (this.txfCiudad.getText().trim().isEmpty()) {
			return true;
		}
		if (this.txfCodigoPostal.getText().trim().isEmpty()) {
			return true;
		}
		if (this.txaDireccionCliente.getText().trim().isEmpty()) {
			return true;
		}

		try {
			LocalDate.parse(this.frmtdtxtfldFechaNacimiento.getText().trim(), FECHA_VISIBLE);
			return false;
		} catch (DateTimeParseException er) {
			return true;
		}
	}

	private void abrirFormConsultaCuentaContablCliente() {

		ConsultaCuentaContableDialog dialog = new ConsultaCuentaContableDialog(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		this.cuentaContable = dialog.getCuentaContable();

		if (cuentaContable.idCuentaContable() < 0)
			return;

		this.txfCtaContable.setText(this.cuentaContable.claveCuentaContable());

	}

	public boolean isOperacionEjecutada() {
		return operacionEjecutada;
	}

}
