package com.kathsoft.kathpos.app.view.clientes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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

public class PanelTipoCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiquetaTipoCliente;
	private Container panelTipoCliente;
	private JLabel lblNewLabel_11;
	private JPanel panelTipoClienteCentral;
	private JPanel panelTipoClienteCentralBotones;
	private JButton btnNuevoTipoCliente;
	private JButton btnActualizarTipoCliente;
	private JButton btnEliminarTipoCliente;
	private JScrollPane scrollPaneTablaTipoCliente;
	private DefaultTableModel modelTablaTipoCliente;
	private JTable tableTipoCliente;
	private JPanel panelInferiorBusqueda;
	private JLabel lblNewLabel_12;
	private Component horizontalStrut_5;
	private JTextField txtBuscarCategoriaCliente;
	private Component horizontalStrut_6;
	private JButton btnBuscarCategoriaCliente;

	/**
	 * Create the panel.
	 */
	public PanelTipoCliente() {
		
		
		this.setBackground(new Color(255, 215, 0));
		this.setLayout(new BorderLayout(0, 0));
		
		
		this.panelEtiquetaTipoCliente = new JPanel();
		this.panelEtiquetaTipoCliente.setBackground(new Color(0, 0, 128));		

		this.lblNewLabel_11 = new JLabel("Categorias de clientes");
		this.lblNewLabel_11.setForeground(new Color(255, 255, 255));
		this.lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.panelEtiquetaTipoCliente.add(lblNewLabel_11);
		
		this.add(panelEtiquetaTipoCliente, BorderLayout.NORTH);
		
		this.panelTipoClienteCentral = new JPanel();
		this.panelTipoClienteCentral.setBackground(new Color(255, 215, 0));
		this.panelTipoClienteCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.panelTipoClienteCentral.setLayout(new BorderLayout(0, 0));
		this.add(panelTipoClienteCentral, BorderLayout.CENTER);
		
		this.panelTipoClienteCentralBotones = new JPanel();
		FlowLayout flowLayout_14 = (FlowLayout) panelTipoClienteCentralBotones.getLayout();
		flowLayout_14.setAlignment(FlowLayout.RIGHT);
		this.panelTipoClienteCentralBotones.setBackground(new Color(255, 215, 0));
		this.panelTipoClienteCentral.add(panelTipoClienteCentralBotones, BorderLayout.NORTH);

		this.btnNuevoTipoCliente = new JButton("Agregar");
		this.btnNuevoTipoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormTipoClientes(1, 0);
			}
		});
		this.btnNuevoTipoCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnNuevoTipoCliente.setBackground(new Color(152, 251, 152));
		this.panelTipoClienteCentralBotones.add(btnNuevoTipoCliente);

		this.btnActualizarTipoCliente = new JButton("Actualizar");
		this.btnActualizarTipoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int index = DataTools.getIndiceElementoSeleccionado(tableTipoCliente, modelTablaTipoCliente, 0);
				abrirFormTipoClientes(2, index);

			}
		});
		this.btnActualizarTipoCliente.setIcon(new ImageIcon(
				Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		this.btnActualizarTipoCliente.setBackground(new Color(152, 251, 152));
		this.panelTipoClienteCentralBotones.add(btnActualizarTipoCliente);
		
		this.btnEliminarTipoCliente = new JButton("Eliminar");
		this.btnEliminarTipoCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTipoCliente();
			}
		});
		this.btnEliminarTipoCliente.setBackground(new Color(255, 51, 0));
		this.btnEliminarTipoCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.panelTipoClienteCentralBotones.add(btnEliminarTipoCliente);

		this.scrollPaneTablaTipoCliente = new JScrollPane();
		this.panelTipoClienteCentral.add(scrollPaneTablaTipoCliente, BorderLayout.CENTER);
		
		this.modelTablaTipoCliente = new DefaultTableModel();

		this.modelTablaTipoCliente.addColumn("Id");
		this.modelTablaTipoCliente.addColumn("Categoria de cliente");
		this.modelTablaTipoCliente.addColumn("Descripción");
		this.modelTablaTipoCliente.addColumn("Estatus");

		this.tableTipoCliente = new JTable();
		this.tableTipoCliente.setModel(modelTablaTipoCliente);
		this.tableTipoCliente.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPaneTablaTipoCliente.setViewportView(tableTipoCliente);
		
		panelInferiorBusqueda = new JPanel();
		FlowLayout flowLayout_15 = (FlowLayout) panelInferiorBusqueda.getLayout();
		flowLayout_15.setAlignment(FlowLayout.RIGHT);
		panelInferiorBusqueda.setBackground(new Color(255, 215, 0));
		panelTipoClienteCentral.add(panelInferiorBusqueda, BorderLayout.SOUTH);

		lblNewLabel_12 = new JLabel("Buscar Categoria");
		lblNewLabel_12.setFont(new Font("Tahoma", Font.BOLD, 12));
		panelInferiorBusqueda.add(lblNewLabel_12);

		horizontalStrut_5 = Box.createHorizontalStrut(10);
		panelInferiorBusqueda.add(horizontalStrut_5);

		txtBuscarCategoriaCliente = new JTextField();
		txtBuscarCategoriaCliente.setColumns(70);
		this.txtBuscarCategoriaCliente.setMaximumSize(this.txtBuscarCategoriaCliente.getPreferredSize());
		panelInferiorBusqueda.add(txtBuscarCategoriaCliente);

		horizontalStrut_6 = Box.createHorizontalStrut(10);
		panelInferiorBusqueda.add(horizontalStrut_6);

		btnBuscarCategoriaCliente = new JButton("Buscar");
		btnBuscarCategoriaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaTipoCliente();
			}
		});
		btnBuscarCategoriaCliente.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		btnBuscarCategoriaCliente.setBackground(new Color(184, 134, 11));
		panelInferiorBusqueda.add(btnBuscarCategoriaCliente);

		DataTools.removerEditorDeTabla(tableTipoCliente, modelTablaTipoCliente);
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaTipoClienteColumnsWidth, tableTipoCliente);

	}
	
	private void abrirFormTipoClientes(int opcion, int idTipoCliente) {

		Component cmp = this;

		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Fr_DatosTipoCliente frame = new Fr_DatosTipoCliente(opcion, idTipoCliente);
					frame.setLocationRelativeTo(cmp);
					frame.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}

			}
		});
	}
	
	private void eliminarTipoCliente() {
		int indiceTipoClienteSeleccionado = -1;
		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this);

		if (input > 0) {
			return;
		}
		
		try {
			
			indiceTipoClienteSeleccionado = DataTools.getIndiceElementoSeleccionado(tableTipoCliente, modelTablaTipoCliente, 0);
			AppContext.tipoClienteController.eliminarTipoCliente(indiceTipoClienteSeleccionado);
		}catch(SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	public void llenarTablaTipoCliente() {
		this.modelTablaTipoCliente.getDataVector().removeAllElements();
		this.tableTipoCliente.updateUI();
		AppContext.tipoClienteController.listarTipoCliente(this.txtBuscarCategoriaCliente.getText()).forEach(Tc -> {
			this.modelTablaTipoCliente.addRow(Tc);
		});
		;
	}
}
