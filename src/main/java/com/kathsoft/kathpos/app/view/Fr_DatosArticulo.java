package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.border.CompoundBorder;

public class Fr_DatosArticulo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1528483064591725560L;
	/**
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JTextField txfIdArticulo;
	private Component horizontalStrut_1;
	private JLabel lblNewLabel_2;
	private Component horizontalStrut_2;
	private JComboBox<String> cmbCodigoArticulo;
	private JTextField txfCodigoArticulo;
	private Component verticalStrut_1;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut_3;
	private JComboBox<String> cmbProveedorArticulo;
	private Component horizontalStrut_4;
	private JLabel lblNewLabel_4;
	private Component horizontalStrut_5;
	private JComboBox<String> cmbMarcaArticulo;
	private Component verticalStrut_2;
	private Box horizontalBox_2;
	private JLabel lblNewLabel_5;
	private Component horizontalStrut_6;
	private JTextField txfNombreArticulo;
	private Component verticalStrut_3;
	private Box verticalBox;
	private JPanel panel;
	private JLabel lblNewLabel_6;
	private JTextArea txaDescripcionArticulo;
	private Component verticalStrut_4;
	private Box horizontalBox_3;
	private JLabel lblNewLabel_7;
	private Component horizontalStrut_7;
	private JTextField txfExistenciaArticulo;
	private Component verticalStrut_5;
	private Box horizontalBox_4;
	private JLabel lblNewLabel_8;
	private Component horizontalStrut_8;
	private JTextField txfCostoArticulo;
	private Component horizontalStrut_9;
	private JLabel lblNewLabel_9;
	private Component horizontalStrut_10;
	private JTextField txfPrecioGArticulo;
	private Component horizontalStrut_11;
	private JLabel lblNewLabel_10;
	private Component horizontalStrut_12;
	private JTextField txfPrecioMayoreoArticulo;
	private Component horizontalStrut_13;
	private JLabel lblNewLabel_11;
	private Component horizontalStrut_14;
	private JTextField txfCantidadParaMayoreo;
	private Component horizontalStrut_15;
	private Box horizontalBox_5;
	private ButtonGroup btnRadioGroup;
	private JRadioButton rdbtnGravado;
	private Component horizontalStrut_16;
	private JRadioButton rdbtnExento;
	private Component horizontalStrut_17;
	private JRadioButton rdbtnNoObjeto;
	private JButton btnCancelar;
	private Component horizontalStrut_18;
	private JButton btnGuardar;
	private Component verticalStrut_6;
	
	
	
	/**
	 * Create the frame.
	 */
	public Fr_DatosArticulo(int tipoOperacion) {
		
		if(tipoOperacion == 0) {
			this.setTitle("Nuevo Articulo");
		}else if(tipoOperacion == 1) {
			this.setTitle("Editar Articulo");
		}
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/resources/productos_icono.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		lblNewLabel_1 = new JLabel();
		
		if(tipoOperacion == 0) {
			this.lblNewLabel_1.setText("Nuevo Articulo");
		}else if(tipoOperacion == 1) {
			this.lblNewLabel_1.setText("Editar atículo");
		}
		
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSuperiorEtiqueta.add(lblNewLabel_1);
		
		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));
		
		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);
		
		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);
		
		lblNewLabel = new JLabel("ID");
		horizontalBox.add(lblNewLabel);
		
		horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);
		
		txfIdArticulo = new JTextField();
		txfIdArticulo.setEditable(false);
		horizontalBox.add(txfIdArticulo);
		txfIdArticulo.setColumns(15);
		this.txfIdArticulo.setMaximumSize(this.txfIdArticulo.getPreferredSize());
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);
		
		lblNewLabel_2 = new JLabel("Código");
		horizontalBox.add(lblNewLabel_2);
		
		horizontalStrut_2 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_2);
		
		if(tipoOperacion == 0) {
			
			this.txfCodigoArticulo = new JTextField();
			this.txfCodigoArticulo.setColumns(50);
			this.txfCodigoArticulo.setMaximumSize(this.txfCodigoArticulo.getPreferredSize());
			horizontalBox.add(this.txfCodigoArticulo);
			
		}else if(tipoOperacion == 1) {
						
			cmbCodigoArticulo = new JComboBox<String>();
			cmbCodigoArticulo.setEditable(false);			
			horizontalBox.add(cmbCodigoArticulo);
		}
		
		
		
		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);
		
		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);
		
		lblNewLabel_3 = new JLabel("Proveedor");
		horizontalBox_1.add(lblNewLabel_3);
		
		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_3);
		
		cmbProveedorArticulo = new JComboBox<String>();
		horizontalBox_1.add(cmbProveedorArticulo);
		
		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);
		
		lblNewLabel_4 = new JLabel("Marca");
		horizontalBox_1.add(lblNewLabel_4);
		
		horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_5);
		
		cmbMarcaArticulo = new JComboBox<String>();
		horizontalBox_1.add(cmbMarcaArticulo);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);
		
		horizontalBox_2 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_2);
		
		lblNewLabel_5 = new JLabel("Nombre");
		horizontalBox_2.add(lblNewLabel_5);
		
		horizontalStrut_6 = Box.createHorizontalStrut(5);
		horizontalBox_2.add(horizontalStrut_6);
		
		txfNombreArticulo = new JTextField();
		horizontalBox_2.add(txfNombreArticulo);
		txfNombreArticulo.setColumns(80);
		this.txfNombreArticulo.setMaximumSize(this.txfNombreArticulo.getPreferredSize());
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);
		
		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);
		
		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(255, 215, 0));
		verticalBox.add(panel);
		
		lblNewLabel_6 = new JLabel("Descripción");
		panel.add(lblNewLabel_6);
		
		txaDescripcionArticulo = new JTextArea();
		verticalBox.add(txaDescripcionArticulo);
		
		verticalStrut_4 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_4);
		
		horizontalBox_3 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_3);
		
		lblNewLabel_7 = new JLabel("Existencia");
		horizontalBox_3.add(lblNewLabel_7);
		
		horizontalStrut_7 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_7);
		
		txfExistenciaArticulo = new JTextField();
		txfExistenciaArticulo.setEditable(false);
		horizontalBox_3.add(txfExistenciaArticulo);
		txfExistenciaArticulo.setColumns(40);
		this.txfExistenciaArticulo.setMaximumSize(this.txfExistenciaArticulo.getPreferredSize());
		
		horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalBox_3.add(horizontalStrut_15);
		
		horizontalBox_5 = Box.createHorizontalBox();
		horizontalBox_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Impuestos Trasladado", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox_3.add(horizontalBox_5);
		
		btnRadioGroup = new ButtonGroup();
		
		rdbtnGravado = new JRadioButton("Gravado");
		rdbtnGravado.setBackground(new Color(255, 215, 0));
		rdbtnGravado.setSelected(true);
		horizontalBox_5.add(rdbtnGravado);
		
		horizontalStrut_16 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_16);
		
		rdbtnExento = new JRadioButton("Exento");
		rdbtnExento.setBackground(new Color(255, 215, 0));
		horizontalBox_5.add(rdbtnExento);
		
		horizontalStrut_17 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_17);
		
		rdbtnNoObjeto = new JRadioButton("No Objeto");
		rdbtnNoObjeto.setBackground(new Color(255, 215, 0));
		horizontalBox_5.add(rdbtnNoObjeto);
		
		btnRadioGroup.add(rdbtnGravado);
		btnRadioGroup.add(rdbtnExento);
		btnRadioGroup.add(rdbtnNoObjeto);
		
		verticalStrut_5 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_5);
		
		horizontalBox_4 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_4);
		
		lblNewLabel_8 = new JLabel("Costo");
		horizontalBox_4.add(lblNewLabel_8);
		
		horizontalStrut_8 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_8);
		
		txfCostoArticulo = new JTextField();
		txfCostoArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!(ch >= '0' && ch <= '9' || ch == '.')) {
					e.consume();
				}else if(ch == '.' && txfCostoArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfCostoArticulo);
		txfCostoArticulo.setColumns(10);
		this.txfCostoArticulo.setMaximumSize(this.txfCostoArticulo.getPreferredSize());
		
		horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_9);
		
		lblNewLabel_9 = new JLabel("Precio General");
		horizontalBox_4.add(lblNewLabel_9);
		
		horizontalStrut_10 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_10);
		
		txfPrecioGArticulo = new JTextField();
		txfPrecioGArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!(ch >= '0' && ch <= '9' || ch == '.')) {
					e.consume();
				}else if(ch == '.' && txfPrecioGArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfPrecioGArticulo);
		txfPrecioGArticulo.setColumns(10);
		this.txfPrecioGArticulo.setMaximumSize(this.txfPrecioGArticulo.getPreferredSize());
		
		horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_11);
		
		lblNewLabel_10 = new JLabel("Mayoreo");
		horizontalBox_4.add(lblNewLabel_10);
		
		horizontalStrut_12 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_12);
		
		txfPrecioMayoreoArticulo = new JTextField();
		txfPrecioMayoreoArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!(ch >= '0' && ch <= '9' || ch == '.')) {
					e.consume();
				}else if(ch == '.' && txfPrecioMayoreoArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfPrecioMayoreoArticulo);
		txfPrecioMayoreoArticulo.setColumns(10);
		this.txfPrecioMayoreoArticulo.setMaximumSize(this.txfPrecioMayoreoArticulo.getPreferredSize());
		
		horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_13);
		
		lblNewLabel_11 = new JLabel("Cant. p/Mayoreo");
		horizontalBox_4.add(lblNewLabel_11);
		
		horizontalStrut_14 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_14);
		
		txfCantidadParaMayoreo = new JTextField();
		txfCantidadParaMayoreo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(!(ch >= '0' && ch <= '9')) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfCantidadParaMayoreo);
		txfCantidadParaMayoreo.setColumns(5);
		this.txfCantidadParaMayoreo.setMaximumSize(this.txfCantidadParaMayoreo.getPreferredSize());
		
		verticalStrut_6 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_6);
		
		
		panelInferiorBotones = new JPanel();
		panelInferiorBotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		FlowLayout flowLayout_1 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(205,92,92));
		btnCancelar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		panelInferiorBotones.add(btnCancelar);
		
		horizontalStrut_18 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_18);
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelInferiorBotones.add(btnGuardar);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}