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

public class Fr_DatosArticulo extends JFrame {

	private static final long serialVersionUID = -1528483064591725560L;

	private static final int COL_TIPO_CLIENTE_ID = 0;
	private static final int COL_TIPO_CLIENTE_NOMBRE = 1;
	private static final int COL_PRECIO = 2;
	private static final int COL_PRECIO_ESPECIAL = 3;
	private static final int COL_CANTIDAD_PRECIO_ESPECIAL = 4;

	private final ArticuloController articuloController = new ArticuloController();
	private final CategoriaController categoriaController = new CategoriaController();
	private final ProveedorController proveedorController = new ProveedorController();

	private Vector<Proveedor> listProveedores = new Vector<>();
	private Vector<Categoria> listCategoria = new Vector<>();
	private final int tipoOperacion;
	private final int idSucursal;
	private final int idArticulo;
	private boolean operacionEjecutada;

	private JPanel contentPane;
	private JPanel panelSuperiorEtiqueta;
	private JPanel panelCentralFormulario;
	private JPanel panelInferiorBotones;
	private JPanel panelDatosGenerales;
	private JPanel panelDescripcion;
	private JPanel panelPrecios;
	private JPanel panelInventario;
	private JLabel lblNewLabel_1;
	private JLabel lblIdArticulo;
	private JLabel lblCodigoArticulo;
	private JLabel lblProveedorArticulo;
	private JLabel lblCategoriaArticulo;
	private JLabel lblNombreArticulo;
	private JLabel lblCodigoSat;
	private JLabel lblUnidadSat;
	private JLabel lblDescripcion;
	private JLabel lblPrecios;
	private JLabel lblExistencia;
	private JLabel lblCosto;
	private JLabel lblActivo;
	private JTextField txfIdArticulo;
	private JTextField txfCodigoArticulo;
	private JComboBox<Proveedor> cmbProveedorArticulo;
	private JComboBox<Categoria> cmbCategoriaArticulo;
	private JTextField txfNombreArticulo;
	private JTextField txfCodigoSat;
	private JTextField txfUnidadSat;
	private JTextArea txaDescripcionArticulo;
	private JScrollPane scrollPaneTxaDescripcion;
	private JScrollPane scrollPaneTablaPreciosTipoCliente;
	private JTable table;
	private JTextField txfExistenciaArticulo;
	private JTextField txfCostoArticulo;
	private JCheckBox chkActivo;
	private ButtonGroup btnRadioGroup;
	private JRadioButton rdbtnGravado;
	private JRadioButton rdbtnExento;
	private JRadioButton rdbtnNoObjeto;
	private JButton btnCancelar;
	private JButton btnGuardar;
	private JButton btnExistenciaGlobal;
	private DefaultTableModel modelTablaPrecios;

	public Fr_DatosArticulo(int tipoOperacion, int idArticulo, int sucursal) {
		this.tipoOperacion = tipoOperacion;
		this.idSucursal = sucursal;
		this.idArticulo = idArticulo;

		setTitle(tipoOperacion == 0 ? "Nuevo Articulo" : "Editar Articulo");
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				Fr_DatosArticulo.class.getResource("/com/kathsoft/kathpos/app/assets/productos_icono.jpg")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setPreferredSize(new Dimension(980, 760));
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
		setLocationRelativeTo(null);
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
		panelCentralFormulario = new JPanel();
		panelCentralFormulario.setBorder(new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(Color.BLACK)));
		panelCentralFormulario.setBackground(new Color(255, 215, 0));
		contentPane.add(panelCentralFormulario, BorderLayout.CENTER);

		panelDatosGenerales = buildPanelDatosGenerales();
		panelDescripcion = buildPanelDescripcion();
		panelPrecios = buildPanelPrecios();
		panelInventario = buildPanelInventario();

