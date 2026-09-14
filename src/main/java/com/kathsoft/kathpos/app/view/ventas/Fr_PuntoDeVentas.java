package com.kathsoft.kathpos.app.view.ventas;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.VentasController;
import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.articulo.ArticuloByCodigo;
import com.kathsoft.kathpos.app.model.articulo.PrecioTipoCliente;
import com.kathsoft.kathpos.app.model.cliente.ClienteEnVentaById;
import com.kathsoft.kathpos.app.model.empleado.EmpleadoById;
import com.kathsoft.kathpos.app.model.interfaces.IListadoArticulosAcciones;
import com.kathsoft.kathpos.app.model.venta.ArticuloPorVenta;
import com.kathsoft.kathpos.app.model.venta.Venta;
import com.kathsoft.kathpos.app.model.venta.VentaConDetalle;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.view.formas_pago.Fr_FormasDePago;
import com.kathsoft.kathpos.app.view.shared.Fr_ListaArticulos;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;

public class Fr_PuntoDeVentas extends JFrame implements IListadoArticulosAcciones {

	private static final long serialVersionUID = 8197295139603781983L;
	private static final int COLUMNA_CODIGO = 0;	
	private static final int COLUMNA_PRECIO = 2;
	private static final int COLUMNA_CANTIDAD = 3;
	private static final int COLUMNA_DESCUENTO = 4;
	private static final int COLUMNA_SUBTOTAL = 5;
	private static final BigDecimal CIEN = new BigDecimal("100");
	private static final BigDecimal FACTOR_IVA = new BigDecimal("1.16");

	private int idSucursal;
	private ArticuloByCodigo articulo;
	private PrecioTipoCliente precioArticuloConsultado;
	private EmpleadoById empleado;
	private ClienteEnVentaById cliente;
	private List<ArticulosPorVentas> articulosVendidos;
	private final Map<String, ArticuloByCodigo> articulosPorCodigo = new HashMap<>();
	private final Map<String, PrecioTipoCliente> preciosPorCodigo = new HashMap<>();
	private final Map<String, Integer> cantidadesValidasPorCodigo = new HashMap<>();
	private final Map<String, BigDecimal> descuentosValidosPorCodigo = new HashMap<>();
	private boolean actualizandoTablaArticulo;
	private EmpleadoController empleadoController = new EmpleadoController();
	private ClientesController clienteController = new ClientesController();
	private VentasController ventasController = new VentasController();
	private ArticuloController articuloController = new ArticuloController();
	private DefaultTableModel modelTablaArticulo;
	private DefaultTableModel modelTablaExistencias;
	private JPanel contentPane;
	private JPanel panelSuperiorDatosVenta;
	private JPanel panelSuperiorDetallesVenta;
	private JLabel lblNewLabel;
	private JTextField txfIdVenta;
	private JButton btnBuscarVentaPorID;
	private JLabel lblFecha;
	private JFormattedTextField formattedTextFieldFechaVenta;
	private JPanel panelSuperiorDetalleEmpleado;
	private JLabel lblCajero;
	private JComboBox<JComboboxDataViewModel> cmbAliasEmpleado;
	private JTextField txfRfcEmpleado;
	private JLabel lblNombre;
	private JPanel panelSuperiorDetallesCliente;
	private JLabel lblCliente;
	private JComboBox<JComboboxDataViewModel> cmbAliasCliente;
	private JLabel lblNombre_1;
	private JTextField txfNombreCompletoCliente;
	private JLabel lblRfc;
	private JTextField txfRfcCliente;
	private JLabel lblCtaContbale;
	private JTextField txfNombreTipoCliente;
	private JPanel panelInferiorDatosArticulos;
	private JLabel lblArticulo;
	private JTextField txfCodigoNombreArticulo;
	private JLabel lblPrecioG;
	private JTextField textField;
	private JLabel lblPrecioM;
	private JTextField textField_1;
	private JButton btnBuscarArticulo;
	private JButton btnAgregar;
	private JLabel lblDescripcin;
	private JScrollPane scrollPaneDescripcionArticulo;
	private JTextArea textAreaDescripcionArticulo;
	private JLabel lblNewLabel_1;
	private JScrollPane scrollPaneExistenciaPorSucursal;
	private JTable tableExistenciaPorSucursal;
	private JScrollPane scrollPaneListadoArticulos;
	private JPanel panelDetallesSubtotales;
	private JTable tableListadoArticulos;
	private JButton btnEliminarArticuloSeleccionado;
	private JLabel lblPartidas;
	private JTextField txfNumeroDePartidas;
	private JLabel lblTotalDeArtculos;
	private JTextField txfTotalDeArticulos;
	private JLabel lblSubTotal;
	private JTextField txfSubtotalVenta;
	private JLabel lblIva;
	private JTextField txfIva;
	private JLabel lblTotal;
	private JTextField txfTotalVenta;
	private JButton btnCancelarSalir;
	private JButton btnCobrar;

