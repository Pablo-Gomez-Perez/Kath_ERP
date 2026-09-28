package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import com.kathsoft.kathpos.app.model.empleado.EmpleadoLogin;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Verifica que quien autoriza el movimiento conozca la contraseña del
 * empleado responsable. Reutiliza el mismo mecanismo PBKDF2 del login.
 *
 * <p>El procedimiento getEmpleadoById resuelve la identidad por ID, nunca
 * mediante un nombre escrito en la UI. LoginController llama al SP
 * getEmpleadoLogin y comprueba el hash almacenado en memoria Java.</p>
 *
 * <p>Esta reautenticación se aplica en el controlador de retiros. No reemplaza
 * controles de privilegios SQL en instalaciones con acceso directo a la BD.</p>
 */
public final class AutorizacionRetiroService {

    @FunctionalInterface
    interface ConsultaEmpleado {
        EmpleadoResponsable buscarPorId(int idEmpleado) throws Exception;
    }

    @FunctionalInterface
    interface AutenticacionEmpleado {
        EmpleadoLogin iniciarSesion(String nombreCorto, char[] contrasenia) throws Exception;
    }

    record EmpleadoResponsable(
            int idEmpleado, long idSucursal, String nombreCorto, boolean activo) {
    }

    private final ConsultaEmpleado consultaEmpleado;
    private final AutenticacionEmpleado autenticacionEmpleado;

    public AutorizacionRetiroService() {
        this(AutorizacionRetiroService::consultarEmpleadoDesdeSp,
                new LoginController()::iniciarSesion);
    }

    AutorizacionRetiroService(
            ConsultaEmpleado consultaEmpleado, AutenticacionEmpleado autenticacionEmpleado) {
        this.consultaEmpleado = Objects.requireNonNull(consultaEmpleado);
        this.autenticacionEmpleado = Objects.requireNonNull(autenticacionEmpleado);
    }

    /**
     * No almacena, convierte a String ni modifica la contraseña proporcionada.
     * La capa que capturó el char[] debe borrarlo después de la operación.
     *
     * @return true sólo si se autenticó al empleado esperado de esa sucursal
     */
    public boolean autorizar(int idEmpleado, long idSucursal, char[] contrasenia)
            throws Exception {
        if (idEmpleado <= 0 || idSucursal <= 0 || contrasenia == null
                || contrasenia.length == 0) {
            return false;
        }

        EmpleadoResponsable esperado = consultaEmpleado.buscarPorId(idEmpleado);
        if (esperado == null || !esperado.activo()
                || esperado.idEmpleado() != idEmpleado
                || esperado.idSucursal() != idSucursal
                || esperado.nombreCorto() == null
                || esperado.nombreCorto().isBlank()) {
            return false;
        }

        EmpleadoLogin autenticado =
                autenticacionEmpleado.iniciarSesion(esperado.nombreCorto(), contrasenia);
        return coincideEmpleadoAutorizado(autenticado, idEmpleado, idSucursal);
    }

    static boolean coincideEmpleadoAutorizado(
            EmpleadoLogin empleado, int idEmpleado, long idSucursal) {
        return empleado != null
                && empleado.isActivo()
                && empleado.getIdEmpleado() == idEmpleado
                && empleado.getIdSucursal() == idSucursal;
    }

    private static EmpleadoResponsable consultarEmpleadoDesdeSp(int idEmpleado)
            throws SQLException {
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL getEmpleadoById(?)")) {
            stm.setInt(1, idEmpleado);
            try (ResultSet rs = stm.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                return new EmpleadoResponsable(
                        rs.getInt("id_empleado"),
                        rs.getLong("id_sucursal"),
                        rs.getString("nombre_corto"),
                        rs.getBoolean("activo"));
            }
        }
    }
}
