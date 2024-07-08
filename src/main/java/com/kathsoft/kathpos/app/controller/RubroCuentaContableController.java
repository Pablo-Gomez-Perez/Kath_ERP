package com.kathsoft.kathpos.app.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kathsoft.kathpos.app.model.RubroCuentaContable;
import com.kathsoft.kathpos.tools.Conexion;

public class RubroCuentaContableController {

	public List<RubroCuentaContable> cmbRubroCuentasContables() {
		var data = new ArrayList<RubroCuentaContable>();

		try (var cn = Conexion.establecerConexionLocal(Conexion.DATA_BASE)) {

			var stm = cn.prepareCall("CALL ver_cmbRubroCuentasContables();");
			var rset = stm.executeQuery();

			while (rset.next()) {

				var item = new RubroCuentaContable();
				item.setIdRubro(rset.getInt(1));
				item.setNombre(rset.getString(2));
				item.setDescripcion(rset.getString(3));
				item.setNaturaleza(rset.getShort(4) == 1 ? true : false);

				data.add(item);
			}

			return data;
		} catch (SQLException er) {
			er.printStackTrace();
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return null;
		}
	}

}
