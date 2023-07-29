-- MariaDB dump 10.19  Distrib 10.4.27-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: kath_erp
-- ------------------------------------------------------
-- Server version	10.4.27-MariaDB

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
  `codigo_articulo` varchar(65) DEFAULT NULL,
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
  `cantidad_mayoreo` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_articulo`),
  UNIQUE KEY `codigo_articulo` (`codigo_articulo`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_producto` (`id_categoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=69 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'19906207',5,15,'53131600','producto de prueba 1','este es un producto de prueba numero 1',67,0,229,458,398.46,13),(2,'57752462',1,13,'53131600','producto de prueba 2','este es un producto de prueba numero 2',13,1,1185,2370,2061.9,10),(3,'53021112',19,6,'53131600','producto de prueba 3','este es un producto de prueba numero 3',8,1,265,530,461.1,12),(4,'42927456',12,10,'53131600','producto de prueba 4','este es un producto de prueba numero 4',31,0,1410,2820,2453.4,14),(5,'35462281',12,9,'53131600','producto de prueba 5','este es un producto de prueba numero 5',1,0,1216,2432,2115.84,15),(6,'90104382',10,6,'53131600','producto de prueba 6','este es un producto de prueba numero 6',7,1,141,282,245.34,11),(7,'61164243',8,12,'53131600','producto de prueba 7','este es un producto de prueba numero 7',59,0,654,1308,1137.96,11),(8,'61661304',25,7,'53131600','producto de prueba 8','este es un producto de prueba numero 8',21,1,103,206,179.22,15),(9,'37702491',8,9,'53131600','producto de prueba 9','este es un producto de prueba numero 9',29,1,998,1996,1736.52,15),(10,'65623841',24,14,'53131600','producto de prueba 10','este es un producto de prueba numero 10',22,1,259,518,450.66,14),(11,'49542596',5,12,'53131600','producto de prueba 11','este es un producto de prueba numero 11',48,0,466,932,810.84,13),(12,'34328847',13,5,'53131600','producto de prueba 12','este es un producto de prueba numero 12',54,0,530,1060,922.2,12),(13,'97657935',3,6,'53131600','producto de prueba 13','este es un producto de prueba numero 13',8,0,506,1012,880.44,14),(14,'16597842',12,13,'53131600','producto de prueba 14','este es un producto de prueba numero 14',47,1,1392,2784,2422.08,10),(15,'60709685',14,6,'53131600','producto de prueba 15','este es un producto de prueba numero 15',7,1,145,290,252.3,15),(16,'97928484',11,15,'53131600','producto de prueba 16','este es un producto de prueba numero 16',43,0,1193,2386,2075.82,13),(17,'52535124',24,12,'53131600','producto de prueba 17','este es un producto de prueba numero 17',18,0,1275,2550,2218.5,14),(18,'71358309',4,10,'53131600','producto de prueba 18','este es un producto de prueba numero 18',6,0,1109,2218,1929.66,15),(19,'50432806',12,7,'53131600','producto de prueba 19','este es un producto de prueba numero 19',18,0,960,1920,1670.4,14),(20,'20944362',20,4,'53131600','producto de prueba 20','este es un producto de prueba numero 20',40,0,1418,2836,2467.32,12),(21,'90753685',17,15,'53131600','producto de prueba 21','este es un producto de prueba numero 21',2,1,1385,2770,2409.9,15),(22,'29007218',14,11,'53131600','producto de prueba 22','este es un producto de prueba numero 22',8,0,276,552,480.24,11),(23,'23172614',4,15,'53131600','producto de prueba 23','este es un producto de prueba numero 23',6,1,1120,2240,1948.8,14),(24,'31033381',22,12,'53131600','producto de prueba 24','este es un producto de prueba numero 24',17,1,401,802,697.74,10),(25,'60088981',3,6,'53131600','producto de prueba 25','este es un producto de prueba numero 25',20,0,1030,2060,1792.2,10),(26,'53244961',14,8,'53131600','producto de prueba 26','este es un producto de prueba numero 26',69,0,347,694,603.78,10),(27,'43826565',15,9,'53131600','producto de prueba 27','este es un producto de prueba numero 27',25,1,437,874,760.38,12),(28,'43653309',22,15,'53131600','producto de prueba 28','este es un producto de prueba numero 28',9,1,215,430,374.1,14),(29,'55364258',21,11,'53131600','producto de prueba 29','este es un producto de prueba numero 29',15,0,399,798,694.26,11),(30,'30758126',6,9,'53131600','producto de prueba 30','este es un producto de prueba numero 30',41,1,416,832,723.84,10),(31,'62885631',22,11,'53131600','producto de prueba 31','este es un producto de prueba numero 31',29,1,590,1180,1026.6,12),(32,'30957315',6,9,'53131600','producto de prueba 32','este es un producto de prueba numero 32',17,1,142,284,247.08,10),(33,'21334808',4,2,'53131600','producto de prueba 33','este es un producto de prueba numero 33',32,1,1372,2744,2387.28,15),(34,'87096923',13,11,'53131600','producto de prueba 34','este es un producto de prueba numero 34',50,0,1260,2520,2192.4,15),(35,'38882503',12,8,'53131600','producto de prueba 35','este es un producto de prueba numero 35',34,0,383,766,666.42,11),(36,'71504305',17,7,'53131600','producto de prueba 36','este es un producto de prueba numero 36',24,1,1393,2786,2423.82,13),(37,'37647220',14,8,'53131600','producto de prueba 37','este es un producto de prueba numero 37',58,1,1175,2350,2044.5,15),(38,'67073851',3,14,'53131600','producto de prueba 38','este es un producto de prueba numero 38',2,0,333,666,579.42,12),(39,'23689002',10,15,'53131600','producto de prueba 39','este es un producto de prueba numero 39',73,0,413,826,718.62,10),(40,'29378600',4,13,'53131600','producto de prueba 40','este es un producto de prueba numero 40',66,1,699,1398,1216.26,15),(41,'77781189',4,7,'53131600','producto de prueba 41','este es un producto de prueba numero 41',51,1,878,1756,1527.72,13),(42,'77622403',9,9,'53131600','producto de prueba 42','este es un producto de prueba numero 42',57,1,911,1822,1585.14,14),(43,'58065688',25,8,'53131600','producto de prueba 43','este es un producto de prueba numero 43',31,1,378,756,657.72,14),(44,'26423885',21,1,'53131600','producto de prueba 44','este es un producto de prueba numero 44',2,1,1177,2354,2047.98,15),(45,'29551336',22,2,'53131600','producto de prueba 45','este es un producto de prueba numero 45',1,0,1312,2624,2282.88,14),(46,'65535490',14,11,'53131600','producto de prueba 46','este es un producto de prueba numero 46',36,0,797,1594,1386.78,12),(47,'95277398',7,11,'53131600','producto de prueba 47','este es un producto de prueba numero 47',61,0,360,720,626.4,10),(48,'86502795',10,4,'53131600','producto de prueba 48','este es un producto de prueba numero 48',52,0,231,462,401.94,11),(49,'87594146',12,4,'53131600','producto de prueba 49','este es un producto de prueba numero 49',50,1,959,1918,1668.66,14),(50,'68695651',8,12,'53131600','producto de prueba 50','este es un producto de prueba numero 50',57,0,826,1652,1437.24,10),(51,'20806199',23,7,'53131600','producto de prueba 51','este es un producto de prueba numero 51',13,1,1191,2382,2072.34,12),(52,'98466078',10,13,'53131600','producto de prueba 52','este es un producto de prueba numero 52',11,0,395,790,687.3,13),(53,'32265945',11,1,'53131600','producto de prueba 53','este es un producto de prueba numero 53',33,0,1372,2744,2387.28,15),(54,'58937856',14,7,'53131600','producto de prueba 54','este es un producto de prueba numero 54',30,0,659,1318,1146.66,12),(55,'78051874',15,6,'53131600','producto de prueba 55','este es un producto de prueba numero 55',29,0,1022,2044,1778.28,12),(56,'17254952',7,14,'53131600','producto de prueba 56','este es un producto de prueba numero 56',6,0,1435,2870,2496.9,14),(57,'32604230',4,12,'53131600','producto de prueba 57','este es un producto de prueba numero 57',45,0,1263,2526,2197.62,15),(58,'16763093',22,9,'53131600','producto de prueba 58','este es un producto de prueba numero 58',14,0,1262,2524,2195.88,10),(59,'28074341',11,9,'53131600','producto de prueba 59','este es un producto de prueba numero 59',31,0,484,968,842.16,12),(60,'55761815',2,5,'53131600','producto de prueba 60','este es un producto de prueba numero 60',16,0,287,574,499.38,13),(61,'41539522',10,5,'53131600','producto de prueba 61','este es un producto de prueba numero 61',58,1,1425,2850,2479.5,14),(62,'90369065',17,4,'53131600','producto de prueba 62','este es un producto de prueba numero 62',61,1,553,1106,962.22,11),(63,'79912183',25,9,'53131600','producto de prueba 63','este es un producto de prueba numero 63',40,0,223,446,388.02,12),(64,'33874081',3,15,'53131600','producto de prueba 64','este es un producto de prueba numero 64',30,0,1233,2466,2145.42,12),(65,'92196651',6,5,'53131600','producto de prueba 65','este es un producto de prueba numero 65',71,1,359,718,624.66,11),(66,'68156248',3,12,'53131600','producto de prueba 66','este es un producto de prueba numero 66',60,0,536,1072,932.64,14),(67,'24639012',11,7,'53131600','producto de prueba 67','este es un producto de prueba numero 67',69,1,697,1394,1212.78,15),(68,'79045801',17,14,'53131600','producto de prueba 68','este es un producto de prueba numero 68',44,1,1033,2066,1797.42,12);
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
) ENGINE=InnoDB AUTO_INCREMENT=927 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
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
  PRIMARY KEY (`id_categoria`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_producto`
--

LOCK TABLES `categoria_producto` WRITE;
/*!40000 ALTER TABLE `categoria_producto` DISABLE KEYS */;
INSERT INTO `categoria_producto` VALUES (1,'atomizadores ','atomizadores'),(2,'tintes','tintes'),(3,'cremas','cremas'),(4,'aceites capilares','aceites capilares'),(5,'aceites corporales','aceites corporales'),(6,'shampoo','shampoo'),(7,'acondicionador','acondicionador'),(8,'herramientas','herramientas'),(9,'bisturis','bisturis'),(10,'prueba','prueba'),(11,'de prueba','de prueba'),(12,'otra puta prueba','otra puta prueba'),(13,'una prueba mas','una prueba mas'),(14,'pruebita','pruebita'),(15,'pruebitita','pruebitita'),(17,'Categor??a 2','Actualizando esta categor??a'),(18,'Una categor??a mas','esta es otra categor??a'),(19,'Nueva categoria de prueba','Actualizando esta categor??a, primer intento'),(20,'Categor??a Nueva de Prueba','Esta es otra categor??a mas de prueba para probar la prueba que se prueba en esta\nprueba de prueba pruebita esto solo es texto de relleno solo para acomodar mas texto\nen el texto de relleno XDDD jaaj que loco'),(21,'Otra categor??a','Estoy actualizando esta categor??a que antes no estaba actualizada pero ahora ya lo est?? y por eso le estoy agregando esta descripci??n chida XDDD');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'ARASAS',45,'MOSTRADOR','adsfa','1997-07-05','correo_pr_1@hotmail.com','Chiapas','Tuxtla Gutierrez','AV.2 NORTE ORIENTE S/N-225','29072'),(2,'TRRTJR',46,'LOURDES LOPEZ GUILLEN','wrqer','1988-04-27','correo_pr_2@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. TACANA MANZANA 29 #37','29031'),(3,'SADVXCB',47,'MARIA ERNESTINA AGUSTIN PEREZ','asa','1981-03-03','correo_pr_3@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. 5A SUR ORIENTE 654-B','29091'),(4,'GHLHJ',48,'MARIA CONCEPCION CHULIN GORDIL','qewr','1996-05-24','correo_pr_4@hotmail.com','Chiapas','Tuxtla Gutierrez','CALLE 5A OTE. NORTE','29054'),(5,'QEWRF',49,'LUCIA ELIZABETH LOPEZ GONZALEZ','adfs','1996-07-24','correo_pr_5@hotmail.com','Chiapas','Tuxtla Gutierrez','9 NORTE ENTRE 6 Y 7 ORIENTE','29057'),(6,'QQSCCAA',50,'ANA YANSI CRUZ GARCIA','qwer','1988-04-02','correo_pr_6@hotmail.com','Chiapas','Tuxtla Gutierrez','5A ORIENTE NORTE ENTRE 1A Y 2A NORTE','29090'),(7,'UI??HJKL',51,'MARIA ENCARNACION SARMIENTO SA','zcxv','1984-06-13','correo_pr_7@hotmail.com','Chiapas','Tuxtla Gutierrez','2 ORIENTE SUR #217 ENTRE 1 Y 2 SUR','29047'),(8,'ACSDFVSC',52,'MARIA BEININA GOMEZ ALVAREZ','qer','1988-01-05','correo_pr_8@hotmail.com','Chiapas','Tuxtla Gutierrez','4 OTE SUR ENTRE 4 Y 5 SUR #538','29029'),(9,'SDFVXCAAS',53,'LUZ MARIA MORALES TORRES','asdf','1994-07-28','correo_pr_9@hotmail.com','Chiapas','Tuxtla Gutierrez','AV OLIVO SUR 112-A','29080'),(10,'AXCXCV',54,'VERONICA LOPEZ CRUZ','wetwtr','1995-06-24','correo_pr_10@hotmail.com','Chiapas','Tuxtla Gutierrez','7A ORIENTE #451 ENTRE 3A Y 4A SUR','29061'),(11,'ASERASD',55,'GUADALUPE NURIULU GORDILLO','ury','1980-04-29','correo_pr_11@hotmail.com','Chiapas','Tuxtla Gutierrez','MNZ.45 LOT.12','29022'),(12,'ADVSASD',56,'WALTER DILTIEV SOTO MORALES','kju','1999-03-15','correo_pr_12@hotmail.com','Chiapas','Tuxtla Gutierrez','AV.2 NORTE ORIENTE S/N-225','29044'),(13,'ASFWASFE',57,'MUNICIPIO DE VENUSTIANO CARRAN','sdfs','1984-06-01','correo_pr_13@hotmail.com','Chiapas','Carranza','CONOCIDO PALACIO MUNICIAPAL','29052'),(14,'WRTWEFAS',58,'BEATRIZ ADRIANA PENAGOS GONZAL','rtyutr','1982-05-28','correo_pr_14@hotmail.com','Chiapas','San Cristobal','3A CALLE ORIENTE SUR #350-A','29056'),(15,'WEFADE',59,'YARENI HERNANDEZ GARCIA','zcxv','1982-10-01','correo_pr_15@hotmail.com','Chiapas','Chilon','AV. TACANA MANZANA 29 #37','29025'),(16,'DVSSSVS',60,'METALLICA','wer','1988-08-21','correo_pr_16@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. 5A SUR ORIENTE 654-B','29009'),(17,'AWETWWD',61,'FABIOLA INDILI CUNDAPI','zxcv','1988-05-05','correo_pr_17@hotmail.com','Chiapas','Tuxtla Gutierrez','CALLE 5A OTE. NORTE','29039'),(18,'WEEFFEW',62,'ELIZA AGUILAR','ewt','1990-05-18','correo_pr_18@hotmail.com','Chiapas','Tuxtla Gutierrez','9 NORTE ENTRE 6 Y 7 ORIENTE','29047'),(19,'ADSVSAC',63,'PAOLA LIZETH GALLEGOS DEL CARP','xzcv','1986-10-27','correo_pr_19@hotmail.com','Chiapas','Tuxtla Gutierrez','5A ORIENTE NORTE ENTRE 1A Y 2A NORTE','29016'),(20,'ASFSASD',64,'DANIA MARGARITA GOMEZ HERNANDE','ewt','1983-05-29','correo_pr_20@hotmail.com','Chiapas','Tuxtla Gutierrez','2 ORIENTE SUR #217 ENTRE 1 Y 2 SUR','29082');
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
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,'MEYM670405XQ6','KAME670405HMNXNX02','Melanie Yepez Moreno','myepezmore','1967-04-05','melanie.yepez@gmail.com','Guerrero','Chilpancingo','Calle Rosales 32','39070','null123456789'),(2,'ZIJP780423B5U','TAHL780423HMNNQQA0','Pablo Zizumbo Jimenez','pzizumboji','1978-04-23','pablo.zizumbo@hotmail.com','Baja California','Ensenada','Calle Las Lomas 45','22790','nullatol1234'),(3,'ZYMF550712N7A','FUSM550712HMCNVG03','Fernanda Zuniga Martinez','fzunigamar','1955-07-12','fernanda.zuniga@yahoo.com','Michoacan','Morelia','Calle 20 de Noviembre 23','58000','zxczxc123'),(4,'SIJB820611FL4','TAVS820611MCMDBH07','Beatriz Sifuentes Juarez','bsifuentes','1982-06-11','beatriz.sifuentes@gmail.com','Chihuahua','Chihuahua','Avenida 5 de Mayo 67','31000','hambre'),(5,'MELC9403054C4','XUVM940305MMNLYB09','Mariana Melendez Lopez','mmelendezl','1994-03-05','mariana.melendez@hotmail.com','Veracruz','Veracruz','Calle Huatulco 89','91700','soutern'),(6,'VOTN720904LD6','TOJV720904HDFVRA03','Nicolas Vargas Ortiz','nvargasort','1972-09-04','nicolas.vargas@gmail.com','Tamaulipas','Matamoros','Calle 2 de Abril 123','87300','asdfgh123'),(7,'XOYD7205018G7','NOIX720501HMCNGA09','Daniel Xavier Xochitiotzin','dxavierxo','1972-05-01','daniel.xavier@yahoo.com','Morelos','Cuernavaca','Calle Las Flores 12','62250','zxcvbnm123'),(8,'NUVJ650313LY5','FELA650313HMCVLR01','Jaime Nuno Vargas','jnunovarga','1965-03-13','jaime.nuno@hotmail.com','Jalisco','Guadalajara','Avenida Vallarta 456','44130','poiuyt123'),(9,'QUZC5903033E2','GAZJ590303HMNLRG02','Cristina Quintero Zuniga','cquinteroz','1959-03-03','cristina.quintero@gmail.com','Coahuila','Saltillo','Calle Cipres 7','25230','987654321'),(10,'CUMJ540707V7S','TEWJ540707HMNTJL06','Juan Cueva Medina','jcuevamedi','1954-07-07','juancueva.medina24@mail.com','Chiapas','Tuxtla Chico','Calle 12 de Octubre 34','29345','1234'),(15,'MELC9403054C5','XUVM943305MMNLYB09','UN EMPLEADO DE PRUEBA','XUVM','1997-08-05','unemail@mail.com','Chiapas','Tuxtla','por ah??','29000','null123456'),(23,'VOTN720904LJ4','VOTN720904LJ4XXA','VICTOR JOSE','VOTN','1997-12-12','votn_@hotmail.com','Chiapas','Tuxtla','4a oriente','20000','null123456'),(25,'MELC9405094C4','MELC9405094C4CY5','CARLOS LEON MADERO ENRIQUES','MELC','1997-05-25','carlos_mail@gmail.com','CHIAPAS','TUXTLA GUTIERREZ','4A ORIENTE SUR #448','29000','null789456'),(26,'CUMJ540707V8K','CUMJ540707V8KRMA','JOSE MANUEL','CUMJ','1998-01-02','cumjfakemail@fakemail.com','Chiapas','Tuxtla Gutierrez','4a oriente sur #448','29000','1234');
/*!40000 ALTER TABLE `empleados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `empleados_nombre_costo`
--

DROP TABLE IF EXISTS `empleados_nombre_costo`;
/*!50001 DROP VIEW IF EXISTS `empleados_nombre_costo`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `empleados_nombre_costo` AS SELECT
 1 AS `nombre_corto` */;
