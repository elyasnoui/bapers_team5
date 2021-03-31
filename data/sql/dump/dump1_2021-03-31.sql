-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 34.105.223.156    Database: bapers_db
-- ------------------------------------------------------
-- Server version	8.0.18-google

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'e1e7f8ff-5ce5-11eb-b4c0-42010a9a0053:1-1175864';

--
-- Table structure for table `availableTask`
--

DROP TABLE IF EXISTS `availableTask`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `availableTask` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `timeTaken` varchar(5) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availableTask`
--

LOCK TABLES `availableTask` WRITE;
/*!40000 ALTER TABLE `availableTask` DISABLE KEYS */;
INSERT INTO `availableTask` VALUES (1,'Use of a large copy camera','Copy room','120',19.00),(2,'Black and white film processing','Development area','60',49.50),(3,'Bagging Up','Packing','30',6.00),(4,'Colour film processing','Development area','90',80.00),(5,'Colour transparency processing','Development area','180',110.30),(6,'Use of a small copy camera','Copy room','75',8.30),(7,'Mount transparancies','Finishing room','45',55.50),(8,'Photo lamination','Finishing room','20',5.00),(10,'Test Row 1','Development area','10',15.00);
/*!40000 ALTER TABLE `availableTask` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `card`
--

DROP TABLE IF EXISTS `card`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `card` (
  `jobID` int(11) NOT NULL,
  `cardType` varchar(15) DEFAULT NULL,
  `expiryDate` varchar(8) DEFAULT NULL,
  `lastFourDigits` int(4) DEFAULT NULL,
  PRIMARY KEY (`jobID`),
  UNIQUE KEY `jobID` (`jobID`),
  CONSTRAINT `card_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `payment` (`jobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `card`
--

LOCK TABLES `card` WRITE;
/*!40000 ALTER TABLE `card` DISABLE KEYS */;
INSERT INTO `card` VALUES (1,'Visa Debit','12/23',4597),(3,'Visa Credit','08/26',9051),(4,'Visa Debit','11/24',6321),(6,'Mastercard','04/22',2193);
/*!40000 ALTER TABLE `card` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash`
--

DROP TABLE IF EXISTS `cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cash` (
  `jobID` int(11) NOT NULL,
  `cashPaid` decimal(5,2) DEFAULT NULL,
  `changeGiven` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`jobID`),
  UNIQUE KEY `jobID` (`jobID`),
  CONSTRAINT `cash_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `payment` (`jobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash`
--

LOCK TABLES `cash` WRITE;
/*!40000 ALTER TABLE `cash` DISABLE KEYS */;
INSERT INTO `cash` VALUES (2,15.00,0.70),(5,120.00,4.70);
/*!40000 ALTER TABLE `cash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(35) DEFAULT NULL,
  `title` varchar(17) DEFAULT NULL,
  `firstName` varchar(35) DEFAULT NULL,
  `lastName` varchar(35) DEFAULT NULL,
  `contactNumber` varchar(15) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'City, University of London','Prof','David','Rhind','02070408000','Northamptom Square, London, EC1V 0HB','David.Rhind@city.ac.uk'),(2,'AirVia Ltd','Mr','Boris','Berezovsky','02073218523','12, Bond Street, London, WC1V 8HU','Boris.B@yahoo.com'),(3,'InfoPharma Ltd','Mr','Alex','Wright','02073218001','25 Bond Street, London, WC1V 8LS','Alex.Wright@infopharma.com'),(4,'Hello Magazine','Ms','Sarah','Brocklehurst','02034567809','12 Charter Street, London, W1 8NS','Sarah.Brocklehurst@hello.com'),(5,'','Ms','Eva','Bauyer','02085558989','1, Liverpool Street, London, EC2V 8NS','eva.bauyer@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `isUrgent` int(1) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `customerID` (`customerID`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,0,122.21,'2021-02-21','2021-02-22','2021-02-22 00:05:55','Paid',1),(2,0,14.30,'2021-02-15','2021-02-16','2021-02-16 00:00:00','Paid',1),(3,1,190.41,'2021-01-14','2021-01-14','2021-01-14 00:00:00','Paid',2),(4,1,99.00,'2021-01-27','2021-01-27','2021-01-27 00:00:00','Paid',3),(5,0,115.30,'2020-11-02','2020-11-02','2020-11-02 00:00:00','Paid',4),(6,0,85.20,'2021-02-11','2021-02-12','2021-02-12 00:00:00','Paid',4),(12,0,127.21,'2021-03-28',NULL,'2021-04-01 00:00:00','Created',5),(13,0,50.30,'2021-02-21','2021-02-22','2021-02-22 00:00:00','complete',1);
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobReport`
--

DROP TABLE IF EXISTS `jobReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `jobReport` (
  `reportID` int(11) NOT NULL,
  `numberOfJobs` int(255) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`reportID`),
  UNIQUE KEY `reportID` (`reportID`),
  KEY `customerID` (`customerID`),
  CONSTRAINT `jobReport_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`ID`),
  CONSTRAINT `jobReport_ibfk_2` FOREIGN KEY (`customerID`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobReport`
--

LOCK TABLES `jobReport` WRITE;
/*!40000 ALTER TABLE `jobReport` DISABLE KEYS */;
INSERT INTO `jobReport` VALUES (3,4,1);
/*!40000 ALTER TABLE `jobReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_jobReport`
--

DROP TABLE IF EXISTS `job_jobReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job_jobReport` (
  `reportID` int(11) NOT NULL,
  `jobID` int(11) NOT NULL,
  `taskID` int(2) NOT NULL,
  `staffID` int(11) DEFAULT NULL,
  PRIMARY KEY (`reportID`,`jobID`,`taskID`),
  KEY `jobID` (`jobID`),
  KEY `taskID` (`taskID`),
  KEY `staffID` (`staffID`),
  CONSTRAINT `job_jobReport_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`ID`),
  CONSTRAINT `job_jobReport_ibfk_2` FOREIGN KEY (`jobID`) REFERENCES `task` (`jobID`),
  CONSTRAINT `job_jobReport_ibfk_3` FOREIGN KEY (`taskID`) REFERENCES `task` (`ID`),
  CONSTRAINT `job_jobReport_ibfk_4` FOREIGN KEY (`staffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_jobReport`
--

LOCK TABLES `job_jobReport` WRITE;
/*!40000 ALTER TABLE `job_jobReport` DISABLE KEYS */;
INSERT INTO `job_jobReport` VALUES (3,2,4,NULL),(3,2,5,NULL),(3,3,6,NULL),(3,3,7,NULL),(3,3,8,NULL),(3,5,11,NULL),(3,5,12,NULL),(3,6,13,NULL),(3,6,14,NULL),(3,6,15,NULL);
/*!40000 ALTER TABLE `job_jobReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `payment` (
  `jobID` int(11) NOT NULL,
  `amountDue` decimal(5,2) DEFAULT NULL,
  `isPaid` tinyint(1) DEFAULT NULL,
  `discount` decimal(5,2) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `paymentType` varchar(15) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  `staffID` int(11) DEFAULT NULL,
  PRIMARY KEY (`jobID`),
  UNIQUE KEY `jobID` (`jobID`),
  KEY `customerID` (`customerID`),
  KEY `staffID` (`staffID`),
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`ID`),
  CONSTRAINT `payment_ibfk_4` FOREIGN KEY (`customerID`) REFERENCES `customer` (`ID`),
  CONSTRAINT `payment_ibfk_5` FOREIGN KEY (`staffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment`
--

LOCK TABLES `payment` WRITE;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
INSERT INTO `payment` VALUES (1,122.21,1,1.79,'2021-03-05 17:20:23','Card',NULL,NULL),(2,14.30,1,0.00,'2021-03-05 17:20:23','Cash',NULL,NULL),(3,190.41,1,5.89,'2021-03-05 17:20:23','Card',NULL,NULL),(4,99.00,1,0.00,'2021-03-05 17:20:23','Card',NULL,NULL),(5,115.30,1,0.00,'2021-03-05 17:20:23','Cash',NULL,NULL),(6,79.20,1,0.00,'2021-03-05 17:20:23','Card',NULL,NULL);
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performanceReport`
--

DROP TABLE IF EXISTS `performanceReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performanceReport` (
  `reportID` int(11) NOT NULL,
  `numberOfStaff` int(4) DEFAULT NULL,
  PRIMARY KEY (`reportID`),
  UNIQUE KEY `reportID` (`reportID`),
  CONSTRAINT `performanceReport_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performanceReport`
--

LOCK TABLES `performanceReport` WRITE;
/*!40000 ALTER TABLE `performanceReport` DISABLE KEYS */;
INSERT INTO `performanceReport` VALUES (1,5);
/*!40000 ALTER TABLE `performanceReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `reportType` varchar(29) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `dateGenerated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'Performance Report',' ','2021-03-04 23:53:27','2021-01-21','2021-03-21'),(2,'Summary Report',' ','2021-03-04 23:53:27','2020-12-21','2021-04-21'),(3,'Customer Sales Report',' ','2021-03-04 23:53:27','2020-10-20','2021-03-21'),(4,'Job Report','','2021-03-07 01:32:19','2021-03-07','2021-04-04');
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(17) DEFAULT NULL,
  `firstName` varchar(35) DEFAULT NULL,
  `lastName` varchar(35) DEFAULT NULL,
  `contactNumber` varchar(15) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `role` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Mrs','Eloise','Powell','07824189936','78 Guild Street, London, EC1P 4ZJ','Eloise.Powell@bapers.com','Manager','ad288992ab3601747efc21c0751d0faa','Office Manager'),(2,'Mr','Billy','Baxter','07901023103','7 Crown Street, London, W3 2FD','Billy.Baxter@bapers.com','Accountant','a8e569334490b7f1d4617803b9337c4c','Shift Manager'),(3,'Mr','Sebastian','Simmons','07715203438','20 Queens Road, London, SE57 4UK','Sebastian.Simmons@bapers.com','Clerk','546ffe5e169c4fa44c88585f8c038f3e','Shift Manager'),(4,'Ms','Natasha','Osborne','07942814800','9005 Victoria Street, London, NW08 1QP','Natasha.Osborne@bapers.com','Hello','a58be4948f79302ffa49b3fb3bcaf384','Receptionist'),(5,'Mr','Samuel','Leonard','07854465691','651 Queen Street, London, N61 8UR','Samuel.Leonard@bapers.com','Development','68c1514765ac1f1d30bb3ae8e980712e','Technician in development area'),(6,'Mr','Harvey','Miah','07727795286','78 The Crescent, London, WC05 4AC','Harvey.Miah@bapers.com','Copy','26c4f1e4166a4010c0a733dcef8da7a8','Technician in copy room'),(7,'Mrs','Kiera','Howarth','07801468787','52 South Street, London, SE56 7IH','Kiera.Howarth@bapers.com','Packer','29a69924a517f2227d45861b2932f6e9','Technician in packing room'),(8,'Mr','Finley','Ross','07007303842','155 Highfield Road, London, WC75 6OX','Finley.Ross@bapers.com','Finish','36461fb8146868853e3e7c45fdceb21e','Technician in finishing room');
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff_performanceReport`
--

DROP TABLE IF EXISTS `staff_performanceReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff_performanceReport` (
  `reportID` int(11) NOT NULL,
  `staffID` int(11) DEFAULT NULL,
  `taskID` int(2) NOT NULL,
  `jobID` int(11) NOT NULL,
  PRIMARY KEY (`reportID`,`taskID`,`jobID`),
  KEY `staffID` (`staffID`),
  KEY `taskID` (`taskID`),
  KEY `jobID` (`jobID`),
  CONSTRAINT `staff_performanceReport_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`ID`),
  CONSTRAINT `staff_performanceReport_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`ID`),
  CONSTRAINT `staff_performanceReport_ibfk_3` FOREIGN KEY (`taskID`) REFERENCES `task` (`ID`),
  CONSTRAINT `staff_performanceReport_ibfk_4` FOREIGN KEY (`jobID`) REFERENCES `task` (`jobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff_performanceReport`
--

LOCK TABLES `staff_performanceReport` WRITE;
/*!40000 ALTER TABLE `staff_performanceReport` DISABLE KEYS */;
INSERT INTO `staff_performanceReport` VALUES (1,NULL,1,1),(1,NULL,1,4),(1,NULL,2,1),(1,NULL,2,6),(1,NULL,3,2),(1,NULL,3,3),(1,NULL,4,3),(1,NULL,4,4),(1,NULL,5,3),(1,NULL,5,5),(1,NULL,6,2),(1,NULL,6,6),(1,NULL,7,1),(1,NULL,7,6),(1,NULL,8,5);
/*!40000 ALTER TABLE `staff_performanceReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `summaryReport`
--

DROP TABLE IF EXISTS `summaryReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `summaryReport` (
  `reportID` int(11) NOT NULL,
  `numberOfTasks` int(4) DEFAULT NULL,
  PRIMARY KEY (`reportID`),
  UNIQUE KEY `reportID` (`reportID`),
  CONSTRAINT `summaryReport_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `summaryReport`
--

LOCK TABLES `summaryReport` WRITE;
/*!40000 ALTER TABLE `summaryReport` DISABLE KEYS */;
INSERT INTO `summaryReport` VALUES (2,7),(4,1);
/*!40000 ALTER TABLE `summaryReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `availableTaskID` int(11) DEFAULT NULL,
  `jobID` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `department` varchar(50) DEFAULT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `timeTaken` varchar(5) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  `staffID` int(11) DEFAULT NULL,
  `isCompleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `jobID` (`jobID`),
  KEY `staffID` (`staffID`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`ID`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (1,1,1,'Use of a large copy camera','Copy room','2021-02-21 05:10:00','80',19.00,6,1),(2,2,1,'Black and white film processing','Development area','2021-02-21 07:32:00','60',49.50,5,1),(3,7,1,'Mount transparancies','Finishing room','2021-02-22 09:10:00','45',55.50,8,1),(4,6,2,'Use of a small copy camera','Copy room','2021-02-16 10:50:00','75',8.30,6,1),(5,3,2,'Bagging Up','Packing departments','2021-02-16 13:40:00','30',6.00,7,1),(6,4,3,'Colour film processing','Development area','2021-01-14 14:54:00','90',80.00,5,1),(7,5,3,'Colour transparency processing','Development area','2021-01-14 15:24:00','180',110.30,5,1),(8,3,3,'Bagging Up','Packing departments','2021-01-14 16:00:00','30',6.00,7,1),(9,1,4,'Use of a large copy camera','Copy room','2021-01-27 19:00:00','70',19.00,6,1),(10,4,4,'Colour film processing','Development area','2021-01-27 21:00:00','100',80.00,5,1),(11,5,5,'Colour transparency processing','Development area','2020-11-02 21:34:00','190',110.30,5,1),(12,8,5,'Photo lamination','Finishing room','2020-11-02 22:46:00','20',5.00,8,1),(13,6,6,'Use of a small copy camera','Copy room','2021-02-11 00:04:00','75',6.00,6,1),(14,2,6,'Black and white film processing','Development area','2021-02-12 00:54:00','70',19.00,5,1),(15,7,6,'Mount transparancies','Finishing room','2021-02-12 03:47:00','50',55.50,8,1),(23,8,12,'Photo lamination','Finishing room','2021-03-28 04:35:02','20',5.00,8,0);
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_summaryReport`
--

DROP TABLE IF EXISTS `task_summaryReport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_summaryReport` (
  `reportID` int(11) NOT NULL,
  `taskID` int(2) NOT NULL,
  `jobID` int(11) NOT NULL,
  PRIMARY KEY (`reportID`,`taskID`,`jobID`),
  KEY `taskID` (`taskID`),
  KEY `jobID` (`jobID`),
  CONSTRAINT `task_summaryReport_ibfk_1` FOREIGN KEY (`reportID`) REFERENCES `report` (`ID`),
  CONSTRAINT `task_summaryReport_ibfk_2` FOREIGN KEY (`taskID`) REFERENCES `task` (`ID`),
  CONSTRAINT `task_summaryReport_ibfk_3` FOREIGN KEY (`jobID`) REFERENCES `task` (`jobID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_summaryReport`
--

LOCK TABLES `task_summaryReport` WRITE;
/*!40000 ALTER TABLE `task_summaryReport` DISABLE KEYS */;
INSERT INTO `task_summaryReport` VALUES (2,2,2),(2,2,6),(2,3,2),(2,3,3),(2,4,3),(2,5,3),(2,6,6),(2,7,6);
/*!40000 ALTER TABLE `task_summaryReport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `valuedCustomer`
--

DROP TABLE IF EXISTS `valuedCustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `valuedCustomer` (
  `customerID` int(11) NOT NULL,
  `agreedDiscount` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `discountRate` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `customerID` (`customerID`),
  CONSTRAINT `valuedCustomer_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `valuedCustomer`
--

LOCK TABLES `valuedCustomer` WRITE;
/*!40000 ALTER TABLE `valuedCustomer` DISABLE KEYS */;
INSERT INTO `valuedCustomer` VALUES (1,'Fixed Discount','3'),(2,'Variable Discount','1-1,2-1,3-0,4-2,5-2,6-0,7-2,8-0,10-0'),(3,'Flexible Discount','1-0,2-1,3-2'),(4,'Flexible Discount','1-0,2-1,3-2'),(5,'Fixed Discount','3');
/*!40000 ALTER TABLE `valuedCustomer` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-31 22:59:54
