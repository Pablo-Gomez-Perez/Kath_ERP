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
  PRIMARY KEY (`id_articulo`),
  UNIQUE KEY `codigo_articulo` (`codigo_articulo`),
  KEY `id_proveedor` (`id_proveedor`),
  KEY `id_categoria` (`id_categoria`),
  CONSTRAINT `articulo_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`) ON UPDATE CASCADE,
  CONSTRAINT `articulo_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_producto` (`id_categoria`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=274 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articulo`
--

LOCK TABLES `articulo` WRITE;
/*!40000 ALTER TABLE `articulo` DISABLE KEYS */;
INSERT INTO `articulo` VALUES (1,'6308156511',5,8,'53131600','producto de prueba 1','este es un producto de prueba numero 1',0,229,458,398.46,12),(2,'6353928792',1,8,'53131600','producto de prueba 2','este es un producto de prueba numero 2',0,1185,2370,2061.9,8),(3,'2714369471',19,3,'53131600','producto de prueba 3','este es un producto de prueba numero 3',0,265,530,461.1,7),(4,'991281012',12,9,'53131600','producto de prueba 4','este es un producto de prueba numero 4',0,1410,2820,2453.4,10),(5,'170577547',12,5,'53131600','producto de prueba 5','este es un producto de prueba numero 5',0,1216,2432,2115.84,11),(6,'3561211483',10,7,'53131600','producto de prueba 6','este es un producto de prueba numero 6',0,141,282,245.34,11),(7,'7557085886',8,12,'53131600','producto de prueba 7','este es un producto de prueba numero 7',0,654,1308,1137.96,6),(8,'4536011527',25,8,'53131600','producto de prueba 8','este es un producto de prueba numero 8',0,103,206,179.22,7),(9,'6302982072',8,11,'53131600','producto de prueba 9','este es un producto de prueba numero 9',0,998,1996,1736.52,11),(10,'112021197',24,7,'53131600','producto de prueba 10','este es un producto de prueba numero 10',0,259,518,450.66,6),(11,'4943051525',5,11,'53131600','producto de prueba 11','este es un producto de prueba numero 11',0,466,932,810.84,15),(12,'7411366789',13,14,'53131600','producto de prueba 12','este es un producto de prueba numero 12',0,530,1060,922.2,7),(13,'789614018',3,11,'53131600','producto de prueba 13','este es un producto de prueba numero 13',0,506,1012,880.44,14),(14,'687131253',12,1,'53131600','producto de prueba 14','este es un producto de prueba numero 14',0,1392,2784,2422.08,11),(15,'1408572448',14,14,'53131600','producto de prueba 15','este es un producto de prueba numero 15',0,145,290,252.3,8),(16,'2424427361',11,8,'53131600','producto de prueba 16','este es un producto de prueba numero 16',0,1193,2386,2075.82,8),(17,'1507869333',24,7,'53131600','producto de prueba 17','este es un producto de prueba numero 17',0,1275,2550,2218.5,14),(18,'620175007',4,2,'53131600','producto de prueba 18','este es un producto de prueba numero 18',0,1109,2218,1929.66,7),(19,'9057093161',12,11,'53131600','producto de prueba 19','este es un producto de prueba numero 19',0,960,1920,1670.4,13),(20,'382986849',20,9,'53131600','producto de prueba 20','este es un producto de prueba numero 20',0,1418,2836,2467.32,12),(21,'726695035',17,12,'53131600','producto de prueba 21','este es un producto de prueba numero 21',0,1385,2770,2409.9,14),(22,'273361305',14,13,'53131600','producto de prueba 22','este es un producto de prueba numero 22',0,276,552,480.24,13),(23,'316535400',4,9,'53131600','producto de prueba 23','este es un producto de prueba numero 23',0,1120,2240,1948.8,9),(24,'339321491',22,4,'53131600','producto de prueba 24','este es un producto de prueba numero 24',0,401,802,697.74,13),(25,'4474820310',3,1,'53131600','producto de prueba 25','este es un producto de prueba numero 25',0,1030,2060,1792.2,8),(26,'6766890378',14,11,'53131600','producto de prueba 26','este es un producto de prueba numero 26',0,347,694,603.78,10),(27,'9618173344',15,4,'53131600','producto de prueba 27','este es un producto de prueba numero 27',0,437,874,760.38,12),(28,'6712913178',22,2,'53131600','producto de prueba 28','este es un producto de prueba numero 28',0,215,430,374.1,14),(29,'4535448809',21,1,'53131600','producto de prueba 29','este es un producto de prueba numero 29',0,399,798,694.26,11),(30,'6650072739',6,4,'53131600','producto de prueba 30','este es un producto de prueba numero 30',0,416,832,723.84,7),(31,'918469346',22,14,'53131600','producto de prueba 31','este es un producto de prueba numero 31',0,590,1180,1026.6,10),(32,'3956527410',6,11,'53131600','producto de prueba 32','este es un producto de prueba numero 32',0,142,284,247.08,9),(33,'2945449610',4,11,'53131600','producto de prueba 33','este es un producto de prueba numero 33',0,1372,2744,2387.28,6),(34,'4186956417',13,11,'53131600','producto de prueba 34','este es un producto de prueba numero 34',0,1260,2520,2192.4,7),(35,'7116414346',12,14,'53131600','producto de prueba 35','este es un producto de prueba numero 35',0,383,766,666.42,7),(36,'2662680632',17,1,'53131600','producto de prueba 36','este es un producto de prueba numero 36',0,1393,2786,2423.82,14),(37,'7400391319',14,3,'53131600','producto de prueba 37','este es un producto de prueba numero 37',0,1175,2350,2044.5,10),(38,'6693770542',3,13,'53131600','producto de prueba 38','este es un producto de prueba numero 38',0,333,666,579.42,13),(39,'8638541969',10,4,'53131600','producto de prueba 39','este es un producto de prueba numero 39',0,413,826,718.62,7),(40,'1691630121',4,3,'53131600','producto de prueba 40','este es un producto de prueba numero 40',0,699,1398,1216.26,7),(41,'1838738431',4,6,'53131600','producto de prueba 41','este es un producto de prueba numero 41',0,878,1756,1527.72,14),(42,'7182875265',9,1,'53131600','producto de prueba 42','este es un producto de prueba numero 42',0,911,1822,1585.14,15),(43,'3980592284',25,15,'53131600','producto de prueba 43','este es un producto de prueba numero 43',0,378,756,657.72,14),(44,'5255512832',21,10,'53131600','producto de prueba 44','este es un producto de prueba numero 44',0,1177,2354,2047.98,9),(45,'488782469',22,3,'53131600','producto de prueba 45','este es un producto de prueba numero 45',0,1312,2624,2282.88,15),(46,'2607341609',14,8,'53131600','producto de prueba 46','este es un producto de prueba numero 46',0,797,1594,1386.78,14),(47,'5212578037',7,10,'53131600','producto de prueba 47','este es un producto de prueba numero 47',0,360,720,626.4,9),(48,'1361099607',10,10,'53131600','producto de prueba 48','este es un producto de prueba numero 48',0,231,462,401.94,13),(49,'775332094',12,10,'53131600','producto de prueba 49','este es un producto de prueba numero 49',0,959,1918,1668.66,9),(50,'904544303',8,9,'53131600','producto de prueba 50','este es un producto de prueba numero 50',0,826,1652,1437.24,15),(51,'3622914630',23,7,'53131600','producto de prueba 51','este es un producto de prueba numero 51',0,1191,2382,2072.34,15),(52,'2279994066',10,12,'53131600','producto de prueba 52','este es un producto de prueba numero 52',0,395,790,687.3,14),(53,'9516169646',11,12,'53131600','producto de prueba 53','este es un producto de prueba numero 53',0,1372,2744,2387.28,8),(54,'8688032229',14,2,'53131600','producto de prueba 54','este es un producto de prueba numero 54',0,659,1318,1146.66,12),(55,'2025784483',15,7,'53131600','producto de prueba 55','este es un producto de prueba numero 55',0,1022,2044,1778.28,7),(56,'3425595146',7,15,'53131600','producto de prueba 56','este es un producto de prueba numero 56',0,1435,2870,2496.9,8),(57,'7844944742',4,2,'53131600','producto de prueba 57','este es un producto de prueba numero 57',0,1263,2526,2197.62,12),(58,'6235320600',22,11,'53131600','producto de prueba 58','este es un producto de prueba numero 58',0,1262,2524,2195.88,13),(59,'9338362463',11,3,'53131600','producto de prueba 59','este es un producto de prueba numero 59',0,484,968,842.16,13),(60,'2724882931',2,12,'53131600','producto de prueba 60','este es un producto de prueba numero 60',0,287,574,499.38,14),(61,'9449511889',10,12,'53131600','producto de prueba 61','este es un producto de prueba numero 61',0,1425,2850,2479.5,12),(62,'6028115898',17,1,'53131600','producto de prueba 62','este es un producto de prueba numero 62',0,553,1106,962.22,6),(63,'6857910633',25,3,'53131600','producto de prueba 63','este es un producto de prueba numero 63',0,223,446,388.02,12),(64,'4874037838',3,1,'53131600','producto de prueba 64','este es un producto de prueba numero 64',0,1233,2466,2145.42,13),(65,'3349062176',6,1,'53131600','producto de prueba 65','este es un producto de prueba numero 65',0,359,718,624.66,6),(66,'3954891843',3,6,'53131600','producto de prueba 66','este es un producto de prueba numero 66',0,536,1072,932.64,14),(67,'5845654682',11,1,'53131600','producto de prueba 67','este es un producto de prueba numero 67',0,697,1394,1212.78,7),(68,'642872922',17,1,'53131600','producto de prueba 68','este es un producto de prueba numero 68',0,1033,2066,1797.42,8),(69,'3180943050',22,6,'53131600','producto de prueba 69','este es un producto de prueba numero 69',0,2870,3357.9,3022.11,9),(70,'5028849193',14,5,'53131600','producto de prueba 70','este es un producto de prueba numero 70',0,929,1049.77,997.2815,15),(71,'8196736116',13,14,'53131600','producto de prueba 71','este es un producto de prueba numero 71',0,2819,3100.9,2852.828,9),(72,'207985049',21,9,'53131600','producto de prueba 72','este es un producto de prueba numero 72',0,1388,1540.68,1463.646,15),(73,'5869616498',20,10,'53131600','producto de prueba 73','este es un producto de prueba numero 73',0,1688,1890.56,1758.2208,15),(74,'5539329872',5,4,'53131600','producto de prueba 74','este es un producto de prueba numero 74',0,375,412.5,379.5,7),(75,'4884141152',2,6,'53131600','producto de prueba 75','este es un producto de prueba numero 75',0,861,998.76,898.884,12),(76,'6489123605',14,15,'53131600','producto de prueba 76','este es un producto de prueba numero 76',0,672,752.64,677.376,15),(77,'2007649196',24,4,'53131600','producto de prueba 77','este es un producto de prueba numero 77',0,1424,1651.84,1519.6928,15),(78,'962842387',3,15,'53131600','producto de prueba 78','este es un producto de prueba numero 78',0,2862,3262.68,3001.6656,10),(79,'8863336746',14,13,'53131600','producto de prueba 79','este es un producto de prueba numero 79',0,1424,1609.12,1448.208,14),(80,'8664622038',22,10,'53131600','producto de prueba 80','este es un producto de prueba numero 80',0,633,740.61,696.1734,9),(81,'9913763536',2,13,'53131600','producto de prueba 81','este es un producto de prueba numero 81',0,681,769.53,723.3582,15),(82,'639735581',15,9,'53131600','producto de prueba 82','este es un producto de prueba numero 82',0,2032,2357.12,2239.264,12),(83,'989231901',10,10,'53131600','producto de prueba 83','este es un producto de prueba numero 83',0,967,1063.7,989.241,10),(84,'5841844899',8,8,'53131600','producto de prueba 84','este es un producto de prueba numero 84',0,928,1104.32,993.888,7),(85,'4786696403',4,3,'53131600','producto de prueba 85','este es un producto de prueba numero 85',0,2870,3357.9,3156.426,14),(86,'6734644294',24,9,'53131600','producto de prueba 86','este es un producto de prueba numero 86',0,1746,1972.98,1795.4118,6),(87,'3991333355',2,8,'53131600','producto de prueba 87','este es un producto de prueba numero 87',0,2117,2371.04,2252.488,10),(88,'425563866',14,14,'53131600','producto de prueba 88','este es un producto de prueba numero 88',0,111,132.09,120.2019,12),(89,'4429544164',15,11,'53131600','producto de prueba 89','este es un producto de prueba numero 89',0,1964,2278.24,2095.9808,12),(90,'8368196433',22,13,'53131600','producto de prueba 90','este es un producto de prueba numero 90',0,1284,1412.4,1327.656,8),(91,'5761828235',9,11,'53131600','producto de prueba 91','este es un producto de prueba numero 91',0,1426,1582.86,1503.717,8),(92,'3695671897',14,6,'53131600','producto de prueba 92','este es un producto de prueba numero 92',0,421,463.1,439.945,11),(93,'7063411618',20,10,'53131600','producto de prueba 93','este es un producto de prueba numero 93',0,2694,3232.8,2974.176,9),(94,'6362746339',9,2,'53131600','producto de prueba 94','este es un producto de prueba numero 94',0,1134,1292.76,1202.2668,13),(95,'7294356437',5,4,'53131600','producto de prueba 95','este es un producto de prueba numero 95',0,111,122.1,111.111,15),(96,'310061324',12,4,'53131600','producto de prueba 96','este es un producto de prueba numero 96',0,2629,3154.8,2839.32,15),(97,'6024919713',4,3,'53131600','producto de prueba 97','este es un producto de prueba numero 97',0,275,316.25,290.95,14),(98,'1949080705',21,15,'53131600','producto de prueba 98','este es un producto de prueba numero 98',0,2991,3469.56,3296.082,15),(99,'562217106',23,14,'53131600','producto de prueba 99','este es un producto de prueba numero 99',0,414,496.8,466.992,6),(100,'4391189109',9,8,'53131600','producto de prueba 100','este es un producto de prueba numero 100',0,2336,2709.76,2492.9792,15),(101,'578705407',8,10,'53131600','producto de prueba 101','este es un producto de prueba numero 101',0,2706,3138.96,2950.6224,9),(102,'2851581072',18,11,'53131600','producto de prueba 102','este es un producto de prueba numero 102',0,1912,2141.44,2034.368,6),(103,'6615817026',14,15,'53131600','producto de prueba 103','este es un producto de prueba numero 103',0,2972,3328.64,3062.3488,9),(104,'3855929178',7,7,'53131600','producto de prueba 104','este es un producto de prueba numero 104',0,707,820.12,779.114,6),(105,'7361440589',16,10,'53131600','producto de prueba 105','este es un producto de prueba numero 105',0,938,1069.32,983.7744,7),(106,'3185448667',3,9,'53131600','producto de prueba 106','este es un producto de prueba numero 106',0,2994,3562.86,3242.2026,10),(107,'6296865881',6,15,'53131600','producto de prueba 107','este es un producto de prueba numero 107',0,2921,3388.36,3083.4076,11),(108,'7245775475',6,7,'53131600','producto de prueba 108','este es un producto de prueba numero 108',0,1908,2136.96,2008.7424,13),(109,'8353637369',11,11,'53131600','producto de prueba 109','este es un producto de prueba numero 109',0,2709,3115.35,2834.9685,14),(110,'3352924748',13,7,'53131600','producto de prueba 110','este es un producto de prueba numero 110',0,2219,2463.09,2241.4119,8),(111,'4003835349',7,12,'53131600','producto de prueba 111','este es un producto de prueba numero 111',0,2852,3165.72,2880.8052,9),(112,'6078880106',12,15,'53131600','producto de prueba 112','este es un producto de prueba numero 112',0,541,622.15,572.378,7),(113,'1170681905',23,6,'53131600','producto de prueba 113','este es un producto de prueba numero 113',0,483,555.45,505.4595,6),(114,'5983321922',23,15,'53131600','producto de prueba 114','este es un producto de prueba numero 114',0,2429,2866.22,2636.9224,14),(115,'6172061819',21,4,'53131600','producto de prueba 115','este es un producto de prueba numero 115',0,2619,3011.85,2740.7835,15),(116,'3711220943',18,8,'53131600','producto de prueba 116','este es un producto de prueba numero 116',0,2782,3310.58,2979.522,10),(117,'5106874879',15,14,'53131600','producto de prueba 117','este es un producto de prueba numero 117',0,2692,3068.88,2854.0584,11),(118,'8316376326',11,4,'53131600','producto de prueba 118','este es un producto de prueba numero 118',0,2506,2781.66,2642.577,8),(119,'7912651668',22,9,'53131600','producto de prueba 119','este es un producto de prueba numero 119',0,832,940.16,883.7504,15),(120,'4298944501',18,4,'53131600','producto de prueba 120','este es un producto de prueba numero 120',0,2451,2843.16,2615.7072,15),(121,'6988382988',23,11,'53131600','producto de prueba 121','este es un producto de prueba numero 121',0,457,502.7,462.484,9),(122,'7990543141',10,2,'53131600','producto de prueba 122','este es un producto de prueba numero 122',0,51,57.63,54.7485,15),(123,'289898498',15,9,'53131600','producto de prueba 123','este es un producto de prueba numero 123',0,1192,1430.4,1301.664,13),(124,'865717618',6,13,'53131600','producto de prueba 124','este es un producto de prueba numero 124',0,1753,1963.36,1845.5584,13),(125,'3436481982',15,1,'53131600','producto de prueba 125','este es un producto de prueba numero 125',0,1564,1876.8,1726.656,15),(126,'9493319717',7,13,'53131600','producto de prueba 126','este es un producto de prueba numero 126',0,1513,1770.21,1681.6995,14),(127,'933533858',1,1,'53131600','producto de prueba 127','este es un producto de prueba numero 127',0,847,974.05,876.645,13),(128,'3143030948',12,10,'53131600','producto de prueba 128','este es un producto de prueba numero 128',0,1274,1503.32,1383.0544,11),(129,'1170225414',22,10,'53131600','producto de prueba 129','este es un producto de prueba numero 129',0,2151,2409.12,2264.5728,14),(130,'3270119836',20,4,'53131600','producto de prueba 130','este es un producto de prueba numero 130',0,707,834.26,750.834,12),(131,'941628112',6,8,'53131600','producto de prueba 131','este es un producto de prueba numero 131',0,2408,2648.8,2463.384,10),(132,'9277998034',2,10,'53131600','producto de prueba 132','este es un producto de prueba numero 132',0,2595,3062.1,2817.132,15),(133,'1684587193',13,9,'53131600','producto de prueba 133','este es un producto de prueba numero 133',0,2076,2491.2,2366.64,11),(134,'6749624344',20,9,'53131600','producto de prueba 134','este es un producto de prueba numero 134',0,2864,3408.16,3169.5888,12),(135,'4310890194',19,9,'53131600','producto de prueba 135','este es un producto de prueba numero 135',0,2415,2656.5,2470.545,14),(136,'744329760',18,6,'53131600','producto de prueba 136','este es un producto de prueba numero 136',0,2316,2570.76,2416.5144,13),(137,'7702130556',19,10,'53131600','producto de prueba 137','este es un producto de prueba numero 137',0,2111,2469.87,2296.9791,15),(138,'2883982296',20,10,'53131600','producto de prueba 138','este es un producto de prueba numero 138',0,2367,2722.05,2449.845,11),(139,'258737178',22,4,'53131600','producto de prueba 139','este es un producto de prueba numero 139',0,1325,1510.5,1389.66,6),(140,'8301631492',4,6,'53131600','producto de prueba 140','este es un producto de prueba numero 140',0,826,991.2,892.08,11),(141,'2768788758',12,4,'53131600','producto de prueba 141','este es un producto de prueba numero 141',0,2529,2832.48,2634.2064,13),(142,'801387390',8,12,'53131600','producto de prueba 142','este es un producto de prueba numero 142',0,2091,2341.92,2177.9856,13),(143,'8826622053',7,15,'53131600','producto de prueba 143','este es un producto de prueba numero 143',0,2801,3305.18,3073.8174,14),(144,'2028626439',21,9,'53131600','producto de prueba 144','este es un producto de prueba numero 144',0,1711,2018.98,1857.4616,8),(145,'9333956283',22,9,'53131600','producto de prueba 145','este es un producto de prueba numero 145',0,351,421.2,379.08,12),(146,'7052028931',17,1,'53131600','producto de prueba 146','este es un producto de prueba numero 146',0,647,731.11,665.3101,13),(147,'3231339073',3,9,'53131600','producto de prueba 147','este es un producto de prueba numero 147',0,163,190.71,175.4532,14),(148,'9787690741',5,5,'53131600','producto de prueba 148','este es un producto de prueba numero 148',0,1257,1420.41,1349.3895,7),(149,'1040924611',17,7,'53131600','producto de prueba 149','este es un producto de prueba numero 149',0,2449,2816.35,2647.369,12),(150,'4328791463',12,1,'53131600','producto de prueba 150','este es un producto de prueba numero 150',0,1066,1268.54,1154.3714,6),(151,'6330576453',25,2,'53131600','producto de prueba 151','este es un producto de prueba numero 151',0,2697,3236.4,3074.58,7),(152,'326267517',10,8,'53131600','producto de prueba 152','este es un producto de prueba numero 152',0,1746,1955.52,1857.744,8),(153,'8913559659',25,1,'53131600','producto de prueba 153','este es un producto de prueba numero 153',0,477,524.7,487.971,11),(154,'2518511959',7,9,'53131600','producto de prueba 154','este es un producto de prueba numero 154',0,2035,2421.65,2179.485,10),(155,'9442950438',6,2,'53131600','producto de prueba 155','este es un producto de prueba numero 155',0,813,959.34,911.373,12),(156,'489179642',23,9,'53131600','producto de prueba 156','este es un producto de prueba numero 156',0,1006,1156.9,1064.348,12),(157,'7622678837',3,1,'53131600','producto de prueba 157','este es un producto de prueba numero 157',0,151,179.69,168.9086,9),(158,'7975339607',4,9,'53131600','producto de prueba 158','este es un producto de prueba numero 158',0,2544,2900.16,2726.1504,8),(159,'9323368151',22,8,'53131600','producto de prueba 159','este es un producto de prueba numero 159',0,184,206.08,195.776,10),(160,'6441012889',11,14,'53131600','producto de prueba 160','este es un producto de prueba numero 160',0,1125,1338.75,1245.0375,12),(161,'3060972104',6,4,'53131600','producto de prueba 161','este es un producto de prueba numero 161',0,914,1051.1,977.523,13),(162,'9277261902',9,9,'53131600','producto de prueba 162','este es un producto de prueba numero 162',0,2783,3311.77,3146.1815,15),(163,'2668574645',12,1,'53131600','producto de prueba 163','este es un producto de prueba numero 163',0,2488,2861.2,2660.916,7),(164,'2272112662',21,3,'53131600','producto de prueba 164','este es un producto de prueba numero 164',0,637,732.55,659.295,12),(165,'8245977732',25,6,'53131600','producto de prueba 165','este es un producto de prueba numero 165',0,2828,3393.6,3122.112,11),(166,'7821070413',6,6,'53131600','producto de prueba 166','este es un producto de prueba numero 166',0,2649,2966.88,2729.5296,13),(167,'3157828298',9,2,'53131600','producto de prueba 167','este es un producto de prueba numero 167',0,1196,1327.56,1221.3552,11),(168,'7586948765',3,2,'53131600','producto de prueba 168','este es un producto de prueba numero 168',0,1430,1615.9,1486.628,15),(169,'4475962429',16,14,'53131600','producto de prueba 169','este es un producto de prueba numero 169',0,361,425.98,387.6418,6),(170,'1180359543',15,11,'53131600','producto de prueba 170','este es un producto de prueba numero 170',0,1950,2340,2223,13),(171,'3729999646',19,15,'53131600','producto de prueba 171','este es un producto de prueba numero 171',0,1986,2363.34,2150.6394,15),(172,'3754763908',2,10,'53131600','producto de prueba 172','este es un producto de prueba numero 172',0,1973,2347.87,2113.083,9),(173,'2538294317',5,1,'53131600','producto de prueba 173','este es un producto de prueba numero 173',0,336,369.6,343.728,8),(174,'4757576587',17,11,'53131600','producto de prueba 174','este es un producto de prueba numero 174',0,1259,1422.67,1323.0831,6),(175,'2554865358',23,3,'53131600','producto de prueba 175','este es un producto de prueba numero 175',0,2054,2321.02,2181.7588,6),(176,'4592647014',20,14,'53131600','producto de prueba 176','este es un producto de prueba numero 176',0,393,451.95,406.755,9),(177,'7410047625',4,10,'53131600','producto de prueba 177','este es un producto de prueba numero 177',0,1145,1362.55,1226.295,9),(178,'2490414284',6,1,'53131600','producto de prueba 178','este es un producto de prueba numero 178',0,193,212.3,201.685,8),(179,'2568095939',2,11,'53131600','producto de prueba 179','este es un producto de prueba numero 179',0,1262,1514.4,1423.536,8),(180,'1894648999',12,9,'53131600','producto de prueba 180','este es un producto de prueba numero 180',0,1614,1839.96,1655.964,15),(181,'6342446565',8,5,'53131600','producto de prueba 181','este es un producto de prueba numero 181',0,2461,2953.2,2657.88,14),(182,'7954229546',3,10,'53131600','producto de prueba 182','este es un producto de prueba numero 182',0,1652,1883.28,1713.7848,9),(183,'1181431656',24,13,'53131600','producto de prueba 183','este es un producto de prueba numero 183',0,723,816.99,743.4609,14),(184,'3254837625',9,3,'53131600','producto de prueba 184','este es un producto de prueba numero 184',0,1146,1272.06,1195.7364,10),(185,'4046865043',18,15,'53131600','producto de prueba 185','este es un producto de prueba numero 185',0,1809,2044.17,1921.5198,15),(186,'2454899988',11,3,'53131600','producto de prueba 186','este es un producto de prueba numero 186',0,1748,2062.64,1959.508,15),(187,'4279274713',3,3,'53131600','producto de prueba 187','este es un producto de prueba numero 187',0,486,583.2,536.544,7),(188,'5971819031',8,7,'53131600','producto de prueba 188','este es un producto de prueba numero 188',0,419,477.66,434.6706,15),(189,'1694242672',19,7,'53131600','producto de prueba 189','este es un producto de prueba numero 189',0,1477,1728.09,1624.4046,14),(190,'1484211401',14,4,'53131600','producto de prueba 190','este es un producto de prueba numero 190',0,681,776.34,721.9962,9),(191,'835816668',11,7,'53131600','producto de prueba 191','este es un producto de prueba numero 191',0,117,129.87,116.883,11),(192,'2847552037',15,8,'53131600','producto de prueba 192','este es un producto de prueba numero 192',0,280,313.6,297.92,9),(193,'2159474710',17,6,'53131600','producto de prueba 193','este es un producto de prueba numero 193',0,2817,3267.72,2973.6252,7),(194,'7803450710',18,1,'53131600','producto de prueba 194','este es un producto de prueba numero 194',0,830,937.9,891.005,7),(195,'4093092720',9,3,'53131600','producto de prueba 195','este es un producto de prueba numero 195',0,1392,1614.72,1533.984,10),(196,'7361915887',13,11,'53131600','producto de prueba 196','este es un producto de prueba numero 196',0,2634,2897.4,2636.634,11),(197,'5174923853',5,7,'53131600','producto de prueba 197','este es un producto de prueba numero 197',0,834,959.1,891.963,7),(198,'7568637301',16,8,'53131600','producto de prueba 198','este es un producto de prueba numero 198',0,2549,2905.86,2644.3326,12),(199,'6133210849',7,15,'53131600','producto de prueba 199','este es un producto de prueba numero 199',0,2607,3102.33,2792.097,10),(200,'8554046750',14,12,'53131600','producto de prueba 200','este es un producto de prueba numero 200',0,938,1097.46,1020.6378,11),(201,'3079584693',3,9,'53131600','producto de prueba 201','este es un producto de prueba numero 201',0,1610,1771,1664.74,12),(202,'2470015503',6,7,'53131600','producto de prueba 202','este es un producto de prueba numero 202',0,1831,2160.58,2009.3394,8),(203,'7516864491',24,2,'53131600','producto de prueba 203','este es un producto de prueba numero 203',0,1827,2009.7,1808.73,9),(204,'674395236',1,15,'53131600','producto de prueba 204','este es un producto de prueba numero 204',0,2066,2313.92,2105.6672,14),(205,'3352056325',25,1,'53131600','producto de prueba 205','este es un producto de prueba numero 205',0,2739,3259.41,3063.8454,9),(206,'5984418548',7,8,'53131600','producto de prueba 206','este es un producto de prueba numero 206',0,1951,2341.2,2107.08,12),(207,'9435252053',21,6,'53131600','producto de prueba 207','este es un producto de prueba numero 207',0,483,565.11,519.9012,7),(208,'1477483364',13,9,'53131600','producto de prueba 208','este es un producto de prueba numero 208',0,143,170.17,154.8547,7),(209,'2738341149',12,5,'53131600','producto de prueba 209','este es un producto de prueba numero 209',0,2582,2866.02,2665.3986,15),(210,'1002223304',11,13,'53131600','producto de prueba 210','este es un producto de prueba numero 210',0,1090,1209.9,1137.306,13),(211,'7125066647',14,13,'53131600','producto de prueba 211','este es un producto de prueba numero 211',0,1582,1866.76,1680.084,12),(212,'2262796359',3,1,'53131600','producto de prueba 212','este es un producto de prueba numero 212',0,608,668.8,635.36,7),(213,'835185095',6,2,'53131600','producto de prueba 213','este es un producto de prueba numero 213',0,729,860.22,800.0046,6),(214,'5963321961',15,11,'53131600','producto de prueba 214','este es un producto de prueba numero 214',0,2667,3200.4,3040.38,7),(215,'6355218985',23,4,'53131600','producto de prueba 215','este es un producto de prueba numero 215',0,273,324.87,298.8804,15),(216,'1177685202',23,6,'53131600','producto de prueba 216','este es un producto de prueba numero 216',0,428,483.64,440.1124,10),(217,'5323846584',12,9,'53131600','producto de prueba 217','este es un producto de prueba numero 217',0,2713,3201.34,3009.2596,12),(218,'7221540488',9,15,'53131600','producto de prueba 218','este es un producto de prueba numero 218',0,2111,2469.87,2296.9791,8),(219,'4668499546',3,2,'53131600','producto de prueba 219','este es un producto de prueba numero 219',0,101,118.17,111.0798,15),(220,'3994424705',2,1,'53131600','producto de prueba 220','este es un producto de prueba numero 220',0,2887,3320.05,3021.2455,8),(221,'4996167478',3,3,'53131600','producto de prueba 221','este es un producto de prueba numero 221',0,1416,1670.88,1520.5008,15),(222,'2998652244',18,4,'53131600','producto de prueba 222','este es un producto de prueba numero 222',0,270,305.1,280.692,12),(223,'7007192322',10,6,'53131600','producto de prueba 223','este es un producto de prueba numero 223',0,1871,2207.78,1987.002,12),(224,'452948008',8,14,'53131600','producto de prueba 224','este es un producto de prueba numero 224',0,165,189.75,172.6725,8),(225,'6714478941',13,9,'53131600','producto de prueba 225','este es un producto de prueba numero 225',0,2847,3160.17,3002.1615,9),(226,'502841134',12,2,'53131600','producto de prueba 226','este es un producto de prueba numero 226',0,1717,1923.04,1749.9664,15),(227,'402828740',15,3,'53131600','producto de prueba 227','este es un producto de prueba numero 227',0,363,406.56,369.9696,15),(228,'5470538886',21,14,'53131600','producto de prueba 228','este es un producto de prueba numero 228',0,1989,2366.91,2153.8881,13),(229,'231543290',16,3,'53131600','producto de prueba 229','este es un producto de prueba numero 229',0,1535,1734.55,1630.477,11),(230,'4485236059',1,7,'53131600','producto de prueba 230','este es un producto de prueba numero 230',0,1127,1318.59,1199.9169,14),(231,'8996889906',25,1,'53131600','producto de prueba 231','este es un producto de prueba numero 231',0,197,232.46,209.214,8),(232,'1825314623',20,13,'53131600','producto de prueba 232','este es un producto de prueba numero 232',0,864,1002.24,952.128,12),(233,'5749978884',6,5,'53131600','producto de prueba 233','este es un producto de prueba numero 233',0,224,255.36,229.824,12),(234,'5197788845',17,4,'53131600','producto de prueba 234','este es un producto de prueba numero 234',0,1444,1703.92,1550.5672,11),(235,'2851625339',11,7,'53131600','producto de prueba 235','este es un producto de prueba numero 235',0,1300,1508,1432.6,12),(236,'1501511623',14,10,'53131600','producto de prueba 236','este es un producto de prueba numero 236',0,2215,2635.85,2424.982,7),(237,'9688485776',6,14,'53131600','producto de prueba 237','este es un producto de prueba numero 237',0,2678,3213.6,2892.24,11),(238,'3104981159',19,11,'53131600','producto de prueba 238','este es un producto de prueba numero 238',0,2568,2901.84,2611.656,10),(239,'2672539210',9,12,'53131600','producto de prueba 239','este es un producto de prueba numero 239',0,2055,2383.8,2145.42,13),(240,'1862698240',7,12,'53131600','producto de prueba 240','este es un producto de prueba numero 240',0,956,1118.52,1062.594,15),(241,'7410780531',9,3,'53131600','producto de prueba 241','este es un producto de prueba numero 241',0,2220,2486.4,2262.624,9),(242,'3988178726',19,5,'53131600','producto de prueba 242','este es un producto de prueba numero 242',0,1530,1820.7,1656.837,8),(243,'9693168555',3,1,'53131600','producto de prueba 243','este es un producto de prueba numero 243',0,77,87.01,80.0492,6),(244,'4735785551',14,5,'53131600','producto de prueba 244','este es un producto de prueba numero 244',0,968,1064.8,968.968,9),(245,'1482864958',3,11,'53131600','producto de prueba 245','este es un producto de prueba numero 245',0,2827,3137.97,2918.3121,14),(246,'6959296072',3,13,'53131600','producto de prueba 246','este es un producto de prueba numero 246',0,1671,1921.65,1748.7015,15),(247,'9822243116',1,9,'53131600','producto de prueba 247','este es un producto de prueba numero 247',0,2276,2594.64,2464.908,10),(248,'6489836832',1,2,'53131600','producto de prueba 248','este es un producto de prueba numero 248',0,999,1198.8,1138.86,8),(249,'1721992170',3,4,'53131600','producto de prueba 249','este es un producto de prueba numero 249',0,737,825.44,751.1504,6),(250,'5840870931',19,3,'53131600','producto de prueba 250','este es un producto de prueba numero 250',0,2025,2288.25,2059.425,15),(251,'9378334924',25,8,'53131600','producto de prueba 251','este es un producto de prueba numero 251',0,1552,1722.72,1619.3568,15),(252,'397882360',17,2,'53131600','producto de prueba 252','este es un producto de prueba numero 252',0,1756,2019.4,1817.46,14),(253,'4484668961',16,6,'53131600','producto de prueba 253','este es un producto de prueba numero 253',0,1164,1315.32,1249.554,7),(254,'6968035057',2,5,'53131600','producto de prueba 254','este es un producto de prueba numero 254',0,2037,2342.55,2108.295,13),(255,'375496824',4,9,'53131600','producto de prueba 255','este es un producto de prueba numero 255',0,72,79.2,74.448,11),(256,'8090842009',17,6,'53131600','producto de prueba 256','este es un producto de prueba numero 256',0,1205,1361.65,1293.5675,10),(257,'3876118555',11,7,'53131600','producto de prueba 257','este es un producto de prueba numero 257',0,757,878.12,834.214,10),(258,'3552479236',9,10,'53131600','producto de prueba 258','este es un producto de prueba numero 258',0,408,473.28,435.4176,6),(259,'8681254623',15,12,'53131600','producto de prueba 259','este es un producto de prueba numero 259',0,1467,1613.7,1452.33,14),(260,'6089762758',25,9,'53131600','producto de prueba 260','este es un producto de prueba numero 260',0,1404,1558.44,1433.7648,9),(261,'9295347968',22,14,'53131600','producto de prueba 261','este es un producto de prueba numero 261',0,1722,2066.4,1901.088,9),(262,'2104748323',4,14,'53131600','producto de prueba 262','este es un producto de prueba numero 262',0,1050,1249.5,1137.045,6),(263,'7933577238',3,7,'53131600','producto de prueba 263','este es un producto de prueba numero 263',0,2573,3061.87,2878.1578,13),(264,'893934278',2,6,'53131600','producto de prueba 264','este es un producto de prueba numero 264',0,1820,2093,1883.7,14),(265,'9536259354',11,1,'53131600','producto de prueba 265','este es un producto de prueba numero 265',0,1540,1771,1664.74,13),(266,'7069368558',15,10,'53131600','producto de prueba 266','este es un producto de prueba numero 266',0,1551,1706.1,1620.795,13),(267,'700837116',1,7,'53131600','producto de prueba 267','este es un producto de prueba numero 267',0,646,775.2,720.936,12),(268,'7792127999',23,3,'53131600','producto de prueba 268','este es un producto de prueba numero 268',0,899,1051.83,978.2019,6),(269,'7398772354',1,10,'53131600','producto de prueba 269','este es un producto de prueba numero 269',0,2244,2490.84,2291.5728,6),(272,'324652362',5,2,'53131600','TINTE NEFERTITI 7.1','UN TINTE DE LA MARCA NEFERTITI TONO RUBIO MEDIO CENIZO ',0,60.2,78.59,71.2,10),(273,'456168651651',14,6,'53131600','Shampoo colorflex','Shampoo de portecci├│n de color para cabello te├▒ido',0,78.25,114.5,97.58,12);
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
INSERT INTO `articulo_x_venta` VALUES (1,1,34,10,25200),(2,1,238,15,43527.6),(3,2,250,3,6864.75),(4,3,214,10,32004),(5,4,195,17,27450.24),(6,5,112,6,3732.9),(7,5,133,9,22420.8),(8,5,180,2,3679.92),(9,5,259,10,16137),(10,6,113,7,3888.15),(11,6,216,8,3869.12),(12,6,86,16,31567.68),(13,6,249,4,3301.76),(14,7,36,3,8358),(15,7,209,1,2866.02),(16,7,263,10,30618.7),(17,8,215,6,1949.22),(18,8,179,2,3028.8),(19,8,39,5,4130),(20,8,16,17,40562),(21,8,58,15,37860),(22,8,56,9,25830),(23,9,177,4,5450.2),(24,10,154,7,16951.55),(25,10,49,8,15344),(26,11,163,6,17167.2),(27,11,125,17,31905.6),(28,11,177,11,14988.05),(29,11,12,15,15900),(30,12,155,7,6715.38),(31,12,85,17,57084.3),(32,12,40,4,5592),(33,13,21,16,44320),(34,13,119,7,6581.12),(35,14,29,9,7182),(36,14,11,11,10252),(37,14,264,13,27209),(38,14,242,17,30951.9),(39,14,218,13,32108.31),(40,15,206,6,14047.2),(41,15,151,16,51782.4),(42,16,9,10,19960),(43,17,96,9,28393.2),(44,17,24,1,802),(45,18,162,3,9935.31),(46,18,218,14,34578.18),(47,18,71,11,34109.9),(48,18,135,10,26565),(49,19,61,13,37050),(50,20,9,7,13972),(51,20,201,17,30107),(52,20,65,2,1436),(53,20,197,8,7672.8),(54,21,59,17,16456),(55,21,76,2,1505.28),(56,21,221,16,26734.08),(57,22,192,7,2195.2),(58,22,216,16,7738.24),(59,22,46,10,15940),(60,23,265,15,26565),(61,23,154,1,2421.65),(62,23,125,11,20644.8),(63,23,241,6,14918.4),(64,24,224,7,1328.25),(65,24,101,4,12555.84),(66,24,255,11,871.2),(67,24,55,8,16352),(68,24,112,6,3732.9),(69,25,123,1,1430.4),(70,25,244,2,2129.6),(71,25,250,8,18306),(72,26,92,12,5557.2),(73,26,243,9,783.09),(74,26,248,4,4795.2),(75,26,78,15,48940.2),(76,27,230,8,10548.72),(77,28,199,17,52739.61),(78,28,109,3,9346.05),(79,29,268,12,12621.96),(80,29,259,2,3227.4),(81,29,29,9,7182),(82,29,230,4,5274.36),(83,30,196,4,11589.6),(84,30,118,17,47288.22),(85,31,41,13,22828),(86,31,184,16,20352.96),(87,31,75,9,8988.84),(88,31,155,17,16308.78),(89,32,242,3,5462.1),(90,32,228,14,33136.74),(91,33,15,2,580),(92,33,169,2,851.96),(93,33,74,12,4950),(94,33,129,1,2409.12),(95,33,177,7,9537.85),(96,34,163,11,31473.2),(97,34,141,17,48152.16),(98,34,73,2,3781.12),(99,34,160,16,21420),(100,35,90,14,19773.6),(101,36,215,17,5522.79),(102,36,10,13,6734),(103,36,116,11,36416.38),(104,36,36,14,39004),(105,37,120,2,5686.32),(106,37,39,16,13216),(107,37,13,7,7084),(108,38,67,8,11152),(109,38,67,13,18122),(110,39,269,11,27399.24),(111,39,222,14,4271.4),(112,39,152,4,7822.08),(113,39,173,13,4804.8),(114,39,28,3,1290),(115,40,40,16,22368),(116,40,229,8,13876.4),(117,40,230,11,14504.49),(118,41,38,7,4662),(119,41,40,10,13980),(120,41,218,4,9879.48),(121,41,122,7,403.41),(122,42,162,11,36429.47),(123,42,207,1,565.11),(124,42,118,11,30598.26),(125,42,110,15,36946.35),(126,42,255,5,396),(127,43,106,13,46317.18),(128,43,244,1,1064.8),(129,43,153,10,5247),(130,44,51,14,33348),(131,44,2,10,23700),(132,44,66,1,1072),(133,44,109,1,3115.35),(134,44,170,9,21060),(135,45,36,17,47362),(136,45,222,8,2440.8),(137,45,195,14,22606.08),(138,45,13,3,3036),(139,46,255,5,396),(140,46,40,5,6990),(141,46,252,12,24232.8),(142,46,170,6,14040),(143,47,76,8,6021.12),(144,47,221,3,5012.64),(145,47,140,4,3964.8),(146,48,176,17,7683.15),(147,48,248,9,10789.2),(148,48,107,4,13553.44),(149,48,178,10,2123),(150,48,150,12,15222.48),(151,49,260,9,14025.96),(152,49,268,4,4207.32),(153,49,141,14,39654.72),(154,49,138,3,8166.15),(155,50,152,12,23466.24),(156,50,235,16,24128),(157,50,30,14,11648),(158,50,18,1,2218),(159,50,35,5,3830),(160,50,38,14,9324),(161,50,131,13,34434.4),(162,51,64,1,2466),(163,51,132,15,45931.5),(164,52,138,14,38108.7),(165,52,61,15,42750),(166,52,63,4,1784),(167,52,38,3,1998),(168,53,26,13,9022),(169,53,158,11,31901.76),(170,54,61,11,31350),(171,54,15,5,1450),(172,54,261,2,4132.8),(173,54,104,3,2460.36),(174,55,141,16,45319.68),(175,55,236,4,10543.4),(176,55,219,1,118.17),(177,55,99,4,1987.2),(178,56,189,17,29377.53),(179,56,252,4,8077.6),(180,57,65,3,2154),(181,57,90,11,15536.4),(182,57,22,11,6072),(183,58,228,13,30769.83),(184,58,205,6,19556.46),(185,58,97,10,3162.5),(186,58,18,14,31052),(187,58,198,1,2905.86),(188,59,16,2,4772),(189,60,67,4,5576),(190,60,29,11,8778),(191,60,48,14,6468),(192,61,243,4,348.04),(193,61,11,1,932),(194,61,60,3,1722),(195,62,194,1,937.9),(196,62,87,10,23710.4),(197,62,33,16,43904),(198,62,24,10,8020),(199,62,122,2,115.26),(200,63,34,17,42840),(201,64,253,1,1315.32),(202,64,148,7,9942.87),(203,64,151,2,6472.8),(204,65,6,15,4230),(205,66,250,8,18306),(206,67,130,7,5839.82),(207,67,48,10,4620),(208,67,91,7,11080.02),(209,67,121,6,3016.2),(210,68,259,8,12909.6),(211,68,49,17,32606),(212,69,82,1,2357.12),(213,69,29,6,4788),(214,69,86,14,27621.72),(215,69,230,17,22416.03),(216,70,36,10,27860),(217,70,147,5,953.55),(218,70,23,4,8960),(219,71,47,15,10800),(220,71,152,16,31288.32),(221,71,47,9,6480),(222,71,239,16,38140.8),(223,72,61,5,14250),(224,72,245,3,9413.91),(225,72,55,12,24528),(226,73,26,1,694),(227,73,102,7,14990.08),(228,73,51,15,35730),(229,73,136,11,28278.36),(230,73,2,13,30810),(231,73,227,2,813.12),(232,74,64,14,34524),(233,74,143,13,42967.34),(234,74,22,17,9384),(235,74,6,15,4230),(236,74,207,14,7911.54),(237,74,224,17,3225.75),(238,75,108,2,4273.92),(239,75,239,12,28605.6),(240,75,172,4,9391.48),(241,75,213,2,1720.44),(242,76,171,7,16543.38),(243,76,64,5,12330),(244,76,63,3,1338),(245,77,196,16,46358.4),(246,78,50,4,6608),(247,78,187,2,1166.4),(248,79,181,15,44298),(249,79,110,13,32020.17),(250,79,268,15,15777.45),(251,79,10,2,1036),(252,79,111,5,15828.6),(253,79,66,3,3216),(254,79,244,7,7453.6),(255,79,147,2,381.42),(256,80,106,6,21377.16),(257,80,99,10,4968),(258,81,20,12,34032),(259,81,138,10,27220.5),(260,81,139,6,9063),(261,81,110,3,7389.27),(262,81,140,5,4956),(263,81,254,10,23425.5),(264,82,71,5,15504.5),(265,82,118,13,36161.58),(266,82,71,2,6201.8),(267,82,248,1,1198.8),(268,82,147,15,2860.65),(269,82,145,5,2106),(270,82,192,1,313.6),(271,83,58,2,5048),(272,83,170,2,4680),(273,84,188,5,2388.3),(274,84,247,17,44108.88),(275,84,246,4,7686.6),(276,84,24,7,5614),(277,85,193,10,32677.2),(278,85,227,17,6911.52),(279,86,201,7,12397),(280,86,231,6,1394.76),(281,86,173,2,739.2),(282,86,263,13,39804.31),(283,86,250,8,18306),(284,86,168,7,11311.3),(285,87,52,16,12640),(286,87,144,6,12113.88),(287,87,201,12,21252),(288,88,129,17,40955.04),(289,88,76,9,6773.76),(290,89,111,12,37988.64),(291,89,101,17,53362.32),(292,90,56,7,20090),(293,90,96,3,9464.4),(294,90,75,1,998.76),(295,91,26,11,7634),(296,91,228,6,14201.46),(297,92,162,4,13247.08),(298,92,181,8,23625.6),(299,92,222,13,3966.3),(300,92,26,9,6246),(301,93,112,15,9332.25),(302,93,242,13,23669.1),(303,94,165,15,50904),(304,95,162,5,16558.85),(305,95,85,13,43652.7),(306,95,181,3,8859.6),(307,95,225,9,28441.53),(308,95,183,15,12254.85),(309,95,79,1,1609.12),(310,95,229,6,10407.3),(311,96,84,12,13251.84),(312,97,35,17,13022),(313,97,152,7,13688.64),(314,97,184,14,17808.84),(315,97,222,1,305.1),(316,98,252,9,18174.6),(317,98,14,12,33408),(318,98,230,4,5274.36),(319,99,17,11,28050),(320,100,59,6,5808),(321,100,27,12,10488),(322,100,196,12,34768.8),(323,100,183,13,10620.87),(324,100,62,1,1106),(325,101,118,7,19471.62),(326,101,257,9,7903.08),(327,101,169,2,851.96),(328,101,257,16,14049.92),(329,102,146,1,731.11),(330,103,25,4,8240),(331,103,64,13,32058),(332,103,92,14,6483.4),(333,103,236,1,2635.85),(334,104,234,7,11927.44),(335,104,25,10,20600),(336,104,209,10,28660.2),(337,104,205,4,13037.64),(338,104,220,1,3320.05),(339,105,238,10,29018.4),(340,105,237,9,28922.4),(341,105,8,13,2678),(342,106,213,4,3440.88),(343,106,33,8,21952),(344,106,247,1,2594.64),(345,106,46,9,14346),(346,107,48,2,924),(347,107,124,13,25523.68),(348,107,191,8,1038.96),(349,108,78,14,45677.52),(350,108,24,2,1604),(351,108,129,5,12045.6),(352,109,57,2,5052),(353,109,262,17,21241.5),(354,109,244,17,18101.6),(355,109,110,8,19704.72),(356,109,64,2,4932),(357,110,198,16,46493.76),(358,110,25,6,12360),(359,110,24,16,12832),(360,110,28,7,3010),(361,110,175,8,18568.16),(362,110,16,12,28632),(363,110,76,5,3763.2),(364,110,31,5,5900),(365,111,177,3,4087.65),(366,111,100,10,27097.6),(367,111,192,16,5017.6),(368,111,238,16,46429.44),(369,111,220,3,9960.15),(370,112,178,12,2547.6),(371,113,249,15,12381.6),(372,114,150,14,17759.56),(373,114,213,11,9462.42),(374,115,38,17,11322),(375,115,248,6,7192.8),(376,115,163,3,8583.6),(377,115,191,1,129.87),(378,115,223,2,4415.56),(379,115,182,5,9416.4),(380,116,191,14,1818.18),(381,116,62,12,13272),(382,116,24,8,6416),(383,117,127,8,7792.4),(384,117,57,7,17682),(385,117,116,16,52969.28),(386,118,68,6,12396),(387,118,82,6,14142.72),(388,119,15,14,4060),(389,119,142,2,4683.84),(390,119,245,10,31379.7),(391,119,79,8,12872.96),(392,120,206,2,4682.4),(393,120,155,16,15349.44),(394,120,33,8,21952),(395,120,175,15,34815.3),(396,121,18,13,28834),(397,121,233,7,1787.52),(398,121,181,3,8859.6),(399,121,202,6,12963.48),(400,121,56,9,25830),(401,121,53,3,8232),(402,122,191,1,129.87),(403,122,56,10,28700),(404,122,220,17,56440.85),(405,123,106,3,10688.58),(406,123,196,15,43461),(407,123,128,14,21046.48),(408,123,265,9,15939),(409,123,194,9,8441.1),(410,123,30,13,10816),(411,124,240,9,10066.68),(412,125,201,5,8855),(413,125,26,14,9716),(414,126,141,16,45319.68),(415,126,192,4,1254.4),(416,126,173,16,5913.6),(417,127,77,10,16518.4),(418,128,123,6,8582.4),(419,128,192,2,627.2),(420,128,253,8,10522.56),(421,129,80,3,2221.83),(422,129,57,12,30312),(423,129,162,2,6623.54),(424,129,199,11,34125.63),(425,129,158,16,46402.56),(426,129,174,17,24185.39),(427,129,221,4,6683.52),(428,130,208,11,1871.87),(429,130,266,12,20473.2),(430,130,232,12,12026.88),(431,130,221,4,6683.52),(432,131,7,13,17004),(433,131,89,1,2278.24),(434,131,234,16,27262.72),(435,131,156,8,9255.2),(436,131,97,6,1897.5),(437,131,120,5,14215.8),(438,132,56,13,37310),(439,133,99,15,7452),(440,133,103,3,9985.92),(441,134,263,13,39804.31),(442,134,156,2,2313.8),(443,134,10,13,6734),(444,134,148,5,7102.05),(445,135,231,2,464.92),(446,135,177,1,1362.55),(447,135,175,12,27852.24),(448,136,257,4,3512.48),(449,137,57,2,5052),(450,138,223,14,30908.92),(451,139,175,15,34815.3),(452,139,165,16,54297.6),(453,140,13,3,3036),(454,140,70,9,9447.93),(455,140,196,11,31871.4),(456,141,267,8,6201.6),(457,141,131,5,13244),(458,142,61,14,39900),(459,142,129,6,14454.72),(460,143,220,9,29880.45),(461,143,55,17,34748),(462,143,255,16,1267.2),(463,144,127,15,14610.75),(464,144,35,11,8426),(465,144,267,8,6201.6),(466,144,195,3,4844.16),(467,145,241,14,34809.6),(468,145,261,15,30996),(469,145,109,13,40499.55),(470,146,234,12,20447.04),(471,146,176,1,451.95),(472,146,55,13,26572),(473,146,186,6,12375.84),(474,146,66,9,9648),(475,146,119,15,14102.4),(476,146,103,6,19971.84),(477,147,194,10,9379),(478,148,79,6,9654.72),(479,148,63,12,5352),(480,149,85,15,50368.5),(481,149,171,12,28360.08),(482,149,26,1,694),(483,149,265,14,24794),(484,149,46,3,4782),(485,150,181,2,5906.4),(486,150,257,1,878.12),(487,151,105,5,5346.6),(488,151,72,6,9244.08),(489,151,212,13,8694.4),(490,152,109,2,6230.7),(491,153,169,16,6815.68),(492,153,100,2,5419.52),(493,153,137,15,37048.05),(494,154,219,1,118.17),(495,154,184,5,6360.3),(496,154,28,6,2580),(497,155,37,3,7050),(498,155,252,9,18174.6),(499,155,196,11,31871.4),(500,156,145,3,1263.6),(501,157,146,7,5117.77),(502,157,193,1,3267.72),(503,158,60,9,5166),(504,158,14,2,5568),(505,158,255,9,712.8),(506,159,84,8,8834.56),(507,159,77,2,3303.68),(508,159,184,10,12720.6),(509,160,144,14,28265.72),(510,160,255,17,1346.4),(511,160,10,6,3108),(512,161,88,4,528.36),(513,161,150,14,17759.56),(514,162,173,4,1478.4),(515,163,190,16,12421.44),(516,163,74,16,6600),(517,163,195,14,22606.08),(518,163,162,7,23182.39),(519,163,17,15,38250),(520,164,9,5,9980),(521,164,215,8,2598.96),(522,165,165,17,57691.2),(523,165,228,15,35503.65),(524,165,61,13,37050),(525,166,227,13,5285.28),(526,166,120,9,25588.44),(527,166,90,1,1412.4),(528,167,9,9,17964),(529,167,76,17,12794.88),(530,167,151,2,6472.8),(531,168,143,14,46272.52),(532,168,10,10,5180),(533,168,238,15,43527.6),(534,168,174,10,14226.7),(535,169,140,2,1982.4),(536,169,249,16,13207.04),(537,170,204,13,30080.96),(538,170,151,1,3236.4),(539,170,267,12,9302.4),(540,170,182,1,1883.28),(541,170,66,14,15008),(542,170,67,3,4182),(543,171,56,3,8610),(544,171,10,6,3108),(545,172,69,5,16789.5),(546,172,82,13,30642.56),(547,172,227,12,4878.72),(548,172,116,7,23174.06),(549,173,226,8,15384.32),(550,173,263,10,30618.7),(551,173,140,11,10903.2),(552,173,131,1,2648.8),(553,174,124,14,27487.04),(554,174,182,10,18832.8),(555,174,211,8,14934.08),(556,174,259,10,16137),(557,174,13,17,17204),(558,175,64,13,32058),(559,176,81,12,9234.36),(560,176,17,11,28050),(561,176,165,6,20361.6),(562,177,42,9,16398),(563,177,133,16,39859.2),(564,177,138,6,16332.3),(565,178,55,4,8176),(566,178,44,2,4708),(567,179,122,15,864.45),(568,180,36,10,27860),(569,180,71,12,37210.8),(570,180,96,10,31548),(571,180,131,11,29136.8),(572,181,38,12,7992),(573,181,174,14,19917.38),(574,181,177,13,17713.15),(575,182,222,5,1525.5),(576,182,138,15,40830.75),(577,182,221,9,15037.92),(578,182,94,12,15513.12),(579,183,123,16,22886.4),(580,183,19,8,15360),(581,183,5,1,2432),(582,183,130,8,6674.08),(583,184,208,11,1871.87),(584,185,197,4,3836.4),(585,185,163,8,22889.6),(586,186,4,6,16920),(587,186,59,8,7744),(588,186,84,8,8834.56),(589,186,253,2,2630.64),(590,186,57,8,20208),(591,187,89,4,9112.96),(592,187,177,12,16350.6),(593,188,267,10,7752),(594,188,232,12,12026.88),(595,188,21,12,33240),(596,189,228,8,18935.28),(597,189,200,5,5487.3),(598,190,44,10,23540),(599,190,269,13,32380.92),(600,190,50,1,1652),(601,191,70,16,16796.32),(602,191,187,14,8164.8),(603,192,40,1,1398),(604,192,72,8,12325.44),(605,192,110,16,39409.44),(606,192,5,11,26752),(607,192,202,12,25926.96),(608,193,244,5,5324),(609,193,190,13,10092.42),(610,193,175,7,16247.14),(611,193,249,13,10730.72),(612,193,112,5,3110.75),(613,193,141,9,25492.32),(614,193,263,5,15309.35),(615,194,223,5,11038.9),(616,194,192,12,3763.2),(617,195,57,4,10104),(618,195,18,7,15526),(619,195,181,12,35438.4),(620,195,119,16,15042.56),(621,195,92,3,1389.3),(622,195,157,1,179.69),(623,195,31,4,4720),(624,196,166,1,2966.88),(625,196,60,7,4018),(626,197,182,3,5649.84),(627,197,229,1,1734.55),(628,197,35,12,9192),(629,197,100,13,35226.88),(630,198,124,7,13743.52),(631,198,36,7,19502),(632,198,106,8,28502.88),(633,199,135,2,5313),(634,199,6,9,2538),(635,200,13,4,4048),(636,200,20,15,42540),(637,200,50,11,18172),(638,200,214,17,54406.8),(639,200,60,4,2296),(640,200,191,7,909.09),(641,201,196,8,23179.2),(642,202,43,15,11340),(643,202,142,17,39812.64),(644,202,146,17,12428.87),(645,203,33,1,2744),(646,203,41,11,19316),(647,204,126,5,8851.05),(648,205,30,13,10816),(649,205,105,10,10693.2),(650,206,187,7,4082.4),(651,206,30,6,4992),(652,206,33,11,30184),(653,206,145,4,1684.8),(654,206,109,5,15576.75),(655,207,162,6,19870.62),(656,207,190,14,10868.76),(657,207,55,13,26572),(658,207,251,8,13781.76),(659,208,77,1,1651.84),(660,208,56,9,25830),(661,208,26,14,9716),(662,209,269,3,7472.52),(663,209,136,16,41132.16),(664,209,32,7,1988),(665,209,183,17,13888.83),(666,209,254,5,11712.75),(667,210,139,1,1510.5),(668,210,36,11,30646),(669,210,5,16,38912),(670,211,122,13,749.19),(671,211,14,14,38976),(672,211,5,8,19456),(673,211,13,7,7084),(674,212,159,16,3297.28),(675,212,162,15,49676.55),(676,213,200,9,9877.14),(677,213,35,8,6128),(678,213,72,16,24650.88),(679,214,268,9,9466.47),(680,215,145,3,1263.6),(681,215,9,1,1996),(682,215,149,2,5632.7),(683,215,208,9,1531.53),(684,216,58,5,12620),(685,216,120,5,14215.8),(686,217,12,6,6360),(687,217,174,17,24185.39),(688,217,71,4,12403.6),(689,217,179,14,21201.6),(690,218,100,1,2709.76),(691,219,212,11,7356.8),(692,219,6,4,1128),(693,220,254,16,37480.8),(694,220,40,4,5592),(695,221,31,8,9440),(696,221,208,14,2382.38),(697,222,109,3,9346.05),(698,222,3,8,4240),(699,222,229,17,29487.35),(700,222,69,5,16789.5),(701,222,214,3,9601.2),(702,222,3,10,5300),(703,223,251,5,8613.6),(704,223,97,8,2530),(705,223,99,7,3477.6),(706,223,174,14,19917.38),(707,224,222,14,4271.4),(708,224,186,2,4125.28),(709,224,192,11,3449.6),(710,224,126,5,8851.05),(711,225,9,1,1996),(712,226,255,6,475.2),(713,226,237,10,32136),(714,226,230,7,9230.13),(715,227,62,7,7742),(716,227,9,4,7984),(717,228,253,8,10522.56),(718,228,180,10,18399.6),(719,228,61,4,11400),(720,229,91,7,11080.02),(721,229,29,14,11172),(722,229,138,12,32664.6),(723,229,53,7,19208),(724,230,180,7,12879.72),(725,230,185,9,18397.53),(726,231,60,17,9758),(727,231,168,14,22622.6),(728,231,124,7,13743.52),(729,231,143,15,49577.7),(730,232,140,1,991.2),(731,232,233,12,3064.32),(732,232,31,11,12980),(733,232,108,2,4273.92),(734,233,229,4,6938.2),(735,233,122,14,806.82),(736,234,114,5,14331.1),(737,234,128,14,21046.48),(738,235,144,16,32303.68),(739,235,151,8,25891.2),(740,236,194,14,13130.6),(741,236,74,9,3712.5),(742,236,245,1,3137.97),(743,236,18,10,22180),(744,236,96,9,28393.2),(745,237,261,11,22730.4),(746,237,45,2,5248),(747,237,146,17,12428.87),(748,238,172,13,30522.31),(749,238,128,8,12026.56),(750,238,242,4,7282.8),(751,238,38,16,10656),(752,239,136,16,41132.16),(753,239,242,6,10924.2),(754,239,163,9,25750.8),(755,240,116,6,19863.48),(756,240,191,17,2207.79),(757,240,48,1,462),(758,241,244,9,9583.2),(759,242,90,17,24010.8),(760,242,197,8,7672.8),(761,242,221,15,25063.2),(762,243,250,5,11441.25),(763,244,163,12,34334.4),(764,244,136,14,35990.64),(765,245,227,6,2439.36),(766,245,96,15,47322),(767,246,27,1,874),(768,246,115,9,27106.65),(769,247,79,3,4827.36),(770,247,226,6,11538.24),(771,247,125,1,1876.8),(772,247,167,9,11948.04),(773,248,177,11,14988.05),(774,248,103,15,49929.6),(775,248,37,10,23500),(776,249,51,1,2382),(777,249,97,5,1581.25),(778,250,65,3,2154),(779,250,214,4,12801.6),(780,250,202,15,32408.7),(781,251,143,13,42967.34),(782,252,190,7,5434.38),(783,252,210,14,16938.6),(784,252,192,2,627.2),(785,253,254,14,32795.7),(786,253,53,2,5488),(787,253,244,2,2129.6),(788,253,185,4,8176.68),(789,253,99,16,7948.8),(790,253,85,7,23505.3),(791,253,24,9,7218),(792,253,48,10,4620),(793,253,3,8,4240),(794,254,191,12,1558.44),(795,254,68,7,14462),(796,255,147,9,1716.39),(797,256,62,11,12166),(798,256,134,7,23857.12),(799,256,88,6,792.54),(800,256,159,14,2885.12),(801,256,120,14,39804.24),(802,257,15,12,3480),(803,258,147,14,2669.94),(804,258,165,9,30542.4),(805,258,48,2,924),(806,259,207,4,2260.44),(807,259,250,12,27459),(808,259,258,17,8045.76),(809,260,167,10,13275.6),(810,261,162,7,23182.39),(811,261,62,11,12166),(812,261,108,11,23506.56),(813,261,165,3,10180.8),(814,261,17,1,2550),(815,261,252,15,30291),(816,261,207,9,5085.99),(817,261,141,3,8497.44),(818,262,169,16,6815.68),(819,262,77,12,19822.08),(820,262,66,16,17152),(821,262,3,4,2120),(822,262,250,4,9153),(823,263,243,11,957.11),(824,263,130,6,5005.56),(825,263,191,14,1818.18),(826,263,165,2,6787.2),(827,263,13,4,4048),(828,264,79,5,8045.6),(829,265,67,17,23698),(830,265,165,12,40723.2),(831,265,157,6,1078.14),(832,265,153,2,1049.4),(833,266,170,1,2340),(834,266,110,7,17241.63),(835,266,155,11,10552.74),(836,267,6,6,1692),(837,268,182,14,26365.92),(838,268,252,14,28271.6),(839,268,137,16,39517.92),(840,268,260,7,10909.08),(841,268,168,12,19390.8),(842,269,59,1,968),(843,269,66,15,16080),(844,269,120,8,22745.28),(845,269,105,16,17109.12),(846,269,171,3,7090.02),(847,270,166,1,2966.88),(848,270,265,5,8855),(849,270,94,6,7756.56),(850,270,42,10,18220),(851,270,203,8,16077.6),(852,270,166,8,23735.04),(853,271,266,17,29003.7),(854,271,242,17,30951.9),(855,272,191,6,779.22),(856,272,65,3,2154),(857,272,185,7,14309.19),(858,272,143,17,56188.06),(859,272,120,14,39804.24),(860,273,61,1,2850),(861,273,120,1,2843.16),(862,273,60,8,4592),(863,274,205,12,39112.92),(864,274,173,8,2956.8),(865,275,176,13,5875.35),(866,276,182,13,24482.64),(867,276,257,9,7903.08),(868,276,220,9,29880.45),(869,276,117,8,24551.04),(870,276,24,2,1604),(871,277,218,5,12349.35),(872,277,78,11,35889.48),(873,278,268,4,4207.32),(874,278,158,11,31901.76),(875,278,206,9,21070.8),(876,279,237,14,44990.4),(877,279,102,6,12848.64),(878,280,45,1,2624),(879,280,257,13,11415.56),(880,280,72,16,24650.88),(881,280,17,12,30600),(882,281,63,17,7582),(883,282,255,12,950.4),(884,282,2,5,11850),(885,283,143,4,13220.72),(886,284,48,2,924),(887,284,93,8,25862.4),(888,285,33,12,32928),(889,285,217,14,44818.76),(890,285,74,9,3712.5),(891,286,10,1,518),(892,287,77,4,6607.36),(893,287,265,1,1771),(894,287,224,6,1138.5),(895,287,216,6,2901.84),(896,288,140,3,2973.6),(897,288,203,3,6029.1),(898,288,27,13,11362),(899,288,205,10,32594.1),(900,289,269,1,2490.84),(901,289,190,2,1552.68),(902,289,225,9,28441.53),(903,289,164,16,11720.8),(904,290,123,16,22886.4),(905,290,247,5,12973.2),(906,290,65,12,8616),(907,290,268,15,15777.45),(908,290,166,9,26701.92),(909,290,174,10,14226.7),(910,291,77,12,19822.08),(911,292,84,14,15460.48),(912,292,136,15,38561.4),(913,292,80,6,4443.66),(914,292,256,10,13616.5),(915,292,15,12,3480),(916,293,13,14,14168),(917,293,207,9,5085.99),(918,293,158,3,8700.48),(919,293,7,3,3924),(920,293,152,3,5866.56),(921,293,37,10,23500),(922,293,18,9,19962),(923,293,89,14,31895.36),(924,294,191,14,1818.18),(925,294,78,13,42414.84),(926,294,190,2,1552.68),(927,294,221,2,3341.76),(928,295,8,6,1236),(929,295,16,8,19088),(930,295,238,2,5803.68),(931,295,239,15,35757),(932,295,196,17,49255.8),(933,296,17,17,43350),(934,296,117,3,9206.64),(935,297,185,5,10220.85),(936,297,12,15,15900),(937,297,221,8,13367.04),(938,297,99,3,1490.4),(939,298,107,4,13553.44),(940,298,118,1,2781.66),(941,298,231,4,929.84),(942,298,153,1,524.7),(943,298,162,11,36429.47),(944,298,251,2,3445.44),(945,299,82,11,25928.32),(946,300,34,3,7560),(947,300,251,3,5168.16),(948,301,188,5,2388.3),(949,301,140,3,2973.6),(950,301,10,8,4144),(951,302,222,11,3356.1),(952,302,141,12,33989.76),(953,303,205,14,45631.74),(954,303,176,9,4067.55),(955,304,38,14,9324),(956,305,241,15,37296),(957,305,203,6,12058.2),(958,305,115,17,51201.45),(959,305,176,14,6327.3),(960,305,64,6,14796),(961,305,238,17,49331.28),(962,306,129,2,4818.24),(963,306,115,13,39154.05),(964,306,230,17,22416.03),(965,306,247,16,41514.24),(966,307,229,14,24283.7),(967,308,138,7,19054.35),(968,309,67,3,4182),(969,309,258,8,3786.24),(970,309,129,1,2409.12),(971,310,111,11,34822.92),(972,310,93,10,32328),(973,311,193,15,49015.8),(974,312,203,6,12058.2),(975,312,213,4,3440.88),(976,312,73,10,18905.6),(977,312,35,10,7660),(978,313,235,13,19604),(979,313,250,4,9153),(980,313,58,5,12620),(981,313,140,14,13876.8),(982,314,3,14,7420),(983,314,48,3,1386),(984,314,243,8,696.08),(985,315,181,2,5906.4),(986,316,185,13,26574.21),(987,316,84,10,11043.2),(988,316,181,6,17719.2),(989,316,103,16,53258.24),(990,317,40,2,2796),(991,317,145,11,4633.2),(992,317,25,5,10300),(993,317,88,3,396.27),(994,317,1,13,5954),(995,317,35,5,3830),(996,318,71,2,6201.8),(997,318,63,17,7582),(998,319,156,7,8098.3),(999,319,197,15,14386.5),(1000,319,18,8,17744),(1001,319,180,6,11039.76),(1002,320,223,3,6623.34),(1003,320,192,4,1254.4),(1004,320,24,1,802),(1005,320,101,5,15694.8),(1006,321,112,2,1244.3),(1007,321,102,10,21414.4),(1008,321,45,9,23616),(1009,322,2,15,35550),(1010,322,198,6,17435.16),(1011,322,180,12,22079.52),(1012,323,199,7,21716.31),(1013,323,148,4,5681.64),(1014,323,193,15,49015.8),(1015,323,71,14,43412.6),(1016,324,72,5,7703.4),(1017,325,153,8,4197.6),(1018,325,208,1,170.17),(1019,326,227,3,1219.68),(1020,326,107,8,27106.88),(1021,326,14,7,19488),(1022,326,266,14,23885.4),(1023,326,136,7,17995.32),(1024,326,46,1,1594),(1025,327,191,7,909.09),(1026,327,18,7,15526),(1027,328,42,4,7288),(1028,328,68,4,8264),(1029,328,81,10,7695.3),(1030,328,132,5,15310.5),(1031,329,200,1,1097.46),(1032,330,236,14,36901.9),(1033,330,196,3,8692.2),(1034,330,216,6,2901.84),(1035,330,148,1,1420.41),(1036,331,257,12,10537.44),(1037,331,266,4,6824.4),(1038,331,69,9,30221.1),(1039,331,60,14,8036),(1040,332,227,4,1626.24),(1041,332,43,3,2268),(1042,332,53,7,19208),(1043,333,162,16,52988.32),(1044,333,241,11,27350.4),(1045,333,209,14,40124.28),(1046,333,166,4,11867.52),(1047,333,11,1,932),(1048,334,185,5,10220.85),(1049,335,103,12,39943.68),(1050,335,29,8,6384),(1051,335,200,17,18656.82),(1052,335,107,2,6776.72),(1053,336,96,11,34702.8),(1054,336,98,17,58982.52),(1055,336,76,1,752.64),(1056,336,10,4,2072),(1057,337,214,3,9601.2),(1058,337,220,9,29880.45),(1059,337,22,9,4968),(1060,337,203,4,8038.8),(1061,337,240,7,7829.64),(1062,337,53,2,5488),(1063,338,216,5,2418.2),(1064,339,38,1,666),(1065,339,27,11,9614),(1066,339,120,9,25588.44),(1067,339,110,15,36946.35),(1068,339,267,1,775.2),(1069,340,186,12,24751.68),(1070,340,90,6,8474.4),(1071,341,37,8,18800),(1072,341,94,8,10342.08),(1073,341,138,1,2722.05),(1074,342,182,5,9416.4),(1075,342,262,16,19992),(1076,343,183,15,12254.85),(1077,343,199,13,40330.29),(1078,343,84,3,3312.96),(1079,343,181,4,11812.8),(1080,344,233,17,4341.12),(1081,344,142,10,23419.2),(1082,344,207,12,6781.32),(1083,344,91,10,15828.6),(1084,344,217,3,9604.02),(1085,345,70,16,16796.32),(1086,345,225,1,3160.17),(1087,345,136,7,17995.32),(1088,346,7,17,22236),(1089,346,66,11,11792),(1090,346,64,1,2466),(1091,346,68,9,18594),(1092,347,220,7,23240.35),(1093,347,151,13,42073.2),(1094,348,178,7,1486.1),(1095,348,51,7,16674),(1096,348,212,4,2675.2),(1097,348,235,3,4524),(1098,349,181,16,47251.2),(1099,349,215,2,649.74),(1100,349,80,11,8146.71),(1101,349,232,13,13029.12),(1102,350,107,17,57602.12),(1103,350,241,5,12432),(1104,351,225,16,50562.72),(1105,351,136,12,30849.12),(1106,351,70,1,1049.77),(1107,351,141,11,31157.28),(1108,352,260,12,18701.28),(1109,352,119,12,11281.92),(1110,353,73,11,20796.16),(1111,353,28,17,7310),(1112,353,34,1,2520),(1113,354,88,3,396.27),(1114,354,256,16,21786.4),(1115,354,136,10,25707.6),(1116,355,76,8,6021.12),(1117,355,10,11,5698),(1118,355,261,3,6199.2),(1119,355,189,9,15552.81),(1120,355,204,10,23139.2),(1121,356,19,10,19200),(1122,356,101,16,50223.36),(1123,356,32,1,284),(1124,356,180,15,27599.4),(1125,357,15,2,580),(1126,357,250,4,9153),(1127,357,203,1,2009.7),(1128,358,148,4,5681.64),(1129,358,37,9,21150),(1130,359,89,2,4556.48),(1131,359,254,6,14055.3),(1132,359,159,5,1030.4),(1133,359,67,8,11152),(1134,359,133,17,42350.4),(1135,360,184,5,6360.3),(1136,360,223,14,30908.92),(1137,360,191,13,1688.31),(1138,360,258,5,2366.4),(1139,361,104,8,6560.96),(1140,361,237,11,35349.6),(1141,361,63,15,6690),(1142,362,127,15,14610.75),(1143,363,63,13,5798),(1144,363,199,13,40330.29),(1145,363,256,2,2723.3),(1146,364,21,7,19390),(1147,364,10,9,4662),(1148,364,182,3,5649.84),(1149,364,237,13,41776.8),(1150,365,192,4,1254.4),(1151,365,125,3,5630.4),(1152,365,132,15,45931.5),(1153,365,257,15,13171.8),(1154,366,249,16,13207.04),(1155,366,90,4,5649.6),(1156,366,80,6,4443.66),(1157,366,140,11,10903.2),(1158,366,224,8,1518),(1159,367,217,10,32013.4),(1160,367,116,14,46348.12),(1161,368,83,14,14891.8),(1162,368,243,4,348.04),(1163,368,7,3,3924),(1164,368,63,12,5352),(1165,369,193,5,16338.6),(1166,370,72,6,9244.08),(1167,370,251,12,20672.64),(1168,370,253,11,14468.52),(1169,370,41,5,8780),(1170,371,93,11,35560.8),(1171,372,189,9,15552.81),(1172,372,67,10,13940),(1173,373,149,1,2816.35),(1174,373,102,12,25697.28),(1175,373,66,8,8576),(1176,373,117,7,21482.16),(1177,374,92,7,3241.7),(1178,374,50,11,18172),(1179,374,232,14,14031.36),(1180,375,244,1,1064.8),(1181,375,209,6,17196.12),(1182,375,108,13,27780.48),(1183,375,73,9,17015.04),(1184,376,29,11,8778),(1185,376,195,5,8073.6),(1186,376,119,2,1880.32),(1187,377,105,1,1069.32),(1188,377,161,2,2102.2),(1189,377,145,11,4633.2),(1190,377,261,6,12398.4),(1191,378,127,14,13636.7),(1192,378,19,17,32640),(1193,378,216,7,3385.48),(1194,378,245,12,37655.64),(1195,379,170,17,39780),(1196,379,12,3,3180),(1197,380,48,1,462),(1198,380,187,12,6998.4),(1199,381,12,10,10600),(1200,381,223,15,33116.7),(1201,382,142,13,30444.96),(1202,382,167,2,2655.12),(1203,383,199,7,21716.31),(1204,383,236,10,26358.5),(1205,383,263,15,45928.05),(1206,384,172,6,14087.22),(1207,385,200,10,10974.6),(1208,386,24,13,10426),(1209,386,181,6,17719.2),(1210,386,235,17,25636),(1211,387,235,14,21112),(1212,387,12,11,11660),(1213,387,187,4,2332.8),(1214,387,206,4,9364.8),(1215,388,254,10,23425.5),(1216,388,70,11,11547.47),(1217,388,25,14,28840),(1218,388,165,7,23755.2),(1219,388,55,9,18396),(1220,389,32,4,1136),(1221,389,180,8,14719.68),(1222,389,50,7,11564),(1223,390,152,17,33243.84),(1224,390,230,14,18460.26),(1225,390,160,17,22758.75),(1226,391,64,10,24660),(1227,392,217,1,3201.34),(1228,392,154,5,12108.25),(1229,392,68,5,10330),(1230,392,119,7,6581.12),(1231,393,241,7,17404.8),(1232,393,89,14,31895.36),(1233,394,108,15,32054.4),(1234,395,199,5,15511.65),(1235,395,29,16,12768),(1236,395,183,13,10620.87),(1237,395,258,2,946.56),(1238,395,10,12,6216),(1239,395,97,3,948.75),(1240,396,22,12,6624),(1241,397,139,17,25678.5),(1242,397,197,14,13427.4),(1243,397,88,9,1188.81),(1244,397,226,13,24999.52),(1245,398,250,4,9153),(1246,398,175,7,16247.14),(1247,398,212,12,8025.6),(1248,398,97,6,1897.5),(1249,399,2,8,18960),(1250,399,171,8,18906.72),(1251,399,170,3,7020),(1252,399,139,5,7552.5),(1253,400,99,5,2484),(1254,401,240,15,16777.8),(1255,401,234,13,22150.96),(1256,401,85,16,53726.4),(1257,401,87,15,35565.6),(1258,401,253,13,17099.16),(1259,401,148,7,9942.87),(1260,401,13,17,17204),(1261,401,55,5,10220),(1262,401,139,15,22657.5),(1263,402,224,7,1328.25),(1264,402,199,10,31023.3),(1265,402,221,14,23392.32),(1266,402,178,6,1273.8),(1267,403,215,3,974.61),(1268,403,10,12,6216),(1269,403,225,11,34761.87),(1270,403,197,2,1918.2),(1271,403,51,12,28584),(1272,404,113,3,1666.35),(1273,404,115,12,36142.2),(1274,404,242,12,21848.4),(1275,405,173,13,4804.8),(1276,405,93,2,6465.6),(1277,405,62,10,11060),(1278,405,246,3,5764.95),(1279,405,208,16,2722.72),(1280,406,196,3,8692.2),(1281,406,137,16,39517.92),(1282,406,11,2,1864),(1283,406,268,9,9466.47),(1284,406,123,5,7152),(1285,406,20,14,39704),(1286,406,110,4,9852.36),(1287,407,185,6,12265.02),(1288,407,248,12,14385.6),(1289,407,125,5,9384),(1290,407,245,7,21965.79),(1291,408,251,11,18949.92),(1292,408,40,2,2796),(1293,408,230,17,22416.03),(1294,409,200,7,7682.22),(1295,409,6,14,3948),(1296,409,201,8,14168),(1297,410,265,7,12397),(1298,410,30,7,5824),(1299,410,158,16,46402.56),(1300,410,16,4,9544),(1301,411,73,15,28358.4),(1302,411,41,15,26340),(1303,411,16,2,4772),(1304,411,236,9,23722.65),(1305,411,110,1,2463.09),(1306,411,247,9,23351.76),(1307,412,167,3,3982.68),(1308,412,200,5,5487.3),(1309,412,172,2,4695.74),(1310,412,98,8,27756.48),(1311,412,50,5,8260),(1312,412,54,12,15816),(1313,413,191,7,909.09),(1314,414,222,13,3966.3),(1315,414,241,17,42268.8),(1316,415,196,1,2897.4),(1317,415,119,17,15982.72),(1318,415,16,16,38176),(1319,415,157,8,1437.52),(1320,416,149,9,25347.15),(1321,416,147,4,762.84),(1322,416,158,2,5800.32),(1323,417,253,17,22360.44),(1324,418,189,2,3456.18),(1325,418,81,2,1539.06),(1326,419,183,12,9803.88),(1327,419,69,2,6715.8),(1328,420,228,16,37870.56),(1329,420,262,2,2499),(1330,420,96,12,37857.6),(1331,420,196,8,23179.2),(1332,420,184,6,7632.36),(1333,420,187,16,9331.2),(1334,420,173,11,4065.6),(1335,420,38,2,1332),(1336,421,56,8,22960),(1337,421,142,9,21077.28),(1338,421,135,13,34534.5),(1339,421,187,13,7581.6),(1340,421,244,8,8518.4),(1341,422,105,3,3207.96),(1342,422,251,9,15504.48),(1343,423,120,12,34117.92),(1344,423,158,17,49302.72),(1345,423,27,6,5244),(1346,423,198,10,29058.6),(1347,423,236,8,21086.8),(1348,423,249,15,12381.6),(1349,424,233,12,3064.32),(1350,424,155,6,5756.04),(1351,424,49,9,17262),(1352,424,12,3,3180),(1353,424,94,4,5171.04),(1354,424,165,11,37329.6),(1355,425,97,12,3795),(1356,425,189,7,12096.63),(1357,426,166,5,14834.4),(1358,426,76,5,3763.2),(1359,427,246,1,1921.65),(1360,427,258,16,7572.48),(1361,427,110,12,29557.08),(1362,427,250,5,11441.25),(1363,428,258,5,2366.4),(1364,428,36,16,44576),(1365,428,253,10,13153.2),(1366,428,162,3,9935.31),(1367,429,158,11,31901.76),(1368,430,126,3,5310.63),(1369,431,71,2,6201.8),(1370,431,176,10,4519.5),(1371,431,96,6,18928.8),(1372,432,135,12,31878),(1373,432,62,9,9954),(1374,432,254,10,23425.5),(1375,432,11,6,5592),(1376,433,257,7,6146.84),(1377,433,65,15,10770),(1378,433,83,13,13828.1),(1379,433,141,9,25492.32),(1380,433,261,15,30996),(1381,433,187,16,9331.2),(1382,434,6,4,1128),(1383,434,254,14,32795.7),(1384,434,251,13,22395.36),(1385,435,115,8,24094.8),(1386,435,252,7,14135.8),(1387,436,18,6,13308),(1388,436,56,1,2870),(1389,437,14,11,30624),(1390,437,185,7,14309.19),(1391,437,13,13,13156),(1392,438,209,3,8598.06),(1393,438,32,8,2272),(1394,438,212,13,8694.4),(1395,438,132,16,48993.6),(1396,439,164,5,3662.75),(1397,439,153,6,3148.2),(1398,439,53,9,24696),(1399,439,197,8,7672.8),(1400,440,171,7,16543.38),(1401,440,226,3,5769.12),(1402,440,57,7,17682),(1403,440,217,11,35214.74),(1404,441,122,6,345.78),(1405,441,192,17,5331.2),(1406,441,135,3,7969.5),(1407,441,192,17,5331.2),(1408,442,20,12,34032),(1409,442,32,8,2272),(1410,442,91,13,20577.18),(1411,443,49,3,5754),(1412,443,37,14,32900),(1413,443,239,16,38140.8),(1414,444,26,15,10410),(1415,444,173,16,5913.6),(1416,444,150,16,20296.64),(1417,445,212,8,5350.4),(1418,446,84,13,14356.16),(1419,446,233,4,1021.44),(1420,446,101,14,43945.44),(1421,447,60,2,1148),(1422,447,135,17,45160.5),(1423,447,202,7,15124.06),(1424,448,101,1,3138.96),(1425,448,146,1,731.11),(1426,448,141,14,39654.72),(1427,448,84,17,18773.44),(1428,449,132,3,9186.3),(1429,449,150,16,20296.64),(1430,449,169,2,851.96),(1431,449,119,10,9401.6),(1432,450,132,7,21434.7),(1433,450,269,6,14945.04),(1434,450,91,15,23742.9),(1435,450,112,17,10576.55),(1436,450,150,11,13953.94),(1437,450,97,2,632.5),(1438,450,109,14,43614.9),(1439,451,57,14,35364),(1440,452,24,6,4812),(1441,452,114,1,2866.22),(1442,452,49,8,15344),(1443,452,167,11,14603.16),(1444,452,98,12,41634.72),(1445,452,260,15,23376.6),(1446,453,116,16,52969.28),(1447,454,122,8,461.04),(1448,454,21,14,38780),(1449,454,200,15,16461.9),(1450,454,12,13,13780),(1451,455,252,8,16155.2),(1452,455,165,5,16968),(1453,456,237,4,12854.4),(1454,456,133,11,27403.2),(1455,456,121,2,1005.4),(1456,457,256,8,10893.2),(1457,457,143,11,36356.98),(1458,457,229,16,27752.8),(1459,457,86,11,21702.78),(1460,457,131,8,21190.4),(1461,457,44,14,32956),(1462,458,75,11,10986.36),(1463,458,48,10,4620),(1464,458,196,4,11589.6),(1465,458,262,13,16243.5),(1466,458,129,4,9636.48),(1467,458,181,7,20672.4),(1468,459,204,13,30080.96),(1469,459,61,7,19950),(1470,459,43,1,756),(1471,460,14,13,36192),(1472,460,244,14,14907.2),(1473,460,148,4,5681.64),(1474,461,118,16,44506.56),(1475,461,238,17,49331.28),(1476,462,48,12,5544),(1477,462,262,12,14994),(1478,462,131,15,39732),(1479,462,115,17,51201.45),(1480,462,146,1,731.11),(1481,463,216,13,6287.32),(1482,464,195,11,17761.92),(1483,465,233,10,2553.6),(1484,465,178,6,1273.8),(1485,465,162,9,29805.93),(1486,465,114,1,2866.22),(1487,466,134,13,44306.08),(1488,467,174,10,14226.7),(1489,468,83,3,3191.1),(1490,469,164,3,2197.65),(1491,469,128,5,7516.6),(1492,469,263,15,45928.05),(1493,469,28,14,6020),(1494,470,193,11,35944.92),(1495,470,28,13,5590),(1496,471,37,6,14100),(1497,472,63,16,7136),(1498,472,73,11,20796.16),(1499,472,4,10,28200),(1500,472,157,15,2695.35),(1501,473,161,17,17868.7),(1502,473,50,4,6608),(1503,473,212,10,6688),(1504,474,60,14,8036),(1505,474,245,10,31379.7),(1506,474,243,6,522.06),(1507,474,58,15,37860),(1508,475,167,2,2655.12),(1509,475,120,9,25588.44),(1510,476,182,13,24482.64),(1511,476,155,13,12471.42),(1512,476,249,7,5778.08),(1513,476,72,9,13866.12),(1514,476,244,7,7453.6),(1515,476,246,9,17294.85),(1516,476,181,9,26578.8),(1517,477,242,15,27310.5),(1518,477,19,14,26880),(1519,477,24,7,5614),(1520,478,82,10,23571.2),(1521,478,173,1,369.6),(1522,479,31,3,3540),(1523,479,142,17,39812.64),(1524,480,249,6,4952.64),(1525,480,137,2,4939.74),(1526,480,144,7,14132.86),(1527,481,147,17,3242.07),(1528,481,56,9,25830),(1529,481,206,4,9364.8),(1530,482,15,6,1740),(1531,482,124,9,17670.24),(1532,482,135,5,13282.5),(1533,482,161,8,8408.8),(1534,482,209,17,48722.34),(1535,482,131,15,39732),(1536,482,27,9,7866),(1537,483,161,4,4204.4),(1538,483,69,2,6715.8),(1539,484,84,15,16564.8),(1540,484,236,9,23722.65),(1541,484,63,14,6244),(1542,484,187,16,9331.2),(1543,485,146,1,731.11),(1544,485,250,4,9153),(1545,485,227,6,2439.36),(1546,486,269,15,37362.6),(1547,486,225,11,34761.87),(1548,486,47,12,8640),(1549,487,89,14,31895.36),(1550,487,7,7,9156),(1551,488,3,9,4770),(1552,489,13,4,4048),(1553,489,25,13,26780),(1554,490,265,15,26565),(1555,491,86,5,9864.9),(1556,491,22,14,7728),(1557,491,53,16,43904),(1558,491,196,6,17384.4),(1559,491,219,8,945.36),(1560,492,186,2,4125.28),(1561,492,83,1,1063.7),(1562,492,153,2,1049.4),(1563,493,237,11,35349.6),(1564,493,210,16,19358.4),(1565,493,150,16,20296.64),(1566,493,47,16,11520),(1567,494,65,9,6462),(1568,494,101,15,47084.4),(1569,494,144,12,24227.76),(1570,495,262,9,11245.5),(1571,496,40,4,5592),(1572,496,235,12,18096),(1573,497,125,11,20644.8),(1574,497,149,2,5632.7),(1575,497,246,13,24981.45),(1576,497,186,3,6187.92),(1577,498,36,11,30646),(1578,498,214,6,19202.4),(1579,498,116,10,33105.8),(1580,499,214,6,19202.4),(1581,499,104,2,1640.24),(1582,500,34,12,30240),(1583,500,97,13,4111.25),(1584,500,102,4,8565.76),(1585,501,180,7,12879.72),(1586,501,144,5,10094.9),(1587,501,94,9,11634.84),(1588,502,245,5,15689.85),(1589,502,219,13,1536.21),(1590,503,207,15,8476.65),(1591,503,65,3,2154),(1592,503,2,14,33180),(1593,504,7,14,18312),(1594,505,88,16,2113.44),(1595,505,182,10,18832.8),(1596,505,26,2,1388),(1597,505,145,8,3369.6),(1598,505,180,8,14719.68),(1599,506,231,7,1627.22),(1600,507,110,4,9852.36),(1601,507,149,15,42245.25),(1602,507,105,9,9623.88),(1603,507,65,5,3590),(1604,508,128,3,4509.96),(1605,509,63,6,2676),(1606,509,45,12,31488),(1607,510,79,1,1609.12),(1608,510,147,16,3051.36),(1609,511,261,2,4132.8),(1610,511,27,4,3496),(1611,511,97,3,948.75),(1612,512,103,9,29957.76),(1613,513,50,1,1652),(1614,513,239,15,35757),(1615,513,54,3,3954),(1616,513,42,12,21864),(1617,514,152,9,17599.68),(1618,514,184,6,7632.36),(1619,514,120,14,39804.24),(1620,514,130,3,2502.78),(1621,514,60,13,7462),(1622,515,230,13,17141.67),(1623,515,91,11,17411.46),(1624,516,267,5,3876),(1625,516,220,3,9960.15),(1626,516,258,17,8045.76),(1627,516,79,1,1609.12),(1628,517,192,16,5017.6),(1629,517,21,7,19390),(1630,517,108,15,32054.4),(1631,517,186,11,22689.04),(1632,517,196,7,20281.8),(1633,517,199,12,37227.96),(1634,518,56,14,40180),(1635,518,201,13,23023),(1636,518,146,4,2924.44),(1637,519,143,8,26441.44),(1638,519,219,1,118.17),(1639,519,178,1,212.3),(1640,519,9,17,33932),(1641,519,24,13,10426),(1642,519,154,8,19373.2),(1643,519,123,6,8582.4),(1644,520,183,4,3267.96),(1645,520,80,8,5924.88),(1646,520,149,3,8449.05),(1647,520,248,8,9590.4),(1648,520,64,6,14796),(1649,521,131,8,21190.4),(1650,521,69,17,57084.3),(1651,521,203,5,10048.5),(1652,522,255,17,1346.4),(1653,522,51,16,38112),(1654,522,103,7,23300.48),(1655,522,11,16,14912),(1656,522,71,5,15504.5),(1657,523,60,9,5166),(1658,523,239,8,19070.4),(1659,523,199,11,34125.63),(1660,523,60,7,4018),(1661,524,254,15,35138.25),(1662,524,68,5,10330),(1663,524,262,4,4998),(1664,524,30,15,12480),(1665,524,210,11,13308.9),(1666,524,269,8,19926.72),(1667,525,265,14,24794),(1668,526,193,14,45748.08),(1669,526,263,2,6123.74),(1670,526,265,4,7084),(1671,526,131,14,37083.2),(1672,527,27,10,8740),(1673,527,125,12,22521.6),(1674,527,220,6,19920.3),(1675,527,79,1,1609.12),(1676,527,251,5,8613.6),(1677,527,245,7,21965.79),(1678,528,118,3,8344.98),(1679,529,8,16,3296),(1680,529,155,6,5756.04),(1681,529,26,11,7634),(1682,530,135,6,15939),(1683,530,39,17,14042),(1684,530,255,16,1267.2),(1685,530,136,2,5141.52),(1686,531,179,6,9086.4),(1687,531,18,6,13308),(1688,531,157,10,1796.9),(1689,531,50,6,9912),(1690,531,139,17,25678.5),(1691,532,88,13,1717.17),(1692,532,84,4,4417.28),(1693,532,129,11,26500.32),(1694,532,35,6,4596),(1695,532,58,6,15144),(1696,533,106,16,57005.76),(1697,533,132,4,12248.4),(1698,533,87,17,40307.68),(1699,534,38,10,6660),(1700,534,232,16,16035.84),(1701,534,153,6,3148.2),(1702,535,238,10,29018.4),(1703,535,62,6,6636),(1704,536,244,16,17036.8),(1705,536,226,16,30768.64),(1706,536,68,2,4132),(1707,537,61,14,39900),(1708,537,60,4,2296),(1709,537,65,8,5744),(1710,537,213,14,12043.08),(1711,538,12,1,1060),(1712,538,34,6,15120),(1713,538,43,14,10584),(1714,538,67,14,19516),(1715,539,155,1,959.34),(1716,539,35,13,9958),(1717,540,99,5,2484),(1718,540,105,4,4277.28),(1719,540,95,9,1098.9),(1720,541,147,14,2669.94),(1721,541,226,9,17307.36),(1722,541,162,4,13247.08),(1723,541,192,7,2195.2),(1724,542,148,4,5681.64),(1725,542,2,16,37920),(1726,542,241,12,29836.8),(1727,542,101,13,40806.48),(1728,542,222,1,305.1),(1729,542,234,16,27262.72),(1730,543,5,7,17024),(1731,543,71,9,27908.1),(1732,543,120,16,45490.56),(1733,543,37,17,39950),(1734,543,203,9,18087.3),(1735,543,248,16,19180.8),(1736,544,105,6,6415.92),(1737,544,167,11,14603.16),(1738,545,173,1,369.6),(1739,546,37,6,14100),(1740,546,27,12,10488),(1741,547,209,14,40124.28),(1742,547,92,3,1389.3),(1743,548,140,16,15859.2),(1744,549,102,15,32121.6),(1745,550,252,12,24232.8),(1746,550,20,16,45376),(1747,550,17,6,15300),(1748,551,208,5,850.85),(1749,551,116,10,33105.8),(1750,551,103,13,43272.32),(1751,551,234,3,5111.76),(1752,552,234,3,5111.76),(1753,552,260,9,14025.96),(1754,552,66,6,6432),(1755,552,259,17,27432.9),(1756,553,101,2,6277.92),(1757,553,49,11,21098),(1758,553,141,12,33989.76),(1759,553,204,8,18511.36),(1760,554,208,6,1021.02),(1761,554,21,8,22160),(1762,555,239,8,19070.4),(1763,555,242,12,21848.4),(1764,556,66,8,8576),(1765,557,144,7,14132.86),(1766,557,139,2,3021),(1767,558,249,7,5778.08),(1768,558,126,16,28323.36),(1769,558,57,14,35364),(1770,559,32,10,2840),(1771,559,138,13,35386.65),(1772,559,78,5,16313.4),(1773,559,55,11,22484),(1774,559,253,10,13153.2),(1775,560,78,9,29364.12),(1776,560,261,9,18597.6),(1777,560,77,7,11562.88),(1778,561,269,15,37362.6),(1779,561,84,7,7730.24),(1780,562,9,13,25948),(1781,562,104,3,2460.36),(1782,562,251,5,8613.6),(1783,563,47,12,8640),(1784,563,9,14,27944),(1785,563,161,5,5255.5),(1786,563,232,3,3006.72),(1787,564,116,16,52969.28),(1788,564,184,4,5088.24),(1789,565,62,13,14378),(1790,565,123,13,18595.2),(1791,565,76,6,4515.84),(1792,565,258,14,6625.92),(1793,565,103,17,56586.88),(1794,566,79,8,12872.96),(1795,566,131,3,7946.4),(1796,566,88,4,528.36),(1797,567,184,12,15264.72),(1798,567,228,13,30769.83),(1799,567,178,15,3184.5),(1800,567,140,5,4956),(1801,568,263,6,18371.22),(1802,568,82,8,18856.96),(1803,568,264,7,14651),(1804,569,109,12,37384.2),(1805,569,36,6,16716),(1806,570,148,7,9942.87),(1807,570,137,10,24698.7),(1808,571,73,7,13233.92),(1809,571,212,11,7356.8),(1810,571,25,15,30900),(1811,572,158,6,17400.96),(1812,572,125,13,24398.4),(1813,572,198,16,46493.76),(1814,572,193,13,42480.36),(1815,573,31,8,9440),(1816,574,137,17,41987.79),(1817,574,207,14,7911.54),(1818,574,158,5,14500.8),(1819,575,197,3,2877.3),(1820,575,13,7,7084),(1821,575,153,9,4722.3),(1822,575,222,6,1830.6),(1823,575,119,8,7521.28),(1824,576,186,2,4125.28),(1825,576,48,17,7854),(1826,577,116,13,43037.54),(1827,577,234,6,10223.52),(1828,577,73,5,9452.8),(1829,577,3,17,9010),(1830,577,231,5,1162.3),(1831,578,142,7,16393.44),(1832,578,108,15,32054.4),(1833,578,156,2,2313.8),(1834,578,190,9,6987.06),(1835,578,69,13,43652.7),(1836,578,8,15,3090),(1837,578,125,2,3753.6),(1838,579,135,13,34534.5),(1839,579,175,14,32494.28),(1840,580,83,4,4254.8),(1841,580,97,3,948.75),(1842,581,30,5,4160),(1843,581,21,3,8310),(1844,581,246,2,3843.3),(1845,581,31,10,11800),(1846,581,221,2,3341.76),(1847,581,231,9,2092.14),(1848,581,167,4,5310.24),(1849,582,138,14,38108.7),(1850,582,240,7,7829.64),(1851,582,84,17,18773.44),(1852,583,28,1,430),(1853,583,246,4,7686.6),(1854,584,155,12,11512.08),(1855,584,18,10,22180),(1856,584,159,16,3297.28),(1857,584,164,16,11720.8),(1858,585,15,16,4640),(1859,585,79,17,27355.04),(1860,585,104,17,13942.04),(1861,585,50,17,28084),(1862,585,113,10,5554.5),(1863,586,55,17,34748),(1864,586,26,6,4164),(1865,586,158,17,49302.72),(1866,586,164,15,10988.25),(1867,586,44,16,37664),(1868,587,201,17,30107),(1869,587,46,2,3188),(1870,587,112,9,5599.35),(1871,587,79,12,19309.44),(1872,588,267,11,8527.2),(1873,588,70,5,5248.85),(1874,588,60,11,6314),(1875,588,110,12,29557.08),(1876,589,106,16,57005.76),(1877,589,134,17,57938.72),(1878,590,153,6,3148.2),(1879,590,25,5,10300),(1880,590,43,6,4536),(1881,591,121,1,502.7),(1882,591,23,10,22400),(1883,591,104,13,10661.56),(1884,592,43,16,12096),(1885,592,112,12,7465.8),(1886,592,37,13,30550),(1887,593,174,5,7113.35),(1888,593,45,6,15744),(1889,593,232,13,13029.12),(1890,593,63,15,6690),(1891,593,102,9,19272.96),(1892,593,230,5,6592.95),(1893,594,108,7,14958.72),(1894,594,211,7,13067.32),(1895,594,101,4,12555.84),(1896,594,186,5,10313.2),(1897,595,157,4,718.76),(1898,595,234,13,22150.96),(1899,595,93,3,9698.4),(1900,595,33,7,19208),(1901,596,192,11,3449.6),(1902,596,70,9,9447.93),(1903,596,57,4,10104),(1904,596,106,17,60568.62),(1905,597,109,17,52960.95),(1906,597,264,7,14651),(1907,597,63,10,4460),(1908,597,236,7,18450.95),(1909,597,214,14,44805.6),(1910,598,213,3,2580.66),(1911,598,175,7,16247.14),(1912,598,166,6,17801.28),(1913,599,201,10,17710),(1914,599,135,8,21252),(1915,599,58,10,25240),(1916,599,183,3,2450.97),(1917,599,129,15,36136.8),(1918,599,23,14,31360),(1919,600,22,10,5520),(1920,601,71,16,49614.4),(1921,601,211,12,22401.12),(1922,601,73,13,24577.28),(1923,601,266,14,23885.4),(1924,601,92,1,463.1),(1925,602,133,1,2491.2),(1926,602,151,1,3236.4),(1927,603,80,16,11849.76),(1928,604,220,16,53120.8),(1929,604,220,13,43160.65),(1930,605,265,14,24794),(1931,605,189,5,8640.45),(1932,606,47,12,8640),(1933,607,103,14,46600.96),(1934,607,25,6,12360),(1935,607,215,15,4873.05),(1936,607,11,6,5592),(1937,607,164,9,6592.95),(1938,608,28,6,2580),(1939,608,86,5,9864.9),(1940,609,79,3,4827.36),(1941,609,119,16,15042.56),(1942,609,49,5,9590),(1943,609,259,5,8068.5),(1944,610,13,8,8096),(1945,610,25,13,26780),(1946,611,137,11,27168.57),(1947,612,149,4,11265.4),(1948,613,91,12,18994.32),(1949,613,135,17,45160.5),(1950,613,243,17,1479.17);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'ARASAS',45,'MOSTRADOR','adsfa','1997-07-05','correo_pr_1@hotmail.com','Chiapas','Tuxtla Gutierrez','AV.2 NORTE ORIENTE S/N-225','29072'),(2,'TRRTJR',46,'LOURDES LOPEZ GUILLEN','wrqer','1988-04-27','correo_pr_2@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. TACANA MANZANA 29 #37','29031'),(3,'SADVXCB',47,'MARIA ERNESTINA AGUSTIN PEREZ','asa','1981-03-03','correo_pr_3@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. 5A SUR ORIENTE 654-B','29091'),(4,'GHLHJ',48,'MARIA CONCEPCION CHULIN GORDIL','qewr','1996-05-24','correo_pr_4@hotmail.com','Chiapas','Tuxtla Gutierrez','CALLE 5A OTE. NORTE','29054'),(5,'QEWRF',49,'LUCIA ELIZABETH LOPEZ GONZALEZ','adfs','1996-07-24','correo_pr_5@hotmail.com','Chiapas','Tuxtla Gutierrez','9 NORTE ENTRE 6 Y 7 ORIENTE','29057'),(6,'QQSCCAA',50,'ANA YANSI CRUZ GARCIA','qwer','1988-04-02','correo_pr_6@hotmail.com','Chiapas','Tuxtla Gutierrez','5A ORIENTE NORTE ENTRE 1A Y 2A NORTE','29090'),(7,'UI??HJKL',51,'MARIA ENCARNACION SARMIENTO SA','zcxv','1984-06-13','correo_pr_7@hotmail.com','Chiapas','Tuxtla Gutierrez','2 ORIENTE SUR #217 ENTRE 1 Y 2 SUR','29047'),(8,'ACSDFVSC',52,'MARIA BEININA GOMEZ ALVAREZ','qer','1988-01-05','correo_pr_8@hotmail.com','Chiapas','Tuxtla Gutierrez','4 OTE SUR ENTRE 4 Y 5 SUR #538','29029'),(9,'SDFVXCAAS',53,'LUZ MARIA MORALES TORRES','asdf','1994-07-28','correo_pr_9@hotmail.com','Chiapas','Tuxtla Gutierrez','AV OLIVO SUR 112-A','29080'),(10,'AXCXCV',54,'VERONICA LOPEZ CRUZ','wetwtr','1995-06-24','correo_pr_10@hotmail.com','Chiapas','Tuxtla Gutierrez','7A ORIENTE #451 ENTRE 3A Y 4A SUR','29061'),(11,'ASERASD',55,'GUADALUPE NURIULU GORDILLO','ury','1980-04-29','correo_pr_11@hotmail.com','Chiapas','Tuxtla Gutierrez','MNZ.45 LOT.12','29022'),(12,'ADVSASD',56,'WALTER DILTIEV SOTO MORALES','kju','1999-03-15','correo_pr_12@hotmail.com','Chiapas','Tuxtla Gutierrez','AV.2 NORTE ORIENTE S/N-225','29044'),(13,'ASFWASFE',57,'MUNICIPIO DE VENUSTIANO CARRAN','sdfs','1984-06-01','correo_pr_13@hotmail.com','Chiapas','Carranza','CONOCIDO PALACIO MUNICIAPAL','29052'),(14,'WRTWEFAS',58,'BEATRIZ ADRIANA PENAGOS GONZAL','rtyutr','1982-05-28','correo_pr_14@hotmail.com','Chiapas','San Cristobal','3A CALLE ORIENTE SUR #350-A','29056'),(15,'WEFADE',59,'YARENI HERNANDEZ GARCIA','zcxv','1982-10-01','correo_pr_15@hotmail.com','Chiapas','Chilon','AV. TACANA MANZANA 29 #37 Se actualizan los datos de este cliente','29025'),(16,'DVSSSVS',60,'METALLICA','wer','1988-08-21','correo_pr_16@hotmail.com','Chiapas','Tuxtla Gutierrez','AV. 5A SUR ORIENTE 654-B','29009'),(17,'AWETWWD',61,'FABIOLA INDILI CUNDAPI','zxcv','1988-05-05','correo_pr_17@hotmail.com','Chiapas','Tuxtla Gutierrez','CALLE 5A OTE. NORTE','29039'),(18,'WEEFFEW',62,'ELIZA AGUILAR','ewt','1990-05-18','correo_pr_18@hotmail.com','Chiapas','Tuxtla Gutierrez','9 NORTE ENTRE 6 Y 7 ORIENTE','29047'),(19,'ADSVSAC',63,'PAOLA LIZETH GALLEGOS DEL CARP','xzcv','1986-10-27','correo_pr_19@hotmail.com','Chiapas','Tuxtla Gutierrez','5A ORIENTE NORTE ENTRE 1A Y 2A NORTE','29016'),(20,'ASFSASD',64,'DANIA MARGARITA GOMEZ HERNANDE','ewt','1983-05-29','correo_pr_20@hotmail.com','Chiapas','Tuxtla Gutierrez','2 ORIENTE SUR #217 ENTRE 1 Y 2 SUR','29082'),(21,'REFCPR00021',114,'UN CLIENTE DE PRUEBA','PR21','1996-08-12','fakemail@mail.com','CHIAPAS','TONALA','POR TU CORAZ??N','26000'),(22,'REFCPR00022',115,'NUEVO CLIENTE DE PRUEBA','PR022','1994-12-11','fakemail@mail.com','Chiapas','Tuxtla Gutierrez','Cerca del centro de la ciudad donde todo est?? bien feo y sucio XDD','29000');
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
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `rfc` (`rfc`),
  UNIQUE KEY `curp` (`curp`),
  KEY `Fk_sucursal_empleado` (`id_sucursal`),
  CONSTRAINT `Fk_sucursal_empleado` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados`
--

LOCK TABLES `empleados` WRITE;
/*!40000 ALTER TABLE `empleados` DISABLE KEYS */;
INSERT INTO `empleados` VALUES (1,6,'PEJU920506ABC','PEJU920506HDFRRN07','Juan P??rez','Juan','1981-01-19','juanfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 5 de Mayo #123','29445','xYz#12Ab'),(2,1,'GAMA880213DEF','GAMA880213MHDFRRN0','Mar??a Garc??a','Mar??a','1986-07-01','mar??afakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Ju??rez #456','29465','P@ssw0rd'),(3,3,'LOCA780704GHI','LOCA780704MHDFRL07','Carlos L??pez','Carlos','1988-01-23','carlosfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Belisario Dom??nguez #789','29198','Qwerty!23'),(4,3,'MAAA950312JKL','MAAA950312MHDFRN02','Ana Mart??nez','Ana','1989-09-21','anafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Central #1010','29146','Summer*99'),(5,5,'ROLU890522MNO','ROLU890522MHDFDS03','Luis Rodr??guez','Luis','1983-09-13','luisfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Hidalgo #234','29365','Secure$456'),(6,3,'FELO930608PQR','FELO930608MHDFNR00','Laura Fern??ndez','Laura','1995-06-30','laurafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Prolongaci??n 16 de Septiembre #567','29252','Orange$Tree67'),(7,3,'GOAL900715STU','GOAL900715HDFNNL08','Alejandro Gonz??lez','Alejandro','1982-12-09','alejandrofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 20 de Noviembre #890','29316','1Lov3Cats'),(8,2,'DISO941118VWX','DISO941118MHDFXF09','Sof??a D??az','Sof??a','1994-11-16','sof??afakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calzada Sumidero #1112','29410','C0ffee&Milk'),(9,5,'SAMA861002YZA','SAMA861002MHDFNH04','Manuel S??nchez','Manuel','1984-10-02','manuelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Los Laguitos #1314','29310','Chocol8!Chip'),(10,2,'RAPA861220BCD','RAPA861220MHDFMR05','Patricia Ram??rez','Patricia','1995-10-23','patriciafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Constituci??n #1516','29066','P@rkRang3r'),(11,6,'TOMI900429EFG','TOMI900429MHDFSL06','Miguel Torres','Miguel','1991-05-27','miguelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Cuauht??moc #1718','29267','Wint3rFrost$'),(12,2,'FLOA880917HIJ','FLOA880917MHDFRN07','Andrea Flores','Andrea','1989-02-14','andreafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Allende #1920','29119','P@ssionFl0w3r'),(13,4,'HEDO960725KLM','HEDO960725MHDFDN08','Daniel Hern??ndez','Daniel','1993-01-25','danielfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. ??ngel Albino Corzo #2122','29097','M0onlight$ky'),(14,5,'VACA890314NOP','VACA890314MHDFRM09','Carmen Vargas','Carmen','1991-09-26','carmenfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 15 de Julio #2324','29042','B3autiful*Sun'),(15,4,'MEOJ940610QRS','MEOJ940610MHDFND10','Eduardo Mendoza','Eduardo','1982-04-01','eduardofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Revoluci??n #2526','29439','S0larSyst3m!'),(16,2,'CUGA950826TUV','CUGA950826MHDFNB11','Gabriela Cruz','Gabriela','1993-01-10','gabrielafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Juventud #2728','29413','Starry*Night'),(17,2,'MOMJ891121WXY','MOMJ891121MHDFNR12','Javier Morales','Javier','1984-10-13','javierfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Miguel Alem??n #2930','29144','Danc1ngWav3s'),(18,5,'RIAR930601ZAB','RIAR930601MHDFQL13','Raquel R??os','Raquel','1983-08-02','raquelfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle Reforma #3132','29221','R@inbow@fterRai'),(19,2,'ORFF871214BCD','ORFF871214MHDFNN14','Francisco Ortega','Francisco','1994-09-18','franciscofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Doctor Belisario Dom??nguez #3334','29496','Sparkl1ngDiam0n'),(20,3,'NUNN900225EFG','NUNN900225MHDFXT15','Natalia N????ez','Natalia','1991-07-15','nataliafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. Central Poniente #3536','29209','F0restWhisper$'),(21,4,'SIDI910517HJK','SIDI910517MHDFLG16','Diego Silva','Diego','1987-05-15','diegofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 2 Norte #3738','29133','S@ndyB3@ch!'),(22,1,'AGVV930811PLM','AGVV930811MHDFGT17','Valentina Aguilar','Valentina','1983-06-26','valentinafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 4 Oriente #3930','29048','M0unt@inT0p'),(23,6,'MORR921010KJH','MORR921010MHDFDC18','Ricardo Mora','Ricardo','1982-05-30','ricardofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. Laguitos #4132','29395','SecretG@rden$'),(24,6,'PECM940731QWE','PECM940731MHDFML19','Camila Pe??a','Camila','1992-04-01','camilafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 6 Sur #4334','29146','H@ppyH3arts'),(25,3,'MONG900416RFG','MONG900416MHDFNL20','Guillermo Montoya','Guillermo','1989-04-25','guillermofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 8 Poniente #4536','29482','CoolBreez3$'),(26,3,'RIMA910218TGB','RIMA910218MHDFTS21','Martina Rivas','Martina','1997-02-25','martinafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Convivencia #4738','29295','S1lv3rLin1ngs'),(27,2,'CASA960511YHN','CASA960511MHDFTR22','Adri??n Castillo','Adri??n','1989-06-18','adri??nfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 10 Oriente #4930','29148','Myst1cM00n'),(28,1,'PARR910713GBV','PARR910713MHDFNS23','Renata Paredes','Renata','1981-06-21','renatafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 12 Sur #4132','29027','H3althyL1f3$'),(29,6,'GUSE921215CVB','GUSE921215MHDFBT24','Sebasti??n Guzm??n','Sebasti??n','1988-08-10','sebasti??nfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 14 Norte #4334','29283','D3epBlu3Sea'),(30,2,'PEIB930320SDX','PEIB930320MHDFRX25','Isabella Peralta','Isabella','1994-10-16','isabellafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 16 Oriente #4536','29323','Fr3shStart!'),(31,4,'VAJJ900504KLS','VAJJ900504MHDFXS26','Jos?? Valenzuela','Jos??','1981-09-15','jos??fakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 18 Sur #4738','29100','Laugh@Lot$'),(32,3,'ROAA870827LMD','ROAA870827MHDFLN27','Antonella Rojas','Antonella','1986-01-08','antonellafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 20 Norte #4930','29130','Adventur3T1m3'),(33,1,'COAA910623LKP','COAA910623MHDFNR28','Andr??s Cordero','Andr??s','1982-12-26','andr??sfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 22 Oriente #5132','29034','H0lid@y@Dream'),(34,2,'SAVV940712LPQ','SAVV940712MHDFTC29','Victoria Salazar','Victoria','1982-05-28','victoriafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 24 Sur #5334','29294','Playful*Smil3'),(35,1,'BAIN930802EJK','BAIN930802MHDFGN30','Ignacio Bravo','Ignacio','1996-06-08','ignaciofakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 26 Norte #5536','29206','B3autifulM3m0ri'),(36,1,'ACOM870101JKH','ACOM870101MHDFVN31','Miranda Acosta','Miranda','1993-08-15','mirandafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 28 Oriente #5738','29040','Wish3s&Candl3s'),(37,2,'FUAM900510OPW','FUAM900510MHDFRT32','Mat??as Fuentes','Mat??as','1994-07-06','mat??asfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Paseo de la Cultura #5930','29387','Creat1veM1nd$'),(38,6,'OREL950315TGY','OREL950315MHDFRT33','Elena Orellana','Elena','1992-03-20','elenafakemail@mail.com','Chiapas','Tuxtla Gutierrez','Calle 30 Sur #5132','29203','B3Insp1r3d!'),(39,2,'ESBM910717GHJ','ESBM910717MHDFNH34','Benjam??n Espinoza','Benjam??n','1990-11-05','benjam??nfakemail@mail.com','Chiapas','Tuxtla Gutierrez','Av. 32 Norte #5334','29393','P0sitiveV1bes'),(40,2,'LEVR920321FDS','LEVR920321MHDFNN35','Ren??e Leiva','Ren??e','1996-04-30','ren??efakemail@mail.com','Chiapas','Tuxtla Gutierrez','Blvd. 34 Oriente #5536','29141','En3rgetic*Day'),(41,1,'XAAXAXXXAAAA','XAXAXAXAXAXA','ADMIN','ADMIN','1999-01-01','NA','NA','NA','NA','NA','123'),(43,4,'GOPP9708241M8','GOPP970824HCSMRB01','PABLO JESUS GOMEZ PEREZ','PABLO','1997-11-14','fakemail@mail.com','CHIAPAS','TUXTLA','4A ORIENTE SUR #448 BARRIO SAN ROQUE','29000',''),(44,6,'GTJO970821HM1','GTJO970821HCSMRB06','GUTIERREZ TORREZ JOAQUIN','JOAT','1997-08-21','joaq_torres@fakemail.com','Durango','Durango','na','1111','');
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
) ENGINE=InnoDB AUTO_INCREMENT=2141 DEFAULT CHARSET=utf32 COLLATE=utf32_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `existencia_x_sucursal`
--

