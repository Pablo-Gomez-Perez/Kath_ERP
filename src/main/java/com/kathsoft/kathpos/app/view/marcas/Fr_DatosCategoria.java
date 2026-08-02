package com.kathsoft.kathpos.app.view.marcas;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.model.categoria.Categoria;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

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

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_DatosCategoria frame = new
	 * Fr_DatosCategoria(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

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

	}

	private void getCategoriaPorId() {

		

	}

	private boolean validarCamposVacios() {
		
		
		
		return false;
	}
	

	private void insertNuevaCategoria() {


		
	}

	private void actualizarCategoria() {

	}

	private void cerrarForm() {
		this.dispose();
	}
}
