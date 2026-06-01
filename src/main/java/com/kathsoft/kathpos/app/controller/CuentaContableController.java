package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.CuentaContable;
import com.kathsoft.kathpos.app.model.viewmodel.CuentaContableFormDetails;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
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
				data.add(new Object[] { rset.getString("id_cuenta"), rset.getString("clave"), rset.getString("nombre"),
						rset.getString("cuenta_padre"), rset.getString("rubro"), rset.getShort("nivel"),
						rset.getBoolean("ultimo_nivel") == true ? "Si" : "No", rset.getDouble("Cargo"),
						rset.getDouble("Abono"), rset.getDouble("saldo"),
						rset.getBoolean("activa") == true ? "Si" : "No" });
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
	public CuentaContableFormDetails buscarCuentaPorClave(String clave) {
				

		try (var cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {

			var stm = cn.prepareCall("CALL buscar_cuenta_x_clave(?);");
			stm.setString(1, clave);
			var rset = stm.executeQuery();

			if (rset.next()) {
				
				return new CuentaContableFormDetails(
						rset.getInt("id_cuenta"),
						rset.getInt("id_cuenta_padre"),
						rset.getInt("fk_id_rubro"),
						rset.getInt("fk_id_grupo_contable"),
						rset.getString("clave"),
						rset.getString("nombre"),
						rset.getString("descripcion"),
						rset.getShort("nivel"),
						rset.getBoolean("ultimo_nivel")
						);						
			}

			return null;
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
				//data.setNombreCuentaPadre(rset.getString(2));
				//data.setRubroCuenta(rset.getString(3));
				data.setNombre(rset.getString(4));
				data.setDescripcion(rset.getString(5));
				data.setNivel(rset.getShort(6));
				data.setUltimoNivel(rset.getShort(7) == 1 ? true : false);
				data.setCargo(rset.getDouble(8));
				data.setAbono(rset.getDouble(9));
				//data.setSaldo(rset.getDouble(10));
				//data.setNaturaleza(rset.getShort(11) == 1 ? true : false);
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

	/**
	 * Obtener un listado completo de todos los rubros contables registrados
	 * 
	 * @param idGrupoContable
	 * @return
	 */
	public List<JComboboxDataViewModel> listCmbRubroCuentasContables(int idGrupoContable) {

		List<JComboboxDataViewModel> data = new Vector<JComboboxDataViewModel>();

		try (var cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {

			var stm = cn.prepareCall("CALL list_cmbRubroCuentasContables(?)");
			stm.setInt("id_grupo_contable", idGrupoContable);
			var rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new JComboboxDataViewModel(rset.getInt("id_rubro"), rset.getString("nombre")));
			}

			return data;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return data;
		}

	}

	/**
	 * Lista los grupos contables para cargar en un JCombobox
	 * 
	 * @return Lista de grupos contables
	 */
	public List<JComboboxDataViewModel> listCmbGrupoCuentasContables() {

		List<JComboboxDataViewModel> data = new Vector<JComboboxDataViewModel>();

		try (var cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {

			var stm = cn.prepareCall("CALL list_cmbGrupoContable()");
			var rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new JComboboxDataViewModel(rset.getInt("id_grupo"), rset.getString("nombre_grupo")));
			}

			return data;

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return data;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return data;
		}

	}

}
