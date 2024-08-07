package com.kathsoft.kathpos.app.view.proveedor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.model.Proveedor;

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
	/**
	 * 
	 * 
	 */

	private JTextField txfRfcProveedor;
	private JPanel contentPane;
	private ProveedorController proveedorController = new ProveedorController();
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblNewLabel;
	private JPanel panelCentralFormulario;
	private Component verticalStrut;
	private Box horizontalBox;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut;
	private JComboBox<String> cmbRFCProveedor;
	private Component horizontalStrut_1;
	private JLabel lblNewLabel_2;
	private JTextField txfCtaContableProveedor;
	private Component horizontalStrut_2;
	private Component verticalStrut_1;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_3;
	private Component horizontalStrut_3;
	private JTextField txfNombreProveedor;
	private Component verticalStrut_2;
	private Box verticalBox;
	private Box horizontalBox_2;
	private JPanel panel;
	private JLabel lblNewLabel_4;
	private Box horizontalBox_3;
	private JTextArea txaDescripcionProveedor;
	private Component verticalStrut_3;
	private Box horizontalBox_4;
	private JLabel lblNewLabel_5;
	private Component horizontalStrut_4;
	private JTextField txfEmailProveedor;
	private Component verticalStrut_4;
	private Box horizontalBox_5;
	private JLabel lblNewLabel_6;
	private Component horizontalStrut_5;
	private JTextField txfEstadoProveedor;
	private Component horizontalStrut_6;
	private JLabel lblNewLabel_7;
	private Component horizontalStrut_7;
	private JTextField txfCiudadProveedor;
	private Component horizontalStrut_8;
	private JLabel lblNewLabel_8;
	private Component horizontalStrut_9;
	private JTextField txfCodigoPostalProveedor;
	private Component verticalStrut_5;
	private Box verticalBox_1;
	private JPanel panel_1;
	private JLabel lblNewLabel_9;
	private Box horizontalBox_6;
	private JTextArea txaDireccionProveedor;
	private JPanel panelInferiorBotones;
	private JButton btn_Cancelar;
	private Component horizontalStrut_10;
	private JButton btn_Guardar;
	private int indiceProveedor;
	private Component verticalStrut_6;

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

		verticalStrut = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut);

		horizontalBox = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox);

		lblNewLabel_1 = new JLabel("RFC");
		horizontalBox.add(lblNewLabel_1);

		horizontalStrut = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut);

		this.txfRfcProveedor = new JTextField();
		txfRfcProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(txfRfcProveedor.getText().length() >= 13) {
					e.consume();
				}
			}
		});
		this.txfRfcProveedor.setColumns(50);
		this.txfRfcProveedor.setMaximumSize(this.txfRfcProveedor.getPreferredSize());
		this.horizontalBox.add(txfRfcProveedor);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut_1);

		lblNewLabel_2 = new JLabel("Cta Contable");
		horizontalBox.add(lblNewLabel_2);

		horizontalStrut_2 = Box.createHorizontalStrut(5);
		horizontalBox.add(horizontalStrut_2);

		txfCtaContableProveedor = new JTextField();
		txfCtaContableProveedor.setEnabled(false);
		txfCtaContableProveedor.setEditable(false);
		horizontalBox.add(txfCtaContableProveedor);
		txfCtaContableProveedor.setColumns(20);
		this.txfCtaContableProveedor.setMaximumSize(this.txfCtaContableProveedor.getPreferredSize());

		verticalStrut_1 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_1);

		horizontalBox_1 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_1);

		lblNewLabel_3 = new JLabel("Nombre");
		horizontalBox_1.add(lblNewLabel_3);

		horizontalStrut_3 = Box.createHorizontalStrut(5);
		horizontalBox_1.add(horizontalStrut_3);

		txfNombreProveedor = new JTextField();
		horizontalBox_1.add(txfNombreProveedor);
		txfNombreProveedor.setColumns(80);
		this.txfNombreProveedor.setMaximumSize(this.txfNombreProveedor.getPreferredSize());

		verticalStrut_2 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_2);

		verticalBox = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox);

		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBackground(new Color(255, 215, 0));
		horizontalBox_2.add(panel);

		lblNewLabel_4 = new JLabel("Descripcion");
		panel.add(lblNewLabel_4);

		horizontalBox_3 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_3);

		txaDescripcionProveedor = new JTextArea();
		txaDescripcionProveedor.setLineWrap(true);
		horizontalBox_3.add(txaDescripcionProveedor);

		verticalStrut_3 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_3);

		horizontalBox_4 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_4);

		lblNewLabel_5 = new JLabel("Email");
		horizontalBox_4.add(lblNewLabel_5);

		horizontalStrut_4 = Box.createHorizontalStrut(5);
		horizontalBox_4.add(horizontalStrut_4);

		txfEmailProveedor = new JTextField();
		horizontalBox_4.add(txfEmailProveedor);
		txfEmailProveedor.setColumns(80);
		this.txfEmailProveedor.setMaximumSize(this.txfEmailProveedor.getPreferredSize());

		verticalStrut_4 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_4);

		horizontalBox_5 = Box.createHorizontalBox();
		panelCentralFormulario.add(horizontalBox_5);

		lblNewLabel_6 = new JLabel("Estado");
		horizontalBox_5.add(lblNewLabel_6);

		horizontalStrut_5 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_5);

		txfEstadoProveedor = new JTextField();
		horizontalBox_5.add(txfEstadoProveedor);
		txfEstadoProveedor.setColumns(20);
		this.txfEstadoProveedor.setMaximumSize(this.txfEstadoProveedor.getPreferredSize());

		horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_6);

		lblNewLabel_7 = new JLabel("Ciudad");
		horizontalBox_5.add(lblNewLabel_7);

		horizontalStrut_7 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_7);

		txfCiudadProveedor = new JTextField();
		horizontalBox_5.add(txfCiudadProveedor);
		txfCiudadProveedor.setColumns(20);
		this.txfCiudadProveedor.setMaximumSize(this.txfCiudadProveedor.getPreferredSize());

		horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalBox_5.add(horizontalStrut_8);

		lblNewLabel_8 = new JLabel("C.P.");
		horizontalBox_5.add(lblNewLabel_8);

		horizontalStrut_9 = Box.createHorizontalStrut(5);
		horizontalBox_5.add(horizontalStrut_9);

		txfCodigoPostalProveedor = new JTextField();
		txfCodigoPostalProveedor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (ch < '0' || ch > '9' || txfCodigoPostalProveedor.getText().length() >= 5) {
					e.consume();
				}
			}
		});
		horizontalBox_5.add(txfCodigoPostalProveedor);
		txfCodigoPostalProveedor.setColumns(15);
		this.txfCodigoPostalProveedor.setMaximumSize(this.txfCodigoPostalProveedor.getPreferredSize());

		verticalStrut_5 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_5);

		verticalBox_1 = Box.createVerticalBox();
		panelCentralFormulario.add(verticalBox_1);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		verticalBox_1.add(panel_1);

		lblNewLabel_9 = new JLabel("Direccion");
		panel_1.add(lblNewLabel_9);

		horizontalBox_6 = Box.createHorizontalBox();
		verticalBox_1.add(horizontalBox_6);

		txaDireccionProveedor = new JTextArea();
		txaDireccionProveedor.setLineWrap(true);
		horizontalBox_6.add(txaDireccionProveedor);

		verticalStrut_6 = Box.createVerticalStrut(20);
		panelCentralFormulario.add(verticalStrut_6);

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
		
		if (this.txfRfcProveedor.getText() == null || this.txfRfcProveedor.getText().isEmpty()
				|| this.txfRfcProveedor.getText() == "" || this.txfRfcProveedor.getText().equals("")
				|| this.txfRfcProveedor.getText().length() < 1) {
			return true;
		}


		if (this.txfNombreProveedor.getText() == null || this.txfNombreProveedor.getText().isEmpty()
				|| this.txfNombreProveedor.getText() == "" || this.txfNombreProveedor.getText().equals("")
				|| this.txfNombreProveedor.getText().length() < 1) {
			return true;
		}

		if (this.txfEmailProveedor.getText() == null || this.txfEmailProveedor.getText().isEmpty()
				|| this.txfEmailProveedor.getText() == "" || this.txfEmailProveedor.getText().equals("")
				|| this.txfEmailProveedor.getText().length() < 1) {
			return true;
		}

		if (this.txfEstadoProveedor.getText() == null || this.txfEstadoProveedor.getText().isEmpty()
				|| this.txfEstadoProveedor.getText() == "" || this.txfEstadoProveedor.getText().equals("")
				|| this.txfEstadoProveedor.getText().length() < 1) {
			return true;
		}

		if (this.txfCiudadProveedor.getText() == null || this.txfCiudadProveedor.getText().isEmpty()
				|| this.txfCiudadProveedor.getText() == "" || this.txfCiudadProveedor.getText().equals("")
				|| this.txfCiudadProveedor.getText().length() < 1) {
			return true;
		}

		if (this.txfCodigoPostalProveedor.getText() == null || this.txfCodigoPostalProveedor.getText().isEmpty()
				|| this.txfCodigoPostalProveedor.getText() == "" || this.txfCodigoPostalProveedor.getText().equals("")
				|| this.txfCodigoPostalProveedor.getText().length() < 1) {			
			return true;
		}

		if (this.txaDireccionProveedor.getText() == null || this.txaDireccionProveedor.getText().isEmpty()
				|| this.txaDireccionProveedor.getText() == "" || this.txaDireccionProveedor.getText().equals("")
				|| this.txaDireccionProveedor.getText().length() < 1) {
			return true;
		}
		
		return false;
		
	}

	/**
	 * inserta un nuevo registro en la base de datos
	 */
	private void insertarNuevoProveedor() {

		Proveedor prv = new Proveedor();
		
		if(this.validarCamposVacios() == true) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		try {

			prv.setRfc(this.txfRfcProveedor.getText());
			prv.setNombre(this.txfNombreProveedor.getText());
			prv.setDescripcion(this.txaDescripcionProveedor.getText());
			prv.setEmail(this.txfEmailProveedor.getText());
			prv.setEstado(this.txfEstadoProveedor.getText());
			prv.setCiudad(this.txfCiudadProveedor.getText());
			prv.setDireccion(this.txaDireccionProveedor.getText());
			prv.setCodigoPostal(this.txfCodigoPostalProveedor.getText());

			// System.out.println(prv.toString());

			proveedorController.insertarNuevoProveedor(prv);
			
			JOptionPane.showMessageDialog(this, "Registro guardado", "Exito", JOptionPane.INFORMATION_MESSAGE);
			
			this.cerrarForm();

		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al intentar guardar: " + er.getMessage(), "error", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void actualizarProveedor() {

		Proveedor prv = new Proveedor();
		
		if(this.validarCamposVacios() == true) {
			JOptionPane.showMessageDialog(this, "No deje campos vacios", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		try {

			prv.setId(this.indiceProveedor);
			prv.setNombre(this.txfNombreProveedor.getText());
			prv.setDescripcion(this.txaDescripcionProveedor.getText());
			prv.setEmail(this.txfEmailProveedor.getText());
			prv.setEstado(this.txfEstadoProveedor.getText());
			prv.setCiudad(this.txfCiudadProveedor.getText());
			prv.setDireccion(this.txaDireccionProveedor.getText());
			prv.setCodigoPostal(this.txfCodigoPostalProveedor.getText());

			proveedorController.actualizarProveedor(prv);
			
			JOptionPane.showMessageDialog(this, "Registro actualizado", "Exito", JOptionPane.INFORMATION_MESSAGE);
			
			this.cerrarForm();
			
		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error en proceso: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error en proceso: " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * busca en la bse de datos el registro de un proveedor en base a su RFC
	 * 
	 * @param rfc
	 */
	private void buscarProveedorPorRFC(String rfc) {

		try {

			Proveedor prv = proveedorController.buscarProveedorPorRFC(rfc);

			this.indiceProveedor = prv.getId();
			this.txfCtaContableProveedor.setText(prv.getClaveCuentaContable());
			this.txfNombreProveedor.setText(prv.getNombre());
			this.txaDescripcionProveedor.setText(prv.getDescripcion());
			this.txfEmailProveedor.setText(prv.getEmail());
			this.txfEstadoProveedor.setText(prv.getEstado());
			this.txfCiudadProveedor.setText(prv.getCiudad());
			this.txfCodigoPostalProveedor.setText(prv.getCodigoPostal());
			this.txaDireccionProveedor.setText(prv.getDireccion());

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error en proceso " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error en procesos " + er.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
	
	private void buscarProveedorPorId() {
		
		Proveedor prv = proveedorController.buscarProveedorPorId(this.indiceProveedor);
		
		this.txfRfcProveedor.setText(prv.getRfc());
		this.txfCtaContableProveedor.setText(prv.getClaveCuentaContable());
		this.txfNombreProveedor.setText(prv.getNombre());
		this.txaDescripcionProveedor.setText(prv.getDescripcion());
		this.txfEmailProveedor.setText(prv.getEmail());
		this.txfEstadoProveedor.setText(prv.getEstado());
		this.txfCiudadProveedor.setText(prv.getCiudad());
		this.txfCodigoPostalProveedor.setText(prv.getCodigoPostal());
		this.txaDireccionProveedor.setText(prv.getDireccion());
		
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