SET character_set_client = @saved_cs_client;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `formas_de_pago`
--

LOCK TABLES `formas_de_pago` WRITE;
/*!40000 ALTER TABLE `formas_de_pago` DISABLE KEYS */;
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
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `proveedor_ibfk_1` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'REFCPR01',65,'Actual Colomer sa de cv','distribuidora de productos de belleza al por mayor','correo_proveedor1@hotmail.com','Edo MX','Cd Mx','Av insurgentes','26087'),(2,'REFCPR02',66,'Revlon SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor2@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25791'),(3,'REFCPR03',67,'Bidiip','distribuidora de productos de belleza al por mayor','correo_proveedor3@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28447'),(4,'REFCPR04',68,'Anven SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor4@hotmail.com','Edo MX','Cd Mx','Av insurgentes','27511'),(5,'REFCPR05',69,'Nefertiti SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor5@hotmail.com','Edo MX','Cd Mx','Av insurgentes','26712'),(6,'REFCPR06',70,'Ricardo Rodrigo','distribuidora de productos de belleza al por mayor','correo_proveedor6@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25761'),(7,'REFCPR07',71,'Raul Trinidad','distribuidora de productos de belleza al por mayor','correo_proveedor7@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28733'),(8,'REFCPR08',72,'Henry Rodriguez','distribuidora de productos de belleza al por mayor','correo_proveedor8@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28906'),(9,'REFCPR09',73,'Cosmetica capilar','distribuidora de productos de belleza al por mayor','correo_proveedor9@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25348'),(10,'REFCPR10',74,'Cosmetica insurgentes','distribuidora de productos de belleza al por mayor','correo_proveedor10@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25862'),(11,'REFCPR11',75,'Belleza cien','distribuidora de productos de belleza al por mayor','correo_proveedor11@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27249'),(12,'REFCPR12',76,'Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor12@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27748'),(13,'REFCPR13',77,'Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor','correo_proveedor13@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27219'),(14,'REFCPR14',78,'Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor','correo_proveedor14@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27151'),(15,'REFCPR15',79,'Regio Belleza','distribuidora de productos de belleza al por mayor','correo_proveedor15@hotmail.com','Nuevo Leon','Monterrey','Av 13 sur','28947'),(16,'REFCPR16',80,'Somo Tapatio SA de CV','distribuidora de productos para oficina','correo_proveedor16@hotmail.com','Guadalajara','Jalisco','Av 13 sur','28960'),(17,'REFCPR17',81,'Actualizado Hidra Color','Se actualiza nuevamente este mismo proveedor','correo_proveedor17@hotmail.com','Edo MX','Cd Mx','Nueva direccion actualizada','25542'),(18,'REFCPR18',82,'Color Shot SAS de CV','distribuidora de productos de belleza al por mayor','correo_proveedor18@hotmail.com','Edo MX','Cd Mx','Av 13 sur','28472'),(19,'REFCPR19',83,'Color Tech','distribuidora de productos de belleza al por mayor','correo_proveedor19@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25526'),(20,'REFCPR20',84,'Nutrapel SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor20@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25386'),(21,'REFCPR21',85,'Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor','correo_proveedor21@hotmail.com','Edo MX','Cd Mx','Av 13 sur','26553'),(22,'REFCPR22',86,'Insumos del sureste','distribuidora de consumibles para oficina','correo_proveedor22@hotmail.com','Yucatan','Merida','Av 13 sur','26285'),(23,'REFCPR23',87,'Tecnologica San Cristobal SA d','tienda de articulos tecono??gicos','correo_proveedor23@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','26080'),(24,'REFCPR24',88,'Micro Chip SA de Cv','tienda de articulos tecono??gicos','correo_proveedor24@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','28583'),(25,'REFCPR25',89,'Jairo Eniquez','tienda de articulos tecono??gicos','correo_proveedor25@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','28233'),(26,'XAXXAXXAXAAXA',90,'PROV DE PRUEBA','ESTE ES UN PROVEEDOR DE PRUEBA BIEN PROBADO INTENTANDO PROBAR LA PRUEBA PROBADA','testmail1@mail.com','ESTADO','CIUDAD','CERCA DE TU CORAZON','29000'),(28,'XAXXAXXXXA',102,'UN PROV DE PRUEBA','UNA PRUEBA PA PROBAR LA PRUEBA','mail@mail.com','estado','ciudad','por ahi','29000'),(29,'XAXXAXXXXR',103,'UN PROV DE PRUEBA 02','UNA PRUEBA PA PROBAR LA PRUEBA','mail@mail.com','estado','ciudad','por ahi','29000'),(30,'REFCPR28',104,'PROVEEDOR DE PRUEBA 3','UNA PRUEBA MAS PARA PROBAR LA PRUEBA BIEN PROBADA XDD','mail@fakemail.com','CHIAPAS','CINTALAPA','cerquita de ahi bin cerca por ahi llegando a al esquina jajajaja','29000'),(32,'REFCPR29',106,'PROV PRUEBA 4','ESTE ES OTRO PROVEEDOR DE PRUEBA','mail_m@mail.com','ABURRIDO','ABURRILANDIA','ESTA ES UNA DIRECCION DE PRUEBA','29000'),(35,'REFC29',110,'PRUEBA','','pruebamail@mail.com','WASHINGTON','SEATTLE','355 CAPITOL HILL','30000');
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
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_cuentas_tercer_nivel`
--

LOCK TABLES `sub_cuentas_tercer_nivel` WRITE;
/*!40000 ALTER TABLE `sub_cuentas_tercer_nivel` DISABLE KEYS */;
INSERT INTO `sub_cuentas_tercer_nivel` VALUES (1,1,'100.1.1','Efectivo disponible','',0,0,0),(2,1,'100.1.2','Fondo de ahorro','efectivo ahorrado',0,0,0),(3,1,'100.1.3','Efectivo en circulacion','efectivo usado para cambios',0,0,0),(4,2,'100.2.1','Bancomer','cuenta bancomer',0,0,0),(5,5,'100.5.1','iva acreditable pagado','iava acreditable efectivamente pagado',0,0,0),(6,6,'100.6.1','iva acreditable por pagar','iva acreditable pendiente de pago',0,0,0),(7,8,'200.2.1','iva trasladado','iva cobrado',0,0,0),(8,9,'200.3.1','iva por trasladar','iva por cobrar',0,0,0),(9,10,'400.1.1','venta de mercanc??as','ingresos obtenidos por la venta de mercancias',0,0,0),(10,11,'401.1.1','Desc y Dev sobre ventas','Devoluciones o descuentos otorgados sobre las ventas',0,0,0),(11,12,'500.1.1','compra de mercancias','',0,0,0),(12,13,'501.1.1','Desc y dev sobre compras','devoluciones o descuentos otorgados sobre las compras de mercancias',0,0,0),(13,14,'600.1.1','sueldos y salarios','',0,0,0),(14,14,'600.1.2','PAGO SERVICIOS POR TERCEROS','',0,0,0),(15,14,'600.1.3','SERVICIO DE AGUA','',0,0,0),(16,14,'600.1.4','ENERGIA ELECTRICA','',0,0,0),(17,14,'600.1.5','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(18,14,'600.1.6','DESPENSAS Y ALIMENTOS','',0,0,0),(19,14,'600.1.7','ASEO Y LIMPIEZA','',0,0,0),(20,14,'600.1.8','MTTO EQUIPO DE TRANSPORTE','',0,0,0),(21,14,'600.1.9','MTTO EQUIPO DE COMPUTO','',0,0,0),(22,14,'600.1.10','MTTO DEL LOCAL','',0,0,0),(23,14,'600.1.11','OTROS GASTOS','',0,0,0),(24,14,'600.1.12','AJUSTE POR GASTOS NO CONOCIDOS','',0,0,0),(25,14,'600.1.13','TELEFONO E INTERNET','',0,0,0),(26,14,'600.1.14','ENVOLTURAS Y EMPAQUES','',0,0,0),(27,15,'600.2.1','GASOLINA MOTO','',0,0,0),(28,15,'600.2.2','TELEFONIA MOVIL','',0,0,0),(29,15,'600.2.3','PUBLICIDAD','',0,0,0),(30,15,'600.2.4','FLETES','',0,0,0),(31,15,'600.2.5','ENVOLTURAS Y EMPAQUES','',0,0,0),(32,16,'600.3.1','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(33,16,'600.3.2','PAPELERIA Y UTILES','',0,0,0),(34,16,'600.3.3','ENERG??A ELECTRICA','',0,0,0),(35,16,'600.3.4','TELEFONIA MOVIL','',0,0,0),(36,16,'600.3.5','TELEFONO E INTERNET','',0,0,0),(37,16,'600.3.6','IMPUESTOS FEDERALES','',0,0,0),(38,16,'600.3.7','RENTA DEL LOCAL','',0,0,0),(39,16,'600.3.8','OTROS GASTOS DE ADMIN','',0,0,0),(40,16,'600.3.9','CUOTAS Y SUSCRIPCIONES','',0,0,0),(41,16,'600.3.10','CUOTAS Y PAGOS IMSS','',0,0,0),(42,18,'700.1.1','PERDIDA CAMBIARIA','',0,0,0),(43,18,'700.1.2','INTERESES A CARGO','',0,0,0),(44,18,'700.1.3','COMISIONES BANCARIAS','',0,0,0),(45,4,'100.4.1','MOSTRADOR','',0,0,0),(46,4,'100.4.2','LOURDES LOPEZ GUILLEN','',0,0,0),(47,4,'100.4.3','MARIA ERNESTINA AGUSTIN PEREZ','',0,0,0),(48,4,'100.4.4','MARIA CONCEPCION CHULIN GORDIL','',0,0,0),(49,4,'100.4.5','LUCIA ELIZABETH LOPEZ GONZALEZ','',0,0,0),(50,4,'100.4.6','ANA YANSI CRUZ GARCIA','',0,0,0),(51,4,'100.4.7','MARIA ENCARNACION SARMIENTO SA','',0,0,0),(52,4,'100.4.8','MARIA BEININA GOMEZ ALVAREZ','',0,0,0),(53,4,'100.4.9','LUZ MARIA MORALES TORRES','',0,0,0),(54,4,'100.4.10','VERONICA LOPEZ CRUZ','',0,0,0),(55,4,'100.4.11','GUADALUPE NURIULU GORDILLO','',0,0,0),(56,4,'100.4.12','WALTER DILTIEV SOTO MORALES','',0,0,0),(57,4,'100.4.13','\"MUNICIPIO DE VENUSTIANO CARRA',' CHIAPAS\"',0,0,0),(58,4,'100.4.14','BEATRIZ ADRIANA PENAGOS GONZAL','',0,0,0),(59,4,'100.4.15','YARENI HERNANDEZ GARCIA','',0,0,0),(60,4,'100.4.16','METALLICA','',0,0,0),(61,4,'100.4.17','FABIOLA INDILI CUNDAPI','',0,0,0),(62,4,'100.4.18','ELIZA AGUILAR','',0,0,0),(63,4,'100.4.19','PAOLA LIZETH GALLEGOS DEL CARP','',0,0,0),(64,4,'100.4.20','DANIA MARGARITA GOMEZ HERNANDE','',0,0,0),(65,7,'200.1.1','Colomer sa de cv','distribuidora de productos de belleza al por mayor',0,0,0),(66,7,'200.1.2','Revlon SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(67,7,'200.1.3','Bidiip','distribuidora de productos de belleza al por mayor',0,0,0),(68,7,'200.1.4','Anven SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(69,7,'200.1.5','Nefertiti SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(70,7,'200.1.6','Ricardo Rodrigo','distribuidora de productos de belleza al por mayor',0,0,0),(71,7,'200.1.7','Raul Trinidad','distribuidora de productos de belleza al por mayor',0,0,0),(72,7,'200.1.8','Henry Rodriguez','distribuidora de productos de belleza al por mayor',0,0,0),(73,7,'200.1.9','Cosmetica capilar','distribuidora de productos de belleza al por mayor',0,0,0),(74,7,'200.1.10','Cosmetica insurgentes','distribuidora de productos de belleza al por mayor',0,0,0),(75,7,'200.1.11','Belleza cien','distribuidora de productos de belleza al por mayor',0,0,0),(76,7,'200.1.12','Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(77,7,'200.1.13','Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor',0,0,0),(78,7,'200.1.14','Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor',0,0,0),(79,7,'200.1.15','Regio Belleza','distribuidora de productos de belleza al por mayor',0,0,0),(80,7,'200.1.16','Somo Tapatio SA de CV','distribuidora de productos para oficina',0,0,0),(81,7,'200.1.17','Hidra Color SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(82,7,'200.1.18','Color Shot SAS de CV','distribuidora de productos de belleza al por mayor',0,0,0),(83,7,'200.1.19','Color Tech','distribuidora de productos de belleza al por mayor',0,0,0),(84,7,'200.1.20','Nutrapel SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(85,7,'200.1.21','Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(86,7,'200.1.22','Insumos del sureste','distribuidora de consumibles para oficina',0,0,0),(87,7,'200.1.23','Tecnologica San Cristobal SA d','tienda de articulos tecono??gicos',0,0,0),(88,7,'200.1.24','Micro Chip SA de Cv','tienda de articulos tecono??gicos',0,0,0),(89,7,'200.1.25','Jairo Eniquez','tienda de articulos tecono??gicos',0,0,0),(90,7,'200.1.26','PROV DE PRUEBA','ESTE ES UN PROVEEDOR DE PRUEBA BIEN PROBADO INTENTANDO PROBAR LA PRUEBA PROBADA',0,0,0),(102,7,'200.1.27','UN PROV DE PRUEBA','UNA PRUEBA PA PROBAR LA PRUEBA',0,0,0),(103,7,'200.1.29','UN PROV DE PRUEBA 02','UNA PRUEBA PA PROBAR LA PRUEBA',0,0,0),(104,7,'200.1.30','PROVEEDOR DE PRUEBA 3','UNA PRUEBA MAS PARA PROBAR LA PRUEBA BIEN PROBADA XDD',0,0,0),(106,7,'200.1.31','PROV PRUEBA 4','ESTE ES OTRO PROVEEDOR DE PRUEBA',0,0,0),(110,7,'200.1.33','PRUEBA','',0,0,0);
/*!40000 ALTER TABLE `sub_cuentas_tercer_nivel` ENABLE KEYS */;
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
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=231 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,'2023-01-03',1,1,7,41710.5,6673.68,48384.18,1),(2,'2023-01-04',1,2,6,20848.5,3335.76,24184.26,1),(3,'2023-01-09',0,10,16,78649.5,12583.92,91233.42,1),(4,'2023-01-10',1,1,20,28122,4499.52,32621.52,1),(5,'2023-01-11',1,6,19,30444,4871.04,35315.04,1),(6,'2023-01-11',1,5,7,82417.5,13186.8,95604.3,1),(7,'2023-01-12',0,9,18,43105.5,6896.88,50002.38,1),(8,'2023-01-13',1,8,1,37237.5,5958,43195.5,1),(9,'2023-01-15',1,2,1,24243,3878.88,28121.88,1),(10,'2023-01-15',1,6,5,46770,7483.2,54253.2,1),(11,'2023-01-17',0,10,7,50938.5,8150.16,59088.66,1),(12,'2023-01-18',1,9,7,29727,4756.32,34483.32,1),(13,'2023-01-19',0,4,6,1765.5,282.48,2047.98,1),(14,'2023-01-20',0,8,12,31755,5080.8,36835.8,1),(15,'2023-01-20',0,2,11,70747.5,11319.6,82067.1,1),(16,'2023-01-21',1,10,9,54684,8749.44,63433.44,1),(17,'2023-01-22',1,9,11,43963.5,7034.16,50997.66,1),(18,'2023-01-23',1,10,20,69510,11121.6,80631.6,1),(19,'2023-01-25',0,4,11,66352.5,10616.4,76968.9,1),(20,'2023-01-26',1,5,8,34171.5,5467.44,39638.94,1),(21,'2023-01-28',1,7,12,7588.5,1214.16,8802.66,1),(22,'2023-01-28',0,9,8,48093,7694.88,55787.88,1),(23,'2023-02-03',1,9,11,32470.5,5195.28,37665.78,1),(24,'2023-02-03',1,8,1,49084.5,7853.52,56938.02,1),(25,'2023-02-07',0,9,17,53349,8535.84,61884.84,1),(26,'2023-02-07',0,6,14,75999,12159.84,88158.84,1),(27,'2023-02-10',1,9,6,26016,4162.56,30178.56,1),(28,'2023-02-12',1,2,9,37509,6001.44,43510.44,1),(29,'2023-02-13',0,2,15,26572.5,4251.6,30824.1,1),(30,'2023-02-18',0,10,6,71221.5,11395.44,82616.94,1),(31,'2023-02-18',0,2,19,66876,10700.16,77576.16,1),(32,'2023-02-19',1,1,4,27703.5,4432.56,32136.06,1),(33,'2023-02-21',1,5,1,10069.5,1611.12,11680.62,1),(34,'2023-02-22',1,9,6,12396,1983.36,14379.36,1),(35,'2023-02-24',1,5,11,7747.5,1239.6,8987.1,1),(36,'2023-02-24',0,4,1,12780,2044.8,14824.8,1),(37,'2023-02-24',0,10,3,28047,4487.52,32534.52,1),(38,'2023-02-25',0,7,11,6253.5,1000.56,7254.06,1),(39,'2023-02-25',1,1,12,18424.5,2947.92,21372.42,1),(40,'2023-02-25',1,8,16,11644.5,1863.12,13507.62,1),(41,'2023-02-25',1,6,10,11548.5,1847.76,13396.26,1),(42,'2023-02-27',0,9,14,101959.5,16313.52,118273.02,1),(43,'2023-02-28',0,1,15,15727.5,2516.4,18243.9,1),(44,'2023-02-28',1,8,12,19719,3155.04,22874.04,1),(45,'2023-03-04',0,9,1,4893,782.88,5675.88,1),(46,'2023-03-04',0,5,20,28687.5,4590,33277.5,1),(47,'2023-03-05',1,5,10,94137,15061.92,109198.92,1),(48,'2023-03-05',0,1,8,59443.5,9510.96,68954.46,1),(49,'2023-03-07',1,6,16,50005.5,8000.88,58006.38,1),(50,'2023-03-07',1,10,20,34318.5,5490.96,39809.46,1),(51,'2023-03-08',1,2,13,14133,2261.28,16394.28,1),(52,'2023-03-11',0,8,11,8989.5,1438.32,10427.82,1),(53,'2023-03-11',1,8,10,55068,8810.88,63878.88,1),(54,'2023-03-11',0,9,15,73459.5,11753.52,85213.02,1),(55,'2023-03-13',1,3,13,66594,10655.04,77249.04,1),(56,'2023-03-14',1,5,6,50059.5,8009.52,58069.02,1),(57,'2023-03-16',0,7,10,55668,8906.88,64574.88,1),(58,'2023-03-16',0,6,9,71725.5,11476.08,83201.58,1),(59,'2023-03-18',1,6,10,36822,5891.52,42713.52,1),(60,'2023-03-19',1,4,5,24093,3854.88,27947.88,1),(61,'2023-03-20',1,10,19,61560,9849.6,71409.6,1),(62,'2023-03-21',1,5,13,42187.5,6750,48937.5,1),(63,'2023-03-23',1,3,17,44644.5,7143.12,51787.62,1),(64,'2023-03-24',1,7,11,59025,9444,68469,1),(65,'2023-03-24',0,5,15,35701.5,5712.24,41413.74,1),(66,'2023-03-25',1,10,8,52213.5,8354.16,60567.66,1),(67,'2023-04-03',0,6,18,48823.5,7811.76,56635.26,1),(68,'2023-04-05',1,4,7,50221.5,8035.44,58256.94,1),(69,'2023-04-07',1,7,4,39759,6361.44,46120.44,1),(70,'2023-04-07',1,3,3,40641,6502.56,47143.56,1),(71,'2023-04-07',0,9,11,28294.5,4527.12,32821.62,1),(72,'2023-04-09',1,2,18,44743.5,7158.96,51902.46,1),(73,'2023-04-10',0,10,11,34018.5,5442.96,39461.46,1),(74,'2023-04-10',0,4,17,75618,12098.88,87716.88,1),(75,'2023-04-16',0,2,16,41598,6655.68,48253.68,1),(76,'2023-04-16',1,2,13,17967,2874.72,20841.72,1),(77,'2023-04-17',1,8,3,85611,13697.76,99308.76,1),(78,'2023-04-17',1,9,4,1390.5,222.48,1612.98,1),(79,'2023-04-18',0,3,5,24793.5,3966.96,28760.46,1),(80,'2023-04-18',1,5,8,3924,627.84,4551.84,1),(81,'2023-04-22',0,6,17,44881.5,7181.04,52062.54,1),(82,'2023-04-26',0,9,18,35368.5,5658.96,41027.46,1),(83,'2023-04-27',0,9,18,37719,6035.04,43754.04,1),(84,'2023-05-02',0,1,7,3969,635.04,4604.04,1),(85,'2023-05-03',0,9,16,1170,187.2,1357.2,1),(86,'2023-05-08',0,1,5,60936,9749.76,70685.76,1),(87,'2023-05-08',0,7,1,20136,3221.76,23357.76,1),(88,'2023-05-08',0,6,9,64341,10294.56,74635.56,1),(89,'2023-05-09',0,2,5,56998.5,9119.76,66118.26,1),(90,'2023-05-09',0,1,11,84993,13598.88,98591.88,1),(91,'2023-05-10',0,5,15,24153,3864.48,28017.48,1),(92,'2023-05-10',0,4,18,27166.5,4346.64,31513.14,1),(93,'2023-05-13',0,1,14,37936.5,6069.84,44006.34,1),(94,'2023-05-16',0,6,11,17560.5,2809.68,20370.18,1),(95,'2023-05-16',1,4,13,25095,4015.2,29110.2,1),(96,'2023-05-16',1,3,2,19461,3113.76,22574.76,1),(97,'2023-05-17',0,1,11,47050.5,7528.08,54578.58,1),(98,'2023-05-18',0,5,12,58836,9413.76,68249.76,1),(99,'2023-05-19',1,3,18,33018,5282.88,38300.88,1),(100,'2023-05-19',1,9,18,91602,14656.32,106258.32,1),(101,'2023-05-20',0,1,15,50818.5,8130.96,58949.46,1),(102,'2023-05-21',0,7,13,56877,9100.32,65977.32,1),(103,'2023-05-23',1,8,2,1777.5,284.4,2061.9,1),(104,'2023-05-24',1,6,11,83701.5,13392.24,97093.74,1),(105,'2023-05-26',1,6,20,51709.5,8273.52,59983.02,1),(106,'2023-05-26',0,1,3,14217,2274.72,16491.72,1),(107,'2023-05-28',1,1,16,60564,9690.24,70254.24,1),(108,'2023-05-28',1,4,7,27850.5,4456.08,32306.58,1),(109,'2023-05-30',1,1,3,26268,4202.88,30470.88,1),(110,'2023-06-02',1,8,15,40975.5,6556.08,47531.58,1),(111,'2023-06-04',1,1,9,26025,4164,30189,1),(112,'2023-06-05',0,3,16,23791.5,3806.64,27598.14,1),(113,'2023-06-05',1,2,18,29352,4696.32,34048.32,1),(114,'2023-06-09',0,1,1,46570.5,7451.28,54021.78,1),(115,'2023-06-10',1,10,13,31180.5,4988.88,36169.38,1),(116,'2023-06-10',0,4,16,37149,5943.84,43092.84,1),(117,'2023-06-12',0,1,3,19005,3040.8,22045.8,1),(118,'2023-06-19',0,7,11,43956,7032.96,50988.96,1),(119,'2023-06-20',0,2,4,46657.5,7465.2,54122.7,1),(120,'2023-06-20',1,5,17,38005.5,6080.88,44086.38,1),(121,'2023-06-21',1,1,1,29662.5,4746,34408.5,1),(122,'2023-06-22',1,5,5,50655,8104.8,58759.8,1),(123,'2023-06-23',1,5,10,42741,6838.56,49579.56,1),(124,'2023-06-23',1,7,11,12627,2020.32,14647.32,1),(125,'2023-06-25',0,10,5,50407.5,8065.2,58472.7,1),(126,'2023-07-03',0,5,7,20604,3296.64,23900.64,1),(127,'2023-07-09',0,9,5,20784,3325.44,24109.44,1),(128,'2023-07-11',0,9,15,42493.5,6798.96,49292.46,1),(129,'2023-07-11',0,4,18,4305,688.8,4993.8,1),(130,'2023-07-12',0,1,12,7492.5,1198.8,8691.3,1),(131,'2023-07-14',0,6,12,47275.5,7564.08,54839.58,1),(132,'2023-07-15',0,2,9,12141,1942.56,14083.56,1),(133,'2023-07-16',0,6,18,15939,2550.24,18489.24,1),(134,'2023-07-17',0,8,4,14269.5,2283.12,16552.62,1),(135,'2023-07-19',1,5,12,21258,3401.28,24659.28,1),(136,'2023-07-20',1,6,10,51238.5,8198.16,59436.66,1),(137,'2023-07-21',1,8,11,52330.5,8372.88,60703.38,1),(138,'2023-07-22',1,5,16,20541,3286.56,23827.56,1),(139,'2023-07-22',1,8,10,36028.5,5764.56,41793.06,1),(140,'2023-07-24',0,10,10,56229,8996.64,65225.64,1),(141,'2023-07-25',0,2,10,86260.5,13801.68,100062.18,1),(142,'2023-07-30',1,5,5,24970.5,3995.28,28965.78,1),(143,'2023-08-01',1,9,7,37288.5,5966.16,43254.66,1),(144,'2023-08-02',0,5,9,17293.5,2766.96,20060.46,1),(145,'2023-08-03',0,9,20,52764,8442.24,61206.24,1),(146,'2023-08-07',0,3,13,15787.5,2526,18313.5,1),(147,'2023-08-08',0,4,3,27835.5,4453.68,32289.18,1),(148,'2023-08-08',0,3,2,33838.5,5414.16,39252.66,1),(149,'2023-08-08',1,3,4,39261,6281.76,45542.76,1),(150,'2023-08-10',1,4,18,8460,1353.6,9813.6,1),(151,'2023-08-10',0,6,2,32529,5204.64,37733.64,1),(152,'2023-08-12',0,5,12,57555,9208.8,66763.8,1),(153,'2023-08-13',0,7,2,11980.5,1916.88,13897.38,1),(154,'2023-08-18',1,8,1,60357,9657.12,70014.12,1),(155,'2023-08-19',1,9,7,11874,1899.84,13773.84,1),(156,'2023-08-20',0,4,4,4459.5,713.52,5173.02,1),(157,'2023-08-21',0,7,12,15024,2403.84,17427.84,1),(158,'2023-08-26',0,4,17,30574.5,4891.92,35466.42,1),(159,'2023-08-26',1,10,15,58125,9300,67425,1),(160,'2023-08-29',0,5,4,1239,198.24,1437.24,1),(161,'2023-08-30',0,6,10,18663,2986.08,21649.08,1),(162,'2023-09-02',1,7,18,8460,1353.6,9813.6,1),(163,'2023-09-04',0,2,15,42751.5,6840.24,49591.74,1),(164,'2023-09-06',1,1,16,57931.5,9269.04,67200.54,1),(165,'2023-09-08',0,4,8,32976,5276.16,38252.16,1),(166,'2023-09-09',1,2,19,14392.5,2302.8,16695.3,1),(167,'2023-09-09',0,3,19,30376.5,4860.24,35236.74,1),(168,'2023-09-10',1,10,16,11097,1775.52,12872.52,1),(169,'2023-09-11',1,5,1,42243,6758.88,49001.88,1),(170,'2023-09-13',0,1,7,40479,6476.64,46955.64,1),(171,'2023-09-15',1,8,17,1545,247.2,1792.2,1),(172,'2023-09-16',1,4,19,30006,4800.96,34806.96,1),(173,'2023-09-18',1,7,12,105544.5,16887.12,122431.62,1),(174,'2023-09-18',1,2,17,38752.5,6200.4,44952.9,1),(175,'2023-09-18',0,4,17,70629,11300.64,81929.64,1),(176,'2023-09-19',0,9,1,22771.5,3643.44,26414.94,1),(177,'2023-09-22',0,2,12,9306,1488.96,10794.96,1),(178,'2023-09-23',1,10,7,60808.5,9729.36,70537.86,1),(179,'2023-09-24',0,9,14,49402.5,7904.4,57306.9,1),(180,'2023-09-27',1,9,10,9958.5,1593.36,11551.86,1),(181,'2023-09-29',1,3,13,17937,2869.92,20806.92,1),(182,'2023-09-30',0,10,4,27303,4368.48,31671.48,1),(183,'2023-10-01',0,4,3,16485,2637.6,19122.6,1),(184,'2023-10-02',0,10,3,38412,6145.92,44557.92,1),(185,'2023-10-06',0,5,6,98068.5,15690.96,113759.46,1),(186,'2023-10-11',1,4,19,40819.5,6531.12,47350.62,1),(187,'2023-10-12',0,9,16,25986,4157.76,30143.76,1),(188,'2023-10-12',0,6,5,15492,2478.72,17970.72,1),(189,'2023-10-13',0,3,9,95151,15224.16,110375.16,1),(190,'2023-10-14',0,3,1,29070,4651.2,33721.2,1),(191,'2023-10-14',0,5,4,53427,8548.32,61975.32,1),(192,'2023-10-15',0,9,10,5170.5,827.28,5997.78,1),(193,'2023-10-17',1,5,11,24028.5,3844.56,27873.06,1),(194,'2023-10-20',1,8,19,40677,6508.32,47185.32,1),(195,'2023-10-21',1,2,19,14850,2376,17226,1),(196,'2023-11-03',0,10,5,13023,2083.68,15106.68,1),(197,'2023-11-03',0,3,5,3726,596.16,4322.16,1),(198,'2023-11-06',1,2,4,43348.5,6935.76,50284.26,1),(199,'2023-11-07',1,2,7,19962,3193.92,23155.92,1),(200,'2023-11-08',0,2,13,14077.5,2252.4,16329.9,1),(201,'2023-11-10',1,8,6,11683.5,1869.36,13552.86,1),(202,'2023-11-15',0,1,10,23524.5,3763.92,27288.42,1),(203,'2023-11-17',0,9,20,48709.5,7793.52,56503.02,1),(204,'2023-11-17',1,10,13,32841,5254.56,38095.56,1),(205,'2023-11-19',1,2,19,20437.5,3270,23707.5,1),(206,'2023-11-23',0,3,2,23970,3835.2,27805.2,1),(207,'2023-11-23',0,8,2,26610,4257.6,30867.6,1),(208,'2023-11-27',0,8,3,59287.5,9486,68773.5,1),(209,'2023-11-28',1,4,20,13591.5,2174.64,15766.14,1),(210,'2023-12-03',1,2,1,14361,2297.76,16658.76,1),(211,'2023-12-09',0,2,8,13866,2218.56,16084.56,1),(212,'2023-12-09',1,5,15,43372.5,6939.6,50312.1,1),(213,'2023-12-12',1,6,4,39370.5,6299.28,45669.78,1),(214,'2023-12-13',0,4,12,35806.5,5729.04,41535.54,1),(215,'2023-12-14',0,6,2,21238.5,3398.16,24636.66,1),(216,'2023-12-15',1,8,17,40465.5,6474.48,46939.98,1),(217,'2023-12-17',0,8,10,74211,11873.76,86084.76,1),(218,'2023-12-17',0,3,17,48882,7821.12,56703.12,1),(219,'2023-12-17',0,2,9,50946,8151.36,59097.36,1),(220,'2023-12-18',0,8,11,50467.5,8074.8,58542.3,1),(221,'2023-12-19',0,10,14,22752,3640.32,26392.32,1),(222,'2023-12-19',1,1,7,58734,9397.44,68131.44,1),(223,'2023-12-22',0,3,13,1804.5,288.72,2093.22,1),(224,'2023-12-25',0,9,7,9423,1507.68,10930.68,1),(225,'2023-12-26',0,10,13,70581,11292.96,81873.96,1),(226,'2023-12-26',0,4,14,12838.5,2054.16,14892.66,1),(227,'2023-12-27',0,10,2,100036.5,16005.84,116042.34,1),(228,'2023-12-27',0,8,3,13810.5,2209.68,16020.18,1),(229,'2023-12-29',0,3,13,24940.5,3990.48,28930.98,1),(230,'2023-12-30',1,5,4,46185,7389.6,53574.6,1);
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
/*!50003 DROP PROCEDURE IF EXISTS `buscar_categoria_por_indice` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `buscar_categoria_por_indice`(IN `indice` INT UNSIGNED)
BEGIN

	

    SELECT categoria_producto.nombre, categoria_producto.descripcion FROM categoria_producto WHERE categoria_producto.id_categoria = indice;

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
		categoria_producto.descripcion
    FROM categoria_producto WHERE categoria_producto.nombre = nombre;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_ZERO_IN_DATE,NO_ZERO_DATE,NO_ENGINE_SUBSTITUTION' */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertar_nva_categoria` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertar_nva_categoria`(IN `nombre_c` VARCHAR(60) CHARSET utf8, IN `descripcion_m` VARCHAR(255) CHARSET utf8)
BEGIN

	DECLARE nombreCategoria VARCHAR(60);

    

    SELECT @nombreCategoria := categoria_producto.nombre FROM categoria_producto WHERE categoria_producto.nombre = nombre_c;

    

    IF (@nombreCategoria = nombre_c) THEN

    	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Registro duplicado';

    END IF;



	INSERT INTO categoria_producto (categoria_producto.nombre, categoria_producto.descripcion) VALUES(

        nombre_c,

        descripcion_m

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
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_articulo`(
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
)
BEGIN
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
/*!50003 SET collation_connection  = utf8mb4_unicode_ci */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insert_nuevo_cliente`(
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
)
BEGIN
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
    rfc_e,
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
        codigo_postal
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
        codigo_postal_p
    );
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
    
    SELECT @nombreCategoria := categoria_producto.nombre FROM categoria_producto WHERE categoria_producto.nombre = nombre_c;
    
    IF (@nombreCategoria = nombre_c) THEN
    	SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Registro duplicado';
    END IF;
    
	INSERT INTO categoria_producto (categoria_producto.nombre, categoria_producto.descripcion) VALUES(
        nombre_c,
        descripcion_m
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
	empleados.rfc,
	empleados.curp,
    empleados.nombre_completo,
    empleados.nombre_corto,
    empleados.correo_electronico
FROM empleados ;;
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
        descripcion = descripcion_c
	WHERE id_categoria = id_categoria_c;
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
		nombre_completo = nombre_completo_e,
        nombre_corto = nombre_corto_e,
        fecha_nac = fecha_nac_e,
        correo_electronico = correo_electronico_e,
        estado = estado_e,
        ciudad = ciudad_e,
        direccion = direccion_e,
        codigo_postal = codigo_postal_e
	WHERE rfc = rfc_e;
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
        codigo_postal = codigo_postal_p
	WHERE proveedor.id_proveedor = id_p;
    
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_articulos`()
BEGIN
	
    SELECT
		articulo.id_articulo,
		articulo.codigo_articulo,
		proveedor.nombre,
		categoria_producto.nombre,
		articulo.codigo_sat,
		articulo.nombre,
		articulo.descripcion,
		articulo.existencia,
		articulo.precio_general,
		articulo.precio_mayoreo
	FROM articulo
	INNER JOIN proveedor ON articulo.id_proveedor = proveedor.id_proveedor
	INNER JOIN categoria_producto ON articulo.id_categoria = categoria_producto.id_categoria
	ORDER BY id_articulo;
    
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
	proveedor.rfc,
    sub_cuentas_tercer_nivel.clave,
    proveedor.nombre,
    proveedor.descripcion,
    proveedor.correo_electronico,
    proveedor.estado,
    proveedor.ciudad,
    proveedor.direccion,
    proveedor.codigo_postal
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

--
-- Final view structure for view `empleados_nombre_costo`
--

/*!50001 DROP VIEW IF EXISTS `empleados_nombre_costo`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_unicode_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `empleados_nombre_costo` AS select `empleados`.`nombre_corto` AS `nombre_corto` from `empleados` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

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

-- Dump completed on 2023-07-28 19:06:31
