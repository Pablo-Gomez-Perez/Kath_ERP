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
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelCuentasContables extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiqueta;
	private JTable tableCuentasContables;	
	private DefaultTableModel tablaCuentasContablesModel;
	private JPanel panelCentralSuperiorBotones;
	private JButton btn_Agregar;
	private JButton btn_Modificar;
	private JButton btn_Eliminar;
	private JButton btn_ReporteExcel;
	private JPanel panelInferiorBusqueda;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JTextField txfBuscarCuentaContable;
	private Component horizontalStrut_1;
	private JButton btnBuscarCuenta;

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
		this.tableCuentasContables.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaConta.setViewportView(tableCuentasContables);		
		
		panelCentralSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelCentralSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelCentralSuperiorBotones.setBackground(new Color(255, 215, 0));
		panelCentral.add(panelCentralSuperiorBotones, BorderLayout.NORTH);
		
		btn_Agregar = new JButton("Agregar");
		btn_Agregar.setIcon(new ImageIcon(PanelCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelCentralSuperiorBotones.add(btn_Agregar);
		this.btn_Agregar.setBackground(new Color(144,238,144));
		
		
		btn_Modificar = new JButton("Modificar");
		btn_Modificar.setIcon(new ImageIcon(PanelCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		panelCentralSuperiorBotones.add(btn_Modificar);
		this.btn_Modificar.setBackground(new Color(144,238,144));
		
		btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setIcon(new ImageIcon(PanelCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		panelCentralSuperiorBotones.add(btn_Eliminar);
		this.btn_Eliminar.setBackground(new Color(255,51,0));
		
		btn_ReporteExcel = new JButton("Exportar Excel");
		btn_ReporteExcel.setIcon(new ImageIcon(PanelCuentasContables.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		panelCentralSuperiorBotones.add(btn_ReporteExcel);
		this.btn_ReporteExcel.setBackground(new Color(102,205,170));
		
		panelInferiorBusqueda = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelInferiorBusqueda.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelInferiorBusqueda.setBackground(new Color(255, 215, 0));
		panelCentral.add(panelInferiorBusqueda, BorderLayout.SOUTH);
		
		lblNewLabel_1 = new JLabel("Cuenta Contable");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelInferiorBusqueda.add(lblNewLabel_1);
		
		horizontalStrut = Box.createHorizontalStrut(20);
		panelInferiorBusqueda.add(horizontalStrut);
		
		txfBuscarCuentaContable = new JTextField();
		panelInferiorBusqueda.add(txfBuscarCuentaContable);
		txfBuscarCuentaContable.setColumns(70);
		this.txfBuscarCuentaContable.setMaximumSize(this.txfBuscarCuentaContable.getPreferredSize());		
		
		horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelInferiorBusqueda.add(horizontalStrut_1);
		
		btnBuscarCuenta = new JButton("Buscar");
		btnBuscarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTablaCuentas();
			}
		});
		this.btnBuscarCuenta.setBackground(new Color(184,134,11));
		panelInferiorBusqueda.add(btnBuscarCuenta);
				
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaCuentasContablesColumnsWidth, tableCuentasContables);
		
	}
	
	public void llenarTablaCuentas() {
		
		var cuentas = AppContext.cuentaContableController.verCuentasContables(this.txfBuscarCuentaContable.getText());
		
		this.tablaCuentasContablesModel.getDataVector().removeAllElements();
		this.tableCuentasContables.updateUI();
		
		cuentas.forEach(c -> {
			this.tablaCuentasContablesModel.addRow(c);
		});
		
	}
	
}
