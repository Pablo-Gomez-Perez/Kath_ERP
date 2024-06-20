package com.kathsoft.kathpos.app.view.articulo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.TipoCliente;
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
	private Box verticalBox;
	private Box horizontalBox_16;
	private Box horizontalBox_17;
	private JLabel lblNewLabel_20;
	private Component horizontalStrut_14;
	private ButtonGroup btnRadioGroupArticulos;
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
	private JLabel lblNewLabel_10;
	private Component horizontalStrut_4;
	private JComboBox<TipoCliente> cmb_tipoCliente;
	private JLabel lblNewLabel_19;
	private Component horizontalStrut_13;
	private JTextField txfBuscarArticulo;
	private Box verticalBox_1;
	private JButton btnBuscarArticulo;
	private Sucursal sucursal;

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
		this.panelArticulosCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.panelArticulosCentral.setBackground(new Color(255, 215, 0));
		this.add(panelArticulosCentral, BorderLayout.CENTER);
		this.panelArticulosCentral.setLayout(new BorderLayout(0, 0));

		this.scrollPaneTablaArticulos = new JScrollPane();
		this.panelArticulosCentral.add(scrollPaneTablaArticulos);

		this.modelTablaArticulos = new DefaultTableModel();
		this.tablaArticulos = new JTable();
		this.tablaArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPaneTablaArticulos.setViewportView(tablaArticulos);
		this.tablaArticulos.setModel(modelTablaArticulos);

		this.modelTablaArticulos.addColumn("Id");
		this.modelTablaArticulos.addColumn("Codigo");
		this.modelTablaArticulos.addColumn("Proveedor");
		this.modelTablaArticulos.addColumn("Categoría");
		this.modelTablaArticulos.addColumn("Codigo Sat");
		this.modelTablaArticulos.addColumn("Nombre");
		this.modelTablaArticulos.addColumn("Descripción");
		this.modelTablaArticulos.addColumn("Existencia");
		this.modelTablaArticulos.addColumn("Precios G");
		this.modelTablaArticulos.addColumn("Precio E");
		this.modelTablaArticulos.addColumn("Despues de");
		this.modelTablaArticulos.addColumn("Estatus");

		DataTools.removerEditorDeTabla(tablaArticulos, modelTablaArticulos);

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

		horizontalStrut_14 = Box.createHorizontalStrut(10);
		horizontalBox_17.add(horizontalStrut_14);

		btnRadioGroupArticulos = new ButtonGroup();

		rdbBuscarArtPorNombre = new JRadioButton("Nombre");
		rdbBuscarArtPorNombre.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbBuscarArtPorNombre.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbBuscarArtPorNombre);

		horizontalStrut_15 = Box.createHorizontalStrut(10);
		horizontalBox_17.add(horizontalStrut_15);

		rdbtBuscarArtPorProveedor = new JRadioButton("Proveedor");
		rdbtBuscarArtPorProveedor.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorProveedor.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorProveedor);

		horizontalStrut_16 = Box.createHorizontalStrut(10);
		horizontalBox_17.add(horizontalStrut_16);

		rdbtBuscarArtPorCategoria = new JRadioButton("Categoria");
		rdbtBuscarArtPorCategoria.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorCategoria.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorCategoria);

		horizontalStrut_17 = Box.createHorizontalStrut(10);
		horizontalBox_17.add(horizontalStrut_17);

		rdbtBuscarArtPorCodigo = new JRadioButton("Código");
		rdbtBuscarArtPorCodigo.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtBuscarArtPorCodigo.setBackground(new Color(255, 215, 0));
		horizontalBox_17.add(rdbtBuscarArtPorCodigo);

		horizontalStrut_18 = Box.createHorizontalStrut(10);
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

		horizontalStrut_19 = Box.createHorizontalStrut(10);
		horizontalBox_17.add(horizontalStrut_19);

		lblNewLabel_10 = new JLabel("Tipo de Cliente");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 12));
		horizontalBox_17.add(lblNewLabel_10);

		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_17.add(horizontalStrut_4);

		cmb_tipoCliente = new JComboBox<TipoCliente>();
		horizontalBox_17.add(cmb_tipoCliente);

		lblNewLabel_19 = new JLabel("Buscar artículo");
		lblNewLabel_19.setFont(new Font("Tahoma", Font.BOLD, 13));
		horizontalBox_16.add(lblNewLabel_19);
		horizontalStrut_13 = Box.createHorizontalStrut(5);
		horizontalBox_16.add(horizontalStrut_13);

		txfBuscarArticulo = new JTextField();
		txfBuscarArticulo.setFont(new Font("Tahoma", Font.BOLD, 13));
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
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));

		verticalBox_1.add(btnBuscarArticulo);

		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaArticulosColumnsWidth, tablaArticulos);
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

	private void eliminarArticulo() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, "");

		if (input > 0) {
			return;
		}

		try {
			AppContext.articuloController
					.eliminarArticulo(DataTools.getIndiceElementoSeleccionado(tablaArticulos, modelTablaArticulos, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, "");

		} catch (SQLException er) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}

	}

	public void exportarArticuloExcel() {
		try {

			DataTools.exportarTablaExcel(modelTablaArticulos, this);

		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
			er.printStackTrace();
		}
	}

	private void consultarArticulosPorNombre() {
		this.borrarElementosDeLaTablaArticulos();
		int idTipoCliente = AppContext.tipoClienteController.cmbTipoCliente()
				.get(this.cmb_tipoCliente.getSelectedIndex()).getIdTipoCliente();
		AppContext.articuloController.consultarArticulosPorNombre(this.txfBuscarArticulo.getText(),
				opcionDeBusquedaDeArticulo(), sucursal.getIdSucursal(), idTipoCliente).forEach(Ar -> {
					this.modelTablaArticulos.addRow(Ar);
				});
		;
	}

	private void borrarElementosDeLaTablaArticulos() {
		this.modelTablaArticulos.getDataVector().removeAllElements();
		this.tablaArticulos.updateUI();
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

	public void llenarTablaArticulos() {
		this.borrarElementosDeLaTablaArticulos();
		int idTipoCliente = AppContext.tipoClienteController.cmbTipoCliente()
				.get(this.cmb_tipoCliente.getSelectedIndex()).getIdTipoCliente();
		AppContext.articuloController.verArticulosEnTabla(this.sucursal.getIdSucursal(), idTipoCliente).forEach(a -> {
			this.modelTablaArticulos.addRow(a);
		});
	}

	public void llenarCmbTipoCliente() {
		this.cmb_tipoCliente.removeAllItems();
		this.cmb_tipoCliente.updateUI();
		AppContext.tipoClienteController.cmbTipoCliente().forEach(Tc -> {
			this.cmb_tipoCliente.addItem(Tc);
		});
	}
}
