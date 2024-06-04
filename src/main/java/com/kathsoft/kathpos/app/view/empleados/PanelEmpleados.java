package com.kathsoft.kathpos.app.view.empleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelEmpleados extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEmpleadosEtiqueta;
	private JLabel lblNewLabel_6;
	private JPanel panelEmpleadosCentral;	
	private JScrollPane scrollPaneTablaEmpleados;
	private DefaultTableModel modelTablaEmpleados;
	private JTable tableEmpleados;
	private JPanel panelEmpleadosCentralbotones;
	private JButton btnAgregarEmpleado;
	private JButton btnActualizarEmpleado;
	private JButton btnEliminarEmpleado;
	private JButton btnExportarEmpleadosExcel;
	private JPanel panelEmpleadosCentralBuscar;
	private JLabel lblNewLabel_4;
	private Component horizontalStrut_1;
	private JTextField txfBuscarEmpleado;
	private JButton btnBuscarEmpleado;

	/**
	 * Create the panel.
	 */
	public PanelEmpleados() {
		
		this.setLayout(new BorderLayout(0, 0));
		
		panelEmpleadosEtiqueta = new JPanel();
		panelEmpleadosEtiqueta.setBackground(new Color(0, 0, 128));
		this.add(panelEmpleadosEtiqueta, BorderLayout.NORTH);
		
		
		lblNewLabel_6 = new JLabel("Modulo de Empleados");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		panelEmpleadosEtiqueta.add(lblNewLabel_6);
		
		panelEmpleadosCentral = new JPanel();
		panelEmpleadosCentral.setBorder(new EmptyBorder(30, 30, 30, 30));
		panelEmpleadosCentral.setBackground(new Color(255, 215, 0));
		this.add(panelEmpleadosCentral, BorderLayout.CENTER);
		panelEmpleadosCentral.setLayout(new BorderLayout(0, 0));

		scrollPaneTablaEmpleados = new JScrollPane();
		panelEmpleadosCentral.add(scrollPaneTablaEmpleados, BorderLayout.CENTER);
		
		modelTablaEmpleados = new DefaultTableModel();
		modelTablaEmpleados.addColumn("id");
		modelTablaEmpleados.addColumn("Sucursal");
		modelTablaEmpleados.addColumn("RFC");
		modelTablaEmpleados.addColumn("CURP");
		modelTablaEmpleados.addColumn("Nombre");
		modelTablaEmpleados.addColumn("Nick");
		modelTablaEmpleados.addColumn("Email");
		modelTablaEmpleados.addColumn("Activo");
		tableEmpleados = new JTable();
		tableEmpleados.setModel(this.modelTablaEmpleados);
		tableEmpleados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		DataTools.removerEditorDeTabla(tableEmpleados, modelTablaEmpleados);
		
		
		scrollPaneTablaEmpleados.setViewportView(tableEmpleados);

		panelEmpleadosCentralbotones = new JPanel();
		FlowLayout flowLayout_7 = (FlowLayout) panelEmpleadosCentralbotones.getLayout();
		flowLayout_7.setAlignment(FlowLayout.RIGHT);
		panelEmpleadosCentralbotones.setBackground(new Color(255, 215, 0));
		panelEmpleadosCentral.add(panelEmpleadosCentralbotones, BorderLayout.NORTH);
		
		btnAgregarEmpleado = new JButton("Agregar");
		btnAgregarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregarEmpleado.setBackground(new Color(144, 238, 144));
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioEmpleados(0, 0);
			}
		});
		panelEmpleadosCentralbotones.add(btnAgregarEmpleado);

		btnActualizarEmpleado = new JButton("Actualizar");
		btnActualizarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnActualizarEmpleado.setBackground(new Color(144, 238, 144));
		btnActualizarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormularioEmpleados(1,
						DataTools.getIndiceElementoSeleccionado(tableEmpleados, modelTablaEmpleados, 0));
			}
		});
		
		panelEmpleadosCentralbotones.add(btnActualizarEmpleado);

		btnEliminarEmpleado = new JButton("Eliminar");
		btnEliminarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarEmpleado();
			}
		});
		
		
		btnEliminarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnEliminarEmpleado.setBackground(new Color(255, 51, 0));
		panelEmpleadosCentralbotones.add(btnEliminarEmpleado);

		btnExportarEmpleadosExcel = new JButton("Exportar a Excel");
		btnExportarEmpleadosExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarEmpleadosExcel();
			}
		});
		btnExportarEmpleadosExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		this.btnExportarEmpleadosExcel.setBackground(new Color(105, 205, 170));
		panelEmpleadosCentralbotones.add(btnExportarEmpleadosExcel);
		
		panelEmpleadosCentralBuscar = new JPanel();
		panelEmpleadosCentralBuscar.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_12 = (FlowLayout) panelEmpleadosCentralBuscar.getLayout();
		flowLayout_12.setAlignment(FlowLayout.RIGHT);
		panelEmpleadosCentral.add(panelEmpleadosCentralBuscar, BorderLayout.SOUTH);

		lblNewLabel_4 = new JLabel("Buscar Empleado");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelEmpleadosCentralBuscar.add(lblNewLabel_4);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelEmpleadosCentralBuscar.add(horizontalStrut_1);

		txfBuscarEmpleado = new JTextField();
		panelEmpleadosCentralBuscar.add(txfBuscarEmpleado);
		txfBuscarEmpleado.setColumns(70);
		
		btnBuscarEmpleado = new JButton("Buscar");
		btnBuscarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarEmpleadoPorNombre();
			}
		});
		btnBuscarEmpleado.setBackground(new Color(184, 134, 11));
		btnBuscarEmpleado.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		panelEmpleadosCentralBuscar.add(btnBuscarEmpleado);
		
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tableEmpleadosColumnsWidth, tableEmpleados);
	}
	
	private void abrirFormularioEmpleados(int opcion, int idEmpleado) {
		Component cm = this;

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					Fr_DatosEmpleado fr = new Fr_DatosEmpleado(opcion, idEmpleado);
					fr.setLocationRelativeTo(cm);
					fr.setVisible(true);

				} catch (Exception er) {
					er.printStackTrace();
				}
			}
		});
	}
	
	private void eliminarEmpleado() {

		int indiceEmpleadoSeleccionado = -1;
		int input = MessageHandler.displayMessage(MessageHandler.DELETE_DATA_QUESTION_MESSAGE, this);

		if (input > 0) {
			return;
		}

		try {

			indiceEmpleadoSeleccionado = DataTools.getIndiceElementoSeleccionado(tableEmpleados, modelTablaEmpleados,
					0);
			AppContext.empleadoController.eliminarEmpleado(indiceEmpleadoSeleccionado);

			MessageHandler.displayMessage(MessageHandler.DELETE_SUCCESS_MESSAGE, this);

		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this);
		}

	}
	
	public void exportarEmpleadosExcel() {
		try {
			DataTools.exportarTablaExcel(modelTablaEmpleados, this);
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
			er.printStackTrace();
		}
	}
	
	private void buscarEmpleadoPorNombre() {
		this.modelTablaEmpleados.getDataVector().removeAllElements();
		this.tableEmpleados.updateUI();
		AppContext.empleadoController.buscarEmpleadoPorNombre(this.txfBuscarEmpleado.getText(), this.modelTablaEmpleados);
	}
	
	/**
	 * borra todos los elementos contenidos en la tabla empleados
	 */
	private void borrarElementosDeLaTablaEmpleados() {
		this.modelTablaEmpleados.getDataVector().removeAllElements();
		this.tableEmpleados.updateUI();
	}
	
	
	/**
	 * llena el jTable del panel de empleados con todos los registros encontrados en
	 * la bd
	 */
	public void llenarTablaEmpleados() {
		this.borrarElementosDeLaTablaEmpleados();
		AppContext.empleadoController.verEmpleadosEnTabla(modelTablaEmpleados);
	}
}
