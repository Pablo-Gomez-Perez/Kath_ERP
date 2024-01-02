package com.kathsoft.kathpos.app.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.controller.VentasController;
import com.kathsoft.kathpos.app.model.Categoria;
import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.tools.DataTools;

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
	private ProveedorController proveedorController = new ProveedorController();
	private ArticuloController articuloController = new ArticuloController();
	private ClientesController clientesController = new ClientesController();
	private VentasController ventasController = new VentasController();
	private SucursalController sucursalController = new SucursalController();
	private FormasDePagoController formasDePagoController = new FormasDePagoController();
	private Sucursal sucursal;
	private JPanel contentPane;
	private JMenuBar BarraMenu;
	private JMenu menuConsultar;
	private JMenuItem opcionConsultarArticulos;
	private JPanel panelPrincipalContenedor;
	private JMenu menuOperaciones;
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
	private DefaultTableModel modelTablaCategoriaArticulo;
	private DefaultTableModel modelTablaProveedores;
	private DefaultTableModel modelTablaArticulos;
	private DefaultTableModel modelTablaVentas;
	private DefaultTableModel modelTablaFormasDePago;
	private JPanel panelProveedorEtiqueta;
	private JPanel panelEmpleadosEtiqueta;
	private JLabel lblNewLabel_6;
	private JPanel panelEmpleadosCentral;
	private DefaultTableModel modelTablaEmpleados;
	private DefaultTableModel modelTablaSucursales;

	// Array que define el ancho de cada columna de la tabla de empleados
	private int[] tableEmpleadosColumnsWidth = { 40, // id
			150, // sucursal
			180, // RFC
			180, // Curp
			180, // Nombre completo
			100, // nombre corto
			200, // email
			150 // activo o inactivo
	};
	// Array que define el ancho de cada columna de la tabla de categoría
	private int[] tablaCategoriaColumnsWidth = { 40, 180, 400 };
	// Array que define el ancho de cada columna de la tabla de Proveedores
	private int[] tablaProveedoresColumnsWidth = { 30, // Indice
			150, // Rfc
			150, // Clave contable
			180, // Nombre
			400, // Descripcion
			200, // Correo
			100, // Estado
			100, // Ciudad
			300, // Direccion
			90, // Codigo postal
			150 // activo o inactivo
	};
	// Array que define el ancho de cada columna de la tabla de Articulos
	private int[] tablaArticulosColumnsWidth = { 40, /* id */
			150, /* codigo */
			200, /* proveedor */
			180, /* categoría */
			100, /* codigo sat */
			300, /* Nombre */
			450, /* descripcion */
			100, /* Existencia */
			100, /* Precio g */
			100, /* Precio m */
			100 /* activo o inactivo*/
	};

	private int[] tablaClientesColumnsWidth = { 30, // indice
			150, // Rfc
			100, // cuenta contable
			300, // nombre completo
			100, // nombre corto
			180, // correo electrónico
			100, // estado
			100, // ciudad
			400, // direccion
			100, // codigo postal
			150 // activo o inactivo
	};

	private int[] tablaVentasColumnsWidth = { 50, // i venta
			120, // fecha venta
			100, // tipo venta
			210, // empleado
			210, // Cliente
			120, // sub total
			120, // Iva
			120, // Total
			90, // Accion
	};

	private int[] tablaSucursalesColumnWidth = { 40, // indice
			150, // nombre
			300, // descripcion
			200, // telefono
			200, // email
			150, // estado
			200, // ciudad
			300, // direccion
			120, // codigo postal
			150 // activo o inactivo

	};
	private JLabel lblNewLabel_2;
	private JPanel panelProovedorCentral;
	private JScrollPane scrollPaneTablaProveedores;
	private JTable tablaProveedores;
	private JPanel panelProveedorCentralBotones;
	private JButton btnAgregarProveedor;
	private JButton btnActualizarProveedor;
	private JScrollPane scrollPaneTablaArticulos;
	private JTable tablaArticulos;
	private JPanel panelArticulosCentralBotones;
	private JButton btnAgregarArticulo;
	private JButton btnActualizarArticulo;
	private JButton btnExportarArticuloExcel;
	private JButton btnCalculadora;
	private JPanel panelArticulosCentralBuscar;
	private JLabel lblNewLabel_19;
	private Component horizontalStrut_13;
	private JTextField txfBuscarArticulo;
	private JButton btnBuscarArticulo;
	private Box verticalBox;
	private Box verticalBox_1;
	private Box horizontalBox_16;
	private Box horizontalBox_17;
	private JLabel lblNewLabel_20;
	private Component horizontalStrut_14;
	private ButtonGroup btnRadioGroupArticulos;
	private ButtonGroup btnRadioGroupOrdernarVentas;
	private ButtonGroup btnRadioGroupBuscarVentas;
	private JRadioButton rdbBuscarArtPorNombre;
	private Component horizontalStrut_15;
	private JRadioButton rdbtBuscarArtPorProveedor;
	private Component horizontalStrut_16;
	private JRadioButton rdbtBuscarArtPorCategoria;
	private Component horizontalStrut_17;
	private JRadioButton rdbtBuscarArtPorCodigo;
	private Component horizontalStrut_18;
	private JRadioButton rdbtBuscarArtPorDescrip;
	private Component horizontalStrut_19;
	private JPanel panelEtiquetaClientes;
	private JPanel panelClientesCentral;
	private JPanel panelClientesCentralBotones;
	private JButton btnAgregarCliente;
	private JButton btnActualizarCliente;
	private JScrollPane scrollPaneTablaClientes;
	private JTable tablaClientes;
	private JPanel panelClientesCentralBuscar;
	private JLabel lblNewLabel_21;
	private Component horizontalStrut_21;
	private JTextField txfBuscarCliente;
	private Component horizontalStrut_22;
	private JButton btnBuscarCliente;
	private DefaultTableModel modelTablaClientes;
	private JPanel panelVentas;
	private JPanel panelCompras;
	private JPanel panelEtiquetaVentas;
	private JPanel panelVentasCentral;
	private JLabel lblNewLabel_22;
	private JButton btn_irAVentas;
	private JPanel panelVentasCentralBotones;
	private JScrollPane scrollPaneTablaVentas;
	private JTable tablaVentas;
	private JPanel panelVentasCentralBuscar;
	private JLabel lblNewLabel_23;
	private Component horizontalStrut_23;
	private JRadioButton rdbOrdenarVtPorId;
	private JRadioButton rdbOrdenarVtPorEmpleado;
	private JRadioButton rdbOrdenarVtPorCliente;
	private JRadioButton rdbOrdenarVtPorVigente;
	private Component horizontalStrut_24;
	private JButton btNuevaVenta;
	private JButton btnExportarVentasExcel;
	private JRadioButton rdbOrdenarVtPorTipo;
	private Box verticalBox_2;
	private Box verticalBox_3;
	private Box horizontalBox_18;
	private JLabel lblNewLabel_24;
	private Component horizontalStrut_25;
	private JTextField txfBuscarVenta;
	private JButton btnBuscarVenta;
	private Box horizontalBox_19;
	private JLabel lblNewLabel_25;
	private Component horizontalStrut_26;
	private JRadioButton rdbBuscarVtPorId;
	private Component horizontalStrut_27;
	private JRadioButton rdbBuscarVtPorEmpleado;
	private Component horizontalStrut_28;
	private JRadioButton rdbBuscarVtPorCliente;
	private Component horizontalStrut_29;
	private JRadioButton rdbBuscarVtPorFecha;
	private Component horizontalStrut_30;
	private JSeparator separator;
	private JMenuItem opcionCerrarSesion;
	private JMenuItem opcionSalirDelSistema;
	private JScrollPane scrollPaneTablaEmpleados;
	private JTable tableEmpleados;
	private JPanel panelEmpleadosCentralbotones;
	private JButton btnAgregarEmpleado;
	private JButton btnActualizarEmpleado;
	private JLabel lblNewLabel_7;
	private JPanel panelSucursales;
	private JPanel panelEtiquetaSucursales;
	private JLabel lblNewLabel_8;
	private JPanel panelSucursalCentral;
	private JScrollPane scrollPaneTablaSucursales;
	private JTable tablaSucursales;
	private JMenuItem opcionSucursales;
	private JPanel panelSucursalCentralBotones;
	private JButton btnNuevaSucursal;
	private JButton btnActualizarSucursal;
	private JPanel panelFormasDePago;
	private JPanel panelEtiquetaFormasDePago;
	private JPanel panelFormasDePagoCentral;
	private JLabel lblNewLabel_9;
	private JScrollPane scrollPaneTablaFormasDePago;
	private JTable tablaFormasDePago;
	private JPanel panelFormasDePagoCentralBotones;
	private JButton btnNuevaFormaDePago;
	private JButton btnActualizarFormaDePago;
	private JMenuItem opcionFormasDePago;
	private JButton btnEliminarFormaPago;
	private JButton btnEliminarEmpleado;
	private JButton btnEliminarCliente;
	private JButton btnEliminarProveedor;
	private JButton btnEliminarSucursal;
	private JButton btnEliminarArticulo;
	private JScrollPane scrollPaneTablaCategorias;
	private JTable tablaCategorias;
	private JPanel panelCategoriasCentralBotones;
	private JButton btnAgregarCategoria;
	private JButton btnActualizarCategoria;
	private JButton btnEliminarCategoria;
	private JPanel panelMarcasCentralBuscar;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut;
	private JTextField txfBuscarCategoria;
	private JButton btnBuscarCategoria;

	/**
	 * Launch the application.
	 *
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_principal frame = new Fr_principal(
							new Sucursal(1, null, null, null, null, null, null, null, null));
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
	public Fr_principal(Sucursal sucursal) {

		this.sucursal = sucursal;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/1643231.png")));
		setTitle("Kath POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 601);

		BarraMenu = new JMenuBar();
		BarraMenu.setBackground(new Color(255, 153, 0));
		setJMenuBar(BarraMenu);

		menuConsultar = new JMenu("Consultar Catalogo");
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

				llenarTablaArticulos();
			}
		});
		opcionConsultarArticulos.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/productos_icono.jpg")));
		menuConsultar.add(opcionConsultarArticulos);

		opcionClientes = new JMenuItem("Clientes");
		opcionClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelClientes");
				panelPrincipalContenedor.updateUI();

				llenarTablaClientes();
			}
		});
		opcionClientes.setIcon(new ImageIcon(Fr_principal.class.getResource(
				"/com/kathsoft/kathpos/app/resources/pngtree-call-center-customer-icon-png-image_4746069.jpg")));
		menuConsultar.add(opcionClientes);

		opcionEmpleados = new JMenuItem("Empleados");
		opcionEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ir al panel de empleados dentro del CardLayout
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelEmpleados");
				panelPrincipalContenedor.updateUI();

				llenarTablaEmpleados();
			}
		});
		opcionEmpleados.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/empleados.jpg")));
		menuConsultar.add(opcionEmpleados);

		opcionProveedores = new JMenuItem("Proveedores");
		opcionProveedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelProveedor");

				llenarTablaProveedor();
			}
		});
		opcionProveedores.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/proveedores.png")));
		menuConsultar.add(opcionProveedores);

		opcionMarcas = new JMenuItem("Categorias");
		opcionMarcas.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/iconoMarca.png")));
		opcionMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelMarcas");
				panelPrincipalContenedor.updateUI();

				llenarTablaCategoria();
			}
		});
		menuConsultar.add(opcionMarcas);

		opcionSucursales = new JMenuItem("Sucursales");
		opcionSucursales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelSucursales");
				panelPrincipalContenedor.updateUI();

				llenarTablaSucursales();
			}
		});
		opcionSucursales.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/sucursal.jpg")));
		menuConsultar.add(opcionSucursales);

		opcionFormasDePago = new JMenuItem("Formas De Pago");
		opcionFormasDePago.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/formas_de_pago.png")));
		opcionFormasDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelFormasDePago");
				panelPrincipalContenedor.updateUI();

				llenarTablaFormasDePago();

			}
		});
		menuConsultar.add(opcionFormasDePago);

		separator = new JSeparator();
		menuConsultar.add(separator);

		opcionCerrarSesion = new JMenuItem("Cerrar sesion");
		opcionCerrarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormLogin();
			}
		});
		opcionCerrarSesion.setBackground(new Color(255, 153, 153));
		opcionCerrarSesion.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/cerrarSesion.png")));
		menuConsultar.add(opcionCerrarSesion);

		opcionSalirDelSistema = new JMenuItem("Salir");
		opcionSalirDelSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarFormPrincipal();
			}
		});
		opcionSalirDelSistema.setBackground(new Color(255, 0, 51));
		opcionSalirDelSistema.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/cerrar.png")));
		menuConsultar.add(opcionSalirDelSistema);

		menuOperaciones = new JMenu("Operaciones");
		menuOperaciones.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/operaciones.png")));
		menuOperaciones.setFont(new Font("Segoe UI", Font.BOLD, 13));
		BarraMenu.add(menuOperaciones);

		subMenuVentas = new JMenu("Ventas");
		subMenuVentas.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/ventas.png")));
		menuOperaciones.add(subMenuVentas);

		opcionRegistrarVenta = new JMenuItem("Registrar");
		opcionRegistrarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormVentas(sucursal.getIdSucursal());
			}
		});
		subMenuVentas.add(opcionRegistrarVenta);

		opcionConsultarVenta = new JMenuItem("Consultar");
		opcionConsultarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelVentas");
				panelPrincipalContenedor.updateUI();

				llenarTablaVentas(1);
			}
		});
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
		panelArticulosCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelArticulosCentral.setBackground(new Color(255, 215, 0));
		panelArticulos.add(panelArticulosCentral, BorderLayout.CENTER);
		panelArticulosCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaArticulos = new JScrollPane();
		panelArticulosCentral.add(scrollPaneTablaArticulos);

		// ==========================================================================
		// Configuracion de la tabla Articulos
		// ==========================================================================
		modelTablaArticulos = new DefaultTableModel();
		tablaArticulos = new JTable();
		tablaArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaArticulos.setViewportView(tablaArticulos);
		tablaArticulos.setModel(modelTablaArticulos);

		modelTablaArticulos.addColumn("Id");
		modelTablaArticulos.addColumn("Codigo");
		modelTablaArticulos.addColumn("Proveedor");
		modelTablaArticulos.addColumn("Categoría");
		modelTablaArticulos.addColumn("Codigo Sat");
		modelTablaArticulos.addColumn("Nombre");
		modelTablaArticulos.addColumn("Descripción");
		modelTablaArticulos.addColumn("Existencia");
		modelTablaArticulos.addColumn("Precio G");
		modelTablaArticulos.addColumn("Precio M");
		modelTablaArticulos.addColumn("Estatus");

		// se remueve el editor de la tabla de articulos
		DataTools.removerEditorDeTabla(tablaArticulos, modelTablaArticulos);

		// =================================================================================================================================================================================

		panelArticulosCentralBotones = new JPanel();
		panelArticulosCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panelArticulosCentralBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		panelArticulosCentral.add(panelArticulosCentralBotones, BorderLayout.NORTH);

		btnAgregarArticulo = new JButton("Agregar");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaFormularioArticulo(0, 0, sucursal.getIdSucursal());

			}
		});
		btnAgregarArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		btnAgregarArticulo.setBackground(new Color(144, 238, 144));
		panelArticulosCentralBotones.add(btnAgregarArticulo);

		btnActualizarArticulo = new JButton("Actualizar");
		btnActualizarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaFormularioArticulo(1,
						DataTools.getIndiceElementoSeleccionado(tablaArticulos, modelTablaArticulos, 0),
						sucursal.getIdSucursal());

			}
		});
		btnActualizarArticulo.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		btnActualizarArticulo.setBackground(new Color(144, 238, 144));
		panelArticulosCentralBotones.add(btnActualizarArticulo);

		btnEliminarArticulo = new JButton("Eliminar");
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarArticulo();
			}
		});
		btnEliminarArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		this.btnEliminarArticulo.setBackground(new Color(255, 51, 0));
		panelArticulosCentralBotones.add(btnEliminarArticulo);

		btnExportarArticuloExcel = new JButton("Exportar a Excel");
		btnExportarArticuloExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/excelLogo.jpg")));
		btnExportarArticuloExcel.setBackground(new Color(102, 205, 170));
		panelArticulosCentralBotones.add(btnExportarArticuloExcel);

		panelArticulosCentralBuscar = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panelArticulosCentralBuscar.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		panelArticulosCentralBuscar.setBackground(new Color(255, 215, 0));
		panelArticulosCentral.add(panelArticulosCentralBuscar, BorderLayout.SOUTH);

		verticalBox = Box.createVerticalBox();
		panelArticulosCentralBuscar.add(verticalBox);

		horizontalBox_16 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_16);

		horizontalBox_17 = Box.createHorizontalBox();
		horizontalBox_17.setBorder(new EmptyBorder(5, 0, 0, 0));
		verticalBox.add(horizontalBox_17);

		lblNewLabel_20 = new JLabel("Buscar por");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.BOLD, 12));
		horizontalBox_17.add(lblNewLabel_20);

		horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalBox_17.add(horizontalStrut_14);

		btnRadioGroupArticulos = new ButtonGroup();

		rdbBuscarArtPorNombre = new JRadioButton("Nombre");
		rdbBuscarArtPorNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbBuscarArtPorNombre.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbBuscarArtPorNombre);

		horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalBox_17.add(horizontalStrut_15);

		rdbtBuscarArtPorProveedor = new JRadioButton("Proveedor");
		rdbtBuscarArtPorProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorProveedor.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorProveedor);

		horizontalStrut_16 = Box.createHorizontalStrut(20);
		horizontalBox_17.add(horizontalStrut_16);

		rdbtBuscarArtPorCategoria = new JRadioButton("Categoria");
		rdbtBuscarArtPorCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorCategoria.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorCategoria);

		horizontalStrut_17 = Box.createHorizontalStrut(20);
		horizontalBox_17.add(horizontalStrut_17);

		rdbtBuscarArtPorCodigo = new JRadioButton("Código");
		rdbtBuscarArtPorCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorCodigo.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorCodigo);

		horizontalStrut_18 = Box.createHorizontalStrut(20);
		horizontalBox_17.add(horizontalStrut_18);

		rdbtBuscarArtPorDescrip = new JRadioButton("Descripción");
		rdbtBuscarArtPorDescrip.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorDescrip.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorDescrip);

		btnRadioGroupArticulos.add(this.rdbBuscarArtPorNombre);
		btnRadioGroupArticulos.add(this.rdbtBuscarArtPorCategoria);
		btnRadioGroupArticulos.add(this.rdbtBuscarArtPorCodigo);
		btnRadioGroupArticulos.add(this.rdbtBuscarArtPorDescrip);
		btnRadioGroupArticulos.add(this.rdbtBuscarArtPorProveedor);

		horizontalStrut_19 = Box.createHorizontalStrut(300);
		horizontalBox_17.add(horizontalStrut_19);

		lblNewLabel_19 = new JLabel("Buscar artículo");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 13));
		// panelArticulosCentralBuscar.add(lblNewLabel_19);
		horizontalBox_16.add(lblNewLabel_19);
		// verticalBox.add(lblNewLabel_19);

		horizontalStrut_13 = Box.createHorizontalStrut(5);
		horizontalBox_16.add(horizontalStrut_13);
		// panelArticulosCentralBuscar.add(horizontalStrut_13);

		txfBuscarArticulo = new JTextField();
		txfBuscarArticulo.setFont(new Font("Tahoma", Font.BOLD, 13));
		// panelArticulosCentralBuscar.add(txfBuscarArticulo);
		horizontalBox_16.add(txfBuscarArticulo);
		txfBuscarArticulo.setColumns(70);
		this.txfBuscarArticulo.setMaximumSize(this.txfBuscarArticulo.getPreferredSize());

		verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new EmptyBorder(10, 30, 10, 10));
		panelArticulosCentralBuscar.add(verticalBox_1);

		btnBuscarArticulo = new JButton("Buscar");
		btnBuscarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarArticulosPorNombre();
			}
		});
		btnBuscarArticulo.setBackground(new Color(184, 134, 11));
		btnBuscarArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		// panelArticulosCentralBuscar.add(btnBuscarArticulo);

		verticalBox_1.add(btnBuscarArticulo);

		panelClientes = new JPanel();
		panelClientes.setBackground(new Color(255, 215, 0));
		panelPrincipalContenedor.add(panelClientes, "panelClientes");
		panelClientes.setLayout(new BorderLayout(0, 0));

		panelEtiquetaClientes = new JPanel();
		panelEtiquetaClientes.setBackground(new Color(25, 25, 112));
		panelClientes.add(panelEtiquetaClientes, BorderLayout.NORTH);

		lblNewLabel_7 = new JLabel("Modulo de Clientes");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		panelEtiquetaClientes.add(lblNewLabel_7);

		panelClientesCentral = new JPanel();
		panelClientesCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelClientesCentral.setBackground(new Color(255, 215, 0));
		panelClientes.add(panelClientesCentral, BorderLayout.CENTER);
		panelClientesCentral.setLayout(new BorderLayout(0, 0));

		panelClientesCentralBotones = new JPanel();
		FlowLayout fl_panelClientesCentralBotones = (FlowLayout) panelClientesCentralBotones.getLayout();
		fl_panelClientesCentralBotones.setAlignment(FlowLayout.RIGHT);
		panelClientesCentralBotones.setBackground(new Color(255, 215, 0));
		panelClientesCentral.add(panelClientesCentralBotones, BorderLayout.NORTH);

		btnAgregarCliente = new JButton("Agregar");
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormClientes(0, 0);
			}
		});
		btnAgregarCliente.setBackground(new Color(144, 238, 144));
		btnAgregarCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelClientesCentralBotones.add(btnAgregarCliente);

		btnActualizarCliente = new JButton("Actualizar");
		btnActualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormClientes(1, DataTools.getIndiceElementoSeleccionado(tablaClientes, modelTablaClientes, 0));
			}
		});
		btnActualizarCliente.setBackground(new Color(144, 238, 144));
		btnActualizarCliente.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		panelClientesCentralBotones.add(btnActualizarCliente);

		btnEliminarCliente = new JButton("Eliminar");
		btnEliminarCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		this.btnEliminarCliente.setBackground(new Color(255, 51, 0));
		btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCliente();
			}
		});
		panelClientesCentralBotones.add(btnEliminarCliente);

		scrollPaneTablaClientes = new JScrollPane();
		panelClientesCentral.add(scrollPaneTablaClientes, BorderLayout.CENTER);

		modelTablaClientes = new DefaultTableModel();
		tablaClientes = new JTable();
		tablaClientes.setModel(modelTablaClientes);
		tablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaClientes.setViewportView(tablaClientes);

		modelTablaClientes.addColumn("Id");
		modelTablaClientes.addColumn("RFC");
		modelTablaClientes.addColumn("Cta Contable");
		modelTablaClientes.addColumn("Nombre completo");
		modelTablaClientes.addColumn("Alias");
		modelTablaClientes.addColumn("Email");
		modelTablaClientes.addColumn("Estado");
		modelTablaClientes.addColumn("Ciudad");
		modelTablaClientes.addColumn("Direccion");
		modelTablaClientes.addColumn("Codigo P.");
		modelTablaClientes.addColumn("Activo");

		// se remueve el editor de la tabla provedoores
		DataTools.removerEditorDeTabla(tablaClientes, modelTablaClientes);

		panelClientesCentralBuscar = new JPanel();
		panelClientesCentralBuscar.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_4 = (FlowLayout) panelClientesCentralBuscar.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		panelClientesCentral.add(panelClientesCentralBuscar, BorderLayout.SOUTH);

		lblNewLabel_21 = new JLabel("Buscar cliente");
		lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelClientesCentralBuscar.add(lblNewLabel_21);

		horizontalStrut_21 = Box.createHorizontalStrut(20);
		panelClientesCentralBuscar.add(horizontalStrut_21);

		txfBuscarCliente = new JTextField();
		panelClientesCentralBuscar.add(txfBuscarCliente);
		txfBuscarCliente.setColumns(70);
		this.txfBuscarCliente.setMaximumSize(this.txfBuscarCliente.getPreferredSize());

		horizontalStrut_22 = Box.createHorizontalStrut(20);
		panelClientesCentralBuscar.add(horizontalStrut_22);

		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.setBackground(new Color(184, 134, 11));
		btnBuscarCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		panelClientesCentralBuscar.add(btnBuscarCliente);

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
		panelEmpleadosCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelEmpleadosCentral.setBackground(new Color(255, 215, 0));
		panelEmpleados.add(panelEmpleadosCentral, BorderLayout.CENTER);
		panelEmpleadosCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaEmpleados = new JScrollPane();
		panelEmpleadosCentral.add(scrollPaneTablaEmpleados, BorderLayout.CENTER);

		modelTablaEmpleados = new DefaultTableModel();
		tableEmpleados = new JTable();
		tableEmpleados.setModel(this.modelTablaEmpleados);
		tableEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		modelTablaEmpleados.addColumn("id");
		modelTablaEmpleados.addColumn("Sucursal");
		modelTablaEmpleados.addColumn("RFC");
		modelTablaEmpleados.addColumn("CURP");
		modelTablaEmpleados.addColumn("Nombre");
		modelTablaEmpleados.addColumn("Nick");
		modelTablaEmpleados.addColumn("Email");
		modelTablaEmpleados.addColumn("Activo");

		// remueve el editor del jtable
		for (int i = 0; i < modelTablaEmpleados.getColumnCount(); i++) {
			Class<?> colClass = tableEmpleados.getColumnClass(i);
			tableEmpleados.setDefaultEditor(colClass, null);
		}

		scrollPaneTablaEmpleados.setViewportView(tableEmpleados);

		panelEmpleadosCentralbotones = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panelEmpleadosCentralbotones.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		panelEmpleadosCentralbotones.setBackground(new Color(255, 215, 0));
		panelEmpleadosCentral.add(panelEmpleadosCentralbotones, BorderLayout.NORTH);

		btnAgregarEmpleado = new JButton("Agregar");
		btnAgregarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		btnAgregarEmpleado.setBackground(new Color(144, 238, 144));
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioEmpleados(0, 0);
			}
		});
		panelEmpleadosCentralbotones.add(btnAgregarEmpleado);

		btnActualizarEmpleado = new JButton("Actualizar");
		btnActualizarEmpleado.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		btnActualizarEmpleado.setBackground(new Color(144, 238, 144));
		btnActualizarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioEmpleados(1,
						DataTools.getIndiceElementoSeleccionado(tableEmpleados, modelTablaEmpleados, 0));
			}
		});
		panelEmpleadosCentralbotones.add(btnActualizarEmpleado);

		btnEliminarEmpleado = new JButton("Eliminar");
		btnEliminarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarEmpleado();
			}
		});
		btnEliminarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		this.btnEliminarEmpleado.setBackground(new Color(255, 51, 0));
		panelEmpleadosCentralbotones.add(btnEliminarEmpleado);

		panelProveedor = new JPanel();
		panelPrincipalContenedor.add(panelProveedor, "panelProveedor");
		panelProveedor.setLayout(new BorderLayout(0, 0));

		panelProveedorEtiqueta = new JPanel();
		panelProveedorEtiqueta.setBackground(new Color(25, 25, 112));
		panelProveedor.add(panelProveedorEtiqueta, BorderLayout.NORTH);

		lblNewLabel_2 = new JLabel("Modulo de Proveedores");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		panelProveedorEtiqueta.add(lblNewLabel_2);

		panelProovedorCentral = new JPanel();
		panelProovedorCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelProovedorCentral.setBackground(new Color(255, 215, 0));
		panelProveedor.add(panelProovedorCentral, BorderLayout.CENTER);
		panelProovedorCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaProveedores = new JScrollPane();
		panelProovedorCentral.add(scrollPaneTablaProveedores, BorderLayout.CENTER);

		// ==========================================================================
		// Configuracion de la tabla Proveedores
		// ================================================================
		modelTablaProveedores = new DefaultTableModel();

		modelTablaProveedores.addColumn("Id");
		modelTablaProveedores.addColumn("RFC");
		modelTablaProveedores.addColumn("Cta Contable");
		modelTablaProveedores.addColumn("Nombre");
		modelTablaProveedores.addColumn("Descripcion");
		modelTablaProveedores.addColumn("Email");
		modelTablaProveedores.addColumn("Estado");
		modelTablaProveedores.addColumn("Ciudad");
		modelTablaProveedores.addColumn("Direccion");
		modelTablaProveedores.addColumn("Codigo P.");
		modelTablaProveedores.addColumn("Activo");

		tablaProveedores = new JTable();
		tablaProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaProveedores.setViewportView(tablaProveedores);
		tablaProveedores.setModel(modelTablaProveedores);

		panelProveedorCentralBotones = new JPanel();
		panelProveedorCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panelProveedorCentralBotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelProovedorCentral.add(panelProveedorCentralBotones, BorderLayout.NORTH);

		btnAgregarProveedor = new JButton("Agregar");
		btnAgregarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioProveedor(0, 0);
			}
		});
		btnAgregarProveedor.setBackground(new Color(144, 238, 144));
		btnAgregarProveedor.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelProveedorCentralBotones.add(btnAgregarProveedor);

		btnActualizarProveedor = new JButton("Actualizar");
		btnActualizarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioProveedor(1,
						DataTools.getIndiceElementoSeleccionado(tablaProveedores, modelTablaProveedores, 0));
			}
		});
		btnActualizarProveedor.setBackground(new Color(144, 238, 144));
		btnActualizarProveedor.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		panelProveedorCentralBotones.add(btnActualizarProveedor);

		btnEliminarProveedor = new JButton("Eliminar");
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProveedor();
			}
		});
		btnEliminarProveedor.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		this.btnEliminarProveedor.setBackground(new Color(255, 51, 0));
		panelProveedorCentralBotones.add(btnEliminarProveedor);

		DataTools.removerEditorDeTabla(this.tablaProveedores, this.modelTablaProveedores);

		// =================================================================================================================================================================================

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
		panelMarcasCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelMarcasCentral.setBackground(new Color(255, 215, 0));
		panelMarcas.add(panelMarcasCentral, BorderLayout.CENTER);
		panelMarcasCentral.setLayout(new BorderLayout(0, 0));

		modelTablaCategoriaArticulo = new DefaultTableModel();

		modelTablaCategoriaArticulo.addColumn("Id");
		modelTablaCategoriaArticulo.addColumn("Nombre");
		modelTablaCategoriaArticulo.addColumn("Descripcion");
		modelTablaCategoriaArticulo.addColumn("Activo");

		scrollPaneTablaCategorias = new JScrollPane();
		panelMarcasCentral.add(scrollPaneTablaCategorias, BorderLayout.CENTER);

		tablaCategorias = new JTable();
		this.tablaCategorias.setModel(modelTablaCategoriaArticulo);
		this.tablaCategorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaCategorias.setViewportView(tablaCategorias);

		DataTools.removerEditorDeTabla(this.tablaCategorias, modelTablaCategoriaArticulo);

		panelCategoriasCentralBotones = new JPanel();
		panelCategoriasCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_10 = (FlowLayout) panelCategoriasCentralBotones.getLayout();
		flowLayout_10.setAlignment(FlowLayout.RIGHT);
		panelMarcasCentral.add(panelCategoriasCentralBotones, BorderLayout.NORTH);

		btnAgregarCategoria = new JButton("Agregar");
		btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioCategoria(1, 0);
			}
		});
		btnAgregarCategoria.setBackground(new Color(144, 238, 144));
		btnAgregarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		panelCategoriasCentralBotones.add(btnAgregarCategoria);

		btnActualizarCategoria = new JButton("Actualizar");
		btnActualizarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioCategoria(2,
						DataTools.getIndiceElementoSeleccionado(tablaCategorias, modelTablaCategoriaArticulo, 0));
			}
		});
		btnActualizarCategoria.setBackground(new Color(144, 238, 144));
		btnActualizarCategoria.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		panelCategoriasCentralBotones.add(btnActualizarCategoria);

		btnEliminarCategoria = new JButton("Eliminar");
		btnEliminarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCategoria();
			}
		});
		btnEliminarCategoria.setBackground(new Color(255, 51, 0));
		btnEliminarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		panelCategoriasCentralBotones.add(btnEliminarCategoria);

		panelMarcasCentralBuscar = new JPanel();
		panelMarcasCentralBuscar.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_11 = (FlowLayout) panelMarcasCentralBuscar.getLayout();
		flowLayout_11.setAlignment(FlowLayout.RIGHT);
		panelMarcasCentral.add(panelMarcasCentralBuscar, BorderLayout.SOUTH);

		lblNewLabel_3 = new JLabel("Buscar categoría");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMarcasCentralBuscar.add(lblNewLabel_3);

		horizontalStrut = Box.createHorizontalStrut(20);
		panelMarcasCentralBuscar.add(horizontalStrut);

		txfBuscarCategoria = new JTextField();
		panelMarcasCentralBuscar.add(txfBuscarCategoria);
		txfBuscarCategoria.setColumns(70);

		btnBuscarCategoria = new JButton("Buscar");
		btnBuscarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultarCategoriaPorNombre();
			}
		});
		btnBuscarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		this.btnBuscarCategoria.setBackground(new Color(184, 134, 11));
		panelMarcasCentralBuscar.add(btnBuscarCategoria);

		panelVentas = new JPanel();
		panelVentas.setBackground(new Color(255, 215, 0));
		panelPrincipalContenedor.add(panelVentas, "panelVentas");
		panelVentas.setLayout(new BorderLayout(0, 0));

		panelEtiquetaVentas = new JPanel();
		panelEtiquetaVentas.setBackground(new Color(0, 0, 128));
		panelVentas.add(panelEtiquetaVentas, BorderLayout.NORTH);

		lblNewLabel_22 = new JLabel("Ventas");
		lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_22.setForeground(new Color(255, 255, 255));
		panelEtiquetaVentas.add(lblNewLabel_22);

		panelVentasCentral = new JPanel();
		panelVentasCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelVentasCentral.setBackground(new Color(255, 215, 0));
		panelVentas.add(panelVentasCentral, BorderLayout.CENTER);
		panelVentasCentral.setLayout(new BorderLayout(0, 0));

		panelVentasCentralBotones = new JPanel();
		panelVentasCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_5 = (FlowLayout) panelVentasCentralBotones.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);
		panelVentasCentral.add(panelVentasCentralBotones, BorderLayout.NORTH);

		lblNewLabel_23 = new JLabel("Ordenar por");
		lblNewLabel_23.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelVentasCentralBotones.add(lblNewLabel_23);

		horizontalStrut_23 = Box.createHorizontalStrut(5);
		panelVentasCentralBotones.add(horizontalStrut_23);

		rdbOrdenarVtPorId = new JRadioButton("Folio");
		rdbOrdenarVtPorId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaVentas(1);
			}
		});
		rdbOrdenarVtPorId.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbOrdenarVtPorId.setSelected(true);
		rdbOrdenarVtPorId.setBackground(new Color(255, 215, 0));
		panelVentasCentralBotones.add(rdbOrdenarVtPorId);

		rdbOrdenarVtPorEmpleado = new JRadioButton("Empleado");
		rdbOrdenarVtPorEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaVentas(2);
			}
		});
		rdbOrdenarVtPorEmpleado.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbOrdenarVtPorEmpleado.setBackground(new Color(255, 215, 0));
		panelVentasCentralBotones.add(rdbOrdenarVtPorEmpleado);

		rdbOrdenarVtPorCliente = new JRadioButton("Cliente");
		rdbOrdenarVtPorCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaVentas(3);
			}
		});
		rdbOrdenarVtPorCliente.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbOrdenarVtPorCliente.setBackground(new Color(255, 215, 0));
		panelVentasCentralBotones.add(rdbOrdenarVtPorCliente);

		rdbOrdenarVtPorVigente = new JRadioButton("Vigente");
		rdbOrdenarVtPorVigente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaVentas(4);
			}
		});
		rdbOrdenarVtPorVigente.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbOrdenarVtPorVigente.setBackground(new Color(255, 215, 0));
		panelVentasCentralBotones.add(rdbOrdenarVtPorVigente);

		rdbOrdenarVtPorTipo = new JRadioButton("Tipo");
		rdbOrdenarVtPorTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaVentas(5);
			}
		});
		rdbOrdenarVtPorTipo.setFont(new Font("Tahoma", Font.BOLD, 11));
		rdbOrdenarVtPorTipo.setBackground(new Color(255, 215, 0));
		panelVentasCentralBotones.add(rdbOrdenarVtPorTipo);

		btnRadioGroupOrdernarVentas = new ButtonGroup();

		btnRadioGroupOrdernarVentas.add(this.rdbOrdenarVtPorId);
		btnRadioGroupOrdernarVentas.add(this.rdbOrdenarVtPorEmpleado);
		btnRadioGroupOrdernarVentas.add(this.rdbOrdenarVtPorCliente);
		btnRadioGroupOrdernarVentas.add(this.rdbOrdenarVtPorVigente);
		btnRadioGroupOrdernarVentas.add(this.rdbOrdenarVtPorTipo);

		horizontalStrut_24 = Box.createHorizontalStrut(320);
		panelVentasCentralBotones.add(horizontalStrut_24);

		btNuevaVenta = new JButton("Punto de venta");
		btNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormVentas(sucursal.getIdSucursal());
			}
		});
		btNuevaVenta.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/ventas.png")));
		btNuevaVenta.setBackground(new Color(152, 251, 152));
		panelVentasCentralBotones.add(btNuevaVenta);

		btnExportarVentasExcel = new JButton("Exportar a Excel");
		btnExportarVentasExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/excelLogo.jpg")));
		btnExportarVentasExcel.setBackground(new Color(102, 205, 170));
		panelVentasCentralBotones.add(btnExportarVentasExcel);

		scrollPaneTablaVentas = new JScrollPane();
		panelVentasCentral.add(scrollPaneTablaVentas, BorderLayout.CENTER);

		modelTablaVentas = new DefaultTableModel();
		tablaVentas = new JTable();

		modelTablaVentas.addColumn("Folio");
		modelTablaVentas.addColumn("Fecha");
		modelTablaVentas.addColumn("Tipo");
		modelTablaVentas.addColumn("Atendió");
		modelTablaVentas.addColumn("Cliente");
		modelTablaVentas.addColumn("Subtotal");
		modelTablaVentas.addColumn("IVA");
		modelTablaVentas.addColumn("Total");
		modelTablaVentas.addColumn("Vigente");
		// modelTablaVentas.addColumn("Edit");

		tablaVentas.setModel(modelTablaVentas);
		scrollPaneTablaVentas.setViewportView(tablaVentas);

		// se remueve el editor del jtable de ventas
		for (int i = 0; i < modelTablaVentas.getColumnCount(); i++) {
			Class<?> colClass = tablaVentas.getColumnClass(i);
			tablaVentas.setDefaultEditor(colClass, null);
		}

		tablaVentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelVentasCentralBuscar = new JPanel();
		panelVentasCentralBuscar.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_6 = (FlowLayout) panelVentasCentralBuscar.getLayout();
		flowLayout_6.setAlignment(FlowLayout.LEFT);
		panelVentasCentral.add(panelVentasCentralBuscar, BorderLayout.SOUTH);

		verticalBox_2 = Box.createVerticalBox();
		panelVentasCentralBuscar.add(verticalBox_2);

		horizontalBox_18 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_18);

		lblNewLabel_24 = new JLabel("Buscar Venta");
		lblNewLabel_24.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_18.add(lblNewLabel_24);

		horizontalStrut_25 = Box.createHorizontalStrut(20);
		horizontalBox_18.add(horizontalStrut_25);

		txfBuscarVenta = new JTextField();
		txfBuscarVenta.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_18.add(txfBuscarVenta);
		txfBuscarVenta.setColumns(70);

		horizontalBox_19 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_19);

		lblNewLabel_25 = new JLabel("Buscar por:");
		lblNewLabel_25.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_19.add(lblNewLabel_25);

		horizontalStrut_26 = Box.createHorizontalStrut(20);
		horizontalBox_19.add(horizontalStrut_26);

		rdbBuscarVtPorId = new JRadioButton("Folio");
		rdbBuscarVtPorId.setBackground(new Color(255, 215, 0));
		rdbBuscarVtPorId.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_19.add(rdbBuscarVtPorId);

		horizontalStrut_27 = Box.createHorizontalStrut(20);
		horizontalBox_19.add(horizontalStrut_27);

		rdbBuscarVtPorEmpleado = new JRadioButton("Empleado");
		rdbBuscarVtPorEmpleado.setBackground(new Color(255, 215, 0));
		rdbBuscarVtPorEmpleado.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_19.add(rdbBuscarVtPorEmpleado);

		horizontalStrut_28 = Box.createHorizontalStrut(20);
		horizontalBox_19.add(horizontalStrut_28);

		rdbBuscarVtPorCliente = new JRadioButton("Cliente");
		rdbBuscarVtPorCliente.setBackground(new Color(255, 215, 0));
		rdbBuscarVtPorCliente.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_19.add(rdbBuscarVtPorCliente);

		horizontalStrut_29 = Box.createHorizontalStrut(20);
		horizontalBox_19.add(horizontalStrut_29);

		rdbBuscarVtPorFecha = new JRadioButton("Fecha");
		rdbBuscarVtPorFecha.setBackground(new Color(255, 215, 0));
		rdbBuscarVtPorFecha.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_19.add(rdbBuscarVtPorFecha);

		btnRadioGroupBuscarVentas = new ButtonGroup();

		btnRadioGroupBuscarVentas.add(this.rdbBuscarVtPorId);
		btnRadioGroupBuscarVentas.add(this.rdbBuscarVtPorEmpleado);
		btnRadioGroupBuscarVentas.add(this.rdbBuscarVtPorCliente);
		btnRadioGroupBuscarVentas.add(this.rdbBuscarVtPorFecha);

		horizontalStrut_30 = Box.createHorizontalStrut(460);
		horizontalBox_19.add(horizontalStrut_30);

		verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new EmptyBorder(10, 30, 10, 10));
		panelVentasCentralBuscar.add(verticalBox_3);

		btnBuscarVenta = new JButton("Buscar");
		btnBuscarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarVentasPor();
			}
		});
		btnBuscarVenta.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		btnBuscarVenta.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBuscarVenta.setBackground(new Color(184, 134, 11));
		verticalBox_3.add(btnBuscarVenta);

		panelCompras = new JPanel();
		panelPrincipalContenedor.add(panelCompras, "panelCompras");

		panelSucursales = new JPanel();
		panelSucursales.setBackground(new Color(255, 215, 0));
		panelPrincipalContenedor.add(panelSucursales, "panelSucursales");
		panelSucursales.setLayout(new BorderLayout(0, 0));

		panelEtiquetaSucursales = new JPanel();
		panelEtiquetaSucursales.setBackground(new Color(0, 0, 128));
		panelSucursales.add(panelEtiquetaSucursales, BorderLayout.NORTH);

		lblNewLabel_8 = new JLabel("Modulo de Sucursales");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelEtiquetaSucursales.add(lblNewLabel_8);

		panelSucursalCentral = new JPanel();
		panelSucursalCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelSucursalCentral.setBackground(new Color(255, 215, 0));
		panelSucursales.add(panelSucursalCentral, BorderLayout.CENTER);
		panelSucursalCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaSucursales = new JScrollPane();
		panelSucursalCentral.add(scrollPaneTablaSucursales, BorderLayout.CENTER);

		modelTablaSucursales = new DefaultTableModel();
		tablaSucursales = new JTable();
		tablaSucursales.setModel(modelTablaSucursales);
		scrollPaneTablaSucursales.setViewportView(tablaSucursales);

		modelTablaSucursales.addColumn("Id");
		modelTablaSucursales.addColumn("Nombre");
		modelTablaSucursales.addColumn("Descripcion");
		modelTablaSucursales.addColumn("Telefono");
		modelTablaSucursales.addColumn("Email");
		modelTablaSucursales.addColumn("Estado");
		modelTablaSucursales.addColumn("Ciudad");
		modelTablaSucursales.addColumn("Dirección");
		modelTablaSucursales.addColumn("Codigo Postal");
		modelTablaSucursales.addColumn("Activo");

		tablaSucursales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// remueve el editor de la tabla de sucursales
		DataTools.removerEditorDeTabla(tablaSucursales, modelTablaSucursales);

		panelSucursalCentralBotones = new JPanel();
		panelSucursalCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_8 = (FlowLayout) panelSucursalCentralBotones.getLayout();
		flowLayout_8.setAlignment(FlowLayout.RIGHT);
		panelSucursalCentral.add(panelSucursalCentralBotones, BorderLayout.NORTH);

		btnNuevaSucursal = new JButton("Agregar");
		btnNuevaSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormSucursales(0, 0);
			}
		});
		btnNuevaSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		btnNuevaSucursal.setBackground(new Color(152, 251, 152));
		panelSucursalCentralBotones.add(btnNuevaSucursal);

		btnActualizarSucursal = new JButton("Actualizar");
		btnActualizarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = DataTools.getIndiceElementoSeleccionado(tablaSucursales, modelTablaSucursales, 0);
				System.out.println(index);

				abrirFormSucursales(1, index);

			}
		});
		btnActualizarSucursal.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		btnActualizarSucursal.setBackground(new Color(0, 255, 127));
		panelSucursalCentralBotones.add(btnActualizarSucursal);

		btnEliminarSucursal = new JButton("Eliminar");

		btnEliminarSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));

		btnEliminarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aliminarSucursal();
			}
		});

		this.btnEliminarSucursal.setBackground(new Color(255, 51, 0));

		panelSucursalCentralBotones.add(btnEliminarSucursal);

		panelFormasDePago = new JPanel();
		panelFormasDePago.setForeground(new Color(255, 215, 0));
		panelPrincipalContenedor.add(panelFormasDePago, "panelFormasDePago");
		panelFormasDePago.setLayout(new BorderLayout(0, 0));

		panelEtiquetaFormasDePago = new JPanel();
		panelEtiquetaFormasDePago.setBackground(new Color(0, 0, 128));
		panelFormasDePago.add(panelEtiquetaFormasDePago, BorderLayout.NORTH);

		lblNewLabel_9 = new JLabel("Formas De Pago");
		lblNewLabel_9.setForeground(new Color(255, 255, 255));
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelEtiquetaFormasDePago.add(lblNewLabel_9);

		panelFormasDePagoCentral = new JPanel();
		panelFormasDePagoCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelFormasDePagoCentral.setBackground(new Color(255, 215, 0));
		panelFormasDePago.add(panelFormasDePagoCentral, BorderLayout.CENTER);
		panelFormasDePagoCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaFormasDePago = new JScrollPane();
		panelFormasDePagoCentral.add(scrollPaneTablaFormasDePago, BorderLayout.CENTER);

		modelTablaFormasDePago = new DefaultTableModel();

		modelTablaFormasDePago.addColumn("Id");
		modelTablaFormasDePago.addColumn("Forma de pago");
		modelTablaFormasDePago.addColumn("Activo");

		tablaFormasDePago = new JTable();
		tablaFormasDePago.setModel(modelTablaFormasDePago);
		scrollPaneTablaFormasDePago.setViewportView(tablaFormasDePago);

		// remueve el editor de la tabla de formas de pago
		for (int i = 0; i < modelTablaFormasDePago.getColumnCount(); i++) {
			Class<?> colClass = modelTablaFormasDePago.getColumnClass(i);
			tablaFormasDePago.setDefaultEditor(colClass, null);
		}

		panelFormasDePagoCentralBotones = new JPanel();
		FlowLayout flowLayout_9 = (FlowLayout) panelFormasDePagoCentralBotones.getLayout();
		flowLayout_9.setAlignment(FlowLayout.RIGHT);
		panelFormasDePagoCentralBotones.setBackground(new Color(255, 215, 0));
		panelFormasDePagoCentral.add(panelFormasDePagoCentralBotones, BorderLayout.NORTH);

		btnNuevaFormaDePago = new JButton("Agregar");
		btnNuevaFormaDePago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
		btnNuevaFormaDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormDatosFormasDePago(1, 0);
			}
		});
		this.btnNuevaFormaDePago.setBackground(new Color(152, 251, 152));
		panelFormasDePagoCentralBotones.add(btnNuevaFormaDePago);

		btnActualizarFormaDePago = new JButton("Actualizar");
		btnActualizarFormaDePago.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/actualizar_ico.png")));
		btnActualizarFormaDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormDatosFormasDePago(2,
						DataTools.getIndiceElementoSeleccionado(tablaFormasDePago, modelTablaFormasDePago, 0));
			}
		});
		this.btnActualizarFormaDePago.setBackground(new Color(152, 251, 152));
		panelFormasDePagoCentralBotones.add(btnActualizarFormaDePago);

		btnEliminarFormaPago = new JButton("Eliminar");
		btnEliminarFormaPago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarFormaDePago();
			}
		});
		btnEliminarFormaPago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
		btnEliminarFormaPago.setBackground(new Color(255, 51, 0));
		panelFormasDePagoCentralBotones.add(btnEliminarFormaPago);

		panelSuperiorBotones = new JPanel();
		panelSuperiorBotones.setBackground(new Color(255, 140, 0));
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelSuperiorBotones, BorderLayout.NORTH);

		btn_irAInicio = new JButton("Inicio");
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

		btnCalculadora = new JButton("Calculadora");
		btnCalculadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCalculadora();
			}
		});
		btnCalculadora.setBackground(new Color(135, 206, 250));
		btnCalculadora.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/Calculadora.png")));
		panelSuperiorBotones.add(btnCalculadora);

		btn_irAVentas = new JButton("Ventas");
		btn_irAVentas.setBackground(new Color(204, 255, 102));
		btn_irAVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirFormVentas(sucursal.getIdSucursal());

			}
		});
		btn_irAVentas.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/resources/ventagr.png")));
		panelSuperiorBotones.add(btn_irAVentas);

		DataTools.definirTamanioDeColumnas(tableEmpleadosColumnsWidth, tableEmpleados);

		DataTools.definirTamanioDeColumnas(tablaProveedoresColumnsWidth, tablaProveedores);

		DataTools.definirTamanioDeColumnas(tablaArticulosColumnsWidth, tablaArticulos);

		DataTools.definirTamanioDeColumnas(tablaClientesColumnsWidth, tablaClientes);

		DataTools.definirTamanioDeColumnas(tablaVentasColumnsWidth, tablaVentas);

		DataTools.definirTamanioDeColumnas(tablaSucursalesColumnWidth, tablaSucursales);

		DataTools.definirTamanioDeColumnas(tablaCategoriaColumnsWidth, tablaCategorias);

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void abrirFormLogin() {
		Component cm = this;
		this.dispose();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Fr_LogIn frame = new Fr_LogIn();
				frame.setLocationRelativeTo(cm);
				frame.setVisible(true);
			}
		});
	}

	private void cerrarFormPrincipal() {
		this.dispose();
		System.exit(0);
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
	 * Llena el JTable correspondiente con los registro de las sucursales extraidos
	 * de la base de datos
	 */
	private void llenarTablaSucursales() {
		this.modelTablaSucursales.getDataVector().removeAllElements();
		this.tablaSucursales.updateUI();
		this.sucursalController.verSucursalesEnTabla(modelTablaSucursales);
	}

	/**
	 * llena el jTable del panel de empleados con todos los registros encontrados en
	 * la bd
	 */
	private void llenarTablaEmpleados() {
		this.borrarElementosDeLaTablaEmpleados();
		empleadoController.verEmpleadosEnTabla(modelTablaEmpleados);
	}

	private void eliminarEmpleado() {

		int indiceEmpleadoSeleccionado = -1;
		int input = JOptionPane.showConfirmDialog(this, "¿Desea eliminar el registro seleccionado?", "Error",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		try {

			indiceEmpleadoSeleccionado = DataTools.getIndiceElementoSeleccionado(tableEmpleados, modelTablaEmpleados,
					0);
			this.empleadoController.eliminarEmpleado(indiceEmpleadoSeleccionado);

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void llenarTablaProveedor() {
		this.borrarElementosDeLaTablaProveedor();
		proveedorController.verProveedoresEnTabla(modelTablaProveedores);
	}

	/**
	 * borra todos los elementos contenidos en la tabla categorias
	 */
	private void borrarElementosDeLaTablaCategorias() {
		this.modelTablaCategoriaArticulo.getDataVector().removeAllElements();
		this.tablaCategorias.updateUI();
	}

	private void borrarElementosDeLaTablaProveedor() {
		this.modelTablaProveedores.getDataVector().removeAllElements();
		this.tablaProveedores.updateUI();
	}

	/**
	 * borra todos los elementos contenidos en la tabla empleados
	 */
	private void borrarElementosDeLaTablaEmpleados() {
		this.modelTablaEmpleados.getDataVector().removeAllElements();
		this.tableEmpleados.updateUI();
	}

	/**
	 * modifica el estatus de activo a inactivo de un proveedor registrado en la bd
	 */
	private void eliminarProveedor() {

		int input = JOptionPane.showConfirmDialog(this, "Desea eliminar el registro", "Eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

		if (input > 0) {
			return;
		}

		proveedorController
				.eliminarProveedor(DataTools.getIndiceElementoSeleccionado(tablaProveedores, modelTablaProveedores, 0));

		JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar", JOptionPane.INFORMATION_MESSAGE);
	}

	private void abrirVentanaFormularioProveedor(int opcion, int indiceProveedor) {

		Component cm = this;

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Fr_DatosProveedor fr = new Fr_DatosProveedor(opcion, indiceProveedor);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);

				} catch (Exception er) {
					er.printStackTrace();
				}

			}

		});
	}

	private void abrirVentanaFormularioArticulo(int opcion, int idArticulo, int sucursal) {

		Component cm = this;

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {

				try {

					Fr_DatosArticulo fr = new Fr_DatosArticulo(opcion, idArticulo, sucursal);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);

				} catch (Exception er) {
					er.printStackTrace();
				}

			}

		});

	}

	private void abrirFormularioEmpleados(int opcion, int idEmpleado) {
		Component cm = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					Fr_DatosEmpleado fr = new Fr_DatosEmpleado(opcion, idEmpleado);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);

				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}

	private void abrirVentanaFormularioCategoria(int opcion, int idCategoria) {
		Component cm = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Fr_DatosCategoria fr = new Fr_DatosCategoria(opcion, idCategoria);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);
					fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}

	private void llenarTablaArticulos() {
		this.borrarElementosDeLaTablaArticulos();
		articuloController.verArticulosEnTabla(modelTablaArticulos, this.sucursal.getIdSucursal());
	}

	/**
	 * borra todos los datos de la tabla de articulos
	 */
	private void borrarElementosDeLaTablaArticulos() {
		this.modelTablaArticulos.getDataVector().removeAllElements();
		this.tablaArticulos.updateUI();
	}

	private void consultarArticulosPorNombre() {
		this.borrarElementosDeLaTablaArticulos();
		articuloController.consultarArticulosPorNombre(this.txfBuscarArticulo.getText(), modelTablaArticulos,
				opcionDeBusquedaDeArticulo(), this.sucursal.getIdSucursal());
	}

	/**
	 * de acuer al radioButton selecionado será el tipo de busqueda de articulo
	 * 
	 * @return
	 */
	private int opcionDeBusquedaDeArticulo() {

		if (this.rdbBuscarArtPorNombre.isSelected()) {
			return 1;
		} else if (this.rdbtBuscarArtPorProveedor.isSelected()) {
			return 2;
		} else if (this.rdbtBuscarArtPorCategoria.isSelected()) {
			return 3;
		} else if (this.rdbtBuscarArtPorCodigo.isSelected()) {
			return 4;
		} else {
			return 5;
		}

	}

	private void borrarElementosDeLaTablaClientes() {
		this.modelTablaClientes.getDataVector().removeAllElements();
		this.tablaClientes.updateUI();
	}

	private void llenarTablaClientes() {
		this.borrarElementosDeLaTablaClientes();
		clientesController.verClientesEnTabla(modelTablaClientes);
	}

	/**
	 * abre la calculadora, valido unicamente en windows
	 */
	private void abrirCalculadora() {
		try {
			Runtime.getRuntime().exec("calc");
		} catch (Exception er) {
			er.printStackTrace();
		}
	}

	/**
	 * abre el formulario del punto de ventas
	 */
	private void abrirFormVentas(int idSucursal) {
		Component cm = this;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				com.kathsoft.kathpos.app.view.Fr_PuntoDeVentas fr = new com.kathsoft.kathpos.app.view.Fr_PuntoDeVentas(
						idSucursal);
				fr.setLocationRelativeTo(cm);
				fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr.setVisible(true);
			}
		});
	}

	private void abrirFormClientes(int tipoOperacion, int indiceCliente) {
		Component cm = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Fr_DatosCliente frame = new Fr_DatosCliente(tipoOperacion, indiceCliente);
				frame.setLocationRelativeTo(cm);
				frame.setVisible(true);
			}
		});
	}

	private void eliminarCliente() {

		int input = JOptionPane.showConfirmDialog(this, "Desea eliminara el registro?", "Eliminar Cliente",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		this.clientesController.eliminarCliente(
				DataTools.getIndiceElementoSeleccionado(this.tablaClientes, this.modelTablaClientes, 0));

		JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar Cliente", JOptionPane.INFORMATION_MESSAGE);

	}

	private void abrirFormSucursales(int opcion, int idSucursal) {
		Component cm = null;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Fr_DatosSucursal frame = new Fr_DatosSucursal(opcion, idSucursal);
					frame.setLocationRelativeTo(cm);
					frame.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}

	private void llenarTablaVentas(int opcion) {
		this.borrarElementosDeLaTablaVentas();
		ventasController.verVentasEnTabla(modelTablaVentas, opcion, this.sucursal.getIdSucursal());
	}

	private void borrarElementosDeLaTablaVentas() {
		this.modelTablaVentas.getDataVector().removeAllElements();
		this.tablaVentas.updateUI();
	}

	private void llenarTablaFormasDePago() {
		this.modelTablaFormasDePago.getDataVector().removeAllElements();
		this.tablaFormasDePago.updateUI();
		this.formasDePagoController.verFormasDePagoEnTabla(this.modelTablaFormasDePago);
	}

	/**
	 * en función del RadioButton seleccionado retorna el criterio de busqueda de
	 * las ventas registradas en la base de datos
	 * 
	 * @return el criterio de busqueda
	 */
	private int opcionDeBusquedaDeVenta() {

		if (this.rdbBuscarVtPorId.isSelected()) {
			return 1;
		}
		if (this.rdbBuscarVtPorEmpleado.isSelected()) {
			return 2;
		}
		if (this.rdbBuscarVtPorCliente.isSelected()) {
			return 3;
		}

		return 4;

	}

	/**
	 * busca las ventas en la db de acuerdo al RadioButton seleccionado
	 */
	private void buscarVentasPor() {
		this.borrarElementosDeLaTablaVentas();
		this.ventasController.buscarVentasPor(modelTablaVentas, this.txfBuscarVenta.getText(),
				this.opcionDeBusquedaDeVenta());
	}

	private void abrirFormDatosFormasDePago(int opcion, int idFormaDePago) {

		Component cm = null;
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Fr_DatosFormaDePago frame = new Fr_DatosFormaDePago(opcion, idFormaDePago);
					frame.setLocationRelativeTo(cm);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception er) {
					er.printStackTrace();
				}

			}
		});

	}

	private void eliminarFormaDePago() {

		int input = JOptionPane.showConfirmDialog(null, "Desea eliminar este registro", "Eliminar Forma de Pago",
				JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		this.formasDePagoController.eliminarFormaDepAgo(
				DataTools.getIndiceElementoSeleccionado(tablaFormasDePago, modelTablaFormasDePago, 0));

		JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar Forma de Pago",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void aliminarSucursal() {

		int input = JOptionPane.showConfirmDialog(this, "Desea eliminara el registro?", "Eliminar Sucursal",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		this.sucursalController
				.eliminarSucursal(DataTools.getIndiceElementoSeleccionado(tablaSucursales, modelTablaSucursales, 0));

		JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar Sucursal", JOptionPane.INFORMATION_MESSAGE);

	}

	private void eliminarArticulo() {
		int input = JOptionPane.showConfirmDialog(this, "Desea eliminara el registro?", "Eliminar Articulo",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		try {
			this.articuloController
			.eliminarArticulo(DataTools.getIndiceElementoSeleccionado(tablaArticulos, modelTablaArticulos, 0));
			
			JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar Articulo", JOptionPane.INFORMATION_MESSAGE);
		}catch(SQLException er) {
			JOptionPane.showMessageDialog(this, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		
	}

	private void eliminarCategoria() {
		int input = JOptionPane.showConfirmDialog(this, "Desea eliminara el registro?", "Eliminar Categoría",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		this.categoriaController.eliminarCategoria(
				DataTools.getIndiceElementoSeleccionado(tablaCategorias, modelTablaCategoriaArticulo, 0));

		JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar Categoria",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void consultarCategoriaPorNombre() {
		this.modelTablaCategoriaArticulo.getDataVector().removeAllElements();
		this.tablaCategorias.updateUI();
		this.categoriaController.buscarCategoriaPorNombre(this.txfBuscarCategoria.getText(),
				modelTablaCategoriaArticulo);
	}

}
