package com.kathsoft.kathpos.app.view.formas_pago;

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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JCheckBox;

public class Fr_DatosFormaDePago extends JFrame {

	private FormasDePagoController formaDePagoController = new FormasDePagoController();
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JButton btn_cancelar;
	private JButton btnAgregar;
	private JLabel lblNombre;
	private JTextField txfNombreFormaDePago;
	private JCheckBox chckbxEsFlujoDeEfectivo;

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
		setBounds(100, 100, 400, 200);
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
		
		this.lblNombre = new JLabel("Nombre");
		
		this.txfNombreFormaDePago = new JTextField();
		this.txfNombreFormaDePago.setColumns(10);
		
		this.chckbxEsFlujoDeEfectivo = new JCheckBox("Es base de flujo de efectivo");
		this.chckbxEsFlujoDeEfectivo.setBackground(new Color(255, 215, 0));
		this.chckbxEsFlujoDeEfectivo.setToolTipText("Selecciona únicamente si la forma de pago es de flujo de efectivo o equivalente");
		GroupLayout gl_panelCentralFormulario = new GroupLayout(this.panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addComponent(this.chckbxEsFlujoDeEfectivo)
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfNombreFormaDePago, GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNombre)
						.addComponent(this.txfNombreFormaDePago, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.chckbxEsFlujoDeEfectivo)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		this.panelCentralFormulario.setLayout(gl_panelCentralFormulario);

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
		this.txfNombreFormaDePago.setText(fpago.getTipoDePago());
		this.chckbxEsFlujoDeEfectivo.setSelected(fpago.isEsFlujoEfectivo());

	}

	private void insertarFormaDePago() {

		FormasDePago fpago = new FormasDePago();
		fpago.setTipoDePago(this.txfNombreFormaDePago.getText());
		fpago.setEsFlujoEfectivo(this.chckbxEsFlujoDeEfectivo.isSelected());
		this.formaDePagoController.insertarFormaDePago(fpago);
		JOptionPane.showMessageDialog(this, "Registro Agregado", "F pagos", JOptionPane.INFORMATION_MESSAGE);

	}

	private void actualizarFormaDePago(int id) {

		FormasDePago fpago = new FormasDePago();
		fpago.setId(id);
		fpago.setTipoDePago(this.txfNombreFormaDePago.getText());
		fpago.setEsFlujoEfectivo(this.chckbxEsFlujoDeEfectivo.isSelected());
		this.formaDePagoController.actualizarFormaDePago(fpago);
		JOptionPane.showMessageDialog(this, "Registro actualizado", "F pagos", JOptionPane.INFORMATION_MESSAGE);
		cerrarForm();

	}

	private void cerrarForm() {
		this.dispose();
	}
}
