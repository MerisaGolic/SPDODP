CREATE DATABASE  IF NOT EXISTS `modulzakorisnikedb` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `modulzakorisnikedb`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: modulzakorisnikedb
-- ------------------------------------------------------
-- Server version	5.7.14

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) COLLATE utf8_bin NOT NULL,
  `password` varchar(10) COLLATE utf8_bin NOT NULL,
  `promjena_passworda` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `email` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `korisnici`
--

LOCK TABLES `korisnici` WRITE;
/*!40000 ALTER TABLE `korisnici` DISABLE KEYS */;
INSERT INTO `korisnici` VALUES (1,'anogo','anogo','2017-05-27 15:30:13','armin_nogo@hotmail.com'),(2,'mgolic','mgolic','2017-05-27 14:33:46','meryssa.c@gmail.com'),(3,'mmujic','mmujic',NULL,NULL),(4,'ssuljic','ssuljic',NULL,NULL),(5,'user','pw',NULL,NULL);
/*!40000 ALTER TABLE `korisnici` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacijenti`
--

DROP TABLE IF EXISTS `pacijenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacijenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime_prezime` varchar(50) COLLATE utf8_bin NOT NULL,
  `spol` varchar(1) COLLATE utf8_bin NOT NULL,
  `datum_rodjenja` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacijenti`
--

LOCK TABLES `pacijenti` WRITE;
/*!40000 ALTER TABLE `pacijenti` DISABLE KEYS */;
INSERT INTO `pacijenti` VALUES (1,'Meho Mehic','m','2017-03-01'),(2,'Mirnes Peljto','m','1994-05-15'),(3,'Senad Peljto','m','1983-05-15');
/*!40000 ALTER TABLE `pacijenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacijenti_korisnici`
--

DROP TABLE IF EXISTS `pacijenti_korisnici`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacijenti_korisnici` (
  `id` int(11) NOT NULL,
  `id_pacijenta` int(11) NOT NULL,
  `id_korisnika` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pacijenta` (`id_pacijenta`),
  KEY `id_korisnika` (`id_korisnika`),
  CONSTRAINT `FK8vgk4ebug1gyqec6e30ae46jy` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`),
  CONSTRAINT `FKcxneb4kyne7qc2rurtak0mfgr` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`),
  CONSTRAINT `pacijenti_korisnici_ibfk_1` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`),
  CONSTRAINT `pacijenti_korisnici_ibfk_2` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacijenti_korisnici`
--

LOCK TABLES `pacijenti_korisnici` WRITE;
/*!40000 ALTER TABLE `pacijenti_korisnici` DISABLE KEYS */;
INSERT INTO `pacijenti_korisnici` VALUES (12,2,1),(13,3,1),(43,3,4);
/*!40000 ALTER TABLE `pacijenti_korisnici` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-28 19:24:55
