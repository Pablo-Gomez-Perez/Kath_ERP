/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE articulo (
  id_articulo int unsigned NOT NULL AUTO_INCREMENT,
  id_proveedor int unsigned NOT NULL,
  id_categoria int unsigned NOT NULL,
  codigo_articulo varchar(65) COLLATE utf8mb4_general_ci NOT NULL,
  codigo_sat varchar(9) COLLATE utf8mb4_general_ci NOT NULL,
  unidad_sat varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  nombre varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion varchar(555) COLLATE utf8mb4_general_ci DEFAULT NULL,
  es_exento tinyint(1) NOT NULL,
  costo_unitario decimal(18,2) NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_articulo),
  UNIQUE KEY codigo_articulo (codigo_articulo),
  KEY id_categoria (id_categoria),
  KEY Fk_Proveedor_x_Articulo (id_proveedor),
  CONSTRAINT Fk_Categoria_x_Articulo FOREIGN KEY (id_categoria) REFERENCES categoria_producto (id_categoria) ON UPDATE CASCADE,
  CONSTRAINT Fk_Proveedor_x_Articulo FOREIGN KEY (id_proveedor) REFERENCES proveedor (id_proveedor) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1182 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE articulo_x_compra (
  id int unsigned NOT NULL AUTO_INCREMENT,
  id_compra int unsigned NOT NULL,
  id_articulo int unsigned NOT NULL,
  cantidad int NOT NULL,
  subtotal double NOT NULL,
  PRIMARY KEY (id),
  KEY id_compra (id_compra),
  KEY id_articulo (id_articulo),
  CONSTRAINT Fk_Articulo_x_Compra FOREIGN KEY (id_articulo) REFERENCES articulo (id_articulo) ON UPDATE CASCADE,
  CONSTRAINT Fk_Compra_x_Articulo FOREIGN KEY (id_compra) REFERENCES compras (id_compra) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE articulo_x_venta (
  id int unsigned NOT NULL AUTO_INCREMENT,
  id_venta int unsigned NOT NULL,
  id_articulo int unsigned NOT NULL,
  cantidad int NOT NULL,
  subtotal double NOT NULL,
  PRIMARY KEY (id),
  KEY id_venta (id_venta),
  KEY id_articulo (id_articulo),
  CONSTRAINT articulo_x_venta_ibfk_1 FOREIGN KEY (id_venta) REFERENCES ventas (id_venta) ON UPDATE CASCADE,
  CONSTRAINT articulo_x_venta_ibfk_2 FOREIGN KEY (id_articulo) REFERENCES articulo (id_articulo) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1951 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE categoria_producto (
  id_categoria int unsigned NOT NULL AUTO_INCREMENT,
  nombre varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_categoria)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cliente (
  id_cliente int unsigned NOT NULL AUTO_INCREMENT,
  id_tipoCliente int NOT NULL,
  id_cuenta_contable int NOT NULL,
  rfc varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  nombre_completo varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  nombre_corto varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  fecha_nac date NOT NULL,
  correo_electronico varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  estado varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  ciudad varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  direccion text COLLATE utf8mb4_general_ci,
  codigo_postal varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_cliente),
  UNIQUE KEY rfc (rfc),
  UNIQUE KEY id_cuenta_contable (id_cuenta_contable),
  KEY Fk_tipoCliente_x_cliente (id_tipoCliente),
  CONSTRAINT Fk_Cliente_x_CuentaContable FOREIGN KEY (id_cuenta_contable) REFERENCES cuentas_contables (id_cuenta) ON UPDATE CASCADE,
  CONSTRAINT Fk_tipoCliente_x_cliente FOREIGN KEY (id_tipoCliente) REFERENCES tipo_cliente (id) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cobro_clientes (
  id_cobro int unsigned NOT NULL,
  id_venta int unsigned NOT NULL,
  id_empleado int unsigned NOT NULL,
  total double NOT NULL,
  fecha_cobro date NOT NULL,
  PRIMARY KEY (id_cobro),
  KEY id_venta (id_venta),
  KEY id_empleado (id_empleado),
  CONSTRAINT Fk_Empleado_x_Cobro FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE,
  CONSTRAINT Fk_Venta_x_cobro FOREIGN KEY (id_venta) REFERENCES ventas (id_venta) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE compras (
  id_compra int unsigned NOT NULL AUTO_INCREMENT,
  id_empleado int unsigned NOT NULL,
  id_proveedor int unsigned NOT NULL,
  id_sucursal bigint unsigned NOT NULL,
  folio_factura varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  fecha_factura date NOT NULL,
  fecha_compra date NOT NULL,
  tipo_compra tinyint(1) NOT NULL,
  subtotal double NOT NULL,
  iva double NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_compra),
  UNIQUE KEY compras_folio_factura_IDX (folio_factura) USING BTREE,
  KEY id_empleado (id_empleado),
  KEY id_proveedor (id_proveedor),
  KEY compras_sucursal_FK (id_sucursal),
  CONSTRAINT compras_sucursal_FK FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursar) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT Fk_Empleado_x_Compra FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE,
  CONSTRAINT Fk_Proveedor_x_Compra FOREIGN KEY (id_proveedor) REFERENCES proveedor (id_proveedor) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE cuentas_contables (
  id_cuenta int NOT NULL AUTO_INCREMENT,
  id_cuenta_padre int DEFAULT NULL,
  fk_id_rubro int NOT NULL,
  clave varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  nombre varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion varchar(555) COLLATE utf8mb4_general_ci DEFAULT NULL,
  nivel tinyint NOT NULL,
  ultimo_nivel tinyint(1) NOT NULL,
  cargo double NOT NULL,
  abono double NOT NULL,
  activa tinyint(1) NOT NULL,
  fecha_modificacion date NOT NULL,
  PRIMARY KEY (id_cuenta),
  UNIQUE KEY clave (clave),
  KEY fk_id_cuenta_superior (id_cuenta_padre),
  KEY fk_id_rubro_cuenta (fk_id_rubro),
  CONSTRAINT fk_id_rubro_cuenta FOREIGN KEY (fk_id_rubro) REFERENCES rubro_cuenta_contable (id_rubro) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE empleados (
  id_empleado int unsigned NOT NULL AUTO_INCREMENT,
  id_cuenta_contable int NOT NULL,
  id_sucursal bigint unsigned NOT NULL,
  rfc varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  curp varchar(18) COLLATE utf8mb4_general_ci NOT NULL,
  nombre_completo varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  nombre_corto varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  fecha_nac date NOT NULL,
  correo_electronico varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  estado varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  ciudad varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  direccion text COLLATE utf8mb4_general_ci,
  codigo_postal varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  contrasenia varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_empleado),
  UNIQUE KEY rfc (rfc),
  UNIQUE KEY curp (curp),
  UNIQUE KEY id_cuenta_contable_U (id_cuenta_contable) USING BTREE,
  UNIQUE KEY uq_empleados_nombre_corto (nombre_corto),
  KEY Fk_sucursal_empleado (id_sucursal),
  CONSTRAINT Fk_Empleado_x_CuentaContable FOREIGN KEY (id_cuenta_contable) REFERENCES cuentas_contables (id_cuenta) ON UPDATE CASCADE,
  CONSTRAINT Fk_sucursal_empleado FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursar)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE existencia_x_sucursal (
  id int NOT NULL AUTO_INCREMENT,
  id_articulo int unsigned NOT NULL,
  id_sucursal bigint unsigned NOT NULL,
  existencia int DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY uq_existencia_articulo_sucursal (id_articulo,id_sucursal),
  KEY Rlt_articulo_existencia (id_articulo),
  KEY Rlt_sucursal_existencia (id_sucursal),
  CONSTRAINT Rlt_articulo_existencia FOREIGN KEY (id_articulo) REFERENCES articulo (id_articulo),
  CONSTRAINT Rlt_sucursal_existencia FOREIGN KEY (id_sucursal) REFERENCES sucursal (id_sucursar)
) ENGINE=InnoDB AUTO_INCREMENT=2200 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE factura (
  id_factura int unsigned NOT NULL AUTO_INCREMENT,
  id_venta int unsigned NOT NULL,
  folio_fiscal varchar(38) COLLATE utf8mb4_general_ci NOT NULL,
  fecha_emision date NOT NULL,
  fecha_certificacion date NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_factura),
  KEY id_venta (id_venta),
  CONSTRAINT factura_ibfk_1 FOREIGN KEY (id_venta) REFERENCES ventas (id_venta) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE formas_de_pago (
  id int NOT NULL AUTO_INCREMENT,
  tipo_de_pago varchar(18) COLLATE utf8mb4_general_ci NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE gastos (
  id_gasto int unsigned NOT NULL AUTO_INCREMENT,
  fecha_operacion date NOT NULL,
  id_empleado int unsigned NOT NULL,
  descripcion varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  id_cuenta_contable int NOT NULL,
  importe double NOT NULL,
  iva double NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_gasto),
  UNIQUE KEY id_cuenta_contable (id_cuenta_contable),
  KEY id_empleado (id_empleado),
  CONSTRAINT fk_gastos_x_cuenta_contable FOREIGN KEY (id_cuenta_contable) REFERENCES cuentas_contables (id_cuenta) ON UPDATE CASCADE,
  CONSTRAINT gastos_ibfk_1 FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE grupo_contable (
  id_grupo int NOT NULL AUTO_INCREMENT,
  nombre_grupo varchar(65) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (id_grupo)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pago_proveedor (
  id_pago int unsigned NOT NULL AUTO_INCREMENT,
  id_compra int unsigned NOT NULL,
  id_forma_pago int NOT NULL,
  importe double NOT NULL,
  PRIMARY KEY (id_pago),
  KEY id_compra (id_compra),
  KEY id_forma_pago (id_forma_pago),
  CONSTRAINT pago_proveedor_ibfk_1 FOREIGN KEY (id_compra) REFERENCES compras (id_compra) ON UPDATE CASCADE,
  CONSTRAINT pago_proveedor_ibfk_2 FOREIGN KEY (id_forma_pago) REFERENCES formas_de_pago (id) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pagos_x_cobro (
  id int unsigned NOT NULL AUTO_INCREMENT,
  id_cobro int unsigned NOT NULL,
  id_forma_pago int NOT NULL,
  importe double NOT NULL,
  PRIMARY KEY (id),
  KEY id_cobro (id_cobro),
  KEY id_forma_pago (id_forma_pago),
  CONSTRAINT Fk_Cobro_x_FormaDePago FOREIGN KEY (id_forma_pago) REFERENCES formas_de_pago (id) ON UPDATE CASCADE,
  CONSTRAINT Fk_Pago_x_Cobro FOREIGN KEY (id_cobro) REFERENCES cobro_clientes (id_cobro) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE pagos_x_venta (
  id int unsigned NOT NULL AUTO_INCREMENT,
  id_venta int unsigned NOT NULL,
  id_forma_pago int NOT NULL,
  importe double NOT NULL,
  PRIMARY KEY (id),
  KEY id_venta (id_venta),
  KEY id_forma_pago (id_forma_pago),
  CONSTRAINT Fk_Pago_x_Venta FOREIGN KEY (id_forma_pago) REFERENCES formas_de_pago (id) ON UPDATE CASCADE,
  CONSTRAINT Fk_venta_x_pago FOREIGN KEY (id_venta) REFERENCES ventas (id_venta) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE permiso_x_empleado (
  id int unsigned NOT NULL AUTO_INCREMENT,
  id_empleado int unsigned NOT NULL,
  id_permiso int unsigned NOT NULL,
  habilitado tinyint(1) NOT NULL,
  PRIMARY KEY (id),
  KEY id_empleado (id_empleado),
  KEY id_permiso (id_permiso),
  CONSTRAINT permiso_x_empleado_ibfk_1 FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE,
  CONSTRAINT permiso_x_empleado_ibfk_2 FOREIGN KEY (id_permiso) REFERENCES permisos (id_permiso) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE permisos (
  id_permiso int unsigned NOT NULL AUTO_INCREMENT,
  nombre varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (id_permiso)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE precios_x_tipocliente (
  id int NOT NULL AUTO_INCREMENT,
  id_articulo int unsigned NOT NULL,
  id_tipoCliente int NOT NULL,
  precio decimal(18,2) NOT NULL,
  precios_especial decimal(18,2) DEFAULT NULL,
  cant_p_precioEspecial int DEFAULT NULL,
  PRIMARY KEY (id),
  KEY fk_articulo_x_precios (id_articulo),
  KEY fk_tipoCliente_x_precios (id_tipoCliente),
  CONSTRAINT fk_articulo_x_precios FOREIGN KEY (id_articulo) REFERENCES articulo (id_articulo) ON UPDATE CASCADE,
  CONSTRAINT fk_tipoCliente_x_precios FOREIGN KEY (id_tipoCliente) REFERENCES tipo_cliente (id) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2421 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE proveedor (
  id_proveedor int unsigned NOT NULL AUTO_INCREMENT,
  id_cuenta_contable int NOT NULL,
  rfc varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  nombre varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  correo_electronico varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  estado varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  ciudad varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  direccion text COLLATE utf8mb4_general_ci,
  codigo_postal varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_proveedor),
  UNIQUE KEY rfc (rfc),
  UNIQUE KEY id_cuenta_contable (id_cuenta_contable),
  CONSTRAINT fk_proveedor_x_cuenta_contable FOREIGN KEY (id_cuenta_contable) REFERENCES cuentas_contables (id_cuenta) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE retiros_de_efectivo (
  id_retiro int unsigned NOT NULL AUTO_INCREMENT,
  id_empleado int unsigned NOT NULL,
  folio varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  fecha date NOT NULL,
  descripcion varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  importe double NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_retiro),
  UNIQUE KEY Unq_folio_retiro (folio) USING BTREE,
  KEY id_empleado (id_empleado),
  CONSTRAINT retiros_de_efectivo_ibfk_1 FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE rubro_cuenta_contable (
  id_rubro int NOT NULL AUTO_INCREMENT,
  fk_id_grupo_contable int NOT NULL,
  nombre varchar(85) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  naturaleza tinyint(1) DEFAULT NULL,
  PRIMARY KEY (id_rubro),
  KEY fk_id_grupo (fk_id_grupo_contable),
  CONSTRAINT fk_id_grupo FOREIGN KEY (fk_id_grupo_contable) REFERENCES grupo_contable (id_grupo) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE sucursal (
  id_sucursar bigint unsigned NOT NULL AUTO_INCREMENT,
  nombre varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion mediumtext COLLATE utf8mb4_general_ci,
  telefono varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  email varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  estado varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  ciudad varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  direccion varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  codigo_postal varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id_sucursar)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE telefono_x_cliente (
  id_telefono int NOT NULL AUTO_INCREMENT,
  id_cliente int unsigned NOT NULL,
  telefono varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (id_telefono),
  UNIQUE KEY Unq_TelefonoCliente (telefono),
  KEY id_cliente (id_cliente),
  CONSTRAINT id_cliente FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE telefono_x_empleado (
  id_telefono int NOT NULL AUTO_INCREMENT,
  id_empleado int unsigned NOT NULL,
  telefono varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (id_telefono),
  UNIQUE KEY Unq_TelefonoEmpleado (telefono) USING BTREE,
  KEY id_empleado (id_empleado),
  CONSTRAINT id_empleado FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE telefono_x_proveedor (
  id_telefono int NOT NULL AUTO_INCREMENT,
  id_proveedor int unsigned NOT NULL,
  telefono varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (id_telefono),
  UNIQUE KEY Unq_TelefonoProveedor (telefono) USING BTREE,
  KEY id_proveedor (id_proveedor),
  CONSTRAINT id_proveedor FOREIGN KEY (id_proveedor) REFERENCES proveedor (id_proveedor) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE tipo_cliente (
  id int NOT NULL AUTO_INCREMENT,
  nombre varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  descripcion varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  activo tinyint(1) NOT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE ventas (
  id_venta int unsigned NOT NULL AUTO_INCREMENT,
  id_empleado int unsigned NOT NULL,
  id_cliente int unsigned NOT NULL,
  fecha date NOT NULL,
  tipo_venta tinyint(1) NOT NULL,
  subtotal double NOT NULL,
  iva double NOT NULL,
  importe_total double NOT NULL,
  status_venta tinyint(1) NOT NULL,
  PRIMARY KEY (id_venta),
  KEY id_empleado (id_empleado),
  KEY id_cliente (id_cliente),
  CONSTRAINT Fk_Cliente_x_Venta FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente) ON UPDATE CASCADE,
  CONSTRAINT Fk_Empleado_x_Venta FOREIGN KEY (id_empleado) REFERENCES empleados (id_empleado) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=616 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
