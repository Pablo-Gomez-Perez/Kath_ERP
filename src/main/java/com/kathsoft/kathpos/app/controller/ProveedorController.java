package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import com.kathsoft.kathpos.app.model.proveedor.Proveedor;
import com.kathsoft.kathpos.app.model.proveedor.ProveedorById;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class ProveedorController implements java.io.Serializable {

	private static final long serialVersionUID = -5820147766336769662L;
	private static Connection cn = null;

	public Vector<Object[]> verProveedoresEnTabla(String nombre) {

		var data = new Vector<Object[]>();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listProveedores(?);")
		) {

			stm.setString("p_nombre_proveedor", nombre);

			try (ResultSet rset = stm.executeQuery()) {

				while (rset.next()) {
					data.add(new Object[] {
							rset.getInt("id_proveedor"),
							rset.getString("rfc"),
							rset.getString("clave"),
							rset.getString("nombre"),
							rset.getString("descripcion"),
							rset.getString("correo_electronico"),
							rset.getString("estado"),
							rset.getString("ciudad"),
							rset.getString("direccion"),
							rset.getString("codigo_postal"),
							rset.getShort("activo") == 1 ? "Activo" : "Inactivo"
					});
				}

			}

		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}

		return data;
	}	

	public SpResponseModel eliminarProveedor(int idProveedor) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL deleteProveedor(?);")
		) {

			stm.setInt("idProveedor", idProveedor);

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}

	}

	public void consultarRFCProveedor(JComboBox<String> cmb) {

		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_rfcProveedores();");
			rset = stm.executeQuery();

			while (rset.next()) {
				cmb.addItem(rset.getString(1));
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
			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public Vector<JComboboxDataViewModel> consultarNombresProveedor() {
		
		var data = new Vector<JComboboxDataViewModel>();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
			stm = cn.prepareCall("CALL listCmbProveeodor();");
			rset = stm.executeQuery();

			while (rset.next()) {
				data.add(new JComboboxDataViewModel(
						rset.getInt("id"),
						rset.getString("nombre")
				));
			}
			
			return data;
		} catch (SQLException er) {
			er.printStackTrace();
			return data;
		} catch (Exception er) {
			er.printStackTrace();
			return data;
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

	public SpResponseModel insertarNuevoProveedor(Proveedor prv) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL insertProveedor(?, ?, ?, ?, ?, ?, ?, ?, ?);")
		) {

			stm.setInt("p_id_cuenta_contable", prv.getIdCuentaContable());
			stm.setString("p_rfc", prv.getRfc());
			stm.setString("p_nombre", prv.getNombre());
			stm.setString("p_descripcion", prv.getDescripcion());
			stm.setString("p_correo_electronico", prv.getCorreoElectronico());
			stm.setString("p_estado", prv.getEstado());
			stm.setString("p_ciudad", prv.getCiudad());
			stm.setString("p_direccion", prv.getDireccion());
			stm.setString("p_codigo_postal", prv.getCodigoPostal());

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}

	}

	public SpResponseModel actualizarProveedor(Proveedor prv) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL updateProveedor(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")
		) {

			stm.setInt("p_id_proveedor", prv.getIdProveedor());
			stm.setInt("p_id_cuenta_contable", prv.getIdCuentaContable());
			stm.setString("p_rfc", prv.getRfc());
			stm.setString("p_nombre", prv.getNombre());
			stm.setString("p_descripcion", prv.getDescripcion());
			stm.setString("p_correo_electronico", prv.getCorreoElectronico());
			stm.setString("p_estado", prv.getEstado());
			stm.setString("p_ciudad", prv.getCiudad());
			stm.setString("p_direccion", prv.getDireccion());
			stm.setString("p_codigo_postal", prv.getCodigoPostal());
			stm.setBoolean("p_activo", true);

			if (stm.execute()) {

				try (ResultSet rset = stm.getResultSet()) {

					if (rset != null && rset.next()) {
						return new SpResponseModel(
								rset.getInt("id"),
								rset.getString("message")
						);
					}

				}

			}

			return new SpResponseModel(500, "Ocurrio un error desconocido");

		} catch (SQLException er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		} catch (Exception er) {
			er.printStackTrace(System.err);
			return new SpResponseModel(500, er.getMessage());
		}

	}

	public Proveedor buscarProveedorPorRFC(String rfc) throws Exception, SQLException {

		Proveedor prv = new Proveedor();
		CallableStatement stm = null;
		ResultSet rset = null;

		try {

			cn = Conexion.establecerConexionLocal("kath_erp");
			stm = cn.prepareCall("CALL ver_proveedor_por_rfc(?);");
			stm.setString(1, rfc);

			rset = stm.executeQuery();

			if (rset.next()) {
				prv.setIdProveedor(rset.getInt(1));
				prv.setIdCuentaContable(rset.getInt(2));				
				prv.setNombre(rset.getString(4));
				prv.setDescripcion(rset.getString(5));
				prv.setCorreoElectronico(rset.getString(6));
				prv.setEstado(rset.getString(7));
				prv.setCiudad(rset.getString(8));
				prv.setDireccion(rset.getString(9));
				prv.setCodigoPostal(rset.getString(10));
			}

			return prv;

		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		} finally {
			try {

				Conexion.cerrarConexion(cn, rset, stm);

			} catch (Exception er) {
				er.printStackTrace();
			}
		}

	}

	public ProveedorById buscarProveedorPorId(int idProveedor) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL getProveedorById(?);")
		) {

			stm.setInt("idProveedor", idProveedor);

			try (ResultSet rset = stm.executeQuery()) {

				if (rset.next()) {
					return new ProveedorById.ProveedorByIdBuilder()
							.idProveedor(rset.getInt("id_proveedor"))
							.idCuentaContable(rset.getInt("id_cuenta_contable"))
							.rfc(rset.getString("rfc"))
							.claveCuentaContable(rset.getString("clave"))
							.nombre(rset.getString("nombre"))
							.descripcion(rset.getString("descripcion"))
							.correoElectronico(rset.getString("correo_electronico"))
							.estado(rset.getString("estado"))
							.ciudad(rset.getString("ciudad"))
							.direccion(rset.getString("direccion"))
							.codigoPostal(rset.getString("codigo_postal"))
							.activo(rset.getBoolean("activo"))
							.build();
				}

			}

		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}

		return null;
	}
}
