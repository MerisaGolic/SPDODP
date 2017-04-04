-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Apr 04, 2017 at 06:39 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `modulodredjivanjeterapije`
--

-- --------------------------------------------------------

--
-- Table structure for table `dijagnoze`
--

CREATE TABLE IF NOT EXISTS `dijagnoze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) NOT NULL,
  `opis` varchar(500) NOT NULL,
  `koristi` int(11) NOT NULL,
  `postotak` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `dijagnoze`
--

INSERT INTO `dijagnoze` (`id`, `naziv`, `opis`, `koristi`, `postotak`) VALUES
(1, 'Dijagnoza1', 'Novi opis dijagnoze1', 12, 5);

-- --------------------------------------------------------

--
-- Table structure for table `dijagnoze_lijekovi`
--

CREATE TABLE IF NOT EXISTS `dijagnoze_lijekovi` (
  `id` int(11) NOT NULL,
  `id_lijeka` int(11) NOT NULL,
  `id_dijagnoze` int(11) NOT NULL,
  `secer` double NOT NULL,
  `eritrociti` double NOT NULL,
  `leukociti` double NOT NULL,
  `trombociti` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_lijeka` (`id_lijeka`,`id_dijagnoze`),
  KEY `id_dijagnoze` (`id_dijagnoze`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `dijagnoze_lijekovi`
--

INSERT INTO `dijagnoze_lijekovi` (`id`, `id_lijeka`, `id_dijagnoze`, `secer`, `eritrociti`, `leukociti`, `trombociti`) VALUES
(71, 7, 1, 10, 300, 100, 200);

-- --------------------------------------------------------

--
-- Table structure for table `lijekovi`
--

CREATE TABLE IF NOT EXISTS `lijekovi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) NOT NULL,
  `standardna_doza` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `lijekovi`
--

INSERT INTO `lijekovi` (`id`, `naziv`, `standardna_doza`) VALUES
(7, 'LijekNovi', 15);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dijagnoze_lijekovi`
--
ALTER TABLE `dijagnoze_lijekovi`
  ADD CONSTRAINT `FK7kw0c964w4nw97kgmp9kf9jut` FOREIGN KEY (`id_lijeka`) REFERENCES `lijekovi` (`id`),
  ADD CONSTRAINT `dijagnoze_lijekovi_ibfk_1` FOREIGN KEY (`id_lijeka`) REFERENCES `lijekovi` (`id`),
  ADD CONSTRAINT `dijagnoze_lijekovi_ibfk_2` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`),
  ADD CONSTRAINT `FK5u2oksi072wdxxmr4pway478q` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`);
