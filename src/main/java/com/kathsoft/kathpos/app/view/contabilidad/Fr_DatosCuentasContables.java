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
import javax.swing.text.MaskFormatter;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

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
	private ButtonGroup btngTipoDeCuentaOperativo;	
	private JPanel panelDatosDeCuenta;
	private JPanel panelDatosCuentaSuperior;
	private JLabel lblClave;
	private JLabel lblNombre;
	private JTextField txfNombreCuentaContable;
	private JFormattedTextField frmtdJtxfClaveCuentaContable;
	private JTextField txfNivelCuentaContable;
	private JRadioButton jrdbtnCuentaDeDetalle;
	private JRadioButton jrdbtnCuentaAgrupadora;
	private GroupLayout gl_panelDetalleOperativoCuentaContable;
	private JLabel lblDescripcin;
	private JScrollPane scrollPane;
	private JTextArea txaDescripcionCuentaContable;
	private FlowLayout flowLayout_1;
	private JLabel lblGrupo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_DatosCuentasContables frame = new Fr_DatosCuentasContables(1, 0);
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
		/*
		 * TODO Agregar JCombobox para filtrado de Rubro contable en base a grupo
		 * contable
		 */

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
		panelDatosDeCuenta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la cuenta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		panelDatosCuentaSuperior = new JPanel();
		panelDatosCuentaSuperior.setBackground(new Color(255, 215, 0));
		panelDatosCuentaSuperior.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cuenta Superior", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentralFormulario.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
								.addComponent(panelDatosCuentaSuperior, Alignment.LEADING, GroupLayout.DEFAULT_SIZE,
										724, Short.MAX_VALUE)
								.addComponent(panelDatosDeCuenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 724,
										Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelCentralFormulario.setVerticalGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup().addContainerGap()
						.addComponent(panelDatosDeCuenta, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panelDatosCuentaSuperior, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
						.addContainerGap()));

		lblClave = new JLabel("Clave");
		
		
		lblNombre = new JLabel("Nombre");
		
		txfNombreCuentaContable = new JTextField();
		txfNombreCuentaContable.setColumns(10);
		
		frmtdJtxfClaveCuentaContable = new JFormattedTextField(this.formatoClave());
		
		JPanel panelDescripcionCuentaContable = new JPanel();
		
		JLabel lblNivel = new JLabel("Nivel");
		
		txfNivelCuentaContable = new JTextField();
		txfNivelCuentaContable.setColumns(10);
		
		JPanel panelDetalleOperativoCuentaContable = new JPanel();
		panelDetalleOperativoCuentaContable.setBackground(new Color(255, 215, 0));
		panelDetalleOperativoCuentaContable.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo Operativo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		lblGrupo = new JLabel("Grupo");
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblRubro = new JLabel("Rubro");
		
		JComboBox comboBox_1 = new JComboBox();
		
		
		GroupLayout gl_panelDatosDeCuenta = new GroupLayout(panelDatosDeCuenta);
		gl_panelDatosDeCuenta.setHorizontalGroup(
			gl_panelDatosDeCuenta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup()
					.addComponent(lblNivel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNivelCuentaContable, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblGrupo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblRubro)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox_1, 0, 209, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup()
					.addComponent(panelDescripcionCuentaContable, GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelDetalleOperativoCuentaContable, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup()
					.addComponent(lblClave, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(frmtdJtxfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNombreCuentaContable, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelDatosDeCuenta.setVerticalGroup(
			gl_panelDatosDeCuenta.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelDatosDeCuenta.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblClave)
						.addComponent(lblNombre)
						.addComponent(txfNombreCuentaContable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtdJtxfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelDetalleOperativoCuentaContable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
						.addComponent(panelDescripcionCuentaContable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosDeCuenta.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNivel)
						.addComponent(txfNivelCuentaContable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblGrupo)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRubro)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		
		jrdbtnCuentaDeDetalle = new JRadioButton("Cuenta de detalle");
		jrdbtnCuentaDeDetalle.setBackground(new Color(255, 215, 0));
		
		jrdbtnCuentaAgrupadora = new JRadioButton("Cuenta Agrupadora");
		jrdbtnCuentaAgrupadora.setBackground(new Color(255, 215, 0));
		gl_panelDetalleOperativoCuentaContable = new GroupLayout(panelDetalleOperativoCuentaContable);
		gl_panelDetalleOperativoCuentaContable.setHorizontalGroup(
			gl_panelDetalleOperativoCuentaContable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetalleOperativoCuentaContable.createSequentialGroup()
					.addGroup(gl_panelDetalleOperativoCuentaContable.createParallelGroup(Alignment.LEADING)
						.addComponent(jrdbtnCuentaDeDetalle)
						.addComponent(jrdbtnCuentaAgrupadora))
					.addContainerGap(9, Short.MAX_VALUE))
		);
		gl_panelDetalleOperativoCuentaContable.setVerticalGroup(
			gl_panelDetalleOperativoCuentaContable.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetalleOperativoCuentaContable.createSequentialGroup()
					.addComponent(jrdbtnCuentaDeDetalle)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(jrdbtnCuentaAgrupadora)
					.addContainerGap(29, Short.MAX_VALUE))
		);
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

		panelInferiorbotones.add(btnGuardar);

		if (opcion == 1) {
			this.lblNewLabel.setText("Registrar cuenta");
			this.llenarCmbRubroCuentas();
		} else {
			this.lblNewLabel.setText("Modificar cuenta");
			this.llenarCmbRubroCuentas();
			this.consultarCuentaContable(idCuenta);
		}

		this.setBounds(100, 100, 600, 490);
	}

	private MaskFormatter formatoClave() {

		var formato = new MaskFormatter();

		try {
			formato.setMask("####-####-####-####");
			return formato;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private void llenarCmbRubroCuentas() {

		/*
		 * AppContext.rubroCuentaContableController.cmbRubroCuentasContables().forEach(i
		 * -> { cmbRubroCuentaContable.addItem(i); });
		 */

	}

	private void consultarCuentaContable(int idCuenta) {

	}
}
