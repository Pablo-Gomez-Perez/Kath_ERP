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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.view.articulo.PanelArticulos;
import com.kathsoft.kathpos.app.view.clientes.PanelClientes;
import com.kathsoft.kathpos.app.view.clientes.PanelTipoCliente;
import com.kathsoft.kathpos.app.view.contabilidad.PanelCuentasContables;
import com.kathsoft.kathpos.app.view.empleados.PanelEmpleados;
import com.kathsoft.kathpos.app.view.formas_pago.PanelFormasDePago;
import com.kathsoft.kathpos.app.view.marcas.PanelMarcas;
import com.kathsoft.kathpos.app.view.proveedor.PanelProveedor;
import com.kathsoft.kathpos.app.view.sucursal.PanelSucursales;
import com.kathsoft.kathpos.app.view.ventas.PanelVentas;

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
	private JButton btnCalculadora;
	private PanelVentas panelVentas;
	private JPanel panelCompras;
	private JButton btn_irAVentas;
	private JSeparator separator;
	private JMenuItem opcionCerrarSesion;
	private JMenuItem opcionSalirDelSistema;
	private PanelSucursales panelSucursales;
	private JMenuItem opcionSucursales;
	private PanelFormasDePago panelFormasDePago;
	private JMenuItem opcionFormasDePago;
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

				panelSucursales.llenarTablaSucursales();
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

				panelFormasDePago.llenarTablaFormasDePago();

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
				panelVentas.abrirFormVentas(sucursal.getIdSucursal());
			}
		});
		subMenuVentas.add(opcionRegistrarVenta);

		opcionConsultarVenta = new JMenuItem("Consultar");
		opcionConsultarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				CardLayout cr = (CardLayout) panelPrincipalContenedor.getLayout();
				cr.show(panelPrincipalContenedor, "panelVentas");
				panelPrincipalContenedor.updateUI();

				panelVentas.llenarTablaVentas(1);
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
				panelVentas.exportarVentaExcel();
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
		// =======================================================================================================================================

		panelVentas = new PanelVentas(this.sucursal);
		panelPrincipalContenedor.add(panelVentas, "panelVentas");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelCompras = new JPanel();
		panelPrincipalContenedor.add(panelCompras, "panelCompras");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelSucursales = new PanelSucursales();
		panelPrincipalContenedor.add(panelSucursales, "panelSucursales");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================

		panelFormasDePago = new PanelFormasDePago();		
		panelPrincipalContenedor.add(panelFormasDePago, "panelFormasDePago");

		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		// =======================================================================================================================================
		
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

				panelVentas.abrirFormVentas(sucursal.getIdSucursal());

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

		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * abre la calculadora, valido unicamente en windows
	 */
	@SuppressWarnings("deprecation")
	private void abrirCalculadora() {
		try {
			Runtime.getRuntime().exec("calc");
		} catch (Exception er) {
			er.printStackTrace();
		}
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
	

}
