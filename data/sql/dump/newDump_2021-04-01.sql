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

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'e1e7f8ff-5ce5-11eb-b4c0-42010a9a0053:1-1190375';

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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `availableTask`
--

LOCK TABLES `availableTask` WRITE;
/*!40000 ALTER TABLE `availableTask` DISABLE KEYS */;
INSERT INTO `availableTask` VALUES (1,'Use of a large copy camera','Copy room','120',19.00),(2,'Black and white film processing','Development area','60',49.50),(3,'Bagging Up','Packing','30',6.00),(4,'Colour film processing','Development area','90',80.00),(5,'Colour transparency processing','Development area','180',110.30),(6,'Use of a small copy camera','Copy room','75',8.30),(7,'Mount transparancies','Finishing room','45',55.50),(8,'Photo lamination','Finishing room','20',5.00);
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
  `cardType` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
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
INSERT INTO `card` VALUES (33,'Visa Debit Card','04/2021',564),(36,'Visa Debit Card','11/2021',7837);
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
  `cashPaid` decimal(9,2) DEFAULT NULL,
  `changeGiven` decimal(9,2) DEFAULT NULL,
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
INSERT INTO `cash` VALUES (34,3000.00,668.19),(37,750.00,131.52),(40,30.00,3.00),(2019,200.00,26.26);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'City, University of London','Prof','David','Rhind','02070408000','Northamptom Square, London, EC1V 0HB','David.Rhind@city.ac.uk'),(2,'AirVia Ltd','Mr','Boris','Berezovsky','02073218523','12, Bond Street, London, WC1V 8HU','Boris.B@yahoo.com'),(3,'InfoPharma Ltd','Mr','Alex','Wright','02073218001','25 Bond Street, London, WC1V 8LS','Alex.Wright@infopharma.com'),(4,'Hello Magazine','Ms','Sarah','Brocklehurst','02034567809','12 Charter Street, London, W1 8NS','Sarah.Brocklehurst@hello.com'),(5,'','Ms','Eva','Bauyer','02085558989','1, Liverpool Street, London, EC2V 8NS','eva.bauyer@gmail.com'),(6,'','Mr','Ivan','Grey','07443235456','12 Apple Street, London, W2 4OP','Ivan.Grey@email.com'),(7,'','Mr','Boris','Bridge','07684748757','12 Pineapple Street, London, EC2 4JF','Boris.Bridge@email.com'),(8,'','Mr','TestName','TestSurname','07349548654','Line1, London, NW3 5HT','TestName.TestSurname@email.com'),(9,'','Mr','TestName','TestSurname','07349548654','Line1, London, NW3 5HT','TestName.TestSurname@email.com');
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
  `price` decimal(9,2) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `deadline` datetime DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  `customerID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `customerID` (`customerID`),
  CONSTRAINT `job_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2022 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (33,0,715.00,'2021-04-01','2021-04-01','2021-04-04 13:47:00','Paid',1),(34,0,1993.00,'2021-04-01','2021-04-01','2021-04-08 13:48:00','Paid',1),(35,0,2155.00,'2021-04-01','2021-04-01','2021-04-05 13:51:00','Paid',4),(36,1,497.40,'2021-04-01','2021-04-01','2021-04-02 01:53:00','Paid',3),(37,0,515.40,'2021-04-01','2021-04-01','2021-04-03 13:53:00','Paid',7),(38,0,1803.00,'2021-04-01','2021-04-01','2021-04-06 13:54:00','Paid',4),(39,2,246.00,'2021-04-01','2021-04-01','2021-04-01 22:26:00','Created',5),(40,1,6.00,'2021-04-01','2021-04-01','2021-04-01 00:00:00','Paid',7),(41,0,49.50,'2021-04-01','2021-04-01','2021-05-10 00:00:00','Paid',2),(42,0,99.60,'2021-04-01','2021-04-01','2021-04-03 14:36:00','Created',1),(43,1,8.30,'2021-04-01','2021-04-01','2021-04-01 16:37:00','Created',8),(44,2,57.80,'2021-04-01','2021-04-01','2021-04-01 19:37:00','Created',4),(2017,0,704.50,'2021-04-01',NULL,'2021-04-03 17:13:00','Created',2),(2018,0,297.00,'2021-04-01',NULL,'2021-04-02 17:37:00','Created',1),(2019,0,148.50,'2021-04-01',NULL,'2021-04-02 17:45:00','Paid',1),(2020,0,12.00,'2021-04-01',NULL,'2021-04-02 17:48:00','Paid',7),(2021,2,365.60,'2021-04-01',NULL,'2021-04-02 17:54:00','Unpaid',2);
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
INSERT INTO `jobReport` VALUES (3,4,4);
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
  `discount` decimal(9,2) DEFAULT NULL,
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
INSERT INTO `payment` VALUES (33,1.00,1.79,'2021-04-01 14:37:11','Card',4,1),(34,1.00,0.00,'2021-04-01 14:37:11','Cash',2,2),(35,1.00,5.89,'2021-04-01 14:37:11','Card',9,1),(36,1.00,0.00,'2021-04-01 14:37:11','Card',1,3),(37,1.00,0.00,'2021-04-01 14:37:11','Cash',7,2),(38,1.00,0.00,'2021-04-01 14:37:11','Cash',2,4),(40,27.00,0.00,'2021-04-01 14:42:24','Cash',7,4),(2019,173.74,4.46,'2021-04-01 16:47:30','Cash',1,4),(2020,14.40,0.00,'2021-04-01 16:50:25','Card',7,4);
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
  `reportType` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `dateGenerated` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1992 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
