package com.kathsoft.kathpos.app.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.empleado.Empleado;
import com.kathsoft.kathpos.tools.AppContext;

public class Fr_LogIn extends JFrame {

	private static final long serialVersionUID = -2897933657525076700L;

	private JPanel contentPane;
	private JTextField txfUsuario = new JTextField();
	private JPasswordField pswfContrasenia = new JPasswordField();
	private JLabel lblUsuario = new JLabel("Usuario");
	private JButton btn_cancelar = new JButton("Cancelar");
	private JButton btn_ingresar = new JButton("Ingresar");
	private Sucursal sucursal;

	private JPanel panelImagen = new JPanel() {
		private static final long serialVersionUID = -7434112987658880465L;
		private Image imagen;

		@Override
		public void paint(Graphics g) {
			this.imagen = new ImageIcon(getClass().getResource("/com/kathsoft/kathpos/app/assets/login_ico.png"))
					.getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	};

	private Component verticalStrut_3;
	private JPanel panelCentral;
	private Box verticalBox_1;
	private Component horizontalStrut_3;
	private Box verticalBox;
	private Component verticalStrut_2;
	private Box horizontalBox;
	private Component horizontalStrut;
	private Component verticalStrut;
	private Box horizontalBox_1;
	private JLabel lblContrasenia;
	private Component horizontalStrut_1;
	private Component verticalStrut_1;
	private Component horizontalStrut_4;
	private JPanel panelInferior;
	private FlowLayout flowLayout;

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

	public Fr_LogIn() {
		setResizable(false);
		setType(Type.UTILITY);
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(Fr_LogIn.class.getResource("/com/kathsoft/kathpos/app/assets/login_ico.png")));
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

		horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);

		horizontalBox.add(lblUsuario);

		horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		txfUsuario.setBackground(new Color(204, 255, 255));
		horizontalBox.add(txfUsuario);

		verticalStrut = Box.createVerticalStrut(20);
		verticalBox.add(verticalStrut);

		horizontalBox_1 = Box.createHorizontalBox();
		verticalBox.add(horizontalBox_1);

		lblContrasenia = new JLabel("Contraseña");
		horizontalBox_1.add(lblContrasenia);

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
		btn_cancelar.addActionListener(e -> cerrarForm());
		btn_cancelar.setBackground(new Color(205, 92, 92));

		panelInferior.add(btn_cancelar);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		panelInferior.add(horizontalStrut_2);
		btn_ingresar.setBackground(new Color(0, 204, 51));
		btn_ingresar.addActionListener(e -> logIngFrPrincipal());

		panelInferior.add(btn_ingresar);

		verticalStrut_3 = Box.createVerticalStrut(0);
		verticalBox.add(verticalStrut_3);

		this.pack();
	}

	private void logIngFrPrincipal() {
		String usuario = this.txfUsuario.getText();
		char[] contrasenia = this.pswfContrasenia.getPassword();

		try {
			if (usuario == null || usuario.isBlank()) {
				JOptionPane.showMessageDialog(this, "Debe capturar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (contrasenia == null || contrasenia.length == 0 || new String(contrasenia).isBlank()) {
				JOptionPane.showMessageDialog(this, "Debe capturar la contraseña", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			Empleado empleado = AppContext.loginController.iniciarSesion(usuario.trim(), contrasenia);
			if (empleado == null) {
				JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}

			this.sucursal = AppContext.sucursalController.consultarSucursal(empleado.getIdSucursal());
			if (this.sucursal == null || this.sucursal.getIdSucursal() <= 0) {
				JOptionPane.showMessageDialog(this, "No se pudo obtener la sucursal del empleado", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

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
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} finally {
			Arrays.fill(contrasenia, '\0');
		}
	}

	private void cerrarForm() {
		System.exit(0);
	}
}
