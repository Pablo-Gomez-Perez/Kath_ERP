package com.kathsoft.kathpos.app.view.articulo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.controller.ArticuloController;
import com.kathsoft.kathpos.app.controller.CategoriaController;
import com.kathsoft.kathpos.app.controller.ProveedorController;
import com.kathsoft.kathpos.app.model.articulo.Articulo;
import com.kathsoft.kathpos.app.model.categoria.Categoria;
import com.kathsoft.kathpos.app.model.cliente.TipoCliente;
import com.kathsoft.kathpos.app.model.proveedor.Proveedor;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
import javax.swing.GroupLayout.Alignment;

public class Fr_DatosArticulo extends JFrame {

	private static final long serialVersionUID = -1528483064591725560L;
	
	private final int tipoOperacion;
	private boolean operacionEjecutada;

	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelInferiorBotones;
	private JLabel lblNewLabel_1;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private DefaultTableModel modelTablaPrecios;
	private JPanel panelCentralFormulario;
	private JLabel lblCdigo;
	private JTextField txfCodigo;
	private JLabel lblCodigoSat;
	private JTextField txfCodigoSAT;
	private JLabel lblUnidadSat;
	private JTextField txfUnidadSAT;
	private JLabel lblNombre;
	private JTextField txfNombre;
	private JLabel lblDescripcion;
	private JScrollPane scrollPane;
	private JLabel lblCostoUnitario;
	private JTextField txfCostoUnitario;
	private JPanel panelRdbIndicadorImpuestos;
	private JRadioButton rdbtnExcento;
	private JRadioButton rdbtnGravado;
	private JLabel lblPreciosPorCategoria;
	private JScrollPane scrollPanePreciosTipoCliente;
	private JTable tablePreciosPorTipoCliente;
	private JButton btnConsultarExistencias;

