-- MariaDB dump 10.19  Distrib 10.4.28-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: kath_erp
-- ------------------------------------------------------
-- Server version	10.4.28-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `id_articulo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `codigo_articulo` varchar(65) NOT NULL,
  `id_proveedor` int(10) unsigned NOT NULL,
  `id_categoria` int(10) unsigned NOT NULL,
  `codigo_sat` varchar(9) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(555) DEFAULT NULL,
  `es_exento` tinyint(1) NOT NULL,
  `costo_unitario` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_articulo`),
  UNIQUE KEY `codigo_articulo` (`codigo_articulo`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_producto` (`id_categoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=279 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria_producto` (
  `id_categoria` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_producto`
--

LOCK TABLES `categoria_producto` WRITE;
/*!40000 ALTER TABLE `categoria_producto` DISABLE KEYS */;
INSERT INTO `categoria_producto` VALUES (1,'Ropa','Vestimenta en general',1),(2,'Electr├│nica','Productos electr├│nicos',1),(3,'Hogar y Jard├¡n','Art├¡culos para el hogar y jard├¡n',1),(4,'Deportes','Art├¡culos deportivos',1),(5,'Alimentaci├│n','Productos alimenticios',1),(6,'Muebles','Muebles y decoraci├│n para el hogar',1),(7,'Joyer├¡a','Joyas y accesorios',1),(8,'Belleza','Productos de belleza',1),(9,'Calzado','Zapatos y calzado',1),(10,'Libros','Libros y literatura',1),(11,'Salud','Productos de salud',1),(12,'Electrodom├®sticos','Electrodom├®sticos para el hogar',1),(13,'Inform├ítica','Equipos y accesorios de inform├ítica',1),(14,'M├║sica','Instrumentos musicales y m├║sica',1),(15,'Cine','Pel├¡culas y entretenimiento',1),(16,'Arte','Arte y artesan├¡a',1),(17,'Infantil','Productos para ni├▒os',1),(18,'Autom├│viles','Accesorios y piezas de autom├│viles',1),(19,'Electrodom├®sticos','Electrodom├®sticos para el hogar',1),(20,'Jardiner├¡a','Suministros de jardiner├¡a',1),(21,'Electrodom├®sticos','Electrodom├®sticos para el hogar',1),(22,'Moda','Art├¡culos de moda y ropa',1),(23,'Electr├│nica de Consumo','Productos electr├│nicos de consumo',1),(24,'Deportes y Aire Libre','Equipamiento deportivo y actividades al aire libre',1),(25,'Salud y Belleza','Productos de salud y belleza',1),(26,'Herramientas','Suministros y herramientas mecanicas o de oficio',1),(27,'Electrodom├®sticos de Cocina','Electrodom├®sticos de cocina y utensilios',1),(28,'Animales','Productos para mascotas y animales',1),(29,'Arte y Manualidades','Materiales y suministros para arte y manualidades',1),(30,'Cine y Entretenimiento','Pel├¡culas y entretenimiento',1),(31,'Beb├®s y Ni├▒os','Productos para beb├®s y ni├▒os',1),(32,'Libros y Literatura','Libros y literatura en general',1),(33,'Calzado y Accesorios','Zapatos',1),(34,'Joyer├¡a y Relojes','Joyer├¡a y relojes en general',1),(35,'Tecnolog├¡a','Productos y dispositivos tecnol├│gicos',1),(36,'Hogar y Cocina','Art├¡culos para el hogar y utensilios de cocina',1),(37,'Instrumentos Musicales','Instrumentos musicales y accesorios',1),(38,'Juegos y Juguetes','Juegos',1),(39,'Alimentos y Bebidas','Productos alimenticios y bebidas',1),(40,'Oficina y Papeler├¡a','Suministros de oficina y papeler├¡a',1),(41,'VideoJuegos','Categor├¡a destinada a los videojuegos tanto en digital como en f├¡sico',1),(42,'Instrumentos Mecanicos','Herramientas y herrajes',1),(43,'Lubricantes Generales','Lubricantes automovilisticos y mec├ínicos de uso general',1),(44,'Tintes Capilares','secci├│n de tintes capilares temporales y permanentes',1);
/*!40000 ALTER TABLE `categoria_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `id_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_tipoCliente` int(11) NOT NULL,
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
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  KEY `Fk_tipoCliente_x_cliente` (`id_tipoCliente`),
  CONSTRAINT `Fk_tipoCliente_x_cliente` FOREIGN KEY (`id_tipoCliente`) REFERENCES `tipo_cliente` (`id`),
  CONSTRAINT `id_cuenta_contable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (25,1,'XAXXAXAXXAAAX',120,'PUBLCO GENERAL','GENERAL','1999-01-01','NA','NA','NA','NA','NA',1),(26,2,'XAXXAXAXAXAAA',121,'UN CLIENTE DE PRUEBA','PR01','1997-01-01','fakemail@mail.com','CHIAPAS','TUXTLA','UNA DIRECCION DE PRUEBA','29000',1),(27,2,'XAAAXAXAAXAAX',122,'OTRO CLIENTE','OTRO','1997-01-01','fakemail@mailto.com.mx','CHIAPAS','TUXTLA','UNA DIRECCION DE PRUEBA','29000',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_clientes`
--

DROP TABLE IF EXISTS `cobro_clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `compras` (
  `id_compra` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `folio_factura` varchar(13) NOT NULL,
  `fecha_compra` date NOT NULL,
  `tipo_compra` tinyint(4) NOT NULL,
  `id_empleado` int(10) unsigned NOT NULL,
  `id_proveedor` int(10) unsigned NOT NULL,
  `subtotal` double NOT NULL,
  `iva` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_compra`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE
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
-- Table structure for table `cuentas_padre`
--

DROP TABLE IF EXISTS `cuentas_padre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas_padre`
--

LOCK TABLES `cuentas_padre` WRITE;
/*!40000 ALTER TABLE `cuentas_padre` DISABLE KEYS */;
INSERT INTO `cuentas_padre` VALUES (1,'100','Activo','cuentas del activo',0,0,0),(2,'200','Pasivo','cuentas del pasivo',0,0,0),(3,'300','Capital contable','cuentas del capital contable',0,0,0),(4,'400','Ingresos','cuentas de ingresos',0,0,0),(5,'401','dev. sobre ingresos','cuentas de registro de devoluciones',0,0,0),(6,'500','compras','cuentas de compras',0,0,0),(7,'501','dev. o desc. Sobre compra','descuentos sobre compras',0,0,0),(8,'600','Gastos','cuentas de gastos',0,0,0),(9,'700','Resultado integral de financia','cuenta de orden',0,0,0);
/*!40000 ALTER TABLE `cuentas_padre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleados`
--

DROP TABLE IF EXISTS `empleados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `empleados` (
  `id_empleado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_sucursal` bigint(20) unsigned NOT NULL,
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
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `curp` (`curp`),
  KEY `Fk_sucursal_empleado` (`id_sucursal`),
  CONSTRAINT `Fk_sucursal_empleado` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,6,'PEJU920506ABC','PEJU920506HDFRRN07','Juan P├®rez','Juan','1981-01-19','juanfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 5 de Mayo #123','29445','xYz#12Ab',1),(2,1,'GAMA880213DEF','GAMA880213MHDFRRN0','Mar├¡a Garc├¡a','Mar├¡a','1986-07-01','mar├¡afakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Ju├írez #456','29465','P@ssw0rd',1),(3,3,'LOCA780704GHI','LOCA780704MHDFRL07','Carlos L├│pez','Carlos','1988-01-23','carlosfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Belisario Dom├¡nguez #789','29198','Qwerty!23',1),(4,3,'MAAA950312JKL','MAAA950312MHDFRN02','Ana Mart├¡nez','Ana','1989-09-21','anafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Central #1010','29146','Summer*99',1),(5,5,'ROLU890522MNO','ROLU890522MHDFDS03','Luis Rodr├¡guez','Luis','1983-09-13','luisfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Hidalgo #234','29365','Secure$456',1),(6,3,'FELO930608PQR','FELO930608MHDFNR00','Laura Fern├índez','Laura','1995-06-30','laurafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Prolongaci├│n 16 de Septiembre #567','29252','Orange$Tree67',1),(7,4,'GOAL900715STU','GOAL900715HDFNNL08','Alejandro Gonz├ílez','Alejandro','1982-12-09','alejandrofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 20 de Noviembre #890','29316','1Lov3Cats',1),(8,2,'DISO941118VWX','DISO941118MHDFXF09','Sof├¡a D├¡az','Sof├¡a','1994-11-16','sof├¡afakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calzada Sumidero #1112','29410','UnaMas_facil',1),(9,5,'SAMA861002YZA','SAMA861002MHDFNH04','Manuel S├ínchez','Manuel','1984-10-02','manuelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Los Laguitos #1314','29310','Chocol8!Chip',1),(10,2,'RAPA861220BCD','RAPA861220MHDFMR05','Patricia Ram├¡rez','Patricia','1995-10-23','patriciafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Constituci├│n #1516','29066','P@rkRang3r',1),(11,6,'TOMI900429EFG','TOMI900429MHDFSL06','Miguel Torres','Miguel','1991-05-27','miguelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Cuauht├®moc #1718','29267','Wint3rFrost$',1),(12,2,'FLOA880917HIJ','FLOA880917MHDFRN07','Andrea Flores','Andrea','1989-02-14','andreafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Allende #1920','29119','P@ssionFl0w3r',1),(13,4,'HEDO960725KLM','HEDO960725MHDFDN08','Daniel Hern├índez','Daniel','1993-01-25','danielfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. ├üngel Albino Corzo #2122','29097','M0onlight$ky',1),(14,5,'VACA890314NOP','VACA890314MHDFRM09','Carmen Vargas','Carmen','1991-09-26','carmenfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 15 de Julio #2324','29042','B3autiful*Sun',1),(15,4,'MEOJ940610QRS','MEOJ940610MHDFND10','Eduardo Mendoza','Eduardo','1982-04-01','eduardofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Revoluci├│n #2526','29439','S0larSyst3m!',1),(16,2,'CUGA950826TUV','CUGA950826MHDFNB11','Gabriela Cruz','Gabriela','1993-01-10','gabrielafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Juventud #2728','29413','Starry*Night',1),(17,2,'MOMJ891121WXY','MOMJ891121MHDFNR12','Javier Morales','Javier','1984-10-13','javierfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Miguel Alem├ín #2930','29144','AlgoMasFacil',1),(18,5,'RIAR930601ZAB','RIAR930601MHDFQL13','Raquel R├¡os','Raquel','1983-08-02','raquelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Reforma #3132','29221','R@inbow@fterRai',1),(19,2,'ORFF871214BCD','ORFF871214MHDFNN14','Francisco Ortega','Francisco','1994-09-18','franciscofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Doctor Belisario Dom├¡nguez #3334','29496','Sparkl1ngDiam0n',1),(20,3,'NUNN900225EFG','NUNN900225MHDFXT15','Natalia N├║├▒ez','Natalia','1991-07-15','nataliafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Central Poniente #3536','29209','F0restWhisper$',1),(21,4,'SIDI910517HJK','SIDI910517MHDFLG16','Diego Silva','Diego','1987-05-15','diegofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 2 Norte #3738','29133','S@ndyB3@ch!',1),(22,1,'AGVV930811PLM','AGVV930811MHDFGT17','Valentina Aguilar','Valentina','1983-06-26','valentinafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 4 Oriente #3930','29048','M0unt@inT0p',1),(23,6,'MORR921010KJH','MORR921010MHDFDC18','Ricardo Mora','Ricardo','1982-05-30','ricardofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Laguitos #4132','29395','SecretG@rden$',1),(24,6,'PECM940731QWE','PECM940731MHDFML19','Camila Pe├▒a','Camila','1992-04-01','camilafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 6 Sur #4334','29146','H@ppyH3arts',1),(25,3,'MONG900416RFG','MONG900416MHDFNL20','Guillermo Montoya','Guillermo','1989-04-25','guillermofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 8 Poniente #4536','29482','CoolBreez3$',1),(26,3,'RIMA910218TGB','RIMA910218MHDFTS21','Martina Rivas','Martina','1997-02-25','martinafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Convivencia #4738','29295','S1lv3rLin1ngs',1),(27,2,'CASA960511YHN','CASA960511MHDFTR22','Adri├ín Castillo','Adri├ín','1989-06-18','adri├ínfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 10 Oriente #4930','29148','Myst1cM00n',1),(28,1,'PARR910713GBV','PARR910713MHDFNS23','Renata Paredes','Renata','1981-06-21','renatafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 12 Sur #4132','29027','H3althyL1f3$',1),(29,6,'GUSE921215CVB','GUSE921215MHDFBT24','Sebasti├ín Guzm├ín','Sebasti├ín','1988-08-10','sebasti├ínfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 14 Norte #4334','29283','D3epBlu3Sea',1),(30,2,'PEIB930320SDX','PEIB930320MHDFRX25','Isabella Peralta','Isabella','1994-10-16','isabellafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 16 Oriente #4536','29323','Fr3shStart!',1),(31,4,'VAJJ900504KLS','VAJJ900504MHDFXS26','Jos├® Valenzuela','Jos├®','1981-09-15','jos├®fakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 18 Sur #4738','29100','Laugh@Lot$',1),(32,3,'ROAA870827LMD','ROAA870827MHDFLN27','Antonella Rojas','Antonella','1986-01-08','antonellafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 20 Norte #4930','29130','Adventur3T1m3',1),(33,1,'COAA910623LKP','COAA910623MHDFNR28','Andr├®s Cordero','Andr├®s','1982-12-26','andr├®sfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 22 Oriente #5132','29034','H0lid@y@Dream',1),(34,2,'SAVV940712LPQ','SAVV940712MHDFTC29','Victoria Salazar','Victoria','1982-05-28','victoriafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 24 Sur #5334','29294','Playful*Smil3',1),(35,1,'BAIN930802EJK','BAIN930802MHDFGN30','Ignacio Bravo','Ignacio','1996-06-08','ignaciofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 26 Norte #5536','29206','B3autifulM3m0ri',1),(36,1,'ACOM870101JKH','ACOM870101MHDFVN31','Miranda Acosta','Miranda','1993-08-15','mirandafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 28 Oriente #5738','29040','Wish3s&Candl3s',1),(37,2,'FUAM900510OPW','FUAM900510MHDFRT32','Mat├¡as Fuentes','Mat├¡as','1994-07-06','mat├¡asfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Cultura #5930','29387','Creat1veM1nd$',1),(38,6,'OREL950315TGY','OREL950315MHDFRT33','Elena Orellana','Elena','1992-03-20','elenafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 30 Sur #5132','29203','B3Insp1r3d!',1),(39,2,'ESBM910717GHJ','ESBM910717MHDFNH34','Benjam├¡n Espinoza','Benjam├¡n','1990-11-05','benjam├¡nfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 32 Norte #5334','29393','P0sitiveV1bes',1),(40,2,'LEVR920321FDS','LEVR920321MHDFNN35','Ren├®e Leiva','Ren├®e','1996-04-30','ren├®efakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 34 Oriente #5536','29141','En3rgetic*Day',1),(46,3,'XAXXAXAAAXXAA','XXXAXAXAAAXAAXAAAX','ROBERTO MOLINA SARMIENTO','ROBER','1998-05-25','mail_m@fakemail.com','CHIAPAS','TUXTLA GUTIERREZ','4A NORTE PONIENTE #970','29050','chiapas12',1);
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `existencia_x_sucursal`
--

DROP TABLE IF EXISTS `existencia_x_sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `existencia_x_sucursal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_articulo` int(10) unsigned NOT NULL,
  `id_sucursal` bigint(20) unsigned NOT NULL,
  `existencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `Rlt_articulo_existencia` (`id_articulo`),
  KEY `Rlt_sucursal_existencia` (`id_sucursal`),
  CONSTRAINT `Rlt_articulo_existencia` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Rlt_sucursal_existencia` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursar`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2176 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `factura` (
  `id_factura` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `folio_fiscal` varchar(38) NOT NULL,
  `fecha_emisin` date NOT NULL,
  `fecha_certificacion` date NOT NULL,
  `id_venta` int(10) unsigned NOT NULL,
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formas_de_pago` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_de_pago` varchar(18) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formas_de_pago`
--

LOCK TABLES `formas_de_pago` WRITE;
/*!40000 ALTER TABLE `formas_de_pago` DISABLE KEYS */;
INSERT INTO `formas_de_pago` VALUES (1,'efectivo',1),(2,'tarjeta de credito',1),(3,'tarjeta de debito',1),(4,'cheque',1),(5,'transferencia',1),(7,'Cupon promo',0);
/*!40000 ALTER TABLE `formas_de_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gastos` (
  `id_gasto` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fecha_operacion` date NOT NULL,
  `id_empleado` int(10) unsigned NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `id_cuenta_contable` int(10) unsigned NOT NULL,
  `importe` double NOT NULL,
  `iva` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_gasto`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `gastos_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `gastos_ibfk_2` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
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
-- Table structure for table `pago_proveedor`
--

DROP TABLE IF EXISTS `pago_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permiso_x_empleado` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `id_permiso` int(10) unsigned NOT NULL,
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permisos` (
  `id_permiso` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `descripcion` text DEFAULT NULL,
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precios_x_tipocliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_articulo` int(10) unsigned NOT NULL,
  `id_tipoCliente` int(11) NOT NULL,
  `precio` double NOT NULL,
  `precios_especial` double DEFAULT NULL,
  `cant_p_precioEspecial` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_articulo_x_precios` (`id_articulo`),
  KEY `fk_tipoCliente_x_precios` (`id_tipoCliente`),
  CONSTRAINT `fk_articulo_x_precios` FOREIGN KEY (`id_articulo`) REFERENCES `articulo` (`id_articulo`) ON UPDATE CASCADE,
  CONSTRAINT `fk_tipoCliente_x_precios` FOREIGN KEY (`id_tipoCliente`) REFERENCES `tipo_cliente` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
/*!40101 SET character_set_client = utf8 */;
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
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'REFCPR01',65,'Colomer sa de cv','distribuidora de productos de belleza al por mayor','correo_proveedor1@hotmail.com','Edo MX','Cd Mx','Av insurgentes','26087',1),(2,'REFCPR02',66,'Revlon SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor2@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25791',1),(3,'REFCPR03',67,'Bidiip','distribuidora de productos de belleza al por mayor','correo_proveedor3@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28447',1),(4,'REFCPR04',68,'Anven SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor4@hotmail.com','Edo MX','Cd Mx','Av insurgentes','27511',1),(5,'REFCPR05',69,'Nefertiti SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor5@hotmail.com','Edo MX','Cd Mx','Av insurgentes','26712',1),(6,'REFCPR06',70,'Ricardo Rodrigo','distribuidora de productos de belleza al por mayor','correo_proveedor6@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25761',1),(7,'REFCPR07',71,'Raul Trinidad','DISTRIBUIDORA DE PRODUCTOS DE BELLEZA PROFESIONAL PARA MINORISTAS O SALONES','correo_proveedor7@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28733',1),(8,'REFCPR08',72,'Henry Rodriguez','distribuidora de productos de belleza al por mayor','correo_proveedor8@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28906',1),(9,'REFCPR09',73,'Cosmetica capilar','distribuidora de productos de belleza al por mayor','correo_proveedor9@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25348',1),(10,'REFCPR10',74,'Cosmetica insurgentes','distribuidora de productos de belleza al por mayor','correo_proveedor10@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25862',1),(11,'REFCPR11',75,'Belleza cien','distribuidora de productos de belleza al por mayor','correo_proveedor11@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27249',1),(12,'REFCPR12',76,'Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor12@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27748',1),(13,'REFCPR13',77,'Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor','correo_proveedor13@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27219',1),(14,'REFCPR14',78,'Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor','correo_proveedor14@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27151',1),(15,'REFCPR15',79,'Regio Belleza','distribuidora de productos de belleza al por mayor','correo_proveedor15@hotmail.com','Nuevo Leon','Monterrey','Av 13 sur','28947',1),(16,'REFCPR16',80,'Somo Tapatio SA de CV','distribuidora de productos para oficina','correo_proveedor16@hotmail.com','Guadalajara','Jalisco','Av 13 sur','28960',1),(17,'REFCPR17',81,'Hidra Color SA de Cv','distribuidora de productos de belleza al por mayor actualizado','correo_proveedor17@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25542',1),(18,'REFCPR18',82,'Color Shot SAS de CV','distribuidora de productos de belleza al por mayor','correo_proveedor18@hotmail.com','Edo MX','Cd Mx','Av 13 sur','28472',1),(19,'REFCPR19',83,'Color Tech','distribuidora de productos de belleza al por mayor','correo_proveedor19@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25526',1),(20,'REFCPR20',84,'Nutrapel SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor20@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25386',1),(21,'REFCPR21',85,'Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor','correo_proveedor21@hotmail.com','Edo MX','Cd Mx','Av 13 sur','26553',1),(22,'REFCPR22',86,'Insumos del sureste','distribuidora de consumibles para oficina','correo_proveedor22@hotmail.com','Yucatan','Merida','Av 13 sur','26285',1),(23,'REFCPR23',87,'Tecnologica San Cristobal SA d','tienda de articulos tecono├│gicos','correo_proveedor23@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','26080',1),(24,'REFCPR24',88,'Micro Chip SA de Cv','tienda de articulos tecono├│gicos','correo_proveedor24@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','28583',1),(25,'REFCPR25',89,'Jairo Eniquez','tienda de articulos tecono├│gicos','correo_proveedor25@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','28233',1),(36,'XAXXAXXAXAXXA',118,'INSUMOS COLORIDOS DEL ORIENTE','PROVEEDOR DE PRODUCTOS VARIOS','mail_fk@fakemail.com','HIDALGO','HIDALGO','CALLE ORIENTE MEDIO COLONIA ALHONDIGA GRANADITAS','29078',1);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retiros_de_efectivo`
--

DROP TABLE IF EXISTS `retiros_de_efectivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retiros_de_efectivo` (
  `id_retiro` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `importe` double NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_retiro`),
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
-- Table structure for table `sub_cuentas_segundo_nivel`
--

