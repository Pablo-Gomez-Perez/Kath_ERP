package com.kathsoft.kathpos.app.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.model.Empleado;

import javax.swing.border.LineBorder;
import javax.swing.Box;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Component;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.SoftBevelBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Fr_PuntoDeVentas extends JFrame {

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
	private EmpleadoController empleadoController = new EmpleadoController();
	private DefaultTableModel modelTablaArticulo;
	private JPanel contentPane;
	private JPanel panelSuperiorDatos;
	private JTextField txfFolioVenta;
	private JButton btnBuscarVenta;
	private Component horizontalStrut_1;
	private Component horizontalStrut_2;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut_3;
	private JTextField txfFechaVenta;
	private Component horizontalStrut_4;
	private JSeparator separator;
	private Box verticalBox;
	private Box horizontalBox_1;
	private Box horizontalBox_2;
	private JLabel lblNewLabel_2;
	private Component horizontalStrut_5;
	private Component horizontalStrut_6;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut_7;
	private JTextField txfNombreEmpleado;
	private Component verticalStrut;
	private Component horizontalStrut_8;
	private Box verticalBox_1;
	private Box horizontalBox_3;
	private Component verticalStrut_1;
	private Box horizontalBox_4;
	private JLabel lblNewLabel_4;
	private JComboBox<String> cmbAliasEmpleado;
	private Component horizontalStrut_9;
	private JComboBox<String> cmbRfcCliente;
	private Component horizontalStrut_10;
	private JLabel lblNewLabel_5;
	private JTextField txfAliasCliente;
	private JLabel lblNewLabel_6;
	private Component horizontalStrut_11;
	private JTextField txfNombreCliente;
	private Component horizontalStrut_12;
	private JLabel lblNewLabel_7;
	private Component horizontalStrut_13;
	private JTextField txfClaveContableCliente;
	private JPanel panelCentralContenedor;
	private JScrollPane scrollPaneTablaArticulos;
	private JTable tablaArticulos;
	private JPanel panelTotales;
	private Box verticalBox_2;
	private Component verticalStrut_2;
	private Box horizontalBox_5;
	private JLabel lblNewLabel_8;
	private Component horizontalStrut_14;
	private JTextField txfSubtotal;
	private Box horizontalBox_6;
	private JLabel lblNewLabel_9;
	private Component horizontalStrut_15;
	private JTextField txfDescuentos;
	private Box horizontalBox_7;
	private JLabel lblNewLabel_10;
	private Component horizontalStrut_16;
	private JTextField txfImpuestoIva;
	private Box horizontalBox_8;
	private JLabel lblNewLabel_11;
	private Component horizontalStrut_17;
	private JTextField txfGranTotalVenta;
	private Box verticalBox_3;
	private Box horizontalBox_9;
	private Component verticalStrut_6;
	private JLabel lblNewLabel_12;
	private Component horizontalStrut_18;
	private JTextField txfArticulosTotales;
	private Component verticalStrut_7;
	private Box horizontalBox_10;
	private JLabel lblNewLabel_13;
	private Component horizontalStrut_19;
	private JTextField txfCantidadDePartidas;
	private JPanel panelDatosArticulo;
	private Box horizontalBox_11;
	private JButton btnCobrar;
	private Component horizontalStrut_20;
	private JButton btnSalir;

	/**
	 * Create the frame.
	 */
	public Fr_PuntoDeVentas(int idSucursal) {
		
		this.idSucursal = idSucursal;
		
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/resources/ventagr.png")));
		setTitle("Punto de venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorDatos = new JPanel();
		panelSuperiorDatos
				.setBorder(new CompoundBorder(new LineBorder(new Color(0, 0, 0)), new EmptyBorder(5, 5, 5, 5)));
		panelSuperiorDatos.setBackground(new Color(204, 255, 255));
		contentPane.add(panelSuperiorDatos, BorderLayout.NORTH);
		panelSuperiorDatos.setLayout(new BoxLayout(panelSuperiorDatos, BoxLayout.Y_AXIS));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Datos de venta",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSuperiorDatos.add(horizontalBox);

		JLabel lblNewLabel = new JLabel("Folio");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		horizontalBox.add(lblNewLabel);

		Component horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);

		txfFolioVenta = new JTextField();
		horizontalBox.add(txfFolioVenta);
		txfFolioVenta.setColumns(10);
		this.txfFolioVenta.setMaximumSize(this.txfFolioVenta.getPreferredSize());

		horizontalStrut_1 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_1);

		btnBuscarVenta = new JButton("");
		btnBuscarVenta.setBackground(new Color(184, 134, 11));
		btnBuscarVenta.setIcon(new ImageIcon(
				Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/resources/buscar_ico.png")));
		horizontalBox.add(btnBuscarVenta);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_2);

		lblNewLabel_1 = new JLabel("Fecha");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_3);

		txfFechaVenta = new JTextField();
		horizontalBox.add(txfFechaVenta);
		txfFechaVenta.setColumns(15);
		this.txfFechaVenta.setMaximumSize(this.txfFechaVenta.getPreferredSize());

		horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_4);

		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		horizontalBox.add(separator);

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_6);

		verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new TitledBorder(
				new TitledBorder(new LineBorder(new Color(0, 0, 0)), "", TitledBorder.LEADING, TitledBorder.TOP, null,
						new Color(0, 0, 0)),
				"Atiende", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		horizontalBox.add(verticalBox);

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		lblNewLabel_2 = new JLabel("Alias");
		horizontalBox_1.add(lblNewLabel_2);

		horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_5);

		cmbAliasEmpleado = new JComboBox<String>();		
		cmbAliasEmpleado.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				consultarEmpleado();
			}
		});
		horizontalBox_1.add(cmbAliasEmpleado);

		verticalStrut = Box.createVerticalStrut(5);
		verticalBox.add(verticalStrut);

		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		lblNewLabel_3 = new JLabel("Nombre");
		horizontalBox_2.add(lblNewLabel_3);

		horizontalStrut_7 = Box.createHorizontalStrut(5);
		horizontalBox_2.add(horizontalStrut_7);

		txfNombreEmpleado = new JTextField();
		horizontalBox_2.add(txfNombreEmpleado);
		txfNombreEmpleado.setColumns(30);
		this.txfNombreEmpleado.setMaximumSize(this.txfNombreEmpleado.getPreferredSize());

		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_8);

		verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cliente", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		horizontalBox.add(verticalBox_1);

		horizontalBox_3 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_3);

		lblNewLabel_4 = new JLabel("RFC");
		horizontalBox_3.add(lblNewLabel_4);

		horizontalStrut_9 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_9);

		cmbRfcCliente = new JComboBox<String>();
		horizontalBox_3.add(cmbRfcCliente);

		horizontalStrut_10 = Box.createHorizontalStrut(5);
		horizontalBox_3.add(horizontalStrut_10);

		lblNewLabel_5 = new JLabel("Alias");
		horizontalBox_3.add(lblNewLabel_5);

		txfAliasCliente = new JTextField();
		horizontalBox_3.add(txfAliasCliente);
		txfAliasCliente.setColumns(20);
		this.txfAliasCliente.setMaximumSize(this.txfAliasCliente.getPreferredSize());

		verticalStrut_1 = Box.createVerticalStrut(5);
		verticalBox_1.add(verticalStrut_1);

		horizontalBox_4 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_4);

		lblNewLabel_6 = new JLabel("Nombre");
		horizontalBox_4.add(lblNewLabel_6);

		horizontalStrut_11 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_11);

		txfNombreCliente = new JTextField();
		horizontalBox_4.add(txfNombreCliente);
		txfNombreCliente.setColumns(30);
		this.txfNombreCliente.setMaximumSize(this.txfNombreCliente.getPreferredSize());

		horizontalStrut_12 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_12);

		lblNewLabel_7 = new JLabel("Cta. Contable");
		horizontalBox_4.add(lblNewLabel_7);

		horizontalStrut_13 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_13);

		txfClaveContableCliente = new JTextField();
		horizontalBox_4.add(txfClaveContableCliente);
		txfClaveContableCliente.setColumns(20);
		this.txfClaveContableCliente.setMaximumSize(this.txfClaveContableCliente.getPreferredSize());

		panelCentralContenedor = new JPanel();
		panelCentralContenedor
				.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(0, 0, 0))));
		contentPane.add(panelCentralContenedor, BorderLayout.CENTER);
		panelCentralContenedor.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaArticulos = new JScrollPane();
		scrollPaneTablaArticulos.setViewportBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelCentralContenedor.add(scrollPaneTablaArticulos, BorderLayout.CENTER);

		modelTablaArticulo = new DefaultTableModel();
		tablaArticulos = new JTable();
		tablaArticulos.setModel(modelTablaArticulo);
		scrollPaneTablaArticulos.setViewportView(tablaArticulos);
		
		modelTablaArticulo.addColumn("Codigo");
		modelTablaArticulo.addColumn("Descripción");
		modelTablaArticulo.addColumn("Precio");
		modelTablaArticulo.addColumn("Cantidad");
		modelTablaArticulo.addColumn("Descuento");
		modelTablaArticulo.addColumn("Subtotal");
		

		panelTotales = new JPanel();
		panelTotales.setBackground(new Color(204, 255, 204));
		panelTotales.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelCentralContenedor.add(panelTotales, BorderLayout.EAST);
		panelTotales.setLayout(new BoxLayout(panelTotales, BoxLayout.Y_AXIS));

		verticalStrut_6 = Box.createVerticalStrut(250);
		panelTotales.add(verticalStrut_6);

		verticalBox_3 = Box.createVerticalBox();
		verticalBox_3.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelTotales.add(verticalBox_3);

		horizontalBox_9 = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox_9);

		lblNewLabel_12 = new JLabel("Articulos");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 11));
		horizontalBox_9.add(lblNewLabel_12);

		horizontalStrut_18 = Box.createHorizontalStrut(20);
		horizontalBox_9.add(horizontalStrut_18);

		txfArticulosTotales = new JTextField();
		horizontalBox_9.add(txfArticulosTotales);
		txfArticulosTotales.setColumns(20);
		this.txfArticulosTotales.setMaximumSize(this.txfArticulosTotales.getPreferredSize());

		verticalStrut_7 = Box.createVerticalStrut(10);
		verticalBox_3.add(verticalStrut_7);

		horizontalBox_10 = Box.createHorizontalBox();
		verticalBox_3.add(horizontalBox_10);

		lblNewLabel_13 = new JLabel("Partidas");
		lblNewLabel_13.setFont(new Font("Tahoma", Font.BOLD, 11));
		horizontalBox_10.add(lblNewLabel_13);

		horizontalStrut_19 = Box.createHorizontalStrut(20);
		horizontalBox_10.add(horizontalStrut_19);

		txfCantidadDePartidas = new JTextField();
		horizontalBox_10.add(txfCantidadDePartidas);
		txfCantidadDePartidas.setColumns(20);
		this.txfCantidadDePartidas.setMaximumSize(this.txfCantidadDePartidas.getPreferredSize());

		verticalStrut_2 = Box.createVerticalStrut(10);
		panelTotales.add(verticalStrut_2);

		verticalBox_2 = Box.createVerticalBox();
		verticalBox_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelTotales.add(verticalBox_2);

		horizontalBox_5 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_5);

		lblNewLabel_8 = new JLabel("Sub Total");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 11));
		horizontalBox_5.add(lblNewLabel_8);

		horizontalStrut_14 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_14);

		txfSubtotal = new JTextField();
		horizontalBox_5.add(txfSubtotal);
		txfSubtotal.setColumns(20);
		this.txfSubtotal.setMaximumSize(this.txfSubtotal.getPreferredSize());

		horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_6);

		lblNewLabel_9 = new JLabel("Descuentos");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.BOLD, 11));
		horizontalBox_6.add(lblNewLabel_9);

		horizontalStrut_15 = Box.createHorizontalStrut(5);
		horizontalBox_6.add(horizontalStrut_15);

		txfDescuentos = new JTextField();
		horizontalBox_6.add(txfDescuentos);
		txfDescuentos.setColumns(18);
		this.txfDescuentos.setMaximumSize(this.txfDescuentos.getPreferredSize());

		horizontalBox_7 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_7);

		lblNewLabel_10 = new JLabel("I.V.A");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.BOLD, 11));
		horizontalBox_7.add(lblNewLabel_10);

		horizontalStrut_16 = Box.createHorizontalStrut(5);
		horizontalBox_7.add(horizontalStrut_16);

		txfImpuestoIva = new JTextField();
		horizontalBox_7.add(txfImpuestoIva);
		txfImpuestoIva.setColumns(23);
		this.txfImpuestoIva.setMaximumSize(this.txfImpuestoIva.getPreferredSize());

		horizontalBox_8 = Box.createHorizontalBox();
		verticalBox_2.add(horizontalBox_8);

		lblNewLabel_11 = new JLabel("Total");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 11));
		horizontalBox_8.add(lblNewLabel_11);

		horizontalStrut_17 = Box.createHorizontalStrut(5);
		horizontalBox_8.add(horizontalStrut_17);

		txfGranTotalVenta = new JTextField();
		horizontalBox_8.add(txfGranTotalVenta);
		txfGranTotalVenta.setColumns(23);
		this.txfGranTotalVenta.setMaximumSize(this.txfGranTotalVenta.getPreferredSize());

		horizontalBox_11 = Box.createHorizontalBox();
		panelTotales.add(horizontalBox_11);

		btnCobrar = new JButton("Cobrar");
		btnCobrar.setBackground(new Color(204, 255, 51));
		btnCobrar.setFont(new Font("Tahoma", Font.BOLD, 20));
		horizontalBox_11.add(btnCobrar);

		horizontalStrut_20 = Box.createHorizontalStrut(20);
		horizontalBox_11.add(horizontalStrut_20);

		btnSalir = new JButton("Cancelar");
		btnSalir.setBackground(new Color(255, 51, 51));
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 20));
		horizontalBox_11.add(btnSalir);

		panelDatosArticulo = new JPanel();
		panelCentralContenedor.add(panelDatosArticulo, BorderLayout.SOUTH);
		
		this.asignarFecha();
		this.llenarCmbEmpleados();

	}
	
	private void asignarFecha() {
		String fecha = LocalDate.now().toString();
		this.txfFechaVenta.setText(fecha);
	}
	
	private void llenarCmbEmpleados() {
		this.cmbAliasEmpleado.removeAllItems();
		this.cmbAliasEmpleado.updateUI();
		
		this.empleadoController.consultaNombresCortosEmpleados(cmbAliasEmpleado, this.idSucursal);
	}
	
	private void consultarEmpleado() {		
		Empleado empleado = this.empleadoController.consultarEmpleadoPorNombre((String)this.cmbAliasEmpleado.getSelectedItem());		
		this.txfNombreEmpleado.setText(empleado.getNombre());		
	}

}
