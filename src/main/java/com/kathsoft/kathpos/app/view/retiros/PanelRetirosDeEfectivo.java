package com.kathsoft.kathpos.app.view.retiros;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

import com.kathsoft.kathpos.app.model.Sucursal;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoFiltro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

/**
 * Panel de retiros de efectivo, siguiendo el patrón de PanelGastos.
 *
 * <p>La sucursal se inyecta desde el login; los filtros no pueden
 * modificarla. Los retiros no tienen edición: sólo permiten consulta
 * e inhabilitación, sujeta a la fecha de registro en MySQL.</p>
 */
public class PanelRetirosDeEfectivo extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FECHA_FILTRO =
            DateTimeFormatter.ofPattern("dd/MM/uuuu")
                    .withResolverStyle(ResolverStyle.STRICT);
    private static final JComboboxDataViewModel OPCION_TODOS =
            new JComboboxDataViewModel(0, "Todos");

    private JPanel panelSuperiorTitulo;
    private JPanel panelSuperiorBotones;
    private JPanel panelInferiorFiltros;
    private JLabel lblTitulo;
    private JLabel lblEmpleado;
    private JLabel lblOrdenarPor;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JScrollPane scrollPaneRetiros;
    private JTable tableRetiros;
    private DefaultTableModel modelTablaRetiros;
    private JComboBox<JComboboxDataViewModel> comboBoxEmpleado;
    private JComboBox<RetiroDeEfectivoFiltro.Orden> comboBoxOrdenarPor;
    private JFormattedTextField formattedTextFieldFechaInicio;
    private JFormattedTextField formattedTextFieldFechaFinal;
    private JButton btnAgregar;
    private JButton btnVerDetalle;
    private JButton btnInhabilitar;
    private JButton btnExportarExcel;
    private JButton btnBuscar;

    private long idSucursalActual;
    private boolean filtrosCargados;
    private boolean cargandoFiltros;
    private int versionListado;

    /**
     * Constructor sin consultas JDBC para la vista previa de WindowBuilder.
     * En producción inyecte la sucursal con el otro constructor o el setter.
     */
    public PanelRetirosDeEfectivo() {
        setBackground(new Color(255, 215, 0));
        setBorder(null);

        panelSuperiorTitulo = new JPanel();
        panelSuperiorTitulo.setBackground(new Color(0, 0, 128));

        lblTitulo = new JLabel("Retiros de efectivo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
        panelSuperiorTitulo.add(lblTitulo);

        panelSuperiorBotones = new JPanel();
        panelSuperiorBotones.setBackground(new Color(255, 204, 0));
        FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);

        btnAgregar = new JButton("Agregar");
        btnAgregar.setIcon(new ImageIcon(PanelRetirosDeEfectivo.class.getResource(
                "/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
        btnAgregar.setBackground(new Color(144, 238, 144));
        panelSuperiorBotones.add(btnAgregar);

        btnVerDetalle = new JButton("Ver detalles");
        btnVerDetalle.setIcon(new ImageIcon(PanelRetirosDeEfectivo.class.getResource(
                "/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
        btnVerDetalle.setBackground(new Color(144, 238, 144));
        panelSuperiorBotones.add(btnVerDetalle);

        btnInhabilitar = new JButton("Inhabilitar");
        btnInhabilitar.setIcon(new ImageIcon(PanelRetirosDeEfectivo.class.getResource(
                "/com/kathsoft/kathpos/app/assets/nwCancel.png")));
        btnInhabilitar.setBackground(new Color(255, 51, 0));
        panelSuperiorBotones.add(btnInhabilitar);

        btnExportarExcel = new JButton("Exportar Excel");
        btnExportarExcel.setIcon(new ImageIcon(PanelRetirosDeEfectivo.class.getResource(
                "/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
        btnExportarExcel.setBackground(new Color(102, 205, 170));
        panelSuperiorBotones.add(btnExportarExcel);

        scrollPaneRetiros = new JScrollPane();

        panelInferiorFiltros = new JPanel();
        panelInferiorFiltros.setBackground(new Color(0, 191, 255));

        // Estructura explícita del formulario: no construir grupos en bucles.
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addComponent(panelSuperiorTitulo, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                .addComponent(panelSuperiorBotones, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                .addComponent(panelInferiorFiltros, GroupLayout.DEFAULT_SIZE, 830, Short.MAX_VALUE)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPaneRetiros, GroupLayout.DEFAULT_SIZE, 806,
                            Short.MAX_VALUE)
                    .addContainerGap())
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addComponent(panelSuperiorTitulo, GroupLayout.PREFERRED_SIZE, 33,
                            GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelSuperiorBotones, GroupLayout.PREFERRED_SIZE, 40,
                            GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPaneRetiros, GroupLayout.DEFAULT_SIZE, 373,
                            Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(panelInferiorFiltros, GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );

        tableRetiros = new JTable();
        scrollPaneRetiros.setViewportView(tableRetiros);

        lblEmpleado = new JLabel("Empleado");
        comboBoxEmpleado = new JComboBox<JComboboxDataViewModel>();

        lblOrdenarPor = new JLabel("Ordenar por");
        comboBoxOrdenarPor = new JComboBox<RetiroDeEfectivoFiltro.Orden>();

        lblDesde = new JLabel("Desde");
        formattedTextFieldFechaInicio = new JFormattedTextField();
        formattedTextFieldFechaInicio.setFormatterFactory(
                new DefaultFormatterFactory(crearMascaraFecha()));
        formattedTextFieldFechaInicio.setFocusLostBehavior(JFormattedTextField.PERSIST);
        formattedTextFieldFechaInicio.setToolTipText("dd/MM/aaaa");

        lblHasta = new JLabel("Hasta");
        formattedTextFieldFechaFinal = new JFormattedTextField();
        formattedTextFieldFechaFinal.setFormatterFactory(
                new DefaultFormatterFactory(crearMascaraFecha()));
        formattedTextFieldFechaFinal.setFocusLostBehavior(JFormattedTextField.PERSIST);
        formattedTextFieldFechaFinal.setToolTipText("dd/MM/aaaa");

        btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon(PanelRetirosDeEfectivo.class.getResource(
                "/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
        btnBuscar.setFont(new Font("Dialog", Font.BOLD, 13));
        btnBuscar.setBackground(new Color(184, 134, 11));

        GroupLayout gl_panelInferiorFiltros = new GroupLayout(panelInferiorFiltros);
        gl_panelInferiorFiltros.setHorizontalGroup(
        	gl_panelInferiorFiltros.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelInferiorFiltros.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelInferiorFiltros.createParallelGroup(Alignment.LEADING)
        				.addGroup(gl_panelInferiorFiltros.createSequentialGroup()
        					.addComponent(lblEmpleado)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(comboBoxEmpleado, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(lblOrdenarPor)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(comboBoxOrdenarPor, 0, 54, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
        				.addGroup(gl_panelInferiorFiltros.createSequentialGroup()
        					.addComponent(lblDesde)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(formattedTextFieldFechaInicio, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(lblHasta)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(formattedTextFieldFechaFinal, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
        			.addContainerGap())
        );
        gl_panelInferiorFiltros.setVerticalGroup(
        	gl_panelInferiorFiltros.createParallelGroup(Alignment.LEADING)
        		.addGroup(gl_panelInferiorFiltros.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(gl_panelInferiorFiltros.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblEmpleado)
        				.addComponent(comboBoxEmpleado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblOrdenarPor)
        				.addComponent(comboBoxOrdenarPor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnBuscar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(gl_panelInferiorFiltros.createParallelGroup(Alignment.BASELINE)
        				.addComponent(lblDesde)
        				.addComponent(formattedTextFieldFechaInicio, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblHasta)
        				.addComponent(formattedTextFieldFechaFinal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        panelInferiorFiltros.setLayout(gl_panelInferiorFiltros);
        setLayout(groupLayout);

        modelTablaRetiros = crearModeloTabla();
        tableRetiros.setModel(modelTablaRetiros);
        tableRetiros.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DataTools.removerEditorDeTabla(tableRetiros, modelTablaRetiros);
        DataTools.definirTamanioDeColumnas(
                ConstantsConllections.tablaRetirosDeEfectivoColumnsWidth, tableRetiros);

        comboBoxEmpleado.addItem(OPCION_TODOS);
        cargarOrdenamientos();

        btnAgregar.addActionListener(event ->
                abrirFormularioRetiro(Fr_DatosRetiroDeEfectivo.OPCION_CREAR, 0));
        btnVerDetalle.addActionListener(event -> verDetalleSeleccionado());
        btnInhabilitar.addActionListener(event -> inhabilitarSeleccionado());
        btnExportarExcel.addActionListener(event -> exportarListado());
        btnBuscar.addActionListener(event -> llenarTablaRetiros());

        // Sin conexión durante el constructor / previsualización de WindowBuilder.
        addHierarchyListener(event -> {
            if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0
                    && isShowing() && idSucursalActual > 0
                    && !filtrosCargados && !cargandoFiltros) {
                cargarFiltros();
            }
        });
    }

    /**
     * Constructor recomendado para producción, siguiendo PanelGastos.
     */
    public PanelRetirosDeEfectivo(Sucursal sucursal) {
        this();
        establecerSucursalActual(sucursal);
    }

    /**
     * Se llama desde el módulo padre si se usó el constructor vacío.
     */
    public void establecerSucursalActual(Sucursal sucursal) {
        if (sucursal == null || sucursal.getIdSucursal() <= 0) {
            throw new IllegalArgumentException("Es obligatoria la sucursal autenticada");
        }
        idSucursalActual = sucursal.getIdSucursal();
        filtrosCargados = false;
        versionListado++;
        modelTablaRetiros.setRowCount(0);
        comboBoxEmpleado.removeAllItems();
        comboBoxEmpleado.addItem(OPCION_TODOS);
        if (isShowing() && !cargandoFiltros) {
            cargarFiltros();
        }
    }

    /**
     * El orden de columnas corresponde a
     * RetiroDeEfectivoController.verRetirosEnTabla.
     */
    static DefaultTableModel crearModeloTabla() {
        return new DefaultTableModel(new Object[] {
                "ID", "Folio", "Fecha", "Empleado",
                "Descripción", "Importe", "Activo"
        }, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
    }

    private void cargarOrdenamientos() {
        comboBoxOrdenarPor.removeAllItems();
        for (RetiroDeEfectivoFiltro.Orden orden : RetiroDeEfectivoFiltro.Orden.values()) {
            comboBoxOrdenarPor.addItem(orden);
        }
        comboBoxOrdenarPor.setSelectedItem(RetiroDeEfectivoFiltro.Orden.FECHA_RECIENTE);
    }

    private void cargarFiltros() {
        if (idSucursalActual <= 0 || cargandoFiltros) {
            return;
        }
        cargandoFiltros = true;
        btnBuscar.setEnabled(false);
        final long sucursalConsulta = idSucursalActual;
        new SwingWorker<Vector<JComboboxDataViewModel>, Void>() {
            @Override
            protected Vector<JComboboxDataViewModel> doInBackground() throws Exception {
                return AppContext.retiroDeEfectivoController
                        .listarEmpleadosFiltro(sucursalConsulta);
            }

            @Override
            protected void done() {
                cargandoFiltros = false;
                if (sucursalConsulta != idSucursalActual) {
                    if (isShowing()) {
                        cargarFiltros();
                    }
                    return;
                }
                btnBuscar.setEnabled(true);
                try {
                    Vector<JComboboxDataViewModel> empleados = get();
                    comboBoxEmpleado.removeAllItems();
                    comboBoxEmpleado.addItem(OPCION_TODOS);
                    for (JComboboxDataViewModel empleado : empleados) {
                        comboBoxEmpleado.addItem(empleado);
                    }
                    filtrosCargados = true;
                    llenarTablaRetiros();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarError("Se interrumpió la carga de empleados");
                } catch (ExecutionException ex) {
                    mostrarError("No fue posible cargar el filtro de empleados: " + causa(ex));
                }
            }
        }.execute();
    }

    private static MaskFormatter crearMascaraFecha() {
        try {
            MaskFormatter mascara = new MaskFormatter("##/##/####");
            mascara.setPlaceholderCharacter('_');
            mascara.setValidCharacters("0123456789");
            return mascara;
        } catch (ParseException ex) {
            throw new IllegalStateException("No se pudo inicializar la máscara de fecha", ex);
        }
    }

    /**
     * La máscara limita el formato, pero esta conversión también
     * rechaza días inexistentes y fechas incompletas.
     */
    static LocalDate interpretarFechaFiltro(String valor) {
        if (valor == null || valor.isBlank() || "__/__/____".equals(valor.trim())) {
            return null;
        }
        String fecha = valor.trim();
        if (fecha.indexOf('_') >= 0) {
            throw new IllegalArgumentException("Complete la fecha (dd/MM/aaaa)");
        }
        try {
            return LocalDate.parse(fecha, FECHA_FILTRO);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Fecha inválida. Utilice dd/MM/aaaa");
        }
    }

    private RetiroDeEfectivoFiltro construirFiltro() {
        JComboboxDataViewModel empleado =
                (JComboboxDataViewModel) comboBoxEmpleado.getSelectedItem();
        Integer idEmpleado = empleado == null || empleado.id() <= 0
                ? null : empleado.id();
        return new RetiroDeEfectivoFiltro(
                idEmpleado,
                interpretarFechaFiltro(formattedTextFieldFechaInicio.getText()),
                interpretarFechaFiltro(formattedTextFieldFechaFinal.getText()),
                (RetiroDeEfectivoFiltro.Orden) comboBoxOrdenarPor.getSelectedItem());
    }

    /**
     * Consulta asíncrona: preserva el resultado anterior si se produce
     * un error; el contador impide mostrar respuestas de filtros obsoletos.
     */
    public void llenarTablaRetiros() {
        if (idSucursalActual <= 0) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "Asigne la sucursal autenticada antes de consultar");
            return;
        }
        if (!filtrosCargados) {
            cargarFiltros();
            return;
        }
        final RetiroDeEfectivoFiltro filtro;
        try {
            filtro = construirFiltro();
        } catch (IllegalArgumentException ex) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
            return;
        }

        final long sucursalConsulta = idSucursalActual;
        final int version = ++versionListado;
        btnBuscar.setEnabled(false);
        new SwingWorker<Vector<Object[]>, Void>() {
            @Override
            protected Vector<Object[]> doInBackground() throws Exception {
                return AppContext.retiroDeEfectivoController
                        .verRetirosEnTabla(sucursalConsulta, filtro);
            }

            @Override
            protected void done() {
                if (sucursalConsulta != idSucursalActual || version != versionListado) {
                    return;
                }
                btnBuscar.setEnabled(true);
                try {
                    Vector<Object[]> filas = get();
                    modelTablaRetiros.setRowCount(0);
                    for (Object[] fila : filas) {
                        modelTablaRetiros.addRow(fila);
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarError("Se interrumpió la consulta de retiros");
                } catch (ExecutionException ex) {
                    mostrarError("No fue posible consultar los retiros: " + causa(ex));
                }
            }
        }.execute();
    }

    /**
     * Selección por modelo: funciona incluso cuando se active RowSorter.
     */
    static int idRetiroSeleccionado(JTable tabla, DefaultTableModel modelo) {
        int fila = tabla.getSelectedRow();
        if (fila < 0) {
            return -1;
        }
        Object id = modelo.getValueAt(tabla.convertRowIndexToModel(fila), 0);
        return id instanceof Number numero && numero.intValue() > 0
                ? numero.intValue() : -1;
    }

    private int obtenerSeleccion() {
        int id = idRetiroSeleccionado(tableRetiros, modelTablaRetiros);
        if (id <= 0) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "Seleccione un retiro válido en la tabla");
        }
        return id;
    }

    private boolean seleccionadoActivo() {
        int fila = tableRetiros.getSelectedRow();
        return fila >= 0 && "Activo".equals(
                modelTablaRetiros.getValueAt(tableRetiros.convertRowIndexToModel(fila), 6));
    }

    private void abrirFormularioRetiro(int opcion, int idRetiro) {
        if (idSucursalActual <= 0) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "El módulo requiere la sucursal de la sesión");
            return;
        }
        Fr_DatosRetiroDeEfectivo formulario =
                new Fr_DatosRetiroDeEfectivo(opcion, idRetiro, idSucursalActual);
        formulario.setLocationRelativeTo(this);
        formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formulario.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                if (formulario.isOperacionEjecutada()) {
                    llenarTablaRetiros();
                }
            }
        });
        formulario.setVisible(true);
    }

    private void verDetalleSeleccionado() {
        int id = obtenerSeleccion();
        if (id > 0) {
            abrirFormularioRetiro(Fr_DatosRetiroDeEfectivo.OPCION_DETALLE, id);
        }
    }

    /**
     * La fecha de cancelación se valida en el SP usando CURDATE() de
     * MySQL; el reloj de la estación de trabajo no es autoridad.
     */
    private void inhabilitarSeleccionado() {
        int id = obtenerSeleccion();
        if (id <= 0) {
            return;
        }
        if (!seleccionadoActivo()) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "El retiro seleccionado ya se encuentra inhabilitado");
            return;
        }
        int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Desea inhabilitar el retiro con ID " + id
                    + "? Sólo se permite durante el día de registro.",
                "Inhabilitar retiro", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        btnInhabilitar.setEnabled(false);
        final long sucursalOperacion = idSucursalActual;
        new SwingWorker<SpResponseModel, Void>() {
            @Override
            protected SpResponseModel doInBackground() {
                return AppContext.retiroDeEfectivoController
                        .inhabilitarRetiro(id, sucursalOperacion);
            }

            @Override
            protected void done() {
                btnInhabilitar.setEnabled(true);
                if (sucursalOperacion != idSucursalActual) {
                    return;
                }
                try {
                    SpResponseModel respuesta = get();
                    if (respuesta != null && respuesta.id() == 200) {
                        JOptionPane.showMessageDialog(PanelRetirosDeEfectivo.this,
                                respuesta.message(), "Retiro inhabilitado",
                                JOptionPane.INFORMATION_MESSAGE);
                        llenarTablaRetiros();
                    } else {
                        mostrarError(respuesta == null
                                ? "No se recibió respuesta del procedimiento"
                                : respuesta.message());
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarError("Se interrumpió la inhabilitación");
                } catch (ExecutionException ex) {
                    mostrarError("No fue posible inhabilitar el retiro: " + causa(ex));
                }
            }
        }.execute();
    }

    private void exportarListado() {
        try {
            DataTools.exportarTablaExcel(modelTablaRetiros, this);
        } catch (Exception ex) {
            mostrarError("No fue posible exportar el listado: " + ex.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, mensaje);
    }

    private static String causa(ExecutionException error) {
        Throwable causa = error.getCause() == null ? error : error.getCause();
        return causa.getMessage() == null ? causa.getClass().getSimpleName()
                : causa.getMessage();
    }
}
