package com.kathsoft.kathpos.app.view.compras;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;

import com.kathsoft.kathpos.app.controller.PagoProveedorController;
import com.kathsoft.kathpos.app.model.compra.PagoProveedor;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;

/**
 * Captura la forma de pago de una compra nueva de contado.
 *
 * <p>Es modal y no realiza operaciones de persistencia: entrega la selección
 * a {@code Fr_DatosCompras}, que la registra junto con la compra y el
 * inventario en una sola transacción JDBC.</p>
 */
public class Fr_PagoProveedor extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final Color AZUL_TITULO = new Color(25, 25, 112);
    private static final Color DORADO = new Color(255, 215, 0);
    private static final Color AZUL_ACCIONES = new Color(30, 144, 255);

    private final PagoProveedorController pagoController = new PagoProveedorController();
    private final BigDecimal total;
    private final JComboBox<JComboboxDataViewModel> cmbFormaPago = new JComboBox<>();
    private final JTextField txfImporte = new JTextField();
    private final JButton btnConfirmar = new JButton("Confirmar");
    private PagoProveedor pagoConfirmado;

    /**
     * Abre un formulario para seleccionar el pago total de una compra.
     *
     * @param propietario ventana de compras
     * @param importeTotal importe exacto que debe liquidarse
     */
    public Fr_PagoProveedor(JFrame propietario, BigDecimal importeTotal) {
        super(propietario, "Pago a proveedor", true);
        if (importeTotal == null || importeTotal.signum() <= 0) {
            throw new IllegalArgumentException("Una compra de contado debe tener un total mayor a cero");
        }
        this.total = importeTotal.setScale(2, RoundingMode.HALF_UP);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(470, 255);
        setResizable(false);

        JPanel encabezado = new JPanel(new FlowLayout(FlowLayout.CENTER));
        encabezado.setBackground(AZUL_TITULO);
        JLabel titulo = new JLabel("Pago de compra de contado");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Dialog", Font.BOLD, 16));
        encabezado.add(titulo);

        JPanel formulario = new JPanel();
        formulario.setBackground(DORADO);
        JLabel lblFormaPago = new JLabel("Forma de pago");
        JLabel lblImporte = new JLabel("Importe total");
        txfImporte.setText(NumberFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
                .format(this.total));
        txfImporte.setEditable(false);
        txfImporte.setEnabled(false);
        txfImporte.setColumns(16);

        GroupLayout layout = new GroupLayout(formulario);
        formulario.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblFormaPago).addComponent(lblImporte))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cmbFormaPago, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)
                        .addComponent(txfImporte, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblFormaPago).addComponent(cmbFormaPago))
                .addGap(20)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblImporte).addComponent(txfImporte)));

        JPanel acciones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        acciones.setBackground(AZUL_ACCIONES);
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(205, 92, 92));
        btnCancelar.addActionListener(event -> dispose());
        btnConfirmar.setBackground(new Color(144, 238, 144));
        btnConfirmar.addActionListener(event -> confirmarPago());
        acciones.add(btnCancelar);
        acciones.add(btnConfirmar);

        JPanel raiz = new JPanel();
        GroupLayout principal = new GroupLayout(raiz);
        raiz.setLayout(principal);
        principal.setHorizontalGroup(principal.createParallelGroup()
                .addComponent(encabezado, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(formulario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acciones, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        principal.setVerticalGroup(principal.createSequentialGroup()
                .addComponent(encabezado, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                .addComponent(formulario, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(acciones, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE));
        setContentPane(raiz);

        cargarFormasPago();
        setLocationRelativeTo(propietario);
    }

    private void cargarFormasPago() {
        btnConfirmar.setEnabled(false);
        try {
            List<JComboboxDataViewModel> formas = pagoController.listarFormasPagoActivas();
            for (JComboboxDataViewModel forma : formas) {
                cmbFormaPago.addItem(forma);
            }
            btnConfirmar.setEnabled(!formas.isEmpty());
            if (formas.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No existen formas de pago activas para registrar esta compra",
                        "Forma de pago requerida", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "No fue posible consultar las formas de pago: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void confirmarPago() {
        JComboboxDataViewModel forma = (JComboboxDataViewModel) cmbFormaPago.getSelectedItem();
        if (forma == null || forma.id() <= 0) {
            JOptionPane.showMessageDialog(this, "Seleccione una forma de pago válida",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // La compra aún no existe; CompraController asigna el ID tras insertCompra.
        pagoConfirmado = new PagoProveedor(0, forma.id(), total);
        dispose();
    }

    /**
     * Obtiene el pago confirmado; {@code null} al cancelar o cerrar la ventana.
     *
     * @return selección validada del usuario o {@code null}
     */
    public PagoProveedor getPagoConfirmado() {
        return pagoConfirmado;
    }
}
