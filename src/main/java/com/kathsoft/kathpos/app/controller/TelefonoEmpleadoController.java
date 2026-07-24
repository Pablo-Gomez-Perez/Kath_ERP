package com.kathsoft.kathpos.app.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import com.kathsoft.kathpos.app.model.telefono_x_empleado.TelefonoEmpleado;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;
import com.kathsoft.kathpos.tools.Conexion;

/**
 * Controlador responsable de gestionar las operaciones relacionadas con los
 * números telefónicos asociados a los empleados.
 *
 * <p>
 * Esta clase proporciona métodos para consultar y registrar números
 * telefónicos mediante procedimientos almacenados ejecutados sobre la base de
 * datos local configurada en {@link Conexion}.
 * </p>
 *
 * <p>
 * Las respuestas de las operaciones de escritura se encapsulan mediante
 * {@link SpResponseModel}.
 * </p>
 *
 * @author Pablo Gómez Pérez
 * @since 1.0
 */
public class TelefonoEmpleadoController {

	public TelefonoEmpleadoController() {
	};

	/**
	 * Consulta los números telefónicos asociados a un empleado.
	 *
	 * <p>
	 * El método ejecuta el procedimiento almacenado
	 * {@code listTelefonosDeEmpleadoByID} y transforma cada registro obtenido en
	 * un arreglo de objetos con la siguiente estructura:
	 * </p>
	 *
	 * <ol>
	 * 	<li>Identificador del teléfono.</li>
	 * 	<li>Número telefónico.</li>
	 * </ol>
	 *
	 * <p>
	 * Si ocurre un error durante la consulta, se imprime la traza de la excepción
	 * y se retorna el vector con los registros recuperados hasta ese momento. Si
	 * no se recuperó ningún registro, el vector retornado estará vacío.
	 * </p>
	 *
	 * @param idEmpleado identificador único del empleado cuyos teléfonos se
	 *                   desean consultar
	 * @return un {@link Vector} de arreglos {@link Object} que contiene el
	 *         identificador y el número de cada teléfono asociado al empleado;
	 *         nunca retorna {@code null}
	 *
	 * @see Vector
	 * @see Object
	 */
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
	
	/**
	 * Registra un número telefónico asociado a un empleado.
	 *
	 * <p>
	 * El método ejecuta el procedimiento almacenado
	 * {@code insertTelefonoEmpleado}, enviando el identificador del empleado y
	 * el número telefónico contenidos en el objeto recibido.
	 * </p>
	 *
	 * <p>
	 * El procedimiento almacenado debe retornar un conjunto de resultados con
	 * las columnas {@code id} y {@code message}. Estos valores se utilizan para
	 * construir el objeto {@link SpResponseModel}.
	 * </p>
	 *
	 * <p>
	 * Si el procedimiento no retorna ningún registro, se genera una respuesta
	 * con código {@code 500}. Si ocurre una excepción, también se retorna una
	 * respuesta con código {@code 500} y el mensaje proporcionado por la
	 * excepción.
	 * </p>
	 *
	 * @param telefonoEmpleado objeto que contiene el identificador del empleado
	 *                         y el número telefónico que se desea registrar
	 * @return una instancia de {@link SpResponseModel} con el identificador y el
	 *         mensaje retornados por el procedimiento almacenado; en caso de
	 *         error, retorna una respuesta con código {@code 500}
	 *
	 * @throws NullPointerException si {@code telefonoEmpleado} es {@code null}
	 *
	 * @see TelefonoEmpleado
	 * @see SpResponseModel
	 */
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
	
	/**
	 * Elimina un número telefónico asociado a un empleado.
	 *
	 * <p>
	 * El método ejecuta el procedimiento almacenado
	 * {@code deleteTelefonoEmpleado}, el cual valida previamente la existencia del
	 * número telefónico antes de eliminarlo de la tabla
	 * {@code telefono_x_empleado}.
	 * </p>
	 *
	 * <p>
	 * El procedimiento almacenado retorna un conjunto de resultados con las
	 * columnas {@code id} y {@code message}, las cuales son utilizadas para
	 * construir el objeto {@link SpResponseModel} que representa el resultado de
	 * la operación.
	 * </p>
	 *
	 * <p>
	 * Si el procedimiento no retorna ningún registro, se genera una respuesta con
	 * código {@code 500}. En caso de producirse una excepción durante la ejecución,
	 * la traza del error es enviada a la salida de error estándar y se retorna un
	 * {@link SpResponseModel} con código {@code 500} y el mensaje de la excepción.
	 * </p>
	 *
	 * @param idTelefono identificador único del número telefónico que se desea
	 *                   eliminar
	 * @return una instancia de {@link SpResponseModel} que contiene el código y el
	 *         mensaje devueltos por el procedimiento almacenado; en caso de error,
	 *         retorna una respuesta con código {@code 500}
	 *
	 * @see SpResponseModel
	 * @see TelefonoEmpleado
	 */
	public SpResponseModel deleteTelefonoEmpleado(int idTelefono) {
		
		try(Connection cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE);
				CallableStatement stm = cn.prepareCall("CALL deleteTelefonoEmpleado(?)")){
			
			stm.setInt("p_id_telefono", idTelefono);
			
			try(ResultSet rset = stm.executeQuery()){
				
				if(rset.next()) {
					return new SpResponseModel(rset.getInt("id"), rset.getString("message"));
				}
				
			}
			
			return new SpResponseModel(500, "Ocurrio un error desconocido");
			
		}catch (Exception e) {
			
			e.printStackTrace(System.err);
			return new SpResponseModel(500, e.getMessage());
		}
		
	}

}
