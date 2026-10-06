package com.kathsoft.kathpos.app.view.reportes;

import java.awt.EventQueue;

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
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class Fr_ReporteDetalleVentas extends JFrame {

	private static final long serialVersionUID = 1L;
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
	private JPanel panelContenedorFormasDePago;
	private JScrollPane scrollPaneTablaFormasDePago;
	private JTable tableFormasDePago;
	private JPanel panelContenedorDetallePorEmpleado;
	private JScrollPane scrollPaneTablaDetallePorEmpleado;
	private JTable tableDetallePorEmpleado;
	private JPanel panelContenedorDetalleRetirosDeEfectivo;
	private JScrollPane scrollPaneTablaRetirosDeEfectivo;

	/**
	 * Create the frame.
	 */
	public Fr_ReporteDetalleVentas() {

		initComponents();
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		this.contentPane.add(this.panelPrincipal, BorderLayout.CENTER);
		
		this.lblFecha = new JLabel("Fecha");
		
		this.formattedTextFieldFechaConsulta = new JFormattedTextField((AbstractFormatter) null);
		this.formattedTextFieldFechaConsulta.setToolTipText("dd/MM/yyyy");
		
		this.buttonBuscar = new JButton("");
		this.buttonBuscar.setIcon(new ImageIcon(Fr_ReporteDetalleVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		
		this.panelContenedorTablaVentas = new JPanel();
		this.panelContenedorTablaVentas.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalle de ventas del dia", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.panelContenedorFormasDePago = new JPanel();
		this.panelContenedorFormasDePago.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalle cobrado por forma de pago", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		this.panelContenedorDetallePorEmpleado = new JPanel();
		this.panelContenedorDetallePorEmpleado.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Detalle cobrado por empleado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		this.panelContenedorDetalleRetirosDeEfectivo = new JPanel();
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
		this.panelPrincipal.setLayout(gl_panelPrincipal);
	}
}
