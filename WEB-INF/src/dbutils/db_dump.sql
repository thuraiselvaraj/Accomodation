-- MySQL dump 10.13  Distrib 5.5.56, for linux-glibc2.5 (x86_64)
--
-- Host: localhost    Database: project
-- ------------------------------------------------------
-- Server version	5.5.56-log

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
-- Table structure for table `login_table`
--

DROP TABLE IF EXISTS `login_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login_table` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `type` enum('ADMIN','STUDENT') DEFAULT NULL,
  PRIMARY KEY (`_id`),
  UNIQUE KEY `email` (`email`),
  KEY `email_2` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login_table`
--

LOCK TABLES `login_table` WRITE;
/*!40000 ALTER TABLE `login_table` DISABLE KEYS */;
INSERT INTO `login_table` VALUES (1,'admin1','81dc9bdb52d04dc20036dbd8313ed055','ADMIN'),(2,'admin2','81dc9bdb52d04dc20036dbd8313ed055','ADMIN'),(3,'admin3','81dc9bdb52d04dc20036dbd8313ed055','ADMIN'),(4,'student1','81dc9bdb52d04dc20036dbd8313ed055','STUDENT'),(5,'student2','81dc9bdb52d04dc20036dbd8313ed055','STUDENT'),(6,'student3','81dc9bdb52d04dc20036dbd8313ed055','STUDENT'),(7,'student4','81dc9bdb52d04dc20036dbd8313ed055','STUDENT');
/*!40000 ALTER TABLE `login_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `type` enum('AC','No/AC') DEFAULT NULL,
  `location` int(11) DEFAULT NULL,
  `charge` int(11) DEFAULT NULL,
  `r_status` enum('AVAILABLE','NOAVAILABLE') DEFAULT NULL,
  `p_status` enum('PAID','NOPAID') DEFAULT NULL,
  `s_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'AC',2,1000,'AVAILABLE','PAID',NULL),(2,'AC',2,2000,'NOAVAILABLE','PAID',5),(3,'AC',2,3000,'AVAILABLE','PAID',NULL),(4,'AC',2,4000,'AVAILABLE','PAID',NULL),(5,'No/AC',2,20212,'AVAILABLE','NOPAID',NULL),(6,'AC',2,4999,'AVAILABLE','PAID',NULL),(7,'AC',2,10000,'NOAVAILABLE','PAID',NULL),(8,'No/AC',4,299,'AVAILABLE','NOPAID',NULL),(9,'AC',3,123,'AVAILABLE','NOPAID',NULL);
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `session_table`
--

DROP TABLE IF EXISTS `session_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `session_table` (
  `_id` int(11) DEFAULT NULL,
  `session_key` varchar(80) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `session_table`
--

LOCK TABLES `session_table` WRITE;
/*!40000 ALTER TABLE `session_table` DISABLE KEYS */;
INSERT INTO `session_table` VALUES (4,'12a19604c398aa8c25a0e7675c6e5bee'),(4,'5cefb6a531ffe7c44a65c4007e1a5f8a'),(5,'4f13361ae6041f0aafd74a2358aed492'),(4,'d92d112fb3fd9d42eb65d6e03260e1f5');
/*!40000 ALTER TABLE `session_table` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-03 19:56:38
