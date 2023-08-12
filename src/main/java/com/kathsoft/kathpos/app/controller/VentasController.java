package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

public class VentasController implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5495779444539458190L;
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private static Connection cn = null;

	public void verVentasEnTabla(DefaultTableModel tabla) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_ventas();");
			rset = stm.executeQuery();

			while (rset.next()) {
				Object[] fila = { rset.getInt(1), // folio o id de venta
						rset.getDate(2), // Fecha de la venta
						(rset.getShort(3) == 1) ? "Contado" : "Credito", // venta a credito o de contado
						rset.getString(4), // nombre del empleado
						rset.getString(5), // nombre del cliente
						rset.getDouble(6), // subtotal
						rset.getDouble(7), // IVA
						rset.getDouble(8), // Importe Total de la venta
						(rset.getShort(9) == 1) ? "Vigente" : "Cancelada" };

				tabla.addRow(fila);
			}

		} catch (SQLException er) {
			er.printStackTrace();
		} catch (Exception er) {
			er.printStackTrace();
		} finally {
			try {

				if (rset != null) {
					rset.close();
				}
				if (stm != null) {
					stm.close();
				}
				if (cn != null) {
					cn.close();
				}

			} catch (SQLException er) {
				er.printStackTrace();
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

}
