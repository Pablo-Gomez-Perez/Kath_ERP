package com.kathsoft.kathpos.app.view.articulo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.articulo.Articulo;
import com.kathsoft.kathpos.app.model.articulo.PrecioTipoCliente;
import com.kathsoft.kathpos.app.model.cliente.TipoCliente;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

public class Fr_DatosArticulo extends JFrame {

	private static final long serialVersionUID = -1528483064591725560L;
	private static final Object VALOR_INVALIDO_TABLA = new Object();
	private static final int MAX_LONGITUD_CODIGO_SAT = 9;

	private final int tipoOperacion;
	private final int idArticulo;
	private final int idSucursal;
	private boolean operacionEjecutada;

	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelInferiorBotones;
	private JPanel panelCentralFormulario;
	private JLabel lblNewLabel_1;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnConsultarExistencias;
	private DefaultTableModel modelTablaPrecios;

	private JLabel lblCodigo;
	private JTextField txfCodigo;
	private JLabel lblCodigoSat;
	private JTextField txfCodigoSAT;
	private JLabel lblUnidadSat;
	private JTextField txfUnidadSAT;
	private JLabel lblNombre;
	private JTextField txfNombre;
	private JLabel lblProveedor;
	private JComboBox<JComboboxDataViewModel> cmbProveedor;
	private JLabel lblCategoria;
	private JComboBox<JComboboxDataViewModel> cmbCategoriaArticulo;
	private JLabel lblDescripcion;
	private JTextArea txaDescripcion;
	private JScrollPane scrollPane;
	private JLabel lblCostoUnitario;
	private JTextField txfCostoUnitario;
	private JPanel panelRdbIndicadorImpuestos;
	private ButtonGroup btnRadioGroup;
	private JRadioButton rdbtnExento;
	private JRadioButton rdbtnGravado;
	private JLabel lblPreciosPorCategoria;
	private JScrollPane scrollPanePreciosTipoCliente;
	private JTable tablePreciosPorTipoCliente;

