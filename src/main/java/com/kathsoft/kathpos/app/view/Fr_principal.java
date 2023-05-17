package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Component;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.model.Categoria;

import javax.swing.border.LineBorder;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Fr_principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8970651466053472860L;
	/**
	 * 
	 * 
	 * 
	 */
	private CategoriaController categoriaController = new CategoriaController();
	private Categoria categoria = new Categoria();
	private JPanel contentPane;
	private JMenuBar BarraMenu;
	private JMenu menuConsultar;
	private JMenuItem opcionConsultarArticulos;
	private JPanel panelPrincipalContenedor;
	private JMenu mnNewMenu;
	private JMenuItem opcionClientes;
	private JMenuItem opcionEmpleados;
	private JMenuItem opcionProveedores;
	private JMenu subMenuVentas;
	private JMenuItem opcionRegistrarVenta;
	private JMenuItem opcionConsultarVenta;
	private JPanel panelSuperiorBotones;
	private JPanel panelArticulos;

	// ============================================================================================
	// ============================================================================================
	// panel que agrega una imagen al formulario
	private JPanel panelInicio = new JPanel() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6872354069531962583L;
		/**
		 * 
		 * 
		 */
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			this.imagen = new ImageIcon(
					getClass().getResource("/com/kathsoft/kathpos/app/resources/control-inventario-erp-3.png"))
					.getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	};
	// ============================================================================================
	// ============================================================================================
	private JButton btn_irAInicio;
	private JPanel panelClientes;
	private JPanel panelEmpleados;
	private JPanel panelProveedor;
	private JPanel panelEtiquetaArticulos;
	private JLabel lblNewLabel;
	private JPanel panelArticulosCentral;
	private JMenuItem opcionMarcas;
	private JPanel panelMarcas;
	private JPanel panelMarcasEtiquetaSuperior;
	private JLabel lblNewLabel_1;
	private JPanel panelMarcasCentral;
	private Box boxVerticalMarcasFormulario;
	private Box boxVerticalMarcasTabla;
	private Box horizontalBox;
	private JLabel lblNewLabel_2;
	private JTextField txtNombreCategoria;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_3;
	private Component verticalStrut;
	private Box horizontalBox_2;
	private JLabel lblNewLabel_4;
	private Component horizontalStrut;
	private JTextField txtBuscarMarcaEnTabla;
	private Component verticalStrut_1;
	private Box horizontalBox_3;
	private JScrollPane scrollPane;
	private JTable tablaCategorias;
	private DefaultTableModel model;
	private Component verticalStrut_2;
	private Component verticalStrut_3;
	private Box horizontalBox_4;
	private Component verticalStrut_4;
	private JButton btnActualizarCategoria;
	private Component horizontalStrut_1;
	private JButton btnAgregarCategoria;
	private Component verticalStrut_5;
	private Component verticalStrut_6;
	private JScrollPane scrollPane_1;
	private JTextArea txaDescripcionCategoria;
	private Box horizontalBox_5;
	private JLabel lblNewLabel_5;
	private JComboBox<Integer> cmbIndiceDeCategoria;
	private Component verticalStrut_7;
	private Component horizontalStrut_2;
	private JButton btnBuscarCategoriaEnTabla;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_principal frame = new Fr_principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fr_principal() {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/1643231.png")));
		setTitle("Kath POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 889, 567);

		BarraMenu = new JMenuBar();
		BarraMenu.setBackground(new Color(255, 153, 0));
		setJMenuBar(BarraMenu);

		menuConsultar = new JMenu("Consultar");
		menuConsultar.setIcon(new ImageIcon(Fr_principal.class
				.getResource("/com/kathsoft/kathpos/app/resources/icone-point-d-interrogation-question-rouge.jpg")));
		menuConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		menuConsultar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		BarraMenu.add(menuConsultar);

		opcionConsultarArticulos = new JMenuItem("Articulos");
		opcionConsultarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ir al panel de los articulos dentro del CardLayout
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelArticulos");
				panelPrincipalContenedor.updateUI();

			}
		});
		opcionConsultarArticulos.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/productos_icono.jpg")));
		menuConsultar.add(opcionConsultarArticulos);

		opcionClientes = new JMenuItem("Clientes");
		opcionClientes.setIcon(new ImageIcon(Fr_principal.class.getResource(
				"/com/kathsoft/kathpos/app/resources/pngtree-call-center-customer-icon-png-image_4746069.jpg")));
		menuConsultar.add(opcionClientes);

		opcionEmpleados = new JMenuItem("Empleados");
		opcionEmpleados.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/empleados.jpg")));
		menuConsultar.add(opcionEmpleados);

		opcionProveedores = new JMenuItem("Proveedores");
		opcionProveedores.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/proveedores.png")));
		menuConsultar.add(opcionProveedores);

		opcionMarcas = new JMenuItem("Marcas");
		opcionMarcas.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/iconoMarca.png")));
		opcionMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelMarcas");
				panelPrincipalContenedor.updateUI();
			}
		});
		menuConsultar.add(opcionMarcas);

		mnNewMenu = new JMenu("Operaciones");
		mnNewMenu.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/operaciones.png")));
		mnNewMenu.setFont(new Font("Segoe UI", Font.BOLD, 13));
		BarraMenu.add(mnNewMenu);

		subMenuVentas = new JMenu("Ventas");
		subMenuVentas.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/ventas.png")));
		mnNewMenu.add(subMenuVentas);

		opcionRegistrarVenta = new JMenuItem("Registrar");
		subMenuVentas.add(opcionRegistrarVenta);

		opcionConsultarVenta = new JMenuItem("Consultar");
		subMenuVentas.add(opcionConsultarVenta);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelPrincipalContenedor = new JPanel();
		panelPrincipalContenedor.setBackground(new Color(255, 255, 0));
		contentPane.add(panelPrincipalContenedor, BorderLayout.CENTER);
		panelPrincipalContenedor.setLayout(new CardLayout(0, 0));

		panelPrincipalContenedor.add(panelInicio, "panelInicio");
		panelInicio.setLayout(new BorderLayout(0, 0));

		panelArticulos = new JPanel();
		panelPrincipalContenedor.add(panelArticulos, "panelArticulos");
		panelArticulos.setLayout(new BorderLayout(0, 0));

		panelEtiquetaArticulos = new JPanel();
		panelEtiquetaArticulos.setBackground(new Color(25, 25, 112));
		panelArticulos.add(panelEtiquetaArticulos, BorderLayout.NORTH);

		lblNewLabel = new JLabel("Modulo de Articulos");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelEtiquetaArticulos.add(lblNewLabel);

		panelArticulosCentral = new JPanel();
		panelArticulosCentral.setBackground(new Color(255, 215, 0));
		panelArticulos.add(panelArticulosCentral, BorderLayout.CENTER);
		panelArticulosCentral.setLayout(new BoxLayout(panelArticulosCentral, BoxLayout.X_AXIS));

		panelClientes = new JPanel();
		panelPrincipalContenedor.add(panelClientes, "panelClientes");
		panelClientes.setLayout(new BorderLayout(0, 0));

		panelEmpleados = new JPanel();
		panelPrincipalContenedor.add(panelEmpleados, "panelEmpleados");

		panelProveedor = new JPanel();
		panelPrincipalContenedor.add(panelProveedor, "panelProveedor");

		panelMarcas = new JPanel();
		panelPrincipalContenedor.add(panelMarcas, "panelMarcas");
		panelMarcas.setLayout(new BorderLayout(0, 0));

		panelMarcasEtiquetaSuperior = new JPanel();
		panelMarcasEtiquetaSuperior.setBackground(new Color(0, 0, 128));
		panelMarcasEtiquetaSuperior.setForeground(new Color(0, 0, 0));
		panelMarcas.add(panelMarcasEtiquetaSuperior, BorderLayout.NORTH);

		lblNewLabel_1 = new JLabel("Modulo de categoria");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panelMarcasEtiquetaSuperior.add(lblNewLabel_1);

		panelMarcasCentral = new JPanel();
		panelMarcasCentral.setBackground(new Color(255, 215, 0));
		panelMarcas.add(panelMarcasCentral, BorderLayout.CENTER);
		panelMarcasCentral.setLayout(new BoxLayout(panelMarcasCentral, BoxLayout.X_AXIS));

		boxVerticalMarcasFormulario = Box.createVerticalBox();
		boxVerticalMarcasFormulario.setBorder(
				new CompoundBorder(new EmptyBorder(0, 3, 0, 3), new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Datos y actualizacion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		boxVerticalMarcasFormulario.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelMarcasCentral.add(boxVerticalMarcasFormulario);

		verticalStrut_7 = Box.createVerticalStrut(20);
		boxVerticalMarcasFormulario.add(verticalStrut_7);

		horizontalBox_5 = Box.createHorizontalBox();
		boxVerticalMarcasFormulario.add(horizontalBox_5);

		lblNewLabel_5 = new JLabel("Indice");
		horizontalBox_5.add(lblNewLabel_5);

		cmbIndiceDeCategoria = new JComboBox<Integer>();
		cmbIndiceDeCategoria.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				consultarCategoriaPorID();
			}
		});
		horizontalBox_5.add(cmbIndiceDeCategoria);

		verticalStrut_5 = Box.createVerticalStrut(20);
		boxVerticalMarcasFormulario.add(verticalStrut_5);

		horizontalBox = Box.createHorizontalBox();
		boxVerticalMarcasFormulario.add(horizontalBox);

		lblNewLabel_2 = new JLabel("Nombre");
		horizontalBox.add(lblNewLabel_2);

		txtNombreCategoria = new JTextField();
		horizontalBox.add(txtNombreCategoria);
		txtNombreCategoria.setColumns(28);
		this.txtNombreCategoria.setMaximumSize(this.txtNombreCategoria.getPreferredSize());
		cmbIndiceDeCategoria.setMaximumSize(this.txtNombreCategoria.getPreferredSize());

		verticalStrut = Box.createVerticalStrut(20);
		boxVerticalMarcasFormulario.add(verticalStrut);

		horizontalBox_1 = Box.createHorizontalBox();
		boxVerticalMarcasFormulario.add(horizontalBox_1);

		lblNewLabel_3 = new JLabel("Descripcion");
		horizontalBox_1.add(lblNewLabel_3);

		scrollPane_1 = new JScrollPane();
		horizontalBox_1.add(scrollPane_1);

		txaDescripcionCategoria = new JTextArea();
		txaDescripcionCategoria.setRows(10);
		txaDescripcionCategoria.setColumns(25);
		scrollPane_1.setViewportView(txaDescripcionCategoria);

		verticalStrut_3 = Box.createVerticalStrut(5);
		boxVerticalMarcasFormulario.add(verticalStrut_3);

		horizontalBox_4 = Box.createHorizontalBox();
		boxVerticalMarcasFormulario.add(horizontalBox_4);

		verticalStrut_4 = Box.createVerticalStrut(20);
		horizontalBox_4.add(verticalStrut_4);

		btnActualizarCategoria = new JButton("Actualizar");
		btnActualizarCategoria.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		btnActualizarCategoria.setBackground(new Color(144, 238, 144));
		horizontalBox_4.add(btnActualizarCategoria);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_4.add(horizontalStrut_1);

		btnAgregarCategoria = new JButton("Agregar");
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarCategoria();
			}
		});
		btnAgregarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		btnAgregarCategoria.setBackground(new Color(144, 238, 144));
		horizontalBox_4.add(btnAgregarCategoria);

		verticalStrut_6 = Box.createVerticalStrut(70);
		boxVerticalMarcasFormulario.add(verticalStrut_6);

		boxVerticalMarcasTabla = Box.createVerticalBox();
		boxVerticalMarcasTabla.setBorder(
				new CompoundBorder(new EmptyBorder(0, 3, 0, 3), new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Registros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelMarcasCentral.add(boxVerticalMarcasTabla);

		verticalStrut_2 = Box.createVerticalStrut(20);
		boxVerticalMarcasTabla.add(verticalStrut_2);

		horizontalBox_2 = Box.createHorizontalBox();
		boxVerticalMarcasTabla.add(horizontalBox_2);

		lblNewLabel_4 = new JLabel("Buscar");
		horizontalBox_2.add(lblNewLabel_4);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut);

		txtBuscarMarcaEnTabla = new JTextField();
		horizontalBox_2.add(txtBuscarMarcaEnTabla);
		txtBuscarMarcaEnTabla.setColumns(80);
		this.txtBuscarMarcaEnTabla.setMaximumSize(this.txtBuscarMarcaEnTabla.getPreferredSize());

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_2);

		btnBuscarCategoriaEnTabla = new JButton("Buscar");
		btnBuscarCategoriaEnTabla.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		btnBuscarCategoriaEnTabla.setBackground(new Color(184, 134, 11));
		horizontalBox_2.add(btnBuscarCategoriaEnTabla);

		verticalStrut_1 = Box.createVerticalStrut(20);
		boxVerticalMarcasTabla.add(verticalStrut_1);

		horizontalBox_3 = Box.createHorizontalBox();
		boxVerticalMarcasTabla.add(horizontalBox_3);

		scrollPane = new JScrollPane();
		horizontalBox_3.add(scrollPane);

		model = new DefaultTableModel();
		tablaCategorias = new JTable(this.model);
		scrollPane.setViewportView(tablaCategorias);
		
		model.addColumn("id Categoria");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		
		this.llenarTablaCategoria();
		this.llenarComboBoxCategoria();
		
		panelSuperiorBotones = new JPanel();
		panelSuperiorBotones.setBackground(new Color(255, 140, 0));
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelSuperiorBotones, BorderLayout.NORTH);

		btn_irAInicio = new JButton("");
		btn_irAInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ir al layout de inicio
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelInicio");
				panelPrincipalContenedor.updateUI();

			}
		});
		btn_irAInicio.setBackground(new Color(102, 205, 170));
		btn_irAInicio.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/inicio_ico.jpg")));
		panelSuperiorBotones.add(btn_irAInicio);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	/**
	 * llena el JTable del panel de categorias con todos los registros encontrados en la bd
	 */
	private void llenarTablaCategoria() {
		this.borrarElementosDeLaTabla();
		categoriaController.verCategoriasEnTabla(this.model);
	}
	
	/**
	 * llena el JCombobox del panel de categorias con todos los indices encontrados en la bd
	 */
	private void llenarComboBoxCategoria() {
		categoriaController.obtenerIndicesDeCategorias(this.cmbIndiceDeCategoria);
	}
	
	/**
	 * coloca los valores de la consulta en sus respectivos campos de texto
	 */
	private void consultarCategoriaPorID() {
		categoria.setIdCategoria(Integer.parseInt(this.cmbIndiceDeCategoria.getSelectedItem().toString()));
		categoriaController.setCategoria(this.categoria);
		categoriaController.buscarCategoriaPorIndice(this.txtNombreCategoria,this.txaDescripcionCategoria);
	}
	
	/**
	 * inserta un nuevo registro en la bd
	 */
	private void insertarCategoria() {
		this.categoria.setNombre(this.txtNombreCategoria.getText());
		this.categoria.setDescripcion(this.txaDescripcionCategoria.getText());
		this.categoriaController.setCategoria(this.categoria);
		this.categoriaController.insertarNuevaCategoria();
		
		this.llenarTablaCategoria();
	}
	
	/**
	 * borra todos los elementos contenidos en la tabla categorias
	 */
	private void borrarElementosDeLaTabla() {
		this.model.getDataVector().removeAllElements();
		this.tablaCategorias.updateUI();
	}
}
