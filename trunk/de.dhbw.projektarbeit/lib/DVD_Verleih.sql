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
-- Table structure for table `agent`
--

DROP TABLE IF EXISTS `agent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agent` (
  `Personal_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  PRIMARY KEY (`Personal_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Agent''s Data\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agent`
--

LOCK TABLES `agent` WRITE;
/*!40000 ALTER TABLE `agent` DISABLE KEYS */;
/*!40000 ALTER TABLE `agent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `author`
--

DROP TABLE IF EXISTS `author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `author` (
  `Author_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Author_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Test','Test');
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
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Camera_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
INSERT INTO `camera` VALUES (1,'Test','Test');
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `Customer_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` text,
  `LastName` text,
  `ZIP_Code` varchar(5) DEFAULT NULL,
  `City` text,
  `Street` text,
  `StreetNo` varchar(10) DEFAULT NULL,
  `Email` varchar(45) DEFAULT NULL,
  `Telefone` varchar(45) DEFAULT NULL,
  `Birthdate` date DEFAULT NULL,
  PRIMARY KEY (`Customer_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='Customer Data';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (3,'Julian','Brunner','09934','Plochingen','Johanniterstr. ','20/2','h0nKi@gmx.de','0176/70902185','1986-11-22'),(4,'Romina','Möller','73207','Plochingen','Johanniterstr. ','20/2','romy_moeller@yahoo.de','07153/9243679','1988-02-29'),(5,'Nataliia','Makara','70174','Stuttgart','Holzgartenstr.','9a','nataliia.makara@gmail.com','01906666666','1986-07-25');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dvd`
--

DROP TABLE IF EXISTS `dvd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dvd` (
  `Quantity` tinyint(4) DEFAULT NULL,
  `Title` varchar(45) DEFAULT NULL,
  `Original_Title` varchar(45) DEFAULT NULL,
  `Genre` varchar(20) DEFAULT NULL,
  `Prod_Country` varchar(40) DEFAULT NULL,
  `Prod_Year` int(4) DEFAULT NULL,
  `Rel_Date` date DEFAULT NULL,
  `Duration` tinyint(4) DEFAULT NULL,
  `FSK` varchar(20) DEFAULT NULL,
  `Regie_ID` int(11) NOT NULL,
  `Author_ID` int(11) NOT NULL,
  `Production_ID` int(11) NOT NULL,
  `Camera_ID` int(11) NOT NULL,
  `Barcode` int(11) NOT NULL,
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
INSERT INTO `dvd` VALUES (127,'321','321','31','31',32,'2012-01-02',21,'321',11,1,2,1,1232),(1,'Title','OriginalTitle','Genre','USA',2012,'2012-10-25',1,'ab 0 Jahre',11,1,2,1,123123),(1,'Title','OriginalTitle','Genre','USA',2012,'2012-10-25',1,'ab 0 Jahre',11,1,2,1,123456789),(1,'Title','OriginalTitle','Genre','USA',2012,'2012-10-25',1,'ab 0 Jahre',11,1,2,1,999999999),(1,'Title','OriginalTitle','Genre','USA',2012,'2012-10-25',1,'ab 0 Jahre',11,1,2,1,1312312312);
/*!40000 ALTER TABLE `dvd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `price` (
  `Price_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Price1` double DEFAULT NULL,
  PRIMARY KEY (`Price_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `price`
--

LOCK TABLES `price` WRITE;
/*!40000 ALTER TABLE `price` DISABLE KEYS */;
/*!40000 ALTER TABLE `price` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `production`
--

DROP TABLE IF EXISTS `production`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `production` (
  `Production_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(45) DEFAULT NULL,
  `LastName` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`Production_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `production`
--

LOCK TABLES `production` WRITE;
/*!40000 ALTER TABLE `production` DISABLE KEYS */;
INSERT INTO `production` VALUES (2,'James','Cameron'),(3,'Jon','Landau');
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regisseur`
--

LOCK TABLES `regisseur` WRITE;
/*!40000 ALTER TABLE `regisseur` DISABLE KEYS */;
INSERT INTO `regisseur` VALUES (11,'Steven ','Spielberg');
/*!40000 ALTER TABLE `regisseur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental`
--

DROP TABLE IF EXISTS `rental`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental` (
  `Rental_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_ID` int(11) NOT NULL,
  `Personal_ID` int(11) NOT NULL,
  PRIMARY KEY (`Rental_ID`,`Customer_ID`),
  KEY `fk_rental_customer1_idx` (`Customer_ID`),
  KEY `fk_rental_agent_idx` (`Personal_ID`),
  CONSTRAINT `fk_rental_agent` FOREIGN KEY (`Personal_ID`) REFERENCES `agent` (`Personal_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rental_customer` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`Customer_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental`
--

LOCK TABLES `rental` WRITE;
/*!40000 ALTER TABLE `rental` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rental_pos`
--

DROP TABLE IF EXISTS `rental_pos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rental_pos` (
  `Pos_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Rental_ID` int(11) NOT NULL,
  `Barcode` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  PRIMARY KEY (`Pos_ID`,`Rental_ID`),
  KEY `fk_rental_pos_rental_idx` (`Rental_ID`),
  KEY `fk_rental_pos_dvd_idx` (`Barcode`),
  CONSTRAINT `fk_rental_pos_dvd` FOREIGN KEY (`Barcode`) REFERENCES `dvd` (`Barcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_rental_pos_rental` FOREIGN KEY (`Rental_ID`) REFERENCES `rental` (`Rental_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rental_pos`
--

LOCK TABLES `rental_pos` WRITE;
/*!40000 ALTER TABLE `rental_pos` DISABLE KEYS */;
/*!40000 ALTER TABLE `rental_pos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return`
--

DROP TABLE IF EXISTS `return`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return` (
  `Return_ID` int(11) NOT NULL AUTO_INCREMENT,
  `Customer_ID` int(11) NOT NULL,
  `Personal_ID` int(11) NOT NULL,
  PRIMARY KEY (`Return_ID`),
  KEY `fk_return_customer_idx` (`Customer_ID`),
  KEY `fk_return_agent_idx` (`Personal_ID`),
  CONSTRAINT `fk_return_agent` FOREIGN KEY (`Personal_ID`) REFERENCES `agent` (`Personal_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_return_customer` FOREIGN KEY (`Customer_ID`) REFERENCES `customer` (`Customer_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return`
--

LOCK TABLES `return` WRITE;
/*!40000 ALTER TABLE `return` DISABLE KEYS */;
/*!40000 ALTER TABLE `return` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_pos`
--

DROP TABLE IF EXISTS `return_pos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_pos` (
  `Pos_ID` tinyint(4) NOT NULL AUTO_INCREMENT,
  `Return_ID` int(11) NOT NULL,
  `Barcode` int(11) NOT NULL,
  `Price_ID` int(11) NOT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `Rental_period` varchar(45) DEFAULT NULL,
  `Discount` double DEFAULT NULL,
  PRIMARY KEY (`Pos_ID`,`Return_ID`),
  KEY `fk_return_pos_return_idx` (`Return_ID`),
  KEY `fk_return_pos_price_idx` (`Price_ID`),
  KEY `fk_return_pos_dvd_idx` (`Barcode`),
  CONSTRAINT `fk_return_pos_dvd` FOREIGN KEY (`Barcode`) REFERENCES `dvd` (`Barcode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_return_pos_price` FOREIGN KEY (`Price_ID`) REFERENCES `price` (`Price_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_return_pos_return` FOREIGN KEY (`Return_ID`) REFERENCES `return` (`Return_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_pos`
--

LOCK TABLES `return_pos` WRITE;
/*!40000 ALTER TABLE `return_pos` DISABLE KEYS */;
/*!40000 ALTER TABLE `return_pos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-10-26 11:32:51
