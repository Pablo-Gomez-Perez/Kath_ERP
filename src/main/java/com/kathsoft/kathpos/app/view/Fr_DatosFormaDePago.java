package com.kathsoft.kathpos.app.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.model.FormasDePago;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fr_DatosFormaDePago extends JFrame {

	private FormasDePagoController formaDePagoController = new FormasDePagoController();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JTextField txf_formaDePago;
	private JButton btn_cancelar;
	private JButton btnAgregar;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_DatosFormaDePago frame = new
	 * Fr_DatosFormaDePago(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_DatosFormaDePago(int opcion, int idFormaDePago) {
		setTitle("Forma De Pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 150);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));

		if (opcion == 1) {
			this.lblNewLabel.setText("Agregar Forma De Pago");
		} else if (opcion == 2) {
			this.lblNewLabel.setText("Editar Forma De Pago");
		}

		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);

		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("Forma de pago");
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		txf_formaDePago = new JTextField();
		horizontalBox.add(txf_formaDePago);
		txf_formaDePago.setColumns(30);
		this.txf_formaDePago.setMaximumSize(this.txf_formaDePago.getPreferredSize());

		if (opcion == 2) {
			this.consultarFormaPago(idFormaDePago);
		}

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btn_cancelar = new JButton("Cancelar");
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btn_cancelar.setIcon(new ImageIcon(
				Fr_DatosFormaDePago.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		panelInferiorBotones.add(btn_cancelar);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opcion == 1) {
					insertarFormaDePago();
				} else if (opcion == 2) {
					actualizarFormaDePago(idFormaDePago);
				}
			}
		});
		btnAgregar.setIcon(new ImageIcon(
				Fr_DatosFormaDePago.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelInferiorBotones.add(btnAgregar);
		
		
	}

	private void consultarFormaPago(int id) {

		FormasDePago fpago = this.formaDePagoController.consultarFormaDePagoPorId(id);
		this.txf_formaDePago.setText(fpago.getTipoDePago());

	}

	private void insertarFormaDePago() {

		FormasDePago fpago = new FormasDePago();
		fpago.setTipoDePago(this.txf_formaDePago.getText());
		this.formaDePagoController.insertarFormaDePago(fpago);
		JOptionPane.showMessageDialog(this, "Registro Agregado", "F pagos", JOptionPane.INFORMATION_MESSAGE);

	}

	private void actualizarFormaDePago(int id) {

		FormasDePago fpago = new FormasDePago();
		fpago.setId(id);
		fpago.setTipoDePago(this.txf_formaDePago.getText());
		this.formaDePagoController.actualizarFormaDePago(fpago);
		JOptionPane.showMessageDialog(this, "Registro actualizado", "F pagos", JOptionPane.INFORMATION_MESSAGE);
		cerrarForm();

	}

	private void cerrarForm() {
		this.dispose();
	}

}
