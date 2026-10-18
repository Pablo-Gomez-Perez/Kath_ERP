package com.kathsoft.kathpos.app.controller;

import java.io.Serial;
import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.model.inventario.CategoriaSalidaInventario;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * CRUD del catálogo de categorías utilizadas por las salidas de inventario.
 * Todas las operaciones persistentes se realizan mediante procedimientos
 * almacenados.
 */
public class CategoriaSalidaInventarioController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public List<CategoriaSalidaInventario> listarCategorias(String nombre)
            throws SQLException {

        List<CategoriaSalidaInventario> categorias = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL listCategoriaSalidasInventario(?)")) {

            if (nombre == null || nombre.trim().isEmpty()) {
                stm.setNull(1, Types.VARCHAR);
            } else {
                stm.setString(1, nombre.trim());
            }

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    categorias.add(mapearCategoria(rs));
                }
            }
        }

        return categorias;
    }

    public SpResponseModel crearCategoria(CategoriaSalidaInventario categoria) {
        SpResponseModel validacion = validarCategoria(categoria, false);
        if (validacion != null) {
            return validacion;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL insertCategoriaSalidaInventario(?,?)")) {

            stm.setString(1, categoria.getNombre().trim());
            setDescripcion(stm, 2, categoria.getDescripcion());

            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorPersistencia("crear", ex);
        }
    }

    public SpResponseModel actualizarCategoria(CategoriaSalidaInventario categoria) {
        SpResponseModel validacion = validarCategoria(categoria, true);
        if (validacion != null) {
            return validacion;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL updateCategoriaSalidaInventario(?,?,?)")) {

            stm.setInt(1, categoria.getIdCategoria());
            stm.setString(2, categoria.getNombre().trim());
            setDescripcion(stm, 3, categoria.getDescripcion());

            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorPersistencia("actualizar", ex);
        }
    }

    public CategoriaSalidaInventario getCategoriaSalidaInventarioById(
            int idCategoria) throws SQLException {

        if (idCategoria <= 0) {
            throw new IllegalArgumentException(
                    "El identificador de la categoría debe ser positivo");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL getCategoriaSalidaInventarioById(?)")) {

            stm.setInt(1, idCategoria);

            try (ResultSet rs = stm.executeQuery()) {
                return rs.next() ? mapearCategoria(rs) : null;
            }
        }
    }

    public SpResponseModel eliminarCategoria(int idCategoria) {
        if (idCategoria <= 0) {
            return new SpResponseModel(
                    500,
                    "Seleccione una categoría de salida válida para eliminar");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall(
                        "CALL deleteCategoriaSalidaInventario(?)")) {

            stm.setInt(1, idCategoria);
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorPersistencia("eliminar", ex);
        }
    }

    private static CategoriaSalidaInventario mapearCategoria(ResultSet rs)
            throws SQLException {

        return new CategoriaSalidaInventario(
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getBoolean("activo"));
    }

    private static void setDescripcion(
            CallableStatement stm,
            int indice,
            String descripcion) throws SQLException {

        if (descripcion == null || descripcion.trim().isEmpty()) {
            stm.setNull(indice, Types.VARCHAR);
        } else {
            stm.setString(indice, descripcion.trim());
        }
    }

    private static SpResponseModel validarCategoria(
            CategoriaSalidaInventario categoria,
            boolean actualizacion) {

        if (categoria == null) {
            return new SpResponseModel(
                    500,
                    "Debe indicar una categoría de salida de inventario");
        }

        if (actualizacion && categoria.getIdCategoria() <= 0) {
            return new SpResponseModel(
                    500,
                    "El identificador de la categoría es obligatorio");
        }

        try {
            categoria.validarParaGuardar();
            return null;
        } catch (IllegalArgumentException ex) {
            return new SpResponseModel(500, ex.getMessage());
        }
    }

    private static SpResponseModel leerRespuesta(CallableStatement stm)
            throws SQLException {

        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return new SpResponseModel(
                        rs.getInt("id"),
                        rs.getString("message"));
            }
        }

        return new SpResponseModel(
                500,
                "El procedimiento no devolvió ninguna respuesta");
    }

    private static SpResponseModel errorPersistencia(
            String operacion,
            SQLException ex) {

        ex.printStackTrace(System.err);

        return new SpResponseModel(
                500,
                "No fue posible " + operacion
                        + " la categoría de salida: "
                        + ex.getMessage());
    }
}
