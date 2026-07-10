package com.kathsoft.kathpos.app.view.contabilidad;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.viewmodel.CuentaContableFormDetails;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.tools.AppContext;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.RenderingHints.Key;

import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fr_DatosCuentasContables extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorbotones;
	private JLabel lblNewLabel;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnGuardar;
	private JButton btnEliminar;
	private ButtonGroup btngTipoDeCuentaOperativo;
	private JPanel panelDatosDeCuenta;
	private JPanel panelDatosCuentaSuperior;
	private JLabel lblClave;
	private JLabel lblNombre;
	private JTextField txfNombreCuentaContable;
	private JFormattedTextField frmTxfClaveCuentaContable;
	private JTextField txfNivelCuentaContable;
	private JRadioButton jrdbtnCuentaDeDetalle;
	private JRadioButton jrdbtnCuentaAgrupadora;
	private GroupLayout gl_panelDetalleOperativoCuentaContable;
	private JLabel lblDescripcin;
	private JScrollPane scrollPane;
	private JTextArea txaDescripcionCuentaContable;
	private FlowLayout flowLayout_1;
	private JLabel lblGrupo;
	private JLabel lblClave_1;
	private JTextField txfNombreCuentaContableSuperior;
	private JFormattedTextField frmTxfClaveCuentaContableSuperior;
	private JLabel lblNombre_1;
	private JPanel panelDescripcionCuentaContableSuperior;
	private GroupLayout gl_panelDatosCuentaSuperior;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPane_1;
	private JTextArea txaDescripcionCuentaContableSuperior;
	private JPanel panelDescripcionCuentaContable;
	private JLabel lblNivel;
	private JPanel panelDetalleOperativoCuentaContable;
	private JComboBox<JComboboxDataViewModel> cmbGrupoCuentaContable;
	private JComboBox<JComboboxDataViewModel> cmbRubroCuentaContable;
	private GroupLayout gl_panelCentralFormulario;
	private JLabel lblRubro;
	private GroupLayout gl_panelDatosDeCuenta;
	private CuentaContableFormDetails ultimaCuentaContable;
	private final int opcionOperacion;
	private final int idCuentaEnEdicion;
	private final Runnable onCambios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_DatosCuentasContables frame = new Fr_DatosCuentasContables(1, 0, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * @param opcion
	 * @param idCuenta
	 */
	public Fr_DatosCuentasContables(int opcion, int idCuenta) {
		this(opcion, idCuenta, null, null);
	}

	public Fr_DatosCuentasContables(int opcion, int idCuenta, String claveCuenta) {
		this(opcion, idCuenta, claveCuenta, null);
	}

	public Fr_DatosCuentasContables(int opcion, int idCuenta, String claveCuenta, Runnable onCambios) {
		/*
		 * TODO Agregar JCombobox para filtrado de Rubro contable en base a grupo
		 * contable
		 */

		this.opcionOperacion = opcion;
		this.idCuentaEnEdicion = idCuenta;
		this.onCambios = onCambios;

		setTitle("Cuenta contable");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(255, 215, 0));
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelPrincipal);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		panelPrincipal.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelPrincipal.add(panelCentralFormulario, BorderLayout.CENTER);

		panelDatosDeCuenta = new JPanel();
		panelDatosDeCuenta.setBackground(new Color(255, 215, 0));
		panelDatosDeCuenta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la cuenta",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelDatosCuentaSuperior = new JPanel();
		panelDatosCuentaSuperior.setBackground(new Color(255, 215, 0));
		panelDatosCuentaSuperior.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cuenta Superior",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelDatosCuentaSuperior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										564, Short.MAX_VALUE)
								.addComponent(panelDatosDeCuenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelCentralFormulario.setVerticalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addContainerGap()
						.addComponent(panelDatosDeCuenta, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelDatosCuentaSuperior, GroupLayout.PREFERRED_SIZE, 140, Short.MAX_VALUE)
						.addContainerGap()));

		lblClave_1 = new JLabel("Clave");

		frmTxfClaveCuentaContableSuperior = new JFormattedTextField();

		lblNombre_1 = new JLabel("Nombre");

		txfNombreCuentaContableSuperior = new JTextField();
		txfNombreCuentaContableSuperior.setColumns(10);

		panelDescripcionCuentaContableSuperior = new JPanel();
		gl_panelDatosCuentaSuperior = new GroupLayout(panelDatosCuentaSuperior);
		gl_panelDatosCuentaSuperior.setHorizontalGroup(gl_panelDatosCuentaSuperior
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDatosCuentaSuperior.createSequentialGroup().addGroup(gl_panelDatosCuentaSuperior
						.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelDescripcionCuentaContableSuperior, Alignment.LEADING,
								GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
						.addGroup(gl_panelDatosCuentaSuperior.createSequentialGroup().addComponent(lblClave_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(frmTxfClaveCuentaContableSuperior, GroupLayout.PREFERRED_SIZE, 116,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNombre_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(txfNombreCuentaContableSuperior, GroupLayout.DEFAULT_SIZE, 308,
										Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panelDatosCuentaSuperior.setVerticalGroup(gl_panelDatosCuentaSuperior.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosCuentaSuperior.createSequentialGroup()
						.addGroup(gl_panelDatosCuentaSuperior.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblClave_1)
								.addComponent(frmTxfClaveCuentaContableSuperior, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNombre_1).addComponent(txfNombreCuentaContableSuperior,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelDescripcionCuentaContableSuperior, GroupLayout.PREFERRED_SIZE, 104,
								GroupLayout.PREFERRED_SIZE)
						.addContainerGap(24, Short.MAX_VALUE)));
		panelDescripcionCuentaContableSuperior.setLayout(new BorderLayout(0, 0));

		lblNewLabel_1 = new JLabel("Descripción");
		panelDescripcionCuentaContableSuperior.add(lblNewLabel_1, BorderLayout.NORTH);

		scrollPane_1 = new JScrollPane();
		panelDescripcionCuentaContableSuperior.add(scrollPane_1, BorderLayout.CENTER);

		txaDescripcionCuentaContableSuperior = new JTextArea();
		scrollPane_1.setViewportView(txaDescripcionCuentaContableSuperior);
		panelDatosCuentaSuperior.setLayout(gl_panelDatosCuentaSuperior);

		lblClave = new JLabel("Clave");

		lblNombre = new JLabel("Nombre");

		txfNombreCuentaContable = new JTextField();
		txfNombreCuentaContable.setColumns(10);

		frmTxfClaveCuentaContable = this.crearCampoClave();
		this.frmTxfClaveCuentaContable.setColumns(19);
		this.configurarFormatoClave(this.frmTxfClaveCuentaContable);
		this.frmTxfClaveCuentaContable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarCuentaSuperiorPorClave();
			}
		});

		panelDescripcionCuentaContable = new JPanel();

		lblNivel = new JLabel("Nivel");

		txfNivelCuentaContable = new JTextField();
		txfNivelCuentaContable.setColumns(10);

		panelDetalleOperativoCuentaContable = new JPanel();
		panelDetalleOperativoCuentaContable.setBackground(new Color(255, 215, 0));
		panelDetalleOperativoCuentaContable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)),
				"Tipo Operativo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		lblGrupo = new JLabel("Grupo");

		cmbGrupoCuentaContable = new JComboBox<JComboboxDataViewModel>();
		cmbGrupoCuentaContable.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				llenarCmbRubroCuentaContable(((JComboboxDataViewModel) cmbGrupoCuentaContable.getSelectedItem()).id());
			}
		});

		lblRubro = new JLabel("Rubro");

		cmbRubroCuentaContable = new JComboBox<>();

		gl_panelDatosDeCuenta = new GroupLayout(panelDatosDeCuenta);
		gl_panelDatosDeCuenta.setHorizontalGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup().addComponent(lblNivel)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfNivelCuentaContable, GroupLayout.PREFERRED_SIZE, 50,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblGrupo)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cmbGrupoCuentaContable, GroupLayout.PREFERRED_SIZE, 118,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblRubro)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(cmbRubroCuentaContable, 0, 209, Short.MAX_VALUE).addContainerGap())
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup()
						.addComponent(panelDescripcionCuentaContable, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(panelDetalleOperativoCuentaContable,
								GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup()
						.addComponent(lblClave, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(frmTxfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, 120,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblNombre)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(txfNombreCuentaContable, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panelDatosDeCuenta.setVerticalGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.BASELINE).addComponent(lblClave)
								.addComponent(lblNombre)
								.addComponent(txfNombreCuentaContable, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(frmTxfClaveCuentaContable, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelDetalleOperativoCuentaContable, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addComponent(panelDescripcionCuentaContable, Alignment.LEADING,
										GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED).addGroup(
								gl_panelDatosDeCuenta.createParallelGroup(Alignment.BASELINE).addComponent(lblNivel)
										.addComponent(txfNivelCuentaContable, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblGrupo)
										.addComponent(cmbGrupoCuentaContable, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblRubro).addComponent(cmbRubroCuentaContable,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))));

		jrdbtnCuentaDeDetalle = new JRadioButton("Cuenta de detalle");
		jrdbtnCuentaDeDetalle.setBackground(new Color(255, 215, 0));

		jrdbtnCuentaAgrupadora = new JRadioButton("Cuenta Agrupadora");
		jrdbtnCuentaAgrupadora.setBackground(new Color(255, 215, 0));
		gl_panelDetalleOperativoCuentaContable = new GroupLayout(panelDetalleOperativoCuentaContable);
		gl_panelDetalleOperativoCuentaContable
				.setHorizontalGroup(gl_panelDetalleOperativoCuentaContable.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDetalleOperativoCuentaContable.createSequentialGroup()
								.addGroup(gl_panelDetalleOperativoCuentaContable.createParallelGroup(Alignment.LEADING)
										.addComponent(jrdbtnCuentaDeDetalle).addComponent(jrdbtnCuentaAgrupadora))
								.addContainerGap(9, Short.MAX_VALUE)));
		gl_panelDetalleOperativoCuentaContable
				.setVerticalGroup(gl_panelDetalleOperativoCuentaContable.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDetalleOperativoCuentaContable.createSequentialGroup()
								.addComponent(jrdbtnCuentaDeDetalle).addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(jrdbtnCuentaAgrupadora).addContainerGap(29, Short.MAX_VALUE)));
		panelDetalleOperativoCuentaContable.setLayout(gl_panelDetalleOperativoCuentaContable);
		panelDescripcionCuentaContable.setLayout(new BorderLayout(0, 0));

		this.btngTipoDeCuentaOperativo = new ButtonGroup();
		this.btngTipoDeCuentaOperativo.add(this.jrdbtnCuentaDeDetalle);
		this.btngTipoDeCuentaOperativo.add(this.jrdbtnCuentaAgrupadora);

		lblDescripcin = new JLabel("Descripción");
		panelDescripcionCuentaContable.add(lblDescripcin, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		panelDescripcionCuentaContable.add(scrollPane, BorderLayout.CENTER);

		txaDescripcionCuentaContable = new JTextArea();
		scrollPane.setViewportView(txaDescripcionCuentaContable);
		panelDatosDeCuenta.setLayout(gl_panelDatosDeCuenta);
		panelCentralFormulario.setLayout(gl_panelCentralFormulario);

		// this.horizontalBox_1.add(horizontalBox);
		// this.horizontalBox_1.add(verticalBox);

		panelInferiorbotones = new JPanel();
		flowLayout_1 = (FlowLayout) panelInferiorbotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelInferiorbotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.panelInferiorbotones.setBackground(new Color(30, 144, 255));
		panelPrincipal.add(panelInferiorbotones, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cerrarForm();
			}
		});
		btnCancelar.setIcon(new ImageIcon(
				Fr_DatosCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnCancelar.setBackground(new Color(205, 92, 92));
		panelInferiorbotones.add(btnCancelar);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelInferiorbotones.add(horizontalStrut_2);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setIcon(new ImageIcon(
				Fr_DatosCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarCuentaContable();
			}
		});

		panelInferiorbotones.add(btnGuardar);

		if (opcion == 2) {
			btnEliminar = new JButton("Eliminar");
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					eliminarCuentaContable();
				}
			});
			btnEliminar.setBackground(new Color(255, 51, 0));
			btnEliminar.setIcon(new ImageIcon(Fr_DatosCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
			panelInferiorbotones.add(btnEliminar);
		}

		this.llenarCmbGrupoContable();

		if (opcion == 1) {
			this.lblNewLabel.setText("Registrar cuenta");
			this.cmbGrupoCuentaContable.setSelectedIndex(0);
			this.llenarCmbRubroCuentaContable(
					((JComboboxDataViewModel) this.cmbGrupoCuentaContable.getSelectedItem()).id());
			this.txfNivelCuentaContable.setEditable(false);
			this.txfNivelCuentaContable.setText("0");
			this.jrdbtnCuentaAgrupadora.setSelected(true);
			this.btnGuardar.setText("Guardar");
		} else {
			this.lblNewLabel.setText("Modificar cuenta");
			this.btnGuardar.setText("Actualizar");
			this.cargarCuentaParaEdicion(claveCuenta);

		}

		this.setBounds(100, 100, 600, 530);
	}
	
	/**
	 * Crea el campo para capturar claves contables.
	 *
	 * @return campo configurado para claves contables
	 */
	private JFormattedTextField crearCampoClave() {

		JFormattedTextField campo = new JFormattedTextField();		

		campo.setColumns(19);
		campo.setFocusLostBehavior(JFormattedTextField.PERSIST);

		campo.setInputVerifier(new InputVerifier() {

			@Override
			public boolean verify(JComponent input) {

				String clave = ((JFormattedTextField) input).getText().trim();

				return clave.isEmpty()
						|| clave.matches("\\d{4}(-\\d{4}){0,3}");
			}
		});

		return campo;
	}
	
	/**
	 * Configura el formato automático de una clave contable.
	 *
	 * @param campo campo donde se captura la clave
	 */
	private void configurarFormatoClave(JFormattedTextField campo) {

		((AbstractDocument) campo.getDocument()).setDocumentFilter(new DocumentFilter() {

			@Override
			public void insertString(
					FilterBypass fb,
					int offset,
					String text,
					AttributeSet attr) throws BadLocationException {

				replace(fb, offset, 0, text, attr);
			}

			@Override
			public void replace(
					FilterBypass fb,
					int offset,
					int length,
					String text,
					AttributeSet attrs) throws BadLocationException {

				String actual = fb.getDocument()
						.getText(0, fb.getDocument().getLength());

				StringBuilder nuevo = new StringBuilder(actual);
				nuevo.replace(offset, offset + length, text == null ? "" : text);

				String soloDigitos = nuevo.toString().replaceAll("\\D", "");

				if (soloDigitos.length() > 16) {
					soloDigitos = soloDigitos.substring(0, 16);
				}

				StringBuilder formateado = new StringBuilder();

				for (int i = 0; i < soloDigitos.length(); i++) {

					if (i > 0 && i % 4 == 0) {
						formateado.append("-");
					}

					formateado.append(soloDigitos.charAt(i));
				}

				fb.replace(
						0,
						fb.getDocument().getLength(),
						formateado.toString(),
						attrs
				);
			}
		});
	}

	private void llenarCmbGrupoContable() {

		this.cmbGrupoCuentaContable.removeAllItems();
		this.cmbGrupoCuentaContable.updateUI();

		AppContext.cuentaContableController.listCmbGrupoCuentasContables().forEach(i -> {
			cmbGrupoCuentaContable.addItem(i);
		});

	}

	private void llenarCmbRubroCuentaContable(int idGrupo) {

		this.cmbRubroCuentaContable.removeAllItems();
		this.cmbRubroCuentaContable.updateUI();

		AppContext.cuentaContableController.listCmbRubroCuentasContables(idGrupo).forEach(i -> {
			cmbRubroCuentaContable.addItem(i);
		});

	}

	/**
	 * Carga datos de cuenta para edición.
	 *
	 * @param claveCuenta clave actual del registro
	 */
	private void cargarCuentaParaEdicion(String claveCuenta) {

		CuentaContableFormDetails data = null;

		if (claveCuenta != null && !claveCuenta.isBlank()) {
			data = this.mapCuentaContable(claveCuenta);
		}

		if (data == null && this.idCuentaEnEdicion > 0) {
			var cuenta = AppContext.cuentaContableController.buscarCuentaPorId(this.idCuentaEnEdicion);
			if (cuenta != null) {
				this.txfNombreCuentaContable.setText(cuenta.getNombre());
				this.txaDescripcionCuentaContable.setText(cuenta.getDescripcion());
			}
			return;
		}

		if (data == null) {
			return;
		}

		this.ultimaCuentaContable = data;
		this.setSelectedItemById(this.cmbGrupoCuentaContable, data.fkIdGrupoContable());
		this.setSelectedItemById(this.cmbRubroCuentaContable, data.fkIdRubro());
		this.frmTxfClaveCuentaContableSuperior.setText(data.clave());
		this.txfNombreCuentaContableSuperior.setText(data.nombre());
		this.txaDescripcionCuentaContableSuperior.setText(data.descripcion());
		this.txfNivelCuentaContable.setText(String.valueOf(data.nivel() + 1));
		this.frmTxfClaveCuentaContable.setText(data.clave());
		this.txfNombreCuentaContable.setText(data.nombre());
		this.txaDescripcionCuentaContable.setText(data.descripcion());
	}


	/**
	 * Guarda o actualiza cuenta contable.
	 */
	private void guardarCuentaContable() {

		String clave = this.frmTxfClaveCuentaContable.getText().trim();
		String nombre = this.txfNombreCuentaContable.getText().trim();
		String descripcion = this.txaDescripcionCuentaContable.getText().trim();
		JComboboxDataViewModel rubro = (JComboboxDataViewModel) this.cmbRubroCuentaContable.getSelectedItem();

		if (clave.isEmpty() || nombre.isEmpty() || rubro == null) {
			JOptionPane.showMessageDialog(this, "No deje campos vacíos", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		boolean esDetalle = this.jrdbtnCuentaDeDetalle.isSelected();
		String resultado;

		if (this.opcionOperacion == 1) {
			if (this.ultimaCuentaContable == null) {
				JOptionPane.showMessageDialog(this, "Debe capturar una cuenta superior válida", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			resultado = AppContext.cuentaContableController.insertarCuentaContable(
					this.ultimaCuentaContable.idCuenta(),
					rubro.id(),
					clave,
					nombre,
					descripcion,
					esDetalle);
		} else {
			resultado = AppContext.cuentaContableController.actualizarCuentaContable(
					this.idCuentaEnEdicion,
					clave,
					nombre,
					descripcion,
					esDetalle,
					true);
		}

		if (resultado != null && !resultado.isBlank()
				&& !"Cuenta contable registrada correctamente".equalsIgnoreCase(resultado)
				&& !"Cuenta contable actualizada correctamente".equalsIgnoreCase(resultado)) {
			JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, this.opcionOperacion == 1 ? "Registro almacenado con éxito" : "Registro actualizado con éxito",
				this.opcionOperacion == 1 ? "Registrar cuenta" : "Actualizar cuenta", JOptionPane.INFORMATION_MESSAGE);
		if (this.onCambios != null) {
			this.onCambios.run();
		}
		this.dispose();
	}

	/**
	 * Elimina cuenta contable actual.
	 */
	private void eliminarCuentaContable() {

		if (this.opcionOperacion != 2 || this.idCuentaEnEdicion <= 0) {
			return;
		}

		int option = JOptionPane.showConfirmDialog(this, "¿Eliminar cuenta contable?", "Eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (option != JOptionPane.YES_OPTION) {
			return;
		}

		String resultado = AppContext.cuentaContableController.eliminarCuentaContable(this.idCuentaEnEdicion);

		if (resultado != null && !resultado.isBlank()
				&& !"Registro eliminado".equalsIgnoreCase(resultado)) {
			JOptionPane.showMessageDialog(this, resultado, "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(this, "Registro eliminado con éxito", "Eliminar cuenta", JOptionPane.INFORMATION_MESSAGE);
		if (this.onCambios != null) {
			this.onCambios.run();
		}
		this.dispose();
	}

	private void cerrarForm() {

		int option = JOptionPane.showConfirmDialog(this, "¿Estas seguro de terminar el registro?", "Cerrar?",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (option != 0) {
			return;
		}

		this.dispose();

	}

	private CuentaContableFormDetails mapCuentaContable(String clave) {

		return AppContext.cuentaContableController.buscarCuentaPorClave(clave);

	}
	
	/**
	 * Consulta y carga la cuenta superior usando la clave capturada.
	 */
	private void consultarCuentaSuperiorPorClave() {

		String claveBuscada = this.frmTxfClaveCuentaContable.getText().trim();

		if (!(claveBuscada.length() >= 4)) {
			return;
		}

		var data = this.mapCuentaContable(claveBuscada);

		if (data == null)
			return;

		this.ultimaCuentaContable = data;

		this.setSelectedItemById(this.cmbGrupoCuentaContable, data.fkIdGrupoContable());
		this.setSelectedItemById(this.cmbRubroCuentaContable, data.fkIdRubro());
		this.frmTxfClaveCuentaContableSuperior.setText(data.clave());
		this.txfNombreCuentaContableSuperior.setText(data.nombre());
		this.txaDescripcionCuentaContableSuperior.setText(data.descripcion());
		this.txfNivelCuentaContable.setText(String.valueOf(data.nivel() + 1));

	}

	/**
	 * Selecciona un elemento del JComboBox por su identificador.
	 *
	 * @param comboBox combo donde se realizará la selección
	 * @param idBuscado identificador del elemento
	 */
	private void setSelectedItemById(JComboBox<JComboboxDataViewModel> comboBox, int idBuscado) {

		for (int i = 0; i < comboBox.getItemCount(); i++) {

			JComboboxDataViewModel item = comboBox.getItemAt(i);

			if (item.id() == idBuscado) {
				comboBox.setSelectedIndex(i);
				return;
			}
		}
	}

}
