package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class TelefonoClienteController {
	
	public TelefonoClienteController() {}
	
	
	/**
	 * Lista los teléfonos asociados a un cliente.
	 *
	 * @param idCliente identificador del cliente
	 * @return vector con los teléfonos encontrados; nunca retorna {@code null}
	 */
	public Vector<Object[]> listTelefonosCliente(int idCliente) {

		var data = new Vector<Object[]>();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listTelefonosCliente(?);")
		) {

			stm.setInt("p_id_cliente", idCliente);

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
	 * Registra un teléfono asociado a un cliente.
	 *
	 * @param idCliente identificador del cliente
	 * @param telefono número telefónico a registrar
	 * @return respuesta estandarizada del procedimiento almacenado
	 */
	public SpResponseModel insertTelefonoCliente(int idCliente, String telefono) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL insertTelefonoCliente(?, ?);")
		) {

			stm.setInt("p_id_cliente", idCliente);
			stm.setString("p_telefono", telefono);

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
	 * Elimina un teléfono asociado a un cliente.
	 *
	 * @param idTelefono identificador del teléfono
	 * @return respuesta estandarizada del procedimiento almacenado
	 */
	public SpResponseModel deleteTelefonoCliente(int idTelefono) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL deleteTelefonoCliente(?);")
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
