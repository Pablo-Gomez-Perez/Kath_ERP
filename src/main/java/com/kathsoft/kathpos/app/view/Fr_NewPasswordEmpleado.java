package com.kathsoft.kathpos.app.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import javax.naming.ldap.ManageReferralControl;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Component;
import javax.swing.JPasswordField;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import com.kathsoft.kathpos.app.model.Empleado;
import com.kathsoft.kathpos.app.controller.EmpleadoController;

public class Fr_NewPasswordEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1378917697295676860L;
	/**
	 * 
	 * 
	 */
	private JPanel contentPane;
	private JPasswordField pswf_Password1;
	private JPasswordField pswf_Password2;
	private EmpleadoController empleadoController = new EmpleadoController();
	private String rfcEmpleado;
	// ============================================================================================
	// ============================================================================================
	// panel que agrega una imagen al formulario
	private JPanel panelImagen = new JPanel() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -7605816442779719273L;
		/**
		 * 
		 */
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			this.imagen = new ImageIcon(getClass().getResource("/com/kathsoft/kathpos/app/resources/Contrass.jpg"))
					.getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	};

	/**
	 * Create the frame.
	 */
	public Fr_NewPasswordEmpleado(String rfcEmpleado) {
		setType(Type.UTILITY);
		setTitle("Nueva contraseña");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 552, 162);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelCentralContenedor = new JPanel();
		panelCentralContenedor.setBackground(new Color(255, 204, 0));
		contentPane.add(panelCentralContenedor, BorderLayout.CENTER);
		panelCentralContenedor.setLayout(new BoxLayout(panelCentralContenedor, BoxLayout.X_AXIS));

		Box verticalBox_1 = Box.createVerticalBox();
		panelCentralContenedor.add(verticalBox_1);

		verticalBox_1.add(panelImagen);

		Component horizontalStrut_3 = Box.createHorizontalStrut(100);
		panelImagen.add(horizontalStrut_3);

		Box verticalBox = Box.createVerticalBox();
		panelCentralContenedor.add(verticalBox);

		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		JLabel lblNewLabel = new JLabel("Ingrese la nueva contraseña");
		horizontalBox.add(lblNewLabel);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);

		pswf_Password1 = new JPasswordField();
		pswf_Password1.setColumns(35);
		horizontalBox.add(pswf_Password1);

		Component verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		JLabel lblNewLabel_1 = new JLabel("Confirme la nueva contraseña");
		horizontalBox_1.add(lblNewLabel_1);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalBox_1.add(horizontalStrut_2);

		pswf_Password2 = new JPasswordField();
		pswf_Password2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				validarContrasenia();
			}
		});
		pswf_Password2.setColumns(35);
		horizontalBox_1.add(pswf_Password2);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut_1);

		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(51, 153, 255));
		FlowLayout flowLayout = (FlowLayout) panelBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		JButton btn_Cancelar = new JButton("Cancelar");
		btn_Cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cerrarVentana();
			}
		});
		btn_Cancelar.setBackground(new Color(205, 92, 92));
		panelBotones.add(btn_Cancelar);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelBotones.add(horizontalStrut_1);

		JButton btn_Guardar = new JButton("Guardar");
		btn_Guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualizarContrasenia();
			}
		});
		btn_Guardar.setBackground(new Color(0, 204, 51));
		panelBotones.add(btn_Guardar);

		this.pack();
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.rfcEmpleado = rfcEmpleado;
	}

	/**
	 * Valida que las contraseñas introducidas en ambos campos del formulario sean
	 * identicas y modifica las propiedades de los componentes ya sea que coincidan
	 * o no.
	 * 
	 * @author PABLO
	 * @return {@code true} si las contraseñas coinciden
	 */
	private boolean validarContrasenia() {
		
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		
		String psw1 = "";
		String psw2 = "";

		char[] caracteresPassword1 = this.pswf_Password1.getPassword();
		char[] caracteresPassword2 = this.pswf_Password2.getPassword();

		if (caracteresPassword1.length < 1) {
			return false;
		}

		for (int i = 0; i < caracteresPassword1.length; i++) {
			sb1.append(caracteresPassword1[i]); 
		}

		for (int i = 0; i < caracteresPassword2.length; i++) {
			sb2.append(caracteresPassword2[i]);
		}
		
		psw1 = sb1.toString();
		psw2 = sb2.toString();

		if (!psw1.equals(psw2) || psw1.length() != psw2.length()) {
			this.pswf_Password2.setBackground(new Color(233, 76, 76));
			return false;
		} else {
			this.pswf_Password2.setBackground(new Color(76, 233, 76));
			return true;
		}
	}

	private void actualizarContrasenia() {

		if (!validarContrasenia()) {
			JOptionPane.showMessageDialog(this, "Ha ocurrido un error, verifique la contraseña y no deje campos vacios",
					"Kath-Pos Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Empleado empl = new Empleado();
		String psw1 = "";

		char[] caracteresPassword1 = this.pswf_Password1.getPassword();

		for (int i = 0; i < caracteresPassword1.length; i++) {
			psw1 += caracteresPassword1[i];
		}

		try {
			empl.setPassword(psw1);
			empl.setRfc(rfcEmpleado);
			empleadoController.actualizarContrasenia(empl);
			
			JOptionPane.showMessageDialog(this, "Contraseña actualizada", 
					"Kat-Pos - Info", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	/**
	 * Cierra la ventana sin detener la a aplicación.
	 */
	private void cerrarVentana() {
		this.dispose();
	}
}
