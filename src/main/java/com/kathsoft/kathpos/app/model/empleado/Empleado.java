/**
 * 
 */
package com.kathsoft.kathpos.app.model.empleado;

import java.sql.Date;

import com.kathsoft.kathpos.app.model.Usuario;

/**
 * @author PABLO
 *
 */
public class Empleado {

	private int idEmpleado;
	private int idCuentaContable;
	private int idSucursal;
	private String rfc;
	private String curp;
	private String nombreCompleto;
	private String nombreCorto;
	private Date fechaNac;
	private String correoElectronico;
	private String estado;
	private String ciudad;
	private String direccion;
	private String codigoPostal;
	private String contrasenia;
	private boolean activo;
	
	public static class EmpleadoBuilder {

		private final Empleado empleado;

		public EmpleadoBuilder() {
			this.empleado = new Empleado();
		}

		public EmpleadoBuilder idEmpleado(int idEmpleado) {
			this.empleado.idEmpleado = idEmpleado;
			return this;
		}

		public EmpleadoBuilder idCuentaContable(int idCuentaContable) {
			this.empleado.idCuentaContable = idCuentaContable;
			return this;
		}

		public EmpleadoBuilder idSucursal(int idSucursal) {
			this.empleado.idSucursal = idSucursal;
			return this;
		}

		public EmpleadoBuilder rfc(String rfc) {
			this.empleado.rfc = rfc;
			return this;
		}

		public EmpleadoBuilder curp(String curp) {
			this.empleado.curp = curp;
			return this;
		}

		public EmpleadoBuilder nombreCompleto(String nombreCompleto) {
			this.empleado.nombreCompleto = nombreCompleto;
			return this;
		}

		public EmpleadoBuilder nombreCorto(String nombreCorto) {
			this.empleado.nombreCorto = nombreCorto;
			return this;
		}

		public EmpleadoBuilder fechaNac(Date fechaNac) {
			this.empleado.fechaNac = fechaNac;
			return this;
		}

		public EmpleadoBuilder correoElectronico(String correoElectronico) {
			this.empleado.correoElectronico = correoElectronico;
			return this;
		}

		public EmpleadoBuilder estado(String estado) {
			this.empleado.estado = estado;
			return this;
		}

		public EmpleadoBuilder ciudad(String ciudad) {
			this.empleado.ciudad = ciudad;
			return this;
		}

		public EmpleadoBuilder direccion(String direccion) {
			this.empleado.direccion = direccion;
			return this;
		}

		public EmpleadoBuilder codigoPostal(String codigoPostal) {
			this.empleado.codigoPostal = codigoPostal;
			return this;
		}

		public EmpleadoBuilder contrasenia(String contrasenia) {
			this.empleado.contrasenia = contrasenia;
			return this;
		}

		public EmpleadoBuilder activo(boolean activo) {
			this.empleado.activo = activo;
			return this;
		}

		public Empleado build() {
			return this.empleado;
		}
	}
	
	public Empleado(int idEmpleado, int idCuentaContable, int idSucursal, String rfc, String curp,
			String nombreCompleto, String nombreCorto, Date fechaNac, String correoElectronico, String estado,
			String ciudad, String direccion, String codigoPostal, String contrasenia, boolean activo) {
		super();
		this.idEmpleado = idEmpleado;
		this.idCuentaContable = idCuentaContable;
		this.idSucursal = idSucursal;
		this.rfc = rfc;
		this.curp = curp;
		this.nombreCompleto = nombreCompleto;
		this.nombreCorto = nombreCorto;
		this.fechaNac = fechaNac;
		this.correoElectronico = correoElectronico;
		this.estado = estado;
		this.ciudad = ciudad;
		this.direccion = direccion;
		this.codigoPostal = codigoPostal;
		this.contrasenia = contrasenia;
		this.activo = activo;
	}

	public Empleado() {
	}

	public int getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public int getIdCuentaContable() {
		return idCuentaContable;
	}

	public void setIdCuentaContable(int idCuentaContable) {
		this.idCuentaContable = idCuentaContable;
	}

	public int getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(int idSucursal) {
		this.idSucursal = idSucursal;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Empleado [idEmpleado=").append(idEmpleado).append(", idCuentaContable=")
				.append(idCuentaContable).append(", idSucursal=").append(idSucursal).append(", rfc=").append(rfc)
				.append(", curp=").append(curp).append(", nombreCompleto=").append(nombreCompleto)
				.append(", nombreCorto=").append(nombreCorto).append(", fechaNac=").append(fechaNac)
				.append(", correoElectronico=").append(correoElectronico).append(", estado=").append(estado)
				.append(", ciudad=").append(ciudad).append(", direccion=").append(direccion).append(", codigoPostal=")
				.append(codigoPostal).append(", contrasenia=").append(contrasenia).append(", activo=").append(activo)
				.append("]");
		return builder.toString();
	}

}
