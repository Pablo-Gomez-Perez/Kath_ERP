package com.kathsoft.kathpos.app.view.formas_pago;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.controller.VentasController;
import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.PagosPorVenta;
import com.kathsoft.kathpos.app.model.Ventas;
import com.kathsoft.kathpos.app.view.ventas.Fr_PuntoDeVentas;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fr_FormasDePago extends JFrame {

	private static final long serialVersionUID = 1L;
	private VentasController ventasController = new VentasController();
	private DefaultTableModel modelTablaFormasDePago = new DefaultTableModel();
	private JPanel contentPane;
	private JTable tablaFormasDePago;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelInferiorBotones;
	private JPanel panelCentralTabla;
	private JScrollPane scrollPaneFormasDePago;
	private FormasDePagoController formasDePagoController = new FormasDePagoController();
	private int[] formasDePagoColumnsWidth = { 40, // id forma de pago
			200, // nombre de la forma de pago
			200 // importe de la forma de apgo
	};
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JLabel lblNewLabel;
	private List<ArticulosPorVentas> articulos;
	private List<PagosPorVenta> fpagos;
	private Ventas venta;
	private Fr_PuntoDeVentas formVentas;

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_FormasDePago frame = new
	 * Fr_FormasDePago(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_FormasDePago(Ventas venta, List<ArticulosPorVentas> articulos, Fr_PuntoDeVentas formVentas) {

		this.formVentas = formVentas;

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

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(255, 51, 51));
		panelInferiorBotones.add(btnCancelar);

		btnRegistrar = new JButton("Cobrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarVenta();
			}
		});
		btnRegistrar.setBackground(new Color(204, 255, 51));
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

		this.articulos = articulos;
		this.venta = venta;
	}

	private void llenarTablaFormasDePago() {
		this.modelTablaFormasDePago.getDataVector().removeAllElements();
		this.tablaFormasDePago.updateUI();
		this.formasDePagoController.verFormasDePagoEnTablaVentas().forEach(data -> {
			this.modelTablaFormasDePago.addRow(data);
		});
		;
	}

	private List<PagosPorVenta> formasDePago() {
		var fpagosList = new ArrayList<PagosPorVenta>();
		var model = (DefaultTableModel) this.tablaFormasDePago.getModel();

		try {

			for (int i = 0; i < model.getRowCount(); i++) {
				if ((model.getValueAt(i, 2)) != null) {

					var formasPagoReg = PagosPorVenta.builder().idVenta(this.venta.getIdVenta())
							.idFormaDePago((int) model.getValueAt(i, 0))
							.importe(Double.parseDouble(model.getValueAt(i, 2).toString())).build();

					fpagosList.add(formasPagoReg);

				}
			}

			return fpagosList;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		}

	}

	/**
	 * realiza escencialmente tres operaciones 1. registra los datos de la venta. 2.
	 * registra los artículos que fueron vendidos 3. registra la forma en la que la
	 * venta fué efectivamente pagada
	 */
	private void finalizarVenta() {

		if (this.venta == null || this.articulos == null) {
			JOptionPane.showMessageDialog(this, "Error al procesar venta", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			this.fpagos = this.formasDePago();

			double totalVenta = fpagos.stream().mapToDouble(PagosPorVenta::getImporte).sum();

			if (totalVenta < this.venta.getTotal()) {
				JOptionPane.showMessageDialog(this, "El importe no puede ser menor a la venta", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			double cambio = totalVenta - this.venta.getTotal();

			// se registran los datos de la venta
			this.ventasController.insertarNuevaVenta(venta);

			this.formVentas.refreshAll();

			JOptionPane.showMessageDialog(this, "Venta registrada, Gracias por su compra, su cambio es $" + cambio,
					"Ventas", JOptionPane.INFORMATION_MESSAGE);

			this.dispose();
		} catch (Exception er) {
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
		}
	}

}
