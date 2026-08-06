package com.kathsoft.kathpos.app.view.articulo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;

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

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.articulo.CriterioBusquedaArticulo;
import com.kathsoft.kathpos.app.model.articulo.CriterioOrdenamientoArticulo;
import com.kathsoft.kathpos.app.view.articulo.Fr_DatosArticulo;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelArticulos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiquetaArticulos;
	private JLabel lblNewLabel;
	private JPanel panelArticulosCentral;
	private JScrollPane scrollPaneTablaArticulos;
	private DefaultTableModel modelTablaArticulos;
	private JTable tablaArticulos;
	private JPanel panelArticulosCentralBotones;
	private JButton btnAgregarArticulo;
	private JButton btnActualizarArticulo;
	private JButton btnEliminarArticulo;
	private JButton btnExportarArticuloExcel;
	private JPanel panelArticulosCentralBuscar;

	/** Datos de la sucursal desde la que se inició sesión. */
	private Sucursal sucursal;

	private JLabel lblBuscar;

	/** Texto que será buscado dependiendo el criterio de busqueda */
	private JTextField txfBuscarArticulo;
	private JButton btnBuscar;
	private JLabel lblBuscarPor;
	private JLabel lblCliente;

	/** Listado de los tipos de cliente en base a los cuales se refleja el precio en la lista */
	private JComboBox<JComboboxDataViewModel> cmbTipoCliente;

	/** Representa la columna por la cual se filtrará la busqueda */
	private JComboBox<CriterioBusquedaArticulo> cmbTipoBusqueda;

	private JLabel lblOrdenarPor;

	/** Representa la forma en la que la busqueda ordenará el resultado listado */
	private JComboBox<CriterioOrdenamientoArticulo> cmbCriterioDeOrdenacion;

	/**
	 * Create the panel.
	 */
	public PanelArticulos(Sucursal sucursal) {

		this.sucursal = sucursal;

		this.setLayout(new BorderLayout(0, 0));

		this.panelEtiquetaArticulos = new JPanel();
		this.panelEtiquetaArticulos.setBackground(new Color(25, 25, 112));
		this.add(panelEtiquetaArticulos, BorderLayout.NORTH);

		this.lblNewLabel = new JLabel("Modulo de Articulos");
		this.lblNewLabel.setForeground(new Color(255, 255, 255));
		this.lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.panelEtiquetaArticulos.add(lblNewLabel);

		this.panelArticulosCentral = new JPanel();
		this.panelArticulosCentral.setBorder(null);
		this.panelArticulosCentral.setBackground(new Color(255, 215, 0));
		this.add(panelArticulosCentral, BorderLayout.CENTER);

		this.scrollPaneTablaArticulos = new JScrollPane();

		this.modelTablaArticulos = new DefaultTableModel();
		this.tablaArticulos = new JTable();
		this.tablaArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPaneTablaArticulos.setViewportView(tablaArticulos);
		this.tablaArticulos.setModel(modelTablaArticulos);
		this.setDefaultTableModel();

		DataTools.removerEditorDeTabla(tablaArticulos, modelTablaArticulos);

		panelArticulosCentralBotones = new JPanel();
		panelArticulosCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_2 = (FlowLayout) panelArticulosCentralBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);

		btnAgregarArticulo = new JButton("Agregar");
		btnAgregarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				abrirVentanaFormularioArticulo(0, 0, sucursal.getIdSucursal());

			}
		});
		btnAgregarArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
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
		btnActualizarArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnActualizarArticulo.setBackground(new Color(144, 238, 144));
		panelArticulosCentralBotones.add(btnActualizarArticulo);

		btnEliminarArticulo = new JButton("Eliminar");
		btnEliminarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarArticulo();
			}
		});
		btnEliminarArticulo.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnEliminarArticulo.setBackground(new Color(255, 51, 0));
		panelArticulosCentralBotones.add(btnEliminarArticulo);

		btnExportarArticuloExcel = new JButton("Exportar a Excel");
		btnExportarArticuloExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarArticuloExcel();
			}
		});
		btnExportarArticuloExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExportarArticuloExcel.setBackground(new Color(102, 205, 170));
		panelArticulosCentralBotones.add(btnExportarArticuloExcel);

		panelArticulosCentralBuscar = new JPanel();
		panelArticulosCentralBuscar.setBackground(new Color(0, 153, 255));

		GroupLayout gl_panelArticulosCentral = new GroupLayout(this.panelArticulosCentral);
		gl_panelArticulosCentral.setHorizontalGroup(
			gl_panelArticulosCentral.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelArticulosCentralBotones, GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
				.addComponent(this.panelArticulosCentralBuscar, GroupLayout.PREFERRED_SIZE, 775, Short.MAX_VALUE)
				.addGroup(gl_panelArticulosCentral.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.scrollPaneTablaArticulos, GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelArticulosCentral.setVerticalGroup(
			gl_panelArticulosCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelArticulosCentral.createSequentialGroup()
					.addComponent(this.panelArticulosCentralBotones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.scrollPaneTablaArticulos, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelArticulosCentralBuscar, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
		);

		this.lblBuscar = new JLabel("Buscar");

		this.txfBuscarArticulo = new JTextField();
		this.txfBuscarArticulo.setColumns(10);

		this.btnBuscar = new JButton("Buscar");
		this.btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaArticulos();
			}
		});
		this.btnBuscar.setBackground(new Color(184, 134, 11));
		this.btnBuscar.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));

		this.lblBuscarPor = new JLabel("Buscar Por:");

		this.lblCliente = new JLabel("Tipo Cliente");

		this.cmbTipoCliente = new JComboBox<JComboboxDataViewModel>();

		this.cmbTipoBusqueda = new JComboBox<CriterioBusquedaArticulo>();

		this.lblOrdenarPor = new JLabel("Ordenar Por");

		this.cmbCriterioDeOrdenacion = new JComboBox<CriterioOrdenamientoArticulo>();

		this.llenarCmbTipoBusqueda();
		this.llenarCmbCriterioDeOrdenacion();
		this.llenarCmbTipoCliente();

		GroupLayout gl_panelArticulosCentralBuscar = new GroupLayout(this.panelArticulosCentralBuscar);
		gl_panelArticulosCentralBuscar.setHorizontalGroup(
			gl_panelArticulosCentralBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelArticulosCentralBuscar.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelArticulosCentralBuscar.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelArticulosCentralBuscar.createSequentialGroup()
							.addComponent(this.lblBuscar)
							.addGap(3)
							.addComponent(this.txfBuscarArticulo, GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btnBuscar))
						.addGroup(gl_panelArticulosCentralBuscar.createSequentialGroup()
							.addComponent(this.lblBuscarPor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbTipoBusqueda, 0, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblOrdenarPor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbCriterioDeOrdenacion, 0, 116, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblCliente)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbTipoCliente, 0, 180, Short.MAX_VALUE)))
					.addGap(6))
		);
		gl_panelArticulosCentralBuscar.setVerticalGroup(
			gl_panelArticulosCentralBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelArticulosCentralBuscar.createSequentialGroup()
					.addGroup(gl_panelArticulosCentralBuscar.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txfBuscarArticulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.btnBuscar)
						.addComponent(this.lblBuscar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelArticulosCentralBuscar.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblBuscarPor)
						.addComponent(this.cmbTipoBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblOrdenarPor)
						.addComponent(this.cmbCriterioDeOrdenacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblCliente)
						.addComponent(this.cmbTipoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		this.panelArticulosCentralBuscar.setLayout(gl_panelArticulosCentralBuscar);
		this.panelArticulosCentral.setLayout(gl_panelArticulosCentral);

		this.llenarTablaArticulos();
	}

	private void abrirVentanaFormularioArticulo(int opcion, int idArticulo, int sucursal) {

		try {
			Fr_DatosArticulo form = new Fr_DatosArticulo(opcion, idArticulo, sucursal);
			form.setLocationRelativeTo(this);
			form.addWindowListener(new WindowAdapter() {
				
				public void windowClosed(WindowEvent e) {
					if (form.isOperacionEjecutada()) {
						llenarTablaArticulos();
					}
				}
			});
			form.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, e.getMessage());
		}

	}

	private void eliminarArticulo() {
		if (this.tablaArticulos.getSelectedRow() < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione un articulo para eliminar");
			return;
		}

		int idArticulo = DataTools.getIndiceElementoSeleccionado(
				this.tablaArticulos,
				this.modelTablaArticulos,
				0
		);

		if (idArticulo < 0) {
			return;
		}

		int option = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, " seleccionado?");

		if (option != 0) {
			return;
		}

		try {
			AppContext.articuloController.eliminarArticulo(idArticulo);
			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, "");
			this.llenarTablaArticulos();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	public void exportarArticuloExcel() {
		try {
			DataTools.exportarTablaExcel(modelTablaArticulos, this);
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
		}
	}

	private void setDefaultTableModel() {
		this.modelTablaArticulos.addColumn("Id");
		this.modelTablaArticulos.addColumn("Proveedor");
		this.modelTablaArticulos.addColumn("Categoría");
		this.modelTablaArticulos.addColumn("Código");
		this.modelTablaArticulos.addColumn("Nombre");
		this.modelTablaArticulos.addColumn("Exento");
		this.modelTablaArticulos.addColumn("Costo Unitario");
		this.modelTablaArticulos.addColumn("Precio");
		this.modelTablaArticulos.addColumn("Existencia");
		this.modelTablaArticulos.addColumn("Activo");

		DataTools.definirTamanioDeColumnas(
				ConstantsConllections.tablaArticulosColumnsWidth,
				this.tablaArticulos
		);
	}

	private void borrarElementosDeLaTablaArticulos() {
		this.modelTablaArticulos.getDataVector().removeAllElements();
		this.tablaArticulos.updateUI();
	}

	public void llenarTablaArticulos() {
		this.borrarElementosDeLaTablaArticulos();

		JComboboxDataViewModel tipoCliente = (JComboboxDataViewModel) this.cmbTipoCliente.getSelectedItem();
		CriterioBusquedaArticulo tipoBusqueda = (CriterioBusquedaArticulo) this.cmbTipoBusqueda.getSelectedItem();
		CriterioOrdenamientoArticulo ordenarPor = (CriterioOrdenamientoArticulo) this.cmbCriterioDeOrdenacion.getSelectedItem();

		if (tipoCliente == null || tipoBusqueda == null || ordenarPor == null) {
			return;
		}

		AppContext.articuloController.verArticulosEnTabla(
				this.sucursal.getIdSucursal(),
				tipoBusqueda.name(),
				ordenarPor.name(),
				this.txfBuscarArticulo.getText().trim(),
				tipoCliente.id()
		).forEach(this.modelTablaArticulos::addRow);
	}

	public void llenarCmbTipoCliente() {
		this.cmbTipoCliente.removeAllItems();
		AppContext.tipoClienteController.cmbTipoCliente().forEach(tipoCliente -> {
			this.cmbTipoCliente.addItem(new JComboboxDataViewModel(tipoCliente.getIdTipoCliente(), tipoCliente.getNombre()));
		});
	}

	private void llenarCmbTipoBusqueda() {
		this.cmbTipoBusqueda.removeAllItems();
		for (CriterioBusquedaArticulo criterio : CriterioBusquedaArticulo.values()) {
			this.cmbTipoBusqueda.addItem(criterio);
		}
	}

	private void llenarCmbCriterioDeOrdenacion() {
		this.cmbCriterioDeOrdenacion.removeAllItems();
		for (CriterioOrdenamientoArticulo criterio : CriterioOrdenamientoArticulo.values()) {
			this.cmbCriterioDeOrdenacion.addItem(criterio);
		}
	}
}
