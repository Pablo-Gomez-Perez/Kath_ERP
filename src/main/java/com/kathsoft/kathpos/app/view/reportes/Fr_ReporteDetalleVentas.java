package com.kathsoft.kathpos.app.view.reportes;

import java.awt.EventQueue;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.reporte.CobroResumenDia;
import com.kathsoft.kathpos.app.model.reporte.RetiroEfectivoDia;
import com.kathsoft.kathpos.app.model.venta.VentaFiltro;
import com.kathsoft.kathpos.app.model.venta.VentaListado;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class Fr_ReporteDetalleVentas extends JFrame {

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
	private JPanel panelSuperiorTitulo;
	private JLabel lblReporteDeVentas_1;
	private JPanel panelPrincipal;
	private JLabel lblFecha;
	private JFormattedTextField formattedTextFieldFechaConsulta;
	private JButton buttonBuscar;
	private JPanel panelContenedorTablaVentas;
	private JScrollPane scrollPaneTablaVentas;
	private JTable tableVentas;
	private JPanel panelContenedorFormasDePago;
	private JScrollPane scrollPaneTablaFormasDePago;
	private JTable tableFormasDePago;
	private JPanel panelContenedorDetallePorEmpleado;
	private JScrollPane scrollPaneTablaDetallePorEmpleado;
	private JTable tableDetallePorEmpleado;
	private JPanel panelContenedorDetalleRetirosDeEfectivo;
	private JScrollPane scrollPaneTablaRetirosDeEfectivo;
	private JTable tableRetirosDeEfectivo;
	private DefaultTableModel modelTablaVentas;
	private DefaultTableModel modelTablaFormasDePago;
	private DefaultTableModel modelTablaDetallePorEmpleado;
	private DefaultTableModel modelTablaRetirosDeEfectivo;

	/**
	 * Create the frame.
	 */
	public Fr_ReporteDetalleVentas() {
		this(0L);
	}

	public Fr_ReporteDetalleVentas(long idSucursal) {
		this.idSucursal = idSucursal;
		initComponents();
		inicializarReporte();
	}
	private void initComponents() {
		setBackground(new Color(255, 215, 0));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 629);
		
		this.menuBarPrincipal = new JMenuBar();
		setJMenuBar(this.menuBarPrincipal);
		
		this.mnArchivo = new JMenu("Archivo");
		this.mnArchivo.setIcon(new ImageIcon(Fr_ReporteDetalleVentas.class.getResource("/com/kathsoft/kathpos/app/assets/folder.png")));
		this.menuBarPrincipal.add(this.mnArchivo);
		
		this.mntmImprimir = new JMenuItem("Imprimir");
		this.mntmImprimir.setEnabled(false);
		this.mnArchivo.add(this.mntmImprimir);
		
		this.mntmGenerarPdf = new JMenuItem("Generar PDF");
		this.mntmGenerarPdf.setIcon(new ImageIcon(Fr_ReporteDetalleVentas.class.getResource("/com/kathsoft/kathpos/app/assets/pdfLogo.jpg")));
		this.mnArchivo.add(this.mntmGenerarPdf);
		
		this.mntmGenerarTxt = new JMenuItem("Generar TXT");
		this.mntmGenerarTxt.setIcon(new ImageIcon(Fr_ReporteDetalleVentas.class.getResource("/com/kathsoft/kathpos/app/assets/txt.png")));
		this.mnArchivo.add(this.mntmGenerarTxt);
		
		this.mntmVerEnExcelcsv = new JMenuItem("Ver en excel(CSV)");
		this.mntmVerEnExcelcsv.setIcon(new ImageIcon(Fr_ReporteDetalleVentas.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.mnArchivo.add(this.mntmVerEnExcelcsv);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 215, 0));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelSuperiorTitulo = new JPanel();
		this.panelSuperiorTitulo.setBackground(new Color(25, 25, 112));
		this.contentPane.add(this.panelSuperiorTitulo, BorderLayout.NORTH);
		
		this.lblReporteDeVentas_1 = new JLabel("Reporte de Ventas a detalle");
		this.lblReporteDeVentas_1.setForeground(Color.WHITE);
		this.lblReporteDeVentas_1.setFont(new Font("Dialog", Font.BOLD, 14));
		this.panelSuperiorTitulo.add(this.lblReporteDeVentas_1);
		
		this.panelPrincipal = new JPanel();
		this.panelPrincipal.setBackground(new Color(255, 215, 0));
		this.contentPane.add(this.panelPrincipal, BorderLayout.CENTER);
		
		this.lblFecha = new JLabel("Fecha");
		
		this.formattedTextFieldFechaConsulta = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaConsulta.setToolTipText("dd/MM/yyyy");
		
		this.buttonBuscar = new JButton("");
		this.buttonBuscar.setIcon(new ImageIcon(Fr_ReporteDetalleVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		
		this.panelContenedorTablaVentas = new JPanel();
		this.panelContenedorTablaVentas.setBackground(new Color(255, 215, 0));
		this.panelContenedorTablaVentas.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalle de ventas del dia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.panelContenedorFormasDePago = new JPanel();
		this.panelContenedorFormasDePago.setBackground(new Color(255, 215, 0));
		this.panelContenedorFormasDePago.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalle cobrado por forma de pago", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		this.panelContenedorDetallePorEmpleado = new JPanel();
		this.panelContenedorDetallePorEmpleado.setBackground(new Color(255, 215, 0));
		this.panelContenedorDetallePorEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalle cobrado por empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.panelContenedorDetalleRetirosDeEfectivo = new JPanel();
		this.panelContenedorDetalleRetirosDeEfectivo.setBackground(new Color(255, 215, 0));
		this.panelContenedorDetalleRetirosDeEfectivo.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Retiros de efectivo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout gl_panelPrincipal = new GroupLayout(this.panelPrincipal);
		gl_panelPrincipal.setHorizontalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelPrincipal.createSequentialGroup()
							.addComponent(this.panelContenedorTablaVentas, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panelPrincipal.createSequentialGroup()
							.addComponent(this.lblFecha)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaConsulta, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.buttonBuscar, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
							.addGap(153))
						.addGroup(Alignment.TRAILING, gl_panelPrincipal.createSequentialGroup()
							.addComponent(this.panelContenedorFormasDePago, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panelPrincipal.createSequentialGroup()
							.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
								.addComponent(this.panelContenedorDetalleRetirosDeEfectivo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
								.addComponent(this.panelContenedorDetallePorEmpleado, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE))
							.addContainerGap())))
		);
		gl_panelPrincipal.setVerticalGroup(
			gl_panelPrincipal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPrincipal.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.buttonBuscar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelPrincipal.createParallelGroup(Alignment.BASELINE)
							.addComponent(this.lblFecha)
							.addComponent(this.formattedTextFieldFechaConsulta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelContenedorTablaVentas, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelContenedorFormasDePago, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelContenedorDetallePorEmpleado, GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelContenedorDetalleRetirosDeEfectivo, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
					.addContainerGap())
		);
		this.panelContenedorDetalleRetirosDeEfectivo.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTablaRetirosDeEfectivo = new JScrollPane();
		this.panelContenedorDetalleRetirosDeEfectivo.add(this.scrollPaneTablaRetirosDeEfectivo, BorderLayout.CENTER);

		this.tableRetirosDeEfectivo = new JTable();
		this.scrollPaneTablaRetirosDeEfectivo.setViewportView(this.tableRetirosDeEfectivo);
		this.panelContenedorDetallePorEmpleado.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTablaDetallePorEmpleado = new JScrollPane();
		this.panelContenedorDetallePorEmpleado.add(this.scrollPaneTablaDetallePorEmpleado, BorderLayout.CENTER);
		
		this.tableDetallePorEmpleado = new JTable();
		this.scrollPaneTablaDetallePorEmpleado.setViewportView(this.tableDetallePorEmpleado);
		this.panelContenedorFormasDePago.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTablaFormasDePago = new JScrollPane();
		this.panelContenedorFormasDePago.add(this.scrollPaneTablaFormasDePago, BorderLayout.CENTER);
		
		this.tableFormasDePago = new JTable();
		this.scrollPaneTablaFormasDePago.setViewportView(this.tableFormasDePago);
		this.panelContenedorTablaVentas.setLayout(new BorderLayout(0, 0));
		
		this.scrollPaneTablaVentas = new JScrollPane();
		this.panelContenedorTablaVentas.add(this.scrollPaneTablaVentas, BorderLayout.CENTER);

		this.tableVentas = new JTable();
		this.scrollPaneTablaVentas.setViewportView(this.tableVentas);
		this.panelPrincipal.setLayout(gl_panelPrincipal);
	}

	private void inicializarReporte() {
		this.modelTablaVentas = crearModeloVentas();
		this.modelTablaFormasDePago = crearModeloFormasDePago();
		this.modelTablaDetallePorEmpleado = crearModeloDetallePorEmpleado();
		this.modelTablaRetirosDeEfectivo = crearModeloRetiros();

		this.tableVentas.setModel(this.modelTablaVentas);
		this.tableFormasDePago.setModel(this.modelTablaFormasDePago);
		this.tableDetallePorEmpleado.setModel(this.modelTablaDetallePorEmpleado);
		this.tableRetirosDeEfectivo.setModel(this.modelTablaRetirosDeEfectivo);

		DataTools.removerEditorDeTabla(this.tableVentas, this.modelTablaVentas);
		DataTools.removerEditorDeTabla(this.tableFormasDePago, this.modelTablaFormasDePago);
		DataTools.removerEditorDeTabla(this.tableDetallePorEmpleado, this.modelTablaDetallePorEmpleado);
		DataTools.removerEditorDeTabla(this.tableRetirosDeEfectivo, this.modelTablaRetirosDeEfectivo);

		this.buttonBuscar.addActionListener(e -> this.consultarDetalleDelDia());
	}

	private void consultarDetalleDelDia() {
		final LocalDate fecha;

		try {
			if (this.idSucursal <= 0) {
				throw new IllegalArgumentException(
						"No existe una sucursal válida para consultar el reporte");
			}
			fecha = this.parseFecha();
		} catch (IllegalArgumentException ex) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
			return;
		}

		this.buttonBuscar.setEnabled(false);

		new javax.swing.SwingWorker<DetalleVentaDiaResultado, Void>() {
			@Override
			protected DetalleVentaDiaResultado doInBackground() throws Exception {
				Date fechaSql = Date.valueOf(fecha);
				VentaFiltro filtroVentas = new VentaFiltro.VentaFiltroBuilder()
						.tipoBusqueda("TODOS")
						.textoBusqueda("")
						.ordenarPor("FECHA")
						.fechaInicial(fechaSql)
						.fechaFinal(fechaSql)
						.build();

				List<VentaListado> ventas =
						AppContext.ventasController.listVentas(idSucursal, filtroVentas);
				List<CobroResumenDia> formasPago =
						AppContext.reporteController.listDetalleCobrosPorFormaDePago(
								idSucursal, fecha);
				List<CobroResumenDia> cobrosEmpleado =
						AppContext.reporteController.listDetalleCobradoVentasPorEmpleado(
								idSucursal, fecha);
				List<RetiroEfectivoDia> retiros =
						AppContext.reporteController.listRetirosDeEfectivoDelDia(
								idSucursal, fecha);

				return new DetalleVentaDiaResultado(
						ventas, formasPago, cobrosEmpleado, retiros);
			}

			@Override
			protected void done() {
				try {
					reemplazarResultados(get());
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
					MessageHandler.displayMessage(
							MessageHandler.ERROR_MESSAGE,
							Fr_ReporteDetalleVentas.this,
							"La consulta del reporte fue interrumpida");
				} catch (ExecutionException ex) {
					Throwable causa = ex.getCause();
					String mensaje = causa == null || causa.getMessage() == null
							? "No fue posible consultar el detalle de ventas"
							: causa.getMessage();
					MessageHandler.displayMessage(
							MessageHandler.ERROR_MESSAGE,
							Fr_ReporteDetalleVentas.this,
							mensaje);
				} finally {
					buttonBuscar.setEnabled(true);
				}
			}
		}.execute();
	}

	private void reemplazarResultados(DetalleVentaDiaResultado resultado) {
		this.modelTablaVentas.setRowCount(0);
		this.modelTablaFormasDePago.setRowCount(0);
		this.modelTablaDetallePorEmpleado.setRowCount(0);
		this.modelTablaRetirosDeEfectivo.setRowCount(0);

		for (VentaListado venta : resultado.ventas()) {
			this.modelTablaVentas.addRow(new Object[] {
					venta.getFolio(),
					venta.getFecha() == null ? "" : venta.getFecha().toLocalDate().format(FORMATO_FECHA),
					venta.getTipo(),
					venta.getAtendio(),
					venta.getCliente(),
					venta.getSubtotal(),
					venta.getIva(),
					venta.getTotal(),
					venta.getVigente()
			});
		}

		for (CobroResumenDia fila : resultado.formasPago()) {
			this.modelTablaFormasDePago.addRow(new Object[] { fila.nombre(), fila.total() });
		}

		for (CobroResumenDia fila : resultado.cobrosEmpleado()) {
			this.modelTablaDetallePorEmpleado.addRow(new Object[] { fila.nombre(), fila.total() });
		}

		for (RetiroEfectivoDia retiro : resultado.retiros()) {
			this.modelTablaRetirosDeEfectivo.addRow(
					new Object[] { retiro.folio(), retiro.importe() });
		}
	}

	static DefaultTableModel crearModeloVentas() {
		return modeloNoEditable(new Object[] {
				"Folio", "Fecha", "Tipo", "Atendió", "Cliente",
				"Sub total", "IVA", "Total", "Estado"
		});
	}

	static DefaultTableModel crearModeloFormasDePago() {
		return modeloNoEditable(new Object[] { "Forma de pago", "Total" });
	}

	static DefaultTableModel crearModeloDetallePorEmpleado() {
		return modeloNoEditable(new Object[] { "Empleado", "Total" });
	}

	static DefaultTableModel crearModeloRetiros() {
		return modeloNoEditable(new Object[] { "Folio", "Importe" });
	}

	private static DefaultTableModel modeloNoEditable(Object[] columnas) {
		return new DefaultTableModel(columnas, 0) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
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

	private LocalDate parseFecha() {
		String texto = this.formattedTextFieldFechaConsulta.getText() == null
				? ""
				: this.formattedTextFieldFechaConsulta.getText().trim();

		if (texto.isEmpty() || "__/__/____".equals(texto)) {
			throw new IllegalArgumentException("Debe indicar la fecha del reporte");
		}
		if (texto.contains("_")) {
			throw new IllegalArgumentException("La fecha del reporte está incompleta");
		}

		try {
			return LocalDate.parse(texto, FORMATO_FECHA);
		} catch (DateTimeParseException ex) {
			throw new IllegalArgumentException(
					"Formato de fecha inválido. Usa dd/MM/yyyy", ex);
		}
	}

	private record DetalleVentaDiaResultado(
			List<VentaListado> ventas,
			List<CobroResumenDia> formasPago,
			List<CobroResumenDia> cobrosEmpleado,
			List<RetiroEfectivoDia> retiros) {

		private DetalleVentaDiaResultado {
			ventas = ventas == null ? List.of() : List.copyOf(ventas);
			formasPago = formasPago == null ? List.of() : List.copyOf(formasPago);
			cobrosEmpleado = cobrosEmpleado == null ? List.of() : List.copyOf(cobrosEmpleado);
			retiros = retiros == null ? List.of() : List.copyOf(retiros);
		}
	}
}
