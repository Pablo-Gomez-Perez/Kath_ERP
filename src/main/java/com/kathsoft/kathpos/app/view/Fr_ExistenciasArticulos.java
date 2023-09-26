package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.ArticuloController;

import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fr_ExistenciasArticulos extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2653673165859622829L;
	/**
	 * 
	 */
	private JPanel contentPane;
	private JTable tablaExistenciasArticulo;
	private JScrollPane scrollPaneExistenciasArticulo;
	private DefaultTableModel modelTablaExistencias;
	private ArticuloController articuloController = new ArticuloController();

	/**
	 * Create the frame.
	 */
	public Fr_ExistenciasArticulos(int id_articulo) {		
		setBounds(100, 100, 480, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		scrollPaneExistenciasArticulo = new JScrollPane();
		contentPane.add(scrollPaneExistenciasArticulo, BorderLayout.CENTER);
		
		modelTablaExistencias = new DefaultTableModel();
		tablaExistenciasArticulo = new JTable();
		tablaExistenciasArticulo.setModel(modelTablaExistencias);
		modelTablaExistencias.addColumn("Sucursal");
		modelTablaExistencias.addColumn("Existencia");
		scrollPaneExistenciasArticulo.setViewportView(tablaExistenciasArticulo);		
		
		this.llenarTablaExistencia(id_articulo);
				
	}
	
	private void llenarTablaExistencia(int id_articulo) {
		this.modelTablaExistencias.getDataVector().removeAllElements();
		this.tablaExistenciasArticulo.updateUI();
		articuloController.consultarExistenciasPorSucursal(id_articulo, modelTablaExistencias);
	}

}