	/**
	 * Create the frame.
	 */
	public Fr_PuntoDeVentas(int idSucursal) {

		this.idSucursal = idSucursal;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/ventagr.png")));
		setTitle("Punto de venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 730);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(null);

		setContentPane(contentPane);

		this.panelSuperiorDatosVenta = new JPanel();
		this.panelSuperiorDatosVenta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Datos Generales", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelSuperiorDatosVenta.setBackground(new Color(85, 223, 255));

		this.panelInferiorDatosArticulos = new JPanel();
		this.panelInferiorDatosArticulos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.panelInferiorDatosArticulos.setBackground(new Color(245, 194, 17));

		this.scrollPaneListadoArticulos = new JScrollPane();

		this.panelDetallesSubtotales = new JPanel();
		this.panelDetallesSubtotales.setBackground(new Color(255, 190, 111));
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelInferiorDatosArticulos, GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
						.addComponent(this.panelSuperiorDatosVenta, GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(this.scrollPaneListadoArticulos, GroupLayout.DEFAULT_SIZE, 775,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.panelDetallesSubtotales,
										GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addComponent(this.panelSuperiorDatosVenta, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.scrollPaneListadoArticulos, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
						.addComponent(this.panelDetallesSubtotales, GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))
				.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.panelInferiorDatosArticulos,
						GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));

		this.btnEliminarArticuloSeleccionado = new JButton("Eliminar Articulo");
		this.btnEliminarArticuloSeleccionado.setBackground(new Color(237, 51, 59));
		this.btnEliminarArticuloSeleccionado.addActionListener(e -> this.eliminarArticuloSeleccionado());

		this.lblPartidas = new JLabel("Partidas");

		this.txfNumeroDePartidas = new JTextField();
		this.txfNumeroDePartidas.setColumns(10);

		this.lblTotalDeArtculos = new JLabel("Total de artículos");

		this.txfTotalDeArticulos = new JTextField();
		this.txfTotalDeArticulos.setColumns(10);

		this.lblSubTotal = new JLabel("Sub Total");

		this.txfSubtotalVenta = new JTextField();
		this.txfSubtotalVenta.setColumns(10);

		this.lblIva = new JLabel("I.V.A.");

		this.txfIva = new JTextField();
		this.txfIva.setColumns(10);

		this.lblTotal = new JLabel("Total");

		this.txfTotalVenta = new JTextField();
		this.txfTotalVenta.setColumns(10);

		this.btnCancelarSalir = new JButton("Cancelar");
		this.btnCancelarSalir.addActionListener(e -> {
			
			int result = JOptionPane.showConfirmDialog(this, "Está seguro de cerrar el punto de venta? la información capturada se perderá", "Salir?", JOptionPane.YES_NO_OPTION);
			
			if(result == JOptionPane.YES_OPTION) this.dispose();
			
			return;
			
		});
		this.btnCancelarSalir.setBackground(new Color(246, 97, 81));

		this.btnCobrar = new JButton("Cobrar");
		this.btnCobrar.setBackground(new Color(87, 227, 137));
		this.btnCobrar.addActionListener(e -> this.prepararCobro());
		GroupLayout gl_panelDetallesSubtotales = new GroupLayout(this.panelDetallesSubtotales);
		gl_panelDetallesSubtotales.setHorizontalGroup(gl_panelDetallesSubtotales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetallesSubtotales.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelDetallesSubtotales.createParallelGroup(Alignment.LEADING)
								.addComponent(this.btnEliminarArticuloSeleccionado, GroupLayout.DEFAULT_SIZE, 202,
										Short.MAX_VALUE)
								.addComponent(this.lblPartidas)
								.addComponent(this.txfNumeroDePartidas, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(this.lblTotalDeArtculos)
								.addComponent(this.txfTotalDeArticulos, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(this.lblSubTotal)
								.addComponent(this.txfSubtotalVenta, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(this.lblIva)
								.addComponent(this.txfIva, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(this.lblTotal)
								.addComponent(this.txfTotalVenta, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(this.btnCancelarSalir, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
								.addComponent(this.btnCobrar, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelDetallesSubtotales.setVerticalGroup(gl_panelDetallesSubtotales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetallesSubtotales.createSequentialGroup().addContainerGap()
						.addComponent(this.btnEliminarArticuloSeleccionado)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.lblPartidas)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.txfNumeroDePartidas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblTotalDeArtculos)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.txfTotalDeArticulos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblSubTotal)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.txfSubtotalVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblIva)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.txfIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblTotal)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.txfTotalVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btnCancelarSalir)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btnCobrar)
						.addContainerGap(15, Short.MAX_VALUE)));
		this.panelDetallesSubtotales.setLayout(gl_panelDetallesSubtotales);

		this.tableListadoArticulos = new JTable();
		this.scrollPaneListadoArticulos.setViewportView(this.tableListadoArticulos);

		this.lblArticulo = new JLabel("Artículo");

		this.txfCodigoNombreArticulo = new JTextField();
		this.txfCodigoNombreArticulo.setColumns(10);
		this.txfCodigoNombreArticulo.addActionListener(e -> this.procesarEnterArticulo());

		this.lblPrecioG = new JLabel("Precio G.");

		this.textField = new JTextField();
		this.textField.setEditable(false);
		this.textField.setColumns(10);

		this.lblPrecioM = new JLabel("Precio M.");

		this.textField_1 = new JTextField();
		this.textField_1.setEditable(false);
		this.textField_1.setColumns(10);

		this.btnBuscarArticulo = new JButton("");
		this.btnBuscarArticulo.setBackground(new Color(181, 131, 90));
		this.btnBuscarArticulo.setIcon(
				new ImageIcon(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		this.btnBuscarArticulo.addActionListener(e -> this.buscarArticulo());

		this.btnAgregar = new JButton("");
		this.btnAgregar.setBackground(new Color(87, 227, 137));
		this.btnAgregar.setFont(new Font("Dialog", Font.BOLD, 9));
		this.btnAgregar.setIcon(
				new ImageIcon(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnAgregar.addActionListener(e -> this.agregarArticuloConsultado());

		this.lblDescripcin = new JLabel("Descripción");

		this.scrollPaneDescripcionArticulo = new JScrollPane();

		this.lblNewLabel_1 = new JLabel("Existencias");

		this.scrollPaneExistenciaPorSucursal = new JScrollPane();
		GroupLayout gl_panelInferiorDatosArticulos = new GroupLayout(this.panelInferiorDatosArticulos);
		gl_panelInferiorDatosArticulos.setHorizontalGroup(gl_panelInferiorDatosArticulos
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
										.addComponent(this.scrollPaneDescripcionArticulo, GroupLayout.DEFAULT_SIZE, 461,
												Short.MAX_VALUE)
										.addGap(18).addComponent(this.scrollPaneExistenciaPorSucursal,
												GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panelInferiorDatosArticulos.createSequentialGroup()
										.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
														.addComponent(this.lblArticulo)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this.txfCodigoNombreArticulo,
																GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
														.addComponent(this.lblDescripcin).addGap(410)))
										.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
												.addComponent(this.lblNewLabel_1)
												.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
														.addComponent(this.btnBuscarArticulo,
																GroupLayout.PREFERRED_SIZE, 50,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this.lblPrecioG, GroupLayout.PREFERRED_SIZE, 61,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this.textField, GroupLayout.DEFAULT_SIZE, 112,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this.lblPrecioM)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this.textField_1, GroupLayout.DEFAULT_SIZE, 115,
																Short.MAX_VALUE)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(this.btnAgregar)))))
						.addContainerGap()));
		gl_panelInferiorDatosArticulos.setVerticalGroup(gl_panelInferiorDatosArticulos
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.TRAILING)
										.addComponent(this.btnAgregar)
										.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.BASELINE)
												.addComponent(this.lblArticulo)
												.addComponent(this.txfCodigoNombreArticulo, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(this.textField_1, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(this.lblPrecioM)
												.addComponent(this.textField, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(this.lblPrecioG)))
								.addComponent(this.btnBuscarArticulo, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
								.addComponent(this.lblDescripcin).addComponent(this.lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
								.addComponent(this.scrollPaneExistenciaPorSucursal, GroupLayout.DEFAULT_SIZE, 85,
										Short.MAX_VALUE)
								.addComponent(this.scrollPaneDescripcionArticulo, GroupLayout.DEFAULT_SIZE, 85,
										Short.MAX_VALUE))
						.addContainerGap()));

		this.tableExistenciaPorSucursal = new JTable();
		this.scrollPaneExistenciaPorSucursal.setViewportView(this.tableExistenciaPorSucursal);

		this.textAreaDescripcionArticulo = new JTextArea();
		this.scrollPaneDescripcionArticulo.setViewportView(this.textAreaDescripcionArticulo);
		this.panelInferiorDatosArticulos.setLayout(gl_panelInferiorDatosArticulos);

		this.panelSuperiorDetallesVenta = new JPanel();
		this.panelSuperiorDetallesVenta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Inf. de Venta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelSuperiorDetallesVenta.setBackground(new Color(85, 223, 255));

		this.panelSuperiorDetalleEmpleado = new JPanel();
		this.panelSuperiorDetalleEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Atiende", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelSuperiorDetalleEmpleado.setBackground(new Color(85, 223, 255));

		this.panelSuperiorDetallesCliente = new JPanel();
		this.panelSuperiorDetallesCliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true),
				"Detalles de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelSuperiorDetallesCliente.setBackground(new Color(85, 223, 255));
		GroupLayout gl_panelSuperiorDatosVenta = new GroupLayout(this.panelSuperiorDatosVenta);
		gl_panelSuperiorDatosVenta.setHorizontalGroup(gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDatosVenta.createSequentialGroup().addContainerGap()
						.addComponent(this.panelSuperiorDetallesVenta, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.panelSuperiorDetalleEmpleado, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.panelSuperiorDetallesCliente, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
						.addContainerGap()));
		gl_panelSuperiorDatosVenta.setVerticalGroup(
				gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.TRAILING).addGroup(Alignment.LEADING,
						gl_panelSuperiorDatosVenta.createSequentialGroup()
								.addGroup(gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.TRAILING)
										.addComponent(this.panelSuperiorDetallesCliente, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
										.addComponent(this.panelSuperiorDetalleEmpleado, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
										.addComponent(this.panelSuperiorDetallesVenta, Alignment.LEADING,
												GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
								.addGap(18)));

		this.lblCliente = new JLabel("Cliente");

		this.cmbAliasCliente = new JComboBox<JComboboxDataViewModel>();
		this.cmbAliasCliente.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				this.consultarClienteSeleccionado();
			}
		});

		this.lblNombre_1 = new JLabel("Nombre");

		this.txfNombreCompletoCliente = new JTextField();
		this.txfNombreCompletoCliente.setColumns(10);

		this.lblRfc = new JLabel("RFC");

		this.txfRfcCliente = new JTextField();
		this.txfRfcCliente.setColumns(10);

		this.lblCtaContbale = new JLabel("T. Cliente");

		this.txfNombreTipoCliente = new JTextField();
		this.txfNombreTipoCliente.setColumns(10);
		GroupLayout gl_panelSuperiorDetallesCliente = new GroupLayout(this.panelSuperiorDetallesCliente);
		gl_panelSuperiorDetallesCliente.setHorizontalGroup(gl_panelSuperiorDetallesCliente
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
										.addComponent(this.lblCliente).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.cmbAliasCliente, GroupLayout.PREFERRED_SIZE, 110,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSuperiorDetallesCliente
										.createSequentialGroup().addComponent(this.lblRfc)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.txfRfcCliente)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
										.addComponent(this.lblNombre_1).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.txfNombreCompletoCliente, GroupLayout.DEFAULT_SIZE, 152,
												Short.MAX_VALUE))
								.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
										.addComponent(this.lblCtaContbale).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.txfNombreTipoCliente, GroupLayout.DEFAULT_SIZE, 145,
												Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panelSuperiorDetallesCliente
				.setVerticalGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
								.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.cmbAliasCliente, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblCliente).addComponent(this.lblNombre_1)
										.addComponent(this.txfNombreCompletoCliente, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.lblRfc)
										.addComponent(this.txfRfcCliente, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblCtaContbale).addComponent(this.txfNombreTipoCliente,
												GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		this.panelSuperiorDetallesCliente.setLayout(gl_panelSuperiorDetallesCliente);

		this.lblCajero = new JLabel("Cajero");

		this.cmbAliasEmpleado = new JComboBox<JComboboxDataViewModel>();
		this.cmbAliasEmpleado.addItemListener(e -> {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				this.consultarEmpleadoSeleccionado();
			}
		});

		this.txfRfcEmpleado = new JTextField();
		this.txfRfcEmpleado.setColumns(10);

		this.lblNombre = new JLabel("Nombre");
		GroupLayout gl_panelSuperiorDetalleEmpleado = new GroupLayout(this.panelSuperiorDetalleEmpleado);
		gl_panelSuperiorDetalleEmpleado.setHorizontalGroup(gl_panelSuperiorDetalleEmpleado
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetalleEmpleado.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.LEADING)
								.addComponent(this.lblCajero).addComponent(this.lblNombre))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.LEADING)
								.addComponent(this.cmbAliasEmpleado, 0, 266, Short.MAX_VALUE)
								.addComponent(this.txfRfcEmpleado, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panelSuperiorDetalleEmpleado.setVerticalGroup(gl_panelSuperiorDetalleEmpleado
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetalleEmpleado.createSequentialGroup()
						.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.lblCajero)
								.addComponent(this.cmbAliasEmpleado, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblNombre))
						.addContainerGap(37, Short.MAX_VALUE)));
		this.panelSuperiorDetalleEmpleado.setLayout(gl_panelSuperiorDetalleEmpleado);

		this.lblNewLabel = new JLabel("Id");

		this.txfIdVenta = new JTextField();
		this.txfIdVenta.setColumns(10);

		this.btnBuscarVentaPorID = new JButton("");
		this.btnBuscarVentaPorID.setIcon(
				new ImageIcon(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));

		this.lblFecha = new JLabel("Fecha");

		this.formattedTextFieldFechaVenta = new JFormattedTextField();
		GroupLayout gl_panelSuperiorDetallesVenta = new GroupLayout(this.panelSuperiorDetallesVenta);
		gl_panelSuperiorDetallesVenta.setHorizontalGroup(gl_panelSuperiorDetallesVenta
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
										.addComponent(this.lblNewLabel).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.txfIdVenta, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.btnBuscarVentaPorID, GroupLayout.PREFERRED_SIZE, 50,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
										.addComponent(this.lblFecha).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.formattedTextFieldFechaVenta, GroupLayout.DEFAULT_SIZE, 138,
												Short.MAX_VALUE)))
						.addContainerGap()));
		gl_panelSuperiorDetallesVenta.setVerticalGroup(gl_panelSuperiorDetallesVenta
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
						.addGroup(gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.lblNewLabel)
								.addComponent(this.txfIdVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.btnBuscarVentaPorID, GroupLayout.PREFERRED_SIZE, 21,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.lblFecha).addComponent(this.formattedTextFieldFechaVenta,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(42, Short.MAX_VALUE)));
		this.panelSuperiorDetallesVenta.setLayout(gl_panelSuperiorDetallesVenta);
		this.panelSuperiorDatosVenta.setLayout(gl_panelSuperiorDatosVenta);
		this.contentPane.setLayout(gl_contentPane);
		this.llenarCmbEmpleados();
		this.llenarCmbClientes();

		this.modelTablaArticulo = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == COLUMNA_CANTIDAD || column == COLUMNA_DESCUENTO;
			}
		};

		this.modelTablaArticulo.addColumn("Codigo");
		this.modelTablaArticulo.addColumn("Descripción");
		this.modelTablaArticulo.addColumn("Precio");
		this.modelTablaArticulo.addColumn("Cantidad");
		this.modelTablaArticulo.addColumn("Descuento");
		this.modelTablaArticulo.addColumn("Subtotal");
		this.tableListadoArticulos.setModel(this.modelTablaArticulo);
		this.configurarEditoresTablaArticulos();
		this.configurarEventosTablaArticulos();
		this.configurarAtajoEliminarArticulo();

		this.modelTablaExistencias = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		this.modelTablaExistencias.addColumn("Sucursal");
		this.modelTablaExistencias.addColumn("Existencias");
		this.tableExistenciaPorSucursal.setModel(this.modelTablaExistencias);

		this.cargarSiguienteIdVenta();
		this.asignarFecha();
	}

	/**
	 * Consulta la fecha actual del ordenador para la venta.
	 */
	private void asignarFecha() {
		this.formattedTextFieldFechaVenta.setText(LocalDate.now().toString());
	}

	private void cargarSiguienteIdVenta() {
		this.txfIdVenta.setText(String.valueOf(this.ventasController.buscarUltimaVenta() + 1));
	}

	/**
	 * Consulta el listado completo de empleados de la sucursal actual y llena el
	 * combo de alias con pares id/nombre.
	 */
	private void llenarCmbEmpleados() {
		this.cmbAliasEmpleado.removeAllItems();
		AppContext.empleadoController.consultaNombresCortosEmpleados(this.idSucursal)
				.forEach(this.cmbAliasEmpleado::addItem);
	}

	private void llenarCmbClientes() {
		this.cmbAliasCliente.removeAllItems();
		try {
			this.clienteController.listCmbClientes().forEach(this.cmbAliasCliente::addItem);
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "No fue posible consultar los clientes: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void consultarEmpleadoSeleccionado() {
		Object seleccionado = this.cmbAliasEmpleado.getSelectedItem();
		if (!(seleccionado instanceof JComboboxDataViewModel item) || item.id() <= 0) {
			this.empleado = null;
			this.txfRfcEmpleado.setText("");
			return;
		}

		this.empleado = this.empleadoController.consultarEmpleadoPorId(item.id());
		if (this.empleado == null || this.empleado.getIdEmpleado() <= 0) {
			this.txfRfcEmpleado.setText("");
			return;
		}
		this.txfRfcEmpleado.setText(this.empleado.getNombreCompleto());
	}

	private void consultarClienteSeleccionado() {

		Object seleccionado = this.cmbAliasCliente.getSelectedItem();

		if (!(seleccionado instanceof JComboboxDataViewModel item) || item.id() <= 0) {
			this.cliente = null;
			this.limpiarDatosCliente();
			return;
		}

		try {

			this.cliente = this.clienteController.getClienteEnVentaById(item.id());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, e.getMessage());
		}

		if (this.cliente == null || this.cliente.getIdCliente() <= 0) {
			this.limpiarDatosCliente();
			return;
		}

		this.txfNombreCompletoCliente.setText(this.cliente.getNombreCompleto());
		this.txfRfcCliente.setText(this.cliente.getRfc());
		this.txfNombreTipoCliente.setText(this.cliente.getTipoCliente());

		if (this.modelTablaArticulo != null && this.modelTablaArticulo.getRowCount() > 0) {
			this.actualizarPreciosPorClienteSeleccionado();
		}
	}

	private void limpiarDatosCliente() {
		this.txfNombreCompletoCliente.setText("");
		this.txfRfcCliente.setText("");
		this.txfNombreTipoCliente.setText("");
	}

	private void procesarEnterArticulo() {
		if (this.articulo != null && this.articulo.getIdArticulo() > 0) {
			this.agregarArticuloConsultado();
			return;
		}
		this.buscarArticulo();
	}

	private void buscarArticulo() {
		if (!this.hayClienteSeleccionado()) {
			JOptionPane.showMessageDialog(this, "Seleccione un cliente antes de consultar artículos", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String textoBusqueda = this.txfCodigoNombreArticulo.getText() == null ? ""
				: this.txfCodigoNombreArticulo.getText().trim();
		this.articulo = null;
		this.precioArticuloConsultado = null;

		if (textoBusqueda.isEmpty()) {
			this.abrirFormListaArticulos("");
			return;
		}

		try {
			ArticuloByCodigo consultado = this.articuloController.consultarArticuloPorCodigo(textoBusqueda,
					this.idSucursal, this.cliente.getIdTipoCliente());
			if (consultado == null) {
				return;
			}
			if (consultado.getIdArticulo() <= 0) {
				this.abrirFormListaArticulos(textoBusqueda);
				return;
			}

			PrecioTipoCliente precio = this.consultarPrecioArticulo(consultado.getIdArticulo());
			if (precio == null || precio.getPrecio() == null) {
				JOptionPane.showMessageDialog(this,
						"El artículo no tiene precio definido para el tipo de cliente seleccionado",
						"Precio no disponible", JOptionPane.WARNING_MESSAGE);
				return;
			}

			this.articulo = consultado;
			this.precioArticuloConsultado = precio;
			this.mostrarArticuloConsultado();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "No fue posible consultar el artículo: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean hayClienteSeleccionado() {
		return this.cliente != null && this.cliente.getIdCliente() > 0 && this.cliente.getIdTipoCliente() > 0;
	}

	private PrecioTipoCliente consultarPrecioArticulo(int idArticulo) throws Exception {
		if (!this.hayClienteSeleccionado() || idArticulo <= 0) {
			return null;
		}
		return this.articuloController.listarPreciosArticuloPorTipoCliente(idArticulo).stream()
				.filter(precio -> precio.getIdTipoCliente() == this.cliente.getIdTipoCliente()).findFirst()
				.orElse(null);
	}

	private void mostrarArticuloConsultado() {
		if (this.articulo == null || this.precioArticuloConsultado == null) {
			return;
		}

		this.txfCodigoNombreArticulo.setText(this.articulo.getCodigoArticulo());
		this.textAreaDescripcionArticulo.setText(this.articulo.getDescripcion());
		this.textField.setText(this.formatearImporte(this.precioArticuloConsultado.getPrecio()));
		this.textField_1.setText(this.precioArticuloConsultado.getPrecioEspecial() == null ? ""
				: this.formatearImporte(this.precioArticuloConsultado.getPrecioEspecial()));
		this.llenarTablaExistencias(this.articulo.getIdArticulo());
	}

	private void agregarArticuloConsultado() {
		if (this.articulo == null || this.articulo.getIdArticulo() <= 0) {
			JOptionPane.showMessageDialog(this, "Primero debe consultar un artículo", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		try {
			// Se consulta nuevamente para que la cantidad mínima de mayoreo utilizada al
			// agregar corresponda al valor vigente del tipo de cliente seleccionado.
			PrecioTipoCliente precio = this.consultarPrecioArticulo(this.articulo.getIdArticulo());
			if (precio == null || precio.getPrecio() == null) {
				JOptionPane.showMessageDialog(this, "No existe precio configurado para el tipo de cliente seleccionado",
						"Precio no disponible", JOptionPane.WARNING_MESSAGE);
				return;
			}

			Integer cantidad = this.solicitarCantidadArticulos();
			if (cantidad == null) {
				return;
			}

			this.agregarArticuloATabla(this.articulo, precio, cantidad.intValue());
			this.limpiarArticuloConsultado();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "No fue posible agregar el artículo: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private Integer solicitarCantidadArticulos() {
		String cantidadIngresada = JOptionPane.showInputDialog(this, "Ingrese la cantidad de artículos");
		if (cantidadIngresada == null) {
			return null;
		}

		try {
			int cantidad = Integer.parseInt(cantidadIngresada.trim());
			if (cantidad <= 0) {
				throw new NumberFormatException();
			}
			return Integer.valueOf(cantidad);
		} catch (NumberFormatException er) {
			JOptionPane.showMessageDialog(this, "Ingrese una cantidad entera mayor a cero", "Cantidad inválida",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}

	private void agregarArticuloATabla(ArticuloByCodigo articuloSeleccionado, PrecioTipoCliente precio, int cantidad) {
		if (articuloSeleccionado == null || articuloSeleccionado.getIdArticulo() <= 0 || precio == null
				|| cantidad <= 0) {
			return;
		}

		String codigo = articuloSeleccionado.getCodigoArticulo();
		int filaExistente = this.buscarFilaArticulo(codigo);
		int cantidadExistente = filaExistente >= 0 ? this.obtenerCantidadFila(filaExistente) : 0;
		int cantidadTotal = cantidadExistente + cantidad;

		if (cantidadTotal > articuloSeleccionado.getExistencia()) {
			JOptionPane.showMessageDialog(this, "La cantidad solicitada supera la existencia disponible en la sucursal",
					"Existencia insuficiente", JOptionPane.WARNING_MESSAGE);
			return;
		}

		this.articulosPorCodigo.put(codigo, articuloSeleccionado);
		this.preciosPorCodigo.put(codigo, precio);

		if (filaExistente >= 0) {
			this.modelTablaArticulo.setValueAt(Integer.valueOf(cantidadTotal), filaExistente, COLUMNA_CANTIDAD);
			return;
		}

		BigDecimal descuento = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		BigDecimal precioBase = this.obtenerPrecioBase(precio, cantidadTotal);
		this.cantidadesValidasPorCodigo.put(codigo, Integer.valueOf(cantidadTotal));
		this.descuentosValidosPorCodigo.put(codigo, descuento);
		this.modelTablaArticulo.addRow(new Object[] { codigo, articuloSeleccionado.getDescripcion(), precioBase,
				Integer.valueOf(cantidadTotal), descuento, precioBase.multiply(BigDecimal.valueOf(cantidadTotal)) });
		this.recalcularFila(this.modelTablaArticulo.getRowCount() - 1);
		this.recalcularTotalesDesdeTabla();
	}

	private int buscarFilaArticulo(String codigo) {
		for (int fila = 0; fila < this.modelTablaArticulo.getRowCount(); fila++) {
			if (codigo.equals(String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO)))) {
				return fila;
			}
		}
		return -1;
	}

	private void configurarEditoresTablaArticulos() {
		this.tableListadoArticulos.setDefaultEditor(Object.class, null);
		this.tableListadoArticulos.getColumnModel().getColumn(COLUMNA_CANTIDAD)
				.setCellEditor(new DefaultCellEditor(new JTextField()));
		this.tableListadoArticulos.getColumnModel().getColumn(COLUMNA_DESCUENTO)
				.setCellEditor(new DefaultCellEditor(new JTextField()));
	}

	private void configurarEventosTablaArticulos() {
		this.modelTablaArticulo.addTableModelListener(event -> {
			if (this.actualizandoTablaArticulo || event.getType() != TableModelEvent.UPDATE
					|| (event.getColumn() != COLUMNA_CANTIDAD && event.getColumn() != COLUMNA_DESCUENTO)) {
				return;
			}

			int fila = event.getFirstRow();
			if (fila < 0 || fila >= this.modelTablaArticulo.getRowCount()) {
				return;
			}

			try {
				this.recalcularFila(fila);
				this.recalcularTotalesDesdeTabla();
			} catch (IllegalArgumentException er) {
				this.restaurarValoresValidos(fila);
				JOptionPane.showMessageDialog(this, er.getMessage(), "Dato inválido", JOptionPane.WARNING_MESSAGE);
			}
		});
	}

	private void configurarAtajoEliminarArticulo() {
		String accionEliminar = "eliminarArticuloSeleccionado";
		this.tableListadoArticulos.getInputMap(javax.swing.JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0), accionEliminar);
		this.tableListadoArticulos.getActionMap().put(accionEliminar, new javax.swing.AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarArticuloSeleccionado();
			}
		});
	}

	private void eliminarArticuloSeleccionado() {
		int filaVista = this.tableListadoArticulos.getSelectedRow();
		if (filaVista < 0) {
			JOptionPane.showMessageDialog(this, "Seleccione un artículo para eliminar", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.tableListadoArticulos.isEditing() && this.tableListadoArticulos.getCellEditor() != null
				&& !this.tableListadoArticulos.getCellEditor().stopCellEditing()) {
			return;
		}

		int filaModelo = this.tableListadoArticulos.convertRowIndexToModel(filaVista);
		String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(filaModelo, COLUMNA_CODIGO));

		this.modelTablaArticulo.removeRow(filaModelo);
		this.articulosPorCodigo.remove(codigo);
		this.preciosPorCodigo.remove(codigo);
		this.cantidadesValidasPorCodigo.remove(codigo);
		this.descuentosValidosPorCodigo.remove(codigo);
		this.recalcularTotalesDesdeTabla();
	}

	private void recalcularFila(int fila) {
		String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO));
		ArticuloByCodigo articuloFila = this.articulosPorCodigo.get(codigo);
		PrecioTipoCliente precio = this.preciosPorCodigo.get(codigo);
		if (articuloFila == null || precio == null) {
			throw new IllegalArgumentException("No existe información suficiente para recalcular el artículo");
		}

		int cantidad = this.obtenerCantidadFila(fila);
		if (cantidad > articuloFila.getExistencia()) {
			throw new IllegalArgumentException("La cantidad solicitada supera la existencia disponible en la sucursal");
		}

		BigDecimal descuento = this.obtenerDescuentoFila(fila);
		BigDecimal precioBase = this.obtenerPrecioBase(precio, cantidad);
		BigDecimal factorDescuento = CIEN.subtract(descuento).divide(CIEN, 6, RoundingMode.HALF_UP);
		BigDecimal precioUnitario = precioBase.multiply(factorDescuento).setScale(2, RoundingMode.HALF_UP);
		BigDecimal subtotalFila = precioUnitario.multiply(BigDecimal.valueOf(cantidad)).setScale(2,
				RoundingMode.HALF_UP);

		this.actualizandoTablaArticulo = true;
		try {
			this.modelTablaArticulo.setValueAt(precioUnitario, fila, COLUMNA_PRECIO);
			this.modelTablaArticulo.setValueAt(subtotalFila, fila, COLUMNA_SUBTOTAL);
		} finally {
			this.actualizandoTablaArticulo = false;
		}

		this.cantidadesValidasPorCodigo.put(codigo, Integer.valueOf(cantidad));
		this.descuentosValidosPorCodigo.put(codigo, descuento);
	}

	private int obtenerCantidadFila(int fila) {
		Object valor = this.modelTablaArticulo.getValueAt(fila, COLUMNA_CANTIDAD);
		int cantidad;
		try {
			cantidad = valor instanceof Number numero ? numero.intValue()
					: Integer.parseInt(String.valueOf(valor).trim());
		} catch (Exception er) {
			throw new IllegalArgumentException("La cantidad debe ser un número entero mayor a cero");
		}
		if (cantidad <= 0) {
			throw new IllegalArgumentException("La cantidad debe ser un número entero mayor a cero");
		}
		return cantidad;
	}

	private BigDecimal obtenerDescuentoFila(int fila) {
		Object valor = this.modelTablaArticulo.getValueAt(fila, COLUMNA_DESCUENTO);
		BigDecimal descuento;
		try {
			descuento = valor instanceof BigDecimal decimal ? decimal : new BigDecimal(String.valueOf(valor).trim());
		} catch (Exception er) {
			throw new IllegalArgumentException("El descuento debe ser un porcentaje entre 0 y 100");
		}
		if (descuento.compareTo(BigDecimal.ZERO) < 0 || descuento.compareTo(CIEN) > 0) {
			throw new IllegalArgumentException("El descuento debe ser un porcentaje entre 0 y 100");
		}
		return descuento.setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal obtenerPrecioBase(PrecioTipoCliente precio, int cantidad) {
		if (precio == null || precio.getPrecio() == null) {
			throw new IllegalArgumentException("El artículo no tiene precio configurado");
		}

		Integer cantidadMayoreo = precio.getCantidadPrecioEspecial();
		if (precio.getPrecioEspecial() != null && cantidadMayoreo != null && cantidadMayoreo.intValue() > 0
				&& cantidad >= cantidadMayoreo.intValue()) {
			return precio.getPrecioEspecial().setScale(2, RoundingMode.HALF_UP);
		}
		return precio.getPrecio().setScale(2, RoundingMode.HALF_UP);
	}

	private void restaurarValoresValidos(int fila) {
		String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO));
		Integer cantidad = this.cantidadesValidasPorCodigo.get(codigo);
		BigDecimal descuento = this.descuentosValidosPorCodigo.get(codigo);
		if (cantidad == null || descuento == null) {
			return;
		}

		this.actualizandoTablaArticulo = true;
		try {
			this.modelTablaArticulo.setValueAt(cantidad, fila, COLUMNA_CANTIDAD);
			this.modelTablaArticulo.setValueAt(descuento, fila, COLUMNA_DESCUENTO);
		} finally {
			this.actualizandoTablaArticulo = false;
		}
		this.recalcularFila(fila);
		this.recalcularTotalesDesdeTabla();
	}

	private void recalcularTotalesDesdeTabla() {
		BigDecimal subtotalVenta = BigDecimal.ZERO;
		BigDecimal ivaVenta = BigDecimal.ZERO;
		BigDecimal totalVenta = BigDecimal.ZERO;
		int totalArticulos = 0;

		for (int fila = 0; fila < this.modelTablaArticulo.getRowCount(); fila++) {
			String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO));
			ArticuloByCodigo articuloFila = this.articulosPorCodigo.get(codigo);
			BigDecimal totalFila = this.obtenerDecimalFila(fila, COLUMNA_SUBTOTAL);
			int cantidad = this.obtenerCantidadFila(fila);
			totalArticulos += cantidad;
			totalVenta = totalVenta.add(totalFila);

			if (articuloFila != null && articuloFila.isExento()) {
				subtotalVenta = subtotalVenta.add(totalFila);
			} else {
				BigDecimal base = totalFila.divide(FACTOR_IVA, 2, RoundingMode.HALF_UP);
				subtotalVenta = subtotalVenta.add(base);
				ivaVenta = ivaVenta.add(totalFila.subtract(base));
			}
		}

		this.txfNumeroDePartidas.setText(String.valueOf(this.modelTablaArticulo.getRowCount()));
		this.txfTotalDeArticulos.setText(String.valueOf(totalArticulos));
		this.txfSubtotalVenta.setText(this.formatearImporte(subtotalVenta));
		this.txfIva.setText(this.formatearImporte(ivaVenta));
		this.txfTotalVenta.setText(this.formatearImporte(totalVenta));
		this.sincronizarArticulosVendidos();
	}

	private BigDecimal obtenerDecimalFila(int fila, int columna) {
		Object valor = this.modelTablaArticulo.getValueAt(fila, columna);
		if (valor instanceof BigDecimal decimal) {
			return decimal;
		}
		if (valor instanceof Number numero) {
			return BigDecimal.valueOf(numero.doubleValue());
		}
		return new BigDecimal(String.valueOf(valor).trim());
	}

	private void sincronizarArticulosVendidos() {
		this.articulosVendidos = new ArrayList<>();
		for (int fila = 0; fila < this.modelTablaArticulo.getRowCount(); fila++) {
			String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO));
			ArticuloByCodigo articuloFila = this.articulosPorCodigo.get(codigo);
			if (articuloFila == null) {
				continue;
			}

			ArticulosPorVentas detalle = new ArticulosPorVentas();
			detalle.setId_articulo(articuloFila.getIdArticulo());
			detalle.setCantidad(this.obtenerCantidadFila(fila));
			detalle.setSubtotal(this.obtenerDecimalFila(fila, COLUMNA_SUBTOTAL).doubleValue());
			this.articulosVendidos.add(detalle);
		}
	}

	private void actualizarPreciosPorClienteSeleccionado() {
		try {
			for (int fila = 0; fila < this.modelTablaArticulo.getRowCount(); fila++) {
				String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO));
				ArticuloByCodigo articuloFila = this.articulosPorCodigo.get(codigo);
				if (articuloFila == null) {
					continue;
				}
				PrecioTipoCliente precio = this.consultarPrecioArticulo(articuloFila.getIdArticulo());
				if (precio == null || precio.getPrecio() == null) {
					throw new IllegalArgumentException(
							"Un artículo no tiene precio para el tipo de cliente seleccionado");
				}
				this.preciosPorCodigo.put(codigo, precio);
				this.recalcularFila(fila);
			}
			this.recalcularTotalesDesdeTabla();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this,
					"No fue posible actualizar los precios por tipo de cliente: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Consulta las existencias del artículo en todas las sucursales y adapta el
	 * resultado del controlador al modelo visible de dos columnas.
	 *
	 * @param id identificador del artículo a consultar
	 */
	private void llenarTablaExistencias(int id) {
		this.modelTablaExistencias.setRowCount(0);
		DefaultTableModel resultado = new DefaultTableModel();
		resultado.addColumn("Id");
		resultado.addColumn("Sucursal");
		resultado.addColumn("Dirección");
		resultado.addColumn("Existencia");
		this.articuloController.consultarExistenciasPorSucursal(id, resultado);

		for (int fila = 0; fila < resultado.getRowCount(); fila++) {
			this.modelTablaExistencias
					.addRow(new Object[] { resultado.getValueAt(fila, 1), resultado.getValueAt(fila, 3) });
		}
	}

	private void abrirFormListaArticulos(String nombreArticulo) {
		if (!this.hayClienteSeleccionado()) {
			JOptionPane.showMessageDialog(this, "Seleccione un cliente antes de consultar artículos", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Component cm = this;
		Fr_PuntoDeVentas puntoVenta = this;
		int tipoCliente = this.cliente.getIdTipoCliente();
		try {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					Fr_ListaArticulos frame = new Fr_ListaArticulos(nombreArticulo, idSucursal, tipoCliente,
							puntoVenta);
					frame.setLocationRelativeTo(cm);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			});
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}
	}

	@Override
	public void listarArticuloDesdeConsulta(ArticuloByCodigo articuloSeleccionado, int cantidad,
			BigDecimal precioListado) {
		if (articuloSeleccionado == null || articuloSeleccionado.getIdArticulo() <= 0) {
			return;
		}

		try {
			PrecioTipoCliente precio = this.consultarPrecioArticulo(articuloSeleccionado.getIdArticulo());
			if (precio == null || precio.getPrecio() == null) {
				precio = new PrecioTipoCliente(this.cliente.getIdTipoCliente(), precioListado, null, null);
			}
			this.agregarArticuloATabla(articuloSeleccionado, precio, cantidad);
			this.limpiarArticuloConsultado();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "No fue posible agregar el artículo seleccionado: " + er.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Contrato legado conservado por la interfaz. El selector compartido utiliza la
	 * sobrecarga tipada con {@link ArticuloByCodigo}.
	 */
	@Override
	public void listarArticuloDesdeConsulta(Object[] articuloFila, ArticulosPorVentas art) {
		if (articuloFila == null || art == null) {
			return;
		}
		this.modelTablaArticulo.addRow(articuloFila);
		if (this.articulosVendidos == null) {
			this.articulosVendidos = new ArrayList<ArticulosPorVentas>();
		}
		this.articulosVendidos.add(art);
		this.calculoDeTotales();
	}

	private void limpiarArticuloConsultado() {
		this.articulo = null;
		this.precioArticuloConsultado = null;
		this.txfCodigoNombreArticulo.setText("");
		this.textAreaDescripcionArticulo.setText("");
		this.textField.setText("");
		this.textField_1.setText("");
		this.modelTablaExistencias.setRowCount(0);
		this.txfCodigoNombreArticulo.requestFocusInWindow();
	}

	private String formatearImporte(BigDecimal importe) {
		return importe == null ? "" : importe.setScale(2, RoundingMode.HALF_UP).toPlainString();
	}

	private void calculoDeTotales() {
		try {
			this.recalcularTotalesDesdeTabla();
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}
	}

	private void prepararCobro() {
		if (!this.detenerEdicionVenta()) {
			return;
		}

		try {
			VentaConDetalle ventaConDetalle = this.construirVentaDesdeFormulario();
			this.abrirFormFormaDePago(ventaConDetalle);
		} catch (IllegalArgumentException er) {
			JOptionPane.showMessageDialog(this, er.getMessage(), "No es posible cobrar la venta",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error al preparar la venta: " + er.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private boolean detenerEdicionVenta() {
		if (this.tableListadoArticulos.isEditing() && this.tableListadoArticulos.getCellEditor() != null
				&& !this.tableListadoArticulos.getCellEditor().stopCellEditing()) {
			JOptionPane.showMessageDialog(this, "No fue posible confirmar la edición del artículo", "Dato inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	private VentaConDetalle construirVentaDesdeFormulario() throws Exception {
		if (this.idSucursal <= 0) {
			throw new IllegalArgumentException("No existe una sucursal válida para registrar la venta");
		}
		if (this.empleado == null || this.empleado.getIdEmpleado() <= 0) {
			throw new IllegalArgumentException("Debe seleccionar el empleado que atiende la venta");
		}
		if (!this.hayClienteSeleccionado()) {
			throw new IllegalArgumentException("Debe seleccionar un cliente");
		}

		List<ArticuloPorVenta> articulos = this.construirArticulosVenta();
		this.recalcularTotalesDesdeTabla();

		Date fechaVenta;
		try {
			fechaVenta = Date.valueOf(LocalDate.parse(this.formattedTextFieldFechaVenta.getText().trim()));
		} catch (Exception er) {
			throw new IllegalArgumentException("La fecha de venta no es válida");
		}

		BigDecimal subtotal = this.obtenerImporteCampo(this.txfSubtotalVenta, "subtotal");
		BigDecimal iva = this.obtenerImporteCampo(this.txfIva, "IVA");
		BigDecimal total = this.obtenerImporteCampo(this.txfTotalVenta, "total");
		if (total.compareTo(BigDecimal.ZERO) <= 0) {
			throw new IllegalArgumentException("El total de la venta debe ser mayor a cero");
		}
		if (subtotal.add(iva).setScale(2, RoundingMode.HALF_UP).compareTo(total.setScale(2, RoundingMode.HALF_UP)) != 0) {
			throw new IllegalArgumentException("Los importes de la venta no son consistentes");
		}

		Venta venta = new Venta.VentaBuilder().idEmpleado(this.empleado.getIdEmpleado())
				.idCliente(this.cliente.getIdCliente()).idSucursal(this.idSucursal).fecha(fechaVenta)
				.tipoVenta(false).subtotal(subtotal.doubleValue()).iva(iva.doubleValue())
				.importeTotal(total.doubleValue()).statusVenta(true).build();

		return new VentaConDetalle(venta, articulos, new ArrayList<>());
	}

	private List<ArticuloPorVenta> construirArticulosVenta() throws Exception {
		if (this.modelTablaArticulo.getRowCount() <= 0) {
			throw new IllegalArgumentException("Debe agregar al menos un artículo a la venta");
		}

		List<ArticuloPorVenta> detalles = new ArrayList<>();
		Set<Integer> idsArticulos = new HashSet<>();

		for (int fila = 0; fila < this.modelTablaArticulo.getRowCount(); fila++) {
			String codigo = String.valueOf(this.modelTablaArticulo.getValueAt(fila, COLUMNA_CODIGO)).trim();
			if (codigo.isEmpty()) {
				throw new IllegalArgumentException("No fue posible identificar el artículo de la fila " + (fila + 1));
			}

			int cantidad = this.obtenerCantidadFila(fila);
			BigDecimal descuento = this.obtenerDescuentoFila(fila);
			if (descuento.compareTo(BigDecimal.ZERO) != 0) {
				throw new IllegalArgumentException("No es posible registrar descuentos por partida con el flujo actual. "
						+ "Retire el descuento del artículo " + codigo + " antes de cobrar");
			}

			ArticuloByCodigo articuloVigente = this.articuloController.consultarArticuloPorCodigo(codigo,
					this.idSucursal, this.cliente.getIdTipoCliente());
			if (articuloVigente == null || articuloVigente.getIdArticulo() <= 0) {
				throw new IllegalArgumentException("El artículo " + codigo + " ya no está disponible para la venta");
			}
			if (!idsArticulos.add(articuloVigente.getIdArticulo())) {
				throw new IllegalArgumentException("El artículo " + codigo + " está repetido en la venta");
			}
			if (articuloVigente.getExistencia() <= 0) {
				throw new IllegalArgumentException("El artículo " + codigo + " no tiene existencia disponible");
			}
			if (cantidad > articuloVigente.getExistencia()) {
				throw new IllegalArgumentException("La cantidad solicitada del artículo " + codigo
						+ " supera la existencia disponible en la sucursal");
			}

			PrecioTipoCliente precioVigente = this.consultarPrecioArticulo(articuloVigente.getIdArticulo());
			if (precioVigente == null || precioVigente.getPrecio() == null) {
				throw new IllegalArgumentException("El artículo " + codigo
						+ " no tiene precio vigente para el tipo de cliente seleccionado");
			}

			this.articulosPorCodigo.put(codigo, articuloVigente);
			this.preciosPorCodigo.put(codigo, precioVigente);
			this.recalcularFila(fila);

			BigDecimal totalFila = this.obtenerDecimalFila(fila, COLUMNA_SUBTOTAL);
			BigDecimal subtotalDetalle = articuloVigente.isExento() ? totalFila
					: totalFila.divide(FACTOR_IVA, 2, RoundingMode.HALF_UP);
			detalles.add(new ArticuloPorVenta.ArticuloPorVentaBuilder().idArticulo(articuloVigente.getIdArticulo())
					.cantidad(cantidad).subtotal(subtotalDetalle.doubleValue()).build());
		}

		return detalles;
	}

	private BigDecimal obtenerImporteCampo(JTextField campo, String nombre) {
		try {
			return new BigDecimal(campo.getText().trim()).setScale(2, RoundingMode.HALF_UP);
		} catch (Exception er) {
			throw new IllegalArgumentException("El " + nombre + " de la venta no es válido");
		}
	}

	public void refreshAll() {
		if (this.modelTablaArticulo != null) {
			this.modelTablaArticulo.setRowCount(0);
		}
		this.articulosPorCodigo.clear();
		this.preciosPorCodigo.clear();
		this.cantidadesValidasPorCodigo.clear();
		this.descuentosValidosPorCodigo.clear();
		this.articulosVendidos = new ArrayList<>();
		this.limpiarArticuloConsultado();
		this.recalcularTotalesDesdeTabla();
		this.cargarSiguienteIdVenta();
		this.asignarFecha();
	}

	private void abrirFormFormaDePago(VentaConDetalle ventaConDetalle) {
		if (ventaConDetalle == null || ventaConDetalle.getVenta() == null || ventaConDetalle.getArticulos() == null
				|| ventaConDetalle.getArticulos().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No existe una venta válida para cobrar", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		Component cmp = this;
		Fr_PuntoDeVentas fr = this;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					var form = new Fr_FormasDePago(ventaConDetalle, fr);
					form.setVisible(true);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					form.setLocationRelativeTo(cmp);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
}
