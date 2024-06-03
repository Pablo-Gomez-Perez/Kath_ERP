package com.kathsoft.kathpos.app.view.contabilidad;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.CuentaContableController;
import com.kathsoft.kathpos.tools.DataTools;

import java.awt.FlowLayout;
import javax.swing.JButton;

public class PanelCuentasContables extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiqueta;
	private JTable tableCuentasContables;
	private CuentaContableController cuentaContableController = new CuentaContableController();
	private DefaultTableModel tablaCuentasContablesModel;
	private int[] columnsWidth = {
			40, //id
			200, // nombre cuenta superior
			150, // clave
			200, // Nombre de la cuenta
			270, //Descripcion
			40, //nivel
			100, // Es de nivel de detalle
			150, // Cargos
			150, //Abonos
			150 // Saldo de la cuenta
	};

	/**
	 * Create the panel.
	 */
	public PanelCuentasContables() {
		setBorder(new EmptyBorder(0, 0, 0, 0));
		setBackground(new Color(255, 215, 0));
		setLayout(new BorderLayout(0, 0));
		
		panelEtiqueta = new JPanel();
		panelEtiqueta.setBorder(new EmptyBorder(0, 0, 5, 0));
		panelEtiqueta.setBackground(new Color(25, 25, 112));
		add(panelEtiqueta, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Contabilidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		panelEtiqueta.add(lblNewLabel);
		
		JPanel panelCentral = new JPanel();
		panelCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelCentral.setBackground(new Color(255, 215, 0));
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneTablaConta = new JScrollPane();
		panelCentral.add(scrollPaneTablaConta, BorderLayout.CENTER);
		
		this.tablaCuentasContablesModel = new DefaultTableModel();
		
		this.tablaCuentasContablesModel.addColumn("id");
		this.tablaCuentasContablesModel.addColumn("Cta Superior");
		this.tablaCuentasContablesModel.addColumn("Clave");
		this.tablaCuentasContablesModel.addColumn("Cuenta");
		this.tablaCuentasContablesModel.addColumn("Descripcion");
		this.tablaCuentasContablesModel.addColumn("nivel");
		this.tablaCuentasContablesModel.addColumn("Detalle ?");
		this.tablaCuentasContablesModel.addColumn("Cargos");
		this.tablaCuentasContablesModel.addColumn("Abonos");
		this.tablaCuentasContablesModel.addColumn("Saldo");
		
		tableCuentasContables = new JTable();
		tableCuentasContables.setModel(tablaCuentasContablesModel);
		scrollPaneTablaConta.setViewportView(tableCuentasContables);
		
		JPanel panelCentralSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCentralSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCentralSuperiorBotones.setBackground(new Color(255, 215, 0));
		panelCentral.add(panelCentralSuperiorBotones, BorderLayout.NORTH);
		
		JButton btn_Agregar = new JButton("Agregar");
		panelCentralSuperiorBotones.add(btn_Agregar);
		
		JButton btn_Modificar = new JButton("Modificar");
		panelCentralSuperiorBotones.add(btn_Modificar);
		
		JButton btn_Eliminar = new JButton("Eliminar");
		panelCentralSuperiorBotones.add(btn_Eliminar);
		
		JButton btn_ReporteExcel = new JButton("Excel");
		panelCentralSuperiorBotones.add(btn_ReporteExcel);
		
		JPanel panelInferiorBusqueda = new JPanel();
		panelInferiorBusqueda.setBackground(new Color(255, 215, 0));
		panelCentral.add(panelInferiorBusqueda, BorderLayout.SOUTH);
		
		
		DataTools.definirTamanioDeColumnas(columnsWidth, tableCuentasContables);
	}
	
	public void llenarTablaCuentas() {
		var cuentas = this.cuentaContableController.verCuentasContables("");
		
		this.tablaCuentasContablesModel.getDataVector().removeAllElements();
		this.tableCuentasContables.updateUI();
		
		cuentas.forEach(c -> {
			this.tablaCuentasContablesModel.addRow(c);
		});
	}
	
}
