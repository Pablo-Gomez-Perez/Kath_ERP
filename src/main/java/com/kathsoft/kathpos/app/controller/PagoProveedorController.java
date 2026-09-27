package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.model.compra.PagoProveedor;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Consultas de formas de pago y registro de pagos a proveedores.
 *
 * <p>El registro utiliza la conexión y transacción recibidas de
 * {@link CompraController}; nunca confirma una transacción propia.</p>
 */
public class PagoProveedorController {

    /**
     * Consulta las formas de pago activas a través de su procedimiento almacenado.
     *
     * @return formas de pago activas disponibles para una compra de contado
     * @throws SQLException si no es posible consultar las formas de pago
     */
    public List<JComboboxDataViewModel> listarFormasPagoActivas() throws SQLException {
        List<JComboboxDataViewModel> formas = new ArrayList<>();
        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement = connection.prepareCall("CALL ver_formas_de_pago()");
                ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                if (result.getBoolean("activo")) {
                    formas.add(new JComboboxDataViewModel(
                            result.getInt("id"),
                            result.getString("tipo_de_pago")));
                }
            }
        }
        return formas;
    }

    /**
     * Registra el pago con la transacción abierta por el controlador de compras.
     *
     * @param connection conexión transaccional de la compra
     * @param pago pago con identificador definitivo de compra
     * @return respuesta estándar del procedimiento {@code insertPagoProveedor}
     * @throws SQLException si falla la ejecución JDBC
     */
    public SpResponseModel registrarPago(Connection connection, PagoProveedor pago) throws SQLException {
        if (connection == null || pago == null || pago.idCompra() <= 0
                || pago.idFormaPago() <= 0 || pago.importe() == null
                || pago.importe().signum() <= 0 || pago.importe().scale() > 2) {
            return new SpResponseModel(500, "El pago a proveedor contiene datos inválidos");
        }
        try (CallableStatement statement = connection.prepareCall("CALL insertPagoProveedor(?,?,?)")) {
            statement.setInt(1, pago.idCompra());
            statement.setInt(2, pago.idFormaPago());
            statement.setBigDecimal(3, pago.importe());
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    return new SpResponseModel(result.getInt("id"), result.getString("message"));
                }
            }
        }
        return new SpResponseModel(500, "Sin respuesta del procedimiento insertPagoProveedor");
    }
}
