package com.kathsoft.kathpos.app.view.formas_pago;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
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
import java.awt.GridLayout;
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
	private JPanel panelResumenPago;
	private JScrollPane scrollPaneFormasDePago;
	private FormasDePagoController formasDePagoController = new FormasDePagoController();
	private int[] formasDePagoColumnsWidth = { 40, // id forma de pago
			200, // nombre de la forma de pago
			200 // importe de la forma de apgo
	};
	private JButton btnCancelar;
	private JButton btnRegistrar;
	private JLabel lblNewLabel;
	private JLabel lblTotalVenta;
	private JLabel lblTotalPagado;
	private JLabel lblCambio;
	private JLabel lblTotalVentaValor;
	private JLabel lblTotalPagadoValor;
	private JLabel lblCambioValor;
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

		panelResumenPago = new JPanel(new GridLayout(2, 3, 8, 2));
		panelResumenPago.setBackground(new Color(255, 215, 0));
		panelResumenPago.setBorder(new TitledBorder("Resumen de cobro"));
		panelCentralTabla.add(panelResumenPago, BorderLayout.SOUTH);

		lblTotalVenta = new JLabel("Total venta");
		lblTotalPagado = new JLabel("Total pagado");
		lblCambio = new JLabel("Cambio");
		lblTotalVentaValor = new JLabel("$0.00");
		lblTotalPagadoValor = new JLabel("$0.00");
		lblCambioValor = new JLabel("$0.00");
		lblTotalVentaValor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTotalPagadoValor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCambioValor.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelResumenPago.add(lblTotalVenta);
		panelResumenPago.add(lblTotalPagado);
		panelResumenPago.add(lblCambio);
		panelResumenPago.add(lblTotalVentaValor);
		panelResumenPago.add(lblTotalPagadoValor);
		panelResumenPago.add(lblCambioValor);

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
		this.modelTablaFormasDePago.addTableModelListener(event -> this.actualizarResumenCobro());
		this.actualizarResumenCobro();
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

	private BigDecimal calcularTotalPagado(List<PagoPorVenta> pagos) {
		return pagos.stream().map(pago -> BigDecimal.valueOf(pago.getImporte()))
				.reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
	}

	private BigDecimal obtenerTotalVenta() {
		if (this.ventaConDetalle == null || this.ventaConDetalle.getVenta() == null) {
			return BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);
		}
		return BigDecimal.valueOf(this.ventaConDetalle.getVenta().getImporteTotal()).setScale(2,
				RoundingMode.HALF_UP);
	}

	private void actualizarResumenCobro() {
		BigDecimal totalVenta = this.obtenerTotalVenta();
		this.lblTotalVentaValor.setText(this.formatearImporte(totalVenta));

		try {
			BigDecimal totalPagado = this.calcularTotalPagado(this.formasDePago());
			BigDecimal cambio = totalPagado.compareTo(totalVenta) > 0 ? totalPagado.subtract(totalVenta)
					: BigDecimal.ZERO;
			this.lblTotalPagadoValor.setText(this.formatearImporte(totalPagado));
			this.lblCambioValor.setText(this.formatearImporte(cambio));
		} catch (IllegalArgumentException er) {
			this.lblTotalPagadoValor.setText("Inválido");
			this.lblCambioValor.setText("$0.00");
		}
	}

	private String formatearImporte(BigDecimal importe) {
		BigDecimal valor = importe == null ? BigDecimal.ZERO : importe.setScale(2, RoundingMode.HALF_UP);
		return "$" + valor.toPlainString();
	}

	/**
	 * El importe capturado representa el dinero entregado por el cliente. Cuando
	 * existe sobrepago, el excedente se devuelve como cambio y no debe persistirse
	 * como pago aplicado a la venta. Este método distribuye únicamente el saldo de
	 * la venta entre las formas de pago capturadas, respetando el orden de la tabla.
	 */
	private List<PagoPorVenta> limitarPagosAlTotal(List<PagoPorVenta> pagosCapturados, BigDecimal totalVenta) {
		List<PagoPorVenta> pagosAplicados = new ArrayList<>();
		BigDecimal saldo = totalVenta.setScale(2, RoundingMode.HALF_UP);

		for (PagoPorVenta pago : pagosCapturados) {
			if (saldo.compareTo(BigDecimal.ZERO) <= 0) {
				break;
			}

			BigDecimal importeCapturado = BigDecimal.valueOf(pago.getImporte()).setScale(2, RoundingMode.HALF_UP);
			BigDecimal importeAplicado = importeCapturado.min(saldo).setScale(2, RoundingMode.HALF_UP);
			if (importeAplicado.compareTo(BigDecimal.ZERO) <= 0) {
				continue;
			}

			pagosAplicados.add(new PagoPorVenta.PagoPorVentaBuilder().idFormaPago(pago.getIdFormaPago())
					.importe(importeAplicado.doubleValue()).build());
			saldo = saldo.subtract(importeAplicado).setScale(2, RoundingMode.HALF_UP);
		}

		return pagosAplicados;
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
	 * de pagos genera una venta a crédito. Un pago igual o superior al total liquida
	 * la venta; cualquier excedente se muestra como cambio y no se persiste como pago
	 * aplicado.
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
			List<PagoPorVenta> pagosCapturados = this.formasDePago();
			BigDecimal totalVenta = BigDecimal.valueOf(venta.getImporteTotal()).setScale(2, RoundingMode.HALF_UP);
			BigDecimal totalPagado = this.calcularTotalPagado(pagosCapturados);

			if (totalVenta.compareTo(BigDecimal.ZERO) <= 0) {
				throw new IllegalArgumentException("El total de la venta debe ser mayor a cero");
			}

			BigDecimal cambio = totalPagado.compareTo(totalVenta) > 0 ? totalPagado.subtract(totalVenta)
					: BigDecimal.ZERO;
			List<PagoPorVenta> pagosAplicados = this.limitarPagosAlTotal(pagosCapturados, totalVenta);

			this.ventaConDetalle.setPagos(pagosAplicados);
			SpResponseModel respuesta = this.ventasController.insertVenta(this.ventaConDetalle);

			if (respuesta == null || respuesta.id() <= 0 || this.esRespuestaError(respuesta)) {
				String mensaje = respuesta == null ? "No se recibió respuesta al registrar la venta" : respuesta.message();
				JOptionPane.showMessageDialog(this, mensaje, "No fue posible registrar la venta",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.formVentas.refreshAll();
			String mensaje = respuesta.message() + "\nID de venta: " + respuesta.id();
			if (cambio.compareTo(BigDecimal.ZERO) > 0) {
				mensaje += "\nCambio: " + this.formatearImporte(cambio);
			}
			JOptionPane.showMessageDialog(this, mensaje, "Ventas", JOptionPane.INFORMATION_MESSAGE);
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
		if (respuesta == null) {
			return true;
		}
		if (respuesta.id() != 500) {
			return false;
		}
		if (respuesta.message() == null) {
			return true;
		}
		return !respuesta.message().toLowerCase().contains("correct");
	}

}
