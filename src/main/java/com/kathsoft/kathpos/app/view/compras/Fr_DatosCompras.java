package com.kathsoft.kathpos.app.view.compras;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

public class Fr_DatosCompras extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralContenedor;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel;
	private JPanel panelDatosCompra;
	private JButton btnGuardar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_DatosCompras frame = new Fr_DatosCompras();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Fr_DatosCompras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(0, 51, 153));
		this.contentPane.add(this.panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		this.lblNewLabel = new JLabel("Compras");
		this.lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		this.lblNewLabel.setForeground(new Color(255, 255, 255));
		this.panelSuperiorEtiqueta.add(this.lblNewLabel);
		
		this.panelCentralContenedor = new JPanel();
		this.contentPane.add(this.panelCentralContenedor, BorderLayout.CENTER);
		
		this.panelDatosCompra = new JPanel();
		this.panelDatosCompra.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_panelCentralContenedor = new GroupLayout(this.panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelDatosCompra, GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addComponent(this.panelDatosCompra, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(383, Short.MAX_VALUE))
		);
		GroupLayout gl_panelDatosCompra = new GroupLayout(this.panelDatosCompra);
		gl_panelDatosCompra.setHorizontalGroup(
			gl_panelDatosCompra.createParallelGroup(Alignment.LEADING)
				.addGap(0, 813, Short.MAX_VALUE)
		);
		gl_panelDatosCompra.setVerticalGroup(
			gl_panelDatosCompra.createParallelGroup(Alignment.LEADING)
				.addGap(0, 137, Short.MAX_VALUE)
		);
		this.panelDatosCompra.setLayout(gl_panelDatosCompra);
		this.panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		
		this.panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.contentPane.add(this.panelInferiorBotones, BorderLayout.SOUTH);
		
		this.btnCancelar = new JButton("Cancelar");
		this.panelInferiorBotones.add(this.btnCancelar);
		
		this.btnGuardar = new JButton("Guardar");
		this.panelInferiorBotones.add(this.btnGuardar);

	}
}
