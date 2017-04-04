-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 03, 2017 at 06:39 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `modulzakorisnikedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnici`
--

DROP TABLE IF EXISTS `korisnici`;
CREATE TABLE IF NOT EXISTS `korisnici` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) COLLATE utf8_bin NOT NULL,
  `password` varchar(10) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `korisnici`
--

INSERT INTO `korisnici` (`id`, `username`, `password`) VALUES
(1, 'anogo', 'anogo'),
(2, 'mgolic', 'mgolic'),
(3, 'mmujic', 'mmujic'),
(4, 'ssuljic', 'ssuljic');

-- --------------------------------------------------------

--
-- Table structure for table `pacijenti`
--

DROP TABLE IF EXISTS `pacijenti`;
CREATE TABLE IF NOT EXISTS `pacijenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime_prezime` varchar(50) COLLATE utf8_bin NOT NULL,
  `spol` varchar(1) COLLATE utf8_bin NOT NULL,
  `datum_rodjenja` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `pacijenti`
--

INSERT INTO `pacijenti` (`id`, `ime_prezime`, `spol`, `datum_rodjenja`) VALUES
(1, 'Meho Mehic', 'm', '2017-03-01'),
(2, 'Mirnes Peljto', 'm', '1994-05-15'),
(3, 'Senad Peljto', 'm', '1983-05-15');

-- --------------------------------------------------------

--
-- Table structure for table `pacijenti_korisnici`
--

DROP TABLE IF EXISTS `pacijenti_korisnici`;
CREATE TABLE IF NOT EXISTS `pacijenti_korisnici` (
  `id` int(11) NOT NULL,
  `id_pacijenta` int(11) NOT NULL,
  `id_korisnika` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_pacijenta` (`id_pacijenta`),
  KEY `id_korisnika` (`id_korisnika`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `pacijenti_korisnici`
--

INSERT INTO `pacijenti_korisnici` (`id`, `id_pacijenta`, `id_korisnika`) VALUES
(12, 2, 1),
(13, 3, 1),
(43, 3, 4);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pacijenti_korisnici`
--
ALTER TABLE `pacijenti_korisnici`
  ADD CONSTRAINT `FK8vgk4ebug1gyqec6e30ae46jy` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`),
  ADD CONSTRAINT `FKcxneb4kyne7qc2rurtak0mfgr` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`),
  ADD CONSTRAINT `pacijenti_korisnici_ibfk_1` FOREIGN KEY (`id_korisnika`) REFERENCES `korisnici` (`id`),
  ADD CONSTRAINT `pacijenti_korisnici_ibfk_2` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
