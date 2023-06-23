package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.Empleado;
import java.sql.CallableStatement;

public class EmpleadoController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1598821464656008533L;
	/**
	 * 
	 * 
	 */
	private Empleado empleado;
	private static Connection cn = null;
	
	public EmpleadoController(Empleado empleado) {
		this.empleado = empleado;
	}
	
	public EmpleadoController() {
		
	}
	
	
	
	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * metodo para validar el ingreso al sistema, hace uso de un procedimiento almacenado
	 * para validar si el usuario existe y sis sus credenciales de acceso son correctas
	 * @return {@code false} si el server arroja un error
	 * @return {@code true} si el server no arroja nada
	 */
	public boolean validarIngreso() {
		
		String pswd = "";
		
		System.out.println(this.empleado.getNombreCorto());
		System.out.println(this.empleado.getPassword());
		
		try {
			
			cn = Conexion.establecerConexionLocal("kath_erp");
			CallableStatement stm = cn.prepareCall("CALL validar_entrada(?,?)");
			stm.setString(1,this.empleado.getNombreCorto());
			stm.setString(2, this.empleado.getPassword());
			ResultSet rset = stm.executeQuery();
			
			if(rset.next()) {
				pswd = rset.getString(1);
				System.out.println(pswd);
			}
			
			System.out.println(this.empleado.getPassword());
			
			return true;
			
		}catch(SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}catch(Exception er){
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	/**
	 * Consulta una vista almacenada en la base de datos que retorna unicamente el campo
	 * nombre_corto de la tabla empleados, y estos datos son cargados al combobox que se
	 * le pase como parámetro
	 * @param jcmb
	 */
	public void consultaNombresCortosEmpleados(JComboBox<String> jcmb) {
		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			Statement stm = cn.createStatement();
			ResultSet rset = stm.executeQuery("SELECT * FROM empleados_nombre_costo"); 
			
			while(rset.next()) {
				jcmb.addItem(rset.getString(1));
			}
			
		}catch (SQLException er) {
			er.printStackTrace();
		}catch (Exception er) {
			er.printStackTrace();
		}
	}
	
	/**
	 * consulta una vista almacenada en la base de datos que retorna unicamente el campo
	 * RFC de la tabla de mpleados, estos datos son cargados al combobox que se le pasa
	 * como parámetro
	 * @param jcmb
	 */
	public void consultarRfcEmpleado(JComboBox<String> jcmb) {
		try {
			cn = Conexion.establecerConexionLocal("kath_erp");
			Statement stm = cn.createStatement();
			ResultSet rset = stm.executeQuery("SELECT * FROM vw_rfcempleados"); 
			
			while(rset.next()) {
				jcmb.addItem(rset.getString(1));
			}
			
		}catch (SQLException er) {
			er.printStackTrace();
		}catch (Exception er) {
			er.printStackTrace();
		}
	}
	
	public Empleado consultarEmpleadoPorRfc(String rfc) {
		Empleado empl = new Empleado();
		CallableStatement stm = null;
		ResultSet rset = null;
		try {
			
			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL sp_consultarEmpleadoPorRFC(?)");
			stm.setString(1, rfc);
			
			rset = stm.executeQuery();
			
			if(rset.next()) {
				empl.setCurp(rset.getString(1));
				empl.setNombre(rset.getString(2));
				empl.setNombreCorto(rset.getString(3));
				empl.setFechaNacimiento(rset.getDate(4));
				empl.setEmail(rset.getString(5));
				empl.setEstado(rset.getString(6));
				empl.setCiudad(rset.getString(7));
				empl.setDireccion(rset.getString(8));
				empl.setCodigoPostal(rset.getString(9));
				empl.setPassword(rset.getString(10));
			}
			return empl;
		}catch(SQLException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}catch(Exception er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}finally {
			try {
				if(cn != null) {
					cn.close();
				}
				if(stm != null) {
					stm.close();
				}
				if(rset != null) {
					rset.close();
				}
			}catch(SQLException er) {
				er.printStackTrace();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void verEmpleadosEnTabla(DefaultTableModel tabla) {
		ResultSet rset = null;
		CallableStatement stm = null;
		
		try {
			cn = Conexion.establecerConexionLocal("Kath_erp");
			stm = cn.prepareCall("CALL spGetListadoEmpleados");
			rset = stm.executeQuery();
			
			while(rset.next()) {
				Object[] fila = {
					rset.getInt(1),			//id
					rset.getString(2),		//rfc
					rset.getString(3),		//curp
					rset.getString(4),		//nombre completo
					rset.getString(5),		//nombre corto
					rset.getDate(6),		//fecha nacimiento
					rset.getString(7),		//correo
					rset.getString(8),		//estado
					rset.getString(9),		//ciudad
					rset.getString(10),		//direccion
					rset.getString(11)		//codigo postal
				};
				tabla.addRow(fila);
			}
		}catch(SQLException er) {
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			er.printStackTrace();
		}
	}
	
}
