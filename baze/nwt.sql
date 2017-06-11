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
-- Database: `nwt`
--

-- --------------------------------------------------------

--
-- Table structure for table `dijagnoze`
--

DROP TABLE IF EXISTS `dijagnoze`;
CREATE TABLE IF NOT EXISTS `dijagnoze` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(45) NOT NULL,
  `opis` varchar(500) NOT NULL,
  `koristi` int(11) NOT NULL,
  `postotak` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dijagnoze`
--

INSERT INTO `dijagnoze` (`id`, `naziv`, `opis`, `koristi`, `postotak`) VALUES
(1, 'Prehlada', 'Upaljeno grlo povisena temperatura.  Nema drugih simptoma.', 0, '0'),
(2, 'Azbestoza', 'Azbestoza je poreme?aj disanja uzrokovan udisanjem azbestnih vlakana. Dugotrajna izloženost tim vlaknima u plu?ima može uzrokovati stvaranje ožiljaka i nedostatak zraka. Simptomi mogu varirati od blagih do teških, a obi?no se pojavljuju nakon mnogo godina izloženosti.', 0, '0'),
(3, 'Toksoplazmoza', 'Toksoplazmoza je u svijetu jedna od najraširenijih zoonoza (bolesti koje prenose životinje) uzrokovana protozoom Toxoplasma gondii. Bolest se može javljati u akutnom i kroni?nom obliku, a infekcija može pro?i i asimptomatski.', 0, '0'),
(4, 'Reumatoidni artritis', 'Reumatoidni artritis je kroni?no sistemsko oboljenje vezivnog tkiva, koje je naj?eš?e lokalizirano na zglobovima, ali i na miši?ima, koži, visceralnim organima i sistemima. Ova bolest zahva?a sve zglobove, osim kralježnice.', 0, '0'),
(5, 'Gnojna angina', 'Gnojna angina ili upala krajnika (tonzilitis) ?esta je bolest koja u velikoj mjeri poga?a malu djecu, ali i odrasle. Glavne karakteristike su bol u grlu i poteško?e s gutanjem. Pojavu ove infekcije naj?eš?e uvjetuje oslabljen imunitet. Rije? je o još jednom stanju ?iji se simptomi mogu ublažiti, ali koji prolazi sam od sebe, bez posebnog lije?enja.', 0, '0'),
(6, 'Upala srednjeg uha', 'Upala srednjeg uha jedna je od naj?eš?ih upala koje se dijagnosticiraju maloj djeci i lije?i se antibioticima. O uzrocima, simptomima i lije?enju ove upale kod djece možete više pro?itati na linku. Tako?er, pro?itajte kako izbje?i antibiotsku terapiju.', 0, '0'),
(7, 'Celijakija', 'Celijakija ili glutenska enteropatija nasljedni je imunološko-posredovani poreme?aj koji obilježava trajna nepodnošljivost glutena, proteina koji se nalazi u pšenici, je?mu, raži i zobi.\n\nZbog nepodnošenja glutena, tijelo stvara antitijela koja napadaju njegove molekule. Tako dolazi do ošte?enja sluznice tankog crijeva i uništenja crijevnih resica što otežava op?u apsorpciju hranjivih tvari. Celijakija je jedna od naj?eš?ih bolesti probavnog sustava.', 0, '0');

-- --------------------------------------------------------

--
-- Table structure for table `dijagnoze_pacijenti`
--

DROP TABLE IF EXISTS `dijagnoze_pacijenti`;
CREATE TABLE IF NOT EXISTS `dijagnoze_pacijenti` (
  `id` int(11) NOT NULL,
  `id_dijagnoze` int(11) NOT NULL,
  `id_pacijenta` int(11) NOT NULL,
  `datum_dijagnoze` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_dijagnoze_idx` (`id_dijagnoze`),
  KEY `id_pacijenta_idx` (`id_pacijenta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dijagnoze_pacijenti`
--

INSERT INTO `dijagnoze_pacijenti` (`id`, `id_dijagnoze`, `id_pacijenta`, `datum_dijagnoze`) VALUES
(11, 1, 1, '2017-05-01'),
(12, 2, 1, '2017-05-31'),
(16, 6, 1, '2017-05-31'),
(23, 3, 2, '2017-05-31'),
(25, 5, 2, '2017-05-31'),
(37, 7, 3, '2017-05-31'),
(46, 6, 4, '2017-05-31'),
(51, 1, 5, '2017-06-02'),
(84, 4, 8, '2017-05-31'),
(101, 1, 10, '2017-05-31'),
(106, 6, 10, '2017-06-02');

-- --------------------------------------------------------

--
-- Table structure for table `pacijenti`
--

DROP TABLE IF EXISTS `pacijenti`;
CREATE TABLE IF NOT EXISTS `pacijenti` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ime_prezime` varchar(45) NOT NULL,
  `spol` varchar(1) NOT NULL,
  `datum_rodjenja` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pacijenti`
--

INSERT INTO `pacijenti` (`id`, `ime_prezime`, `spol`, `datum_rodjenja`) VALUES
(1, 'Damir Fazlinovic', 'M', '2017-02-01'),
(2, 'Faruk Fazlinovic', 'M', '2017-02-14'),
(3, 'Izet Fazlinovic', 'M', '2017-03-09'),
(4, 'Dino Mehmeda Mujkic', 'M', '2017-05-02'),
(5, 'Samir Fazlinovic', 'M', '2017-05-03'),
(6, 'Fadil Opancic', 'M', '2017-05-09'),
(7, 'Bahra Opancic', 'M', '2017-01-23'),
(8, 'Frodo Bagins', 'M', '2017-05-02'),
(10, 'Edin Dzeko', 'M', '2017-05-23'),
(11, 'rrrrr', 'Ž', '2017-05-23');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dijagnoze_pacijenti`
--
ALTER TABLE `dijagnoze_pacijenti`
  ADD CONSTRAINT `FKg11w8ner7t0uwwmvp15jimdnx` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`),
  ADD CONSTRAINT `FKrxwgt6oh4y5u1n45c4lydokj6` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`),
  ADD CONSTRAINT `id_dijagnoze` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `id_pacijenta` FOREIGN KEY (`id_pacijenta`) REFERENCES `pacijenti` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
