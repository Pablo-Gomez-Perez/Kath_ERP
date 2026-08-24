package com.kathsoft.kathpos.app.view.compras;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.articulo.ArticuloByCodigo;
import com.kathsoft.kathpos.app.model.interfaces.IListadoArticulosAcciones;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.view.shared.Fr_ListaArticulos;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;

public class Fr_DatosCompras extends JFrame implements IListadoArticulosAcciones{

	private static final long serialVersionUID = 1L;
	private static final int COLUMNA_CODIGO = 0;
	private static final int COLUMNA_CANTIDAD = 2;
	private static final int COLUMNA_COSTO_UNITARIO = 3;
	private static final int COLUMNA_SUBTOTAL = 4;
	private static final double FACTOR_IVA = 1.16;
	private int idSucursal;
	private ArticuloByCodigo articuloConsultado;
	private Map<String, ArticuloByCodigo> articulosPorCodigo;
	private boolean actualizandoImportes;
	private double subtotalCompra;
	private double ivaCompra;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralContenedor;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel;
	private JPanel panelDatosCompra;
	private JPanel panelDatosFactura;
	private JPanel panelDatosControlInterno;
	private JLabel lblFolioFactura;
	private JTextField txfFolioFactura;
	private JLabel lblFechaFactura;
	private JFormattedTextField formattedTextFieldFechaFactura;
	private JLabel lblFechaDeCompra;
	private JFormattedTextField formattedTextFieldFechaDeCompra;
	private JLabel lblProveedor;
	private JComboBox<JComboboxDataViewModel> comboBoxProveedor;
	private JLabel lblIdCompra;
	private JTextField txfIdCompra;
	private JButton btnBuscarCompra;
	private JLabel lblRecibe;
	private JComboBox<JComboboxDataViewModel> comboBoxEmpleado;
	private JPanel panelTipoDeCompra;
	private JRadioButton rdbtnCredito;
	private JRadioButton rdbtnContado;
	private ButtonGroup buttonGroupTipoCompra;
	private JPanel panelInferiorConsultaArticulos;
	private JButton btnCancelar;
	private JButton btnGuardarCompra;
	private JLabel lblArticulo;
	private JButton btnAgregarArticulo;
	private JTextField txfNombreCodigoArticulo;
	private JButton btnBuscarArticulo;
	private JPanel panelCentralContenedorTabla;
	private JPanel panelContenedorDatos;
	private JScrollPane scrollPaneTablaArticulos;
	private DefaultTableModel modelTablaArticulosListados;
	private JTable tableArticulosListados;
	private JButton btnEliminarArticuloSeleccionado;
	private JLabel lblSubTotal;
	private JTextField txfSubTotal;
	private JLabel lblIva;
	private JTextField txfIva;
	private JLabel lblTotales;
	private JLabel lblTotalCompra;

