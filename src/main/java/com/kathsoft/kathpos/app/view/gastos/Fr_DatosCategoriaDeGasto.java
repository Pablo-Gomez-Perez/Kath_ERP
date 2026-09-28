package com.kathsoft.kathpos.app.view.gastos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.kathsoft.kathpos.app.model.gastos.CategoriaDeGasto;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

/**
 * Formulario de alta y edición de categorías de gasto.
 *
 * <p>El constructor acepta la misma convención de {@code Fr_DatosEmpleado}:
 * {@link #OPCION_CREAR} = 0 y {@link #OPCION_EDITAR} = 1. No se expone el
 * estado activo: el procedimiento de alta crea categorías activas y el
 * procedimiento de edición reactiva la categoría editada.</p>
 *
 * <p>Se deja la creación del {@code GroupLayout} declarativa y explícita
 * para que Eclipse WindowBuilder pueda reconstruirla en su diseñador.</p>
 */
public class Fr_DatosCategoriaDeGasto extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int OPCION_CREAR = 0;
    public static final int OPCION_EDITAR = 1;

    private final int opcion;
    private int idCategoria;
    private boolean operacionEjecutada;
    private boolean datosCargados;

    private JPanel contentPane;
    private JPanel panelSuperiorEtiqueta;
    private JPanel panelCentralFormulario;
    private JPanel panelInferiorBotones;
    private JLabel lblTitulo;
    private JLabel lblNombre;
    private JLabel lblDescripcion;
    private JTextField txfNombre;
    private JTextArea textAreaDescripcion;
    private JScrollPane scrollPaneDescripcion;
    private JButton btnCancelar;
    private JButton btnGuardar;

    /**
     * Construye el formulario sin ejecutar JDBC desde el constructor.
     *
     * @param opcion {@link #OPCION_CREAR} o {@link #OPCION_EDITAR}
     * @param idCategoria ID del registro; requerido para edición
     */
    public Fr_DatosCategoriaDeGasto(int opcion, int idCategoria) {
        if (opcion != OPCION_CREAR && opcion != OPCION_EDITAR) {
            throw new IllegalArgumentException("Opción inválida para el formulario de categoría de gasto");
        }

        this.opcion = opcion;
        this.idCategoria = idCategoria;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 460, 350);
        setTitle(opcion == OPCION_CREAR ? "Nueva categoría de gasto" : "Actualizar categoría de gasto");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 215, 0));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelSuperiorEtiqueta = new JPanel();
        panelSuperiorEtiqueta.setBackground(new Color(0, 0, 128));
        contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);

        lblTitulo = new JLabel();
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTitulo.setText(opcion == OPCION_CREAR ? "Nueva categoría de gasto" : "Actualizar categoría de gasto");
        panelSuperiorEtiqueta.add(lblTitulo);

        panelCentralFormulario = new JPanel();
        panelCentralFormulario.setBackground(new Color(255, 215, 0));
        panelCentralFormulario.setBorder(
                new CompoundBorder(new EmptyBorder(5, 0, 5, 0), new LineBorder(Color.BLACK)));
        contentPane.add(panelCentralFormulario, BorderLayout.CENTER);

        lblNombre = new JLabel("Nombre");
        txfNombre = new JTextField();
        txfNombre.setColumns(10);

        lblDescripcion = new JLabel("Descripción");
        scrollPaneDescripcion = new JScrollPane();

        // Estructura explícita conforme al código generado por WindowBuilder.
        GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
        gl_panelCentralFormulario.setHorizontalGroup(
                gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                    .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(txfNombre, GroupLayout.DEFAULT_SIZE, 359, Short.MAX_VALUE))
                            .addComponent(lblDescripcion)
                            .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE))
                        .addContainerGap()));
        gl_panelCentralFormulario.setVerticalGroup(
                gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                    .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txfNombre, GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblDescripcion)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(scrollPaneDescripcion, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addContainerGap()));

        textAreaDescripcion = new JTextArea();
        textAreaDescripcion.setLineWrap(true);
        textAreaDescripcion.setWrapStyleWord(true);
        scrollPaneDescripcion.setViewportView(textAreaDescripcion);
        panelCentralFormulario.setLayout(gl_panelCentralFormulario);

        panelInferiorBotones = new JPanel();
        panelInferiorBotones.setBackground(new Color(30, 144, 255));
        panelInferiorBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
        contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(205, 92, 92));
        btnCancelar.addActionListener(e -> dispose());
        panelInferiorBotones.add(btnCancelar);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.addActionListener(e -> {
            if (this.opcion == OPCION_CREAR) {
                guardarCategoria();
            } else {
                actualizarCategoria();
            }
        });
        panelInferiorBotones.add(btnGuardar);

        if (opcion == OPCION_EDITAR) {
            btnGuardar.setEnabled(false);
        }

        // Evita consultar la BD cuando WindowBuilder construye la vista previa.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if (Fr_DatosCategoriaDeGasto.this.opcion == OPCION_EDITAR && !datosCargados) {
                    datosCargados = true;
                    cargarCategoriaPorId();
                }
            }
        });
    }

    /**
     * Consulta los datos de una categoría para su edición, incluidos los de
     * categorías inactivas. No se permite guardar si falla la consulta.
     */
    private void cargarCategoriaPorId() {
        if (idCategoria <= 0) {
            MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                    this, "Seleccione una categoría válida para actualizar");
            return;
        }

        try {
            CategoriaDeGasto categoria =
                    AppContext.categoriaDeGastoController.getCategoriaGastoById(idCategoria);
            if (categoria == null) {
                MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                        this, "No se encontró la categoría de gasto solicitada");
                return;
            }

            idCategoria = categoria.getIdCategoria();
            txfNombre.setText(categoria.getNombre() == null ? "" : categoria.getNombre());
            textAreaDescripcion.setText(
                    categoria.getDescripcion() == null ? "" : categoria.getDescripcion());
            btnGuardar.setEnabled(true);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                    this, "No fue posible cargar la categoría de gasto: " + ex.getMessage());
        }
    }

    /**
     * Guarda una categoría nueva mediante su procedimiento almacenado.
     */
    private void guardarCategoria() {
        CategoriaDeGasto categoria = construirCategoria();
        if (!validarFormulario(categoria)) {
            return;
        }

        SpResponseModel respuesta = AppContext.categoriaDeGastoController.crearCategoria(categoria);
        mostrarResultado(respuesta, MessageHandler.INSERT_SUCCESS_MESSAGE);
    }

    /**
     * Actualiza y reactiva una categoría existente mediante el procedimiento.
     */
    private void actualizarCategoria() {
        if (idCategoria <= 0 || !btnGuardar.isEnabled()) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE,
                    this, "No existe una categoría válida cargada para actualizar");
            return;
        }

        CategoriaDeGasto categoria = construirCategoria();
        if (!validarFormulario(categoria)) {
            return;
        }

        SpResponseModel respuesta = AppContext.categoriaDeGastoController.actualizarCategoria(categoria);
        mostrarResultado(respuesta, MessageHandler.UPDATE_SUCCESS_MESSAGE);
    }

    private CategoriaDeGasto construirCategoria() {
        CategoriaDeGasto categoria = new CategoriaDeGasto();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(txfNombre.getText().trim());
        categoria.setDescripcion(textAreaDescripcion.getText().trim());
        return categoria;
    }

    private boolean validarFormulario(CategoriaDeGasto categoria) {
        try {
            categoria.validarParaGuardar();
            return true;
        } catch (IllegalArgumentException ex) {
            MessageHandler.displayMessage(MessageHandler.WARN_MESSAGE, this, ex.getMessage());
            return false;
        }
    }

    private void mostrarResultado(SpResponseModel respuesta, short tipoExito) {
        if (respuesta == null) {
            MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE,
                    this, "No se obtuvo respuesta del procedimiento almacenado");
            return;
        }

        if (respuesta.id() == 200) {
            MessageHandler.displayMessage(tipoExito, this, respuesta.message());
            operacionEjecutada = true;
            dispose();
        } else {
            MessageHandler.displayMessage(MessageHandler.ERROR_MESSAGE, this, respuesta.message());
        }
    }

    /**
     * Permite que el futuro panel refresque el listado sólo si se guardó
     * correctamente la categoría y el formulario se cerró.
     *
     * @return {@code true} exclusivamente tras alta o edición exitosa
     */
    public boolean isOperacionEjecutada() {
        return operacionEjecutada;
    }
}