INSERT INTO `report` VALUES (1,'Performance Report',' ','2021-04-01 14:20:17','2015-03-21','2021-04-21'),(2,'Summary Report',' ','2021-04-01 14:20:17','2015-03-21','2021-04-21'),(3,'Job Report',' ','2021-04-01 14:20:17','2015-03-21','2021-04-21'),(4,'Customer Sales','','2021-04-01 14:20:17','2015-03-21','2021-04-21');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (1,'Mrs','Eloise','Powell','07824189936','78 Guild Street, London, EC1P 4ZJ','Eloise.Powell@bapers.com','Manager','ad288992ab3601747efc21c0751d0faa','Office Manager'),(2,'Mr','Billy','Baxter','07901023103','7 Crown Street, London, W3 2FD','Billy.Baxter@bapers.com','Accountant','a8e569334490b7f1d4617803b9337c4c','Shift Manager'),(3,'Mr','Sebastian','Simmons','07715203438','20 Queens Road, London, SE57 4UK','Sebastian.Simmons@bapers.com','Clerk','546ffe5e169c4fa44c88585f8c038f3e','Shift Manager'),(4,'Ms','Natasha','Osborne','07942814800','9005 Victoria Street, London, NW08 1QP','Natasha.Osborne@bapers.com','Hello','a58be4948f79302ffa49b3fb3bcaf384','Receptionist'),(5,'Mr','Samuel','Leonard','07854465691','651 Queen Street, London, N61 8UR','Samuel.Leonard@bapers.com','Development','68c1514765ac1f1d30bb3ae8e980712e','Technician (development)'),(6,'Mr','Harvey','Miah','07727795286','78 The Crescent, London, WC05 4AC','Harvey.Miah@bapers.com','Copy','26c4f1e4166a4010c0a733dcef8da7a8','Technician (copy)'),(7,'Mrs','Kiera','Howarth','07801468787','52 South Street, London, SE56 7IH','Kiera.Howarth@bapers.com','Packer','29a69924a517f2227d45861b2932f6e9','Technician (packing)'),(8,'Mr','Finley','Ross','07007303842','155 Highfield Road, London, WC75 6OX','Finley.Ross@bapers.com','Finish','36461fb8146868853e3e7c45fdceb21e','Technician (finishing)'),(9,'Mr','NewTechnician','Tecxh','06546453566','Line1, London, U7 6JW','a@a.com','NewTechnician','dc647eb65e6711e155375218212b3964','Receptionist');
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
INSERT INTO `summaryReport` VALUES (2,7);
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
  `price` decimal(6,2) DEFAULT NULL,
  `staffID` int(11) DEFAULT NULL,
  `isCompleted` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `jobID` (`jobID`),
  KEY `staffID` (`staffID`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`jobID`) REFERENCES `job` (`ID`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=432 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES (192,1,33,'Use of a large copy camera','Copy room','2021-04-01 12:47:22','120',19.00,6,1),(262,6,34,'Use of a small copy camera','Copy room','2021-04-01 12:49:39','75',8.30,6,1),(269,2,35,'Black and white film processing','Development area','2021-04-01 12:51:46','60',49.50,5,1),(271,2,35,'Black and white film processing','Development area','2021-04-01 12:51:47','60',49.50,5,1),(277,2,35,'Black and white film processing','Development area','2021-04-01 12:51:51','60',49.50,5,1),(279,3,35,'Bagging Up','Packing','2021-04-01 12:51:52','30',6.00,7,1),(282,3,35,'Bagging Up','Packing','2021-04-01 12:51:54','30',6.00,7,1),(283,3,35,'Bagging Up','Packing','2021-04-01 12:51:55','30',6.00,7,1),(284,3,35,'Bagging Up','Packing','2021-04-01 12:51:55','30',6.00,7,1),(298,4,35,'Colour film processing','Development area','2021-04-01 12:52:03','90',80.00,5,1),(325,3,38,'Bagging Up','Packing','2021-04-01 12:54:45','30',6.00,7,1),(326,3,38,'Bagging Up','Packing','2021-04-01 12:54:45','30',6.00,7,1),(327,3,38,'Bagging Up','Packing','2021-04-01 12:54:46','30',6.00,7,1),(328,3,38,'Bagging Up','Packing','2021-04-01 12:54:47','30',6.00,7,1),(395,6,44,'Use of a small copy camera','Copy room','2021-04-01 13:37:30','75',8.30,6,0),(396,2,44,'Black and white film processing','Development area','2021-04-01 13:37:30','60',49.50,5,0),(397,2,2017,'Black and white film processing','Development area','2021-04-01 16:13:48','60',49.50,5,1),(398,2,2017,'Black and white film processing','Development area','2021-04-01 16:13:48','60',49.50,5,1),(399,2,2017,'Black and white film processing','Development area','2021-04-01 16:13:48','60',49.50,5,1),(400,4,2017,'Colour film processing','Development area','2021-04-01 16:13:48','90',80.00,5,1),(401,4,2017,'Colour film processing','Development area','2021-04-01 16:13:48','90',80.00,5,1),(402,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:33','60',49.50,7,0),(403,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:56','60',49.50,5,1),(404,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:57','60',49.50,5,1),(405,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:57','60',49.50,5,1),(406,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:57','60',49.50,5,1),(407,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:57','60',49.50,5,1),(408,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:57','60',49.50,5,1),(409,2,2017,'Black and white film processing','Development area','2021-04-01 16:28:57','60',49.50,5,1),(410,2,2018,'Black and white film processing','Development area','2021-04-01 16:37:53','60',49.50,5,0),(411,2,2018,'Black and white film processing','Development area','2021-04-01 16:37:54','60',49.50,5,0),(412,2,2018,'Black and white film processing','Development area','2021-04-01 16:37:54','60',49.50,5,1),(413,2,2018,'Black and white film processing','Development area','2021-04-01 16:37:54','60',49.50,5,1),(414,2,2018,'Black and white film processing','Development area','2021-04-01 16:37:54','60',49.50,5,1),(415,2,2018,'Black and white film processing','Development area','2021-04-01 16:37:54','60',49.50,5,1),(416,2,2019,'Black and white film processing','Development area','2021-04-01 16:45:26','60',49.50,5,1),(417,2,2019,'Black and white film processing','Development area','2021-04-01 16:45:26','60',49.50,5,1),(418,2,2019,'Black and white film processing','Development area','2021-04-01 16:45:26','60',49.50,5,1),(419,3,2020,'Bagging Up','Packing','2021-04-01 16:48:21','30',6.00,7,1),(420,3,2020,'Bagging Up','Packing','2021-04-01 16:48:21','30',6.00,7,1),(421,3,2021,'Bagging Up','Packing','2021-04-01 16:54:33','30',6.00,7,0),(422,3,2021,'Bagging Up','Packing','2021-04-01 16:54:34','30',6.00,7,0),(423,5,2021,'Colour transparency processing','Development area','2021-04-01 16:54:34','180',110.30,5,0),(424,5,2021,'Colour transparency processing','Development area','2021-04-01 16:54:34','180',110.30,5,0),(425,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:34','120',19.00,6,0),(426,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:34','120',19.00,6,0),(427,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:34','120',19.00,6,0),(428,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:34','120',19.00,6,0),(429,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:34','120',19.00,6,0),(430,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:35','120',19.00,6,0),(431,1,2021,'Use of a large copy camera','Copy room','2021-04-01 16:54:35','120',19.00,6,0);
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
INSERT INTO `valuedCustomer` VALUES (1,'Fixed Discount','3'),(2,'Variable Discount','1-1,2-1,3-0,4-2,5-2,6-0,7-2,8-0'),(3,'Flexible Discount','1-0,2-1,3-2'),(4,'Flexible Discount','1-0,2-1,3-2'),(5,'Fixed Discount','3'),(8,'Flexible Discount','1-1,2-3,3-5'),(9,'Flexible Discount','1-1,2-2,3-5');
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

-- Dump completed on 2021-04-01 18:15:24
