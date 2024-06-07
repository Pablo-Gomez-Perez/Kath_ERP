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

public class PanelProveedor extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelProveedorEtiqueta;
	private JLabel lblNewLabel_2;
	private JPanel panelProovedorCentral;
	private JScrollPane scrollPaneTablaProveedores;
	private DefaultTableModel modelTablaProveedores;
	private JTable tablaProveedores;
	private JPanel panelProveedorCentralBotones;
	private JButton btnAgregarProveedor;
	private JButton btnActualizarProveedor;
	private JButton btnEliminarProveedor;
	private JButton btnExportarProveedoresExcel;
	private JPanel panelProveedorCentralBuscar;
	private JLabel lblNewLabel_5;
	private Component horizontalStrut_2;
	private JTextField txfBuscarProveedor;
	private Component horizontalStrut_3;
	private JButton btnBuscarProveedor;

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

		panelProovedorCentral = new JPanel();
		panelProovedorCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelProovedorCentral.setBackground(new Color(255, 215, 0));
		this.add(panelProovedorCentral, BorderLayout.CENTER);
		panelProovedorCentral.setLayout(new BorderLayout(0, 0));
		
		scrollPaneTablaProveedores = new JScrollPane();
		panelProovedorCentral.add(scrollPaneTablaProveedores, BorderLayout.CENTER);

		modelTablaProveedores = new DefaultTableModel();

		modelTablaProveedores.addColumn("Id");
		modelTablaProveedores.addColumn("RFC");
		modelTablaProveedores.addColumn("Cta Contable");
		modelTablaProveedores.addColumn("Nombre");
		modelTablaProveedores.addColumn("Descripcion");
		modelTablaProveedores.addColumn("Email");
		modelTablaProveedores.addColumn("Estado");
		modelTablaProveedores.addColumn("Ciudad");
		modelTablaProveedores.addColumn("Direccion");
		modelTablaProveedores.addColumn("Codigo P.");
		modelTablaProveedores.addColumn("Activo");

		tablaProveedores = new JTable();
		tablaProveedores.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaProveedores.setViewportView(tablaProveedores);
		tablaProveedores.setModel(modelTablaProveedores);
		
		panelProveedorCentralBotones = new JPanel();
		panelProveedorCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panelProveedorCentralBotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panelProovedorCentral.add(panelProveedorCentralBotones, BorderLayout.NORTH);

		btnAgregarProveedor = new JButton("Agregar");
		btnAgregarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioProveedor(0, 0);
			}
		});
		btnAgregarProveedor.setBackground(new Color(144, 238, 144));
		btnAgregarProveedor.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelProveedorCentralBotones.add(btnAgregarProveedor);

		btnActualizarProveedor = new JButton("Actualizar");
		btnActualizarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioProveedor(1,
						DataTools.getIndiceElementoSeleccionado(tablaProveedores, modelTablaProveedores, 0));
			}
		});
		btnActualizarProveedor.setBackground(new Color(144, 238, 144));
		btnActualizarProveedor.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		panelProveedorCentralBotones.add(btnActualizarProveedor);
		
		btnEliminarProveedor = new JButton("Eliminar");
		btnEliminarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarProveedor();
			}
		});
		btnEliminarProveedor.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnEliminarProveedor.setBackground(new Color(255, 51, 0));
		panelProveedorCentralBotones.add(btnEliminarProveedor);

		btnExportarProveedoresExcel = new JButton("Exportar a Excel");
		btnExportarProveedoresExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarProveedoresExcel();
			}
		});
		btnExportarProveedoresExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.btnExportarProveedoresExcel.setBackground(new Color(102, 205, 170));
		panelProveedorCentralBotones.add(btnExportarProveedoresExcel);
		
		DataTools.removerEditorDeTabla(this.tablaProveedores, this.modelTablaProveedores);

		panelProveedorCentralBuscar = new JPanel();
		FlowLayout flowLayout_13 = (FlowLayout) panelProveedorCentralBuscar.getLayout();
		flowLayout_13.setAlignment(FlowLayout.RIGHT);
		panelProveedorCentralBuscar.setBackground(new Color(255, 215, 0));
		panelProovedorCentral.add(panelProveedorCentralBuscar, BorderLayout.SOUTH);

		lblNewLabel_5 = new JLabel("Buscar Proveedor");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelProveedorCentralBuscar.add(lblNewLabel_5);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelProveedorCentralBuscar.add(horizontalStrut_2);

		txfBuscarProveedor = new JTextField();
		panelProveedorCentralBuscar.add(txfBuscarProveedor);
		txfBuscarProveedor.setColumns(70);

		horizontalStrut_3 = Box.createHorizontalStrut(20);
		panelProveedorCentralBuscar.add(horizontalStrut_3);
		
		btnBuscarProveedor = new JButton("Buscar");
		btnBuscarProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaProveedor(txfBuscarProveedor.getText());
			}
		});
		btnBuscarProveedor.setBackground(new Color(184, 134, 11));
		btnBuscarProveedor.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		panelProveedorCentralBuscar.add(btnBuscarProveedor);
		
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaProveedoresColumnsWidth, tablaProveedores);

	}
	
	private void abrirVentanaFormularioProveedor(int opcion, int indiceProveedor) {

		Component cm = this;

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Fr_DatosProveedor fr = new Fr_DatosProveedor(opcion, indiceProveedor);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);

				} catch (Exception er) {
					er.printStackTrace();
				}

			}

		});
	}
	
	/**
	 * modifica el estatus de activo a inactivo de un proveedor registrado en la bd
	 */
	private void eliminarProveedor() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this);

		if (input > 0) {
			return;
		}

		try {

			AppContext.proveedorController.eliminarProveedor(
					DataTools.getIndiceElementoSeleccionado(tablaProveedores, modelTablaProveedores, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this);

		} catch (SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}
	
	public void exportarProveedoresExcel() {
		try {
			DataTools.exportarTablaExcel(modelTablaProveedores, this);
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
			er.printStackTrace();
		}
	}
	
	
	
	private void borrarElementosDeLaTablaProveedor() {
		this.modelTablaProveedores.getDataVector().removeAllElements();
		this.tablaProveedores.updateUI();
	}
	
	public void llenarTablaProveedor(String nombre) {
		this.borrarElementosDeLaTablaProveedor();
		AppContext.proveedorController.verProveedoresEnTabla(nombre).forEach(d -> {
			this.modelTablaProveedores.addRow(d);
		});;
	}

}
