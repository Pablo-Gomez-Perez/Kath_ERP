package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import com.kathsoft.kathpos.tools.Conexion;

public class TelefonoEmpleadoController {

	public TelefonoEmpleadoController() {
	};

	public Vector<Object[]> listTelefonoPorIdEmpleado(int idEmpleado) {

		var data = new Vector<Object[]>();

		try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listTelefonosDeEmpleadoByID(?)");) {

			stm.setInt("id_empleado", idEmpleado);
			ResultSet rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new Object[] { rset.getInt("id_telefono"), rset.getString("telefono") });
			}

			return data;

		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}

	}

}
