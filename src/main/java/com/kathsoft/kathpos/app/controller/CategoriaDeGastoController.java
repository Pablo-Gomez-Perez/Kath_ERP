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
import java.util.Vector;

import com.kathsoft.kathpos.app.model.gastos.CategoriaDeGasto;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Operaciones del catálogo de categorías de gasto.
 *
 * <p>Todas las operaciones de negocio se realizan mediante procedimientos
 * almacenados. Requiere instalar manualmente los seis SP definidos para
 * este módulo antes de ejecutar las operaciones contra una base real.</p>
 */
public class CategoriaDeGastoController implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Crea una categoría activa. El procedimiento asigna {@code ACTIVO = TRUE}.
     *
     * @param categoria categoría a registrar
     * @return {@code id = 200} si se registró; {@code id = 500} en otro caso
     */
    public SpResponseModel crearCategoria(CategoriaDeGasto categoria) {
        SpResponseModel error = validarCategoria(categoria, false);
        if (error != null) {
            return error;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL insertCategoriaDeGasto(?, ?)")) {
            stm.setString(1, categoria.getNombre().trim());
            setDescripcion(stm, 2, categoria.getDescripcion());
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorPersistencia("crear", ex);
        }
    }

    /**
     * Actualiza y activa la categoría indicada; no recibe estado del formulario.
     *
     * @param categoria categoría con ID válido y campos modificados
     * @return respuesta del procedimiento almacenado
     */
    public SpResponseModel actualizarCategoria(CategoriaDeGasto categoria) {
        SpResponseModel error = validarCategoria(categoria, true);
        if (error != null) {
            return error;
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL updateCategoriaDeGasto(?, ?, ?)")) {
            stm.setInt(1, categoria.getIdCategoria());
            stm.setString(2, categoria.getNombre().trim());
            setDescripcion(stm, 3, categoria.getDescripcion());
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorPersistencia("actualizar", ex);
        }
    }

    /**
     * Inhabilita una categoría sin eliminar sus asociaciones históricas.
     *
     * @param idCategoria identificador de la categoría
     * @return respuesta de la baja lógica
     */
    public SpResponseModel eliminarCategoria(int idCategoria) {
        if (idCategoria <= 0) {
            return new SpResponseModel(500, "Seleccione una categoría válida para eliminar");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL deleteCategoriaDeGasto(?)")) {
            stm.setInt(1, idCategoria);
            return leerRespuesta(stm);
        } catch (SQLException ex) {
            return errorPersistencia("eliminar", ex);
        }
    }

    /**
     * Lista todas las categorías, incluidas las inactivas, filtrando por nombre.
     * Una búsqueda nula o vacía devuelve todas las categorías.
     *
     * @param nombre texto opcional a buscar
     * @return categorías completas ordenadas por nombre
     * @throws SQLException si falla la consulta
     */
    public List<CategoriaDeGasto> listarCategorias(String nombre) throws SQLException {
        List<CategoriaDeGasto> categorias = new ArrayList<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listCategoriasDeGasto(?)")) {
            stm.setString(1, nombre == null ? null : nombre.trim());

            try (ResultSet rs = stm.executeQuery()) {
                while (rs.next()) {
                    categorias.add(mapearCategoria(rs));
                }
            }
        }

        return categorias;
    }

    /**
     * Proyección para el futuro panel de categorías de gasto.
     *
     * @param nombre filtro por nombre; puede estar vacío
     * @return filas en orden ID, nombre, descripción, estado
     * @throws SQLException si no es posible recuperar el listado
     */
    public Vector<Object[]> verCategoriasEnTabla(String nombre) throws SQLException {
        Vector<Object[]> filas = new Vector<>();
        for (CategoriaDeGasto categoria : listarCategorias(nombre)) {
            filas.add(new Object[] {
                    categoria.getIdCategoria(),
                    categoria.getNombre(),
                    categoria.getDescripcion(),
                    categoria.isActivo() ? "Activo" : "Inactivo"
            });
        }
        return filas;
    }

    /**
     * Consulta una categoría independientemente de que esté activa o inactiva.
     *
     * @param idCategoria ID a consultar
     * @return categoría encontrada o {@code null} si no existe
     * @throws SQLException ante fallos de conexión o procedimiento
     */
    public CategoriaDeGasto getCategoriaGastoById(int idCategoria) throws SQLException {
        if (idCategoria <= 0) {
            throw new IllegalArgumentException("El identificador de la categoría debe ser positivo");
        }

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL getCategoriaGastoById(?)")) {
            stm.setInt(1, idCategoria);
            try (ResultSet rs = stm.executeQuery()) {
                return rs.next() ? mapearCategoria(rs) : null;
            }
        }
    }

    /**
     * Obtiene exclusivamente categorías activas para el ComboBox de gastos.
     * El contrato SQL del procedimiento es {@code id, nombre}.
     *
     * @return opciones activas con ID y nombre
     * @throws SQLException si no es posible consultar las opciones
     */
    public Vector<JComboboxDataViewModel> listCmbCategoriaDeGasto() throws SQLException {
        Vector<JComboboxDataViewModel> opciones = new Vector<>();

        try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
                CallableStatement stm = cn.prepareCall("CALL listCmbCategoriaDeGasto()");
                ResultSet rs = stm.executeQuery()) {
            while (rs.next()) {
                opciones.add(new JComboboxDataViewModel(
                        rs.getInt("id"),
                        rs.getString("nombre")));
            }
        }

        return opciones;
    }

    private static CategoriaDeGasto mapearCategoria(ResultSet rs) throws SQLException {
        return new CategoriaDeGasto(
                rs.getInt("id_categoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"),
                rs.getBoolean("activo"));
    }

    private static void setDescripcion(CallableStatement stm, int indice, String descripcion)
            throws SQLException {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            stm.setNull(indice, Types.VARCHAR);
        } else {
            stm.setString(indice, descripcion.trim());
        }
    }

    private static SpResponseModel validarCategoria(CategoriaDeGasto categoria, boolean actualizacion) {
        if (categoria == null) {
            return new SpResponseModel(500, "Debe indicar una categoría de gasto");
        }
        if (actualizacion && categoria.getIdCategoria() <= 0) {
            return new SpResponseModel(500, "El identificador de la categoría es obligatorio");
        }

        try {
            categoria.validarParaGuardar();
            return null;
        } catch (IllegalArgumentException ex) {
            return new SpResponseModel(500, ex.getMessage());
        }
    }

    private static SpResponseModel leerRespuesta(CallableStatement stm) throws SQLException {
        try (ResultSet rs = stm.executeQuery()) {
            if (rs.next()) {
                return new SpResponseModel(rs.getInt("id"), rs.getString("message"));
            }
        }
        return new SpResponseModel(500, "El procedimiento no devolvió ninguna respuesta");
    }

    private static SpResponseModel errorPersistencia(String operacion, SQLException ex) {
        ex.printStackTrace(System.err);
        return new SpResponseModel(500,
                "No fue posible " + operacion + " la categoría: " + ex.getMessage());
    }
}
