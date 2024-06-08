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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.controller.VentasController;
import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.view.articulo.PanelArticulos;
import com.kathsoft.kathpos.app.view.clientes.PanelClientes;
import com.kathsoft.kathpos.app.view.clientes.PanelTipoCliente;
import com.kathsoft.kathpos.app.view.contabilidad.PanelCuentasContables;
import com.kathsoft.kathpos.app.view.empleados.Fr_DatosEmpleado;
import com.kathsoft.kathpos.app.view.empleados.PanelEmpleados;
import com.kathsoft.kathpos.app.view.marcas.Fr_DatosCategoria;
import com.kathsoft.kathpos.app.view.marcas.PanelMarcas;
import com.kathsoft.kathpos.app.view.proveedor.Fr_DatosProveedor;
import com.kathsoft.kathpos.app.view.proveedor.PanelProveedor;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class Fr_principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8970651466053472860L;

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
	 * 
	 * 
	 * 
	 */	
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

	private PanelArticulos panelArticulos;
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
					getClass().getResource("/com/kathsoft/kathpos/app/assets/control-inventario-erp-3.png")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	};
	// ============================================================================================
	// ============================================================================================
	private JButton btn_irAInicio;
	private PanelClientes panelClientes;
	private PanelEmpleados panelEmpleados;
	private PanelProveedor panelProveedor;
	private JMenuItem opcionMarcas;
	private PanelMarcas panelMarcas;
	private DefaultTableModel modelTablaVentas;
	private DefaultTableModel modelTablaFormasDePago;
	private DefaultTableModel modelTablaSucursales;	
	

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
	private JButton btnCalculadora;
	private ButtonGroup btnRadioGroupOrdernarVentas;
	private ButtonGroup btnRadioGroupBuscarVentas;
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
	private JButton btnEliminarSucursal;
	private JMenu menuReportes;
	private JMenu subMenuReportesExcel;
	private JMenu subMenuReportesPDF;
	private JMenu subMenuGraficas;
	private JMenuItem opcionReporteExcelArticulo;
	private JMenuItem opcionReporteExcelClientes;
	private JMenuItem opcionReporteExcelEmpleados;
	private JMenuItem opcionReporteExcelProveedores;
	private JMenuItem opcionReporteExcelVentas;
	private JMenu menuConsultaClientes;
	private JMenuItem opcionTipoClientes;
	private PanelTipoCliente panelTipoCliente;
	private PanelCuentasContables panelConta;
	private JMenu menuContable;
	private JMenuItem opcionCatalogoCuentas;

	/**
	 * Create the frame.
	 */
	public Fr_principal(Sucursal sucursal) {

		this.sucursal = sucursal;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/1643231.png")));
		setTitle("Kath POS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1150, 601);

		BarraMenu = new JMenuBar();
		BarraMenu.setBackground(new Color(255, 153, 0));
		setJMenuBar(BarraMenu);

		menuConsultar = new JMenu("Consultar Catalogo");
		menuConsultar.setIcon(new ImageIcon(Fr_principal.class
				.getResource("/com/kathsoft/kathpos/app/assets/icone-point-d-interrogation-question-rouge.jpg")));
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

				panelArticulos.llenarCmbTipoCliente();
				panelArticulos.llenarTablaArticulos();
			}
		});
		opcionConsultarArticulos.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/productos_icono.jpg")));
		menuConsultar.add(opcionConsultarArticulos);

		menuConsultaClientes = new JMenu("Clientes");
		menuConsultaClientes.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/cliente_ico_catalog.png")));
		menuConsultar.add(menuConsultaClientes);

		opcionClientes = new JMenuItem("Catálogo Clientes");
		menuConsultaClientes.add(opcionClientes);
		opcionClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelClientes");
				panelPrincipalContenedor.updateUI();

				panelClientes.llenarTablaClientes();
			}
		});
		opcionClientes.setIcon(new ImageIcon(Fr_principal.class.getResource(
				"/com/kathsoft/kathpos/app/assets/pngtree-call-center-customer-icon-png-image_4746069.jpg")));

		opcionTipoClientes = new JMenuItem("Categoria Cliente");
		opcionTipoClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelTipoCliente");
				panelPrincipalContenedor.updateUI();

				panelTipoCliente.llenarTablaTipoCliente();
			}
		});

		opcionTipoClientes.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/cliente_categoria_ico.png")));
		menuConsultaClientes.add(opcionTipoClientes);

		opcionEmpleados = new JMenuItem("Empleados");
		opcionEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// ir al panel de empleados dentro del CardLayout
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelEmpleados");
				panelPrincipalContenedor.updateUI();

				panelEmpleados.llenarTablaEmpleados();
			}
		});
		opcionEmpleados.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/empleados.jpg")));
		menuConsultar.add(opcionEmpleados);

		opcionProveedores = new JMenuItem("Proveedores");
		opcionProveedores.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelProveedor");

				panelProveedor.llenarTablaProveedor("");
			}
		});
		opcionProveedores.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/proveedores.png")));
		menuConsultar.add(opcionProveedores);

		opcionMarcas = new JMenuItem("Categorias");
		opcionMarcas.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/iconoMarca.png")));
		opcionMarcas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelMarcas");
				panelPrincipalContenedor.updateUI();

				panelMarcas.llenarTablaCategoria("");
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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/sucursal.jpg")));
		menuConsultar.add(opcionSucursales);

		opcionFormasDePago = new JMenuItem("Formas De Pago");
		opcionFormasDePago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/formas_de_pago.png")));
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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/cerrarSesion.png")));
		menuConsultar.add(opcionCerrarSesion);

		opcionSalirDelSistema = new JMenuItem("Salir");
		opcionSalirDelSistema.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarFormPrincipal();
			}
		});
		opcionSalirDelSistema.setBackground(new Color(255, 0, 51));
		opcionSalirDelSistema
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/cerrar.png")));
		menuConsultar.add(opcionSalirDelSistema);

		menuOperaciones = new JMenu("Operaciones");
		menuOperaciones.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/operaciones.png")));
		menuOperaciones.setFont(new Font("Segoe UI", Font.BOLD, 13));
		BarraMenu.add(menuOperaciones);

		subMenuVentas = new JMenu("Ventas");
		subMenuVentas
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/ventas.png")));
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

		menuReportes = new JMenu("Reportes");
		menuReportes.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/reportes.jpg")));
		BarraMenu.add(menuReportes);

		subMenuReportesExcel = new JMenu("Exportar Tabla");
		subMenuReportesExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		menuReportes.add(subMenuReportesExcel);

		opcionReporteExcelArticulo = new JMenuItem("Articulos");
		opcionReporteExcelArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelArticulos.exportarArticuloExcel();
			}
		});
		opcionReporteExcelArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/productos_icono.jpg")));
		subMenuReportesExcel.add(opcionReporteExcelArticulo);

		opcionReporteExcelClientes = new JMenuItem("Clientes");
		opcionReporteExcelClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelClientes.exportarClientesExcel();
			}
		});
		opcionReporteExcelClientes.setIcon(new ImageIcon(Fr_principal.class.getResource(
				"/com/kathsoft/kathpos/app/assets/pngtree-call-center-customer-icon-png-image_4746069.jpg")));
		subMenuReportesExcel.add(opcionReporteExcelClientes);

		opcionReporteExcelEmpleados = new JMenuItem("Empleados");
		opcionReporteExcelEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelEmpleados.exportarEmpleadosExcel();
			}
		});
		opcionReporteExcelEmpleados.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/empleados.jpg")));
		subMenuReportesExcel.add(opcionReporteExcelEmpleados);

		opcionReporteExcelProveedores = new JMenuItem("Proveedores");
		opcionReporteExcelProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelProveedor.exportarProveedoresExcel();
			}
		});
		opcionReporteExcelProveedores.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/proveedores.png")));
		subMenuReportesExcel.add(opcionReporteExcelProveedores);

		opcionReporteExcelVentas = new JMenuItem("Ventas");
		opcionReporteExcelVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarVentaExcel();
			}
		});
		opcionReporteExcelVentas
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/ventas.png")));
		subMenuReportesExcel.add(opcionReporteExcelVentas);

		subMenuReportesPDF = new JMenu("PDF");
		subMenuReportesPDF
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/pdfLogo.jpg")));
		menuReportes.add(subMenuReportesPDF);

		subMenuGraficas = new JMenu("Analisis Grafico");
		subMenuGraficas
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/grafico.png")));
		menuReportes.add(subMenuGraficas);

		menuContable = new JMenu("Contabilidad");
		BarraMenu.add(menuContable);

		this.opcionCatalogoCuentas = new JMenuItem();
		opcionCatalogoCuentas.setText("Cuentas");

		opcionCatalogoCuentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelConta");
				panelPrincipalContenedor.updateUI();

				panelConta.llenarTablaCuentas();
			}
		});
		menuContable.add(opcionCatalogoCuentas);

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

		/**
		 * ====================================================================================
		 * ====================================================================================
		 * 
		 * Panel articulos
		 * ====================================================================================
		 * ====================================================================================
		 */
		panelArticulos = new PanelArticulos(sucursal);
		panelPrincipalContenedor.add(panelArticulos, "panelArticulos");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelClientes = new PanelClientes();
		panelPrincipalContenedor.add(panelClientes, "panelClientes");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelEmpleados = new PanelEmpleados();
		panelPrincipalContenedor.add(panelEmpleados, "panelEmpleados");
		
		
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelProveedor = new PanelProveedor();
		panelPrincipalContenedor.add(panelProveedor, "panelProveedor");
		
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelMarcas = new PanelMarcas();
		panelPrincipalContenedor.add(panelMarcas, "panelMarcas");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// '=======================================================================================================================================
		
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
		btNuevaVenta
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/ventas.png")));
		btNuevaVenta.setBackground(new Color(152, 251, 152));
		panelVentasCentralBotones.add(btNuevaVenta);

		btnExportarVentasExcel = new JButton("Exportar a Excel");
		btnExportarVentasExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarVentaExcel();
			}
		});
		btnExportarVentasExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
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
		DataTools.removerEditorDeTabla(tablaVentas, modelTablaVentas);

		/*
		 * for (int i = 0; i < modelTablaVentas.getColumnCount(); i++) { Class<?>
		 * colClass = tablaVentas.getColumnClass(i);
		 * tablaVentas.setDefaultEditor(colClass, null); }
		 */

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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnNuevaSucursal.setBackground(new Color(152, 251, 152));
		panelSucursalCentralBotones.add(btnNuevaSucursal);

		btnActualizarSucursal = new JButton("Actualizar");
		btnActualizarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = DataTools.getIndiceElementoSeleccionado(tablaSucursales, modelTablaSucursales, 0);
				// System.out.println(index);

				abrirFormSucursales(1, index);

			}
		});
		btnActualizarSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnActualizarSucursal.setBackground(new Color(0, 255, 127));
		panelSucursalCentralBotones.add(btnActualizarSucursal);

		btnEliminarSucursal = new JButton("Eliminar");

		btnEliminarSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));

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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnNuevaFormaDePago.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormDatosFormasDePago(1, 0);
			}
		});
		this.btnNuevaFormaDePago.setBackground(new Color(152, 251, 152));
		panelFormasDePagoCentralBotones.add(btnNuevaFormaDePago);

		btnActualizarFormaDePago = new JButton("Actualizar");
		btnActualizarFormaDePago.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/inicio_ico.jpg")));
		panelSuperiorBotones.add(btn_irAInicio);

		btnCalculadora = new JButton("Calculadora");
		btnCalculadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirCalculadora();
			}
		});
		btnCalculadora.setBackground(new Color(135, 206, 250));
		btnCalculadora.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/Calculadora.png")));
		panelSuperiorBotones.add(btnCalculadora);

		btn_irAVentas = new JButton("Ventas");
		btn_irAVentas.setBackground(new Color(204, 255, 102));
		btn_irAVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirFormVentas(sucursal.getIdSucursal());

			}
		});
		btn_irAVentas
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/ventagr.png")));
		panelSuperiorBotones.add(btn_irAVentas);

		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================

		panelTipoCliente = new PanelTipoCliente();
		panelPrincipalContenedor.add(panelTipoCliente, "panelTipoCliente");

		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================
		// =====================================================================================================================

		panelConta = new PanelCuentasContables();
		panelPrincipalContenedor.add(panelConta, "panelConta");

		opcionCatalogoCuentas = new JMenuItem("Cuentas");				

		DataTools.definirTamanioDeColumnas(tablaVentasColumnsWidth, tablaVentas);

		DataTools.definirTamanioDeColumnas(tablaSucursalesColumnWidth, tablaSucursales);		

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

	

	

	private void aliminarSucursal() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, "");

		if (input > 0) {
			return;
		}

		try {

			this.sucursalController.eliminarSucursal(
					DataTools.getIndiceElementoSeleccionado(tablaSucursales, modelTablaSucursales, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, "");

		} catch (SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}

	}

	/**
	 * borra todos los datos de la tabla de articulos
	 */

	
	

	

	private void borrarElementosDeLaTablaVentas() {
		this.modelTablaVentas.getDataVector().removeAllElements();
		this.tablaVentas.updateUI();
	}

		

	/**
	 * busca las ventas en la db de acuerdo al RadioButton seleccionado
	 */
	private void buscarVentasPor() {
		this.borrarElementosDeLaTablaVentas();
		this.ventasController.buscarVentasPor(modelTablaVentas, this.txfBuscarVenta.getText(),
				this.opcionDeBusquedaDeVenta());
	}

	private void cerrarFormPrincipal() {
		this.dispose();
		System.exit(0);
	}

	/*private void consultarCategoriaPorNombre() {
		this.modelTablaCategoriaArticulo.getDataVector().removeAllElements();
		this.tablaCategorias.updateUI();
		this.categoriaController.buscarCategoriaPorNombre(this.txfBuscarCategoria.getText(),
				modelTablaCategoriaArticulo);
	}*/

	

	

	private void eliminarFormaDePago() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, "");

		if (input > 0) {
			return;
		}

		try {
			this.formasDePagoController.eliminarFormaDepAgo(
					DataTools.getIndiceElementoSeleccionado(tablaFormasDePago, modelTablaFormasDePago, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, "");
		} catch (SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, " ", er.getMessage());
		}
	}

	

	

	

	private void exportarVentaExcel() {
		try {
			DataTools.exportarTablaExcel(modelTablaVentas, this);
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
			er.printStackTrace();
		}
	}

	

	

	private void llenarTablaFormasDePago() {
		this.modelTablaFormasDePago.getDataVector().removeAllElements();
		this.tablaFormasDePago.updateUI();
		this.formasDePagoController.verFormasDePagoEnTabla(this.modelTablaFormasDePago);
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

	private void llenarTablaVentas(int opcion) {
		this.borrarElementosDeLaTablaVentas();
		ventasController.verVentasEnTabla(modelTablaVentas, opcion, this.sucursal.getIdSucursal());
	}

	/**
	 * de acuer al radioButton selecionado será el tipo de busqueda de articulo
	 * 
	 * @return
	 */

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
}
