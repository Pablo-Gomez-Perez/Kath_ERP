package com.kathsoft.kathpos.app.view.configuracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.border.EmptyBorder;

import com.kathsoft.kathpos.app.model.configuracion.EstadoInicializacion;
import com.kathsoft.kathpos.app.model.configuracion.SolicitudInicializacion;
import com.kathsoft.kathpos.app.model.configuracion.SucursalInicial;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.AppContext;

/**
 * Configuración inicial sobre una BD sin empleados. El panel de sucursal
 * aparece exclusivamente cuando no existe ninguna, evitando duplicaciones.
 * GroupLayouts con campos explícitos para Eclipse WindowBuilder.
 */
public class Fr_InicializacionSistema extends JFrame {

    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter FECHA = DateTimeFormatter
            .ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    private final EstadoInicializacion estado;
    private final List<SucursalInicial> sucursales;
    private boolean completado;
    private boolean guardando;

    private JPanel contentPane;
    private JPanel panelTitulo;
    private JPanel panelCentral;
    private JPanel panelSelectorSucursal;
    private JPanel panelSucursal;
    private JPanel panelAdministrador;
    private JPanel panelBotones;
    private JLabel lblTitulo;
    private JComboBox<SucursalInicial> cmbSucursal;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JLabel lblNombreSucursal;
    private JTextField txfNombreSucursal;
    private JLabel lblDescripcionSucursal;
    private JTextField txfDescripcionSucursal;
    private JLabel lblTelefonoSucursal;
    private JTextField txfTelefonoSucursal;
    private JLabel lblCorreoSucursal;
    private JTextField txfCorreoSucursal;
    private JLabel lblEstadoSucursal;
    private JTextField txfEstadoSucursal;
    private JLabel lblCiudadSucursal;
    private JTextField txfCiudadSucursal;
    private JLabel lblDireccionSucursal;
    private JTextField txfDireccionSucursal;
    private JLabel lblCpSucursal;
    private JTextField txfCpSucursal;
    private JLabel lblRfc;
    private JTextField txfRfc;
    private JLabel lblCurp;
    private JTextField txfCurp;
    private JLabel lblNombreAdmin;
    private JTextField txfNombreAdmin;
    private JLabel lblFechaNacimiento;
    private JTextField txfNacimiento;
    private JLabel lblCorreoAdmin;
    private JTextField txfCorreoAdmin;
    private JLabel lblUsuario;
    private JTextField txfUsuario;
    private JLabel lblClave;
    private JPasswordField pswfClave;
    private JLabel lblConfirmacion;
    private JPasswordField pswfConfirmacion;

    /**
     * Constructor vacío para el diseñador, sin conexión JDBC.
     */
    public Fr_InicializacionSistema() {
        this(new EstadoInicializacion(true, 0, 0), List.of());
    }

