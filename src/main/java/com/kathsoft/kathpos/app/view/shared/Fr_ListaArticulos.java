package com.kathsoft.kathpos.app.view.shared;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.model.articulo.ArticuloByCodigo;
import com.kathsoft.kathpos.app.model.interfaces.IListadoArticulosAcciones;
import com.kathsoft.kathpos.tools.ConstantsConllections;

public class Fr_ListaArticulos extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int ID_TIPO_CLIENTE_GENERAL = 1;

	private DefaultTableModel modelTablaArticulos;
	private ArticuloController articuloController = new ArticuloController();
	private String nombreArticulo;
	private int idSucursal;

	/**
	 * Referencia al formulario invocador que recibirá el artículo seleccionado.
	 */
	private IListadoArticulosAcciones frame;

	private JPanel contentPane;
	private JTextField txfNombreArticulo;
	private JTable tablaArticulos;
	private JPanel panelSuperiorBusqueda;
	private FlowLayout flowLayout;
	private JLabel lblNewLabel;
	private Component horizontalStrut_1;
	private JButton btnBusquedaArticulo;
	private JPanel panelCentralTabla;
	private JScrollPane scrollPaneTablaArticulo;
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnSeleccionarArticulo;

	/**
	 * Crea el formulario auxiliar para consultar y seleccionar artículos.
	 *
	 * @param nombreArticulo texto inicial de búsqueda para filtrar artículos.
	 * @param idSucursal identificador de la sucursal sobre la cual se consulta
	 *                   la disponibilidad o información del artículo.
	 * @param frame formulario invocador que recibirá el artículo seleccionado.
	 */
	public Fr_ListaArticulos(String nombreArticulo, int idSucursal, IListadoArticulosAcciones frame) {

		this.nombreArticulo = nombreArticulo;
		this.idSucursal = idSucursal;
		this.frame = frame;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorBusqueda = new JPanel();
		panelSuperiorBusqueda.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				new EmptyBorder(5, 5, 5, 5)));
		flowLayout = (FlowLayout) panelSuperiorBusqueda.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelSuperiorBusqueda, BorderLayout.NORTH);

		lblNewLabel = new JLabel("Articulo");
		panelSuperiorBusqueda.add(lblNewLabel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelSuperiorBusqueda.add(horizontalStrut);

		txfNombreArticulo = new JTextField();
		panelSuperiorBusqueda.add(txfNombreArticulo);
		txfNombreArticulo.setColumns(40);
		this.txfNombreArticulo.setMaximumSize(this.txfNombreArticulo.getPreferredSize());
		this.txfNombreArticulo.setText(this.nombreArticulo);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelSuperiorBusqueda.add(horizontalStrut_1);

		btnBusquedaArticulo = new JButton("Buscar");
		btnBusquedaArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaArticulos(txfNombreArticulo.getText());
			}
		});
		btnBusquedaArticulo.setIcon(new ImageIcon(
				Fr_ListaArticulos.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		panelSuperiorBusqueda.add(btnBusquedaArticulo);

		panelCentralTabla = new JPanel();
		panelCentralTabla.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				new EmptyBorder(5, 5, 5, 5)));
		contentPane.add(panelCentralTabla, BorderLayout.CENTER);
		panelCentralTabla.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaArticulo = new JScrollPane();
		panelCentralTabla.add(scrollPaneTablaArticulo);

		this.setModelTablaArticulos();

		tablaArticulos = new JTable();
		scrollPaneTablaArticulo.setViewportView(tablaArticulos);
		tablaArticulos.setModel(this.modelTablaArticulos);
		tablaArticulos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelInferiorBotones = new JPanel();
		panelInferiorBotones.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null),
				new EmptyBorder(5, 5, 5, 5)));
		FlowLayout fl_panelInferiorBotones = (FlowLayout) panelInferiorBotones.getLayout();
		fl_panelInferiorBotones.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Fr_ListaArticulos.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnCancelar.setBackground(new Color(255, 51, 51));
		panelInferiorBotones.add(btnCancelar);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_2);

		btnSeleccionarArticulo = new JButton("Seleccionar");
		btnSeleccionarArticulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarArticulo();
			}
		});
		btnSeleccionarArticulo.setIcon(
				new ImageIcon(Fr_ListaArticulos.class.getResource("/com/kathsoft/kathpos/app/assets/palomita.jpg")));
		this.btnSeleccionarArticulo.setBackground(new Color(204, 255, 51));
		panelInferiorBotones.add(btnSeleccionarArticulo);

		for (int i = 0; i < modelTablaArticulos.getColumnCount(); i++) {
			Class<?> colClass = modelTablaArticulos.getColumnClass(i);
			tablaArticulos.setDefaultEditor(colClass, null);
		}

		TableColumnModel articulosColumnModel = tablaArticulos.getColumnModel();
		for (int i = 0; i < ConstantsConllections.tablaArticulosListadoColumnsWidth.length; i++) {
			articulosColumnModel.getColumn(i).setPreferredWidth(ConstantsConllections.tablaArticulosListadoColumnsWidth[i]);
			articulosColumnModel.getColumn(i).setMinWidth(ConstantsConllections.tablaArticulosListadoColumnsWidth[i]);
		}

		this.llenarTablaArticulos(this.nombreArticulo);
	}

	private void llenarTablaArticulos(String nombreArticulo) {
		this.modelTablaArticulos.setRowCount(0);
		this.articuloController.verArticulosEnTabla(this.idSucursal, nombreArticulo, ID_TIPO_CLIENTE_GENERAL)
				.forEach(this.modelTablaArticulos::addRow);
	}

	public void setModelTablaArticulos() {
		this.modelTablaArticulos = new DefaultTableModel();
		this.modelTablaArticulos.addColumn("Id");
		this.modelTablaArticulos.addColumn("Codigo");
		this.modelTablaArticulos.addColumn("Nombre");
		this.modelTablaArticulos.addColumn("Costo");
		this.modelTablaArticulos.addColumn("Precio");
		this.modelTablaArticulos.addColumn("Existencia");
	}

	private void listarArticulo() {
		int articuloSeleccionado = this.tablaArticulos.getSelectedRow();

		if (articuloSeleccionado == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un articulo", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			String codigoArticulo = String.valueOf(this.tablaArticulos.getValueAt(articuloSeleccionado, 1));
			ArticuloByCodigo articulo = this.articuloController.consultarArticuloPorCodigo(codigoArticulo,
					this.idSucursal, ID_TIPO_CLIENTE_GENERAL);

			if (articulo == null || articulo.getIdArticulo() <= 0) {
				return;
			}

			String cantidadIngresada = JOptionPane.showInputDialog(this, "Ingrese la cantidad de articulos");
			if (cantidadIngresada == null) {
				return;
			}

			int cantidad = Integer.parseInt(cantidadIngresada.trim());
			if (cantidad <= 0) {
				throw new NumberFormatException("La cantidad debe ser mayor a cero");
			}

			BigDecimal precio = this.obtenerPrecioSeleccionado(articuloSeleccionado);
			this.frame.listarArticuloDesdeConsulta(articulo, cantidad, precio);
			this.dispose();
		} catch (NumberFormatException er) {
			JOptionPane.showMessageDialog(this, "Ingrese una cantidad entera mayor a cero", "Cantidad inválida",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, "No se pudo seleccionar el artículo: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private BigDecimal obtenerPrecioSeleccionado(int fila) {
		Object valor = this.tablaArticulos.getValueAt(fila, 4);
		if (valor == null) {
			return BigDecimal.ZERO;
		}
		if (valor instanceof BigDecimal precio) {
			return precio;
		}
		if (valor instanceof Number numero) {
			return BigDecimal.valueOf(numero.doubleValue());
		}
		return new BigDecimal(valor.toString());
	}
}
