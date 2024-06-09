package com.kathsoft.kathpos.app.view.articulo;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import com.kathsoft.kathpos.app.model.Articulo;
import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.view.ventas.Fr_PuntoDeVentas;

public class Fr_ListaArticulos extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 */
	private DefaultTableModel modelTablaArticulos;
	private ArticuloController articuloController = new ArticuloController();
	private String nombreArticulo;
	private int idSucursal;
	private Fr_PuntoDeVentas puntoVenta;
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
	private int[] tablaArticulosColumnsWidth = { 40, /* id */
			150, /* codigo */
			200, /* proveedor */
			180, /* categoría */
			100, /* codigo sat */
			300, /* Nombre */
			450, /* descripcion */
			100, /* Existencia */
			100, /* Precio g */
			100 /* Precio m */
	};
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnSeleccionarArticulo;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_ListaArticulos frame = new
	 * Fr_ListaArticulos(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_ListaArticulos(String nombreArticulo, int idSucursal, Fr_PuntoDeVentas frame) {

		this.nombreArticulo = nombreArticulo;
		this.idSucursal = idSucursal;
		this.puntoVenta = frame;

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

		this.modelTablaArticulos = new DefaultTableModel();

		this.modelTablaArticulos.addColumn("Id");
		this.modelTablaArticulos.addColumn("Codigo");
		this.modelTablaArticulos.addColumn("Proveedor");
		this.modelTablaArticulos.addColumn("Categoría");
		this.modelTablaArticulos.addColumn("Codigo Sat");
		this.modelTablaArticulos.addColumn("Nombre");
		this.modelTablaArticulos.addColumn("Descripción");
		this.modelTablaArticulos.addColumn("Existencia");
		this.modelTablaArticulos.addColumn("Precio G.");
		this.modelTablaArticulos.addColumn("Precio M");

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

		/**
		 * se establecen los tamaños preestablecidos para cada columna de la tabla de
		 * los articulos
		 */
		TableColumnModel articulosColumnModel = tablaArticulos.getColumnModel();

		for (int i = 0; i < tablaArticulosColumnsWidth.length; i++) {
			articulosColumnModel.getColumn(i).setPreferredWidth(tablaArticulosColumnsWidth[i]);
			articulosColumnModel.getColumn(i).setMinWidth(tablaArticulosColumnsWidth[i]);
		}

		this.llenarTablaArticulos(this.nombreArticulo);

	}

	private void llenarTablaArticulos(String nombreArticulo) {
		System.out.println(this.nombreArticulo);
		this.modelTablaArticulos.getDataVector().removeAllElements();
		this.tablaArticulos.updateUI();
		/*this.articuloController.consultarArticulosPorNombre(nombreArticulo, this.modelTablaArticulos, 1,
				this.idSucursal);*/
	}
	
	/**
	 * inserta los articulos seleccionados en la tabla principal del formulario y calcula los totales de compra
	 */
	private void listarArticulo() {

		int articuloSeleccionado = this.tablaArticulos.getSelectedRow();
		Articulo articulo = new Articulo();
		int cantidad = 0;
		double subtotal = 0;
		
		try {

			if (articuloSeleccionado == -1) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar un articulo", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// var m = (DefaultTableModel) this.tablaArticulos.getModel();
			articulo = this.articuloController.consultarArticuloPorCodigo(
					(String) this.tablaArticulos.getValueAt(articuloSeleccionado, 1), this.idSucursal);
			System.out.println(articulo.toString());

			cantidad = Integer.parseInt(JOptionPane.showInputDialog(this, "Ingrese la cantidad de articulos"));
			//subtotal = articulo.getPrecioGeneral() * cantidad;
						
			Object[] fila = {
				articulo.getCodigoArticulo(),
				articulo.getDescripcion(),
				//articulo.getPrecioGeneral(),
				cantidad,
				0,
				subtotal
			};
			
			var vendidos = new ArticulosPorVentas();
			vendidos.setId_articulo(articulo.getIdArticulo());
			vendidos.setCantidad(cantidad);
			vendidos.setSubtotal(subtotal);
			
			this.puntoVenta.listarArticuloDesdeConsulta(fila, vendidos);

		} catch (Exception er) {
			er.printStackTrace();
		}

	}

}
