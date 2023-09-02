package com.kathsoft.kathpos.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.controller.EmpleadoController;
import com.kathsoft.kathpos.app.controller.SucursalController;
import com.kathsoft.kathpos.app.model.Empleado;
import com.kathsoft.kathpos.app.model.Sucursal;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Fr_LogIn extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2897933657525076700L;
	/**
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPasswordField pswfContrasenia = new JPasswordField();
	private JComboBox<String> jcmbUsuarios = new JComboBox<String>();
	private JLabel lblNewLabel = new JLabel("Usuario");
	private JButton btn_cancelar = new JButton("Cancelar");
	private JButton btn_ingresar = new JButton("Ingresar");
	private EmpleadoController emplController = new EmpleadoController();
	private SucursalController sucursalController = new SucursalController();
	private Empleado empl = new Empleado();
	private Sucursal sucursal;
	//============================================================================================	
	//============================================================================================
	// panel que agrega una imagen al formulario
	private JPanel panelImagen = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7434112987658880465L;
		/**
		 * 
		 */
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			this.imagen = new ImageIcon(getClass().getResource("/com/kathsoft/kathpos/app/resources/login_ico.png"))
					.getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	};
	//============================================================================================
	//============================================================================================
	private JComboBox<String> cmbSucursal;
	private Component verticalStrut_3;
	private JPanel panelCentral;
	private Box verticalBox_1;
	private Component horizontalStrut_3;
	private Box verticalBox;
	private Component verticalStrut_2;
	private Box horizontalBox_2;
	private JLabel lblNewLabel_2;
	private Component horizontalStrut_5;
	private Box horizontalBox;
	private Component horizontalStrut;
	private Component verticalStrut;
	private Box horizontalBox_1;
	private JLabel lblNewLabel_1;
	private Component horizontalStrut_1;
	private Component verticalStrut_1;
	private Component horizontalStrut_4;
	private JPanel panelInferior;
	private FlowLayout flowLayout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_LogIn frame = new Fr_LogIn();
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
	public Fr_LogIn() {
		setResizable(false);
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_LogIn.class.getResource("/com/kathsoft/kathpos/app/resources/login_ico.png")));
		setTitle("Acceso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 418, 164);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		panelCentral = new JPanel();
		panelCentral.setBackground(new Color(255, 204, 0));
		panelCentral.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.X_AXIS));

		verticalBox_1 = Box.createVerticalBox();
		verticalBox_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCentral.add(verticalBox_1);

		verticalBox_1.add(panelImagen);

		horizontalStrut_3 = Box.createHorizontalStrut(150);
		panelImagen.add(horizontalStrut_3);

		verticalBox = Box.createVerticalBox();
		verticalBox.setBorder(new EmptyBorder(0, 10, 0, 10));
		panelCentral.add(verticalBox);
		
		verticalStrut_2 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_2);
		
		horizontalBox_2 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_2);
		
		lblNewLabel_2 = new JLabel("Sucursal");
		horizontalBox_2.add(lblNewLabel_2);
		
		horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalBox_2.add(horizontalStrut_5);
		
		cmbSucursal = new JComboBox<String>();
		this.llenarCmbSucursales();
		cmbSucursal.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				sucursal = sucursalController.consultarSucursalPorId(cmbSucursal.getSelectedIndex() + 1);
				//System.out.println(sucursal.toString());
				llenarCmbEmpleados();
			}
		});
		
		
		horizontalBox_2.add(cmbSucursal);
		
		verticalStrut_3 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_3);

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		horizontalBox.add(lblNewLabel);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		horizontalBox.add(jcmbUsuarios);

		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		lblNewLabel_1 = new JLabel("Contraseña");
		horizontalBox_1.add(lblNewLabel_1);

		horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_1);
		pswfContrasenia.setBackground(new Color(204, 255, 255));

		horizontalBox_1.add(pswfContrasenia);

		verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		horizontalStrut_4 = Box.createHorizontalStrut(300);
		verticalBox.add(horizontalStrut_4);

		panelInferior = new JPanel();
		panelInferior.setBackground(new Color(51, 153, 255));
		flowLayout = (FlowLayout) panelInferior.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelInferior, BorderLayout.SOUTH);
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarForm();
			}
		});
		btn_cancelar.setBackground(new Color(205, 92, 92));
		

		panelInferior.add(btn_cancelar);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelInferior.add(horizontalStrut_2);
		btn_ingresar.setBackground(new Color(0, 204, 51));
		btn_ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				logIngFrPrincipal();
			}
		});

		panelInferior.add(btn_ingresar);

		

		this.pack();
	}
	
	
	private void llenarCmbSucursales() {
		this.borrarElementosJcmb(cmbSucursal);
		sucursalController.consultarSucursales(cmbSucursal);
		this.cmbSucursal.setSelectedIndex(0);
	}
	
	private void borrarElementosJcmb(JComboBox<String> cmb) {
		cmb.removeAllItems();
		cmb.updateUI();
	}
	
	private void llenarCmbEmpleados() {
		borrarElementosJcmb(this.jcmbUsuarios);
		this.emplController.consultaNombresCortosEmpleados(this.jcmbUsuarios,this.cmbSucursal.getSelectedIndex() + 1);
	}
	
	/**
	 * validación de credeciales de acceso y entrada al sistema
	 */
	private void logIngFrPrincipal() {
		
		String contra = "";
		char[] pswdCaracteres = this.pswfContrasenia.getPassword();
		
		for(char c: pswdCaracteres) {
			contra = contra + c;
		}
		
		empl.setNombreCorto(this.jcmbUsuarios.getSelectedItem().toString());
		empl.setPassword(contra);			
		
		if (emplController.validarIngreso(empl) == false || contra.isEmpty()) {
			
			JOptionPane.showMessageDialog(this, "Error de acceso", "Error", JOptionPane.ERROR_MESSAGE);
			
		} else {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Fr_principal frame = new Fr_principal(sucursal);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			
			this.dispose();
		}
	}
	
	/**
	 * cierra el formulario y sale del sistema por completo
	 */
	private void cerrarForm() {
		System.exit(0);
	}
	
}
