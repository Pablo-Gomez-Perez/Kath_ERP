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
 *
 * <p>Una configuración con ID 0 representa una captura nueva en Java.
 * El identificador real siempre lo asigna MySQL.</p>
 */
public class ConfiguracionFiscalController {

    private static final int ERROR = -1;

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

    /**
     * Crea la primera configuración fiscal activa.
     *
     * <p>La regla de unicidad de configuración activa reside en
     * createConfiguracionFiscal(); Java sólo valida la captura antes de
     * abrir la conexión.</p>
     */
    public SpResponseModel createConfiguracionFiscal(ConfiguracionFiscal configuracion) {
        SpResponseModel validacion = validarDatos(configuracion);
        if (validacion != null) {
            return validacion;
        }

        String call = "CALL createConfiguracionFiscal(?,?,?,?,?,?)";

        try (Connection connection = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement statement = connection.prepareCall(call)) {

            statement.setString(1, configuracion.rfcEmisor());
            statement.setString(2, configuracion.nombreRazonSocial());
            statement.setString(3, configuracion.nombreComercial());
            statement.setString(4, configuracion.regimenFiscalClave());
            statement.setString(5, configuracion.regimenFiscalDescripcion());
            statement.setString(6, configuracion.numeroRegistroSistema());

            return leerRespuesta(statement,
                    "El procedimiento de alta no devolvió una respuesta");

        } catch (SQLException er) {
            er.printStackTrace(System.err);
            return new SpResponseModel(ERROR, er.getMessage());
        } catch (Exception er) {
            er.printStackTrace(System.err);
            return new SpResponseModel(ERROR, er.getMessage());
        }
    }

    public SpResponseModel updateConfiguracionFiscal(ConfiguracionFiscal configuracion) {
        if (configuracion == null || configuracion.idConfiguracion() <= 0) {
            return new SpResponseModel(ERROR, "La configuración fiscal no es válida");
        }

        SpResponseModel validacion = validarDatos(configuracion);
        if (validacion != null) {
            return validacion;
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

            return leerRespuesta(statement,
                    "El procedimiento de actualización no devolvió una respuesta");

        } catch (SQLException er) {
            er.printStackTrace(System.err);
            return new SpResponseModel(ERROR, er.getMessage());
        } catch (Exception er) {
            er.printStackTrace(System.err);
            return new SpResponseModel(ERROR, er.getMessage());
        }
    }

    /**
     * Validación independiente de JDBC para altas y actualizaciones.
     */
    static SpResponseModel validarDatos(ConfiguracionFiscal configuracion) {
        if (configuracion == null) {
            return new SpResponseModel(ERROR, "La configuración fiscal no es válida");
        }

        String rfc = limpiar(configuracion.rfcEmisor());
        String razonSocial = limpiar(configuracion.nombreRazonSocial());
        String nombreComercial = limpiar(configuracion.nombreComercial());
        String claveRegimen = limpiar(configuracion.regimenFiscalClave());
        String descripcionRegimen = limpiar(configuracion.regimenFiscalDescripcion());
        String numeroRegistro = limpiar(configuracion.numeroRegistroSistema());

        if (rfc.length() != 12 && rfc.length() != 13) {
            return new SpResponseModel(ERROR,
                    "El RFC del emisor debe contener 12 o 13 caracteres");
        }
        if (razonSocial.isEmpty() || razonSocial.length() > 255) {
            return new SpResponseModel(ERROR,
                    "El nombre o razón social es obligatorio y admite hasta 255 caracteres");
        }
        if (nombreComercial.length() > 255) {
            return new SpResponseModel(ERROR,
                    "El nombre comercial admite hasta 255 caracteres");
        }
        if (claveRegimen.length() != 3) {
            return new SpResponseModel(ERROR,
                    "La clave del régimen fiscal debe contener 3 caracteres");
        }
        if (descripcionRegimen.isEmpty() || descripcionRegimen.length() > 150) {
            return new SpResponseModel(ERROR,
                    "La descripción del régimen fiscal es obligatoria y admite hasta 150 caracteres");
        }
        if (numeroRegistro.length() > 100) {
            return new SpResponseModel(ERROR,
                    "El número de registro del sistema admite hasta 100 caracteres");
        }
        return null;
    }

    private static SpResponseModel leerRespuesta(
            CallableStatement statement, String mensajeSinRespuesta) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new SpResponseModel(
                        resultSet.getInt("id"), resultSet.getString("message"));
            }
        }
        return new SpResponseModel(ERROR, mensajeSinRespuesta);
    }

    private static String limpiar(String dato) {
        return dato == null ? "" : dato.trim();
    }
}
