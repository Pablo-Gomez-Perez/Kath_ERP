package com.kathsoft.kathpos.app.view.configuracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.kathsoft.kathpos.app.model.configuracion.ParametrosDeConexion;
import com.kathsoft.kathpos.tools.ConfiguracionConexionDBService;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Formulario de configuración inicial y edición de la conexión MySQL/MariaDB.
 *
 * <p>Utiliza un archivo cifrado en la carpeta de configuración del usuario,
 * con ubicación fija para que pueda encontrarse al inicio sin depender de
 * la carpeta actual ni de un selector de archivos.</p>
 */
public class Fr_ConfiguracionConexionDB extends JFrame {

    private static final long serialVersionUID = 1L;

    private final ConfiguracionConexionDBService almacenamiento = new ConfiguracionConexionDBService();
    private final Runnable alGuardar;
    private JPanel contentPane;
    private JPanel panelSuperiorEtiqueta;
    private JPanel panelCentral;
    private JPanel panelDatosConexion;
    private JPanel panelConsola;
    private JPanel panelInferiorBotones;
    private JLabel lblTitulo;
    private JLabel lblHost;
    private JLabel lblPuerto;
    private JLabel lblNombreBD;
    private JLabel lblUsuario;
    private JLabel lblContrasenia;
    private JLabel lblParametros;
    private JLabel lblConsola;
    private JTextField txfHost;
    private JTextField txfPuerto;
    private JTextField txfNombreBD;
    private JTextField txfUsuario;
    private JPasswordField pswfContrasenia;
    private JTextField txfParametros;
    private JTextArea txaConsola;
    private JScrollPane scrollPaneConsola;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JButton btnProbarConexion;

    /**
     * Crea el formulario para invocarse posteriormente desde Configuración.
     */
    public Fr_ConfiguracionConexionDB() {
        this(null);
    }

    /**
     * Crea el formulario con una acción opcional tras guardar correctamente.
     *
     * @param alGuardar acción que habilita el login tras la instalación;
     *                  puede ser {@code null}
     */
    public Fr_ConfiguracionConexionDB(Runnable alGuardar) {
        this.alGuardar = alGuardar;
        setTitle("Configuración de conexión a la base de datos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(Toolkit.getDefaultToolkit().getImage(
                getClass().getResource("/com/kathsoft/kathpos/app/assets/login_ico.png")));
        setSize(600, 475);
        setMinimumSize(new java.awt.Dimension(600, 540));
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panelSuperiorEtiqueta = new JPanel();
        panelSuperiorEtiqueta.setBackground(new Color(14, 14, 216));
        contentPane.add(panelSuperiorEtiqueta, BorderLayout.NORTH);
        panelSuperiorEtiqueta.setLayout(new FlowLayout(FlowLayout.CENTER));

        lblTitulo = new JLabel("Conexión con MySQL / MariaDB");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
        panelSuperiorEtiqueta.add(lblTitulo);

        panelCentral = new JPanel();
        panelCentral.setBackground(new Color(255, 204, 0));
        panelCentral.setBorder(new EmptyBorder(12, 14, 12, 14));
        contentPane.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(new BorderLayout(8, 10));

        panelDatosConexion = new JPanel();
        panelDatosConexion.setBackground(new Color(255, 204, 0));
        panelDatosConexion.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        panelCentral.add(panelDatosConexion, BorderLayout.NORTH);

        lblHost = new JLabel("Servidor / IP");
        txfHost = new JTextField("localhost");
        txfHost.setColumns(24);
        txfHost.setBackground(new Color(204, 255, 255));

        lblPuerto = new JLabel("Puerto");
        txfPuerto = new JTextField("3306");
        txfPuerto.setColumns(24);
        txfPuerto.setBackground(new Color(204, 255, 255));

        lblNombreBD = new JLabel("Base de datos");
        txfNombreBD = new JTextField("kath_erp");
        txfNombreBD.setColumns(24);
        txfNombreBD.setBackground(new Color(204, 255, 255));

        lblUsuario = new JLabel("Usuario");
        txfUsuario = new JTextField("root");
        txfUsuario.setColumns(24);
        txfUsuario.setBackground(new Color(204, 255, 255));

