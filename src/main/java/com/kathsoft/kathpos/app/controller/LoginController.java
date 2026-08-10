package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kathsoft.kathpos.app.model.empleado.Empleado;
import com.kathsoft.kathpos.app.model.empleado.EmpleadoLogin;
import com.kathsoft.kathpos.tools.Conexion;
import com.kathsoft.kathpos.tools.PasswordHashService;

public class LoginController implements java.io.Serializable {

	private static final long serialVersionUID = -2632747518110861235L;

	public Empleado iniciarSesion(String nombreCorto, char[] contrasenia) throws SQLException, Exception {
		if (nombreCorto == null || nombreCorto.isBlank()) {
			throw new IllegalArgumentException("Debe capturar el usuario");
		}

		if (contrasenia == null || contrasenia.length == 0) {
			throw new IllegalArgumentException("Debe capturar la contraseña");
		}

		EmpleadoLogin empleadoLogin = this.consultarEmpleadoLogin(nombreCorto.trim());
		if (empleadoLogin == null || empleadoLogin.getIdEmpleado() <= 0) {
			return null;
		}

		if (!PasswordHashService.verifyPassword(contrasenia, empleadoLogin.getContraseniaHash())) {
			return null;
		}

		return empleadoLogin.toEmpleado();
	}

	public boolean validarCredenciales(String nombreCorto, char[] contrasenia) throws SQLException, Exception {
		return this.iniciarSesion(nombreCorto, contrasenia) != null;
	}

	private EmpleadoLogin consultarEmpleadoLogin(String nombreCorto) throws SQLException, Exception {
		try (Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL getEmpleadoLogin(?);")) {
			stm.setString(1, nombreCorto);

			try (ResultSet rset = stm.executeQuery()) {
				if (rset.next()) {
					EmpleadoLogin empleado = new EmpleadoLogin();
					empleado.setIdEmpleado(rset.getInt("id_empleado"));
					empleado.setIdCuentaContable(rset.getInt("id_cuenta_contable"));
					empleado.setIdSucursal(rset.getInt("id_sucursal"));
					empleado.setNombreSucursal(rset.getString("nombre_sucursal"));
					empleado.setRfc(rset.getString("rfc"));
					empleado.setCurp(rset.getString("curp"));
					empleado.setNombreCompleto(rset.getString("nombre_completo"));
					empleado.setNombreCorto(rset.getString("nombre_corto"));
					empleado.setFechaNac(rset.getDate("fecha_nac"));
					empleado.setCorreoElectronico(rset.getString("correo_electronico"));
					empleado.setEstado(rset.getString("estado"));
					empleado.setCiudad(rset.getString("ciudad"));
					empleado.setDireccion(rset.getString("direccion"));
					empleado.setCodigoPostal(rset.getString("codigo_postal"));
					empleado.setContraseniaHash(rset.getString("contrasenia_hash"));
					empleado.setActivo(rset.getBoolean("activo"));
					return empleado;
				}
			}
		}

		return null;
	}
}