	/**
	 * Create the frame.
	 */
	public Fr_DatosCompras() {
		this.articulosPorCodigo = new HashMap<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 630);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(0, 51, 153));
		this.contentPane.add(this.panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		this.lblNewLabel = new JLabel("Compras");
		this.lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		this.lblNewLabel.setForeground(new Color(255, 255, 255));
		this.panelSuperiorEtiqueta.add(this.lblNewLabel);
		
		this.panelCentralContenedor = new JPanel();
		this.contentPane.add(this.panelCentralContenedor, BorderLayout.CENTER);
		
		this.panelDatosCompra = new JPanel();
		this.panelDatosCompra.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de la compra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.panelInferiorConsultaArticulos = new JPanel();
		this.panelInferiorConsultaArticulos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		this.panelCentralContenedorTabla = new JPanel();
		this.panelCentralContenedorTabla.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		this.panelContenedorDatos = new JPanel();
		this.panelContenedorDatos.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_panelCentralContenedor = new GroupLayout(this.panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.TRAILING)
				.addComponent(this.panelDatosCompra, GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
				.addComponent(this.panelInferiorConsultaArticulos, GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.panelCentralContenedorTabla, GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelContenedorDatos, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addComponent(this.panelDatosCompra, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelCentralContenedorTabla, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
						.addComponent(this.panelContenedorDatos, GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelInferiorConsultaArticulos, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		
		this.btnEliminarArticuloSeleccionado = new JButton("Quitar Articulo");
		this.btnEliminarArticuloSeleccionado.addActionListener(e -> this.eliminarArticuloSeleccionado());
		
		this.lblSubTotal = new JLabel("Sub Total");
		
		this.txfSubTotal = new JTextField();
		this.txfSubTotal.setEditable(false);
		this.txfSubTotal.setEnabled(false);
		this.txfSubTotal.setColumns(10);
		
		this.lblIva = new JLabel("IVA pagado");
		
		this.txfIva = new JTextField();
		this.txfIva.setEditable(false);
		this.txfIva.setEnabled(false);
		this.txfIva.setColumns(10);
		
		this.lblTotales = new JLabel("Totales");
		
		this.lblTotalCompra = new JLabel("$0.00");
		this.lblTotalCompra.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 36));
		this.lblTotalCompra.setForeground(new Color(0, 153, 0));
		GroupLayout gl_panelContenedorDatos = new GroupLayout(this.panelContenedorDatos);
		gl_panelContenedorDatos.setHorizontalGroup(
			gl_panelContenedorDatos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContenedorDatos.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelContenedorDatos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelContenedorDatos.createSequentialGroup()
							.addGap(12)
							.addComponent(this.lblTotalCompra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(this.btnEliminarArticuloSeleccionado, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.addComponent(this.lblSubTotal)
						.addComponent(this.txfSubTotal, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.addComponent(this.lblIva)
						.addComponent(this.txfIva, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
						.addComponent(this.lblTotales))
					.addContainerGap())
		);
		gl_panelContenedorDatos.setVerticalGroup(
			gl_panelContenedorDatos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelContenedorDatos.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.btnEliminarArticuloSeleccionado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblSubTotal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfSubTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblIva)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfIva, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblTotales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblTotalCompra, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(79))
		);
		this.panelContenedorDatos.setLayout(gl_panelContenedorDatos);
		this.panelCentralContenedorTabla.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTablaArticulos = new JScrollPane();
		this.panelCentralContenedorTabla.add(this.scrollPaneTablaArticulos, BorderLayout.CENTER);
		
		this.modelTablaArticulosListados = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == COLUMNA_CANTIDAD;
			}
		};
		this.modelTablaArticulosListados.addColumn("Código");
		this.modelTablaArticulosListados.addColumn("Descripción");
		this.modelTablaArticulosListados.addColumn("Cantidad");
		this.modelTablaArticulosListados.addColumn("Costo Unitario");
		this.modelTablaArticulosListados.addColumn("Subtotal");
		
		this.tableArticulosListados = new JTable();
		this.tableArticulosListados.setModel(this.modelTablaArticulosListados);
		this.tableArticulosListados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPaneTablaArticulos.setViewportView(this.tableArticulosListados);
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaArticulosCompraColumnsWidth,
				this.tableArticulosListados);
		this.configurarEditoresTablaArticulos();
		this.configurarEventosTablaArticulos();
		
		this.lblArticulo = new JLabel("Articulo");
		
		this.btnAgregarArticulo = new JButton("Agregar");
		this.btnAgregarArticulo.addActionListener(e -> this.agregarArticuloConsultado());
		
		this.txfNombreCodigoArticulo = new JTextField();
		this.lblArticulo.setLabelFor(this.txfNombreCodigoArticulo);
		this.txfNombreCodigoArticulo.setToolTipText("Ingresa el código del artículo para registrarlo o el nombre para consultar");
		this.txfNombreCodigoArticulo.setColumns(10);
		this.txfNombreCodigoArticulo.addActionListener(e -> this.procesarEnterArticulo());
		
		this.btnBuscarArticulo = new JButton("Buscar");
		this.btnBuscarArticulo.addActionListener(e -> this.buscarArticulo());
		GroupLayout gl_panelInferiorConsultaArticulos = new GroupLayout(this.panelInferiorConsultaArticulos);
		gl_panelInferiorConsultaArticulos.setHorizontalGroup(
			gl_panelInferiorConsultaArticulos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorConsultaArticulos.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.lblArticulo)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfNombreCodigoArticulo, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnBuscarArticulo)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.btnAgregarArticulo)
					.addContainerGap())
		);
		gl_panelInferiorConsultaArticulos.setVerticalGroup(
			gl_panelInferiorConsultaArticulos.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelInferiorConsultaArticulos.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelInferiorConsultaArticulos.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.btnAgregarArticulo)
						.addComponent(this.txfNombreCodigoArticulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblArticulo)
						.addComponent(this.btnBuscarArticulo))
					.addContainerGap())
		);
		this.panelInferiorConsultaArticulos.setLayout(gl_panelInferiorConsultaArticulos);
		this.panelDatosCompra.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panelDatosFactura = new JPanel();
		this.panelDatosFactura.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Provedor y factura", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 102)));
		this.panelDatosCompra.add(this.panelDatosFactura);
		
		this.lblFolioFactura = new JLabel("Folio Factura");
		
		this.txfFolioFactura = new JTextField();
		this.lblFolioFactura.setLabelFor(this.txfFolioFactura);
		this.txfFolioFactura.setColumns(10);
		
		this.lblFechaFactura = new JLabel("Fecha Factura");
		
		this.formattedTextFieldFechaFactura = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaFactura.setToolTipText("dd/MM/yyyy");
		this.lblFechaFactura.setLabelFor(this.formattedTextFieldFechaFactura);
		
		this.lblFechaDeCompra = new JLabel("Fecha de Compra");
		
		this.formattedTextFieldFechaDeCompra = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaDeCompra.setToolTipText("dd/MM/yyyy");
		this.lblFechaDeCompra.setLabelFor(this.formattedTextFieldFechaDeCompra);
		
		this.lblProveedor = new JLabel("Proveedor");
		
		this.comboBoxProveedor = new JComboBox<JComboboxDataViewModel>();
		this.llenarComboBoxProveedor();
		GroupLayout gl_panelDatosFactura = new GroupLayout(this.panelDatosFactura);
		gl_panelDatosFactura.setHorizontalGroup(
			gl_panelDatosFactura.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosFactura.createSequentialGroup()
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblFolioFactura)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfFolioFactura, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblFechaFactura)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaFactura, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblFechaDeCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaDeCompra, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblProveedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.comboBoxProveedor, 0, 306, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelDatosFactura.setVerticalGroup(
			gl_panelDatosFactura.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosFactura.createSequentialGroup()
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFolioFactura)
						.addComponent(this.txfFolioFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFechaFactura)
						.addComponent(this.formattedTextFieldFechaFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFechaDeCompra)
						.addComponent(this.formattedTextFieldFechaDeCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblProveedor)
						.addComponent(this.comboBoxProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		this.panelDatosFactura.setLayout(gl_panelDatosFactura);
		
		this.panelDatosControlInterno = new JPanel();
		this.panelDatosControlInterno.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Control Interno", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelDatosCompra.add(this.panelDatosControlInterno);
		
		this.lblIdCompra = new JLabel("ID Compra");
		
		this.txfIdCompra = new JTextField();
		this.txfIdCompra.setColumns(10);
		
		this.btnBuscarCompra = new JButton("Buscar");
		
		this.lblRecibe = new JLabel("Recibe");
		
		this.comboBoxEmpleado = new JComboBox<JComboboxDataViewModel>();
		
		this.panelTipoDeCompra = new JPanel();
		FlowLayout flowLayoutPanelTipoCompra = (FlowLayout) this.panelTipoDeCompra.getLayout();
		flowLayoutPanelTipoCompra.setAlignment(FlowLayout.LEFT);
		flowLayoutPanelTipoCompra.setVgap(0);
		flowLayoutPanelTipoCompra.setHgap(0);
		this.panelTipoDeCompra.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo de compra", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GroupLayout gl_panelDatosControlInterno = new GroupLayout(this.panelDatosControlInterno);
		gl_panelDatosControlInterno.setHorizontalGroup(
			gl_panelDatosControlInterno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDatosControlInterno.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelTipoDeCompra, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
							.addComponent(this.lblIdCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfIdCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btnBuscarCompra)
							.addGap(97))
						.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
							.addComponent(this.lblRecibe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.comboBoxEmpleado, 0, 315, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelDatosControlInterno.setVerticalGroup(
			gl_panelDatosControlInterno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
					.addGroup(gl_panelDatosControlInterno.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblIdCompra)
						.addComponent(this.txfIdCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.btnBuscarCompra))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosControlInterno.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblRecibe)
						.addComponent(this.comboBoxEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelTipoDeCompra, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
		);
		
		this.buttonGroupTipoCompra = new ButtonGroup();
		
		this.rdbtnContado = new JRadioButton("Contado");
		this.buttonGroupTipoCompra.add(this.rdbtnContado);
		this.panelTipoDeCompra.add(this.rdbtnContado);
		
		this.rdbtnCredito = new JRadioButton("Credito");
		this.buttonGroupTipoCompra.add(this.rdbtnCredito);
		this.panelTipoDeCompra.add(this.rdbtnCredito);
		this.panelDatosControlInterno.setLayout(gl_panelDatosControlInterno);
		this.panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		
		this.panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.contentPane.add(this.panelInferiorBotones, BorderLayout.SOUTH);
		
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setToolTipText("Cerrar form y cancelar operacion");
		this.panelInferiorBotones.add(this.btnCancelar);
		
		this.btnGuardarCompra = new JButton("Guardar");
		this.btnGuardarCompra.setToolTipText("Guardar compra");
		this.panelInferiorBotones.add(this.btnGuardarCompra);

	}

	/**
	 * Crea el formulario con el contexto de la sucursal desde la cual se registra
	 * la compra. Este identificador se utiliza para cargar únicamente los empleados
	 * pertenecientes a la sucursal actual.
	 *
	 * @param idSucursal identificador de la sucursal de trabajo actual
	 */
	public Fr_DatosCompras(int idSucursal) {
		this();
		this.idSucursal = idSucursal;
		this.llenarComboBoxEmpleado();
		this.cargarSiguienteIdCompra();
	}

	private void llenarComboBoxProveedor() {
		this.comboBoxProveedor.removeAllItems();
		AppContext.proveedorController.consultarNombresProveedor().forEach(this.comboBoxProveedor::addItem);
	}

	private void llenarComboBoxEmpleado() {
		this.comboBoxEmpleado.removeAllItems();
		if (this.idSucursal <= 0) {
			return;
		}
		AppContext.empleadoController.consultaNombresCortosEmpleados(this.idSucursal)
				.forEach(this.comboBoxEmpleado::addItem);
	}

	private void cargarSiguienteIdCompra() {
		if (this.idSucursal <= 0) {
			return;
		}
		this.txfIdCompra.setText(String.valueOf(AppContext.compraController.getSiguienteIdCompra(this.idSucursal)));
	}

	private MaskFormatter buildDateFormatter() {
		try {
			MaskFormatter formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
			formatter.setValidCharacters("0123456789");
			return formatter;
		} catch (ParseException er) {
			er.printStackTrace(System.err);
			return null;
		}
	}

	private void configurarEditoresTablaArticulos() {
		DefaultCellEditor editorCantidad = new DefaultCellEditor(new JTextField());
		this.tableArticulosListados.setDefaultEditor(Object.class, null);
		this.tableArticulosListados.getColumnModel().getColumn(COLUMNA_CANTIDAD).setCellEditor(editorCantidad);
	}

	private void configurarEventosTablaArticulos() {
		this.modelTablaArticulosListados.addTableModelListener(event -> {
			if (this.actualizandoImportes || event.getType() != TableModelEvent.UPDATE
					|| event.getColumn() != COLUMNA_CANTIDAD) {
				return;
			}
			this.recalcularImportesDesdeTabla(true);
		});

		String accionEliminar = "eliminarArticuloCompra";
		this.tableArticulosListados.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), accionEliminar);
		this.tableArticulosListados.getActionMap().put(accionEliminar, new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableArticulosListados.getSelectedRow() >= 0
						&& tableArticulosListados.getSelectedColumn() == COLUMNA_CODIGO) {
					eliminarArticuloSeleccionado();
				}
			}
		});
	}

	private void procesarEnterArticulo() {
		if (this.articuloConsultado != null && this.articuloConsultado.getIdArticulo() > 0) {
			this.agregarArticuloConsultado();
			return;
		}
		this.buscarArticulo();
	}

	private void buscarArticulo() {
		String textoBusqueda = this.txfNombreCodigoArticulo.getText().trim();
		this.articuloConsultado = null;

		if (textoBusqueda.isEmpty()) {
			this.abrirFormListaArticulos("");
			return;
		}

		try {
			ArticuloByCodigo articulo = AppContext.articuloController.consultarArticuloPorCodigo(textoBusqueda,
					this.idSucursal);

			if (articulo == null) {
				return;
			}

			if (articulo.getIdArticulo() <= 0) {
				this.abrirFormListaArticulos(textoBusqueda);
				return;
			}

			this.articuloConsultado = articulo;
			this.txfNombreCodigoArticulo.setText(articulo.getNombre());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error al consultar el artículo: " + er.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void agregarArticuloConsultado() {
		if (this.articuloConsultado == null || this.articuloConsultado.getIdArticulo() <= 0) {
			JOptionPane.showMessageDialog(this, "Primero debe buscar un artículo por código", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		Integer cantidad = this.solicitarCantidadArticulos();
		if (cantidad == null) {
			return;
		}

		this.agregarArticuloATabla(this.articuloConsultado, cantidad.intValue());
		this.limpiarArticuloConsultado();
	}

	private Integer solicitarCantidadArticulos() {
		String cantidadIngresada = JOptionPane.showInputDialog(this, "Ingrese la cantidad de artículos");
		if (cantidadIngresada == null) {
			return null;
		}

		try {
			int cantidad = Integer.parseInt(cantidadIngresada.trim());
			if (cantidad <= 0) {
				throw new NumberFormatException("La cantidad debe ser mayor a cero");
			}
			return Integer.valueOf(cantidad);
		} catch (NumberFormatException er) {
			JOptionPane.showMessageDialog(this, "Ingrese una cantidad entera mayor a cero", "Cantidad inválida",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}

	private void agregarArticuloATabla(ArticuloByCodigo articulo, int cantidad) {
		if (articulo == null || articulo.getIdArticulo() <= 0 || cantidad <= 0) {
			return;
		}

		double importeArticulo = articulo.getCostoUnitario() * cantidad;
		this.articulosPorCodigo.put(articulo.getCodigoArticulo(), articulo);
		this.modelTablaArticulosListados.addRow(new Object[] {
				articulo.getCodigoArticulo(),
				articulo.getDescripcion(),
				cantidad,
				articulo.getCostoUnitario(),
				importeArticulo
		});
		this.recalcularImportesDesdeTabla(false);
	}

	private void eliminarArticuloSeleccionado() {
		int filaSeleccionada = this.tableArticulosListados.getSelectedRow();
		if (filaSeleccionada < 0) {
			JOptionPane.showMessageDialog(this, "Seleccione un artículo para eliminar", "Atención",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		if (this.tableArticulosListados.isEditing() && this.tableArticulosListados.getCellEditor() != null) {
			this.tableArticulosListados.getCellEditor().stopCellEditing();
		}

		int filaModelo = this.tableArticulosListados.convertRowIndexToModel(filaSeleccionada);
		this.modelTablaArticulosListados.removeRow(filaModelo);
		this.recalcularImportesDesdeTabla(false);
	}

	private void recalcularImportesDesdeTabla(boolean mostrarErrorCantidad) {
		if (this.actualizandoImportes) {
			return;
		}

		this.actualizandoImportes = true;
		try {
			int totalFilas = this.modelTablaArticulosListados.getRowCount();
			double[] importes = new double[totalFilas];
			double nuevoSubtotal = 0;
			double nuevoIva = 0;

			for (int fila = 0; fila < totalFilas; fila++) {
				int cantidad = this.obtenerCantidadFila(fila);
				double costoUnitario = this.obtenerDoubleFila(fila, COLUMNA_COSTO_UNITARIO);
				double importeConIva = costoUnitario * cantidad;
				importes[fila] = importeConIva;

				String codigo = String.valueOf(this.modelTablaArticulosListados.getValueAt(fila, COLUMNA_CODIGO));
				ArticuloByCodigo articulo = this.articulosPorCodigo.get(codigo);
				boolean exento = articulo != null && articulo.isExento();

				if (exento) {
					nuevoSubtotal += importeConIva;
				} else {
					double baseGravada = importeConIva / FACTOR_IVA;
					nuevoSubtotal += baseGravada;
					nuevoIva += importeConIva - baseGravada;
				}
			}

			for (int fila = 0; fila < totalFilas; fila++) {
				this.modelTablaArticulosListados.setValueAt(importes[fila], fila, COLUMNA_SUBTOTAL);
			}

			this.subtotalCompra = nuevoSubtotal;
			this.ivaCompra = nuevoIva;
			this.actualizarTotalesCompra();
		} catch (NumberFormatException er) {
			if (mostrarErrorCantidad) {
				JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero mayor a cero",
						"Cantidad inválida", JOptionPane.WARNING_MESSAGE);
			}
		} finally {
			this.actualizandoImportes = false;
		}
	}

	private int obtenerCantidadFila(int fila) {
		Object valor = this.modelTablaArticulosListados.getValueAt(fila, COLUMNA_CANTIDAD);
		int cantidad;
		if (valor instanceof Number numero) {
			cantidad = numero.intValue();
		} else {
			cantidad = Integer.parseInt(String.valueOf(valor).trim());
		}
		if (cantidad <= 0) {
			throw new NumberFormatException("La cantidad debe ser mayor a cero");
		}
		return cantidad;
	}

	private double obtenerDoubleFila(int fila, int columna) {
		Object valor = this.modelTablaArticulosListados.getValueAt(fila, columna);
		if (valor instanceof Number numero) {
			return numero.doubleValue();
		}
		return Double.parseDouble(String.valueOf(valor).trim());
	}

	private void actualizarTotalesCompra() {
		double totalCompra = this.subtotalCompra + this.ivaCompra;
		this.txfSubTotal.setText(String.format("%.2f", this.subtotalCompra));
		this.txfIva.setText(String.format("%.2f", this.ivaCompra));
		this.lblTotalCompra.setText(String.format("$%.2f", totalCompra));
	}

	private void limpiarArticuloConsultado() {
		this.articuloConsultado = null;
		this.txfNombreCodigoArticulo.setText("");
		this.txfNombreCodigoArticulo.requestFocusInWindow();
	}

	private void abrirFormListaArticulos(String nombreArticulo) {
		EventQueue.invokeLater(() -> {
			Fr_ListaArticulos frame = new Fr_ListaArticulos(nombreArticulo, this.idSucursal, this);
			frame.setLocationRelativeTo(this);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
		});
	}

	/**
	 * Obtiene el tipo de compra seleccionado en los radio buttons.
	 *
	 * @return {@link Boolean#TRUE} para crédito, {@link Boolean#FALSE} para contado
	 *         o {@code null} cuando no existe una selección
	 */
	public Boolean getTipoCompraSeleccionado() {
		if (this.rdbtnCredito.isSelected()) {
			return Boolean.TRUE;
		}
		if (this.rdbtnContado.isSelected()) {
			return Boolean.FALSE;
		}
		return null;
	}

	@Override
	public void listarArticuloDesdeConsulta(ArticuloByCodigo articulo, int cantidad, BigDecimal precio) {
		this.agregarArticuloATabla(articulo, cantidad);
		this.limpiarArticuloConsultado();
	}

	@Override
	public void listarArticuloDesdeConsulta(Object[] articulo, ArticulosPorVentas art) {
		// Compatibilidad con el contrato legado utilizado todavía por punto de venta.
	}
}
