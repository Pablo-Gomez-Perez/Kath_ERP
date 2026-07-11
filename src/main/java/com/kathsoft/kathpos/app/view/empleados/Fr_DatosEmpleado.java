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
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;

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
	private JTextField txfRfcEmpleado;
	private JTextField txfCurpEmpleado;
	private JTextField txfNombreCompletoEmpleado;
	private JTextField txfNombreCortoEmpleado;
	private JLabel lblFNacimiento;
	private JTextField txfEstadoEmpleado;
	private JTextField txfCiudadEmpleado;
	private JTextField txfCodigoPostal;
	private JTextField txfClaveCuentaContable;
	private JTable tableNumerosTelefonicos;
	

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
		setBounds(100, 100, 570, 420);
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
		
		JLabel lblForRfc = new JLabel("RFC");
		
		txfRfcEmpleado = new JTextField();
		txfRfcEmpleado.setColumns(10);
		
		JLabel lblForCurp = new JLabel("CURP");
		
		txfCurpEmpleado = new JTextField();
		txfCurpEmpleado.setColumns(10);
		
		JLabel lblForNombreCompleto = new JLabel("Nombre Completo");
		
		txfNombreCompletoEmpleado = new JTextField();
		txfNombreCompletoEmpleado.setColumns(10);
		
		JLabel lblForAlias = new JLabel("Alias");
		
		txfNombreCortoEmpleado = new JTextField();
		txfNombreCortoEmpleado.setColumns(10);
		
		lblFNacimiento = new JLabel("F. Nacimiento");
		
		JFormattedTextField frmtdtxtfldFechanacimientoempleado = new JFormattedTextField();
		
		JLabel lblEmail = new JLabel("Email");
		
		JFormattedTextField frmtxfCorreoElectronico = new JFormattedTextField();
		
		JLabel lblEstado = new JLabel("Estado");
		
		txfEstadoEmpleado = new JTextField();
		txfEstadoEmpleado.setColumns(10);
		
		JLabel lblCiudad = new JLabel("Ciudad");
		
		txfCiudadEmpleado = new JTextField();
		txfCiudadEmpleado.setColumns(10);
		
		JLabel lblDireccion = new JLabel("Dirección");
		
		JScrollPane scrollPaneTxaDireccionEmpleado = new JScrollPane();
		
		JLabel lblCodigoPostal = new JLabel("Codigo Postal");
		
		txfCodigoPostal = new JTextField();
		txfCodigoPostal.setColumns(10);
		
		JLabel lblClaveContable = new JLabel("Clave Contable");
		
		txfClaveCuentaContable = new JTextField();
		txfClaveCuentaContable.setColumns(10);
		
		JLabel lblNmerosDeContacto = new JLabel("Números de contacto");
		
		JScrollPane scrollPaneNumerosTelefonicos = new JScrollPane();
		
		JButton btnAgregarTelefono = new JButton("Nuevo");
		btnAgregarTelefono.setBackground(new Color(0, 255, 51));
		btnAgregarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		
		JButton btnEliminarTelefono = new JButton("Nuevo");
		btnEliminarTelefono.setFont(new Font("Dialog", Font.BOLD, 9));
		btnEliminarTelefono.setBackground(new Color(255, 102, 102));
		GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneNumerosTelefonicos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
						.addComponent(scrollPaneTxaDireccionEmpleado, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 547, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblForRfc)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblForCurp)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfCurpEmpleado, GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblForNombreCompleto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombreCompletoEmpleado, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblForAlias)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfNombreCortoEmpleado, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
									.addComponent(lblFNacimiento)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(frmtdtxtfldFechanacimientoempleado, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelCentralFormulario.createSequentialGroup()
									.addComponent(lblEstado)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txfEstadoEmpleado, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addComponent(lblEmail)
								.addComponent(lblCiudad))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
								.addComponent(txfCiudadEmpleado, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
								.addComponent(frmtxfCorreoElectronico)))
						.addComponent(lblDireccion, Alignment.LEADING)
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblCodigoPostal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblClaveContable)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txfClaveCuentaContable, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(lblNmerosDeContacto)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAgregarTelefono)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblForRfc)
						.addComponent(txfRfcEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblForCurp)
						.addComponent(txfCurpEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblForNombreCompleto)
						.addComponent(txfNombreCompletoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblForAlias)
						.addComponent(txfNombreCortoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFNacimiento)
						.addComponent(frmtdtxtfldFechanacimientoempleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(frmtxfCorreoElectronico, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblEmail))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEstado)
						.addComponent(txfEstadoEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCiudad)
						.addComponent(txfCiudadEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDireccion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTxaDireccionEmpleado, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigoPostal)
						.addComponent(txfCodigoPostal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClaveContable)
						.addComponent(txfClaveCuentaContable, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNmerosDeContacto)
							.addComponent(btnAgregarTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnEliminarTelefono, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneNumerosTelefonicos, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		tableNumerosTelefonicos = new JTable();
		scrollPaneNumerosTelefonicos.setViewportView(tableNumerosTelefonicos);
		
		JTextArea textAreaDireccionEmpleado = new JTextArea();
		scrollPaneTxaDireccionEmpleado.setViewportView(textAreaDireccionEmpleado);
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
