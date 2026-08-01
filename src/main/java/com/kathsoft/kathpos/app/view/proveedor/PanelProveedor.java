package com.kathsoft.kathpos.app.view.proveedor;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelProveedor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelProveedorEtiqueta;
	private JLabel lblNewLabel_2;
	private DefaultTableModel modelTablaProveedores;
	private JPanel panelCentralContenedor;
	private JTextField txfNombreProveedor;
	private JTable tablaProveedor;
	private JPanel panelInferiorBusqueda;
	private JPanel panelSuperiorBotones;
	private FlowLayout flowLayout;
	private JScrollPane scrollPaneTablaProveedor;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnToExcel;
	private JLabel lblNombre;
	private JButton btnBuscar;

	/**
	 * Create the panel.
	 */
	public PanelProveedor() {
		
		
		this.setLayout(new BorderLayout(0, 0));

		panelProveedorEtiqueta = new JPanel();
		panelProveedorEtiqueta.setBackground(new Color(25, 25, 112));
		this.add(panelProveedorEtiqueta, BorderLayout.NORTH);
		
		lblNewLabel_2 = new JLabel("Modulo de Proveedores");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		panelProveedorEtiqueta.add(lblNewLabel_2);
		
		panelCentralContenedor = new JPanel();
		panelCentralContenedor.setBackground(new Color(255, 204, 0));
		add(panelCentralContenedor, BorderLayout.CENTER);
		
		panelInferiorBusqueda = new JPanel();
		panelInferiorBusqueda.setBackground(new Color(0, 153, 255));
		
		panelSuperiorBotones = new JPanel();
		flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		
		scrollPaneTablaProveedor = new JScrollPane();
		GroupLayout gl_panelCentralContenedor = new GroupLayout(panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(panelInferiorBusqueda, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTablaProveedor, GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentralContenedor.createSequentialGroup()
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTablaProveedor, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferiorBusqueda, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
		);
		
		modelTablaProveedores = this.setTableModel();
		tablaProveedor = new JTable();
		this.tablaProveedor.setModel(modelTablaProveedores);
		scrollPaneTablaProveedor.setViewportView(tablaProveedor);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormDatosProveedor(0, 0);
			}
		});
		btnAgregar.setIcon(new ImageIcon(PanelProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelSuperiorBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tablaProveedor.getSelectedRow() < 0) {
					MessageHandler.displayMessage(
							MessageHandler.WARN_MESSAGE,
							PanelProveedor.this,
							"Seleccione un proveedor para modificar"
					);
					return;
				}

				int idProveedor = DataTools.getIndiceElementoSeleccionado(tablaProveedor, modelTablaProveedores, 0);

				if (idProveedor < 0) {
					return;
				}

				abrirFormDatosProveedor(1, idProveedor);
			}
		});
		btnModificar.setIcon(new ImageIcon(PanelProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		panelSuperiorBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProveedor();
			}
		});
		btnEliminar.setIcon(new ImageIcon(PanelProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		panelSuperiorBotones.add(btnEliminar);
		
		btnToExcel = new JButton("Exportar Excel");
		btnToExcel.setIcon(new ImageIcon(PanelProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		panelSuperiorBotones.add(btnToExcel);
		
		lblNombre = new JLabel("Nombre");
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaProveedor(txfNombreProveedor.getText());
			}
		});
		btnBuscar.setIcon(new ImageIcon(PanelProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		
		txfNombreProveedor = new JTextField();
		txfNombreProveedor.setColumns(10);
		GroupLayout gl_panelInferiorBusqueda = new GroupLayout(panelInferiorBusqueda);
		gl_panelInferiorBusqueda.setHorizontalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNombreProveedor, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		gl_panelInferiorBusqueda.setVerticalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(btnBuscar)
						.addComponent(txfNombreProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panelInferiorBusqueda.setLayout(gl_panelInferiorBusqueda);
		panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		this.llenarTablaProveedor("");

	}
	
	
	
	private void borrarElementosDeLaTablaProveedor() {
		this.modelTablaProveedores.getDataVector().removeAllElements();
		this.tablaProveedor.updateUI();
	}
	
	public void llenarTablaProveedor(String nombre) {
		this.borrarElementosDeLaTablaProveedor();
		var proveedores = AppContext.proveedorController.verProveedoresEnTabla(nombre);
		
		if (proveedores == null || proveedores.isEmpty()) {
			return;
		}
		
		proveedores.forEach(this.modelTablaProveedores::addRow);
	}
	
	private void eliminarProveedor() {

		if (this.tablaProveedor.getSelectedRow() < 0) {
			return;
		}

		int idProveedor = DataTools.getIndiceElementoSeleccionado(this.tablaProveedor, this.modelTablaProveedores, 0);

		if (idProveedor < 0) {
			return;
		}

		int option = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, " seleccionado?");

		if (option != 0) {
			return;
		}

		var response = AppContext.proveedorController.eliminarProveedor(idProveedor);

		if (response.id() == 200) {
			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, response.message());
			this.llenarTablaProveedor(this.txfNombreProveedor.getText());
			return;
		}

		MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, response.message());
	}
	
	private void abrirFormDatosProveedor(int idOperacion, int idProveedor) {

		try {
			Fr_DatosProveedor form = new Fr_DatosProveedor(idOperacion, idProveedor);
			form.setLocationRelativeTo(this);
			form.setVisible(true);
			form.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosed(java.awt.event.WindowEvent e) {
					if (form.isOperacionEjecutada()) {
						llenarTablaProveedor(txfNombreProveedor.getText());
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, e.getMessage());
		}
	}
	
	private DefaultTableModel setTableModel() {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Id");
		model.addColumn("RFC");
		model.addColumn("Cta Contable");
		model.addColumn("Nombre");
		model.addColumn("Descripcion");
		model.addColumn("Email");
		model.addColumn("Estado");
		model.addColumn("Ciudad");
		model.addColumn("Direccion");
		model.addColumn("Codigo P.");
		model.addColumn("Activo");
		
		return model;
		
	}
}