package com.kathsoft.kathpos.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.model.Articulo;

public class Fr_DatosArticulo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1528483064591725560L;
	/**
	 * 
	 * 
	 */
	private ArticuloController articuloController = new ArticuloController();
	private CategoriaController categoriaController = new CategoriaController();
	private ProveedorController proveedorController = new ProveedorController();
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
	private Component horizontalStrut_19;
	private JLabel lblNewLabel_12;
	private Component horizontalStrut_20;
	private JTextField txfCodigoSat;

	/**
	 * Create the frame.
	 */
	public Fr_DatosArticulo(int tipoOperacion) {

		if (tipoOperacion == 0) {
			this.setTitle("Nuevo Articulo");
		} else if (tipoOperacion == 1) {
			this.setTitle("Editar Articulo");
		}

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/resources/productos_icono.jpg")));
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

		if (tipoOperacion == 0) {
			this.lblNewLabel_1.setText("Nuevo Articulo");
		} else if (tipoOperacion == 1) {
			this.lblNewLabel_1.setText("Editar atículo");
		}

		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSuperiorEtiqueta.add(lblNewLabel_1);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
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

		if (tipoOperacion == 0) {

			this.txfCodigoArticulo = new JTextField();
			this.txfCodigoArticulo.setColumns(50);
			this.txfCodigoArticulo.setMaximumSize(this.txfCodigoArticulo.getPreferredSize());
			horizontalBox.add(this.txfCodigoArticulo);

		} else if (tipoOperacion == 1) {

			cmbCodigoArticulo = new JComboBox<String>();
			cmbCodigoArticulo.setEditable(false);
			horizontalBox.add(cmbCodigoArticulo);

			this.llenarCmbCodigoArticulos();

			this.cmbCodigoArticulo.addItemListener(new ItemListener() {
				@Override
				public void itemStateChanged(ItemEvent e) {

					consultarArticuloPorCodigo();

				}
			});
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
		this.llenarCmbProveedor();

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_4);

		lblNewLabel_4 = new JLabel("Categoría");
		horizontalBox_1.add(lblNewLabel_4);

		horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_5);

		cmbMarcaArticulo = new JComboBox<String>();
		horizontalBox_1.add(cmbMarcaArticulo);
		this.llenarCmbMarca();

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

		horizontalStrut_19 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_19);

		lblNewLabel_12 = new JLabel("Codigo SAT");
		horizontalBox_2.add(lblNewLabel_12);

		horizontalStrut_20 = Box.createHorizontalStrut(5);
		horizontalBox_2.add(horizontalStrut_20);

		txfCodigoSat = new JTextField();
		txfCodigoSat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(ch >= '0' && ch <= '9') || txfCodigoSat.getText().length() >= 8) {
					e.consume();
				}
			}
		});
		horizontalBox_2.add(txfCodigoSat);
		txfCodigoSat.setColumns(20);
		this.txfCodigoSat.setMaximumSize(this.txfCodigoSat.getPreferredSize());

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
		txaDescripcionArticulo.setLineWrap(true);
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
		horizontalBox_5.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Impuestos Trasladado",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
				if (!(ch >= '0' && ch <= '9' || ch == '.')) {
					e.consume();
				} else if (ch == '.' && txfCostoArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfCostoArticulo);
		txfCostoArticulo.setColumns(10);
		this.txfCostoArticulo.setMaximumSize(this.txfCostoArticulo.getPreferredSize());

		horizontalStrut_9 = Box.createHorizontalStrut(10);
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
				if (!(ch >= '0' && ch <= '9' || ch == '.')) {
					e.consume();
				} else if (ch == '.' && txfPrecioGArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfPrecioGArticulo);
		txfPrecioGArticulo.setColumns(10);
		this.txfPrecioGArticulo.setMaximumSize(this.txfPrecioGArticulo.getPreferredSize());

		horizontalStrut_11 = Box.createHorizontalStrut(10);
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
				if (!(ch >= '0' && ch <= '9' || ch == '.')) {
					e.consume();
				} else if (ch == '.' && txfPrecioMayoreoArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});
		horizontalBox_4.add(txfPrecioMayoreoArticulo);
		txfPrecioMayoreoArticulo.setColumns(10);
		this.txfPrecioMayoreoArticulo.setMaximumSize(this.txfPrecioMayoreoArticulo.getPreferredSize());

		horizontalStrut_13 = Box.createHorizontalStrut(10);
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
				if (!(ch >= '0' && ch <= '9')) {
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
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btnCancelar.setBackground(new Color(205, 92, 92));
		btnCancelar.setIcon(
				new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		panelInferiorBotones.add(btnCancelar);

		horizontalStrut_18 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_18);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipoOperacion == 0) {
					insertarNuevoArticulo();
				} else if (tipoOperacion == 1) {
					actualizarArticulo();
				}
			}
		});
		btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.setIcon(new ImageIcon(
				Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelInferiorBotones.add(btnGuardar);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void llenarCmbCodigoArticulos() {
		this.limpiarCmbCodigoArticulos();
		articuloController.consultarCodigosArticulos(this.cmbCodigoArticulo);
	}

	private void limpiarCmbCodigoArticulos() {
		this.cmbCodigoArticulo.removeAllItems();
		this.cmbCodigoArticulo.updateUI();
	}

	private void llenarCmbMarca() {
		this.limpiarCmbMarca();
		this.categoriaController.obtenerIndicesDeCategorias(this.cmbMarcaArticulo);
	}

	private void limpiarCmbMarca() {
		this.cmbMarcaArticulo.removeAllItems();
		this.cmbMarcaArticulo.updateUI();
	}

	private void llenarCmbProveedor() {
		this.limpiarCmbProveedor();
		this.proveedorController.consultarNombresProveedor(this.cmbProveedorArticulo);
	}

	private void limpiarCmbProveedor() {
		this.cmbProveedorArticulo.removeAllItems();
		this.cmbProveedorArticulo.updateUI();
	}

	private void cerrarForm() {
		this.dispose();
	}

	private void insertarNuevoArticulo() {

		Articulo art = new Articulo();

		if (this.txfCodigoArticulo.getText().length() < 1 || this.txfCodigoArticulo.getText().equals(null)
				|| this.txfCodigoArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Se debe asignar un codigo", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfCodigoSat.getText().length() < 1 || this.txfCodigoSat.getText().equals(null)
				|| this.txfCodigoSat.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Se debe asignar un codigo agrupador SAT", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfNombreArticulo.getText().length() < 1 || this.txfNombreArticulo.getText().equals(null)
				|| this.txfNombreArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe indicar el nombre comercial del acrticulo", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfCostoArticulo.getText().length() < 1 || this.txfCostoArticulo.getText().equals(null)
				|| this.txfCostoArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe indicar el costo del articulo", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (Double.parseDouble(this.txfCostoArticulo.getText()) > Double
				.parseDouble(this.txfPrecioGArticulo.getText())) {
			JOptionPane.showMessageDialog(this, "El costo unitario no puede ser superior al precio de venta", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (Double.parseDouble(this.txfCostoArticulo.getText()) > Double
				.parseDouble(this.txfPrecioMayoreoArticulo.getText())) {
			JOptionPane.showMessageDialog(this, "El costo unitario no puede ser superior al precio de venta", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfPrecioGArticulo.getText().length() < 1 || this.txfPrecioGArticulo.getText().equals(null)
				|| this.txfPrecioGArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe indicar el precio de venta del articulo", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {

			art.setCodigoArticulo(this.txfCodigoArticulo.getText());
			art.setNombreProveedor((String) this.cmbProveedorArticulo.getSelectedItem());
			art.setNombreCategoria((String) this.cmbMarcaArticulo.getSelectedItem());
			art.setCodigoSat(this.txfCodigoSat.getText());
			art.setNombre(this.txfNombreArticulo.getText());
			art.setDescripcion(this.txaDescripcionArticulo.getText());
			art.setExento((this.rdbtnExento.isSelected() || this.rdbtnNoObjeto.isSelected()) ? true : false);
			art.setCostoUnitario(Double.parseDouble(this.txfCostoArticulo.getText()));
			art.setPrecioGeneral(Double.parseDouble(this.txfPrecioGArticulo.getText()));
			art.setPrecioMayoreo(Double.parseDouble(this.txfPrecioMayoreoArticulo.getText()));
			art.setCantidadMayoreo(Integer.parseInt(this.txfCantidadParaMayoreo.getText()));

			// System.out.println(art.toString());

			articuloController.insertarNuevoArticulo(art);

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [SQL] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [Generic] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		this.limpiarCampos();
		JOptionPane.showMessageDialog(this, "Articulo almacenado", "Registro almacenado",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * coloca valores de forma dinámica en cada uno de los campos correspondientes
	 * al consultar la información de un artículo en específico en la bd
	 */
	private void consultarArticuloPorCodigo() {

		try {

			Articulo art = articuloController
					.consultarArticuloPorCodigo((String) this.cmbCodigoArticulo.getSelectedItem());

			this.txfIdArticulo.setText(String.valueOf(art.getIdArticulo()));
			this.cmbProveedorArticulo.setSelectedItem(art.getNombreProveedor());
			this.cmbMarcaArticulo.setSelectedItem(art.getNombreCategoria());
			this.txfNombreArticulo.setText(art.getNombre());
			this.txfCodigoSat.setText(art.getCodigoSat());
			this.txaDescripcionArticulo.setText(art.getDescripcion());

			if (art.isExento() == true) {
				this.rdbtnExento.setSelected(true);
			} else {
				this.rdbtnGravado.setSelected(true);
			}

			this.txfExistenciaArticulo.setText(String.valueOf(art.getExistencia()));
			this.txfCostoArticulo.setText(String.valueOf(art.getCostoUnitario()));
			this.txfPrecioGArticulo.setText(String.valueOf(art.getPrecioGeneral()));
			this.txfPrecioMayoreoArticulo.setText(String.valueOf(art.getPrecioMayoreo()));
			this.txfCantidadParaMayoreo.setText(String.valueOf(art.getCantidadMayoreo()));

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [SQL] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error: [Generic] -> " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void actualizarArticulo() {

		Articulo art = new Articulo();

		if (((String) this.cmbCodigoArticulo.getSelectedItem()).length() < 1
				|| ((String) this.cmbCodigoArticulo.getSelectedItem()).equals(null)
				|| ((String) this.cmbCodigoArticulo.getSelectedItem()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "Se debe indicar el código del artículo", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfCodigoSat.getText().length() < 1 || this.txfCodigoSat.getText().equals(null)
				|| this.txfCodigoSat.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Se debe asignar un codigo agrupador SAT", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfNombreArticulo.getText().length() < 1 || this.txfNombreArticulo.getText().equals(null)
				|| this.txfNombreArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe indicar el nombre comercial del acrticulo", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.txfCostoArticulo.getText().length() < 1 || this.txfCostoArticulo.getText().equals(null)
				|| this.txfCostoArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe indicar el costo del articulo", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (Double.parseDouble(this.txfCostoArticulo.getText()) > Double
				.parseDouble(this.txfPrecioGArticulo.getText())) {
			JOptionPane.showMessageDialog(this, "El costo unitario no puede ser superior al precio de venta", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (Double.parseDouble(this.txfCostoArticulo.getText()) > Double
				.parseDouble(this.txfPrecioMayoreoArticulo.getText())) {
			JOptionPane.showMessageDialog(this, "El costo unitario no puede ser superior al precio de venta", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.txfPrecioGArticulo.getText().length() < 1 || this.txfPrecioGArticulo.getText().equals(null)
				|| this.txfPrecioGArticulo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe indicar el precio de venta del articulo", "Error",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {

			art.setIdArticulo(Integer.parseInt(this.txfIdArticulo.getText()));
			art.setNombreProveedor((String) this.cmbProveedorArticulo.getSelectedItem());
			art.setNombreCategoria((String) this.cmbMarcaArticulo.getSelectedItem());
			art.setCodigoSat(this.txfCodigoSat.getText());
			art.setNombre(this.txfNombreArticulo.getText());
			art.setDescripcion(this.txaDescripcionArticulo.getText());
			art.setExento((this.rdbtnExento.isSelected() || this.rdbtnNoObjeto.isSelected()) ? true : false);
			art.setCostoUnitario(Double.parseDouble(this.txfCostoArticulo.getText()));
			art.setPrecioGeneral(Double.parseDouble(this.txfPrecioGArticulo.getText()));
			art.setPrecioMayoreo(Double.parseDouble(this.txfPrecioMayoreoArticulo.getText()));
			art.setCantidadMayoreo(Integer.parseInt(this.txfCantidadParaMayoreo.getText()));

			System.out.println("Desde la vista---------");

			System.out.println(art.toString());

			articuloController.actualizarArticulo(art);

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [SQL] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: [Generic] ->" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void limpiarCampos() {

		this.txfCodigoArticulo.setText("");
		this.txfNombreArticulo.setText("");
		this.txfCodigoSat.setText("");
		this.txaDescripcionArticulo.setText("");
		this.txfCostoArticulo.setText("");
		this.txfPrecioGArticulo.setText("");
		this.txfPrecioMayoreoArticulo.setText("");
		this.txfCantidadParaMayoreo.setText("");

	}

}
