CREATE DATABASE  IF NOT EXISTS `nwt` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `nwt`;
-- MySQL dump 10.13  Distrib 5.7.9, for Win32 (AMD64)
--
-- Host: localhost    Database: nwt
-- ------------------------------------------------------
-- Server version	5.7.9

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
-- Table structure for table `dijagnoze`
--

DROP TABLE IF EXISTS `dijagnoze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dijagnoze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `opis` varchar(500) NOT NULL,
  `koristi` int(11) NOT NULL,
  `postotak` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dijagnoze`
--

LOCK TABLES `dijagnoze` WRITE;
/*!40000 ALTER TABLE `dijagnoze` DISABLE KEYS */;
INSERT INTO `dijagnoze` VALUES (1,'kasalj','hripa samo tako',1,'20');
/*!40000 ALTER TABLE `dijagnoze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dijagnoze_pacijenti`
--

DROP TABLE IF EXISTS `dijagnoze_pacijenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dijagnoze_pacijenti` (
  `id` int(11) NOT NULL,
  `id_dijagnoze` int(11) NOT NULL,
  `id_pacijenta` int(11) NOT NULL,
  `datum_dijagnoze` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dijagnoze_idx` (`id_dijagnoze`),
  KEY `id_pacijenta_idx` (`id_pacijenta`),
  CONSTRAINT `FKg11w8ner7t0uwwmvp15jimdnx` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`),
  CONSTRAINT `FKrxwgt6oh4y5u1n45c4lydokj6` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`),
  CONSTRAINT `id_dijagnoze` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `id_pacijenta` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dijagnoze_pacijenti`
--

LOCK TABLES `dijagnoze_pacijenti` WRITE;
/*!40000 ALTER TABLE `dijagnoze_pacijenti` DISABLE KEYS */;
INSERT INTO `dijagnoze_pacijenti` VALUES (11,1,1,'2017-03-27');
/*!40000 ALTER TABLE `dijagnoze_pacijenti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pacijenti`
--

DROP TABLE IF EXISTS `pacijenti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pacijenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime_prezime` varchar(45) NOT NULL,
  `spol` varchar(1) NOT NULL,
  `datum_rodjenja` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pacijenti`
--

LOCK TABLES `pacijenti` WRITE;
/*!40000 ALTER TABLE `pacijenti` DISABLE KEYS */;
INSERT INTO `pacijenti` VALUES (1,'Armin Nogo','M','1995-05-02');
/*!40000 ALTER TABLE `pacijenti` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-04 19:33:27
