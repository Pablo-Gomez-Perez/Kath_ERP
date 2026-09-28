package com.kathsoft.kathpos.app.view.retiros;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

/**
 * Solicita una contraseña en un JPasswordField, sin crear un String ni
 * escribirla en logs. La persona que invoca debe borrar el char[] devuelto
 * tan pronto termine la reautenticación.
 */
final class DialogoContraseniaRetiro {

    private DialogoContraseniaRetiro() {
    }

    /**
     * @return contraseña capturada o null si se canceló.
     */
    static char[] solicitar(Component padre, String nombreEmpleado, String accion) {
        if (!SwingUtilities.isEventDispatchThread()) {
            throw new IllegalStateException("El diálogo de contraseña debe abrirse en el EDT");
        }
        JPasswordField campo = new JPasswordField(20);
        campo.setFont(new Font("Dialog", Font.PLAIN, 13));
        campo.setEchoChar('\u2022');

        JPanel contenido = new JPanel(new BorderLayout(0, 8));
        contenido.add(new JLabel(
                "<html>Confirme la contraseña de <b>"
                        + escaparHtml(nombreEmpleado)
                        + "</b> para " + escaparHtml(accion) + ".</html>"),
                BorderLayout.NORTH);
        contenido.add(campo, BorderLayout.CENTER);

        int seleccion = JOptionPane.showConfirmDialog(
                padre, contenido, "Autorizar retiro de efectivo",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (seleccion != JOptionPane.OK_OPTION) {
            char[] descarte = campo.getPassword();
            Arrays.fill(descarte, '\0');
            campo.setText("");
            return null;
        }

        char[] contrasenia = campo.getPassword();
        campo.setText("");
        return contrasenia;
    }

    private static String escaparHtml(String texto) {
        if (texto == null || texto.isBlank()) {
            return "el empleado";
        }
        return texto.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
}