	public Fr_DatosArticulo(int tipoOperacion, int idArticulo, int sucursal) {
		this.tipoOperacion = tipoOperacion;
		this.idArticulo = idArticulo;
		this.idSucursal = sucursal;

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

		pack();
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
		this.panelCentralFormulario = new JPanel();
		this.panelCentralFormulario.setBackground(new Color(255, 204, 0));
		this.contentPane.add(this.panelCentralFormulario, BorderLayout.CENTER);

		this.lblCodigo = new JLabel("Código");
		this.txfCodigo = new JTextField();
		this.txfCodigo.setColumns(10);
		this.txfCodigo.setEditable(this.tipoOperacion == 0);

		this.lblCodigoSat = new JLabel("Código SAT");
		this.txfCodigoSAT = new JTextField();
		this.txfCodigoSAT.setColumns(10);

		this.lblUnidadSat = new JLabel("Unidad SAT");
		this.txfUnidadSAT = new JTextField();
		this.txfUnidadSAT.setColumns(10);

		this.lblNombre = new JLabel("Nombre");
		this.txfNombre = new JTextField();
		this.txfNombre.setColumns(10);

		this.lblProveedor = new JLabel("Proveedor");
		this.cmbProveedor = new JComboBox<JComboboxDataViewModel>();

		this.lblCategoria = new JLabel("Categoría");
		this.cmbCategoriaArticulo = new JComboBox<JComboboxDataViewModel>();

		this.lblDescripcion = new JLabel("Descripción");
		this.txaDescripcion = new JTextArea();
		this.txaDescripcion.setLineWrap(true);
		this.txaDescripcion.setWrapStyleWord(true);
		this.scrollPane = new JScrollPane(this.txaDescripcion);

		this.lblCostoUnitario = new JLabel("Costo Unitario");
		this.txfCostoUnitario = new JTextField();
		this.txfCostoUnitario.setColumns(10);

		this.panelRdbIndicadorImpuestos = new JPanel();
		this.panelRdbIndicadorImpuestos.setLayout(new GridLayout(2, 1, 0, 0));
		this.panelRdbIndicadorImpuestos.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "IVA", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));

		this.btnRadioGroup = new ButtonGroup();
		this.rdbtnExento = new JRadioButton("Exento");
		this.rdbtnGravado = new JRadioButton("Gravado 16%");
		this.rdbtnGravado.setSelected(true);
		this.btnRadioGroup.add(this.rdbtnExento);
		this.btnRadioGroup.add(this.rdbtnGravado);
		this.panelRdbIndicadorImpuestos.add(this.rdbtnExento);
		this.panelRdbIndicadorImpuestos.add(this.rdbtnGravado);

		this.lblPreciosPorCategoria = new JLabel("Precios por categoría de cliente");
		this.scrollPanePreciosTipoCliente = new JScrollPane();
		this.tablePreciosPorTipoCliente = new JTable();
		this.scrollPanePreciosTipoCliente.setViewportView(this.tablePreciosPorTipoCliente);

		GroupLayout gl_panelCentralFormulario = new GroupLayout(this.panelCentralFormulario);
		gl_panelCentralFormulario.setHorizontalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.lblCodigo)
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
						.addGroup(gl_panelCentralFormulario.createSequentialGroup()
							.addComponent(this.lblProveedor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbProveedor, 0, 204, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.lblCategoria)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(this.cmbCategoriaArticulo, 0, 207, Short.MAX_VALUE))
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
							.addComponent(this.txfCostoUnitario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(this.scrollPanePreciosTipoCliente, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelCentralFormulario.setVerticalGroup(
			gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelCentralFormulario.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblCodigo)
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
					.addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
						.addComponent(this.lblProveedor)
						.addComponent(this.cmbProveedor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(this.lblCategoria)
						.addComponent(this.cmbCategoriaArticulo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
					.addComponent(this.scrollPanePreciosTipoCliente, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
					.addGap(22))
		);
		this.panelCentralFormulario.setLayout(gl_panelCentralFormulario);
	}

	private void buildFooter() {
		panelInferiorBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelInferiorBotones.setBorder(new LineBorder(Color.BLACK));
		panelInferiorBotones.setBackground(new Color(30, 144, 255));
		contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

		this.btnConsultarExistencias = new JButton("Existencias");
		this.btnConsultarExistencias.setEnabled(this.tipoOperacion != 0);
		this.btnConsultarExistencias.addActionListener(e -> abrirExistenciasArticulo());
		this.panelInferiorBotones.add(this.btnConsultarExistencias);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(new Color(205, 92, 92));
		btnCancelar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnCancelar.addActionListener(e -> dispose());
		panelInferiorBotones.add(btnCancelar);

		btnGuardar = new JButton("Guardar");
		btnGuardar.setBackground(new Color(144, 238, 144));
		btnGuardar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnGuardar.addActionListener(e -> {
			if (tipoOperacion == 0) {
				insertarNuevoArticulo();
			} else {
				actualizarArticulo();
			}
		});
		panelInferiorBotones.add(btnGuardar);
	}

	private void abrirExistenciasArticulo() {
		if (this.idArticulo <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"Debe seleccionar o guardar un articulo antes de consultar existencias");
			return;
		}

		Fr_ExistenciasArticulos form = new Fr_ExistenciasArticulos(this.idArticulo);
		form.setLocationRelativeTo(this);
		form.setVisible(true);
	}

	private void getArticuloPorId() {
		if (this.idArticulo <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar un articulo para editar");
			return;
		}

		try {
			Articulo articulo = AppContext.articuloController.consultarArticuloPorId(this.idArticulo, this.idSucursal);
			if (articulo == null || articulo.getIdArticulo() <= 0) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "No se encontró el articulo seleccionado");
				return;
			}

			this.txfCodigo.setText(nullToEmpty(articulo.getCodigoArticulo()));
			this.txfCodigoSAT.setText(nullToEmpty(articulo.getCodigoSat()));
			this.txfUnidadSAT.setText(nullToEmpty(articulo.getUnidadSat()));
			this.txfNombre.setText(nullToEmpty(articulo.getNombre()));
			this.txaDescripcion.setText(nullToEmpty(articulo.getDescripcion()));
			this.txfCostoUnitario.setText(String.valueOf(articulo.getCostoUnitario()));
			setRadioExento(articulo.isExento());
			seleccionarProveedorPorId(articulo.getIdProvedor());
			seleccionarCategoriaPorId(articulo.getIdCategoria());
		} catch (SQLException er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void insertarNuevoArticulo() {
		cerrarEdicionTablaPrecios();

		if (!validarCamposVacios()) {
			return;
		}

		try {
			Articulo articulo = buildArticulo();
			List<PrecioTipoCliente> preciosTipoCliente = buildPreciosArticulo();
			AppContext.articuloController.insertarNuevoArticulo(articulo, preciosTipoCliente);
			this.operacionEjecutada = true;
			MessageHandler.displayMessage(MessageHandler.INSERT_SUCCESS_MESSAGE, this, "");
			dispose();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void actualizarArticulo() {
		cerrarEdicionTablaPrecios();

		if (this.idArticulo <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar un articulo para actualizar");
			return;
		}

		if (this.cmbProveedor.getSelectedItem() == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar un proveedor");
			return;
		}

		if (this.cmbCategoriaArticulo.getSelectedItem() == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar una categoría");
			return;
		}

		if (isBlank(this.txfCodigo.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar el código del artículo");
			return;
		}

		if (isBlank(this.txfCodigoSAT.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar el código SAT");
			return;
		}

		if (this.txfCodigoSAT.getText().trim().length() > MAX_LONGITUD_CODIGO_SAT) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"El código SAT no puede exceder " + MAX_LONGITUD_CODIGO_SAT + " caracteres");
			return;
		}

		if (isBlank(this.txfUnidadSAT.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar la unidad SAT");
			return;
		}

		if (isBlank(this.txfNombre.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar el nombre del artículo");
			return;
		}

		BigDecimal costoUnitario = parseDecimal(this.txfCostoUnitario.getText());
		if (costoUnitario == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar un costo unitario válido");
			return;
		}

		if (costoUnitario.compareTo(BigDecimal.ZERO) < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "El costo unitario no puede ser negativo");
			return;
		}

		try {
			Articulo articulo = buildArticulo();
			AppContext.articuloController.actualizarArticulo(articulo);
			this.operacionEjecutada = true;
			MessageHandler.displayMessage(MessageHandler.UPDATE_SUCCESS_MESSAGE, this, "");
			dispose();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private boolean validarCamposVacios() {
		if (this.cmbProveedor.getSelectedItem() == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar un proveedor");
			return false;
		}

		if (this.cmbCategoriaArticulo.getSelectedItem() == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe seleccionar una categoría");
			return false;
		}

		if (isBlank(this.txfCodigo.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar el código del artículo");
			return false;
		}

		if (isBlank(this.txfCodigoSAT.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar el código SAT");
			return false;
		}

		if (this.txfCodigoSAT.getText().trim().length() > MAX_LONGITUD_CODIGO_SAT) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
					"El código SAT no puede exceder " + MAX_LONGITUD_CODIGO_SAT + " caracteres");
			return false;
		}

		if (isBlank(this.txfUnidadSAT.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar la unidad SAT");
			return false;
		}

		if (isBlank(this.txfNombre.getText())) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar el nombre del artículo");
			return false;
		}

		BigDecimal costoUnitario = parseDecimal(this.txfCostoUnitario.getText());
		if (costoUnitario == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe capturar un costo unitario válido");
			return false;
		}

		if (costoUnitario.compareTo(BigDecimal.ZERO) < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "El costo unitario no puede ser negativo");
			return false;
		}

		return validarPreciosArticulo(costoUnitario);
	}

	private boolean validarPreciosArticulo(BigDecimal costoUnitario) {
		if (this.modelTablaPrecios == null || this.modelTablaPrecios.getRowCount() == 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Debe existir al menos un tipo de cliente para registrar precios");
			return false;
		}

		for (int row = 0; row < this.modelTablaPrecios.getRowCount(); row++) {
			String tipoCliente = String.valueOf(this.modelTablaPrecios.getValueAt(row, 1));
			BigDecimal precio = parseOptionalDecimal(this.modelTablaPrecios.getValueAt(row, 2));
			BigDecimal precioEspecial = parseOptionalDecimal(this.modelTablaPrecios.getValueAt(row, 3));
			Integer cantidadPrecioEspecial = parseOptionalInteger(this.modelTablaPrecios.getValueAt(row, 4));

			if (precio == null) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"Debe capturar un precio válido para el tipo de cliente: " + tipoCliente);
				return false;
			}

			if (precio.compareTo(costoUnitario) < 0) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"El precio normal no puede ser menor al costo unitario para: " + tipoCliente);
				return false;
			}

			if (precioEspecial != null && precioEspecial.compareTo(costoUnitario) < 0) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"El precio especial no puede ser menor al costo unitario para: " + tipoCliente);
				return false;
			}

			if (cantidadPrecioEspecial != null && cantidadPrecioEspecial.intValue() < 0) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"La cantidad para precio especial no puede ser negativa para: " + tipoCliente);
				return false;
			}
		}

		return true;
	}

	private void limpiarCampos() {
		this.txfCodigo.setText("");
		this.txfCodigoSAT.setText("");
		this.txfUnidadSAT.setText("");
		this.txfNombre.setText("");
		this.txaDescripcion.setText("");
		this.txfCostoUnitario.setText("");
		this.rdbtnGravado.setSelected(true);
	}

	private Articulo buildArticulo() {
		JComboboxDataViewModel proveedor = (JComboboxDataViewModel) this.cmbProveedor.getSelectedItem();
		JComboboxDataViewModel categoria = (JComboboxDataViewModel) this.cmbCategoriaArticulo.getSelectedItem();
		BigDecimal costoUnitario = parseDecimal(this.txfCostoUnitario.getText());

		return new Articulo.ArticuloBuilder()
				.idArticulo(this.idArticulo)
				.idProvedor(proveedor.id())
				.idCategoria(categoria.id())
				.codigoArticulo(this.txfCodigo.getText().trim())
				.codigoSat(this.txfCodigoSAT.getText().trim())
				.unidadSat(this.txfUnidadSAT.getText().trim())
				.nombre(this.txfNombre.getText().trim())
				.descripcion(getOptionalText(this.txaDescripcion.getText()))
				.exento(this.rdbtnExento.isSelected())
				.costoUnitario(costoUnitario.doubleValue())
				.activo(true)
				.build();
	}

	private List<PrecioTipoCliente> buildPreciosArticulo() {
		List<PrecioTipoCliente> preciosTipoCliente = new ArrayList<PrecioTipoCliente>();

		for (int row = 0; row < this.modelTablaPrecios.getRowCount(); row++) {
			Integer idTipoCliente = parseOptionalInteger(this.modelTablaPrecios.getValueAt(row, 0));
			BigDecimal precio = parseOptionalDecimal(this.modelTablaPrecios.getValueAt(row, 2));
			BigDecimal precioEspecial = parseOptionalDecimal(this.modelTablaPrecios.getValueAt(row, 3));
			Integer cantidadPrecioEspecial = parseOptionalInteger(this.modelTablaPrecios.getValueAt(row, 4));

			preciosTipoCliente.add(new PrecioTipoCliente(
					idTipoCliente.intValue(),
					precio,
					precioEspecial,
					cantidadPrecioEspecial
			));
		}

		return preciosTipoCliente;
	}

	private void setDefaultTableModelPrecios() {
		this.modelTablaPrecios = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column >= 2 && column <= 4;
			}

			@Override
			public void setValueAt(Object aValue, int row, int column) {
				if (column >= 2 && column <= 4) {
					Object valorNormalizado = normalizarValorPrecio(aValue, column);
					if (valorNormalizado == VALOR_INVALIDO_TABLA) {
						return;
					}

					super.setValueAt(valorNormalizado, row, column);
					return;
				}

				super.setValueAt(aValue, row, column);
			}
		};
		this.modelTablaPrecios.addColumn("Id Tipo Cliente");
		this.modelTablaPrecios.addColumn("Tipo Cliente");
		this.modelTablaPrecios.addColumn("Precio");
		this.modelTablaPrecios.addColumn("Precio Especial");
		this.modelTablaPrecios.addColumn("Cantidad Precio Especial");
		this.tablePreciosPorTipoCliente.setModel(this.modelTablaPrecios);
	}

	private Object normalizarValorPrecio(Object value, int column) {
		switch (column) {
		case 2:
			return normalizarDecimalPrecio(value, true, "Precio");
		case 3:
			return normalizarDecimalPrecio(value, false, "Precio especial");
		case 4:
			return normalizarCantidadPrecioEspecial(value);
		default:
			return value;
		}
	}

	private Object normalizarDecimalPrecio(Object value, boolean requerido, String campo) {
		String text = value == null ? "" : String.valueOf(value).trim();
		if (text.isEmpty()) {
			if (requerido) {
				mostrarAdvertenciaValorNumerico(campo);
				return VALOR_INVALIDO_TABLA;
			}

			return null;
		}

		try {
			return new BigDecimal(text.replace(',', '.'));
		} catch (NumberFormatException er) {
			mostrarAdvertenciaValorNumerico(campo);
			return VALOR_INVALIDO_TABLA;
		}
	}

	private Object normalizarCantidadPrecioEspecial(Object value) {
		String text = value == null ? "" : String.valueOf(value).trim();
		if (text.isEmpty()) {
			return null;
		}

		try {
			return Integer.valueOf(text);
		} catch (NumberFormatException er) {
			mostrarAdvertenciaValorNumerico("Cantidad precio especial");
			return VALOR_INVALIDO_TABLA;
		}
	}

	private void mostrarAdvertenciaValorNumerico(String campo) {
		MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
				campo + " debe ser un valor numérico válido");
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
		
	}

	private void llenarCmbProveedor() {
		this.cmbProveedor.removeAllItems();
		AppContext.proveedorController.consultarNombresProveedor().forEach(this.cmbProveedor::addItem);
	}

	private void llenarCmbCategoria() {
		this.cmbCategoriaArticulo.removeAllItems();
		AppContext.categoriaController.obtenerIndicesDeCategorias().forEach(this.cmbCategoriaArticulo::addItem);
	}

	public boolean isOperacionEjecutada() {
		return operacionEjecutada;
	}

	private void setRadioExento(boolean exento) {
		if (exento) {
			this.rdbtnExento.setSelected(true);
		} else {
			this.rdbtnGravado.setSelected(true);
		}
	}

	private void seleccionarProveedorPorId(int idProveedor) {
		seleccionarComboPorId(this.cmbProveedor, idProveedor);
	}

	private void seleccionarCategoriaPorId(int idCategoria) {
		seleccionarComboPorId(this.cmbCategoriaArticulo, idCategoria);
	}

	private void seleccionarComboPorId(JComboBox<JComboboxDataViewModel> combo, int id) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			JComboboxDataViewModel item = combo.getItemAt(i);
			if (item != null && item.id() == id) {
				combo.setSelectedIndex(i);
				return;
			}
		}
	}

	private void cerrarEdicionTablaPrecios() {
		if (this.tablePreciosPorTipoCliente != null && this.tablePreciosPorTipoCliente.isEditing()) {
			this.tablePreciosPorTipoCliente.getCellEditor().stopCellEditing();
		}
	}

	private BigDecimal parseDecimal(String value) {
		try {
			if (value == null || value.isBlank()) {
				return null;
			}
			return new BigDecimal(value.trim().replace(",", "."));
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
			return Integer.valueOf(value.trim());
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

	private String getOptionalText(String value) {
		if (value == null || value.isBlank()) {
			return null;
		}
		return value.trim();
	}

	private boolean isBlank(String value) {
		return value == null || value.isBlank();
	}
}