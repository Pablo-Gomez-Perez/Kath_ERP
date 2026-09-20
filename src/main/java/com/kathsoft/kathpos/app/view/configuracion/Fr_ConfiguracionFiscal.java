package com.kathsoft.kathpos.app.view.configuracion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.border.EtchedBorder;
import javax.swing.JOptionPane;

import com.kathsoft.kathpos.app.controller.ConfiguracionFiscalController;
import com.kathsoft.kathpos.app.model.configuracion.ConfiguracionFiscal;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

public class Fr_ConfiguracionFiscal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JLabel lblDatosFiscales;
	private JPanel panelCentral;
	private JLabel lblRfcEmisor;
	private JTextField txfRfcEmisor;
	private JLabel lblNombrerazonSocial;
	private JTextField txfNombreRazonSocial;
	private JLabel lblNombreComercial;
	private JTextField txfNombreComercial;
	private JLabel lblClaveRegismenFiscal;
	private JTextField txfClaveRegimenFiscal;
	private JLabel lblRegimenFiscal;
	private JTextField txfRegimenFiscalDescripcion;
	private JLabel lblNumeroDeRegistro;
	private JTextField textField;
	private JPanel panelInferiorBotones;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private final ConfiguracionFiscalController configuracionFiscalController = new ConfiguracionFiscalController();
	private int idConfiguracionFiscal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_ConfiguracionFiscal frame = new Fr_ConfiguracionFiscal();
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
	public Fr_ConfiguracionFiscal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 295);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 204, 0));
		this.contentPane.setBorder(null);
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(14, 14, 216));
		this.contentPane.add(this.panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		this.lblDatosFiscales = new JLabel("Datos fiscales");
		this.lblDatosFiscales.setForeground(new Color(255, 255, 255));
		this.lblDatosFiscales.setFont(new Font("Dialog", Font.BOLD, 16));
		this.panelSuperiorEtiqueta.add(this.lblDatosFiscales);
		
		this.panelCentral = new JPanel();
		this.panelCentral.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		this.panelCentral.setBackground(new Color(255, 204, 0));
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		
		this.lblRfcEmisor = new JLabel("Rfc Emisor");
		
		this.txfRfcEmisor = new JTextField();
		this.txfRfcEmisor.setColumns(10);
		
		this.lblNombrerazonSocial = new JLabel("Nombre/Razon Social");
		
		this.txfNombreRazonSocial = new JTextField();
		this.txfNombreRazonSocial.setColumns(10);
		
		this.lblNombreComercial = new JLabel("Nombre Comercial");
		
		this.txfNombreComercial = new JTextField();
		this.txfNombreComercial.setColumns(10);
		
		this.lblClaveRegismenFiscal = new JLabel("Clave Regismen Fiscal");
		
		this.txfClaveRegimenFiscal = new JTextField();
		this.txfClaveRegimenFiscal.setColumns(10);
		
		this.lblRegimenFiscal = new JLabel("Regimen fiscal");
		
		this.txfRegimenFiscalDescripcion = new JTextField();
		this.txfRegimenFiscalDescripcion.setColumns(10);
		
		this.lblNumeroDeRegistro = new JLabel("Numero de registro de sistema");
		
		this.textField = new JTextField();
		this.textField.setColumns(10);
		GroupLayout gl_panelCentral = new GroupLayout(this.panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(this.lblRfcEmisor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfRfcEmisor, GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(this.lblNombrerazonSocial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfNombreRazonSocial, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(this.lblNombreComercial)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfNombreComercial, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(this.lblClaveRegismenFiscal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfClaveRegimenFiscal, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(this.lblRegimenFiscal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfRegimenFiscalDescripcion, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
						.addGroup(gl_panelCentral.createSequentialGroup()
							.addComponent(this.lblNumeroDeRegistro)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.textField, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentral.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblRfcEmisor)
						.addComponent(this.txfRfcEmisor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNombrerazonSocial)
						.addComponent(this.txfNombreRazonSocial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNombreComercial)
						.addComponent(this.txfNombreComercial, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblClaveRegismenFiscal)
						.addComponent(this.txfClaveRegimenFiscal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblRegimenFiscal)
						.addComponent(this.txfRegimenFiscalDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentral.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNumeroDeRegistro)
						.addComponent(this.textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(103, Short.MAX_VALUE))
		);
		this.panelCentral.setLayout(gl_panelCentral);
		
		this.panelInferiorBotones = new JPanel();
		this.panelInferiorBotones.setBackground(new Color(0, 255, 255));
		FlowLayout flowLayout = (FlowLayout) this.panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.contentPane.add(this.panelInferiorBotones, BorderLayout.SOUTH);
		
		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setBackground(new Color(246, 97, 81));
		this.btnCancelar.addActionListener(e -> this.dispose());
		this.panelInferiorBotones.add(this.btnCancelar);
		
		this.btnGuardar = new JButton("Guardar");
		this.btnGuardar.setBackground(new Color(87, 227, 137));
		this.btnGuardar.addActionListener(e -> this.guardarConfiguracionFiscal());
		this.panelInferiorBotones.add(this.btnGuardar);

		this.cargarConfiguracionFiscal();
	}

	private void cargarConfiguracionFiscal() {
		ConfiguracionFiscal configuracion = this.configuracionFiscalController.getConfiguracionFiscal();
		if (configuracion == null) {
			JOptionPane.showMessageDialog(this,
					"No fue posible obtener la configuración fiscal activa. Verifique que exista el registro inicial en la base de datos.",
					"Configuración fiscal", JOptionPane.WARNING_MESSAGE);
			this.btnGuardar.setEnabled(false);
			return;
		}

		this.idConfiguracionFiscal = configuracion.idConfiguracion();
		this.txfRfcEmisor.setText(valor(configuracion.rfcEmisor()));
		this.txfNombreRazonSocial.setText(valor(configuracion.nombreRazonSocial()));
		this.txfNombreComercial.setText(valor(configuracion.nombreComercial()));
		this.txfClaveRegimenFiscal.setText(valor(configuracion.regimenFiscalClave()));
		this.txfRegimenFiscalDescripcion.setText(valor(configuracion.regimenFiscalDescripcion()));
		this.textField.setText(valor(configuracion.numeroRegistroSistema()));
		this.btnGuardar.setEnabled(true);
	}

	private void guardarConfiguracionFiscal() {
		try {
			ConfiguracionFiscal configuracion = this.construirConfiguracionDesdeFormulario();
			SpResponseModel respuesta = this.configuracionFiscalController.updateConfiguracionFiscal(configuracion);

			if (respuesta == null || respuesta.id() <= 0) {
				String mensaje = respuesta == null ? "No se recibió respuesta al actualizar la configuración fiscal"
						: respuesta.message();
				JOptionPane.showMessageDialog(this, mensaje, "No fue posible guardar",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			JOptionPane.showMessageDialog(this, respuesta.message(), "Configuración fiscal",
					JOptionPane.INFORMATION_MESSAGE);
			this.cargarConfiguracionFiscal();
		} catch (IllegalArgumentException er) {
			JOptionPane.showMessageDialog(this, er.getMessage(), "Datos fiscales inválidos",
					JOptionPane.WARNING_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace(System.err);
			JOptionPane.showMessageDialog(this,
					"Ha ocurrido un error al actualizar la configuración fiscal: " + er.getMessage(),
					"Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private ConfiguracionFiscal construirConfiguracionDesdeFormulario() {
		if (this.idConfiguracionFiscal <= 0) {
			throw new IllegalArgumentException("No existe una configuración fiscal válida para actualizar");
		}

		String rfc = this.txfRfcEmisor.getText().trim().toUpperCase();
		String razonSocial = this.txfNombreRazonSocial.getText().trim();
		String nombreComercial = this.txfNombreComercial.getText().trim();
		String claveRegimen = this.txfClaveRegimenFiscal.getText().trim();
		String descripcionRegimen = this.txfRegimenFiscalDescripcion.getText().trim();
		String numeroRegistro = this.textField.getText().trim();

		if (rfc.isEmpty()) {
			throw new IllegalArgumentException("El RFC del emisor es obligatorio");
		}
		if (rfc.length() != 12 && rfc.length() != 13) {
			throw new IllegalArgumentException("El RFC del emisor debe contener 12 o 13 caracteres");
		}
		if (razonSocial.isEmpty()) {
			throw new IllegalArgumentException("El nombre o razón social es obligatorio");
		}
		if (claveRegimen.length() != 3) {
			throw new IllegalArgumentException("La clave del régimen fiscal debe contener 3 caracteres");
		}
		if (descripcionRegimen.isEmpty()) {
			throw new IllegalArgumentException("La descripción del régimen fiscal es obligatoria");
		}

		return new ConfiguracionFiscal(
				this.idConfiguracionFiscal,
				rfc,
				razonSocial,
				nombreComercial,
				claveRegimen,
				descripcionRegimen,
				numeroRegistro,
				true);
	}

	private static String valor(String valor) {
		return valor == null ? "" : valor;
	}
}

