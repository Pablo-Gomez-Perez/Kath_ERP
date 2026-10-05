package com.kathsoft.kathpos.app.view.reportes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.reporte.VentaTotalPorFecha;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class Fr_ReporteVentasTotales extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter FORMATO_FECHA = new DateTimeFormatterBuilder()
			.appendPattern("dd/MM/uuuu")
			.toFormatter(Locale.ROOT)
			.withResolverStyle(ResolverStyle.STRICT);

	private final long idSucursal;
	private JPanel contentPane;
	private JMenuBar menuBarPrincipal;
	private JMenu mnArchivo;
	private JMenuItem mntmImprimir;
	private JMenuItem mntmGenerarPdf;
	private JMenuItem mntmGenerarTxt;
	private JMenuItem mntmVerEnExcelcsv;
	private JPanel panelPrincipal;
	private JPanel panelSuperiorTitulo;
	private JLabel lblReporteDeVentas;
	private JLabel lblDesde;
	private JFormattedTextField formattedTextFieldFechaInicio;
	private JLabel lblHasta;
	private JFormattedTextField formattedTextFieldFechaFinal;
	private JButton buttonBuscar;
	private JScrollPane scrollPaneVentasDelPeriodo;
	private JPanel panelInferiorTotales;
	private JLabel lblVentaTotal;
	private JTextField textFieldVentasTotales;
	private JLabel lblIvaCobrado;
	private JTextField textField;
	private JTable tableVentasTotales;
	private DefaultTableModel modelTablaVentasTotales;
	private LocalDate fechaInicioReporte;
	private LocalDate fechaFinReporte;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_ReporteVentasTotales frame = new Fr_ReporteVentasTotales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Constructor sin contexto operativo para conservar compatibilidad con
	 * WindowBuilder y la ejecución aislada del formulario.
	 */
	public Fr_ReporteVentasTotales() {
		this(0L);
	}

	/**
	 * Create the frame.
	 *
	 * @param idSucursal sucursal autenticada desde la que se genera el reporte
	 */
	public Fr_ReporteVentasTotales(long idSucursal) {
		this.idSucursal = idSucursal;
		initComponents();
		inicializarReporte();
	}

	private void initComponents() {
		setBackground(new Color(255, 215, 0));
		setTitle("Reporte de ventas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		
		this.menuBarPrincipal = new JMenuBar();
		setJMenuBar(this.menuBarPrincipal);
		
		this.mnArchivo = new JMenu("Archivo");
		this.mnArchivo.setIcon(new ImageIcon(Fr_ReporteVentasTotales.class.getResource("/com/kathsoft/kathpos/app/assets/folder.png")));
		this.menuBarPrincipal.add(this.mnArchivo);
		
		this.mntmImprimir = new JMenuItem("Imprimir");
		this.mntmImprimir.setEnabled(false);
		this.mnArchivo.add(this.mntmImprimir);
		
		this.mntmGenerarPdf = new JMenuItem("Generar PDF");
		this.mntmGenerarPdf.setIcon(new ImageIcon(Fr_ReporteVentasTotales.class.getResource("/com/kathsoft/kathpos/app/assets/pdfLogo.jpg")));
		this.mnArchivo.add(this.mntmGenerarPdf);
		
		this.mntmGenerarTxt = new JMenuItem("Generar TXT");
		this.mntmGenerarTxt.setIcon(new ImageIcon(Fr_ReporteVentasTotales.class.getResource("/com/kathsoft/kathpos/app/assets/txt.png")));
		this.mnArchivo.add(this.mntmGenerarTxt);
		
		this.mntmVerEnExcelcsv = new JMenuItem("Ver en excel(CSV)");
		this.mntmVerEnExcelcsv.setIcon(new ImageIcon(Fr_ReporteVentasTotales.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.mnArchivo.add(this.mntmVerEnExcelcsv);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 215, 0));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(255, 215, 0));
		this.contentPane.add(this.panelPrincipal, BorderLayout.CENTER);
		
		this.lblDesde = new JLabel("Desde");
		
		this.formattedTextFieldFechaInicio = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaInicio.setToolTipText("dd/MM/yyyy");
		
		this.lblHasta = new JLabel("Hasta");
		
		this.formattedTextFieldFechaFinal = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaFinal.setToolTipText("dd/MM/yyyy");
		
		this.buttonBuscar = new JButton("");
		this.buttonBuscar.setIcon(new ImageIcon(Fr_ReporteVentasTotales.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		
		this.scrollPaneVentasDelPeriodo = new JScrollPane();
		
		this.panelInferiorTotales = new JPanel();
		this.panelInferiorTotales.setBackground(new Color(0, 191, 255));
		GroupLayout gl_panelPrincipal = new GroupLayout(this.panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panelPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(this.scrollPaneVentasDelPeriodo, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelPrincipal.createSequentialGroup()
							.addContainerGap()
							.addComponent(this.panelInferiorTotales, GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelPrincipal.createSequentialGroup()
							.addComponent(this.lblDesde)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaInicio, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblHasta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaFinal, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.buttonBuscar)))
					.addContainerGap())
		);
		gl_panelPrincipal.setVerticalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.buttonBuscar)
						.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.BASELINE)
							.addComponent(this.lblDesde)
							.addComponent(this.formattedTextFieldFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(this.formattedTextFieldFechaFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(this.lblHasta)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.scrollPaneVentasDelPeriodo, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelInferiorTotales, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		this.tableVentasTotales = new JTable();
		this.scrollPaneVentasDelPeriodo.setViewportView(this.tableVentasTotales);
		
		this.lblVentaTotal = new JLabel("Ventas Totales");
		
		this.textFieldVentasTotales = new JTextField();
		this.textFieldVentasTotales.setEnabled(false);
		this.textFieldVentasTotales.setColumns(10);
		
		this.lblIvaCobrado = new JLabel("I.V.A cobrado");
		
		this.textField = new JTextField();
		this.textField.setEnabled(false);
		this.textField.setColumns(10);
		GroupLayout gl_panelInferiorTotales = new GroupLayout(this.panelInferiorTotales);
		gl_panelInferiorTotales.setHorizontalGroup(
			gl_panelInferiorTotales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorTotales.createSequentialGroup()
					.addContainerGap()
					.addComponent(this.lblVentaTotal)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.textFieldVentasTotales)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblIvaCobrado)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.textField, GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panelInferiorTotales.setVerticalGroup(
			gl_panelInferiorTotales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorTotales.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorTotales.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblVentaTotal)
						.addComponent(this.textFieldVentasTotales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblIvaCobrado)
						.addComponent(this.textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		this.panelInferiorTotales.setLayout(gl_panelInferiorTotales);
		this.panelPrincipal.setLayout(gl_panelPrincipal);
		
		this.panelSuperiorTitulo = new JPanel();
		this.panelSuperiorTitulo.setBackground(new Color(25, 25, 112));
		this.contentPane.add(this.panelSuperiorTitulo, BorderLayout.NORTH);
		
		this.lblReporteDeVentas = new JLabel("Reporte de Ventas Totales");
		this.lblReporteDeVentas.setFont(new Font("Dialog", Font.BOLD, 14));
		this.lblReporteDeVentas.setForeground(new Color(255, 255, 255));
		this.panelSuperiorTitulo.add(this.lblReporteDeVentas);
	}

	private void inicializarReporte() {
		this.modelTablaVentasTotales = new DefaultTableModel(
				new Object[] { "Fecha", "Número de ventas", "Sub total", "IVA", "Total" }, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		this.tableVentasTotales.setModel(this.modelTablaVentasTotales);
		DataTools.removerEditorDeTabla(this.tableVentasTotales, this.modelTablaVentasTotales);
		this.buttonBuscar.addActionListener(e -> this.consultarVentasTotales());
		this.mntmVerEnExcelcsv.addActionListener(e -> this.exportarTablaCsv());
		this.mntmGenerarTxt.addActionListener(e -> this.exportarReporteTxt());
	}

	private void exportarTablaCsv() {
		try {
			DataTools.exportarJTableCsv(this.tableVentasTotales, this);
		} catch (IllegalArgumentException ex) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"No fue posible exportar el reporte CSV: " + ex.getMessage());
		}
	}

	private void exportarReporteTxt() {
		if (this.tableVentasTotales.getRowCount() == 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"No existen datos a exportar");
			return;
		}

		if (this.fechaInicioReporte == null || this.fechaFinReporte == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"Debe realizar una consulta válida antes de exportar el reporte");
			return;
		}

		String encabezado = "Reporte de ventas totales de "
				+ this.fechaInicioReporte.format(FORMATO_FECHA)
				+ " a "
				+ this.fechaFinReporte.format(FORMATO_FECHA);

		String ventasTotales = this.lblVentaTotal.getText() + ": "
				+ this.textFieldVentasTotales.getText();
		String ivaCobrado = this.lblIvaCobrado.getText() + ": "
				+ this.textField.getText();

		try {
			DataTools.exportarJTableTxt(
					this.tableVentasTotales,
					this,
					encabezado,
					ventasTotales,
					ivaCobrado);
		} catch (IllegalArgumentException ex) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"No fue posible exportar el reporte TXT: " + ex.getMessage());
		}
	}

	private void consultarVentasTotales() {
		final LocalDate fechaInicio;
		final LocalDate fechaFinal;

		try {
			if (this.idSucursal <= 0) {
				throw new IllegalArgumentException("No existe una sucursal válida para generar el reporte");
			}

			fechaInicio = this.parseFecha(this.formattedTextFieldFechaInicio, "fecha inicial");
			fechaFinal = this.parseFecha(this.formattedTextFieldFechaFinal, "fecha final");

			if (fechaInicio.isAfter(fechaFinal)) {
				throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
			}
		} catch (IllegalArgumentException ex) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
			return;
		}

		this.buttonBuscar.setEnabled(false);

		new SwingWorker<List<VentaTotalPorFecha>, Void>() {
			@Override
			protected List<VentaTotalPorFecha> doInBackground() throws Exception {
				return AppContext.reporteController.getVentasTotalesByFechas(
						idSucursal, fechaInicio, fechaFinal);
			}

			@Override
			protected void done() {
				try {
					List<VentaTotalPorFecha> resultado = get();
					reemplazarResultados(resultado);
					fechaInicioReporte = fechaInicio;
					fechaFinReporte = fechaFinal;
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
							Fr_ReporteVentasTotales.this,
							"La consulta del reporte fue interrumpida");
				} catch (ExecutionException ex) {
					Throwable causa = ex.getCause();
					String mensaje = causa == null || causa.getMessage() == null
							? "No fue posible consultar el reporte de ventas"
							: causa.getMessage();
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
							Fr_ReporteVentasTotales.this, mensaje);
				} finally {
					buttonBuscar.setEnabled(true);
				}
			}
		}.execute();
	}

	private void reemplazarResultados(List<VentaTotalPorFecha> ventas) {
		this.modelTablaVentasTotales.setRowCount(0);

		BigDecimal totalVentas = BigDecimal.ZERO;
		BigDecimal totalIva = BigDecimal.ZERO;

		if (ventas != null) {
			for (VentaTotalPorFecha venta : ventas) {
				if (venta == null) {
					continue;
				}

				this.modelTablaVentasTotales.addRow(new Object[] {
						venta.fecha().format(FORMATO_FECHA),
						venta.numeroVentas(),
						venta.subtotal(),
						venta.iva(),
						venta.total()
				});

				totalVentas = totalVentas.add(venta.total());
				totalIva = totalIva.add(venta.iva());
			}
		}

		this.textFieldVentasTotales.setText(totalVentas.setScale(2).toPlainString());
		this.textField.setText(totalIva.setScale(2).toPlainString());
	}

	private MaskFormatter buildDateFormatter() {
		try {
			MaskFormatter formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
			formatter.setValidCharacters("0123456789");
			return formatter;
		} catch (ParseException ex) {
			ex.printStackTrace(System.err);
			return null;
		}
	}

	private LocalDate parseFecha(JFormattedTextField field, String nombreCampo) {
		String texto = field.getText() == null ? "" : field.getText().trim();

		if (texto.isEmpty() || "__/__/____".equals(texto)) {
			throw new IllegalArgumentException("Debe indicar la " + nombreCampo);
		}
		if (texto.contains("_")) {
			throw new IllegalArgumentException("La " + nombreCampo + " está incompleta");
		}

		try {
			return LocalDate.parse(texto, FORMATO_FECHA);
		} catch (DateTimeParseException ex) {
			throw new IllegalArgumentException(
					"Formato de " + nombreCampo + " inválido. Usa dd/MM/yyyy", ex);
		}
	}
}
