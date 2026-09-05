package com.kathsoft.kathpos.app.view.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
import javax.swing.DropMode;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

public class PanelVentas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelEtiquetaVentas;
	private JLabel lblNewLabel_22;
	private JPanel panelVentasCentral;
	private JPanel panelVentasCentralBotones;
	private ButtonGroup btnRadioGroupOrdernarVentas;
	private JButton btNuevaVenta;
	private Sucursal sucursal;
	private JButton btnExportarVentasExcel;
	private JScrollPane scrollPaneTablaVentas;
	private DefaultTableModel modelTablaVentas;
	private JTable tablaVentas;
	private JPanel panelVentasCentralBuscar;
	private ButtonGroup btnRadioGroupBuscarVentas;
	private JTextField textField;
	private JButton btnBuscarVenta;
	private JLabel lblFInicial;
	private JFormattedTextField formattedTextFieldFechaInicial;
	private JLabel lblFfinal;
	private JFormattedTextField formattedTextFieldFechaFinal;
	private JLabel lblBuscarPor;
	private JComboBox comboBoxBuscarPor;
	private JLabel lblOrdernarPor;
	private JComboBox comboBoxBuscarPor_1;

	/**
	 * Create the panel.
	 */
	public PanelVentas(Sucursal sucursal) {

		this.sucursal = sucursal;

		this.setBackground(new Color(255, 215, 0));
		this.setLayout(new BorderLayout(0, 0));

		this.panelEtiquetaVentas = new JPanel();
		this.panelEtiquetaVentas.setBackground(new Color(0, 0, 128));
		this.add(panelEtiquetaVentas, BorderLayout.NORTH);

		this.lblNewLabel_22 = new JLabel("Ventas");
		this.lblNewLabel_22.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.lblNewLabel_22.setForeground(new Color(255, 255, 255));
		this.panelEtiquetaVentas.add(lblNewLabel_22);

		this.panelVentasCentral = new JPanel();
		this.panelVentasCentral.setBorder(null);
		this.panelVentasCentral.setBackground(new Color(255, 215, 0));
		this.add(panelVentasCentral, BorderLayout.CENTER);

		this.panelVentasCentralBotones = new JPanel();
		this.panelVentasCentralBotones.setBackground(new Color(255, 215, 0));
		FlowLayout flowLayout_5 = (FlowLayout) panelVentasCentralBotones.getLayout();
		flowLayout_5.setAlignment(FlowLayout.RIGHT);

		this.btnRadioGroupOrdernarVentas = new ButtonGroup();

		btNuevaVenta = new JButton("Punto de venta");
		btNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abrirFormVentas(sucursal.getIdSucursal());
			}
		});
		btNuevaVenta
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/ventas.png")));
		btNuevaVenta.setBackground(new Color(152, 251, 152));
		panelVentasCentralBotones.add(btNuevaVenta);

		btnExportarVentasExcel = new JButton("Exportar a Excel");
		btnExportarVentasExcel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportarVentaExcel();
			}
		});
		btnExportarVentasExcel.setIcon(
				new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExportarVentasExcel.setBackground(new Color(102, 205, 170));
		panelVentasCentralBotones.add(btnExportarVentasExcel);

		scrollPaneTablaVentas = new JScrollPane();

		modelTablaVentas = new DefaultTableModel();
		tablaVentas = new JTable();

		modelTablaVentas.addColumn("Folio");
		modelTablaVentas.addColumn("Fecha");
		modelTablaVentas.addColumn("Tipo");
		modelTablaVentas.addColumn("Atendió");
		modelTablaVentas.addColumn("Cliente");
		modelTablaVentas.addColumn("Subtotal");
		modelTablaVentas.addColumn("IVA");
		modelTablaVentas.addColumn("Total");
		modelTablaVentas.addColumn("Vigente");
		// modelTablaVentas.addColumn("Edit");

		tablaVentas.setModel(modelTablaVentas);
		scrollPaneTablaVentas.setViewportView(tablaVentas);

		// se remueve el editor del jtable de ventas
		DataTools.removerEditorDeTabla(tablaVentas, modelTablaVentas);

		tablaVentas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		panelVentasCentralBuscar = new JPanel();
		panelVentasCentralBuscar.setBackground(new Color(255, 215, 0));

		this.textField = new JTextField();
		this.textField.setColumns(10);

		this.btnBuscarVenta = new JButton("Buscar");
		this.btnBuscarVenta.setIcon(
				new ImageIcon(PanelVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		this.btnBuscarVenta.setFont(new Font("Dialog", Font.BOLD, 13));
		this.btnBuscarVenta.setBackground(new Color(184, 134, 11));

		this.lblFInicial = new JLabel("F. inicial");

		this.formattedTextFieldFechaInicial = new JFormattedTextField();

		this.lblFfinal = new JLabel("F.Final");

		this.formattedTextFieldFechaFinal = new JFormattedTextField();

		this.lblBuscarPor = new JLabel("Buscar por");

		this.comboBoxBuscarPor = new JComboBox();

		this.lblOrdernarPor = new JLabel("Ordernar por");

		this.comboBoxBuscarPor_1 = new JComboBox();
		GroupLayout gl_panelVentasCentralBuscar = new GroupLayout(this.panelVentasCentralBuscar);
		gl_panelVentasCentralBuscar.setHorizontalGroup(gl_panelVentasCentralBuscar
				.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelVentasCentralBuscar.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelVentasCentralBuscar.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelVentasCentralBuscar.createSequentialGroup()
										.addComponent(this.textField, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblFInicial)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.formattedTextFieldFechaInicial, GroupLayout.DEFAULT_SIZE,
												100, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblFfinal)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.formattedTextFieldFechaFinal, GroupLayout.DEFAULT_SIZE, 100,
												Short.MAX_VALUE))
								.addGroup(Alignment.TRAILING, gl_panelVentasCentralBuscar.createSequentialGroup()
										.addComponent(this.lblBuscarPor).addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.comboBoxBuscarPor, 0, 186, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblOrdernarPor)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(this.comboBoxBuscarPor_1, 0, 186, Short.MAX_VALUE).addGap(89)
										.addComponent(this.btnBuscarVenta, GroupLayout.PREFERRED_SIZE, 103,
												GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		gl_panelVentasCentralBuscar.setVerticalGroup(gl_panelVentasCentralBuscar.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVentasCentralBuscar.createSequentialGroup().addContainerGap()
						.addGroup(gl_panelVentasCentralBuscar.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.formattedTextFieldFechaFinal, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblFfinal)
								.addComponent(this.formattedTextFieldFechaInicial, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblFInicial))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panelVentasCentralBuscar.createParallelGroup(Alignment.BASELINE)
								.addComponent(this.btnBuscarVenta, GroupLayout.PREFERRED_SIZE, 30,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblBuscarPor)
								.addComponent(this.comboBoxBuscarPor, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(this.lblOrdernarPor).addComponent(this.comboBoxBuscarPor_1,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		this.panelVentasCentralBuscar.setLayout(gl_panelVentasCentralBuscar);

		btnRadioGroupBuscarVentas = new ButtonGroup();

		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaVentasColumnsWidth, tablaVentas);
		GroupLayout gl_panelVentasCentral = new GroupLayout(this.panelVentasCentral);
		gl_panelVentasCentral.setHorizontalGroup(gl_panelVentasCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVentasCentral.createSequentialGroup().addGroup(gl_panelVentasCentral
						.createParallelGroup(Alignment.LEADING)
						.addComponent(this.panelVentasCentralBotones, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(this.scrollPaneTablaVentas, GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
						.addComponent(this.panelVentasCentralBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE))
						.addGap(0)));
		gl_panelVentasCentral.setVerticalGroup(gl_panelVentasCentral.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelVentasCentral.createSequentialGroup()
						.addComponent(this.panelVentasCentralBotones, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(this.scrollPaneTablaVentas, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(this.panelVentasCentralBuscar,
								GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)));
		this.panelVentasCentral.setLayout(gl_panelVentasCentral);
	}

	/**
	 * abre el formulario del punto de ventas
	 */
	public void abrirFormVentas(int idSucursal) {
		Component cm = this;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				com.kathsoft.kathpos.app.view.ventas.Fr_PuntoDeVentas fr = new com.kathsoft.kathpos.app.view.ventas.Fr_PuntoDeVentas(
						idSucursal);
				fr.setLocationRelativeTo(cm);
				fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr.setVisible(true);
			}
		});
	}

	private void borrarElementosDeLaTablaVentas() {
		this.modelTablaVentas.getDataVector().removeAllElements();
		this.tablaVentas.updateUI();
	}

	public void exportarVentaExcel() {
		try {
			DataTools.exportarTablaExcel(modelTablaVentas, this);
		} catch (Exception er) {
			er.printStackTrace();
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Error de escritura en fichero CSV: " + er.getMessage());
			er.printStackTrace();
		}
	}
}
