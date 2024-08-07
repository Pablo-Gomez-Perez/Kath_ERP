package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Ventas;
import com.kathsoft.kathpos.tools.Conexion;

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
	 * Consulta todos los registros de ventas en la base de datos y los ordena de acuerdo a la
	 * opción seleccionada
	 * 
	 * @param opcion
	 * @param sucursal
	 * @return 
	 */
	public Vector<Object[]> verVentasEnTabla(int opcion, int sucursal) {

		CallableStatement stm = null;
		ResultSet rset = null;
		var data = new Vector<Object[]>();

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
						(rset.getShort(9) == 1) ? "Vigente" : "Cancelada", };

				data.add(fila);
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	
	public Vector<Object[]> buscarVentasPor(String datoBuscado, int opcion) {

		CallableStatement stm = null;
		ResultSet rset = null;
		var data = new Vector<Object[]>();
		
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
						(rset.getShort(9) == 1) ? "Vigente" : "Cancelada" };

				data.add(fila);
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}
	
	
	public int buscarUltimaVenta() {
		
		CallableStatement stm = null;
		ResultSet rset = null;
		int valor = 0;
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL buscar_ultima_venta();");
			rset = stm.executeQuery();
			
			if(rset.next()) {
				valor = rset.getInt(1);
			}
			
			return valor;
			
		}catch(SQLException er) {
			er.printStackTrace();
			return 0;
		}catch(Exception er) {
			er.printStackTrace();
			return 0;
		}
	}
	
	
	public void insertarNuevaVenta(Ventas venta) throws SQLException {
		
		CallableStatement stm = null;		
		
		try {
			
			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL insert_nueva_venta(?,?,?,?,?,?,?,?,?);");
			
			stm.setInt(1, venta.getIdSucursal());
			stm.setDate(2, venta.getFechaVenta());
			stm.setBoolean(3, venta.isVentaContado());
			stm.setInt(4, venta.getEmpleado().getId());
			stm.setInt(5, venta.getCliente().getId());
			stm.setDouble(6, venta.getSubTotal());
			stm.setDouble(7, venta.getIva());
			stm.setDouble(8, venta.getTotal());
			stm.setBoolean(9, venta.isStatusVenta());
			
			stm.execute();
			
			
		}catch(SQLException er) {
			er.printStackTrace();
		}catch(Exception er) {
			er.printStackTrace();
		}
		
	}
	
}
































//
