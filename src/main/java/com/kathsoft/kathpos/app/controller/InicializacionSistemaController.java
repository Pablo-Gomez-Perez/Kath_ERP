package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.model.configuracion.EstadoInicializacion;
import com.kathsoft.kathpos.app.model.configuracion.SolicitudInicializacion;
import com.kathsoft.kathpos.app.model.configuracion.SucursalInicial;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;
import com.kathsoft.kathpos.tools.PasswordHashService;

/**
 * Primera ejecución sobre una base de datos sin empleados.
 * Todas las consultas y escrituras se delegan a procedimientos almacenados.
 */
public final class InicializacionSistemaController {

    /** Nunca confundir ausencia de empleados con errores de conexión. */
    public EstadoInicializacion consultarEstado() throws SQLException {
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL consultarEstadoInicializacion()");
                ResultSet rs = stm.executeQuery()) {
            if (!rs.next()) {
                throw new SQLException("El procedimiento de inicialización no devolvió el estado");
            }
            return new EstadoInicializacion(
                    rs.getBoolean("requiere_inicializacion"),
                    rs.getInt("sucursales"),
                    rs.getInt("sucursales_activas"));
        }
    }

    /** Muestra sólo sucursales activas cuando hay sucursales sin empleados. */
    public List<SucursalInicial> listarSucursales() throws SQLException {
        List<SucursalInicial> sucursales = new ArrayList<>();
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listSucursalesInicializacion()");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                sucursales.add(new SucursalInicial(
                        rs.getLong("id_sucursal"), rs.getString("nombre"),
                        rs.getString("estado"), rs.getString("ciudad"),
                        rs.getString("direccion"), rs.getString("codigo_postal")));
            }
        }
        return sucursales;
    }

    /**
     * El hash se genera localmente; jamás se envía una contraseña en claro
     * a MySQL. El procedimiento vuelve a comprobar el estado bajo bloqueo
     * y crea sucursal/administrador en una única transacción.
     *
     * La UI debe borrar el arreglo recibido tras completar esta llamada.
     */
    public SpResponseModel inicializar(
            SolicitudInicializacion datos, char[] contraseniaAdministrador) {
        SpResponseModel error = validar(datos, contraseniaAdministrador);
        if (error != null) {
            return error;
        }
        try {
            String hash = PasswordHashService.hashPassword(contraseniaAdministrador);
            try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                    CallableStatement stm = cn.prepareCall(
                            "CALL inicializarKathErp(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                if (datos.idSucursalExistente() == null) {
                    stm.setNull(1, Types.BIGINT);
                } else {
                    stm.setLong(1, datos.idSucursalExistente());
                }
                stm.setString(2, datos.nombreSucursal());
                stm.setString(3, datos.descripcionSucursal());
                stm.setString(4, datos.telefonoSucursal());
                stm.setString(5, datos.correoSucursal());
                stm.setString(6, datos.estadoSucursal());
                stm.setString(7, datos.ciudadSucursal());
                stm.setString(8, datos.direccionSucursal());
                stm.setString(9, datos.codigoPostalSucursal());
                stm.setString(10, datos.rfcAdministrador().trim().toUpperCase(java.util.Locale.ROOT));
                stm.setString(11, datos.curpAdministrador().trim().toUpperCase(java.util.Locale.ROOT));
                stm.setString(12, datos.nombreAdministrador().trim());
                stm.setString(13, SolicitudInicializacion.USUARIO_ADMINISTRADOR);
                stm.setDate(14, Date.valueOf(datos.fechaNacimientoAdministrador()));
                stm.setString(15, datos.correoAdministrador().trim());
                stm.setString(16, hash);
                try (ResultSet rs = stm.executeQuery()) {
                    if (rs.next()) {
                        return new SpResponseModel(rs.getInt("id"), rs.getString("message"));
                    }
                }
                return new SpResponseModel(500, "El procedimiento no devolvió confirmación");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return new SpResponseModel(500, "No fue posible completar la inicialización: "
                    + ex.getMessage());
        }
    }

    public static SpResponseModel validar(
            SolicitudInicializacion datos, char[] contrasenia) {
        if (datos == null) {
            return new SpResponseModel(500, "Capture los datos iniciales");
        }
        try {
            datos.validar();
        } catch (IllegalArgumentException ex) {
            return new SpResponseModel(500, ex.getMessage());
        }
        if (contrasenia == null || contrasenia.length < 10) {
            return new SpResponseModel(500, "La contraseña debe contener al menos 10 caracteres");
        }
        boolean contenido = false;
        for (char c : contrasenia) {
            if (!Character.isWhitespace(c)) {
                contenido = true;
                break;
            }
        }
        if (!contenido) {
            return new SpResponseModel(500, "La contraseña es obligatoria");
        }
        return null;
    }
}
