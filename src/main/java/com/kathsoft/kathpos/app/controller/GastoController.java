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

import com.kathsoft.kathpos.app.model.gastos.GastoDetalle;
import com.kathsoft.kathpos.app.model.gastos.GastoFiltro;
import com.kathsoft.kathpos.app.model.gastos.GastoRegistro;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Operaciones de gastos de la sucursal actual.
 *
 * <p>No contiene SQL de negocio embebido: las operaciones se delegan
 * exclusivamente a los procedimientos almacenados publicados para este módulo.
 * La base de datos debe contener la migración id_forma_pago y los seis SP
 * antes de utilizar el módulo.</p>
 */
public class GastoController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Registra un gasto fechado por el servidor MySQL, sin aceptar fecha desde Java.
     */
    public SpResponseModel crearGasto(GastoRegistro gasto) {
        SpResponseModel validacion = validar(gasto, false);
        if (validacion != null) {
            return validacion;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL createGasto(?, ?, ?, ?, ?, ?, ?)")) {
            stm.setLong(1, gasto.idSucursal());
            stm.setInt(2, gasto.idCategoria());
            stm.setInt(3, gasto.idEmpleado());
            stm.setInt(4, gasto.idFormaPago());
            stm.setString(5, gasto.descripcion().trim());
            stm.setBigDecimal(6, gasto.importe());
            stm.setBigDecimal(7, gasto.iva());
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorSql("registrar", ex);
        }
    }

    /**
     * Actualiza un gasto. El SP aplica el bloqueo y verifica que el registro
     * corresponde a la sucursal y a la fecha actual del servidor.
     */
    public SpResponseModel actualizarGasto(GastoRegistro gasto) {
        SpResponseModel validacion = validar(gasto, true);
        if (validacion != null) {
            return validacion;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL updateGasto(?, ?, ?, ?, ?, ?, ?, ?)")) {
            stm.setInt(1, gasto.idGasto());
            stm.setLong(2, gasto.idSucursal());
            stm.setInt(3, gasto.idCategoria());
            stm.setInt(4, gasto.idEmpleado());
            stm.setInt(5, gasto.idFormaPago());
            stm.setString(6, gasto.descripcion().trim());
            stm.setBigDecimal(7, gasto.importe());
            stm.setBigDecimal(8, gasto.iva());
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorSql("actualizar", ex);
        }
    }

    /**
     * Inhabilita el gasto seleccionado sin eliminar su registro histórico.
     */
    public SpResponseModel eliminarGasto(int idGasto, long idSucursal) {
        if (idGasto <= 0 || idSucursal <= 0) {
            return new SpResponseModel(500, "El gasto y la sucursal son obligatorios");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL deleteGasto(?, ?)")) {
            stm.setInt(1, idGasto);
            stm.setLong(2, idSucursal);
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorSql("inhabilitar", ex);
        }
    }

    /**
     * Lista gastos activos e inactivos con filtro y ordenamiento.
     *
     * @param idSucursal sucursal proveniente de la sesión del ERP
     * @param filtro filtros opcionales; null equivale a sin filtros
     * @return listado completo
     * @throws SQLException al fallar el procedimiento o la conexión
     */
    public List<GastoDetalle> listarGastos(long idSucursal, GastoFiltro filtro) throws SQLException {
        if (idSucursal <= 0) {
            throw new IllegalArgumentException("La sucursal actual es obligatoria");
        }
        GastoFiltro aplicado = filtro == null ? GastoFiltro.sinFiltros() : filtro;
        List<GastoDetalle> resultado = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listGastos(?, ?, ?, ?, ?, ?)")) {
            stm.setLong(1, idSucursal);
            setEnteroOpcional(stm, 2, aplicado.idEmpleado());
            setEnteroOpcional(stm, 3, aplicado.idCategoria());
            setFechaOpcional(stm, 4, aplicado.fechaInicial());
            setFechaOpcional(stm, 5, aplicado.fechaFinal());
            stm.setInt(6, aplicado.orden().codigoSql());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultado.add(mapearDetalle(rs));
                }
            }
        }

        return resultado;
    }

    /**
     * Consulta un gasto sin permitir obtener registros de otra sucursal.
     */
    public GastoDetalle getGastoByID(int idGasto, long idSucursal) throws SQLException {
        if (idGasto <= 0 || idSucursal <= 0) {
            throw new IllegalArgumentException("El gasto y la sucursal son obligatorios");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL getGastoByID(?, ?)")) {
            stm.setInt(1, idGasto);
            stm.setLong(2, idSucursal);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next() ? mapearDetalle(rs) : null;
            }
        }
    }

    /**
     * Devuelve sólo empleados activos pertenecientes a la sucursal actual,
     * evitando exponer opciones que createGasto rechazaría.
     */
    public Vector<JComboboxDataViewModel> listCmbEmpleadosGasto(long idSucursal)
            throws SQLException {
        if (idSucursal <= 0) {
            throw new IllegalArgumentException("La sucursal actual es obligatoria");
        }
        Vector<JComboboxDataViewModel> resultado = new Vector<>();
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listCmbEmpleadosGasto(?)")) {
            stm.setLong(1, idSucursal);
            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    resultado.add(new JComboboxDataViewModel(
                            rs.getInt("id"), rs.getString("nombre")));
                }
            }
        }
        return resultado;
    }

    /**
     * Reutiliza el SP de formas de pago; no depende del controlador del proveedor.
     */
    public Vector<JComboboxDataViewModel> listarFormasPagoActivas() throws SQLException {
        Vector<JComboboxDataViewModel> resultado = new Vector<>();
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL ver_formas_de_pago()");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                if (rs.getBoolean("activo")) {
                    resultado.add(new JComboboxDataViewModel(
                            rs.getInt("id"), rs.getString("tipo_de_pago")));
                }
            }
        }
        return resultado;
    }

    /**
     * Adaptador del futuro PanelGastos, con las mismas columnas de listGastos.
     */
    public Vector<Object[]> verGastosEnTabla(long idSucursal, GastoFiltro filtro)
            throws SQLException {
        Vector<Object[]> filas = new Vector<>();
        for (GastoDetalle gasto : listarGastos(idSucursal, filtro)) {
            filas.add(new Object[] {
                    gasto.idGasto(),
                    gasto.fechaOperacion(),
                    gasto.empleado(),
                    gasto.categoria(),
                    gasto.formaPago(),
                    gasto.descripcion(),
                    gasto.importe(),
                    gasto.iva(),
                    gasto.total(),
                    gasto.activo() ? "Activo" : "Inactivo"
            });
        }
        return filas;
    }

    static SpResponseModel validar(GastoRegistro gasto, boolean actualizacion) {
        if (gasto == null) {
            return new SpResponseModel(500, "Debe indicar los datos del gasto");
        }
        try {
            gasto.validar(actualizacion);
            return null;
        } catch (IllegalArgumentException ex) {
            return new SpResponseModel(500, ex.getMessage());
        }
    }

    private static GastoDetalle mapearDetalle(ResultSet rs) throws SQLException {
        int idFormaPago = rs.getInt("id_forma_pago");
        Integer formaPago = rs.wasNull() ? null : idFormaPago;

        Date fecha = rs.getDate("fecha_operacion");
        return new GastoDetalle(
                rs.getInt("id_gasto"),
                rs.getLong("id_sucursal"),
                rs.getInt("id_categoria"),
                rs.getString("categoria"),
                rs.getInt("id_empleado"),
                rs.getString("empleado"),
                formaPago,
                rs.getString("forma_pago"),
                fecha == null ? null : fecha.toLocalDate(),
                rs.getString("descripcion"),
                leerImporte(rs, "importe"),
                leerImporte(rs, "iva"),
                leerImporte(rs, "total"),
                rs.getBoolean("activo"));
    }

    private static BigDecimal leerImporte(ResultSet rs, String columna) throws SQLException {
        BigDecimal importe = rs.getBigDecimal(columna);
        return importe == null ? null : importe.setScale(2, RoundingMode.HALF_UP);
    }

    private static void setEnteroOpcional(CallableStatement stm, int indice, Integer valor)
            throws SQLException {
        if (valor == null) {
            stm.setNull(indice, Types.INTEGER);
        } else {
            stm.setInt(indice, valor);
        }
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
        return new SpResponseModel(500, "No fue posible " + operacion
                + " el gasto: " + ex.getMessage());
    }
}
