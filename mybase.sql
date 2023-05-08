-- MariaDB dump 10.19  Distrib 10.4.27-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: kath_erp
-- ------------------------------------------------------
-- Server version	10.4.27-MariaDB
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */
;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */
;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */
;
/*!40101 SET NAMES utf8mb4 */
;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */
;
/*!40103 SET TIME_ZONE='+00:00' */
;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */
;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */
;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */
;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */
;
--
-- Table structure for table `articulo`
--
DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `articulo` (
  `id_articulo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(10) unsigned NOT NULL,
  `id_categoria` int(10) unsigned NOT NULL,
  `codigo_sat` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `existencia` int(11) DEFAULT NULL,
  `es_exento` tinyint(1) NOT NULL,
  `costo_unitario` double NOT NULL,
  `precio_general` double NOT NULL,
  `precio_mayoreo` double NOT NULL,
  PRIMARY KEY (`id_articulo`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_producto` (`id_categoria`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `articulo_x_compra`
--
DROP TABLE IF EXISTS `articulo_x_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `articulo_x_compra` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_compra` int(10) unsigned NOT NULL,
  `id_articulo` int(10) unsigned NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_compra` (`id_compra`),
  KEY `id_articulo` (`id_articulo`),
  CONSTRAINT `articulo_x_compra_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id_compra`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_x_compra_ibfk_2` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `articulo_x_venta`
--
DROP TABLE IF EXISTS `articulo_x_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `articulo_x_venta` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_venta` int(10) unsigned NOT NULL,
  `id_articulo` int(10) unsigned NOT NULL,
  `cantidad` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_venta` (`id_venta`),
  KEY `id_articulo` (`id_articulo`),
  CONSTRAINT `articulo_x_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_x_venta_ibfk_2` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `categoria_producto`
--
DROP TABLE IF EXISTS `categoria_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `categoria_producto` (
  `id_categoria` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `cliente`
--
DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `cliente` (
  `id_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rfc` varchar(13) NOT NULL,
  `id_cuenta_contable` int(10) unsigned NOT NULL,
  `nombre_completo` varchar(30) NOT NULL,
  `nombre_corto` varchar(10) NOT NULL,
  `fecha_nac` date NOT NULL,
  `correo_electronico` varchar(30) NOT NULL,
  `estado` varchar(30) DEFAULT NULL,
  `ciudad` varchar(40) DEFAULT NULL,
  `direccion` text DEFAULT NULL,
  `codigo_postal` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `id_cuenta_contable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `cobro_clientes`
--
DROP TABLE IF EXISTS `cobro_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `cobro_clientes` (
  `id_cobro` int(10) unsigned NOT NULL,
  `id_venta` int(10) unsigned NOT NULL,
  `id_empleado` int(10) unsigned NOT NULL,
  `total` double NOT NULL,
  `fecha_cobro` date NOT NULL,
  PRIMARY KEY (`id_cobro`),
  KEY `id_venta` (`id_venta`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `cobro_clientes_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE,
  CONSTRAINT `cobro_clientes_ibfk_2` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `compras`
--
DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `compras` (
  `id_compra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `folio_factura` varchar(13) NOT NULL,
  `fecha_compra` date NOT NULL,
  `tipo_compra` tinyint(4) NOT NULL,
  `id_empleado` int(10) unsigned NOT NULL,
  `id_proveedor` int(10) unsigned NOT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `cuentas_padre`
--
DROP TABLE IF EXISTS `cuentas_padre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `cuentas_padre` (
  `id_cuenta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `clave` varchar(6) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `cargos` double NOT NULL,
  `abonos` double NOT NULL,
  `saldo` double NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `clave` (`clave`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `empleados`
--
DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `empleados` (
  `id_empleado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rfc` varchar(13) NOT NULL,
  `curp` varchar(18) NOT NULL,
  `nombre_completo` varchar(30) NOT NULL,
  `nombre_corto` varchar(10) NOT NULL,
  `fecha_nac` date NOT NULL,
  `correo_electronico` varchar(30) NOT NULL,
  `estado` varchar(30) DEFAULT NULL,
  `ciudad` varchar(40) DEFAULT NULL,
  `direccion` text DEFAULT NULL,
  `codigo_postal` varchar(6) DEFAULT NULL,
  `contrasenia` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `curp` (`curp`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `factura`
--
DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `factura` (
  `id_factura` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `folio_fiscal` varchar(38) NOT NULL,
  `fecha_emisin` date NOT NULL,
  `fecha_certificacion` date NOT NULL,
  `id_venta` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `id_venta` (`id_venta`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `formas_de_pago`
--
DROP TABLE IF EXISTS `formas_de_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `formas_de_pago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_de_pago` varchar(18) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `gastos`
--
DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `gastos` (
  `id_gasto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_operacion` date NOT NULL,
  `id_empleado` int(10) unsigned NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_cuenta_contable` int(10) unsigned NOT NULL,
  `importe` double NOT NULL,
  `iva` double NOT NULL,
  PRIMARY KEY (`id_gasto`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `gastos_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `gastos_ibfk_2` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `pago_proveedor`
--
DROP TABLE IF EXISTS `pago_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `pago_proveedor` (
  `id_pago` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_compra` int(10) unsigned NOT NULL,
  `id_forma_pago` int(11) NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_compra` (`id_compra`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `pago_proveedor_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id_compra`) ON UPDATE CASCADE,
  CONSTRAINT `pago_proveedor_ibfk_2` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_de_pago` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `pagos_x_cobro`
--
DROP TABLE IF EXISTS `pagos_x_cobro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `pagos_x_cobro` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cobro` int(10) unsigned NOT NULL,
  `id_forma_pago` int(11) NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cobro` (`id_cobro`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `pagos_x_cobro_ibfk_1` FOREIGN KEY (`id_cobro`) REFERENCES `cobro_clientes` (`id_cobro`) ON UPDATE CASCADE,
  CONSTRAINT `pagos_x_cobro_ibfk_2` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_de_pago` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `pagos_x_venta`
--
DROP TABLE IF EXISTS `pagos_x_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `pagos_x_venta` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_venta` int(10) unsigned NOT NULL,
  `id_forma_pago` int(11) NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_venta` (`id_venta`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `pagos_x_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE,
  CONSTRAINT `pagos_x_venta_ibfk_2` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_de_pago` (`id`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `permiso_x_empleado`
--
DROP TABLE IF EXISTS `permiso_x_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `permiso_x_empleado` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `id_permiso` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_permiso` (`id_permiso`),
  CONSTRAINT `permiso_x_empleado_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `permiso_x_empleado_ibfk_2` FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id_permiso`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `permisos`
--
DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `permisos` (
  `id_permiso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `descripcion` text DEFAULT NULL,
  PRIMARY KEY (`id_permiso`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `proveedor`
--
DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `proveedor` (
  `id_proveedor` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `rfc` varchar(13) NOT NULL,
  `id_cuenta_contable` int(10) unsigned NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `correo_electronico` varchar(30) NOT NULL,
  `estado` varchar(30) DEFAULT NULL,
  `ciudad` varchar(40) DEFAULT NULL,
  `direccion` text DEFAULT NULL,
  `codigo_postal` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `retiros_de_efectivo`
--
DROP TABLE IF EXISTS `retiros_de_efectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `retiros_de_efectivo` (
  `id_retiro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id_retiro`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `retiros_de_efectivo_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `sub_cuentas_segundo_nivel`
--
DROP TABLE IF EXISTS `sub_cuentas_segundo_nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `sub_cuentas_segundo_nivel` (
  `id_cuenta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cuenta_padre` int(10) unsigned NOT NULL,
  `clave` varchar(18) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `cargos` double NOT NULL,
  `abonos` double NOT NULL,
  `saldo` double NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `clave` (`clave`),
  KEY `id_cuenta_padre` (`id_cuenta_padre`),
  CONSTRAINT `id_cuenta_padre` FOREIGN KEY (`id_cuenta_padre`) REFERENCES `cuentas_padre` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `sub_cuentas_tercer_nivel`
--
DROP TABLE IF EXISTS `sub_cuentas_tercer_nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `sub_cuentas_tercer_nivel` (
  `id_cuenta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_cuenta_superior` int(10) unsigned NOT NULL,
  `clave` varchar(18) NOT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `cargos` double NOT NULL,
  `abonos` double NOT NULL,
  `saldo` double NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `clave` (`clave`),
  KEY `id_cuenta_superior` (`id_cuenta_superior`),
  CONSTRAINT `id_cuenta_superior` FOREIGN KEY (`id_cuenta_superior`) REFERENCES `sub_cuentas_segundo_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `telefono_x_cliente`
--
DROP TABLE IF EXISTS `telefono_x_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `telefono_x_cliente` (
  `id_telefono` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(10) unsigned NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`id_telefono`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `telefono_x_empleado`
--
DROP TABLE IF EXISTS `telefono_x_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `telefono_x_empleado` (
  `id_telefono` int(11) NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`id_telefono`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `id_empleado` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `telefono_x_proveedor`
--
DROP TABLE IF EXISTS `telefono_x_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `telefono_x_proveedor` (
  `id_telefono` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(10) unsigned NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`id_telefono`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `id_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Table structure for table `ventas`
--
DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */
;
/*!40101 SET character_set_client = utf8 */
;
CREATE TABLE `ventas` (
  `id_venta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `tipo_venta` tinyint(1) NOT NULL,
  `id_empleado` int(10) unsigned NOT NULL,
  `id_cliente` int(10) unsigned NOT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  `importe_total` double NOT NULL,
  `status_venta` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */
;
--
-- Dumping routines for database 'kath_erp'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */
;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */
;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_articulo` */
;
/*!50003 SET @saved_cs_client      = @@character_set_client */
;
/*!50003 SET @saved_cs_results     = @@character_set_results */
;
/*!50003 SET @saved_col_connection = @@collation_connection */
;
/*!50003 SET character_set_client  = utf8mb4 */
;
/*!50003 SET character_set_results = utf8mb4 */
;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */
;
DELIMITER;
;
CREATE DEFINER = `root` @`localhost` PROCEDURE `insert_nuevo_articulo`(
  IN `proveedor` VARCHAR(30),
  IN `categoria` VARCHAR(60),
  IN `codigo_sat` VARCHAR(9),
  IN `nombre` VARCHAR(30),
  IN `descripcion` VARCHAR(255),
  IN `existencia` INT,
  IN `es_exento` BOOLEAN,
  IN `costo_unitario` DOUBLE,
  IN `precio_general` DOUBLE,
  IN `precio_mayoreo` DOUBLE
) BEGIN
DECLARE proveedor INT;
DECLARE categoria INT;
SELECT proveedor INTO proveedor
FROM proveedor
WHERE proveedor.nombre = proveedor;
IF proveedor IS NULL THEN SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'No existe el proveedor';
END IF;
SELECT categoria_producto.id_categoria INTO categoria
FROM categoria_producto
WHERE categoria_producto.nombre = categoria;
IF categoria IS NULL THEN SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'No existe la categoria';
END IF;
IF costo_unitario <= 0 THEN SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Costo no puede ser 0';
END IF;
IF precio_general <= 0 THEN SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Precio no puede ser 0';
END IF;
IF precio_mayoreo <= 0 THEN SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'Mayoreo no puede ser 0';
END IF;
INSERT INTO articulo(
    id_proveedor,
    id_categoria,
    codigo_sat,
    nombre,
    descripcion,
    existencia,
    es_exento,
    costo_unitario,
    precio_general,
    precio_mayoreo
  )
VALUES(
    proveedor,
    categoria,
    codigo_sat,
    nombre,
    descripcion,
    existencia,
    es_exento,
    costo_unitario,
    precio_general,
    precio_mayoreo
  );
END;
;
DELIMITER;
/*!50003 SET sql_mode              = @saved_sql_mode */
;
/*!50003 SET character_set_client  = @saved_cs_client */
;
/*!50003 SET character_set_results = @saved_cs_results */
;
/*!50003 SET collation_connection  = @saved_col_connection */
;
/*!50003 SET @saved_sql_mode       = @@sql_mode */
;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */
;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_categoria` */
;
/*!50003 SET @saved_cs_client      = @@character_set_client */
;
/*!50003 SET @saved_cs_results     = @@character_set_results */
;
/*!50003 SET @saved_col_connection = @@collation_connection */
;
/*!50003 SET character_set_client  = utf8mb4 */
;
/*!50003 SET character_set_results = utf8mb4 */
;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */
;
DELIMITER;
;
CREATE DEFINER = `root` @`localhost` PROCEDURE `insert_nuevo_categoria`(
  IN `nombre_m` VARCHAR(60),
  IN `descripcion_m` VARCHAR(255)
) COMMENT 'Procedimiento para insertar un nuevo registro' BEGIN
INSERT INTO categoria_producto(nombre, descripcion)
VALUES(nombre_m, descripcion_m);
END;
;
DELIMITER;
/*!50003 SET sql_mode              = @saved_sql_mode */
;
/*!50003 SET character_set_client  = @saved_cs_client */
;
/*!50003 SET character_set_results = @saved_cs_results */
;
/*!50003 SET collation_connection  = @saved_col_connection */
;
/*!50003 SET @saved_sql_mode       = @@sql_mode */
;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */
;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_cliente` */
;
/*!50003 SET @saved_cs_client      = @@character_set_client */
;
/*!50003 SET @saved_cs_results     = @@character_set_results */
;
/*!50003 SET @saved_col_connection = @@collation_connection */
;
/*!50003 SET character_set_client  = utf8mb4 */
;
/*!50003 SET character_set_results = utf8mb4 */
;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */
;
DELIMITER;
;
CREATE DEFINER = `root` @`localhost` PROCEDURE `insert_nuevo_cliente`(
  IN rfc VARCHAR(13),
  IN cuenta_contable VARCHAR(10),
  IN cuenta_superior VARCHAR(10),
  IN nombre_completo VARCHAR(30),
  IN nombre_corto VARCHAR(10),
  IN fecha_nac DATE,
  IN correo VARCHAR(30),
  IN estado VARCHAR(30),
  IN ciudad VARCHAR(40),
  IN direccion TEXT,
  IN codigo_postal VARCHAR(6)
) BEGIN
DECLARE cuenta_superior INT UNSIGNED;
DECLARE cuenta_cliente INT UNSIGNED;
SELECT sub_cuentas_segundo_nivel.id_cuenta INTO cuenta_superior
FROM sub_cuentas_segundo_nivel
WHERE sub_cuentas_segundo_nivel.clave = cuenta_superior;
INSERT INTO sub_cuentas_tercer_nivel(
    id_cuenta_superior,
    clave,
    nombre,
    descripcion,
    cargos,
    abonos,
    saldo
  )
VALUES(
    cuenta_superior,
    cuenta_contable,
    nombre_corto,
    NULL,
    0,
    0,
    0
  );
SELECT sub_cuentas_tercer_nivel.id_cuenta INTO cuenta_cliente
FROM sub_cuentas_tercer_nivel
WHERE sub_cuentas_tercer_nivel.clave = cuenta_contable;
INSERT INTO cliente(
    rfc,
    id_cuenta_contable,
    nombre_completo,
    nombre_corto,
    fecha_nac,
    correo_electronico,
    estado,
    ciudad,
    direccion,
    codigo_postal
  )
VALUES(
    rfc,
    cuenta_cliente,
    nombre_completo,
    nombre_corto,
    fecha_nac,
    correo,
    estado,
    ciudad,
    direccion,
    codigo_postal
  );
END;
;
DELIMITER;
/*!50003 SET sql_mode              = @saved_sql_mode */
;
/*!50003 SET character_set_client  = @saved_cs_client */
;
/*!50003 SET character_set_results = @saved_cs_results */
;
/*!50003 SET collation_connection  = @saved_col_connection */
;
/*!50003 SET @saved_sql_mode       = @@sql_mode */
;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */
;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_empleado` */
;
/*!50003 SET @saved_cs_client      = @@character_set_client */
;
/*!50003 SET @saved_cs_results     = @@character_set_results */
;
/*!50003 SET @saved_col_connection = @@collation_connection */
;
/*!50003 SET character_set_client  = utf8mb4 */
;
/*!50003 SET character_set_results = utf8mb4 */
;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */
;
DELIMITER;
;
CREATE DEFINER = `root` @`localhost` PROCEDURE `insert_nuevo_empleado`(
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
) BEGIN
INSERT INTO empleados(
    rfc,
    curp,
    nombre_completo,
    nombre_corto,
    fecha_nac,
    correo_electronico,
    estado,
    ciudad,
    direccion,
    codigo_postal
  )
VALUES(
    ref_e,
    curp_e,
    nombre_completo_e,
    nombre_corto_e,
    fecha_nac_e,
    correo_electronico_e,
    estado_e,
    ciudad_e,
    direccion_e,
    codigo_postal_e
  );
END;
;
DELIMITER;
/*!50003 SET sql_mode              = @saved_sql_mode */
;
/*!50003 SET character_set_client  = @saved_cs_client */
;
/*!50003 SET character_set_results = @saved_cs_results */
;
/*!50003 SET collation_connection  = @saved_col_connection */
;
/*!50003 SET @saved_sql_mode       = @@sql_mode */
;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */
;
/*!50003 DROP PROCEDURE IF EXISTS `validar_entrada` */
;
/*!50003 SET @saved_cs_client      = @@character_set_client */
;
/*!50003 SET @saved_cs_results     = @@character_set_results */
;
/*!50003 SET @saved_col_connection = @@collation_connection */
;
/*!50003 SET character_set_client  = utf8mb4 */
;
/*!50003 SET character_set_results = utf8mb4 */
;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */
;
DELIMITER;
;
CREATE DEFINER = `root` @`localhost` PROCEDURE `validar_entrada`(
  IN `nombre_corto` VARCHAR(10),
  IN `contrasenia` VARCHAR(15)
) COMMENT 'validaci├│n de el usuario y la contrase├▒a al momento del ingreso' BEGIN
DECLARE nombre VARCHAR(10);
DECLARE pswd VARCHAR(15);
SELECT empleados.nombre_corto,
  empleados.contrasenia INTO nombre,
  pswd
FROM empleados
WHERE empleados.nombre_corto = nombre_corto;
IF nombre IS NULL
OR pswd IS NULL
OR pswd <> contrasenia THEN SIGNAL SQLSTATE '42000'
SET MESSAGE_TEXT = 'Error de autenticaci├│n';
END IF;
END;
;
DELIMITER;
/*!50003 SET sql_mode              = @saved_sql_mode */
;
/*!50003 SET character_set_client  = @saved_cs_client */
;
/*!50003 SET character_set_results = @saved_cs_results */
;
/*!50003 SET collation_connection  = @saved_col_connection */
;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */
;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */
;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */
;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */
;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */
;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */
;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */
;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */
;
-- Dump completed on 2023-05-08 11:40:00