package com.kathsoft.kathpos.app.view.sucursal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import javax.swing.JTextField;

public class PanelSucursales extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiquetaSucursales;
	private JLabel lblNewLabel_8;
	private JPanel panelSucursalCentral;
	private JScrollPane scrollPaneTablaSucursales;
	private DefaultTableModel modelTablaSucursales;
	private JTable tablaSucursales;
	private JPanel panelSucursalCentralBotones;
	private JButton btnNuevaSucursal;
	private JButton btnActualizarSucursal;
	private JButton btnEliminarSucursal;
	private JPanel panel;
	private JLabel lblNombreDeSucursal;
	private JButton btnBuscar;
	private JTextField txfNombreSucursal;

	/**
	 * Create the panel.
	 */
	public PanelSucursales() {
		
		this.setBackground(new Color(255, 215, 0));
		this.setLayout(new BorderLayout(0, 0));
		
		panelEtiquetaSucursales = new JPanel();
		panelEtiquetaSucursales.setBackground(new Color(0, 0, 128));
		this.add(panelEtiquetaSucursales, BorderLayout.NORTH);

		lblNewLabel_8 = new JLabel("Modulo de Sucursales");
		lblNewLabel_8.setForeground(new Color(255, 255, 255));
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelEtiquetaSucursales.add(lblNewLabel_8);

		panelSucursalCentral = new JPanel();
		panelSucursalCentral.setBorder(null);
		panelSucursalCentral.setBackground(new Color(255, 215, 0));
		this.add(panelSucursalCentral, BorderLayout.CENTER);

		scrollPaneTablaSucursales = new JScrollPane();

		modelTablaSucursales = new DefaultTableModel();
		tablaSucursales = new JTable();
		tablaSucursales.setModel(modelTablaSucursales);
		scrollPaneTablaSucursales.setViewportView(tablaSucursales);

		modelTablaSucursales.addColumn("Id");
		modelTablaSucursales.addColumn("Nombre");
		modelTablaSucursales.addColumn("Descripcion");
		modelTablaSucursales.addColumn("Telefono");
		modelTablaSucursales.addColumn("Email");
		modelTablaSucursales.addColumn("Estado");
		modelTablaSucursales.addColumn("Ciudad");
		modelTablaSucursales.addColumn("Dirección");
		modelTablaSucursales.addColumn("Codigo Postal");
		modelTablaSucursales.addColumn("Activo");

		tablaSucursales.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		// remueve el editor de la tabla de sucursales
		DataTools.removerEditorDeTabla(tablaSucursales, modelTablaSucursales);

		panelSucursalCentralBotones = new JPanel();
		panelSucursalCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_8 = (FlowLayout) panelSucursalCentralBotones.getLayout();
		flowLayout_8.setAlignment(FlowLayout.RIGHT);

		btnNuevaSucursal = new JButton("Agregar");
		btnNuevaSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormSucursales(0, 0);
			}
		});
		btnNuevaSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnNuevaSucursal.setBackground(new Color(152, 251, 152));
		panelSucursalCentralBotones.add(btnNuevaSucursal);

		btnActualizarSucursal = new JButton("Actualizar");
		btnActualizarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = DataTools.getIndiceElementoSeleccionado(tablaSucursales, modelTablaSucursales, 0);
				// System.out.println(index);

				abrirFormSucursales(1, index);

			}
		});
		btnActualizarSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnActualizarSucursal.setBackground(new Color(0, 255, 127));
		panelSucursalCentralBotones.add(btnActualizarSucursal);

		btnEliminarSucursal = new JButton("Eliminar");

		btnEliminarSucursal.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));

		btnEliminarSucursal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aliminarSucursal();
			}
		});

		this.btnEliminarSucursal.setBackground(new Color(255, 51, 0));

		panelSucursalCentralBotones.add(btnEliminarSucursal);
		
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaSucursalesColumnWidth, tablaSucursales);
		
		this.panel = new JPanel();
		this.panel.setBackground(new Color(255, 204, 0));
		GroupLayout gl_panelSucursalCentral = new GroupLayout(this.panelSucursalCentral);
		gl_panelSucursalCentral.setHorizontalGroup(
			gl_panelSucursalCentral.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelSucursalCentralBotones, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
				.addComponent(this.panel, GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
				.addGroup(gl_panelSucursalCentral.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.scrollPaneTablaSucursales, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelSucursalCentral.setVerticalGroup(
			gl_panelSucursalCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelSucursalCentral.createSequentialGroup()
					.addComponent(this.panelSucursalCentralBotones, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.scrollPaneTablaSucursales, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.panel, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
		);
		
		this.lblNombreDeSucursal = new JLabel("Nombre de Sucursal");
		
		this.btnBuscar = new JButton("Buscar");
		
		this.txfNombreSucursal = new JTextField();
		this.txfNombreSucursal.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(this.panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.lblNombreDeSucursal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfNombreSucursal, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.btnBuscar)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNombreDeSucursal)
						.addComponent(this.btnBuscar)
						.addComponent(this.txfNombreSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		this.panel.setLayout(gl_panel);
		this.panelSucursalCentral.setLayout(gl_panelSucursalCentral);
		this.llenarTablaSucursales();
	}
	
	private void abrirFormSucursales(int opcion, int idSucursal) {
		Component cm = null;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Fr_DatosSucursal frame = new Fr_DatosSucursal(opcion, idSucursal);
					frame.setLocationRelativeTo(cm);
					frame.setVisible(true);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
	
	private void aliminarSucursal() {

		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, "");

		if (input > 0) {
			return;
		}

		try {

			AppContext.sucursalController.eliminarSucursal(
					DataTools.getIndiceElementoSeleccionado(tablaSucursales, modelTablaSucursales, 0));

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, "");

		} catch (SQLException er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}

	}
	
	/**
	 * Llena el JTable correspondiente con los registro de las sucursales extraidos
	 * de la base de datos
	 */
	public void llenarTablaSucursales() {
		this.modelTablaSucursales.getDataVector().removeAllElements();
		AppContext.sucursalController.verSucursalesEnTabla().forEach(this.modelTablaSucursales::addRow);
		this.tablaSucursales.updateUI();
	}
}