DROP TABLE IF EXISTS `sub_cuentas_segundo_nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_cuentas_segundo_nivel`
--

LOCK TABLES `sub_cuentas_segundo_nivel` WRITE;
/*!40000 ALTER TABLE `sub_cuentas_segundo_nivel` DISABLE KEYS */;
INSERT INTO `sub_cuentas_segundo_nivel` VALUES (1,1,'100.1','Caja','',0,0,0),(2,1,'100.2','Bancos nacionales','',0,0,0),(3,1,'100.3','Bancos extranjeros','',0,0,0),(4,1,'100.4','Clientes nacionales','',0,0,0),(5,1,'100.5','Iva acreditable pagado','',0,0,0),(6,1,'100.6','iva acreditable por pagar','',0,0,0),(7,2,'200.1','Proveedores nacionales','',0,0,0),(8,2,'200.2','Iva trasladado cobrado','',0,0,0),(9,2,'200.3','iva trasladado pend de cobro','',0,0,0),(10,4,'400.1','ingresos por ventas','',0,0,0),(11,5,'401.1','devoluciones y desc sobre vent','',0,0,0),(12,6,'500.1','compras','',0,0,0),(13,7,'501.1','dev. Y desc. Sobre compras','',0,0,0),(14,8,'600.1','Gastos generales','',0,0,0),(15,8,'600.2','Gastos de venta','',0,0,0),(16,8,'600.3','Gastos de administracion','',0,0,0),(17,8,'600.4','Gastos de compra','',0,0,0),(18,9,'700.1','gastos financieros','',0,0,0);
/*!40000 ALTER TABLE `sub_cuentas_segundo_nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_cuentas_tercer_nivel`
--

DROP TABLE IF EXISTS `sub_cuentas_tercer_nivel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_cuentas_tercer_nivel`
--

LOCK TABLES `sub_cuentas_tercer_nivel` WRITE;
/*!40000 ALTER TABLE `sub_cuentas_tercer_nivel` DISABLE KEYS */;
INSERT INTO `sub_cuentas_tercer_nivel` VALUES (1,1,'100.1.1','Efectivo disponible','',0,0,0),(2,1,'100.1.2','Fondo de ahorro','efectivo ahorrado',0,0,0),(3,1,'100.1.3','Efectivo en circulacion','efectivo usado para cambios',0,0,0),(4,2,'100.2.1','Bancomer','cuenta bancomer',0,0,0),(5,5,'100.5.1','iva acreditable pagado','iava acreditable efectivamente pagado',0,0,0),(6,6,'100.6.1','iva acreditable por pagar','iva acreditable pendiente de pago',0,0,0),(7,8,'200.2.1','iva trasladado','iva cobrado',0,0,0),(8,9,'200.3.1','iva por trasladar','iva por cobrar',0,0,0),(9,10,'400.1.1','venta de mercanc├¡as','ingresos obtenidos por la venta de mercancias',0,0,0),(10,11,'401.1.1','Desc y Dev sobre ventas','Devoluciones o descuentos otorgados sobre las ventas',0,0,0),(11,12,'500.1.1','compra de mercancias','',0,0,0),(12,13,'501.1.1','Desc y dev sobre compras','devoluciones o descuentos otorgados sobre las compras de mercancias',0,0,0),(13,14,'600.1.1','sueldos y salarios','',0,0,0),(14,14,'600.1.2','PAGO SERVICIOS POR TERCEROS','',0,0,0),(15,14,'600.1.3','SERVICIO DE AGUA','',0,0,0),(16,14,'600.1.4','ENERGIA ELECTRICA','',0,0,0),(17,14,'600.1.5','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(18,14,'600.1.6','DESPENSAS Y ALIMENTOS','',0,0,0),(19,14,'600.1.7','ASEO Y LIMPIEZA','',0,0,0),(20,14,'600.1.8','MTTO EQUIPO DE TRANSPORTE','',0,0,0),(21,14,'600.1.9','MTTO EQUIPO DE COMPUTO','',0,0,0),(22,14,'600.1.10','MTTO DEL LOCAL','',0,0,0),(23,14,'600.1.11','OTROS GASTOS','',0,0,0),(24,14,'600.1.12','AJUSTE POR GASTOS NO CONOCIDOS','',0,0,0),(25,14,'600.1.13','TELEFONO E INTERNET','',0,0,0),(26,14,'600.1.14','ENVOLTURAS Y EMPAQUES','',0,0,0),(27,15,'600.2.1','GASOLINA MOTO','',0,0,0),(28,15,'600.2.2','TELEFONIA MOVIL','',0,0,0),(29,15,'600.2.3','PUBLICIDAD','',0,0,0),(30,15,'600.2.4','FLETES','',0,0,0),(31,15,'600.2.5','ENVOLTURAS Y EMPAQUES','',0,0,0),(32,16,'600.3.1','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(33,16,'600.3.2','PAPELERIA Y UTILES','',0,0,0),(34,16,'600.3.3','ENERG├ìA ELECTRICA','',0,0,0),(35,16,'600.3.4','TELEFONIA MOVIL','',0,0,0),(36,16,'600.3.5','TELEFONO E INTERNET','',0,0,0),(37,16,'600.3.6','IMPUESTOS FEDERALES','',0,0,0),(38,16,'600.3.7','RENTA DEL LOCAL','',0,0,0),(39,16,'600.3.8','OTROS GASTOS DE ADMIN','',0,0,0),(40,16,'600.3.9','CUOTAS Y SUSCRIPCIONES','',0,0,0),(41,16,'600.3.10','CUOTAS Y PAGOS IMSS','',0,0,0),(42,18,'700.1.1','PERDIDA CAMBIARIA','',0,0,0),(43,18,'700.1.2','INTERESES A CARGO','',0,0,0),(44,18,'700.1.3','COMISIONES BANCARIAS','',0,0,0),(65,7,'200.1.1','Colomer sa de cv','distribuidora de productos de belleza al por mayor',0,0,0),(66,7,'200.1.2','Revlon SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(67,7,'200.1.3','Bidiip','distribuidora de productos de belleza al por mayor',0,0,0),(68,7,'200.1.4','Anven SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(69,7,'200.1.5','Nefertiti SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(70,7,'200.1.6','Ricardo Rodrigo','distribuidora de productos de belleza al por mayor',0,0,0),(71,7,'200.1.7','Raul Trinidad','distribuidora de productos de belleza al por mayor',0,0,0),(72,7,'200.1.8','Henry Rodriguez','distribuidora de productos de belleza al por mayor',0,0,0),(73,7,'200.1.9','Cosmetica capilar','distribuidora de productos de belleza al por mayor',0,0,0),(74,7,'200.1.10','Cosmetica insurgentes','distribuidora de productos de belleza al por mayor',0,0,0),(75,7,'200.1.11','Belleza cien','distribuidora de productos de belleza al por mayor',0,0,0),(76,7,'200.1.12','Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(77,7,'200.1.13','Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor',0,0,0),(78,7,'200.1.14','Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor',0,0,0),(79,7,'200.1.15','Regio Belleza','distribuidora de productos de belleza al por mayor',0,0,0),(80,7,'200.1.16','Somo Tapatio SA de CV','distribuidora de productos para oficina',0,0,0),(81,7,'200.1.17','Hidra Color SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(82,7,'200.1.18','Color Shot SAS de CV','distribuidora de productos de belleza al por mayor',0,0,0),(83,7,'200.1.19','Color Tech','distribuidora de productos de belleza al por mayor',0,0,0),(84,7,'200.1.20','Nutrapel SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(85,7,'200.1.21','Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(86,7,'200.1.22','Insumos del sureste','distribuidora de consumibles para oficina',0,0,0),(87,7,'200.1.23','Tecnologica San Cristobal SA d','tienda de articulos tecono├│gicos',0,0,0),(88,7,'200.1.24','Micro Chip SA de Cv','tienda de articulos tecono├│gicos',0,0,0),(89,7,'200.1.25','Jairo Eniquez','tienda de articulos tecono├│gicos',0,0,0),(118,7,'200.1.26','INSUMOS COLORIDOS DEL ORIENTE','PROVEEDOR DE PRODUCTOS VARIOS',0,0,0),(119,4,'100.4.1','GENERAL','PUBLICO GENERAL',0,0,0),(120,4,'100.4.2','GENERAL','NA',0,0,0),(121,4,'100.4.26','PR01','ESTE ES UN CLIENTE DE PRUEBA',0,0,0),(122,4,'100.4.27','OTRO','OTRO MAS PARA PROBAR',0,0,0);
/*!40000 ALTER TABLE `sub_cuentas_tercer_nivel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `id_sucursar` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `telefono` varchar(10) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `estado` varchar(60) NOT NULL,
  `ciudad` varchar(60) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `codigo_postal` varchar(5) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'Matris Principal','Tienda primaria','9619888758','sucursal1_empresa@fakemail.com','Chiapas','Tuxtla Gutierrez','4a oriente sur #448 barrio san roque','29000',1),(2,'Sucursal Sab','Segunda Sucursal','9612364800','sucursal2_empresa@fakemail.com','Chiapas','Chiapa de Corzo','Av principal zona centro local7','29000',1),(3,'Sucursal Trend','Tercera Sucursal','9615568093','sucursal3_empresa@fakemail.com','Chiapas','Cintalapa','Av central poniente #725','29000',1),(4,'Sucursal Jaf','Cuarta Sucursal Actualizada','9617921545','sucursal4_empresa@fakemail.com','Chiapas','Tuxtla Gutierrez','5a norte oriente #613','29000',1),(5,'Sucursal Pablo','Quinta Sucursal','9616693648','sucursal5_empresa@fakemail.com','Chiapas','San Cristobal','Calle Miguel Aleman Col la antigua #698','29000',1),(6,'Sucursal Noely','Sexta Sucursal','9616951522','sucursal6_empresa@fakemail.com','Queretaro','Queretaro','Av corregidora col centro #450','29000',1),(8,'Sucursal Moderna','Nueva sucursar la moderna de prueba','9614427278','fakemail_mail@hubmail.com','Chiapas','Tuxtla','Una direcci├│n cualquiera de alg├║n sitio de la ciudad solo para la prueba','29000',1);
/*!40000 ALTER TABLE `sucursal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefono_x_cliente`
--

DROP TABLE IF EXISTS `telefono_x_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono_x_cliente` (
  `id_telefono` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(10) unsigned NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`id_telefono`),
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono_x_empleado` (
  `id_telefono` int(11) NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`id_telefono`),
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `telefono_x_proveedor` (
  `id_telefono` int(11) NOT NULL AUTO_INCREMENT,
  `id_proveedor` int(10) unsigned NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`id_telefono`),
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
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipo_cliente` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cliente`
--

LOCK TABLES `tipo_cliente` WRITE;
/*!40000 ALTER TABLE `tipo_cliente` DISABLE KEYS */;
INSERT INTO `tipo_cliente` VALUES (1,'General','Cliente minorista general'),(2,'Mayorista','Cliente de compras regulares al por mayoor'),(3,'Especial','Cliente epecial');
/*!40000 ALTER TABLE `tipo_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ventas` (
  `id_venta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_sucursal` bigint(20) unsigned NOT NULL,
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
  KEY `ventas_ibfk_3` (`id_sucursal`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados` (`id_empleado`) ON UPDATE CASCADE,
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON UPDATE CASCADE,
  CONSTRAINT `ventas_ibfk_3` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursar`) ON UPDATE CASCADE
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
-- Temporary table structure for view `vw_rfcempleados`
--

DROP TABLE IF EXISTS `vw_rfcempleados`;
/*!50001 DROP VIEW IF EXISTS `vw_rfcempleados`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `vw_rfcempleados` AS SELECT
 1 AS `rfc` */;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'kath_erp'
--
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `actualizarPassWordEmpleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `bucar_forma_pago_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_articulos_por_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_articulos_por_nombre`(
	IN nombre_p VARCHAR(65),
    IN opcion INT,
    IN id_sucursal INT
)
BEGIN
	
    IF opcion = 1 THEN
		SELECT
			articulo.id_articulo,
			articulo.codigo_articulo,
			proveedor.nombre,
			categoria_producto.nombre,
			articulo.codigo_sat,
			articulo.nombre,
			articulo.descripcion,
			existencia_x_sucursal.existencia,
			articulo.precio_general,
			articulo.precio_mayoreo,
            articulo.activo
		FROM existencia_x_sucursal
        INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
		INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
		INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
		WHERE articulo.nombre LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal
		ORDER BY id_articulo;
	END IF;    
    
    
	IF opcion = 2 THEN
		SELECT
			articulo.id_articulo,
			articulo.codigo_articulo,
			proveedor.nombre,
			categoria_producto.nombre,
			articulo.codigo_sat,
			articulo.nombre,
			articulo.descripcion,
			existencia_x_sucursal.existencia,
			articulo.precio_general,
			articulo.precio_mayoreo,
            articulo.activo
		FROM existencia_x_sucursal
        INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
		INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
		INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
		WHERE proveedor.nombre LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal
		ORDER BY id_articulo;
    END IF;
    
    
    IF opcion = 3 THEN
		SELECT
			articulo.id_articulo,
			articulo.codigo_articulo,
			proveedor.nombre,
			categoria_producto.nombre,
			articulo.codigo_sat,
			articulo.nombre,
			articulo.descripcion,
			existencia_x_sucursal.existencia,
			articulo.precio_general,
			articulo.precio_mayoreo,
            articulo.activo
		FROM existencia_x_sucursal
        INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
		INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
		INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
		WHERE categoria_producto.nombre LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal
		ORDER BY id_articulo;
    END IF;
    
    
    IF opcion = 4 THEN
		SELECT
			articulo.id_articulo,
			articulo.codigo_articulo,
			proveedor.nombre,
			categoria_producto.nombre,
			articulo.codigo_sat,
			articulo.nombre,
			articulo.descripcion,
			existencia_x_sucursal.existencia,
			articulo.precio_general,
			articulo.precio_mayoreo,
            articulo.activo
		FROM existencia_x_sucursal
        INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
		INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
		INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
		WHERE articulo.codigo_articulo LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal
		ORDER BY id_articulo;
    END IF;
    
    
    IF opcion = 5 THEN
		SELECT
			articulo.id_articulo,
			articulo.codigo_articulo,
			proveedor.nombre,
			categoria_producto.nombre,
			articulo.codigo_sat,
			articulo.nombre,
			articulo.descripcion,
			existencia_x_sucursal.existencia,
			articulo.precio_general,
			articulo.precio_mayoreo,
            articulo.activo
		FROM existencia_x_sucursal
        INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
		INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
		INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
		WHERE articulo.descripcion LIKE CONCAT('%',nombre_p,'%') AND existencia_x_sucursal.id_sucursal = id_sucursal
		ORDER BY id_articulo;
    END IF;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_articulo_por_codigo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_articulo_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_categoria_por_indice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_categoria_por_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_cliente_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_cliente_por_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_cliente_por_nombre`(
	IN nombre VARCHAR(30)
)
BEGIN
	
    SELECT
		cliente.id_cliente,
		cliente.rfc,
		sub_cuentas_tercer_nivel.clave,
		cliente.nombre_completo,
		cliente.nombre_corto,
		cliente.correo_electronico,
		cliente.estado,
		cliente.ciudad,
		cliente.direccion,
		cliente.codigo_postal,
        cliente.activo        
    FROM cliente
    INNER JOIN sub_cuentas_tercer_nivel ON sub_cuentas_tercer_nivel.id_cuenta = cliente.id_cuenta_contable
    WHERE cliente.nombre_completo LIKE CONCAT('%',nombre,'%');
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_empleado_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_empleado_por_id`(
	IN idEmpleado INT
)
BEGIN

	SELECT * FROM empleados WHERE empleados.id_empleado = idEmpleado;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_empleado_por_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_proveedor_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_proveedor_por_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_sucursal_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_sucursal_por_id`(
	IN id_sucursal INT
)
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
	FROM sucursal
    WHERE sucursal.id_sucursar = id_sucursal;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_ultima_venta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `buscar_ventas_por` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `cmb_tipoCliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_articulo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_categoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_categoria`(
	IN id INT
)
BEGIN
	
    DECLARE estado TINYINT(1);
    SELECT @estado := categoria_producto.activo FROM categori_producto WHERE categoria_producto.id_categoria = id;
    
    IF(@estado = 0)THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La categor├¡a ya se encuentra inactiva';
    END IF;
    
    UPDATE categoria_producto SET
		activo = 0
	WHERE id_categoria = id;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_forma_pago` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_sucursal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_sucursal`(
	IN idSucursal INT
)
BEGIN
	
    ## Se elimina de forma parcial el registro dentro de la bd
    UPDATE sucursal SET
		activo = 0
	WHERE sucursal.id_sucursar = idSucursal;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `eliminar_venta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `eliminar_venta`(
	IN idVenta INT
)
BEGIN
	## Se elimina de forma parcial el registro dentro de la bd
    UPDATE ventas SET
		status_venta = 0
	WHERE ventas.id_venta = idVenta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_forma_de_pago` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nueva_sucursal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nueva_venta` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_articulo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_articulo`(
	IN codigo_a VARCHAR(65),
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
    DECLARE ultimoIndiceArticulo INT;
    DECLARE v_id_sucursal INT;
    DECLARE cur CURSOR FOR SELECT id_sucursal FROM tmp_id_sucursales;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET v_id_sucursal = NULL;
    
    
    SELECT @indiceProveedor := proveedor.id_proveedor FROM proveedor WHERE proveedor.nombre = proveedor_a;
    SELECT @indiceCategoria := categoria_producto.id_categoria FROM categoria_producto WHERE categoria_producto.nombre = marca_a;
    
    IF @indiceProveedor IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No existen registro de proveedor';
	END IF;
    
    IF @indiceCategoria IS NULL THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'No existen registros de categoria';
    END IF;
    
    INSERT INTO articulo(
		codigo_articulo,
        id_proveedor,
        id_categoria,
        codigo_sat,
        nombre,
        descripcion,
        es_exento,
        costo_unitario,
        precio_general,
        precio_mayoreo,
        cantidad_mayoreo,
        activo
    )VALUES(
		codigo_a,
        @indiceProveedor,
        @indiceCategoria,
        codigo_sat_a,
        nombre_a,
        descripcion_a,
        exento_a,
        costo,
        precio_g,
        precio_m,
        cant_m,
        1
    );
	
    SELECT @ultimoIndiceArticulo := articulo.id_articulo FROM articulo ORDER BY id_articulo DESC LIMIT 1;
    
    CREATE TEMPORARY TABLE tmp_id_sucursales (id_sucursal INT);    
    INSERT INTO tmp_id_sucursales(id_sucursal) SELECT id_sucursar FROM sucursal;
    
    OPEN cur;
    read_loop: LOOP
		FETCH cur INTO v_id_sucursal;
        IF v_id_sucursal IS NULL THEN
			LEAVE read_loop;
        END IF;
        
        INSERT INTO existencia_x_sucursal (id_articulo,id_sucursal)
        VALUES (@ultimoIndiceArticulo,v_id_sucursal);
        
    END LOOP read_loop;
    CLOSE cur;
    
    DROP TEMPORARY TABLE tmp_id_sucursales;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_categoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nuevo_tipo_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_nva_categoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `spGetListadoEmpleados` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sp_consultarEmpleadoPorRFC` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_articulo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_categoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_cliente`(
	IN id_cliente_c BIGINT UNSIGNED,
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_empleado` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `update_empleado`(
	IN idSucursal INT,
	IN rfc_e VARCHAR(13),
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
	UPDATE empleados
    SET
		id_sucursal = idSucursal,
		nombre_completo = nombre_completo_e,
        nombre_corto = nombre_corto_e,
        fecha_nac = fecha_nac_e,
        correo_electronico = correo_electronico_e,
        estado = estado_e,
        ciudad = ciudad_e,
        direccion = direccion_e,
        codigo_postal = codigo_postal_e,
        activo = 1
	WHERE rfc = rfc_e;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_forma_de_pago` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `update_sucursal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `validar_entrada` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `validar_entrada`(IN `nombre_c` VARCHAR(10) CHARSET utf8, IN `contra_c` VARCHAR(15) CHARSET utf8)
BEGIN



DECLARE contra VARCHAR(15);



SELECT @contra := empleados.contrasenia AS pswd FROM empleados WHERE empleados.nombre_corto = nombre_c;



IF(@contra != contra_c) THEN

	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Contrase??a incorrecta';

END IF;



END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_articulos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_articulos`(
	IN id_sucursal INT
)
BEGIN
	
    SELECT
		articulo.id_articulo,
		articulo.codigo_articulo,
		proveedor.nombre,
		categoria_producto.nombre,
		articulo.codigo_sat,
		articulo.nombre,
		articulo.descripcion,
		existencia_x_sucursal.existencia,
		articulo.precio_general,
		articulo.precio_mayoreo,
        articulo.activo
	FROM existencia_x_sucursal
    INNER JOIN articulo ON existencia_x_sucursal.id_articulo = articulo.id_articulo
	INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
	INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
    WHERE existencia_x_sucursal.id_sucursal = id_sucursal
	ORDER BY id_articulo;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_clientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_clientes`(
	IN nombre_c VARCHAR(30)
)
BEGIN
	
    SELECT
		cliente.id_cliente,
		cliente.rfc,
        tipo_cliente.nombre,
		sub_cuentas_tercer_nivel.clave,
		cliente.nombre_completo,
		cliente.nombre_corto,
		cliente.correo_electronico,
		cliente.estado,
		cliente.ciudad,
		cliente.direccion,
		cliente.codigo_postal,
        cliente.activo
	FROM cliente    
	INNER JOIN sub_cuentas_tercer_nivel ON sub_cuentas_tercer_nivel.id_cuenta = cliente.id_cuenta_contable
    INNER JOIN tipo_cliente ON tipo_cliente.id = cliente.id_tipoCliente
    WHERE cliente.nombre_completo LIKE CONCAT('%',nombre_c,'%');
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_cliente_por_rfc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_codigos_articulos` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_existencias_articulo_sucursal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_formas_de_pago` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_indices_categorias` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_indice_venta_actual` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_marcas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_marcas`()
BEGIN

	

	SELECT * FROM categoria_producto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_nombres_categorias` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_nombres_categorias`()
BEGIN

	SELECT categoria_producto.nombre FROM categoria_producto;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_nombres_proveedor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_nombres_proveedor`()
BEGIN
	
    SELECT
		nombre
    FROM proveedor;
    
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_nombres_sucursal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_proveedores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_proveedores`()
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
	INNER JOIN sub_cuentas_tercer_nivel ON proveedor.id_cuenta_contable = sub_cuentas_tercer_nivel.id_cuenta;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_proveedor_por_rfc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_rfcProveedores` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_rfc_clientes` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_rfc_empleado_por_sucursal` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_rfc_empleado_por_sucursal`(
	IN id_sucursal INT
)
BEGIN	
    SELECT
		empleados.nombre_corto
	FROM empleados
    WHERE empleados.id_sucursal = id_sucursal;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_sucursales` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
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
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_sucursales_nombres` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_sucursales_nombres`()
BEGIN
	SELECT 
		sucursal.id_sucursar,
        sucursal.nombre
	FROM sucursal ORDER BY id_sucursar;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_sucursal_por_id` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_sucursal_por_id`(
	IN id_sucursal INT
)
BEGIN
	
    SELECT * FROM sucursal WHERE id_sucursar = id_sucursal;

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `ver_ventas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_ventas`(
	IN opcion INT,
    IN sucursal INT
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

--
-- Final view structure for view `vw_rfcempleados`
--

/*!50001 DROP VIEW IF EXISTS `vw_rfcempleados`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vw_rfcempleados` AS select `empleados`.`rfc` AS `rfc` from `empleados` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-30 15:46:09
