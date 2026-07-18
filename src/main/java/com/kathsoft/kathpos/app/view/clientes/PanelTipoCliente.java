package com.kathsoft.kathpos.app.view.clientes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PanelTipoCliente extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiquetaTipoCliente;
	//private Container panelTipoCliente;
	private JLabel lblNewLabel_11;
	private DefaultTableModel modelTablaTipoCliente;
	private JPanel panelInferiorBusqueda;
	private JTextField txfNombreTipoCliente;
	private JPanel panelSuperiorBotones;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnExcel;
	private JTable tableTipoCliente;
	private JPanel panelCentralContenedor;
	private FlowLayout flowLayout;
	private JScrollPane scrollPaneTableTipoCliente;
	private GroupLayout gl_panelCentralContenedor;
	private JLabel lblBuscar;
	private JButton btnBuscar;
	private GroupLayout gl_panelInferiorBusqueda;
	private GroupLayout groupLayout;	

	/**
	 * Create the panel.
	 */
	public PanelTipoCliente() {
		
		
		this.setBackground(new Color(255, 215, 0));
		
		
		this.panelEtiquetaTipoCliente = new JPanel();
		this.panelEtiquetaTipoCliente.setBackground(new Color(0, 0, 128));		

		this.lblNewLabel_11 = new JLabel("Categorias de clientes");
		this.lblNewLabel_11.setForeground(new Color(255, 255, 255));
		this.lblNewLabel_11.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.panelEtiquetaTipoCliente.add(lblNewLabel_11);
		
		this.modelTablaTipoCliente = this.getModelTablaCliente();
		
		
		panelCentralContenedor = new JPanel();
		panelCentralContenedor.setBackground(new Color(255, 204, 0));
		groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelEtiquetaTipoCliente, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelCentralContenedor, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(0))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelEtiquetaTipoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(8)
					.addComponent(panelCentralContenedor, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
		);
		
		panelInferiorBusqueda = new JPanel();
		panelInferiorBusqueda.setBackground(new Color(0, 153, 255));
		
		panelSuperiorBotones = new JPanel();
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		scrollPaneTableTipoCliente = new JScrollPane();
		gl_panelCentralContenedor = new GroupLayout(panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(panelInferiorBusqueda, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTableTipoCliente, GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTableTipoCliente, GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferiorBusqueda, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
		);
		
		tableTipoCliente = new JTable();
		this.tableTipoCliente.setModel(this.modelTablaTipoCliente);
		scrollPaneTableTipoCliente.setViewportView(tableTipoCliente);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormTipoClientes(1, 0);
			}
		});
		btnAgregar.setIcon(new ImageIcon(PanelTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int index = DataTools.getIndiceElementoSeleccionado(tableTipoCliente, modelTablaTipoCliente, 0);
					abrirFormTipoClientes(2, index);
				}catch (Exception er) {
					// TODO: handle exception
					er.printStackTrace();
				}
			}
		});
		btnModificar.setIcon(new ImageIcon(PanelTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarTipoCliente();
			}
		});
		btnEliminar.setIcon(new ImageIcon(PanelTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255, 51, 0));
		panelSuperiorBotones.add(btnEliminar);
		
		btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExcel.setBackground(new Color(102, 205, 170));
		panelSuperiorBotones.add(btnExcel);
		
		lblBuscar = new JLabel("Buscar");
		
		txfNombreTipoCliente = new JTextField();
		txfNombreTipoCliente.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listarTipoClientes(txfNombreTipoCliente.getText());
			}
		});
		btnBuscar.setBackground(new Color(153, 102, 0));
		btnBuscar.setIcon(new ImageIcon(PanelTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		gl_panelInferiorBusqueda = new GroupLayout(panelInferiorBusqueda);
		gl_panelInferiorBusqueda.setHorizontalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblBuscar)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNombreTipoCliente, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panelInferiorBusqueda.setVerticalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelInferiorBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblBuscar)
						.addComponent(txfNombreTipoCliente, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscar))
					.addContainerGap())
		);
		panelInferiorBusqueda.setLayout(gl_panelInferiorBusqueda);
		panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		setLayout(groupLayout);

	}
	
	private DefaultTableModel getModelTablaCliente() {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("Id");
		model.addColumn("Categoria de cliente");
		model.addColumn("Descripción");
		model.addColumn("Estatus");
		
		return model;
		
	}
	
	public void listarTipoClientes(String nombre) {
		
		this.modelTablaTipoCliente.getDataVector().removeAllElements();
		this.tableTipoCliente.updateUI();
		
		AppContext.tipoClienteController.listarTipoCliente(nombre).forEach(modelTablaTipoCliente::addRow);
		
	}
	
	private void eliminarTipoCliente() {
		
		int index = DataTools.getIndiceElementoSeleccionado(this.tableTipoCliente, modelTablaTipoCliente, 0);
		if(index < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar un registro");
			return;
		}
		
		var result = AppContext.tipoClienteController.eliminarTipoCliente(index);
		
		MessageHandler.displayMessage(
				result.id() == 200 ?
				MessageHandler.DELETE_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE, this, result.message());
		
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
}