        lblContrasenia = new JLabel("Contraseña");
        pswfContrasenia = new JPasswordField();
        pswfContrasenia.setColumns(24);
        pswfContrasenia.setBackground(new Color(204, 255, 255));

        lblParametros = new JLabel("Parámetros JDBC");
        txfParametros = new JTextField("serverTimezone=UTC");
        txfParametros.setColumns(24);
        txfParametros.setBackground(new Color(204, 255, 255));

        // Grupos explícitos generados en el patrón del diseñador Eclipse.
        GroupLayout gl_panelDatosConexion = new GroupLayout(panelDatosConexion);
        gl_panelDatosConexion.setHorizontalGroup(
            gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panelDatosConexion.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblHost)
                        .addComponent(lblPuerto)
                        .addComponent(lblNombreBD)
                        .addComponent(lblUsuario)
                        .addComponent(lblContrasenia)
                        .addComponent(lblParametros))
                    .addPreferredGap(ComponentPlacement.UNRELATED)
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(txfHost, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addComponent(txfPuerto, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addComponent(txfNombreBD, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addComponent(txfUsuario, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addComponent(pswfContrasenia, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                        .addComponent(txfParametros, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                    .addContainerGap())
        );
        gl_panelDatosConexion.setVerticalGroup(
            gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(gl_panelDatosConexion.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblHost)
                        .addComponent(txfHost, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblPuerto)
                        .addComponent(txfPuerto, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblNombreBD)
                        .addComponent(txfNombreBD, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblUsuario)
                        .addComponent(txfUsuario, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblContrasenia)
                        .addComponent(pswfContrasenia, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(gl_panelDatosConexion.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblParametros)
                        .addComponent(txfParametros, GroupLayout.PREFERRED_SIZE,
                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );
        panelDatosConexion.setLayout(gl_panelDatosConexion);

        panelConsola = new JPanel();
        panelConsola.setOpaque(false);
        panelCentral.add(panelConsola, BorderLayout.CENTER);
        panelConsola.setLayout(new BorderLayout(0, 5));

        lblConsola = new JLabel("Registro de conexión (no muestra contraseñas)");
        panelConsola.add(lblConsola, BorderLayout.NORTH);

        scrollPaneConsola = new JScrollPane();
        panelConsola.add(scrollPaneConsola, BorderLayout.CENTER);

        txaConsola = new JTextArea();
        txaConsola.setEditable(false);
        txaConsola.setLineWrap(true);
        txaConsola.setWrapStyleWord(true);
        txaConsola.setBackground(new Color(248, 248, 248));
        scrollPaneConsola.setViewportView(txaConsola);

        panelInferiorBotones = new JPanel();
        panelInferiorBotones.setBackground(new Color(51, 153, 255));
        contentPane.add(panelInferiorBotones, BorderLayout.SOUTH);
        panelInferiorBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(205, 92, 92));
        panelInferiorBotones.add(btnCancelar);

        btnProbarConexion = new JButton("Probar conexión");
        btnProbarConexion.setBackground(new Color(204, 255, 255));
        panelInferiorBotones.add(btnProbarConexion);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 204, 51));
        panelInferiorBotones.add(btnGuardar);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardarConfiguracion());
        btnProbarConexion.addActionListener(e -> probarConexion());

        // El diseñador instancia el JFrame para generar la previsualización.
        // La lectura de credenciales se pospone al evento real de apertura.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent event) {
                cargarValoresGuardados();
                registrar("Archivo de conexión: " + almacenamiento.rutaArchivo());
                registrar("La contraseña se almacena cifrada, pero no sustituye un gestor de secretos del SO.");
            }
        });
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void cargarValoresGuardados() {
        try {
            ParametrosDeConexion almacenados = Conexion.obtenerConfiguracionGuardada();
            if (almacenados == null) {
                return;
            }
            txfHost.setText(almacenados.host());
            txfPuerto.setText(Integer.toString(almacenados.port()));
            txfNombreBD.setText(almacenados.name());
            txfUsuario.setText(almacenados.user());
            pswfContrasenia.setText(almacenados.password());
            txfParametros.setText(almacenados.params());
            registrar("Se cargó la configuración existente para su edición.");
        } catch (IOException ex) {
            registrar("No se pudo leer la configuración previa. Puede introducir nuevos parámetros y guardar.");
        }
    }

    private ParametrosDeConexion leerFormulario() {
        char[] clave = pswfContrasenia.getPassword();
        try {
            ParametrosDeConexion datos = new ParametrosDeConexion(
                    txfHost.getText().trim(),
                    Integer.parseInt(txfPuerto.getText().trim()),
                    txfNombreBD.getText().trim(),
                    txfUsuario.getText().trim(),
                    new String(clave),
                    txfParametros.getText().trim());
            datos.validar();
            return datos;
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("El puerto debe ser un número válido");
        } finally {
            Arrays.fill(clave, (char) 0);
        }
    }

    private void probarConexion() {
        ParametrosDeConexion datos;
        try {
            datos = leerFormulario();
        } catch (IllegalArgumentException ex) {
            registrar("No se puede probar la conexión: " + ex.getMessage());
            return;
        }
        cambiarBotones(false);
        registrar("Probando conexión con " + datos.host() + ":" + datos.port() + " ...");
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try (Connection ignorada = Conexion.probarConexion(datos)) {
                    return null;
                }
            }

            @Override
            protected void done() {
                try {
                    get();
                    registrar("Conexión establecida correctamente.");
                } catch (Exception ex) {
                    registrar("Error de conexión: " + describirFallo(ex, datos.password()));
                } finally {
                    cambiarBotones(true);
                }
            }
        }.execute();
    }

    private void guardarConfiguracion() {
        ParametrosDeConexion datos;
        try {
            datos = leerFormulario();
        } catch (IllegalArgumentException ex) {
            registrar("No fue posible guardar: " + ex.getMessage());
            return;
        }
        cambiarBotones(false);
        registrar("Cifrando y guardando archivo de conexión...");
        new SwingWorker<Path, Void>() {
            @Override
            protected Path doInBackground() throws Exception {
                almacenamiento.guardar(datos);
                return almacenamiento.rutaArchivo();
            }

            @Override
            protected void done() {
                try {
                    Path ruta = get();
                    Conexion.activarConfiguracion(datos);
                    registrar("Archivo guardado correctamente: " + ruta);
                    registrar("La configuración se utilizará en las siguientes conexiones.");
                    if (alGuardar != null) {
                        alGuardar.run();
                    }
                    //dispose();
                } catch (Exception ex) {
                    registrar("No fue posible guardar el archivo: " + describirFallo(ex, datos.password()));
                    JOptionPane.showMessageDialog(Fr_ConfiguracionConexionDB.this,
                            "No fue posible guardar la configuración. Revise el registro de conexión.",
                            "Error al guardar", JOptionPane.ERROR_MESSAGE);
                } finally {
                    cambiarBotones(true);
                }
            }
        }.execute();
    }

    private void cambiarBotones(boolean habilitados) {
        btnGuardar.setEnabled(habilitados);
        btnProbarConexion.setEnabled(habilitados);
        btnCancelar.setEnabled(habilitados);
    }

    private void registrar(String mensaje) {
        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> registrar(mensaje));
            return;
        }
        txaConsola.append(mensaje + System.lineSeparator());
        txaConsola.setCaretPosition(txaConsola.getDocument().getLength());
    }

    private static String describirFallo(Exception error, String clave) {
        Throwable causa = error;
        while (causa.getCause() != null) {
            causa = causa.getCause();
        }
        if (causa instanceof SQLException sql) {
            // SQLState/código informan del fallo sin publicar URLs ni secretos.
            return "SQLState=" + sql.getSQLState() + ", código=" + sql.getErrorCode()
                    + ". Verifique servidor, credenciales, permisos y conectividad.";
        }
        String mensaje = causa.getMessage() == null ? causa.getClass().getSimpleName()
                : causa.getMessage();
        return clave == null || clave.isEmpty() ? mensaje
                : mensaje.replace(clave, "<oculta>");
    }
}
