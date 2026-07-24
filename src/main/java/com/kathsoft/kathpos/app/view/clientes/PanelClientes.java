package com.kathsoft.kathpos.app.view.clientes;

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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.ClientesController;
import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelClientes extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 * 
	 */	
	private JPanel panelEtiquetaClientes;
	private DefaultTableModel modelTablaClientes;
	private JLabel lblNewLabel;
	private JPanel panelCentralContenedor;
	private JPanel panelSuperiorBotones;
	private JPanel panelInferiorBusqueda;
	private JTextField txfNombreCliente;
	private JButton btnExcel;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAgregar;
	private JTable tablaClientes;
	

	/**
	 * Create the panel.
	 */
	public PanelClientes() {
		
		this.setBackground(new Color(255, 215, 0));
		
		this.panelEtiquetaClientes = new JPanel();
		this.panelEtiquetaClientes.setBackground(new Color(25, 25, 112));
		
		lblNewLabel = new JLabel("Catálogo de clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panelEtiquetaClientes.add(lblNewLabel);

		this.modelTablaClientes = new DefaultTableModel();
		
		panelCentralContenedor = new JPanel();
		panelCentralContenedor.setBackground(new Color(255, 204, 0));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelEtiquetaClientes, GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
				.addComponent(panelCentralContenedor, GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelEtiquetaClientes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelCentralContenedor, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE))
		);
		
		panelSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		
		panelInferiorBusqueda = new JPanel();
		panelInferiorBusqueda.setBackground(new Color(0, 153, 255));
		
		JScrollPane scrollPaneClientes = new JScrollPane();
		GroupLayout gl_panelCentralContenedor = new GroupLayout(panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
				.addComponent(panelInferiorBusqueda, GroupLayout.DEFAULT_SIZE, 881, Short.MAX_VALUE)
				.addGroup(Alignment.TRAILING, gl_panelCentralContenedor.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneClientes, GroupLayout.DEFAULT_SIZE, 857, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneClientes, GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferiorBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		tablaClientes = new JTable();
		scrollPaneClientes.setViewportView(tablaClientes);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(PanelClientes.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnAgregar.setBackground(new Color(144,238,144));
		panelSuperiorBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelClientes.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		this.btnModificar.setBackground(new Color(144,238,144));
		panelSuperiorBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelClientes.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnEliminar.setBackground(new Color(255,51,0));
		panelSuperiorBotones.add(btnEliminar);
		
		btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelClientes.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.btnExcel.setBackground(new Color(102,205,170));
		panelSuperiorBotones.add(btnExcel);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar");
		
		txfNombreCliente = new JTextField();
		txfNombreCliente.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.setBackground(new Color(153, 102, 51));
		btnNewButton.setIcon(new ImageIcon(PanelClientes.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		GroupLayout gl_panelInferiorBusqueda = new GroupLayout(panelInferiorBusqueda);
		gl_panelInferiorBusqueda.setHorizontalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNombreCliente, GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewButton)
					.addContainerGap())
		);
		gl_panelInferiorBusqueda.setVerticalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelInferiorBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(txfNombreCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addContainerGap())
		);
		panelInferiorBusqueda.setLayout(gl_panelInferiorBusqueda);
		panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		setLayout(groupLayout);
		
		this.modelTablaClientes.addColumn("Id");
		this.modelTablaClientes.addColumn("RFC");
		this.modelTablaClientes.addColumn("Tipo");
		this.modelTablaClientes.addColumn("Cta Contable");
		this.modelTablaClientes.addColumn("Nombre completo");
		this.modelTablaClientes.addColumn("Alias");
		this.modelTablaClientes.addColumn("Email");
		this.modelTablaClientes.addColumn("Estado");
		this.modelTablaClientes.addColumn("Ciudad");
		this.modelTablaClientes.addColumn("Direccion");
		this.modelTablaClientes.addColumn("Codigo P.");
		this.modelTablaClientes.addColumn("Activo");
		
		
	}
	
	public void llenarTablaClientes() {
		this.borrarElementosDeLaTablaClientes();
		AppContext.clientesController.verClientesEnTabla(this.txfNombreCliente.getText(), modelTablaClientes);
	}
	
	private void borrarElementosDeLaTablaClientes() {
		this.modelTablaClientes.getDataVector().removeAllElements();
		this.tablaClientes.updateUI();
	}
}
