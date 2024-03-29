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
  `nombre` varchar(30) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `es_exento` tinyint(1) NOT NULL,
  `costo_unitario` double NOT NULL,
  `precio_general` double NOT NULL,
  `precio_mayoreo` double NOT NULL,
  `cantidad_mayoreo` int(11) DEFAULT NULL,
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_articulo`),
  UNIQUE KEY `codigo_articulo` (`codigo_articulo`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_producto` (`id_categoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=275 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'6308156511',5,8,'53131600','producto de prueba 1','este es un producto de prueba numero 1',0,229,458,398.46,12,1),(2,'6353928792',1,8,'53131600','producto de prueba 2','este es un producto de prueba numero 2',0,1185,2370,2061.9,8,0),(3,'2714369471',19,3,'53131600','producto de prueba 3','este es un producto de prueba numero 3',0,265,530,461.1,7,1),(4,'991281012',12,9,'53131600','producto de prueba 4','este es un producto de prueba numero 4 ahora actualizado',0,1410,2820,2453.4,10,1),(5,'170577547',12,5,'53131600','producto de prueba 5','este es un producto de prueba numero 5',0,1216,2432,2115.84,11,1),(6,'3561211483',10,7,'53131600','producto de prueba 6','este es un producto de prueba numero 6',0,141,282,245.34,11,1),(7,'7557085886',8,12,'53131600','producto de prueba 7','este es un producto de prueba numero 7',0,654,1308,1137.96,6,1),(8,'4536011527',25,8,'53131600','producto de prueba 8','este es un producto de prueba numero 8',0,103,206,179.22,7,1),(9,'6302982072',8,11,'53131600','producto de prueba 9','este es un producto de prueba numero 9',0,998,1996,1736.52,11,1),(10,'112021197',24,7,'53131600','producto de prueba 10','este es un producto de prueba numero 10',0,259,518,450.66,6,1),(11,'4943051525',5,11,'53131600','producto de prueba 11','este es un producto de prueba numero 11',0,466,932,810.84,15,1),(12,'7411366789',13,14,'53131600','producto de prueba 12','este es un producto de prueba numero 12',0,530,1060,922.2,7,1),(13,'789614018',3,11,'53131600','producto de prueba 13','este es un producto de prueba numero 13',0,506,1012,880.44,14,1),(14,'687131253',12,1,'53131600','producto de prueba 14','este es un producto de prueba numero 14',0,1392,2784,2422.08,11,1),(15,'1408572448',14,14,'53131600','producto de prueba 15','este es un producto de prueba numero 15',0,145,290,252.3,8,1),(16,'2424427361',11,8,'53131600','producto de prueba 16','este es un producto de prueba numero 16',0,1193,2386,2075.82,8,1),(17,'1507869333',24,7,'53131600','producto de prueba 17','este es un producto de prueba numero 17',0,1275,2550,2218.5,14,1),(18,'620175007',4,2,'53131600','producto de prueba 18','este es un producto de prueba numero 18',0,1109,2218,1929.66,7,1),(19,'9057093161',12,11,'53131600','producto de prueba 19','este es un producto de prueba numero 19',0,960,1920,1670.4,13,1),(20,'382986849',20,9,'53131600','producto de prueba 20','este es un producto de prueba numero 20',0,1418,2836,2467.32,12,1),(21,'726695035',17,12,'53131600','producto de prueba 21','este es un producto de prueba numero 21',0,1385,2770,2409.9,14,1),(22,'273361305',14,13,'53131600','producto de prueba 22','este es un producto de prueba numero 22',0,276,552,480.24,13,1),(23,'316535400',4,9,'53131600','producto de prueba 23','este es un producto de prueba numero 23',0,1120,2240,1948.8,9,1),(24,'339321491',22,4,'53131600','producto de prueba 24','este es un producto de prueba numero 24',0,401,802,697.74,13,1),(25,'4474820310',3,1,'53131600','producto de prueba 25','este es un producto de prueba numero 25',0,1030,2060,1792.2,8,1),(26,'6766890378',14,11,'53131600','producto de prueba 26','este es un producto de prueba numero 26',0,347,694,603.78,10,1),(27,'9618173344',15,4,'53131600','producto de prueba 27','este es un producto de prueba numero 27',0,437,874,760.38,12,1),(28,'6712913178',22,2,'53131600','producto de prueba 28','este es un producto de prueba numero 28',0,215,430,374.1,14,1),(29,'4535448809',21,1,'53131600','producto de prueba 29','este es un producto de prueba numero 29',0,399,798,694.26,11,1),(30,'6650072739',6,4,'53131600','producto de prueba 30','este es un producto de prueba numero 30',0,416,832,723.84,7,1),(31,'918469346',22,14,'53131600','producto de prueba 31','este es un producto de prueba numero 31',0,590,1180,1026.6,10,1),(32,'3956527410',6,11,'53131600','producto de prueba 32','este es un producto de prueba numero 32',0,142,284,247.08,9,1),(33,'2945449610',4,11,'53131600','producto de prueba 33','este es un producto de prueba numero 33',0,1372,2744,2387.28,6,1),(34,'4186956417',13,11,'53131600','producto de prueba 34','este es un producto de prueba numero 34',0,1260,2520,2192.4,7,1),(35,'7116414346',12,14,'53131600','producto de prueba 35','este es un producto de prueba numero 35',0,383,766,666.42,7,1),(36,'2662680632',17,1,'53131600','producto de prueba 36','este es un producto de prueba numero 36',0,1393,2786,2423.82,14,1),(37,'7400391319',14,3,'53131600','producto de prueba 37','este es un producto de prueba numero 37',0,1175,2350,2044.5,10,1),(38,'6693770542',3,13,'53131600','producto de prueba 38','este es un producto de prueba numero 38',0,333,666,579.42,13,1),(39,'8638541969',10,4,'53131600','producto de prueba 39','este es un producto de prueba numero 39',0,413,826,718.62,7,1),(40,'1691630121',4,3,'53131600','producto de prueba 40','este es un producto de prueba numero 40',0,699,1398,1216.26,7,1),(41,'1838738431',4,6,'53131600','producto de prueba 41','este es un producto de prueba numero 41',0,878,1756,1527.72,14,1),(42,'7182875265',9,1,'53131600','producto de prueba 42','este es un producto de prueba numero 42',0,911,1822,1585.14,15,1),(43,'3980592284',25,15,'53131600','producto de prueba 43','este es un producto de prueba numero 43',0,378,756,657.72,14,1),(44,'5255512832',21,10,'53131600','producto de prueba 44','este es un producto de prueba numero 44',0,1177,2354,2047.98,9,1),(45,'488782469',22,3,'53131600','producto de prueba 45','este es un producto de prueba numero 45',0,1312,2624,2282.88,15,1),(46,'2607341609',14,8,'53131600','producto de prueba 46','este es un producto de prueba numero 46',0,797,1594,1386.78,14,1),(47,'5212578037',7,13,'53147800','Laptop Lenovo X carbon','Laptop Lenoco X carbon con componentes personalizables',0,3871.54,10250.12,9215.12,10,1),(48,'1361099607',10,10,'53131600','producto de prueba 48','este es un producto de prueba numero 48',0,231,462,401.94,13,1),(49,'775332094',12,10,'53131600','producto de prueba 49','este es un producto de prueba numero 49',0,959,1918,1668.66,9,1),(50,'904544303',8,9,'53131600','producto de prueba 50','este es un producto de prueba numero 50',0,826,1652,1437.24,15,1),(51,'3622914630',23,7,'53131600','producto de prueba 51','este es un producto de prueba numero 51',0,1191,2382,2072.34,15,1),(52,'2279994066',10,12,'53131600','producto de prueba 52','este es un producto de prueba numero 52',0,395,790,687.3,14,1),(53,'9516169646',11,12,'53131600','producto de prueba 53','este es un producto de prueba numero 53',0,1372,2744,2387.28,8,1),(54,'8688032229',14,2,'53131600','producto de prueba 54','este es un producto de prueba numero 54',0,659,1318,1146.66,12,1),(55,'2025784483',15,7,'53131600','producto de prueba 55','este es un producto de prueba numero 55',0,1022,2044,1778.28,7,1),(56,'3425595146',7,15,'53131600','producto de prueba 56','este es un producto de prueba numero 56',0,1435,2870,2496.9,8,1),(57,'7844944742',4,2,'53131600','producto de prueba 57','este es un producto de prueba numero 57',0,1263,2526,2197.62,12,1),(58,'6235320600',22,11,'53131600','producto de prueba 58','este es un producto de prueba numero 58',0,1262,2524,2195.88,13,1),(59,'9338362463',11,3,'53131600','producto de prueba 59','este es un producto de prueba numero 59',0,484,968,842.16,13,1),(60,'2724882931',2,12,'53131600','producto de prueba 60','este es un producto de prueba numero 60',0,287,574,499.38,14,1),(61,'9449511889',10,12,'53131600','producto de prueba 61','este es un producto de prueba numero 61',0,1425,2850,2479.5,12,1),(62,'6028115898',17,1,'53131600','producto de prueba 62','este es un producto de prueba numero 62',0,553,1106,962.22,6,1),(63,'6857910633',25,3,'53131600','producto de prueba 63','este es un producto de prueba numero 63',0,223,446,388.02,12,1),(64,'4874037838',3,1,'53131600','producto de prueba 64','este es un producto de prueba numero 64',0,1233,2466,2145.42,13,1),(65,'3349062176',6,1,'53131600','producto de prueba 65','este es un producto de prueba numero 65',0,359,718,624.66,6,1),(66,'3954891843',3,6,'53131600','producto de prueba 66','este es un producto de prueba numero 66',0,536,1072,932.64,14,1),(67,'5845654682',11,1,'53131600','producto de prueba 67','este es un producto de prueba numero 67',0,697,1394,1212.78,7,1),(68,'642872922',17,1,'53131600','producto de prueba 68','este es un producto de prueba numero 68',0,1033,2066,1797.42,8,1),(69,'3180943050',22,6,'53131600','producto de prueba 69','este es un producto de prueba numero 69',0,2870,3357.9,3022.11,9,1),(70,'5028849193',14,5,'53131600','producto de prueba 70','este es un producto de prueba numero 70',0,929,1049.77,997.2815,15,1),(71,'8196736116',13,14,'53131600','producto de prueba 71','este es un producto de prueba numero 71',0,2819,3100.9,2852.828,9,1),(72,'207985049',21,9,'53131600','producto de prueba 72','este es un producto de prueba numero 72',0,1388,1540.68,1463.646,15,1),(73,'5869616498',20,10,'53131600','producto de prueba 73','este es un producto de prueba numero 73',0,1688,1890.56,1758.2208,15,1),(74,'5539329872',5,4,'53131600','producto de prueba 74','este es un producto de prueba numero 74',0,375,412.5,379.5,7,1),(75,'4884141152',2,6,'53131600','producto de prueba 75','este es un producto de prueba numero 75',0,861,998.76,898.884,12,1),(76,'6489123605',14,15,'53131600','producto de prueba 76','este es un producto de prueba numero 76',0,672,752.64,677.376,15,1),(77,'2007649196',24,4,'53131600','producto de prueba 77','este es un producto de prueba numero 77',0,1424,1651.84,1519.6928,15,1),(78,'962842387',3,15,'53131600','producto de prueba 78','este es un producto de prueba numero 78',0,2862,3262.68,3001.6656,10,1),(79,'8863336746',14,13,'53131600','producto de prueba 79','este es un producto de prueba numero 79',0,1424,1609.12,1448.208,14,1),(80,'8664622038',22,10,'53131600','producto de prueba 80','este es un producto de prueba numero 80',0,633,740.61,696.1734,9,1),(81,'9913763536',2,13,'53131600','producto de prueba 81','este es un producto de prueba numero 81',0,681,769.53,723.3582,15,1),(82,'639735581',15,9,'53131600','producto de prueba 82','este es un producto de prueba numero 82',0,2032,2357.12,2239.264,12,1),(83,'989231901',10,10,'53131600','producto de prueba 83','este es un producto de prueba numero 83',0,967,1063.7,989.241,10,1),(84,'5841844899',8,8,'53131600','producto de prueba 84','este es un producto de prueba numero 84',0,928,1104.32,993.888,7,1),(85,'4786696403',4,3,'53131600','producto de prueba 85','este es un producto de prueba numero 85',0,2870,3357.9,3156.426,14,1),(86,'6734644294',24,9,'53131600','producto de prueba 86','este es un producto de prueba numero 86',0,1746,1972.98,1795.4118,6,1),(87,'3991333355',2,8,'53131600','producto de prueba 87','este es un producto de prueba numero 87',0,2117,2371.04,2252.488,10,1),(88,'425563866',14,14,'53131600','producto de prueba 88','este es un producto de prueba numero 88',0,111,132.09,120.2019,12,1),(89,'4429544164',15,11,'53131600','producto de prueba 89','este es un producto de prueba numero 89',0,1964,2278.24,2095.9808,12,1),(90,'8368196433',22,13,'53131600','producto de prueba 90','este es un producto de prueba numero 90',0,1284,1412.4,1327.656,8,1),(91,'5761828235',9,11,'53131600','producto de prueba 91','este es un producto de prueba numero 91',0,1426,1582.86,1503.717,8,1),(92,'3695671897',14,6,'53131600','producto de prueba 92','este es un producto de prueba numero 92',0,421,463.1,439.945,11,1),(93,'7063411618',20,10,'53131600','producto de prueba 93','este es un producto de prueba numero 93',0,2694,3232.8,2974.176,9,1),(94,'6362746339',9,2,'53131600','producto de prueba 94','este es un producto de prueba numero 94',0,1134,1292.76,1202.2668,13,1),(95,'7294356437',5,4,'53131600','producto de prueba 95','este es un producto de prueba numero 95',0,111,122.1,111.111,15,1),(96,'310061324',12,4,'53131600','producto de prueba 96','este es un producto de prueba numero 96',0,2629,3154.8,2839.32,15,1),(97,'6024919713',4,3,'53131600','producto de prueba 97','este es un producto de prueba numero 97',0,275,316.25,290.95,14,1),(98,'1949080705',21,15,'53131600','producto de prueba 98','este es un producto de prueba numero 98',0,2991,3469.56,3296.082,15,1),(99,'562217106',23,14,'53131600','producto de prueba 99','este es un producto de prueba numero 99',0,414,496.8,466.992,6,1),(100,'4391189109',9,8,'53131600','producto de prueba 100','este es un producto de prueba numero 100',0,2336,2709.76,2492.9792,15,1),(101,'578705407',8,10,'53131600','producto de prueba 101','este es un producto de prueba numero 101',0,2706,3138.96,2950.6224,9,1),(102,'2851581072',18,11,'53131600','producto de prueba 102','este es un producto de prueba numero 102',0,1912,2141.44,2034.368,6,1),(103,'6615817026',14,15,'53131600','producto de prueba 103','este es un producto de prueba numero 103',0,2972,3328.64,3062.3488,9,1),(104,'3855929178',7,7,'53131600','producto de prueba 104','este es un producto de prueba numero 104',0,707,820.12,779.114,6,1),(105,'7361440589',16,10,'53131600','producto de prueba 105','este es un producto de prueba numero 105',0,938,1069.32,983.7744,7,1),(106,'3185448667',3,9,'53131600','producto de prueba 106','este es un producto de prueba numero 106',0,2994,3562.86,3242.2026,10,1),(107,'6296865881',6,15,'53131600','producto de prueba 107','este es un producto de prueba numero 107',0,2921,3388.36,3083.4076,11,1),(108,'7245775475',6,7,'53131600','producto de prueba 108','este es un producto de prueba numero 108',0,1908,2136.96,2008.7424,13,1),(109,'8353637369',11,11,'53131600','producto de prueba 109','este es un producto de prueba numero 109',0,2709,3115.35,2834.9685,14,1),(110,'3352924748',13,7,'53131600','producto de prueba 110','este es un producto de prueba numero 110',0,2219,2463.09,2241.4119,8,1),(111,'4003835349',7,12,'53131600','producto de prueba 111','este es un producto de prueba numero 111',0,2852,3165.72,2880.8052,9,1),(112,'6078880106',12,15,'53131600','producto de prueba 112','este es un producto de prueba numero 112',0,541,622.15,572.378,7,1),(113,'1170681905',23,6,'53131600','producto de prueba 113','este es un producto de prueba numero 113',0,483,555.45,505.4595,6,1),(114,'5983321922',23,15,'53131600','producto de prueba 114','este es un producto de prueba numero 114',0,2429,2866.22,2636.9224,14,1),(115,'6172061819',21,4,'53131600','producto de prueba 115','este es un producto de prueba numero 115',0,2619,3011.85,2740.7835,15,1),(116,'3711220943',18,8,'53131600','producto de prueba 116','este es un producto de prueba numero 116',0,2782,3310.58,2979.522,10,1),(117,'5106874879',15,14,'53131600','producto de prueba 117','este es un producto de prueba numero 117',0,2692,3068.88,2854.0584,11,1),(118,'8316376326',11,4,'53131600','producto de prueba 118','este es un producto de prueba numero 118',0,2506,2781.66,2642.577,8,1),(119,'7912651668',22,9,'53131600','producto de prueba 119','este es un producto de prueba numero 119',0,832,940.16,883.7504,15,1),(120,'4298944501',18,4,'53131600','producto de prueba 120','este es un producto de prueba numero 120',0,2451,2843.16,2615.7072,15,1),(121,'6988382988',23,11,'53131600','producto de prueba 121','este es un producto de prueba numero 121',0,457,502.7,462.484,9,1),(122,'7990543141',10,2,'53131600','producto de prueba 122','este es un producto de prueba numero 122',0,51,57.63,54.7485,15,1),(123,'289898498',15,9,'53131600','producto de prueba 123','este es un producto de prueba numero 123',0,1192,1430.4,1301.664,13,1),(124,'865717618',6,13,'53131600','producto de prueba 124','este es un producto de prueba numero 124',0,1753,1963.36,1845.5584,13,1),(125,'3436481982',15,1,'53131600','producto de prueba 125','este es un producto de prueba numero 125',0,1564,1876.8,1726.656,15,1),(126,'9493319717',7,13,'53131600','producto de prueba 126','este es un producto de prueba numero 126',0,1513,1770.21,1681.6995,14,1),(127,'933533858',1,1,'53131600','producto de prueba 127','este es un producto de prueba numero 127',0,847,974.05,876.645,13,1),(128,'3143030948',12,10,'53131600','producto de prueba 128','este es un producto de prueba numero 128',0,1274,1503.32,1383.0544,11,1),(129,'1170225414',22,10,'53131600','producto de prueba 129','este es un producto de prueba numero 129',0,2151,2409.12,2264.5728,14,1),(130,'3270119836',20,4,'53131600','producto de prueba 130','este es un producto de prueba numero 130',0,707,834.26,750.834,12,1),(131,'941628112',6,8,'53131600','producto de prueba 131','este es un producto de prueba numero 131',0,2408,2648.8,2463.384,10,1),(132,'9277998034',2,10,'53131600','producto de prueba 132','este es un producto de prueba numero 132',0,2595,3062.1,2817.132,15,1),(133,'1684587193',13,9,'53131600','producto de prueba 133','este es un producto de prueba numero 133',0,2076,2491.2,2366.64,11,1),(134,'6749624344',20,9,'53131600','producto de prueba 134','este es un producto de prueba numero 134',0,2864,3408.16,3169.5888,12,1),(135,'4310890194',19,9,'53131600','producto de prueba 135','este es un producto de prueba numero 135',0,2415,2656.5,2470.545,14,1),(136,'744329760',18,6,'53131600','producto de prueba 136','este es un producto de prueba numero 136',0,2316,2570.76,2416.5144,13,1),(137,'7702130556',19,10,'53131600','producto de prueba 137','este es un producto de prueba numero 137',0,2111,2469.87,2296.9791,15,1),(138,'2883982296',20,10,'53131600','producto de prueba 138','este es un producto de prueba numero 138',0,2367,2722.05,2449.845,11,1),(139,'258737178',22,4,'53131600','producto de prueba 139','este es un producto de prueba numero 139',0,1325,1510.5,1389.66,6,1),(140,'8301631492',4,6,'53131600','producto de prueba 140','este es un producto de prueba numero 140',0,826,991.2,892.08,11,1),(141,'2768788758',12,4,'53131600','producto de prueba 141','este es un producto de prueba numero 141',0,2529,2832.48,2634.2064,13,1),(142,'801387390',8,12,'53131600','producto de prueba 142','este es un producto de prueba numero 142',0,2091,2341.92,2177.9856,13,1),(143,'8826622053',7,15,'53131600','producto de prueba 143','este es un producto de prueba numero 143',0,2801,3305.18,3073.8174,14,1),(144,'2028626439',21,9,'53131600','producto de prueba 144','este es un producto de prueba numero 144',0,1711,2018.98,1857.4616,8,1),(145,'9333956283',22,9,'53131600','producto de prueba 145','este es un producto de prueba numero 145',0,351,421.2,379.08,12,1),(146,'7052028931',17,1,'53131600','producto de prueba 146','este es un producto de prueba numero 146',0,647,731.11,665.3101,13,1),(147,'3231339073',3,9,'53131600','producto de prueba 147','este es un producto de prueba numero 147',0,163,190.71,175.4532,14,1),(148,'9787690741',5,5,'53131600','producto de prueba 148','este es un producto de prueba numero 148',0,1257,1420.41,1349.3895,7,1),(149,'1040924611',17,7,'53131600','producto de prueba 149','este es un producto de prueba numero 149',0,2449,2816.35,2647.369,12,1),(150,'4328791463',12,1,'53131600','producto de prueba 150','este es un producto de prueba numero 150',0,1066,1268.54,1154.3714,6,1),(151,'6330576453',25,2,'53131600','producto de prueba 151','este es un producto de prueba numero 151',0,2697,3236.4,3074.58,7,1),(152,'326267517',10,8,'53131600','producto de prueba 152','este es un producto de prueba numero 152',0,1746,1955.52,1857.744,8,1),(153,'8913559659',25,1,'53131600','producto de prueba 153','este es un producto de prueba numero 153',0,477,524.7,487.971,11,1),(154,'2518511959',7,9,'53131600','producto de prueba 154','este es un producto de prueba numero 154',0,2035,2421.65,2179.485,10,1),(155,'9442950438',6,2,'53131600','producto de prueba 155','este es un producto de prueba numero 155',0,813,959.34,911.373,12,1),(156,'489179642',23,9,'53131600','producto de prueba 156','este es un producto de prueba numero 156',0,1006,1156.9,1064.348,12,1),(157,'7622678837',3,1,'53131600','producto de prueba 157','este es un producto de prueba numero 157',0,151,179.69,168.9086,9,1),(158,'7975339607',4,9,'53131600','producto de prueba 158','este es un producto de prueba numero 158',0,2544,2900.16,2726.1504,8,1),(159,'9323368151',22,8,'53131600','producto de prueba 159','este es un producto de prueba numero 159',0,184,206.08,195.776,10,1),(160,'6441012889',11,14,'53131600','producto de prueba 160','este es un producto de prueba numero 160',0,1125,1338.75,1245.0375,12,1),(161,'3060972104',6,4,'53131600','producto de prueba 161','este es un producto de prueba numero 161',0,914,1051.1,977.523,13,1),(162,'9277261902',9,9,'53131600','producto de prueba 162','este es un producto de prueba numero 162',0,2783,3311.77,3146.1815,15,1),(163,'2668574645',12,1,'53131600','producto de prueba 163','este es un producto de prueba numero 163',0,2488,2861.2,2660.916,7,1),(164,'2272112662',21,3,'53131600','producto de prueba 164','este es un producto de prueba numero 164',0,637,732.55,659.295,12,1),(165,'8245977732',25,6,'53131600','producto de prueba 165','este es un producto de prueba numero 165',0,2828,3393.6,3122.112,11,1),(166,'7821070413',6,6,'53131600','producto de prueba 166','este es un producto de prueba numero 166',0,2649,2966.88,2729.5296,13,1),(167,'3157828298',9,2,'53131600','producto de prueba 167','este es un producto de prueba numero 167',0,1196,1327.56,1221.3552,11,1),(168,'7586948765',3,2,'53131600','producto de prueba 168','este es un producto de prueba numero 168',0,1430,1615.9,1486.628,15,1),(169,'4475962429',16,14,'53131600','producto de prueba 169','este es un producto de prueba numero 169',0,361,425.98,387.6418,6,1),(170,'1180359543',15,11,'53131600','producto de prueba 170','este es un producto de prueba numero 170',0,1950,2340,2223,13,1),(171,'3729999646',19,15,'53131600','producto de prueba 171','este es un producto de prueba numero 171',0,1986,2363.34,2150.6394,15,1),(172,'3754763908',2,10,'53131600','producto de prueba 172','este es un producto de prueba numero 172',0,1973,2347.87,2113.083,9,1),(173,'2538294317',5,1,'53131600','producto de prueba 173','este es un producto de prueba numero 173',0,336,369.6,343.728,8,1),(174,'4757576587',17,11,'53131600','producto de prueba 174','este es un producto de prueba numero 174',0,1259,1422.67,1323.0831,6,1),(175,'2554865358',23,3,'53131600','producto de prueba 175','este es un producto de prueba numero 175',0,2054,2321.02,2181.7588,6,1),(176,'4592647014',20,14,'53131600','producto de prueba 176','este es un producto de prueba numero 176',0,393,451.95,406.755,9,1),(177,'7410047625',4,10,'53131600','producto de prueba 177','este es un producto de prueba numero 177',0,1145,1362.55,1226.295,9,1),(178,'2490414284',6,1,'53131600','producto de prueba 178','este es un producto de prueba numero 178',0,193,212.3,201.685,8,1),(179,'2568095939',2,11,'53131600','producto de prueba 179','este es un producto de prueba numero 179',0,1262,1514.4,1423.536,8,1),(180,'1894648999',12,9,'53131600','producto de prueba 180','este es un producto de prueba numero 180',0,1614,1839.96,1655.964,15,1),(181,'6342446565',8,5,'53131600','producto de prueba 181','este es un producto de prueba numero 181',0,2461,2953.2,2657.88,14,1),(182,'7954229546',3,10,'53131600','producto de prueba 182','este es un producto de prueba numero 182',0,1652,1883.28,1713.7848,9,1),(183,'1181431656',24,13,'53131600','producto de prueba 183','este es un producto de prueba numero 183',0,723,816.99,743.4609,14,1),(184,'3254837625',9,3,'53131600','producto de prueba 184','este es un producto de prueba numero 184',0,1146,1272.06,1195.7364,10,1),(185,'4046865043',18,15,'53131600','producto de prueba 185','este es un producto de prueba numero 185',0,1809,2044.17,1921.5198,15,1),(186,'2454899988',11,3,'53131600','producto de prueba 186','este es un producto de prueba numero 186',0,1748,2062.64,1959.508,15,1),(187,'4279274713',3,3,'53131600','producto de prueba 187','este es un producto de prueba numero 187',0,486,583.2,536.544,7,1),(188,'5971819031',8,7,'53131600','producto de prueba 188','este es un producto de prueba numero 188',0,419,477.66,434.6706,15,1),(189,'1694242672',19,7,'53131600','producto de prueba 189','este es un producto de prueba numero 189',0,1477,1728.09,1624.4046,14,1),(190,'1484211401',14,4,'53131600','producto de prueba 190','este es un producto de prueba numero 190',0,681,776.34,721.9962,9,1),(191,'835816668',11,7,'53131600','producto de prueba 191','este es un producto de prueba numero 191',0,117,129.87,116.883,11,1),(192,'2847552037',15,8,'53131600','producto de prueba 192','este es un producto de prueba numero 192',0,280,313.6,297.92,9,1),(193,'2159474710',17,6,'53131600','producto de prueba 193','este es un producto de prueba numero 193',0,2817,3267.72,2973.6252,7,1),(194,'7803450710',18,1,'53131600','producto de prueba 194','este es un producto de prueba numero 194',0,830,937.9,891.005,7,1),(195,'4093092720',9,3,'53131600','producto de prueba 195','este es un producto de prueba numero 195',0,1392,1614.72,1533.984,10,1),(196,'7361915887',13,11,'53131600','producto de prueba 196','este es un producto de prueba numero 196',0,2634,2897.4,2636.634,11,1),(197,'5174923853',5,7,'53131600','producto de prueba 197','este es un producto de prueba numero 197',0,834,959.1,891.963,7,1),(198,'7568637301',16,8,'53131600','producto de prueba 198','este es un producto de prueba numero 198',0,2549,2905.86,2644.3326,12,1),(199,'6133210849',7,15,'53131600','producto de prueba 199','este es un producto de prueba numero 199',0,2607,3102.33,2792.097,10,1),(200,'8554046750',14,12,'53131600','producto de prueba 200','este es un producto de prueba numero 200',0,938,1097.46,1020.6378,11,1),(201,'3079584693',3,9,'53131600','producto de prueba 201','este es un producto de prueba numero 201',0,1610,1771,1664.74,12,1),(202,'2470015503',6,7,'53131600','producto de prueba 202','este es un producto de prueba numero 202',0,1831,2160.58,2009.3394,8,1),(203,'7516864491',24,2,'53131600','producto de prueba 203','este es un producto de prueba numero 203',0,1827,2009.7,1808.73,9,1),(204,'674395236',1,15,'53131600','producto de prueba 204','este es un producto de prueba numero 204',0,2066,2313.92,2105.6672,14,1),(205,'3352056325',25,1,'53131600','producto de prueba 205','este es un producto de prueba numero 205',0,2739,3259.41,3063.8454,9,1),(206,'5984418548',7,8,'53131600','producto de prueba 206','este es un producto de prueba numero 206',0,1951,2341.2,2107.08,12,1),(207,'9435252053',21,6,'53131600','producto de prueba 207','este es un producto de prueba numero 207',0,483,565.11,519.9012,7,1),(208,'1477483364',13,9,'53131600','producto de prueba 208','este es un producto de prueba numero 208',0,143,170.17,154.8547,7,1),(209,'2738341149',12,5,'53131600','producto de prueba 209','este es un producto de prueba numero 209',0,2582,2866.02,2665.3986,15,1),(210,'1002223304',11,13,'53131600','producto de prueba 210','este es un producto de prueba numero 210',0,1090,1209.9,1137.306,13,1),(211,'7125066647',14,13,'53131600','producto de prueba 211','este es un producto de prueba numero 211',0,1582,1866.76,1680.084,12,1),(212,'2262796359',3,1,'53131600','producto de prueba 212','este es un producto de prueba numero 212',0,608,668.8,635.36,7,1),(213,'835185095',6,2,'53131600','producto de prueba 213','este es un producto de prueba numero 213',0,729,860.22,800.0046,6,1),(214,'5963321961',15,11,'53131600','producto de prueba 214','este es un producto de prueba numero 214',0,2667,3200.4,3040.38,7,1),(215,'6355218985',23,4,'53131600','producto de prueba 215','este es un producto de prueba numero 215',0,273,324.87,298.8804,15,1),(216,'1177685202',23,6,'53131600','producto de prueba 216','este es un producto de prueba numero 216',0,428,483.64,440.1124,10,1),(217,'5323846584',12,9,'53131600','producto de prueba 217','este es un producto de prueba numero 217',0,2713,3201.34,3009.2596,12,1),(218,'7221540488',9,15,'53131600','producto de prueba 218','este es un producto de prueba numero 218',0,2111,2469.87,2296.9791,8,1),(219,'4668499546',3,2,'53131600','producto de prueba 219','este es un producto de prueba numero 219',0,101,118.17,111.0798,15,1),(220,'3994424705',2,1,'53131600','producto de prueba 220','este es un producto de prueba numero 220',0,2887,3320.05,3021.2455,8,1),(221,'4996167478',3,3,'53131600','producto de prueba 221','este es un producto de prueba numero 221',0,1416,1670.88,1520.5008,15,1),(222,'2998652244',18,4,'53131600','producto de prueba 222','este es un producto de prueba numero 222',0,270,305.1,280.692,12,1),(223,'7007192322',10,6,'53131600','producto de prueba 223','este es un producto de prueba numero 223',0,1871,2207.78,1987.002,12,1),(224,'452948008',8,14,'53131600','producto de prueba 224','este es un producto de prueba numero 224',0,165,189.75,172.6725,8,1),(225,'6714478941',13,9,'53131600','producto de prueba 225','este es un producto de prueba numero 225',0,2847,3160.17,3002.1615,9,1),(226,'502841134',12,2,'53131600','producto de prueba 226','este es un producto de prueba numero 226',0,1717,1923.04,1749.9664,15,1),(227,'402828740',15,3,'53131600','producto de prueba 227','este es un producto de prueba numero 227',0,363,406.56,369.9696,15,1),(228,'5470538886',21,14,'53131600','producto de prueba 228','este es un producto de prueba numero 228',0,1989,2366.91,2153.8881,13,1),(229,'231543290',16,3,'53131600','producto de prueba 229','este es un producto de prueba numero 229',0,1535,1734.55,1630.477,11,1),(230,'4485236059',1,7,'53131600','producto de prueba 230','este es un producto de prueba numero 230',0,1127,1318.59,1199.9169,14,1),(231,'8996889906',25,1,'53131600','producto de prueba 231','este es un producto de prueba numero 231',0,197,232.46,209.214,8,1),(232,'1825314623',20,13,'53131600','producto de prueba 232','este es un producto de prueba numero 232',0,864,1002.24,952.128,12,1),(233,'5749978884',6,5,'53131600','producto de prueba 233','este es un producto de prueba numero 233',0,224,255.36,229.824,12,1),(234,'5197788845',17,4,'53131600','producto de prueba 234','este es un producto de prueba numero 234',0,1444,1703.92,1550.5672,11,1),(235,'2851625339',11,7,'53131600','producto de prueba 235','este es un producto de prueba numero 235',0,1300,1508,1432.6,12,1),(236,'1501511623',14,10,'53131600','producto de prueba 236','este es un producto de prueba numero 236',0,2215,2635.85,2424.982,7,1),(237,'9688485776',6,14,'53131600','producto de prueba 237','este es un producto de prueba numero 237',0,2678,3213.6,2892.24,11,1),(238,'3104981159',19,11,'53131600','producto de prueba 238','este es un producto de prueba numero 238',0,2568,2901.84,2611.656,10,1),(239,'2672539210',9,12,'53131600','producto de prueba 239','este es un producto de prueba numero 239',0,2055,2383.8,2145.42,13,1),(240,'1862698240',7,12,'53131600','producto de prueba 240','este es un producto de prueba numero 240',0,956,1118.52,1062.594,15,1),(241,'7410780531',9,3,'53131600','producto de prueba 241','este es un producto de prueba numero 241',0,2220,2486.4,2262.624,9,1),(242,'3988178726',19,5,'53131600','producto de prueba 242','este es un producto de prueba numero 242',0,1530,1820.7,1656.837,8,1),(243,'9693168555',3,1,'53131600','producto de prueba 243','este es un producto de prueba numero 243',0,77,87.01,80.0492,6,1),(244,'4735785551',14,5,'53131600','producto de prueba 244','este es un producto de prueba numero 244',0,968,1064.8,968.968,9,1),(245,'1482864958',3,11,'53131600','producto de prueba 245','este es un producto de prueba numero 245',0,2827,3137.97,2918.3121,14,1),(246,'6959296072',3,13,'53131600','producto de prueba 246','este es un producto de prueba numero 246',0,1671,1921.65,1748.7015,15,1),(247,'9822243116',1,9,'53131600','producto de prueba 247','este es un producto de prueba numero 247',0,2276,2594.64,2464.908,10,1),(248,'6489836832',1,2,'53131600','producto de prueba 248','este es un producto de prueba numero 248',0,999,1198.8,1138.86,8,1),(249,'1721992170',3,4,'53131600','producto de prueba 249','este es un producto de prueba numero 249',0,737,825.44,751.1504,6,1),(250,'5840870931',19,3,'53131600','producto de prueba 250','este es un producto de prueba numero 250',0,2025,2288.25,2059.425,15,1),(251,'9378334924',25,8,'53131600','producto de prueba 251','este es un producto de prueba numero 251',0,1552,1722.72,1619.3568,15,1),(252,'397882360',17,2,'53131600','producto de prueba 252','este es un producto de prueba numero 252',0,1756,2019.4,1817.46,14,1),(253,'4484668961',16,6,'53131600','producto de prueba 253','este es un producto de prueba numero 253',0,1164,1315.32,1249.554,7,1),(254,'6968035057',2,5,'53131600','producto de prueba 254','este es un producto de prueba numero 254',0,2037,2342.55,2108.295,13,1),(255,'375496824',4,9,'53131600','producto de prueba 255','este es un producto de prueba numero 255',0,72,79.2,74.448,11,1),(256,'8090842009',17,6,'53131600','producto de prueba 256','este es un producto de prueba numero 256',0,1205,1361.65,1293.5675,10,1),(257,'3876118555',11,7,'53131600','producto de prueba 257','este es un producto de prueba numero 257',0,757,878.12,834.214,10,1),(258,'3552479236',9,10,'53131600','producto de prueba 258','este es un producto de prueba numero 258',0,408,473.28,435.4176,6,1),(259,'8681254623',15,12,'53131600','producto de prueba 259','este es un producto de prueba numero 259',0,1467,1613.7,1452.33,14,1),(260,'6089762758',25,9,'53131600','producto de prueba 260','este es un producto de prueba numero 260',0,1404,1558.44,1433.7648,9,1),(261,'9295347968',22,14,'53131600','producto de prueba 261','este es un producto de prueba numero 261',0,1722,2066.4,1901.088,9,1),(262,'2104748323',4,14,'53131600','producto de prueba 262','este es un producto de prueba numero 262',0,1050,1249.5,1137.045,6,1),(263,'7933577238',3,7,'53131600','producto de prueba 263','este es un producto de prueba numero 263',0,2573,3061.87,2878.1578,13,1),(264,'893934278',2,6,'53131600','producto de prueba 264','este es un producto de prueba numero 264',0,1820,2093,1883.7,14,1),(265,'9536259354',11,1,'53131600','producto de prueba 265','este es un producto de prueba numero 265',0,1540,1771,1664.74,13,1),(266,'7069368558',15,10,'53131600','producto de prueba 266','este es un producto de prueba numero 266',0,1551,1706.1,1620.795,13,1),(267,'700837116',1,7,'53131600','producto de prueba 267','este es un producto de prueba numero 267',0,646,775.2,720.936,12,1),(268,'7792127999',23,3,'53131600','producto de prueba 268','este es un producto de prueba numero 268',0,899,1051.83,978.2019,6,1),(269,'7398772354',1,10,'53131600','producto de prueba 269','este es un producto de prueba numero 269',0,2244,2490.84,2291.5728,6,1),(274,'124659175',11,8,'53131600','SHAMPOO CON ACONDICIONADOR','UN SHAMPOOO CON ACONDICIONADOR IDIONEO PARA CABELLO DAÑADO O TEÑIDO',0,78.15,118.93,110.12,5,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria_producto`
--

LOCK TABLES `categoria_producto` WRITE;
/*!40000 ALTER TABLE `categoria_producto` DISABLE KEYS */;
INSERT INTO `categoria_producto` VALUES (1,'Ropa','Vestimenta en general',1),(2,'Electrónica','Productos electrónicos',1),(3,'Hogar y Jardín','Artículos para el hogar y jardín',1),(4,'Deportes','Artículos deportivos',1),(5,'Alimentación','Productos alimenticios',1),(6,'Muebles','Muebles y decoración para el hogar',1),(7,'Joyería','Joyas y accesorios',1),(8,'Belleza','Productos de belleza',1),(9,'Calzado','Zapatos y calzado',1),(10,'Libros','Libros y literatura',1),(11,'Salud','Productos de salud',1),(12,'Electrodomésticos','Electrodomésticos para el hogar',1),(13,'Informática','Equipos y accesorios de informática',1),(14,'Música','Instrumentos musicales y música',1),(15,'Cine','Películas y entretenimiento',1),(16,'Arte','Arte y artesanía',1),(17,'Infantil','Productos para niños',1),(18,'Automóviles','Accesorios y piezas de automóviles',1),(19,'Electrodomésticos','Electrodomésticos para el hogar',1),(20,'Jardinería','Suministros de jardinería',1),(21,'Electrodomésticos','Electrodomésticos para el hogar',1),(22,'Moda','Artículos de moda y ropa',1),(23,'Electrónica de Consumo','Productos electrónicos de consumo',1),(24,'Deportes y Aire Libre','Equipamiento deportivo y actividades al aire libre',1),(25,'Salud y Belleza','Productos de salud y belleza',1),(26,'Herramientas','Suministros y herramientas',1),(27,'Electrodomésticos de Cocina','Electrodomésticos de cocina y utensilios',1),(28,'Animales','Productos para mascotas y animales',1),(29,'Arte y Manualidades','Materiales y suministros para arte y manualidades',1),(30,'Cine y Entretenimiento','Películas y entretenimiento',1),(31,'Bebés y Niños','Productos para bebés y niños',1),(32,'Libros y Literatura','Libros y literatura en general',1),(33,'Calzado y Accesorios','Zapatos',1),(34,'Joyería y Relojes','Joyería',1),(35,'Tecnología','Productos y dispositivos tecnológicos',1),(36,'Hogar y Cocina','Artículos para el hogar y utensilios de cocina',1),(37,'Instrumentos Musicales','Instrumentos musicales y accesorios',1),(38,'Juegos y Juguetes','Juegos',1),(39,'Alimentos y Bebidas','Productos alimenticios y bebidas',1),(40,'Oficina y Papelería','Suministros de oficina y papelería',1),(41,'VideoJuegos','Categoría destinada a los videojuegos tanto en digital como en físico',1),(42,'Instrumentos Mecanicos','Herramientas y herrajes',1);
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
  `activo` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `id_cuenta_contable` (`id_cuenta_contable`),
  CONSTRAINT `id_cuenta_contable` FOREIGN KEY (`id_cuenta_contable`) REFERENCES `sub_cuentas_tercer_nivel` (`id_cuenta`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'ARASAS',45,'MOSTRADOR','adsfa','1997-07-05','correo_pr_1@hotmail.com','Chiapas','Tuxtla Gutierrez','AV.2 NORTE ORIENTE S/N-225','29072',1),(2,'TRRTJR',46,'LOURDES LOPEZ GUILLEN','wrqer','1988-04-27','correo_pr_2@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. TACANA MANZANA 29 #37','29031',1),(3,'SADVXCB',47,'MARIA ERNESTINA AGUSTIN PEREZ','asa','1981-03-03','correo_pr_3@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. 5A SUR ORIENTE 654-B','29091',1),(4,'GHLHJ',48,'MARIA CONCEPCION CHULIN GORDIL','qewr','1996-05-24','correo_pr_4@hotmail.com','Chiapas','Tuxtla Gutierrez','CALLE 5A OTE. NORTE','29054',0),(5,'QEWRF',49,'LUCIA ELIZABETH LOPEZ GONZALEZ','adfs','1996-07-24','correo_pr_5@hotmail.com','Chiapas','Tuxtla Gutierrez','9 NORTE ENTRE 6 Y 7 ORIENTE','29057',1),(6,'QQSCCAA',50,'ANA YANSI CRUZ GARCIA','qwer','1988-04-02','correo_pr_6@hotmail.com','Chiapas','Tuxtla Gutierrez','5A ORIENTE NORTE ENTRE 1A Y 2A NORTE','29090',1),(7,'UIÑHJKL',51,'MARIA ENCARNACION SARMIENTO SA','zcxv','1984-06-13','correo_pr_7@hotmail.com','Chiapas','Tuxtla Gutierrez','2 ORIENTE SUR #217 ENTRE 1 Y 2 SUR','29047',1),(8,'ACSDFVSC',52,'MARIA BEININA GOMEZ ALVAREZ','qer','1988-01-05','correo_pr_8@hotmail.com','Chiapas','Tuxtla Gutierrez','4 OTE SUR ENTRE 4 Y 5 SUR #538','29029',1),(9,'SDFVXCAAS',53,'LUZ MARIA MORALES TORRES','asdf','1994-07-28','correo_pr_9@hotmail.com','Chiapas','Tuxtla Gutierrez','AV OLIVO SUR 112-A','29080',1),(10,'AXCXCV',54,'VERONICA LOPEZ CRUZ','wetwtr','1995-06-24','correo_pr_10@hotmail.com','Chiapas','Tuxtla Gutierrez','7A ORIENTE #451 ENTRE 3A Y 4A SUR','29061',1),(11,'ASERASD',55,'GUADALUPE NURIULU GORDILLO','ury','1980-04-29','correo_pr_11@hotmail.com','Chiapas','Tuxtla Gutierrez','MNZ.45 LOT.12','29022',1),(12,'ADVSASD',56,'WALTER DILTIEV SOTO MORALES','kju','1999-03-15','correo_pr_12@hotmail.com','Chiapas','Tuxtla Gutierrez','AV.2 NORTE ORIENTE S/N-225','29044',1),(13,'ASFWASFE',57,'MUNICIPIO DE VENUSTIANO CARRAN','sdfs','1984-06-01','correo_pr_13@hotmail.com','Chiapas','Carranza','CONOCIDO PALACIO MUNICIAPAL','29052',1),(14,'WRTWEFAS',58,'BEATRIZ ADRIANA PENAGOS GONZAL','rtyutr','1982-05-28','correo_pr_14@hotmail.com','Chiapas','San Cristobal','3A CALLE ORIENTE SUR #350-A','29056',1),(15,'WEFADE',59,'YARENI HERNANDEZ GARCIA','zcxv','1982-10-01','correo_pr_15@hotmail.com','Chiapas','Chilon','AV. TACANA MANZANA 29 #37','29025',1),(16,'DVSSSVS',60,'METALLICA','wer','1988-08-21','correo_pr_16@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. 5A SUR ORIENTE 654-B','29009',0),(17,'AWETWWD',61,'FABIOLA INDILI CUNDAPI','zxcv','1988-05-05','correo_pr_17@hotmail.com','Chiapas','Tuxtla Gutierrez','CALLE 5A OTE. NORTE','29039',1),(18,'WEEFFEW',62,'ELIZA AGUILAR','ewt','1990-05-18','correo_pr_18@hotmail.com','Chiapas','Tuxtla Gutierrez','9 NORTE ENTRE 6 Y 7 ORIENTE','29047',1),(19,'ADSVSAC',63,'PAOLA LIZETH GALLEGOS DEL CARP','xzcv','1986-10-27','correo_pr_19@hotmail.com','Chiapas','Tuxtla Gutierrez','5A ORIENTE NORTE ENTRE 1A Y 2A NORTE','29016',1),(20,'ASFSASD',64,'DANIA MARGARITA GOMEZ HERNANDE','ewt','1983-05-29','correo_pr_20@hotmail.com','Chiapas','Tuxtla Gutierrez','2 ORIENTE SUR #217 ENTRE 1 Y 2 SUR','29082',1),(23,'XAAXAXXAXA',116,'XATAKA CLIENT','XTK','1998-10-12','fakemail_mail@mail.com','CHIAPAS','TUXTLA GUTIERREZ','5A NORTE ORIENTE #459','29000',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,6,'PEJU920506ABC','PEJU920506HDFRRN07','Juan Pérez','Juan','1981-01-19','juanfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 5 de Mayo #123','29445','xYz#12Ab',1),(2,1,'GAMA880213DEF','GAMA880213MHDFRRN0','María García','María','1986-07-01','maríafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Juárez #456','29465','P@ssw0rd',1),(3,3,'LOCA780704GHI','LOCA780704MHDFRL07','Carlos López','Carlos','1988-01-23','carlosfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Belisario Domínguez #789','29198','Qwerty!23',1),(4,3,'MAAA950312JKL','MAAA950312MHDFRN02','Ana Martínez','Ana','1989-09-21','anafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Central #1010','29146','Summer*99',1),(5,5,'ROLU890522MNO','ROLU890522MHDFDS03','Luis Rodríguez','Luis','1983-09-13','luisfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Hidalgo #234','29365','Secure$456',1),(6,3,'FELO930608PQR','FELO930608MHDFNR00','Laura Fernández','Laura','1995-06-30','laurafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Prolongación 16 de Septiembre #567','29252','Orange$Tree67',1),(7,4,'GOAL900715STU','GOAL900715HDFNNL08','Alejandro González','Alejandro','1982-12-09','alejandrofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 20 de Noviembre #890','29316','1Lov3Cats',1),(8,2,'DISO941118VWX','DISO941118MHDFXF09','Sofía Díaz','Sofía','1994-11-16','sofíafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calzada Sumidero #1112','29410','C0ffee&Milk',0),(9,5,'SAMA861002YZA','SAMA861002MHDFNH04','Manuel Sánchez','Manuel','1984-10-02','manuelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Los Laguitos #1314','29310','Chocol8!Chip',1),(10,2,'RAPA861220BCD','RAPA861220MHDFMR05','Patricia Ramírez','Patricia','1995-10-23','patriciafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Constitución #1516','29066','P@rkRang3r',1),(11,6,'TOMI900429EFG','TOMI900429MHDFSL06','Miguel Torres','Miguel','1991-05-27','miguelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Cuauhtémoc #1718','29267','Wint3rFrost$',1),(12,2,'FLOA880917HIJ','FLOA880917MHDFRN07','Andrea Flores','Andrea','1989-02-14','andreafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Allende #1920','29119','P@ssionFl0w3r',1),(13,4,'HEDO960725KLM','HEDO960725MHDFDN08','Daniel Hernández','Daniel','1993-01-25','danielfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Ángel Albino Corzo #2122','29097','M0onlight$ky',1),(14,5,'VACA890314NOP','VACA890314MHDFRM09','Carmen Vargas','Carmen','1991-09-26','carmenfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 15 de Julio #2324','29042','B3autiful*Sun',1),(15,4,'MEOJ940610QRS','MEOJ940610MHDFND10','Eduardo Mendoza','Eduardo','1982-04-01','eduardofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Revolución #2526','29439','S0larSyst3m!',1),(16,2,'CUGA950826TUV','CUGA950826MHDFNB11','Gabriela Cruz','Gabriela','1993-01-10','gabrielafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Juventud #2728','29413','Starry*Night',1),(17,2,'MOMJ891121WXY','MOMJ891121MHDFNR12','Javier Morales','Javier','1984-10-13','javierfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Miguel Alemán #2930','29144','Danc1ngWav3s',1),(18,5,'RIAR930601ZAB','RIAR930601MHDFQL13','Raquel Ríos','Raquel','1983-08-02','raquelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Reforma #3132','29221','R@inbow@fterRai',1),(19,2,'ORFF871214BCD','ORFF871214MHDFNN14','Francisco Ortega','Francisco','1994-09-18','franciscofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Doctor Belisario Domínguez #3334','29496','Sparkl1ngDiam0n',1),(20,3,'NUNN900225EFG','NUNN900225MHDFXT15','Natalia Núñez','Natalia','1991-07-15','nataliafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Central Poniente #3536','29209','F0restWhisper$',1),(21,4,'SIDI910517HJK','SIDI910517MHDFLG16','Diego Silva','Diego','1987-05-15','diegofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 2 Norte #3738','29133','S@ndyB3@ch!',1),(22,1,'AGVV930811PLM','AGVV930811MHDFGT17','Valentina Aguilar','Valentina','1983-06-26','valentinafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 4 Oriente #3930','29048','M0unt@inT0p',1),(23,6,'MORR921010KJH','MORR921010MHDFDC18','Ricardo Mora','Ricardo','1982-05-30','ricardofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Laguitos #4132','29395','SecretG@rden$',1),(24,6,'PECM940731QWE','PECM940731MHDFML19','Camila Peña','Camila','1992-04-01','camilafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 6 Sur #4334','29146','H@ppyH3arts',1),(25,3,'MONG900416RFG','MONG900416MHDFNL20','Guillermo Montoya','Guillermo','1989-04-25','guillermofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 8 Poniente #4536','29482','CoolBreez3$',1),(26,3,'RIMA910218TGB','RIMA910218MHDFTS21','Martina Rivas','Martina','1997-02-25','martinafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Convivencia #4738','29295','S1lv3rLin1ngs',1),(27,2,'CASA960511YHN','CASA960511MHDFTR22','Adrián Castillo','Adrián','1989-06-18','adriánfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 10 Oriente #4930','29148','Myst1cM00n',1),(28,1,'PARR910713GBV','PARR910713MHDFNS23','Renata Paredes','Renata','1981-06-21','renatafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 12 Sur #4132','29027','H3althyL1f3$',1),(29,6,'GUSE921215CVB','GUSE921215MHDFBT24','Sebastián Guzmán','Sebastián','1988-08-10','sebastiánfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 14 Norte #4334','29283','D3epBlu3Sea',1),(30,2,'PEIB930320SDX','PEIB930320MHDFRX25','Isabella Peralta','Isabella','1994-10-16','isabellafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 16 Oriente #4536','29323','Fr3shStart!',1),(31,4,'VAJJ900504KLS','VAJJ900504MHDFXS26','José Valenzuela','José','1981-09-15','joséfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 18 Sur #4738','29100','Laugh@Lot$',1),(32,3,'ROAA870827LMD','ROAA870827MHDFLN27','Antonella Rojas','Antonella','1986-01-08','antonellafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 20 Norte #4930','29130','Adventur3T1m3',1),(33,1,'COAA910623LKP','COAA910623MHDFNR28','Andrés Cordero','Andrés','1982-12-26','andrésfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 22 Oriente #5132','29034','H0lid@y@Dream',1),(34,2,'SAVV940712LPQ','SAVV940712MHDFTC29','Victoria Salazar','Victoria','1982-05-28','victoriafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 24 Sur #5334','29294','Playful*Smil3',1),(35,1,'BAIN930802EJK','BAIN930802MHDFGN30','Ignacio Bravo','Ignacio','1996-06-08','ignaciofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 26 Norte #5536','29206','B3autifulM3m0ri',1),(36,1,'ACOM870101JKH','ACOM870101MHDFVN31','Miranda Acosta','Miranda','1993-08-15','mirandafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 28 Oriente #5738','29040','Wish3s&Candl3s',1),(37,2,'FUAM900510OPW','FUAM900510MHDFRT32','Matías Fuentes','Matías','1994-07-06','matíasfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Cultura #5930','29387','Creat1veM1nd$',1),(38,6,'OREL950315TGY','OREL950315MHDFRT33','Elena Orellana','Elena','1992-03-20','elenafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 30 Sur #5132','29203','B3Insp1r3d!',1),(39,2,'ESBM910717GHJ','ESBM910717MHDFNH34','Benjamín Espinoza','Benjamín','1990-11-05','benjamínfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 32 Norte #5334','29393','P0sitiveV1bes',1),(40,2,'LEVR920321FDS','LEVR920321MHDFNN35','Renée Leiva','Renée','1996-04-30','renéefakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 34 Oriente #5536','29141','En3rgetic*Day',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2148 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `existencia_x_sucursal`
--

LOCK TABLES `existencia_x_sucursal` WRITE;
/*!40000 ALTER TABLE `existencia_x_sucursal` DISABLE KEYS */;
INSERT INTO `existencia_x_sucursal` VALUES (1,1,1,186),(2,2,1,162),(3,3,1,136),(4,4,1,133),(5,5,1,104),(6,6,1,63),(7,7,1,199),(8,8,1,173),(9,9,1,30),(10,10,1,177),(11,11,1,70),(12,12,1,71),(13,13,1,35),(14,14,1,5),(15,15,1,126),(16,16,1,196),(17,17,1,144),(18,18,1,107),(19,19,1,45),(20,20,1,135),(21,21,1,163),(22,22,1,123),(23,23,1,7),(24,24,1,32),(25,25,1,169),(26,26,1,90),(27,27,1,51),(28,28,1,147),(29,29,1,28),(30,30,1,49),(31,31,1,138),(32,32,1,40),(33,33,1,157),(34,34,1,6),(35,35,1,180),(36,36,1,180),(37,37,1,6),(38,38,1,114),(39,39,1,177),(40,40,1,147),(41,41,1,123),(42,42,1,32),(43,43,1,40),(44,44,1,123),(45,45,1,97),(46,46,1,86),(47,47,1,23),(48,48,1,162),(49,49,1,32),(50,50,1,99),(51,51,1,126),(52,52,1,102),(53,53,1,182),(54,54,1,6),(55,55,1,162),(56,56,1,199),(57,57,1,0),(58,58,1,163),(59,59,1,9),(60,60,1,134),(61,61,1,7),(62,62,1,181),(63,63,1,180),(64,64,1,199),(65,65,1,3),(66,66,1,105),(67,67,1,110),(68,68,1,86),(69,69,1,124),(70,70,1,167),(71,71,1,34),(72,72,1,45),(73,73,1,8),(74,74,1,60),(75,75,1,49),(76,76,1,194),(77,77,1,120),(78,78,1,36),(79,79,1,199),(80,80,1,60),(81,81,1,194),(82,82,1,109),(83,83,1,35),(84,84,1,15),(85,85,1,116),(86,86,1,1),(87,87,1,180),(88,88,1,191),(89,89,1,104),(90,90,1,50),(91,91,1,108),(92,92,1,158),(93,93,1,63),(94,94,1,39),(95,95,1,25),(96,96,1,13),(97,97,1,35),(98,98,1,187),(99,99,1,132),(100,100,1,107),(101,101,1,2),(102,102,1,163),(103,103,1,121),(104,104,1,180),(105,105,1,112),(106,106,1,189),(107,107,1,111),(108,108,1,44),(109,109,1,121),(110,110,1,163),(111,111,1,150),(112,112,1,142),(113,113,1,180),(114,114,1,78),(115,115,1,155),(116,116,1,181),(117,117,1,10),(118,118,1,170),(119,119,1,101),(120,120,1,157),(121,121,1,26),(122,122,1,26),(123,123,1,192),(124,124,1,157),(125,125,1,158),(126,126,1,119),(127,127,1,185),(128,128,1,16),(129,129,1,142),(130,130,1,181),(131,131,1,161),(132,132,1,26),(133,133,1,191),(134,134,1,188),(135,135,1,107),(136,136,1,114),(137,137,1,161),(138,138,1,155),(139,139,1,64),(140,140,1,100),(141,141,1,184),(142,142,1,37),(143,143,1,70),(144,144,1,68),(145,145,1,91),(146,146,1,126),(147,147,1,82),(148,148,1,102),(149,149,1,52),(150,150,1,11),(151,151,1,55),(152,152,1,36),(153,153,1,28),(154,154,1,144),(155,155,1,53),(156,156,1,165),(157,157,1,97),(158,158,1,133),(159,159,1,190),(160,160,1,20),(161,161,1,59),(162,162,1,49),(163,163,1,29),(164,164,1,144),(165,165,1,190),(166,166,1,36),(167,167,1,87),(168,168,1,184),(169,169,1,190),(170,170,1,158),(171,171,1,127),(172,172,1,8),(173,173,1,100),(174,174,1,24),(175,175,1,157),(176,176,1,14),(177,177,1,159),(178,178,1,13),(179,179,1,161),(180,180,1,152),(181,181,1,172),(182,182,1,16),(183,183,1,164),(184,184,1,39),(185,185,1,106),(186,186,1,165),(187,187,1,176),(188,188,1,6),(189,189,1,11),(190,190,1,154),(191,191,1,195),(192,192,1,95),(193,193,1,85),(194,194,1,121),(195,195,1,31),(196,196,1,105),(197,197,1,165),(198,198,1,85),(199,199,1,37),(200,200,1,137),(201,201,1,166),(202,202,1,25),(203,203,1,55),(204,204,1,39),(205,205,1,49),(206,206,1,160),(207,207,1,173),(208,208,1,54),(209,209,1,177),(210,210,1,3),(211,211,1,149),(212,212,1,147),(213,213,1,189),(214,214,1,120),(215,215,1,194),(216,216,1,19),(217,217,1,124),(218,218,1,21),(219,219,1,76),(220,220,1,149),(221,221,1,186),(222,222,1,152),(223,223,1,40),(224,224,1,44),(225,225,1,31),(226,226,1,61),(227,227,1,0),(228,228,1,171),(229,229,1,130),(230,230,1,175),(231,231,1,84),(232,232,1,91),(233,233,1,188),(234,234,1,43),(235,235,1,54),(236,236,1,65),(237,237,1,161),(238,238,1,155),(239,239,1,160),(240,240,1,185),(241,241,1,58),(242,242,1,115),(243,243,1,74),(244,244,1,191),(245,245,1,9),(246,246,1,20),(247,247,1,169),(248,248,1,44),(249,249,1,12),(250,250,1,37),(251,251,1,36),(252,252,1,104),(253,253,1,89),(254,254,1,20),(255,255,1,153),(256,256,1,56),(257,257,1,40),(258,258,1,28),(259,259,1,27),(260,260,1,179),(261,261,1,49),(262,262,1,138),(263,263,1,182),(264,264,1,159),(265,265,1,9),(266,266,1,100),(267,267,1,111),(268,268,1,50),(269,269,1,66),(270,1,2,177),(271,2,2,117),(272,3,2,90),(273,4,2,103),(274,5,2,29),(275,6,2,65),(276,7,2,119),(277,8,2,167),(278,9,2,173),(279,10,2,68),(280,11,2,58),(281,12,2,96),(282,13,2,92),(283,14,2,15),(284,15,2,160),(285,16,2,133),(286,17,2,150),(287,18,2,185),(288,19,2,61),(289,20,2,5),(290,21,2,103),(291,22,2,144),(292,23,2,92),(293,24,2,44),(294,25,2,83),(295,26,2,172),(296,27,2,183),(297,28,2,132),(298,29,2,193),(299,30,2,37),(300,31,2,137),(301,32,2,48),(302,33,2,136),(303,34,2,171),(304,35,2,87),(305,36,2,48),(306,37,2,28),(307,38,2,0),(308,39,2,18),(309,40,2,192),(310,41,2,74),(311,42,2,189),(312,43,2,171),(313,44,2,48),(314,45,2,132),(315,46,2,21),(316,47,2,15),(317,48,2,59),(318,49,2,40),(319,50,2,114),(320,51,2,115),(321,52,2,70),(322,53,2,53),(323,54,2,83),(324,55,2,157),(325,56,2,48),(326,57,2,23),(327,58,2,199),(328,59,2,142),(329,60,2,20),(330,61,2,144),(331,62,2,60),(332,63,2,184),(333,64,2,168),(334,65,2,4),(335,66,2,109),(336,67,2,172),(337,68,2,181),(338,69,2,89),(339,70,2,127),(340,71,2,187),(341,72,2,69),(342,73,2,180),(343,74,2,28),(344,75,2,172),(345,76,2,90),(346,77,2,24),(347,78,2,140),(348,79,2,11),(349,80,2,72),(350,81,2,64),(351,82,2,187),(352,83,2,99),(353,84,2,174),(354,85,2,197),(355,86,2,196),(356,87,2,119),(357,88,2,63),(358,89,2,196),(359,90,2,165),(360,91,2,152),(361,92,2,159),(362,93,2,157),(363,94,2,61),(364,95,2,185),(365,96,2,28),(366,97,2,77),(367,98,2,98),(368,99,2,57),(369,100,2,2),(370,101,2,140),(371,102,2,102),(372,103,2,94),(373,104,2,68),(374,105,2,147),(375,106,2,112),(376,107,2,122),(377,108,2,145),(378,109,2,75),(379,110,2,180),(380,111,2,58),(381,112,2,108),(382,113,2,162),(383,114,2,136),(384,115,2,198),(385,116,2,32),(386,117,2,86),(387,118,2,121),(388,119,2,9),(389,120,2,166),(390,121,2,117),(391,122,2,107),(392,123,2,135),(393,124,2,195),(394,125,2,9),(395,126,2,94),(396,127,2,16),(397,128,2,172),(398,129,2,199),(399,130,2,41),(400,131,2,123),(401,132,2,68),(402,133,2,163),(403,134,2,44),(404,135,2,15),(405,136,2,176),(406,137,2,137),(407,138,2,175),(408,139,2,182),(409,140,2,146),(410,141,2,8),(411,142,2,22),(412,143,2,160),(413,144,2,154),(414,145,2,87),(415,146,2,64),(416,147,2,144),(417,148,2,73),(418,149,2,129),(419,150,2,192),(420,151,2,102),(421,152,2,38),(422,153,2,40),(423,154,2,50),(424,155,2,171),(425,156,2,1),(426,157,2,62),(427,158,2,13),(428,159,2,158),(429,160,2,28),(430,161,2,62),(431,162,2,120),(432,163,2,86),(433,164,2,10),(434,165,2,38),(435,166,2,113),(436,167,2,103),(437,168,2,71),(438,169,2,22),(439,170,2,169),(440,171,2,2),(441,172,2,44),(442,173,2,17),(443,174,2,106),(444,175,2,185),(445,176,2,185),(446,177,2,78),(447,178,2,160),(448,179,2,80),(449,180,2,57),(450,181,2,70),(451,182,2,120),(452,183,2,36),(453,184,2,159),(454,185,2,113),(455,186,2,127),(456,187,2,90),(457,188,2,197),(458,189,2,191),(459,190,2,129),(460,191,2,56),(461,192,2,94),(462,193,2,167),(463,194,2,98),(464,195,2,36),(465,196,2,111),(466,197,2,36),(467,198,2,48),(468,199,2,61),(469,200,2,139),(470,201,2,87),(471,202,2,77),(472,203,2,33),(473,204,2,88),(474,205,2,148),(475,206,2,91),(476,207,2,168),(477,208,2,32),(478,209,2,179),(479,210,2,60),(480,211,2,67),(481,212,2,44),(482,213,2,100),(483,214,2,90),(484,215,2,70),(485,216,2,5),(486,217,2,143),(487,218,2,192),(488,219,2,35),(489,220,2,28),(490,221,2,169),(491,222,2,142),(492,223,2,18),(493,224,2,100),(494,225,2,70),(495,226,2,139),(496,227,2,121),(497,228,2,25),(498,229,2,197),(499,230,2,107),(500,231,2,161),(501,232,2,154),(502,233,2,13),(503,234,2,181),(504,235,2,119),(505,236,2,40),(506,237,2,192),(507,238,2,124),(508,239,2,136),(509,240,2,153),(510,241,2,121),(511,242,2,58),(512,243,2,58),(513,244,2,101),(514,245,2,132),(515,246,2,148),(516,247,2,91),(517,248,2,63),(518,249,2,121),(519,250,2,163),(520,251,2,89),(521,252,2,183),(522,253,2,42),(523,254,2,137),(524,255,2,136),(525,256,2,69),(526,257,2,133),(527,258,2,185),(528,259,2,72),(529,260,2,172),(530,261,2,116),(531,262,2,92),(532,263,2,2),(533,264,2,161),(534,265,2,74),(535,266,2,132),(536,267,2,159),(537,268,2,139),(538,269,2,182),(539,1,3,88),(540,2,3,79),(541,3,3,194),(542,4,3,3),(543,5,3,105),(544,6,3,28),(545,7,3,34),(546,8,3,77),(547,9,3,103),(548,10,3,138),(549,11,3,138),(550,12,3,165),(551,13,3,59),(552,14,3,117),(553,15,3,33),(554,16,3,154),(555,17,3,55),(556,18,3,33),(557,19,3,37),(558,20,3,127),(559,21,3,53),(560,22,3,142),(561,23,3,65),(562,24,3,17),(563,25,3,14),(564,26,3,24),(565,27,3,74),(566,28,3,87),(567,29,3,47),(568,30,3,59),(569,31,3,39),(570,32,3,6),(571,33,3,40),(572,34,3,77),(573,35,3,39),(574,36,3,114),(575,37,3,197),(576,38,3,139),(577,39,3,119),(578,40,3,26),(579,41,3,41),(580,42,3,47),(581,43,3,103),(582,44,3,89),(583,45,3,153),(584,46,3,166),(585,47,3,15),(586,48,3,197),(587,49,3,49),(588,50,3,51),(589,51,3,163),(590,52,3,177),(591,53,3,161),(592,54,3,193),(593,55,3,167),(594,56,3,195),(595,57,3,85),(596,58,3,199),(597,59,3,61),(598,60,3,197),(599,61,3,50),(600,62,3,156),(601,63,3,54),(602,64,3,122),(603,65,3,59),(604,66,3,11),(605,67,3,140),(606,68,3,55),(607,69,3,44),(608,70,3,178),(609,71,3,33),(610,72,3,156),(611,73,3,10),(612,74,3,31),(613,75,3,77),(614,76,3,39),(615,77,3,113),(616,78,3,47),(617,79,3,180),(618,80,3,186),(619,81,3,147),(620,82,3,21),(621,83,3,113),(622,84,3,71),(623,85,3,68),(624,86,3,168),(625,87,3,48),(626,88,3,120),(627,89,3,35),(628,90,3,49),(629,91,3,58),(630,92,3,43),(631,93,3,171),(632,94,3,109),(633,95,3,142),(634,96,3,167),(635,97,3,94),(636,98,3,17),(637,99,3,137),(638,100,3,16),(639,101,3,83),(640,102,3,46),(641,103,3,148),(642,104,3,19),(643,105,3,137),(644,106,3,80),(645,107,3,182),(646,108,3,181),(647,109,3,198),(648,110,3,164),(649,111,3,25),(650,112,3,161),(651,113,3,100),(652,114,3,57),(653,115,3,111),(654,116,3,188),(655,117,3,56),(656,118,3,116),(657,119,3,191),(658,120,3,137),(659,121,3,24),(660,122,3,132),(661,123,3,140),(662,124,3,94),(663,125,3,91),(664,126,3,11),(665,127,3,140),(666,128,3,42),(667,129,3,34),(668,130,3,126),(669,131,3,177),(670,132,3,161),(671,133,3,130),(672,134,3,129),(673,135,3,176),(674,136,3,55),(675,137,3,15),(676,138,3,114),(677,139,3,123),(678,140,3,44),(679,141,3,145),(680,142,3,41),(681,143,3,51),(682,144,3,124),(683,145,3,104),(684,146,3,24),(685,147,3,83),(686,148,3,173),(687,149,3,170),(688,150,3,167),(689,151,3,84),(690,152,3,97),(691,153,3,22),(692,154,3,9),(693,155,3,109),(694,156,3,132),(695,157,3,71),(696,158,3,186),(697,159,3,170),(698,160,3,137),(699,161,3,136),(700,162,3,196),(701,163,3,143),(702,164,3,175),(703,165,3,4),(704,166,3,186),(705,167,3,93),(706,168,3,137),(707,169,3,129),(708,170,3,17),(709,171,3,89),(710,172,3,4),(711,173,3,186),(712,174,3,52),(713,175,3,137),(714,176,3,5),(715,177,3,83),(716,178,3,92),(717,179,3,172),(718,180,3,92),(719,181,3,124),(720,182,3,28),(721,183,3,12),(722,184,3,41),(723,185,3,134),(724,186,3,190),(725,187,3,143),(726,188,3,122),(727,189,3,149),(728,190,3,158),(729,191,3,95),(730,192,3,182),(731,193,3,3),(732,194,3,41),(733,195,3,70),(734,196,3,136),(735,197,3,95),(736,198,3,62),(737,199,3,127),(738,200,3,196),(739,201,3,20),(740,202,3,79),(741,203,3,70),(742,204,3,146),(743,205,3,28),(744,206,3,95),(745,207,3,86),(746,208,3,142),(747,209,3,111),(748,210,3,179),(749,211,3,162),(750,212,3,88),(751,213,3,168),(752,214,3,36),(753,215,3,53),(754,216,3,26),(755,217,3,15),(756,218,3,117),(757,219,3,48),(758,220,3,29),(759,221,3,142),(760,222,3,43),(761,223,3,109),(762,224,3,6),(763,225,3,78),(764,226,3,89),(765,227,3,184),(766,228,3,163),(767,229,3,25),(768,230,3,140),(769,231,3,58),(770,232,3,138),(771,233,3,55),(772,234,3,111),(773,235,3,147),(774,236,3,127),(775,237,3,21),(776,238,3,81),(777,239,3,185),(778,240,3,127),(779,241,3,134),(780,242,3,126),(781,243,3,68),(782,244,3,134),(783,245,3,180),(784,246,3,172),(785,247,3,12),(786,248,3,15),(787,249,3,77),(788,250,3,1),(789,251,3,36),(790,252,3,6),(791,253,3,23),(792,254,3,69),(793,255,3,4),(794,256,3,36),(795,257,3,65),(796,258,3,139),(797,259,3,139),(798,260,3,63),(799,261,3,130),(800,262,3,155),(801,263,3,109),(802,264,3,133),(803,265,3,185),(804,266,3,2),(805,267,3,157),(806,268,3,1),(807,269,3,118),(808,1,4,14),(809,2,4,199),(810,3,4,69),(811,4,4,152),(812,5,4,158),(813,6,4,151),(814,7,4,23),(815,8,4,166),(816,9,4,191),(817,10,4,60),(818,11,4,110),(819,12,4,86),(820,13,4,191),(821,14,4,12),(822,15,4,78),(823,16,4,109),(824,17,4,173),(825,18,4,72),(826,19,4,102),(827,20,4,161),(828,21,4,26),(829,22,4,49),(830,23,4,193),(831,24,4,184),(832,25,4,117),(833,26,4,123),(834,27,4,129),(835,28,4,166),(836,29,4,142),(837,30,4,155),(838,31,4,198),(839,32,4,37),(840,33,4,149),(841,34,4,72),(842,35,4,86),(843,36,4,46),(844,37,4,84),(845,38,4,115),(846,39,4,106),(847,40,4,72),(848,41,4,1),(849,42,4,101),(850,43,4,145),(851,44,4,30),(852,45,4,73),(853,46,4,19),(854,47,4,22),(855,48,4,79),(856,49,4,117),(857,50,4,71),(858,51,4,99),(859,52,4,55),(860,53,4,168),(861,54,4,99),(862,55,4,104),(863,56,4,48),(864,57,4,22),(865,58,4,100),(866,59,4,118),(867,60,4,62),(868,61,4,115),(869,62,4,12),(870,63,4,170),(871,64,4,142),(872,65,4,118),(873,66,4,175),(874,67,4,13),(875,68,4,184),(876,69,4,9),(877,70,4,98),(878,71,4,12),(879,72,4,186),(880,73,4,148),(881,74,4,35),(882,75,4,41),(883,76,4,15),(884,77,4,152),(885,78,4,41),(886,79,4,167),(887,80,4,63),(888,81,4,126),(889,82,4,71),(890,83,4,79),(891,84,4,3),(892,85,4,2),(893,86,4,95),(894,87,4,196),(895,88,4,138),(896,89,4,113),(897,90,4,0),(898,91,4,142),(899,92,4,78),(900,93,4,27),(901,94,4,33),(902,95,4,65),(903,96,4,120),(904,97,4,98),(905,98,4,164),(906,99,4,37),(907,100,4,57),(908,101,4,29),(909,102,4,165),(910,103,4,79),(911,104,4,68),(912,105,4,43),(913,106,4,77),(914,107,4,172),(915,108,4,197),(916,109,4,26),(917,110,4,167),(918,111,4,73),(919,112,4,55),(920,113,4,3),(921,114,4,101),(922,115,4,41),(923,116,4,94),(924,117,4,196),(925,118,4,115),(926,119,4,91),(927,120,4,176),(928,121,4,181),(929,122,4,186),(930,123,4,16),(931,124,4,32),(932,125,4,125),(933,126,4,170),(934,127,4,5),(935,128,4,188),(936,129,4,130),(937,130,4,9),(938,131,4,41),(939,132,4,45),(940,133,4,35),(941,134,4,61),(942,135,4,15),(943,136,4,131),(944,137,4,171),(945,138,4,7),(946,139,4,62),(947,140,4,11),(948,141,4,66),(949,142,4,168),(950,143,4,139),(951,144,4,91),(952,145,4,151),(953,146,4,169),(954,147,4,74),(955,148,4,196),(956,149,4,180),(957,150,4,78),(958,151,4,49),(959,152,4,146),(960,153,4,51),(961,154,4,71),(962,155,4,53),(963,156,4,189),(964,157,4,154),(965,158,4,30),(966,159,4,110),(967,160,4,77),(968,161,4,65),(969,162,4,81),(970,163,4,118),(971,164,4,187),(972,165,4,147),(973,166,4,119),(974,167,4,91),(975,168,4,178),(976,169,4,165),(977,170,4,71),(978,171,4,49),(979,172,4,32),(980,173,4,169),(981,174,4,78),(982,175,4,93),(983,176,4,51),(984,177,4,135),(985,178,4,102),(986,179,4,137),(987,180,4,198),(988,181,4,54),(989,182,4,83),(990,183,4,133),(991,184,4,59),(992,185,4,20),(993,186,4,160),(994,187,4,120),(995,188,4,127),(996,189,4,149),(997,190,4,174),(998,191,4,128),(999,192,4,4),(1000,193,4,179),(1001,194,4,80),(1002,195,4,72),(1003,196,4,73),(1004,197,4,109),(1005,198,4,13),(1006,199,4,165),(1007,200,4,168),(1008,201,4,23),(1009,202,4,18),(1010,203,4,23),(1011,204,4,47),(1012,205,4,88),(1013,206,4,147),(1014,207,4,141),(1015,208,4,197),(1016,209,4,90),(1017,210,4,51),(1018,211,4,67),(1019,212,4,76),(1020,213,4,161),(1021,214,4,183),(1022,215,4,18),(1023,216,4,19),(1024,217,4,14),(1025,218,4,186),(1026,219,4,129),(1027,220,4,45),(1028,221,4,43),(1029,222,4,64),(1030,223,4,117),(1031,224,4,23),(1032,225,4,146),(1033,226,4,11),(1034,227,4,195),(1035,228,4,123),(1036,229,4,16),(1037,230,4,38),(1038,231,4,48),(1039,232,4,70),(1040,233,4,73),(1041,234,4,44),(1042,235,4,152),(1043,236,4,3),(1044,237,4,30),(1045,238,4,141),(1046,239,4,149),(1047,240,4,178),(1048,241,4,169),(1049,242,4,43),(1050,243,4,39),(1051,244,4,93),(1052,245,4,143),(1053,246,4,131),(1054,247,4,116),(1055,248,4,60),(1056,249,4,5),(1057,250,4,112),(1058,251,4,196),(1059,252,4,81),(1060,253,4,79),(1061,254,4,122),(1062,255,4,129),(1063,256,4,179),(1064,257,4,65),(1065,258,4,133),(1066,259,4,176),(1067,260,4,174),(1068,261,4,76),(1069,262,4,16),(1070,263,4,110),(1071,264,4,112),(1072,265,4,104),(1073,266,4,153),(1074,267,4,7),(1075,268,4,29),(1076,269,4,151),(1077,1,5,164),(1078,2,5,124),(1079,3,5,95),(1080,4,5,74),(1081,5,5,7),(1082,6,5,192),(1083,7,5,103),(1084,8,5,193),(1085,9,5,33),(1086,10,5,107),(1087,11,5,158),(1088,12,5,30),(1089,13,5,59),(1090,14,5,42),(1091,15,5,15),(1092,16,5,108),(1093,17,5,145),(1094,18,5,91),(1095,19,5,84),(1096,20,5,76),(1097,21,5,124),(1098,22,5,190),(1099,23,5,92),(1100,24,5,98),(1101,25,5,41),(1102,26,5,136),(1103,27,5,45),(1104,28,5,99),(1105,29,5,38),(1106,30,5,7),(1107,31,5,18),(1108,32,5,156),(1109,33,5,112),(1110,34,5,77),(1111,35,5,21),(1112,36,5,198),(1113,37,5,35),(1114,38,5,26),(1115,39,5,3),(1116,40,5,122),(1117,41,5,176),(1118,42,5,102),(1119,43,5,170),(1120,44,5,176),(1121,45,5,138),(1122,46,5,100),(1123,47,5,77),(1124,48,5,108),(1125,49,5,84),(1126,50,5,188),(1127,51,5,110),(1128,52,5,157),(1129,53,5,70),(1130,54,5,186),(1131,55,5,196),(1132,56,5,182),(1133,57,5,7),(1134,58,5,61),(1135,59,5,136),(1136,60,5,168),(1137,61,5,4),(1138,62,5,32),(1139,63,5,130),(1140,64,5,130),(1141,65,5,24),(1142,66,5,7),(1143,67,5,2),(1144,68,5,69),(1145,69,5,164),(1146,70,5,17),(1147,71,5,168),(1148,72,5,63),(1149,73,5,40),(1150,74,5,125),(1151,75,5,127),(1152,76,5,2),(1153,77,5,63),(1154,78,5,145),(1155,79,5,40),(1156,80,5,32),(1157,81,5,37),(1158,82,5,51),(1159,83,5,124),(1160,84,5,109),(1161,85,5,134),(1162,86,5,188),(1163,87,5,146),(1164,88,5,77),(1165,89,5,164),(1166,90,5,45),(1167,91,5,147),(1168,92,5,7),(1169,93,5,40),(1170,94,5,138),(1171,95,5,34),(1172,96,5,45),(1173,97,5,131),(1174,98,5,120),(1175,99,5,93),(1176,100,5,198),(1177,101,5,55),(1178,102,5,82),(1179,103,5,117),(1180,104,5,95),(1181,105,5,139),(1182,106,5,26),(1183,107,5,37),(1184,108,5,14),(1185,109,5,75),(1186,110,5,140),(1187,111,5,182),(1188,112,5,155),(1189,113,5,198),(1190,114,5,52),(1191,115,5,122),(1192,116,5,102),(1193,117,5,106),(1194,118,5,26),(1195,119,5,121),(1196,120,5,149),(1197,121,5,165),(1198,122,5,107),(1199,123,5,111),(1200,124,5,25),(1201,125,5,26),(1202,126,5,106),(1203,127,5,48),(1204,128,5,70),(1205,129,5,155),(1206,130,5,143),(1207,131,5,142),(1208,132,5,76),(1209,133,5,179),(1210,134,5,141),(1211,135,5,60),(1212,136,5,87),(1213,137,5,60),(1214,138,5,104),(1215,139,5,150),(1216,140,5,195),(1217,141,5,46),(1218,142,5,49),(1219,143,5,187),(1220,144,5,193),(1221,145,5,177),(1222,146,5,80),(1223,147,5,183),(1224,148,5,79),(1225,149,5,54),(1226,150,5,3),(1227,151,5,182),(1228,152,5,83),(1229,153,5,55),(1230,154,5,90),(1231,155,5,56),(1232,156,5,148),(1233,157,5,64),(1234,158,5,161),(1235,159,5,124),(1236,160,5,113),(1237,161,5,67),(1238,162,5,183),(1239,163,5,27),(1240,164,5,199),(1241,165,5,141),(1242,166,5,88),(1243,167,5,144),(1244,168,5,103),(1245,169,5,135),(1246,170,5,79),(1247,171,5,175),(1248,172,5,89),(1249,173,5,125),(1250,174,5,66),(1251,175,5,158),(1252,176,5,174),(1253,177,5,89),(1254,178,5,181),(1255,179,5,72),(1256,180,5,175),(1257,181,5,52),(1258,182,5,67),(1259,183,5,165),(1260,184,5,144),(1261,185,5,57),(1262,186,5,162),(1263,187,5,93),(1264,188,5,4),(1265,189,5,111),(1266,190,5,23),(1267,191,5,107),(1268,192,5,50),(1269,193,5,154),(1270,194,5,53),(1271,195,5,44),(1272,196,5,111),(1273,197,5,173),(1274,198,5,50),(1275,199,5,16),(1276,200,5,24),(1277,201,5,171),(1278,202,5,133),(1279,203,5,116),(1280,204,5,83),(1281,205,5,164),(1282,206,5,58),(1283,207,5,120),(1284,208,5,64),(1285,209,5,117),(1286,210,5,65),(1287,211,5,36),(1288,212,5,178),(1289,213,5,122),(1290,214,5,140),(1291,215,5,76),(1292,216,5,48),(1293,217,5,174),(1294,218,5,75),(1295,219,5,28),(1296,220,5,1),(1297,221,5,44),(1298,222,5,112),(1299,223,5,146),(1300,224,5,29),(1301,225,5,68),(1302,226,5,4),(1303,227,5,181),(1304,228,5,25),(1305,229,5,136),(1306,230,5,49),(1307,231,5,89),(1308,232,5,42),(1309,233,5,112),(1310,234,5,42),(1311,235,5,161),(1312,236,5,62),(1313,237,5,148),(1314,238,5,7),(1315,239,5,22),(1316,240,5,104),(1317,241,5,33),(1318,242,5,9),(1319,243,5,13),(1320,244,5,60),(1321,245,5,168),(1322,246,5,199),(1323,247,5,9),(1324,248,5,159),(1325,249,5,186),(1326,250,5,107),(1327,251,5,118),(1328,252,5,127),(1329,253,5,38),(1330,254,5,161),(1331,255,5,163),(1332,256,5,42),(1333,257,5,174),(1334,258,5,91),(1335,259,5,62),(1336,260,5,88),(1337,261,5,9),(1338,262,5,131),(1339,263,5,12),(1340,264,5,55),(1341,265,5,77),(1342,266,5,5),(1343,267,5,20),(1344,268,5,111),(1345,269,5,44),(1346,1,6,96),(1347,2,6,12),(1348,3,6,90),(1349,4,6,56),(1350,5,6,63),(1351,6,6,77),(1352,7,6,169),(1353,8,6,120),(1354,9,6,17),(1355,10,6,190),(1356,11,6,194),(1357,12,6,37),(1358,13,6,16),(1359,14,6,66),(1360,15,6,178),(1361,16,6,126),(1362,17,6,60),(1363,18,6,9),(1364,19,6,195),(1365,20,6,137),(1366,21,6,110),(1367,22,6,26),(1368,23,6,138),(1369,24,6,120),(1370,25,6,51),(1371,26,6,94),(1372,27,6,71),(1373,28,6,149),(1374,29,6,97),(1375,30,6,161),(1376,31,6,78),(1377,32,6,170),(1378,33,6,126),(1379,34,6,139),(1380,35,6,189),(1381,36,6,106),(1382,37,6,101),(1383,38,6,104),(1384,39,6,147),(1385,40,6,49),(1386,41,6,169),(1387,42,6,0),(1388,43,6,170),(1389,44,6,102),(1390,45,6,197),(1391,46,6,82),(1392,47,6,3),(1393,48,6,146),(1394,49,6,60),(1395,50,6,63),(1396,51,6,124),(1397,52,6,184),(1398,53,6,78),(1399,54,6,27),(1400,55,6,72),(1401,56,6,54),(1402,57,6,57),(1403,58,6,31),(1404,59,6,68),(1405,60,6,131),(1406,61,6,29),(1407,62,6,137),(1408,63,6,28),(1409,64,6,11),(1410,65,6,123),(1411,66,6,191),(1412,67,6,50),(1413,68,6,3),(1414,69,6,56),(1415,70,6,39),(1416,71,6,73),(1417,72,6,3),(1418,73,6,12),(1419,74,6,57),(1420,75,6,93),(1421,76,6,51),(1422,77,6,50),(1423,78,6,49),(1424,79,6,37),(1425,80,6,83),(1426,81,6,60),(1427,82,6,153),(1428,83,6,40),(1429,84,6,54),(1430,85,6,80),(1431,86,6,133),(1432,87,6,33),(1433,88,6,168),(1434,89,6,179),(1435,90,6,97),(1436,91,6,177),(1437,92,6,58),(1438,93,6,71),(1439,94,6,44),(1440,95,6,167),(1441,96,6,134),(1442,97,6,28),(1443,98,6,169),(1444,99,6,168),(1445,100,6,138),(1446,101,6,178),(1447,102,6,48),(1448,103,6,134),(1449,104,6,49),(1450,105,6,81),(1451,106,6,121),(1452,107,6,16),(1453,108,6,133),(1454,109,6,103),(1455,110,6,82),(1456,111,6,154),(1457,112,6,55),(1458,113,6,6),(1459,114,6,26),(1460,115,6,132),(1461,116,6,191),(1462,117,6,20),(1463,118,6,101),(1464,119,6,160),(1465,120,6,71),(1466,121,6,87),(1467,122,6,5),(1468,123,6,4),(1469,124,6,74),(1470,125,6,170),(1471,126,6,46),(1472,127,6,165),(1473,128,6,190),(1474,129,6,119),(1475,130,6,68),(1476,131,6,57),(1477,132,6,58),(1478,133,6,92),(1479,134,6,52),(1480,135,6,5),(1481,136,6,120),(1482,137,6,176),(1483,138,6,154),(1484,139,6,77),(1485,140,6,17),(1486,141,6,177),(1487,142,6,66),(1488,143,6,52),(1489,144,6,15),(1490,145,6,100),(1491,146,6,44),(1492,147,6,47),(1493,148,6,98),(1494,149,6,63),(1495,150,6,15),(1496,151,6,153),(1497,152,6,198),(1498,153,6,61),(1499,154,6,195),(1500,155,6,3),(1501,156,6,168),(1502,157,6,184),(1503,158,6,33),(1504,159,6,182),(1505,160,6,162),(1506,161,6,147),(1507,162,6,188),(1508,163,6,14),(1509,164,6,39),(1510,165,6,0),(1511,166,6,116),(1512,167,6,133),(1513,168,6,73),(1514,169,6,30),(1515,170,6,97),(1516,171,6,111),(1517,172,6,27),(1518,173,6,32),(1519,174,6,47),(1520,175,6,16),(1521,176,6,169),(1522,177,6,153),(1523,178,6,173),(1524,179,6,86),(1525,180,6,25),(1526,181,6,33),(1527,182,6,60),(1528,183,6,184),(1529,184,6,51),(1530,185,6,21),(1531,186,6,189),(1532,187,6,105),(1533,188,6,100),(1534,189,6,38),(1535,190,6,93),(1536,191,6,125),(1537,192,6,36),(1538,193,6,99),(1539,194,6,133),(1540,195,6,121),(1541,196,6,128),(1542,197,6,192),(1543,198,6,170),(1544,199,6,43),(1545,200,6,28),(1546,201,6,74),(1547,202,6,8),(1548,203,6,185),(1549,204,6,11),(1550,205,6,151),(1551,206,6,13),(1552,207,6,90),(1553,208,6,28),(1554,209,6,117),(1555,210,6,74),(1556,211,6,126),(1557,212,6,11),(1558,213,6,161),(1559,214,6,56),(1560,215,6,93),(1561,216,6,125),(1562,217,6,16),(1563,218,6,66),(1564,219,6,147),(1565,220,6,45),(1566,221,6,5),(1567,222,6,43),(1568,223,6,199),(1569,224,6,180),(1570,225,6,49),(1571,226,6,141),(1572,227,6,176),(1573,228,6,130),(1574,229,6,33),(1575,230,6,63),(1576,231,6,101),(1577,232,6,17),(1578,233,6,188),(1579,234,6,2),(1580,235,6,192),(1581,236,6,95),(1582,237,6,122),(1583,238,6,43),(1584,239,6,94),(1585,240,6,183),(1586,241,6,121),(1587,242,6,186),(1588,243,6,135),(1589,244,6,127),(1590,245,6,77),(1591,246,6,109),(1592,247,6,158),(1593,248,6,161),(1594,249,6,84),(1595,250,6,40),(1596,251,6,173),(1597,252,6,107),(1598,253,6,42),(1599,254,6,163),(1600,255,6,145),(1601,256,6,7),(1602,257,6,82),(1603,258,6,84),(1604,259,6,81),(1605,260,6,106),(1606,261,6,124),(1607,262,6,182),(1608,263,6,121),(1609,264,6,176),(1610,265,6,33),(1611,266,6,110),(1612,267,6,19),(1613,268,6,191),(1614,269,6,79),(2141,274,1,NULL),(2142,274,2,NULL),(2143,274,3,NULL),(2144,274,4,NULL),(2145,274,5,NULL),(2146,274,6,NULL),(2147,274,8,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,'REFCPR01',65,'Colomer sa de cv','distribuidora de productos de belleza al por mayor','correo_proveedor1@hotmail.com','Edo MX','Cd Mx','Av insurgentes','26087',1),(2,'REFCPR02',66,'Revlon SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor2@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25791',1),(3,'REFCPR03',67,'Bidiip','distribuidora de productos de belleza al por mayor','correo_proveedor3@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28447',1),(4,'REFCPR04',68,'Anven SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor4@hotmail.com','Edo MX','Cd Mx','Av insurgentes','27511',1),(5,'REFCPR05',69,'Nefertiti SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor5@hotmail.com','Edo MX','Cd Mx','Av insurgentes','26712',1),(6,'REFCPR06',70,'Ricardo Rodrigo','distribuidora de productos de belleza al por mayor','correo_proveedor6@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25761',1),(7,'REFCPR07',71,'Raul Trinidad','distribuidora de productos de belleza al por mayor','correo_proveedor7@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28733',1),(8,'REFCPR08',72,'Henry Rodriguez','distribuidora de productos de belleza al por mayor','correo_proveedor8@hotmail.com','Edo MX','Cd Mx','Av insurgentes','28906',1),(9,'REFCPR09',73,'Cosmetica capilar','distribuidora de productos de belleza al por mayor','correo_proveedor9@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25348',1),(10,'REFCPR10',74,'Cosmetica insurgentes','distribuidora de productos de belleza al por mayor','correo_proveedor10@hotmail.com','Edo MX','Cd Mx','Av insurgentes','25862',1),(11,'REFCPR11',75,'Belleza cien','distribuidora de productos de belleza al por mayor','correo_proveedor11@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27249',1),(12,'REFCPR12',76,'Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor12@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27748',1),(13,'REFCPR13',77,'Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor','correo_proveedor13@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27219',1),(14,'REFCPR14',78,'Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor','correo_proveedor14@hotmail.com','Guadalajara','Jalisco','Av 13 sur','27151',1),(15,'REFCPR15',79,'Regio Belleza','distribuidora de productos de belleza al por mayor','correo_proveedor15@hotmail.com','Nuevo Leon','Monterrey','Av 13 sur','28947',1),(16,'REFCPR16',80,'Somo Tapatio SA de CV','distribuidora de productos para oficina','correo_proveedor16@hotmail.com','Guadalajara','Jalisco','Av 13 sur','28960',1),(17,'REFCPR17',81,'Hidra Color SA de Cv','distribuidora de productos de belleza al por mayor actualizado','correo_proveedor17@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25542',1),(18,'REFCPR18',82,'Color Shot SAS de CV','distribuidora de productos de belleza al por mayor','correo_proveedor18@hotmail.com','Edo MX','Cd Mx','Av 13 sur','28472',1),(19,'REFCPR19',83,'Color Tech','distribuidora de productos de belleza al por mayor','correo_proveedor19@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25526',1),(20,'REFCPR20',84,'Nutrapel SA de CV','distribuidora de productos de belleza al por mayor','correo_proveedor20@hotmail.com','Edo MX','Cd Mx','Av 13 sur','25386',1),(21,'REFCPR21',85,'Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor','correo_proveedor21@hotmail.com','Edo MX','Cd Mx','Av 13 sur','26553',1),(22,'REFCPR22',86,'Insumos del sureste','distribuidora de consumibles para oficina','correo_proveedor22@hotmail.com','Yucatan','Merida','Av 13 sur','26285',1),(23,'REFCPR23',87,'Tecnologica San Cristobal SA d','tienda de articulos teconoógicos','correo_proveedor23@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','26080',1),(24,'REFCPR24',88,'Micro Chip SA de Cv','tienda de articulos teconoógicos','correo_proveedor24@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','28583',1),(25,'REFCPR25',89,'Jairo Eniquez','tienda de articulos teconoógicos','correo_proveedor25@hotmail.com','Chiapas','Tuxtla Gutierrez','Calle central sur','28233',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_cuentas_tercer_nivel`
--

LOCK TABLES `sub_cuentas_tercer_nivel` WRITE;
/*!40000 ALTER TABLE `sub_cuentas_tercer_nivel` DISABLE KEYS */;
INSERT INTO `sub_cuentas_tercer_nivel` VALUES (1,1,'100.1.1','Efectivo disponible','',0,0,0),(2,1,'100.1.2','Fondo de ahorro','efectivo ahorrado',0,0,0),(3,1,'100.1.3','Efectivo en circulacion','efectivo usado para cambios',0,0,0),(4,2,'100.2.1','Bancomer','cuenta bancomer',0,0,0),(5,5,'100.5.1','iva acreditable pagado','iava acreditable efectivamente pagado',0,0,0),(6,6,'100.6.1','iva acreditable por pagar','iva acreditable pendiente de pago',0,0,0),(7,8,'200.2.1','iva trasladado','iva cobrado',0,0,0),(8,9,'200.3.1','iva por trasladar','iva por cobrar',0,0,0),(9,10,'400.1.1','venta de mercancías','ingresos obtenidos por la venta de mercancias',0,0,0),(10,11,'401.1.1','Desc y Dev sobre ventas','Devoluciones o descuentos otorgados sobre las ventas',0,0,0),(11,12,'500.1.1','compra de mercancias','',0,0,0),(12,13,'501.1.1','Desc y dev sobre compras','devoluciones o descuentos otorgados sobre las compras de mercancias',0,0,0),(13,14,'600.1.1','sueldos y salarios','',0,0,0),(14,14,'600.1.2','PAGO SERVICIOS POR TERCEROS','',0,0,0),(15,14,'600.1.3','SERVICIO DE AGUA','',0,0,0),(16,14,'600.1.4','ENERGIA ELECTRICA','',0,0,0),(17,14,'600.1.5','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(18,14,'600.1.6','DESPENSAS Y ALIMENTOS','',0,0,0),(19,14,'600.1.7','ASEO Y LIMPIEZA','',0,0,0),(20,14,'600.1.8','MTTO EQUIPO DE TRANSPORTE','',0,0,0),(21,14,'600.1.9','MTTO EQUIPO DE COMPUTO','',0,0,0),(22,14,'600.1.10','MTTO DEL LOCAL','',0,0,0),(23,14,'600.1.11','OTROS GASTOS','',0,0,0),(24,14,'600.1.12','AJUSTE POR GASTOS NO CONOCIDOS','',0,0,0),(25,14,'600.1.13','TELEFONO E INTERNET','',0,0,0),(26,14,'600.1.14','ENVOLTURAS Y EMPAQUES','',0,0,0),(27,15,'600.2.1','GASOLINA MOTO','',0,0,0),(28,15,'600.2.2','TELEFONIA MOVIL','',0,0,0),(29,15,'600.2.3','PUBLICIDAD','',0,0,0),(30,15,'600.2.4','FLETES','',0,0,0),(31,15,'600.2.5','ENVOLTURAS Y EMPAQUES','',0,0,0),(32,16,'600.3.1','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(33,16,'600.3.2','PAPELERIA Y UTILES','',0,0,0),(34,16,'600.3.3','ENERGÍA ELECTRICA','',0,0,0),(35,16,'600.3.4','TELEFONIA MOVIL','',0,0,0),(36,16,'600.3.5','TELEFONO E INTERNET','',0,0,0),(37,16,'600.3.6','IMPUESTOS FEDERALES','',0,0,0),(38,16,'600.3.7','RENTA DEL LOCAL','',0,0,0),(39,16,'600.3.8','OTROS GASTOS DE ADMIN','',0,0,0),(40,16,'600.3.9','CUOTAS Y SUSCRIPCIONES','',0,0,0),(41,16,'600.3.10','CUOTAS Y PAGOS IMSS','',0,0,0),(42,18,'700.1.1','PERDIDA CAMBIARIA','',0,0,0),(43,18,'700.1.2','INTERESES A CARGO','',0,0,0),(44,18,'700.1.3','COMISIONES BANCARIAS','',0,0,0),(45,4,'100.4.1','MOSTRADOR','publico general',0,0,0),(46,4,'100.4.2','LOURDES LOPEZ GUILLEN','',0,0,0),(47,4,'100.4.3','MARIA ERNESTINA AGUSTIN PEREZ','',0,0,0),(48,4,'100.4.4','MARIA CONCEPCION CHULIN GORDIL','',0,0,0),(49,4,'100.4.5','LUCIA ELIZABETH LOPEZ GONZALEZ','',0,0,0),(50,4,'100.4.6','ANA YANSI CRUZ GARCIA','',0,0,0),(51,4,'100.4.7','MARIA ENCARNACION SARMIENTO SA','',0,0,0),(52,4,'100.4.8','MARIA BEININA GOMEZ ALVAREZ','',0,0,0),(53,4,'100.4.9','LUZ MARIA MORALES TORRES','',0,0,0),(54,4,'100.4.10','VERONICA LOPEZ CRUZ','',0,0,0),(55,4,'100.4.11','GUADALUPE NURIULU GORDILLO','',0,0,0),(56,4,'100.4.12','WALTER DILTIEV SOTO MORALES','',0,0,0),(57,4,'100.4.13','MUNICIPIO DE VENUSTIANO CARRAN','SE ACTUALIZA LA INFORMACIÓN DE ESTE CLIENTE',0,0,0),(58,4,'100.4.14','BEATRIZ ADRIANA PENAGOS GONZAL','',0,0,0),(59,4,'100.4.15','YARENI HERNANDEZ GARCIA','',0,0,0),(60,4,'100.4.16','METALLICA','',0,0,0),(61,4,'100.4.17','FABIOLA INDILI CUNDAPI','',0,0,0),(62,4,'100.4.18','ELIZA AGUILAR','',0,0,0),(63,4,'100.4.19','PAOLA LIZETH GALLEGOS DEL CARP','',0,0,0),(64,4,'100.4.20','DANIA MARGARITA GOMEZ HERNANDE','',0,0,0),(65,7,'200.1.1','Colomer sa de cv','distribuidora de productos de belleza al por mayor',0,0,0),(66,7,'200.1.2','Revlon SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(67,7,'200.1.3','Bidiip','distribuidora de productos de belleza al por mayor',0,0,0),(68,7,'200.1.4','Anven SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(69,7,'200.1.5','Nefertiti SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(70,7,'200.1.6','Ricardo Rodrigo','distribuidora de productos de belleza al por mayor',0,0,0),(71,7,'200.1.7','Raul Trinidad','distribuidora de productos de belleza al por mayor',0,0,0),(72,7,'200.1.8','Henry Rodriguez','distribuidora de productos de belleza al por mayor',0,0,0),(73,7,'200.1.9','Cosmetica capilar','distribuidora de productos de belleza al por mayor',0,0,0),(74,7,'200.1.10','Cosmetica insurgentes','distribuidora de productos de belleza al por mayor',0,0,0),(75,7,'200.1.11','Belleza cien','distribuidora de productos de belleza al por mayor',0,0,0),(76,7,'200.1.12','Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(77,7,'200.1.13','Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor',0,0,0),(78,7,'200.1.14','Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor',0,0,0),(79,7,'200.1.15','Regio Belleza','distribuidora de productos de belleza al por mayor',0,0,0),(80,7,'200.1.16','Somo Tapatio SA de CV','distribuidora de productos para oficina',0,0,0),(81,7,'200.1.17','Hidra Color SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(82,7,'200.1.18','Color Shot SAS de CV','distribuidora de productos de belleza al por mayor',0,0,0),(83,7,'200.1.19','Color Tech','distribuidora de productos de belleza al por mayor',0,0,0),(84,7,'200.1.20','Nutrapel SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(85,7,'200.1.21','Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(86,7,'200.1.22','Insumos del sureste','distribuidora de consumibles para oficina',0,0,0),(87,7,'200.1.23','Tecnologica San Cristobal SA d','tienda de articulos teconoógicos',0,0,0),(88,7,'200.1.24','Micro Chip SA de Cv','tienda de articulos teconoógicos',0,0,0),(89,7,'200.1.25','Jairo Eniquez','tienda de articulos teconoógicos',0,0,0),(116,4,'100.4.21','XTK','UN CLIENTE DE PRUEBA PARA PROBAR LA PRUEBA PROBADA',0,0,0);
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
INSERT INTO `sucursal` VALUES (1,'Matris Principal','Tienda primaria','9619888758','sucursal1_empresa@fakemail.com','Chiapas','Tuxtla Gutierrez','4a oriente sur #448 barrio san roque','29000',1),(2,'Sucursal Sab','Segunda Sucursal','9612364800','sucursal2_empresa@fakemail.com','Chiapas','Chiapa de Corzo','Av principal zona centro local7','29000',1),(3,'Sucursal Trend','Tercera Sucursal','9615568093','sucursal3_empresa@fakemail.com','Chiapas','Cintalapa','Av central poniente #725','29000',1),(4,'Sucursal Jaf','Cuarta Sucursal Actualizada','9617921545','sucursal4_empresa@fakemail.com','Chiapas','Tuxtla Gutierrez','5a norte oriente #613','29000',1),(5,'Sucursal Pablo','Quinta Sucursal','9616693648','sucursal5_empresa@fakemail.com','Chiapas','San Cristobal','Calle Miguel Aleman Col la antigua #698','29000',1),(6,'Sucursal Noely','Sexta Sucursal','9616951522','sucursal6_empresa@fakemail.com','Queretaro','Queretaro','Av corregidora col centro #450','29000',1),(8,'Sucursal Moderna','Nueva sucursar la moderna de prueba','9614427278','fakemail_mail@hubmail.com','Chiapas','Tuxtla','Una dirección cualquiera de algún sitio de la ciudad solo para la prueba','29000',0);
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
INSERT INTO `ventas` VALUES (1,1,'2022-01-02',1,22,7,59247.93103,9479.668966,68727.6,1),(2,2,'2022-01-02',1,16,12,5917.887931,946.862069,6864.75,1),(3,1,'2022-01-03',1,14,18,27589.65517,4414.344828,32004,1),(4,2,'2022-01-03',1,38,6,23664,3786.24,27450.24,1),(5,2,'2022-01-04',1,23,14,39629.84483,6340.775172,45970.62,1),(6,2,'2022-01-04',1,35,4,36747.16379,5879.546207,42626.71,1),(7,6,'2022-01-04',1,26,3,36071.31034,5771.409655,41842.72,1),(8,4,'2022-01-04',1,3,7,97724.15517,15635.86483,113360.02,1),(9,6,'2022-01-05',1,26,15,4698.448276,751.7517241,5450.2,1),(10,1,'2022-01-05',1,23,16,27840.99138,4454.558621,32295.55,1),(11,1,'2022-01-06',1,21,18,68931.76724,11029.08276,79960.85,1),(12,6,'2022-01-06',1,30,18,59820.41379,9571.266207,69391.68,1),(13,3,'2022-01-07',1,40,18,43880.27586,7020.844138,50901.12,1),(14,3,'2022-01-07',1,30,20,92847.59483,14855.61517,107703.21,1),(15,2,'2022-01-08',1,6,6,56749.65517,9079.944828,65829.6,1),(16,1,'2022-01-08',1,23,8,17206.89655,2753.103448,19960,1),(17,2,'2022-01-09',1,17,8,25168.27586,4026.924138,29195.2,1),(18,2,'2022-01-09',1,37,17,90679.64655,14508.74345,105188.39,1),(19,5,'2022-01-10',1,38,14,31939.65517,5110.344828,37050,1),(20,3,'2022-01-10',1,20,3,45851.55172,7336.248276,53187.8,1),(21,2,'2022-01-10',1,22,16,38530.48276,6164.877241,44695.36,1),(22,3,'2022-01-10',1,1,1,22304.68966,3568.750345,25873.44,1),(23,4,'2022-01-11',1,9,3,55646.42241,8903.427586,64549.85,1),(24,2,'2022-01-12',1,17,18,30034.64655,4805.543448,34840.19,1),(25,3,'2022-01-12',1,9,1,18850,3016,21866,1),(26,3,'2022-01-13',1,29,6,51789.38793,8286.302069,60075.69,1),(27,6,'2022-01-14',1,35,9,9093.724138,1454.995862,10548.72,1),(28,4,'2022-01-14',1,40,12,53522.12069,8563.53931,62085.66,1),(29,2,'2022-01-14',1,35,16,24401.48276,3904.237241,28305.72,1),(30,6,'2022-01-14',1,31,17,50756.74138,8121.078621,58877.82,1),(31,4,'2022-01-15',1,4,15,59033.25862,9445.321379,68478.58,1),(32,6,'2022-01-15',1,32,4,33274.86207,5323.977931,38598.84,1),(33,1,'2022-01-17',1,36,18,15800.80172,2528.128276,18328.93,1),(34,3,'2022-01-18',1,3,15,90367.65517,14458.82483,104826.48,1),(35,2,'2022-01-19',1,29,8,17046.2069,2727.393103,19773.6,1),(36,1,'2022-01-19',1,28,6,75583.76724,12093.40276,87677.17,1),(37,6,'2022-01-19',1,5,10,22402,3584.32,25986.32,1),(38,6,'2022-01-20',1,40,6,25236.2069,4037.793103,29274,1),(39,3,'2022-01-20',1,35,2,39299.58621,6287.933793,45587.52,1),(40,1,'2022-01-21',1,11,20,43749.0431,6999.846897,50748.89,1),(41,2,'2022-01-22',1,29,5,24935.25,3989.64,28924.89,1),(42,6,'2022-01-24',1,18,14,90461.37069,14473.81931,104935.19,1),(43,1,'2022-01-24',1,12,16,45369.81034,7259.169655,52628.98,1),(44,6,'2022-01-26',1,26,14,70944.26724,11351.08276,82295.35,1),(45,4,'2022-01-27',1,13,6,65038.68966,10406.19034,75444.88,1),(46,2,'2022-01-28',1,38,14,39361.03448,6297.765517,45658.8,1),(47,5,'2022-01-29',1,3,12,12929.7931,2068.766897,14998.56,1),(48,2,'2022-01-29',1,40,5,42561.43966,6809.830345,49371.27,1),(49,5,'2022-01-30',1,14,16,56943.23276,9110.917241,66054.15,1),(50,2,'2022-01-30',1,5,12,94007.44828,15041.19172,109048.64,1),(51,2,'2022-01-30',1,31,4,41721.98276,6675.517241,48397.5,1),(52,2,'2022-01-30',1,11,7,72966.12069,11674.57931,84640.7,1),(53,5,'2022-02-01',1,32,10,35279.10345,5644.656552,40923.76,1),(54,2,'2022-02-01',1,3,2,33959.62069,5433.53931,39393.16,1),(55,6,'2022-02-01',1,22,17,49972.80172,7995.648276,57968.45,1),(56,4,'2022-02-02',1,2,6,32288.90517,5166.224828,37455.13,1),(57,3,'2022-02-02',1,8,2,20484.82759,3277.572414,23762.4,1),(58,2,'2022-02-03',1,19,14,75385.0431,12061.6069,87446.65,1),(59,6,'2022-02-03',1,11,17,4113.793103,658.2068966,4772,1),(60,3,'2022-02-04',1,3,11,17950,2872,20822,1),(61,2,'2022-02-05',1,9,15,2587.965517,414.0744828,3002.04,1),(62,2,'2022-02-05',1,39,7,66109.96552,10577.59448,76687.56,1),(63,2,'2022-02-06',1,27,11,36931.03448,5908.965517,42840,1),(64,3,'2022-02-09',1,19,4,15285.33621,2445.653793,17730.99,1),(65,3,'2022-02-09',1,30,16,3646.551724,583.4482759,4230,1),(66,6,'2022-02-10',1,23,19,15781.03448,2524.965517,18306,1),(67,2,'2022-02-10',1,40,15,21169,3387.04,24556.04,1),(68,6,'2022-02-10',1,39,14,39237.58621,6278.013793,45515.6,1),(69,6,'2022-02-11',1,36,11,49295.57759,7887.292414,57182.87,1),(70,5,'2022-02-11',1,10,9,32563.40517,5210.144828,37773.55,1),(71,2,'2022-02-12',1,32,11,74749.24138,11959.87862,86709.12,1),(72,1,'2022-02-12',1,9,18,41544.75,6647.16,48191.91,1),(73,2,'2022-02-12',1,25,5,95961.68966,15353.87034,111315.56,1),(74,3,'2022-02-14',1,21,20,88140.19828,14102.43172,102242.63,1),(75,1,'2022-02-15',1,19,4,37923.65517,6067.784828,43991.44,1),(76,4,'2022-02-15',1,4,5,26044.2931,4167.086897,30211.38,1),(77,2,'2022-02-16',1,7,7,39964.13793,6394.262069,46358.4,1),(78,3,'2022-02-16',1,18,20,6702.068966,1072.331034,7774.4,1),(79,3,'2022-02-16',1,11,4,103457.9655,16553.27448,120011.24,1),(80,1,'2022-02-17',1,40,17,22711.34483,3633.815172,26345.16,1),(81,2,'2022-02-17',1,32,14,91453.68103,14632.58897,106086.27,1),(82,2,'2022-02-18',1,40,9,55471.49138,8875.438621,64346.93,1),(83,6,'2022-02-20',1,24,10,8386.206897,1341.793103,9728,1),(84,2,'2022-02-20',1,3,1,51549.81034,8247.969655,59797.78,1),(85,3,'2022-02-20',1,26,4,34128.2069,5460.513103,39588.72,1),(86,5,'2022-02-20',1,15,7,72372.90517,11579.66483,83952.57,1),(87,6,'2022-02-21',1,36,20,39660.24138,6345.638621,46005.88,1),(88,6,'2022-02-22',1,32,2,41145.51724,6583.282759,47728.8,1),(89,2,'2022-02-22',1,33,8,78750.82759,12600.13241,91350.96,1),(90,1,'2022-02-22',1,28,4,26338.93103,4214.228966,30553.16,1),(91,2,'2022-02-23',1,23,6,18823.67241,3011.787586,21835.46,1),(92,1,'2022-02-24',1,20,6,40590.5,6494.48,47084.98,1),(93,2,'2022-02-25',1,8,17,28449.43966,4551.910345,33001.35,1),(94,1,'2022-02-25',1,2,14,43882.75862,7021.241379,50904,1),(95,2,'2022-02-26',1,5,9,104986.1638,16797.78621,121783.95,1),(96,3,'2022-02-27',1,1,8,11424,1827.84,13251.84,1),(97,3,'2022-03-01',1,14,7,38641.87931,6182.70069,44824.58,1),(98,2,'2022-03-01',1,19,19,49014.62069,7842.33931,56856.96,1),(99,2,'2022-03-02',1,29,15,24181.03448,3868.965517,28050,1),(100,2,'2022-03-02',1,38,4,54130.75,8660.92,62791.67,1),(101,2,'2022-03-03',1,24,12,36445.32759,5831.252414,42276.58,1),(102,2,'2022-03-03',1,9,17,630.2672414,100.8427586,731.11,1),(103,3,'2022-03-04',1,3,11,42601.07759,6816.172414,49417.25,1),(104,2,'2022-03-06',1,32,16,66849.42241,10695.90759,77545.33,1),(105,5,'2022-03-07',1,17,10,52257.58621,8361.213793,60618.8,1),(106,2,'2022-03-07',1,6,18,36494.41379,5839.106207,42333.52,1),(107,6,'2022-03-08',1,33,6,23695.37931,3791.26069,27486.64,1),(108,3,'2022-03-08',1,23,8,51144.06897,8183.051034,59327.12,1),(109,5,'2022-03-09',1,7,15,59510.18966,9521.630345,69031.82,1),(110,2,'2022-03-10',1,26,10,113413.0345,18146.08552,131559.12,1),(111,1,'2022-03-10',1,30,15,79821.06897,12771.37103,92592.44,1),(112,2,'2022-03-11',1,14,13,2196.206897,351.3931034,2547.6,1),(113,1,'2022-03-12',1,9,18,10673.7931,1707.806897,12381.6,1),(114,3,'2022-03-13',1,6,3,23467.22414,3754.755862,27221.98,1),(115,3,'2022-03-13',1,7,3,35396.75,5663.48,41060.23,1),(116,3,'2022-03-14',1,19,8,18539.81034,2966.369655,21506.18,1),(117,3,'2022-03-15',1,27,8,67623.86207,10819.81793,78443.68,1),(118,1,'2022-03-15',1,1,3,22878.2069,3660.513103,26538.72,1),(119,6,'2022-03-16',1,30,14,45686.63793,7309.862069,52996.5,1),(120,3,'2022-03-17',1,40,10,66206.15517,10592.98483,76799.14,1),(121,3,'2022-03-17',1,4,8,74574.65517,11931.94483,86506.6,1),(122,1,'2022-03-19',1,7,16,73509.24138,11761.47862,85270.72,1),(123,1,'2022-03-20',1,25,18,95165.65517,15226.50483,110392.16,1),(124,5,'2022-03-21',1,21,2,8678.172414,1388.507586,10066.68,1),(125,4,'2022-03-21',1,16,12,16009.48276,2561.517241,18571,1),(126,4,'2022-03-22',1,23,9,45248,7239.68,52487.68,1),(127,2,'2022-03-23',1,26,1,14240,2278.4,16518.4,1),(128,2,'2022-03-23',1,6,1,17010.48276,2721.677241,19732.16,1),(129,2,'2022-03-23',1,10,14,129788.3362,20766.13379,150554.47,1),(130,2,'2022-03-24',1,12,15,35392.64655,5662.823448,41055.47,1),(131,6,'2022-03-24',1,8,18,61994.36207,9919.097931,71913.46,1),(132,2,'2022-03-24',1,32,15,32163.7931,5146.206897,37310,1),(133,3,'2022-03-24',1,35,4,15032.68966,2405.230345,17437.92,1),(134,2,'2022-03-24',1,11,16,48236.34483,7717.815172,55954.16,1),(135,2,'2022-03-25',1,36,11,25585.9569,4093.753103,29679.71,1),(136,3,'2022-03-25',1,9,8,3028,484.48,3512.48,1),(137,2,'2022-03-25',1,17,17,4355.172414,696.8275862,5052,1),(138,1,'2022-03-26',1,17,8,26645.62069,4263.29931,30908.92,1),(139,5,'2022-03-26',1,34,14,76821.46552,12291.43448,89112.9,1),(140,3,'2022-03-26',1,19,1,38237.35345,6117.976552,44355.33,1),(141,2,'2022-03-27',1,24,17,16763.44828,2682.151724,19445.6,1),(142,2,'2022-03-27',1,17,14,46857.51724,7497.202759,54354.72,1),(143,6,'2022-03-28',1,38,9,56806.59483,9089.055172,65895.65,1),(144,1,'2022-03-28',1,37,2,29381.47414,4701.035862,34082.51,1),(145,5,'2022-03-28',1,33,7,91642.37069,14662.77931,106305.15,1),(146,3,'2022-03-30',1,14,20,89283.68103,14285.38897,103569.07,1),(147,3,'2022-03-30',1,9,16,8085.344828,1293.655172,9379,1),(148,2,'2022-04-02',1,29,10,12936.82759,2069.892414,15006.72,1),(149,1,'2022-04-03',1,6,13,93964.2931,15034.2869,108998.58,1),(150,3,'2022-04-03',1,20,14,5848.724138,935.7958621,6784.52,1),(151,6,'2022-04-03',1,24,18,20073.34483,3211.735172,23285.08,1),(152,5,'2022-04-03',1,16,15,5371.293103,859.4068966,6230.7,1),(153,2,'2022-04-04',1,8,11,42485.56034,6797.689655,49283.25,1),(154,2,'2022-04-04',1,25,16,7809.025862,1249.444138,9058.47,1),(155,5,'2022-04-06',1,20,1,49220.68966,7875.310345,57096,1),(156,2,'2022-04-06',1,5,12,1089.310345,174.2896552,1263.6,1),(157,2,'2022-04-06',1,30,19,7228.87069,1156.61931,8385.49,1),(158,3,'2022-04-06',1,22,5,9867.931034,1578.868966,11446.8,1),(159,6,'2022-04-07',1,9,4,21430.03448,3428.805517,24858.84,1),(160,5,'2022-04-07',1,4,17,28207,4513.12,32720.12,1),(161,2,'2022-04-07',1,38,2,15765.44828,2522.471724,18287.92,1),(162,5,'2022-04-07',1,14,2,1274.482759,203.9172414,1478.4,1),(163,2,'2022-04-08',1,3,13,88844.75,14215.16,103059.91,1),(164,1,'2022-04-09',1,23,14,10843.93103,1735.028966,12578.96,1),(165,2,'2022-04-10',1,20,16,112280.0431,17964.8069,130244.85,1),(166,2,'2022-04-10',1,24,3,27832.86207,4453.257931,32286.12,1),(167,5,'2022-04-12',1,13,11,32096.27586,5135.404138,37231.68,1),(168,1,'2022-04-13',1,25,17,94143.81034,15063.00966,109206.82,1),(169,2,'2022-04-14',1,36,5,13094.34483,2095.095172,15189.44,1),(170,3,'2022-04-14',1,40,15,54907.7931,8785.246897,63693.04,1),(171,1,'2022-04-15',1,24,19,10101.72414,1616.275862,11718,1),(172,5,'2022-04-15',1,6,7,65073.13793,10411.70207,75484.84,1),(173,3,'2022-04-15',1,13,9,51340.53448,8214.485517,59555.02,1),(174,2,'2022-04-16',1,33,12,81547.34483,13047.57517,94594.92,1),(175,1,'2022-04-16',1,40,6,27636.2069,4421.793103,32058,1),(176,3,'2022-04-17',1,12,19,49694.7931,7951.166897,57645.96,1),(177,1,'2022-04-18',1,12,9,62577.15517,10012.34483,72589.5,1),(178,2,'2022-04-19',1,3,20,11106.89655,1777.103448,12884,1),(179,2,'2022-04-19',1,17,20,745.2155172,119.2344828,864.45,1),(180,4,'2022-04-20',1,18,13,108410,17345.6,125755.6,1),(181,3,'2022-04-20',1,10,17,39329.76724,6292.762759,45622.53,1),(182,2,'2022-04-20',1,17,15,62851.11207,10056.17793,72907.29,1),(183,6,'2022-04-20',1,5,10,40821.10345,6531.376552,47352.48,1),(184,2,'2022-04-21',1,39,3,1613.681034,258.1889655,1871.87,1),(185,6,'2022-04-22',1,34,4,23039.65517,3686.344828,26726,1),(186,1,'2022-04-22',1,39,13,48566.55172,7770.648276,56337.2,1),(187,2,'2022-04-22',1,13,16,21951.34483,3512.215172,25463.56,1),(188,6,'2022-04-23',1,17,18,45705.93103,7312.948966,53018.88,1),(189,3,'2022-04-24',1,24,11,21053.94828,3368.631724,24422.58,1),(190,2,'2022-04-24',1,32,9,49631.82759,7941.092414,57572.92,1),(191,1,'2022-04-24',1,10,9,21518.2069,3442.913103,24961.12,1),(192,2,'2022-04-24',1,19,2,91217.10345,14594.73655,105811.84,1),(193,3,'2022-04-24',1,34,1,74402.32759,11904.37241,86306.7,1),(194,2,'2022-04-26',1,9,8,12760.43103,2041.668966,14802.1,1),(195,4,'2022-04-26',1,21,14,71034.43966,11365.51034,82399.95,1),(196,3,'2022-04-27',1,39,2,6021.448276,963.4317241,6984.88,1),(197,2,'2022-04-30',1,29,10,44657.99138,7145.278621,51803.27,1),(198,5,'2022-04-30',1,36,17,53231.37931,8517.02069,61748.4,1),(199,2,'2022-05-01',1,22,10,6768.103448,1082.896552,7851,1),(200,2,'2022-05-02',1,9,18,105493.0086,16878.88138,122371.89,1),(201,6,'2022-05-02',1,30,15,19982.06897,3197.131034,23179.2,1),(202,2,'2022-05-03',1,27,3,54811.64655,8769.863448,63581.51,1),(203,2,'2022-05-04',1,14,14,19017.24138,3042.758621,22060,1),(204,1,'2022-05-05',1,39,14,7630.215517,1220.834483,8851.05,1),(205,2,'2022-05-06',1,3,11,18542.41379,2966.786207,21509.2,1),(206,6,'2022-05-07',1,29,8,48724.09483,7795.855172,56519.95,1),(207,5,'2022-05-09',1,30,13,61287.18966,9805.950345,71093.14,1),(208,2,'2022-05-09',1,24,17,32067.10345,5130.736552,37197.84,1),(209,6,'2022-05-09',1,10,18,65684.7069,10509.5531,76194.26,1),(210,2,'2022-05-09',1,24,5,61265.94828,9802.551724,71068.5,1),(211,6,'2022-05-10',1,29,20,57125.16379,9140.026207,66265.19,1),(212,3,'2022-05-11',1,24,20,45667.09483,7306.735172,52973.83,1),(213,4,'2022-05-11',1,3,2,35048.2931,5607.726897,40656.02,1),(214,4,'2022-05-11',1,9,12,8160.75,1305.72,9466.47,1),(215,3,'2022-05-12',1,26,5,8986.060345,1437.769655,10423.83,1),(216,2,'2022-05-13',1,3,2,23134.31034,3701.489655,26835.8,1),(217,6,'2022-05-13',1,2,14,55302.23276,8848.357241,64150.59,1),(218,5,'2022-05-14',1,9,8,2336,373.76,2709.76,1),(219,3,'2022-05-14',1,5,5,7314.482759,1170.317241,8484.8,1),(220,3,'2022-05-14',1,8,15,37131.72414,5941.075862,43072.8,1),(221,3,'2022-05-14',1,6,13,10191.7069,1630.673103,11822.38,1),(222,6,'2022-05-15',1,33,8,64451.81034,10312.28966,74764.1,1),(223,2,'2022-05-15',1,37,5,29774.63793,4763.942069,34538.58,1),(224,4,'2022-05-16',1,19,18,17842.52586,2854.804138,20697.33,1),(225,5,'2022-05-17',1,19,8,1720.689655,275.3103448,1996,1),(226,1,'2022-05-17',1,11,11,36070.11207,5771.217931,41841.33,1),(227,1,'2022-05-18',1,38,14,13556.89655,2169.103448,15726,1),(228,3,'2022-05-18',1,38,9,34760.48276,5561.677241,40322.16,1),(229,5,'2022-05-20',1,14,9,63900.53448,10224.08552,74124.62,1),(230,2,'2022-05-20',1,23,18,26963.14655,4314.103448,31277.25,1),(231,4,'2022-05-20',1,26,12,82501.56897,13200.25103,95701.82,1),(232,4,'2022-05-20',1,26,1,18370.2069,2939.233103,21309.44,1),(233,3,'2022-05-20',1,34,3,6676.741379,1068.278621,7745.02,1),(234,3,'2022-05-21',1,40,7,30497.91379,4879.666207,35377.58,1),(235,5,'2022-05-21',1,29,19,50168,8026.88,58194.88,1),(236,3,'2022-05-22',1,39,19,60822.64655,9731.623448,70554.27,1),(237,4,'2022-05-22',1,33,2,34833.85345,5573.416552,40407.27,1),(238,2,'2022-05-23',1,3,13,52144.5431,8343.126897,60487.67,1),(239,2,'2022-05-23',1,7,4,67075.13793,10732.02207,77807.16,1),(240,4,'2022-05-24',1,10,8,19425.23276,3108.037241,22533.27,1),(241,3,'2022-05-24',1,22,9,8261.37931,1321.82069,9583.2,1),(242,3,'2022-05-24',1,38,1,48919.65517,7827.144828,56746.8,1),(243,1,'2022-05-25',1,17,19,9863.146552,1578.103448,11441.25,1),(244,2,'2022-05-26',1,38,3,60625.03448,9700.005517,70325.04,1),(245,6,'2022-05-27',1,29,18,42897.72414,6863.635862,49761.36,1),(246,5,'2022-05-27',1,30,1,24121.25,3859.4,27980.65,1),(247,2,'2022-05-27',1,22,1,26026.24138,4164.198621,30190.44,1),(248,2,'2022-05-28',1,27,14,76222.11207,12195.53793,88417.65,1),(249,2,'2022-05-30',1,34,18,3416.594828,546.6551724,3963.25,1),(250,3,'2022-05-30',1,35,2,40831.2931,6533.006897,47364.3,1),(251,5,'2022-06-01',1,13,17,37040.81034,5926.529655,42967.34,1),(252,4,'2022-06-01',1,22,7,19827.74138,3172.438621,23000.18,1),(253,6,'2022-06-02',1,25,14,82863.86207,13258.21793,96122.08,1),(254,5,'2022-06-02',1,38,12,13810.72414,2209.715862,16020.44,1),(255,6,'2022-06-03',1,6,20,1479.646552,236.7434483,1716.39,1),(256,1,'2022-06-03',1,14,16,68538.81034,10966.20966,79505.02,1),(257,2,'2022-06-05',1,4,19,3000,480,3480,1),(258,1,'2022-06-05',1,28,1,29427.87931,4708.46069,34136.34,1),(259,4,'2022-06-05',1,18,15,32556.2069,5208.993103,37765.2,1),(260,1,'2022-06-05',1,14,16,11444.48276,1831.117241,13275.6,1),(261,5,'2022-06-05',1,18,7,99534.63793,15925.54207,115460.18,1),(262,2,'2022-06-06',1,23,17,47467.89655,7594.863448,55062.76,1),(263,4,'2022-06-06',1,36,15,16048.31897,2567.731034,18616.05,1),(264,1,'2022-06-07',1,37,10,6935.862069,1109.737931,8045.6,1),(265,2,'2022-06-07',1,8,18,57369.60345,9179.136552,66548.74,1),(266,6,'2022-06-08',1,26,1,25977.90517,4156.464828,30134.37,1),(267,1,'2022-06-08',1,23,5,1458.62069,233.3793103,1692,1),(268,2,'2022-06-08',1,40,14,107289.069,17166.25103,124455.32,1),(269,2,'2022-06-09',1,12,3,55165.87931,8826.54069,63992.42,1),(270,4,'2022-06-09',1,32,14,66906.10345,10704.97655,77611.08,1),(271,4,'2022-06-09',1,29,17,51685.86207,8269.737931,59955.6,1),(272,2,'2022-06-09',1,30,1,97616.12931,15618.58069,113234.71,1),(273,5,'2022-06-11',1,13,4,8866.517241,1418.642759,10285.16,1),(274,2,'2022-06-12',1,7,9,36267,5802.72,42069.72,1),(275,5,'2022-06-14',1,36,6,5064.956897,810.3931034,5875.35,1),(276,5,'2022-06-14',1,24,15,76225.18103,12196.02897,88421.21,1),(277,1,'2022-06-14',1,25,11,41585.19828,6653.631724,48238.83,1),(278,2,'2022-06-15',1,13,14,49293,7886.88,57179.88,1),(279,4,'2022-06-15',1,22,20,49861.24138,7977.798621,57839.04,1),(280,4,'2022-06-15',1,4,20,59733.13793,9557.302069,69290.44,1),(281,2,'2022-06-15',1,15,15,6536.206897,1045.793103,7582,1),(282,3,'2022-06-16',1,32,13,11034.82759,1765.572414,12800.4,1),(283,3,'2022-06-18',1,1,5,11397.17241,1823.547586,13220.72,1),(284,6,'2022-06-20',1,15,11,23091.72414,3694.675862,26786.4,1),(285,2,'2022-06-21',1,3,4,70223.5,11235.76,81459.26,1),(286,3,'2022-06-21',1,10,20,446.5517241,71.44827586,518,1),(287,1,'2022-06-22',1,31,3,10705.77586,1712.924138,12418.7,1),(288,5,'2022-06-22',1,37,19,45654.13793,7304.662069,52958.8,1),(289,4,'2022-06-23',1,3,18,38108.49138,6097.358621,44205.85,1),(290,1,'2022-06-24',1,20,8,87225.57759,13956.09241,101181.67,1),(291,2,'2022-06-24',1,34,5,17088,2734.08,19822.08,1),(292,6,'2022-06-25',1,10,13,65139.68966,10422.35034,75562.04,1),(293,4,'2022-06-25',1,38,6,97502.06034,15600.32966,113102.39,1),(294,2,'2022-06-25',1,25,7,42351.25862,6776.201379,49127.46,1),(295,2,'2022-06-25',1,4,11,95810.75862,15329.72138,111140.48,1),(296,3,'2022-06-25',1,34,9,45307.44828,7249.191724,52556.64,1),(297,4,'2022-06-26',1,28,1,35326.11207,5652.177931,40978.29,1),(298,6,'2022-06-26',1,38,7,49710.81897,7953.731034,57664.55,1),(299,2,'2022-06-28',1,38,2,22352,3576.32,25928.32,1),(300,3,'2022-06-28',1,23,19,10972.55172,1755.608276,12728.16,1),(301,3,'2022-06-28',1,27,1,8194.741379,1311.158621,9505.9,1),(302,6,'2022-06-29',1,2,5,32194.7069,5151.153103,37345.86,1),(303,1,'2022-06-29',1,13,12,42844.21552,6855.074483,49699.29,1),(304,4,'2022-06-30',1,27,14,8037.931034,1286.068966,9324,1),(305,1,'2022-06-30',1,8,1,147422.6121,23587.61793,171010.23,1),(306,1,'2022-07-01',1,4,4,93019.44828,14883.11172,107902.56,1),(307,6,'2022-07-03',1,13,7,20934.22414,3349.475862,24283.7,1),(308,6,'2022-07-04',1,1,13,16426.16379,2628.186207,19054.35,1),(309,2,'2022-07-04',1,27,12,8946,1431.36,10377.36,1),(310,2,'2022-07-04',1,39,10,57888.72414,9262.195862,67150.92,1),(311,1,'2022-07-05',1,33,2,42255,6760.8,49015.8,1),(312,6,'2022-07-05',1,11,14,36262.65517,5802.024828,42064.68,1),(313,2,'2022-07-06',1,13,10,47632.58621,7621.213793,55253.8,1),(314,5,'2022-07-06',1,17,8,8191.448276,1310.631724,9502.08,1),(315,2,'2022-07-07',1,5,10,5091.724138,814.6758621,5906.4,1),(316,2,'2022-07-07',1,29,16,93616.25,14978.6,108594.85,1),(317,1,'2022-07-08',1,39,9,24059.88793,3849.582069,27909.47,1),(318,6,'2022-07-09',1,32,10,11882.58621,1901.213793,13783.8,1),(319,6,'2022-07-10',1,40,7,44197.03448,7071.525517,51268.56,1),(320,1,'2022-07-11',1,20,11,21012.53448,3362.005517,24374.54,1),(321,3,'2022-07-11',1,4,2,39891.98276,6382.717241,46274.7,1),(322,2,'2022-07-11',1,11,4,64710.93103,10353.74897,75064.68,1),(323,6,'2022-07-12',1,12,5,103298.5776,16527.77241,119826.35,1),(324,3,'2022-07-13',1,36,11,6640.862069,1062.537931,7703.4,1),(325,2,'2022-07-13',1,13,12,3765.318966,602.4510345,4367.77,1),(326,2,'2022-07-15',1,14,2,78697.65517,12591.62483,91289.28,1),(327,5,'2022-07-17',1,32,11,14168.18103,2266.908966,16435.09,1),(328,3,'2022-07-17',1,3,13,33239.48276,5318.317241,38557.8,1),(329,2,'2022-07-20',1,3,6,946.0862069,151.3737931,1097.46,1),(330,4,'2022-07-20',1,30,4,43031.33621,6885.013793,49916.35,1),(331,5,'2022-07-20',1,15,3,47947.36207,7671.577931,55618.94,1),(332,1,'2022-07-22',1,9,12,19915.72414,3186.515862,23102.24,1),(333,6,'2022-07-22',1,24,4,114881.4828,18381.03724,133262.52,1),(334,4,'2022-07-22',1,14,6,8811.077586,1409.772414,10220.85,1),(335,4,'2022-07-26',1,8,13,61863.12069,9898.09931,71761.22,1),(336,2,'2022-07-26',1,20,1,83198.24138,13311.71862,96509.96,1),(337,6,'2022-07-26',1,1,11,56729.38793,9076.702069,65806.09,1),(338,6,'2022-07-26',1,40,17,2084.655172,333.5448276,2418.2,1),(339,3,'2022-07-27',1,21,13,63439.64655,10150.34345,73589.99,1),(340,3,'2022-07-27',1,14,9,28643.17241,4582.907586,33226.08,1),(341,1,'2022-07-28',1,14,10,27469.07759,4395.052414,31864.13,1),(342,1,'2022-07-28',1,18,12,25352.06897,4056.331034,29408.4,1),(343,1,'2022-07-29',1,23,9,58371.46552,9339.434483,67710.9,1),(344,3,'2022-07-30',1,16,18,51701.94828,8272.311724,59974.26,1),(345,1,'2022-07-30',1,32,16,32717.07759,5234.732414,37951.81,1),(346,1,'2022-07-30',1,30,20,47489.65517,7598.344828,55088,1),(347,4,'2022-07-30',1,16,12,56304.78448,9008.765517,65313.55,1),(348,2,'2022-08-02',1,38,10,21861.46552,3497.834483,25359.3,1),(349,2,'2022-08-02',1,17,19,59548.93966,9527.830345,69076.77,1),(350,2,'2022-08-03',1,5,16,60374.24138,9659.878621,70034.12,1),(351,3,'2022-08-03',1,14,13,97947.31897,15671.57103,113618.89,1),(352,4,'2022-08-04',1,39,8,25847.58621,4135.613793,29983.2,1),(353,2,'2022-08-04',1,7,6,26401.86207,4224.297931,30626.16,1),(354,1,'2022-08-05',1,32,9,41284.71552,6605.554483,47890.27,1),(355,3,'2022-08-05',1,28,13,48802.00862,7808.321379,56610.33,1),(356,1,'2022-08-08',1,14,16,83885.13793,13421.62207,97306.76,1),(357,2,'2022-08-09',1,6,13,10123.01724,1619.682759,11742.7,1),(358,2,'2022-08-09',1,10,15,23130.72414,3700.915862,26831.64,1),(359,1,'2022-08-09',1,7,4,63055.67241,10088.90759,73144.58,1),(360,1,'2022-08-10',1,3,10,35624.07759,5699.852414,41323.93,1),(361,1,'2022-08-11',1,9,19,41897.03448,6703.525517,48600.56,1),(362,4,'2022-08-11',1,27,15,12595.47414,2015.275862,14610.75,1),(363,2,'2022-08-11',1,22,6,42113.43966,6738.150345,48851.59,1),(364,6,'2022-08-11',1,32,18,61619.51724,9859.122759,71478.64,1),(365,4,'2022-08-11',1,18,19,56886.2931,9101.806897,65988.1,1),(366,4,'2022-08-12',1,8,15,30794.39655,4927.103448,35721.5,1),(367,3,'2022-08-12',1,24,17,67553.03448,10808.48552,78361.52,1),(368,4,'2022-08-12',1,1,11,21134.34483,3381.495172,24515.84,1),(369,1,'2022-08-13',1,38,13,14085,2253.6,16338.6,1),(370,1,'2022-08-14',1,29,6,45832.10345,7333.136552,53165.24,1),(371,3,'2022-08-15',1,36,19,30655.86207,4904.937931,35560.8,1),(372,5,'2022-08-15',1,36,17,25424.83621,4067.973793,29492.81,1),(373,2,'2022-08-16',1,34,15,50492.92241,8078.867586,58571.79,1),(374,6,'2022-08-16',1,29,8,30556.08621,4888.973793,35445.06,1),(375,6,'2022-08-17',1,27,14,54359,8697.44,63056.44,1),(376,3,'2022-08-18',1,17,8,16148.2069,2583.713103,18731.92,1),(377,2,'2022-08-19',1,4,2,17416.48276,2786.637241,20203.12,1),(378,6,'2022-08-19',1,23,6,75273.98276,12043.83724,87317.82,1),(379,1,'2022-08-19',1,6,5,37034.48276,5925.517241,42960,1),(380,1,'2022-08-19',1,21,18,6431.37931,1029.02069,7460.4,1),(381,2,'2022-08-23',1,20,17,37686.81034,6029.889655,43716.7,1),(382,1,'2022-08-25',1,37,14,28534.55172,4565.528276,33100.08,1),(383,2,'2022-08-25',1,38,17,81036.94828,12965.91172,94002.86,1),(384,1,'2022-08-25',1,23,6,12144.15517,1943.064828,14087.22,1),(385,6,'2022-08-25',1,38,3,9460.862069,1513.737931,10974.6,1),(386,6,'2022-08-25',1,14,12,46363.10345,7418.096552,53781.2,1),(387,2,'2022-08-28',1,18,3,38335.86207,6133.737931,44469.6,1),(388,6,'2022-08-29',1,37,3,91348.42241,14615.74759,105964.17,1),(389,6,'2022-08-30',1,23,20,23637.65517,3782.024828,27419.68,1),(390,2,'2022-08-30',1,10,5,64192.11207,10270.73793,74462.85,1),(391,2,'2022-09-01',1,7,12,21258.62069,3401.37931,24660,1),(392,2,'2022-09-01',1,21,9,27776.47414,4444.235862,32220.71,1),(393,2,'2022-09-02',1,38,13,42500.13793,6800.022069,49300.16,1),(394,5,'2022-09-04',1,7,18,27633.10345,4421.296552,32054.4,1),(395,2,'2022-09-05',1,33,7,40527.43966,6484.390345,47011.83,1),(396,5,'2022-09-05',1,35,19,5710.344828,913.6551724,6624,1),(397,2,'2022-09-06',1,16,6,56288.12931,9006.10069,65294.23,1),(398,2,'2022-09-06',1,37,6,30451.06897,4872.171034,35323.24,1),(399,3,'2022-09-06',1,23,18,45206.22414,7232.995862,52439.22,1),(400,6,'2022-09-09',1,25,9,2141.37931,342.6206897,2484,1),(401,2,'2022-09-09',1,37,14,177020.9397,28323.35034,205344.29,1),(402,4,'2022-09-10',1,11,5,49153.16379,7864.506207,57017.67,1),(403,3,'2022-09-13',1,4,13,62460.93103,9993.748966,72454.68,1),(404,1,'2022-09-13',1,21,13,51428.40517,8228.544828,59656.95,1),(405,5,'2022-09-14',1,35,2,26567.30172,4250.768276,30818.07,1),(406,1,'2022-09-14',1,22,20,100214.6121,16034.33793,116248.95,1),(407,3,'2022-09-15',1,27,14,50000.35345,8000.056552,58000.41,1),(408,2,'2022-09-15',1,19,4,38070.64655,6091.303448,44161.95,1),(409,2,'2022-09-15',1,36,18,22239.84483,3558.375172,25798.22,1),(410,2,'2022-09-18',1,27,3,63937.55172,10230.00828,74167.56,1),(411,2,'2022-09-19',1,7,11,93972.32759,15035.57241,109007.9,1),(412,3,'2022-09-19',1,36,9,56895,9103.2,65998.2,1),(413,2,'2022-09-20',1,12,4,783.6982759,125.3917241,909.09,1),(414,2,'2022-09-20',1,27,16,39857.84483,6377.255172,46235.1,1),(415,6,'2022-09-21',1,30,3,50425.55172,8068.088276,58493.64,1),(416,1,'2022-09-21',1,24,6,27508.88793,4401.422069,31910.31,1),(417,2,'2022-09-22',1,33,19,19276.24138,3084.198621,22360.44,1),(418,2,'2022-09-22',1,29,11,4306.241379,688.9986207,4995.24,1),(419,2,'2022-09-23',1,38,19,14241.10345,2278.576552,16519.68,1),(420,2,'2022-09-24',1,12,11,106696.1379,17071.38207,123767.52,1),(421,2,'2022-09-24',1,10,18,81613.60345,13058.17655,94671.78,1),(422,4,'2022-09-24',1,22,18,16131.41379,2581.026207,18712.44,1),(423,6,'2022-09-25',1,32,2,130337.6207,20854.01931,151191.64,1),(424,1,'2022-09-25',1,35,10,61864.65517,9898.344828,71763,1),(425,6,'2022-09-25',1,15,4,13699.68103,2191.948966,15891.63,1),(426,3,'2022-09-26',1,39,18,16032.41379,2565.186207,18597.6,1),(427,1,'2022-09-28',1,4,10,43527.98276,6964.477241,50492.46,1),(428,5,'2022-09-28',1,38,20,60371.47414,9659.435862,70030.91,1),(429,6,'2022-09-29',1,31,16,27501.51724,4400.242759,31901.76,1),(430,2,'2022-09-29',1,11,19,4578.12931,732.5006897,5310.63,1),(431,5,'2022-09-29',1,38,5,25560.43103,4089.668966,29650.1,1),(432,3,'2022-09-30',1,15,5,61077.15517,9772.344828,70849.5,1),(433,2,'2022-09-30',1,32,1,83245.22414,13319.23586,96564.46,1),(434,4,'2022-10-01',1,26,12,48550.91379,7768.146207,56319.06,1),(435,4,'2022-10-02',1,17,17,32957.41379,5273.186207,38230.6,1),(436,1,'2022-10-03',1,14,13,13946.55172,2231.448276,16178,1),(437,3,'2022-10-03',1,4,13,50076.88793,8012.302069,58089.19,1),(438,1,'2022-10-04',1,23,13,59101.77586,9456.284138,68558.06,1),(439,1,'2022-10-04',1,22,14,33775.64655,5404.103448,39179.75,1),(440,5,'2022-10-05',1,4,20,64835.55172,10373.68828,75209.24,1),(441,5,'2022-10-05',1,3,2,16360.06897,2617.611034,18977.68,1),(442,6,'2022-10-06',1,19,1,49035.5,7845.68,56881.18,1),(443,3,'2022-10-07',1,32,8,66202.41379,10592.38621,76794.8,1),(444,2,'2022-10-07',1,15,7,31569.17241,5051.067586,36620.24,1),(445,5,'2022-10-08',1,15,20,4612.413793,737.9862069,5350.4,1),(446,4,'2022-10-08',1,18,7,51140.55172,8182.488276,59323.04,1),(447,3,'2022-10-09',1,24,6,52959.10345,8473.456552,61432.56,1),(448,1,'2022-10-09',1,10,3,53705.37069,8592.85931,62298.23,1),(449,2,'2022-10-10',1,26,11,34255.60345,5480.896552,39736.5,1),(450,1,'2022-10-10',1,7,6,111121.1466,17779.38345,128900.53,1),(451,3,'2022-10-10',1,13,5,30486.2069,4877.793103,35364,1),(452,2,'2022-10-11',1,29,6,88479.91379,14156.78621,102636.7,1),(453,1,'2022-10-12',1,40,2,45663.17241,7306.107586,52969.28,1),(454,3,'2022-10-12',1,3,10,59899.08621,9583.853793,69482.94,1),(455,6,'2022-10-12',1,39,5,28554.48276,4568.717241,33123.2,1),(456,6,'2022-10-13',1,2,5,35571.55172,5691.448276,41263,1),(457,5,'2022-10-13',1,37,19,130044.9655,20807.19448,150852.16,1),(458,2,'2022-10-14',1,8,11,63576.15517,10172.18483,73748.34,1),(459,1,'2022-10-15',1,28,2,43781.86207,7005.097931,50786.96,1),(460,2,'2022-10-15',1,18,9,48949,7831.84,56780.84,1),(461,2,'2022-10-15',1,14,11,80894.68966,12943.15034,93837.84,1),(462,5,'2022-10-15',1,39,17,96726.34483,15476.21517,112202.56,1),(463,4,'2022-10-15',1,30,20,5420.103448,867.2165517,6287.32,1),(464,3,'2022-10-15',1,31,3,15312,2449.92,17761.92,1),(465,1,'2022-10-15',1,23,14,31465.12931,5034.42069,36499.55,1),(466,3,'2022-10-17',1,1,3,38194.89655,6111.183448,44306.08,1),(467,6,'2022-10-18',1,38,9,12264.39655,1962.303448,14226.7,1),(468,4,'2022-10-19',1,21,8,2750.948276,440.1517241,3191.1,1),(469,3,'2022-10-19',1,35,20,53157.15517,8505.144828,61662.3,1),(470,2,'2022-10-20',1,20,2,35805.96552,5728.954483,41534.92,1),(471,2,'2022-10-20',1,38,17,12155.17241,1944.827586,14100,1),(472,2,'2022-10-20',1,36,1,50713.37069,8114.13931,58827.51,1),(473,6,'2022-10-20',1,40,7,26866.12069,4298.57931,31164.7,1),(474,3,'2022-10-20',1,18,6,67067.03448,10730.72552,77797.76,1),(475,6,'2022-10-20',1,33,6,24347.89655,3895.663448,28243.56,1),(476,2,'2022-10-20',1,5,3,93039.23276,14886.27724,107925.51,1),(477,5,'2022-10-20',1,16,5,51555.60345,8248.896552,59804.5,1),(478,5,'2022-10-21',1,19,18,20638.62069,3302.17931,23940.8,1),(479,6,'2022-10-21',1,39,12,37372.96552,5979.674483,43352.64,1),(480,3,'2022-10-21',1,35,17,20711.41379,3313.826207,24025.24,1),(481,1,'2022-10-21',1,29,4,33135.23276,5301.637241,38436.87,1),(482,4,'2022-10-22',1,8,12,118467.1379,18954.74207,137421.88,1),(483,2,'2022-10-23',1,6,4,9413.965517,1506.234483,10920.2,1),(484,4,'2022-10-24',1,33,11,48157.4569,7705.193103,55862.65,1),(485,1,'2022-10-25',1,34,14,10623.68103,1699.788966,12323.47,1),(486,4,'2022-10-25',1,25,13,69624.5431,11139.9269,80764.47,1),(487,6,'2022-10-27',1,1,17,35389.10345,5662.256552,41051.36,1),(488,2,'2022-10-27',1,39,3,4112.068966,657.9310345,4770,1),(489,3,'2022-10-28',1,8,4,26575.86207,4252.137931,30828,1),(490,4,'2022-10-28',1,39,9,22900.86207,3664.137931,26565,1),(491,1,'2022-10-29',1,21,16,68816.08621,11010.57379,79826.66,1),(492,6,'2022-10-29',1,22,1,5377.913793,860.4662069,6238.38,1),(493,5,'2022-11-01',1,1,9,74590.2069,11934.4331,86524.64,1),(494,2,'2022-11-01',1,25,15,67046.68966,10727.47034,77774.16,1),(495,3,'2022-11-02',1,9,11,9694.396552,1551.103448,11245.5,1),(496,2,'2022-11-03',1,37,7,20420.68966,3267.310345,23688,1),(497,1,'2022-11-03',1,17,6,49523.16379,7923.706207,57446.87,1),(498,6,'2022-11-04',1,14,14,71512.24138,11441.95862,82954.2,1),(499,2,'2022-11-04',1,24,5,17967.7931,2874.846897,20842.64,1),(500,3,'2022-11-04',1,23,13,36997.42241,5919.587586,42917.01,1),(501,4,'2022-11-05',1,17,1,29835.74138,4773.718621,34609.46,1),(502,4,'2022-11-05',1,37,12,14850.05172,2376.008276,17226.06,1),(503,2,'2022-11-06',1,35,9,37767.80172,6042.848276,43810.65,1),(504,2,'2022-11-06',1,1,14,15786.2069,2525.793103,18312,1),(505,2,'2022-11-06',1,26,8,34847.86207,5575.657931,40423.52,1),(506,1,'2022-11-06',1,21,13,1402.775862,224.4441379,1627.22,1),(507,6,'2022-11-07',1,14,9,56303.00862,9008.481379,65311.49,1),(508,1,'2022-11-07',1,18,12,3887.896552,622.0634483,4509.96,1),(509,2,'2022-11-08',1,12,18,29451.72414,4712.275862,34164,1),(510,3,'2022-11-09',1,23,3,4017.655172,642.8248276,4660.48,1),(511,3,'2022-11-09',1,7,4,7394.439655,1183.110345,8577.55,1),(512,5,'2022-11-10',1,6,20,25825.65517,4132.104828,29957.76,1),(513,2,'2022-11-10',1,6,2,54506.03448,8720.965517,63227,1),(514,1,'2022-11-10',1,17,7,64656.08621,10344.97379,75001.06,1),(515,4,'2022-11-10',1,17,18,29787.18103,4765.948966,34553.13,1),(516,6,'2022-11-10',1,34,12,20250.88793,3240.142069,23491.03,1),(517,6,'2022-11-11',1,35,17,117811.0345,18849.76552,136660.8,1),(518,2,'2022-11-11',1,36,5,57006.41379,9121.026207,66127.44,1),(519,6,'2022-11-11',1,20,12,85418.5431,13666.9669,99085.51,1),(520,3,'2022-11-12',1,7,5,36231.28448,5797.005517,42028.29,1),(521,6,'2022-11-12',1,14,4,76140.68966,12182.51034,88323.2,1),(522,4,'2022-11-13',1,23,14,80323.60345,12851.77655,93175.38,1),(523,6,'2022-11-15',1,27,17,53775.88793,8604.142069,62380.03,1),(524,2,'2022-11-15',1,2,11,82915.40517,13266.46483,96181.87,1),(525,2,'2022-11-15',1,29,17,21374.13793,3419.862069,24794,1),(526,1,'2022-11-16',1,24,14,82792.25862,13246.76138,96039.02,1),(527,6,'2022-11-16',1,27,9,71871.0431,11499.3669,83370.41,1),(528,2,'2022-11-18',1,21,14,7193.948276,1151.031724,8344.98,1),(529,6,'2022-11-18',1,24,12,14384.51724,2301.522759,16686.04,1),(530,3,'2022-11-18',1,17,13,31370.44828,5019.271724,36389.72,1),(531,2,'2022-11-19',1,40,2,51536.03448,8245.765517,59781.8,1),(532,2,'2022-11-20',1,31,9,45150.66379,7224.106207,52374.77,1),(533,5,'2022-11-20',1,35,4,94449.86207,15111.97793,109561.84,1),(534,6,'2022-11-21',1,7,10,22279.34483,3564.695172,25844.04,1),(535,2,'2022-11-21',1,23,3,30736.55172,4917.848276,35654.4,1),(536,6,'2022-11-22',1,12,8,44773.65517,7163.784828,51937.44,1),(537,1,'2022-11-22',1,36,19,51709.55172,8273.528276,59983.08,1),(538,2,'2022-11-22',1,35,15,39896.55172,6383.448276,46280,1),(539,2,'2022-11-23',1,11,3,9411.5,1505.84,10917.34,1),(540,5,'2022-11-24',1,12,8,6776.017241,1084.162759,7860.18,1),(541,4,'2022-11-24',1,30,10,30534.12069,4885.45931,35419.58,1),(542,2,'2022-11-24',1,9,14,122252.3621,19560.37793,141812.74,1),(543,1,'2022-11-24',1,30,18,144517.8966,23122.86345,167640.76,1),(544,4,'2022-11-25',1,12,4,18119.89655,2899.183448,21019.08,1),(545,5,'2022-11-25',1,4,17,318.6206897,50.97931034,369.6,1),(546,1,'2022-11-25',1,13,12,21196.55172,3391.448276,24588,1),(547,3,'2022-11-26',1,16,16,35787.56897,5726.011034,41513.58,1),(548,4,'2022-11-27',1,5,17,13671.72414,2187.475862,15859.2,1),(549,4,'2022-11-28',1,8,19,27691.03448,4430.565517,32121.6,1),(550,2,'2022-11-28',1,9,16,73197.24138,11711.55862,84908.8,1),(551,3,'2022-11-28',1,31,5,70983.38793,11357.34207,82340.73,1),(552,1,'2022-11-28',1,17,11,45691.91379,7310.706207,53002.62,1),(553,2,'2022-11-28',1,33,9,68859.51724,11017.52276,79877.04,1),(554,6,'2022-11-28',1,7,17,19983.63793,3197.382069,23181.02,1),(555,1,'2022-11-29',1,30,17,35274.82759,5643.972414,40918.8,1),(556,6,'2022-11-29',1,20,6,7393.103448,1182.896552,8576,1),(557,1,'2022-11-29',1,14,7,14787.81034,2366.049655,17153.86,1),(558,4,'2022-11-29',1,30,20,59884,9581.44,69465.44,1),(559,2,'2022-11-30',1,17,9,77739.00862,12438.24138,90177.25,1),(560,1,'2022-11-30',1,3,18,51314.31034,8210.289655,59524.6,1),(561,5,'2022-11-30',1,2,3,38873.13793,6219.702069,45092.84,1),(562,2,'2022-12-01',1,16,1,31915.48276,5106.477241,37021.96,1),(563,5,'2022-12-01',1,12,11,38660.53448,6185.685517,44846.22,1),(564,1,'2022-12-01',1,24,16,50049.58621,8007.933793,58057.52,1),(565,6,'2022-12-02',1,31,10,86811.93103,13889.90897,100701.84,1),(566,6,'2022-12-03',1,39,10,18403.2069,2944.513103,21347.72,1),(567,2,'2022-12-03',1,18,8,46702.62931,7472.42069,54175.05,1),(568,1,'2022-12-03',1,12,2,44723.43103,7155.748966,51879.18,1),(569,3,'2022-12-04',1,8,13,46638.10345,7462.096552,54100.2,1),(570,3,'2022-12-05',1,13,11,29863.42241,4778.147586,34641.57,1),(571,2,'2022-12-06',1,4,16,44388.55172,7102.168276,51490.72,1),(572,2,'2022-12-06',1,37,16,112735.7586,18037.72138,130773.48,1),(573,4,'2022-12-06',1,30,13,8137.931034,1302.068966,9440,1),(574,2,'2022-12-07',1,38,8,55517.35345,8882.776552,64400.13,1),(575,2,'2022-12-07',1,22,17,20720.24138,3315.238621,24035.48,1),(576,3,'2022-12-08',1,2,18,10326.96552,1652.314483,11979.28,1),(577,1,'2022-12-09',1,9,15,62832.89655,10053.26345,72886.16,1),(578,1,'2022-12-09',1,9,15,93314.65517,14930.34483,108245,1),(579,2,'2022-12-09',1,27,10,57783.43103,9245.348966,67028.78,1),(580,2,'2022-12-10',1,1,19,4485.818966,717.7310345,5203.55,1),(581,3,'2022-12-11',1,16,4,33497.7931,5359.646897,38857.44,1),(582,2,'2022-12-11',1,11,3,55786.01724,8925.762759,64711.78,1),(583,4,'2022-12-11',1,14,8,6997.068966,1119.531034,8116.6,1),(584,1,'2022-12-12',1,26,2,41991.51724,6718.642759,48710.16,1),(585,2,'2022-12-13',1,13,20,68599.63793,10975.94207,79575.58,1),(586,1,'2022-12-13',1,7,17,117988.7672,18878.20276,136866.97,1),(587,2,'2022-12-14',1,27,6,50175.68103,8028.108966,58203.79,1),(588,2,'2022-12-14',1,15,2,42799.25,6847.88,49647.13,1),(589,5,'2022-12-14',1,13,14,99090.06897,15854.41103,114944.48,1),(590,3,'2022-12-14',1,17,9,15503.62069,2480.57931,17984.2,1),(591,4,'2022-12-14',1,13,10,28934.7069,4629.553103,33564.26,1),(592,1,'2022-12-14',1,40,17,43199.82759,6911.972414,50111.8,1),(593,2,'2022-12-16',1,22,16,59002.05172,9440.328276,68442.38,1),(594,6,'2022-12-17',1,3,3,43875.06897,7020.011034,50895.08,1),(595,5,'2022-12-17',1,25,20,44634.58621,7141.533793,51776.12,1),(596,3,'2022-12-17',1,12,16,72043.23276,11526.91724,83570.15,1),(597,5,'2022-12-18',1,11,6,116662.5,18666,135328.5,1),(598,1,'2022-12-19',1,34,11,31576.7931,5052.286897,36629.08,1),(599,6,'2022-12-21',1,7,9,115646.3534,18503.41655,134149.77,1),(600,1,'2022-12-21',1,25,14,4758.62069,761.3793103,5520,1),(601,2,'2022-12-22',1,24,7,104259.7414,16681.55862,120941.3,1),(602,2,'2022-12-22',1,27,19,4937.586207,790.0137931,5727.6,1),(603,1,'2022-12-23',1,33,19,10215.31034,1634.449655,11849.76,1),(604,1,'2022-12-24',1,12,5,83001.25,13280.2,96281.45,1),(605,2,'2022-12-24',1,40,4,28822.80172,4611.648276,33434.45,1),(606,1,'2022-12-25',1,6,20,7448.275862,1191.724138,8640,1),(607,3,'2022-12-27',1,24,3,65533.58621,10485.37379,76018.96,1),(608,3,'2022-12-27',1,31,9,10728.36207,1716.537931,12444.9,1),(609,3,'2022-12-28',1,26,13,32352.08621,5176.333793,37528.42,1),(610,5,'2022-12-28',1,15,15,30065.51724,4810.482759,34876,1),(611,2,'2022-12-28',1,11,2,23421.18103,3747.388966,27168.57,1),(612,2,'2022-12-29',1,16,15,9711.551724,1553.848276,11265.4,1),(613,1,'2022-12-30',1,8,8,56581.02586,9052.964138,65633.99,1),(614,1,'2023-12-11',1,33,4,6693.1,1070.9,7764,1),(615,1,'2023-12-11',1,33,12,1156.9,185.1,1342,1);
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
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La categoría ya se encuentra inactiva';
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
    IN nombre_a VARCHAR(30),
    IN descripcion_a VARCHAR(255),
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
        cantidad_mayoreo
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
        cant_m
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
    IN nombre_a VARCHAR(30),
    IN descripcion_a VARCHAR(255),
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `ver_clientes`()
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
	INNER JOIN sub_cuentas_tercer_nivel ON sub_cuentas_tercer_nivel.id_cuenta = cliente.id_cuenta_contable;
    
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

-- Dump completed on 2024-01-02  0:26:30
