CREATE DATABASE  IF NOT EXISTS `modul_dijagnoze` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `modul_dijagnoze`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: modul_dijagnoze
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
-- Table structure for table `dijagnoze`
--

DROP TABLE IF EXISTS `dijagnoze`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dijagnoze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `opis` varchar(500) DEFAULT NULL,
  `postotak` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dijagnoze`
--

LOCK TABLES `dijagnoze` WRITE;
/*!40000 ALTER TABLE `dijagnoze` DISABLE KEYS */;
INSERT INTO `dijagnoze` VALUES (3,'test1','test1',1),(4,'dijagnoza1','dijagnoza1opis',2),(6,'nova','novi',0),(8,'novadijag','novi',0),(9,'novadijag','novi',0),(10,'novad','novi',0),(11,'novad','novi',0),(12,'novadg','novi',0),(13,'novadga','novi',0),(14,'nd','novi',0),(15,'novd','novi',0),(16,'xx','novi',0),(17,'y','novi',0),(18,'yyy','novi',0),(19,'zzz','novi',0),(20,'kkk','novi',0),(21,'l','novi',0),(22,'p','novi',0),(23,'o','novi',0),(24,'u','novi',0),(25,'uk','novi',0),(26,'sklj','sklj',0);
/*!40000 ALTER TABLE `dijagnoze` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dijagnoze_simptomi`
--

DROP TABLE IF EXISTS `dijagnoze_simptomi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dijagnoze_simptomi` (
  `id` int(11) NOT NULL,
  `id_dijagnoze` int(11) NOT NULL,
  `id_simptoma` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idd_idx` (`id_dijagnoze`),
  KEY `ids_idx` (`id_simptoma`),
  CONSTRAINT `FKo9r0tgqlr0hdjuog1nnc7up2m` FOREIGN KEY (`id_simptoma`) REFERENCES `simptomi` (`id`),
  CONSTRAINT `FKqyye3yju58etlcru230ay2r5s` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dijagnoze_simptomi`
--

LOCK TABLES `dijagnoze_simptomi` WRITE;
/*!40000 ALTER TABLE `dijagnoze_simptomi` DISABLE KEYS */;
INSERT INTO `dijagnoze_simptomi` VALUES (203,20,3),(233,23,3),(234,23,4),(243,24,3),(244,24,4),(253,25,3),(254,25,4),(263,26,3),(264,26,4);
/*!40000 ALTER TABLE `dijagnoze_simptomi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `simptomi`
--

DROP TABLE IF EXISTS `simptomi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `simptomi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `simptomi`
--

LOCK TABLES `simptomi` WRITE;
/*!40000 ALTER TABLE `simptomi` DISABLE KEYS */;
INSERT INTO `simptomi` VALUES (1,'teeeest'),(2,'teeeest11'),(3,'s1'),(4,'s2');
/*!40000 ALTER TABLE `simptomi` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-04 19:29:39
