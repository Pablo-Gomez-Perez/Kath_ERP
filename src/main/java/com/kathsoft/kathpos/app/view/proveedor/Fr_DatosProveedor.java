package com.kathsoft.kathpos.app.view.proveedor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.model.proveedor.Proveedor;
import com.kathsoft.kathpos.app.model.viewmodel.CuentaContableResponseViewModel;
import com.kathsoft.kathpos.app.view.contabilidad.ConsultaCuentaContableDialog;
import com.kathsoft.kathpos.tools.MessageHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

public class Fr_DatosProveedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374525545404673239L;
	private JPanel contentPane;
	private ProveedorController proveedorController = new ProveedorController();
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JComboBox<String> cmbRFCProveedor;
	private JPanel panelInferiorBotones;
	private JButton btn_Cancelar;
	private Component horizontalStrut_10;
	private JButton btn_Guardar;
	private int indiceProveedor;
	private CuentaContableResponseViewModel cuentaContable;
	private boolean operacionEjecutada = false;
	private JLabel lblRfc;
	private JTextField txfRFC;
	private JTextField txfNombre;
	private JTextField txfClaveCtaContable;
	private JTextField txfCorreoElectronico;
	private JTextField txfEstado;
	private JTextField txfCiudad;
	private JTextField textField;
	private JTextField txfDireccion;
	private JTextArea textAreaDescripcion;
	private JScrollPane scrollPaneDescripcion;
	private JLabel lblTelefonos;
	private JButton btnAgregarTelefono;
	private JButton btnEliminarTelefono;
	private JScrollPane scrollPaneTelefonos;

	/**
	 * Create the frame.
	 */
	public Fr_DatosProveedor(int tipoOperacion, int indiceProveedor) {

		this.indiceProveedor = indiceProveedor;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/proveedores.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 434);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panelSuperiorEtiqueta.add(lblNewLabel);

		if (tipoOperacion == 0) {
			this.setTitle("Nuevo Proveedor");
			lblNewLabel.setText("Agregar nuevo Proveedor");
		} else if (tipoOperacion == 1) {
			this.setTitle("Editar Proveedor");
			lblNewLabel.setText("Editar Proveedor");
		}

		panelCentralFormulario = new JPanel();
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		
		lblRfc = new JLabel("RFC");
		
		txfRFC = new JTextField();
		txfRFC.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txfNombre = new JTextField();
		txfNombre.setColumns(10);
		
		JLabel lblCtaContable = new JLabel("Cta. Contable");
		
		txfClaveCtaContable = new JTextField();
		txfClaveCtaContable.setEnabled(false);
		txfClaveCtaContable.setEditable(false);
		txfClaveCtaContable.setColumns(10);
		
		JButton btnFormConsultaCuentaContable = new JButton("");
		btnFormConsultaCuentaContable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormConsultaCuentaContableProveedor();
			}
		});
		btnFormConsultaCuentaContable.setIcon(new ImageIcon(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/cuentas_contables.png")));
		
		JLabel lblMail = new JLabel("Mail");
		
		txfCorreoElectronico = new JTextField();
		txfCorreoElectronico.setColumns(10);
		
		JLabel lblEstado = new JLabel("Estado");
		
		txfEstado = new JTextField();
		txfEstado.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		
		txfCiudad = new JTextField();
		txfCiudad.setColumns(10);
		
		JLabel lblCPostal = new JLabel("C. Postal");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblDireccin = new JLabel("Dirección");
		
		txfDireccion = new JTextField();
		txfDireccion.setColumns(10);
		
		JLabel lblDescripcin = new JLabel("Descripción");
		
		scrollPaneDescripcion = new JScrollPane();
		
		lblTelefonos = new JLabel("Telefonos");
		
		btnAgregarTelefono = new JButton("Nuevo");
		btnAgregarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnAgregarTelefono.setBackground(new Color(0, 255, 51));
		
		btnEliminarTelefono = new JButton("Borrar");
		btnEliminarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEliminarTelefono.setBackground(new Color(255, 102, 102));
		
		scrollPaneTelefonos = new JScrollPane();
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addComponent(lblTelefonos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAgregarTelefono, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
					.addGap(367))
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneTelefonos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
						.addComponent(scrollPaneDescripcion, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblDireccin)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfDireccion, GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblRfc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfRFC, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombre, GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblCtaContable)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfClaveCtaContable, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblMail)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfCorreoElectronico, GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblEstado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfEstado, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCiudad)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfCiudad, GroupLayout.DEFAULT_SIZE, 169, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblCPostal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)))
					.addGap(2))
				.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
					.addComponent(lblDescripcin)
					.addContainerGap())
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRfc)
						.addComponent(txfRFC, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNombre)
						.addComponent(txfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblCtaContable)
							.addComponent(txfClaveCtaContable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnFormConsultaCuentaContable, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMail)
							.addComponent(txfCorreoElectronico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(txfEstado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCiudad)
						.addComponent(txfCiudad, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCPostal)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDireccin)
						.addComponent(txfDireccion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDescripcin)
					.addGap(10)
					.addComponent(scrollPaneDescripcion, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblTelefonos)
							.addComponent(btnAgregarTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTelefonos, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(27, Short.MAX_VALUE))
		);
		
		textAreaDescripcion = new JTextArea();
		scrollPaneDescripcion.setViewportView(textAreaDescripcion);
		panelCentralFormulario.setLayout(gl_panelCentralFormulario);

		panelInferiorBotones = new JPanel();
		panelInferiorBotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		FlowLayout flowLayout_2 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btn_Cancelar.setIcon(
				new ImageIcon(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btn_Cancelar.setBackground(new Color(205, 92, 92));
		panelInferiorBotones.add(btn_Cancelar);

		horizontalStrut_10 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_10);

		btn_Guardar = new JButton("Guardar");
		btn_Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tipoOperacion == 0) {
					// para insertar un nuevo proveedor en la bd
					insertarNuevoProveedor();
				} else if (tipoOperacion == 1) {
					// para actualizar un proveedor existente
					actualizarProveedor();
				}
			}
		});
		btn_Guardar.setBackground(new Color(144, 238, 144));
		btn_Guardar.setIcon(new ImageIcon(
				Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelInferiorBotones.add(btn_Guardar);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(tipoOperacion == 1) {
			this.buscarProveedorPorId();
		}
	}
	
	private boolean validarCamposVacios() {
		String rfc = this.txfRFC.getText() == null ? "" : this.txfRFC.getText().trim();
		String nombre = this.txfNombre.getText() == null ? "" : this.txfNombre.getText().trim();
		String correo = this.txfCorreoElectronico.getText() == null ? "" : this.txfCorreoElectronico.getText().trim();

		if (rfc.isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "El RFC del proveedor es obligatorio");
			return true;
		}

		if (rfc.length() != 10 && rfc.length() != 13) {
			MessageHandler.displayMessage(
					MessageHandler.WARN_MESSAGE,
					this,
					"El RFC del proveedor debe tener exactamente 10 o 13 caracteres"
			);
			return true;
		}

		if (nombre.isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "El nombre del proveedor es obligatorio");
			return true;
		}

		if (this.cuentaContable == null || this.cuentaContable.idCuentaContable() < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar una cuenta contable");
			return true;
		}

		if (correo.isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "El correo electronico del proveedor es obligatorio");
			return true;
		}

		return false;
		
	}

	/**
	 * inserta un nuevo registro en la base de datos
	 */
	private void insertarNuevoProveedor() {

		if (this.validarCamposVacios()) {
			return;
		}

		var response = this.proveedorController.insertarNuevoProveedor(this.buildProveedor());

		MessageHandler.displayMessage(
				response.id() == 200 ? MessageHandler.INSERT_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE,
				this,
				response.message()
		);

		if (response.id() == 200) {
			this.operacionEjecutada = true;
			this.cerrarForm();
		}

	}

	private void actualizarProveedor() {

		
	}

	/**
	 * Construye una entidad {@link Proveedor} con los datos capturados en el formulario.
	 *
	 * @return proveedor construido para alta o actualizacion
	 */
	private Proveedor buildProveedor() {
		return new Proveedor.ProveedorBuilder()
				.idProveedor(this.indiceProveedor)
				.idCuentaContable(this.cuentaContable.idCuentaContable())
				.rfc(this.txfRFC.getText().trim().toUpperCase())
				.nombre(this.txfNombre.getText().trim())
				.descripcion(this.textAreaDescripcion.getText().trim())
				.correoElectronico(this.txfCorreoElectronico.getText().trim())
				.estado(this.txfEstado.getText().trim())
				.ciudad(this.txfCiudad.getText().trim())
				.direccion(this.txfDireccion.getText().trim())
				.codigoPostal(this.textField.getText().trim())
				.activo(true)
				.build();
	}

	/**
	 * Indica si el formulario ejecuto una operacion de base de datos correctamente.
	 *
	 * @return {@code true} si la operacion fue exitosa; {@code false} en caso contrario
	 */
	public boolean isOperacionEjecutada() {
		return this.operacionEjecutada;
	}

	/**
	 * busca en la bse de datos el registro de un proveedor en base a su RFC
	 * 
	 * @param rfc
	 */
	private void buscarProveedorPorRFC(String rfc) {

		

	}
	
	private void buscarProveedorPorId() {
		
		
		
	}

	/**
	 * Abre el dialogo de consulta de cuentas contables y asigna la cuenta
	 * seleccionada al proveedor.
	 *
	 * <p>
	 * Si no se selecciona una cuenta valida, no modifica el campo contable.
	 * </p>
	 *
	 * @see ConsultaCuentaContableDialog
	 * @see CuentaContableResponseViewModel
	 */
	private void abrirFormConsultaCuentaContableProveedor() {

		ConsultaCuentaContableDialog dialog = new ConsultaCuentaContableDialog(this);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);

		this.cuentaContable = dialog.getCuentaContable();

		if (this.cuentaContable.idCuentaContable() < 0) {
			return;
		}

		this.txfClaveCtaContable.setText(this.cuentaContable.claveCuentaContable());

	}

	/**
	 * llena el combobox de los RFC de los proveedores con sus datos
	 * correspondientes consultando la base de datos
	 */
	private void llenarCmbRfcProveedor() {
		this.limpiarCmbProveedor();
		proveedorController.consultarRFCProveedor(this.cmbRFCProveedor);
	}

	/**
	 * borra todos los items o elementos contenidos en el ComboBox de RFC de los
	 * proveedores
	 */
	private void limpiarCmbProveedor() {
		this.cmbRFCProveedor.removeAllItems();
		this.cmbRFCProveedor.updateUI();
	}

	/**
	 * cierra el formulario
	 */
	private void cerrarForm() {
		this.dispose();
	}
}