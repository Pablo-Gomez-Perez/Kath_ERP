-- MySQL dump 10.13  Distrib 8.0.46, for Linux (x86_64)
--
-- Host: localhost    Database: kath_erp
-- ------------------------------------------------------
-- Server version	8.0.46-0ubuntu0.24.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo` (
  `id_articulo` int unsigned NOT NULL AUTO_INCREMENT,
  `id_proveedor` int unsigned NOT NULL,
  `id_categoria` int unsigned NOT NULL,
  `codigo_articulo` varchar(65) COLLATE utf8mb4_general_ci NOT NULL,
  `codigo_sat` varchar(9) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(555) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `es_exento` tinyint(1) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_articulo`),
  UNIQUE KEY `codigo_articulo` (`codigo_articulo`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `Fk_Categoria_x_Articulo` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_producto` (`id_categoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1158 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo_x_compra`
--

DROP TABLE IF EXISTS `articulo_x_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo_x_compra` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_compra` int unsigned NOT NULL,
  `id_articulo` int unsigned NOT NULL,
  `cantidad` int NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_compra` (`id_compra`),
  KEY `id_articulo` (`id_articulo`),
  CONSTRAINT `Fk_Articulo_x_Compra` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_Compra_x_Articulo` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id_compra`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo_x_compra`
--

LOCK TABLES `articulo_x_compra` WRITE;
/*!40000 ALTER TABLE `articulo_x_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo_x_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `articulo_x_venta`
--

DROP TABLE IF EXISTS `articulo_x_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articulo_x_venta` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_venta` int unsigned NOT NULL,
  `id_articulo` int unsigned NOT NULL,
  `cantidad` int NOT NULL,
  `subtotal` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_venta` (`id_venta`),
  KEY `id_articulo` (`id_articulo`),
  CONSTRAINT `articulo_x_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_x_venta_ibfk_2` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1951 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo_x_venta`
--

LOCK TABLES `articulo_x_venta` WRITE;
/*!40000 ALTER TABLE `articulo_x_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `articulo_x_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria_producto`
--

DROP TABLE IF EXISTS `categoria_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria_producto` (
  `id_categoria` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_producto`
--

LOCK TABLES `categoria_producto` WRITE;
/*!40000 ALTER TABLE `categoria_producto` DISABLE KEYS */;
INSERT INTO `categoria_producto` VALUES (1,'Ropa','Vestimenta en general',1),(2,'Electr?nica','Productos electr?nicos',1),(3,'Hogar y Jard?n','Art?culos para el hogar y jard?n',1),(4,'Deportes','Art?culos deportivos',1),(5,'Alimentaci?n','Productos alimenticios',1),(6,'Muebles','Muebles y decoraci?n para el hogar',1),(7,'Joyer?a','Joyas y accesorios',1),(8,'Belleza','Productos de belleza',1),(9,'Calzado','Zapatos y calzado',1),(10,'Libros','Libros y literatura',1),(11,'Salud','Productos de salud',1),(12,'Electrodom?sticos','Electrodom?sticos para el hogar',1),(13,'Inform?tica','Equipos y accesorios de inform?tica',1),(14,'M├║sica','Instrumentos musicales y m├║sica',1),(15,'Cine','Pel?culas y entretenimiento',1),(16,'Arte','Arte y artesan?a',1),(17,'Infantil','Productos para ni?os',1),(18,'Autom?viles','Accesorios y piezas de autom?viles',1),(19,'Electrodom?sticos','Electrodom?sticos para el hogar',1),(20,'Jardiner?a','Suministros de jardiner?a',1),(21,'Electrodom?sticos','Electrodom?sticos para el hogar',1),(22,'Moda','Art?culos de moda y ropa',1),(23,'Electr?nica de Consumo','Productos electr?nicos de consumo',1),(24,'Deportes y Aire Libre','Equipamiento deportivo y actividades al aire libre',1),(25,'Salud y Belleza','Productos de salud y belleza',1),(26,'Herramientas','Suministros y herramientas mecanicas o de oficio',1),(27,'Electrodom?sticos de Cocina','Electrodom?sticos de cocina y utensilios',1),(28,'Animales','Productos para mascotas y animales',1),(29,'Arte y Manualidades','Materiales y suministros para arte y manualidades',1),(30,'Cine y Entretenimiento','Pel?culas y entretenimiento',1),(31,'Beb?s y Ni?os','Productos para beb?s y ni?os',1),(32,'Libros y Literatura','Libros y literatura en general',1),(33,'Calzado y Accesorios','Zapatos',1),(34,'Joyer?a y Relojes','Joyer?a y relojes en general',1),(35,'Tecnolog?a','Productos y dispositivos tecnol?gicos',1),(36,'Hogar y Cocina','Art?culos para el hogar y utensilios de cocina',1),(37,'Instrumentos Musicales','Instrumentos musicales y accesorios',1),(38,'Juegos y Juguetes','Juegos',1),(39,'Alimentos y Bebidas','Productos alimenticios y bebidas',1),(40,'Oficina y Papeler?a','Suministros de oficina y papeler?a',1),(41,'VideoJuegos','Categor?a destinada a los videojuegos tanto en digital como en f?sico',1),(42,'Instrumentos Mecanicos','Herramientas y herrajes',1),(43,'Lubricantes Generales','Lubricantes automovilisticos y mec?nicos de uso general',1),(44,'Tintes Capilares','secci?n de tintes capilares temporales y permanentes',1);
/*!40000 ALTER TABLE `categoria_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int unsigned NOT NULL AUTO_INCREMENT,
  `id_tipoCliente` int NOT NULL,
  `id_cuenta_contable` int NOT NULL,
  `rfc` varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre_completo` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre_corto` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `correo_electronico` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `estado` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ciudad` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `direccion` text COLLATE utf8mb4_general_ci,
  `codigo_postal` varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  KEY `Fk_tipoCliente_x_cliente` (`id_tipoCliente`),
  CONSTRAINT `Fk_Cliente_x_CuentaContable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `cuentas_contables` (`id_cuenta`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_tipoCliente_x_cliente` FOREIGN KEY (`id_tipoCliente`) REFERENCES `tipo_cliente` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_clientes`
--

DROP TABLE IF EXISTS `cobro_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobro_clientes` (
  `id_cobro` int unsigned NOT NULL,
  `id_venta` int unsigned NOT NULL,
  `id_empleado` int unsigned NOT NULL,
  `total` double NOT NULL,
  `fecha_cobro` date NOT NULL,
  PRIMARY KEY (`id_cobro`),
  KEY `id_venta` (`id_venta`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `Fk_Empleado_x_Cobro` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_Venta_x_cobro` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro_clientes`
--

LOCK TABLES `cobro_clientes` WRITE;
/*!40000 ALTER TABLE `cobro_clientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro_clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compras`
--

DROP TABLE IF EXISTS `compras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compras` (
  `id_compra` int unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int unsigned NOT NULL,
  `id_proveedor` int unsigned NOT NULL,
  `folio_factura` varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_factura` date NOT NULL,
  `fecha_compra` date NOT NULL,
  `tipo_compra` tinyint(1) NOT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `Fk_Empleado_x_Compra` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_Proveedor_x_Compra` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compras`
--

LOCK TABLES `compras` WRITE;
/*!40000 ALTER TABLE `compras` DISABLE KEYS */;
/*!40000 ALTER TABLE `compras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas_contables`
--

DROP TABLE IF EXISTS `cuentas_contables`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas_contables` (
  `id_cuenta` int NOT NULL AUTO_INCREMENT,
  `id_cuenta_padre` int DEFAULT NULL,
  `fk_id_rubro` int NOT NULL,
  `clave` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(555) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `nivel` tinyint NOT NULL,
  `ultimo_nivel` tinyint(1) NOT NULL,
  `cargo` double NOT NULL,
  `abono` double NOT NULL,
  `activa` tinyint(1) NOT NULL,
  `fecha_modificacion` date NOT NULL,
  PRIMARY KEY (`id_cuenta`),
  UNIQUE KEY `clave` (`clave`),
  KEY `fk_id_cuenta_superior` (`id_cuenta_padre`),
  KEY `fk_id_rubro_cuenta` (`fk_id_rubro`),
  CONSTRAINT `fk_id_rubro_cuenta` FOREIGN KEY (`fk_id_rubro`) REFERENCES `rubro_cuenta_contable` (`id_rubro`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas_contables`
--

LOCK TABLES `cuentas_contables` WRITE;
/*!40000 ALTER TABLE `cuentas_contables` DISABLE KEYS */;
INSERT INTO `cuentas_contables` VALUES (1,NULL,1,'1000','Caja','Caja',0,0,0,0,1,'2024-07-09'),(2,NULL,2,'1001','Bancos nacionales','Bancos nacionales',0,0,0,0,1,'2024-07-09'),(3,NULL,2,'1002','Bancos extranjeros','Bancos extranjeros',0,0,0,0,1,'2024-07-09'),(4,NULL,4,'1003','Clientes nacionales','Clientes nacionales',0,0,0,0,1,'2024-07-09'),(5,NULL,15,'1005','Iva acreditable pagado','Iva acreditable pagado',0,0,0,0,1,'2024-07-09'),(6,NULL,16,'1006','iva acreditable por pagar','iva acreditable por pagar',0,0,0,0,1,'2024-07-09'),(7,NULL,5,'1007','Inventario Neto','Inventario disponible para ventas en el corto plazo',0,0,0,0,1,'2024-07-09'),(8,NULL,33,'2000','Proveedores nacionales','',0,0,0,0,1,'2024-07-09'),(9,NULL,39,'2001','Iva trasladado cobrado','',0,0,0,0,1,'2024-07-09'),(10,NULL,40,'2002','iva trasladado pend de cobro','',0,0,0,0,1,'2024-07-09'),(11,NULL,73,'4000','ingresos por ventas','',0,0,0,0,1,'2024-07-09'),(12,NULL,73,'4001','otros ingresos','',0,0,0,0,1,'2024-07-09'),(13,NULL,68,'5000','Compras nacionales','Cuenta corriente de compras nacionales',0,0,0,0,1,'2024-07-09'),(14,NULL,68,'5001','Compras moneda extranjera','Compras a proveedores extranjeros',0,0,0,0,1,'2024-07-09'),(15,NULL,70,'6000','Gastos generales','',0,0,0,0,1,'2024-07-09'),(16,NULL,71,'6001','Gastos administrativos','',0,0,0,0,1,'2024-07-09'),(17,NULL,70,'6002','Gastos de ventas','',0,0,0,0,1,'2024-07-09'),(18,NULL,70,'6003','Gastos de compras','',0,0,0,0,1,'2024-07-09'),(22,NULL,4,'1004','Funcionarios y empleados','Cuenta agrupadora para empleados',0,0,0,0,1,'2026-05-27'),(23,22,4,'1004-0001','ADMIN','EMPLEADO ADMINISTRADOR',1,1,0,0,1,'2026-05-27'),(24,2,2,'1001-0001','bbva','cuenta corriente de bancos',1,1,0,0,1,'2026-07-11'),(25,1,1,'1000-0001','Fondo revolvente','Cuenta dedicada al flujo de efectivo de caja en operaciones de ventas',1,1,0,0,1,'2026-07-11');
/*!40000 ALTER TABLE `cuentas_contables` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados` (
  `id_empleado` int unsigned NOT NULL AUTO_INCREMENT,
  `id_cuenta_contable` int NOT NULL,
  `id_sucursal` bigint unsigned NOT NULL,
  `rfc` varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  `curp` varchar(18) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre_completo` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre_corto` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_nac` date NOT NULL,
  `correo_electronico` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `estado` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ciudad` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `direccion` text COLLATE utf8mb4_general_ci,
  `codigo_postal` varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `contrasenia` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `curp` (`curp`),
  UNIQUE KEY `id_cuenta_contable_U` (`id_cuenta_contable`) USING BTREE,
  KEY `Fk_sucursal_empleado` (`id_sucursal`),
  CONSTRAINT `Fk_Empleado_x_CuentaContable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `cuentas_contables` (`id_cuenta`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_sucursal_empleado` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (48,23,9,'XAXAXAXAXAXAX','XAXAXAXAXAXAXXXXXX','ADMIN','ADMIN','1990-01-01','NA','NA','NA','NA','NA','ADMIN',1);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `existencia_x_sucursal`
--

DROP TABLE IF EXISTS `existencia_x_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `existencia_x_sucursal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_articulo` int unsigned NOT NULL,
  `id_sucursal` bigint unsigned NOT NULL,
  `existencia` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Rlt_articulo_existencia` (`id_articulo`),
  KEY `Rlt_sucursal_existencia` (`id_sucursal`),
  CONSTRAINT `Rlt_articulo_existencia` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`),
  CONSTRAINT `Rlt_sucursal_existencia` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=2178 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `existencia_x_sucursal`
--

LOCK TABLES `existencia_x_sucursal` WRITE;
/*!40000 ALTER TABLE `existencia_x_sucursal` DISABLE KEYS */;
/*!40000 ALTER TABLE `existencia_x_sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `factura` (
  `id_factura` int unsigned NOT NULL AUTO_INCREMENT,
  `id_venta` int unsigned NOT NULL,
  `folio_fiscal` varchar(38) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha_emision` date NOT NULL,
  `fecha_certificacion` date NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_factura`),
  KEY `id_venta` (`id_venta`),
  CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `factura`
--

LOCK TABLES `factura` WRITE;
/*!40000 ALTER TABLE `factura` DISABLE KEYS */;
/*!40000 ALTER TABLE `factura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `formas_de_pago`
--

DROP TABLE IF EXISTS `formas_de_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `formas_de_pago` (
  `id` int NOT NULL AUTO_INCREMENT,
  `tipo_de_pago` varchar(18) COLLATE utf8mb4_general_ci NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formas_de_pago`
--

LOCK TABLES `formas_de_pago` WRITE;
/*!40000 ALTER TABLE `formas_de_pago` DISABLE KEYS */;
INSERT INTO `formas_de_pago` VALUES (1,'efectivo',1),(2,'tarjeta de credito',1),(3,'tarjeta de debito',1),(4,'cheque',1),(5,'transferencia',0),(7,'Cupon promo',0);
/*!40000 ALTER TABLE `formas_de_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `id_gasto` int unsigned NOT NULL AUTO_INCREMENT,
  `fecha_operacion` date NOT NULL,
  `id_empleado` int unsigned NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `id_cuenta_contable` int NOT NULL,
  `importe` double NOT NULL,
  `iva` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_gasto`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `fk_gastos_x_cuenta_contable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `cuentas_contables` (`id_cuenta`) ON UPDATE CASCADE,
  CONSTRAINT `gastos_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos`
--

LOCK TABLES `gastos` WRITE;
/*!40000 ALTER TABLE `gastos` DISABLE KEYS */;
/*!40000 ALTER TABLE `gastos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupo_contable`
--

DROP TABLE IF EXISTS `grupo_contable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupo_contable` (
  `id_grupo` int NOT NULL AUTO_INCREMENT,
  `nombre_grupo` varchar(65) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_grupo`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupo_contable`
--

LOCK TABLES `grupo_contable` WRITE;
/*!40000 ALTER TABLE `grupo_contable` DISABLE KEYS */;
INSERT INTO `grupo_contable` VALUES (1,'Activo a corto plazo'),(2,'Activo a largo plazo'),(3,'Pasivo a corto plazo'),(4,'Pasivo a largo plazo'),(5,'Capital Contable'),(6,'Costo'),(7,'Gasto'),(8,'Ingreso'),(9,'Utilidad o Perdida');
/*!40000 ALTER TABLE `grupo_contable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago_proveedor`
--

DROP TABLE IF EXISTS `pago_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago_proveedor` (
  `id_pago` int unsigned NOT NULL AUTO_INCREMENT,
  `id_compra` int unsigned NOT NULL,
  `id_forma_pago` int NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_compra` (`id_compra`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `pago_proveedor_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id_compra`) ON UPDATE CASCADE,
  CONSTRAINT `pago_proveedor_ibfk_2` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_de_pago` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago_proveedor`
--

LOCK TABLES `pago_proveedor` WRITE;
/*!40000 ALTER TABLE `pago_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_x_cobro`
--

DROP TABLE IF EXISTS `pagos_x_cobro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_x_cobro` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_cobro` int unsigned NOT NULL,
  `id_forma_pago` int NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cobro` (`id_cobro`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `Fk_Cobro_x_FormaDePago` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_de_pago` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_Pago_x_Cobro` FOREIGN KEY (`id_cobro`) REFERENCES `cobro_clientes` (`id_cobro`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_x_cobro`
--

LOCK TABLES `pagos_x_cobro` WRITE;
/*!40000 ALTER TABLE `pagos_x_cobro` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos_x_cobro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_x_venta`
--

DROP TABLE IF EXISTS `pagos_x_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_x_venta` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_venta` int unsigned NOT NULL,
  `id_forma_pago` int NOT NULL,
  `importe` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_venta` (`id_venta`),
  KEY `id_forma_pago` (`id_forma_pago`),
  CONSTRAINT `Fk_Pago_x_Venta` FOREIGN KEY (`id_forma_pago`) REFERENCES `formas_de_pago` (`id`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_venta_x_pago` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id_venta`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_x_venta`
--

LOCK TABLES `pagos_x_venta` WRITE;
/*!40000 ALTER TABLE `pagos_x_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos_x_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permiso_x_empleado`
--

DROP TABLE IF EXISTS `permiso_x_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permiso_x_empleado` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int unsigned NOT NULL,
  `id_permiso` int unsigned NOT NULL,
  `habilitado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_permiso` (`id_permiso`),
  CONSTRAINT `permiso_x_empleado_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `permiso_x_empleado_ibfk_2` FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id_permiso`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permiso_x_empleado`
--

LOCK TABLES `permiso_x_empleado` WRITE;
/*!40000 ALTER TABLE `permiso_x_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `permiso_x_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `permisos` (
  `id_permiso` int unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` text COLLATE utf8mb4_general_ci,
  PRIMARY KEY (`id_permiso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permisos`
--

LOCK TABLES `permisos` WRITE;
/*!40000 ALTER TABLE `permisos` DISABLE KEYS */;
/*!40000 ALTER TABLE `permisos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `precios_x_tipocliente`
--

DROP TABLE IF EXISTS `precios_x_tipocliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `precios_x_tipocliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_articulo` int unsigned NOT NULL,
  `id_tipoCliente` int NOT NULL,
  `precio` double NOT NULL,
  `precios_especial` double DEFAULT NULL,
  `cant_p_precioEspecial` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_articulo_x_precios` (`id_articulo`),
  KEY `fk_tipoCliente_x_precios` (`id_tipoCliente`),
  CONSTRAINT `fk_articulo_x_precios` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tipoCliente_x_precios` FOREIGN KEY (`id_tipoCliente`) REFERENCES `tipo_cliente` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2315 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `precios_x_tipocliente`
--

LOCK TABLES `precios_x_tipocliente` WRITE;
/*!40000 ALTER TABLE `precios_x_tipocliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `precios_x_tipocliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `id_proveedor` int unsigned NOT NULL AUTO_INCREMENT,
  `id_cuenta_contable` int NOT NULL,
  `rfc` varchar(13) COLLATE utf8mb4_general_ci NOT NULL,
  `nombre` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `correo_electronico` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `estado` varchar(30) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ciudad` varchar(40) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `direccion` text COLLATE utf8mb4_general_ci,
  `codigo_postal` varchar(6) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `fk_proveedor_x_cuenta_contable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `cuentas_contables` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retiros_de_efectivo`
--

DROP TABLE IF EXISTS `retiros_de_efectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retiros_de_efectivo` (
  `id_retiro` int unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int unsigned NOT NULL,
  `folio` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `fecha` date NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `importe` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_retiro`),
  UNIQUE KEY `Unq_folio_retiro` (`folio`) USING BTREE,
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `retiros_de_efectivo_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retiros_de_efectivo`
--

LOCK TABLES `retiros_de_efectivo` WRITE;
/*!40000 ALTER TABLE `retiros_de_efectivo` DISABLE KEYS */;
/*!40000 ALTER TABLE `retiros_de_efectivo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubro_cuenta_contable`
--

DROP TABLE IF EXISTS `rubro_cuenta_contable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubro_cuenta_contable` (
  `id_rubro` int NOT NULL AUTO_INCREMENT,
  `fk_id_grupo_contable` int NOT NULL,
  `nombre` varchar(85) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `naturaleza` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_rubro`),
  KEY `fk_id_grupo` (`fk_id_grupo_contable`),
  CONSTRAINT `fk_id_grupo` FOREIGN KEY (`fk_id_grupo_contable`) REFERENCES `grupo_contable` (`id_grupo`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubro_cuenta_contable`
--

LOCK TABLES `rubro_cuenta_contable` WRITE;
/*!40000 ALTER TABLE `rubro_cuenta_contable` DISABLE KEYS */;
INSERT INTO `rubro_cuenta_contable` VALUES (1,1,'Efectivo y equivalentes de efectivo','NIF C-1',1),(2,1,'Bancos','',1),(3,1,'Inversiones a corto plazo','',1),(4,1,'Cuentas y documentos por cobrar a clientes y otros Neto','',1),(5,1,'Inventarios Neto','',1),(6,1,'Activos biol??gicos','',1),(7,1,'Pagos anticipados y otros activos','',1),(8,1,'Activos de larga duraci??n disponibles para su venta','',1),(9,1,'Activos relacionados con operaciones descontinuadas','',1),(10,1,'Activos por instrumentos financieros derivados y de cobertura','',1),(11,1,'Obra ejecutada por aprobar','',1),(12,1,'Impuestos a la utilidad a favor a recuperar','',1),(13,1,'Partes relacionadas','',1),(14,1,'Inversion neta para el arrendador en arrendamientos capitalizables','',1),(15,1,'Iva acreditable efectivamente pagado','',1),(16,1,'Iva Acreditable pendiente de pago','',1),(17,2,'Inventarios largo plazo Neto','',1),(18,2,'Inversiones a largo plazo','',1),(19,2,'Cuentas y documentos por cobrar a clientes y otros a largo plazo Neto','',1),(20,2,'Inversiones reconocidas bajo el m?®todo de participaci??n','',1),(21,2,'Propiedades planta y equipo','',1),(22,2,'Propiedades de inversi??n','',1),(23,2,'Activos biol??gicos a largo plazo','',1),(24,2,'Activos intangibles excluyendo credito mercantil','',1),(25,2,'Activo neto proyectado de planes de beneficios a empleados','',1),(26,2,'Activos por instrumentos financieros derivados y de cobertura a largo plazo','',1),(27,2,'Efectivo y equivalentes de efectivo restringidos a largo plazo','',1),(28,2,'Pagos anticipados y otros activos a largo plazo','',1),(29,2,'Partes relacionadas a largo plazo','',1),(30,2,'Activo por impuesto a la utilidad diferido','',1),(31,2,'Activo por participaci??n de los trabajadores en la utilidad diferida','',1),(32,2,'Credito mercantil','',1),(33,3,'Proveedores pasivos acumulados y otras cuentas por pagar','',0),(34,3,'Prestamos bancarios','',0),(35,3,'Prestamos de otros acreedores','',0),(36,3,'Pasivo por emision de obligaciones y de otrosinstrumentos de deuda','',0),(37,3,'Pasivo por retenci??n de efectivo y cobros por cuenta de terceros','',0),(38,3,'Anticipos de clientes','',0),(39,3,'Iva trasladado efectivamente cobrado','',0),(40,3,'Iva trasladado pendiente de cobro','',0),(41,3,'Pasivo por impuestos a la utilidad causado','',0),(42,3,'Obra cobrada por ejecutar','',0),(43,3,'Pasivo por instrumentos derivados y de cobertura','',0),(44,3,'Provisiones','',0),(45,3,'Provisi??n de perdidas sobre contratos de construcci??n','',0),(46,3,'Partes relacionadas','',0),(47,3,'Pasivos relacionados con activos disponibles para su venta','',0),(48,3,'Pasivos relacionados con operaciones descontinuadas','',0),(49,3,'Otros pasivos a corto plazo','',0),(50,4,'Deuda a largo plazo','',0),(51,4,'Provisi??n de beneficios posteriores al empleo','',0),(52,4,'Obligaciones asociadas con el retiro de componentes de propiedades planta y equipo','',0),(53,4,'Provisi??n por impuestos a la utilidad diferido','',0),(54,4,'Provisi??n por participaci??n de los trabajadores en la utilidad diferida','',0),(55,4,'Pasivos por instrumentos financieros derivados y de cobertura a largo plazo','',0),(56,4,'partes relacionadas','',0),(57,4,'Porci??n del pasivo convertibleen capital','',0),(58,4,'Otros pasivos a largo plazo','',0),(59,5,'Capital social','',0),(60,5,'Acciones en tesorer?¡a','',0),(61,5,'Prima en emisi??n o venta de acciones o capital adicional ganado','',0),(62,5,'Capital aportado por planes de participaci??n a empleados','',0),(63,5,'Aportaciones para futuros aumentos de capital','',0),(64,5,'Otros resultados integrales netos despues de impuestos','',0),(65,5,'Reserva de capital','',0),(66,5,'Utilidades o perdidas retenidas o acumuladas','',0),(67,5,'Participaciones no controladas','',0),(68,6,'Costos de venta y distribuci??n','',1),(69,6,'Costos administrativos','',1),(70,7,'Gastos de venta y distribuci??n','',1),(71,7,'Gastos de administraci??n','',1),(72,7,'Gastos de investigaci??n','',1),(73,8,'Ingresos','',0),(74,8,'Otros ingresos no afines a las actividades de la empresa','',0),(75,9,'utilidad o Perdida','',1);
/*!40000 ALTER TABLE `rubro_cuenta_contable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sucursal` (
  `id_sucursar` bigint unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` mediumtext COLLATE utf8mb4_general_ci,
  `telefono` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `estado` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `ciudad` varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  `direccion` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `codigo_postal` varchar(5) COLLATE utf8mb4_general_ci NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (9,'Matriz','Sucursal principal','9616110331','valentina_pdbe@hotmail.com','Chiapas','Tuxtla','4a oriente sur #448','29000',1);
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_x_cliente`
--

DROP TABLE IF EXISTS `telefono_x_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono_x_cliente` (
  `id_telefono` int NOT NULL AUTO_INCREMENT,
  `id_cliente` int unsigned NOT NULL,
  `telefono` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_telefono`),
  UNIQUE KEY `Unq_TelefonoCliente` (`telefono`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `id_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono_x_cliente`
--

LOCK TABLES `telefono_x_cliente` WRITE;
/*!40000 ALTER TABLE `telefono_x_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono_x_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_x_empleado`
--

DROP TABLE IF EXISTS `telefono_x_empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono_x_empleado` (
  `id_telefono` int NOT NULL AUTO_INCREMENT,
  `id_empleado` int unsigned NOT NULL,
  `telefono` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_telefono`),
  UNIQUE KEY `Unq_TelefonoEmpleado` (`telefono`) USING BTREE,
  KEY `id_empleado` (`id_empleado`),
  CONSTRAINT `id_empleado` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono_x_empleado`
--

LOCK TABLES `telefono_x_empleado` WRITE;
/*!40000 ALTER TABLE `telefono_x_empleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono_x_empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_x_proveedor`
--

DROP TABLE IF EXISTS `telefono_x_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefono_x_proveedor` (
  `id_telefono` int NOT NULL AUTO_INCREMENT,
  `id_proveedor` int unsigned NOT NULL,
  `telefono` varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id_telefono`),
  UNIQUE KEY `Unq_TelefonoProveedor` (`telefono`) USING BTREE,
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `id_proveedor` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefono_x_proveedor`
--

LOCK TABLES `telefono_x_proveedor` WRITE;
/*!40000 ALTER TABLE `telefono_x_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `telefono_x_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cliente`
--

DROP TABLE IF EXISTS `tipo_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  `descripcion` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cliente`
--

LOCK TABLES `tipo_cliente` WRITE;
/*!40000 ALTER TABLE `tipo_cliente` DISABLE KEYS */;
INSERT INTO `tipo_cliente` VALUES (1,'General','Cliente minorista general',1),(2,'Especial','Cliente de concurrencia moderada',1);
/*!40000 ALTER TABLE `tipo_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `id_venta` int unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int unsigned NOT NULL,
  `id_cliente` int unsigned NOT NULL,
  `fecha` date NOT NULL,
  `tipo_venta` tinyint(1) NOT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  `importe_total` double NOT NULL,
  `status_venta` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `Fk_Cliente_x_Venta` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE,
  CONSTRAINT `Fk_Empleado_x_Venta` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=616 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'kath_erp'
--
/*!50003 DROP PROCEDURE IF EXISTS `actualizarPassWordEmpleado` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `actualizarPassWordEmpleado`(IN rfcEmpl VARCHAR(13), IN passwordE VARCHAR(15))
BEGIN
	UPDATE empleados
    SET empleados.contrasenia = passwordE WHERE empleados.rfc = rfcEmpl;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `bucar_forma_pago_por_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `bucar_forma_pago_por_id`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_articulos_por_nombre` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_articulos_por_nombre`(
	IN nombre_p VARCHAR(65),
    IN opcion INT,
    IN id_sucursal INT,
    IN id_tipoCliente_c INT
)
BEGIN
	
    IF opcion = 1 THEN    
		SELECT
			articulo.id_articulo,
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
		WHERE articulo.nombre LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_c
		ORDER BY id_articulo;
        
	END IF;    
    
    
	IF opcion = 2 THEN
		SELECT
			articulo.id_articulo,
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
		WHERE proveedor.nombre LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_c
		ORDER BY id_articulo;
		
    END IF;
    
    
    IF opcion = 3 THEN
		SELECT
			articulo.id_articulo,
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
		WHERE categoria_producto.nombre LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_c
		ORDER BY id_articulo;
    END IF;
    
    
    IF opcion = 4 THEN
		SELECT
			articulo.id_articulo,
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
		WHERE articulo.codigo_articulo LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_c
		ORDER BY id_articulo;
        
    END IF;
    
    
    IF opcion = 5 THEN
		SELECT
			articulo.id_articulo,
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
		WHERE articulo.descripcion LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal AND precios_x_tipoCliente.id_tipoCliente = id_tipoCliente_c
		ORDER BY id_articulo;
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_articulo_por_codigo` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_articulo_por_codigo`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_articulo_por_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_articulo_por_id`(
	IN idArticulo INT,
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
    WHERE articulo.id_articulo = idArticulo AND existencia_x_sucursal.id_sucursal = sucursal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_categoria_por_indice` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_categoria_por_indice`(IN `indice` INT UNSIGNED)
BEGIN

    SELECT
		categoria_producto.id_categoria,
		categoria_producto.nombre, 
        categoria_producto.descripcion,
        categoria_producto.activo
	FROM categoria_producto WHERE categoria_producto.id_categoria = indice;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_categoria_por_nombre` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_categoria_por_nombre`(IN `nombre` VARCHAR(60))
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_cliente_por_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_cliente_por_id`(
	IN idCliente INT
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
    WHERE cliente.id_cliente = idCliente;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_cliente_por_nombre` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_cliente_por_nombre`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_cuenta_x_clave` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_cuenta_x_clave`(
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
/*!50003 DROP PROCEDURE IF EXISTS `buscar_cuenta_x_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_cuenta_x_id`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_empleado` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_empleado`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_empleado_por_nombre` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_empleado_por_nombre`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_proveedor_por_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_proveedor_por_id`(
	IN idProveedor INT
)
BEGIN
	
    SELECT
		proveedor.id_proveedor,
        proveedor.rfc,
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
	WHERE proveedor.id_proveedor = idProveedor;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_proveedor_por_nombre` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_proveedor_por_nombre`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_sucursal_por_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_sucursal_por_id`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_tipoCliente_por_id` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_tipoCliente_por_id`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_ultima_venta` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_ultima_venta`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_ventas_por` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_ventas_por`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `cmb_tipoCliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `cmb_tipoCliente`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `delete_cuenta_contable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_cuenta_contable`(
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
/*!50003 DROP PROCEDURE IF EXISTS `delete_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delete_empleado`(
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
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_articulo` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_articulo`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_categoria` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_categoria`(
	IN `id` INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := categoria_producto.activo FROM categoria_producto WHERE categoria_producto.id_categoria = id;
    
    IF(@estado = 0)THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La categor├¡a ya se encuentra inactiva';
	 ELSE
    	UPDATE categoria_producto SET
		activo = 0    
		WHERE id_categoria = id;
	 END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_cliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_cliente`(
	IN idCliente INT
)
BEGIN
	
	DECLARE estado TINYINT(1);
    SELECT @estado := cliente.activo FROM cliente WHERE cliente.id_cliente = idCliente;
    
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El cliente ya se encuentra inactivo';
    END IF;
	
	UPDATE cliente SET
		cliente.activo = 0
	WHERE cliente.id_cliente = idCliente;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_empleado` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_empleado`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_forma_pago` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_forma_pago`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_proveedor` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_proveedor`(
	IN idProveedor INT
)
BEGIN
	
    DECLARE estado TINYINT(1);    
    SELECT @estado := proveedor.activo FROM proveedor WHERE proveedor.id_proveedor = idProveedor;
    
    IF(@estado = 0) THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El proveedor ya se encuentra inactivo';
    END IF;
    
    UPDATE proveedor SET
		proveedor.activo = 0
	WHERE proveedor.id_proveedor = idProveedor;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_sucursal` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_sucursal`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_tipoCliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_tipoCliente`(
	IN id_tipoCliente INT
)
BEGIN
	
    UPDATE tipo_cliente SET
		tipo_cliente.activo = 0
	WHERE tipo_cliente.id = id_tipoCliente;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_venta` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_venta`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `getEmpleadoById` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEmpleadoById`(
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
/*!50003 DROP PROCEDURE IF EXISTS `getEmpleadoByRFC` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getEmpleadoByRFC`(
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
/*!50003 DROP PROCEDURE IF EXISTS `getListadoEmpleados` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getListadoEmpleados`(
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
/*!50003 DROP PROCEDURE IF EXISTS `insert_cuenta_contable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_cuenta_contable`(
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
/*!50003 DROP PROCEDURE IF EXISTS `insert_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_empleado`(
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
		CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci
)
    MODIFIES SQL DATA
    COMMENT 'Registra un empleado con cuenta contable existente'
BEGIN
	DECLARE v_id_empleado INT DEFAULT 0;
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

	IF p_contrasenia IS NULL OR p_contrasenia = '' THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La contraseña es obligatoria';
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
	INTO v_existe_cuenta
	FROM cuentas_contables
	WHERE id_cuenta = p_id_cuenta_contable;

	IF v_existe_cuenta = 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable indicada no existe';
	END IF;

	SELECT activa, ultimo_nivel
	INTO v_cuenta_activa, v_ultimo_nivel
	FROM cuentas_contables
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
	FROM empleados
	WHERE id_cuenta_contable = p_id_cuenta_contable;

	IF v_cuenta_asignada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La cuenta contable ya está asignada a otro empleado';
	END IF;

	SELECT COUNT(*)
	INTO v_rfc_duplicado
	FROM empleados
	WHERE rfc = UPPER(TRIM(p_rfc));

	IF v_rfc_duplicado > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'El RFC ya está registrado';
	END IF;

	SELECT COUNT(*)
	INTO v_curp_duplicada
	FROM empleados
	WHERE curp = UPPER(TRIM(p_curp));

	IF v_curp_duplicada > 0 THEN
		SIGNAL SQLSTATE '45000'
			SET MESSAGE_TEXT = 'La CURP ya está registrada';
	END IF;

	INSERT INTO empleados (
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
		p_contrasenia,
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
/*!50003 DROP PROCEDURE IF EXISTS `insert_forma_de_pago` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_forma_de_pago`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nueva_sucursal` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nueva_sucursal`(
	IN nombre VARCHAR(100),
    IN descripcion TEXT,
    IN telefono VARCHAR(10),
    IN email VARCHAR(255),
    IN estado VARCHAR(60),
    IN ciudad VARCHAR(60),
    IN direccion VARCHAR(255),
    IN codigo_postal VARCHAR(5)
)
BEGIN
	
    DECLARE id_ultima_sucursal INT;
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        SELECT 'A ocurrido un error', SQLEXCEPTION_MESSAGE();
    END;
    
    START TRANSACTION;
		
        INSERT INTO sucursal(
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
			@id_ultima_sucursal := id_sucursar
		FROM sucursal
        ORDER BY id_sucursar DESC LIMIT 1;
        
        INSERT INTO existencia_x_sucursal(
			id_articulo,
            id_sucursal,
            existencia
        )
        SELECT id_articulo, @id_ultima_sucursal,0 FROM articulo;
        
    COMMIT;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nueva_venta` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nueva_venta`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_articulo` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_articulo`(
	IN codigo_a VARCHAR(65),
    IN id_proveedor_a INT,
    IN id_marca_a INT,
    IN codigo_sat_a VARCHAR(9),
    IN nombre_a VARCHAR(100),
    IN descripcion_a VARCHAR(555),
    IN exento_a TINYINT,
    IN costo DOUBLE    
)
BEGIN
	
    
    
    DECLARE ultimoIndiceArticulo INT;
    
    
    
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'ERROR, PROCEDIMIENTO CANCELADO';
        
    END;
    
    START TRANSACTION;
    
    
    
    
    
	
	
    
    
	
    
    
    INSERT INTO articulo(
		codigo_articulo,
        id_proveedor,
        id_categoria,
        codigo_sat,
        nombre,
        descripcion,
        es_exento,
        costo_unitario,
        activo
    )VALUES(
		codigo_a,
        id_proveedor_a,
        id_marca_a,
        codigo_sat_a,
        nombre_a,
        descripcion_a,
        exento_a,
        costo,			
        1
    );
	
    SELECT @ultimoIndiceArticulo := id_articulo FROM articulo ORDER BY id_articulo DESC LIMIT 1;
    
    INSERT INTO existencia_x_sucursal(
		id_articulo,
        id_sucursal,
        existencia
    )SELECT @ultimoIndiceArticulo, id_sucursal, 0 FROM sucursal;
    
   
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_categoria` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_categoria`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_cliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_cliente`(
  IN rfc_c VARCHAR(13),
  IN id_tipo_cliente INT,
  IN nombre_completo_a VARCHAR(30),
  IN nombre_corto_a VARCHAR(10),
  IN descripcion_a VARCHAR(255),
  IN fecha_nac_a DATE,
  IN correo VARCHAR(30),
  IN estado_a VARCHAR(30),
  IN ciudad_a VARCHAR(40),
  IN direccion_a TEXT,
  IN codigo_postal_a VARCHAR(6)
)
BEGIN
	
    
    DECLARE ultimoIndiceCliente TINYINT;
    DECLARE ultimoIndiceCuentaContable BIGINT UNSIGNED;
    DECLARE rfcClienteBuscado VARCHAR(13);
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		SELECT "HA OCURRIDO UN ERROR" AS message;
		ROLLBACK;
    END;	
    
    START TRANSACTION;
      
		SELECT 
			@rfcClienteBuscado := cliente.rfc 
		FROM cliente 
		WHERE cliente.rfc LIKE rfc_c;
		
		IF @rfcClienteBuscado IS NOT NULL THEN
			SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existen Registros con el cliente';
		END IF;
		
		SELECT
			@ultimoIndiceCliente := cliente.id_cliente
		FROM cliente
		ORDER BY id_cliente DESC LIMIT 1;
		
        if @ultimoIndiceCliente IS NULL THEN
			SET @ultimoIndiceCliente = 1;
        END IF;
        
		INSERT INTO sub_cuentas_tercer_nivel(
			id_cuenta_superior,
			clave,
			nombre,
			descripcion
		)VALUES(
			4,
			CONCAT('100.4.',@ultimoIndiceCliente + 1),
			nombre_corto_a,
			descripcion_a
		);
		
		SELECT 
			@ultimoIndiceCuentaContable := sub_cuentas_tercer_nivel.id_cuenta
		FROM sub_cuentas_tercer_nivel
		ORDER BY id_cuenta DESC LIMIT 1;
		
		INSERT INTO cliente(
			rfc,
            id_tipoCliente,
			id_cuenta_contable,
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
			rfc_c,
            id_tipo_cliente,
			@ultimoIndiceCuentaContable,
			nombre_completo_a,
			nombre_corto_a,
			fecha_nac_a,
			correo,
			estado_a,
			ciudad_a,
			direccion_a,
			codigo_postal_a,
			1
		  );      
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_empleado` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_empleado`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_proveedor` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_proveedor`(
	IN rfc_p VARCHAR(13),
    IN nombre_p VARCHAR(30),
    IN descripcion_p VARCHAR(255),
    IN correo_p VARCHAR(30),
    IN estado_p VARCHAR(30),
    IN ciudad_p VARCHAR(40),
    IN direccion_p TEXT,
    IN codigo_postal_p VARCHAR(6)
)
BEGIN
	
    
    DECLARE ultimoIndiceProveedor tinyint;
    DECLARE ultimoIndiceCuentaContable BIGINT UNSIGNED;
    DECLARE rfcProveedorListado VARCHAR(13);
    
    SELECT
		@rfcProveedorListado := proveedor.rfc
    FROM proveedor
    WHERE proveedor.rfc LIKE rfc_p;
    
    IF @rfcProveedorListado IS NOT NULL THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existen registros del proveedor';
    END IF;
	
    SELECT 
		@ultimoIndiceProveedor := proveedor.id_proveedor
	FROM proveedor 
    ORDER BY id_proveedor DESC LIMIT 1;
    
    INSERT INTO sub_cuentas_tercer_nivel(
		id_cuenta_superior,
        clave,
        nombre,
        descripcion
    )
    VALUES(
		7,
        
        concat('200.1.',@ultimoIndiceProveedor + 1),
        nombre_p,
        descripcion_p
    );
    
    SELECT
		@ultimoIndiceCuentaContable := sub_cuentas_tercer_nivel.id_cuenta
	FROM sub_cuentas_tercer_nivel
    ORDER BY id_cuenta DESC LIMIT 1;
    
    INSERT INTO proveedor(
		rfc,
        id_cuenta_contable,
        nombre,
        descripcion,
        correo_electronico,
        estado,
        ciudad,
        direccion,
        codigo_postal,
        activo
    )
    VALUES(
		rfc_p,
        @ultimoIndiceCuentaContable,
        nombre_p,
        descripcion_p,
        correo_p,
        estado_p,
        ciudad_p,
        direccion_p,
        codigo_postal_p,
        1
    );
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_tipoCliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_tipoCliente`(
	IN nombre_t VARCHAR(100),
    IN descripcion_t VARCHAR(500)
)
BEGIN
	
    INSERT INTO tipo_cliente(
		nombre,
        descripcion,
        activo
    )VALUES(
		nombre_t,
        descripcion_t,
        1
    );
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_tipo_cliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_tipo_cliente`(	
    IN nombre_t VARCHAR(100),
    IN descripcion_t VARCHAR(500)
)
BEGIN
	
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
		ROLLBACK;
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Error al realizar la operacion, procedimiento cancelado';
    END;
    
    START TRANSACTION;
    
    INSERT INTO tipo_cliente(
		nombre,
        descripcion
    )VALUES (
		nombre_t,
        descripcion_t
    );
    
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nva_categoria` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nva_categoria`(IN `nombre_c` VARCHAR(60) CHARSET utf8, IN `descripcion_m` VARCHAR(255) CHARSET utf8)
BEGIN

	DECLARE nombreCategoria VARCHAR(60);
    
    SELECT 
		@nombreCategoria := categoria_producto.nombre 
	FROM categoria_producto WHERE categoria_producto.nombre = nombre_c;
    
    IF (@nombreCategoria = nombre_c) THEN
    	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Registro duplicado';
    END IF;
    
	INSERT INTO categoria_producto 
    (
		categoria_producto.nombre, 
		categoria_producto.descripcion, 
		categoria_producto.activo
    ) 
    VALUES
    (
        nombre_c,
        descripcion_m,
        1
    );

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `list_cmbGrupoContable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `list_cmbGrupoContable`()
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
/*!50003 DROP PROCEDURE IF EXISTS `list_cmbRubroCuentasContables` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `list_cmbRubroCuentasContables`(
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
/*!50003 DROP PROCEDURE IF EXISTS `spGetListadoEmpleados` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `spGetListadoEmpleados`()
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
INNER JOIN sucursal ON empleados.id_sucursal = sucursal.id_sucursar ORDER BY id_empleado ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_consultarEmpleadoPorRFC` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_consultarEmpleadoPorRFC`(IN `rfc` VARCHAR(13) CHARSET utf8)
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_articulo` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_articulo`(
	IN id_a BIGINT UNSIGNED,
	IN proveedor_a VARCHAR(30),
    IN marca_a VARCHAR(60),
    IN codigo_sat_a VARCHAR(9),
    IN nombre_a VARCHAR(100),
    IN descripcion_a VARCHAR(555),
    IN exento_a TINYINT,
    IN costo DOUBLE,
    IN precio_g DOUBLE,
    IN precio_m DOUBLE,
    IN cant_m INT
)
BEGIN
	
    DECLARE indiceProveedor BIGINT UNSIGNED;
    DECLARE indiceCategoria BIGINT UNSIGNED;
    
    SELECT @indiceProveedor := proveedor.id_proveedor FROM proveedor WHERE proveedor.nombre = proveedor_a;
    SELECT @indiceCategoria := categoria_producto.id_categoria FROM categoria_producto WHERE categoria_producto.nombre = marca_a;
    
    UPDATE articulo 
    SET
		id_proveedor = @indiceProveedor,
        id_categoria = @indiceCategoria,
        codigo_sat = codigo_sat_a,
        nombre = nombre_a,
        descripcion = descripcion_a,
        es_exento = exento_a,
        costo_unitario = costo,
        precio_general = precio_g,
        precio_mayoreo = precio_m,
        cantidad_mayoreo = cant_m,
        activo = 1
    WHERE articulo.id_articulo = id_a;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_categoria` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_categoria`(IN `id_categoria_c` INT UNSIGNED, IN `nombre_c` VARCHAR(60), IN `descripcion_c` VARCHAR(255))
BEGIN
	UPDATE categoria_producto
    SET
		nombre = nombre_c,
        descripcion = descripcion_c,
        activo = 1
	WHERE id_categoria = id_categoria_c;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_cliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_cliente`(
	IN id_cliente_c BIGINT UNSIGNED,
    IN id_tipoCliente_c INT,
    IN clave_cta VARCHAR(18),
    IN nombre_completo_a VARCHAR(30),
	IN nombre_corto_a VARCHAR(10),
	IN descripcion_a VARCHAR(255),
	IN fecha_nac_a DATE,
	IN correo VARCHAR(30),
	IN estado_a VARCHAR(30),
	IN ciudad_a VARCHAR(40),
	IN direccion_a TEXT,
	IN codigo_postal_a VARCHAR(6)
)
BEGIN
	
    UPDATE sub_cuentas_tercer_nivel
    SET
		descripcion = descripcion_a
    WHERE clave = clave_cta;
    
    UPDATE cliente
    SET
		nombre_completo = nombre_completo_a,
        id_tipoCliente = id_tipoCliente_c,
        nombre_corto = nombre_corto_a,
        fecha_nac = fecha_nac_a,
        correo_electronico = correo,
        estado = estado_a,
        ciudad = ciudad_a,
        direccion = direccion_a,
        codigo_postal = codigo_postal_a,
        activo = 1
    WHERE cliente.id_cliente = id_cliente_c;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_cuenta_contable` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_cuenta_contable`(
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
/*!50003 DROP PROCEDURE IF EXISTS `update_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_empleado`(
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
	   OR p_correo_electronico IS NULL OR TRIM(p_correo_electronico) = ''
	   OR p_contrasenia IS NULL OR p_contrasenia = '' THEN
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
		contrasenia = p_contrasenia,
		activo = p_activo
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
/*!50003 DROP PROCEDURE IF EXISTS `update_forma_de_pago` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_forma_de_pago`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_proveedor` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_proveedor`(
	IN id_p BIGINT UNSIGNED,
	IN nombre_p VARCHAR(30),
    IN descripcion_p VARCHAR(255),
    IN correo_p VARCHAR(30),
    IN estado_p VARCHAR(30),
    IN ciudad_p VARCHAR(40),
    IN direccion_p TEXT,
    IN codigo_postal_p VARCHAR(6)
)
BEGIN
	
    UPDATE proveedor
    SET
		nombre = nombre_p,
        descripcion = descripcion_p,
        correo_electronico = correo_p,
        estado = estado_p,
        ciudad = ciudad_p,
        direccion = direccion_p,
        codigo_postal = codigo_postal_p,
        activo = 1
	WHERE proveedor.id_proveedor = id_p;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_sucursal` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_sucursal`(
	IN id_sucursal INT,
	IN nombre VARCHAR(100),
    IN descripcion TEXT,
    IN telefono VARCHAR(10),
    IN email VARCHAR(255),
    IN estado VARCHAR(60),
    IN ciudad VARCHAR(60),
    IN direccion VARCHAR(255),
    IN codigo_postal VARCHAR(5)
)
BEGIN
	
    UPDATE sucursal 
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
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `update_tipoCliente` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_tipoCliente`(
	IN id_tipoCliente INT,
	IN nombre_t VARCHAR(100),
    IN descripcion_t VARCHAR(500)
)
BEGIN
	
    UPDATE tipo_cliente SET
		nombre = nombre_t,
        descripcion = descripcion_t,
        activo = 1
    WHERE id = id_tipoCliente;
        
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `validar_entrada` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `validar_entrada`(IN `nombre_c` VARCHAR(10) CHARSET utf8, IN `contra_c` VARCHAR(15) CHARSET utf8)
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_articulos` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_articulos`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_clientes` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_clientes`(
	IN `nombre_c` VARCHAR(30)
)
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_cliente_por_rfc` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_cliente_por_rfc`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_cmbRubroCuentasContables` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_cmbRubroCuentasContables`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_codigos_articulos` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_codigos_articulos`()
BEGIN
	
    SELECT articulo.codigo_articulo
    FROM articulo;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_cuentas_contables` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_cuentas_contables`(
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
/*!50003 DROP PROCEDURE IF EXISTS `ver_existencias_articulo_sucursal` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_existencias_articulo_sucursal`(
	IN id_articulo INT
)
BEGIN
	SELECT 
		sucursal.nombre,
		existencia_x_sucursal.existencia
	FROM existencia_x_sucursal
	INNER JOIN sucursal ON existencia_x_sucursal.id_sucursal = sucursal.id_sucursar
	WHERE existencia_x_sucursal.id_articulo = id_articulo;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_formas_de_pago` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_formas_de_pago`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_indices_categorias` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_indices_categorias`()
BEGIN

	

	SELECT categoria_producto.id_categoria FROM categoria_producto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_indice_venta_actual` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_indice_venta_actual`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_marcas` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_marcas`(
	IN `nombre` VARCHAR(60)
)
BEGIN

	SELECT
		cp.id_categoria,
		cp.nombre,
		cp.descripcion,
		cp.activo
	FROM categoria_producto AS cp
	WHERE cp.nombre LIKE CONCAT('%',nombre,'%');

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_nombres_categorias` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_nombres_categorias`()
BEGIN

	SELECT 
		categoria_producto.id_categoria,
		categoria_producto.nombre 
    FROM categoria_producto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_nombres_proveedor` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_nombres_proveedor`()
BEGIN
	
    SELECT
		proveedor.id_proveedor,
		proveedor.nombre
    FROM proveedor;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_nombres_sucursal` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_nombres_sucursal`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_proveedores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_proveedores`(
	IN `nombre` VARCHAR(30)
)
BEGIN
SELECT
	p.id_proveedor,
	p.rfc,
	c.clave,
	p.nombre,
	p.descripcion,
	p.correo_electronico,
	p.estado,
	p.ciudad,
	p.direccion,
	p.codigo_postal,
	p.activo
FROM
	proveedor AS p
INNER JOIN cuentas_contables AS c ON p.id_cuenta_contable = c.id_cuenta
WHERE
	p.nombre LIKE CONCAT('%',nombre,'%'); END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_proveedor_por_rfc` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_proveedor_por_rfc`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_rfcProveedores` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_rfcProveedores`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_rfc_clientes` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_rfc_clientes`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_rfc_empleado_por_sucursal` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_rfc_empleado_por_sucursal`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_sucursales` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_sucursales`()
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_sucursales_nombres` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_sucursales_nombres`()
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
/*!50003 DROP PROCEDURE IF EXISTS `ver_tipo_clientes` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_tipo_clientes`(
	IN nombre_tipo_cliente VARCHAR(100)
)
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_ventas` */;
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_ventas`(
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
ALTER DATABASE `kath_erp` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-07-13  2:32:33