    public Fr_InicializacionSistema(
            EstadoInicializacion estado, List<SucursalInicial> sucursales) {
        if (estado == null || !estado.requiereInicializacion()
                || estado.requiereIntervencion()) {
            throw new IllegalArgumentException("El estado inicial de la BD no permite el asistente");
        }
        this.estado = estado;
        this.sucursales = List.copyOf(sucursales);
        if (estado.necesitaSeleccionarSucursal() && this.sucursales.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron sucursales activas");
        }
        setTitle("Primera configuración de Kath ERP");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 660, 710);
        setMinimumSize(new java.awt.Dimension(570, 570));
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 0, 128));
        lblTitulo = new JLabel("Inicialización de sucursal y administrador");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Dialog", Font.BOLD, 16));
        panelTitulo.add(lblTitulo);
        contentPane.add(panelTitulo, BorderLayout.NORTH);

        panelCentral = new JPanel();
        panelCentral.setBackground(new Color(255, 215, 0));
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(panelCentral);
        scroll.getVerticalScrollBar().setUnitIncrement(18);
        contentPane.add(scroll, BorderLayout.CENTER);

        panelSelectorSucursal = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelSelectorSucursal.setBackground(new Color(255, 215, 0));
        panelSelectorSucursal.add(new JLabel("Sucursal existente"));
        cmbSucursal = new JComboBox<>();
        for (SucursalInicial sucursal : this.sucursales) {
            cmbSucursal.addItem(sucursal);
        }
        panelSelectorSucursal.add(cmbSucursal);
        panelSelectorSucursal.setVisible(estado.necesitaSeleccionarSucursal());
        panelCentral.add(panelSelectorSucursal);

        panelSucursal = new JPanel();
        panelSucursal.setBackground(new Color(255, 215, 0));
        panelSucursal.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de la primera sucursal"));
        lblNombreSucursal = new JLabel("Nombre");
        txfNombreSucursal = new JTextField(24);
        txfNombreSucursal.setBackground(new Color(226, 246, 252));
        lblDescripcionSucursal = new JLabel("Descripción");
        txfDescripcionSucursal = new JTextField(24);
        txfDescripcionSucursal.setBackground(new Color(226, 246, 252));
        lblTelefonoSucursal = new JLabel("Teléfono (10 dígitos)");
        txfTelefonoSucursal = new JTextField(24);
        txfTelefonoSucursal.setBackground(new Color(226, 246, 252));
        lblCorreoSucursal = new JLabel("Correo (opcional)");
        txfCorreoSucursal = new JTextField(24);
        txfCorreoSucursal.setBackground(new Color(226, 246, 252));
        lblEstadoSucursal = new JLabel("Estado");
        txfEstadoSucursal = new JTextField(24);
        txfEstadoSucursal.setBackground(new Color(226, 246, 252));
        lblCiudadSucursal = new JLabel("Ciudad");
        txfCiudadSucursal = new JTextField(24);
        txfCiudadSucursal.setBackground(new Color(226, 246, 252));
        lblDireccionSucursal = new JLabel("Dirección");
        txfDireccionSucursal = new JTextField(24);
        txfDireccionSucursal.setBackground(new Color(226, 246, 252));
        lblCpSucursal = new JLabel("Código postal (5 dígitos)");
        txfCpSucursal = new JTextField(24);
        txfCpSucursal.setBackground(new Color(226, 246, 252));
        GroupLayout glSucursal = new GroupLayout(panelSucursal);
        glSucursal.setAutoCreateGaps(true);
        glSucursal.setAutoCreateContainerGaps(true);
        glSucursal.setHorizontalGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(glSucursal.createSequentialGroup()
                .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescripcionSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelefonoSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreoSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstadoSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCiudadSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDireccionSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCpSucursal, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txfNombreSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfDescripcionSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfTelefonoSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfCorreoSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfEstadoSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfCiudadSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfDireccionSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfCpSucursal, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))));
        glSucursal.setVerticalGroup(glSucursal.createSequentialGroup()
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNombreSucursal)
                .addComponent(txfNombreSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblDescripcionSucursal)
                .addComponent(txfDescripcionSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblTelefonoSucursal)
                .addComponent(txfTelefonoSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCorreoSucursal)
                .addComponent(txfCorreoSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblEstadoSucursal)
                .addComponent(txfEstadoSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCiudadSucursal)
                .addComponent(txfCiudadSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblDireccionSucursal)
                .addComponent(txfDireccionSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glSucursal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCpSucursal)
                .addComponent(txfCpSucursal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        panelSucursal.setLayout(glSucursal);

        panelSucursal.setVisible(estado.necesitaCrearSucursal());
        panelCentral.add(panelSucursal);

        panelAdministrador = new JPanel();
        panelAdministrador.setBackground(new Color(255, 215, 0));
        panelAdministrador.setBorder(javax.swing.BorderFactory.createTitledBorder("Primer administrador"));
        lblRfc = new JLabel("RFC");
        txfRfc = new JTextField(24);
        txfRfc.setBackground(new Color(226, 246, 252));
        lblCurp = new JLabel("CURP");
        txfCurp = new JTextField(24);
        txfCurp.setBackground(new Color(226, 246, 252));
        lblNombreAdmin = new JLabel("Nombre completo");
        txfNombreAdmin = new JTextField(24);
        txfNombreAdmin.setBackground(new Color(226, 246, 252));
        lblFechaNacimiento = new JLabel("Fecha nacimiento (dd/MM/aaaa)");
        txfNacimiento = new JTextField(24);
        txfNacimiento.setBackground(new Color(226, 246, 252));
        lblCorreoAdmin = new JLabel("Correo electrónico");
        txfCorreoAdmin = new JTextField(24);
        txfCorreoAdmin.setBackground(new Color(226, 246, 252));
        lblUsuario = new JLabel("Usuario de acceso");
        txfUsuario = new JTextField(24);
        txfUsuario.setBackground(new Color(226, 246, 252));
        lblClave = new JLabel("Contraseña");
        pswfClave = new JPasswordField(24);
        pswfClave.setBackground(new Color(226, 246, 252));
        lblConfirmacion = new JLabel("Repetir contraseña");
        pswfConfirmacion = new JPasswordField(24);
        pswfConfirmacion.setBackground(new Color(226, 246, 252));
        GroupLayout glAdministrador = new GroupLayout(panelAdministrador);
        glAdministrador.setAutoCreateGaps(true);
        glAdministrador.setAutoCreateContainerGaps(true);
        glAdministrador.setHorizontalGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(glAdministrador.createSequentialGroup()
                .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lblRfc, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurp, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreAdmin, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaNacimiento, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCorreoAdmin, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblClave, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblConfirmacion, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
                .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(txfRfc, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfCurp, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfNombreAdmin, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfNacimiento, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfCorreoAdmin, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(txfUsuario, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(pswfClave, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(pswfConfirmacion, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE))));
        glAdministrador.setVerticalGroup(glAdministrador.createSequentialGroup()
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblRfc)
                .addComponent(txfRfc, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCurp)
                .addComponent(txfCurp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblNombreAdmin)
                .addComponent(txfNombreAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblFechaNacimiento)
                .addComponent(txfNacimiento, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblCorreoAdmin)
                .addComponent(txfCorreoAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblUsuario)
                .addComponent(txfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblClave)
                .addComponent(pswfClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
            .addGroup(glAdministrador.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(lblConfirmacion)
                .addComponent(pswfConfirmacion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)));
        panelAdministrador.setLayout(glAdministrador);

        txfUsuario.setText(SolicitudInicializacion.USUARIO_ADMINISTRADOR);
        txfUsuario.setEditable(false);
        txfNacimiento.setToolTipText("dd/MM/aaaa");
        panelCentral.add(panelAdministrador);

        panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.setBackground(new Color(30, 144, 255));
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(205, 92, 92));
        btnCancelar.addActionListener(event -> {
            if (!guardando) {
                dispose();
            }
        });
        panelBotones.add(btnCancelar);
        btnGuardar = new JButton("Crear administrador");
        btnGuardar.setBackground(new Color(144, 238, 144));
        btnGuardar.addActionListener(event -> guardar());
        panelBotones.add(btnGuardar);
        contentPane.add(panelBotones, BorderLayout.SOUTH);
    }

    private SolicitudInicializacion leerFormulario() {
        Long sucursalExistente = null;
        if (estado.necesitaSeleccionarSucursal()) {
            SucursalInicial sucursal = (SucursalInicial) cmbSucursal.getSelectedItem();
            if (sucursal == null) {
                throw new IllegalArgumentException("Seleccione una sucursal existente");
            }
            sucursalExistente = sucursal.id();
        }
        final LocalDate nacimiento;
        try {
            nacimiento = LocalDate.parse(txfNacimiento.getText().trim(), FECHA);
        } catch (DateTimeParseException ex) {
            throw new IllegalArgumentException("Fecha de nacimiento inválida: dd/MM/aaaa");
        }
        return new SolicitudInicializacion(
                sucursalExistente, txfNombreSucursal.getText().trim(),
                txfDescripcionSucursal.getText().trim(),
                txfTelefonoSucursal.getText().trim(),
                txfCorreoSucursal.getText().trim(),
                txfEstadoSucursal.getText().trim(), txfCiudadSucursal.getText().trim(),
                txfDireccionSucursal.getText().trim(), txfCpSucursal.getText().trim(),
                txfRfc.getText().trim(), txfCurp.getText().trim(),
                txfNombreAdmin.getText().trim(), nacimiento, txfCorreoAdmin.getText().trim());
    }

    private void guardar() {
        if (guardando) {
            return;
        }
        final SolicitudInicializacion datos;
        try {
            datos = leerFormulario();
            datos.validar();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Datos inválidos", JOptionPane.WARNING_MESSAGE);
            return;
        }

        char[] clave = pswfClave.getPassword();
        char[] confirmacion = pswfConfirmacion.getPassword();
        try {
            if (!Arrays.equals(clave, confirmacion)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden",
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            SpResponseModel validacion = AppContext.inicializacionSistemaController.validar(datos, clave);
            if (validacion != null) {
                JOptionPane.showMessageDialog(this, validacion.message(),
                        "Datos inválidos", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } finally {
            Arrays.fill(confirmacion, '\0');
        }

        guardando = true;
        btnGuardar.setEnabled(false);
        btnCancelar.setEnabled(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        new SwingWorker<SpResponseModel, Void>() {
            @Override
            protected SpResponseModel doInBackground() {
                try {
                    return AppContext.inicializacionSistemaController.inicializar(datos, clave);
                } finally {
                    Arrays.fill(clave, '\0');
                }
            }
            @Override
            protected void done() {
                guardando = false;
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                btnCancelar.setEnabled(true);
                try {
                    SpResponseModel respuesta = get();
                    if (respuesta != null && respuesta.id() == 200) {
                        completado = true;
                        pswfClave.setText("");
                        pswfConfirmacion.setText("");
                        JOptionPane.showMessageDialog(Fr_InicializacionSistema.this,
                                "Configuración inicial completada. Inicie sesión con ADMIN y su contraseña.",
                                "Instalación completada", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        return;
                    }
                    JOptionPane.showMessageDialog(Fr_InicializacionSistema.this,
                            respuesta == null ? "No se recibió respuesta de MySQL" : respuesta.message(),
                            "No se completó la inicialización", JOptionPane.ERROR_MESSAGE);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    JOptionPane.showMessageDialog(Fr_InicializacionSistema.this,
                            "Se interrumpió la inicialización", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ExecutionException ex) {
                    JOptionPane.showMessageDialog(Fr_InicializacionSistema.this,
                            "No se pudo inicializar el sistema", "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    btnGuardar.setEnabled(isDisplayable() && !completado);
                }
            }
        }.execute();
    }

    public boolean isCompletado() {
        return completado;
    }
}
