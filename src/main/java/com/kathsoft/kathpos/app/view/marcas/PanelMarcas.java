package com.kathsoft.kathpos.app.view.marcas;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

public class PanelMarcas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelMarcasEtiquetaSuperior;
	private JLabel lblNewLabel_1;
	private JPanel panelMarcasCentral;
	private DefaultTableModel modelTablaCategoriaArticulo;
	private JScrollPane scrollPaneTablaCategorias;
	private JTable tablaCategorias;
	private JPanel panelCategoriasCentralBotones;
	private JButton btnAgregarCategoria;
	private JButton btnActualizarCategoria;
	private JButton btnEliminarCategoria;
	private JPanel panelMarcasCentralBuscar;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut;
	private JTextField txfBuscarCategoria;
	private JButton btnBuscarCategoria;

	/**
	 * Create the panel.
	 */
	public PanelMarcas() {
		
		this.setLayout(new BorderLayout(0, 0));
		
		this.panelMarcasEtiquetaSuperior = new JPanel();
		this.panelMarcasEtiquetaSuperior.setBackground(new Color(0, 0, 128));
		this.panelMarcasEtiquetaSuperior.setForeground(new Color(0, 0, 0));
		this.add(panelMarcasEtiquetaSuperior, BorderLayout.NORTH);
		
		this.lblNewLabel_1 = new JLabel("Modulo de categoria");
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblNewLabel_1.setForeground(new Color(255, 255, 255));
		this.panelMarcasEtiquetaSuperior.add(lblNewLabel_1);
		
		this.panelMarcasCentral = new JPanel();
		this.panelMarcasCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		this.panelMarcasCentral.setBackground(new Color(255, 215, 0));
		this.add(panelMarcasCentral, BorderLayout.CENTER);
		this.panelMarcasCentral.setLayout(new BorderLayout(0, 0));
		
		this.modelTablaCategoriaArticulo = new DefaultTableModel();

		this.modelTablaCategoriaArticulo.addColumn("Id");
		this.modelTablaCategoriaArticulo.addColumn("Nombre");
		this.modelTablaCategoriaArticulo.addColumn("Descripcion");
		this.modelTablaCategoriaArticulo.addColumn("Activo");

		this.scrollPaneTablaCategorias = new JScrollPane();
		this.panelMarcasCentral.add(scrollPaneTablaCategorias, BorderLayout.CENTER);

		this.tablaCategorias = new JTable();
		this.tablaCategorias.setModel(modelTablaCategoriaArticulo);
		this.tablaCategorias.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		this.scrollPaneTablaCategorias.setViewportView(tablaCategorias);

		DataTools.removerEditorDeTabla(this.tablaCategorias, modelTablaCategoriaArticulo);
		
		this.panelCategoriasCentralBotones = new JPanel();
		this.panelCategoriasCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_10 = (FlowLayout) panelCategoriasCentralBotones.getLayout();
		flowLayout_10.setAlignment(FlowLayout.RIGHT);
		this.panelMarcasCentral.add(panelCategoriasCentralBotones, BorderLayout.NORTH);
		
		this.btnAgregarCategoria = new JButton("Agregar");
		this.btnAgregarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioCategoria(1, 0);
			}
		});
		this.btnAgregarCategoria.setBackground(new Color(144, 238, 144));
		this.btnAgregarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.panelCategoriasCentralBotones.add(btnAgregarCategoria);

		this.btnActualizarCategoria = new JButton("Actualizar");
		this.btnActualizarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioCategoria(2,
						DataTools.getIndiceElementoSeleccionado(tablaCategorias, modelTablaCategoriaArticulo, 0));
			}
		});
		this.btnActualizarCategoria.setBackground(new Color(144, 238, 144));
		this.btnActualizarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		this.panelCategoriasCentralBotones.add(btnActualizarCategoria);
		
		this.btnEliminarCategoria = new JButton("Eliminar");
		this.btnEliminarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCategoria();
			}
		});
		this.btnEliminarCategoria.setBackground(new Color(255, 51, 0));
		this.btnEliminarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.panelCategoriasCentralBotones.add(btnEliminarCategoria);
		
		this.panelMarcasCentralBuscar = new JPanel();
		this.panelMarcasCentralBuscar.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_11 = (FlowLayout) panelMarcasCentralBuscar.getLayout();
		flowLayout_11.setAlignment(FlowLayout.RIGHT);
		this.panelMarcasCentral.add(panelMarcasCentralBuscar, BorderLayout.SOUTH);

		this.lblNewLabel_3 = new JLabel("Buscar categoría");
		this.lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.panelMarcasCentralBuscar.add(lblNewLabel_3);

		this.horizontalStrut = Box.createHorizontalStrut(20);
		this.panelMarcasCentralBuscar.add(horizontalStrut);

		this.txfBuscarCategoria = new JTextField();
		this.panelMarcasCentralBuscar.add(txfBuscarCategoria);
		this.txfBuscarCategoria.setColumns(70);
		
		btnBuscarCategoria = new JButton("Buscar");
		btnBuscarCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaCategoria(txfBuscarCategoria.getText());
			}
		});
		btnBuscarCategoria.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		this.btnBuscarCategoria.setBackground(new Color(184, 134, 11));
		panelMarcasCentralBuscar.add(btnBuscarCategoria);
		
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaCategoriaColumnsWidth, tablaCategorias);
		
	}
	
	private void abrirVentanaFormularioCategoria(int opcion, int idCategoria) {
		Component cm = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Fr_DatosCategoria fr = new Fr_DatosCategoria(opcion, idCategoria);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);
					fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
	
	private void eliminarCategoria() {

		int input = JOptionPane.showConfirmDialog(this, "Desea eliminara el registro?", "Eliminar Categoría",
				JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);

		if (input > 0) {
			return;
		}

		try {

			AppContext.categoriaController.eliminarCategoria(
					DataTools.getIndiceElementoSeleccionado(tablaCategorias, modelTablaCategoriaArticulo, 0));

			JOptionPane.showMessageDialog(this, "Registro eliminado", "Eliminar Categoria",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException er) {
			JOptionPane.showMessageDialog(this, er.getMessage(), "Eliminar Categoria", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	/**
	 * borra todos los elementos contenidos en la tabla categorias
	 */
	private void borrarElementosDeLaTablaCategorias() {
		this.modelTablaCategoriaArticulo.getDataVector().removeAllElements();
		this.tablaCategorias.updateUI();
	}
	
	
	/**
	 * llena el JTable del panel de categorias con todos los registros encontrados
	 * en la bd
	 */
	public void llenarTablaCategoria(String nombre) {
		this.borrarElementosDeLaTablaCategorias();
		AppContext.categoriaController.verCategoriasEnTabla(nombre).forEach(d -> {
			this.modelTablaCategoriaArticulo.addRow(d);
		});
	}

}
