package com.kathsoft.kathpos.app.view.marcas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.model.categoria.Categoria;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

public class Fr_DatosCategoria extends JFrame {
	
	private int idCategoria;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnGuardar;
	private JLabel lblNombre;
	private JTextField txfNombre;
	private JLabel lblDescripcion;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea textAreaDescripcion;
	private boolean operacionEjecutada = false;
	private boolean categoriaActiva = true;

	/**
	 * Create the frame.
	 */
	public Fr_DatosCategoria(int opcion, int idCategoria) {

		this.idCategoria = idCategoria;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 400, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		
		this.lblNombre = new JLabel("Nombre");
		
		this.txfNombre = new JTextField();
		this.txfNombre.setColumns(10);
		
		this.lblDescripcion = new JLabel("Descripcion");
		
		this.scrollPaneDescripcion = new JScrollPane();
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addComponent(this.lblNombre)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.txfNombre, GroupLayout.DEFAULT_SIZE, 327, Short.MAX_VALUE))
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addComponent(this.lblDescripcion)
					.addContainerGap())
				.addComponent(this.scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNombre)
						.addComponent(this.txfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(this.lblDescripcion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 198, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		this.textAreaDescripcion = new JTextArea();
		this.scrollPaneDescripcion.setViewportView(this.textAreaDescripcion);
		panelCentralFormulario.setLayout(gl_panelCentralFormulario);

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		this.panelInferiorBotones.setBackground(new Color(30, 144, 255));

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btnCancelar.setIcon(
				new ImageIcon(Fr_DatosCategoria.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnCancelar.setBackground(new Color(205, 92, 92));
		panelInferiorBotones.add(btnCancelar);

		horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_2);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opcion == 1) {
					insertNuevaCategoria();
				}
				if (opcion == 2) {
					actualizarCategoria();
				}
			}
		});
		btnGuardar.setIcon(new ImageIcon(
				Fr_DatosCategoria.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnGuardar.setBackground(new Color(144, 238, 144));
		panelInferiorBotones.add(btnGuardar);

		if (opcion == 1) {
			this.lblNewLabel.setText("Nueva Categoría");
		}

		if (opcion == 2) {
			this.lblNewLabel.setText("Editar Categoría");
			this.getCategoriaPorId();
		}

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Consulta una categoria de producto por id y mapea el resultado al formulario.
	 */
	private void getCategoriaPorId() {
		if (this.idCategoria <= 0) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, "No se recibio una categoria valida");
			return;
		}

		Categoria categoria = AppContext.categoriaController.buscarCategoriaPorId(this.idCategoria);

		if (categoria == null) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, "No se pudo cargar la categoria");
			return;
		}

		this.idCategoria = categoria.getIdCategoria();
		this.categoriaActiva = categoria.isActivo();
		this.txfNombre.setText(this.valueOrEmpty(categoria.getNombre()));
		this.textAreaDescripcion.setText(this.valueOrEmpty(categoria.getDescripcion()));
	}

	/**
	 * Valida los campos obligatorios del formulario.
	 *
	 * @return {@code true} si hay errores de validacion; {@code false} si el formulario es valido
	 */
	private boolean validarCamposVacios() {
		String nombre = this.txfNombre.getText() == null ? "" : this.txfNombre.getText().trim();

		if (nombre.isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "El nombre de la categoria es obligatorio");
			return true;
		}

		return false;
	}
	
	/**
	 * Registra una nueva categoria de producto.
	 */
	private void insertNuevaCategoria() {
		if (this.validarCamposVacios()) {
			return;
		}

		var response = AppContext.categoriaController.insertarNuevaCategoria(this.buildCategoria());

		MessageHandler.displayMessage(
				response.id() == 200 ? MessageHandler.INSERT_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE,
				this,
				response.message()
		);

		if (response.id() == 200) {
			this.operacionEjecutada = true;
			this.cerrarForm();
		}
	}

	/**
	 * Actualiza la categoria de producto cargada en el formulario.
	 */
	private void actualizarCategoria() {
		if (this.validarCamposVacios()) {
			return;
		}

		var response = AppContext.categoriaController.actualizarCategoria(this.buildCategoria());

		MessageHandler.displayMessage(
				response.id() == 200 ? MessageHandler.UPDATE_SUCCESS_MESSAGE : MessageHandler.ERROR_MESSAGE,
				this,
				response.message()
		);

		if (response.id() == 200) {
			this.operacionEjecutada = true;
			this.cerrarForm();
		}
	}

	/**
	 * Construye la entidad {@link Categoria} con los datos capturados en el formulario.
	 *
	 * @return categoria construida para alta o actualizacion
	 */
	private Categoria buildCategoria() {
		var categoria = new Categoria();
		categoria.setIdCategoria(this.idCategoria);
		categoria.setNombre(this.txfNombre.getText().trim());
		categoria.setDescripcion(this.textAreaDescripcion.getText().trim());
		categoria.setActivo(this.categoriaActiva);
		return categoria;
	}

	/**
	 * Indica si el formulario ejecuto una operacion de base de datos correctamente.
	 *
	 * @return {@code true} si la operacion fue exitosa; {@code false} en caso contrario
	 */
	public boolean isOperacionEjecutada() {
		return this.operacionEjecutada;
	}

	private String valueOrEmpty(String value) {
		return value == null ? "" : value;
	}

	private void cerrarForm() {
		this.dispose();
	}
}
