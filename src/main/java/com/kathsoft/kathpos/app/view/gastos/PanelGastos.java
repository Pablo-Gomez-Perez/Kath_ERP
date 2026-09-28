package com.kathsoft.kathpos.app.view.gastos;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelGastos extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable tableGastos;

	/**
	 * Create the panel.
	 */
	public PanelGastos() {
		setBackground(new Color(255, 215, 0));
		setBorder(null);
		
		JPanel panelEtiquetaVentas = new JPanel();
		panelEtiquetaVentas.setBackground(new Color(0, 0, 128));
		
		JLabel lblTituloGastos = new JLabel("Gastos");
		lblTituloGastos.setForeground(Color.WHITE);
		lblTituloGastos.setFont(new Font("Dialog", Font.BOLD, 16));
		panelEtiquetaVentas.add(lblTituloGastos);
		
		JPanel panelInferiorCriteriosBusqueda = new JPanel();
		panelInferiorCriteriosBusqueda.setBackground(new Color(0, 191, 255));
		
		JPanel panelSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255, 51, 0));
		panelSuperiorBotones.add(btnEliminar);
		
		JButton btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExcel.setBackground(new Color(102, 205, 170));
		panelSuperiorBotones.add(btnExcel);
		
		JScrollPane scrollPaneTablaGastos = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelEtiquetaVentas, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelInferiorCriteriosBusqueda, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTablaGastos, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelEtiquetaVentas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTablaGastos, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferiorCriteriosBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		tableGastos = new JTable();
		scrollPaneTablaGastos.setViewportView(tableGastos);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		
		JComboBox comboBoxEmpleado = new JComboBox();
		
		JLabel lblCategoria = new JLabel("Categoria");
		
		JComboBox comboBoxCategoriaDeGasto = new JComboBox();
		
		JButton btnBuscarGasto = new JButton("Buscar");
		btnBuscarGasto.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		btnBuscarGasto.setFont(new Font("Dialog", Font.BOLD, 13));
		btnBuscarGasto.setBackground(new Color(184, 134, 11));
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por");
		
		JComboBox comboBoxOrdenarPor = new JComboBox();
		
		JLabel lblDesde = new JLabel("Desde");
		
		JFormattedTextField formattedTextFieldFechaInicio = new JFormattedTextField();
		
		JLabel lblHasta = new JLabel("Hasta");
		
		JFormattedTextField formattedTextFieldFechaFinal = new JFormattedTextField();
		GroupLayout gl_panelInferiorCriteriosBusqueda = new GroupLayout(panelInferiorCriteriosBusqueda);
		gl_panelInferiorCriteriosBusqueda.setHorizontalGroup(
			gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblEmpleado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxEmpleado, 0, 169, Short.MAX_VALUE))
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblDesde)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldFechaInicio, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblCategoria)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxCategoriaDeGasto, 0, 159, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOrdenarPor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxOrdenarPor, 0, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscarGasto, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblHasta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldFechaFinal, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
							.addGap(315)))
					.addContainerGap())
		);
		gl_panelInferiorCriteriosBusqueda.setVerticalGroup(
			gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmpleado)
						.addComponent(comboBoxEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscarGasto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria)
						.addComponent(comboBoxCategoriaDeGasto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdenarPor)
						.addComponent(comboBoxOrdenarPor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDesde)
						.addComponent(formattedTextFieldFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHasta)
						.addComponent(formattedTextFieldFechaFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panelInferiorCriteriosBusqueda.setLayout(gl_panelInferiorCriteriosBusqueda);
		setLayout(groupLayout);

	}
}
