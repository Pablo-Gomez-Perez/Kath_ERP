package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.Ventas;
import com.kathsoft.kathpos.app.model.venta.ArticuloPorVenta;
import com.kathsoft.kathpos.app.model.venta.ArticuloVentaListado;
import com.kathsoft.kathpos.app.model.venta.ArticuloVentaRegistrado;
import com.kathsoft.kathpos.app.model.venta.PagoPorVenta;
import com.kathsoft.kathpos.app.model.venta.Venta;
import com.kathsoft.kathpos.app.model.venta.VentaById;
import com.kathsoft.kathpos.app.model.venta.VentaConDetalle;
import com.kathsoft.kathpos.app.model.venta.VentaDetalleConsulta;
import com.kathsoft.kathpos.app.model.venta.VentaFiltro;
import com.kathsoft.kathpos.app.model.venta.VentaFinalizada;
import com.kathsoft.kathpos.app.model.venta.VentaListado;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class VentasController implements java.io.Serializable {

    private static final long serialVersionUID = -5495779444539458190L;
    private static final int ERROR = 500;

    public List<VentaListado> listVentas(long idSucursal) {
        return listVentas(idSucursal, new VentaFiltro());
    }

    public List<VentaListado> listVentas(long idSucursal, VentaFiltro filtro) {
        VentaFiltro f = filtro == null ? new VentaFiltro() : filtro;
        List<VentaListado> ventas = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listVentas(?,?,?,?,?,?)")) {
            stm.setLong(1, idSucursal);
            stm.setString(2, f.getTipoBusqueda());
            stm.setString(3, f.getTextoBusqueda());
            stm.setString(4, f.getOrdenarPor());
            stm.setDate(5, f.getFechaInicial());
            stm.setDate(6, f.getFechaFinal());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    ventas.add(mapVentaListado(rs));
                }
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return ventas;
    }

    public VentaDetalleConsulta getVentaById(int idVenta) {
        if (idVenta <= 0) {
            return null;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL getVentaById(?)")) {
            stm.setInt(1, idVenta);

            if (!stm.execute()) {
                return null;
            }

            VentaById cabecera;
            try (ResultSet rs = stm.getResultSet()) {
                if (rs == null || !rs.next()) {
                    return null;
                }
                cabecera = mapVentaById(rs);
            }

            List<ArticuloVentaListado> articulos = new ArrayList<>();
            if (stm.getMoreResults()) {
                try (ResultSet rs = stm.getResultSet()) {
                    while (rs != null && rs.next()) {
                        articulos.add(mapArticuloVentaListado(rs));
                    }
                }
            }
            return new VentaDetalleConsulta(cabecera, articulos);
        } catch (Exception er) {
            er.printStackTrace();
            return null;
        }
    }

    public SpResponseModel insertVenta(Venta venta) {
        SpResponseModel error = validarCabecera(venta);
        if (error != null) {
            return error;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            return insertVenta(cn, venta);
        } catch (Exception er) {
            er.printStackTrace();
            return error(er);
        }
    }

    public ArticuloVentaRegistrado insertArticuloVenta(ArticuloPorVenta articulo) {
        SpResponseModel error = validarArticulo(articulo, true);
        if (error != null) {
            return errorArticulo(error.message());
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            return insertArticuloVenta(cn, articulo);
        } catch (Exception er) {
            er.printStackTrace();
            return errorArticulo(er.getMessage());
        }
    }

    public SpResponseModel restarExistenciaSucursalVenta(int idDetalleVenta) {
        if (idDetalleVenta <= 0) {
            return new SpResponseModel(ERROR, "El detalle de venta es obligatorio");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            return restarExistenciaSucursalVenta(cn, idDetalleVenta);
        } catch (Exception er) {
            er.printStackTrace();
            return error(er);
        }
    }

    public SpResponseModel insertPagoVenta(PagoPorVenta pago) {
        SpResponseModel error = validarPago(pago, true);
        if (error != null) {
            return error;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            return insertPagoVenta(cn, pago);
        } catch (Exception er) {
            er.printStackTrace();
            return error(er);
        }
    }

    public VentaFinalizada finalizarVenta(int idVenta) {
        if (idVenta <= 0) {
            return errorFinalizacion("La venta es obligatoria");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            return finalizarVenta(cn, idVenta);
        } catch (Exception er) {
            er.printStackTrace();
            return errorFinalizacion(er.getMessage());
        }
    }

    public SpResponseModel insertVenta(VentaConDetalle ventaConDetalle) {
        SpResponseModel validacion = validarVentaCompleta(ventaConDetalle);
        if (validacion != null) {
            return validacion;
        }

        List<ArticuloPorVenta> articulos = ventaConDetalle.getArticulos();
        List<PagoPorVenta> pagos = ventaConDetalle.getPagos() == null
                ? Collections.emptyList()
                : ventaConDetalle.getPagos();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            boolean autoCommit = cn.getAutoCommit();
            cn.setAutoCommit(false);

            try {
                Venta venta = ventaConDetalle.getVenta();
                SpResponseModel cabecera = insertVenta(cn, venta);
                if (esError(cabecera)) {
                    cn.rollback();
                    return cabecera;
                }

                int idVenta = cabecera.id();
                venta.setIdVenta(idVenta);
                venta.setStatusVenta(true);

                for (ArticuloPorVenta articulo : articulos) {
                    articulo.setIdVenta(idVenta);
                    ArticuloVentaRegistrado detalle = insertArticuloVenta(cn, articulo);
                    if (esError(detalle.getId(), detalle.getMessage())) {
                        cn.rollback();
                        return new SpResponseModel(detalle.getId(), detalle.getMessage());
                    }

                    articulo.setId(detalle.getId());
                    articulo.setSubtotal(detalle.getSubtotal());

                    SpResponseModel existencia = restarExistenciaSucursalVenta(cn, detalle.getId());
                    if (esError(existencia)) {
                        cn.rollback();
                        return existencia;
                    }
                }

                for (PagoPorVenta pago : pagos) {
                    pago.setIdVenta(idVenta);
                    SpResponseModel respuestaPago = insertPagoVenta(cn, pago);
                    if (esError(respuestaPago)) {
                        cn.rollback();
                        return respuestaPago;
                    }
                    pago.setId(respuestaPago.id());
                }

                VentaFinalizada finalizada = finalizarVenta(cn, idVenta);
                if (esError(finalizada.getId(), finalizada.getMessage())) {
                    cn.rollback();
                    return new SpResponseModel(finalizada.getId(), finalizada.getMessage());
                }

                venta.setSubtotal(finalizada.getSubtotal());
                venta.setIva(finalizada.getIva());
                venta.setImporteTotal(finalizada.getTotal());
                venta.setTipoVenta("CONTADO".equalsIgnoreCase(finalizada.getTipoVenta()));

                cn.commit();
                return new SpResponseModel(finalizada.getId(), finalizada.getMessage());
            } catch (Exception er) {
                cn.rollback();
                er.printStackTrace();
                return error(er);
            } finally {
                cn.setAutoCommit(autoCommit);
            }
        } catch (Exception er) {
            er.printStackTrace();
            return error(er);
        }
    }

    public SpResponseModel cancelVenta(int idVenta) {
        if (idVenta <= 0) {
            return new SpResponseModel(ERROR, "La venta es obligatoria");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {
            boolean autoCommit = cn.getAutoCommit();
            cn.setAutoCommit(false);
            try {
                SpResponseModel respuesta = cancelVenta(cn, idVenta);
                if (esError(respuesta)) {
                    cn.rollback();
                    return respuesta;
                }
                cn.commit();
                return respuesta;
            } catch (Exception er) {
                cn.rollback();
                er.printStackTrace();
                return error(er);
            } finally {
                cn.setAutoCommit(autoCommit);
            }
        } catch (Exception er) {
            er.printStackTrace();
            return error(er);
        }
    }

    private SpResponseModel insertVenta(Connection cn, Venta venta) throws SQLException {
        try (CallableStatement stm = cn.prepareCall("CALL insertVenta(?,?,?,?)")) {
            stm.setInt(1, venta.getIdEmpleado());
            stm.setInt(2, venta.getIdCliente());
            stm.setLong(3, venta.getIdSucursal());
            stm.setDate(4, venta.getFecha());
            return ejecutarRespuestaSimple(stm);
        }
    }

    private ArticuloVentaRegistrado insertArticuloVenta(Connection cn, ArticuloPorVenta articulo) throws SQLException {
        try (CallableStatement stm = cn.prepareCall("CALL insertArticuloVenta(?,?,?)")) {
            stm.setInt(1, articulo.getIdVenta());
            stm.setInt(2, articulo.getIdArticulo());
            stm.setInt(3, articulo.getCantidad());
            return ejecutarArticuloRegistrado(stm);
        }
    }

    private SpResponseModel restarExistenciaSucursalVenta(Connection cn, int idDetalleVenta) throws SQLException {
        try (CallableStatement stm = cn.prepareCall("CALL restarExistenciaSucursalVenta(?)")) {
            stm.setInt(1, idDetalleVenta);
            return ejecutarRespuestaSimple(stm);
        }
    }

    private SpResponseModel insertPagoVenta(Connection cn, PagoPorVenta pago) throws SQLException {
        try (CallableStatement stm = cn.prepareCall("CALL insertPagoVenta(?,?,?)")) {
            stm.setInt(1, pago.getIdVenta());
            stm.setInt(2, pago.getIdFormaPago());
            stm.setDouble(3, pago.getImporte());
            return ejecutarRespuestaSimple(stm);
        }
    }

    private VentaFinalizada finalizarVenta(Connection cn, int idVenta) throws SQLException {
        try (CallableStatement stm = cn.prepareCall("CALL finalizarVenta(?)")) {
            stm.setInt(1, idVenta);
            return ejecutarVentaFinalizada(stm);
        }
    }

    private SpResponseModel cancelVenta(Connection cn, int idVenta) throws SQLException {
        try (CallableStatement stm = cn.prepareCall("CALL cancelVenta(?)")) {
            stm.setInt(1, idVenta);
            return ejecutarRespuestaSimple(stm);
        }
    }

    private SpResponseModel ejecutarRespuestaSimple(CallableStatement stm) throws SQLException {
        boolean resultado = stm.execute();
        while (true) {
            if (resultado) {
                try (ResultSet rs = stm.getResultSet()) {
                    if (rs != null && tieneColumnas(rs, "id", "message")) {
                        return construirRespuesta(rs);
                    }
                }
            } else if (stm.getUpdateCount() == -1) {
                break;
            }
            resultado = stm.getMoreResults();
        }
        return new SpResponseModel(ERROR, "El procedimiento almacenado no devolvio respuesta");
    }

    private ArticuloVentaRegistrado ejecutarArticuloRegistrado(CallableStatement stm) throws SQLException {
        boolean resultado = stm.execute();
        while (true) {
            if (resultado) {
                try (ResultSet rs = stm.getResultSet()) {
                    if (rs != null && tieneColumnas(rs, "id", "message")) {
                        if (!rs.next()) {
                            break;
                        }
                        int id = rs.getInt("id");
                        String message = rs.getString("message");
                        ArticuloVentaRegistrado.ArticuloVentaRegistradoBuilder b =
                                new ArticuloVentaRegistrado.ArticuloVentaRegistradoBuilder()
                                        .id(id).message(message);
                        if (!esError(id, message)) {
                            b.precioUnitario(rs.getDouble("precio_unitario"))
                                    .subtotal(rs.getDouble("subtotal"))
                                    .iva(rs.getDouble("iva"))
                                    .total(rs.getDouble("total"));
                        }
                        return b.build();
                    }
                }
            } else if (stm.getUpdateCount() == -1) {
                break;
            }
            resultado = stm.getMoreResults();
        }
        return errorArticulo("El procedimiento almacenado no devolvio respuesta");
    }

    private VentaFinalizada ejecutarVentaFinalizada(CallableStatement stm) throws SQLException {
        boolean resultado = stm.execute();
        while (true) {
            if (resultado) {
                try (ResultSet rs = stm.getResultSet()) {
                    if (rs != null && tieneColumnas(rs, "id", "message")) {
                        if (!rs.next()) {
                            break;
                        }
                        int id = rs.getInt("id");
                        String message = rs.getString("message");
                        VentaFinalizada.VentaFinalizadaBuilder b = new VentaFinalizada.VentaFinalizadaBuilder()
                                .id(id).message(message);
                        if (!esError(id, message)) {
                            b.subtotal(rs.getDouble("subtotal"))
                                    .iva(rs.getDouble("iva"))
                                    .total(rs.getDouble("total"))
                                    .pagos(rs.getDouble("pagos"))
                                    .tipoVenta(rs.getString("tipo_venta"));
                        }
                        return b.build();
                    }
                }
            } else if (stm.getUpdateCount() == -1) {
                break;
            }
            resultado = stm.getMoreResults();
        }
        return errorFinalizacion("El procedimiento almacenado no devolvio respuesta");
    }

    private boolean tieneColumnas(ResultSet rs, String... columnas) {
        try {
            for (String columna : columnas) {
                rs.findColumn(columna);
            }
            return true;
        } catch (SQLException er) {
            return false;
        }
    }

    private SpResponseModel construirRespuesta(ResultSet rs) throws SQLException {
        return rs.next()
                ? new SpResponseModel(rs.getInt("id"), rs.getString("message"))
                : new SpResponseModel(ERROR, "El procedimiento almacenado no devolvio respuesta");
    }

    private VentaListado mapVentaListado(ResultSet rs) throws SQLException {
        return new VentaListado.VentaListadoBuilder()
                .folio(rs.getInt("folio")).fecha(rs.getDate("fecha"))
                .tipo(rs.getString("tipo")).atendio(rs.getString("atendio"))
                .cliente(rs.getString("cliente")).subtotal(rs.getDouble("subtotal"))
                .iva(rs.getDouble("iva")).total(rs.getDouble("total"))
                .vigente(rs.getString("vigente")).build();
    }

    private VentaById mapVentaById(ResultSet rs) throws SQLException {
        return new VentaById.VentaByIdBuilder()
                .idVenta(rs.getInt("id_venta")).idEmpleado(rs.getInt("id_empleado"))
                .nombreEmpleado(rs.getString("nombre_empleado"))
                .nombreCortoEmpleado(rs.getString("nombre_corto_empleado"))
                .idCliente(rs.getInt("id_cliente")).nombreCliente(rs.getString("nombre_cliente"))
                .nombreCortoCliente(rs.getString("nombre_corto_cliente"))
                .idSucursal(rs.getLong("id_sucursal")).fecha(rs.getDate("fecha"))
                .tipoVenta(rs.getBoolean("tipo_venta"))
                .tipoVentaDescripcion(rs.getString("tipo_venta_descripcion"))
                .subtotal(rs.getDouble("subtotal")).iva(rs.getDouble("iva"))
                .importeTotal(rs.getDouble("importe_total"))
                .statusVenta(rs.getBoolean("status_venta"))
                .statusVentaDescripcion(rs.getString("status_venta_descripcion")).build();
    }

    private ArticuloVentaListado mapArticuloVentaListado(ResultSet rs) throws SQLException {
        return new ArticuloVentaListado.ArticuloVentaListadoBuilder()
                .id(rs.getInt("id")).idVenta(rs.getInt("id_venta"))
                .idArticulo(rs.getInt("id_articulo")).codigoArticulo(rs.getString("codigo_articulo"))
                .codigoSat(rs.getString("codigo_sat")).unidadSat(rs.getString("unidad_sat"))
                .nombre(rs.getString("nombre")).descripcion(rs.getString("descripcion"))
                .exento(rs.getBoolean("es_exento")).cantidad(rs.getInt("cantidad"))
                .subtotal(rs.getDouble("subtotal")).iva(rs.getDouble("iva"))
                .total(rs.getDouble("total")).build();
    }

    private SpResponseModel validarCabecera(Venta venta) {
        if (venta == null) return new SpResponseModel(ERROR, "La venta es obligatoria");
        if (venta.getIdEmpleado() <= 0) return new SpResponseModel(ERROR, "El empleado es obligatorio");
        if (venta.getIdCliente() <= 0) return new SpResponseModel(ERROR, "El cliente es obligatorio");
        if (venta.getIdSucursal() <= 0) return new SpResponseModel(ERROR, "La sucursal es obligatoria");
        if (venta.getFecha() == null) return new SpResponseModel(ERROR, "La fecha de venta es obligatoria");
        return null;
    }

    private SpResponseModel validarArticulo(ArticuloPorVenta articulo, boolean requiereVenta) {
        if (articulo == null) return new SpResponseModel(ERROR, "El articulo es obligatorio");
        if (requiereVenta && articulo.getIdVenta() <= 0) return new SpResponseModel(ERROR, "La venta es obligatoria");
        if (articulo.getIdArticulo() <= 0) return new SpResponseModel(ERROR, "El articulo es obligatorio");
        if (articulo.getCantidad() <= 0) return new SpResponseModel(ERROR, "La cantidad debe ser mayor a cero");
        return null;
    }

    private SpResponseModel validarPago(PagoPorVenta pago, boolean requiereVenta) {
        if (pago == null) return new SpResponseModel(ERROR, "El pago es obligatorio");
        if (requiereVenta && pago.getIdVenta() <= 0) return new SpResponseModel(ERROR, "La venta es obligatoria");
        if (pago.getIdFormaPago() <= 0) return new SpResponseModel(ERROR, "La forma de pago es obligatoria");
        if (pago.getImporte() <= 0) return new SpResponseModel(ERROR, "El importe del pago debe ser mayor a cero");
        return null;
    }

    private SpResponseModel validarVentaCompleta(VentaConDetalle venta) {
        if (venta == null) return new SpResponseModel(ERROR, "La venta es obligatoria");

        SpResponseModel error = validarCabecera(venta.getVenta());
        if (error != null) return error;

        List<ArticuloPorVenta> articulos = venta.getArticulos();
        if (articulos == null || articulos.isEmpty()) {
            return new SpResponseModel(ERROR, "La venta debe contener al menos un articulo");
        }

        Set<Integer> ids = new HashSet<>();
        for (ArticuloPorVenta articulo : articulos) {
            error = validarArticulo(articulo, false);
            if (error != null) return error;
            if (!ids.add(articulo.getIdArticulo())) {
                return new SpResponseModel(ERROR, "No se puede repetir un articulo en la misma venta");
            }
        }

        if (venta.getPagos() != null) {
            for (PagoPorVenta pago : venta.getPagos()) {
                error = validarPago(pago, false);
                if (error != null) return error;
            }
        }
        return null;
    }

    private boolean esError(SpResponseModel respuesta) {
        return respuesta == null || esError(respuesta.id(), respuesta.message());
    }

    private boolean esError(int id, String message) {
        if (id != ERROR) return false;
        if (message == null) return true;

        // Un id autoincremental valido puede ser exactamente 500.
        return !message.toLowerCase(Locale.ROOT).contains("correct");
    }

    private SpResponseModel error(Throwable er) {
        return new SpResponseModel(ERROR, er == null ? "Error no identificado" : er.getMessage());
    }

    private ArticuloVentaRegistrado errorArticulo(String message) {
        return new ArticuloVentaRegistrado.ArticuloVentaRegistradoBuilder()
                .id(ERROR).message(message).build();
    }

    private VentaFinalizada errorFinalizacion(String message) {
        return new VentaFinalizada.VentaFinalizadaBuilder()
                .id(ERROR).message(message).build();
    }

    @Deprecated
    public Vector<Object[]> verVentasEnTabla(int opcion, int sucursal) {
        String orden = switch (opcion) {
        case 2 -> "EMPLEADO";
        case 3 -> "CLIENTE";
        case 4 -> "TIPO";
        case 5 -> "STATUS";
        default -> "FECHA";
        };

        VentaFiltro filtro = new VentaFiltro.VentaFiltroBuilder()
                .tipoBusqueda("TODOS").ordenarPor(orden).build();

        Vector<Object[]> datos = new Vector<>();
        listVentas(sucursal, filtro).forEach(v -> datos.add(new Object[] {
                v.getFolio(), v.getFecha(), v.getTipo(), v.getAtendio(), v.getCliente(),
                v.getSubtotal(), v.getIva(), v.getTotal(), v.getVigente()
        }));
        return datos;
    }

    @Deprecated
    public Vector<Object[]> buscarVentasPor(String datoBuscado, int opcion) {
        throw new IllegalStateException(
                "buscarVentasPor es obsoleto: use listVentas(idSucursal, VentaFiltro)");
    }

    @Deprecated
    public int buscarUltimaVenta() {
        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL buscar_ultima_venta()");
                ResultSet rs = stm.executeQuery()) {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (Exception er) {
            er.printStackTrace();
            return 0;
        }
    }

    @Deprecated
    public void insertarNuevaVenta(Ventas venta) throws SQLException {
        throw new SQLException(
                "insertarNuevaVenta(Ventas) es obsoleto: use insertVenta(VentaConDetalle)");
    }
}
