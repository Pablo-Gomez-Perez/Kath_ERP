package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fr_ListaArticulos extends JFrame {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 */
	private DefaultTableModel articulosTableModel;
	private String nombreArticulo;	
	private int idSurucsal;
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

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_ListaArticulos frame = new Fr_ListaArticulos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	
	/**
	 * Create the frame.
	 */
	public Fr_ListaArticulos(String nombreArticulo, int idSucursal) {
		
		this.nombreArticulo = nombreArticulo;
		this.idSurucsal = idSucursal;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelSuperiorBusqueda = new JPanel();
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
		btnBusquedaArticulo.setIcon(new ImageIcon(Fr_ListaArticulos.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		panelSuperiorBusqueda.add(btnBusquedaArticulo);
		
		panelCentralTabla = new JPanel();
		contentPane.add(panelCentralTabla, BorderLayout.CENTER);
		panelCentralTabla.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTablaArticulo = new JScrollPane();
		panelCentralTabla.add(scrollPaneTablaArticulo);
		
		this.articulosTableModel = new DefaultTableModel();
		
		this.articulosTableModel.addColumn("Id");
		
		tablaArticulos = new JTable();
		scrollPaneTablaArticulo.setViewportView(tablaArticulos);
		tablaArticulos.setModel(this.articulosTableModel);
		
	}

}
