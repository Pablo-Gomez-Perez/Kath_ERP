package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.FormasDePago;
import com.kathsoft.kathpos.tools.Conexion;

public class FormasDePagoController implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4738796646362834472L;
	/**
	 * 
	 * 
	 * 
	 */

	private static Connection cn = null;

	public FormasDePagoController() {

	}

	/**
	 * 
	 * @param tabla
	 */
	public void verFormasDePagoEnTabla(DefaultTableModel tabla) {

		ResultSet rset = null;
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_formas_de_pago();");

			rset = stm.executeQuery();

			while (rset.next()) {
				tabla.addRow(new Object[] { rset.getInt(1), rset.getString(2),
						rset.getShort(3) == 1 ? "Activo" : "Inactivo" });
			}

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, rset, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}

	}

	public Vector<Object[]> verFormasDePagoEnTablaVentas() {
		ResultSet rset = null;
		CallableStatement stm = null;
		var data = new Vector<Object[]>();

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_formas_de_pago();");

			rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new Object[] { rset.getInt(1), rset.getString(2) });
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
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param formaDePago
	 */
	public void insertarFormaDePago(FormasDePago formaDePago) {

		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL insert_forma_de_pago(?,?);");
			stm.setString("forma_pago", formaDePago.getTipoDePago());
			stm.setBoolean("p_es_flujo_efectivo", formaDePago.isEsFlujoEfectivo());
			stm.execute();

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}

	}

	public void actualizarFormaDePago(FormasDePago formaDePago) {
		
		CallableStatement stm = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL update_forma_de_pago(?,?,?);");
			stm.setInt("id_forma_pago", formaDePago.getId());
			stm.setString("forma_pago", formaDePago.getTipoDePago());
			stm.setBoolean("p_es_flujo_efectivo", formaDePago.isEsFlujoEfectivo());
			stm.execute();

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {
				Conexion.cerrarConexion(cn, stm);
			} catch (SQLException er) {
				er.printStackTrace();
			}
		}
	}

	public void eliminarFormaDepAgo(int idFormaDePago) throws SQLException {

		CallableStatement stm = null;

		cn = Conexion.establecerConexionLocal("kath_erp");
		stm = cn.prepareCall("CALL eliminar_forma_pago(?);");
		stm.setInt("idFormaPago", idFormaDePago);

		stm.execute();

		Conexion.cerrarConexion(cn, stm);

	}

	public FormasDePago consultarFormaDePagoPorId(int id) {

		ResultSet rset = null;
		CallableStatement stm = null;
		FormasDePago fpago = new FormasDePago();

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL bucar_forma_pago_por_id(?);");
			stm.setInt("idFormaDePago", id);			

			rset = stm.executeQuery();

			if (rset.next()) {
				fpago.setId(rset.getInt("id"));
				fpago.setEsFlujoEfectivo(rset.getBoolean("es_flujo_efectivo"));
				fpago.setTipoDePago(rset.getString("tipo_de_pago"));
				fpago.setEstaActivo(rset.getBoolean("activo"));
			}

			return fpago;

		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		}

	}

}
