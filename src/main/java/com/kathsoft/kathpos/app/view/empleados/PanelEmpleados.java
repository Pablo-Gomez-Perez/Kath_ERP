package com.kathsoft.kathpos.app.view.empleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PanelEmpleados extends JPanel {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelTablaEmpleados;
	private JPanel panelSuperiorTitulo;
	private JLabel lblEmpleados;
	private JTextField txfNombreEmpleado;

	/**
	 * Create the panel.
	 */
	public PanelEmpleados() {
		setBackground(new Color(255, 204, 0));
		
		//CODEX.TODO: El las columnas de la tabla en base a la respuesta retornada desde el controlador.
		modelTablaEmpleados = new DefaultTableModel();
		modelTablaEmpleados.addColumn("id");
		modelTablaEmpleados.addColumn("Sucursal");
		modelTablaEmpleados.addColumn("RFC");
		modelTablaEmpleados.addColumn("CURP");
		modelTablaEmpleados.addColumn("Nombre");
		modelTablaEmpleados.addColumn("Nick");
		modelTablaEmpleados.addColumn("Email");
		modelTablaEmpleados.addColumn("Activo");
		
		panelSuperiorTitulo = new JPanel();
		panelSuperiorTitulo.setBackground(new Color(0, 0, 102));
		
		JPanel panelinferiorbusquedas = new JPanel();
		panelinferiorbusquedas.setBackground(new Color(0, 153, 255));
		
		JPanel panelSuperiorBotones = new JPanel();
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSuperiorTitulo, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addComponent(panelinferiorbusquedas, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 752, Short.MAX_VALUE)
					.addContainerGap())
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 776, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelSuperiorTitulo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelinferiorbusquedas, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JButton btnAgregar = new JButton("Agregar");
		panelSuperiorBotones.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		panelSuperiorBotones.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		panelSuperiorBotones.add(btnEliminar);
		
		JButton btnExcel = new JButton("Excel");
		panelSuperiorBotones.add(btnExcel);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txfNombreEmpleado = new JTextField();
		txfNombreEmpleado.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		GroupLayout gl_panelinferiorbusquedas = new GroupLayout(panelinferiorbusquedas);
		gl_panelinferiorbusquedas.setHorizontalGroup(
			gl_panelinferiorbusquedas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelinferiorbusquedas.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNombreEmpleado, GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		gl_panelinferiorbusquedas.setVerticalGroup(
			gl_panelinferiorbusquedas.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelinferiorbusquedas.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelinferiorbusquedas.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(lblNombre)
						.addGroup(gl_panelinferiorbusquedas.createSequentialGroup()
							.addGap(6)
							.addComponent(txfNombreEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnBuscar))
					.addContainerGap())
		);
		panelinferiorbusquedas.setLayout(gl_panelinferiorbusquedas);
		
		lblEmpleados = new JLabel("Empleados");
		lblEmpleados.setForeground(new Color(255, 255, 255));
		lblEmpleados.setFont(new Font("Dialog", Font.BOLD, 20));
		panelSuperiorTitulo.add(lblEmpleados);
		setLayout(groupLayout);
	}
	
	/**
	 * borra todos los elementos contenidos en la tabla empleados
	 */
	private void borrarElementosDeLaTablaEmpleados() {
		this.modelTablaEmpleados.getDataVector().removeAllElements();
		//this.tableEmpleados.updateUI();
	}
	
	
	/**
	 * llena el jTable del panel de empleados con todos los registros encontrados en
	 * la bd
	 */
	public void llenarTablaEmpleados() {
		this.borrarElementosDeLaTablaEmpleados();
		AppContext.empleadoController.verEmpleadosEnTabla(modelTablaEmpleados);
	}
}
