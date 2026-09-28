package com.kathsoft.kathpos.app.view.gastos;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import java.awt.event.HierarchyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.gastos.CategoriaDeGasto;
import com.kathsoft.kathpos.app.model.gastos.GastoFiltro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

public class PanelGastos extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final DateTimeFormatter FECHA_FILTRO = DateTimeFormatter.ofPattern("dd/MM/uuuu")
			.withResolverStyle(ResolverStyle.STRICT);
	private static final JComboboxDataViewModel OPCION_TODOS =
			new JComboboxDataViewModel(0, "Todos");

	private JTable tableGastos;
	private DefaultTableModel modelTablaGastos;
	private JComboBox<JComboboxDataViewModel> comboBoxEmpleado;
	private JComboBox<JComboboxDataViewModel> comboBoxCategoriaDeGasto;
	private JComboBox<GastoFiltro.Orden> comboBoxOrdenarPor;
	private JFormattedTextField formattedTextFieldFechaInicio;
	private JFormattedTextField formattedTextFieldFechaFinal;
	private JButton btnBuscarGasto;
	private JButton btnEliminar;
	private JButton btnModificar;
	private long idSucursalActual;
	private boolean filtrosCargados;
	private boolean cargandoFiltros;
	private int versionListado;

	/**
	 * Create the panel.
	 */
	public PanelGastos() {
		setBackground(new Color(255, 215, 0));
		setBorder(null);		
		
		JPanel panelEtiquetaVentas = new JPanel();
		panelEtiquetaVentas.setBackground(new Color(0, 0, 128));
		
		JLabel lblTituloGastos = new JLabel("Gastos");
		lblTituloGastos.setForeground(Color.WHITE);
		lblTituloGastos.setFont(new Font("Dialog", Font.BOLD, 16));
		panelEtiquetaVentas.add(lblTituloGastos);
		
		JPanel panelInferiorCriteriosBusqueda = new JPanel();
		panelInferiorCriteriosBusqueda.setBackground(new Color(0, 191, 255));
		
		JPanel panelSuperiorBotones = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelSuperiorBotones.setBackground(new Color(255, 204, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
		btnAgregar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnAgregar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
		btnModificar.setBackground(new Color(144, 238, 144));
		panelSuperiorBotones.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		btnEliminar.setBackground(new Color(255, 51, 0));
		panelSuperiorBotones.add(btnEliminar);
		
		JButton btnExcel = new JButton("Exportar Excel");
		btnExcel.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
		btnExcel.setBackground(new Color(102, 205, 170));
		panelSuperiorBotones.add(btnExcel);
		
		JScrollPane scrollPaneTablaGastos = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panelEtiquetaVentas, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelInferiorCriteriosBusqueda, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPaneTablaGastos, GroupLayout.DEFAULT_SIZE, 806, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelEtiquetaVentas, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneTablaGastos, GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelInferiorCriteriosBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		tableGastos = new JTable();
		scrollPaneTablaGastos.setViewportView(tableGastos);
		
		JLabel lblEmpleado = new JLabel("Empleado");
		
		comboBoxEmpleado = new JComboBox<JComboboxDataViewModel>();
		
		JLabel lblCategoria = new JLabel("Categoria");
		
		comboBoxCategoriaDeGasto = new JComboBox<JComboboxDataViewModel>();
		
		btnBuscarGasto = new JButton("Buscar");
		btnBuscarGasto.setIcon(new ImageIcon(PanelGastos.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		btnBuscarGasto.setFont(new Font("Dialog", Font.BOLD, 13));
		btnBuscarGasto.setBackground(new Color(184, 134, 11));
		
		JLabel lblOrdenarPor = new JLabel("Ordenar por");
		
		comboBoxOrdenarPor = new JComboBox<GastoFiltro.Orden>();
		
		JLabel lblDesde = new JLabel("Desde");
		
		formattedTextFieldFechaInicio = new JFormattedTextField();
		formattedTextFieldFechaInicio.setFormatterFactory(
				new DefaultFormatterFactory(crearMascaraFecha()));
		formattedTextFieldFechaInicio.setFocusLostBehavior(JFormattedTextField.PERSIST);
		
		JLabel lblHasta = new JLabel("Hasta");
		
		formattedTextFieldFechaFinal = new JFormattedTextField();
		formattedTextFieldFechaFinal.setFormatterFactory(
				new DefaultFormatterFactory(crearMascaraFecha()));
		formattedTextFieldFechaFinal.setFocusLostBehavior(JFormattedTextField.PERSIST);
		GroupLayout gl_panelInferiorCriteriosBusqueda = new GroupLayout(panelInferiorCriteriosBusqueda);
		gl_panelInferiorCriteriosBusqueda.setHorizontalGroup(
			gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblEmpleado)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxEmpleado, 0, 169, Short.MAX_VALUE))
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblDesde)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldFechaInicio, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblCategoria)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxCategoriaDeGasto, 0, 159, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblOrdenarPor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBoxOrdenarPor, 0, 112, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnBuscarGasto, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
							.addComponent(lblHasta)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(formattedTextFieldFechaFinal, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
							.addGap(315)))
					.addContainerGap())
		);
		gl_panelInferiorCriteriosBusqueda.setVerticalGroup(
			gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInferiorCriteriosBusqueda.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEmpleado)
						.addComponent(comboBoxEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnBuscarGasto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCategoria)
						.addComponent(comboBoxCategoriaDeGasto, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOrdenarPor)
						.addComponent(comboBoxOrdenarPor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelInferiorCriteriosBusqueda.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDesde)
						.addComponent(formattedTextFieldFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHasta)
						.addComponent(formattedTextFieldFechaFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		panelInferiorCriteriosBusqueda.setLayout(gl_panelInferiorCriteriosBusqueda);
		setLayout(groupLayout);

		// Inicialización funcional posterior al diseño generado por WindowBuilder.
		modelTablaGastos = crearModeloTabla();
		tableGastos.setModel(modelTablaGastos);
		tableGastos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		DataTools.removerEditorDeTabla(tableGastos, modelTablaGastos);
		DataTools.definirTamanioDeColumnas(ConstantsConllections.tablaGastosColumnsWidth, tableGastos);
		cargarOpcionesOrdenamiento();
		prepararOpcionesVacias();

		btnAgregar.addActionListener(event ->
				abrirFormularioGasto(Fr_DatosGasto.OPCION_CREAR, 0));
		btnModificar.addActionListener(event -> modificarGastoSeleccionado());
		btnEliminar.addActionListener(event -> inhabilitarGastoSeleccionado());
		btnBuscarGasto.addActionListener(event -> llenarTablaGastos());
		btnExcel.addActionListener(event -> exportarListado());

		// El diseñador puede instanciar este panel sin sesión ni acceder a MySQL.
		addHierarchyListener(event -> {
			if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0
					&& isShowing() && idSucursalActual > 0) {
				if (!filtrosCargados && !cargandoFiltros) {
					cargarFiltros();
				}
			}
		});
	}

	/**
	 * Constructor utilizado exclusivamente por el diseñador WindowBuilder.
	 * La aplicación debe usar el constructor con sucursal o establecerla.
	 */
	public PanelGastos(Sucursal sucursal) {
		this();
		establecerSucursalActual(sucursal);
	}

	/**
	 * El padre inyecta el contexto del login: ningún filtro permite cambiarlo.
	 */
	public void establecerSucursalActual(Sucursal sucursal) {
		if (sucursal == null || sucursal.getIdSucursal() <= 0) {
			throw new IllegalArgumentException("Es obligatoria una sucursal válida de la sesión");
		}
		this.idSucursalActual = sucursal.getIdSucursal();
		filtrosCargados = false;
		cargandoFiltros = false;
		versionListado++;
		modelTablaGastos.setRowCount(0);
		prepararOpcionesVacias();
		if (isShowing()) {
			cargarFiltros();
		}
	}

	/**
	 * Las columnas tienen el orden exacto de GastoController.verGastosEnTabla.
	 */
	static DefaultTableModel crearModeloTabla() {
		return new DefaultTableModel(new Object[] {
				"Folio", "Fecha", "Empleado", "Categoría", "Forma de pago",
				"Descripción", "Importe", "IVA", "Total", "Activo"
		}, 0) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int fila, int columna) {
				return false;
			}
		};
	}

	private void prepararOpcionesVacias() {
		comboBoxEmpleado.removeAllItems();
		comboBoxEmpleado.addItem(OPCION_TODOS);
		comboBoxCategoriaDeGasto.removeAllItems();
		comboBoxCategoriaDeGasto.addItem(OPCION_TODOS);
		comboBoxOrdenarPor.setSelectedItem(GastoFiltro.Orden.FECHA_RECIENTE);
	}

	private void cargarOpcionesOrdenamiento() {
		comboBoxOrdenarPor.removeAllItems();
		for (GastoFiltro.Orden opcion : GastoFiltro.Orden.values()) {
			comboBoxOrdenarPor.addItem(opcion);
		}
		comboBoxOrdenarPor.setSelectedItem(GastoFiltro.Orden.FECHA_RECIENTE);
	}

	/**
	 * Empleados de la sucursal (también inactivos para consulta histórica) y todas las categorías (incluyendo
	 * inactivas para poder buscar gastos históricos).
	 */
	private void cargarFiltros() {
		if (idSucursalActual <= 0 || cargandoFiltros) {
			return;
		}
		cargandoFiltros = true;
		btnBuscarGasto.setEnabled(false);
		final long sucursalConsulta = idSucursalActual;
		new SwingWorker<OpcionesFiltros, Void>() {
			@Override
			protected OpcionesFiltros doInBackground() throws Exception {
				Vector<JComboboxDataViewModel> empleados =
						AppContext.gastoController.listCmbEmpleadosFiltroGastos(sucursalConsulta);
				List<CategoriaDeGasto> categorias =
						AppContext.categoriaDeGastoController.listarCategorias("");
				return new OpcionesFiltros(empleados, categorias);
			}
			@Override
			protected void done() {
				if (sucursalConsulta != idSucursalActual) {
					return; // La sesión cambió durante la consulta.
				}
				cargandoFiltros = false;
				btnBuscarGasto.setEnabled(true);
				try {
					OpcionesFiltros filtros = get();
					prepararOpcionesVacias();
					for (JComboboxDataViewModel empleado : filtros.empleados()) {
						comboBoxEmpleado.addItem(empleado);
					}
					for (CategoriaDeGasto categoria : filtros.categorias()) {
						comboBoxCategoriaDeGasto.addItem(new JComboboxDataViewModel(
								categoria.getIdCategoria(), categoria.getNombre()));
					}
					filtrosCargados = true;
					llenarTablaGastos();
				} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
							PanelGastos.this, "Se interrumpió la carga de filtros");
				} catch (ExecutionException ex) {
						Throwable causa = ex.getCause() == null ? ex : ex.getCause();
						MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
							PanelGastos.this, "No fue posible cargar los filtros: " + causa.getMessage());
				}
			}
		}.execute();
	}

	/**
	 * Máscara dd/MM/aaaa. La validez real del calendario se comprueba después
	 * con DateTimeFormatter. Ninguna fecha se asigna automáticamente.
	 */
	private static MaskFormatter crearMascaraFecha() {
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			mascara.setValidCharacters("0123456789");
			return mascara;
		} catch (ParseException ex) {
			throw new IllegalStateException("No se pudo crear la máscara de fecha", ex);
		}
	}

	/**
	 * Parser estricto reutilizable y testeable, con rangos opcionales.
	 */
	static LocalDate interpretarFechaFiltro(String valor) {
		if (valor == null || valor.isBlank() || "__/__/____".equals(valor.trim())) {
			return null;
		}
		String fecha = valor.trim();
		if (fecha.indexOf('_') >= 0) {
			throw new IllegalArgumentException("Complete los ocho dígitos de la fecha (dd/MM/aaaa)");
		}
		try {
			return LocalDate.parse(fecha, FECHA_FILTRO);
		} catch (DateTimeParseException ex) {
			throw new IllegalArgumentException("Fecha inválida: use dd/MM/aaaa e indique una fecha real");
		}
	}

	private static Integer idSeleccionado(JComboBox<JComboboxDataViewModel> combo) {
		JComboboxDataViewModel item = (JComboboxDataViewModel) combo.getSelectedItem();
		return item == null || item.id() <= 0 ? null : item.id();
	}

	private GastoFiltro construirFiltro() {
		return new GastoFiltro(
				idSeleccionado(comboBoxEmpleado),
				idSeleccionado(comboBoxCategoriaDeGasto),
				interpretarFechaFiltro(formattedTextFieldFechaInicio.getText()),
				interpretarFechaFiltro(formattedTextFieldFechaFinal.getText()),
				(GastoFiltro.Orden) comboBoxOrdenarPor.getSelectedItem());
	}

	/**
	 * Recupera el listado de la sucursal con filtros opcionales sin bloquear
	 * el hilo gráfico; conserva la tabla anterior si ocurre un error.
	 */
	public void llenarTablaGastos() {
		if (idSucursalActual <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
					this, "El módulo requiere una sucursal de sesión válida");
			return;
		}
		if (!filtrosCargados) {
			cargarFiltros();
			return;
		}
		final GastoFiltro filtro;
		try {
			filtro = construirFiltro();
		} catch (IllegalArgumentException ex) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
			return;
		}
		final long sucursalConsulta = idSucursalActual;
		final int version = ++versionListado;
		btnBuscarGasto.setEnabled(false);
		new SwingWorker<Vector<Object[]>, Void>() {
			@Override
			protected Vector<Object[]> doInBackground() throws Exception {
				return AppContext.gastoController.verGastosEnTabla(sucursalConsulta, filtro);
			}
			@Override
			protected void done() {
				if (sucursalConsulta != idSucursalActual || version != versionListado) {
					return;
				}
				btnBuscarGasto.setEnabled(true);
				try {
					Vector<Object[]> filas = get();
					modelTablaGastos.setRowCount(0);
					for (Object[] fila : filas) {
						modelTablaGastos.addRow(fila);
					}
				} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, PanelGastos.this,
							"Se interrumpió la consulta de gastos");
				} catch (ExecutionException ex) {
						Throwable causa = ex.getCause() == null ? ex : ex.getCause();
						MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, PanelGastos.this,
							"No fue posible consultar los gastos: " + causa.getMessage());
				}
			}
		}.execute();
	}

	/**
	 * Convierte la fila visual al modelo para respetar ordenamientos futuros.
	 */
	static int idGastoDeFila(JTable tabla, DefaultTableModel modelo) {
		if (tabla.getSelectedRow() < 0) {
			return -1;
		}
		Object valor = modelo.getValueAt(tabla.convertRowIndexToModel(tabla.getSelectedRow()), 0);
		return valor instanceof Number numero && numero.intValue() > 0 ? numero.intValue() : -1;
	}

	private int gastoSeleccionado() {
		int id = idGastoDeFila(tableGastos, modelTablaGastos);
		if (id <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
					this, "Seleccione un gasto válido en la tabla");
		}
		return id;
	}

	private boolean gastoSeleccionadoActivo() {
		int fila = tableGastos.getSelectedRow();
		return fila >= 0 && "Activo".equals(
				modelTablaGastos.getValueAt(tableGastos.convertRowIndexToModel(fila), 9));
	}

	private void abrirFormularioGasto(int opcion, int id) {
		if (idSucursalActual <= 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
					this, "No se ha asignado una sucursal de sesión");
			return;
		}
		Fr_DatosGasto formulario = new Fr_DatosGasto(opcion, id, idSucursalActual);
		formulario.setLocationRelativeTo(this);
		formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		formulario.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent event) {
				if (formulario.isOperacionEjecutada()) {
					llenarTablaGastos();
				}
			}
		});
		formulario.setVisible(true);
	}

	private void modificarGastoSeleccionado() {
		int id = gastoSeleccionado();
		if (id <= 0) {
			return;
		}
		if (!gastoSeleccionadoActivo()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
					this, "No se puede actualizar un gasto inhabilitado");
			return;
		}
		// El SP updateGasto aplica la restricción de día con la hora del servidor.
		abrirFormularioGasto(Fr_DatosGasto.OPCION_EDITAR, id);
	}

	private void inhabilitarGastoSeleccionado() {
		int id = gastoSeleccionado();
		if (id <= 0) {
			return;
		}
		if (!gastoSeleccionadoActivo()) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
					this, "El gasto ya está inhabilitado");
			return;
		}
		int opcion = JOptionPane.showConfirmDialog(this,
				"¿Desea inhabilitar el gasto con folio " + id + "?",
				"Inhabilitar gasto", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE);
		if (opcion != JOptionPane.YES_OPTION) {
			return;
		}
		final long sucursalOperacion = idSucursalActual;
		btnEliminar.setEnabled(false);
		new SwingWorker<SpResponseModel, Void>() {
			@Override
			protected SpResponseModel doInBackground() {
				return AppContext.gastoController.eliminarGasto(id, sucursalOperacion);
			}
			@Override
			protected void done() {
				btnEliminar.setEnabled(true);
				if (sucursalOperacion != idSucursalActual) {
					return;
				}
				try {
					SpResponseModel respuesta = get();
					if (respuesta != null && respuesta.id() == 200) {
						JOptionPane.showMessageDialog(PanelGastos.this,
								respuesta.message(), "Gasto inhabilitado",
								JOptionPane.INFORMATION_MESSAGE);
						llenarTablaGastos();
					} else {
						MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, PanelGastos.this,
								respuesta == null ? "No se recibió respuesta al inhabilitar"
										: respuesta.message());
					}
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, PanelGastos.this,
							"Se interrumpió la inhabilitación");
				} catch (ExecutionException ex) {
					MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, PanelGastos.this,
							"No fue posible inhabilitar el gasto: " + ex.getMessage());
				}
			}
		}.execute();
	}

	private void exportarListado() {
		try {
			DataTools.exportarTablaExcel(modelTablaGastos, this);
		} catch (Exception ex) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
					this, "No fue posible exportar los gastos: " + ex.getMessage());
		}
	}

	private record OpcionesFiltros(
			List<JComboboxDataViewModel> empleados,
			List<CategoriaDeGasto> categorias) {
	}
}
