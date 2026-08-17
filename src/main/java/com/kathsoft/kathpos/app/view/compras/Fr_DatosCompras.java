package com.kathsoft.kathpos.app.view.compras;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.model.ArticulosPorVentas;
import com.kathsoft.kathpos.app.model.interfaces.IListadoArticulosAcciones;
import javax.swing.border.TitledBorder;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class Fr_DatosCompras extends JFrame implements IListadoArticulosAcciones{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralContenedor;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel;
	private JPanel panelDatosCompra;
	private JButton btnGuardar;
	private JButton btnCancelar;
	private JPanel panelDatosFactura;
	private JPanel panelDatosControlInterno;
	private JLabel lblFolioFactura;
	private JTextField txfFolioFactura;
	private JLabel lblFechaFactura;
	private JFormattedTextField formattedTextFieldFechaFactura;
	private JLabel lblFechaDeCompra;
	private JFormattedTextField formattedTextFieldFechaDeCompra;
	private JLabel lblProveedor;
	private JComboBox comboBoxProveedor;
	private JLabel lblIdCompra;
	private JTextField txfIdCompra;
	private JButton btnBuscar;
	private JLabel lblRecibe;
	private JComboBox comboBoxEmpleado;
	private JPanel panelTipoDeCompra;
	private JRadioButton rdbtnCredito;
	private JRadioButton rdbtnContado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fr_DatosCompras frame = new Fr_DatosCompras();
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
	public Fr_DatosCompras() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 823, 600);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(0, 51, 153));
		this.contentPane.add(this.panelSuperiorEtiqueta, BorderLayout.NORTH);
		
		this.lblNewLabel = new JLabel("Compras");
		this.lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		this.lblNewLabel.setForeground(new Color(255, 255, 255));
		this.panelSuperiorEtiqueta.add(this.lblNewLabel);
		
		this.panelCentralContenedor = new JPanel();
		this.contentPane.add(this.panelCentralContenedor, BorderLayout.CENTER);
		
		this.panelDatosCompra = new JPanel();
		this.panelDatosCompra.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Datos de la compra", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panelCentralContenedor = new GroupLayout(this.panelCentralContenedor);
		gl_panelCentralContenedor.setHorizontalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addComponent(this.panelDatosCompra, GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
		);
		gl_panelCentralContenedor.setVerticalGroup(
			gl_panelCentralContenedor.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralContenedor.createSequentialGroup()
					.addComponent(this.panelDatosCompra, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(360, Short.MAX_VALUE))
		);
		this.panelDatosCompra.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.panelDatosFactura = new JPanel();
		this.panelDatosFactura.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Provedor y factura", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 102)));
		this.panelDatosCompra.add(this.panelDatosFactura);
		
		this.lblFolioFactura = new JLabel("Folio Factura");
		
		this.txfFolioFactura = new JTextField();
		this.lblFolioFactura.setLabelFor(this.txfFolioFactura);
		this.txfFolioFactura.setColumns(10);
		
		this.lblFechaFactura = new JLabel("Fecha Factura");
		
		this.formattedTextFieldFechaFactura = new JFormattedTextField();
		this.lblFechaFactura.setLabelFor(this.formattedTextFieldFechaFactura);
		
		this.lblFechaDeCompra = new JLabel("Fecha de Compra");
		
		this.formattedTextFieldFechaDeCompra = new JFormattedTextField();
		this.lblFechaDeCompra.setLabelFor(this.formattedTextFieldFechaDeCompra);
		
		this.lblProveedor = new JLabel("Proveedor");
		
		this.comboBoxProveedor = new JComboBox();
		GroupLayout gl_panelDatosFactura = new GroupLayout(this.panelDatosFactura);
		gl_panelDatosFactura.setHorizontalGroup(
			gl_panelDatosFactura.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosFactura.createSequentialGroup()
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblFolioFactura)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfFolioFactura, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblFechaFactura)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaFactura, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblFechaDeCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.formattedTextFieldFechaDeCompra, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
						.addGroup(gl_panelDatosFactura.createSequentialGroup()
							.addComponent(this.lblProveedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.comboBoxProveedor, 0, 306, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelDatosFactura.setVerticalGroup(
			gl_panelDatosFactura.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosFactura.createSequentialGroup()
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFolioFactura)
						.addComponent(this.txfFolioFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFechaFactura)
						.addComponent(this.formattedTextFieldFechaFactura, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblFechaDeCompra)
						.addComponent(this.formattedTextFieldFechaDeCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosFactura.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblProveedor)
						.addComponent(this.comboBoxProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(32, Short.MAX_VALUE))
		);
		this.panelDatosFactura.setLayout(gl_panelDatosFactura);
		
		this.panelDatosControlInterno = new JPanel();
		this.panelDatosControlInterno.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Control Interno", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(51, 51, 51)));
		this.panelDatosCompra.add(this.panelDatosControlInterno);
		
		this.lblIdCompra = new JLabel("ID Compra");
		
		this.txfIdCompra = new JTextField();
		this.txfIdCompra.setColumns(10);
		
		this.btnBuscar = new JButton("Buscar");
		
		this.lblRecibe = new JLabel("Recibe");
		
		this.comboBoxEmpleado = new JComboBox();
		
		this.panelTipoDeCompra = new JPanel();
		FlowLayout flowLayoutPanelTipoCompra = (FlowLayout) this.panelTipoDeCompra.getLayout();
		flowLayoutPanelTipoCompra.setAlignment(FlowLayout.LEFT);
		flowLayoutPanelTipoCompra.setVgap(0);
		flowLayoutPanelTipoCompra.setHgap(0);
		this.panelTipoDeCompra.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Tipo de compra", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		GroupLayout gl_panelDatosControlInterno = new GroupLayout(this.panelDatosControlInterno);
		gl_panelDatosControlInterno.setHorizontalGroup(
			gl_panelDatosControlInterno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelDatosControlInterno.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelTipoDeCompra, GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE)
						.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
							.addComponent(this.lblIdCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfIdCompra)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.btnBuscar)
							.addGap(97))
						.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
							.addComponent(this.lblRecibe)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.comboBoxEmpleado, 0, 315, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panelDatosControlInterno.setVerticalGroup(
			gl_panelDatosControlInterno.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelDatosControlInterno.createSequentialGroup()
					.addGroup(gl_panelDatosControlInterno.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblIdCompra)
						.addComponent(this.txfIdCompra, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.btnBuscar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelDatosControlInterno.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblRecibe)
						.addComponent(this.comboBoxEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.panelTipoDeCompra, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
		);
		
		this.rdbtnContado = new JRadioButton("Contado");
		this.panelTipoDeCompra.add(this.rdbtnContado);
		
		this.rdbtnCredito = new JRadioButton("Credito");
		this.panelTipoDeCompra.add(this.rdbtnCredito);
		this.panelDatosControlInterno.setLayout(gl_panelDatosControlInterno);
		this.panelCentralContenedor.setLayout(gl_panelCentralContenedor);
		
		this.panelInferiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) this.panelInferiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		this.contentPane.add(this.panelInferiorBotones, BorderLayout.SOUTH);
		
		this.btnCancelar = new JButton("Cancelar");
		this.panelInferiorBotones.add(this.btnCancelar);
		
		this.btnGuardar = new JButton("Guardar");
		this.panelInferiorBotones.add(this.btnGuardar);

	}

	@Override
	public void listarArticuloDesdeConsulta(Object[] articulo, ArticulosPorVentas art) {
		// TODO Auto-generated method stub
		
	}
}
