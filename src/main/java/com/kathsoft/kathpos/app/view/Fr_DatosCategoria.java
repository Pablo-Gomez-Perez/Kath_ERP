package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.model.Categoria;

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

public class Fr_DatosCategoria extends JFrame {

	private CategoriaController categoriaController = new CategoriaController();
	private int idCategoria;
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JTextField txfIdMarca;
	private Component verticalStrut_1;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_2;
	private Component horizontalStrut_1;
	private JTextField txfNombreMarca;
	private Component verticalStrut_2;
	private Box verticalBox;
	private JPanel panel;
	private JLabel lblNewLabel_3;
	private Box horizontalBox_2;
	private JTextArea txaDescripcion;
	private Component verticalStrut_3;
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private Component horizontalStrut_2;
	private JButton btnGuardar;

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
		this.setBounds(100, 100, 581, 356);
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
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));

		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);

		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("Id Marca");
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		txfIdMarca = new JTextField();
		txfIdMarca.setEnabled(false);
		txfIdMarca.setEditable(false);
		horizontalBox.add(txfIdMarca);
		txfIdMarca.setColumns(50);
		this.txfIdMarca.setMaximumSize(this.txfIdMarca.getPreferredSize());

		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);

		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);

		lblNewLabel_2 = new JLabel("Nombre");
		horizontalBox_1.add(lblNewLabel_2);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);

		txfNombreMarca = new JTextField();
		horizontalBox_1.add(txfNombreMarca);
		txfNombreMarca.setColumns(50);
		this.txfNombreMarca.setMaximumSize(this.txfNombreMarca.getPreferredSize());

		verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);

		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(255, 215, 0));
		verticalBox.add(panel);

		lblNewLabel_3 = new JLabel("Descripción");
		panel.add(lblNewLabel_3);

		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		txaDescripcion = new JTextArea();
		txaDescripcion.setLineWrap(true);
		horizontalBox_2.add(txaDescripcion);

		verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);

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
				new ImageIcon(Fr_DatosCategoria.class.getResource("/com/kathsoft/kathpos/app/resources/nwCancel.png")));
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
				Fr_DatosCategoria.class.getResource("/com/kathsoft/kathpos/app/resources/agregar_ico.png")));
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

		var categoria = this.categoriaController.buscarCategoriaPorId(this.idCategoria);

		this.txfIdMarca.setText(String.valueOf(categoria.getIdCategoria()));
		this.txfNombreMarca.setText(categoria.getNombre());
		this.txaDescripcion.append(categoria.getDescripcion());

	}

	private boolean validarCamposVacios() {
		if (this.txfNombreMarca.getText().isEmpty() || this.txfNombreMarca.getText().length() < 3
				|| this.txfNombreMarca.getText().isBlank()) {
			return true;
		}
		if (this.txaDescripcion.getText().isEmpty() || this.txaDescripcion.getText().length() < 4
				|| this.txaDescripcion.getText().isBlank()) {
			return true;
		}
		return false;
	}

	private void borrarCampos() {
		this.txfNombreMarca.setText("");
		this.txaDescripcion.setText("");
	}

	private void insertNuevaCategoria() {
		if (this.validarCamposVacios()) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			var categoria = new Categoria();

			categoria.setIdCategoria(0);
			categoria.setNombre(this.txfNombreMarca.getText());
			categoria.setDescripcion(this.txaDescripcion.getText());

			this.categoriaController.insertarNuevaCategoria(categoria);

			JOptionPane.showMessageDialog(this, "Categoria almacenada", "Exito", JOptionPane.INFORMATION_MESSAGE);

			this.borrarCampos();			

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void actualizarCategoria() {
		if (this.validarCamposVacios()) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			var categoria = new Categoria();

			categoria.setIdCategoria(Integer.parseInt(this.txfIdMarca.getText()));
			categoria.setNombre(this.txfNombreMarca.getText());
			categoria.setDescripcion(this.txaDescripcion.getText());

			this.categoriaController.actualizarCategoria(categoria);

			JOptionPane.showMessageDialog(this, "Categoria almacenada", "Exito", JOptionPane.INFORMATION_MESSAGE);
			
			this.cerrarForm();

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error" + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cerrarForm() {
		this.dispose();
	}

}
