package com.kathsoft.kathpos.app.view.inventario;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.inventario.CategoriaSalidaInventario;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;
import com.kathsoft.kathpos.tools.ConstantsConllections;
import com.kathsoft.kathpos.tools.DataTools;
import com.kathsoft.kathpos.tools.MessageHandler;

/**
 * Panel CRUD para las categorías de salida de inventario.
 *
 * <p>La estructura visual sigue el patrón de los demás módulos del proyecto y
 * mantiene el GroupLayout declarado directamente para conservar compatibilidad
 * con Eclipse WindowBuilder.</p>
 */
public class PanelCategoriaSalidaDeINventario extends JPanel {

    private static final long serialVersionUID = 1L;

    private JTextField txfNombre;
    private JButton btnCrear;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnBuscar;
    private JButton btnExportarExcel;
    private JTable tableCategoriasSalidaDeInventarrio;
    private DefaultTableModel categoriaSalidaInventarioModel;
    private boolean listadoInicialCargado;

    public PanelCategoriaSalidaDeINventario() {
        setBackground(new Color(255, 215, 0));
        setBorder(null);

        JPanel panelSuperiorTitulo = new JPanel();
        panelSuperiorTitulo.setBackground(new Color(0, 0, 102));

        JLabel lblTitulo = new JLabel("Categorías de salidas de inventario");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
        panelSuperiorTitulo.add(lblTitulo);

        JPanel panelInferiorBusquedas = new JPanel();
        panelInferiorBusquedas.setBackground(new Color(0, 153, 255));

        JLabel lblNombre = new JLabel("Nombre");

        txfNombre = new JTextField();
        txfNombre.setColumns(10);
        lblNombre.setLabelFor(txfNombre);
        txfNombre.addActionListener(e -> buscarCategorias());

        btnBuscar = new JButton("Buscar");
        btnBuscar.setIcon(new ImageIcon(
                PanelCategoriaSalidaDeINventario.class.getResource(
                        "/com/kathsoft/kathpos/app/assets/buscar_ico.png")));
        btnBuscar.setBackground(new Color(184, 134, 11));
        btnBuscar.addActionListener(e -> buscarCategorias());

        GroupLayout gl_panelInferiorBusquedas =
                new GroupLayout(panelInferiorBusquedas);

        gl_panelInferiorBusquedas.setHorizontalGroup(
                gl_panelInferiorBusquedas.createParallelGroup(Alignment.LEADING)
                    .addGroup(gl_panelInferiorBusquedas.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(
                                lblNombre,
                                GroupLayout.PREFERRED_SIZE,
                                49,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(
                                txfNombre,
                                GroupLayout.DEFAULT_SIZE,
                                309,
                                Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addContainerGap()));

        gl_panelInferiorBusquedas.setVerticalGroup(
                gl_panelInferiorBusquedas.createParallelGroup(Alignment.TRAILING)
                    .addGroup(gl_panelInferiorBusquedas.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                gl_panelInferiorBusquedas
                                    .createParallelGroup(Alignment.BASELINE)
                                    .addComponent(lblNombre)
                                    .addComponent(
                                            txfNombre,
                                            GroupLayout.PREFERRED_SIZE,
                                            GroupLayout.DEFAULT_SIZE,
                                            GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar))
                        .addContainerGap()));

        panelInferiorBusquedas.setLayout(gl_panelInferiorBusquedas);

        JPanel panelSuperiorBotones = new JPanel();
        FlowLayout flowLayout = (FlowLayout) panelSuperiorBotones.getLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panelSuperiorBotones.setBackground(new Color(255, 204, 0));

        btnCrear = new JButton("Agregar");
        btnCrear.setIcon(new ImageIcon(
                PanelCategoriaSalidaDeINventario.class.getResource(
                        "/com/kathsoft/kathpos/app/assets/agregar_ico.png")));
        btnCrear.setBackground(new Color(144, 238, 144));
        btnCrear.addActionListener(e ->
                abrirFormularioCategoria(
                        Fr_DatosCategoriaSalidaDeInventario.OPCION_CREAR,
                        0));
        panelSuperiorBotones.add(btnCrear);

        btnModificar = new JButton("Modificar");
        btnModificar.setIcon(new ImageIcon(
                PanelCategoriaSalidaDeINventario.class.getResource(
                        "/com/kathsoft/kathpos/app/assets/actualizar_ico.png")));
        btnModificar.setBackground(new Color(144, 238, 144));
        btnModificar.addActionListener(e -> modificarCategoria());
        panelSuperiorBotones.add(btnModificar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setIcon(new ImageIcon(
                PanelCategoriaSalidaDeINventario.class.getResource(
                        "/com/kathsoft/kathpos/app/assets/nwCancel.png")));
        btnEliminar.setBackground(new Color(255, 51, 0));
        btnEliminar.addActionListener(e -> eliminarCategoria());
        panelSuperiorBotones.add(btnEliminar);

        btnExportarExcel = new JButton("Exportar Excel");
        btnExportarExcel.setIcon(new ImageIcon(
                PanelCategoriaSalidaDeINventario.class.getResource(
                        "/com/kathsoft/kathpos/app/assets/excelLogo.jpg")));
        btnExportarExcel.setBackground(new Color(102, 205, 170));
        btnExportarExcel.addActionListener(e -> exportarListado());
        panelSuperiorBotones.add(btnExportarExcel);

        JScrollPane scrollPaneCategorias = new JScrollPane();

        GroupLayout groupLayout = new GroupLayout(this);

        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                    .addComponent(
                            panelSuperiorTitulo,
                            GroupLayout.DEFAULT_SIZE,
                            749,
                            Short.MAX_VALUE)
                    .addComponent(
                            panelInferiorBusquedas,
                            GroupLayout.DEFAULT_SIZE,
                            749,
                            Short.MAX_VALUE)
                    .addComponent(
                            panelSuperiorBotones,
                            GroupLayout.DEFAULT_SIZE,
                            749,
                            Short.MAX_VALUE)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(
                                scrollPaneCategorias,
                                GroupLayout.DEFAULT_SIZE,
                                725,
                                Short.MAX_VALUE)
                        .addContainerGap()));

        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(groupLayout.createSequentialGroup()
                        .addComponent(
                                panelSuperiorTitulo,
                                GroupLayout.PREFERRED_SIZE,
                                44,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(
                                panelSuperiorBotones,
                                GroupLayout.PREFERRED_SIZE,
                                40,
                                GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(
                                scrollPaneCategorias,
                                GroupLayout.DEFAULT_SIZE,
                                325,
                                Short.MAX_VALUE)
                        .addPreferredGap(ComponentPlacement.RELATED)
                        .addComponent(
                                panelInferiorBusquedas,
                                GroupLayout.PREFERRED_SIZE,
                                54,
                                GroupLayout.PREFERRED_SIZE)));

        tableCategoriasSalidaDeInventarrio = new JTable();
        categoriaSalidaInventarioModel = crearModeloTabla();
        tableCategoriasSalidaDeInventarrio.setModel(
                categoriaSalidaInventarioModel);
        tableCategoriasSalidaDeInventarrio.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF);

        DataTools.removerEditorDeTabla(
                tableCategoriasSalidaDeInventarrio,
                categoriaSalidaInventarioModel);

        DataTools.definirTamanioDeColumnas(
                ConstantsConllections.tablaCategoriaSalidaInventarioColumnsWidth,
                tableCategoriasSalidaDeInventarrio);

        scrollPaneCategorias.setViewportView(
                tableCategoriasSalidaDeInventarrio);

        setLayout(groupLayout);

        /*
         * WindowBuilder puede construir el panel sin conexión a la base.
         * El primer listado sólo se ejecuta cuando el componente se muestra.
         */
        addHierarchyListener(event -> {
            if ((event.getChangeFlags() & HierarchyEvent.SHOWING_CHANGED) != 0
                    && isShowing()
                    && !listadoInicialCargado) {

                listadoInicialCargado = true;
                buscarCategorias();
            }
        });
    }

