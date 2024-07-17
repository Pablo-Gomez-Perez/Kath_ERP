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

import com.kathsoft.kathpos.app.model.RubroCuentaContable;
import com.kathsoft.kathpos.tools.AppContext;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import java.awt.Dimension;
import java.awt.GridLayout;

public class Fr_DatosCuentasContables extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorbotones;
	private JLabel lblNewLabel;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JFormattedTextField fmtxfClaveContable;
	private Component horizontalStrut_1;
	private JLabel lblNewLabel_2;
	private JTextField txfNombreCuenta;
	private Component verticalStrut_1;
	private Box verticalBox;
	private JPanel panel;
	private JLabel lblNewLabel_3;
	private JScrollPane scrollPane;
	private JTextArea txaDescripcionCuenta;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnGuardar;
	private Box horizontalBox_1;
	private Box verticalBox_1;
	private Component horizontalStrut_3;
	private Box verticalBox_2;
	private JRadioButton rdbtnCtaAgrupadora;
	private Component verticalStrut_2;
	private JRadioButton rdbtnCtaDetalle;
	private ButtonGroup btnRadioGroup;
	private Component verticalStrut_3;
	private Box horizontalBox_3;
	private JLabel lblNewLabel_4;
	private Component horizontalStrut_4;
	private JComboBox<RubroCuentaContable> cmbRubroCuentaContable;
	private Box verticalBox_3;
	private Box horizontalBox_2;
	private JPanel verticalBox_4;
	private Box verticalBox_5;
	private Box horizontalBox_4;
	private JLabel lblNewLabel_5;
	private Component horizontalStrut_5;
	private JTextField textField;
	private Box horizontalBox_5;
	private JLabel lblNewLabel_6;
	private Component horizontalStrut_6;
	private JTextField textField_1;
	private JRadioButton rdbtnNaturalezaDeudora;
	private JRadioButton rdbtnNaturalezaAcreedora;
	private Component verticalStrut_4;
	private ButtonGroup buttonGroup2;

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
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));

		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);

		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);

		horizontalBox = Box.createHorizontalBox();
		// panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("Clave de cuenta");
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);

		fmtxfClaveContable = new JFormattedTextField(formatoClave());
		fmtxfClaveContable.setColumns(25);
		this.fmtxfClaveContable.setMaximumSize(this.fmtxfClaveContable.getPreferredSize());
		horizontalBox.add(fmtxfClaveContable);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);

		lblNewLabel_2 = new JLabel("Nombre");
		horizontalBox.add(lblNewLabel_2);

		txfNombreCuenta = new JTextField();
		txfNombreCuenta.setColumns(50);
		this.txfNombreCuenta.setMaximumSize(this.txfNombreCuenta.getPreferredSize());
		horizontalBox.add(txfNombreCuenta);

		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);

		verticalBox = Box.createVerticalBox();
		// panelCentralFormulario.add(verticalBox);

		panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		flowLayout.setAlignment(FlowLayout.LEFT);
		verticalBox.add(panel);

		lblNewLabel_3 = new JLabel("Descripción");
		panel.add(lblNewLabel_3);

		scrollPane = new JScrollPane();
		verticalBox.add(scrollPane);

		txaDescripcionCuenta = new JTextArea();
		scrollPane.setViewportView(txaDescripcionCuenta);

		this.verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la cuenta",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		this.verticalBox_1.add(horizontalBox);
		this.verticalBox_1.add(verticalBox);

		this.horizontalBox_1.add(verticalBox_1);

		verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox_1.add(verticalStrut_3);

		horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_3);

		lblNewLabel_4 = new JLabel("Rubro");
		horizontalBox_3.add(lblNewLabel_4);

		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_4);

		cmbRubroCuentaContable = new JComboBox<RubroCuentaContable>();
		cmbRubroCuentaContable.setPreferredSize(new Dimension(600, 22));
		cmbRubroCuentaContable.setMaximumSize(new Dimension(600, 22));
		horizontalBox_3.add(cmbRubroCuentaContable);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_3);

		verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo de cuenta",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		horizontalBox_1.add(verticalBox_2);

		btnRadioGroup = new ButtonGroup();

		rdbtnCtaAgrupadora = new JRadioButton("Cuenta Agrupadora");
		rdbtnCtaAgrupadora.setBackground(new Color(255, 215, 0));
		verticalBox_2.add(rdbtnCtaAgrupadora);

		verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox_2.add(verticalStrut_2);

		rdbtnCtaDetalle = new JRadioButton("Cuenta de detalle");
		rdbtnCtaDetalle.setBackground(new Color(255, 215, 0));
		verticalBox_2.add(rdbtnCtaDetalle);

		btnRadioGroup.add(this.rdbtnCtaAgrupadora);
		this.btnRadioGroup.add(this.rdbtnCtaDetalle);

		verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de la cuenta superior",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCentralFormulario.add(verticalBox_3);

		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox_2);

		verticalBox_4 = new JPanel();
		verticalBox_4.setBackground(new Color(255, 215, 0));
		horizontalBox_2.add(verticalBox_4);
		verticalBox_4.setLayout(new GridLayout(0, 1, 0, 0));

		horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_4.add(horizontalBox_4);

		lblNewLabel_5 = new JLabel("Cuenta Superior");
		horizontalBox_4.add(lblNewLabel_5);

		horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_5);

		textField = new JTextField();
		textField.setMaximumSize(new Dimension(406, 20));
		textField.setColumns(90);
		horizontalBox_4.add(textField);

		horizontalBox_5 = Box.createHorizontalBox();
		verticalBox_4.add(horizontalBox_5);

		lblNewLabel_6 = new JLabel("Clave");
		horizontalBox_5.add(lblNewLabel_6);

		horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_6);

		textField_1 = new JTextField();
		textField_1.setMaximumSize(new Dimension(406, 20));
		textField_1.setColumns(100);
		horizontalBox_5.add(textField_1);

		verticalBox_5 = Box.createVerticalBox();
		verticalBox_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Naturaleza", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_2.add(verticalBox_5);

		rdbtnNaturalezaDeudora = new JRadioButton("Deudora");
		rdbtnNaturalezaDeudora.setBackground(new Color(255, 215, 0));
		verticalBox_5.add(rdbtnNaturalezaDeudora);

		verticalStrut_4 = Box.createVerticalStrut(20);
		verticalBox_5.add(verticalStrut_4);

		rdbtnNaturalezaAcreedora = new JRadioButton("Acreedora");
		rdbtnNaturalezaAcreedora.setBackground(new Color(255, 215, 0));
		verticalBox_5.add(rdbtnNaturalezaAcreedora);

		buttonGroup2 = new ButtonGroup();

		this.buttonGroup2.add(rdbtnNaturalezaDeudora);
		this.buttonGroup2.add(this.rdbtnNaturalezaAcreedora);

		// this.horizontalBox_1.add(horizontalBox);
		// this.horizontalBox_1.add(verticalBox);

		panelInferiorbotones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferiorbotones.getLayout();
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
				
		this.setBounds(100, 100, 760, 460);
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

		AppContext.rubroCuentaContableController.cmbRubroCuentasContables().forEach(i -> {
			cmbRubroCuentaContable.addItem(i);
		});
		

	}

	private void consultarCuentaContable(int idCuenta) {
		
	}
}
