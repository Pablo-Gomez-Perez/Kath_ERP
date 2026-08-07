package com.kathsoft.kathpos.app.view.articulo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
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

		this.contentPane = new JPanel();
		this.contentPane.setBackground(new Color(255, 215, 0));
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(this.contentPane);

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
		this.panelSuperiorEtiqueta = new JPanel();
		this.panelSuperiorEtiqueta.setBackground(new Color(25, 25, 112));
		this.contentPane.add(this.panelSuperiorEtiqueta, BorderLayout.NORTH);

		this.lblNewLabel_1 = new JLabel(getTitle());
		this.lblNewLabel_1.setForeground(Color.WHITE);
		this.lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		this.panelSuperiorEtiqueta.add(this.lblNewLabel_1);
	}

	private void buildCentralForm() {
		this.panelCentralFormulario = new JPanel(new GridBagLayout());
		this.panelCentralFormulario.setBackground(new Color(255, 204, 0));
		this.contentPane.add(this.panelCentralFormulario, BorderLayout.CENTER);

		this.lblCodigo = new JLabel("Código");
		this.txfCodigo = new JTextField(12);
		this.txfCodigo.setEditable(this.tipoOperacion == 0);

		this.lblCodigoSat = new JLabel("Código SAT");
		this.txfCodigoSAT = new JTextField(12);

		this.lblUnidadSat = new JLabel("Unidad SAT");
		this.txfUnidadSAT = new JTextField(8);

		this.lblNombre = new JLabel("Nombre");
		this.txfNombre = new JTextField(30);

		this.lblProveedor = new JLabel("Proveedor");
		this.cmbProveedor = new JComboBox<JComboboxDataViewModel>();

		this.lblCategoria = new JLabel("Categoría");
		this.cmbCategoriaArticulo = new JComboBox<JComboboxDataViewModel>();

		this.lblDescripcion = new JLabel("Descripción");
		this.txaDescripcion = new JTextArea(5, 30);
		this.txaDescripcion.setLineWrap(true);
		this.txaDescripcion.setWrapStyleWord(true);
		this.scrollPane = new JScrollPane(this.txaDescripcion);

		this.lblCostoUnitario = new JLabel("Costo Unitario");
		this.txfCostoUnitario = new JTextField(10);

		this.panelRdbIndicadorImpuestos = new JPanel(new GridLayout(2, 1, 0, 0));
		this.panelRdbIndicadorImpuestos.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "IVA"));
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

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		this.panelCentralFormulario.add(this.lblCodigo, gbc);
		gbc.gridx = 1;
		gbc.weightx = 1;
		this.panelCentralFormulario.add(this.txfCodigo, gbc);
		gbc.gridx = 2;
		gbc.weightx = 0;
		this.panelCentralFormulario.add(this.lblCodigoSat, gbc);
		gbc.gridx = 3;
		gbc.weightx = 1;
		this.panelCentralFormulario.add(this.txfCodigoSAT, gbc);
		gbc.gridx = 4;
		gbc.weightx = 0;
		this.panelCentralFormulario.add(this.lblUnidadSat, gbc);
		gbc.gridx = 5;
		gbc.weightx = 0.5;
		this.panelCentralFormulario.add(this.txfUnidadSAT, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		this.panelCentralFormulario.add(this.lblNombre, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 5;
		gbc.weightx = 1;
		this.panelCentralFormulario.add(this.txfNombre, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 0;
		this.panelCentralFormulario.add(this.lblProveedor, gbc);
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		this.panelCentralFormulario.add(this.cmbProveedor, gbc);
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		this.panelCentralFormulario.add(this.lblCategoria, gbc);
		gbc.gridx = 4;
		gbc.gridwidth = 2;
		gbc.weightx = 1;
		this.panelCentralFormulario.add(this.cmbCategoriaArticulo, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 6;
		gbc.weightx = 1;
		this.panelCentralFormulario.add(this.lblDescripcion, gbc);

		gbc.gridy = 4;
		gbc.weighty = 0.2;
		gbc.fill = GridBagConstraints.BOTH;
		this.panelCentralFormulario.add(this.scrollPane, gbc);
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 3;
		this.panelCentralFormulario.add(this.panelRdbIndicadorImpuestos, gbc);
		gbc.gridx = 3;
		gbc.gridwidth = 1;
		this.panelCentralFormulario.add(this.lblCostoUnitario, gbc);
		gbc.gridx = 4;
		gbc.gridwidth = 2;
		this.panelCentralFormulario.add(this.txfCostoUnitario, gbc);
		gbc.gridwidth = 1;

		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 6;
		this.panelCentralFormulario.add(this.lblPreciosPorCategoria, gbc);

		gbc.gridy = 7;
		gbc.weighty = 1;
		gbc.fill = GridBagConstraints.BOTH;
		this.panelCentralFormulario.add(this.scrollPanePreciosTipoCliente, gbc);
	}

	private void buildFooter() {
		this.panelInferiorBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		this.panelInferiorBotones.setBorder(new LineBorder(Color.BLACK));
		this.panelInferiorBotones.setBackground(new Color(30, 144, 255));
		this.contentPane.add(this.panelInferiorBotones, BorderLayout.SOUTH);

		this.btnConsultarExistencias = new JButton("Existencias");
		this.btnConsultarExistencias.setEnabled(this.tipoOperacion != 0);
		this.btnConsultarExistencias.addActionListener(e -> abrirExistenciasArticulo());
		this.panelInferiorBotones.add(this.btnConsultarExistencias);

		this.btnCancelar = new JButton("Cancelar");
		this.btnCancelar.setBackground(new Color(205, 92, 92));
		this.btnCancelar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnCancelar.addActionListener(e -> dispose());
		this.panelInferiorBotones.add(this.btnCancelar);

		this.btnGuardar = new JButton("Guardar");
		this.btnGuardar.setBackground(new Color(144, 238, 144));
		this.btnGuardar.setIcon(new ImageIcon(Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		this.btnGuardar.addActionListener(e -> {
			if (this.tipoOperacion == 0) {
				insertarNuevoArticulo();
			} else {
				actualizarArticulo();
			}
		});
		this.panelInferiorBotones.add(this.btnGuardar);
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

		if (!validarDatosArticulo()) {
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
		if (!validarDatosArticulo()) {
			return false;
		}

		BigDecimal costoUnitario = parseDecimal(this.txfCostoUnitario.getText());
		return validarPreciosArticulo(costoUnitario);
	}

	private boolean validarDatosArticulo() {
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

		return true;
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
		if (this.modelTablaPrecios == null) {
			setDefaultTableModelPrecios();
		}
		llenarTiposClienteEnTablaPrecios();
	}

	private void llenarTiposClienteEnTablaPrecios() {
		this.modelTablaPrecios.getDataVector().removeAllElements();

		Vector<TipoCliente> tiposCliente = AppContext.tipoClienteController.cmbTipoCliente();
		if (tiposCliente == null || tiposCliente.isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "No hay tipos de cliente para precios");
			return;
		}

		for (TipoCliente tipoCliente : tiposCliente) {
			this.modelTablaPrecios.addRow(new Object[] {
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
		return this.operacionEjecutada;
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
