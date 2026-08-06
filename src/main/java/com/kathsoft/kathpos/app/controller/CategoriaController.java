package com.kathsoft.kathpos.app.controller;

import java.io.Serializable;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import com.kathsoft.kathpos.app.model.categoria.Categoria;
import com.kathsoft.kathpos.app.model.viewmodel.JComboboxDataViewModel;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

public class CategoriaController implements Serializable {

	private static final long serialVersionUID = 6835247986143695345L;

	public Vector<Object[]> verCategoriasEnTabla(String nombre) {

		var data = new Vector<Object[]>();

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listCategoriaProducto(?);")
		) {

			stm.setString("p_nombre_categoria", nombre);

			try (ResultSet rset = stm.executeQuery()) {

				while (rset.next()) {
					data.add(new Object[] {
							rset.getInt("id_categoria"),
							rset.getString("nombre"),
							rset.getString("descripcion"),
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

	public Vector<JComboboxDataViewModel> obtenerIndicesDeCategorias() {

		var categorias = new Vector<JComboboxDataViewModel>();
		
		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listCmbCategoriaProducto();");
				ResultSet rset = stm.executeQuery()
		) {

			while (rset.next()) {
				categorias.add(new JComboboxDataViewModel(
						rset.getInt("id_categoria"),
						rset.getString("nombre")
				));
			}
			
		} catch (SQLException er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		} catch (Exception er) {
			er.printStackTrace();
			JOptionPane.showMessageDialog(null, er.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}

		return categorias;
	}

	public void buscarCategoriaPorNombre(String nombre, DefaultTableModel model) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL listCategoriaProducto(?);")
		) {

			stm.setString("p_nombre_categoria", nombre);

			try (ResultSet rset = stm.executeQuery()) {

				while (rset.next()) {
					model.addRow(new Object[] {
							rset.getInt("id_categoria"),
							rset.getString("nombre"),
							rset.getString("descripcion"),
							rset.getShort("activo") == 1 ? "Activo" : "Inactivo"
					});
				}

			}

		} catch (SQLException er) {
			er.printStackTrace(System.err);
		} catch (Exception er) {
			er.printStackTrace(System.err);
		}
	}

	public SpResponseModel insertarNuevaCategoria(Categoria categoria) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL insertCategoriaProducto(?, ?);")
		) {

			stm.setString("p_nombre", categoria.getNombre());
			stm.setString("p_descripcion", categoria.getDescripcion());

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

	public SpResponseModel actualizarCategoria(Categoria categoria) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL updateCategoriaProducto(?, ?, ?, ?);")
		) {

			stm.setInt("p_id_categoria", categoria.getIdCategoria());
			stm.setString("p_nombre", categoria.getNombre());
			stm.setString("p_descripcion", categoria.getDescripcion());
			stm.setBoolean("p_activo", categoria.isActivo());

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

	public SpResponseModel eliminarCategoria(int id) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL deleteCategoriaProducto(?);")
		) {

			stm.setInt("p_id_categoria", id);

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

	public Categoria buscarCategoriaPorId(int id) {

		try (
				Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL getCategoriaById(?);")
		) {

			stm.setInt("p_id_categoria", id);

			try (ResultSet rset = stm.executeQuery()) {

				if (rset.next()) {
					var categoria = new Categoria();
					categoria.setIdCategoria(rset.getInt("id_categoria"));
					categoria.setNombre(rset.getString("nombre"));
					categoria.setDescripcion(rset.getString("descripcion"));
					categoria.setActivo(rset.getBoolean("activo"));
					return categoria;
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
