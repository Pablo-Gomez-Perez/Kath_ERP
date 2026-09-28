package com.kathsoft.kathpos.app.view.gastos;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelCategoriaDeGasto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txfNombreCategoriaDeGasto;
	private JTable tableCategoriasDeGasto;

	/**
	 * Create the panel.
	 */
	public PanelCategoriaDeGasto() {
		setBackground(new Color(255, 215, 0));
		setBorder(null);
		
		JPanel panelSuperiorTitulo = new JPanel();
		panelSuperiorTitulo.setBackground(new Color(0, 0, 102));
		
		JLabel lblTituloCategoriaGasto = new JLabel("Categorias de gastos");
		lblTituloCategoriaGasto.setForeground(Color.WHITE);
		lblTituloCategoriaGasto.setFont(new Font("Dialog", Font.BOLD, 20));
		panelSuperiorTitulo.add(lblTituloCategoriaGasto);
		
		JPanel panelinferiorbusquedas = new JPanel();
		panelinferiorbusquedas.setBackground(new Color(0, 153, 255));
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txfNombreCategoriaDeGasto = new JTextField();
		lblNombre.setLabelFor(txfNombreCategoriaDeGasto);
		txfNombreCategoriaDeGasto.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		btnBuscar.setBackground(new Color(184, 134, 11));
		GroupLayout gl_panelinferiorbusquedas = new GroupLayout(panelinferiorbusquedas);
		gl_panelinferiorbusquedas.setHorizontalGroup(
			gl_panelinferiorbusquedas.createParallelGroup(Alignment.LEADING)
				.addGap(0, 668, Short.MAX_VALUE)
				.addGroup(gl_panelinferiorbusquedas.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombre, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txfNombreCategoriaDeGasto, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		gl_panelinferiorbusquedas.setVerticalGroup(
			gl_panelinferiorbusquedas.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 54, Short.MAX_VALUE)
				.addGroup(gl_panelinferiorbusquedas.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelinferiorbusquedas.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panelinferiorbusquedas.createSequentialGroup()
							.addGap(6)
							.addComponent(txfNombreCategoriaDeGasto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelinferiorbusquedas.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNombre)
							.addComponent(btnBuscar)))
					.addContainerGap())
		);
		panelinferiorbusquedas.setLayout(gl_panelinferiorbusquedas);
		
		JPanel panelSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnAgregar);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255, 51, 0));
		panelSuperiorBotones.add(btnEliminar);
		
		JButton btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExcel.setBackground(new Color(102, 205, 170));
		panelSuperiorBotones.add(btnExcel);
		
		JScrollPane scrollPaneTablaCategoriasDeGasto = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelSuperiorTitulo, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
				.addComponent(panelinferiorbusquedas, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTablaCategoriasDeGasto, GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelSuperiorTitulo, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTablaCategoriasDeGasto, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelinferiorbusquedas, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
		);
		
		tableCategoriasDeGasto = new JTable();
		scrollPaneTablaCategoriasDeGasto.setViewportView(tableCategoriasDeGasto);
		setLayout(groupLayout);
		
	}
}
