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
	private JPanel panelinferiorbusquedas;
	private JPanel panelSuperiorBotones;
	private FlowLayout flowLayout;
	private JScrollPane scrollPane;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnExcel;
	private JButton btnBuscar;
	private JTable tableEmpleados;
	private JLabel lblNombre;

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
		
		panelinferiorbusquedas = new JPanel();
		panelinferiorbusquedas.setBackground(new Color(0, 153, 255));
		
		panelSuperiorBotones = new JPanel();
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		
		scrollPane = new JScrollPane();
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
		
		tableEmpleados = new JTable();
		scrollPane.setViewportView(tableEmpleados);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(PanelEmpleados.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelEmpleados.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelEmpleados.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255,51,0));
		panelSuperiorBotones.add(btnEliminar);
		
		btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelEmpleados.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExcel.setBackground(new Color(102,205,170));
		panelSuperiorBotones.add(btnExcel);
		
		lblNombre = new JLabel("Nombre");
		
		txfNombreEmpleado = new JTextField();
		txfNombreEmpleado.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
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
		this.tableEmpleados.updateUI();
	}
	
	
	//CODEX.TODO: llenar la tabla previallamada al método en el controlador, el controlador no debe tocar nada relacionado  ala vista.
	/**
	 * llena el jTable del panel de empleados con todos los registros encontrados en
	 * la bd
	 */
	public void llenarTablaEmpleados() {
		this.borrarElementosDeLaTablaEmpleados();
		AppContext.empleadoController.verEmpleadosEnTabla(modelTablaEmpleados);
	}
}
