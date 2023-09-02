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

	/**
	 * Busca el todas las ventas registradas en la bd y las muestra en un Jtable
	 * ordenadas de acuerdo a la opción que se le pase como parámetro. Esta opción
	 * dependerá del JRadioButton que esté seleccionado
	 * 
	 * @param tabla  -> tabla en la que será mostrada la información
	 * @param opcion -> opción de ordenamiento
	 */
	public void verVentasEnTabla(DefaultTableModel tabla, int opcion, int sucursal) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_ventas(?,?);");

			stm.setInt(1, opcion);
			stm.setInt(2, sucursal);

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
						(rset.getShort(9) == 1) ? "Vigente" : "Cancelada",
				};

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

	/**
	 * Buscar el conjunto de ventas en la base de datos en función de la opción
	 * seleccionada, la opción de busqueda dependerá del JRadioButton que esté
	 * seleccionado en el momento de llamar a la función
	 * 
	 * @param tabla       -> tabla en la que se mostrarán los datos
	 * @param datoBuscado -> cadena de texto que se buscará en la bd
	 * @param opcion      -> opción de busqueda de la base de datos
	 */
	public void buscarVentasPor(DefaultTableModel tabla, String datoBuscado, int opcion) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_ventas_por(?,?);");

			stm.setString(1, datoBuscado);
			stm.setInt(2, opcion);

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
						(rset.getShort(9) == 1) ? "Vigente" : "Cancelada"};

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
