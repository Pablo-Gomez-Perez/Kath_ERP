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

public class PanelClientes extends JPanel {

	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 * 
	 */	
	private JPanel panelEtiquetaClientes;
	private JLabel lblNewLabel_7;
	private JPanel panelClientesCentral;
	private JPanel panelClientesCentralBotones;
	private JButton btnAgregarCliente;
	private JButton btnActualizarCliente;
	private JButton btnEliminarCliente;
	private JButton btnExportarClientesExcel;
	private JScrollPane scrollPaneTablaClientes;
	private DefaultTableModel modelTablaClientes;
	private JTable tablaClientes;
	private JPanel panelClientesCentralBuscar;
	private JLabel lblNewLabel_21;
	private Component horizontalStrut_22;
	private JButton btnBuscarCliente;
	private JTextField txfBuscarCliente;	
	private JLabel lblNewLabel;
	

	/**
	 * Create the panel.
	 */
	public PanelClientes() {
		
		this.setBackground(new Color(255, 215, 0));
		this.setLayout(new BorderLayout(0, 0));
		
		this.panelEtiquetaClientes = new JPanel();
		this.panelEtiquetaClientes.setBackground(new Color(25, 25, 112));
		
		this.add(panelEtiquetaClientes, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Catálogo de clientes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panelEtiquetaClientes.add(lblNewLabel);
		
		this.lblNewLabel_7 = new JLabel("Modulo de Clientes");
		this.lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblNewLabel_7.setForeground(new Color(255, 255, 255));
		
		this.add(lblNewLabel_7);
		
		this.panelClientesCentral = new JPanel();
		this.panelClientesCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.panelClientesCentral.setBackground(new Color(255, 215, 0));
		this.panelClientesCentral.setLayout(new BorderLayout(0, 0));
		this.add(panelClientesCentral, BorderLayout.CENTER);
		
		this.panelClientesCentralBotones = new JPanel();
		FlowLayout fl_panelClientesCentralBotones = (FlowLayout) panelClientesCentralBotones.getLayout();
		fl_panelClientesCentralBotones.setAlignment(FlowLayout.RIGHT);
		panelClientesCentralBotones.setBackground(new Color(255, 215, 0));
		panelClientesCentral.add(panelClientesCentralBotones, BorderLayout.NORTH);
		
		this.btnAgregarCliente = new JButton("Agregar");
		this.btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormClientes(0, 0);
			}
		});
		
		this.btnAgregarCliente.setBackground(new Color(144, 238, 144));
		this.btnAgregarCliente.setIcon(
				new ImageIcon(PanelClientes.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.panelClientesCentralBotones.add(btnAgregarCliente);

		this.btnActualizarCliente = new JButton("Actualizar");
		this.btnActualizarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormClientes(1, DataTools.getIndiceElementoSeleccionado(tablaClientes, modelTablaClientes, 0));
			}
		});
		
		this.btnActualizarCliente.setBackground(new Color(144, 238, 144));
		this.btnActualizarCliente.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		this.panelClientesCentralBotones.add(btnActualizarCliente);
		
		this.btnEliminarCliente = new JButton("Eliminar");
		this.btnEliminarCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnEliminarCliente.setBackground(new Color(255, 51, 0));
		this.btnEliminarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCliente();
			}
		});
		this.panelClientesCentralBotones.add(btnEliminarCliente);
		
		this.btnExportarClientesExcel = new JButton("Exportar a Excel");
		this.btnExportarClientesExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarClientesExcel();
			}
		});
		this.btnExportarClientesExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.btnExportarClientesExcel.setBackground(new Color(102, 205, 170));
		this.panelClientesCentralBotones.add(btnExportarClientesExcel);
		
		this.scrollPaneTablaClientes = new JScrollPane();
		this.panelClientesCentral.add(scrollPaneTablaClientes, BorderLayout.CENTER);

		this.modelTablaClientes = new DefaultTableModel();
		this.tablaClientes = new JTable();
		this.tablaClientes.setModel(modelTablaClientes);
		this.tablaClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPaneTablaClientes.setViewportView(tablaClientes);
		
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
		
		// se remueve el editor de la tabla provedoores
		DataTools.removerEditorDeTabla(tablaClientes, modelTablaClientes);
		
		this.panelClientesCentralBuscar = new JPanel();
		this.panelClientesCentralBuscar.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_4 = (FlowLayout) panelClientesCentralBuscar.getLayout();
		flowLayout_4.setAlignment(FlowLayout.RIGHT);
		this.panelClientesCentral.add(panelClientesCentralBuscar, BorderLayout.SOUTH);

		this.lblNewLabel_21 = new JLabel("Buscar cliente");
		this.lblNewLabel_21.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.panelClientesCentralBuscar.add(lblNewLabel_21);
		
		horizontalStrut_22 = Box.createHorizontalStrut(20);
		panelClientesCentralBuscar.add(horizontalStrut_22);

		btnBuscarCliente = new JButton("Buscar");
		btnBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarClientePorNombre();
			}
		});
		
		this.txfBuscarCliente = new JTextField();
		this.txfBuscarCliente.setMaximumSize(this.txfBuscarCliente.getPreferredSize());
		
		panelClientesCentralBuscar.add(txfBuscarCliente);
		txfBuscarCliente.setColumns(70);
		btnBuscarCliente.setBackground(new Color(184, 134, 11));
		btnBuscarCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		panelClientesCentralBuscar.add(btnBuscarCliente);
		
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaClientesColumnsWidth, tablaClientes);
		
		
	}
	
	
	private void abrirFormClientes(int tipoOperacion, int indiceCliente) {
		Component cm = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Fr_DatosCliente frame = new Fr_DatosCliente(tipoOperacion, indiceCliente);
				frame.setLocationRelativeTo(cm);
				frame.setVisible(true);
			}
		});
	}
	
	private void eliminarCliente() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this);

		if (input > 0) {
			return;
		}

		try {

			AppContext.clientesController.eliminarCliente(
					DataTools.getIndiceElementoSeleccionado(this.tablaClientes, this.modelTablaClientes, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this);

		} catch (SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}

	}
	
	public void exportarClientesExcel() {
		try {
			DataTools.exportarTablaExcel(modelTablaClientes, this);
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
			er.printStackTrace();
		}
	}
	
	private void buscarClientePorNombre() {
		this.modelTablaClientes.getDataVector().removeAllElements();
		this.tablaClientes.updateUI();
		AppContext.clientesController.verClientesEnTabla(this.txfBuscarCliente.getText(), this.modelTablaClientes);
	}
	
	public void llenarTablaClientes() {
		this.borrarElementosDeLaTablaClientes();
		AppContext.clientesController.verClientesEnTabla(this.txfBuscarCliente.getText(), modelTablaClientes);
	}
	
	private void borrarElementosDeLaTablaClientes() {
		this.modelTablaClientes.getDataVector().removeAllElements();
		this.tablaClientes.updateUI();
	}
}
