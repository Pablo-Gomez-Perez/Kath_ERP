package com.kathsoft.kathpos.app.view.reportes;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;

public class Fr_ReporteVentasTotales extends JFrame {

	private static final long serialVersionUID = 1L;
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
	 * Create the frame.
	 */
	public Fr_ReporteVentasTotales() {

		initComponents();
	}
	private void initComponents() {
		setBackground(new Color(255, 215, 0));
		setTitle("Reporte de ventas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		
		this.formattedTextFieldFechaInicio = new JFormattedTextField();
		
		this.lblHasta = new JLabel("Hasta");
		
		this.formattedTextFieldFechaFinal = new JFormattedTextField();
		
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
}
