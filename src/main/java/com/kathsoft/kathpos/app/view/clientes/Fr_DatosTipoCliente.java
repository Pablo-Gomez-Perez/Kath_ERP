package com.kathsoft.kathpos.app.view.clientes;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.TipoClienteController;
import com.kathsoft.kathpos.app.model.TipoCliente;
import com.kathsoft.kathpos.tools.MessageHandler;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fr_DatosTipoCliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * 
	 * 
	 */
	private int tipoOperacion;
	private int idTipoCliente;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private Box verticalBox;
	private JPanel panelInferiorBotones;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JTextField txfNombre;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_2;
	private Component horizontalStrut_1;
	private JTextField txfDescripcion;
	private Component verticalStrut_1;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private TipoClienteController tipoClienteController = new TipoClienteController();

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_DatosTipoCliente frame = new
	 * Fr_DatosTipoCliente(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_DatosTipoCliente(int tipoOperacion, int idTipoCliente) {

		this.tipoOperacion = tipoOperacion;
		this.idTipoCliente = idTipoCliente;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 506, 200);
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

		if (this.tipoOperacion == 1) {
			this.lblNewLabel.setText("Nueva Categoría");
		} else {
			this.lblNewLabel.setText("Editar Categoría");
		}

		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.X_AXIS));

		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		lblNewLabel_1 = new JLabel("Nombre");
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		txfNombre = new JTextField();
		horizontalBox.add(txfNombre);
		txfNombre.setColumns(50);
		txfNombre.setMaximumSize(this.txfNombre.getPreferredSize());

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		lblNewLabel_2 = new JLabel("Descripción");
		horizontalBox_1.add(lblNewLabel_2);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);

		txfDescripcion = new JTextField();
		horizontalBox_1.add(txfDescripcion);
		txfDescripcion.setColumns(50);
		this.txfDescripcion.setMaximumSize(this.txfDescripcion.getPreferredSize());

		verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btnCancelar.setBackground(new Color(255, 51, 0));
		btnCancelar.setIcon(new ImageIcon(
				Fr_DatosTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		panelInferiorBotones.add(btnCancelar);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tipoOperacion == 1) {
					insertarNuevoTipoCliente();
				} else {
					actualizarTipoCliente();
				}
			}
		});
		btnAgregar.setIcon(new ImageIcon(
				Fr_DatosTipoCliente.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(151, 252, 151));
		panelInferiorBotones.add(btnAgregar);

		if (tipoOperacion != 1) {
			this.buscarTipoClientePorid();
		}

	}

	private void insertarNuevoTipoCliente() {

		if (this.validarCamposVacios()) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, "No deje campos vacios");
			return;
		}

		var data = new TipoCliente();

		data.setNombre(this.txfNombre.getText());
		data.setDescripcion(this.txfDescripcion.getText());

		this.tipoClienteController.insertarNuevoTipoCliente(data);

		MessageHandler.displayMessage(MessageHandler.INSERT_SUCCESS_MESSAGE, this, "");
		
		this.limpiarCampos();

	}

	private void buscarTipoClientePorid() {

		var data = this.tipoClienteController.buscarPorId(this.idTipoCliente);

		this.txfNombre.setText(data.getNombre());
		this.txfDescripcion.setText(data.getDescripcion());

	}
	
	private void limpiarCampos() {
		this.txfNombre.setText("");
		this.txfDescripcion.setText("");
	}

	private void actualizarTipoCliente() {

		if (this.validarCamposVacios()) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, "No deje campos vacios");
			return;
		}

		var data = new TipoCliente();

		data.setId_tipoCliente(this.idTipoCliente);
		data.setNombre(this.txfNombre.getText());
		data.setDescripcion(this.txfDescripcion.getText());

		this.tipoClienteController.actualizarTipoCliente(data);

		MessageHandler.displayMessage(MessageHandler.UPDATE_SUCCESS_MESSAGE, this, "");

	}

	private boolean validarCamposVacios() {
		return this.txfNombre.getText().isBlank() || this.txfDescripcion.getText().isBlank() ? true : false;
	}

	private void cerrarForm() {
		this.dispose();
	}

}
