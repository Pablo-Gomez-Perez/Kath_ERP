package com.kathsoft.kathpos.app.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.Ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

public class Fr_FormasDePago extends JFrame {

	private static final long serialVersionUID = 1L;
	private DefaultTableModel modelTablaFormasDePago = new DefaultTableModel();
	private JPanel contentPane;
	private JTable tablaFormasDePago;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelInferiorBotones;
	private JPanel panelCentralTabla;
	private JScrollPane scrollPaneFormasDePago;
	private FormasDePagoController formasDePagoController = new FormasDePagoController();
	private int[] formasDePagoColumnsWidth = {
		40, //id forma de pago
		200, //nombre de la forma de pago
		200 //importe de la forma de apgo
	};
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_FormasDePago frame = new Fr_FormasDePago();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Fr_FormasDePago(Ventas venta, List<ArticulosPorVentas> articulos) {
		setTitle("Forma de pago");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 402);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		panelSuperiorEtiqueta.setForeground(new Color(0, 0, 0));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Forma de pago");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 224));
		panelSuperiorEtiqueta.add(lblNewLabel);
		
		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		btnCancelar = new JButton("New button");
		btnCancelar.setBackground(new Color(255, 51, 51));
		panelInferiorBotones.add(btnCancelar);
		
		btnRegistrar = new JButton("New button");
		panelInferiorBotones.add(btnRegistrar);
		
		panelCentralTabla = new JPanel();
		panelCentralTabla.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralTabla, BorderLayout.CENTER);
		panelCentralTabla.setLayout(new BorderLayout(0, 0));
		
		scrollPaneFormasDePago = new JScrollPane();
		panelCentralTabla.add(scrollPaneFormasDePago, BorderLayout.CENTER);
		
		modelTablaFormasDePago.addColumn("Id");
		modelTablaFormasDePago.addColumn("Forma de pago");
		modelTablaFormasDePago.addColumn("Importe $");
		
		tablaFormasDePago = new JTable();
		tablaFormasDePago.setModel(modelTablaFormasDePago);
		scrollPaneFormasDePago.setViewportView(tablaFormasDePago);
		
		TableColumnModel formasDePagoColumnModel = tablaFormasDePago.getColumnModel();
		for (int i = 0; i < formasDePagoColumnsWidth.length; i++) {
			formasDePagoColumnModel.getColumn(i).setPreferredWidth(formasDePagoColumnsWidth[i]);
			formasDePagoColumnModel.getColumn(i).setMinWidth(formasDePagoColumnsWidth[i]);
		}
		
		this.llenarTablaFormasDePago();
	}
	
	private void llenarTablaFormasDePago() {
		this.modelTablaFormasDePago.getDataVector().removeAllElements();
		this.tablaFormasDePago.updateUI();
		this.formasDePagoController.verFormasDePagoEnTablaVentas(modelTablaFormasDePago);
	}

}
