package com.kathsoft.kathpos.app.view.formas_pago;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.kathsoft.kathpos.app.controller.FormasDePagoController;
import com.kathsoft.kathpos.app.controller.VentasController;
import com.kathsoft.kathpos.app.model.venta.PagoPorVenta;
import com.kathsoft.kathpos.app.model.venta.Venta;
import com.kathsoft.kathpos.app.model.venta.VentaConDetalle;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
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
	private VentaConDetalle ventaConDetalle;
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
	public Fr_FormasDePago(VentaConDetalle ventaConDetalle, Fr_PuntoDeVentas formVentas) {

		this.formVentas = formVentas;
		this.ventaConDetalle = ventaConDetalle;

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
		btnCancelar.addActionListener(e -> this.dispose());
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
	}

	private void llenarTablaFormasDePago() {
		this.modelTablaFormasDePago.getDataVector().removeAllElements();
		this.tablaFormasDePago.updateUI();
		this.formasDePagoController.verFormasDePagoEnTablaVentas().forEach(data -> {
			this.modelTablaFormasDePago.addRow(data);
		});
		;
	}

	private List<PagoPorVenta> formasDePago() {
		List<PagoPorVenta> pagos = new ArrayList<>();
		DefaultTableModel model = (DefaultTableModel) this.tablaFormasDePago.getModel();

		for (int fila = 0; fila < model.getRowCount(); fila++) {
			Object valorImporte = model.getValueAt(fila, 2);
			if (valorImporte == null || String.valueOf(valorImporte).trim().isEmpty()) {
				continue;
			}

			BigDecimal importe;
			try {
				importe = new BigDecimal(String.valueOf(valorImporte).trim()).setScale(2, RoundingMode.HALF_UP);
			} catch (NumberFormatException er) {
				throw new IllegalArgumentException("El importe de la forma de pago en la fila " + (fila + 1)
						+ " no es válido");
			}

			if (importe.compareTo(BigDecimal.ZERO) < 0) {
				throw new IllegalArgumentException("Los importes de pago no pueden ser negativos");
			}
			if (importe.compareTo(BigDecimal.ZERO) == 0) {
				continue;
			}

			Object valorIdFormaPago = model.getValueAt(fila, 0);
			int idFormaPago;
			try {
				idFormaPago = valorIdFormaPago instanceof Number numero ? numero.intValue()
						: Integer.parseInt(String.valueOf(valorIdFormaPago).trim());
			} catch (Exception er) {
				throw new IllegalArgumentException("No fue posible identificar la forma de pago de la fila " + (fila + 1));
			}
			if (idFormaPago <= 0) {
				throw new IllegalArgumentException("La forma de pago de la fila " + (fila + 1) + " no es válida");
			}

			pagos.add(new PagoPorVenta.PagoPorVentaBuilder().idFormaPago(idFormaPago).importe(importe.doubleValue())
					.build());
		}
		return pagos;
	}

	private boolean detenerEdicionPago() {
		if (this.tablaFormasDePago.isEditing() && this.tablaFormasDePago.getCellEditor() != null
				&& !this.tablaFormasDePago.getCellEditor().stopCellEditing()) {
			JOptionPane.showMessageDialog(this, "No fue posible confirmar el importe capturado", "Importe inválido",
					JOptionPane.WARNING_MESSAGE);
			return false;
		}
		return true;
	}

	/**
	 * Registra la venta completa en una sola transacción: cabecera, artículos,
	 * afectación de existencias, pagos y finalización. Un pago parcial o la ausencia
	 * de pagos genera una venta a crédito; únicamente el pago exacto genera una venta
	 * de contado.
	 */
	private void finalizarVenta() {
		if (!this.detenerEdicionPago()) {
			return;
		}

		if (this.ventaConDetalle == null || this.ventaConDetalle.getVenta() == null
				|| this.ventaConDetalle.getArticulos() == null || this.ventaConDetalle.getArticulos().isEmpty()) {
			JOptionPane.showMessageDialog(this, "No existe una venta completa para procesar", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {
			Venta venta = this.ventaConDetalle.getVenta();
			List<PagoPorVenta> pagos = this.formasDePago();
			BigDecimal totalVenta = BigDecimal.valueOf(venta.getImporteTotal()).setScale(2, RoundingMode.HALF_UP);
			BigDecimal totalPagado = pagos.stream().map(pago -> BigDecimal.valueOf(pago.getImporte()))
					.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);

			if (totalVenta.compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException("El total de la venta debe ser mayor a cero");
			}
			if (totalPagado.compareTo(totalVenta) > 0) {
				throw new IllegalArgumentException("La suma de los pagos no puede superar el importe total de la venta");
			}

			this.ventaConDetalle.setPagos(pagos);
			SpResponseModel respuesta = this.ventasController.insertVenta(this.ventaConDetalle);

			if (respuesta == null || respuesta.id() <= 0 || this.esRespuestaError(respuesta)) {
				String mensaje = respuesta == null ? "No se recibió respuesta al registrar la venta" : respuesta.message();
				JOptionPane.showMessageDialog(this, mensaje, "No fue posible registrar la venta",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.formVentas.refreshAll();
			JOptionPane.showMessageDialog(this, respuesta.message() + "\nID de venta: " + respuesta.id(), "Ventas",
					JOptionPane.INFORMATION_MESSAGE);
			this.dispose();
		} catch (IllegalArgumentException er) {
			JOptionPane.showMessageDialog(this, er.getMessage(), "Datos de cobro inválidos",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception er) {
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error al registrar la venta: " + er.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace(System.err);
		}
	}

	private boolean esRespuestaError(SpResponseModel respuesta) {
		if (respuesta == null || respuesta.message() == null) {
			return respuesta == null;
		}
		String mensaje = respuesta.message().trim().toLowerCase();
		return respuesta.id() == 500 && (mensaje.startsWith("error") || mensaje.contains("no fue posible"));
	}

}
