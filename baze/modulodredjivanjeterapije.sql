-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 11, 2017 at 02:52 PM
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `dijagnoze`
--

INSERT INTO `dijagnoze` (`id`, `naziv`, `opis`, `koristi`, `postotak`) VALUES
(1, 'Prehlada', 'Upaljeno grlo povišena temperatura.  Nema drugih simptoma.', 0, 0),
(2, 'Azbestoza', 'Azbestoza je poremećaj disanja uzrokovan udisanjem azbestnih vlakana. Dugotrajna izloženost tim vlaknima u plućima može uzrokovati stvaranje ožiljaka i nedostatak zraka. Simptomi mogu varirati od blagih do teških, a obično se pojavljuju nakon mnogo godina izloženosti.', 0, 0),
(3, 'Toksoplazmoza', 'Toksoplazmoza je u svijetu jedna od najraširenijih zoonoza (bolesti koje prenose životinje) uzrokovana protozoom Toxoplasma gondii. Bolest se može javljati u akutnom i kroničnom obliku, a infekcija može proći i asimptomatski.', 0, 0),
(4, 'Reumatoidni artritis', 'Reumatoidni artritis je kronično sistemsko oboljenje vezivnog tkiva, koje je najčešće lokalizirano na zglobovima, ali i na mišićima, koži, visceralnim organima i sistemima. Ova bolest zahvaća sve zglobove, osim kralježnice.', 0, 0),
(5, 'Gnojna angina', 'Gnojna angina ili upala krajnika (tonzilitis) česta je bolest koja u velikoj mjeri pogađa malu djecu, ali i odrasle. Glavne karakteristike su bol u grlu i poteškoće s gutanjem. Pojavu ove infekcije najčešće uvjetuje oslabljen imunitet. Riječ je o još jednom stanju čiji se simptomi mogu ublažiti, ali koji prolazi sam od sebe, bez posebnog liječenja.', 0, 0),
(6, 'Upala srednjeg uha', 'Upala srednjeg uha jedna je od najčešćih upala koje se dijagnosticiraju maloj djeci i liječi se antibioticima. O uzrocima, simptomima i liječenju ove upale kod djece možete više pročitati na linku. Također, pročitajte kako izbjeći antibiotsku terapiju.', 0, 0),
(7, 'Celijakija', 'Celijakija ili glutenska enteropatija nasljedni je imunološko-posredovani poremećaj koji obilježava trajna nepodnošljivost glutena, proteina koji se nalazi u pšenici, ječmu, raži i zobi.\r\n\r\nZbog nepodnošenja glutena, tijelo stvara antitijela koja napadaju njegove molekule. Tako dolazi do oštećenja sluznice tankog crijeva i uništenja crijevnih resica što otežava opću apsorpciju hranjivih tvari. Celijakija je jedna od najčešćih bolesti probavnog sustava.', 0, 0);

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
(11, 1, 1, 4, 5, 5, 200),
(23, 2, 3, 0, 0, 0, 0),
(36, 3, 6, 4, 5, 5, 200),
(45, 4, 5, 0, 0, 0, 0),
(57, 5, 7, 0, 0, 0, 0),
(64, 6, 4, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `lijekovi`
--

CREATE TABLE IF NOT EXISTS `lijekovi` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(20) NOT NULL,
  `standardna_doza` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Dumping data for table `lijekovi`
--

INSERT INTO `lijekovi` (`id`, `naziv`, `standardna_doza`) VALUES
(1, 'Sirup', 0),
(2, 'Pirimetamin', 3),
(3, 'Ephedrin(antibiotik)', 2),
(4, 'Penicilin(antibiotik', 0),
(5, 'Bezglutenska hrana', 0),
(6, 'Kortikosteroidi', 5),
(7, 'cgcghc', 6);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dijagnoze_lijekovi`
--
ALTER TABLE `dijagnoze_lijekovi`
  ADD CONSTRAINT `FK5u2oksi072wdxxmr4pway478q` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`),
  ADD CONSTRAINT `FK7kw0c964w4nw97kgmp9kf9jut` FOREIGN KEY (`id_lijeka`) REFERENCES `lijekovi` (`id`),
  ADD CONSTRAINT `dijagnoze_lijekovi_ibfk_1` FOREIGN KEY (`id_lijeka`) REFERENCES `lijekovi` (`id`),
  ADD CONSTRAINT `dijagnoze_lijekovi_ibfk_2` FOREIGN KEY (`id_dijagnoze`) REFERENCES `dijagnoze` (`id`);
