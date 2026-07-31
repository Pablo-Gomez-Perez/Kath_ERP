package com.kathsoft.kathpos.app.view.proveedor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.model.proveedor.Proveedor;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;
import javax.swing.border.CompoundBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Fr_DatosProveedor extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374525545404673239L;
	private JPanel contentPane;
	private ProveedorController proveedorController = new ProveedorController();
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private JComboBox<String> cmbRFCProveedor;
	private JPanel panelInferiorBotones;
	private JButton btn_Cancelar;
	private Component horizontalStrut_10;
	private JButton btn_Guardar;
	private int indiceProveedor;

	/**
	 * Create the frame.
	 */
	public Fr_DatosProveedor(int tipoOperacion, int indiceProveedor) {

		this.indiceProveedor = indiceProveedor;

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/proveedores.png")));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 512);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel = new JLabel();
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panelSuperiorEtiqueta.add(lblNewLabel);

		if (tipoOperacion == 0) {
			this.setTitle("Nuevo Proveedor");
			lblNewLabel.setText("Agregar nuevo Proveedor");
		} else if (tipoOperacion == 1) {
			this.setTitle("Editar Proveedor");
			lblNewLabel.setText("Editar Proveedor");
		}

		panelCentralFormulario = new JPanel();
		panelCentralFormulario
				.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(new Color(0, 0, 0))));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);
		panelCentralFormulario.setLayout(new BoxLayout(panelCentralFormulario, BoxLayout.Y_AXIS));

		panelInferiorBotones = new JPanel();
		panelInferiorBotones.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		FlowLayout flowLayout_2 = (FlowLayout) panelInferiorBotones.getLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btn_Cancelar.setIcon(
				new ImageIcon(Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btn_Cancelar.setBackground(new Color(205, 92, 92));
		panelInferiorBotones.add(btn_Cancelar);

		horizontalStrut_10 = Box.createHorizontalStrut(20);
		panelInferiorBotones.add(horizontalStrut_10);

		btn_Guardar = new JButton("Guardar");
		btn_Guardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tipoOperacion == 0) {
					// para insertar un nuevo proveedor en la bd
					insertarNuevoProveedor();
				} else if (tipoOperacion == 1) {
					// para actualizar un proveedor existente
					actualizarProveedor();
				}
			}
		});
		btn_Guardar.setBackground(new Color(144, 238, 144));
		btn_Guardar.setIcon(new ImageIcon(
				Fr_DatosProveedor.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		panelInferiorBotones.add(btn_Guardar);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		if(tipoOperacion == 1) {
			this.buscarProveedorPorId();
		}
	}
	
	private boolean validarCamposVacios() {
		
		
		
		return false;
		
	}

	/**
	 * inserta un nuevo registro en la base de datos
	 */
	private void insertarNuevoProveedor() {

		

	}

	private void actualizarProveedor() {

		
	}

	/**
	 * busca en la bse de datos el registro de un proveedor en base a su RFC
	 * 
	 * @param rfc
	 */
	private void buscarProveedorPorRFC(String rfc) {

		

	}
	
	private void buscarProveedorPorId() {
		
		
		
	}

	/**
	 * llena el combobox de los RFC de los proveedores con sus datos
	 * correspondientes consultando la base de datos
	 */
	private void llenarCmbRfcProveedor() {
		this.limpiarCmbProveedor();
		proveedorController.consultarRFCProveedor(this.cmbRFCProveedor);
	}

	/**
	 * borra todos los items o elementos contenidos en el ComboBox de RFC de los
	 * proveedores
	 */
	private void limpiarCmbProveedor() {
		this.cmbRFCProveedor.removeAllItems();
		this.cmbRFCProveedor.updateUI();
	}

	/**
	 * cierra el formulario
	 */
	private void cerrarForm() {
		this.dispose();
	}

}
