package com.kathsoft.kathpos.app.view.retiros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.Arrays;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoRegistro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

/**
 * Formulario de alta y consulta de retiros de efectivo.
 *
 * <p>No existe una modalidad de actualización: las bajas lógicas sólo
 * se realizan desde el panel y el SP comprueba la fecha y sucursal.</p>
 *
 * <p>GroupLayout explícito compatible con WindowBuilder. La conexión a
 * MySQL sólo se abre al mostrarse una instancia de ejecución real.</p>
 */
public class Fr_DatosRetiroDeEfectivo extends JFrame {

    private static final long serialVersionUID = 1L;
    public static final int OPCION_CREAR = 0;
    public static final int OPCION_DETALLE = 1;
    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private final int opcion;
    private final int idRetiro;
    private final long idSucursal;
    private final boolean vistaDisenador;
    private boolean datosCargados;
    private boolean operacionEjecutada;
    private boolean procesando;

    private JPanel contentPane;
    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotones;
    private JLabel lblTitulo;
    private JLabel lblFolio;
    private JLabel lblFecha;
    private JLabel lblEmpleado;
    private JLabel lblDescripcion;
    private JLabel lblImporte;
    private JTextField txfFolio;
    private JTextField txfFecha;
    private JComboBox<JComboboxDataViewModel> cmbEmpleado;
    private JScrollPane scrollPaneDescripcion;
    private JTextArea txaDescripcion;
    private JTextField txfImporte;
    private JButton btnCancelar;
    private JButton btnGuardar;

    /**
     * Constructor utilizado por el diseñador de Eclipse. No accede a JDBC
     * ni permite registrar sin una sucursal autenticada.
     */
    public Fr_DatosRetiroDeEfectivo() {
        this(OPCION_CREAR, 0, 0, true);
    }

    /**
     * @param opcion OPCION_CREAR u OPCION_DETALLE
     * @param idRetiro cero para alta o ID del retiro que se consulta
     * @param idSucursal sucursal autenticada del módulo padre
     */
    public Fr_DatosRetiroDeEfectivo(int opcion, int idRetiro, long idSucursal) {
        this(opcion, idRetiro, idSucursal, false);
    }

