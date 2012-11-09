CREATE DATABASE  IF NOT EXISTS `dvd_verleih` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dvd_verleih`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: localhost    Database: dvd_verleih
-- ------------------------------------------------------
-- Server version	5.5.8

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
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `Author_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Author_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS `camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camera` (
  `Camera_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Camera_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd`
--

DROP TABLE IF EXISTS `dvd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dvd` (
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
  KEY `fk_dvd_regiseur_idx` (`Regie_ID`),
  CONSTRAINT `fk_dvd_author` FOREIGN KEY (`Author_ID`) REFERENCES `author` (`Author_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dvd_camera` FOREIGN KEY (`Camera_ID`) REFERENCES `camera` (`Camera_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dvd_production` FOREIGN KEY (`Production_ID`) REFERENCES `production` (`Production_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_dvd_regiseur` FOREIGN KEY (`Regie_ID`) REFERENCES `regisseur` (`Regie_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 PACK_KEYS=1 COMMENT='DVD Information';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dvd`
--

LOCK TABLES `dvd` WRITE;
/*!40000 ALTER TABLE `dvd` DISABLE KEYS */;
/*!40000 ALTER TABLE `dvd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `production` (
  `Production_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Production_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
/*!40000 ALTER TABLE `production` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regisseur`
--

DROP TABLE IF EXISTS `regisseur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `regisseur` (
  `Regie_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Regie_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regisseur`
--

LOCK TABLES `regisseur` WRITE;
/*!40000 ALTER TABLE `regisseur` DISABLE KEYS */;
/*!40000 ALTER TABLE `regisseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'dvd_verleih'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-11-09 10:53:29
