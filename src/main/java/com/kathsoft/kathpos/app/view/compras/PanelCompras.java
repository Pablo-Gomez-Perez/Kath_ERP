package com.kathsoft.kathpos.app.view.compras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.compra.CompraFiltro;
import com.kathsoft.kathpos.app.model.compra.CompraListado;
import com.kathsoft.kathpos.app.model.compra.TipoCompraFiltro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelCompras extends JPanel {

	private static final long serialVersionUID = 6513201656152065768L;

	private JPanel panelTitulo;
	private JLabel lblTitulo;
	private JPanel panelPrincipalContenedor;
	private JPanel panelBotones;
	private JPanel panelFiltros;
	private JScrollPane scrollPaneTablaCompras;
	private DefaultTableModel modelTablaCompras;
	private JTable tablaCompras;
	private JButton btnAgregar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JButton btnExportarExcel;
	private JButton btnBuscar;
	private JButton btnLimpiar;
	private JLabel lblProveedor;
	private JLabel lblFechaFacturaInicio;
	private JLabel lblFechaFacturaFin;
	private JLabel lblFolioFactura;
	private JLabel lblTipoCompra;
	private JComboBox<JComboboxDataViewModel> cmbProveedor;
	private JComboBox<TipoCompraFiltro> cmbTipoCompra;
	private JTextField txfFolioFactura;
	private FlowLayout flowLayoutPanelBotones;
	private JFormattedTextField formattedTextFieldFechaInicio;
	private JFormattedTextField formattedTextFieldFechaFin;
	private int idSucursal;

	/**
	 * Constructor conservado únicamente para compatibilidad con WindowBuilder y
	 * referencias existentes. Para listar compras debe utilizarse
	 * {@link #PanelCompras(int)}.
	 */
	@Deprecated
	public PanelCompras() {
		this(0);
	}

	public PanelCompras(int idSucursal) {
		this.idSucursal = idSucursal;
		this.setLayout(new BorderLayout(0, 0));
		this.setBackground(new Color(255, 215, 0));

		this.panelTitulo = new JPanel();
		this.panelTitulo.setBackground(new Color(25, 25, 112));
		this.add(this.panelTitulo, BorderLayout.NORTH);

		this.lblTitulo = new JLabel("Compras");
		this.lblTitulo.setForeground(new Color(255, 255, 255));
		this.lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.panelTitulo.add(this.lblTitulo);

		this.panelPrincipalContenedor = new JPanel();
		this.panelPrincipalContenedor.setBackground(new Color(255, 215, 0));
		this.add(this.panelPrincipalContenedor, BorderLayout.CENTER);

		this.panelBotones = new JPanel();
		this.panelBotones.setBackground(new Color(255, 215, 0));
		this.flowLayoutPanelBotones = (FlowLayout) this.panelBotones.getLayout();
		this.flowLayoutPanelBotones.setAlignment(FlowLayout.RIGHT);

		this.btnAgregar = new JButton("Agregar");
		this.btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormCompras(idSucursal);
			}
		});
		this.btnAgregar.setIcon(
				new ImageIcon(PanelCompras.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnAgregar.setBackground(new Color(144, 238, 144));
		this.panelBotones.add(this.btnAgregar);

		this.btnActualizar = new JButton("Actualizar");
		this.btnActualizar.addActionListener(e -> this.abrirCompraSeleccionada());
		this.btnActualizar.setIcon(
				new ImageIcon(PanelCompras.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		this.btnActualizar.setBackground(new Color(144, 238, 144));
		this.panelBotones.add(this.btnActualizar);

		this.btnEliminar = new JButton("Eliminar");
		this.btnEliminar.setIcon(
				new ImageIcon(PanelCompras.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnEliminar.setBackground(new Color(255, 51, 0));
		this.panelBotones.add(this.btnEliminar);

		this.btnExportarExcel = new JButton("Exportar a Excel");
		this.btnExportarExcel.setIcon(
				new ImageIcon(PanelCompras.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.btnExportarExcel.setBackground(new Color(102, 205, 170));
		this.panelBotones.add(this.btnExportarExcel);

		this.scrollPaneTablaCompras = new JScrollPane();
		this.modelTablaCompras = new DefaultTableModel();
		this.tablaCompras = new JTable();
		this.tablaCompras.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.tablaCompras.setModel(this.modelTablaCompras);
		this.scrollPaneTablaCompras.setViewportView(this.tablaCompras);
		this.setDefaultTableModel();
		DataTools.removerEditorDeTabla(this.tablaCompras, this.modelTablaCompras);

		this.panelFiltros = new JPanel();
		this.panelFiltros.setBackground(new Color(0, 153, 255));

		GroupLayout glPanelPrincipalContenedor = new GroupLayout(this.panelPrincipalContenedor);
		glPanelPrincipalContenedor.setHorizontalGroup(glPanelPrincipalContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelBotones, GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
				.addComponent(this.panelFiltros, GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
				.addGroup(glPanelPrincipalContenedor.createSequentialGroup().addContainerGap()
						.addComponent(this.scrollPaneTablaCompras, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
						.addContainerGap()));
		glPanelPrincipalContenedor.setVerticalGroup(glPanelPrincipalContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelPrincipalContenedor.createSequentialGroup()
						.addComponent(this.panelBotones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.scrollPaneTablaCompras, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.panelFiltros, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE)));

		this.lblProveedor = new JLabel("Proveedor");
		this.cmbProveedor = new JComboBox<JComboboxDataViewModel>();
		this.llenarCmbProveedor();

		this.lblFechaFacturaInicio = new JLabel("Fecha factura inicio");

		this.lblFechaFacturaFin = new JLabel("Fecha factura fin");

		this.lblFolioFactura = new JLabel("Folio factura");
		this.txfFolioFactura = new JTextField();
		this.txfFolioFactura.setColumns(10);

		this.lblTipoCompra = new JLabel("Tipo compra");
		this.cmbTipoCompra = new JComboBox<TipoCompraFiltro>();
		this.llenarCmbTipoCompra();

		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.setBackground(new Color(184, 134, 11));
		this.btnBuscar.setIcon(
				new ImageIcon(PanelCompras.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		this.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaCompras();
			}
		});

		this.btnLimpiar = new JButton("Limpiar");
		this.btnLimpiar.setBackground(new Color(176, 196, 222));
		this.btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFiltros();
				llenarTablaCompras();
			}
		});

		this.formattedTextFieldFechaInicio = new JFormattedTextField(buildDateFormatter());
		this.formattedTextFieldFechaInicio.setColumns(10);
		this.formattedTextFieldFechaInicio.setToolTipText("dd/MM/yyyy");

		this.formattedTextFieldFechaFin = new JFormattedTextField(buildDateFormatter());
		this.formattedTextFieldFechaFin.setColumns(10);
		this.formattedTextFieldFechaFin.setToolTipText("dd/MM/yyyy");

		GroupLayout glPanelFiltros = new GroupLayout(this.panelFiltros);
		glPanelFiltros.setHorizontalGroup(glPanelFiltros.createParallelGroup(Alignment.LEADING).addGroup(glPanelFiltros
				.createSequentialGroup().addContainerGap()
				.addGroup(glPanelFiltros.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelFiltros.createSequentialGroup().addComponent(this.lblProveedor)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.cmbProveedor, 0, 231, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblFolioFactura)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.txfFolioFactura, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(this.btnBuscar)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.btnLimpiar).addGap(6))
						.addGroup(glPanelFiltros.createSequentialGroup().addComponent(this.lblFechaFacturaInicio)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.formattedTextFieldFechaInicio, GroupLayout.DEFAULT_SIZE, 149,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblFechaFacturaFin)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.formattedTextFieldFechaFin, GroupLayout.DEFAULT_SIZE, 159,
										Short.MAX_VALUE)
								.addGap(4).addComponent(this.lblTipoCompra).addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(this.cmbTipoCompra, 0, 148, Short.MAX_VALUE)))
				.addContainerGap()));
		glPanelFiltros
				.setVerticalGroup(glPanelFiltros.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelFiltros.createSequentialGroup().addContainerGap()
								.addGroup(glPanelFiltros.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.lblProveedor)
										.addComponent(this.cmbProveedor, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.lblFolioFactura)
										.addComponent(this.txfFolioFactura, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.btnLimpiar).addComponent(this.btnBuscar))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(glPanelFiltros.createParallelGroup(Alignment.BASELINE)
										.addComponent(this.lblFechaFacturaInicio).addComponent(this.lblFechaFacturaFin)
										.addComponent(this.lblTipoCompra)
										.addComponent(this.cmbTipoCompra, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.formattedTextFieldFechaInicio, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(this.formattedTextFieldFechaFin, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addContainerGap(13, Short.MAX_VALUE)));
		this.panelFiltros.setLayout(glPanelFiltros);
		this.panelPrincipalContenedor.setLayout(glPanelPrincipalContenedor);

		if (this.idSucursal > 0) {
			this.llenarTablaCompras();
		}
	}

	private void setDefaultTableModel() {
		this.modelTablaCompras.addColumn("Id");
		this.modelTablaCompras.addColumn("Empleado");
		this.modelTablaCompras.addColumn("Proveedor");
		this.modelTablaCompras.addColumn("Folio Factura");
		this.modelTablaCompras.addColumn("Fecha Factura");
		this.modelTablaCompras.addColumn("Fecha Compra");
		this.modelTablaCompras.addColumn("Tipo Compra");
		this.modelTablaCompras.addColumn("Subtotal");
		this.modelTablaCompras.addColumn("IVA");
		this.modelTablaCompras.addColumn("Total");
		this.modelTablaCompras.addColumn("Activo");
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaComprasColumnsWidth, this.tablaCompras);
	}

	private void borrarElementosDeLaTablaCompras() {
		this.modelTablaCompras.getDataVector().removeAllElements();
		this.tablaCompras.updateUI();
	}

	public void llenarTablaCompras() {
		if (this.idSucursal <= 0) {
			return;
		}

		this.borrarElementosDeLaTablaCompras();
		try {
			CompraFiltro filtro = this.buildCompraFiltro();
			AppContext.compraController.listCompras(this.idSucursal, filtro).forEach(this::addCompraListadoToTable);
		} catch (ParseException er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Formato de fecha inválido. Usa dd/MM/yyyy");
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void addCompraListadoToTable(CompraListado compra) {
		if (compra == null) {
			return;
		}

		this.modelTablaCompras.addRow(new Object[] { compra.getIdCompra(), compra.getIdEmpleado(),
				compra.getIdProveedor(), compra.getFolioFactura(), compra.getFechaFactura(), compra.getFechaCompra(),
				compra.getTipoCompraDescripcion(), compra.getSubtotal(), compra.getIva(), compra.getImporteTotal(),
				compra.isActivo() ? "Activo" : "Inactivo" });
	}

	private CompraFiltro buildCompraFiltro() throws ParseException {
		JComboboxDataViewModel proveedorSeleccionado = (JComboboxDataViewModel) this.cmbProveedor.getSelectedItem();
		TipoCompraFiltro tipoCompraFiltro = this.getTipoCompraFiltroSeleccionado();

		return new CompraFiltro(proveedorSeleccionado == null ? 0 : proveedorSeleccionado.id(),
				this.parseFechaFiltro(this.formattedTextFieldFechaInicio),
				this.parseFechaFiltro(this.formattedTextFieldFechaFin), this.txfFolioFactura.getText().trim(),
				tipoCompraFiltro == null ? null : tipoCompraFiltro.getValor());
	}

	private Date parseFechaFiltro(JFormattedTextField fechaField) throws ParseException {
		String fecha = fechaField.getText() == null ? "" : fechaField.getText().trim();
		if (this.isFechaVacia(fecha)) {
			return null;
		}

		if (fecha.contains("_")) {
			throw new ParseException("Fecha incompleta", 0);
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		return new Date(dateFormat.parse(fecha).getTime());
	}

	private boolean isFechaVacia(String fecha) {
		return fecha == null || fecha.trim().isEmpty() || "__/__/____".equals(fecha.trim());
	}

	private void llenarCmbProveedor() {
		this.cmbProveedor.removeAllItems();
		this.cmbProveedor.addItem(new JComboboxDataViewModel(0, "Todos"));
		AppContext.proveedorController.consultarNombresProveedor().forEach(this.cmbProveedor::addItem);
	}

	private void llenarCmbTipoCompra() {
		this.cmbTipoCompra.removeAllItems();
		for (TipoCompraFiltro tipoCompraFiltro : TipoCompraFiltro.values()) {
			this.cmbTipoCompra.addItem(tipoCompraFiltro);
		}
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

	private void limpiarFiltros() {
		this.txfFolioFactura.setText("");
		this.formattedTextFieldFechaInicio.setValue(null);
		this.formattedTextFieldFechaFin.setValue(null);

		if (this.cmbProveedor.getItemCount() > 0) {
			this.cmbProveedor.setSelectedIndex(0);
		}

		if (this.cmbTipoCompra.getItemCount() > 0) {
			this.cmbTipoCompra.setSelectedIndex(0);
		}
	}

	public TipoCompraFiltro getTipoCompraFiltroSeleccionado() {
		return (TipoCompraFiltro) this.cmbTipoCompra.getSelectedItem();
	}

	private void abrirCompraSeleccionada() {
		int filaSeleccionada = this.tablaCompras.getSelectedRow();
		if (filaSeleccionada < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione una compra para actualizar");
			return;
		}

		int filaModelo = this.tablaCompras.convertRowIndexToModel(filaSeleccionada);
		int idCompra = this.obtenerIdCompraSeleccionada(filaModelo);
		if (idCompra <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"No fue posible identificar la compra seleccionada");
			return;
		}

		this.abrirFormCompras(this.idSucursal, idCompra);
	}

	private int obtenerIdCompraSeleccionada(int filaModelo) {
		Object valor = this.modelTablaCompras.getValueAt(filaModelo, 0);
		if (valor instanceof Number numero) {
			return numero.intValue();
		}

		try {
			return Integer.parseInt(String.valueOf(valor).trim());
		} catch (NumberFormatException er) {
			return 0;
		}
	}

	public void abrirFormCompras(int idSucursal) {
		this.abrirFormCompras(idSucursal, 0);
	}

	public void abrirFormCompras(int idSucursal, int idCompra) {
		JComponent cmp = this;
		try {

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {

					try {

						Fr_DatosCompras form = idCompra > 0 ? new Fr_DatosCompras(idSucursal, idCompra)
								: new Fr_DatosCompras(idSucursal);
						form.setLocationRelativeTo(cmp);
						form.setVisible(true);
						form.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					} catch (Exception e) {
						e.printStackTrace();
						MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, cmp, "Error al abrir form");

					}

				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
