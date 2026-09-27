package com.kathsoft.kathpos.app.view.ventas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.venta.VentaCriterioBusqueda;
import com.kathsoft.kathpos.app.model.venta.VentaFiltro;
import com.kathsoft.kathpos.app.model.venta.VentaListado;
import com.kathsoft.kathpos.app.model.venta.VentaOrdenamiento;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.app.view.Fr_principal;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;
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
	private JButton btNuevaVenta;
	private Sucursal sucursal;
	private JButton btnExportarVentasExcel;
	private JScrollPane scrollPaneTablaVentas;
	private DefaultTableModel modelTablaVentas;
	private JTable tablaVentas;
	private JPanel panelVentasCentralBuscar;
	private JTextField textField;
	private JButton btnBuscarVenta;
	private JLabel lblFInicial;
	private JFormattedTextField formattedTextFieldFechaInicial;
	private JLabel lblFfinal;
	private JFormattedTextField formattedTextFieldFechaFinal;
	private JLabel lblBuscarPor;
	private JComboBox<VentaCriterioBusqueda> comboBoxBuscarPor;
	private JLabel lblOrdernarPor;
	private JComboBox<VentaOrdenamiento> comboBoxBuscarPor_1;
	private JButton btnVerDetalles;
	private JButton btnCancelarVenta;

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

		new ButtonGroup();

		btNuevaVenta = new JButton("Punto de venta");
		btNuevaVenta.addActionListener(new ActionListener() {
			/**
			 * Atiende el evento generado al presionar el botón "Punto de venta".
			 * Su responsabilidad es delegar la apertura del formulario de captura de ventas
			 * utilizando la sucursal actualmente asociada al panel, de forma que la nueva
			 * operación conserve el contexto de sucursal desde el que fue iniciada.
			 *
			 * @param e evento de acción emitido por el botón
			 */
			public void actionPerformed(ActionEvent e) {
				abrirFormVentas(sucursal.getIdSucursal());
			}
		});
		
		this.btnCancelarVenta = new JButton("Cancelar Venta");
		this.btnCancelarVenta.setIcon(new ImageIcon(PanelVentas.class.getResource("/com/kathsoft/kathpos/app/assets/nwCancel.png")));
		this.btnCancelarVenta.setBackground(new Color(237, 51, 59));
		this.btnCancelarVenta.addActionListener(e -> this.cancelarVentaSeleccionada());
		this.panelVentasCentralBotones.add(this.btnCancelarVenta);
		
		this.btnVerDetalles = new JButton("Ver Detalles");
		this.btnVerDetalles.setIcon(new ImageIcon(PanelVentas.class.getResource("/com/kathsoft/kathpos/app/assets/reportes.jpg")));
		this.btnVerDetalles.setBackground(new Color(143, 240, 164));
		this.btnVerDetalles.addActionListener(e -> this.verDetalleVentaSeleccionada());
		this.panelVentasCentralBotones.add(this.btnVerDetalles);
		btNuevaVenta
				.setIcon(new ImageIcon(Fr_principal.class.getResource("/com/kathsoft/kathpos/app/assets/ventas.png")));
		btNuevaVenta.setBackground(new Color(152, 251, 152));
		panelVentasCentralBotones.add(btNuevaVenta);

		btnExportarVentasExcel = new JButton("Exportar a Excel");
		btnExportarVentasExcel.addActionListener(new ActionListener() {
			/**
			 * Atiende el evento generado al presionar el botón "Exportar a Excel".
			 * Delega la exportación del contenido actualmente visible en el modelo de la
			 * tabla, por lo que el archivo generado refleja el resultado vigente de los
			 * filtros y criterios de búsqueda aplicados por el usuario.
			 *
			 * @param e evento de acción emitido por el botón
			 */
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
		this.textField.addActionListener(e -> this.llenarTablaVentas());

		this.btnBuscarVenta = new JButton("Buscar");
		this.btnBuscarVenta.setIcon(
				new ImageIcon(PanelVentas.class.getResource("/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
		this.btnBuscarVenta.setFont(new Font("Dialog", Font.BOLD, 13));
		this.btnBuscarVenta.setBackground(new Color(184, 134, 11));
		this.btnBuscarVenta.addActionListener(e -> this.llenarTablaVentas());

		this.lblFInicial = new JLabel("F. inicial");

		this.formattedTextFieldFechaInicial = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaInicial.setToolTipText("dd/MM/yyyy");

		this.lblFfinal = new JLabel("F.Final");

		this.formattedTextFieldFechaFinal = new JFormattedTextField(this.buildDateFormatter());
		this.formattedTextFieldFechaFinal.setToolTipText("dd/MM/yyyy");

		this.lblBuscarPor = new JLabel("Buscar por");

		this.comboBoxBuscarPor = new JComboBox<VentaCriterioBusqueda>();
		this.llenarComboBuscarPor();

		this.lblOrdernarPor = new JLabel("Ordernar por");

		this.comboBoxBuscarPor_1 = new JComboBox<VentaOrdenamiento>();
		this.llenarComboOrdenarPor();
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

		new ButtonGroup();

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

		if (this.sucursal != null && this.sucursal.getIdSucursal() > 0) {
			this.llenarTablaVentas();
		}
	}

	/**
	 * Abre el formulario principal del punto de venta para la sucursal indicada.
	 * La creación y visualización del formulario se delega al Event Dispatch Thread
	 * mediante {@link EventQueue#invokeLater(Runnable)} para mantener las operaciones
	 * de Swing dentro del hilo correspondiente. El formulario se centra respecto a este
	 * panel y se configura para liberar únicamente su propia ventana al cerrarse.
	 *
	 * @param idSucursal identificador de la sucursal que debe conservarse como contexto
	 *                   de la nueva operación de venta
	 */
	public void abrirFormVentas(int idSucursal) {
		Component cm = this;
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				Fr_PuntoDeVentas fr = new Fr_PuntoDeVentas(idSucursal, PanelVentas.this::llenarTablaVentas);
				fr.setLocationRelativeTo(cm);
				fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				fr.setVisible(true);
			}
		});
	}

	private void abrirDetalleVenta(int idVenta) {
		if (this.sucursal == null || this.sucursal.getIdSucursal() <= 0 || idVenta <= 0) {
			return;
		}

		Component cm = this;
		EventQueue.invokeLater(() -> {
			Fr_PuntoDeVentas fr = new Fr_PuntoDeVentas(this.sucursal.getIdSucursal(), PanelVentas.this::llenarTablaVentas);
			fr.setLocationRelativeTo(cm);
			fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			if (fr.cargarVentaPorId(idVenta)) {
				fr.setVisible(true);
			} else {
				fr.dispose();
			}
		});
	}

	private Integer obtenerIdVentaSeleccionada() {
		int filaVista = this.tablaVentas.getSelectedRow();
		if (filaVista < 0) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, "Seleccione una venta de la tabla");
			return null;
		}

		int filaModelo = this.tablaVentas.convertRowIndexToModel(filaVista);
		Object valorId = this.modelTablaVentas.getValueAt(filaModelo, 0);
		try {
			int idVenta = valorId instanceof Number numero ? numero.intValue()
					: Integer.parseInt(String.valueOf(valorId).trim());
			if (idVenta <= 0) {
				throw new NumberFormatException();
			}
			return Integer.valueOf(idVenta);
		} catch (Exception er) {
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"No fue posible identificar la venta seleccionada");
			return null;
		}
	}

	private void verDetalleVentaSeleccionada() {
		Integer idVenta = this.obtenerIdVentaSeleccionada();
		if (idVenta != null) {
			this.abrirDetalleVenta(idVenta.intValue());
		}
	}

	private void cancelarVentaSeleccionada() {
		Integer idVenta = this.obtenerIdVentaSeleccionada();
		if (idVenta == null) {
			return;
		}

		int confirmacion = JOptionPane.showConfirmDialog(this,
				"¿Está seguro de cancelar la venta " + idVenta + "?\nLas existencias serán reincorporadas por el sistema.",
				"Cancelar venta", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (confirmacion != JOptionPane.YES_OPTION) {
			return;
		}

		try {
			SpResponseModel respuesta = AppContext.ventasController.cancelVenta(idVenta.intValue());
			if (this.esRespuestaError(respuesta)) {
				String mensaje = respuesta == null ? "No se recibió respuesta al cancelar la venta" : respuesta.message();
				MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, mensaje);
				return;
			}

			JOptionPane.showMessageDialog(this, respuesta.message(), "Venta cancelada", JOptionPane.INFORMATION_MESSAGE);
			this.llenarTablaVentas();
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	private boolean esRespuestaError(SpResponseModel respuesta) {
		if (respuesta == null || respuesta.id() <= 0) {
			return true;
		}
		if (respuesta.id() != 500) {
			return false;
		}
		return respuesta.message() == null
				|| !respuesta.message().toLowerCase(Locale.ROOT).contains("correct");
	}

	/**
	 * Elimina todas las filas actualmente cargadas en el modelo de la tabla de ventas
	 * y solicita la actualización visual del componente. Se utiliza antes de poblar un
	 * nuevo resultado para evitar que una búsqueda o recarga mezcle registros anteriores
	 * con los obtenidos por el filtro vigente.
	 */
	private void borrarElementosDeLaTablaVentas() {
		this.modelTablaVentas.getDataVector().removeAllElements();
		this.tablaVentas.updateUI();
	}

	/**
	 * Consulta y vuelve a poblar la tabla de ventas utilizando el estado actual de los
	 * controles de búsqueda del panel. Primero valida que exista una sucursal válida,
	 * después construye un {@link VentaFiltro}, invoca
	 * {@code VentasController.listVentas(...)} a través de {@link AppContext}, limpia el
	 * resultado anterior y agrega cada {@link VentaListado} al modelo de tabla.
	 * <p>
	 * El método centraliza también la respuesta ante errores de entrada: informa fechas
	 * con formato inválido, muestra advertencias para reglas de filtro no válidas y evita
	 * reemplazar silenciosamente el contenido de la tabla cuando la consulta no puede
	 * construirse correctamente.
	 */
	public void llenarTablaVentas() {
		if (this.sucursal == null || this.sucursal.getIdSucursal() <= 0) {
			return;
		}

		try {
			VentaFiltro filtro = this.buildVentaFiltro();
			
			List<VentaListado> ventas = AppContext.ventasController.listVentas(this.sucursal.getIdSucursal(), filtro);

			this.borrarElementosDeLaTablaVentas();
			ventas.forEach(this::addVentaListadoToTable);
			
		} catch (ParseException er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this,
					"Formato de fecha inválido. Usa dd/MM/yyyy");
		} catch (IllegalArgumentException er) {
			MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, er.getMessage());
		}
	}

	/**
	 * Construye el objeto de criterios enviado al listado de ventas a partir de los
	 * controles visibles del panel. Recupera el tipo de búsqueda, el texto ingresado,
	 * el criterio de ordenamiento y las fechas opcionales. Cuando los combos no tienen
	 * una selección válida utiliza los valores seguros {@code TODOS} y {@code FECHA}.
	 * <p>
	 * Las fechas son convertidas con validación estricta y, si ambas fueron indicadas,
	 * se comprueba que la fecha inicial no sea posterior a la final. Para el criterio
	 * {@code TODOS} el texto de búsqueda se descarta porque no participa en la consulta.
	 *
	 * @return filtro normalizado y listo para enviarse a {@code listVentas}
	 * @throws ParseException si alguno de los campos de fecha contiene una fecha
	 *                        incompleta o inválida
	 * @throws IllegalArgumentException si el intervalo de fechas es cronológicamente
	 *                                  inválido
	 */
	private VentaFiltro buildVentaFiltro() throws ParseException {
		VentaCriterioBusqueda criterio = (VentaCriterioBusqueda) this.comboBoxBuscarPor.getSelectedItem();
		VentaOrdenamiento ordenamiento = (VentaOrdenamiento) this.comboBoxBuscarPor_1.getSelectedItem();
		String textoBusqueda = this.textField.getText() == null ? "" : this.textField.getText().trim();

		if (criterio == null) {
			criterio = VentaCriterioBusqueda.TODOS;
		}
		if (ordenamiento == null) {
			ordenamiento = VentaOrdenamiento.FECHA;
		}

		Date fechaInicial = this.parseFechaFiltro(this.formattedTextFieldFechaInicial);
		Date fechaFinal = this.parseFechaFiltro(this.formattedTextFieldFechaFinal);
		if (fechaInicial != null && fechaFinal != null && fechaInicial.after(fechaFinal)) {
			throw new IllegalArgumentException("La fecha inicial no puede ser posterior a la fecha final");
		}

		return new VentaFiltro(criterio.getValor(), criterio == VentaCriterioBusqueda.TODOS ? "" : textoBusqueda,
				ordenamiento.getValor(), fechaInicial, fechaFinal);
	}

	/**
	 * Agrega al modelo de la tabla una representación de una venta ya mapeada por el
	 * controlador. Conserva el mismo orden de columnas definido por la UI: folio, fecha,
	 * tipo, empleado que atendió, cliente, subtotal, IVA, total y estado de vigencia.
	 * Las referencias nulas se ignoran para no insertar filas inconsistentes.
	 *
	 * @param venta registro de venta que será representado como una fila de la tabla
	 */
	private void addVentaListadoToTable(VentaListado venta) {
		if (venta == null) {
			return;
		}

		this.modelTablaVentas.addRow(new Object[] { venta.getFolio(), venta.getFecha(), venta.getTipo(),
				venta.getAtendio(), venta.getCliente(), venta.getSubtotal(), venta.getIva(), venta.getTotal(),
				venta.getVigente() });
	}

	/**
	 * Inicializa el combo de criterios de búsqueda con todos los valores declarados en
	 * {@link VentaCriterioBusqueda}. El combo se limpia antes de cargar los elementos para
	 * que una reinicialización del panel no produzca criterios duplicados.
	 */
	private void llenarComboBuscarPor() {
		this.comboBoxBuscarPor.removeAllItems();
		for (VentaCriterioBusqueda criterio : VentaCriterioBusqueda.values()) {
			this.comboBoxBuscarPor.addItem(criterio);
		}
	}

	/**
	 * Inicializa el combo de ordenamiento con los criterios soportados por
	 * {@link VentaOrdenamiento}. Después de cargar las opciones establece
	 * {@link VentaOrdenamiento#FECHA} como selección predeterminada para que el listado
	 * inicial tenga un criterio de ordenamiento explícito.
	 */
	private void llenarComboOrdenarPor() {
		this.comboBoxBuscarPor_1.removeAllItems();
		for (VentaOrdenamiento ordenamiento : VentaOrdenamiento.values()) {
			this.comboBoxBuscarPor_1.addItem(ordenamiento);
		}
		this.comboBoxBuscarPor_1.setSelectedItem(VentaOrdenamiento.FECHA);
	}

	/**
	 * Construye el formateador utilizado por los campos de fecha del panel. La máscara
	 * obliga a capturar ocho dígitos con la estructura {@code dd/MM/yyyy}, muestra
	 * guiones bajos en las posiciones pendientes y restringe la entrada a caracteres
	 * numéricos. La validez calendárica de la fecha se comprueba posteriormente al
	 * convertir el valor mediante {@link #parseFechaFiltro(JFormattedTextField)}.
	 *
	 * @return formateador de máscara para fechas, o {@code null} si la máscara no puede
	 *         construirse
	 */
	private MaskFormatter buildDateFormatter() {
		try {
			MaskFormatter formatter = new MaskFormatter("##/##/####");
			formatter.setPlaceholderCharacter('_');
			formatter.setValidCharacters("0123456789");
			return formatter;
		} catch (ParseException er) {
			er.printStackTrace(System.err);
			return null;
		}
	}

	/**
	 * Convierte el contenido de un campo de fecha a {@link Date} para enviarlo como
	 * parámetro del filtro de ventas. Un campo sin valor se representa como
	 * {@code null}, permitiendo que el procedimiento almacenado omita ese límite del
	 * rango. Si la máscara todavía contiene posiciones pendientes o la fecha no existe
	 * en el calendario, la conversión se rechaza.
	 *
	 * @param field campo formateado del que se obtendrá la fecha
	 * @return fecha SQL equivalente al valor capturado, o {@code null} cuando el campo
	 *         se encuentra vacío
	 * @throws ParseException si la fecha está incompleta, no cumple {@code dd/MM/yyyy}
	 *                        o representa una fecha calendárica inválida
	 */
	private Date parseFechaFiltro(JFormattedTextField field) throws ParseException {
		String fecha = field.getText() == null ? "" : field.getText().trim();
		if (this.isFechaVacia(fecha)) {
			return null;
		}
		if (fecha.contains("_")) {
			throw new ParseException("Fecha incompleta", 0);
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		return new Date(dateFormat.parse(fecha).getTime());
	}

	/**
	 * Determina si el texto de un campo de fecha representa ausencia de filtro. Se
	 * consideran vacíos tanto una referencia nula o una cadena sin contenido como la
	 * máscara sin capturar {@code __/__/____} generada por {@link MaskFormatter}.
	 *
	 * @param fecha texto que será evaluado
	 * @return {@code true} cuando no existe una fecha capturada; {@code false} en caso
	 *         contrario
	 */
	private boolean isFechaVacia(String fecha) {
		return fecha == null || fecha.trim().isEmpty() || "__/__/____".equals(fecha.trim());
	}

	/**
	 * Exporta el contenido actual de la tabla de ventas mediante la utilidad compartida
	 * de exportación. El método trabaja sobre {@code modelTablaVentas}, por lo que exporta
	 * exactamente las filas resultantes de la última consulta o filtro aplicado. Si la
	 * escritura falla, conserva la vista y comunica el error al usuario mediante
	 * {@link MessageHandler}.
	 */
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