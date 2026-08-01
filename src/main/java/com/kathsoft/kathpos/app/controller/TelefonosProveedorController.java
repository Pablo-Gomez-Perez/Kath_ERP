package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.proveedor.TelefonoProveedor;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Controlador para gestionar los telefonos asociados a proveedores.
 *
 * @author PABLO
 */
public class TelefonosProveedorController implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Lista los telefonos asociados a un proveedor.
	 *
	 * @param idProveedor identificador del proveedor
	 * @return vector con los telefonos encontrados; nunca retorna {@code null}
	 */
	public Vector<Object[]> listTelefonosProveedor(int idProveedor) {

		var data = new Vector<Object[]>();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listTelefonoProveedor(?);")
		) {

			stm.setInt("p_id_proveedor", idProveedor);

			try (ResultSet rset = stm.executeQuery()) {

				while (rset.next()) {
					data.add(new Object[] {
							rset.getInt("id_telefono"),
							rset.getString("telefono")
					});
				}

			}

		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}

		return data;
	}

	/**
	 * Registra un telefono asociado a un proveedor.
	 *
	 * @param data datos del telefono a registrar
	 * @return respuesta estandarizada del procedimiento almacenado
	 */
	public SpResponseModel insertTelefonoProveedor(TelefonoProveedor data) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL insertTelefonoProveedor(?, ?);")
		) {

			stm.setInt("p_id_proveedor", data.getIdProveedor());
			stm.setString("p_telefono", data.getTelefono());

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}
	}

	/**
	 * Elimina un telefono asociado a un proveedor.
	 *
	 * @param idTelefono identificador del telefono
	 * @return respuesta estandarizada del procedimiento almacenado
	 */
	public SpResponseModel deleteTelefonoProveedor(int idTelefono) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL deleteTelefonoProveedor(?);")
		) {

			stm.setInt("p_id_telefono", idTelefono);

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}
	}
}