LOCK TABLES `existencia_x_sucursal` WRITE;
/*!40000 ALTER TABLE `existencia_x_sucursal` DISABLE KEYS */;
INSERT INTO `existencia_x_sucursal` VALUES (1,1,1,186),(2,2,1,162),(3,3,1,136),(4,4,1,133),(5,5,1,104),(6,6,1,63),(7,7,1,199),(8,8,1,173),(9,9,1,30),(10,10,1,177),(11,11,1,70),(12,12,1,71),(13,13,1,35),(14,14,1,5),(15,15,1,126),(16,16,1,196),(17,17,1,144),(18,18,1,107),(19,19,1,45),(20,20,1,135),(21,21,1,163),(22,22,1,123),(23,23,1,7),(24,24,1,32),(25,25,1,169),(26,26,1,90),(27,27,1,51),(28,28,1,147),(29,29,1,28),(30,30,1,49),(31,31,1,138),(32,32,1,40),(33,33,1,157),(34,34,1,6),(35,35,1,180),(36,36,1,180),(37,37,1,6),(38,38,1,114),(39,39,1,177),(40,40,1,147),(41,41,1,123),(42,42,1,32),(43,43,1,40),(44,44,1,123),(45,45,1,97),(46,46,1,86),(47,47,1,23),(48,48,1,162),(49,49,1,32),(50,50,1,99),(51,51,1,126),(52,52,1,102),(53,53,1,182),(54,54,1,6),(55,55,1,162),(56,56,1,199),(57,57,1,0),(58,58,1,163),(59,59,1,9),(60,60,1,134),(61,61,1,7),(62,62,1,181),(63,63,1,180),(64,64,1,199),(65,65,1,3),(66,66,1,105),(67,67,1,110),(68,68,1,86),(69,69,1,124),(70,70,1,167),(71,71,1,34),(72,72,1,45),(73,73,1,8),(74,74,1,60),(75,75,1,49),(76,76,1,194),(77,77,1,120),(78,78,1,36),(79,79,1,199),(80,80,1,60),(81,81,1,194),(82,82,1,109),(83,83,1,35),(84,84,1,15),(85,85,1,116),(86,86,1,1),(87,87,1,180),(88,88,1,191),(89,89,1,104),(90,90,1,50),(91,91,1,108),(92,92,1,158),(93,93,1,63),(94,94,1,39),(95,95,1,25),(96,96,1,13),(97,97,1,35),(98,98,1,187),(99,99,1,132),(100,100,1,107),(101,101,1,2),(102,102,1,163),(103,103,1,121),(104,104,1,180),(105,105,1,112),(106,106,1,189),(107,107,1,111),(108,108,1,44),(109,109,1,121),(110,110,1,163),(111,111,1,150),(112,112,1,142),(113,113,1,180),(114,114,1,78),(115,115,1,155),(116,116,1,181),(117,117,1,10),(118,118,1,170),(119,119,1,101),(120,120,1,157),(121,121,1,26),(122,122,1,26),(123,123,1,192),(124,124,1,157),(125,125,1,158),(126,126,1,119),(127,127,1,185),(128,128,1,16),(129,129,1,142),(130,130,1,181),(131,131,1,161),(132,132,1,26),(133,133,1,191),(134,134,1,188),(135,135,1,107),(136,136,1,114),(137,137,1,161),(138,138,1,155),(139,139,1,64),(140,140,1,100),(141,141,1,184),(142,142,1,37),(143,143,1,70),(144,144,1,68),(145,145,1,91),(146,146,1,126),(147,147,1,82),(148,148,1,102),(149,149,1,52),(150,150,1,11),(151,151,1,55),(152,152,1,36),(153,153,1,28),(154,154,1,144),(155,155,1,53),(156,156,1,165),(157,157,1,97),(158,158,1,133),(159,159,1,190),(160,160,1,20),(161,161,1,59),(162,162,1,49),(163,163,1,29),(164,164,1,144),(165,165,1,190),(166,166,1,36),(167,167,1,87),(168,168,1,184),(169,169,1,190),(170,170,1,158),(171,171,1,127),(172,172,1,8),(173,173,1,100),(174,174,1,24),(175,175,1,157),(176,176,1,14),(177,177,1,159),(178,178,1,13),(179,179,1,161),(180,180,1,152),(181,181,1,172),(182,182,1,16),(183,183,1,164),(184,184,1,39),(185,185,1,106),(186,186,1,165),(187,187,1,176),(188,188,1,6),(189,189,1,11),(190,190,1,154),(191,191,1,195),(192,192,1,95),(193,193,1,85),(194,194,1,121),(195,195,1,31),(196,196,1,105),(197,197,1,165),(198,198,1,85),(199,199,1,37),(200,200,1,137),(201,201,1,166),(202,202,1,25),(203,203,1,55),(204,204,1,39),(205,205,1,49),(206,206,1,160),(207,207,1,173),(208,208,1,54),(209,209,1,177),(210,210,1,3),(211,211,1,149),(212,212,1,147),(213,213,1,189),(214,214,1,120),(215,215,1,194),(216,216,1,19),(217,217,1,124),(218,218,1,21),(219,219,1,76),(220,220,1,149),(221,221,1,186),(222,222,1,152),(223,223,1,40),(224,224,1,44),(225,225,1,31),(226,226,1,61),(227,227,1,0),(228,228,1,171),(229,229,1,130),(230,230,1,175),(231,231,1,84),(232,232,1,91),(233,233,1,188),(234,234,1,43),(235,235,1,54),(236,236,1,65),(237,237,1,161),(238,238,1,155),(239,239,1,160),(240,240,1,185),(241,241,1,58),(242,242,1,115),(243,243,1,74),(244,244,1,191),(245,245,1,9),(246,246,1,20),(247,247,1,169),(248,248,1,44),(249,249,1,12),(250,250,1,37),(251,251,1,36),(252,252,1,104),(253,253,1,89),(254,254,1,20),(255,255,1,153),(256,256,1,56),(257,257,1,40),(258,258,1,28),(259,259,1,27),(260,260,1,179),(261,261,1,49),(262,262,1,138),(263,263,1,182),(264,264,1,159),(265,265,1,9),(266,266,1,100),(267,267,1,111),(268,268,1,50),(269,269,1,66),(270,1,2,177),(271,2,2,117),(272,3,2,90),(273,4,2,103),(274,5,2,29),(275,6,2,65),(276,7,2,119),(277,8,2,167),(278,9,2,173),(279,10,2,68),(280,11,2,58),(281,12,2,96),(282,13,2,92),(283,14,2,15),(284,15,2,160),(285,16,2,133),(286,17,2,150),(287,18,2,185),(288,19,2,61),(289,20,2,5),(290,21,2,103),(291,22,2,144),(292,23,2,92),(293,24,2,44),(294,25,2,83),(295,26,2,172),(296,27,2,183),(297,28,2,132),(298,29,2,193),(299,30,2,37),(300,31,2,137),(301,32,2,48),(302,33,2,136),(303,34,2,171),(304,35,2,87),(305,36,2,48),(306,37,2,28),(307,38,2,0),(308,39,2,18),(309,40,2,192),(310,41,2,74),(311,42,2,189),(312,43,2,171),(313,44,2,48),(314,45,2,132),(315,46,2,21),(316,47,2,15),(317,48,2,59),(318,49,2,40),(319,50,2,114),(320,51,2,115),(321,52,2,70),(322,53,2,53),(323,54,2,83),(324,55,2,157),(325,56,2,48),(326,57,2,23),(327,58,2,199),(328,59,2,142),(329,60,2,20),(330,61,2,144),(331,62,2,60),(332,63,2,184),(333,64,2,168),(334,65,2,4),(335,66,2,109),(336,67,2,172),(337,68,2,181),(338,69,2,89),(339,70,2,127),(340,71,2,187),(341,72,2,69),(342,73,2,180),(343,74,2,28),(344,75,2,172),(345,76,2,90),(346,77,2,24),(347,78,2,140),(348,79,2,11),(349,80,2,72),(350,81,2,64),(351,82,2,187),(352,83,2,99),(353,84,2,174),(354,85,2,197),(355,86,2,196),(356,87,2,119),(357,88,2,63),(358,89,2,196),(359,90,2,165),(360,91,2,152),(361,92,2,159),(362,93,2,157),(363,94,2,61),(364,95,2,185),(365,96,2,28),(366,97,2,77),(367,98,2,98),(368,99,2,57),(369,100,2,2),(370,101,2,140),(371,102,2,102),(372,103,2,94),(373,104,2,68),(374,105,2,147),(375,106,2,112),(376,107,2,122),(377,108,2,145),(378,109,2,75),(379,110,2,180),(380,111,2,58),(381,112,2,108),(382,113,2,162),(383,114,2,136),(384,115,2,198),(385,116,2,32),(386,117,2,86),(387,118,2,121),(388,119,2,9),(389,120,2,166),(390,121,2,117),(391,122,2,107),(392,123,2,135),(393,124,2,195),(394,125,2,9),(395,126,2,94),(396,127,2,16),(397,128,2,172),(398,129,2,199),(399,130,2,41),(400,131,2,123),(401,132,2,68),(402,133,2,163),(403,134,2,44),(404,135,2,15),(405,136,2,176),(406,137,2,137),(407,138,2,175),(408,139,2,182),(409,140,2,146),(410,141,2,8),(411,142,2,22),(412,143,2,160),(413,144,2,154),(414,145,2,87),(415,146,2,64),(416,147,2,144),(417,148,2,73),(418,149,2,129),(419,150,2,192),(420,151,2,102),(421,152,2,38),(422,153,2,40),(423,154,2,50),(424,155,2,171),(425,156,2,1),(426,157,2,62),(427,158,2,13),(428,159,2,158),(429,160,2,28),(430,161,2,62),(431,162,2,120),(432,163,2,86),(433,164,2,10),(434,165,2,38),(435,166,2,113),(436,167,2,103),(437,168,2,71),(438,169,2,22),(439,170,2,169),(440,171,2,2),(441,172,2,44),(442,173,2,17),(443,174,2,106),(444,175,2,185),(445,176,2,185),(446,177,2,78),(447,178,2,160),(448,179,2,80),(449,180,2,57),(450,181,2,70),(451,182,2,120),(452,183,2,36),(453,184,2,159),(454,185,2,113),(455,186,2,127),(456,187,2,90),(457,188,2,197),(458,189,2,191),(459,190,2,129),(460,191,2,56),(461,192,2,94),(462,193,2,167),(463,194,2,98),(464,195,2,36),(465,196,2,111),(466,197,2,36),(467,198,2,48),(468,199,2,61),(469,200,2,139),(470,201,2,87),(471,202,2,77),(472,203,2,33),(473,204,2,88),(474,205,2,148),(475,206,2,91),(476,207,2,168),(477,208,2,32),(478,209,2,179),(479,210,2,60),(480,211,2,67),(481,212,2,44),(482,213,2,100),(483,214,2,90),(484,215,2,70),(485,216,2,5),(486,217,2,143),(487,218,2,192),(488,219,2,35),(489,220,2,28),(490,221,2,169),(491,222,2,142),(492,223,2,18),(493,224,2,100),(494,225,2,70),(495,226,2,139),(496,227,2,121),(497,228,2,25),(498,229,2,197),(499,230,2,107),(500,231,2,161),(501,232,2,154),(502,233,2,13),(503,234,2,181),(504,235,2,119),(505,236,2,40),(506,237,2,192),(507,238,2,124),(508,239,2,136),(509,240,2,153),(510,241,2,121),(511,242,2,58),(512,243,2,58),(513,244,2,101),(514,245,2,132),(515,246,2,148),(516,247,2,91),(517,248,2,63),(518,249,2,121),(519,250,2,163),(520,251,2,89),(521,252,2,183),(522,253,2,42),(523,254,2,137),(524,255,2,136),(525,256,2,69),(526,257,2,133),(527,258,2,185),(528,259,2,72),(529,260,2,172),(530,261,2,116),(531,262,2,92),(532,263,2,2),(533,264,2,161),(534,265,2,74),(535,266,2,132),(536,267,2,159),(537,268,2,139),(538,269,2,182),(539,1,3,88),(540,2,3,79),(541,3,3,194),(542,4,3,3),(543,5,3,105),(544,6,3,28),(545,7,3,34),(546,8,3,77),(547,9,3,103),(548,10,3,138),(549,11,3,138),(550,12,3,165),(551,13,3,59),(552,14,3,117),(553,15,3,33),(554,16,3,154),(555,17,3,55),(556,18,3,33),(557,19,3,37),(558,20,3,127),(559,21,3,53),(560,22,3,142),(561,23,3,65),(562,24,3,17),(563,25,3,14),(564,26,3,24),(565,27,3,74),(566,28,3,87),(567,29,3,47),(568,30,3,59),(569,31,3,39),(570,32,3,6),(571,33,3,40),(572,34,3,77),(573,35,3,39),(574,36,3,114),(575,37,3,197),(576,38,3,139),(577,39,3,119),(578,40,3,26),(579,41,3,41),(580,42,3,47),(581,43,3,103),(582,44,3,89),(583,45,3,153),(584,46,3,166),(585,47,3,15),(586,48,3,197),(587,49,3,49),(588,50,3,51),(589,51,3,163),(590,52,3,177),(591,53,3,161),(592,54,3,193),(593,55,3,167),(594,56,3,195),(595,57,3,85),(596,58,3,199),(597,59,3,61),(598,60,3,197),(599,61,3,50),(600,62,3,156),(601,63,3,54),(602,64,3,122),(603,65,3,59),(604,66,3,11),(605,67,3,140),(606,68,3,55),(607,69,3,44),(608,70,3,178),(609,71,3,33),(610,72,3,156),(611,73,3,10),(612,74,3,31),(613,75,3,77),(614,76,3,39),(615,77,3,113),(616,78,3,47),(617,79,3,180),(618,80,3,186),(619,81,3,147),(620,82,3,21),(621,83,3,113),(622,84,3,71),(623,85,3,68),(624,86,3,168),(625,87,3,48),(626,88,3,120),(627,89,3,35),(628,90,3,49),(629,91,3,58),(630,92,3,43),(631,93,3,171),(632,94,3,109),(633,95,3,142),(634,96,3,167),(635,97,3,94),(636,98,3,17),(637,99,3,137),(638,100,3,16),(639,101,3,83),(640,102,3,46),(641,103,3,148),(642,104,3,19),(643,105,3,137),(644,106,3,80),(645,107,3,182),(646,108,3,181),(647,109,3,198),(648,110,3,164),(649,111,3,25),(650,112,3,161),(651,113,3,100),(652,114,3,57),(653,115,3,111),(654,116,3,188),(655,117,3,56),(656,118,3,116),(657,119,3,191),(658,120,3,137),(659,121,3,24),(660,122,3,132),(661,123,3,140),(662,124,3,94),(663,125,3,91),(664,126,3,11),(665,127,3,140),(666,128,3,42),(667,129,3,34),(668,130,3,126),(669,131,3,177),(670,132,3,161),(671,133,3,130),(672,134,3,129),(673,135,3,176),(674,136,3,55),(675,137,3,15),(676,138,3,114),(677,139,3,123),(678,140,3,44),(679,141,3,145),(680,142,3,41),(681,143,3,51),(682,144,3,124),(683,145,3,104),(684,146,3,24),(685,147,3,83),(686,148,3,173),(687,149,3,170),(688,150,3,167),(689,151,3,84),(690,152,3,97),(691,153,3,22),(692,154,3,9),(693,155,3,109),(694,156,3,132),(695,157,3,71),(696,158,3,186),(697,159,3,170),(698,160,3,137),(699,161,3,136),(700,162,3,196),(701,163,3,143),(702,164,3,175),(703,165,3,4),(704,166,3,186),(705,167,3,93),(706,168,3,137),(707,169,3,129),(708,170,3,17),(709,171,3,89),(710,172,3,4),(711,173,3,186),(712,174,3,52),(713,175,3,137),(714,176,3,5),(715,177,3,83),(716,178,3,92),(717,179,3,172),(718,180,3,92),(719,181,3,124),(720,182,3,28),(721,183,3,12),(722,184,3,41),(723,185,3,134),(724,186,3,190),(725,187,3,143),(726,188,3,122),(727,189,3,149),(728,190,3,158),(729,191,3,95),(730,192,3,182),(731,193,3,3),(732,194,3,41),(733,195,3,70),(734,196,3,136),(735,197,3,95),(736,198,3,62),(737,199,3,127),(738,200,3,196),(739,201,3,20),(740,202,3,79),(741,203,3,70),(742,204,3,146),(743,205,3,28),(744,206,3,95),(745,207,3,86),(746,208,3,142),(747,209,3,111),(748,210,3,179),(749,211,3,162),(750,212,3,88),(751,213,3,168),(752,214,3,36),(753,215,3,53),(754,216,3,26),(755,217,3,15),(756,218,3,117),(757,219,3,48),(758,220,3,29),(759,221,3,142),(760,222,3,43),(761,223,3,109),(762,224,3,6),(763,225,3,78),(764,226,3,89),(765,227,3,184),(766,228,3,163),(767,229,3,25),(768,230,3,140),(769,231,3,58),(770,232,3,138),(771,233,3,55),(772,234,3,111),(773,235,3,147),(774,236,3,127),(775,237,3,21),(776,238,3,81),(777,239,3,185),(778,240,3,127),(779,241,3,134),(780,242,3,126),(781,243,3,68),(782,244,3,134),(783,245,3,180),(784,246,3,172),(785,247,3,12),(786,248,3,15),(787,249,3,77),(788,250,3,1),(789,251,3,36),(790,252,3,6),(791,253,3,23),(792,254,3,69),(793,255,3,4),(794,256,3,36),(795,257,3,65),(796,258,3,139),(797,259,3,139),(798,260,3,63),(799,261,3,130),(800,262,3,155),(801,263,3,109),(802,264,3,133),(803,265,3,185),(804,266,3,2),(805,267,3,157),(806,268,3,1),(807,269,3,118),(808,1,4,14),(809,2,4,199),(810,3,4,69),(811,4,4,152),(812,5,4,158),(813,6,4,151),(814,7,4,23),(815,8,4,166),(816,9,4,191),(817,10,4,60),(818,11,4,110),(819,12,4,86),(820,13,4,191),(821,14,4,12),(822,15,4,78),(823,16,4,109),(824,17,4,173),(825,18,4,72),(826,19,4,102),(827,20,4,161),(828,21,4,26),(829,22,4,49),(830,23,4,193),(831,24,4,184),(832,25,4,117),(833,26,4,123),(834,27,4,129),(835,28,4,166),(836,29,4,142),(837,30,4,155),(838,31,4,198),(839,32,4,37),(840,33,4,149),(841,34,4,72),(842,35,4,86),(843,36,4,46),(844,37,4,84),(845,38,4,115),(846,39,4,106),(847,40,4,72),(848,41,4,1),(849,42,4,101),(850,43,4,145),(851,44,4,30),(852,45,4,73),(853,46,4,19),(854,47,4,22),(855,48,4,79),(856,49,4,117),(857,50,4,71),(858,51,4,99),(859,52,4,55),(860,53,4,168),(861,54,4,99),(862,55,4,104),(863,56,4,48),(864,57,4,22),(865,58,4,100),(866,59,4,118),(867,60,4,62),(868,61,4,115),(869,62,4,12),(870,63,4,170),(871,64,4,142),(872,65,4,118),(873,66,4,175),(874,67,4,13),(875,68,4,184),(876,69,4,9),(877,70,4,98),(878,71,4,12),(879,72,4,186),(880,73,4,148),(881,74,4,35),(882,75,4,41),(883,76,4,15),(884,77,4,152),(885,78,4,41),(886,79,4,167),(887,80,4,63),(888,81,4,126),(889,82,4,71),(890,83,4,79),(891,84,4,3),(892,85,4,2),(893,86,4,95),(894,87,4,196),(895,88,4,138),(896,89,4,113),(897,90,4,0),(898,91,4,142),(899,92,4,78),(900,93,4,27),(901,94,4,33),(902,95,4,65),(903,96,4,120),(904,97,4,98),(905,98,4,164),(906,99,4,37),(907,100,4,57),(908,101,4,29),(909,102,4,165),(910,103,4,79),(911,104,4,68),(912,105,4,43),(913,106,4,77),(914,107,4,172),(915,108,4,197),(916,109,4,26),(917,110,4,167),(918,111,4,73),(919,112,4,55),(920,113,4,3),(921,114,4,101),(922,115,4,41),(923,116,4,94),(924,117,4,196),(925,118,4,115),(926,119,4,91),(927,120,4,176),(928,121,4,181),(929,122,4,186),(930,123,4,16),(931,124,4,32),(932,125,4,125),(933,126,4,170),(934,127,4,5),(935,128,4,188),(936,129,4,130),(937,130,4,9),(938,131,4,41),(939,132,4,45),(940,133,4,35),(941,134,4,61),(942,135,4,15),(943,136,4,131),(944,137,4,171),(945,138,4,7),(946,139,4,62),(947,140,4,11),(948,141,4,66),(949,142,4,168),(950,143,4,139),(951,144,4,91),(952,145,4,151),(953,146,4,169),(954,147,4,74),(955,148,4,196),(956,149,4,180),(957,150,4,78),(958,151,4,49),(959,152,4,146),(960,153,4,51),(961,154,4,71),(962,155,4,53),(963,156,4,189),(964,157,4,154),(965,158,4,30),(966,159,4,110),(967,160,4,77),(968,161,4,65),(969,162,4,81),(970,163,4,118),(971,164,4,187),(972,165,4,147),(973,166,4,119),(974,167,4,91),(975,168,4,178),(976,169,4,165),(977,170,4,71),(978,171,4,49),(979,172,4,32),(980,173,4,169),(981,174,4,78),(982,175,4,93),(983,176,4,51),(984,177,4,135),(985,178,4,102),(986,179,4,137),(987,180,4,198),(988,181,4,54),(989,182,4,83),(990,183,4,133),(991,184,4,59),(992,185,4,20),(993,186,4,160),(994,187,4,120),(995,188,4,127),(996,189,4,149),(997,190,4,174),(998,191,4,128),(999,192,4,4),(1000,193,4,179),(1001,194,4,80),(1002,195,4,72),(1003,196,4,73),(1004,197,4,109),(1005,198,4,13),(1006,199,4,165),(1007,200,4,168),(1008,201,4,23),(1009,202,4,18),(1010,203,4,23),(1011,204,4,47),(1012,205,4,88),(1013,206,4,147),(1014,207,4,141),(1015,208,4,197),(1016,209,4,90),(1017,210,4,51),(1018,211,4,67),(1019,212,4,76),(1020,213,4,161),(1021,214,4,183),(1022,215,4,18),(1023,216,4,19),(1024,217,4,14),(1025,218,4,186),(1026,219,4,129),(1027,220,4,45),(1028,221,4,43),(1029,222,4,64),(1030,223,4,117),(1031,224,4,23),(1032,225,4,146),(1033,226,4,11),(1034,227,4,195),(1035,228,4,123),(1036,229,4,16),(1037,230,4,38),(1038,231,4,48),(1039,232,4,70),(1040,233,4,73),(1041,234,4,44),(1042,235,4,152),(1043,236,4,3),(1044,237,4,30),(1045,238,4,141),(1046,239,4,149),(1047,240,4,178),(1048,241,4,169),(1049,242,4,43),(1050,243,4,39),(1051,244,4,93),(1052,245,4,143),(1053,246,4,131),(1054,247,4,116),(1055,248,4,60),(1056,249,4,5),(1057,250,4,112),(1058,251,4,196),(1059,252,4,81),(1060,253,4,79),(1061,254,4,122),(1062,255,4,129),(1063,256,4,179),(1064,257,4,65),(1065,258,4,133),(1066,259,4,176),(1067,260,4,174),(1068,261,4,76),(1069,262,4,16),(1070,263,4,110),(1071,264,4,112),(1072,265,4,104),(1073,266,4,153),(1074,267,4,7),(1075,268,4,29),(1076,269,4,151),(1077,1,5,164),(1078,2,5,124),(1079,3,5,95),(1080,4,5,74),(1081,5,5,7),(1082,6,5,192),(1083,7,5,103),(1084,8,5,193),(1085,9,5,33),(1086,10,5,107),(1087,11,5,158),(1088,12,5,30),(1089,13,5,59),(1090,14,5,42),(1091,15,5,15),(1092,16,5,108),(1093,17,5,145),(1094,18,5,91),(1095,19,5,84),(1096,20,5,76),(1097,21,5,124),(1098,22,5,190),(1099,23,5,92),(1100,24,5,98),(1101,25,5,41),(1102,26,5,136),(1103,27,5,45),(1104,28,5,99),(1105,29,5,38),(1106,30,5,7),(1107,31,5,18),(1108,32,5,156),(1109,33,5,112),(1110,34,5,77),(1111,35,5,21),(1112,36,5,198),(1113,37,5,35),(1114,38,5,26),(1115,39,5,3),(1116,40,5,122),(1117,41,5,176),(1118,42,5,102),(1119,43,5,170),(1120,44,5,176),(1121,45,5,138),(1122,46,5,100),(1123,47,5,77),(1124,48,5,108),(1125,49,5,84),(1126,50,5,188),(1127,51,5,110),(1128,52,5,157),(1129,53,5,70),(1130,54,5,186),(1131,55,5,196),(1132,56,5,182),(1133,57,5,7),(1134,58,5,61),(1135,59,5,136),(1136,60,5,168),(1137,61,5,4),(1138,62,5,32),(1139,63,5,130),(1140,64,5,130),(1141,65,5,24),(1142,66,5,7),(1143,67,5,2),(1144,68,5,69),(1145,69,5,164),(1146,70,5,17),(1147,71,5,168),(1148,72,5,63),(1149,73,5,40),(1150,74,5,125),(1151,75,5,127),(1152,76,5,2),(1153,77,5,63),(1154,78,5,145),(1155,79,5,40),(1156,80,5,32),(1157,81,5,37),(1158,82,5,51),(1159,83,5,124),(1160,84,5,109),(1161,85,5,134),(1162,86,5,188),(1163,87,5,146),(1164,88,5,77),(1165,89,5,164),(1166,90,5,45),(1167,91,5,147),(1168,92,5,7),(1169,93,5,40),(1170,94,5,138),(1171,95,5,34),(1172,96,5,45),(1173,97,5,131),(1174,98,5,120),(1175,99,5,93),(1176,100,5,198),(1177,101,5,55),(1178,102,5,82),(1179,103,5,117),(1180,104,5,95),(1181,105,5,139),(1182,106,5,26),(1183,107,5,37),(1184,108,5,14),(1185,109,5,75),(1186,110,5,140),(1187,111,5,182),(1188,112,5,155),(1189,113,5,198),(1190,114,5,52),(1191,115,5,122),(1192,116,5,102),(1193,117,5,106),(1194,118,5,26),(1195,119,5,121),(1196,120,5,149),(1197,121,5,165),(1198,122,5,107),(1199,123,5,111),(1200,124,5,25),(1201,125,5,26),(1202,126,5,106),(1203,127,5,48),(1204,128,5,70),(1205,129,5,155),(1206,130,5,143),(1207,131,5,142),(1208,132,5,76),(1209,133,5,179),(1210,134,5,141),(1211,135,5,60),(1212,136,5,87),(1213,137,5,60),(1214,138,5,104),(1215,139,5,150),(1216,140,5,195),(1217,141,5,46),(1218,142,5,49),(1219,143,5,187),(1220,144,5,193),(1221,145,5,177),(1222,146,5,80),(1223,147,5,183),(1224,148,5,79),(1225,149,5,54),(1226,150,5,3),(1227,151,5,182),(1228,152,5,83),(1229,153,5,55),(1230,154,5,90),(1231,155,5,56),(1232,156,5,148),(1233,157,5,64),(1234,158,5,161),(1235,159,5,124),(1236,160,5,113),(1237,161,5,67),(1238,162,5,183),(1239,163,5,27),(1240,164,5,199),(1241,165,5,141),(1242,166,5,88),(1243,167,5,144),(1244,168,5,103),(1245,169,5,135),(1246,170,5,79),(1247,171,5,175),(1248,172,5,89),(1249,173,5,125),(1250,174,5,66),(1251,175,5,158),(1252,176,5,174),(1253,177,5,89),(1254,178,5,181),(1255,179,5,72),(1256,180,5,175),(1257,181,5,52),(1258,182,5,67),(1259,183,5,165),(1260,184,5,144),(1261,185,5,57),(1262,186,5,162),(1263,187,5,93),(1264,188,5,4),(1265,189,5,111),(1266,190,5,23),(1267,191,5,107),(1268,192,5,50),(1269,193,5,154),(1270,194,5,53),(1271,195,5,44),(1272,196,5,111),(1273,197,5,173),(1274,198,5,50),(1275,199,5,16),(1276,200,5,24),(1277,201,5,171),(1278,202,5,133),(1279,203,5,116),(1280,204,5,83),(1281,205,5,164),(1282,206,5,58),(1283,207,5,120),(1284,208,5,64),(1285,209,5,117),(1286,210,5,65),(1287,211,5,36),(1288,212,5,178),(1289,213,5,122),(1290,214,5,140),(1291,215,5,76),(1292,216,5,48),(1293,217,5,174),(1294,218,5,75),(1295,219,5,28),(1296,220,5,1),(1297,221,5,44),(1298,222,5,112),(1299,223,5,146),(1300,224,5,29),(1301,225,5,68),(1302,226,5,4),(1303,227,5,181),(1304,228,5,25),(1305,229,5,136),(1306,230,5,49),(1307,231,5,89),(1308,232,5,42),(1309,233,5,112),(1310,234,5,42),(1311,235,5,161),(1312,236,5,62),(1313,237,5,148),(1314,238,5,7),(1315,239,5,22),(1316,240,5,104),(1317,241,5,33),(1318,242,5,9),(1319,243,5,13),(1320,244,5,60),(1321,245,5,168),(1322,246,5,199),(1323,247,5,9),(1324,248,5,159),(1325,249,5,186),(1326,250,5,107),(1327,251,5,118),(1328,252,5,127),(1329,253,5,38),(1330,254,5,161),(1331,255,5,163),(1332,256,5,42),(1333,257,5,174),(1334,258,5,91),(1335,259,5,62),(1336,260,5,88),(1337,261,5,9),(1338,262,5,131),(1339,263,5,12),(1340,264,5,55),(1341,265,5,77),(1342,266,5,5),(1343,267,5,20),(1344,268,5,111),(1345,269,5,44),(1346,1,6,96),(1347,2,6,12),(1348,3,6,90),(1349,4,6,56),(1350,5,6,63),(1351,6,6,77),(1352,7,6,169),(1353,8,6,120),(1354,9,6,17),(1355,10,6,190),(1356,11,6,194),(1357,12,6,37),(1358,13,6,16),(1359,14,6,66),(1360,15,6,178),(1361,16,6,126),(1362,17,6,60),(1363,18,6,9),(1364,19,6,195),(1365,20,6,137),(1366,21,6,110),(1367,22,6,26),(1368,23,6,138),(1369,24,6,120),(1370,25,6,51),(1371,26,6,94),(1372,27,6,71),(1373,28,6,149),(1374,29,6,97),(1375,30,6,161),(1376,31,6,78),(1377,32,6,170),(1378,33,6,126),(1379,34,6,139),(1380,35,6,189),(1381,36,6,106),(1382,37,6,101),(1383,38,6,104),(1384,39,6,147),(1385,40,6,49),(1386,41,6,169),(1387,42,6,0),(1388,43,6,170),(1389,44,6,102),(1390,45,6,197),(1391,46,6,82),(1392,47,6,3),(1393,48,6,146),(1394,49,6,60),(1395,50,6,63),(1396,51,6,124),(1397,52,6,184),(1398,53,6,78),(1399,54,6,27),(1400,55,6,72),(1401,56,6,54),(1402,57,6,57),(1403,58,6,31),(1404,59,6,68),(1405,60,6,131),(1406,61,6,29),(1407,62,6,137),(1408,63,6,28),(1409,64,6,11),(1410,65,6,123),(1411,66,6,191),(1412,67,6,50),(1413,68,6,3),(1414,69,6,56),(1415,70,6,39),(1416,71,6,73),(1417,72,6,3),(1418,73,6,12),(1419,74,6,57),(1420,75,6,93),(1421,76,6,51),(1422,77,6,50),(1423,78,6,49),(1424,79,6,37),(1425,80,6,83),(1426,81,6,60),(1427,82,6,153),(1428,83,6,40),(1429,84,6,54),(1430,85,6,80),(1431,86,6,133),(1432,87,6,33),(1433,88,6,168),(1434,89,6,179),(1435,90,6,97),(1436,91,6,177),(1437,92,6,58),(1438,93,6,71),(1439,94,6,44),(1440,95,6,167),(1441,96,6,134),(1442,97,6,28),(1443,98,6,169),(1444,99,6,168),(1445,100,6,138),(1446,101,6,178),(1447,102,6,48),(1448,103,6,134),(1449,104,6,49),(1450,105,6,81),(1451,106,6,121),(1452,107,6,16),(1453,108,6,133),(1454,109,6,103),(1455,110,6,82),(1456,111,6,154),(1457,112,6,55),(1458,113,6,6),(1459,114,6,26),(1460,115,6,132),(1461,116,6,191),(1462,117,6,20),(1463,118,6,101),(1464,119,6,160),(1465,120,6,71),(1466,121,6,87),(1467,122,6,5),(1468,123,6,4),(1469,124,6,74),(1470,125,6,170),(1471,126,6,46),(1472,127,6,165),(1473,128,6,190),(1474,129,6,119),(1475,130,6,68),(1476,131,6,57),(1477,132,6,58),(1478,133,6,92),(1479,134,6,52),(1480,135,6,5),(1481,136,6,120),(1482,137,6,176),(1483,138,6,154),(1484,139,6,77),(1485,140,6,17),(1486,141,6,177),(1487,142,6,66),(1488,143,6,52),(1489,144,6,15),(1490,145,6,100),(1491,146,6,44),(1492,147,6,47),(1493,148,6,98),(1494,149,6,63),(1495,150,6,15),(1496,151,6,153),(1497,152,6,198),(1498,153,6,61),(1499,154,6,195),(1500,155,6,3),(1501,156,6,168),(1502,157,6,184),(1503,158,6,33),(1504,159,6,182),(1505,160,6,162),(1506,161,6,147),(1507,162,6,188),(1508,163,6,14),(1509,164,6,39),(1510,165,6,0),(1511,166,6,116),(1512,167,6,133),(1513,168,6,73),(1514,169,6,30),(1515,170,6,97),(1516,171,6,111),(1517,172,6,27),(1518,173,6,32),(1519,174,6,47),(1520,175,6,16),(1521,176,6,169),(1522,177,6,153),(1523,178,6,173),(1524,179,6,86),(1525,180,6,25),(1526,181,6,33),(1527,182,6,60),(1528,183,6,184),(1529,184,6,51),(1530,185,6,21),(1531,186,6,189),(1532,187,6,105),(1533,188,6,100),(1534,189,6,38),(1535,190,6,93),(1536,191,6,125),(1537,192,6,36),(1538,193,6,99),(1539,194,6,133),(1540,195,6,121),(1541,196,6,128),(1542,197,6,192),(1543,198,6,170),(1544,199,6,43),(1545,200,6,28),(1546,201,6,74),(1547,202,6,8),(1548,203,6,185),(1549,204,6,11),(1550,205,6,151),(1551,206,6,13),(1552,207,6,90),(1553,208,6,28),(1554,209,6,117),(1555,210,6,74),(1556,211,6,126),(1557,212,6,11),(1558,213,6,161),(1559,214,6,56),(1560,215,6,93),(1561,216,6,125),(1562,217,6,16),(1563,218,6,66),(1564,219,6,147),(1565,220,6,45),(1566,221,6,5),(1567,222,6,43),(1568,223,6,199),(1569,224,6,180),(1570,225,6,49),(1571,226,6,141),(1572,227,6,176),(1573,228,6,130),(1574,229,6,33),(1575,230,6,63),(1576,231,6,101),(1577,232,6,17),(1578,233,6,188),(1579,234,6,2),(1580,235,6,192),(1581,236,6,95),(1582,237,6,122),(1583,238,6,43),(1584,239,6,94),(1585,240,6,183),(1586,241,6,121),(1587,242,6,186),(1588,243,6,135),(1589,244,6,127),(1590,245,6,77),(1591,246,6,109),(1592,247,6,158),(1593,248,6,161),(1594,249,6,84),(1595,250,6,40),(1596,251,6,173),(1597,252,6,107),(1598,253,6,42),(1599,254,6,163),(1600,255,6,145),(1601,256,6,7),(1602,257,6,82),(1603,258,6,84),(1604,259,6,81),(1605,260,6,106),(1606,261,6,124),(1607,262,6,182),(1608,263,6,121),(1609,264,6,176),(1610,265,6,33),(1611,266,6,110),(1612,267,6,19),(1613,268,6,191),(1614,269,6,79),(1617,272,1,NULL),(1618,272,2,NULL),(1619,272,3,NULL),(1620,272,4,NULL),(1621,272,5,NULL),(1622,272,6,NULL),(1623,2,7,0),(1624,127,7,0),(1625,204,7,0),(1626,230,7,0),(1627,247,7,0),(1628,248,7,0),(1629,267,7,0),(1630,269,7,0),(1631,60,7,0),(1632,75,7,0),(1633,81,7,0),(1634,87,7,0),(1635,132,7,0),(1636,172,7,0),(1637,179,7,0),(1638,220,7,0),(1639,254,7,0),(1640,264,7,0),(1641,13,7,0),(1642,25,7,0),(1643,38,7,0),(1644,64,7,0),(1645,66,7,0),(1646,78,7,0),(1647,106,7,0),(1648,147,7,0),(1649,157,7,0),(1650,168,7,0),(1651,182,7,0),(1652,187,7,0),(1653,201,7,0),(1654,212,7,0),(1655,219,7,0),(1656,221,7,0),(1657,243,7,0),(1658,245,7,0),(1659,246,7,0),(1660,249,7,0),(1661,263,7,0),(1662,18,7,0),(1663,23,7,0),(1664,33,7,0),(1665,40,7,0),(1666,41,7,0),(1667,57,7,0),(1668,85,7,0),(1669,97,7,0),(1670,140,7,0),(1671,158,7,0),(1672,177,7,0),(1673,255,7,0),(1674,262,7,0),(1675,1,7,0),(1676,11,7,0),(1677,74,7,0),(1678,95,7,0),(1679,148,7,0),(1680,173,7,0),(1681,197,7,0),(1682,272,7,0),(1683,30,7,0),(1684,32,7,0),(1685,65,7,0),(1686,107,7,0),(1687,108,7,0),(1688,124,7,0),(1689,131,7,0),(1690,155,7,0),(1691,161,7,0),(1692,166,7,0),(1693,178,7,0),(1694,202,7,0),(1695,213,7,0),(1696,233,7,0),(1697,237,7,0),(1698,47,7,0),(1699,56,7,0),(1700,104,7,0),(1701,111,7,0),(1702,126,7,0),(1703,143,7,0),(1704,154,7,0),(1705,199,7,0),(1706,206,7,0),(1707,240,7,0),(1708,7,7,0),(1709,9,7,0),(1710,50,7,0),(1711,84,7,0),(1712,101,7,0),(1713,142,7,0),(1714,181,7,0),(1715,188,7,0),(1716,224,7,0),(1717,42,7,0),(1718,91,7,0),(1719,94,7,0),(1720,100,7,0),(1721,162,7,0),(1722,167,7,0),(1723,184,7,0),(1724,195,7,0),(1725,218,7,0),(1726,239,7,0),(1727,241,7,0),(1728,258,7,0),(1729,6,7,0),(1730,39,7,0),(1731,48,7,0),(1732,52,7,0),(1733,61,7,0),(1734,83,7,0),(1735,122,7,0),(1736,152,7,0),(1737,223,7,0),(1738,16,7,0),(1739,53,7,0),(1740,59,7,0),(1741,67,7,0),(1742,109,7,0),(1743,118,7,0),(1744,160,7,0),(1745,186,7,0),(1746,191,7,0),(1747,210,7,0),(1748,235,7,0),(1749,257,7,0),(1750,265,7,0),(1751,4,7,0),(1752,5,7,0),(1753,14,7,0),(1754,19,7,0),(1755,35,7,0),(1756,49,7,0),(1757,96,7,0),(1758,112,7,0),(1759,128,7,0),(1760,141,7,0),(1761,150,7,0),(1762,163,7,0),(1763,180,7,0),(1764,209,7,0),(1765,217,7,0),(1766,226,7,0),(1767,12,7,0),(1768,34,7,0),(1769,71,7,0),(1770,110,7,0),(1771,133,7,0),(1772,196,7,0),(1773,208,7,0),(1774,225,7,0),(1775,15,7,0),(1776,22,7,0),(1777,26,7,0),(1778,37,7,0),(1779,46,7,0),(1780,54,7,0),(1781,70,7,0),(1782,76,7,0),(1783,79,7,0),(1784,88,7,0),(1785,92,7,0),(1786,103,7,0),(1787,190,7,0),(1788,200,7,0),(1789,211,7,0),(1790,236,7,0),(1791,244,7,0),(1792,27,7,0),(1793,55,7,0),(1794,82,7,0),(1795,89,7,0),(1796,117,7,0),(1797,123,7,0),(1798,125,7,0),(1799,170,7,0),(1800,192,7,0),(1801,214,7,0),(1802,227,7,0),(1803,259,7,0),(1804,266,7,0),(1805,105,7,0),(1806,169,7,0),(1807,198,7,0),(1808,229,7,0),(1809,253,7,0),(1810,21,7,0),(1811,36,7,0),(1812,62,7,0),(1813,68,7,0),(1814,146,7,0),(1815,149,7,0),(1816,174,7,0),(1817,193,7,0),(1818,234,7,0),(1819,252,7,0),(1820,256,7,0),(1821,102,7,0),(1822,116,7,0),(1823,120,7,0),(1824,136,7,0),(1825,185,7,0),(1826,194,7,0),(1827,222,7,0),(1828,3,7,0),(1829,135,7,0),(1830,137,7,0),(1831,171,7,0),(1832,189,7,0),(1833,238,7,0),(1834,242,7,0),(1835,250,7,0),(1836,20,7,0),(1837,73,7,0),(1838,93,7,0),(1839,130,7,0),(1840,134,7,0),(1841,138,7,0),(1842,176,7,0),(1843,232,7,0),(1844,29,7,0),(1845,44,7,0),(1846,72,7,0),(1847,98,7,0),(1848,115,7,0),(1849,144,7,0),(1850,164,7,0),(1851,207,7,0),(1852,228,7,0),(1853,24,7,0),(1854,28,7,0),(1855,31,7,0),(1856,45,7,0),(1857,58,7,0),(1858,69,7,0),(1859,80,7,0),(1860,90,7,0),(1861,119,7,0),(1862,129,7,0),(1863,139,7,0),(1864,145,7,0),(1865,159,7,0),(1866,261,7,0),(1867,51,7,0),(1868,99,7,0),(1869,113,7,0),(1870,114,7,0),(1871,121,7,0),(1872,156,7,0),(1873,175,7,0),(1874,215,7,0),(1875,216,7,0),(1876,268,7,0),(1877,10,7,0),(1878,17,7,0),(1879,77,7,0),(1880,86,7,0),(1881,183,7,0),(1882,203,7,0),(1883,8,7,0),(1884,43,7,0),(1885,63,7,0),(1886,151,7,0),(1887,153,7,0),(1888,165,7,0),(1889,205,7,0),(1890,231,7,0),(1891,251,7,0),(1892,260,7,0),(2134,273,1,NULL),(2135,273,2,NULL),(2136,273,3,NULL),(2137,273,4,NULL),(2138,273,5,NULL),(2139,273,6,NULL),(2140,273,7,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_cuentas_tercer_nivel`
--

LOCK TABLES `sub_cuentas_tercer_nivel` WRITE;
/*!40000 ALTER TABLE `sub_cuentas_tercer_nivel` DISABLE KEYS */;
INSERT INTO `sub_cuentas_tercer_nivel` VALUES (1,1,'100.1.1','Efectivo disponible','',0,0,0),(2,1,'100.1.2','Fondo de ahorro','efectivo ahorrado',0,0,0),(3,1,'100.1.3','Efectivo en circulacion','efectivo usado para cambios',0,0,0),(4,2,'100.2.1','Bancomer','cuenta bancomer',0,0,0),(5,5,'100.5.1','iva acreditable pagado','iava acreditable efectivamente pagado',0,0,0),(6,6,'100.6.1','iva acreditable por pagar','iva acreditable pendiente de pago',0,0,0),(7,8,'200.2.1','iva trasladado','iva cobrado',0,0,0),(8,9,'200.3.1','iva por trasladar','iva por cobrar',0,0,0),(9,10,'400.1.1','venta de mercanc??as','ingresos obtenidos por la venta de mercancias',0,0,0),(10,11,'401.1.1','Desc y Dev sobre ventas','Devoluciones o descuentos otorgados sobre las ventas',0,0,0),(11,12,'500.1.1','compra de mercancias','',0,0,0),(12,13,'501.1.1','Desc y dev sobre compras','devoluciones o descuentos otorgados sobre las compras de mercancias',0,0,0),(13,14,'600.1.1','sueldos y salarios','',0,0,0),(14,14,'600.1.2','PAGO SERVICIOS POR TERCEROS','',0,0,0),(15,14,'600.1.3','SERVICIO DE AGUA','',0,0,0),(16,14,'600.1.4','ENERGIA ELECTRICA','',0,0,0),(17,14,'600.1.5','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(18,14,'600.1.6','DESPENSAS Y ALIMENTOS','',0,0,0),(19,14,'600.1.7','ASEO Y LIMPIEZA','',0,0,0),(20,14,'600.1.8','MTTO EQUIPO DE TRANSPORTE','',0,0,0),(21,14,'600.1.9','MTTO EQUIPO DE COMPUTO','',0,0,0),(22,14,'600.1.10','MTTO DEL LOCAL','',0,0,0),(23,14,'600.1.11','OTROS GASTOS','',0,0,0),(24,14,'600.1.12','AJUSTE POR GASTOS NO CONOCIDOS','',0,0,0),(25,14,'600.1.13','TELEFONO E INTERNET','',0,0,0),(26,14,'600.1.14','ENVOLTURAS Y EMPAQUES','',0,0,0),(27,15,'600.2.1','GASOLINA MOTO','',0,0,0),(28,15,'600.2.2','TELEFONIA MOVIL','',0,0,0),(29,15,'600.2.3','PUBLICIDAD','',0,0,0),(30,15,'600.2.4','FLETES','',0,0,0),(31,15,'600.2.5','ENVOLTURAS Y EMPAQUES','',0,0,0),(32,16,'600.3.1','COMBUSTIBLES Y LUBRICANTES','',0,0,0),(33,16,'600.3.2','PAPELERIA Y UTILES','',0,0,0),(34,16,'600.3.3','ENERG??A ELECTRICA','',0,0,0),(35,16,'600.3.4','TELEFONIA MOVIL','',0,0,0),(36,16,'600.3.5','TELEFONO E INTERNET','',0,0,0),(37,16,'600.3.6','IMPUESTOS FEDERALES','',0,0,0),(38,16,'600.3.7','RENTA DEL LOCAL','',0,0,0),(39,16,'600.3.8','OTROS GASTOS DE ADMIN','',0,0,0),(40,16,'600.3.9','CUOTAS Y SUSCRIPCIONES','',0,0,0),(41,16,'600.3.10','CUOTAS Y PAGOS IMSS','',0,0,0),(42,18,'700.1.1','PERDIDA CAMBIARIA','',0,0,0),(43,18,'700.1.2','INTERESES A CARGO','',0,0,0),(44,18,'700.1.3','COMISIONES BANCARIAS','',0,0,0),(45,4,'100.4.1','MOSTRADOR','',0,0,0),(46,4,'100.4.2','LOURDES LOPEZ GUILLEN','',0,0,0),(47,4,'100.4.3','MARIA ERNESTINA AGUSTIN PEREZ','Se actualiza la descripci??n de este cliente',0,0,0),(48,4,'100.4.4','MARIA CONCEPCION CHULIN GORDIL','',0,0,0),(49,4,'100.4.5','LUCIA ELIZABETH LOPEZ GONZALEZ','nuevamente se actualiza la descripci??n de este cliente',0,0,0),(50,4,'100.4.6','ANA YANSI CRUZ GARCIA','',0,0,0),(51,4,'100.4.7','MARIA ENCARNACION SARMIENTO SA','',0,0,0),(52,4,'100.4.8','MARIA BEININA GOMEZ ALVAREZ','',0,0,0),(53,4,'100.4.9','LUZ MARIA MORALES TORRES','',0,0,0),(54,4,'100.4.10','VERONICA LOPEZ CRUZ','',0,0,0),(55,4,'100.4.11','GUADALUPE NURIULU GORDILLO','',0,0,0),(56,4,'100.4.12','WALTER DILTIEV SOTO MORALES','',0,0,0),(57,4,'100.4.13','\"MUNICIPIO DE VENUSTIANO CARRA',' CHIAPAS\"',0,0,0),(58,4,'100.4.14','BEATRIZ ADRIANA PENAGOS GONZAL','',0,0,0),(59,4,'100.4.15','YARENI HERNANDEZ GARCIA','',0,0,0),(60,4,'100.4.16','METALLICA','',0,0,0),(61,4,'100.4.17','FABIOLA INDILI CUNDAPI','',0,0,0),(62,4,'100.4.18','ELIZA AGUILAR','',0,0,0),(63,4,'100.4.19','PAOLA LIZETH GALLEGOS DEL CARP','',0,0,0),(64,4,'100.4.20','DANIA MARGARITA GOMEZ HERNANDE','',0,0,0),(65,7,'200.1.1','Colomer sa de cv','distribuidora de productos de belleza al por mayor',0,0,0),(66,7,'200.1.2','Revlon SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(67,7,'200.1.3','Bidiip','distribuidora de productos de belleza al por mayor',0,0,0),(68,7,'200.1.4','Anven SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(69,7,'200.1.5','Nefertiti SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(70,7,'200.1.6','Ricardo Rodrigo','distribuidora de productos de belleza al por mayor',0,0,0),(71,7,'200.1.7','Raul Trinidad','distribuidora de productos de belleza al por mayor',0,0,0),(72,7,'200.1.8','Henry Rodriguez','distribuidora de productos de belleza al por mayor',0,0,0),(73,7,'200.1.9','Cosmetica capilar','distribuidora de productos de belleza al por mayor',0,0,0),(74,7,'200.1.10','Cosmetica insurgentes','distribuidora de productos de belleza al por mayor',0,0,0),(75,7,'200.1.11','Belleza cien','distribuidora de productos de belleza al por mayor',0,0,0),(76,7,'200.1.12','Tu Beseza SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(77,7,'200.1.13','Cosmeticos de la fuente','distribuidora de productos de belleza al por mayor',0,0,0),(78,7,'200.1.14','Cosmetica Guadalajara','distribuidora de productos de belleza al por mayor',0,0,0),(79,7,'200.1.15','Regio Belleza','distribuidora de productos de belleza al por mayor',0,0,0),(80,7,'200.1.16','Somo Tapatio SA de CV','distribuidora de productos para oficina',0,0,0),(81,7,'200.1.17','Hidra Color SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(82,7,'200.1.18','Color Shot SAS de CV','distribuidora de productos de belleza al por mayor',0,0,0),(83,7,'200.1.19','Color Tech','distribuidora de productos de belleza al por mayor',0,0,0),(84,7,'200.1.20','Nutrapel SA de CV','distribuidora de productos de belleza al por mayor',0,0,0),(85,7,'200.1.21','Nattura Labs SA de Cv','distribuidora de productos de belleza al por mayor',0,0,0),(86,7,'200.1.22','Insumos del sureste','distribuidora de consumibles para oficina',0,0,0),(87,7,'200.1.23','Tecnologica San Cristobal SA d','tienda de articulos tecono??gicos',0,0,0),(88,7,'200.1.24','Micro Chip SA de Cv','tienda de articulos tecono??gicos',0,0,0),(89,7,'200.1.25','Jairo Eniquez','tienda de articulos tecono??gicos',0,0,0),(90,7,'200.1.26','PROV DE PRUEBA','ESTE ES UN PROVEEDOR DE PRUEBA BIEN PROBADO INTENTANDO PROBAR LA PRUEBA PROBADA',0,0,0),(102,7,'200.1.27','UN PROV DE PRUEBA','UNA PRUEBA PA PROBAR LA PRUEBA',0,0,0),(103,7,'200.1.29','UN PROV DE PRUEBA 02','UNA PRUEBA PA PROBAR LA PRUEBA',0,0,0),(104,7,'200.1.30','PROVEEDOR DE PRUEBA 3','UNA PRUEBA MAS PARA PROBAR LA PRUEBA BIEN PROBADA XDD',0,0,0),(106,7,'200.1.31','PROV PRUEBA 4','ESTE ES OTRO PROVEEDOR DE PRUEBA',0,0,0),(110,7,'200.1.33','PRUEBA','',0,0,0),(114,4,'100.4.21','PR21','ESTE ES UN CLIENTE DE PRUEBA',0,0,0),(115,4,'100.4.22','PR022','ESTE ES UN NUEVO CLIENTE DE PRUEBA',0,0,0);
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
  PRIMARY KEY (`id_sucursar`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sucursal`
--

LOCK TABLES `sucursal` WRITE;
/*!40000 ALTER TABLE `sucursal` DISABLE KEYS */;
INSERT INTO `sucursal` VALUES (1,'Matris Principal','Tienda primaria','9619888758','sucursal1_empresa@fakemail.com','Chiapas','Tuxtla Gutierrez','4a oriente sur #448 barrio san roque','29000'),(2,'Sucursal Sab','Segunda Sucursal','9612364800','sucursal2_empresa@fakemail.com','Chiapas','Chiapa de Corzo','Av principal zona centro local7','29000'),(3,'Sucursal Trend','Tercera Sucursal con registro editado','9615568093','sucursal3_empresa@fakemail.com','Chiapas','Cintalapa','Av central poniente #725\nse modifican los datos de esta sucursal','29000'),(4,'Sucursal Jaf','Cuarta Sucursal','9617921545','sucursal4_empresa@fakemail.com','Chiapas','Tuxtla Gutierrez','5a norte oriente #613','29000'),(5,'Sucursal Pablo','Quinta Sucursal','9616693648','sucursal5_empresa@fakemail.com','Chiapas','San Cristobal','Calle Miguel Aleman Col la antigua #698','29000'),(6,'Sucursal Noely','Sexta Sucursal','9616951522','sucursal6_empresa@fakemail.com','Queretaro','Queretaro','Av corregidora col centro #450','29000'),(7,'Nueva sucursal','Esta es una nueva sucursal de prueba','9611567894','fakemail@mail.com','Chiapas','Tuxtla','4a oriente sur #449 entre 4a y 5a sur','29000');
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
) ENGINE=InnoDB AUTO_INCREMENT=614 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,1,'2022-01-02',1,36,2,59247.93103,9479.668966,68727.6,1),(2,2,'2022-01-02',1,17,16,5917.887931,946.862069,6864.75,1),(3,1,'2022-01-03',1,22,5,27589.65517,4414.344828,32004,1),(4,2,'2022-01-03',1,8,5,23664,3786.24,27450.24,1),(5,2,'2022-01-04',1,19,2,39629.84483,6340.775172,45970.62,1),(6,2,'2022-01-04',1,17,22,36747.16379,5879.546207,42626.71,1),(7,6,'2022-01-04',1,24,13,36071.31034,5771.409655,41842.72,1),(8,4,'2022-01-04',1,21,3,97724.15517,15635.86483,113360.02,1),(9,6,'2022-01-05',1,11,17,4698.448276,751.7517241,5450.2,1),(10,1,'2022-01-05',1,35,9,27840.99138,4454.558621,32295.55,1),(11,1,'2022-01-06',1,33,3,68931.76724,11029.08276,79960.85,1),(12,6,'2022-01-06',1,1,18,59820.41379,9571.266207,69391.68,1),(13,3,'2022-01-07',1,6,14,43880.27586,7020.844138,50901.12,1),(14,3,'2022-01-07',1,26,16,92847.59483,14855.61517,107703.21,1),(15,2,'2022-01-08',1,34,16,56749.65517,9079.944828,65829.6,1),(16,1,'2022-01-08',1,22,11,17206.89655,2753.103448,19960,1),(17,2,'2022-01-09',1,30,7,25168.27586,4026.924138,29195.2,1),(18,2,'2022-01-09',1,16,7,90679.64655,14508.74345,105188.39,1),(19,5,'2022-01-10',1,14,6,31939.65517,5110.344828,37050,1),(20,3,'2022-01-10',1,25,17,45851.55172,7336.248276,53187.8,1),(21,2,'2022-01-10',1,8,4,38530.48276,6164.877241,44695.36,1),(22,3,'2022-01-10',1,4,4,22304.68966,3568.750345,25873.44,1),(23,4,'2022-01-11',1,31,9,55646.42241,8903.427586,64549.85,1),(24,2,'2022-01-12',1,40,18,30034.64655,4805.543448,34840.19,1),(25,3,'2022-01-12',1,4,18,18850,3016,21866,1),(26,3,'2022-01-13',1,3,19,51789.38793,8286.302069,60075.69,1),(27,6,'2022-01-14',1,29,14,9093.724138,1454.995862,10548.72,1),(28,4,'2022-01-14',1,21,17,53522.12069,8563.53931,62085.66,1),(29,2,'2022-01-14',1,19,15,24401.48276,3904.237241,28305.72,1),(30,6,'2022-01-14',1,11,22,50756.74138,8121.078621,58877.82,1),(31,4,'2022-01-15',1,15,14,59033.25862,9445.321379,68478.58,1),(32,6,'2022-01-15',1,24,21,33274.86207,5323.977931,38598.84,1),(33,1,'2022-01-17',1,36,13,15800.80172,2528.128276,18328.93,1),(34,3,'2022-01-18',1,6,19,90367.65517,14458.82483,104826.48,1),(35,2,'2022-01-19',1,16,12,17046.2069,2727.393103,19773.6,1),(36,1,'2022-01-19',1,22,11,75583.76724,12093.40276,87677.17,1),(37,6,'2022-01-19',1,23,4,22402,3584.32,25986.32,1),(38,6,'2022-01-20',1,24,6,25236.2069,4037.793103,29274,1),(39,3,'2022-01-20',1,25,8,39299.58621,6287.933793,45587.52,1),(40,1,'2022-01-21',1,36,22,43749.0431,6999.846897,50748.89,1),(41,2,'2022-01-22',1,19,3,24935.25,3989.64,28924.89,1),(42,6,'2022-01-24',1,29,3,90461.37069,14473.81931,104935.19,1),(43,1,'2022-01-24',1,33,6,45369.81034,7259.169655,52628.98,1),(44,6,'2022-01-26',1,29,15,70944.26724,11351.08276,82295.35,1),(45,4,'2022-01-27',1,15,21,65038.68966,10406.19034,75444.88,1),(46,2,'2022-01-28',1,27,14,39361.03448,6297.765517,45658.8,1),(47,5,'2022-01-29',1,14,12,12929.7931,2068.766897,14998.56,1),(48,2,'2022-01-29',1,40,6,42561.43966,6809.830345,49371.27,1),(49,5,'2022-01-30',1,5,7,56943.23276,9110.917241,66054.15,1),(50,2,'2022-01-30',1,39,7,94007.44828,15041.19172,109048.64,1),(51,2,'2022-01-30',1,8,17,41721.98276,6675.517241,48397.5,1),(52,2,'2022-01-30',1,30,8,72966.12069,11674.57931,84640.7,1),(53,5,'2022-02-01',1,5,4,35279.10345,5644.656552,40923.76,1),(54,2,'2022-02-01',1,27,20,33959.62069,5433.53931,39393.16,1),(55,6,'2022-02-01',1,24,19,49972.80172,7995.648276,57968.45,1),(56,4,'2022-02-02',1,31,4,32288.90517,5166.224828,37455.13,1),(57,3,'2022-02-02',1,3,13,20484.82759,3277.572414,23762.4,1),(58,2,'2022-02-03',1,8,16,75385.0431,12061.6069,87446.65,1),(59,6,'2022-02-03',1,1,22,4113.793103,658.2068966,4772,1),(60,3,'2022-02-04',1,20,10,17950,2872,20822,1),(61,2,'2022-02-05',1,19,5,2587.965517,414.0744828,3002.04,1),(62,2,'2022-02-05',1,8,13,66109.96552,10577.59448,76687.56,1),(63,2,'2022-02-06',1,8,6,36931.03448,5908.965517,42840,1),(64,3,'2022-02-09',1,25,19,15285.33621,2445.653793,17730.99,1),(65,3,'2022-02-09',1,20,20,3646.551724,583.4482759,4230,1),(66,6,'2022-02-10',1,24,16,15781.03448,2524.965517,18306,1),(67,2,'2022-02-10',1,27,15,21169,3387.04,24556.04,1),(68,6,'2022-02-10',1,11,5,39237.58621,6278.013793,45515.6,1),(69,6,'2022-02-11',1,23,9,49295.57759,7887.292414,57182.87,1),(70,5,'2022-02-11',1,9,9,32563.40517,5210.144828,37773.55,1),(71,2,'2022-02-12',1,34,4,74749.24138,11959.87862,86709.12,1),(72,1,'2022-02-12',1,28,21,41544.75,6647.16,48191.91,1),(73,2,'2022-02-12',1,12,5,95961.68966,15353.87034,111315.56,1),(74,3,'2022-02-14',1,26,2,88140.19828,14102.43172,102242.63,1),(75,1,'2022-02-15',1,28,13,37923.65517,6067.784828,43991.44,1),(76,4,'2022-02-15',1,13,8,26044.2931,4167.086897,30211.38,1),(77,2,'2022-02-16',1,12,1,39964.13793,6394.262069,46358.4,1),(78,3,'2022-02-16',1,4,16,6702.068966,1072.331034,7774.4,1),(79,3,'2022-02-16',1,25,19,103457.9655,16553.27448,120011.24,1),(80,1,'2022-02-17',1,2,3,22711.34483,3633.815172,26345.16,1),(81,2,'2022-02-17',1,40,17,91453.68103,14632.58897,106086.27,1),(82,2,'2022-02-18',1,27,17,55471.49138,8875.438621,64346.93,1),(83,6,'2022-02-20',1,38,12,8386.206897,1341.793103,9728,1),(84,2,'2022-02-20',1,39,4,51549.81034,8247.969655,59797.78,1),(85,3,'2022-02-20',1,26,22,34128.2069,5460.513103,39588.72,1),(86,5,'2022-02-20',1,14,11,72372.90517,11579.66483,83952.57,1),(87,6,'2022-02-21',1,11,19,39660.24138,6345.638621,46005.88,1),(88,6,'2022-02-22',1,38,6,41145.51724,6583.282759,47728.8,1),(89,2,'2022-02-22',1,30,4,78750.82759,12600.13241,91350.96,1),(90,1,'2022-02-22',1,35,11,26338.93103,4214.228966,30553.16,1),(91,2,'2022-02-23',1,16,16,18823.67241,3011.787586,21835.46,1),(92,1,'2022-02-24',1,33,1,40590.5,6494.48,47084.98,1),(93,2,'2022-02-25',1,39,14,28449.43966,4551.910345,33001.35,1),(94,1,'2022-02-25',1,33,11,43882.75862,7021.241379,50904,1),(95,2,'2022-02-26',1,27,13,104986.1638,16797.78621,121783.95,1),(96,3,'2022-02-27',1,6,10,11424,1827.84,13251.84,1),(97,3,'2022-03-01',1,6,9,38641.87931,6182.70069,44824.58,1),(98,2,'2022-03-01',1,30,18,49014.62069,7842.33931,56856.96,1),(99,2,'2022-03-02',1,30,9,24181.03448,3868.965517,28050,1),(100,2,'2022-03-02',1,27,15,54130.75,8660.92,62791.67,1),(101,2,'2022-03-03',1,16,19,36445.32759,5831.252414,42276.58,1),(102,2,'2022-03-03',1,10,17,630.2672414,100.8427586,731.11,1),(103,3,'2022-03-04',1,6,7,42601.07759,6816.172414,49417.25,1),(104,2,'2022-03-06',1,37,16,66849.42241,10695.90759,77545.33,1),(105,5,'2022-03-07',1,9,1,52257.58621,8361.213793,60618.8,1),(106,2,'2022-03-07',1,30,3,36494.41379,5839.106207,42333.52,1),(107,6,'2022-03-08',1,29,9,23695.37931,3791.26069,27486.64,1),(108,3,'2022-03-08',1,6,19,51144.06897,8183.051034,59327.12,1),(109,5,'2022-03-09',1,9,14,59510.18966,9521.630345,69031.82,1),(110,2,'2022-03-10',1,27,1,113413.0345,18146.08552,131559.12,1),(111,1,'2022-03-10',1,22,9,79821.06897,12771.37103,92592.44,1),(112,2,'2022-03-11',1,40,12,2196.206897,351.3931034,2547.6,1),(113,1,'2022-03-12',1,28,6,10673.7931,1707.806897,12381.6,1),(114,3,'2022-03-13',1,32,1,23467.22414,3754.755862,27221.98,1),(115,3,'2022-03-13',1,3,19,35396.75,5663.48,41060.23,1),(116,3,'2022-03-14',1,6,21,18539.81034,2966.369655,21506.18,1),(117,3,'2022-03-15',1,3,3,67623.86207,10819.81793,78443.68,1),(118,1,'2022-03-15',1,28,13,22878.2069,3660.513103,26538.72,1),(119,6,'2022-03-16',1,38,5,45686.63793,7309.862069,52996.5,1),(120,3,'2022-03-17',1,7,12,66206.15517,10592.98483,76799.14,1),(121,3,'2022-03-17',1,20,6,74574.65517,11931.94483,86506.6,1),(122,1,'2022-03-19',1,36,11,73509.24138,11761.47862,85270.72,1),(123,1,'2022-03-20',1,2,14,95165.65517,15226.50483,110392.16,1),(124,5,'2022-03-21',1,14,12,8678.172414,1388.507586,10066.68,1),(125,4,'2022-03-21',1,31,2,16009.48276,2561.517241,18571,1),(126,4,'2022-03-22',1,21,16,45248,7239.68,52487.68,1),(127,2,'2022-03-23',1,39,17,14240,2278.4,16518.4,1),(128,2,'2022-03-23',1,10,7,17010.48276,2721.677241,19732.16,1),(129,2,'2022-03-23',1,30,6,129788.3362,20766.13379,150554.47,1),(130,2,'2022-03-24',1,27,21,35392.64655,5662.823448,41055.47,1),(131,6,'2022-03-24',1,11,5,61994.36207,9919.097931,71913.46,1),(132,2,'2022-03-24',1,34,9,32163.7931,5146.206897,37310,1),(133,3,'2022-03-24',1,4,13,15032.68966,2405.230345,17437.92,1),(134,2,'2022-03-24',1,16,21,48236.34483,7717.815172,55954.16,1),(135,2,'2022-03-25',1,17,14,25585.9569,4093.753103,29679.71,1),(136,3,'2022-03-25',1,32,7,3028,484.48,3512.48,1),(137,2,'2022-03-25',1,19,2,4355.172414,696.8275862,5052,1),(138,1,'2022-03-26',1,2,7,26645.62069,4263.29931,30908.92,1),(139,5,'2022-03-26',1,14,21,76821.46552,12291.43448,89112.9,1),(140,3,'2022-03-26',1,3,21,38237.35345,6117.976552,44355.33,1),(141,2,'2022-03-27',1,34,11,16763.44828,2682.151724,19445.6,1),(142,2,'2022-03-27',1,17,20,46857.51724,7497.202759,54354.72,1),(143,6,'2022-03-28',1,11,3,56806.59483,9089.055172,65895.65,1),(144,1,'2022-03-28',1,41,19,29381.47414,4701.035862,34082.51,1),(145,5,'2022-03-28',1,9,3,91642.37069,14662.77931,106305.15,1),(146,3,'2022-03-30',1,25,10,89283.68103,14285.38897,103569.07,1),(147,3,'2022-03-30',1,26,4,8085.344828,1293.655172,9379,1),(148,2,'2022-04-02',1,37,6,12936.82759,2069.892414,15006.72,1),(149,1,'2022-04-03',1,36,18,93964.2931,15034.2869,108998.58,1),(150,3,'2022-04-03',1,32,15,5848.724138,935.7958621,6784.52,1),(151,6,'2022-04-03',1,11,10,20073.34483,3211.735172,23285.08,1),(152,5,'2022-04-03',1,5,6,5371.293103,859.4068966,6230.7,1),(153,2,'2022-04-04',1,40,18,42485.56034,6797.689655,49283.25,1),(154,2,'2022-04-04',1,16,7,7809.025862,1249.444138,9058.47,1),(155,5,'2022-04-06',1,18,19,49220.68966,7875.310345,57096,1),(156,2,'2022-04-06',1,27,9,1089.310345,174.2896552,1263.6,1),(157,2,'2022-04-06',1,40,12,7228.87069,1156.61931,8385.49,1),(158,3,'2022-04-06',1,6,4,9867.931034,1578.868966,11446.8,1),(159,6,'2022-04-07',1,11,12,21430.03448,3428.805517,24858.84,1),(160,5,'2022-04-07',1,5,16,28207,4513.12,32720.12,1),(161,2,'2022-04-07',1,30,14,15765.44828,2522.471724,18287.92,1),(162,5,'2022-04-07',1,9,7,1274.482759,203.9172414,1478.4,1),(163,2,'2022-04-08',1,40,19,88844.75,14215.16,103059.91,1),(164,1,'2022-04-09',1,41,9,10843.93103,1735.028966,12578.96,1),(165,2,'2022-04-10',1,40,3,112280.0431,17964.8069,130244.85,1),(166,2,'2022-04-10',1,39,5,27832.86207,4453.257931,32286.12,1),(167,5,'2022-04-12',1,5,17,32096.27586,5135.404138,37231.68,1),(168,1,'2022-04-13',1,41,19,94143.81034,15063.00966,109206.82,1),(169,2,'2022-04-14',1,27,16,13094.34483,2095.095172,15189.44,1),(170,3,'2022-04-14',1,4,19,54907.7931,8785.246897,63693.04,1),(171,1,'2022-04-15',1,35,1,10101.72414,1616.275862,11718,1),(172,5,'2022-04-15',1,9,2,65073.13793,10411.70207,75484.84,1),(173,3,'2022-04-15',1,7,17,51340.53448,8214.485517,59555.02,1),(174,2,'2022-04-16',1,34,8,81547.34483,13047.57517,94594.92,1),(175,1,'2022-04-16',1,36,4,27636.2069,4421.793103,32058,1),(176,3,'2022-04-17',1,7,22,49694.7931,7951.166897,57645.96,1),(177,1,'2022-04-18',1,33,2,62577.15517,10012.34483,72589.5,1),(178,2,'2022-04-19',1,17,1,11106.89655,1777.103448,12884,1),(179,2,'2022-04-19',1,30,6,745.2155172,119.2344828,864.45,1),(180,4,'2022-04-20',1,13,18,108410,17345.6,125755.6,1),(181,3,'2022-04-20',1,26,20,39329.76724,6292.762759,45622.53,1),(182,2,'2022-04-20',1,40,10,62851.11207,10056.17793,72907.29,1),(183,6,'2022-04-20',1,11,15,40821.10345,6531.376552,47352.48,1),(184,2,'2022-04-21',1,27,11,1613.681034,258.1889655,1871.87,1),(185,6,'2022-04-22',1,11,3,23039.65517,3686.344828,26726,1),(186,1,'2022-04-22',1,2,15,48566.55172,7770.648276,56337.2,1),(187,2,'2022-04-22',1,16,15,21951.34483,3512.215172,25463.56,1),(188,6,'2022-04-23',1,24,10,45705.93103,7312.948966,53018.88,1),(189,3,'2022-04-24',1,32,19,21053.94828,3368.631724,24422.58,1),(190,2,'2022-04-24',1,30,9,49631.82759,7941.092414,57572.92,1),(191,1,'2022-04-24',1,36,7,21518.2069,3442.913103,24961.12,1),(192,2,'2022-04-24',1,40,20,91217.10345,14594.73655,105811.84,1),(193,3,'2022-04-24',1,7,3,74402.32759,11904.37241,86306.7,1),(194,2,'2022-04-26',1,27,6,12760.43103,2041.668966,14802.1,1),(195,4,'2022-04-26',1,21,19,71034.43966,11365.51034,82399.95,1),(196,3,'2022-04-27',1,6,2,6021.448276,963.4317241,6984.88,1),(197,2,'2022-04-30',1,30,17,44657.99138,7145.278621,51803.27,1),(198,5,'2022-04-30',1,14,15,53231.37931,8517.02069,61748.4,1),(199,2,'2022-05-01',1,8,15,6768.103448,1082.896552,7851,1),(200,2,'2022-05-02',1,10,5,105493.0086,16878.88138,122371.89,1),(201,6,'2022-05-02',1,23,15,19982.06897,3197.131034,23179.2,1),(202,2,'2022-05-03',1,40,9,54811.64655,8769.863448,63581.51,1),(203,2,'2022-05-04',1,12,4,19017.24138,3042.758621,22060,1),(204,1,'2022-05-05',1,35,10,7630.215517,1220.834483,8851.05,1),(205,2,'2022-05-06',1,27,6,18542.41379,2966.786207,21509.2,1),(206,6,'2022-05-07',1,23,15,48724.09483,7795.855172,56519.95,1),(207,5,'2022-05-09',1,5,6,61287.18966,9805.950345,71093.14,1),(208,2,'2022-05-09',1,27,15,32067.10345,5130.736552,37197.84,1),(209,6,'2022-05-09',1,24,7,65684.7069,10509.5531,76194.26,1),(210,2,'2022-05-09',1,39,3,61265.94828,9802.551724,71068.5,1),(211,6,'2022-05-10',1,23,7,57125.16379,9140.026207,66265.19,1),(212,3,'2022-05-11',1,7,14,45667.09483,7306.735172,52973.83,1),(213,4,'2022-05-11',1,13,6,35048.2931,5607.726897,40656.02,1),(214,4,'2022-05-11',1,13,3,8160.75,1305.72,9466.47,1),(215,3,'2022-05-12',1,3,22,8986.060345,1437.769655,10423.83,1),(216,2,'2022-05-13',1,27,3,23134.31034,3701.489655,26835.8,1),(217,6,'2022-05-13',1,11,17,55302.23276,8848.357241,64150.59,1),(218,5,'2022-05-14',1,5,2,2336,373.76,2709.76,1),(219,3,'2022-05-14',1,20,19,7314.482759,1170.317241,8484.8,1),(220,3,'2022-05-14',1,4,13,37131.72414,5941.075862,43072.8,1),(221,3,'2022-05-14',1,3,19,10191.7069,1630.673103,11822.38,1),(222,6,'2022-05-15',1,24,10,64451.81034,10312.28966,74764.1,1),(223,2,'2022-05-15',1,8,16,29774.63793,4763.942069,34538.58,1),(224,4,'2022-05-16',1,31,22,17842.52586,2854.804138,20697.33,1),(225,5,'2022-05-17',1,14,8,1720.689655,275.3103448,1996,1),(226,1,'2022-05-17',1,2,19,36070.11207,5771.217931,41841.33,1),(227,1,'2022-05-18',1,35,5,13556.89655,2169.103448,15726,1),(228,3,'2022-05-18',1,3,20,34760.48276,5561.677241,40322.16,1),(229,5,'2022-05-20',1,5,18,63900.53448,10224.08552,74124.62,1),(230,2,'2022-05-20',1,30,17,26963.14655,4314.103448,31277.25,1),(231,4,'2022-05-20',1,13,18,82501.56897,13200.25103,95701.82,1),(232,4,'2022-05-20',1,13,10,18370.2069,2939.233103,21309.44,1),(233,3,'2022-05-20',1,6,7,6676.741379,1068.278621,7745.02,1),(234,3,'2022-05-21',1,3,12,30497.91379,4879.666207,35377.58,1),(235,5,'2022-05-21',1,5,5,50168,8026.88,58194.88,1),(236,3,'2022-05-22',1,20,5,60822.64655,9731.623448,70554.27,1),(237,4,'2022-05-22',1,15,11,34833.85345,5573.416552,40407.27,1),(238,2,'2022-05-23',1,16,8,52144.5431,8343.126897,60487.67,1),(239,2,'2022-05-23',1,12,18,67075.13793,10732.02207,77807.16,1),(240,4,'2022-05-24',1,21,21,19425.23276,3108.037241,22533.27,1),(241,3,'2022-05-24',1,3,13,8261.37931,1321.82069,9583.2,1),(242,3,'2022-05-24',1,7,5,48919.65517,7827.144828,56746.8,1),(243,1,'2022-05-25',1,2,18,9863.146552,1578.103448,11441.25,1),(244,2,'2022-05-26',1,40,13,60625.03448,9700.005517,70325.04,1),(245,6,'2022-05-27',1,38,21,42897.72414,6863.635862,49761.36,1),(246,5,'2022-05-27',1,5,7,24121.25,3859.4,27980.65,1),(247,2,'2022-05-27',1,40,8,26026.24138,4164.198621,30190.44,1),(248,2,'2022-05-28',1,10,10,76222.11207,12195.53793,88417.65,1),(249,2,'2022-05-30',1,10,1,3416.594828,546.6551724,3963.25,1),(250,3,'2022-05-30',1,26,7,40831.2931,6533.006897,47364.3,1),(251,5,'2022-06-01',1,9,13,37040.81034,5926.529655,42967.34,1),(252,4,'2022-06-01',1,21,2,19827.74138,3172.438621,23000.18,1),(253,6,'2022-06-02',1,24,14,82863.86207,13258.21793,96122.08,1),(254,5,'2022-06-02',1,14,21,13810.72414,2209.715862,16020.44,1),(255,6,'2022-06-03',1,38,19,1479.646552,236.7434483,1716.39,1),(256,1,'2022-06-03',1,2,15,68538.81034,10966.20966,79505.02,1),(257,2,'2022-06-05',1,12,3,3000,480,3480,1),(258,1,'2022-06-05',1,2,18,29427.87931,4708.46069,34136.34,1),(259,4,'2022-06-05',1,13,17,32556.2069,5208.993103,37765.2,1),(260,1,'2022-06-05',1,33,9,11444.48276,1831.117241,13275.6,1),(261,5,'2022-06-05',1,18,21,99534.63793,15925.54207,115460.18,1),(262,2,'2022-06-06',1,12,3,47467.89655,7594.863448,55062.76,1),(263,4,'2022-06-06',1,31,9,16048.31897,2567.731034,18616.05,1),(264,1,'2022-06-07',1,41,1,6935.862069,1109.737931,8045.6,1),(265,2,'2022-06-07',1,27,7,57369.60345,9179.136552,66548.74,1),(266,6,'2022-06-08',1,1,7,25977.90517,4156.464828,30134.37,1),(267,1,'2022-06-08',1,33,21,1458.62069,233.3793103,1692,1),(268,2,'2022-06-08',1,10,3,107289.069,17166.25103,124455.32,1),(269,2,'2022-06-09',1,10,17,55165.87931,8826.54069,63992.42,1),(270,4,'2022-06-09',1,15,18,66906.10345,10704.97655,77611.08,1),(271,4,'2022-06-09',1,13,4,51685.86207,8269.737931,59955.6,1),(272,2,'2022-06-09',1,12,5,97616.12931,15618.58069,113234.71,1),(273,5,'2022-06-11',1,18,21,8866.517241,1418.642759,10285.16,1),(274,2,'2022-06-12',1,12,19,36267,5802.72,42069.72,1),(275,5,'2022-06-14',1,18,5,5064.956897,810.3931034,5875.35,1),(276,5,'2022-06-14',1,18,16,76225.18103,12196.02897,88421.21,1),(277,1,'2022-06-14',1,22,5,41585.19828,6653.631724,48238.83,1),(278,2,'2022-06-15',1,40,1,49293,7886.88,57179.88,1),(279,4,'2022-06-15',1,15,12,49861.24138,7977.798621,57839.04,1),(280,4,'2022-06-15',1,13,5,59733.13793,9557.302069,69290.44,1),(281,2,'2022-06-15',1,16,19,6536.206897,1045.793103,7582,1),(282,3,'2022-06-16',1,25,9,11034.82759,1765.572414,12800.4,1),(283,3,'2022-06-18',1,20,22,11397.17241,1823.547586,13220.72,1),(284,6,'2022-06-20',1,1,9,23091.72414,3694.675862,26786.4,1),(285,2,'2022-06-21',1,12,10,70223.5,11235.76,81459.26,1),(286,3,'2022-06-21',1,25,1,446.5517241,71.44827586,518,1),(287,1,'2022-06-22',1,2,12,10705.77586,1712.924138,12418.7,1),(288,5,'2022-06-22',1,18,4,45654.13793,7304.662069,52958.8,1),(289,4,'2022-06-23',1,21,1,38108.49138,6097.358621,44205.85,1),(290,1,'2022-06-24',1,35,22,87225.57759,13956.09241,101181.67,1),(291,2,'2022-06-24',1,17,11,17088,2734.08,19822.08,1),(292,6,'2022-06-25',1,38,15,65139.68966,10422.35034,75562.04,1),(293,4,'2022-06-25',1,21,3,97502.06034,15600.32966,113102.39,1),(294,2,'2022-06-25',1,30,9,42351.25862,6776.201379,49127.46,1),(295,2,'2022-06-25',1,40,14,95810.75862,15329.72138,111140.48,1),(296,3,'2022-06-25',1,7,21,45307.44828,7249.191724,52556.64,1),(297,4,'2022-06-26',1,13,6,35326.11207,5652.177931,40978.29,1),(298,6,'2022-06-26',1,11,11,49710.81897,7953.731034,57664.55,1),(299,2,'2022-06-28',1,10,11,22352,3576.32,25928.32,1),(300,3,'2022-06-28',1,6,21,10972.55172,1755.608276,12728.16,1),(301,3,'2022-06-28',1,3,12,8194.741379,1311.158621,9505.9,1),(302,6,'2022-06-29',1,29,19,32194.7069,5151.153103,37345.86,1),(303,1,'2022-06-29',1,33,19,42844.21552,6855.074483,49699.29,1),(304,4,'2022-06-30',1,21,6,8037.931034,1286.068966,9324,1),(305,1,'2022-06-30',1,28,13,147422.6121,23587.61793,171010.23,1),(306,1,'2022-07-01',1,41,15,93019.44828,14883.11172,107902.56,1),(307,6,'2022-07-03',1,29,5,20934.22414,3349.475862,24283.7,1),(308,6,'2022-07-04',1,1,21,16426.16379,2628.186207,19054.35,1),(309,2,'2022-07-04',1,34,14,8946,1431.36,10377.36,1),(310,2,'2022-07-04',1,30,19,57888.72414,9262.195862,67150.92,1),(311,1,'2022-07-05',1,41,4,42255,6760.8,49015.8,1),(312,6,'2022-07-05',1,38,21,36262.65517,5802.024828,42064.68,1),(313,2,'2022-07-06',1,40,7,47632.58621,7621.213793,55253.8,1),(314,5,'2022-07-06',1,5,13,8191.448276,1310.631724,9502.08,1),(315,2,'2022-07-07',1,12,3,5091.724138,814.6758621,5906.4,1),(316,2,'2022-07-07',1,8,20,93616.25,14978.6,108594.85,1),(317,1,'2022-07-08',1,2,7,24059.88793,3849.582069,27909.47,1),(318,6,'2022-07-09',1,38,22,11882.58621,1901.213793,13783.8,1),(319,6,'2022-07-10',1,11,15,44197.03448,7071.525517,51268.56,1),(320,1,'2022-07-11',1,33,7,21012.53448,3362.005517,24374.54,1),(321,3,'2022-07-11',1,6,22,39891.98276,6382.717241,46274.7,1),(322,2,'2022-07-11',1,40,5,64710.93103,10353.74897,75064.68,1),(323,6,'2022-07-12',1,24,14,103298.5776,16527.77241,119826.35,1),(324,3,'2022-07-13',1,3,13,6640.862069,1062.537931,7703.4,1),(325,2,'2022-07-13',1,10,12,3765.318966,602.4510345,4367.77,1),(326,2,'2022-07-15',1,17,17,78697.65517,12591.62483,91289.28,1),(327,5,'2022-07-17',1,9,3,14168.18103,2266.908966,16435.09,1),(328,3,'2022-07-17',1,6,6,33239.48276,5318.317241,38557.8,1),(329,2,'2022-07-20',1,19,1,946.0862069,151.3737931,1097.46,1),(330,4,'2022-07-20',1,13,21,43031.33621,6885.013793,49916.35,1),(331,5,'2022-07-20',1,5,14,47947.36207,7671.577931,55618.94,1),(332,1,'2022-07-22',1,36,21,19915.72414,3186.515862,23102.24,1),(333,6,'2022-07-22',1,23,7,114881.4828,18381.03724,133262.52,1),(334,4,'2022-07-22',1,15,1,8811.077586,1409.772414,10220.85,1),(335,4,'2022-07-26',1,15,12,61863.12069,9898.09931,71761.22,1),(336,2,'2022-07-26',1,39,6,83198.24138,13311.71862,96509.96,1),(337,6,'2022-07-26',1,1,9,56729.38793,9076.702069,65806.09,1),(338,6,'2022-07-26',1,1,11,2084.655172,333.5448276,2418.2,1),(339,3,'2022-07-27',1,25,22,63439.64655,10150.34345,73589.99,1),(340,3,'2022-07-27',1,25,17,28643.17241,4582.907586,33226.08,1),(341,1,'2022-07-28',1,28,20,27469.07759,4395.052414,31864.13,1),(342,1,'2022-07-28',1,33,4,25352.06897,4056.331034,29408.4,1),(343,1,'2022-07-29',1,2,15,58371.46552,9339.434483,67710.9,1),(344,3,'2022-07-30',1,6,11,51701.94828,8272.311724,59974.26,1),(345,1,'2022-07-30',1,41,13,32717.07759,5234.732414,37951.81,1),(346,1,'2022-07-30',1,36,14,47489.65517,7598.344828,55088,1),(347,4,'2022-07-30',1,13,15,56304.78448,9008.765517,65313.55,1),(348,2,'2022-08-02',1,34,10,21861.46552,3497.834483,25359.3,1),(349,2,'2022-08-02',1,27,22,59548.93966,9527.830345,69076.77,1),(350,2,'2022-08-03',1,17,19,60374.24138,9659.878621,70034.12,1),(351,3,'2022-08-03',1,20,19,97947.31897,15671.57103,113618.89,1),(352,4,'2022-08-04',1,31,18,25847.58621,4135.613793,29983.2,1),(353,2,'2022-08-04',1,34,22,26401.86207,4224.297931,30626.16,1),(354,1,'2022-08-05',1,22,12,41284.71552,6605.554483,47890.27,1),(355,3,'2022-08-05',1,3,8,48802.00862,7808.321379,56610.33,1),(356,1,'2022-08-08',1,22,7,83885.13793,13421.62207,97306.76,1),(357,2,'2022-08-09',1,39,11,10123.01724,1619.682759,11742.7,1),(358,2,'2022-08-09',1,8,12,23130.72414,3700.915862,26831.64,1),(359,1,'2022-08-09',1,36,22,63055.67241,10088.90759,73144.58,1),(360,1,'2022-08-10',1,33,3,35624.07759,5699.852414,41323.93,1),(361,1,'2022-08-11',1,22,16,41897.03448,6703.525517,48600.56,1),(362,4,'2022-08-11',1,21,4,12595.47414,2015.275862,14610.75,1),(363,2,'2022-08-11',1,34,14,42113.43966,6738.150345,48851.59,1),(364,6,'2022-08-11',1,29,1,61619.51724,9859.122759,71478.64,1),(365,4,'2022-08-11',1,21,17,56886.2931,9101.806897,65988.1,1),(366,4,'2022-08-12',1,31,21,30794.39655,4927.103448,35721.5,1),(367,3,'2022-08-12',1,3,19,67553.03448,10808.48552,78361.52,1),(368,4,'2022-08-12',1,13,8,21134.34483,3381.495172,24515.84,1),(369,1,'2022-08-13',1,33,1,14085,2253.6,16338.6,1),(370,1,'2022-08-14',1,36,10,45832.10345,7333.136552,53165.24,1),(371,3,'2022-08-15',1,7,19,30655.86207,4904.937931,35560.8,1),(372,5,'2022-08-15',1,9,11,25424.83621,4067.973793,29492.81,1),(373,2,'2022-08-16',1,40,11,50492.92241,8078.867586,58571.79,1),(374,6,'2022-08-16',1,1,7,30556.08621,4888.973793,35445.06,1),(375,6,'2022-08-17',1,29,21,54359,8697.44,63056.44,1),(376,3,'2022-08-18',1,26,13,16148.2069,2583.713103,18731.92,1),(377,2,'2022-08-19',1,27,16,17416.48276,2786.637241,20203.12,1),(378,6,'2022-08-19',1,1,8,75273.98276,12043.83724,87317.82,1),(379,1,'2022-08-19',1,35,16,37034.48276,5925.517241,42960,1),(380,1,'2022-08-19',1,28,6,6431.37931,1029.02069,7460.4,1),(381,2,'2022-08-23',1,39,17,37686.81034,6029.889655,43716.7,1),(382,1,'2022-08-25',1,28,8,28534.55172,4565.528276,33100.08,1),(383,2,'2022-08-25',1,37,16,81036.94828,12965.91172,94002.86,1),(384,1,'2022-08-25',1,2,20,12144.15517,1943.064828,14087.22,1),(385,6,'2022-08-25',1,38,11,9460.862069,1513.737931,10974.6,1),(386,6,'2022-08-25',1,38,22,46363.10345,7418.096552,53781.2,1),(387,2,'2022-08-28',1,27,4,38335.86207,6133.737931,44469.6,1),(388,6,'2022-08-29',1,24,5,91348.42241,14615.74759,105964.17,1),(389,6,'2022-08-30',1,24,10,23637.65517,3782.024828,27419.68,1),(390,2,'2022-08-30',1,34,11,64192.11207,10270.73793,74462.85,1),(391,2,'2022-09-01',1,30,9,21258.62069,3401.37931,24660,1),(392,2,'2022-09-01',1,27,3,27776.47414,4444.235862,32220.71,1),(393,2,'2022-09-02',1,27,20,42500.13793,6800.022069,49300.16,1),(394,5,'2022-09-04',1,18,10,27633.10345,4421.296552,32054.4,1),(395,2,'2022-09-05',1,37,18,40527.43966,6484.390345,47011.83,1),(396,5,'2022-09-05',1,9,8,5710.344828,913.6551724,6624,1),(397,2,'2022-09-06',1,16,18,56288.12931,9006.10069,65294.23,1),(398,2,'2022-09-06',1,12,18,30451.06897,4872.171034,35323.24,1),(399,3,'2022-09-06',1,6,5,45206.22414,7232.995862,52439.22,1),(400,6,'2022-09-09',1,29,19,2141.37931,342.6206897,2484,1),(401,2,'2022-09-09',1,34,14,177020.9397,28323.35034,205344.29,1),(402,4,'2022-09-10',1,13,4,49153.16379,7864.506207,57017.67,1),(403,3,'2022-09-13',1,3,15,62460.93103,9993.748966,72454.68,1),(404,1,'2022-09-13',1,35,14,51428.40517,8228.544828,59656.95,1),(405,5,'2022-09-14',1,5,19,26567.30172,4250.768276,30818.07,1),(406,1,'2022-09-14',1,36,7,100214.6121,16034.33793,116248.95,1),(407,3,'2022-09-15',1,3,16,50000.35345,8000.056552,58000.41,1),(408,2,'2022-09-15',1,34,19,38070.64655,6091.303448,44161.95,1),(409,2,'2022-09-15',1,30,20,22239.84483,3558.375172,25798.22,1),(410,2,'2022-09-18',1,40,21,63937.55172,10230.00828,74167.56,1),(411,2,'2022-09-19',1,16,16,93972.32759,15035.57241,109007.9,1),(412,3,'2022-09-19',1,20,4,56895,9103.2,65998.2,1),(413,2,'2022-09-20',1,30,20,783.6982759,125.3917241,909.09,1),(414,2,'2022-09-20',1,37,10,39857.84483,6377.255172,46235.1,1),(415,6,'2022-09-21',1,29,8,50425.55172,8068.088276,58493.64,1),(416,1,'2022-09-21',1,35,11,27508.88793,4401.422069,31910.31,1),(417,2,'2022-09-22',1,37,10,19276.24138,3084.198621,22360.44,1),(418,2,'2022-09-22',1,17,12,4306.241379,688.9986207,4995.24,1),(419,2,'2022-09-23',1,12,2,14241.10345,2278.576552,16519.68,1),(420,2,'2022-09-24',1,27,10,106696.1379,17071.38207,123767.52,1),(421,2,'2022-09-24',1,10,18,81613.60345,13058.17655,94671.78,1),(422,4,'2022-09-24',1,21,7,16131.41379,2581.026207,18712.44,1),(423,6,'2022-09-25',1,38,15,130337.6207,20854.01931,151191.64,1),(424,1,'2022-09-25',1,28,5,61864.65517,9898.344828,71763,1),(425,6,'2022-09-25',1,38,17,13699.68103,2191.948966,15891.63,1),(426,3,'2022-09-26',1,26,5,16032.41379,2565.186207,18597.6,1),(427,1,'2022-09-28',1,36,11,43527.98276,6964.477241,50492.46,1),(428,5,'2022-09-28',1,9,17,60371.47414,9659.435862,70030.91,1),(429,6,'2022-09-29',1,29,4,27501.51724,4400.242759,31901.76,1),(430,2,'2022-09-29',1,19,2,4578.12931,732.5006897,5310.63,1),(431,5,'2022-09-29',1,5,10,25560.43103,4089.668966,29650.1,1),(432,3,'2022-09-30',1,3,3,61077.15517,9772.344828,70849.5,1),(433,2,'2022-09-30',1,39,22,83245.22414,13319.23586,96564.46,1),(434,4,'2022-10-01',1,15,15,48550.91379,7768.146207,56319.06,1),(435,4,'2022-10-02',1,31,20,32957.41379,5273.186207,38230.6,1),(436,1,'2022-10-03',1,41,17,13946.55172,2231.448276,16178,1),(437,3,'2022-10-03',1,26,5,50076.88793,8012.302069,58089.19,1),(438,1,'2022-10-04',1,28,8,59101.77586,9456.284138,68558.06,1),(439,1,'2022-10-04',1,41,16,33775.64655,5404.103448,39179.75,1),(440,5,'2022-10-05',1,18,3,64835.55172,10373.68828,75209.24,1),(441,5,'2022-10-05',1,9,4,16360.06897,2617.611034,18977.68,1),(442,6,'2022-10-06',1,29,17,49035.5,7845.68,56881.18,1),(443,3,'2022-10-07',1,26,10,66202.41379,10592.38621,76794.8,1),(444,2,'2022-10-07',1,8,15,31569.17241,5051.067586,36620.24,1),(445,5,'2022-10-08',1,18,3,4612.413793,737.9862069,5350.4,1),(446,4,'2022-10-08',1,13,1,51140.55172,8182.488276,59323.04,1),(447,3,'2022-10-09',1,7,8,52959.10345,8473.456552,61432.56,1),(448,1,'2022-10-09',1,33,22,53705.37069,8592.85931,62298.23,1),(449,2,'2022-10-10',1,37,7,34255.60345,5480.896552,39736.5,1),(450,1,'2022-10-10',1,41,9,111121.1466,17779.38345,128900.53,1),(451,3,'2022-10-10',1,20,4,30486.2069,4877.793103,35364,1),(452,2,'2022-10-11',1,37,21,88479.91379,14156.78621,102636.7,1),(453,1,'2022-10-12',1,33,13,45663.17241,7306.107586,52969.28,1),(454,3,'2022-10-12',1,32,15,59899.08621,9583.853793,69482.94,1),(455,6,'2022-10-12',1,24,13,28554.48276,4568.717241,33123.2,1),(456,6,'2022-10-13',1,11,8,35571.55172,5691.448276,41263,1),(457,5,'2022-10-13',1,5,6,130044.9655,20807.19448,150852.16,1),(458,2,'2022-10-14',1,39,18,63576.15517,10172.18483,73748.34,1),(459,1,'2022-10-15',1,35,3,43781.86207,7005.097931,50786.96,1),(460,2,'2022-10-15',1,37,9,48949,7831.84,56780.84,1),(461,2,'2022-10-15',1,37,20,80894.68966,12943.15034,93837.84,1),(462,5,'2022-10-15',1,5,15,96726.34483,15476.21517,112202.56,1),(463,4,'2022-10-15',1,13,22,5420.103448,867.2165517,6287.32,1),(464,3,'2022-10-15',1,20,2,15312,2449.92,17761.92,1),(465,1,'2022-10-15',1,2,15,31465.12931,5034.42069,36499.55,1),(466,3,'2022-10-17',1,3,14,38194.89655,6111.183448,44306.08,1),(467,6,'2022-10-18',1,11,12,12264.39655,1962.303448,14226.7,1),(468,4,'2022-10-19',1,21,12,2750.948276,440.1517241,3191.1,1),(469,3,'2022-10-19',1,26,22,53157.15517,8505.144828,61662.3,1),(470,2,'2022-10-20',1,40,1,35805.96552,5728.954483,41534.92,1),(471,2,'2022-10-20',1,34,22,12155.17241,1944.827586,14100,1),(472,2,'2022-10-20',1,34,20,50713.37069,8114.13931,58827.51,1),(473,6,'2022-10-20',1,38,9,26866.12069,4298.57931,31164.7,1),(474,3,'2022-10-20',1,3,21,67067.03448,10730.72552,77797.76,1),(475,6,'2022-10-20',1,24,17,24347.89655,3895.663448,28243.56,1),(476,2,'2022-10-20',1,8,5,93039.23276,14886.27724,107925.51,1),(477,5,'2022-10-20',1,9,10,51555.60345,8248.896552,59804.5,1),(478,5,'2022-10-21',1,9,10,20638.62069,3302.17931,23940.8,1),(479,6,'2022-10-21',1,38,10,37372.96552,5979.674483,43352.64,1),(480,3,'2022-10-21',1,6,1,20711.41379,3313.826207,24025.24,1),(481,1,'2022-10-21',1,36,2,33135.23276,5301.637241,38436.87,1),(482,4,'2022-10-22',1,31,2,118467.1379,18954.74207,137421.88,1),(483,2,'2022-10-23',1,40,19,9413.965517,1506.234483,10920.2,1),(484,4,'2022-10-24',1,13,20,48157.4569,7705.193103,55862.65,1),(485,1,'2022-10-25',1,35,7,10623.68103,1699.788966,12323.47,1),(486,4,'2022-10-25',1,15,8,69624.5431,11139.9269,80764.47,1),(487,6,'2022-10-27',1,11,17,35389.10345,5662.256552,41051.36,1),(488,2,'2022-10-27',1,16,1,4112.068966,657.9310345,4770,1),(489,3,'2022-10-28',1,26,4,26575.86207,4252.137931,30828,1),(490,4,'2022-10-28',1,15,3,22900.86207,3664.137931,26565,1),(491,1,'2022-10-29',1,22,6,68816.08621,11010.57379,79826.66,1),(492,6,'2022-10-29',1,11,4,5377.913793,860.4662069,6238.38,1),(493,5,'2022-11-01',1,18,6,74590.2069,11934.4331,86524.64,1),(494,2,'2022-11-01',1,37,10,67046.68966,10727.47034,77774.16,1),(495,3,'2022-11-02',1,6,10,9694.396552,1551.103448,11245.5,1),(496,2,'2022-11-03',1,16,7,20420.68966,3267.310345,23688,1),(497,1,'2022-11-03',1,22,6,49523.16379,7923.706207,57446.87,1),(498,6,'2022-11-04',1,23,12,71512.24138,11441.95862,82954.2,1),(499,2,'2022-11-04',1,12,21,17967.7931,2874.846897,20842.64,1),(500,3,'2022-11-04',1,20,16,36997.42241,5919.587586,42917.01,1),(501,4,'2022-11-05',1,31,6,29835.74138,4773.718621,34609.46,1),(502,4,'2022-11-05',1,13,7,14850.05172,2376.008276,17226.06,1),(503,2,'2022-11-06',1,37,11,37767.80172,6042.848276,43810.65,1),(504,2,'2022-11-06',1,40,2,15786.2069,2525.793103,18312,1),(505,2,'2022-11-06',1,27,5,34847.86207,5575.657931,40423.52,1),(506,1,'2022-11-06',1,22,12,1402.775862,224.4441379,1627.22,1),(507,6,'2022-11-07',1,29,14,56303.00862,9008.481379,65311.49,1),(508,1,'2022-11-07',1,35,4,3887.896552,622.0634483,4509.96,1),(509,2,'2022-11-08',1,30,12,29451.72414,4712.275862,34164,1),(510,3,'2022-11-09',1,26,3,4017.655172,642.8248276,4660.48,1),(511,3,'2022-11-09',1,3,13,7394.439655,1183.110345,8577.55,1),(512,5,'2022-11-10',1,14,15,25825.65517,4132.104828,29957.76,1),(513,2,'2022-11-10',1,39,9,54506.03448,8720.965517,63227,1),(514,1,'2022-11-10',1,35,18,64656.08621,10344.97379,75001.06,1),(515,4,'2022-11-10',1,15,3,29787.18103,4765.948966,34553.13,1),(516,6,'2022-11-10',1,24,3,20250.88793,3240.142069,23491.03,1),(517,6,'2022-11-11',1,38,5,117811.0345,18849.76552,136660.8,1),(518,2,'2022-11-11',1,30,12,57006.41379,9121.026207,66127.44,1),(519,6,'2022-11-11',1,1,3,85418.5431,13666.9669,99085.51,1),(520,3,'2022-11-12',1,6,20,36231.28448,5797.005517,42028.29,1),(521,6,'2022-11-12',1,38,16,76140.68966,12182.51034,88323.2,1),(522,4,'2022-11-13',1,15,1,80323.60345,12851.77655,93175.38,1),(523,6,'2022-11-15',1,11,19,53775.88793,8604.142069,62380.03,1),(524,2,'2022-11-15',1,8,12,82915.40517,13266.46483,96181.87,1),(525,2,'2022-11-15',1,16,21,21374.13793,3419.862069,24794,1),(526,1,'2022-11-16',1,2,15,82792.25862,13246.76138,96039.02,1),(527,6,'2022-11-16',1,1,4,71871.0431,11499.3669,83370.41,1),(528,2,'2022-11-18',1,37,1,7193.948276,1151.031724,8344.98,1),(529,6,'2022-11-18',1,24,19,14384.51724,2301.522759,16686.04,1),(530,3,'2022-11-18',1,25,14,31370.44828,5019.271724,36389.72,1),(531,2,'2022-11-19',1,17,4,51536.03448,8245.765517,59781.8,1),(532,2,'2022-11-20',1,17,7,45150.66379,7224.106207,52374.77,1),(533,5,'2022-11-20',1,9,3,94449.86207,15111.97793,109561.84,1),(534,6,'2022-11-21',1,11,8,22279.34483,3564.695172,25844.04,1),(535,2,'2022-11-21',1,37,7,30736.55172,4917.848276,35654.4,1),(536,6,'2022-11-22',1,1,6,44773.65517,7163.784828,51937.44,1),(537,1,'2022-11-22',1,28,12,51709.55172,8273.528276,59983.08,1),(538,2,'2022-11-22',1,19,8,39896.55172,6383.448276,46280,1),(539,2,'2022-11-23',1,27,9,9411.5,1505.84,10917.34,1),(540,5,'2022-11-24',1,9,9,6776.017241,1084.162759,7860.18,1),(541,4,'2022-11-24',1,21,6,30534.12069,4885.45931,35419.58,1),(542,2,'2022-11-24',1,37,16,122252.3621,19560.37793,141812.74,1),(543,1,'2022-11-24',1,2,19,144517.8966,23122.86345,167640.76,1),(544,4,'2022-11-25',1,13,1,18119.89655,2899.183448,21019.08,1),(545,5,'2022-11-25',1,18,13,318.6206897,50.97931034,369.6,1),(546,1,'2022-11-25',1,2,11,21196.55172,3391.448276,24588,1),(547,3,'2022-11-26',1,7,5,35787.56897,5726.011034,41513.58,1),(548,4,'2022-11-27',1,31,7,13671.72414,2187.475862,15859.2,1),(549,4,'2022-11-28',1,31,2,27691.03448,4430.565517,32121.6,1),(550,2,'2022-11-28',1,16,5,73197.24138,11711.55862,84908.8,1),(551,3,'2022-11-28',1,25,1,70983.38793,11357.34207,82340.73,1),(552,1,'2022-11-28',1,36,20,45691.91379,7310.706207,53002.62,1),(553,2,'2022-11-28',1,19,13,68859.51724,11017.52276,79877.04,1),(554,6,'2022-11-28',1,29,14,19983.63793,3197.382069,23181.02,1),(555,1,'2022-11-29',1,41,8,35274.82759,5643.972414,40918.8,1),(556,6,'2022-11-29',1,11,14,7393.103448,1182.896552,8576,1),(557,1,'2022-11-29',1,35,15,14787.81034,2366.049655,17153.86,1),(558,4,'2022-11-29',1,31,12,59884,9581.44,69465.44,1),(559,2,'2022-11-30',1,16,1,77739.00862,12438.24138,90177.25,1),(560,1,'2022-11-30',1,41,15,51314.31034,8210.289655,59524.6,1),(561,5,'2022-11-30',1,18,4,38873.13793,6219.702069,45092.84,1),(562,2,'2022-12-01',1,39,4,31915.48276,5106.477241,37021.96,1),(563,5,'2022-12-01',1,5,17,38660.53448,6185.685517,44846.22,1),(564,1,'2022-12-01',1,2,9,50049.58621,8007.933793,58057.52,1),(565,6,'2022-12-02',1,24,7,86811.93103,13889.90897,100701.84,1),(566,6,'2022-12-03',1,29,1,18403.2069,2944.513103,21347.72,1),(567,2,'2022-12-03',1,27,6,46702.62931,7472.42069,54175.05,1),(568,1,'2022-12-03',1,41,16,44723.43103,7155.748966,51879.18,1),(569,3,'2022-12-04',1,6,15,46638.10345,7462.096552,54100.2,1),(570,3,'2022-12-05',1,7,15,29863.42241,4778.147586,34641.57,1),(571,2,'2022-12-06',1,16,12,44388.55172,7102.168276,51490.72,1),(572,2,'2022-12-06',1,39,11,112735.7586,18037.72138,130773.48,1),(573,4,'2022-12-06',1,21,1,8137.931034,1302.068966,9440,1),(574,2,'2022-12-07',1,19,4,55517.35345,8882.776552,64400.13,1),(575,2,'2022-12-07',1,8,2,20720.24138,3315.238621,24035.48,1),(576,3,'2022-12-08',1,26,6,10326.96552,1652.314483,11979.28,1),(577,1,'2022-12-09',1,22,11,62832.89655,10053.26345,72886.16,1),(578,1,'2022-12-09',1,36,9,93314.65517,14930.34483,108245,1),(579,2,'2022-12-09',1,27,8,57783.43103,9245.348966,67028.78,1),(580,2,'2022-12-10',1,27,9,4485.818966,717.7310345,5203.55,1),(581,3,'2022-12-11',1,26,13,33497.7931,5359.646897,38857.44,1),(582,2,'2022-12-11',1,30,12,55786.01724,8925.762759,64711.78,1),(583,4,'2022-12-11',1,15,12,6997.068966,1119.531034,8116.6,1),(584,1,'2022-12-12',1,28,3,41991.51724,6718.642759,48710.16,1),(585,2,'2022-12-13',1,39,13,68599.63793,10975.94207,79575.58,1),(586,1,'2022-12-13',1,41,21,117988.7672,18878.20276,136866.97,1),(587,2,'2022-12-14',1,37,5,50175.68103,8028.108966,58203.79,1),(588,2,'2022-12-14',1,8,17,42799.25,6847.88,49647.13,1),(589,5,'2022-12-14',1,14,4,99090.06897,15854.41103,114944.48,1),(590,3,'2022-12-14',1,20,13,15503.62069,2480.57931,17984.2,1),(591,4,'2022-12-14',1,15,12,28934.7069,4629.553103,33564.26,1),(592,1,'2022-12-14',1,22,6,43199.82759,6911.972414,50111.8,1),(593,2,'2022-12-16',1,12,15,59002.05172,9440.328276,68442.38,1),(594,6,'2022-12-17',1,29,3,43875.06897,7020.011034,50895.08,1),(595,5,'2022-12-17',1,5,19,44634.58621,7141.533793,51776.12,1),(596,3,'2022-12-17',1,20,10,72043.23276,11526.91724,83570.15,1),(597,5,'2022-12-18',1,5,17,116662.5,18666,135328.5,1),(598,1,'2022-12-19',1,41,21,31576.7931,5052.286897,36629.08,1),(599,6,'2022-12-21',1,24,18,115646.3534,18503.41655,134149.77,1),(600,1,'2022-12-21',1,2,15,4758.62069,761.3793103,5520,1),(601,2,'2022-12-22',1,16,1,104259.7414,16681.55862,120941.3,1),(602,2,'2022-12-22',1,10,4,4937.586207,790.0137931,5727.6,1),(603,1,'2022-12-23',1,33,8,10215.31034,1634.449655,11849.76,1),(604,1,'2022-12-24',1,35,5,83001.25,13280.2,96281.45,1),(605,2,'2022-12-24',1,27,4,28822.80172,4611.648276,33434.45,1),(606,1,'2022-12-25',1,2,20,7448.275862,1191.724138,8640,1),(607,3,'2022-12-27',1,6,9,65533.58621,10485.37379,76018.96,1),(608,3,'2022-12-27',1,6,17,10728.36207,1716.537931,12444.9,1),(609,3,'2022-12-28',1,20,5,32352.08621,5176.333793,37528.42,1),(610,5,'2022-12-28',1,5,10,30065.51724,4810.482759,34876,1),(611,2,'2022-12-28',1,16,10,23421.18103,3747.388966,27168.57,1),(612,2,'2022-12-29',1,40,16,9711.551724,1553.848276,11265.4,1),(613,1,'2022-12-30',1,22,18,56581.02586,9052.964138,65633.99,1);
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
			articulo.precio_mayoreo
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
			articulo.precio_mayoreo
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
			articulo.precio_mayoreo
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
			articulo.precio_mayoreo
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
			articulo.precio_mayoreo
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
		nombre,
        descripcion,
        telefono,
        email,
        estado,
        ciudad,
        direccion,
        codigo_postal
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
			codigo_postal
		)VALUES(
			nombre,
			descripcion,
			telefono,
			email,
			estado,
			ciudad,
			direccion,
			codigo_postal
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
		codigo_postal
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
		codigo_postal_a
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
    codigo_postal
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
        cantidad_mayoreo = cant_m
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
        codigo_postal = codigo_postal_a
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
        codigo_postal = codigo_postal
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
		articulo.precio_mayoreo
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
		cliente.rfc,
		sub_cuentas_tercer_nivel.clave,
		cliente.nombre_completo,
		cliente.nombre_corto,
		cliente.correo_electronico,
		cliente.estado,
		cliente.ciudad,
		cliente.direccion,
		cliente.codigo_postal
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
		nombre,
        descripcion,
        telefono,
        email,
        estado,
        ciudad,
        direccion,
        codigo_postal
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

-- Dump completed on 2023-09-18 18:03:18
