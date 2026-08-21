/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE actualizarPassWordEmpleado(IN rfcEmpl VARCHAR(13), IN passwordE VARCHAR(15))
BEGIN
	UPDATE empleados
    SET empleados.contrasenia = passwordE WHERE empleados.rfc = rfcEmpl;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE bucar_forma_pago_por_id(
	IN idFormaDePago INT
)
BEGIN
	
    SELECT * FROM formas_de_pago WHERE formas_de_pago.id = idFormaDePago;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_articulo_por_codigo(
	IN codigo_a VARCHAR(65),
    IN sucursal INT
)
BEGIN
    
    SELECT
		articulo.id_articulo,
        articulo.codigo_articulo,
        proveedor.nombre,
        categoria_producto.nombre,
        articulo.nombre,
        articulo.codigo_sat,
        articulo.descripcion,
        existencia_x_sucursal.existencia,
        articulo.es_exento,
        articulo.costo_unitario,
        articulo.precio_general,
        articulo.precio_mayoreo,
        articulo.cantidad_mayoreo
	FROM existencia_x_sucursal
    INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
	INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
	INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
    WHERE articulo.codigo_articulo = codigo_a AND existencia_x_sucursal.id_sucursal = sucursal;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_categoria_por_nombre(IN `nombre` VARCHAR(60))
