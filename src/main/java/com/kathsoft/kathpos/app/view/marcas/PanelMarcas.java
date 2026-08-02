package com.kathsoft.kathpos.app.view.marcas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelMarcas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelMarcasEtiquetaSuperior;
	private JLabel lblNewLabel_1;
	private JPanel panelMarcasCentral;
	private DefaultTableModel modelTablaCategoriaArticulo;
	private JPanel panelInferiorBusqueda;
	private JLabel lblNombre;
	private JTextField textField;
	private JButton btnBuscar;
	private JPanel panelSuperiorBotones;
	private JButton btnAgregar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JButton btnToExcel;
	private JTable tableCategoriaProducto;

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
		this.panelMarcasCentral.setBorder(null);
		this.panelMarcasCentral.setBackground(new Color(255, 204, 0));
		this.add(panelMarcasCentral, BorderLayout.CENTER);
		
		panelInferiorBusqueda = new JPanel();
		panelInferiorBusqueda.setBackground(new Color(0, 153, 255));
		
		lblNombre = new JLabel("Nombre");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaCategoria(textField.getText());
			}
		});
		btnBuscar.setIcon(new ImageIcon(PanelMarcas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		btnBuscar.setBackground(UIManager.getColor("OptionPane.warningDialog.border.background"));
		GroupLayout gl_panelInferiorBusqueda = new GroupLayout(panelInferiorBusqueda);
		gl_panelInferiorBusqueda.setHorizontalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.LEADING)
				.addGap(0, 703, Short.MAX_VALUE)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBuscar)
					.addContainerGap())
		);
		gl_panelInferiorBusqueda.setVerticalGroup(
			gl_panelInferiorBusqueda.createParallelGroup(Alignment.LEADING)
				.addGap(0, 49, Short.MAX_VALUE)
				.addGroup(gl_panelInferiorBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(btnBuscar)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelInferiorBusqueda.setLayout(gl_panelInferiorBusqueda);
		
		panelSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirVentanaFormularioCategoria(1, 0);
			}
		});
		btnAgregar.setIcon(new ImageIcon(PanelMarcas.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modificarCategoria();
			}
		});
		btnModificar.setIcon(new ImageIcon(PanelMarcas.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCategoria();
			}
		});
		btnEliminar.setIcon(new ImageIcon(PanelMarcas.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255, 51, 0));
		panelSuperiorBotones.add(btnEliminar);
		
		btnToExcel = new JButton("Exportar Excel");
		btnToExcel.setIcon(new ImageIcon(PanelMarcas.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnToExcel.setBackground(new Color(102, 205, 170));
		panelSuperiorBotones.add(btnToExcel);
		
		JScrollPane scrollPaneTablaCategoriaProducto = new JScrollPane();
		GroupLayout gl_panelMarcasCentral = new GroupLayout(panelMarcasCentral);
		gl_panelMarcasCentral.setHorizontalGroup(
			gl_panelMarcasCentral.createParallelGroup(Alignment.LEADING)
				.addComponent(panelInferiorBusqueda, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
				.addGroup(gl_panelMarcasCentral.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTablaCategoriaProducto, GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelMarcasCentral.setVerticalGroup(
			gl_panelMarcasCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelMarcasCentral.createSequentialGroup()
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTablaCategoriaProducto, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferiorBusqueda, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE))
		);
		
		tableCategoriaProducto = new JTable();
		scrollPaneTablaCategoriaProducto.setViewportView(tableCategoriaProducto);
		panelMarcasCentral.setLayout(gl_panelMarcasCentral);
		
		this.modelTablaCategoriaArticulo = new DefaultTableModel();

		this.modelTablaCategoriaArticulo.addColumn("Id");
		this.modelTablaCategoriaArticulo.addColumn("Nombre");
		this.modelTablaCategoriaArticulo.addColumn("Descripcion");
		this.modelTablaCategoriaArticulo.addColumn("Activo");
		this.tableCategoriaProducto.setModel(this.modelTablaCategoriaArticulo);
		this.tableCategoriaProducto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DataTools.removerEditorDeTabla(this.tableCategoriaProducto, modelTablaCategoriaArticulo);
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaCategoriaColumnsWidth, tableCategoriaProducto);
		
		this.llenarTablaCategoria("");
	}
	
	/**
	 * Abre el formulario para agregar o modificar una categoria de producto.
	 *
	 * @param opcion tipo de operacion del formulario
	 * @param idCategoria identificador de la categoria seleccionada
	 */
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
					fr.addWindowListener(new java.awt.event.WindowAdapter() {
						@Override
						public void windowClosed(java.awt.event.WindowEvent e) {
							if (fr.isOperacionEjecutada()) {
								llenarTablaCategoria(textField.getText());
							}
						}
					});
				} catch (Exception er) {
					er.printStackTrace(System.err);
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, cm, er.getMessage());
				}
			}
		});
	}
	
	/**
	 * Abre el formulario de modificacion con la categoria seleccionada en la tabla.
	 */
	private void modificarCategoria() {
		if (this.tableCategoriaProducto.getSelectedRow() < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione una categoria para modificar");
			return;
		}

		int idCategoria = DataTools.getIndiceElementoSeleccionado(
				this.tableCategoriaProducto,
				this.modelTablaCategoriaArticulo,
				0
		);

		if (idCategoria < 0) {
			return;
		}

		this.abrirVentanaFormularioCategoria(2, idCategoria);
	}
	
	/**
	 * Inhabilita la categoria seleccionada y actualiza la tabla si la operacion fue exitosa.
	 */
	private void eliminarCategoria() {
		if (this.tableCategoriaProducto.getSelectedRow() < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione una categoria para eliminar");
			return;
		}

		int idCategoria = DataTools.getIndiceElementoSeleccionado(
				this.tableCategoriaProducto,
				this.modelTablaCategoriaArticulo,
				0
		);

		if (idCategoria < 0) {
			return;
		}

		int option = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this, " seleccionada?");

		if (option != 0) {
			return;
		}

		var response = AppContext.categoriaController.eliminarCategoria(idCategoria);

		if (response.id() == 200) {
			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this, response.message());
			this.llenarTablaCategoria(this.textField.getText());
			return;
		}

		MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, response.message());
	}
	
	/**
	 * borra todos los elementos contenidos en la tabla categorias.
	 */
	private void borrarElementosDeLaTablaCategorias() {
		this.modelTablaCategoriaArticulo.getDataVector().removeAllElements();
		this.tableCategoriaProducto.updateUI();
	}
	
	/**
	 * Llena la tabla de categorias de producto usando el filtro de nombre indicado.
	 *
	 * @param nombre nombre o fragmento de nombre para filtrar categorias
	 */
	public void llenarTablaCategoria(String nombre) {
		this.borrarElementosDeLaTablaCategorias();
		var categorias = AppContext.categoriaController.verCategoriasEnTabla(nombre);

		if (categorias == null || categorias.isEmpty()) {
			return;
		}

		categorias.forEach(this.modelTablaCategoriaArticulo::addRow);
	}
}
