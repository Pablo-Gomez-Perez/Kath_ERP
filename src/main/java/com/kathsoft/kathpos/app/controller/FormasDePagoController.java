package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.model.FormasDePago;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Acceso a formas de pago mediante procedimientos almacenados.
 *
 * <p>El controlador no conoce componentes Swing ni DefaultTableModel.
 * Las vistas reciben modelos de dominio y deciden cómo representarlos.</p>
 */
public class FormasDePagoController implements java.io.Serializable {

    private static final long serialVersionUID = -4738796646362834472L;

    /**
     * Lista las formas de pago retornadas por ver_formas_de_pago().
     */
    public List<FormasDePago> listarFormasDePago() throws SQLException {
        List<FormasDePago> formas = new ArrayList<>();

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement =
                        connection.prepareCall("CALL ver_formas_de_pago()");
                ResultSet result = statement.executeQuery()) {

            while (result.next()) {
                FormasDePago forma = new FormasDePago();
                forma.setId(result.getInt("id"));
                forma.setTipoDePago(result.getString("tipo_de_pago"));
                forma.setEstaActivo(result.getBoolean("activo"));
                formas.add(forma);
            }
        }

        return formas;
    }

    /**
     * Registra una forma de pago. Si el procedimiento falla la SQLException
     * se propaga a la vista; no se informa éxito antes de tiempo.
     */
    public void insertarFormaDePago(FormasDePago formaDePago) throws SQLException {
        validarFormaDePago(formaDePago, false);

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement =
                        connection.prepareCall("CALL insert_forma_de_pago(?,?)")) {

            statement.setString(1, formaDePago.getTipoDePago().trim());
            statement.setBoolean(2, formaDePago.isEsFlujoEfectivo());
            statement.execute();
        }
    }

    /**
     * Actualiza una forma de pago existente.
     */
    public void actualizarFormaDePago(FormasDePago formaDePago) throws SQLException {
        validarFormaDePago(formaDePago, true);

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement =
                        connection.prepareCall("CALL update_forma_de_pago(?,?,?)")) {

            statement.setInt(1, formaDePago.getId());
            statement.setString(2, formaDePago.getTipoDePago().trim());
            statement.setBoolean(3, formaDePago.isEsFlujoEfectivo());
            statement.execute();
        }
    }

    /**
     * Inhabilita una forma de pago.
     */
    public void eliminarFormaDepAgo(int idFormaDePago) throws SQLException {
        if (idFormaDePago <= 0) {
            throw new IllegalArgumentException("Seleccione una forma de pago válida");
        }

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement =
                        connection.prepareCall("CALL eliminar_forma_pago(?)")) {

            statement.setInt(1, idFormaDePago);
            statement.execute();
        }
    }

    /**
     * Consulta una forma de pago por ID. Retorna null si el SP no devuelve fila.
     */
    public FormasDePago consultarFormaDePagoPorId(int id) throws SQLException {
        if (id <= 0) {
            throw new IllegalArgumentException("La forma de pago no es válida");
        }

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement =
                        connection.prepareCall("CALL bucar_forma_pago_por_id(?)")) {

            statement.setInt(1, id);

            try (ResultSet result = statement.executeQuery()) {
                if (!result.next()) {
                    return null;
                }

                FormasDePago forma = new FormasDePago();
                forma.setId(result.getInt("id"));
                forma.setEsFlujoEfectivo(result.getBoolean("es_flujo_efectivo"));
                forma.setTipoDePago(result.getString("tipo_de_pago"));
                forma.setEstaActivo(result.getBoolean("activo"));
                return forma;
            }
        }
    }

    /**
     * Validación simple previa a JDBC. Las reglas definitivas siguen en los SP.
     */
    static void validarFormaDePago(FormasDePago formaDePago, boolean requiereId) {
        if (formaDePago == null) {
            throw new IllegalArgumentException("La forma de pago es obligatoria");
        }
        if (requiereId && formaDePago.getId() <= 0) {
            throw new IllegalArgumentException("La forma de pago no es válida");
        }
        String nombre = formaDePago.getTipoDePago();
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la forma de pago es obligatorio");
        }
        if (nombre.trim().length() > 18) {
            throw new IllegalArgumentException(
                    "El nombre de la forma de pago admite hasta 18 caracteres");
        }
    }
}
