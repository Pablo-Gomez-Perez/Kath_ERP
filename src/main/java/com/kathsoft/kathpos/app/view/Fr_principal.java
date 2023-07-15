package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
import javax.swing.table.TableColumnModel;

import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.model.Categoria;
import com.kathsoft.kathpos.app.model.Empleado;

import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.ComponentOrientation;
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
	private EmpleadoController empleadoController = new EmpleadoController();
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
	private DefaultTableModel modelTablaCategoriaArticulo;
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
	private JPanel panelProveedorEtiqueta;
	private JPanel panelEmpleadosEtiqueta;
	private JLabel lblNewLabel_6;
	private JPanel panelEmpleadosCentral;
	private Box boxVerticalEmpleadosFormulario;
	private Box horizontalBox_6;
	private JLabel lblNewLabel_7;
	private JComboBox<String> cmbRFCEmpleado;
	private Component verticalStrut_8;
	private Box horizontalBox_7;
	private JLabel lblNewLabel_8;
	private JTextField txfCurpEmpleado;
	private Component horizontalStrut_3;
	private JLabel lblNewLabel_9;
	private JTextField txfNombreCortoEmpleado;
	private Component verticalStrut_9;
	private Box horizontalBox_8;
	private JLabel lblNewLabel_10;
	private JTextField txfNombreCompletoEmpleado;
	private Component verticalStrut_10;
	private Box horizontalBox_9;
	private JLabel lblNewLabel_11;
	private JTextField txfFechaNacEmpleadoDD;
	private Component horizontalStrut_4;
	private JLabel lblNewLabel_12;
	private JTextField txfEmailEmpleado;
	private Component verticalStrut_11;
	private Box horizontalBox_10;
	private JLabel lblNewLabel_13;
	private JTextField txfEstadoEmpleado;
	private Component horizontalStrut_5;
	private JLabel lblNewLabel_14;
	private JTextField txfCiudadEmpleado;
	private Component verticalStrut_12;
	private Box horizontalBox_11;
	private JLabel lblNewLabel_15;
	private JTextField txfDireccionEmpleado;
	private Component horizontalStrut_6;
	private JLabel lblNewLabel_16;
	private JTextField txfCodigoPostalEmpleado;
	private Component verticalStrut_13;
	private Box horizontalBox_12;
	private JLabel lblNewLabel_17;
	private JPasswordField txpsContraseniaEmpleado;
	private Component horizontalStrut_7;
	private JButton btnNuevaContraseniaEmpleado;
	private Box boxVerticalEmpleadosTabla;
	private Component verticalStrut_14;
	private Component verticalStrut_15;
	private Box horizontalBox_13;
	private JLabel lblNewLabel_18;
	private JTextField txfbuscarEmpleadoEnTabla;
	private Component horizontalStrut_8;
	private JButton btnBuscarEmpleadoEnTabla;
	private Component verticalStrut_16;
	private Box horizontalBox_14;
	private JScrollPane scrollPane_2;
	private JTable tableEmpleados;
	private DefaultTableModel modelTablaEmpleados;
	private Component verticalStrut_17;
	private Box horizontalBox_15;
	private Component verticalStrut_18;
	private JButton btnActualizarEmpleado;
	private Component horizontalStrut_9;
	private JButton btnAgregarEmpleado;
	private Component verticalStrut_19;

	// Array que define el ancho de cada columna de la tabla de empleados
	private int[] tableEmpleadosColumnsWidth = { 40, 180, 180, 180, 100, 200 };
	private JTextField txfFechaNacEmpleadoMM;
	private JTextField txfFechaNacEmpleadoYY;

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
		setBounds(100, 100, 1150, 601);

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
		opcionEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelEmpleados");
				panelPrincipalContenedor.updateUI();

				llenarTablaEmpleados();
				llenarCmbRfcEmpleados();
			}
		});
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

				llenarComboBoxCategoria();
				llenarTablaCategoria();
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
		panelEmpleados.setLayout(new BorderLayout(0, 0));

		panelEmpleadosEtiqueta = new JPanel();
		panelEmpleadosEtiqueta.setBackground(new Color(0, 0, 128));
		panelEmpleados.add(panelEmpleadosEtiqueta, BorderLayout.NORTH);

		lblNewLabel_6 = new JLabel("Modulo de Empleados");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		panelEmpleadosEtiqueta.add(lblNewLabel_6);

		panelEmpleadosCentral = new JPanel();
		panelEmpleadosCentral.setBackground(new Color(255, 215, 0));
		panelEmpleados.add(panelEmpleadosCentral, BorderLayout.CENTER);
		panelEmpleadosCentral.setLayout(new BoxLayout(panelEmpleadosCentral, BoxLayout.X_AXIS));

		boxVerticalEmpleadosFormulario = Box.createVerticalBox();
		boxVerticalEmpleadosFormulario.setBorder(
				new CompoundBorder(new EmptyBorder(0, 5, 0, 5), new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Datos y Actualizacion", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelEmpleadosCentral.add(boxVerticalEmpleadosFormulario);

		verticalStrut_14 = Box.createVerticalStrut(20);
		boxVerticalEmpleadosFormulario.add(verticalStrut_14);

		horizontalBox_6 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_6);

		lblNewLabel_7 = new JLabel("RFC Empleado");
		horizontalBox_6.add(lblNewLabel_7);

		cmbRFCEmpleado = new JComboBox<String>();		
		cmbRFCEmpleado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if((String) cmbRFCEmpleado.getSelectedItem() == null || cmbRFCEmpleado.getSelectedItem().equals("") || ((String)cmbRFCEmpleado.getSelectedItem()).length() < 1) {
					return;
				}
				consultarEmpleadoPorRfc((String) cmbRFCEmpleado.getSelectedItem());
			}
		});
		this.cmbRFCEmpleado.setEditable(true);
		//cmbRFCEmpleado.setEditable(false);
		//cmbRFCEmpleado.setToolTipText("Presiona enter para ver detalles del empleado");
		
		horizontalBox_6.add(cmbRFCEmpleado);

		verticalStrut_8 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_8);

		horizontalBox_7 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_7);

		lblNewLabel_8 = new JLabel("CURP");
		horizontalBox_7.add(lblNewLabel_8);

		txfCurpEmpleado = new JTextField();
		horizontalBox_7.add(txfCurpEmpleado);
		txfCurpEmpleado.setColumns(20);
		this.txfCurpEmpleado.setMaximumSize(this.txfCurpEmpleado.getPreferredSize());

		horizontalStrut_3 = Box.createHorizontalStrut(10);
		horizontalBox_7.add(horizontalStrut_3);

		lblNewLabel_9 = new JLabel("Nombre Corto");
		horizontalBox_7.add(lblNewLabel_9);

		txfNombreCortoEmpleado = new JTextField();
		horizontalBox_7.add(txfNombreCortoEmpleado);
		txfNombreCortoEmpleado.setColumns(10);
		this.txfNombreCortoEmpleado.setMaximumSize(this.txfNombreCortoEmpleado.getPreferredSize());

		panelProveedor = new JPanel();
		panelPrincipalContenedor.add(panelProveedor, "panelProveedor");
		panelProveedor.setLayout(new BorderLayout(0, 0));

		panelProveedorEtiqueta = new JPanel();
		panelProveedor.add(panelProveedorEtiqueta, BorderLayout.NORTH);

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
		cmbIndiceDeCategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() != KeyEvent.VK_ENTER) {
					return;
				}
				consultarCategoriaPorID(Integer.parseInt(cmbIndiceDeCategoria.getSelectedItem().toString()));
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
		cmbRFCEmpleado.setMaximumSize(this.txtNombreCategoria.getPreferredSize());

		verticalStrut_9 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_9);

		horizontalBox_8 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_8);

		lblNewLabel_10 = new JLabel("Nombre completo");
		horizontalBox_8.add(lblNewLabel_10);

		txfNombreCompletoEmpleado = new JTextField();
		horizontalBox_8.add(txfNombreCompletoEmpleado);
		txfNombreCompletoEmpleado.setColumns(35);
		this.txfNombreCompletoEmpleado.setMaximumSize(this.txfNombreCompletoEmpleado.getPreferredSize());

		verticalStrut_10 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_10);

		horizontalBox_9 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_9);

		lblNewLabel_11 = new JLabel("Fecha de n.");
		horizontalBox_9.add(lblNewLabel_11);

		txfFechaNacEmpleadoDD = new JTextField();
		txfFechaNacEmpleadoDD.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(ch < '0' || ch > '9') {
					e.consume();
				}
			}
		});
		txfFechaNacEmpleadoDD.setToolTipText("Día");
		horizontalBox_9.add(txfFechaNacEmpleadoDD);
		txfFechaNacEmpleadoDD.setColumns(2);
		this.txfFechaNacEmpleadoDD.setMaximumSize(this.txfFechaNacEmpleadoDD.getPreferredSize());

		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_9.add(horizontalStrut_4);
		
		txfFechaNacEmpleadoMM = new JTextField();
		txfFechaNacEmpleadoMM.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(ch < '0' || ch > '9') {
					e.consume();
				}
			}
		});
		txfFechaNacEmpleadoMM.setToolTipText("Mes");
		horizontalBox_9.add(txfFechaNacEmpleadoMM);
		txfFechaNacEmpleadoMM.setColumns(2);
		this.txfFechaNacEmpleadoMM.setMaximumSize(this.txfFechaNacEmpleadoMM.getPreferredSize());
		
		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_9.add(horizontalStrut_4);			
		
		txfFechaNacEmpleadoYY = new JTextField();
		txfFechaNacEmpleadoYY.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(ch < '0' || ch > '9') {
					e.consume();
				}
			}
		});
		txfFechaNacEmpleadoYY.setToolTipText("Año");
		horizontalBox_9.add(txfFechaNacEmpleadoYY);
		txfFechaNacEmpleadoYY.setColumns(4);
		this.txfFechaNacEmpleadoYY.setMaximumSize(this.txfFechaNacEmpleadoYY.getPreferredSize());
		
		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_9.add(horizontalStrut_4);
		
		lblNewLabel_12 = new JLabel("Email");
		horizontalBox_9.add(lblNewLabel_12);

		txfEmailEmpleado = new JTextField();
		horizontalBox_9.add(txfEmailEmpleado);
		txfEmailEmpleado.setColumns(20);
		this.txfEmailEmpleado.setMaximumSize(this.txfEmailEmpleado.getPreferredSize());

		verticalStrut_11 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_11);

		horizontalBox_10 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_10);

		lblNewLabel_13 = new JLabel("Estado");
		horizontalBox_10.add(lblNewLabel_13);

		txfEstadoEmpleado = new JTextField();
		horizontalBox_10.add(txfEstadoEmpleado);
		txfEstadoEmpleado.setColumns(15);
		this.txfEstadoEmpleado.setMaximumSize(this.txfEstadoEmpleado.getPreferredSize());

		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_10.add(horizontalStrut_5);

		lblNewLabel_14 = new JLabel("Ciudad");
		horizontalBox_10.add(lblNewLabel_14);

		txfCiudadEmpleado = new JTextField();
		horizontalBox_10.add(txfCiudadEmpleado);
		txfCiudadEmpleado.setColumns(15);
		this.txfCiudadEmpleado.setMaximumSize(this.txfCiudadEmpleado.getPreferredSize());

		verticalStrut_12 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_12);

		horizontalBox_11 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_11);

		lblNewLabel_15 = new JLabel("Direccion");
		horizontalBox_11.add(lblNewLabel_15);

		txfDireccionEmpleado = new JTextField();
		horizontalBox_11.add(txfDireccionEmpleado);
		txfDireccionEmpleado.setColumns(20);
		this.txfDireccionEmpleado.setMaximumSize(this.txfDireccionEmpleado.getPreferredSize());

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_11.add(horizontalStrut_6);

		lblNewLabel_16 = new JLabel("Codigo Postal");
		horizontalBox_11.add(lblNewLabel_16);

		txfCodigoPostalEmpleado = new JTextField();
		txfCodigoPostalEmpleado.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if(ch < '0' || ch > '9') {
					e.consume();
				}
			}
		});
		horizontalBox_11.add(txfCodigoPostalEmpleado);
		txfCodigoPostalEmpleado.setColumns(8);
		this.txfCodigoPostalEmpleado.setMaximumSize(this.txfCodigoPostalEmpleado.getPreferredSize());

		verticalStrut_13 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_13);

		horizontalBox_12 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_12);

		lblNewLabel_17 = new JLabel("Contraseña");
		horizontalBox_12.add(lblNewLabel_17);

		txpsContraseniaEmpleado = new JPasswordField();
		txpsContraseniaEmpleado.setEnabled(false);
		txpsContraseniaEmpleado.setEditable(false);
		txpsContraseniaEmpleado.setColumns(20);
		horizontalBox_12.add(txpsContraseniaEmpleado);
		this.txpsContraseniaEmpleado.setMaximumSize(this.txpsContraseniaEmpleado.getPreferredSize());

		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_12.add(horizontalStrut_7);

		btnNuevaContraseniaEmpleado = new JButton("Nueva");
		btnNuevaContraseniaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaPasswordEmpleado();
			}
		});
		btnNuevaContraseniaEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/lapiz.png")));
		btnNuevaContraseniaEmpleado.setBackground(new Color(0, 128, 128));
		horizontalBox_12.add(btnNuevaContraseniaEmpleado);

		verticalStrut_17 = Box.createVerticalStrut(5);
		boxVerticalEmpleadosFormulario.add(verticalStrut_17);

		horizontalBox_15 = Box.createHorizontalBox();
		boxVerticalEmpleadosFormulario.add(horizontalBox_15);

		verticalStrut_18 = Box.createVerticalStrut(20);
		horizontalBox_15.add(verticalStrut_18);

		btnActualizarEmpleado = new JButton("Actualizar");
		btnActualizarEmpleado.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		horizontalBox_15.add(btnActualizarEmpleado);

		horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalBox_15.add(horizontalStrut_9);

		btnAgregarEmpleado = new JButton("Agregar");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarNuevoEmpleado();
			}
		});
		btnAgregarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		horizontalBox_15.add(btnAgregarEmpleado);

		verticalStrut_19 = Box.createVerticalStrut(150);
		boxVerticalEmpleadosFormulario.add(verticalStrut_19);

		boxVerticalEmpleadosTabla = Box.createVerticalBox();
		boxVerticalEmpleadosTabla.setBorder(
				new CompoundBorder(new EmptyBorder(0, 5, 0, 5), new TitledBorder(new LineBorder(new Color(0, 0, 0)),
						"Registros", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))));
		panelEmpleadosCentral.add(boxVerticalEmpleadosTabla);

		verticalStrut_15 = Box.createVerticalStrut(20);
		boxVerticalEmpleadosTabla.add(verticalStrut_15);

		horizontalBox_13 = Box.createHorizontalBox();
		boxVerticalEmpleadosTabla.add(horizontalBox_13);

		lblNewLabel_18 = new JLabel("Buscar");
		horizontalBox_13.add(lblNewLabel_18);

		txfbuscarEmpleadoEnTabla = new JTextField();
		horizontalBox_13.add(txfbuscarEmpleadoEnTabla);
		txfbuscarEmpleadoEnTabla.setColumns(80);
		this.txfbuscarEmpleadoEnTabla.setMaximumSize(this.txfbuscarEmpleadoEnTabla.getPreferredSize());

		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_13.add(horizontalStrut_8);

		btnBuscarEmpleadoEnTabla = new JButton("Buscar");
		btnBuscarEmpleadoEnTabla.setBackground(new Color(205, 133, 63));
		btnBuscarEmpleadoEnTabla.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		horizontalBox_13.add(btnBuscarEmpleadoEnTabla);

		verticalStrut_16 = Box.createVerticalStrut(20);
		boxVerticalEmpleadosTabla.add(verticalStrut_16);

		horizontalBox_14 = Box.createHorizontalBox();
		boxVerticalEmpleadosTabla.add(horizontalBox_14);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		horizontalBox_14.add(scrollPane_2);

		modelTablaEmpleados = new DefaultTableModel();
		tableEmpleados = new JTable();
		tableEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		//tableEmpleados.setEnabled(false);
		scrollPane_2.setViewportView(tableEmpleados);
		tableEmpleados.setModel(modelTablaEmpleados);

		modelTablaEmpleados.addColumn("id");
		modelTablaEmpleados.addColumn("RFC");
		modelTablaEmpleados.addColumn("CURP");
		modelTablaEmpleados.addColumn("Nombre");
		modelTablaEmpleados.addColumn("Nick");
		modelTablaEmpleados.addColumn("Email");
		
		//remueve el editor del jtable
		for(int i = 0; i < modelTablaEmpleados.getColumnCount(); i++) {
			Class<?> colClass = tableEmpleados.getColumnClass(i);
			tableEmpleados.setDefaultEditor(colClass, null);
		}

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

		modelTablaCategoriaArticulo = new DefaultTableModel();
		tablaCategorias = new JTable(this.modelTablaCategoriaArticulo);
		scrollPane.setViewportView(tablaCategorias);

		modelTablaCategoriaArticulo.addColumn("id Categoria");
		modelTablaCategoriaArticulo.addColumn("Nombre");
		modelTablaCategoriaArticulo.addColumn("Descripcion");

		// this.llenarTablaCategoria();
		// this.llenarComboBoxCategoria();

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

		TableColumnModel empleadosColumnModel = tableEmpleados.getColumnModel();

		for (int i = 0; i < tableEmpleadosColumnsWidth.length; i++) {
			empleadosColumnModel.getColumn(i).setPreferredWidth(tableEmpleadosColumnsWidth[i]);
			empleadosColumnModel.getColumn(i).setMinWidth(tableEmpleadosColumnsWidth[i]);			
		}

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * llena el JTable del panel de categorias con todos los registros encontrados
	 * en la bd
	 */
	private void llenarTablaCategoria() {
		this.borrarElementosDeLaTablaCategorias();
		categoriaController.verCategoriasEnTabla(this.modelTablaCategoriaArticulo);
	}

	/**
	 * llena el jTable del panel de empleados con todos los registros encontrados en
	 * la bd
	 */
	private void llenarTablaEmpleados() {
		this.borrarElementosDeLaTablaEmpleados();
		empleadoController.verEmpleadosEnTabla(modelTablaEmpleados);
	}

	/**
	 * llena el JCombobox del panel de categorias con todos los indices encontrados
	 * en la bd
	 */
	private void llenarComboBoxCategoria() {
		this.cmbIndiceDeCategoria.removeAllItems();
		this.cmbIndiceDeCategoria.updateUI();
		categoriaController.obtenerIndicesDeCategorias(this.cmbIndiceDeCategoria);
		this.cmbIndiceDeCategoria.setSelectedIndex(0);		
	}

	/**
	 * llena el JCombobox del panel de rfc de empleados con todos los elemtnos
	 * retornados por la vista almacenada
	 */
	private void llenarCmbRfcEmpleados() {
		this.cmbRFCEmpleado.removeAllItems();
		this.cmbRFCEmpleado.updateUI();
		empleadoController.consultarRfcEmpleado(this.cmbRFCEmpleado);
		cmbRFCEmpleado.setSelectedIndex(1);
	}

	/**
	 * busca los datos del empleado en la bd de acuerdo al rfc que se le pase como
	 * parametro y asigna los valores correspondientes a sus respectivos campos en
	 * el formulario
	 * 
	 * @param rfc
	 */
	private void consultarEmpleadoPorRfc(String rfc) {
		Empleado empl = empleadoController.consultarEmpleadoPorRfc(rfc);
		String dia = "";
		String mes = "";
		String anio = "";
		
		if(empl.getFechaNacimiento() != null) {
			String[] fecha = empl.getFechaNacimiento().toString().split("-");
			dia = fecha[2];
			mes = fecha[1];
			anio = fecha[0];
		}
				
		this.txfCurpEmpleado.setText(empl.getCurp());
		this.txfNombreCortoEmpleado.setText(empl.getNombreCorto());
		this.txfNombreCompletoEmpleado.setText(empl.getNombre());
		this.txfFechaNacEmpleadoDD.setText(dia);
		this.txfFechaNacEmpleadoMM.setText(mes);
		this.txfFechaNacEmpleadoYY.setText(anio);
		this.txfEmailEmpleado.setText(empl.getEmail());
		this.txfEstadoEmpleado.setText(empl.getEstado());
		this.txfCiudadEmpleado.setText(empl.getCiudad());
		this.txfDireccionEmpleado.setText(empl.getDireccion());
		this.txfCodigoPostalEmpleado.setText(empl.getCodigoPostal());
		this.txpsContraseniaEmpleado.setText(empl.getPassword());
	}
	
	/**
	 * inserta un nuevo registro de empleado en la bd
	 */	
	private void insertarNuevoEmpleado() {
		
		Empleado empl = new Empleado();	
		
		if(this.cmbRFCEmpleado.getSelectedItem() == null || this.cmbRFCEmpleado.getSelectedItem().toString() == "") {
			JOptionPane.showMessageDialog(null, "Debe asignar un RFC", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.txfCurpEmpleado.getText() == null || this.txfCurpEmpleado.getText() == "") {
			JOptionPane.showMessageDialog(null, "Debe asignar un CURP", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.txfNombreCompletoEmpleado.getText() == null || this.txfNombreCompletoEmpleado.getText() == "") {
			JOptionPane.showMessageDialog(null, "Debe indicar el nombre", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.txfNombreCortoEmpleado.getText() == null || this.txfNombreCortoEmpleado.getText() == "") {
			JOptionPane.showMessageDialog(null, "Debe asignar un alias o nombre corto", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		String fecha = "";
		
		if(this.txfFechaNacEmpleadoDD.getText() == null || this.txfFechaNacEmpleadoDD.getText() == ""
				|| this.txfFechaNacEmpleadoMM.getText() == null || this.txfFechaNacEmpleadoMM.getText() == ""
				|| this.txfFechaNacEmpleadoYY.getText() == null || this.txfFechaNacEmpleadoYY.getText() == "") {			
			JOptionPane.showMessageDialog(null, "Indique la fecha correctamente", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}else {
			fecha = this.txfFechaNacEmpleadoYY.getText() + "-" + this.txfFechaNacEmpleadoMM.getText() + "-" + this.txfFechaNacEmpleadoDD.getText();
		}
			
		if(fecha.equals("") || fecha.length()<2) {
			JOptionPane.showMessageDialog(null, "Indique la fecha correctamente", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(this.txfEmailEmpleado.getText() == null || this.txfEmailEmpleado.getText() == "") {
			JOptionPane.showMessageDialog(null, "Indique el correo Electrónico", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
								
		System.out.println(fecha);
		try {
			
			empl.setRfc(this.cmbRFCEmpleado.getSelectedItem().toString());
			empl.setCurp(this.txfCurpEmpleado.getText());
			empl.setNombre(this.txfNombreCompletoEmpleado.getText());
			empl.setNombreCorto(this.txfNombreCortoEmpleado.getText());
			empl.setFechaNacimiento(java.sql.Date.valueOf(fecha));
			empl.setEmail(this.txfEmailEmpleado.getText());
			empl.setEstado(this.txfEstadoEmpleado.getText());
			empl.setCiudad(this.txfCiudadEmpleado.getText());
			empl.setDireccion(this.txfDireccionEmpleado.getText());
			empl.setCodigoPostal(this.txfCodigoPostalEmpleado.getText());
			
			empleadoController.insertarNuevoEmpleado(empl);
			
			
		}catch(Exception er){
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error", "Error Object", JOptionPane.ERROR_MESSAGE);
		}
		
		this.llenarCmbRfcEmpleados();
		this.llenarTablaEmpleados();
				
	}

	/**
	 * coloca los valores de la consulta en sus respectivos campos de texto
	 */
	private void consultarCategoriaPorID(int id) {
		Categoria cta = categoriaController.buscarCategoriaPorIndice(id);
		this.txtNombreCategoria.setText(cta.getNombre());
		this.txaDescripcionCategoria.setText(cta.getDescripcion());
	}

	/**
	 * inserta un nuevo registro en la bd
	 */
	private void insertarCategoria() {

		this.categoria.setNombre(this.txtNombreCategoria.getText());
		this.categoria.setDescripcion(this.txaDescripcionCategoria.getText());
		this.categoriaController.setCategoria(this.categoria);
		this.categoriaController.insertarNuevaCategoria();
		// this.cmbIndiceDeCategoria.addItem(this.cmbIndiceDeCategoria.getSelectedIndex()+1);
		this.llenarComboBoxCategoria();
		this.llenarTablaCategoria();
		this.limpiarCampos();

		// JOptionPane.showMessageDialog(this, "Registro agregado", "Kath POS",
		// JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * borra todos los elementos contenidos en la tabla categorias
	 */
	private void borrarElementosDeLaTablaCategorias() {
		this.modelTablaCategoriaArticulo.getDataVector().removeAllElements();
		this.tablaCategorias.updateUI();
	}

	/**
	 * borra todos los elementos contenidos en la tabla empleados
	 */
	private void borrarElementosDeLaTablaEmpleados() {
		this.modelTablaEmpleados.getDataVector().removeAllElements();
		this.tableEmpleados.updateUI();
	}

	/**
	 * limpia los campos te texto del formulario
	 */
	private void limpiarCampos() {
		this.txtNombreCategoria.setText("");
		this.txaDescripcionCategoria.setText("");
	}
	
	private void abrirVentanaPasswordEmpleado() {
				
		String rfcEmpleado = this.cmbRFCEmpleado.getSelectedItem().toString();
		
		if(rfcEmpleado.equals("") || rfcEmpleado == null || rfcEmpleado.length() < 1) {
			JOptionPane.showMessageDialog(this, "Error", "No se ha seleccionado un empleado", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		System.out.println(rfcEmpleado);
		
		EventQueue.invokeLater(new Runnable() {			
			@Override
			public void run() {
				try {
					
					Fr_NewPasswordEmpleado frame = new Fr_NewPasswordEmpleado(rfcEmpleado);
					frame.setVisible(true);
				}catch(Exception er) {
					er.printStackTrace();
				}				
			}
		});
	}
}
