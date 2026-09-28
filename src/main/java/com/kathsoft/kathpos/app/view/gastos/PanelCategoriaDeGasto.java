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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelCategoriaDeGasto extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txfNombreCategoriaDeGasto;
	private JTable tableCategoriasDeGasto;
	private DefaultTableModel modelTablaCategoriasDeGasto;
	private boolean listadoInicialCargado;

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
		txfNombreCategoriaDeGasto.addActionListener(e -> buscarCategorias());
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		btnBuscar.setBackground(new Color(184, 134, 11));
		btnBuscar.addActionListener(e -> buscarCategorias());
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
		btnAgregar.addActionListener(e ->
				abrirFormularioCategoria(Fr_DatosCategoriaDeGasto.OPCION_CREAR, 0));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		btnModificar.addActionListener(e -> modificarCategoria());
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255, 51, 0));
		panelSuperiorBotones.add(btnEliminar);
		btnEliminar.addActionListener(e -> eliminarCategoria());
		
		JButton btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelCategoriaDeGasto.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExcel.setBackground(new Color(102, 205, 170));
		panelSuperiorBotones.add(btnExcel);
		// Se reutiliza el exportador CSV del proyecto (compatible con Excel).
		btnExcel.addActionListener(e -> exportarListado());
		
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
		modelTablaCategoriasDeGasto = crearModeloTabla();
		tableCategoriasDeGasto.setModel(modelTablaCategoriasDeGasto);
		tableCategoriasDeGasto.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DataTools.removerEditorDeTabla(tableCategoriasDeGasto, modelTablaCategoriasDeGasto);
		DataTools.definirTamanioDeColumnas(
				ConstantsConllections.tablaCategoriasDeGastoColumnsWidth, tableCategoriasDeGasto);
		scrollPaneTablaCategoriasDeGasto.setViewportView(tableCategoriasDeGasto);
		setLayout(groupLayout);

		// Evita consultar la base durante la construcción de la vista de WindowBuilder.
		// El listado se carga una sola vez cuando el panel entra en pantalla.
		addHierarchyListener(event -> {
			if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0
					&& isShowing() && !listadoInicialCargado) {
				listadoInicialCargado = true;
				buscarCategorias();
			}
		});
	}

	/**
	 * Modelo de cuatro columnas según el contrato de listCategoriasDeGasto.
	 * Su inmutabilidad también impide editar datos cuando el JTable crea
	 * nuevos editores por su cuenta.
	 */
	static DefaultTableModel crearModeloTabla() {
		return new DefaultTableModel(new Object[] {
				"Id", "Nombre", "Descripción", "Activo"
		}, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
	}

	private void buscarCategorias() {
		llenarTablaCategoriasDeGasto(txfNombreCategoriaDeGasto.getText().trim());
	}

	/**
	 * Reemplaza el contenido sólo después de recibir correctamente todos
	 * los registros. Si falla la consulta, conserva el listado anterior.
	 */
	public void llenarTablaCategoriasDeGasto(String nombre) {
		try {
			Vector<Object[]> categorias =
					AppContext.categoriaDeGastoController.verCategoriasEnTabla(nombre);
			modelTablaCategoriasDeGasto.setRowCount(0);
			for (Object[] categoria : categorias) {
				modelTablaCategoriasDeGasto.addRow(categoria);
			}
		} catch (SQLException ex) {
			ex.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"No fue posible consultar las categorías de gasto: " + ex.getMessage());
		}
	}

	/**
	 * Obtiene el ID de la fila seleccionada y convierte la posición visual a
	 * índice de modelo, por si posteriormente se habilita ordenamiento.
	 */
	private int idCategoriaSeleccionada(String operacion) {
		int filaVista = tableCategoriasDeGasto.getSelectedRow();
		if (filaVista < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"Seleccione una categoría de gasto antes de " + operacion);
			return -1;
		}

		int filaModelo = tableCategoriasDeGasto.convertRowIndexToModel(filaVista);
		Object valor = modelTablaCategoriasDeGasto.getValueAt(filaModelo, 0);
		if (!(valor instanceof Number numero) || numero.intValue() <= 0) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"La categoría seleccionada no tiene un identificador válido");
			return -1;
		}
		return numero.intValue();
	}

	private void abrirFormularioCategoria(int opcion, int idCategoria) {
		Fr_DatosCategoriaDeGasto formulario = new Fr_DatosCategoriaDeGasto(opcion, idCategoria);
		formulario.setLocationRelativeTo(this);
		formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// Registrar el listener ANTES de abrir la ventana.
		formulario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				if (formulario.isOperacionEjecutada()) {
					buscarCategorias();
				}
			}
		});
		formulario.setVisible(true);
	}

	private void modificarCategoria() {
		int id = idCategoriaSeleccionada("modificar");
		if (id > 0) {
			abrirFormularioCategoria(Fr_DatosCategoriaDeGasto.OPCION_EDITAR, id);
		}
	}

	/**
	 * La eliminación es lógica: los gastos históricos conservan su categoría.
	 * Sólo se refresca tras confirmación de éxito del procedimiento.
	 */
	private void eliminarCategoria() {
		int id = idCategoriaSeleccionada("eliminar");
		if (id <= 0) {
			return;
		}

		int opcion = JOptionPane.showConfirmDialog(this,
				"¿Desea inhabilitar la categoría de gasto seleccionada?",
				"Inhabilitar categoría", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE);
		if (opcion != JOptionPane.YES_OPTION) {
			return;
		}

		SpResponseModel respuesta = AppContext.categoriaDeGastoController.eliminarCategoria(id);
		if (respuesta == null) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"No se recibió respuesta al inhabilitar la categoría");
			return;
		}
		if (respuesta.id() == 200) {
			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE,
					this, respuesta.message());
			buscarCategorias();
		} else {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
					this, respuesta.message());
		}
	}

	private void exportarListado() {
		try {
			DataTools.exportarTablaExcel(modelTablaCategoriasDeGasto, this);
		} catch (Exception ex) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
					this, "No fue posible iniciar la exportación: " + ex.getMessage());
		}
	}
}