		GroupLayout gl = new GroupLayout(panelCentralFormulario);
		panelCentralFormulario.setLayout(gl);
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
						.addContainerGap()
						.addGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(panelDatosGenerales, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelDescripcion, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelPrecios, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelInventario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		gl.setVerticalGroup(gl.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(gl.createSequentialGroup()
						.addContainerGap()
						.addComponent(panelDatosGenerales, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelDescripcion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelPrecios, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panelInventario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	}

	private JPanel buildPanelDatosGenerales() {
		panelDatosGenerales = new JPanel(new GridLayout(3, 1, 0, 6));
		panelDatosGenerales.setOpaque(false);

		lblIdArticulo = new JLabel("ID");
		lblCodigoArticulo = new JLabel("Código");
		lblProveedorArticulo = new JLabel("Proveedor");
		lblCategoriaArticulo = new JLabel("Categoría");
		lblNombreArticulo = new JLabel("Nombre");
		lblCodigoSat = new JLabel("Código SAT");
		lblUnidadSat = new JLabel("Unidad SAT");
		lblActivo = new JLabel("Activo");

		txfIdArticulo = new JTextField();
		txfIdArticulo.setEditable(false);
		txfIdArticulo.setColumns(10);

		txfCodigoArticulo = new JTextField();
		txfCodigoArticulo.setColumns(25);
		txfCodigoArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (Character.isWhitespace(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		if (this.tipoOperacion == 1) {
			txfCodigoArticulo.setEditable(false);
		}

		cmbProveedorArticulo = new JComboBox<>();
		cmbProveedorArticulo.setMaximumRowCount(12);
		cmbProveedorArticulo.setRenderer(new javax.swing.DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Proveedor proveedor) {
					setText(proveedor.getNombre());
				}
				return this;
			}
		});

		cmbCategoriaArticulo = new JComboBox<>();
		cmbCategoriaArticulo.setMaximumRowCount(12);
		cmbCategoriaArticulo.setRenderer(new javax.swing.DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
				if (value instanceof Categoria categoria) {
					setText(categoria.getNombre());
				}
				return this;
			}
		});

		txfNombreArticulo = new JTextField();
		txfNombreArticulo.setColumns(40);

		txfCodigoSat = new JTextField();
		txfCodigoSat.setColumns(10);
		txfCodigoSat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!Character.isDigit(ch) || txfCodigoSat.getText().length() >= 9) {
					e.consume();
				}
			}
		});

		txfUnidadSat = new JTextField();
		txfUnidadSat.setColumns(15);

		chkActivo = new JCheckBox("Activo");
		chkActivo.setBackground(new Color(255, 215, 0));
		chkActivo.setSelected(true);

		JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		row1.setOpaque(false);
		row1.add(lblIdArticulo);
		row1.add(txfIdArticulo);
		row1.add(lblCodigoArticulo);
		row1.add(txfCodigoArticulo);
		row1.add(lblCodigoSat);
		row1.add(txfCodigoSat);
		row1.add(lblUnidadSat);
		row1.add(txfUnidadSat);

		JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		row2.setOpaque(false);
		row2.add(lblProveedorArticulo);
		row2.add(cmbProveedorArticulo);
		row2.add(lblCategoriaArticulo);
		row2.add(cmbCategoriaArticulo);
		row2.add(lblActivo);
		row2.add(chkActivo);

		JPanel row3 = new JPanel(new BorderLayout(6, 0));
		row3.setOpaque(false);
		row3.add(lblNombreArticulo, BorderLayout.WEST);
		row3.add(txfNombreArticulo, BorderLayout.CENTER);

		panelDatosGenerales.add(row1);
		panelDatosGenerales.add(row2);
		panelDatosGenerales.add(row3);
		return panelDatosGenerales;
	}

	private JPanel buildPanelDescripcion() {
		panelDescripcion = new JPanel(new BorderLayout(0, 4));
		panelDescripcion.setOpaque(false);

		JPanel cabecera = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cabecera.setOpaque(false);
		lblDescripcion = new JLabel("Descripción");
		cabecera.add(lblDescripcion);

		txaDescripcionArticulo = new JTextArea();
		txaDescripcionArticulo.setLineWrap(true);
		txaDescripcionArticulo.setWrapStyleWord(true);
		scrollPaneTxaDescripcion = new JScrollPane(txaDescripcionArticulo);
		scrollPaneTxaDescripcion.setPreferredSize(new Dimension(0, 120));

		panelDescripcion.add(cabecera, BorderLayout.NORTH);
		panelDescripcion.add(scrollPaneTxaDescripcion, BorderLayout.CENTER);
		return panelDescripcion;
	}

	private JPanel buildPanelPrecios() {
		panelPrecios = new JPanel(new BorderLayout(0, 4));
		panelPrecios.setOpaque(false);

		JPanel cabecera = new JPanel(new FlowLayout(FlowLayout.LEFT));
		cabecera.setOpaque(false);
		lblPrecios = new JLabel("Precios por tipo de cliente");
		cabecera.add(lblPrecios);

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPaneTablaPreciosTipoCliente = new JScrollPane(table);
		scrollPaneTablaPreciosTipoCliente.setPreferredSize(new Dimension(0, 160));

		panelPrecios.add(cabecera, BorderLayout.NORTH);
		panelPrecios.add(scrollPaneTablaPreciosTipoCliente, BorderLayout.CENTER);
		return panelPrecios;
	}

	private JPanel buildPanelInventario() {
		panelInventario = new JPanel(new GridLayout(2, 1, 0, 6));
		panelInventario.setOpaque(false);

		lblExistencia = new JLabel("Existencia");
		lblCosto = new JLabel("Costo");

		txfExistenciaArticulo = new JTextField();
		txfExistenciaArticulo.setEditable(false);
		txfExistenciaArticulo.setColumns(10);

		btnExistenciaGlobal = new JButton("Global");
		btnExistenciaGlobal.setBackground(new Color(102, 51, 255));
		btnExistenciaGlobal.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				abrirFormExistencias();
			}
		});

		btnRadioGroup = new ButtonGroup();
		rdbtnGravado = new JRadioButton("Gravado");
		rdbtnExento = new JRadioButton("Exento");
		rdbtnNoObjeto = new JRadioButton("No Objeto");
		rdbtnGravado.setBackground(new Color(255, 215, 0));
		rdbtnExento.setBackground(new Color(255, 215, 0));
		rdbtnNoObjeto.setBackground(new Color(255, 215, 0));
		btnRadioGroup.add(rdbtnGravado);
		btnRadioGroup.add(rdbtnExento);
		btnRadioGroup.add(rdbtnNoObjeto);
		rdbtnGravado.setSelected(true);

		txfCostoArticulo = new JTextField();
		txfCostoArticulo.setColumns(10);
		txfCostoArticulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if (!(Character.isDigit(ch) || ch == '.')) {
					e.consume();
				} else if (ch == '.' && txfCostoArticulo.getText().contains(".")) {
					e.consume();
				}
			}
		});

		JPanel row1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		row1.setOpaque(false);
		row1.add(lblExistencia);
		row1.add(txfExistenciaArticulo);
		row1.add(btnExistenciaGlobal);

		JPanel row2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		row2.setOpaque(false);
		row2.add(lblCosto);
		row2.add(txfCostoArticulo);

		JPanel panelImpuestos = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelImpuestos.setOpaque(false);
		panelImpuestos.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Impuestos Trasladado",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panelImpuestos.add(rdbtnGravado);
		panelImpuestos.add(rdbtnExento);
		panelImpuestos.add(rdbtnNoObjeto);
		row2.add(panelImpuestos);

		panelInventario.add(row1);
		panelInventario.add(row2);
		return panelInventario;
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
	}

	private void getArticuloPorId() {
		try {
			Articulo articulo = this.articuloController.consultarArticuloPorId(this.idArticulo, this.idSucursal);
			if (articulo == null) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "No se encontró articulo para editar");
				return;
			}

			txfIdArticulo.setText(String.valueOf(articulo.getIdArticulo()));
			txfCodigoArticulo.setText(nullToEmpty(articulo.getCodigoArticulo()));
			txfCodigoSat.setText(nullToEmpty(articulo.getCodigoSat()));
			txfUnidadSat.setText(nullToEmpty(articulo.getUnidadSat()));
			txfNombreArticulo.setText(nullToEmpty(articulo.getNombre()));
			txaDescripcionArticulo.setText(nullToEmpty(articulo.getDescripcion()));
			txfCostoArticulo.setText(String.valueOf(articulo.getCostoUnitario()));
			chkActivo.setSelected(true);
			setRadioExento(articulo.isExento());
			seleccionarProveedorPorId(articulo.getIdProvedor());
			seleccionarCategoriaPorId(articulo.getIdCategoria());
			llenarTablaPrecios();
		} catch (SQLException er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void insertarNuevoArticulo() {
		if (!validarCamposVacios()) {
			return;
		}

		try {
			Articulo articulo = buildArticulo();
			this.articuloController.insertarNuevoArticulo(articulo);
			guardarPreciosArticulo();
			this.operacionEjecutada = true;
			MessageHandler.displayMessage(MessageHandler.INSERT_SUCCESS_MESSAGE, this, "");
			dispose();
		} catch (SQLException er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private void actualizarArticulo() {
		if (!validarCamposVacios()) {
			return;
		}

		try {
			Articulo articulo = buildArticulo();
			this.articuloController.actualizarArticulo(articulo);
			guardarPreciosArticulo();
			this.operacionEjecutada = true;
			MessageHandler.displayMessage(MessageHandler.UPDATE_SUCCESS_MESSAGE, this, "");
			dispose();
		} catch (SQLException er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private boolean validarCamposVacios() {
		if (cmbProveedorArticulo.getSelectedItem() == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione proveedor");
			return false;
		}
		if (cmbCategoriaArticulo.getSelectedItem() == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione categoría");
			return false;
		}
		if (txfCodigoArticulo.getText().trim().isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Código de artículo requerido");
			return false;
		}
		if (txfCodigoSat.getText().trim().isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Código SAT requerido");
			return false;
		}
		if (txfUnidadSat.getText().trim().isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Unidad SAT requerida");
			return false;
		}
		if (txfNombreArticulo.getText().trim().isEmpty()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Nombre requerido");
			return false;
		}

		BigDecimal costo = parseDecimal(txfCostoArticulo.getText().trim());
		if (costo == null) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Costo unitario inválido");
			return false;
		}
		if (costo.compareTo(BigDecimal.ZERO) < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Costo unitario debe ser mayor o igual a 0");
			return false;
		}

		if (modelTablaPrecios == null || modelTablaPrecios.getRowCount() == 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Tabla de precios sin datos");
			return false;
		}

		for (int i = 0; i < modelTablaPrecios.getRowCount(); i++) {
			Object idTipoCliente = modelTablaPrecios.getValueAt(i, COL_TIPO_CLIENTE_ID);
			Object nombreTipoCliente = modelTablaPrecios.getValueAt(i, COL_TIPO_CLIENTE_NOMBRE);
			if (idTipoCliente == null || String.valueOf(idTipoCliente).trim().isEmpty() || nombreTipoCliente == null
					|| String.valueOf(nombreTipoCliente).trim().isEmpty()) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"Tipo cliente inválido en fila " + (i + 1));
				return false;
			}

			BigDecimal precio = parseDecimal(String.valueOf(modelTablaPrecios.getValueAt(i, COL_PRECIO)).trim());
			if (precio == null || precio.compareTo(BigDecimal.ZERO) < 0) {
				MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"Precio inválido en fila " + (i + 1));
				return false;
			}

			Object precioEspecialRaw = modelTablaPrecios.getValueAt(i, COL_PRECIO_ESPECIAL);
			if (precioEspecialRaw != null && !String.valueOf(precioEspecialRaw).trim().isEmpty()) {
				BigDecimal precioEspecial = parseDecimal(String.valueOf(precioEspecialRaw).trim());
				if (precioEspecial == null || precioEspecial.compareTo(BigDecimal.ZERO) < 0) {
					MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"Precio especial inválido en fila " + (i + 1));
					return false;
				}
			}

			Object cantidadRaw = modelTablaPrecios.getValueAt(i, COL_CANTIDAD_PRECIO_ESPECIAL);
			if (cantidadRaw != null && !String.valueOf(cantidadRaw).trim().isEmpty()) {
				Integer cantidad = parseInteger(String.valueOf(cantidadRaw).trim());
				if (cantidad == null || cantidad.intValue() <= 0) {
					MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
						"Cantidad precio especial inválida en fila " + (i + 1));
					return false;
				}
			}
		}

		return true;
	}

	private void limpiarCampos() {
		txfIdArticulo.setText("");
		txfCodigoArticulo.setText("");
		txfCodigoSat.setText("");
		txfUnidadSat.setText("");
		txfNombreArticulo.setText("");
		txaDescripcionArticulo.setText("");
		txfExistenciaArticulo.setText("");
		txfCostoArticulo.setText("");
		chkActivo.setSelected(true);
		rdbtnGravado.setSelected(true);
		if (cmbProveedorArticulo.getItemCount() > 0) {
			cmbProveedorArticulo.setSelectedIndex(0);
		}
		if (cmbCategoriaArticulo.getItemCount() > 0) {
			cmbCategoriaArticulo.setSelectedIndex(0);
		}
		llenarTablaPrecios();
	}

	private Articulo buildArticulo() {
		Proveedor proveedor = (Proveedor) cmbProveedorArticulo.getSelectedItem();
		Categoria categoria = (Categoria) cmbCategoriaArticulo.getSelectedItem();
		return new Articulo.ArticuloBuilder()
				.idArticulo(parseIntegerOrZero(txfIdArticulo.getText().trim()))
				.idProvedor(proveedor != null ? proveedor.getIdProveedor() : 0)
				.idCategoria(categoria != null ? categoria.getIdCategoria() : 0)
				.codigoArticulo(txfCodigoArticulo.getText().trim())
				.codigoSat(txfCodigoSat.getText().trim())
				.unidadSat(txfUnidadSat.getText().trim())
				.nombre(txfNombreArticulo.getText().trim())
				.descripcion(txaDescripcionArticulo.getText().trim())
				.exento(rdbtnExento.isSelected())
				.costoUnitario(parseDecimal(txfCostoArticulo.getText().trim()).doubleValue())
				.build();
	}

	private void setDefaultTableModelPrecios() {
		modelTablaPrecios = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column >= COL_PRECIO;
			}
		};

		modelTablaPrecios.addColumn("Id Tipo Cliente");
		modelTablaPrecios.addColumn("Tipo Cliente");
		modelTablaPrecios.addColumn("Precio");
		modelTablaPrecios.addColumn("Precio Especial");
		modelTablaPrecios.addColumn("Cantidad Precio Especial");

		table.setModel(modelTablaPrecios);
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaPreciosArticuloColumnsWidth, table);
	}

	private void llenarTablaPrecios() {
		if (modelTablaPrecios == null) {
			setDefaultTableModelPrecios();
		}
		llenarTiposClienteEnTablaPrecios();
	}

	private void llenarTiposClienteEnTablaPrecios() {
		modelTablaPrecios.getDataVector().removeAllElements();
		table.updateUI();

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

	private Vector<Object[]> buildPreciosArticulo() {
		Vector<Object[]> precios = new Vector<>();
		for (int i = 0; i < modelTablaPrecios.getRowCount(); i++) {
			Integer idTipoCliente = parseInteger(String.valueOf(modelTablaPrecios.getValueAt(i, COL_TIPO_CLIENTE_ID)).trim());
			String nombreTipoCliente = String.valueOf(modelTablaPrecios.getValueAt(i, COL_TIPO_CLIENTE_NOMBRE)).trim();
			BigDecimal precio = parseDecimal(String.valueOf(modelTablaPrecios.getValueAt(i, COL_PRECIO)).trim());
			BigDecimal precioEspecial = parseOptionalDecimal(modelTablaPrecios.getValueAt(i, COL_PRECIO_ESPECIAL));
			Integer cantidadEspecial = parseOptionalInteger(modelTablaPrecios.getValueAt(i, COL_CANTIDAD_PRECIO_ESPECIAL));
			precios.add(new Object[] { idTipoCliente, nombreTipoCliente, precio, precioEspecial, cantidadEspecial });
		}
		return precios;
	}

	private void guardarPreciosArticulo() {
		buildPreciosArticulo();
		// Pendiente: no existe SP para persistir precios_x_tipocliente en este repo.
	}

	private void llenarCmbProveedor() {
		cmbProveedorArticulo.removeAllItems();
		listProveedores = proveedorController.consultarNombresProveedor();
		if (listProveedores != null) {
			for (Proveedor proveedor : listProveedores) {
				cmbProveedorArticulo.addItem(proveedor);
			}
		}
	}

	private void llenarCmbCategoria() {
		cmbCategoriaArticulo.removeAllItems();
		listCategoria = categoriaController.obtenerIndicesDeCategorias();
		if (listCategoria != null) {
			for (Categoria categoria : listCategoria) {
				cmbCategoriaArticulo.addItem(categoria);
			}
		}
	}

	public boolean isOperacionEjecutada() {
		return operacionEjecutada;
	}

	private void abrirFormExistencias() {
		int idActual = parseIntegerOrZero(txfIdArticulo.getText().trim());
		if (idActual <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Guarde articulo antes de ver existencias");
			return;
		}
		Fr_ExistenciasArticulos form = new Fr_ExistenciasArticulos(idActual);
		form.setLocationRelativeTo(this);
		form.setVisible(true);
	}

	private void setRadioExento(boolean exento) {
		if (exento) {
			rdbtnExento.setSelected(true);
		} else {
			rdbtnGravado.setSelected(true);
		}
	}

	private void seleccionarProveedorPorId(int idProveedor) {
		for (int i = 0; i < cmbProveedorArticulo.getItemCount(); i++) {
			Proveedor proveedor = cmbProveedorArticulo.getItemAt(i);
			if (proveedor != null && proveedor.getIdProveedor() == idProveedor) {
				cmbProveedorArticulo.setSelectedIndex(i);
				return;
			}
		}
	}

	private void seleccionarCategoriaPorId(int idCategoria) {
		for (int i = 0; i < cmbCategoriaArticulo.getItemCount(); i++) {
			Categoria categoria = cmbCategoriaArticulo.getItemAt(i);
			if (categoria != null && categoria.getIdCategoria() == idCategoria) {
				cmbCategoriaArticulo.setSelectedIndex(i);
				return;
			}
		}
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