    static DefaultTableModel crearModeloTabla() {
        return new DefaultTableModel(
                new Object[] {
                    "id_categoria",
                    "nombre",
                    "descripcion",
                    "activo"
                },
                0) {

            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private void buscarCategorias() {
        llenarTablaCategorias(txfNombre.getText().trim());
    }

    /**
     * Sustituye el contenido del modelo sólo después de recibir correctamente
     * toda la consulta. Ante un error conserva los datos anteriores.
     */
    public void llenarTablaCategorias(String nombre) {
        try {
            List<CategoriaSalidaInventario> categorias =
                    AppContext.categoriaSalidaInventarioController
                            .listarCategorias(nombre);

            categoriaSalidaInventarioModel.setRowCount(0);

            for (CategoriaSalidaInventario categoria : categorias) {
                categoriaSalidaInventarioModel.addRow(new Object[] {
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getDescripcion(),
                    categoria.isActivo() ? "Activo" : "Inactivo"
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.err);
            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "No fue posible consultar las categorías de salida: "
                            + ex.getMessage());
        }
    }

    private int idCategoriaSeleccionada(String operacion) {
        int filaVista = tableCategoriasSalidaDeInventarrio.getSelectedRow();

        if (filaVista < 0) {
            MessageHandler.displayMessage(
                    MessageHandler.WARN_MESSAGE,
                    this,
                    "Seleccione una categoría de salida antes de " + operacion);
            return -1;
        }

        int filaModelo =
                tableCategoriasSalidaDeInventarrio.convertRowIndexToModel(
                        filaVista);

        Object valor =
                categoriaSalidaInventarioModel.getValueAt(filaModelo, 0);

        if (!(valor instanceof Number numero)
                || numero.intValue() <= 0) {

            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "La categoría seleccionada no tiene un identificador válido");
            return -1;
        }

        return numero.intValue();
    }

    private void abrirFormularioCategoria(
            int opcion,
            int idCategoria) {

        Fr_DatosCategoriaSalidaDeInventario formulario =
                new Fr_DatosCategoriaSalidaDeInventario(
                        opcion,
                        idCategoria);

        formulario.setLocationRelativeTo(this);
        formulario.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        formulario.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent event) {
                if (formulario.isOperacionEjecutada()) {
                    buscarCategorias();
                }
            }
        });

