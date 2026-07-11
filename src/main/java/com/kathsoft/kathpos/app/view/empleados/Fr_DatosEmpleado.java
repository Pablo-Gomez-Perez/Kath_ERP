package com.kathsoft.kathpos.app.view.empleados;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.model.Empleado;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Fr_DatosEmpleado extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2754103795838559070L;
	/**
	 * 
	 * 
	 */	
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel;	
	private Box horizontalBox_7;
	private JButton btnCancelar;
	private Component horizontalStrut_7;
	private JButton btnAgregarEmpleado;
	

	/**
	 * Launch the application.
	 *
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { Fr_DatosEmpleado frame = new
	 * Fr_DatosEmpleado(); frame.setVisible(true); } catch (Exception e) {
	 * e.printStackTrace(); } } }); }
	 */

	/**
	 * Create the frame.
	 */
	public Fr_DatosEmpleado(int opcion, int idEmpleado) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));

		if (opcion == 0) {
			this.setTitle("Nuevo Empleado");
			lblNewLabel.setText("Agregar Nuevo Empleado");
		} else if (opcion == 1) {
			this.setTitle("Actualizar Empleado");
			lblNewLabel.setText("Actualizar Empleado");
		}

		panelSuperiorEtiqueta.add(lblNewLabel);

		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGap(0, 571, Short.MAX_VALUE)
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGap(0, 452, Short.MAX_VALUE)
		);
		panelCentralFormulario.setLayout(gl_panelCentralFormulario);		

		panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		horizontalBox_7 = Box.createHorizontalBox();
		panelInferiorBotones.add(horizontalBox_7);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btnCancelar.setIcon(
				new ImageIcon(Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnCancelar.setBackground(new Color(205, 92, 92));
		horizontalBox_7.add(btnCancelar);

		horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalBox_7.add(horizontalStrut_7);

		btnAgregarEmpleado = new JButton("Agregar");
		btnAgregarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (opcion == 0) {
					insertarEmpleado();
				} else if (opcion == 1) {
					actualizarEmpleado(idEmpleado);
				}
			}
		});
		btnAgregarEmpleado.setIcon(new ImageIcon(
				Fr_DatosEmpleado.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregarEmpleado.setBackground(new Color(144, 238, 144));
		horizontalBox_7.add(btnAgregarEmpleado);

		if (opcion == 1) {

		}

		this.llenarCmbSucursales();
		if (opcion == 1) {
			this.consultarEmpleadoPorId(idEmpleado);
		}
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	private void cerrarForm() {
		this.dispose();
	}

	/**
	 * llena el JCombobox del panel de rfc de empleados con todos los elemtnos
	 * retornados por la vista almacenada
	 *
	 * private void llenarCmbRfcEmpleados() { this.cmbRFCEmpleado.removeAllItems();
	 * this.cmbRFCEmpleado.updateUI();
	 * empleadoController.consultarRfcEmpleado(this.cmbRFCEmpleado);
	 * cmbRFCEmpleado.setSelectedIndex(1); }
	 */

	/**
	 * busca los datos del empleado en la bd de acuerdo al rfc que se le pase como
	 * parametro y asigna los valores correspondientes a sus respectivos campos en
	 * el formulario
	 * 
	 * @param idEmpleado
	 */
	private void consultarEmpleadoPorId(int idEmpleado) {
		
		
		
	}

	/**
	 * inserta un registro de un nuevo empleado en la base de datos
	 */
	private void insertarEmpleado() {

		


	}

	/**
	 * actualiza un registro existente de un empleado en la base de datos
	 */
	private void actualizarEmpleado(int idEmpleado) {

		

	}

	private boolean validarCamposVacios() {

		return true;
	}

	private void llenarCmbSucursales() {
		
	}

}
