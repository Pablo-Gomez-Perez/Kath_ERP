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
import com.kathsoft.kathpos.app.model.cliente.TipoCliente;
import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Dimension;

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
	
	/**Datos de la sucursal desde la que se inició sesión.*/
	private Sucursal sucursal;
	
	private JLabel lblBuscar;
	
	/** Texto que será buscado dependiendo el criterio de busqueda */
	private JTextField txfBuscarArticulo;
	private JButton btnBuscar;
	private JLabel lblBuscarPor;
	private JLabel lblCliente;
	
	/** Listado de los tipos de cliente en base a los cuales se refleja el precio en la lista */
	private JComboBox cmbTipoCliente;
	
	/** Representa la columna por la cual se filtrará la busqueda */
	private JComboBox cmbTipoBusqueda;
	
	private JLabel lblOrdenarPor;
	
	/** Representa la forma en la que la busqueda ordenará el resultado listado */
	private JComboBox cmbCriterioDeOrdenacion;

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
		panelArticulosCentralBuscar.setBackground(new Color(51, 153, 255));
		
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
		
		this.lblBuscarPor = new JLabel("Buscar Por:");
		
		this.lblCliente = new JLabel("Tipo Cliente");
		
		this.cmbTipoCliente = new JComboBox();
		
		this.cmbTipoBusqueda = new JComboBox();
		
		this.lblOrdenarPor = new JLabel("Ordenar Por");
		
		this.cmbCriterioDeOrdenacion = new JComboBox();
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

	}

	private void abrirVentanaFormularioArticulo(int opcion, int idArticulo, int sucursal) {

	}

	private void eliminarArticulo() {

	}

	public void exportarArticuloExcel() {

	}

	private void borrarElementosDeLaTablaArticulos() {

	}

	public void llenarTablaArticulos() {

	}

	public void llenarCmbTipoCliente() {

	}
}
