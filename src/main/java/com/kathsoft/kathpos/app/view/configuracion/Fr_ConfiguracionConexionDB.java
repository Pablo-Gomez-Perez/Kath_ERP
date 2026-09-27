package com.kathsoft.kathpos.app.view.configuracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.LayoutStyle;
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
    private final JTextField txfHost = new JTextField("localhost");
    private final JTextField txfPuerto = new JTextField("3306");
    private final JTextField txfNombreBD = new JTextField("kath_erp");
    private final JTextField txfUsuario = new JTextField("root");
    private final JPasswordField pswfContrasenia = new JPasswordField();
    private final JTextField txfParametros = new JTextField("serverTimezone=UTC");
    private final JTextArea txaConsola = new JTextArea();
    private final JButton btnCancelar = new JButton("Cancelar");
    private final JButton btnGuardar = new JButton("Guardar");
    private final JButton btnProbarConexion = new JButton("Probar conexión");

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
        setSize(670, 605);
        setMinimumSize(new java.awt.Dimension(600, 540));
        setLocationRelativeTo(null);

        JPanel contenido = new JPanel(new BorderLayout());
        setContentPane(contenido);

        JPanel encabezado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        encabezado.setBackground(new Color(14, 14, 216));
        JLabel titulo = new JLabel("Conexión con MySQL / MariaDB");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Dialog", Font.BOLD, 16));
        encabezado.add(titulo);
        contenido.add(encabezado, BorderLayout.NORTH);

        JPanel centro = new JPanel(new BorderLayout(8, 10));
        centro.setBackground(new Color(255, 204, 0));
        centro.setBorder(new EmptyBorder(12, 14, 12, 14));
        contenido.add(centro, BorderLayout.CENTER);

        JPanel datos = new JPanel();
        datos.setBackground(new Color(255, 204, 0));
        datos.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        JLabel host = new JLabel("Servidor / IP");
        JLabel puerto = new JLabel("Puerto");
        JLabel nombre = new JLabel("Base de datos");
        JLabel usuario = new JLabel("Usuario");
        JLabel contrasenia = new JLabel("Contraseña");
        JLabel parametros = new JLabel("Parámetros JDBC");
        JLabel[] etiquetas = {host, puerto, nombre, usuario, contrasenia, parametros};
        javax.swing.JComponent[] entradas = {txfHost, txfPuerto, txfNombreBD,
                txfUsuario, pswfContrasenia, txfParametros};
        for (javax.swing.JComponent entrada : entradas) {
            if (entrada instanceof JTextField campo) {
                campo.setColumns(24);
                campo.setBackground(new Color(204, 255, 255));
            }
        }

        GroupLayout layout = new GroupLayout(datos);
        datos.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        GroupLayout.ParallelGroup columnasTexto = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.ParallelGroup columnasCampo = layout.createParallelGroup(GroupLayout.Alignment.LEADING);
        GroupLayout.SequentialGroup filas = layout.createSequentialGroup();
        for (int indice = 0; indice < etiquetas.length; indice++) {
            columnasTexto.addComponent(etiquetas[indice]);
            columnasCampo.addComponent(entradas[indice], GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE);
            filas.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(etiquetas[indice])
                    .addComponent(entradas[indice], GroupLayout.PREFERRED_SIZE,
                            GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE));
        }
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(columnasTexto)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(columnasCampo));
        layout.setVerticalGroup(filas);
        centro.add(datos, BorderLayout.NORTH);

        JPanel consola = new JPanel(new BorderLayout(0, 5));
        consola.setOpaque(false);
        JLabel tituloConsola = new JLabel("Registro de conexión (no muestra contraseñas)");
        consola.add(tituloConsola, BorderLayout.NORTH);
        txaConsola.setEditable(false);
        txaConsola.setLineWrap(true);
        txaConsola.setWrapStyleWord(true);
        txaConsola.setBackground(new Color(248, 248, 248));
        JScrollPane desplazamiento = new JScrollPane(txaConsola);
        consola.add(desplazamiento, BorderLayout.CENTER);
        centro.add(consola, BorderLayout.CENTER);

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.setBackground(new Color(51, 153, 255));
        btnCancelar.setBackground(new Color(205, 92, 92));
        btnGuardar.setBackground(new Color(0, 204, 51));
        btnProbarConexion.setBackground(new Color(204, 255, 255));
        acciones.add(btnCancelar);
        acciones.add(btnProbarConexion);
        acciones.add(btnGuardar);
        contenido.add(acciones, BorderLayout.SOUTH);

        btnCancelar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardarConfiguracion());
        btnProbarConexion.addActionListener(e -> probarConexion());

        cargarValoresGuardados();
        registrar("Archivo de conexión: " + almacenamiento.rutaArchivo());
        registrar("La contraseña se almacena cifrada, pero no sustituye un gestor de secretos del SO.");
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
            Arrays.fill(clave, '\\0');
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
                    dispose();
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
