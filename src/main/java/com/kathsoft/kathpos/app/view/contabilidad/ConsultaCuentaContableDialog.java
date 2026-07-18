package com.kathsoft.kathpos.app.view.contabilidad;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.viewmodel.CuentaContableResponseViewModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ConsultaCuentaContableDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txfNombreCuenta;
	private JTable tableCuentasContables;
	private CuentaContableResponseViewModel result;

	/**
	 * Create the frame.
	 */
	public ConsultaCuentaContableDialog(Frame parent) {
		super(parent,true);		
		setBounds(100, 100, 520, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);			
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelCentralContenedor = new JPanel();
		panelCentralContenedor.setBackground(new Color(255, 204, 0));
		contentPane.add(panelCentralContenedor, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre");
		
		txfNombreCuenta = new JTextField();
		txfNombreCuenta.setColumns(10);
		
		JButton btnBuscarCuenta = new JButton("Buscar");
		btnBuscarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				consultarCuentas(txfNombreCuenta.getText());
			}
		});
		btnBuscarCuenta.setBackground(new Color(153, 102, 0));
		
		JScrollPane scrollPaneTablaCuentasContables = new JScrollPane();
		GroupLayout gl_panelCentralContenedor = new GroupLayout(panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneTablaCuentasContables, GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
						.addGroup(gl_panelCentralContenedor.createSequentialGroup()
							.addComponent(lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombreCuenta, GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnBuscarCuenta)))
					.addContainerGap())
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentralContenedor.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTablaCuentasContables, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralContenedor.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNombre)
						.addComponent(txfNombreCuenta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscarCuenta))
					.addContainerGap())
		);
		
		tableCuentasContables = new JTable();
		this.tableCuentasContables.setModel(this.definirModelo());
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaCuentasContablesDialogColumnsWidth, tableCuentasContables);
		scrollPaneTablaCuentasContables.setViewportView(tableCuentasContables);
		panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		
		JPanel panelInferiorBotones = new JPanel();
		panelInferiorBotones.setBackground(new Color(0, 204, 255));
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
		
		JButton btnAceptar = new JButton("Seleccionar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCuentaContable();
				dispose();
			}
			
		});
		btnAceptar.setBackground(new Color(0, 255, 102));
		panelInferiorBotones.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				result = new CuentaContableResponseViewModel(-1,"");
				dispose();
			}
		});
		btnCancelar.setBackground(new Color(255, 51, 51));
		panelInferiorBotones.add(btnCancelar);
		
		this.setLocationRelativeTo(parent);
		this.consultarCuentas("");

	}
	
	/**
	 * Crea el modelo de datos utilizado por la tabla de selección de cuentas
	 * contables.
	 *
	 * <p>El modelo contiene las columnas necesarias para mostrar la información
	 * básica de una cuenta contable disponible para selección.</p>
	 *
	 * @return Modelo de tabla configurado con las columnas Id, Clave y Nombre.
	 */
	private DefaultTableModel definirModelo() {
		
		DefaultTableModel model = new DefaultTableModel();
		
		model.addColumn("id");
		model.addColumn("Clave");
		model.addColumn("Nombre");
		
		return model;
		
	}
	
	/**
	 * Consulta las cuentas contables cuyo nombre coincide con el criterio de
	 * búsqueda y actualiza el contenido de la tabla.
	 *
	 * <p>Antes de cargar los nuevos registros, se eliminan todas las filas
	 * existentes del modelo de la tabla.</p>
	 *
	 * @param nombre Nombre o parte del nombre de la cuenta contable a buscar.
	 */
	private void consultarCuentas(String nombre) {
		
		((DefaultTableModel) this.tableCuentasContables.getModel()).getDataVector().removeAllElements();
		this.tableCuentasContables.updateUI();
		
		AppContext.cuentaContableController.listCuentasContablesDialog(nombre)
			.forEach(((DefaultTableModel)this.tableCuentasContables.getModel())::addRow);
		
	}
	
	/**
	 * Obtiene la cuenta contable seleccionada actualmente en la tabla.
	 *
	 * <p>La información retornada contiene únicamente el identificador interno y
	 * la clave contable de la cuenta seleccionada.</p>
	 *
	 * @return Objeto {@link CuentaContableResponseViewModel} con la información de
	 *         la cuenta seleccionada.
	 *
	 * @throws IllegalStateException Si no existe una fila seleccionada en
	 *         la tabla.
	 */
	private CuentaContableResponseViewModel selectCuentaContable() {
		
		int indice = this.tableCuentasContables.getSelectedRow();
		
		if(indice < 0) throw new IllegalStateException("Debe seleccionar una cuenta contbale");
		
		return new CuentaContableResponseViewModel(
				(int)((DefaultTableModel)tableCuentasContables.getModel()).getValueAt(indice, 0),
				String.valueOf(((DefaultTableModel)tableCuentasContables.getModel()).getValueAt(indice, 1))
				);
		
	}
	
	public void setCuentaContable() {
		this.result = selectCuentaContable();
	}
	
	public CuentaContableResponseViewModel getCuentaContable() {
		return this.result;
	}
	
	
}
