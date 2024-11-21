-- MariaDB dump 10.19  Distrib 10.4.32-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: gd_demons
-- ------------------------------------------------------
-- Server version	10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `beaten_level`
--

DROP TABLE IF EXISTS `beaten_level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `beaten_level` (
  `blid` int(11) NOT NULL AUTO_INCREMENT,
  `lid` int(11) DEFAULT NULL,
  `music_rate` float DEFAULT NULL,
  `gameplay_rate` float DEFAULT NULL,
  `deco_rate` float DEFAULT NULL,
  `fx_rate` float DEFAULT NULL,
  `enjoyment` float DEFAULT NULL,
  `total_attempts` int(11) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  PRIMARY KEY (`blid`),
  KEY `lid` (`lid`),
  CONSTRAINT `beaten_level_ibfk_1` FOREIGN KEY (`lid`) REFERENCES `level` (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beaten_level`
--

LOCK TABLES `beaten_level` WRITE;
/*!40000 ALTER TABLE `beaten_level` DISABLE KEYS */;
INSERT INTO `beaten_level` VALUES (2,3,8,10,5,3,6.5,10000,'2024-11-19'),(3,4,10,10,10,10,10,2000,'2023-11-12');
/*!40000 ALTER TABLE `beaten_level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demon_difficulties`
--

DROP TABLE IF EXISTS `demon_difficulties`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `demon_difficulties` (
  `did` int(11) NOT NULL AUTO_INCREMENT,
  `difficulty_name` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`did`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demon_difficulties`
--

LOCK TABLES `demon_difficulties` WRITE;
/*!40000 ALTER TABLE `demon_difficulties` DISABLE KEYS */;
INSERT INTO `demon_difficulties` VALUES (1,'Easy Demon'),(2,'Medium Demon'),(3,'Hard Demon'),(4,'Insane Demon'),(5,'Extreme Demon');
/*!40000 ALTER TABLE `demon_difficulties` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fav_demons`
--

DROP TABLE IF EXISTS `fav_demons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `fav_demons` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `lid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`),
  KEY `uid` (`uid`),
  KEY `lid` (`lid`),
  CONSTRAINT `fav_demons_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`),
  CONSTRAINT `fav_demons_ibfk_2` FOREIGN KEY (`lid`) REFERENCES `level` (`lid`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fav_demons`
--

LOCK TABLES `fav_demons` WRITE;
/*!40000 ALTER TABLE `fav_demons` DISABLE KEYS */;
INSERT INTO `fav_demons` VALUES (1,8,3),(2,8,4);
/*!40000 ALTER TABLE `fav_demons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `level`
--

DROP TABLE IF EXISTS `level`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `level` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `game_id` int(11) DEFAULT NULL,
  `level_name` varchar(20) DEFAULT NULL,
  `creator` varchar(20) DEFAULT NULL,
  `music` varchar(100) DEFAULT NULL,
  `difficulty` int(11) DEFAULT NULL,
  `diff_num` float DEFAULT NULL,
  `attempts` int(11) DEFAULT NULL,
  `beaten` tinyint(1) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  PRIMARY KEY (`lid`),
  KEY `difficulty` (`difficulty`),
  KEY `uid` (`uid`),
  CONSTRAINT `level_ibfk_1` FOREIGN KEY (`difficulty`) REFERENCES `demon_difficulties` (`did`),
  CONSTRAINT `level_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (2,8,111368620,'Adelucid','MannyHeffley','Adelucid',5,26,2000,0,'2024-11-18'),(3,8,1,'Stereo Madness','Robtop','Stereo Madness',5,35,20000,1,'2024-11-19'),(4,8,93549959,'Mystic Bounds','gmdmann','Mystic Bounds',5,20.59,2000,1,'2023-09-01');
/*!40000 ALTER TABLE `level` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal_info`
--

DROP TABLE IF EXISTS `personal_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal_info` (
  `uid` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  KEY `uid` (`uid`),
  CONSTRAINT `personal_info_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal_info`
--

LOCK TABLES `personal_info` WRITE;
/*!40000 ALTER TABLE `personal_info` DISABLE KEYS */;
INSERT INTO `personal_info` VALUES (8,'Alvarin','Aguadin','alvarinaguado05@gmail.com');
/*!40000 ALTER TABLE `personal_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'alvar','81dc9bdb52d04dc20036dbd8313ed055');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-21 21:08:58
