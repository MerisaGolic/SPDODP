-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 05, 2017 at 03:31 PM
-- Server version: 5.7.9
-- PHP Version: 5.6.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `modul_dijagnoze`
--

-- --------------------------------------------------------

--
-- Table structure for table `dijagnoze`
--

DROP TABLE IF EXISTS `dijagnoze`;
CREATE TABLE IF NOT EXISTS `dijagnoze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  `opis` varchar(500) DEFAULT NULL,
  `postotak` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dijagnoze`
--

INSERT INTO `dijagnoze` (`id`, `naziv`, `opis`, `postotak`) VALUES
(1, 'Prehlada', 'Upaljeno grlo povisena temperatura.  Nema drugih simptoma.', 0),
(2, 'Azbestoza', 'Azbestoza je poreme?aj disanja uzrokovan udisanjem azbestnih vlakana. Dugotrajna izloženost tim vlaknima u plu?ima može uzrokovati stvaranje ožiljaka i nedostatak zraka. Simptomi mogu varirati od blagih do teških, a obi?no se pojavljuju nakon mnogo godina izloženosti.', 0),
(3, 'Toksoplazmoza', 'Toksoplazmoza je u svijetu jedna od najraširenijih zoonoza (bolesti koje prenose životinje) uzrokovana protozoom Toxoplasma gondii. Bolest se može javljati u akutnom i kroni?nom obliku, a infekcija može pro?i i asimptomatski.', 0),
(4, 'Reumatoidni artritis', 'Reumatoidni artritis je kroni?no sistemsko oboljenje vezivnog tkiva, koje je naj?eš?e lokalizirano na zglobovima, ali i na miši?ima, koži, visceralnim organima i sistemima. Ova bolest zahva?a sve zglobove, osim kralježnice.', 0),
(5, 'Gnojna angina', 'Gnojna angina ili upala krajnika (tonzilitis) ?esta je bolest koja u velikoj mjeri poga?a malu djecu, ali i odrasle. Glavne karakteristike su bol u grlu i poteško?e s gutanjem. Pojavu ove infekcije naj?eš?e uvjetuje oslabljen imunitet. Rije? je o još jednom stanju ?iji se simptomi mogu ublažiti, ali koji prolazi sam od sebe, bez posebnog lije?enja.', 0),
(6, 'Upala srednjeg uha', 'Upala srednjeg uha jedna je od naj?eš?ih upala koje se dijagnosticiraju maloj djeci i lije?i se antibioticima. O uzrocima, simptomima i lije?enju ove upale kod djece možete više pro?itati na linku. Tako?er, pro?itajte kako izbje?i antibiotsku terapiju.', 0),
(7, 'Celijakija', 'Celijakija ili glutenska enteropatija nasljedni je imunološko-posredovani poreme?aj koji obilježava trajna nepodnošljivost glutena, proteina koji se nalazi u pšenici, je?mu, raži i zobi.\n\nZbog nepodnošenja glutena, tijelo stvara antitijela koja napadaju njegove molekule. Tako dolazi do ošte?enja sluznice tankog crijeva i uništenja crijevnih resica što otežava op?u apsorpciju hranjivih tvari. Celijakija je jedna od naj?eš?ih bolesti probavnog sustava.', 0);

-- --------------------------------------------------------

--
-- Table structure for table `dijagnoze_simptomi`
--

DROP TABLE IF EXISTS `dijagnoze_simptomi`;
CREATE TABLE IF NOT EXISTS `dijagnoze_simptomi` (
  `id` int(11) NOT NULL,
  `id_dijagnoze` int(11) NOT NULL,
  `id_simptoma` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idd_idx` (`id_dijagnoze`),
  KEY `ids_idx` (`id_simptoma`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dijagnoze_simptomi`
--

INSERT INTO `dijagnoze_simptomi` (`id`, `id_dijagnoze`, `id_simptoma`) VALUES
(11, 1, 1),
(12, 1, 2),
(25, 2, 5),
(29, 2, 9),
(32, 3, 2),
(33, 3, 3),
(36, 3, 6),
(51, 5, 1),
(52, 5, 2),
(55, 5, 5),
(63, 6, 3),
(73, 7, 3),
(74, 7, 4),
(512, 5, 12),
(513, 5, 13),
(520, 5, 20),
(610, 6, 10),
(611, 6, 11),
(613, 6, 13),
(623, 6, 23),
(711, 7, 11),
(713, 7, 13),
(720, 7, 20),
(727, 7, 27);

-- --------------------------------------------------------

--
-- Table structure for table `simptomi`
--

DROP TABLE IF EXISTS `simptomi`;
CREATE TABLE IF NOT EXISTS `simptomi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `simptomi`
--

INSERT INTO `simptomi` (`id`, `naziv`) VALUES
(1, 'temperatura'),
(2, 'bolno grlo'),
(3, 'glavobolja'),
(4, 'umor'),
(5, 'kasalj'),
(6, 'upala misica'),
(7, 'zacepljen nos'),
(8, 'bol u ledjima'),
(9, 'bol u prsima'),
(10, 'vrtoglavica'),
(11, 'mucnina'),
(12, 'osip'),
(13, 'povracanje'),
(14, 'visok pritisak'),
(15, 'nizak pritisak'),
(16, 'depresija'),
(17, 'stucanje'),
(18, 'svrbez'),
(19, 'bol u koljenu'),
(20, 'gubitak apetita'),
(21, 'bol u vratu'),
(22, 'krvarenje iz nosa'),
(23, 'bol u uhu'),
(24, 'zubobolja'),
(25, 'utrnutost'),
(26, 'sljepoca'),
(27, 'gubitak tezine');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dijagnoze_simptomi`
--
ALTER TABLE `dijagnoze_simptomi`
  ADD CONSTRAINT `FKo9r0tgqlr0hdjuog1nnc7up2m` FOREIGN KEY (`id_simptoma`) REFERENCES `simptomi` (`id`),
  ADD CONSTRAINT `FKqyye3yju58etlcru230ay2r5s` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
