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

	/**
	 * Create the frame.
	 */
	public Fr_PuntoDeVentas(int idSucursal) {

		this.idSucursal = idSucursal;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_PuntoDeVentas.class.getResource("/com/kathsoft/kathpos/app/assets/ventagr.png")));
		setTitle("Punto de venta");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1031, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		
		this.panelSuperiorDatosVenta = new JPanel();
		this.panelSuperiorDatosVenta.setBackground(new Color(85, 223, 255));
		GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelSuperiorDatosVenta, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1031, Short.MAX_VALUE)
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(this.panelSuperiorDatosVenta, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(522, Short.MAX_VALUE))
		);
		GroupLayout gl_panelSuperiorDatosVenta = new GroupLayout(this.panelSuperiorDatosVenta);
		gl_panelSuperiorDatosVenta.setHorizontalGroup(
			gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1021, Short.MAX_VALUE)
		);
		gl_panelSuperiorDatosVenta.setVerticalGroup(
			gl_panelSuperiorDatosVenta.createParallelGroup(Alignment.LEADING)
				.addGap(0, 148, Short.MAX_VALUE)
		);
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
