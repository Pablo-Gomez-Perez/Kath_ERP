package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kathsoft.kathpos.app.model.configuracion.ConfiguracionFiscal;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Acceso a la configuración fiscal del emisor mediante procedimientos almacenados.
 */
public class ConfiguracionFiscalController {

    private static final int ERROR = 500;

    public ConfiguracionFiscal getConfiguracionFiscal() {
        String call = "CALL getConfiguracionFiscal()";

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement = connection.prepareCall(call);
                ResultSet resultSet = statement.executeQuery()) {

            if (!resultSet.next()) {
                return null;
            }

            return new ConfiguracionFiscal(
                    resultSet.getInt("id_configuracion"),
                    resultSet.getString("rfc_emisor"),
                    resultSet.getString("nombre_razon_social"),
                    resultSet.getString("nombre_comercial"),
                    resultSet.getString("regimen_fiscal_clave"),
                    resultSet.getString("regimen_fiscal_descripcion"),
                    resultSet.getString("numero_registro_sistema"),
                    resultSet.getBoolean("activo"));

        } catch (SQLException er) {
            er.printStackTrace(System.err);
            return null;
        } catch (Exception er) {
            er.printStackTrace(System.err);
            return null;
        }
    }

    public SpResponseModel updateConfiguracionFiscal(ConfiguracionFiscal configuracion) {
        if (configuracion == null || configuracion.idConfiguracion() <= 0) {
            return new SpResponseModel(ERROR, "La configuración fiscal no es válida");
        }

        String call = "CALL updateConfiguracionFiscal(?,?,?,?,?,?,?)";

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement = connection.prepareCall(call)) {

            statement.setInt(1, configuracion.idConfiguracion());
            statement.setString(2, configuracion.rfcEmisor());
            statement.setString(3, configuracion.nombreRazonSocial());
            statement.setString(4, configuracion.nombreComercial());
            statement.setString(5, configuracion.regimenFiscalClave());
            statement.setString(6, configuracion.regimenFiscalDescripcion());
            statement.setString(7, configuracion.numeroRegistroSistema());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new SpResponseModel(resultSet.getInt("id"), resultSet.getString("message"));
                }
            }

            return new SpResponseModel(ERROR, "El procedimiento no devolvió una respuesta");

        } catch (SQLException er) {
            er.printStackTrace(System.err);
            return new SpResponseModel(ERROR, er.getMessage());
        } catch (Exception er) {
            er.printStackTrace(System.err);
            return new SpResponseModel(ERROR, er.getMessage());
        }
    }
}
