package com.kathsoft.kathpos.app.view.gastos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.kathsoft.kathpos.app.model.gastos.GastoDetalle;
import com.kathsoft.kathpos.app.model.gastos.GastoRegistro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

/**
 * Alta o edición de un gasto de la sucursal actual. No permite elegir
 * sucursal ni cambiar fecha, estado o identificador desde la interfaz.
 *
 * <p>El GroupLayout es declarativo y explícito para Eclipse WindowBuilder.
 * La carga JDBC se realiza al abrir la ventana y no en el constructor.</p>
 */
public class Fr_DatosGasto extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int OPCION_CREAR = 0;
    public static final int OPCION_EDITAR = 1;
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private final int opcion;
    private final int idGasto;
    private final long idSucursal;
    private boolean datosCargados;
    private boolean operacionEjecutada;

    private JPanel contentPane;
    private JPanel panelSuperiorEtiqueta;
    private JPanel panelCentralFormulario;
    private JPanel panelInferiorBotones;
    private JLabel lblTitulo;
    private JLabel lblFecha;
    private JLabel lblCategoria;
    private JLabel lblEmpleado;
    private JLabel lblFormaPago;
    private JLabel lblDescripcion;
    private JLabel lblImporte;
    private JLabel lblIva;
    private JLabel lblTotal;
    private JTextField txfFecha;
    private JComboBox<JComboboxDataViewModel> cmbCategoria;
    private JComboBox<JComboboxDataViewModel> cmbEmpleado;
    private JComboBox<JComboboxDataViewModel> cmbFormaPago;
    private JTextArea txaDescripcion;
    private JScrollPane scrollPaneDescripcion;
    private JTextField txfImporte;
    private JTextField txfIva;
    private JTextField txfTotal;
    private JButton btnCancelar;
    private JButton btnGuardar;

    /**
     * @param opcion 0 = nuevo gasto; 1 = editar gasto existente
     * @param idGasto cero en altas o folio de gasto existente
     * @param idSucursal sucursal autenticada, proporcionada por el módulo padre
     */
    public Fr_DatosGasto(int opcion, int idGasto, long idSucursal) {
        if (opcion != OPCION_CREAR && opcion != OPCION_EDITAR) {
            throw new IllegalArgumentException("Opción de operación inválida");
        }
        if (idSucursal <= 0 || (opcion == OPCION_EDITAR && idGasto <= 0)
                || (opcion == OPCION_CREAR && idGasto != 0)) {
            throw new IllegalArgumentException("Gasto o sucursal de sesión inválidos");
        }
        this.opcion = opcion;
        this.idGasto = idGasto;
        this.idSucursal = idSucursal;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 610, 520);
        setMinimumSize(new java.awt.Dimension(560, 480));
        setTitle(opcion == OPCION_CREAR ? "Registrar gasto" : "Actualizar gasto");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 215, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelSuperiorEtiqueta = new JPanel();
        panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
        contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

        lblTitulo = new JLabel(opcion == OPCION_CREAR ? "Registrar nuevo gasto" : "Actualizar gasto");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setForeground(Color.WHITE);
        panelSuperiorEtiqueta.add(lblTitulo);

        panelCentralFormulario = new JPanel();
        panelCentralFormulario.setBackground(new Color(255, 215, 0));
        panelCentralFormulario.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.add(panelCentralFormulario, BorderLayout.CENTER);

        lblFecha = new JLabel("Fecha de registro");
        txfFecha = new JTextField("Asignada por el servidor al guardar");
        txfFecha.setEditable(false);
        txfFecha.setColumns(10);

        lblCategoria = new JLabel("Categoría");
        cmbCategoria = new JComboBox<JComboboxDataViewModel>();

        lblEmpleado = new JLabel("Empleado");
        cmbEmpleado = new JComboBox<JComboboxDataViewModel>();

        lblFormaPago = new JLabel("Forma de pago");
        cmbFormaPago = new JComboBox<JComboboxDataViewModel>();

        lblDescripcion = new JLabel("Descripción");
        scrollPaneDescripcion = new JScrollPane();

        lblImporte = new JLabel("Importe sin IVA");
        txfImporte = new JTextField();
        txfImporte.setColumns(10);

        lblIva = new JLabel("IVA");
        txfIva = new JTextField("0.00");
        txfIva.setColumns(10);

        lblTotal = new JLabel("Total");
        txfTotal = new JTextField("0.00");
        txfTotal.setColumns(10);
        txfTotal.setEditable(false);

        // Sintaxis declarativa en ambos ejes: compatible con WindowBuilder.
        GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
        gl_panelCentralFormulario.setHorizontalGroup(
            gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                            .addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfFecha, GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                        .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                            .addComponent(lblCategoria, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmbCategoria, 0, 415, Short.MAX_VALUE))
                        .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                            .addComponent(lblEmpleado, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmbEmpleado, 0, 415, Short.MAX_VALUE))
                        .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                            .addComponent(lblFormaPago, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(cmbFormaPago, 0, 415, Short.MAX_VALUE))
                        .addComponent(lblDescripcion)
                        .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
                        .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                            .addComponent(lblImporte)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfImporte, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblIva)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfIva, GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                            .addPreferredGap(ComponentPlacement.UNRELATED)
                            .addComponent(lblTotal)
                            .addPreferredGap(ComponentPlacement.RELATED)
                            .addComponent(txfTotal, GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)))
                    .addContainerGap()));

        gl_panelCentralFormulario.setVerticalGroup(
            gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFecha)
                        .addComponent(txfFecha, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblCategoria)
                        .addComponent(cmbCategoria, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblEmpleado)
                        .addComponent(cmbEmpleado, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblFormaPago)
                        .addComponent(cmbFormaPago, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addComponent(lblDescripcion)
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblImporte)
                        .addComponent(txfImporte, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblIva)
                        .addComponent(txfIva, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTotal)
                        .addComponent(txfTotal, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap()));

        txaDescripcion = new JTextArea();
        txaDescripcion.setLineWrap(true);
        txaDescripcion.setWrapStyleWord(true);
        scrollPaneDescripcion.setViewportView(txaDescripcion);
        panelCentralFormulario.setLayout(gl_panelCentralFormulario);

        panelInferiorBotones = new JPanel();
        panelInferiorBotones.setBackground(new Color(30, 144, 255));
        panelInferiorBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(205, 92, 92));
        btnCancelar.addActionListener(e -> dispose());
        panelInferiorBotones.add(btnCancelar);

        btnGuardar = new JButton(opcion == OPCION_CREAR ? "Guardar" : "Actualizar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(e -> {
            if (this.opcion == OPCION_CREAR) {
                guardarGasto();
            } else {
                actualizarGasto();
            }
        });
        panelInferiorBotones.add(btnGuardar);

        DocumentListener calcular = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                actualizarTotal();
            }
            @Override
            public void removeUpdate(DocumentEvent event) {
                actualizarTotal();
            }
            @Override
            public void changedUpdate(DocumentEvent event) {
                actualizarTotal();
            }
        };
        txfImporte.getDocument().addDocumentListener(calcular);
        txfIva.getDocument().addDocumentListener(calcular);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                if (!datosCargados) {
                    datosCargados = true;
                    cargarDatosDeFormulario();
                }
            }
        });
    }

    private void cargarDatosDeFormulario() {
        btnGuardar.setEnabled(false);
        new SwingWorker<DatosFormulario, Void>() {
            @Override
            protected DatosFormulario doInBackground() throws Exception {
                Vector<JComboboxDataViewModel> categorias =
                        AppContext.categoriaDeGastoController.listCmbCategoriaDeGasto();
                Vector<JComboboxDataViewModel> empleados =
                        AppContext.gastoController.listCmbEmpleadosGasto(idSucursal);
                Vector<JComboboxDataViewModel> formas =
                        AppContext.gastoController.listarFormasPagoActivas();
                GastoDetalle detalle = opcion == OPCION_EDITAR
                        ? AppContext.gastoController.getGastoByID(idGasto, idSucursal) : null;
                return new DatosFormulario(categorias, empleados, formas, detalle);
            }

            @Override
            protected void done() {
                try {
                    DatosFormulario datos = get();
                    if (opcion == OPCION_EDITAR && datos.detalle() == null) {
                        MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                                Fr_DatosGasto.this, "El gasto no existe en la sucursal actual");
                        return;
                    }
                    for (JComboboxDataViewModel item : datos.categorias()) {
                        cmbCategoria.addItem(item);
                    }
                    for (JComboboxDataViewModel item : datos.empleados()) {
                        cmbEmpleado.addItem(item);
                    }
                    for (JComboboxDataViewModel item : datos.formas()) {
                        cmbFormaPago.addItem(item);
                    }

                    cmbCategoria.setSelectedIndex(-1);
                    cmbEmpleado.setSelectedIndex(-1);
                    cmbFormaPago.setSelectedIndex(-1);

                    if (datos.detalle() != null) {
                        cargarGastoExistente(datos.detalle());
                    }

                    if (datos.categorias().isEmpty() || datos.empleados().isEmpty()
                            || datos.formas().isEmpty()) {
                        MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                                Fr_DatosGasto.this,
                                "Debe existir al menos una categoría, un empleado y una forma de pago activos");
                        return;
                    }
                    btnGuardar.setEnabled(true);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                            Fr_DatosGasto.this, "Se interrumpió la carga de opciones");
                } catch (ExecutionException ex) {
                    Throwable causa = ex.getCause() == null ? ex : ex.getCause();
                    MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                            Fr_DatosGasto.this, "No fue posible cargar el formulario: " + causa.getMessage());
                }
            }
        }.execute();
    }

    private void cargarGastoExistente(GastoDetalle gasto) {
        if (!gasto.activo()) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "El gasto está inhabilitado y no puede actualizarse");
            return;
        }
        txfFecha.setText(gasto.fechaOperacion() == null
                ? "Sin fecha" : gasto.fechaOperacion().format(FORMATO_FECHA));
        txaDescripcion.setText(gasto.descripcion() == null ? "" : gasto.descripcion());
        txfImporte.setText(gasto.importe() == null ? "" : gasto.importe().toPlainString());
        txfIva.setText(gasto.iva() == null ? "0.00" : gasto.iva().toPlainString());

        boolean categoriaValida = seleccionarId(cmbCategoria, gasto.idCategoria());
        boolean empleadoValido = seleccionarId(cmbEmpleado, gasto.idEmpleado());
        boolean formaValida = seleccionarId(cmbFormaPago, gasto.idFormaPago());

        if (!categoriaValida || !empleadoValido || !formaValida) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this,
                    "Alguna opción original ya está inactiva o falta la forma de pago. "
                    + "Seleccione una categoría, empleado y forma de pago activos antes de actualizar");
        }
    }

    private static boolean seleccionarId(JComboBox<JComboboxDataViewModel> combo, Integer id) {
        if (id != null) {
            for (int i = 0; i < combo.getItemCount(); i++) {
                if (combo.getItemAt(i).id() == id) {
                    combo.setSelectedIndex(i);
                    return true;
                }
            }
        }
        combo.setSelectedIndex(-1);
        return false;
    }

    private void actualizarTotal() {
        try {
            BigDecimal base = txfImporte.getText().isBlank()
                    ? BigDecimal.ZERO : new BigDecimal(txfImporte.getText().trim());
            BigDecimal iva = txfIva.getText().isBlank()
                    ? BigDecimal.ZERO : new BigDecimal(txfIva.getText().trim());
            txfTotal.setText(base.add(iva).setScale(2, RoundingMode.HALF_UP).toPlainString());
        } catch (NumberFormatException ex) {
            txfTotal.setText("Importes inválidos");
        }
    }

    private void guardarGasto() {
        procesarGuardado(false);
    }

    private void actualizarGasto() {
        procesarGuardado(true);
    }

    private void procesarGuardado(boolean actualizacion) {
        GastoRegistro gasto;
        try {
            gasto = leerFormulario();
            gasto.validar(actualizacion);
        } catch (IllegalArgumentException ex) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
            return;
        }

        btnGuardar.setEnabled(false);
        new SwingWorker<SpResponseModel, Void>() {
            @Override
            protected SpResponseModel doInBackground() {
                return actualizacion
                        ? AppContext.gastoController.actualizarGasto(gasto)
                        : AppContext.gastoController.crearGasto(gasto);
            }

            @Override
            protected void done() {
                try {
                    SpResponseModel respuesta = get();
                    if (respuesta.id() == 200) {
                        operacionEjecutada = true;
                        MessageHandler.displayMessage(
                                actualizacion
                                    ? MessageHandler.UPDATE_SUCCESS_MESSAGE
                                    : MessageHandler.INSERT_SUCCESS_MESSAGE,
                                Fr_DatosGasto.this, respuesta.message());
                        dispose();
                    } else {
                        MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                                Fr_DatosGasto.this, respuesta.message());
                    }
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                            Fr_DatosGasto.this, "Se interrumpió el registro del gasto");
                } catch (ExecutionException ex) {
                    MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                            Fr_DatosGasto.this, "No fue posible procesar el gasto: " + ex.getMessage());
                } finally {
                    btnGuardar.setEnabled(isDisplayable() && !operacionEjecutada);
                }
            }
        }.execute();
    }

    private GastoRegistro leerFormulario() {
        JComboboxDataViewModel categoria =
                (JComboboxDataViewModel) cmbCategoria.getSelectedItem();
        JComboboxDataViewModel empleado =
                (JComboboxDataViewModel) cmbEmpleado.getSelectedItem();
        JComboboxDataViewModel forma =
                (JComboboxDataViewModel) cmbFormaPago.getSelectedItem();

        if (categoria == null || empleado == null || forma == null) {
            throw new IllegalArgumentException(
                    "Debe seleccionar una categoría, un empleado y una forma de pago");
        }

        BigDecimal importe;
        BigDecimal iva;
        try {
            importe = new BigDecimal(txfImporte.getText().trim());
            iva = new BigDecimal(txfIva.getText().trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El importe y el IVA deben ser números válidos");
        }

        return new GastoRegistro(
                opcion == OPCION_CREAR ? 0 : idGasto,
                idSucursal, categoria.id(), empleado.id(), forma.id(),
                txaDescripcion.getText().trim(), importe, iva);
    }

    public boolean isOperacionEjecutada() {
        return operacionEjecutada;
    }

    private record DatosFormulario(
            List<JComboboxDataViewModel> categorias,
            List<JComboboxDataViewModel> empleados,
            List<JComboboxDataViewModel> formas,
            GastoDetalle detalle) {
    }
}
