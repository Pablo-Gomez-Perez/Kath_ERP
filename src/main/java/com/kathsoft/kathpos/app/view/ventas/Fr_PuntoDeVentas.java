package com.kathsoft.kathpos.app.view.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.VentasController;
import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.Ventas;
import com.kathsoft.kathpos.app.model.articulo.Articulo;
import com.kathsoft.kathpos.app.model.cliente.Clientes;
import com.kathsoft.kathpos.app.model.empleado.Empleado;
import com.kathsoft.kathpos.app.model.interfaces.IListadoArticulosAcciones;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.view.formas_pago.Fr_FormasDePago;
import com.kathsoft.kathpos.app.view.shared.Fr_ListaArticulos;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;

public class Fr_PuntoDeVentas extends JFrame implements IListadoArticulosAcciones{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8197295139603781983L;
	/**
	 * 
	 * 
	 * 
	 */
	private int idSucursal;
	private Articulo articulo;
	private Empleado empleado;
	private Clientes cliente;
	private List<ArticulosPorVentas> articulosVendidos;
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
	private JComboBox cmbAliasEmpleado;
	private JTextField txfRfcEmpleado;
	private JLabel lblNombre;
	private JPanel panelSuperiorDetallesCliente;
	private JLabel lblCliente;
	private JComboBox cmbAliasCliente;
	private JLabel lblNombre_1;
	private JTextField txfNombreCompletoCliente;
	private JLabel lblRfc;
	private JTextField txfRfcCliente;
	private JLabel lblCtaContbale;
	private JTextField txfCuentaContableCliente;
	private JPanel panelInferiorDatosArticulos;
	private JLabel lblArticulo;
	private JTextField txfCodigoNombreArticulo;
	private JLabel lblPrecioG;
	private JTextField textField;
	private JLabel lblPrecioM;
	private JTextField textField_1;
	private JButton btnBuscarVentaPorID_1;
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
		this.panelSuperiorDatosVenta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos Generales", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelSuperiorDatosVenta.setBackground(new Color(85, 223, 255));
		
		this.panelInferiorDatosArticulos = new JPanel();
		this.panelInferiorDatosArticulos.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		this.panelInferiorDatosArticulos.setBackground(new Color(245, 194, 17));
		
		this.scrollPaneListadoArticulos = new JScrollPane();
		
