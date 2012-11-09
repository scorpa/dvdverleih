-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 09, 2012 at 12:20 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dvd_verleih`
--

-- --------------------------------------------------------

--
-- Table structure for table `author`
--

CREATE TABLE IF NOT EXISTS `author` (
  `Author_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Author_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `author`
--

INSERT INTO `author` (`Author_ID`, `FirstName`, `LastName`) VALUES
(1, 'David', 'Latt'),
(2, 'Jonah', 'Hill'),
(3, 'Drew', 'Goddard'),
(4, 'Patrick', 'Marber'),
(5, 'Rolland', 'Emmerich');

-- --------------------------------------------------------

--
-- Table structure for table `camera`
--

CREATE TABLE IF NOT EXISTS `camera` (
  `Camera_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Camera_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `camera`
--

INSERT INTO `camera` (`Camera_ID`, `FirstName`, `LastName`) VALUES
(1, 'Adam', 'Silver'),
(2, 'Barry', 'Peterson'),
(3, 'Peter', 'Deming'),
(4, 'Roger', 'Deakins'),
(5, 'Dean', 'Semler');

-- --------------------------------------------------------

--
-- Table structure for table `dvd`
--

CREATE TABLE IF NOT EXISTS `dvd` (
  `Quantity` tinyint(4) DEFAULT NULL,
  `Title` text,
  `Original_Title` text,
  `Genre` text,
  `Prod_Country` text,
  `Prod_Year` int(4) DEFAULT NULL,
  `Rel_Date` date DEFAULT NULL,
  `Duration` int(4) DEFAULT NULL,
  `FSK` varchar(20) DEFAULT NULL,
  `Regie_ID` int(11) NOT NULL,
  `Author_ID` int(11) NOT NULL,
  `Production_ID` int(11) NOT NULL,
  `Camera_ID` int(11) NOT NULL,
  `Barcode` varchar(13) NOT NULL,
  PRIMARY KEY (`Barcode`),
  KEY `fk_dvd_author_idx` (`Author_ID`),
  KEY `fk_dvd_camera_idx` (`Camera_ID`),
  KEY `fk_dvd_production_idx` (`Production_ID`),
  KEY `fk_dvd_regiseur_idx` (`Regie_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=1 COMMENT='DVD Information';

--
-- Dumping data for table `dvd`
--

INSERT INTO `dvd` (`Quantity`, `Title`, `Original_Title`, `Genre`, `Prod_Country`, `Prod_Year`, `Rel_Date`, `Duration`, `FSK`, `Regie_ID`, `Author_ID`, `Production_ID`, `Camera_ID`, `Barcode`) VALUES
(1, 'James Bond 007: Skyfall', 'Skyfall', 'Thriller, Action', 'USA', 2012, '2012-10-31', 143, 'ab 12 Jahre', 4, 4, 4, 4, '1245678354456'),
(2, '2012', '2012', 'Science Fiction', 'USA', 2012, '2009-11-13', 158, 'ab 16 Jahre', 5, 5, 5, 5, '145276387245'),
(3, 'The Cabin in the Woods', 'The Cabin in the Woods', 'horror', 'USA', 2012, '2012-04-13', 95, 'ab 16 Jahre', 3, 3, 3, 3, '1863542345634'),
(1, '21 Jump Street', '21 Jump Street (USA 2012)', 'Action,Komödie', 'USA', 2012, '2012-09-13', 105, 'ab 12 Jahre', 2, 2, 2, 2, '4030521727021'),
(1, 'Armageddon', 'The Apocalypse', 'Action', 'USA', 2011, '2011-09-22', 90, 'ab 16 Jahre', 1, 1, 1, 1, '40512380048');

-- --------------------------------------------------------

--
-- Table structure for table `production`
--

CREATE TABLE IF NOT EXISTS `production` (
  `Production_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Production_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `production`
--

INSERT INTO `production` (`Production_ID`, `FirstName`, `LastName`) VALUES
(1, 'Paul', 'Bales'),
(2, 'Jonah', 'Hill'),
(3, 'Jason', 'Clark'),
(4, 'Callum', 'McDougall'),
(5, 'Harald', 'Kloser');

-- --------------------------------------------------------

--
-- Table structure for table `regisseur`
--

CREATE TABLE IF NOT EXISTS `regisseur` (
  `Regie_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Regie_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `regisseur`
--

INSERT INTO `regisseur` (`Regie_ID`, `FirstName`, `LastName`) VALUES
(1, 'Justin', 'Jones'),
(2, 'Chris', 'Miller'),
(3, 'Drew', 'Goddard'),
(4, 'Sam', 'Mendes'),
(5, 'Roland', 'Emmerich');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `dvd`
--
ALTER TABLE `dvd`
  ADD CONSTRAINT `fk_dvd_author` FOREIGN KEY (`Author_ID`) REFERENCES `author` (`Author_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_dvd_camera` FOREIGN KEY (`Camera_ID`) REFERENCES `camera` (`Camera_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_dvd_production` FOREIGN KEY (`Production_ID`) REFERENCES `production` (`Production_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_dvd_regiseur` FOREIGN KEY (`Regie_ID`) REFERENCES `regisseur` (`Regie_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
