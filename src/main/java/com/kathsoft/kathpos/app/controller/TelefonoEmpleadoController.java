package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.telefono_x_empleado.TelefonoEmpleado;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
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
	
	public SpResponseModel createTelefonoEmpleado(TelefonoEmpleado telefonoEmpleado) {
		
		try(Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL insertTelefonoEmpleado(?,?)");){
			
			stm.setInt("p_id_empleado", telefonoEmpleado.getIdEmpleado());
			stm.setString("p_telefono_empleado", telefonoEmpleado.getTelefono());
			
			ResultSet rset = stm.executeQuery();
			
			if(rset.next()) {
				return new SpResponseModel(rset.getInt("id"),rset.getString("message"));
			}
			
			return new SpResponseModel(500, "Ocurrio un error desconocido");
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new SpResponseModel(500, e.getMessage());
		}
		
		
	}

}