		this.panelDetallesSubtotales = new JPanel();
		this.panelDetallesSubtotales.setBackground(new Color(255, 190, 111));
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelInferiorDatosArticulos, GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
						.addComponent(this.panelSuperiorDatosVenta, GroupLayout.DEFAULT_SIZE, 1007, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(this.scrollPaneListadoArticulos, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.panelDetallesSubtotales, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panelSuperiorDatosVenta, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.scrollPaneListadoArticulos, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
						.addComponent(this.panelDetallesSubtotales, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelInferiorDatosArticulos, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		this.btnEliminarArticuloSeleccionado = new JButton("Eliminar Articulo");
		this.btnEliminarArticuloSeleccionado.setBackground(new Color(237, 51, 59));
		
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
		this.btnCancelarSalir.setBackground(new Color(246, 97, 81));
		
		this.btnCobrar = new JButton("Cobrar");
		this.btnCobrar.setBackground(new Color(87, 227, 137));
		GroupLayout gl_panelDetallesSubtotales = new GroupLayout(this.panelDetallesSubtotales);
		gl_panelDetallesSubtotales.setHorizontalGroup(
			gl_panelDetallesSubtotales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetallesSubtotales.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDetallesSubtotales.createParallelGroup(Alignment.LEADING)
						.addComponent(this.btnEliminarArticuloSeleccionado, GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
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
					.addContainerGap())
		);
		gl_panelDetallesSubtotales.setVerticalGroup(
			gl_panelDetallesSubtotales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDetallesSubtotales.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.btnEliminarArticuloSeleccionado)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.lblPartidas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfNumeroDePartidas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblTotalDeArtculos)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfTotalDeArticulos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblSubTotal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfSubtotalVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblIva)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblTotal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfTotalVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnCancelarSalir)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnCobrar)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		this.panelDetallesSubtotales.setLayout(gl_panelDetallesSubtotales);
		
		this.tableListadoArticulos = new JTable();
		this.scrollPaneListadoArticulos.setViewportView(this.tableListadoArticulos);
		
		this.lblArticulo = new JLabel("Artículo");
		
		this.txfCodigoNombreArticulo = new JTextField();
		this.txfCodigoNombreArticulo.setColumns(10);
		
		this.lblPrecioG = new JLabel("Precio G.");
		
		this.textField = new JTextField();
		this.textField.setEditable(false);
		this.textField.setColumns(10);
		
		this.lblPrecioM = new JLabel("Precio M.");
		
		this.textField_1 = new JTextField();
		this.textField_1.setEditable(false);
		this.textField_1.setColumns(10);
		
		this.btnBuscarVentaPorID_1 = new JButton("");
		this.btnBuscarVentaPorID_1.setBackground(new Color(181, 131, 90));
		this.btnBuscarVentaPorID_1.setIcon(new ImageIcon(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		
		this.btnAgregar = new JButton("");
		this.btnAgregar.setBackground(new Color(87, 227, 137));
		this.btnAgregar.setFont(new Font("Dialog", Font.BOLD, 9));
		this.btnAgregar.setIcon(new ImageIcon(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		
		this.lblDescripcin = new JLabel("Descripción");
		
		this.scrollPaneDescripcionArticulo = new JScrollPane();
		
		this.lblNewLabel_1 = new JLabel("Existencias");
		
		this.scrollPaneExistenciaPorSucursal = new JScrollPane();
		GroupLayout gl_panelInferiorDatosArticulos = new GroupLayout(this.panelInferiorDatosArticulos);
		gl_panelInferiorDatosArticulos.setHorizontalGroup(
			gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
							.addComponent(this.scrollPaneDescripcionArticulo, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(this.scrollPaneExistenciaPorSucursal, GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, gl_panelInferiorDatosArticulos.createSequentialGroup()
							.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
									.addComponent(this.lblArticulo)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.txfCodigoNombreArticulo, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
									.addComponent(this.lblDescripcin)
									.addGap(410)))
							.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
								.addComponent(this.lblNewLabel_1)
								.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
									.addComponent(this.btnBuscarVentaPorID_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.lblPrecioG, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.textField, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.lblPrecioM)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.textField_1, GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(this.btnAgregar)))))
					.addContainerGap())
		);
		gl_panelInferiorDatosArticulos.setVerticalGroup(
			gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorDatosArticulos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.TRAILING)
							.addComponent(this.btnAgregar)
							.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.lblArticulo)
								.addComponent(this.txfCodigoNombreArticulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblPrecioM)
								.addComponent(this.textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblPrecioG)))
						.addComponent(this.btnBuscarVentaPorID_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
						.addComponent(this.lblDescripcin)
						.addComponent(this.lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInferiorDatosArticulos.createParallelGroup(Alignment.LEADING)
						.addComponent(this.scrollPaneExistenciaPorSucursal, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.addComponent(this.scrollPaneDescripcionArticulo, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
					.addContainerGap())
		);
		
		this.tableExistenciaPorSucursal = new JTable();
		this.scrollPaneExistenciaPorSucursal.setViewportView(this.tableExistenciaPorSucursal);
		
		this.textAreaDescripcionArticulo = new JTextArea();
		this.scrollPaneDescripcionArticulo.setViewportView(this.textAreaDescripcionArticulo);
		this.panelInferiorDatosArticulos.setLayout(gl_panelInferiorDatosArticulos);
		
		this.panelSuperiorDetallesVenta = new JPanel();
		this.panelSuperiorDetallesVenta.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Inf. de Venta", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelSuperiorDetallesVenta.setBackground(new Color(85, 223, 255));
		
		this.panelSuperiorDetalleEmpleado = new JPanel();
		this.panelSuperiorDetalleEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Atiende", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.panelSuperiorDetalleEmpleado.setBackground(new Color(85, 223, 255));
		
		this.panelSuperiorDetallesCliente = new JPanel();
		this.panelSuperiorDetallesCliente.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalles de cliente", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelSuperiorDetallesCliente.setBackground(new Color(85, 223, 255));
		GroupLayout gl_panelSuperiorDatosVenta = new GroupLayout(this.panelSuperiorDatosVenta);
		gl_panelSuperiorDatosVenta.setHorizontalGroup(
			gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDatosVenta.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panelSuperiorDetallesVenta, GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelSuperiorDetalleEmpleado, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelSuperiorDetallesCliente, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelSuperiorDatosVenta.setVerticalGroup(
			gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panelSuperiorDatosVenta.createSequentialGroup()
					.addGroup(gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.panelSuperiorDetallesCliente, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(this.panelSuperiorDetalleEmpleado, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
						.addComponent(this.panelSuperiorDetallesVenta, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
					.addGap(18))
		);
		
		this.lblCliente = new JLabel("Cliente");
		
		this.cmbAliasCliente = new JComboBox();
		
		this.lblNombre_1 = new JLabel("Nombre");
		
		this.txfNombreCompletoCliente = new JTextField();
		this.txfNombreCompletoCliente.setColumns(10);
		
		this.lblRfc = new JLabel("RFC");
		
		this.txfRfcCliente = new JTextField();
		this.txfRfcCliente.setColumns(10);
		
		this.lblCtaContbale = new JLabel("Cta. Contable");
		
		this.txfCuentaContableCliente = new JTextField();
		this.txfCuentaContableCliente.setColumns(10);
		GroupLayout gl_panelSuperiorDetallesCliente = new GroupLayout(this.panelSuperiorDetallesCliente);
		gl_panelSuperiorDetallesCliente.setHorizontalGroup(
			gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
							.addComponent(this.lblCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbAliasCliente, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
							.addComponent(this.lblRfc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfRfcCliente)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
							.addComponent(this.lblNombre_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfNombreCompletoCliente, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
						.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
							.addComponent(this.lblCtaContbale)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfCuentaContableCliente, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelSuperiorDetallesCliente.setVerticalGroup(
			gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesCliente.createSequentialGroup()
					.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.cmbAliasCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblCliente)
						.addComponent(this.lblNombre_1)
						.addComponent(this.txfNombreCompletoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelSuperiorDetallesCliente.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblRfc)
						.addComponent(this.txfRfcCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblCtaContbale)
						.addComponent(this.txfCuentaContableCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		this.panelSuperiorDetallesCliente.setLayout(gl_panelSuperiorDetallesCliente);
		
		this.lblCajero = new JLabel("Cajero");
		
		this.cmbAliasEmpleado = new JComboBox();
		
		this.txfRfcEmpleado = new JTextField();
		this.txfRfcEmpleado.setColumns(10);
		
		this.lblNombre = new JLabel("Nombre");
		GroupLayout gl_panelSuperiorDetalleEmpleado = new GroupLayout(this.panelSuperiorDetalleEmpleado);
		gl_panelSuperiorDetalleEmpleado.setHorizontalGroup(
			gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetalleEmpleado.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.LEADING)
						.addComponent(this.lblCajero)
						.addComponent(this.lblNombre))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.LEADING)
						.addComponent(this.cmbAliasEmpleado, 0, 266, Short.MAX_VALUE)
						.addComponent(this.txfRfcEmpleado, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelSuperiorDetalleEmpleado.setVerticalGroup(
			gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetalleEmpleado.createSequentialGroup()
					.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblCajero)
						.addComponent(this.cmbAliasEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelSuperiorDetalleEmpleado.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblNombre))
					.addContainerGap(37, Short.MAX_VALUE))
		);
		this.panelSuperiorDetalleEmpleado.setLayout(gl_panelSuperiorDetalleEmpleado);
		
		this.lblNewLabel = new JLabel("Id");
		
		this.txfIdVenta = new JTextField();
		this.txfIdVenta.setColumns(10);
		
		this.btnBuscarVentaPorID = new JButton("");
		this.btnBuscarVentaPorID.setIcon(new ImageIcon(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		
		this.lblFecha = new JLabel("Fecha");
		
		this.formattedTextFieldFechaVenta = new JFormattedTextField();
		GroupLayout gl_panelSuperiorDetallesVenta = new GroupLayout(this.panelSuperiorDetallesVenta);
		gl_panelSuperiorDetallesVenta.setHorizontalGroup(
			gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
							.addComponent(this.lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfIdVenta, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btnBuscarVentaPorID, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
							.addComponent(this.lblFecha)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaVenta, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelSuperiorDetallesVenta.setVerticalGroup(
			gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSuperiorDetallesVenta.createSequentialGroup()
					.addGroup(gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNewLabel)
						.addComponent(this.txfIdVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.btnBuscarVentaPorID, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelSuperiorDetallesVenta.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFecha)
						.addComponent(this.formattedTextFieldFechaVenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		this.panelSuperiorDetallesVenta.setLayout(gl_panelSuperiorDetallesVenta);
		this.panelSuperiorDatosVenta.setLayout(gl_panelSuperiorDatosVenta);
		this.contentPane.setLayout(gl_contentPane);
		this.llenarCmbEmpleados();
		this.llenarCmbRfcCliente();

		modelTablaArticulo = new DefaultTableModel();

		modelTablaArticulo.addColumn("Codigo");
		modelTablaArticulo.addColumn("Descripción");
		modelTablaArticulo.addColumn("Precio");
		modelTablaArticulo.addColumn("Cantidad");
		modelTablaArticulo.addColumn("Descuento");
		modelTablaArticulo.addColumn("Subtotal");

		this.modelTablaExistencias = new DefaultTableModel();

		this.modelTablaExistencias.addColumn("Sucursal");
		this.modelTablaExistencias.addColumn("Existencias");
		this.asignarFecha();
	}

	/**
	 * consulta la fecha actual del ordenador para la venta
	 */
	private void asignarFecha() {
		String fecha = LocalDate.now().toString();
	}

	/**
	 * consulta el listado completo de empleados en la bd de la sucursal de trabajo
	 * actual en la que se inició sesión y llena un {@code JCombobox} con los
	 * nombres de los empleados que pertenecen a esa sucursal
	 */
	private void llenarCmbEmpleados() {

		
	}

	private void llenarCmbRfcCliente() {

		

	}

	/**
	 * al momento de realizar la respectiva consulta en la base de datos mediante el
	 * código indicado la tabla {@code JTable tablaExistenciaPorSucursal} consulta
	 * las existencias de ese artículo en las demas sucursales listadas
	 * 
	 * @param id -> indice del articulo a consultar su existencia en las demás
	 *           sucursales
	 */
	private void llenarTablaExistencias(int id) {
		
		this.modelTablaExistencias.getDataVector().removeAllElements();		
		this.articuloController.consultarExistenciasPorSucursal(id, modelTablaExistencias);
		
	}

	private void abrirFormListaArticulos(String nombreArticulo, int idSucursal) {
		Component cm = this;
		Fr_PuntoDeVentas puntoVenta = this;
		try {
			EventQueue.invokeLater(new Runnable() {
				@Override
				public void run() {
					Fr_ListaArticulos frame = new Fr_ListaArticulos(nombreArticulo, idSucursal, puntoVenta);
					frame.setLocationRelativeTo(cm);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				}
			});
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	/**
	 * Método invocado desde el JFrame de consulta. Agrega un nuevo registro al
	 * jTable de productos para la compra
	 * 
	 * @param articulo
	 */
	@Override
	public void listarArticuloDesdeConsulta(Object[] articulo, ArticulosPorVentas art) {		
		modelTablaArticulo.addRow(articulo);
		if(this.articulosVendidos == null) {
			this.articulosVendidos = new ArrayList<ArticulosPorVentas>();
		}
		
		//art.setId_venta(Integer.parseInt(this.txfFolioVenta.getText()));
		this.articulosVendidos.add(art);
		calculoDeTotales();
	}

	private void calculoDeTotales() {

		//var model = (DefaultTableModel) this.tablaArticulos.getModel();
		double total = 0;
		double subtotal = 0;
		double iva = 0;
		int totalArticulos = 0;

		try {

			

			subtotal = total / 1.16;
			iva = total - subtotal;

			

		} catch (Exception er) {
			er.printStackTrace();
			return;
		}

	}
	
	public void refreshAll() {
		
	}
	
	private void abrirFormFormaDePago(Ventas venta){		
		
		if (venta == null) {
			JOptionPane.showMessageDialog(this, "No existe una venta válida para cobrar", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (this.articulosVendidos == null || this.articulosVendidos.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No existen artículos agregados a la venta", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		Component cmp = this;
		Fr_PuntoDeVentas fr = this;
				
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					var form = new Fr_FormasDePago(venta, articulosVendidos, fr);
					form.setVisible(true);
					form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					form.setLocationRelativeTo(cmp);
				}catch(Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
}
