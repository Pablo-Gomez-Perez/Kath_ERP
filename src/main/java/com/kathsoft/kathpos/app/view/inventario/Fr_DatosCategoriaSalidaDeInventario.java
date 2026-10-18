package com.kathsoft.kathpos.app.view.inventario;

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

import com.kathsoft.kathpos.app.model.inventario.CategoriaSalidaInventario;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.MessageHandler;

/**
 * Formulario reutilizable para crear y modificar categorías de salida de inventario.
 *
 * <p>La construcción de componentes y del GroupLayout se mantiene declarativa
 * para conservar compatibilidad con Eclipse WindowBuilder.</p>
 */
public class Fr_DatosCategoriaSalidaDeInventario extends JFrame {

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

    public Fr_DatosCategoriaSalidaDeInventario(int opcion, int idCategoria) {
        if (opcion != OPCION_CREAR && opcion != OPCION_EDITAR) {
            throw new IllegalArgumentException(
                    "Opción inválida para el formulario de categoría de salida de inventario");
        }

        this.opcion = opcion;
        this.idCategoria = idCategoria;

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 506, 300);
        setTitle(opcion == OPCION_CREAR
                ? "Nueva categoría de salida de inventario"
                : "Actualizar categoría de salida de inventario");

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
        lblTitulo.setText(opcion == OPCION_CREAR
                ? "Nueva categoría de salida de inventario"
                : "Actualizar categoría de salida de inventario");
        panelSuperiorEtiqueta.add(lblTitulo);

        panelCentralFormulario = new JPanel();
        panelCentralFormulario.setBackground(new Color(255, 215, 0));
        panelCentralFormulario.setBorder(
                new CompoundBorder(
                        new EmptyBorder(5, 0, 5, 0),
                        new LineBorder(Color.BLACK)));
        contentPane.add(panelCentralFormulario, BorderLayout.CENTER);

        lblNombre = new JLabel("Nombre");

        txfNombre = new JTextField();
        txfNombre.setColumns(10);

        lblDescripcion = new JLabel("Descripción");

        scrollPaneDescripcion = new JScrollPane();

        GroupLayout gl_panelCentralFormulario = new GroupLayout(panelCentralFormulario);
        gl_panelCentralFormulario.setHorizontalGroup(
                gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                    .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                            .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                                .addComponent(lblNombre)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(
                                        txfNombre,
                                        GroupLayout.DEFAULT_SIZE,
                                        396,
                                        Short.MAX_VALUE))
                            .addComponent(lblDescripcion)
                            .addComponent(
                                    scrollPaneDescripcion,
                                    GroupLayout.DEFAULT_SIZE,
                                    460,
                                    Short.MAX_VALUE))
                        .addContainerGap()));
        gl_panelCentralFormulario.setVerticalGroup(
                gl_panelCentralFormulario.createParallelGroup(Alignment.LEADING)
                    .addGroup(gl_panelCentralFormulario.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(gl_panelCentralFormulario.createParallelGroup(Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(
                                    txfNombre,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(ComponentPlacement.UNRELATED)
                        .addComponent(lblDescripcion)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(
                                scrollPaneDescripcion,
                                GroupLayout.DEFAULT_SIZE,
                                184,
                                Short.MAX_VALUE)
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
        btnGuardar.addActionListener(e -> guardar());
        panelInferiorBotones.add(btnGuardar);

        if (opcion == OPCION_EDITAR) {
            btnGuardar.setEnabled(false);
        }

        /*
         * La consulta no se ejecuta desde el constructor. Esto permite que
         * WindowBuilder instancie y renderice el JFrame sin requerir acceso a BD.
         */
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                if (Fr_DatosCategoriaSalidaDeInventario.this.opcion == OPCION_EDITAR
                        && !datosCargados) {
                    datosCargados = true;
                    cargarCategoriaPorId();
                }
            }
        });
    }

    private void cargarCategoriaPorId() {
        if (idCategoria <= 0) {
            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "Seleccione una categoría de salida válida para actualizar");
            return;
        }

        try {
            CategoriaSalidaInventario categoria =
                    AppContext.categoriaSalidaInventarioController
                            .getCategoriaSalidaInventarioById(idCategoria);

            if (categoria == null) {
                MessageHandler.displayMessage(
                        MessageHandler.ERROR_MESSAGE,
                        this,
                        "No se encontró la categoría de salida solicitada");
                return;
            }

            idCategoria = categoria.getIdCategoria();
            txfNombre.setText(
                    categoria.getNombre() == null ? "" : categoria.getNombre());
            textAreaDescripcion.setText(
                    categoria.getDescripcion() == null
                            ? ""
                            : categoria.getDescripcion());
            btnGuardar.setEnabled(true);
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "No fue posible cargar la categoría de salida: " + ex.getMessage());
        }
    }

    private void guardar() {
        if (opcion == OPCION_EDITAR
                && (idCategoria <= 0 || !btnGuardar.isEnabled())) {
            MessageHandler.displayMessage(
                    MessageHandler.WARN_MESSAGE,
                    this,
                    "No existe una categoría válida cargada para actualizar");
            return;
        }

        CategoriaSalidaInventario categoria = construirCategoria();

        if (!validarFormulario(categoria)) {
            return;
        }

        SpResponseModel respuesta = opcion == OPCION_CREAR
                ? AppContext.categoriaSalidaInventarioController
                        .crearCategoria(categoria)
                : AppContext.categoriaSalidaInventarioController
                        .actualizarCategoria(categoria);

        short tipoExito = opcion == OPCION_CREAR
                ? MessageHandler.INSERT_SUCCESS_MESSAGE
                : MessageHandler.UPDATE_SUCCESS_MESSAGE;

        mostrarResultado(respuesta, tipoExito);
    }

    private CategoriaSalidaInventario construirCategoria() {
        CategoriaSalidaInventario categoria = new CategoriaSalidaInventario();
        categoria.setIdCategoria(idCategoria);
        categoria.setNombre(txfNombre.getText().trim());
        categoria.setDescripcion(textAreaDescripcion.getText().trim());
        return categoria;
    }

    private boolean validarFormulario(CategoriaSalidaInventario categoria) {
        try {
            categoria.validarParaGuardar();
            return true;
        } catch (IllegalArgumentException ex) {
            MessageHandler.displayMessage(
                    MessageHandler.WARN_MESSAGE,
                    this,
                    ex.getMessage());
            return false;
        }
    }

    private void mostrarResultado(
            SpResponseModel respuesta,
            short tipoExito) {

        if (respuesta == null) {
            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "No se obtuvo respuesta del procedimiento almacenado");
            return;
        }

        if (respuesta.id() == 200) {
            MessageHandler.displayMessage(
                    tipoExito,
                    this,
                    respuesta.message());

            operacionEjecutada = true;
            dispose();
            return;
        }

        MessageHandler.displayMessage(
                MessageHandler.ERROR_MESSAGE,
                this,
                respuesta.message());
    }

    public boolean isOperacionEjecutada() {
        return operacionEjecutada;
    }
}