BEGIN
    SELECT 
		categoria_producto.id_categoria,
        categoria_producto.nombre,
		categoria_producto.descripcion,
        categoria_producto.activo
    FROM categoria_producto WHERE categoria_producto.nombre LIKE CONCAT('%',nombre,'%');
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_cliente_por_nombre(
	IN `nombre` VARCHAR(30)
)
BEGIN
	
    SELECT
		cliente.id_cliente,
		cliente.rfc,
		cuentas_contables.clave,
		cliente.nombre_completo,
		cliente.nombre_corto,
		cliente.correo_electronico,
		cliente.estado,
		cliente.ciudad,
		cliente.direccion,
		cliente.codigo_postal,
        cliente.activo        
    FROM cliente
    INNER JOIN cuentas_contables ON cuentas_contables.id_cuenta = cliente.id_cuenta_contable
    WHERE cliente.nombre_completo LIKE CONCAT('%',nombre,'%');
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_cuenta_x_clave(
	IN `clave_cuenta` VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'busqueda de una cuenta contable por su clave'
BEGIN
	
	SELECT
		cc.id_cuenta,
		CASE WHEN cc.id_cuenta_padre IS NULL THEN 0 ELSE cc.id_cuenta_padre END AS 'id_cuenta_padre',
		cc.fk_id_rubro,
		rcc.fk_id_grupo_contable,
		cc.clave,
		cc.nombre,
		cc.descripcion,
		cc.nivel,
		cc.ultimo_nivel 
	FROM
		kath_erp.cuentas_contables AS cc
		INNER JOIN kath_erp.rubro_cuenta_contable rcc ON cc.fk_id_rubro = rcc.id_rubro
		WHERE cc.clave LIKE CONCAT('%',`clave_cuenta`) COLLATE utf8mb4_general_ci;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_cuenta_x_id(
	IN `id_cuenta` INT
)
BEGIN
	
	SELECT
		_cc.id_cuenta,
		_cp.nombre AS `nombre_cuenta_padre`,
		_rcta.nombre AS `rubro_cuenta`,
		_cc.nombre AS `nombre_cuenta`,
		_cc.descripcion,
		_cc.nivel,
		_cc.ultimo_nivel,
		_cc.cargo,
		_cc.abono,
		(_cc.cargo - _cc.abono) AS `saldo`,
		_rcta.naturaleza
	FROM cuentas_contables AS _cc
	INNER JOIN cuentas_contables AS _cp ON _cc.id_cuenta_padre = _cp.id_cuenta 
	INNER JOIN rubro_cuenta_contable AS _rcta ON _cc.fk_id_rubro = _rcta.id_rubro
	WHERE _cc.id_cuenta = id_cuenta;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_empleado(
	IN nombre_e VARCHAR(30)
)
BEGIN
	
	SELECT
		empleados.id_empleado,
		sucursal.nombre,
		empleados.rfc,
		empleados.curp,
		empleados.nombre_completo,
		empleados.nombre_corto,
		empleados.correo_electronico,
		empleados.activo
	FROM empleados
	INNER JOIN sucursal ON empleados.id_sucursal = sucursal.id_sucursar
    WHERE empleados.nombre_completo LIKE CONCAT('%',nombre_e,'%') ORDER BY id_empleado;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_empleado_por_nombre(
	IN nombre VARCHAR(10)
)
BEGIN

	SELECT
		empleados.id_empleado,
        empleados.nombre_completo
	FROM empleados WHERE empleados.nombre_corto = nombre;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_proveedor_por_nombre(
	IN nombre_prov VARCHAR(30)
)
BEGIN

	SELECT
		proveedor.id_proveedor,
		proveedor.rfc,
		sub_cuentas_tercer_nivel.clave,
		proveedor.nombre,
		proveedor.descripcion,
		proveedor.correo_electronico,
		proveedor.estado,
		proveedor.ciudad,
		proveedor.direccion,
		proveedor.codigo_postal,
        proveedor.activo
	FROM proveedor
	INNER JOIN sub_cuentas_tercer_nivel ON proveedor.id_cuenta_contable = sub_cuentas_tercer_nivel.id_cuenta
    WHERE proveedor.nombre LIKE CONCAT('%',nombre_prov,'%');
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_sucursal_por_id(
	IN `id_sucursal` INT
)
BEGIN
SELECT id_sucursar,
       nombre,
       descripcion,
       telefono,
       email,
       estado,
       ciudad,
       direccion,
       codigo_postal,
       activo
FROM sucursal
WHERE sucursal.id_sucursar = id_sucursal;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_tipoCliente_por_id(
	IN id_tipoCliente INT
)
BEGIN	
    SELECT
		tipo_cliente.id,
        tipo_cliente.nombre,
        tipo_cliente.descripcion
	FROM tipo_cliente
    WHERE tipo_cliente.id = id_tipoCliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_ultima_venta()
BEGIN
	
    SELECT 
		ventas.id_venta
	FROM ventas ORDER BY ventas.id_venta DESC LIMIT 1;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE buscar_ventas_por(
	IN dato VARCHAR(100),
    IN opcion INT
)
BEGIN
	
    
    IF opcion = 1 THEN
		 SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE ventas.id_venta LIKE CONCAT('%',dato,'%')
		ORDER BY ventas.id_venta;
    END IF;
    
    
    IF opcion = 2 THEN
		 SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE empleados.nombre_corto LIKE CONCAT('%',dato,'%')
		ORDER BY ventas.id_venta;
    END IF;
    
    IF opcion = 3 THEN
		 SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE cliente.nombre_corto LIKE CONCAT('%',dato,'%')
		ORDER BY ventas.id_venta;
    END IF;
    
    IF opcion = 4 THEN
		 SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE ventas.fecha LIKE CONCAT('%',dato,'%')
		ORDER BY ventas.id_venta;
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE cmb_tipoCliente()
BEGIN
	SELECT
		tipo_cliente.id,
        tipo_cliente.nombre
	FROM tipo_cliente;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteArticuloCompra(
    IN p_id_detalle_compra INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un artículo del detalle de compra y descuenta su existencia si es válido'
BEGIN
    DECLARE v_id_compra INT UNSIGNED DEFAULT 0;
    DECLARE v_id_articulo INT UNSIGNED DEFAULT 0;
    DECLARE v_cantidad_actual INT DEFAULT 0;
    DECLARE v_existencia_actual INT DEFAULT 0;
    DECLARE v_id_existencia INT DEFAULT 0;
    DECLARE v_id_sucursal BIGINT UNSIGNED DEFAULT 0;
    DECLARE v_fecha_compra DATE;
    DECLARE v_ventas_posteriores INT DEFAULT 0;
    DECLARE v_registros_existencia INT DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    IF p_id_detalle_compra IS NULL OR p_id_detalle_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El detalle de compra es obligatorio';
    END IF;

    SELECT
        axc.id_compra,
        axc.id_articulo,
        axc.cantidad,
        c.fecha_compra,
        emp.id_sucursal
    INTO
        v_id_compra,
        v_id_articulo,
        v_cantidad_actual,
        v_fecha_compra,
        v_id_sucursal
    FROM kath_erp.articulo_x_compra AS axc
    INNER JOIN kath_erp.compras AS c
        ON axc.id_compra = c.id_compra
    INNER JOIN kath_erp.empleados AS emp
        ON c.id_empleado = emp.id_empleado
    WHERE axc.id = p_id_detalle_compra
      AND c.activo = TRUE
    LIMIT 1
    FOR UPDATE;

    IF v_id_compra IS NULL OR v_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El detalle de compra no existe o la compra está inactiva';
    END IF;

    SELECT COUNT(*)
    INTO v_ventas_posteriores
    FROM kath_erp.articulo_x_venta AS axv
    INNER JOIN kath_erp.ventas AS v
        ON axv.id_venta = v.id_venta
    INNER JOIN kath_erp.empleados AS emp_venta
        ON v.id_empleado = emp_venta.id_empleado
    WHERE axv.id_articulo = v_id_articulo
      AND emp_venta.id_sucursal = v_id_sucursal
      AND v.fecha > v_fecha_compra
      AND v.status_venta = TRUE;

    IF v_ventas_posteriores > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede eliminar el artículo porque ya tiene ventas posteriores a la compra';
    END IF;

    SELECT COUNT(*)
    INTO v_registros_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal;

    IF v_registros_existencia = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No existe registro de existencia para el artículo y sucursal';
    END IF;

    IF v_registros_existencia > 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Existe más de un registro de existencia para el artículo y sucursal';
    END IF;

    SELECT id, COALESCE(existencia, 0)
    INTO v_id_existencia, v_existencia_actual
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal
    LIMIT 1
    FOR UPDATE;

    IF v_existencia_actual - v_cantidad_actual < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se puede eliminar el artículo porque la existencia quedaría negativa';
    END IF;

    DELETE FROM kath_erp.articulo_x_compra
    WHERE id = p_id_detalle_compra;

    UPDATE kath_erp.existencia_x_sucursal
    SET existencia = v_existencia_actual - v_cantidad_actual
    WHERE id = v_id_existencia;

    SELECT
        p_id_detalle_compra AS id,
        'Artículo eliminado de la compra correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteCategoriaProducto(
	IN p_id_categoria INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Inhabilita una categoria de producto'
BEGIN
	
    DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_categoria_activa BOOLEAN DEFAULT FALSE;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la categoria no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM categoria_producto
	WHERE id_categoria = p_id_categoria;

	IF v_existe_categoria = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria indicada no existe';
	END IF;

	SELECT activo
	INTO v_categoria_activa
	FROM categoria_producto
	WHERE id_categoria = p_id_categoria
	FOR UPDATE;

	IF v_categoria_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria ya se encuentra inactiva';
	END IF;

	UPDATE categoria_producto
	SET activo = FALSE
	WHERE id_categoria = p_id_categoria;

	COMMIT;

	SELECT
		200 AS id,
		'Categoria inhabilitada correctamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteCliente(
	IN p_id_cliente INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Cambia el status del cliente, y de su cuenta contable'
BEGIN
	
	DECLARE v_existe_cliente INT DEFAULT 0;
	DECLARE v_cliente_activo BOOLEAN DEFAULT FALSE;
	DECLARE v_id_cuenta_contable INT DEFAULT 0;

	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del cliente no es válido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cliente
	FROM cliente
	WHERE id_cliente = p_id_cliente;

	IF v_existe_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente indicado no existe';
	END IF;

	SELECT
		activo,
		id_cuenta_contable
	INTO
		v_cliente_activo,
		v_id_cuenta_contable
	FROM cliente
	WHERE id_cliente = p_id_cliente
	FOR UPDATE;

	IF v_cliente_activo = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente ya se encuentra inactivo';
	END IF;

	SELECT
		cargo,
		abono
	INTO
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = v_id_cuenta_contable
	FOR UPDATE;

	SET v_saldo = ROUND(
		COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
		2
	);

	IF v_saldo <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar el cliente porque su cuenta contable tiene saldo pendiente';
	END IF;

	UPDATE cliente
	SET activo = FALSE
	WHERE id_cliente = p_id_cliente;

	UPDATE cuentas_contables
	SET
		activa = FALSE,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = v_id_cuenta_contable;

	COMMIT;

	SELECT
		200 AS id,
		'Cliente desactivado correctamente' AS message;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteProveedor(
	IN idProveedor INT UNSIGNED
)
BEGIN
	
    DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_proveedor_activo BOOLEAN DEFAULT FALSE;
	DECLARE v_id_cuenta_contable INT DEFAULT 0;

	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF idProveedor IS NULL OR idProveedor <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del proveedor no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_proveedor
	FROM proveedor
	WHERE id_proveedor = idProveedor;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor indicado no existe';
	END IF;

	SELECT
		activo,
		id_cuenta_contable
	INTO
		v_proveedor_activo,
		v_id_cuenta_contable
	FROM proveedor
	WHERE id_proveedor = idProveedor
	FOR UPDATE;

	IF v_proveedor_activo = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor ya se encuentra inactivo';
	END IF;

	SELECT
		cargo,
		abono
	INTO
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = v_id_cuenta_contable
	FOR UPDATE;

	SET v_saldo = ROUND(
		COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
		2
	);

	IF v_saldo <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede inhabilitar el proveedor porque su cuenta contable tiene saldo';
	END IF;

	UPDATE proveedor
	SET activo = FALSE
	WHERE id_proveedor = idProveedor;

	UPDATE cuentas_contables
	SET
		activa = FALSE,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = v_id_cuenta_contable;

	COMMIT;

	SELECT
		200 AS id,
		'Proveedor inhabilitado correctamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteTelefonoCliente(
	IN p_id_telefono INT
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un telefono asociado a un cliente'
BEGIN
	
	DECLARE v_existe_telefono INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_telefono IS NULL OR p_id_telefono <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del telefono no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_cliente
	WHERE id_telefono = p_id_telefono;

	IF v_existe_telefono = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado no existe';
	END IF;

	DELETE FROM telefono_x_cliente
	WHERE id_telefono = p_id_telefono;

	COMMIT;

	SELECT
		200 AS id,
		'Telefono eliminado correctamente' AS message;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteTelefonoEmpleado(
	IN p_id_telefono INT
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un numero telefonico asociado a un empleado'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_numero_existe INT;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;		

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;
	
	SELECT COUNT(*) INTO v_numero_existe FROM kath_erp.telefono_x_empleado AS txe WHERE txe.id_telefono = p_id_telefono;
	
	IF v_numero_existe = 0 THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El numero indicado no existe o es erroneo';
	END IF;
	
	
	DELETE FROM kath_erp.telefono_x_empleado WHERE id_telefono = p_id_telefono;
	
	SELECT 200 AS id, 'Numero telefonico eliminado correctamente' AS message;
	
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE deleteTelefonoProveedor(
	IN p_id_telefono INT
)
    MODIFIES SQL DATA
    COMMENT 'Elimina un telefono asociado a un proveedor'
BEGIN
	
	DECLARE v_existe_telefono INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_telefono IS NULL OR p_id_telefono <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del telefono no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_proveedor
	WHERE id_telefono = p_id_telefono;

	IF v_existe_telefono = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado no existe';
	END IF;

	DELETE FROM telefono_x_proveedor
	WHERE id_telefono = p_id_telefono;

	COMMIT;

	SELECT
		200 AS id,
		'Telefono eliminado correctamente' AS message;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE delete_cuenta_contable(
	IN p_id_cuenta INT
)
    MODIFIES SQL DATA
    COMMENT 'Desactiva una cuenta contable con validaciones operativas'
BEGIN
	DECLARE v_cuenta_existe INT DEFAULT 0;
	DECLARE v_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_hijas_activas INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4
		COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT(
				'Error ',
				v_errno,
				' (',
				v_sqlstate,
				'): ',
				v_text
			) AS message;
	END;

	START TRANSACTION;

	IF p_id_cuenta IS NULL OR p_id_cuenta <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la cuenta es inválido';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_existe
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta;

	IF v_cuenta_existe = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable no existe';
	END IF;

	SELECT
		activa,
		cargo,
		abono
	INTO
		v_activa,
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta
	FOR UPDATE;

	IF v_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable ya se encuentra inactiva';
	END IF;

	/*
	 * Bloquea cuentas que ya registraron movimientos,
	 * aunque su saldo actual sea cero.
	 */
	IF v_cargo <> 0 OR v_abono <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar una cuenta con movimientos contables';
	END IF;

	SELECT COUNT(*)
	INTO v_hijas_activas
	FROM cuentas_contables
	WHERE id_cuenta_padre = p_id_cuenta
	  AND activa = TRUE;

	IF v_hijas_activas > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar una cuenta con subcuentas activas';
	END IF;

	UPDATE cuentas_contables
	SET
		activa = FALSE,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = p_id_cuenta;

	COMMIT;

	SELECT
		p_id_cuenta AS id,
		'Cuenta contable desactivada correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE delete_empleado(
	IN p_id_empleado INT UNSIGNED
)
    MODIFIES SQL DATA
    COMMENT 'Desactiva un empleado y su cuenta contable'
BEGIN
	DECLARE v_existe_empleado INT DEFAULT 0;
	DECLARE v_empleado_activo BOOLEAN DEFAULT FALSE;
	DECLARE v_id_cuenta_contable INT DEFAULT 0;
	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del empleado no es válido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_empleado
	FROM empleados
	WHERE id_empleado = p_id_empleado;

	IF v_existe_empleado = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El empleado no existe';
	END IF;

	SELECT activo, id_cuenta_contable
	INTO v_empleado_activo, v_id_cuenta_contable
	FROM empleados
	WHERE id_empleado = p_id_empleado
	FOR UPDATE;

	IF v_empleado_activo = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El empleado ya se encuentra inactivo';
	END IF;

	SELECT cargo, abono
	INTO v_cargo, v_abono
	FROM cuentas_contables
	WHERE id_cuenta = v_id_cuenta_contable
	FOR UPDATE;

	SET v_saldo = ROUND(v_cargo - v_abono, 2);

	IF v_saldo <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede eliminar al empleado porque su cuenta contable tiene saldo pendiente';
	END IF;

	UPDATE empleados
	SET activo = FALSE
	WHERE id_empleado = p_id_empleado;

	UPDATE cuentas_contables
	SET
		activa = FALSE,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = v_id_cuenta_contable;

	COMMIT;

	SELECT
		p_id_empleado AS id,
		'Empleado desactivado correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE eliminar_articulo(
	IN id INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := articulo.activo FROM articulo WHERE articulo.id_articulo = id;
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El articulo ya se encuentra inactivo';
    END IF;
    
    UPDATE articulo SET
		activo = 0
	WHERE articulo.id_articulo = id;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE eliminar_empleado(
	IN idEmpleado INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := empleados.activo FROM empleados WHERE empleados.id_empleado = idEmpleado;
    
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El empleado ya se encuentra inactivo';
    END IF;
    
    UPDATE empleados SET
		empleados.activo = 0
	WHERE empleados.id_empleado = idEmpleado;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE eliminar_forma_pago(
	IN idFormaPago INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := formas_de_pago.activo FROM formas_de_pago WHERE formas_de_pago.id = idFormaPago;
    
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000'  SET MESSAGE_TEXT = 'La forma de pago ya se encuentra inactiva';
    END IF;
    
    UPDATE formas_de_pago SET
		activo = 0
    WHERE id = idFormaPago;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE eliminar_sucursal(
	IN idSucursal INT
)
BEGIN
	
    
    UPDATE sucursal SET
		activo = 0
	WHERE sucursal.id_sucursar = idSucursal;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE eliminar_tipoCliente(
	IN id_tipoCliente INT
)
BEGIN
	
    UPDATE tipo_cliente SET
		tipo_cliente.activo = 0
	WHERE tipo_cliente.id = id_tipoCliente;
    
    SELECT 200 AS id, 'Tipo Cliente inhabilitado exitosamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE eliminar_venta(
	IN idVenta INT
)
BEGIN
	
    UPDATE ventas SET
		status_venta = 0
	WHERE ventas.id_venta = idVenta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getArticuloById(
	IN p_id_articulo INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Consulta el detalle de un articulo por su id para edicion'
BEGIN
	
	IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del articulo no es valido';
	END IF;

	SELECT
		art.id_articulo,
		art.id_proveedor,		
		art.id_categoria,		
		art.codigo_articulo,
		art.codigo_sat,
		art.unidad_sat,
		art.nombre,
		art.descripcion,
		art.es_exento,
		art.costo_unitario,
		art.activo
	FROM kath_erp.articulo AS art	
	WHERE art.id_articulo = p_id_articulo;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getCategoriaById(
	IN `p_id_categoria` INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'CONSULTA EL DETALLE DE UNA CATEGORIA DE PRODUCTO POR SU ID'
BEGIN

    SELECT
	    cp.id_categoria,
	    cp.nombre,
	    cp.descripcion,
	    cp.activo
    FROM kath_erp.categoria_producto AS cp
    WHERE cp.id_categoria = `p_id_categoria`;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getClienteById(
	IN p_idCliente INT
)
    READS SQL DATA
    COMMENT 'BUSCA EL REGISTRO DE UN CLIENTE MEDIANTE SU ID Y RETORNA LOS CAMPOS A UTILIZAR EN Fr_DatosCliente.java'
BEGIN

	SELECT 
		c.id_cliente,
		c.id_tipoCliente,
		c.id_cuenta_contable,
		cc.clave,
		c.rfc,
		c.nombre_completo,
		c.nombre_corto,
		c.fecha_nac,
		c.correo_electronico,
		c.estado,
		c.ciudad,
		c.direccion,
		c.codigo_postal,
		c.activo
	FROM kath_erp.cliente AS c
	INNER JOIN kath_erp.cuentas_contables AS cc ON c.id_cuenta_contable  = cc.id_cuenta
	WHERE p_idCliente = c.id_cliente; 

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getCompraById(
    IN p_id_compra INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Obtiene los datos generales de una compra'
BEGIN
    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    SELECT
        c.id_compra,
        c.id_empleado,
        emp.nombre_completo AS nombre_empleado,
        emp.nombre_corto AS nombre_corto_empleado,
        emp.id_sucursal,
        c.id_proveedor,
        c.folio_factura,
        c.fecha_factura,
        c.fecha_compra,
        c.tipo_compra,
        CASE
            WHEN c.tipo_compra = TRUE THEN 'Crédito'
            ELSE 'Contado'
        END AS tipo_compra_descripcion,
        c.subtotal,
        c.iva,
        (c.subtotal + c.iva) AS importe_total,
        c.activo
    FROM kath_erp.compras AS c
    INNER JOIN kath_erp.empleados AS emp
        ON c.id_empleado = emp.id_empleado
    WHERE c.id_compra = p_id_compra
    LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getEmpleadoById(
	IN id_empleado INT
)
    READS SQL DATA
    COMMENT 'Consulta un empleado por su id'
BEGIN
	
	SELECT 
		
		em.id_empleado,
		em.id_cuenta_contable,
		cc.clave,
		em.id_sucursal,
		em.rfc,
		em.curp,
		em.nombre_completo,
		em.nombre_corto,
		em.fecha_nac,
		em.correo_electronico,
		em.estado,
		em.ciudad,
		em.direccion,
		em.codigo_postal,
		em.activo		
	
	FROM kath_erp.empleados AS em
	INNER JOIN kath_erp.cuentas_contables AS cc ON em.id_cuenta_contable = cc.id_cuenta
	WHERE em.id_empleado = id_empleado;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getEmpleadoByRFC(
	IN rfc_empleado VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Consulta los datos de un empleado por su rfc'
BEGIN
	
	SELECT 
		
		em.id_empleado,
		em.id_cuenta_contable,
		cc.clave,
		em.id_sucursal,
		em.rfc,
		em.curp,
		em.nombre_completo,
		em.nombre_corto,
		em.fecha_nac,
		em.correo_electronico,
		em.estado,
		em.ciudad,
		em.direccion,
		em.codigo_postal,
		em.activo		
	
	FROM kath_erp.empleados AS em
	INNER JOIN kath_erp.cuentas_contables AS cc ON em.id_cuenta_contable = cc.id_cuenta
	WHERE em.rfc = rfc_empleado;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getEmpleadoLogin(
    IN p_nombre_corto VARCHAR(10)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Obtiene empleado activo por nombre corto para validación de login en Java'
BEGIN
    IF p_nombre_corto IS NULL OR TRIM(p_nombre_corto) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El usuario es obligatorio';
    END IF;

    SELECT
        e.id_empleado,
        e.id_cuenta_contable,
        e.id_sucursal,
        s.nombre AS nombre_sucursal,
        e.rfc,
        e.curp,
        e.nombre_completo,
        e.nombre_corto,
        e.fecha_nac,
        e.correo_electronico,
        e.estado,
        e.ciudad,
        e.direccion,
        e.codigo_postal,
        e.contrasenia AS contrasenia_hash,
        e.activo
    FROM kath_erp.empleados AS e
    INNER JOIN kath_erp.sucursal AS s
        ON e.id_sucursal = s.id_sucursar
    WHERE e.nombre_corto = TRIM(p_nombre_corto)
      AND e.activo = TRUE
    LIMIT 1;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getListadoEmpleados(
	IN nombre_empleado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Obtiene un listado completo de todos los empleados registrados en la bd y filtra por nombres'
BEGIN
	
	SELECT 
		
		em.id_empleado,		
		cc.clave,		
		em.rfc,
		em.curp,
		em.nombre_completo,
		em.nombre_corto,	
		em.correo_electronico,		
		em.activo
	
	FROM kath_erp.empleados AS em
	INNER JOIN kath_erp.cuentas_contables AS cc ON em.id_cuenta_contable = cc.id_cuenta
	WHERE em.nombre_completo LIKE CONCAT('%',nombre_empleado,'%');
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE getProveedorById(
	IN idProveedor INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Consulta los detalles de un proveedor por su ID'
BEGIN
	
    SELECT
		pr.id_proveedor, 
        pr.id_cuenta_contable,
        cc.clave,
        pr.rfc,
        pr.nombre,                
        pr.descripcion,
        pr.correo_electronico,
        pr.estado,
        pr.ciudad,
        pr.direccion,
        pr.codigo_postal,
        pr.activo 
	FROM kath_erp.proveedor AS pr
    INNER JOIN kath_erp.cuentas_contables AS cc ON pr.id_cuenta_contable = cc.id_cuenta 
	WHERE pr.id_proveedor = idProveedor;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertArticulo(
	IN p_id_proveedor INT UNSIGNED,
	IN p_id_categoria INT UNSIGNED,
	IN p_codigo_articulo VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_sat VARCHAR(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_unidad_sat VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(555) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_es_exento TINYINT,
	IN p_costo_unitario DECIMAL(18,2)
)
    MODIFIES SQL DATA
    COMMENT 'Inserta un articulo base y devuelve el id generado'
BEGIN
	
    DECLARE v_id_articulo INT UNSIGNED;
	DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_codigo_duplicado INT DEFAULT 0;

	IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor no es valido';
	END IF;

	IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria no es valida';
	END IF;

	IF p_codigo_articulo IS NULL OR TRIM(p_codigo_articulo) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El codigo del articulo es obligatorio';
	END IF;

	IF p_codigo_sat IS NULL OR TRIM(p_codigo_sat) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El codigo SAT es obligatorio';
	END IF;

	IF p_unidad_sat IS NULL OR TRIM(p_unidad_sat) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La unidad SAT es obligatoria';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre del articulo es obligatorio';
	END IF;

	IF p_es_exento IS NULL OR p_es_exento NOT IN (0, 1) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El valor de exento no es valido';
	END IF;

	IF p_costo_unitario IS NULL OR p_costo_unitario < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El costo unitario no es valido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_proveedor
	FROM kath_erp.proveedor
	WHERE id_proveedor = p_id_proveedor
	  AND activo = 1;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor no existe o esta inactivo';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM kath_erp.categoria_producto
	WHERE id_categoria = p_id_categoria
	  AND activo = 1;

	IF v_existe_categoria = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria no existe o esta inactiva';
	END IF;

	SELECT COUNT(*)
	INTO v_codigo_duplicado
	FROM kath_erp.articulo
	WHERE codigo_articulo COLLATE utf8mb4_general_ci = TRIM(p_codigo_articulo) COLLATE utf8mb4_general_ci;

	IF v_codigo_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El codigo del articulo ya esta registrado';
	END IF;

	INSERT INTO kath_erp.articulo (
		id_proveedor,
		id_categoria,
		codigo_articulo,
		codigo_sat,
		unidad_sat,
		nombre,
		descripcion,
		es_exento,
		costo_unitario,
		activo
	) VALUES (
		p_id_proveedor,
		p_id_categoria,
		UPPER(TRIM(p_codigo_articulo)),
		TRIM(p_codigo_sat),
		UPPER(TRIM(p_unidad_sat)),
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		p_es_exento,
		p_costo_unitario,
		1
	);

	SET v_id_articulo = LAST_INSERT_ID();

	SELECT
		v_id_articulo AS id,
		'Articulo registrado correctamente' AS message;

	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertArticuloCompra(
    IN p_id_compra INT UNSIGNED,
    IN p_id_articulo INT UNSIGNED,
    IN p_cantidad INT,
    IN p_subtotal DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Inserta un artículo en el detalle de compra. La existencia se actualiza con otro SP'
BEGIN
    DECLARE v_existe_compra INT DEFAULT 0;
    DECLARE v_existe_articulo INT DEFAULT 0;
    DECLARE v_existe_detalle INT DEFAULT 0;
    DECLARE v_id_detalle INT UNSIGNED DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo es obligatorio';
    END IF;

    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero';
    END IF;

    IF p_subtotal IS NULL OR p_subtotal < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal del artículo no puede ser negativo';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra
      AND activo = TRUE;

    IF v_existe_compra = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra indicada no existe o está inactiva';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_articulo
    FROM kath_erp.articulo
    WHERE id_articulo = p_id_articulo
      AND activo = TRUE;

    IF v_existe_articulo = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo indicado no existe o está inactivo';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_detalle
    FROM kath_erp.articulo_x_compra
    WHERE id_compra = p_id_compra
      AND id_articulo = p_id_articulo;

    IF v_existe_detalle > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo ya está registrado en esta compra';
    END IF;

    INSERT INTO kath_erp.articulo_x_compra (
        id_compra,
        id_articulo,
        cantidad,
        subtotal
    ) VALUES (
        p_id_compra,
        p_id_articulo,
        p_cantidad,
        p_subtotal
    );

    SET v_id_detalle = LAST_INSERT_ID();

    SELECT
        v_id_detalle AS id,
        'Artículo agregado a la compra correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertCategoriaProducto(
	IN p_nombre VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra una nueva categoria de producto'
BEGIN

	DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_id_categoria INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la categoria es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM categoria_producto
	WHERE nombre COLLATE utf8mb4_general_ci = TRIM(p_nombre);

	IF v_existe_categoria > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe una categoria con el nombre indicado';
	END IF;

	INSERT INTO categoria_producto (
		nombre,
		descripcion,
		activo
	) VALUES (
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		TRUE
	);

	SET v_id_categoria = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Categoria registrada correctamente' AS message;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertCliente(
  	IN p_id_tipoCliente INT,
	IN p_id_cuenta_contable INT,
	IN p_rfc VARCHAR(13)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_completo VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_corto VARCHAR(10)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_fecha_nac DATE,
	IN p_correo_electronico VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo cliente asociado a una cuenta contable existente. necesario agregar una cuenta contable antes de agregar al cliente'
BEGIN
	
    DECLARE v_id_cliente INT UNSIGNED DEFAULT 0;
	DECLARE v_existe_tipo_cliente INT DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;
	DECLARE v_rfc_duplicado INT DEFAULT 0;
	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_ultimo_nivel BOOLEAN DEFAULT FALSE;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT(
				'Error ',
				v_errno,
				' (',
				v_sqlstate,
				'): ',
				v_text
			) AS message;
	END;

	START TRANSACTION;

	/*
	 * Validación de parámetros obligatorios.
	 */
	IF p_id_tipoCliente IS NULL OR p_id_tipoCliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente es obligatorio';
	END IF;

	IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable es obligatoria';
	END IF;

	IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC es obligatorio';
	END IF;

	IF p_nombre_completo IS NULL
			OR TRIM(p_nombre_completo) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre completo es obligatorio';
	END IF;

	IF p_nombre_corto IS NULL
			OR TRIM(p_nombre_corto) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre corto es obligatorio';
	END IF;

	IF p_fecha_nac IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento es obligatoria';
	END IF;

	IF p_fecha_nac > CURDATE() THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
	END IF;

	IF p_correo_electronico IS NULL
			OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El correo electrónico es obligatorio';
	END IF;

	/*
	 * Validación de tipo de cliente.
	 */
	SELECT COUNT(*)
	INTO v_existe_tipo_cliente
	FROM tipo_cliente
	WHERE id = p_id_tipoCliente;

	IF v_existe_tipo_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente indicado no existe';
	END IF;

	/*
	 * Validación del RFC.
	 */
	SELECT COUNT(*)
	INTO v_rfc_duplicado
	FROM cliente
	WHERE rfc = UPPER(TRIM(p_rfc));

	IF v_rfc_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC ya está registrado';
	END IF;

	/*
	 * Validación de la cuenta contable.
	 */
	SELECT COUNT(*)
	INTO v_existe_cuenta
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable;

	IF v_existe_cuenta = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada no existe';
	END IF;

	SELECT
		activa,
		ultimo_nivel
	INTO
		v_cuenta_activa,
		v_ultimo_nivel
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable
	FOR UPDATE;

	IF v_cuenta_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable se encuentra inactiva';
	END IF;

	IF v_ultimo_nivel = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta del cliente debe ser una cuenta de detalle';
	END IF;

	/*
	 * Las cuentas contables no pueden compartirse entre clientes.
	 */
	SELECT COUNT(*)
	INTO v_cuenta_asignada
	FROM cliente
	WHERE id_cuenta_contable = p_id_cuenta_contable;

	IF v_cuenta_asignada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable ya está asignada a otro cliente';
	END IF;

	/*
	 * Registro del cliente.
	 */
	INSERT INTO cliente (
		id_tipoCliente,
		id_cuenta_contable,
		rfc,
		nombre_completo,
		nombre_corto,
		fecha_nac,
		correo_electronico,
		estado,
		ciudad,
		direccion,
		codigo_postal,
		activo
	)
	VALUES (
		p_id_tipoCliente,
		p_id_cuenta_contable,
		UPPER(TRIM(p_rfc)),
		TRIM(p_nombre_completo),
		TRIM(p_nombre_corto),
		p_fecha_nac,
		LOWER(TRIM(p_correo_electronico)),
		NULLIF(TRIM(p_estado), ''),
		NULLIF(TRIM(p_ciudad), ''),
		NULLIF(TRIM(p_direccion), ''),
		NULLIF(TRIM(p_codigo_postal), ''),
		TRUE
	);

	SET v_id_cliente = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Cliente registrado correctamente' AS message;
	
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertCompra(
    IN p_id_empleado INT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_folio_factura VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_factura DATE,
    IN p_fecha_compra DATE,
    IN p_tipo_compra BOOLEAN,
    IN p_subtotal DOUBLE,
    IN p_iva DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Inserta cabecera de compra asociándola directamente a la sucursal donde se realiza'
BEGIN
    DECLARE v_id_compra INT UNSIGNED DEFAULT 0;

    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_existe_proveedor INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;
    DECLARE v_folio_duplicado INT DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;
    END;

    IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado es obligatorio';
    END IF;

    IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor es obligatorio';
    END IF;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    IF p_folio_factura IS NULL
       OR TRIM(p_folio_factura) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura es obligatorio';

    END IF;

    IF CHAR_LENGTH(TRIM(p_folio_factura)) > 13 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura no puede exceder 13 caracteres';
    END IF;

    IF p_fecha_factura IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de factura es obligatoria';
    END IF;

    IF p_fecha_compra IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de compra es obligatoria';
    END IF;

    IF p_tipo_compra IS NULL THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El tipo de compra es obligatorio';
    END IF;

    IF p_subtotal IS NULL OR p_subtotal < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal no puede ser negativo';
    END IF;

    IF p_iva IS NULL OR p_iva < 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El IVA no puede ser negativo';
    END IF;


    -- Validar empleado

    SELECT COUNT(*)
    INTO v_existe_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
      AND activo = TRUE;

    IF v_existe_empleado = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado indicado no existe o está inactivo';
    END IF;


    -- Validar proveedor

    SELECT COUNT(*)
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = p_id_proveedor;

    IF v_existe_proveedor = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor indicado no existe';
    END IF;


    -- Validar directamente la sucursal

    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal
      AND activo = TRUE;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal indicada no existe o está inactiva';
    END IF;


    -- Mantengo por ahora tu regla existente:
    -- folio único por proveedor.

    SELECT COUNT(*)
    INTO v_folio_duplicado
    FROM kath_erp.compras
    WHERE id_proveedor = p_id_proveedor
      AND folio_factura = TRIM(p_folio_factura)
      AND activo = TRUE;

    IF v_folio_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura ya está registrado para este proveedor';
    END IF;


    INSERT INTO kath_erp.compras (
        id_empleado,
        id_proveedor,
        id_sucursal,
        folio_factura,
        fecha_factura,
        fecha_compra,
        tipo_compra,
        subtotal,
        iva,
        activo
    )
    VALUES (
        p_id_empleado,
        p_id_proveedor,
        p_id_sucursal,
        TRIM(p_folio_factura),
        p_fecha_factura,
        p_fecha_compra,
        p_tipo_compra,
        p_subtotal,
        p_iva,
        TRUE
    );

    SET v_id_compra = LAST_INSERT_ID();

    SELECT
        v_id_compra AS id,
        'Compra registrada correctamente' AS message;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertExistenciaArticuloSucursal(
	IN p_id_articulo INT UNSIGNED,
	IN p_id_sucursal BIGINT UNSIGNED,
	IN p_existencia INT
)
    MODIFIES SQL DATA
    COMMENT 'Inserta existencia inicial de un articulo por sucursal'
BEGIN
	DECLARE v_existe_articulo INT DEFAULT 0;
	DECLARE v_existe_sucursal INT DEFAULT 0;
	DECLARE v_existe_relacion INT DEFAULT 0;

	IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no es valido';
	END IF;

	IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La sucursal no es valida';
	END IF;

	IF p_existencia IS NULL OR p_existencia < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La existencia no es valida';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_articulo
	FROM kath_erp.articulo
	WHERE id_articulo = p_id_articulo;

	IF v_existe_articulo = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_sucursal
	FROM kath_erp.sucursal
	WHERE id_sucursar = p_id_sucursal;

	IF v_existe_sucursal = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La sucursal no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_relacion
	FROM kath_erp.existencia_x_sucursal
	WHERE id_articulo = p_id_articulo
	  AND id_sucursal = p_id_sucursal;

	IF v_existe_relacion > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La existencia del articulo ya existe para esta sucursal';
	END IF;

	INSERT INTO kath_erp.existencia_x_sucursal (
		id_articulo,
		id_sucursal,
		existencia
	) VALUES (
		p_id_articulo,
		p_id_sucursal,
		p_existencia
	);

	SELECT
		LAST_INSERT_ID() AS id,
		'Existencia registrada correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertPrecioArticuloTipoCliente(
	IN p_id_articulo INT UNSIGNED,
	IN p_id_tipo_cliente INT,
	IN p_precio DECIMAL(18,2),
	IN p_precio_especial DECIMAL(18,2),
	IN p_cantidad_precio_especial INT
)
    MODIFIES SQL DATA
    COMMENT 'Inserta precio de articulo por tipo de cliente'
BEGIN
	DECLARE v_existe_articulo INT DEFAULT 0;
	DECLARE v_existe_tipo_cliente INT DEFAULT 0;
	DECLARE v_existe_relacion INT DEFAULT 0;

	IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no es valido';
	END IF;

	IF p_id_tipo_cliente IS NULL OR p_id_tipo_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente no es valido';
	END IF;

	IF p_precio IS NULL OR p_precio < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El precio no es valido';
	END IF;

	IF p_precio_especial IS NOT NULL AND p_precio_especial < 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El precio especial no es valido';
	END IF;

	IF p_cantidad_precio_especial IS NOT NULL AND p_cantidad_precio_especial <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cantidad minima para precio especial no es valida';
	END IF;

	IF p_precio_especial IS NULL AND p_cantidad_precio_especial IS NOT NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No debe existir cantidad minima si no existe precio especial';
	END IF;

	IF p_precio_especial IS NOT NULL AND p_cantidad_precio_especial IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Debe existir cantidad minima si existe precio especial';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_articulo
	FROM kath_erp.articulo
	WHERE id_articulo = p_id_articulo;

	IF v_existe_articulo = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_tipo_cliente
	FROM kath_erp.tipo_cliente
	WHERE id = p_id_tipo_cliente
	  AND activo = 1;

	IF v_existe_tipo_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente no existe o esta inactivo';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_relacion
	FROM kath_erp.precios_x_tipocliente
	WHERE id_articulo = p_id_articulo
	  AND id_tipoCliente = p_id_tipo_cliente;

	IF v_existe_relacion > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El articulo ya tiene precio para este tipo de cliente';
	END IF;

	INSERT INTO kath_erp.precios_x_tipocliente (
		id_articulo,
		id_tipoCliente,
		precio,
		precios_especial,
		cant_p_precioEspecial
	) VALUES (
		p_id_articulo,
		p_id_tipo_cliente,
		p_precio,
		p_precio_especial,
		p_cantidad_precio_especial
	);

	SELECT
		LAST_INSERT_ID() AS id,
		'Precio registrado correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertProveedor(
	IN p_id_cuenta_contable INT,
	IN p_rfc VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_correo_electronico VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo proveedor'
BEGIN
	
    DECLARE v_id_proveedor INT UNSIGNED DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_cuenta_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_existe_rfc INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable del proveedor es obligatoria';
	END IF;

	IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC del proveedor es obligatorio';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre del proveedor es obligatorio';
	END IF;

	IF p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El correo electronico del proveedor es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cuenta
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable;

	IF v_existe_cuenta = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada no existe';
	END IF;

	SELECT
		activa,
		ultimo_nivel
	INTO
		v_cuenta_activa,
		v_cuenta_ultimo_nivel
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable
	FOR UPDATE;

	IF v_cuenta_activa = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada se encuentra inactiva';
	END IF;

	IF v_cuenta_ultimo_nivel = FALSE THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada no es de ultimo nivel';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_rfc
	FROM proveedor
	WHERE rfc COLLATE utf8mb4_general_ci = TRIM(p_rfc);

	IF v_existe_rfc > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe un proveedor registrado con el RFC indicado';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_asignada
	FROM proveedor
	WHERE id_cuenta_contable = p_id_cuenta_contable;

	IF v_cuenta_asignada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada ya se encuentra asignada a un proveedor';
	END IF;

	INSERT INTO proveedor (
		id_cuenta_contable,
		rfc,
		nombre,
		descripcion,
		correo_electronico,
		estado,
		ciudad,
		direccion,
		codigo_postal,
		activo
	) VALUES (
		p_id_cuenta_contable,
		UPPER(TRIM(p_rfc)),
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		TRIM(p_correo_electronico),
		NULLIF(TRIM(p_estado), ''),
		NULLIF(TRIM(p_ciudad), ''),
		NULLIF(TRIM(p_direccion), ''),
		NULLIF(TRIM(p_codigo_postal), ''),
		TRUE
	);

	SET v_id_proveedor = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Proveedor registrado correctamente' AS message;
   
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertSucursal(
	IN nombre VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN email VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN estado VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN ciudad VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN direccion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN codigo_postal VARCHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra una nueva sucurlar junto con su respectivo catalogo de productos'
BEGIN
	
    DECLARE v_id_ultima_sucursal INT;
    
    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
	    GET DIAGNOSTICS CONDITION 1
	    v_sqlstate = RETURNED_SQLSTATE,
	    v_errno = MYSQL_ERRNO,
	    v_text = MESSAGE_TEXT;
    
    	SELECT
    		500 AS id,
    		CONCAT('Error ', `v_errno`,' (', `v_sqlstate`, '): ', `v_text`) AS message;
    
		ROLLBACK;        
    END;
    
    START TRANSACTION;
		
        INSERT INTO kath_erp.sucursal(
			nombre,
			descripcion,
			telefono,
			email,
			estado,
			ciudad,
			direccion,
			codigo_postal,
            activo
		)VALUES(
			nombre,
			descripcion,
			telefono,
			email,
			estado,
			ciudad,
			direccion,
			codigo_postal,
            1
		);
        
        SELECT
			s,id_sucursar
		INTO
			v_id_ultima_sucursal
		FROM kath_erp.sucursal AS s
        ORDER BY s.id_sucursar DESC LIMIT 1;
        
        INSERT INTO kath_erp.existencia_x_sucursal(
			id_articulo,
            id_sucursal,
            existencia
        )
        SELECT id_articulo, v_id_ultima_sucursal,0 FROM kath_erp.articulo;
        
    COMMIT;
    
    SELECT 200 AS id, 'Sucursal registrada existosamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertTelefonoCliente(
	IN p_id_cliente INT UNSIGNED,
	IN p_telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un telefono asociado a un cliente'
BEGIN
	
	DECLARE v_id_telefono INT DEFAULT 0;
	DECLARE v_existe_cliente INT DEFAULT 0;
	DECLARE v_existe_telefono INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del cliente no es valido';
	END IF;

	IF p_telefono IS NULL OR TRIM(p_telefono) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono es obligatorio';
	END IF;

	IF CHAR_LENGTH(TRIM(p_telefono)) <> 10 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono debe contener 10 digitos';
	END IF;

	IF TRIM(p_telefono) NOT REGEXP '^[0-9]{10}$' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono solo debe contener numeros';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cliente
	FROM cliente
	WHERE id_cliente = p_id_cliente;

	IF v_existe_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente indicado no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_cliente
	WHERE telefono = TRIM(p_telefono);

	IF v_existe_telefono > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado ya se encuentra registrado';
	END IF;

	INSERT INTO telefono_x_cliente (
		id_cliente,
		telefono
	) VALUES (
		p_id_cliente,
		TRIM(p_telefono)
	);

	SET v_id_telefono = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Telefono registrado correctamente' AS message;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertTelefonoEmpleado(
	IN p_id_empleado INT,
	IN p_telefono_empleado VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci 
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo numero telefonico asociado a un empleado'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;		

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;
		
	INSERT INTO kath_erp.telefono_x_empleado (
		id_empleado,
		telefono 
	)VALUES(
		p_id_empleado,
		p_telefono_empleado
	);
		
	SELECT 200 AS id, 'Numero registrado exitosamente' AS message;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insertTelefonoProveedor(
	IN p_id_proveedor INT UNSIGNED,
	IN p_telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un telefono asociado a un proveedor'
BEGIN
	
	DECLARE v_id_telefono INT DEFAULT 0;
	DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_existe_telefono INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del proveedor no es valido';
	END IF;

	IF p_telefono IS NULL OR TRIM(p_telefono) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono es obligatorio';
	END IF;

	IF CHAR_LENGTH(TRIM(p_telefono)) <> 10 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono debe contener 10 digitos';
	END IF;

	IF TRIM(p_telefono) NOT REGEXP '^[0-9]{10}$' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono solo debe contener numeros';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_proveedor
	FROM proveedor
	WHERE id_proveedor = p_id_proveedor;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor indicado no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_telefono
	FROM telefono_x_proveedor
	WHERE telefono COLLATE utf8mb4_general_ci = TRIM(p_telefono);

	IF v_existe_telefono > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El telefono indicado ya se encuentra registrado';
	END IF;

	INSERT INTO telefono_x_proveedor (
		id_proveedor,
		telefono
	) VALUES (
		p_id_proveedor,
		TRIM(p_telefono)
	);

	SET v_id_telefono = LAST_INSERT_ID();

	COMMIT;

	SELECT
		200 AS id,
		'Telefono registrado correctamente' AS message;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_cuenta_contable(
	IN p_id_cuenta_padre INT,
	IN p_id_rubro INT,
	IN p_clave VARCHAR(25)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(255)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(555)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ultimo_nivel BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Creacion de una nueva cuenta contable'
BEGIN 

DECLARE v_cuenta_existente INT DEFAULT 0;
	DECLARE v_rubro_existente INT DEFAULT 0;
	DECLARE v_nivel TINYINT DEFAULT 1;
	DECLARE v_padre_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_padre_existente INT DEFAULT 0;
	DECLARE v_id_cuenta INT DEFAULT 0;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			@sqlstate = RETURNED_SQLSTATE,
			@errno = MYSQL_ERRNO,
			@text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', @errno, ' (', @sqlstate, '): ', @text) AS message;
	END;

	START TRANSACTION;

	IF p_clave IS NULL OR TRIM(p_clave) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La clave contable es obligatoria';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la cuenta es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_existente
	FROM cuentas_contables
	WHERE clave = TRIM(p_clave);

	IF v_cuenta_existente > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable ya existe';
	END IF;

	SELECT COUNT(*)
	INTO v_rubro_existente
	FROM rubro_cuenta_contable
	WHERE id_rubro = p_id_rubro;

	IF v_rubro_existente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El rubro contable no existe';
	END IF;

	IF p_id_cuenta_padre IS NOT NULL THEN

		SELECT COUNT(*)
		INTO v_padre_existente
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_padre;

		IF v_padre_existente = 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La cuenta superior no existe';
		END IF;

		SELECT
			nivel,
			ultimo_nivel
		INTO
			v_nivel,
			v_padre_ultimo_nivel
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_padre
		FOR UPDATE;

		IF v_padre_ultimo_nivel = TRUE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La cuenta superior es de detalle y no admite subcuentas';
		END IF;

		SET v_nivel = v_nivel + 1;

	ELSE
		SET v_nivel = 1;
	END IF;

	INSERT INTO cuentas_contables (
		id_cuenta_padre,
		fk_id_rubro,
		clave,
		nombre,
		descripcion,
		nivel,
		ultimo_nivel,
		cargo,
		abono,
		activa,
		fecha_modificacion
	) VALUES (
		p_id_cuenta_padre,
		p_id_rubro,
		TRIM(p_clave),
		TRIM(p_nombre),
		NULLIF(TRIM(p_descripcion), ''),
		v_nivel,
		p_ultimo_nivel,
		0,
		0,
		TRUE,
		CURDATE()
	);

	SET v_id_cuenta = LAST_INSERT_ID();

	COMMIT;

	SELECT
		v_id_cuenta AS id,
		'Cuenta contable registrada correctamente' AS message;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_empleado(
    IN p_id_cuenta_contable INT,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_rfc VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_curp VARCHAR(18)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre_completo VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre_corto VARCHAR(10)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_nac DATE,
    IN p_correo_electronico VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_estado VARCHAR(30)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_ciudad VARCHAR(40)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_direccion TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_postal VARCHAR(6)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_contrasenia_hash VARCHAR(255)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un empleado con contraseña hasheada desde Java'
BEGIN
    DECLARE v_id_empleado INT DEFAULT 0;
    DECLARE v_existe_cuenta INT DEFAULT 0;
    DECLARE v_cuenta_asignada INT DEFAULT 0;
    DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
    DECLARE v_ultimo_nivel BOOLEAN DEFAULT FALSE;
    DECLARE v_existe_sucursal INT DEFAULT 0;
    DECLARE v_rfc_duplicado INT DEFAULT 0;
    DECLARE v_curp_duplicada INT DEFAULT 0;
    DECLARE v_nombre_corto_duplicado INT DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        ROLLBACK;

        SELECT
            500 AS id,
            CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
    END;

    START TRANSACTION;

    IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cuenta contable es obligatoria';
    END IF;

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';
    END IF;

    IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC es obligatorio';
    END IF;

    IF p_curp IS NULL OR TRIM(p_curp) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La CURP es obligatoria';
    END IF;

    IF p_nombre_completo IS NULL OR TRIM(p_nombre_completo) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre completo es obligatorio';
    END IF;

    IF p_nombre_corto IS NULL OR TRIM(p_nombre_corto) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre corto es obligatorio';
    END IF;

    IF p_fecha_nac IS NULL OR p_fecha_nac > CURDATE() THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
    END IF;

    IF p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El correo electrónico es obligatorio';
    END IF;

    IF p_contrasenia_hash IS NULL OR TRIM(p_contrasenia_hash) = '' THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El hash de contraseña es obligatorio';
    END IF;

    IF CHAR_LENGTH(TRIM(p_contrasenia_hash)) > 255 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El hash de contraseña excede la longitud permitida';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal;

    IF v_existe_sucursal = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal indicada no existe';
    END IF;

    SELECT COUNT(*)
    INTO v_existe_cuenta
    FROM kath_erp.cuentas_contables
    WHERE id_cuenta = p_id_cuenta_contable;

    IF v_existe_cuenta = 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cuenta contable indicada no existe';
    END IF;

    SELECT activa, ultimo_nivel
    INTO v_cuenta_activa, v_ultimo_nivel
    FROM kath_erp.cuentas_contables
    WHERE id_cuenta = p_id_cuenta_contable
    FOR UPDATE;

    IF v_cuenta_activa = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cuenta contable se encuentra inactiva';
    END IF;

    IF v_ultimo_nivel = FALSE THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cuenta del empleado debe ser una cuenta de detalle';
    END IF;

    SELECT COUNT(*)
    INTO v_cuenta_asignada
    FROM kath_erp.empleados
    WHERE id_cuenta_contable = p_id_cuenta_contable;

    IF v_cuenta_asignada > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cuenta contable ya está asignada a otro empleado';
    END IF;

    SELECT COUNT(*)
    INTO v_rfc_duplicado
    FROM kath_erp.empleados
    WHERE rfc = UPPER(TRIM(p_rfc));

    IF v_rfc_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El RFC ya está registrado';
    END IF;

    SELECT COUNT(*)
    INTO v_curp_duplicada
    FROM kath_erp.empleados
    WHERE curp = UPPER(TRIM(p_curp));

    IF v_curp_duplicada > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La CURP ya está registrada';
    END IF;

    SELECT COUNT(*)
    INTO v_nombre_corto_duplicado
    FROM kath_erp.empleados
    WHERE nombre_corto = TRIM(p_nombre_corto);

    IF v_nombre_corto_duplicado > 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El nombre corto ya está registrado';
    END IF;

    INSERT INTO kath_erp.empleados (
        id_cuenta_contable,
        id_sucursal,
        rfc,
        curp,
        nombre_completo,
        nombre_corto,
        fecha_nac,
        correo_electronico,
        estado,
        ciudad,
        direccion,
        codigo_postal,
        contrasenia,
        activo
    ) VALUES (
        p_id_cuenta_contable,
        p_id_sucursal,
        UPPER(TRIM(p_rfc)),
        UPPER(TRIM(p_curp)),
        TRIM(p_nombre_completo),
        TRIM(p_nombre_corto),
        p_fecha_nac,
        LOWER(TRIM(p_correo_electronico)),
        NULLIF(TRIM(p_estado), ''),
        NULLIF(TRIM(p_ciudad), ''),
        NULLIF(TRIM(p_direccion), ''),
        NULLIF(TRIM(p_codigo_postal), ''),
        TRIM(p_contrasenia_hash),
        TRUE
    );

    SET v_id_empleado = LAST_INSERT_ID();

    COMMIT;

    SELECT
        v_id_empleado AS id,
        'Empleado registrado correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_forma_de_pago(
	IN forma_pago VARCHAR(18)
)
BEGIN
	INSERT INTO formas_de_pago(
		tipo_de_pago,
        activo
    )VALUES(
		forma_pago,
        1
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_nueva_venta(
	idSucursal INT,
    fechaVenta DATE,
    ventaContado BOOLEAN,
    idEmpleado INT,
    idCliente INT,
    subTotal DOUBLE,
    iva DOUBLE,
    total DOUBLE,
    statusVenta BOOLEAN    
)
BEGIN

	INSERT INTO ventas(
		id_sucursal,
        fecha,
        tipo_venta,
        id_empleado,
        id_cliente,
        subtotal,
        iva,
        importe_total,
        status_venta
    )VALUES(
		idSucursal,
        fechaVenta,
        ventaContado,
        idEmpleado,
        idCliente,
        subTotal,
        iva,
        total,
        statusVenta
    );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_nuevo_categoria(
  IN `nombre_m` VARCHAR(60),
  IN `descripcion_m` VARCHAR(255)
)
    COMMENT 'Procedimiento para insertar un nuevo registro'
BEGIN
INSERT INTO categoria_producto(nombre, descripcion)
VALUES(nombre_m, descripcion_m);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_nuevo_empleado(
  IN id_sucursal INT,
  IN rfc_e VARCHAR(13),
  IN curp_e VARCHAR(18),
  IN nombre_completo_e VARCHAR(30),
  IN nombre_corto_e VARCHAR(10),
  IN fecha_nac_e DATE,
  IN correo_electronico_e VARCHAR(30),
  IN estado_e VARCHAR(30),
  IN ciudad_e VARCHAR(40),
  IN direccion_e TEXT,
  IN codigo_postal_e VARCHAR(6)
)
BEGIN
INSERT INTO empleados(
	id_sucursal,
    rfc,
    curp,
    nombre_completo,
    nombre_corto,
    fecha_nac,
    correo_electronico,
    estado,
    ciudad,
    direccion,
    codigo_postal,
    activo
  )
VALUES(
	id_sucursal,
    rfc_e,
    curp_e,
    nombre_completo_e,
    nombre_corto_e,
    fecha_nac_e,
    correo_electronico_e,
    estado_e,
    ciudad_e,
    direccion_e,
    codigo_postal_e,
    1
  );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE insert_nuevo_tipoCliente(
	IN nombre_t VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion_t VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un nuevo tipo de cliente o categoria de cliente'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;		

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;
	
    INSERT INTO tipo_cliente(
		nombre,
        descripcion,
        activo
    )VALUES(
		nombre_t,
        descripcion_t,
        1
    );
    
    SELECT 200 AS id, 'Tipo de cliente registrado con exito' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listArticulos(
	IN p_id_sucursal BIGINT UNSIGNED,
	IN p_tipo_busqueda VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ordenar_por VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_texto_busqueda VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_id_tipo_cliente INT
)
    READS SQL DATA
    COMMENT 'Lista articulos registrados con precio por tipo de cliente y existencia por sucursal'
BEGIN
	
    DECLARE v_tipo_busqueda VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_ordenar_por VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_texto_busqueda VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	SET v_tipo_busqueda = UPPER(TRIM(COALESCE(p_tipo_busqueda, 'TODOS')));
	SET v_ordenar_por = UPPER(TRIM(COALESCE(p_ordenar_por, 'NOMBRE')));
	SET v_texto_busqueda = TRIM(COALESCE(p_texto_busqueda, ''));

	IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la sucursal no es valido';
	END IF;

	IF p_id_tipo_cliente IS NULL OR p_id_tipo_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del tipo de cliente no es valido';
	END IF;

	IF v_tipo_busqueda NOT IN ('TODOS', 'CODIGO', 'NOMBRE', 'PROVEEDOR', 'CATEGORIA', 'DESCRIPCION') THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de busqueda no es valido';
	END IF;

	IF v_ordenar_por NOT IN ('CODIGO', 'NOMBRE', 'PROVEEDOR', 'CATEGORIA') THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El criterio de ordenamiento no es valido';
	END IF;

	SELECT
		art.id_articulo,
		prv.nombre AS nombre_proveedor,
		cat.nombre AS nombre_categoria,
		art.codigo_articulo,
		art.nombre,
		art.es_exento,
		art.costo_unitario,
		pxc.precio,
		COALESCE(exs.existencia, 0) AS existencia,
		art.activo
	FROM articulo AS art
	INNER JOIN proveedor AS prv
		ON prv.id_proveedor = art.id_proveedor
	INNER JOIN categoria_producto AS cat
		ON cat.id_categoria = art.id_categoria
	LEFT JOIN precios_x_tipocliente AS pxc
		ON pxc.id_articulo = art.id_articulo
	   AND pxc.id_tipoCliente = p_id_tipo_cliente
	LEFT JOIN existencia_x_sucursal AS exs
		ON exs.id_articulo = art.id_articulo
	   AND exs.id_sucursal = p_id_sucursal
	WHERE
		v_texto_busqueda = ''
		OR (
			v_tipo_busqueda = 'TODOS'
			AND (
				art.codigo_articulo COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR art.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR prv.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR cat.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
				OR art.descripcion COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
			)
		)
		OR (
			v_tipo_busqueda = 'CODIGO'
			AND art.codigo_articulo COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'NOMBRE'
			AND art.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'PROVEEDOR'
			AND prv.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'CATEGORIA'
			AND cat.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
		OR (
			v_tipo_busqueda = 'DESCRIPCION'
			AND art.descripcion COLLATE utf8mb4_general_ci LIKE CONCAT('%', v_texto_busqueda, '%')
		)
	ORDER BY
		CASE WHEN v_ordenar_por = 'CODIGO' THEN art.codigo_articulo END ASC,
		CASE WHEN v_ordenar_por = 'NOMBRE' THEN art.nombre END ASC,
		CASE WHEN v_ordenar_por = 'PROVEEDOR' THEN prv.nombre END ASC,
		CASE WHEN v_ordenar_por = 'CATEGORIA' THEN cat.nombre END ASC,
		art.nombre ASC;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listArticulosCompraById(
    IN p_id_compra INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los artículos registrados en una compra'
BEGIN
    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    SELECT
        axc.id,
        axc.id_compra,
        axc.id_articulo,
        a.codigo_articulo,
        a.nombre AS nombre_articulo,
        axc.cantidad,
        axc.subtotal
    FROM kath_erp.articulo_x_compra AS axc
    INNER JOIN kath_erp.articulo AS a
        ON axc.id_articulo = a.id_articulo
    WHERE axc.id_compra = p_id_compra
    ORDER BY axc.id ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listCategoriaProducto(
	IN p_nombre_categoria VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Lista categorias de productos filtradas por nombre'
BEGIN

	SELECT
		cp.id_categoria,
		cp.nombre,
		cp.descripcion,
		cp.activo
	FROM categoria_producto AS cp
	WHERE
		p_nombre_categoria IS NULL
		OR TRIM(p_nombre_categoria) = ''
		OR cp.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_categoria), '%')
	ORDER BY cp.nombre ASC;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listClientes(
	IN `nombre_c` VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'listado de clientes registrados filtrado por nombre del cliente'
BEGIN
SELECT
	cliente.id_cliente,
	cliente.rfc,
	tipo_cliente.nombre,
	cuentas_contables.clave,
	cliente.nombre_completo,
	cliente.nombre_corto,
	cliente.correo_electronico,
	cliente.estado,
	cliente.ciudad,
	cliente.direccion,
	cliente.codigo_postal,
	cliente.activo
FROM
	cliente
	INNER JOIN cuentas_contables ON cuentas_contables.id_cuenta = cliente.id_cuenta_contable
	INNER JOIN tipo_cliente ON tipo_cliente.id = cliente.id_tipoCliente
WHERE
	cliente.nombre_completo LIKE CONCAT('%', nombre_c, '%'); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listCmbCategoriaProducto()
    READS SQL DATA
    COMMENT 'Lista categorias activas para combo'
BEGIN

	SELECT
		cp.id_categoria,
		cp.nombre
	FROM categoria_producto AS cp
	WHERE cp.activo = TRUE
	ORDER BY cp.nombre ASC;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listCmbProveeodor()
BEGIN
	
    SELECT 
    	p.id_proveedor AS id,
    	p.nombre 
    FROM kath_erp.proveedor  AS p
    WHERE p.activo = true;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listCompras(
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_fecha_factura_inicio DATE,
    IN p_fecha_factura_fin DATE,
    IN p_folio_factura VARCHAR(13),
    IN p_tipo_compra BOOLEAN
)
    READS SQL DATA
    COMMENT 'Lista compras activas pertenecientes a una sucursal con filtros opcionales'
BEGIN

    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria para consultar compras';
    END IF;

    SELECT
        c.id_compra,
        c.id_empleado,
        c.id_proveedor,
        c.id_sucursal,
        c.folio_factura,
        c.fecha_factura,
        c.fecha_compra,
        c.tipo_compra,
        CASE
            WHEN c.tipo_compra = TRUE THEN 'Crédito'
            ELSE 'Contado'
        END AS tipo_compra_descripcion,
        c.subtotal,
        c.iva,
        (c.subtotal + c.iva) AS importe_total,
        c.activo
    FROM kath_erp.compras AS c
    WHERE c.activo = TRUE

      -- La sucursal deja de ser opcional.
      AND c.id_sucursal = p_id_sucursal

      AND (
            p_id_proveedor IS NULL
            OR p_id_proveedor = 0
            OR c.id_proveedor = p_id_proveedor
          )

      AND (
            p_fecha_factura_inicio IS NULL
            OR c.fecha_factura >= p_fecha_factura_inicio
          )

      AND (
            p_fecha_factura_fin IS NULL
            OR c.fecha_factura <= p_fecha_factura_fin
          )

      AND (
            p_folio_factura IS NULL
            OR TRIM(p_folio_factura) = ''
            OR c.folio_factura LIKE CONCAT('%', TRIM(p_folio_factura), '%')
          )

      AND (
            p_tipo_compra IS NULL
            OR c.tipo_compra = p_tipo_compra
          )

    ORDER BY
        c.fecha_compra DESC,
        c.id_compra DESC;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listCuentasContablesEnDialog(
	IN nombre_cuenta VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Muestra un listado reducido de columnas para ser consultado desde un dialog de seleccion rapida'
BEGIN
	
	SELECT 
		cc.id_cuenta,
		cc.clave,
		cc.nombre
	FROM
		cuentas_contables AS cc
	WHERE
		cc.nombre LIKE CONCAT('%',nombre_cuenta,'%') AND cc.ultimo_nivel = 1;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listExistenciaGlobalArticulo(
	IN p_id_articulo INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'CONSULTA LA EXISTENCIA DE UN ARTICULO EN TODAS LAS SUCURSALES REGISTRADAS'
BEGIN
	
	SELECT 
		s.id_sucursar,
		s.nombre,
		s.direccion,
		exs.existencia 
	FROM kath_erp.existencia_x_sucursal AS exs
	INNER JOIN kath_erp.sucursal AS s on exs.id_sucursal = s.id_sucursar 
	WHERE exs.id_articulo = p_id_articulo;
		
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listPreciosArticuloTipoCliente(
    IN p_id_articulo INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los precios registrados de un artículo por tipo de cliente activo'
BEGIN

    SELECT
        tc.id AS id_tipo_cliente,
        tc.nombre AS tipo_cliente,
        patc.precio,
        patc.precios_especial,
        patc.cant_p_precioEspecial 
    FROM kath_erp.precios_x_tipocliente AS patc
    INNER JOIN kath_erp.tipo_cliente AS tc ON patc.id_tipoCliente  = tc.id 
    WHERE patc.id_articulo = p_id_articulo
      AND tc.activo = 1
    ORDER BY tc.nombre ASC;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listProveedores(
	IN p_nombre_proveedor VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'Lista proveedores por nombre'
BEGIN


	 SELECT
		p.id_proveedor,
		p.rfc,
		p.nombre,
		cc.clave,
		p.descripcion,
		p.correo_electronico,
		p.estado,
		p.ciudad,
		p.direccion,
		p.codigo_postal,
		p.activo
	FROM proveedor AS p
	INNER JOIN cuentas_contables AS cc
		ON cc.id_cuenta = p.id_cuenta_contable
	WHERE
		(
			p_nombre_proveedor IS NULL
			OR TRIM(p_nombre_proveedor) = ''
			OR p.nombre COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_proveedor), '%')
			OR p.rfc COLLATE utf8mb4_general_ci LIKE CONCAT('%', TRIM(p_nombre_proveedor), '%')
		)
	ORDER BY p.nombre ASC;

	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listTelefonoProveedor(
	IN p_id_proveedor INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los telefonos asociados a un proveedor'
BEGIN
	
	SELECT
		txp.id_telefono,
		txp.telefono
	FROM telefono_x_proveedor AS txp
	WHERE txp.id_proveedor = p_id_proveedor
	ORDER BY txp.id_telefono ASC;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listTelefonosCliente(
	IN p_id_cliente INT UNSIGNED
)
    READS SQL DATA
    COMMENT 'Lista los telefonos asociados a un cliente'
BEGIN
	
	SELECT
		txc.id_telefono,		
		txc.telefono
	FROM telefono_x_cliente AS txc
	WHERE txc.id_cliente = p_id_cliente
	ORDER BY txc.id_telefono ASC;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE listTelefonosDeEmpleadoByID(
	IN id_empleado INT
)
    READS SQL DATA
    COMMENT 'EMPLEADO PARA VER LOS TELEFONOS ASOCIADOS A UN EMPLEADO AL MOMENTO DE VISUALIZAR SUS DATOS EN FORMULARIO'
BEGIN
	
	SELECT 
		txe.id_telefono,
		txe.telefono 
	FROM
		kath_erp.telefono_x_empleado AS txe
	WHERE 
		txe.id_empleado = id_empleado;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE list_cmbGrupoContable()
    READS SQL DATA
    COMMENT 'Listado de todos los grupos contables registrados para un ComboBox'
BEGIN
	
	SELECT 
		gc.id_grupo,
		gc.nombre_grupo 
	FROM
		kath_erp.grupo_contable AS gc;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE list_cmbRubroCuentasContables(
	IN `id_grupo_contable` INT
)
    COMMENT 'LISTADO DE RUBROS CONTABLES PARA UN COMBOBOX'
BEGIN
	
	SELECT
		rcc.id_rubro,
		rcc.nombre 
	FROM
		kath_erp.rubro_cuenta_contable AS rcc
	WHERE rcc.fk_id_grupo_contable = `id_grupo_contable`;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE sp_consultarEmpleadoPorRFC(IN `rfc` VARCHAR(13) CHARSET utf8)
BEGIN

	SELECT

    	empleados.curp,

        empleados.nombre_completo,

        empleados.nombre_corto,

        empleados.fecha_nac,

        empleados.correo_electronico,

        empleados.estado,

        empleados.ciudad,

        empleados.direccion,

        empleados.codigo_postal,

        empleados.contrasenia

	FROM

    	empleados

    WHERE

    	empleados.rfc = rfc;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE sumarExistenciaSucursalCompra(
    IN p_id_compra INT UNSIGNED,
    IN p_id_articulo INT UNSIGNED,
    IN p_cantidad INT
)
    MODIFIES SQL DATA
    COMMENT 'Suma existencia usando directamente la sucursal registrada en la compra'
BEGIN
    DECLARE v_id_sucursal BIGINT UNSIGNED DEFAULT 0;
    DECLARE v_id_existencia INT DEFAULT 0;
    DECLARE v_registros_existencia INT DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;
    END;

    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';
    END IF;

    IF p_id_articulo IS NULL OR p_id_articulo <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El artículo es obligatorio';
    END IF;

    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad a sumar debe ser mayor a cero';
    END IF;


    /*
     * IMPORTANTE:
     * La sucursal sale directamente de compras.id_sucursal.
     *
     * Ya NO:
     * compras -> empleados -> sucursal
     */

    SELECT c.id_sucursal
    INTO v_id_sucursal
    FROM kath_erp.compras AS c
    WHERE c.id_compra = p_id_compra
      AND c.activo = TRUE
    LIMIT 1;

    IF v_id_sucursal IS NULL OR v_id_sucursal <= 0 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'No se pudo determinar la sucursal de la compra';
    END IF;


    SELECT COUNT(*)
    INTO v_registros_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = p_id_articulo
      AND id_sucursal = v_id_sucursal;

    IF v_registros_existencia > 1 THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Existe más de un registro de existencia para el artículo y sucursal';
    END IF;


    IF v_registros_existencia = 0 THEN

        INSERT INTO kath_erp.existencia_x_sucursal (
            id_articulo,
            id_sucursal,
            existencia
        )
        VALUES (
            p_id_articulo,
            v_id_sucursal,
            p_cantidad
        );

    ELSE

        SELECT id
        INTO v_id_existencia
        FROM kath_erp.existencia_x_sucursal
        WHERE id_articulo = p_id_articulo
          AND id_sucursal = v_id_sucursal
        LIMIT 1
        FOR UPDATE;

        UPDATE kath_erp.existencia_x_sucursal
        SET existencia =
            COALESCE(existencia, 0) + p_cantidad
        WHERE id = v_id_existencia;

    END IF;


    SELECT
        200 AS id,
        'Existencia actualizada correctamente' AS message;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateArticulo(
	IN p_id_articulo INT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_id_categoria INT UNSIGNED,
    IN p_codigo_articulo VARCHAR(65) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_codigo_sat VARCHAR(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_unidad_sat VARCHAR(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_nombre VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_descripcion VARCHAR(555) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_es_exento TINYINT,
    IN p_costo_unitario DECIMAL(18,2),
    IN p_activo TINYINT
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos generales de un artículo existente'
BEGIN
	
    IF NOT EXISTS (
        SELECT 1
        FROM kath_erp.articulo AS a
        WHERE a.id_articulo = p_id_articulo
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el artículo indicado' AS message;

    ELSEIF EXISTS (
        SELECT 1
        FROM kath_erp.articulo AS a
        WHERE a.codigo_articulo = p_codigo_articulo
          AND a.id_articulo <> p_id_articulo
    ) THEN

        SELECT
            409 AS id,
            'Ya existe otro artículo registrado con el mismo código' AS message;

    ELSE

        UPDATE kath_erp.articulo AS a
        SET
            a.id_proveedor = p_id_proveedor,
            a.id_categoria = p_id_categoria,
            a.codigo_articulo = p_codigo_articulo,
            a.codigo_sat = p_codigo_sat,
            a.unidad_sat = p_unidad_sat,
            a.nombre = p_nombre,
            a.descripcion = p_descripcion,
            a.es_exento = p_es_exento,
            a.costo_unitario = p_costo_unitario,
            a.activo = p_activo
        WHERE a.id_articulo = p_id_articulo;

        SELECT
            200 AS id,
            'Artículo actualizado correctamente' AS message;

    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateArticuloCompra(
    IN p_id_detalle_compra INT UNSIGNED,
    IN p_cantidad INT,
    IN p_subtotal DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza un artículo comprado y ajusta existencia en la sucursal registrada en la compra'
BEGIN
    DECLARE v_id_compra INT UNSIGNED DEFAULT 0;
    DECLARE v_id_articulo INT UNSIGNED DEFAULT 0;
    DECLARE v_cantidad_actual INT DEFAULT 0;

    DECLARE v_delta INT DEFAULT 0;

    DECLARE v_existencia_actual INT DEFAULT 0;
    DECLARE v_id_existencia INT DEFAULT 0;

    DECLARE v_id_sucursal BIGINT UNSIGNED DEFAULT 0;

    DECLARE v_fecha_compra DATE;

    DECLARE v_ventas_posteriores INT DEFAULT 0;
    DECLARE v_registros_existencia INT DEFAULT 0;

    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;
    END;


    IF p_id_detalle_compra IS NULL
       OR p_id_detalle_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El detalle de compra es obligatorio';

    END IF;


    IF p_cantidad IS NULL OR p_cantidad <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La cantidad debe ser mayor a cero';

    END IF;


    IF p_subtotal IS NULL OR p_subtotal < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal no puede ser negativo';

    END IF;


    /*
     * Se obtiene directamente c.id_sucursal.
     * Ya no necesitamos JOIN empleados.
     */

    SELECT
        axc.id_compra,
        axc.id_articulo,
        axc.cantidad,
        c.fecha_compra,
        c.id_sucursal
    INTO
        v_id_compra,
        v_id_articulo,
        v_cantidad_actual,
        v_fecha_compra,
        v_id_sucursal
    FROM kath_erp.articulo_x_compra AS axc
    INNER JOIN kath_erp.compras AS c
        ON axc.id_compra = c.id_compra
    WHERE axc.id = p_id_detalle_compra
      AND c.activo = TRUE
    LIMIT 1
    FOR UPDATE;


    IF v_id_compra IS NULL OR v_id_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El detalle de compra no existe o la compra está inactiva';

    END IF;


    /*
     * Esta parte sigue dependiendo de cómo ventas determina su sucursal.
     *
     * Por ahora se mantiene porque no me compartiste una relación directa
     * ventas -> sucursal.
     */

    SELECT COUNT(*)
    INTO v_ventas_posteriores
    FROM kath_erp.articulo_x_venta AS axv
    INNER JOIN kath_erp.ventas AS v
        ON axv.id_venta = v.id_venta
    INNER JOIN kath_erp.empleados AS emp_venta
        ON v.id_empleado = emp_venta.id_empleado
    WHERE axv.id_articulo = v_id_articulo
      AND emp_venta.id_sucursal = v_id_sucursal
      AND v.fecha > v_fecha_compra
      AND v.status_venta = TRUE;


    IF v_ventas_posteriores > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede modificar el artículo porque ya tiene ventas posteriores a la compra';

    END IF;


    SELECT COUNT(*)
    INTO v_registros_existencia
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal;


    IF v_registros_existencia = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No existe registro de existencia para el artículo y sucursal';

    END IF;


    IF v_registros_existencia > 1 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'Existe más de un registro de existencia para el artículo y sucursal';

    END IF;


    SELECT
        id,
        COALESCE(existencia, 0)
    INTO
        v_id_existencia,
        v_existencia_actual
    FROM kath_erp.existencia_x_sucursal
    WHERE id_articulo = v_id_articulo
      AND id_sucursal = v_id_sucursal
    LIMIT 1
    FOR UPDATE;


    SET v_delta =
        p_cantidad - v_cantidad_actual;


    IF v_existencia_actual + v_delta < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'No se puede modificar la compra porque la existencia quedaría negativa';

    END IF;


    UPDATE kath_erp.articulo_x_compra
    SET
        cantidad = p_cantidad,
        subtotal = p_subtotal
    WHERE id = p_id_detalle_compra;


    UPDATE kath_erp.existencia_x_sucursal
    SET existencia =
        v_existencia_actual + v_delta
    WHERE id = v_id_existencia;


    SELECT
        p_id_detalle_compra AS id,
        'Artículo de compra actualizado correctamente' AS message;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateCategoriaProducto(
	IN p_id_categoria INT UNSIGNED,
	IN p_nombre VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza una categoria de producto'
BEGIN
	
	
	DECLARE v_existe_categoria INT DEFAULT 0;
	DECLARE v_existe_nombre INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_categoria IS NULL OR p_id_categoria <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la categoria no es valido';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la categoria es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_categoria
	FROM categoria_producto
	WHERE id_categoria = p_id_categoria;

	IF v_existe_categoria = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La categoria indicada no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_nombre
	FROM categoria_producto
	WHERE nombre COLLATE utf8mb4_general_ci = TRIM(p_nombre)
	  AND id_categoria <> p_id_categoria;

	IF v_existe_nombre > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe otra categoria con el nombre indicado';
	END IF;

	UPDATE categoria_producto
	SET
		nombre = TRIM(p_nombre),
		descripcion = NULLIF(TRIM(p_descripcion), ''),
		activo = COALESCE(p_activo, TRUE)
	WHERE id_categoria = p_id_categoria;

	COMMIT;

	SELECT
		200 AS id,
		'Categoria actualizada correctamente' AS message;
	
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateCliente(
	IN p_id_cliente INT UNSIGNED,
	IN p_id_tipoCliente INT,
	IN p_id_cuenta_contable INT,
	IN p_rfc VARCHAR(13)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_completo VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_corto VARCHAR(10)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_fecha_nac DATE,
	IN p_correo_electronico VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza y valida los datos de un cliente existente, si la cuenta contable presenta saldos el registro contable no puede ser modificado'
BEGIN
	
    DECLARE v_existe_cliente INT DEFAULT 0;
	DECLARE v_existe_tipo_cliente INT DEFAULT 0;
	DECLARE v_cuenta_actual INT DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;
	DECLARE v_rfc_duplicado INT DEFAULT 0;

	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_ultimo_nivel BOOLEAN DEFAULT FALSE;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT(
				'Error ',
				v_errno,
				' (',
				v_sqlstate,
				'): ',
				v_text
			) AS message;
	END;
		
	START TRANSACTION;

	
	IF p_id_cliente IS NULL OR p_id_cliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del cliente no es válido';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_cliente
	FROM cliente
	WHERE id_cliente = p_id_cliente;

	IF v_existe_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El cliente indicado no existe';
	END IF;

	
	SELECT id_cuenta_contable
	INTO v_cuenta_actual
	FROM cliente
	WHERE id_cliente = p_id_cliente
	FOR UPDATE;

	
	IF p_id_tipoCliente IS NULL OR p_id_tipoCliente <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente es obligatorio';
	END IF;

	IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable es obligatoria';
	END IF;

	IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC es obligatorio';
	END IF;

	IF p_nombre_completo IS NULL
			OR TRIM(p_nombre_completo) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre completo es obligatorio';
	END IF;

	IF p_nombre_corto IS NULL
			OR TRIM(p_nombre_corto) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre corto es obligatorio';
	END IF;

	IF p_fecha_nac IS NULL THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento es obligatoria';
	END IF;

	IF p_fecha_nac > CURDATE() THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
	END IF;

	IF p_correo_electronico IS NULL
			OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El correo electrónico es obligatorio';
	END IF;

	/*
	 * Validación del tipo de cliente.
	 */
	SELECT COUNT(*)
	INTO v_existe_tipo_cliente
	FROM tipo_cliente
	WHERE id = p_id_tipoCliente;

	IF v_existe_tipo_cliente = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El tipo de cliente indicado no existe';
	END IF;

	/*
	 * Validación de RFC duplicado.
	 */
	SELECT COUNT(*)
	INTO v_rfc_duplicado
	FROM cliente
	WHERE rfc = UPPER(TRIM(p_rfc))
	  AND id_cliente <> p_id_cliente;

	IF v_rfc_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC ya pertenece a otro cliente';
	END IF;

	/*
	 * Si cambia la cuenta contable, se valida el saldo de la cuenta actual y
	 * posteriormente se valida la nueva cuenta.
	 */
	IF p_id_cuenta_contable <> v_cuenta_actual THEN

		SELECT
			cargo,
			abono
		INTO
			v_cargo,
			v_abono
		FROM cuentas_contables
		WHERE id_cuenta = v_cuenta_actual
		FOR UPDATE;

		SET v_saldo = ROUND(
			COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
			2
		);

		IF v_saldo <> 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede cambiar la cuenta contable porque tiene saldo pendiente';
		END IF;

		SELECT COUNT(*)
		INTO v_existe_cuenta
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable;

		IF v_existe_cuenta = 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable no existe';
		END IF;

		SELECT
			activa,
			ultimo_nivel
		INTO
			v_cuenta_activa,
			v_ultimo_nivel
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable
		FOR UPDATE;

		IF v_cuenta_activa = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable se encuentra inactiva';
		END IF;

		IF v_ultimo_nivel = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta debe ser una cuenta de detalle';
		END IF;

		/*
		 * Validación de exclusividad de la nueva cuenta.
		 */
		SELECT COUNT(*)
		INTO v_cuenta_asignada
		FROM cliente
		WHERE id_cuenta_contable = p_id_cuenta_contable
		  AND id_cliente <> p_id_cliente;

		IF v_cuenta_asignada > 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable ya está asignada a otro cliente';
		END IF;

	END IF;

	/*
	 * Actualización del cliente.
	 */
	UPDATE cliente
	SET
		id_tipoCliente = p_id_tipoCliente,
		id_cuenta_contable = p_id_cuenta_contable,
		rfc = UPPER(TRIM(p_rfc)),
		nombre_completo = TRIM(p_nombre_completo),
		nombre_corto = TRIM(p_nombre_corto),
		fecha_nac = p_fecha_nac,
		correo_electronico = LOWER(TRIM(p_correo_electronico)),
		estado = NULLIF(TRIM(p_estado), ''),
		ciudad = NULLIF(TRIM(p_ciudad), ''),
		direccion = NULLIF(TRIM(p_direccion), ''),
		codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
		activo = p_activo
	WHERE id_cliente = p_id_cliente;

	COMMIT;

	SELECT
		200 AS id,
		'Cliente actualizado correctamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateCompra(
    IN p_id_compra INT UNSIGNED,
    IN p_id_empleado INT UNSIGNED,
    IN p_id_proveedor INT UNSIGNED,
    IN p_id_sucursal BIGINT UNSIGNED,
    IN p_folio_factura VARCHAR(13)
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN p_fecha_factura DATE,
    IN p_fecha_compra DATE,
    IN p_tipo_compra BOOLEAN,
    IN p_subtotal DOUBLE,
    IN p_iva DOUBLE
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza compra validando que pertenezca a la sucursal de la sesión. El update rehabilita el registro'
BEGIN

    DECLARE v_existe_compra INT DEFAULT 0;
    DECLARE v_existe_empleado INT DEFAULT 0;
    DECLARE v_existe_proveedor INT DEFAULT 0;
    DECLARE v_existe_sucursal INT DEFAULT 0;

    DECLARE v_folio_duplicado INT DEFAULT 0;

    DECLARE v_id_sucursal_compra BIGINT UNSIGNED DEFAULT 0;


    DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT
        CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;


    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN

        GET DIAGNOSTICS CONDITION 1
            v_sqlstate = RETURNED_SQLSTATE,
            v_errno = MYSQL_ERRNO,
            v_text = MESSAGE_TEXT;

        SELECT
            500 AS id,
            CONCAT(
                'Error ',
                v_errno,
                ' (',
                v_sqlstate,
                '): ',
                v_text
            ) AS message;

    END;


    IF p_id_compra IS NULL OR p_id_compra <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra es obligatoria';

    END IF;


    IF p_id_empleado IS NULL OR p_id_empleado <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El empleado es obligatorio';

    END IF;


    IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El proveedor es obligatorio';

    END IF;


    IF p_id_sucursal IS NULL OR p_id_sucursal <= 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La sucursal es obligatoria';

    END IF;


    IF p_folio_factura IS NULL
       OR TRIM(p_folio_factura) = '' THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El folio de factura es obligatorio';

    END IF;


    IF CHAR_LENGTH(TRIM(p_folio_factura)) > 13 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El folio de factura no puede exceder 13 caracteres';

    END IF;


    IF p_fecha_factura IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de factura es obligatoria';

    END IF;


    IF p_fecha_compra IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de compra es obligatoria';

    END IF;


    IF p_tipo_compra IS NULL THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El tipo de compra es obligatorio';

    END IF;


    IF p_subtotal IS NULL OR p_subtotal < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El subtotal no puede ser negativo';

    END IF;


    IF p_iva IS NULL OR p_iva < 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'El IVA no puede ser negativo';

    END IF;


    /*
     * Obtener la sucursal histórica directamente de la compra.
     */

    SELECT
        COUNT(*),
        COALESCE(MAX(id_sucursal), 0)
    INTO
        v_existe_compra,
        v_id_sucursal_compra
    FROM kath_erp.compras
    WHERE id_compra = p_id_compra;


    IF v_existe_compra = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La compra indicada no existe';

    END IF;


    /*
     * No permitimos editar una compra perteneciente
     * a otra sucursal.
     */

    IF v_id_sucursal_compra <> p_id_sucursal THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La compra indicada no pertenece a la sucursal actual';

    END IF;


    SELECT COUNT(*)
    INTO v_existe_sucursal
    FROM kath_erp.sucursal
    WHERE id_sucursar = p_id_sucursal
      AND activo = TRUE;


    IF v_existe_sucursal = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'La sucursal indicada no existe o está inactiva';

    END IF;


    SELECT COUNT(*)
    INTO v_existe_empleado
    FROM kath_erp.empleados
    WHERE id_empleado = p_id_empleado
      AND activo = TRUE;


    IF v_existe_empleado = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El empleado indicado no existe o está inactivo';

    END IF;


    SELECT COUNT(*)
    INTO v_existe_proveedor
    FROM kath_erp.proveedor
    WHERE id_proveedor = p_id_proveedor;


    IF v_existe_proveedor = 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El proveedor indicado no existe';

    END IF;


    SELECT COUNT(*)
    INTO v_folio_duplicado
    FROM kath_erp.compras
    WHERE id_proveedor = p_id_proveedor
      AND folio_factura = TRIM(p_folio_factura)
      AND id_compra <> p_id_compra
      AND activo = TRUE;


    IF v_folio_duplicado > 0 THEN

        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT =
                'El folio de factura ya está registrado para este proveedor';

    END IF;


    /*
     * Importante:
     * id_sucursal NO se actualiza.
     *
     * La compra permanece ligada a la sucursal
     * en la que fue originalmente realizada.
     */

    UPDATE kath_erp.compras
    SET
        id_empleado = p_id_empleado,
        id_proveedor = p_id_proveedor,
        folio_factura = TRIM(p_folio_factura),
        fecha_factura = p_fecha_factura,
        fecha_compra = p_fecha_compra,
        tipo_compra = p_tipo_compra,
        subtotal = p_subtotal,
        iva = p_iva,
        activo = TRUE
    WHERE id_compra = p_id_compra
      AND id_sucursal = p_id_sucursal;


    SELECT
        p_id_compra AS id,
        'Compra actualizada correctamente' AS message;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updatePrecioPorTipoCliente(
    IN p_id_articulo INT UNSIGNED,
    IN p_id_tipoCliente INT,
    IN p_precio DECIMAL(18,2),
    IN p_precios_especial DECIMAL(18,2),
    IN p_cant_p_precioEspecial INT
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza el precio de un artículo por tipo de cliente'
BEGIN

    IF NOT EXISTS (
        SELECT 1
        FROM kath_erp.articulo AS a
        WHERE a.id_articulo = p_id_articulo
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el artículo indicado' AS message;

    ELSEIF NOT EXISTS (
        SELECT 1
        FROM kath_erp.tipo_cliente AS tc
        WHERE tc.id = p_id_tipoCliente
          AND tc.activo = 1
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el tipo de cliente activo indicado' AS message;

    ELSEIF NOT EXISTS (
        SELECT 1
        FROM kath_erp.precios_x_tipocliente AS pxt
        WHERE pxt.id_articulo = p_id_articulo
          AND pxt.id_tipoCliente = p_id_tipoCliente
    ) THEN

        SELECT
            404 AS id,
            'No se encontró el precio del artículo para el tipo de cliente indicado' AS message;

    ELSE

        UPDATE kath_erp.precios_x_tipocliente AS pxt
        SET
            pxt.precio = p_precio,
            pxt.precios_especial = p_precios_especial,
            pxt.cant_p_precioEspecial = p_cant_p_precioEspecial
        WHERE pxt.id_articulo = p_id_articulo
          AND pxt.id_tipoCliente = p_id_tipoCliente;

        SELECT
            200 AS id,
            'Precio por tipo de cliente actualizado correctamente' AS message;

    END IF;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateProveedor(
	
	IN p_id_proveedor INT UNSIGNED,
	IN p_id_cuenta_contable INT,
	IN p_rfc VARCHAR(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_correo_electronico VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_activo BOOLEAN

)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos de un proveedor'
BEGIN
	
    DECLARE v_existe_proveedor INT DEFAULT 0;
	DECLARE v_id_cuenta_actual INT DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_cuenta_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_existe_rfc INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;

	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_saldo DOUBLE DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	IF p_id_proveedor IS NULL OR p_id_proveedor <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador del proveedor no es valido';
	END IF;

	IF p_id_cuenta_contable IS NULL OR p_id_cuenta_contable <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable del proveedor es obligatoria';
	END IF;

	IF p_rfc IS NULL OR TRIM(p_rfc) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC del proveedor es obligatorio';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre del proveedor es obligatorio';
	END IF;

	IF p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El correo electronico del proveedor es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_proveedor
	FROM proveedor
	WHERE id_proveedor = p_id_proveedor;

	IF v_existe_proveedor = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El proveedor indicado no existe';
	END IF;

	SELECT id_cuenta_contable
	INTO v_id_cuenta_actual
	FROM proveedor
	WHERE id_proveedor = p_id_proveedor
	FOR UPDATE;

	SELECT COUNT(*)
	INTO v_existe_rfc
	FROM proveedor
	WHERE
		rfc COLLATE utf8mb4_general_ci = TRIM(p_rfc)
		AND id_proveedor <> p_id_proveedor;

	IF v_existe_rfc > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Ya existe otro proveedor registrado con el RFC indicado';
	END IF;

	IF v_id_cuenta_actual <> p_id_cuenta_contable THEN

		SELECT
			cargo,
			abono
		INTO
			v_cargo,
			v_abono
		FROM cuentas_contables
		WHERE id_cuenta = v_id_cuenta_actual
		FOR UPDATE;

		SET v_saldo = ROUND(
			COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
			2
		);

		IF v_saldo <> 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede cambiar la cuenta contable del proveedor porque la cuenta actual tiene saldo';
		END IF;

		SELECT COUNT(*)
		INTO v_existe_cuenta
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable;

		IF v_existe_cuenta = 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada no existe';
		END IF;

		SELECT
			activa,
			ultimo_nivel
		INTO
			v_cuenta_activa,
			v_cuenta_ultimo_nivel
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable
		FOR UPDATE;

		IF v_cuenta_activa = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada se encuentra inactiva';
		END IF;

		IF v_cuenta_ultimo_nivel = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada no es de ultimo nivel';
		END IF;

		SELECT COUNT(*)
		INTO v_cuenta_asignada
		FROM proveedor
		WHERE
			id_cuenta_contable = p_id_cuenta_contable
			AND id_proveedor <> p_id_proveedor;

		IF v_cuenta_asignada > 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable indicada ya se encuentra asignada a otro proveedor';
		END IF;

	END IF;

	IF p_activo = FALSE THEN

		SELECT
			cargo,
			abono
		INTO
			v_cargo,
			v_abono
		FROM cuentas_contables
		WHERE id_cuenta = v_id_cuenta_actual
		FOR UPDATE;

		SET v_saldo = ROUND(
			COALESCE(v_cargo, 0) - COALESCE(v_abono, 0),
			2
		);

		IF v_saldo <> 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede inhabilitar el proveedor porque su cuenta contable tiene saldo';
		END IF;

	END IF;

	UPDATE proveedor
	SET
		id_cuenta_contable = p_id_cuenta_contable,
		rfc = UPPER(TRIM(p_rfc)),
		nombre = TRIM(p_nombre),
		descripcion = NULLIF(TRIM(p_descripcion), ''),
		correo_electronico = TRIM(p_correo_electronico),
		estado = NULLIF(TRIM(p_estado), ''),
		ciudad = NULLIF(TRIM(p_ciudad), ''),
		direccion = NULLIF(TRIM(p_direccion), ''),
		codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
		activo = p_activo
	WHERE id_proveedor = p_id_proveedor;

	COMMIT;

	SELECT
		200 AS id,
		'Proveedor actualizado correctamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE updateSucursal(
	IN id_sucursal INT,
	IN nombre VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN telefono VARCHAR(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN email VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN estado VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN ciudad VARCHAR(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN direccion VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN codigo_postal VARCHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos de una Sucursal ya registrada'
BEGIN
	
	DECLARE v_sqlstate CHAR(5);
    DECLARE v_errno INT;
    DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
	    GET DIAGNOSTICS CONDITION 1
	    v_sqlstate = RETURNED_SQLSTATE,
	    v_errno = MYSQL_ERRNO,
	    v_text = MESSAGE_TEXT;
    
    	SELECT
    		500 AS id,
    		CONCAT('Error ', `v_errno`,' (', `v_sqlstate`, '): ', `v_text`) AS message;
    		
    END;
	
    UPDATE kath_erp.sucursal 
    SET
		nombre = nombre,
        descripcion = descripcion,
        telefono = telefono,
        email = email,
        estado = estado,
        ciudad = ciudad,
        direccion = direccion,
        codigo_postal = codigo_postal,
        activo = 1
    WHERE sucursal.id_sucursar = id_sucursal;
    
    SELECT 200 AS id, 'Sucursal Actualizada exitosamente' AS message;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE update_cuenta_contable(
	IN p_id_cuenta INT,
	IN p_clave VARCHAR(25)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre VARCHAR(255)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_descripcion VARCHAR(555)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ultimo_nivel BOOLEAN,
	IN p_activa BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza datos permitidos de una cuenta contable'
BEGIN
	DECLARE v_cuenta_existe INT DEFAULT 0;
	DECLARE v_clave_actual VARCHAR(25);
	DECLARE v_ultimo_nivel_actual BOOLEAN;
	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_total_hijas INT DEFAULT 0;
	DECLARE v_hijas_activas INT DEFAULT 0;
	DECLARE v_clave_duplicada INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT(
				'Error ',
				v_errno,
				' (',
				v_sqlstate,
				'): ',
				v_text
			) AS message;
	END;

	START TRANSACTION;

	IF p_id_cuenta IS NULL OR p_id_cuenta <= 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El identificador de la cuenta es inválido';
	END IF;

	IF p_clave IS NULL OR TRIM(p_clave) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La clave contable es obligatoria';
	END IF;

	IF p_nombre IS NULL OR TRIM(p_nombre) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El nombre de la cuenta es obligatorio';
	END IF;

	SELECT COUNT(*)
	INTO v_cuenta_existe
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta;

	IF v_cuenta_existe = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable no existe';
	END IF;

	SELECT
		clave,
		ultimo_nivel,
		cargo,
		abono
	INTO
		v_clave_actual,
		v_ultimo_nivel_actual,
		v_cargo,
		v_abono
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta
	FOR UPDATE;

	SELECT COUNT(*)
	INTO v_clave_duplicada
	FROM cuentas_contables
	WHERE clave = TRIM(p_clave)
	  AND id_cuenta <> p_id_cuenta;

	IF v_clave_duplicada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La clave contable ya pertenece a otra cuenta';
	END IF;

	SELECT COUNT(*)
	INTO v_total_hijas
	FROM cuentas_contables
	WHERE id_cuenta_padre = p_id_cuenta;

	SELECT COUNT(*)
	INTO v_hijas_activas
	FROM cuentas_contables
	WHERE id_cuenta_padre = p_id_cuenta
	  AND activa = TRUE;

	/*
	 * Una cuenta con movimientos no puede cambiar de clave.
	 */
	IF TRIM(p_clave) <> v_clave_actual
	   AND (v_cargo <> 0 OR v_abono <> 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede modificar la clave de una cuenta con movimientos';
	END IF;

	/*
	 * Una cuenta con subcuentas no puede convertirse en cuenta de detalle.
	 */
	IF p_ultimo_nivel = TRUE
	   AND v_total_hijas > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Una cuenta con subcuentas no puede convertirse en cuenta de detalle';
	END IF;

	/*
	 * Una cuenta con movimientos no puede cambiar su tipo operativo.
	 */
	IF p_ultimo_nivel <> v_ultimo_nivel_actual
	   AND (v_cargo <> 0 OR v_abono <> 0) THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede modificar el tipo de una cuenta con movimientos';
	END IF;

	/*
	 * No se puede desactivar una cuenta con saldo.
	 */
	IF p_activa = FALSE
	   AND (v_cargo - v_abono) <> 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede desactivar una cuenta con saldo distinto de cero';
	END IF;

	/*
	 * No se puede desactivar una cuenta con subcuentas activas.
	 */
	IF p_activa = FALSE
	   AND v_hijas_activas > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'No se puede desactivar una cuenta con subcuentas activas';
	END IF;

	UPDATE cuentas_contables
	SET
		clave = TRIM(p_clave),
		nombre = TRIM(p_nombre),
		descripcion = NULLIF(TRIM(p_descripcion), ''),
		ultimo_nivel = p_ultimo_nivel,
		activa = p_activa,
		fecha_modificacion = CURDATE()
	WHERE id_cuenta = p_id_cuenta;

	COMMIT;

	SELECT
		p_id_cuenta AS id,
		'Cuenta contable actualizada correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE update_empleado(
	IN p_id_empleado INT UNSIGNED,
	IN p_id_cuenta_contable INT,
	IN p_id_sucursal BIGINT UNSIGNED,
	IN p_rfc VARCHAR(13)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_curp VARCHAR(18)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_completo VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_nombre_corto VARCHAR(10)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_fecha_nac DATE,
	IN p_correo_electronico VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_estado VARCHAR(30)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_ciudad VARCHAR(40)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_direccion TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_codigo_postal VARCHAR(6)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_contrasenia VARCHAR(255)
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
	IN p_activo BOOLEAN
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos permitidos de un empleado'
BEGIN
	DECLARE v_existe_empleado INT DEFAULT 0;
	DECLARE v_cuenta_actual INT DEFAULT 0;
	DECLARE v_cargo DOUBLE DEFAULT 0;
	DECLARE v_abono DOUBLE DEFAULT 0;
	DECLARE v_existe_cuenta INT DEFAULT 0;
	DECLARE v_cuenta_asignada INT DEFAULT 0;
	DECLARE v_cuenta_activa BOOLEAN DEFAULT FALSE;
	DECLARE v_ultimo_nivel BOOLEAN DEFAULT FALSE;
	DECLARE v_existe_sucursal INT DEFAULT 0;
	DECLARE v_rfc_duplicado INT DEFAULT 0;
	DECLARE v_curp_duplicada INT DEFAULT 0;

	DECLARE v_sqlstate CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		GET DIAGNOSTICS CONDITION 1
			v_sqlstate = RETURNED_SQLSTATE,
			v_errno = MYSQL_ERRNO,
			v_text = MESSAGE_TEXT;

		ROLLBACK;

		SELECT
			500 AS id,
			CONCAT('Error ', v_errno, ' (', v_sqlstate, '): ', v_text) AS message;
	END;

	START TRANSACTION;

	SELECT COUNT(*)
	INTO v_existe_empleado
	FROM empleados
	WHERE id_empleado = p_id_empleado;

	IF v_existe_empleado = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El empleado no existe';
	END IF;

	SELECT id_cuenta_contable
	INTO v_cuenta_actual
	FROM empleados
	WHERE id_empleado = p_id_empleado
	FOR UPDATE;

	IF p_rfc IS NULL OR TRIM(p_rfc) = ''
	   OR p_curp IS NULL OR TRIM(p_curp) = ''
	   OR p_nombre_completo IS NULL OR TRIM(p_nombre_completo) = ''
	   OR p_nombre_corto IS NULL OR TRIM(p_nombre_corto) = ''
	   OR p_fecha_nac IS NULL
	   OR p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'Faltan datos obligatorios del empleado';
	END IF;

	IF p_fecha_nac > CURDATE() THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La fecha de nacimiento no es válida';
	END IF;

	SELECT COUNT(*)
	INTO v_existe_sucursal
	FROM sucursal
	WHERE id_sucursar = p_id_sucursal;

	IF v_existe_sucursal = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La sucursal indicada no existe';
	END IF;

	SELECT COUNT(*)
	INTO v_rfc_duplicado
	FROM empleados
	WHERE rfc = UPPER(TRIM(p_rfc))
	  AND id_empleado <> p_id_empleado;

	IF v_rfc_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC ya pertenece a otro empleado';
	END IF;

	SELECT COUNT(*)
	INTO v_curp_duplicada
	FROM empleados
	WHERE curp = UPPER(TRIM(p_curp))
	  AND id_empleado <> p_id_empleado;

	IF v_curp_duplicada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La CURP ya pertenece a otro empleado';
	END IF;

	IF p_id_cuenta_contable <> v_cuenta_actual THEN

		SELECT cargo, abono
		INTO v_cargo, v_abono
		FROM cuentas_contables
		WHERE id_cuenta = v_cuenta_actual
		FOR UPDATE;

		IF v_cargo <> 0 OR v_abono <> 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'No se puede cambiar la cuenta de un empleado con movimientos contables';
		END IF;

		SELECT COUNT(*)
		INTO v_existe_cuenta
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable;

		IF v_existe_cuenta = 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable no existe';
		END IF;

		SELECT activa, ultimo_nivel
		INTO v_cuenta_activa, v_ultimo_nivel
		FROM cuentas_contables
		WHERE id_cuenta = p_id_cuenta_contable
		FOR UPDATE;

		IF v_cuenta_activa = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta contable se encuentra inactiva';
		END IF;

		IF v_ultimo_nivel = FALSE THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta debe ser una cuenta de detalle';
		END IF;

		SELECT COUNT(*)
		INTO v_cuenta_asignada
		FROM empleados
		WHERE id_cuenta_contable = p_id_cuenta_contable
		  AND id_empleado <> p_id_empleado;

		IF v_cuenta_asignada > 0 THEN
			SIGNAL SQLSTATE '45000'
				SET MESSAGE_TEXT = 'La nueva cuenta ya está asignada a otro empleado';
		END IF;
	END IF;

	UPDATE empleados
	SET
		id_cuenta_contable = p_id_cuenta_contable,
		id_sucursal = p_id_sucursal,
		rfc = UPPER(TRIM(p_rfc)),
		curp = UPPER(TRIM(p_curp)),
		nombre_completo = TRIM(p_nombre_completo),
		nombre_corto = TRIM(p_nombre_corto),
		fecha_nac = p_fecha_nac,
		correo_electronico = LOWER(TRIM(p_correo_electronico)),
		estado = NULLIF(TRIM(p_estado), ''),
		ciudad = NULLIF(TRIM(p_ciudad), ''),
		direccion = NULLIF(TRIM(p_direccion), ''),
		codigo_postal = NULLIF(TRIM(p_codigo_postal), ''),
		contrasenia = COALESCE(NULLIF(TRIM(p_contrasenia), ''), contrasenia),
		activo = TRUE
	WHERE id_empleado = p_id_empleado;

	COMMIT;

	SELECT
		p_id_empleado AS id,
		'Empleado actualizado correctamente' AS message;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE update_forma_de_pago(
	IN id_forma_pago INT,
	IN forma_pago VARCHAR(18)
)
BEGIN

	UPDATE formas_de_pago
    SET
		tipo_de_pago = forma_pago,
        activo = 1
	WHERE id = id_forma_pago;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE update_tipoCliente(
	IN id_tipoCliente INT,
	IN nombre_t VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci,
    IN descripcion_t VARCHAR(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Actualiza los datos de un tipo de cliente ya registrado'
BEGIN
	
	DECLARE v_sqlState CHAR(5);
	DECLARE v_errno INT;
	DECLARE v_text TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
	
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN
		
		GET DIAGNOSTICS CONDITION 1
		v_sqlState = RETURNED_SQLSTATE,
		v_errno = MYSQL_ERRNO,
		v_text = MESSAGE_TEXT;
		
		SELECT 500 AS id,
		CONCAT('Error ', v_errno, ' (', v_sqlState, ' ):', v_text) AS message;
		
	END;
	
	
    UPDATE tipo_cliente SET
		nombre = nombre_t,
        descripcion = descripcion_t,
        activo = 1
    WHERE id = id_tipoCliente;
    
    SELECT 200 AS id, 'Tipo cliente actualizado exitosamente' AS message;
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE validar_entrada(IN `nombre_c` VARCHAR(10) CHARSET utf8, IN `contra_c` VARCHAR(15) CHARSET utf8)
BEGIN



DECLARE contra VARCHAR(15);



SELECT @contra := empleados.contrasenia AS pswd FROM empleados WHERE empleados.nombre_corto = nombre_c;



IF(@contra != contra_c) THEN

	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Contraseña incorrecta';

END IF;



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_articulos(
	IN `id_sucursal` INT,
	IN `id_tipoCliente_a` INT
)
BEGIN
SELECT articulo.id_articulo,
       articulo.codigo_articulo,
       proveedor.nombre AS proveedor,
       categoria_producto.nombre AS Categoria,
       articulo.codigo_sat,
       articulo.nombre AS Articulo,
       articulo.descripcion,
       existencia_x_sucursal.existencia,
       precios_x_tipoCliente.precio,
       precios_x_tipoCliente.precios_especial AS especial,
       precios_x_tipoCliente.cant_p_precioEspecial AS despues_de,
       articulo.activo
FROM precios_x_tipoCliente
INNER JOIN existencia_x_sucursal ON precios_x_tipoCliente.id_articulo = existencia_x_sucursal.id_articulo
INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
WHERE existencia_x_sucursal.id_sucursal = id_sucursal
  AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_a
ORDER BY id_articulo;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_cliente_por_rfc(
	IN rfc_cl VARCHAR(13)
)
BEGIN

	SELECT
		cliente.id_cliente,
        cliente.rfc,
        sub_cuentas_tercer_nivel.clave,
        sub_cuentas_tercer_nivel.descripcion,
        cliente.nombre_completo,
        cliente.nombre_corto,
        cliente.fecha_nac,
        cliente.correo_electronico,
        cliente.estado,
        cliente.ciudad,
        cliente.direccion,
        cliente.codigo_postal
    FROM cliente
    INNER JOIN sub_cuentas_tercer_nivel ON cliente.id_cuenta_contable = sub_cuentas_tercer_nivel.id_cuenta
    WHERE cliente.rfc = rfc_cl;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_cmbRubroCuentasContables()
BEGIN
	
	SELECT 
		_rc.id_rubro,
		_rc.nombre,
		_rc.descripcion,
		_rc.naturaleza
	FROM rubro_cuenta_contable AS _rc;
	
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_codigos_articulos()
BEGIN
	
    SELECT articulo.codigo_articulo
    FROM articulo;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_cuentas_contables(
	IN `nombre_cta_contable` VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    COMMENT 'LISTA EL CATALOGO COMPLETO DE CUENTAS CONTABLES'
BEGIN            
	SELECT
		cc.id_cuenta,
		cc.clave,
		cc.nombre,
		cc2.nombre AS 'cuenta_padre',
		rcc.nombre AS 'rubro',
		cc.nivel,
		cc.ultimo_nivel,
		cc.cargo,
		cc.abono,
		cc.cargo - cc.abono AS 'saldo',
		cc.activa 
	FROM cuentas_contables AS cc
	LEFT JOIN cuentas_contables cc2 ON cc.id_cuenta_padre = cc2.id_cuenta
	INNER JOIN rubro_cuenta_contable AS rcc ON cc.fk_id_rubro = rcc.id_rubro
	WHERE cc.nombre LIKE CONCAT('%',nombre_cta_contable,'%') COLLATE utf8mb4_general_ci;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_formas_de_pago()
BEGIN	
    SELECT 
		fp.id,
        fp.tipo_de_pago,
        fp.activo
    FROM formas_de_pago AS fp;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_indices_categorias()
BEGIN

	

	SELECT categoria_producto.id_categoria FROM categoria_producto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_indice_venta_actual()
BEGIN

    SELECT
		ventas.id_venta
	FROM ventas
    ORDER BY ventas.id_venta DESC LIMIT 1;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_nombres_sucursal()
BEGIN
	
	SELECT
		id_sucursar,
		nombre
	FROM sucursal ORDER BY id_sucursar;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_proveedor_por_rfc(
	IN rfc_p VARCHAR(13)
)
BEGIN
	
	SELECT
		proveedor.id_proveedor,
        proveedor.id_cuenta_contable,
        sub_cuentas_tercer_nivel.clave,
        proveedor.nombre,
        proveedor.descripcion,
        proveedor.correo_electronico,
        proveedor.estado,
        proveedor.ciudad,
        proveedor.direccion,
        proveedor.codigo_postal
	FROM proveedor
    INNER JOIN sub_cuentas_tercer_nivel ON proveedor.id_cuenta_contable = sub_cuentas_tercer_nivel.id_cuenta
	WHERE proveedor.rfc = rfc_p;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_rfcProveedores()
BEGIN
	select
		proveedor.rfc
	from proveedor;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_rfc_clientes()
BEGIN
	SELECT
		cliente.id_cliente,
		cliente.rfc
	FROM cliente 
    ORDER BY id_cliente ASC;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_rfc_empleado_por_sucursal(
	IN id_sucursal INT
)
BEGIN	
    SELECT
    	empleados.id_empleado,
		empleados.nombre_corto
	FROM empleados
    WHERE empleados.id_sucursal = id_sucursal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_sucursales()
BEGIN
	SELECT
		id_sucursar,
		nombre,
        descripcion,
        telefono,
        email,
        estado,
        ciudad,
        direccion,
        codigo_postal,
        activo
	FROM sucursal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_sucursales_nombres()
    COMMENT 'Procedimeinto para el listado de las sucursales en un combobox'
BEGIN
	SELECT 
		sucursal.id_sucursar AS id,
        sucursal.nombre
	FROM sucursal ORDER BY id_sucursar;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_tipo_clientes(
	IN nombre_tipo_cliente VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    READS SQL DATA
    COMMENT 'LISTADO COMPLETO DE TODAS LAS CATEGORIAS DE CLIENTES REGISTRADAS, FILTRADO POR NOMBRE'
BEGIN
	
    SELECT
		tipo_cliente.id,
        tipo_cliente.nombre,
        tipo_cliente.descripcion,
        tipo_cliente.activo
    FROM tipo_cliente
    WHERE tipo_cliente.nombre LIKE CONCAT('%',nombre_tipo_cliente,'%');
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=root@localhost PROCEDURE ver_ventas(
	IN `opcion` INT,
	IN `sucursal` INT
)
BEGIN
	
    IF opcion = 1 THEN
		
      SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
      WHERE ventas.id_sucursal = sucursal
		ORDER BY ventas.id_venta;
      
    END IF;
    
    IF opcion = 2 THEN
		
        SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE ventas.id_sucursal = sucursal
		ORDER BY empleados.nombre_corto;
        
    END IF;
    
    IF opcion = 3 THEN
		SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE ventas.id_sucursal = sucursal
		ORDER BY cliente.nombre_corto;
    END IF;
    
    IF opcion = 4 THEN
		SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE ventas.id_sucursal = sucursal
		ORDER BY ventas.status_venta;
    END IF;
    
    IF opcion = 5 THEN
		SELECT
			ventas.id_venta,
			ventas.fecha,
			ventas.tipo_venta,
			empleados.nombre_corto,
			cliente.nombre_corto,
			ventas.subtotal,
			ventas.iva,
			ventas.importe_total,
			ventas.status_venta
		FROM ventas
		INNER JOIN empleados ON empleados.id_empleado = ventas.id_empleado
		INNER JOIN cliente ON cliente.id_cliente = ventas.id_cliente
        WHERE ventas.id_sucursal = sucursal
		ORDER BY ventas.tipo_venta;
    END IF;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE kath_erp CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

