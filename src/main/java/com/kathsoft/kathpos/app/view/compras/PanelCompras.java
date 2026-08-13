package com.kathsoft.kathpos.app.view.compras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.compra.TipoCompraFiltro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;

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
	private JTextField txfFechaFacturaInicio;
	private JTextField txfFechaFacturaFin;
	private JTextField txfFolioFactura;
	private FlowLayout flowLayoutPanelBotones;

	public PanelCompras() {
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
		this.btnAgregar.setIcon(
				new ImageIcon(PanelCompras.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnAgregar.setBackground(new Color(144, 238, 144));
		this.panelBotones.add(this.btnAgregar);

		this.btnActualizar = new JButton("Actualizar");
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

		this.panelFiltros = new JPanel();
		this.panelFiltros.setBackground(new Color(0, 153, 255));

		GroupLayout glPanelPrincipalContenedor = new GroupLayout(this.panelPrincipalContenedor);
		glPanelPrincipalContenedor.setHorizontalGroup(
			glPanelPrincipalContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelBotones, GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
				.addComponent(this.panelFiltros, GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
				.addGroup(glPanelPrincipalContenedor.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.scrollPaneTablaCompras, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
					.addContainerGap())
		);
		glPanelPrincipalContenedor.setVerticalGroup(
			glPanelPrincipalContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelPrincipalContenedor.createSequentialGroup()
					.addComponent(this.panelBotones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.scrollPaneTablaCompras, GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelFiltros, GroupLayout.PREFERRED_SIZE, 87, GroupLayout.PREFERRED_SIZE))
		);

		this.lblProveedor = new JLabel("Proveedor");
		this.cmbProveedor = new JComboBox<JComboboxDataViewModel>();
		this.cmbProveedor.addItem(new JComboboxDataViewModel(0, "Todos"));

		this.lblFechaFacturaInicio = new JLabel("Fecha factura inicio");
		this.txfFechaFacturaInicio = new JTextField();
		this.txfFechaFacturaInicio.setColumns(10);

		this.lblFechaFacturaFin = new JLabel("Fecha factura fin");
		this.txfFechaFacturaFin = new JTextField();
		this.txfFechaFacturaFin.setColumns(10);

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

		this.btnLimpiar = new JButton("Limpiar");
		this.btnLimpiar.setBackground(new Color(176, 196, 222));

		GroupLayout glPanelFiltros = new GroupLayout(this.panelFiltros);
		glPanelFiltros.setHorizontalGroup(
			glPanelFiltros.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelFiltros.createSequentialGroup()
					.addContainerGap()
					.addGroup(glPanelFiltros.createParallelGroup(Alignment.LEADING)
						.addGroup(glPanelFiltros.createSequentialGroup()
							.addComponent(this.lblProveedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbProveedor, 0, 231, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblFolioFactura)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfFolioFactura, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.btnBuscar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btnLimpiar)
							.addGap(6))
						.addGroup(glPanelFiltros.createSequentialGroup()
							.addComponent(this.lblFechaFacturaInicio)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfFechaFacturaInicio, GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.lblFechaFacturaFin)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfFechaFacturaFin, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.lblTipoCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbTipoCompra, 0, 148, Short.MAX_VALUE)))
					.addContainerGap())
		);
		glPanelFiltros.setVerticalGroup(
			glPanelFiltros.createParallelGroup(Alignment.LEADING)
				.addGroup(glPanelFiltros.createSequentialGroup()
					.addContainerGap()
					.addGroup(glPanelFiltros.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblProveedor)
						.addComponent(this.cmbProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblFolioFactura)
						.addComponent(this.txfFolioFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.btnLimpiar)
						.addComponent(this.btnBuscar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(glPanelFiltros.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFechaFacturaInicio)
						.addComponent(this.txfFechaFacturaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblFechaFacturaFin)
						.addComponent(this.txfFechaFacturaFin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblTipoCompra)
						.addComponent(this.cmbTipoCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		this.panelFiltros.setLayout(glPanelFiltros);
		this.panelPrincipalContenedor.setLayout(glPanelPrincipalContenedor);
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
	}

	private void llenarCmbTipoCompra() {
		this.cmbTipoCompra.removeAllItems();
		for (TipoCompraFiltro tipoCompraFiltro : TipoCompraFiltro.values()) {
			this.cmbTipoCompra.addItem(tipoCompraFiltro);
		}
	}

	public TipoCompraFiltro getTipoCompraFiltroSeleccionado() {
		return (TipoCompraFiltro) this.cmbTipoCompra.getSelectedItem();
	}
}