	public Fr_DatosArticulo(int tipoOperacion, int idArticulo, int sucursal) {
		this.tipoOperacion = tipoOperacion;
		setTitle(tipoOperacion == 0 ? "Nuevo Articulo" : "Editar Articulo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/productos_icono.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		buildHeader();
		buildCentralForm();
		buildFooter();

		llenarCmbProveedor();
		llenarCmbCategoria();
		setDefaultTableModelPrecios();
		llenarTablaPrecios();

		if (this.tipoOperacion == 0) {
			limpiarCampos();
		} else {
			getArticuloPorId();
		}
		
	}

	private void buildHeader() {
		panelSuperiorEtiqueta = new JPanel();
		panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

		lblNewLabel_1 = new JLabel(getTitle());
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelSuperiorEtiqueta.add(lblNewLabel_1);
	}

	private void buildCentralForm() {
	}

	

	private void buildFooter() {
		panelInferiorBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelInferiorBotones.setBorder(new LineBorder(Color.BLACK));
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(205, 92, 92));
		btnCancelar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		this.btnConsultarExistencias = new JButton("Existencias");
		this.panelInferiorBotones.add(this.btnConsultarExistencias);
		panelInferiorBotones.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tipoOperacion == 0) {
					insertarNuevoArticulo();
				} else {
					actualizarArticulo();
				}
			}
		});
		panelInferiorBotones.add(btnGuardar);
		
		this.panelCentralFormulario = new JPanel();
		this.panelCentralFormulario.setBackground(new Color(255, 204, 0));
		this.contentPane.add(this.panelCentralFormulario, BorderLayout.CENTER);
		
		this.lblCdigo = new JLabel("Código");
		
		this.txfCodigo = new JTextField();
		this.txfCodigo.setColumns(10);
		
		this.lblCodigoSat = new JLabel("Codigo SAT");
		
		this.txfCodigoSAT = new JTextField();
		this.txfCodigoSAT.setColumns(10);
		
		this.lblUnidadSat = new JLabel("Unidad SAT");
		
		this.txfUnidadSAT = new JTextField();
		this.txfUnidadSAT.setColumns(10);
		
		this.lblNombre = new JLabel("Nombre");
		
		this.txfNombre = new JTextField();
		this.txfNombre.setColumns(10);
		
		this.lblDescripcion = new JLabel("Descripcion");
		
		this.scrollPane = new JScrollPane();
		
		this.lblCostoUnitario = new JLabel("Costo Unitario");
		
		this.txfCostoUnitario = new JTextField();
		this.txfCostoUnitario.setColumns(10);
		
		this.panelRdbIndicadorImpuestos = new JPanel();
		this.panelRdbIndicadorImpuestos.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Iva", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		
		this.lblPreciosPorCategoria = new JLabel("Precios por categoria de cliente");
		
		this.scrollPanePreciosTipoCliente = new JScrollPane();
		GroupLayout gl_panelCentralFormulario = new GroupLayout(this.panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addComponent(this.scrollPanePreciosTipoCliente, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.lblCdigo)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfCodigo, GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblCodigoSat)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfCodigoSAT, GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblUnidadSat)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfUnidadSAT, 52, 52, 52))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.lblNombre)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfNombre, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
						.addComponent(this.lblDescripcion)
						.addGroup(Alignment.TRAILING, gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.scrollPane, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(this.panelRdbIndicadorImpuestos, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.lblPreciosPorCategoria)
							.addPreferredGap(ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
							.addComponent(this.lblCostoUnitario)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.txfCostoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblCdigo)
						.addComponent(this.txfCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblUnidadSat)
						.addComponent(this.txfUnidadSAT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblCodigoSat)
						.addComponent(this.txfCodigoSAT, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblNombre)
						.addComponent(this.txfNombre, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.lblDescripcion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
						.addComponent(this.scrollPane, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
						.addComponent(this.panelRdbIndicadorImpuestos, GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.txfCostoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblCostoUnitario)
						.addComponent(this.lblPreciosPorCategoria))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(this.scrollPanePreciosTipoCliente, GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
					.addGap(22))
		);
		
		this.tablePreciosPorTipoCliente = new JTable();
		this.scrollPanePreciosTipoCliente.setViewportView(this.tablePreciosPorTipoCliente);
		this.panelRdbIndicadorImpuestos.setLayout(new GridLayout(2, 1, 0, 0));
		
		this.rdbtnExcento = new JRadioButton("Excento");
		this.panelRdbIndicadorImpuestos.add(this.rdbtnExcento);
		
		this.rdbtnGravado = new JRadioButton("Gravado 16%");
		this.panelRdbIndicadorImpuestos.add(this.rdbtnGravado);
		this.panelCentralFormulario.setLayout(gl_panelCentralFormulario);
	}

	private void getArticuloPorId() {
		
	}

	private void insertarNuevoArticulo() {
		
	}

	private void actualizarArticulo() {
		
	}

	private boolean validarCamposVacios() {
		

		return true;
	}

	private void limpiarCampos() {
		
	}

	private Articulo buildArticulo() {
		return new Articulo();
	}

	private void setDefaultTableModelPrecios() {		

		modelTablaPrecios.addColumn("Id Tipo Cliente");
		modelTablaPrecios.addColumn("Tipo Cliente");
		modelTablaPrecios.addColumn("Precio");
		modelTablaPrecios.addColumn("Precio Especial");
		modelTablaPrecios.addColumn("Cantidad Precio Especial");
	}

	private void llenarTablaPrecios() {
		if (modelTablaPrecios == null) {
			setDefaultTableModelPrecios();
		}
		llenarTiposClienteEnTablaPrecios();
	}

	private void llenarTiposClienteEnTablaPrecios() {
		modelTablaPrecios.getDataVector().removeAllElements();

		Vector<TipoCliente> tiposCliente = AppContext.tipoClienteController.cmbTipoCliente();
		if (tiposCliente == null || tiposCliente.isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "No hay tipos de cliente para precios");
			return;
		}

		for (TipoCliente tipoCliente : tiposCliente) {
			modelTablaPrecios.addRow(new Object[] {
					Integer.valueOf(tipoCliente.getIdTipoCliente()),
					tipoCliente.getNombre(),
					BigDecimal.ZERO,
					null,
					null
			});
		}
	}

	

	private void guardarPreciosArticulo() {
		
		// Pendiente: no existe SP para persistir precios_x_tipocliente en este repo.
	}

	private void llenarCmbProveedor() {
		
	}

	private void llenarCmbCategoria() {
		
	}

	public boolean isOperacionEjecutada() {
		return operacionEjecutada;
	}

	private void setRadioExento() {
		
	}

	private void seleccionarProveedorPorId() {
		
	}

	private void seleccionarCategoriaPorId() {
		
	}

	private BigDecimal parseDecimal(String value) {
		try {
			if (value == null || value.isBlank()) {
				return null;
			}
			return new BigDecimal(value);
		} catch (Exception er) {
			return null;
		}
	}

	private BigDecimal parseOptionalDecimal(Object value) {
		if (value == null) {
			return null;
		}
		String text = String.valueOf(value).trim();
		if (text.isEmpty()) {
			return null;
		}
		return parseDecimal(text);
	}

	private Integer parseInteger(String value) {
		try {
			if (value == null || value.isBlank()) {
				return null;
			}
			return Integer.valueOf(value);
		} catch (Exception er) {
			return null;
		}
	}

	private Integer parseOptionalInteger(Object value) {
		if (value == null) {
			return null;
		}
		String text = String.valueOf(value).trim();
		if (text.isEmpty()) {
			return null;
		}
		return parseInteger(text);
	}

	private int parseIntegerOrZero(String value) {
		Integer parsed = parseInteger(value);
		return parsed == null ? 0 : parsed.intValue();
	}

	private String nullToEmpty(String value) {
		return value == null ? "" : value;
	}
}