        formulario.setVisible(true);
    }

    private void modificarCategoria() {
        int id = idCategoriaSeleccionada("modificar");

        if (id > 0) {
            abrirFormularioCategoria(
                    Fr_DatosCategoriaSalidaDeInventario.OPCION_EDITAR,
                    id);
        }
    }

    private void eliminarCategoria() {
        int id = idCategoriaSeleccionada("eliminar");

        if (id <= 0) {
            return;
        }

        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Desea inhabilitar la categoría de salida seleccionada?",
                "Inhabilitar categoría",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (opcion != JOptionPane.YES_OPTION) {
            return;
        }

        SpResponseModel respuesta =
                AppContext.categoriaSalidaInventarioController
                        .eliminarCategoria(id);

        if (respuesta == null) {
            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "No se recibió respuesta al inhabilitar la categoría");
            return;
        }

        if (respuesta.id() == 200) {
            MessageHandler.displayMessage(
                    MessageHandler.DELETE_SUCCESS_MESSAGE,
                    this,
                    respuesta.message());

            buscarCategorias();
            return;
        }

        MessageHandler.displayMessage(
                MessageHandler.ERROR_MESSAGE,
                this,
                respuesta.message());
    }

    private void exportarListado() {
        try {
            DataTools.exportarJTableCsv(
                    tableCategoriasSalidaDeInventarrio,
                    this);
        } catch (IllegalArgumentException ex) {
            MessageHandler.displayMessage(
                    MessageHandler.WARN_MESSAGE,
                    this,
                    ex.getMessage());
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
            MessageHandler.displayMessage(
                    MessageHandler.ERROR_MESSAGE,
                    this,
                    "No fue posible exportar las categorías de salida: "
                            + ex.getMessage());
        }
    }
}
