package com.kathsoft.kathpos.app.controller;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoDetalle;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoFiltro;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoRegistro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Controlador para registros históricos de retiros de efectivo.
 *
 * <p>Todas las operaciones de negocio se realizan mediante procedimientos
 * almacenados. No existe método de actualización: después del alta sólo
 * puede inhabilitarse el retiro y sólo durante el día de registro, tal
 * como valida el SP.</p>
 */
public class RetiroDeEfectivoController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private final transient AutorizacionRetiroService autorizacion;

    public RetiroDeEfectivoController() {
        this(new AutorizacionRetiroService());
    }

    RetiroDeEfectivoController(AutorizacionRetiroService autorizacion) {
        this.autorizacion = java.util.Objects.requireNonNull(autorizacion);
    }

    /**
     * Crea un retiro parcial o un corte final por sucursal y día. La validación
     * de unicidad del corte, incluido el escenario concurrente, reside en el SP.
     */
    public SpResponseModel registrarRetiro(
            RetiroDeEfectivoRegistro retiro, char[] contraseniaEmpleado) {
        SpResponseModel error = validarRegistro(retiro);
        if (error != null) {
            return error;
        }
        if (!contraseniaProporcionada(contraseniaEmpleado)) {
            return new SpResponseModel(401, "Debe proporcionar la contraseña del empleado");
        }

        try {
            if (!autorizacion.autorizar(
                    retiro.idEmpleado(), retiro.idSucursal(), contraseniaEmpleado)) {
                return new SpResponseModel(401, "Contraseña incorrecta o empleado no autorizado");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return new SpResponseModel(500, "No fue posible verificar la identidad del empleado");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL registrarRetiroDeEfectivo(?, ?, ?, ?, ?, ?)")) {
            stm.setLong(1, retiro.idSucursal());
            stm.setInt(2, retiro.idEmpleado());
            stm.setString(3, retiro.folio().trim());
            stm.setString(4, retiro.descripcion().trim());
            stm.setBigDecimal(5, retiro.importe());
            stm.setBoolean(6, retiro.esRetiroFinal());
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorSql("registrar", ex);
        }
    }

    /**
     * Inhabilita un retiro. El procedimiento valida sucursal, estado y fecha.
     */
    public SpResponseModel inhabilitarRetiro(
            int idRetiro, long idSucursal, char[] contraseniaEmpleado) {
        if (idRetiro <= 0 || idSucursal <= 0) {
            return new SpResponseModel(500, "El retiro y la sucursal son obligatorios");
        }
        if (!contraseniaProporcionada(contraseniaEmpleado)) {
            return new SpResponseModel(401, "Debe proporcionar la contraseña del empleado");
        }

        // El empleado autorizado se recupera del registro actual de la BD;
        // no se confía en la selección de una fila del JTable.
        try {
            RetiroDeEfectivoDetalle retiro = getRetiroDeEfectivoById(idRetiro, idSucursal);
            if (retiro == null) {
                return new SpResponseModel(500, "El retiro no existe en la sucursal actual");
            }
            if (!retiro.activo()) {
                return new SpResponseModel(500, "El retiro ya está inhabilitado");
            }
            if (!autorizacion.autorizar(
                    retiro.idEmpleado(), idSucursal, contraseniaEmpleado)) {
                return new SpResponseModel(401, "Contraseña incorrecta o empleado no autorizado");
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
            return new SpResponseModel(500, "No fue posible verificar la autorización del retiro");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL inhabilitarRetiroDeEfectivo(?, ?)")) {
            stm.setInt(1, idRetiro);
            stm.setLong(2, idSucursal);
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorSql("inhabilitar", ex);
        }
    }

    /**
     * Consulta el detalle de un registro, también si está inactivo.
     */
    public RetiroDeEfectivoDetalle getRetiroDeEfectivoById(int idRetiro, long idSucursal)
            throws SQLException {
        if (idRetiro <= 0 || idSucursal <= 0) {
            throw new IllegalArgumentException("El retiro y la sucursal son obligatorios");
        }
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL getRetiroDeEfectivoById(?, ?)")) {
            stm.setInt(1, idRetiro);
            stm.setLong(2, idSucursal);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next() ? mapearDetalle(rs) : null;
            }
        }
    }

    /**
     * Retorna todos los retiros de la sucursal o los que coincidan con
     * empleado, intervalo de fechas y ordenamiento indicados.
     */
    public List<RetiroDeEfectivoDetalle> listarRetiros(
            long idSucursal, RetiroDeEfectivoFiltro filtro) throws SQLException {
        if (idSucursal <= 0) {
            throw new IllegalArgumentException("Es obligatoria una sucursal válida");
        }
        RetiroDeEfectivoFiltro aplicado =
                filtro == null ? RetiroDeEfectivoFiltro.sinFiltros() : filtro;
        List<RetiroDeEfectivoDetalle> registros = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL listRetirosDeEfectivo(?, ?, ?, ?, ?)")) {
            stm.setLong(1, idSucursal);
            if (aplicado.idEmpleado() == null) {
                stm.setNull(2, Types.INTEGER);
            } else {
                stm.setInt(2, aplicado.idEmpleado());
            }
            setFechaOpcional(stm, 3, aplicado.fechaInicial());
            setFechaOpcional(stm, 4, aplicado.fechaFinal());
            stm.setInt(5, aplicado.orden().codigoSql());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    registros.add(mapearDetalle(rs));
                }
            }
        }
        return registros;
    }

    /**
     * Proyección del listado para el DefaultTableModel del panel.
     * Orden: ID, folio, fecha, empleado, descripción, importe,
     * tipo de retiro y estado.
     */
    public Vector<Object[]> verRetirosEnTabla(long idSucursal, RetiroDeEfectivoFiltro filtro)
            throws SQLException {
        Vector<Object[]> filas = new Vector<>();
        for (RetiroDeEfectivoDetalle retiro : listarRetiros(idSucursal, filtro)) {
            filas.add(proyectarFila(retiro));
        }
        return filas;
    }

    /** Contrato explícito entre la proyección del controlador y el JTable. */
    static Object[] proyectarFila(RetiroDeEfectivoDetalle retiro) {
        return new Object[] {
                retiro.idRetiro(),
                retiro.folio(),
                retiro.fecha(),
                retiro.empleado(),
                retiro.descripcion(),
                retiro.importe(),
                retiro.esRetiroFinal() ? "Corte final" : "Retiro parcial",
                retiro.activo() ? "Activo" : "Inactivo"
        };
    }

    /**
     * Opciones de captura: sólo empleados activos de la sucursal.
     * Reutiliza el SP ya instalado por el módulo de gastos.
     */
    public Vector<JComboboxDataViewModel> listarEmpleadosActivos(long idSucursal)
            throws SQLException {
        if (idSucursal <= 0) {
            throw new IllegalArgumentException("La sucursal es obligatoria");
        }
        Vector<JComboboxDataViewModel> empleados = new Vector<>();
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listCmbEmpleadosGasto(?)")) {
            stm.setLong(1, idSucursal);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    empleados.add(new JComboboxDataViewModel(
                            rs.getInt("id"), rs.getString("nombre")));
                }
            }
        }
        return empleados;
    }

    /**
     * Filtro histórico: incluye empleados activos e inactivos para no
     * perder la capacidad de buscar retiros efectuados antes de su baja.
     * El SP heredado recibe un INT de sucursal.
     */
    public Vector<JComboboxDataViewModel> listarEmpleadosFiltro(long idSucursal)
            throws SQLException {
        if (idSucursal <= 0 || idSucursal > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("La sucursal es inválida");
        }
        Vector<JComboboxDataViewModel> empleados = new Vector<>();
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL ver_rfc_empleado_por_sucursal(?)")) {
            stm.setInt(1, Math.toIntExact(idSucursal));
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    empleados.add(new JComboboxDataViewModel(
                            rs.getInt(1), rs.getString(2)));
                }
            }
        }
        return empleados;
    }

    /**
     * Evita invocar la autenticación y cualquier procedimiento de escritura
     * cuando el diálogo no proporcionó contraseña.
     */
    private static boolean contraseniaProporcionada(char[] contrasenia) {
        return contrasenia != null && contrasenia.length > 0;
    }

    /**
     * Permite probar las reglas de captura sin establecer una conexión JDBC.
     */
    public static SpResponseModel validarRegistro(RetiroDeEfectivoRegistro retiro) {
        if (retiro == null) {
            return new SpResponseModel(500, "Debe proporcionar los datos del retiro");
        }
        try {
            retiro.validar();
            return null;
        } catch (IllegalArgumentException ex) {
            return new SpResponseModel(500, ex.getMessage());
        }
    }

    private static RetiroDeEfectivoDetalle mapearDetalle(ResultSet rs) throws SQLException {
        Date fecha = rs.getDate("fecha");
        BigDecimal importe = rs.getBigDecimal("importe");
        return new RetiroDeEfectivoDetalle(
                rs.getInt("id_retiro"),
                rs.getLong("id_sucursal"),
                rs.getInt("id_empleado"),
                rs.getString("empleado"),
                rs.getString("folio"),
                fecha == null ? null : fecha.toLocalDate(),
                rs.getString("descripcion"),
                importe == null ? null : importe.setScale(2, RoundingMode.HALF_UP),
                rs.getBoolean("es_retiro_final"),
                rs.getBoolean("activo"));
    }

    private static void setFechaOpcional(CallableStatement stm, int indice, java.time.LocalDate fecha)
            throws SQLException {
        if (fecha == null) {
            stm.setNull(indice, Types.DATE);
        } else {
            stm.setDate(indice, Date.valueOf(fecha));
        }
    }

    private static SpResponseModel leerRespuesta(CallableStatement stm) throws SQLException {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return new SpResponseModel(rs.getInt("id"), rs.getString("message"));
            }
        }
        return new SpResponseModel(500, "El procedimiento no devolvió una respuesta");
    }

    private static SpResponseModel errorSql(String operacion, SQLException ex) {
        ex.printStackTrace(System.err);
        return new SpResponseModel(500,
                "No fue posible " + operacion + " el retiro: " + ex.getMessage());
    }
}