    private Fr_DatosRetiroDeEfectivo(
            int opcion, int idRetiro, long idSucursal, boolean vistaDisenador) {
        if (opcion != OPCION_CREAR && opcion != OPCION_DETALLE) {
            throw new IllegalArgumentException("La operación de retiro es inválida");
        }
        if (!vistaDisenador && (idSucursal <= 0
                || (opcion == OPCION_CREAR && idRetiro != 0)
                || (opcion == OPCION_DETALLE && idRetiro <= 0))) {
            throw new IllegalArgumentException("Retiro o sucursal de sesión inválidos");
        }
        this.opcion = opcion;
        this.idRetiro = idRetiro;
        this.idSucursal = idSucursal;
        this.vistaDisenador = vistaDisenador;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 560, 455);
        setMinimumSize(new java.awt.Dimension(510, 415));
        setTitle(opcion == OPCION_CREAR ? "Registrar retiro de efectivo"
                : "Detalle del retiro de efectivo");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 215, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 0, 128));
        contentPane.add(panelTitulo, BorderLayout.NORTH);

        lblTitulo = new JLabel(opcion == OPCION_CREAR
                ? "Registrar retiro de efectivo" : "Detalle del retiro de efectivo");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        panelTitulo.add(lblTitulo);

        panelFormulario = new JPanel();
        panelFormulario.setBackground(new Color(255, 215, 0));
        panelFormulario.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(panelFormulario, BorderLayout.CENTER);

        lblFolio = new JLabel("Folio");
        txfFolio = new JTextField();
        txfFolio.setColumns(10);
        txfFolio.setToolTipText("Folio único, hasta 10 caracteres");

        lblFecha = new JLabel("Fecha");
        txfFecha = new JTextField("Asignada por el servidor al guardar");
        txfFecha.setEditable(false);
        txfFecha.setColumns(10);

        lblEmpleado = new JLabel("Empleado");
        cmbEmpleado = new JComboBox<JComboboxDataViewModel>();

        lblDescripcion = new JLabel("Descripción");
        scrollPaneDescripcion = new JScrollPane();

        lblImporte = new JLabel("Importe");
        txfImporte = new JTextField();
        txfImporte.setColumns(10);
        txfImporte.setToolTipText("Importe del retiro, con hasta dos decimales");

        // Estructura literal declarativa: WindowBuilder puede reconstruir
        // todos los intervalos, sin grupos temporales creados en bucles.
        GroupLayout gl_panelFormulario = new GroupLayout(panelFormulario);
        gl_panelFormulario.setHorizontalGroup(
            gl_panelFormulario.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelFormulario.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelFormulario.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelFormulario.createSequentialGroup()
                            .addComponent(lblFolio, GroupLayout.PREFERRED_SIZE, 106,
                                    GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfFolio, GroupLayout.DEFAULT_SIZE, 374,
                                    Short.MAX_VALUE))
                        .addGroup(gl_panelFormulario.createSequentialGroup()
                            .addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 106,
                                    GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfFecha, GroupLayout.DEFAULT_SIZE, 374,
                                    Short.MAX_VALUE))
                        .addGroup(gl_panelFormulario.createSequentialGroup()
                            .addComponent(lblEmpleado, GroupLayout.PREFERRED_SIZE, 106,
                                    GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmbEmpleado, 0, 374, Short.MAX_VALUE))
                        .addComponent(lblDescripcion)
                        .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 486,
                                Short.MAX_VALUE)
                        .addGroup(gl_panelFormulario.createSequentialGroup()
                            .addComponent(lblImporte, GroupLayout.PREFERRED_SIZE, 106,
                                    GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfImporte, GroupLayout.DEFAULT_SIZE, 374,
                                    Short.MAX_VALUE)))
                    .addContainerGap())
        );
        gl_panelFormulario.setVerticalGroup(
            gl_panelFormulario.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelFormulario.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFolio)
                        .addComponent(txfFolio, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFecha)
                        .addComponent(txfFecha, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEmpleado)
                        .addComponent(cmbEmpleado, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblDescripcion)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 146,
                            Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblImporte)
                        .addComponent(txfImporte, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );

        txaDescripcion = new JTextArea();
        txaDescripcion.setLineWrap(true);
        txaDescripcion.setWrapStyleWord(true);
        scrollPaneDescripcion.setViewportView(txaDescripcion);
        panelFormulario.setLayout(gl_panelFormulario);

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(30, 144, 255));
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(panelBotones, BorderLayout.SOUTH);

        btnCancelar = new JButton(opcion == OPCION_CREAR ? "Cancelar" : "Cerrar");
        btnCancelar.setBackground(new Color(205, 92, 92));
        btnCancelar.addActionListener(event -> {
            if (!procesando) {
                dispose();
            }
        });
        panelBotones.add(btnCancelar);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.setEnabled(false);
        if (opcion == OPCION_DETALLE) {
            btnGuardar.setVisible(false);
            activarSoloLectura();
        }
        btnGuardar.addActionListener(event -> guardarRetiro());
        panelBotones.add(btnGuardar);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                if (!Fr_DatosRetiroDeEfectivo.this.vistaDisenador && !datosCargados) {
                    datosCargados = true;
                    cargarFormulario();
                }
            }

            @Override
            public void windowClosing(WindowEvent event) {
                if (procesando) {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
    }

    private void activarSoloLectura() {
        txfFolio.setEditable(false);
        txfFecha.setEditable(false);
        cmbEmpleado.setEnabled(false);
        txaDescripcion.setEditable(false);
        txfImporte.setEditable(false);
    }

    private void cargarFormulario() {
        btnGuardar.setEnabled(false);
        if (opcion == OPCION_DETALLE) {
            cargarDetalle();
        } else {
            cargarEmpleados();
        }
    }

    private void cargarEmpleados() {
        new SwingWorker<Vector<JComboboxDataViewModel>, Void>() {
            @Override
            protected Vector<JComboboxDataViewModel> doInBackground() throws SQLException {
                return AppContext.retiroDeEfectivoController.listarEmpleadosActivos(idSucursal);
            }

            @Override
            protected void done() {
                try {
                    Vector<JComboboxDataViewModel> empleados = get();
                    for (JComboboxDataViewModel empleado : empleados) {
                        cmbEmpleado.addItem(empleado);
                    }
                    cmbEmpleado.setSelectedIndex(-1);
                    if (empleados.isEmpty()) {
                        MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                                Fr_DatosRetiroDeEfectivo.this,
                                "No hay empleados activos en la sucursal para registrar el retiro");
                        return;
                    }
                    btnGuardar.setEnabled(true);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarError("Se interrumpió la carga de empleados");
                } catch (ExecutionException ex) {
                    mostrarError("No fue posible cargar los empleados: " + causa(ex));
                }
            }
        }.execute();
    }

    private void cargarDetalle() {
        new SwingWorker<RetiroDeEfectivoDetalle, Void>() {
            @Override
            protected RetiroDeEfectivoDetalle doInBackground() throws SQLException {
                return AppContext.retiroDeEfectivoController
                        .getRetiroDeEfectivoById(idRetiro, idSucursal);
            }

            @Override
            protected void done() {
                try {
                    RetiroDeEfectivoDetalle retiro = get();
                    if (retiro == null) {
                        mostrarError("No se encontró el retiro en la sucursal actual");
                        return;
                    }
                    txfFolio.setText(retiro.folio());
                    txfFecha.setText(retiro.fecha() == null
                            ? "" : retiro.fecha().format(FORMATO_FECHA));
                    cmbEmpleado.addItem(new JComboboxDataViewModel(
                            retiro.idEmpleado(), retiro.empleado()));
                    cmbEmpleado.setSelectedIndex(0);
                    txaDescripcion.setText(retiro.descripcion());
                    txfImporte.setText(retiro.importe() == null
                            ? "" : retiro.importe().toPlainString());
                    activarSoloLectura();
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarError("Se interrumpió la consulta del retiro");
                } catch (ExecutionException ex) {
                    mostrarError("No fue posible consultar el retiro: " + causa(ex));
                }
            }
        }.execute();
    }

    private void guardarRetiro() {
        if (opcion != OPCION_CREAR || procesando || idSucursal <= 0
                || !btnGuardar.isEnabled()) {
            return;
        }
        RetiroDeEfectivoRegistro retiro;
        try {
            JComboboxDataViewModel empleado =
                    (JComboboxDataViewModel) cmbEmpleado.getSelectedItem();
            if (empleado == null || empleado.id() <= 0) {
                throw new IllegalArgumentException("Seleccione el empleado que realiza el retiro");
            }
            BigDecimal importe;
            try {
                importe = new BigDecimal(txfImporte.getText().trim());
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("El importe debe ser un número válido", ex);
            }
            retiro = new RetiroDeEfectivoRegistro(
                    idSucursal, empleado.id(), txfFolio.getText().trim(),
                    txaDescripcion.getText().trim(), importe);
            retiro.validar();
        } catch (IllegalArgumentException ex) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
            return;
        }

        // La contraseña corresponde exactamente al empleado seleccionado.
        // Si se cancela o se deja vacía no se ejecuta ningún SP de escritura.
        JComboboxDataViewModel empleadoAutorizante =
                (JComboboxDataViewModel) cmbEmpleado.getSelectedItem();
        char[] contrasenia = DialogoContraseniaRetiro.solicitar(
                this, empleadoAutorizante.nombre(), "registrar este retiro");
        if (contrasenia == null) {
            return;
        }
        if (contrasenia.length == 0) {
            Arrays.fill(contrasenia, '\0');
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "Debe proporcionar la contraseña del empleado");
            return;
        }

        procesando = true;
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        new SwingWorker<SpResponseModel, Void>() {
            @Override
            protected SpResponseModel doInBackground() {
                try {
                    return AppContext.retiroDeEfectivoController
                            .registrarRetiro(retiro, contrasenia);
                } finally {
                    Arrays.fill(contrasenia, '\0');
                }
            }

            @Override
            protected void done() {
                try {
                    SpResponseModel respuesta = get();
                    if (respuesta != null && respuesta.id() == 200) {
                        operacionEjecutada = true;
                        MessageHandler.displayMessage(MessageHandler.INSERT_SUCCESS_MESSAGE,
                                Fr_DatosRetiroDeEfectivo.this, respuesta.message());
                        dispose();
                    } else {
                        mostrarError(respuesta == null
                                ? "El procedimiento no devolvió una respuesta"
                                : respuesta.message());
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    mostrarError("Se interrumpió el registro del retiro");
                } catch (ExecutionException ex) {
                    mostrarError("No fue posible registrar el retiro: " + causa(ex));
                } finally {
                    procesando = false;
                    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    btnCancelar.setEnabled(true);
                    btnGuardar.setEnabled(isDisplayable() && !operacionEjecutada);
                }
            }
        }.execute();
    }

    private void mostrarError(String mensaje) {
        MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, mensaje);
    }

    private static String causa(ExecutionException error) {
        Throwable causa = error.getCause() == null ? error : error.getCause();
        return causa.getMessage() == null ? causa.getClass().getSimpleName()
                : causa.getMessage();
    }

    /**
     * El panel refresca el listado exclusivamente si el SP confirmó el alta.
     */
    public boolean isOperacionEjecutada() {
        return operacionEjecutada;
    }
}
