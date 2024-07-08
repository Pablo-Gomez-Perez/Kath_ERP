package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.CuentaContable;
import com.kathsoft.kathpos.tools.Conexion;

public class CuentaContableController {

	private static Connection cn;

	public CuentaContableController() {

	}

	public Vector<Object[]> verCuentasContables(String cuenta) {
		var data = new Vector<Object[]>();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL ver_cuentas_contables(?)");
			stm.setString(1, cuenta);
			rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new Object[] { rset.getInt(1), // id_cuenta
						rset.getString(2), // Nombre de la cuenta contable superior
						rset.getString(3), // clave de la cuenta contable
						rset.getString(4), // Nombre cuenta
						rset.getString(5), // descripcion
						rset.getShort(6), // nivel
						rset.getBoolean(7) ? "Si" : "No", rset.getDouble(8), // cargos
						rset.getDouble(9), // abonos
						rset.getDouble(10) // saldo de la cuenta
				});
			}

			return data;

		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	/**
	 * 
	 * @param clave -> Clave de la cuenta contable
	 * @return {@code new CuentaContable()}
	 */
	public CuentaContable buscarCuentaPorClave(String clave) {
		var data = new CuentaContable();

		try (var cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {

			var stm = cn.prepareCall("CALL buscar_cuenta_x_clave(?);");
			stm.setString(1, clave);
			var rset = stm.executeQuery();

			if (rset.next())
				data.setIdCuenta(rset.getInt(1));
			data.setIdCuentaPadre(rset.getInt(1));
			data.setClave(rset.getString(3));
			data.setNombre(rset.getString(4));
			data.setDescripcion(rset.getString(5));
			data.setNivel(rset.getShort(6));
			data.setUltimoNivel(rset.getShort(7) == 1 ? true : false);
			data.setNaturaleza(rset.getShort(8) == 1 ? true : false);

			return data;
		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param idCuenta -> Indice de la cuenta contable
	 * @return {@code new CuentaContable()}
	 */
	public CuentaContable buscarCuentaPorId(int idCuenta) {
		var data = new CuentaContable();

		try (var cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {

			var stm = cn.prepareCall("CALL buscar_cuenta_x_id(?)");
			stm.setInt(1, idCuenta);
			var rset = stm.executeQuery();

			if (rset.next()) {
				data.setIdCuenta(rset.getInt(1));
				data.setNombreCuentaPadre(rset.getString(2));
				data.setRubroCuenta(rset.getString(3));
				data.setNombre(rset.getString(4));
				data.setDescripcion(rset.getString(5));
				data.setNivel(rset.getShort(6));
				data.setUltimoNivel(rset.getShort(7) == 1 ? true : false);
				data.setCargo(rset.getDouble(8));
				data.setAbono(rset.getDouble(9));
				data.setSaldo(rset.getDouble(10));
				data.setNaturaleza(rset.getShort(11) == 1 ? true : false);
			}

			return data;
		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		}
	}

}
