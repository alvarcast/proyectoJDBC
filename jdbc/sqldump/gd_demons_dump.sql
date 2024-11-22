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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beaten_level`
--

LOCK TABLES `beaten_level` WRITE;
/*!40000 ALTER TABLE `beaten_level` DISABLE KEYS */;
INSERT INTO `beaten_level` VALUES (3,4,10,10,10,10,10,2000,'2023-11-12'),(11,9,10,10,10,10,10,320,'2024-10-02'),(12,10,9,10,10,8.5,9.375,879,'2024-11-22');
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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fav_demons`
--

LOCK TABLES `fav_demons` WRITE;
/*!40000 ALTER TABLE `fav_demons` DISABLE KEYS */;
INSERT INTO `fav_demons` VALUES (2,8,4),(9,8,9),(10,12,10),(12,12,10),(13,12,10);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `level`
--

LOCK TABLES `level` WRITE;
/*!40000 ALTER TABLE `level` DISABLE KEYS */;
INSERT INTO `level` VALUES (2,8,111368620,'Adelucid','MannyHeffley','Adelucid',5,26,2000,0,'2024-11-18'),(4,8,93549959,'Mystic Bounds','gmdmann','Mystic Bounds',5,20.59,2000,1,'2023-09-01'),(8,8,95089240,'CONNECT','MCres','CONNECT',5,32,0,0,'2025-01-01'),(9,8,110653463,'commatose','rply','commatose',4,15.87,320,1,'2024-10-01'),(10,12,97493518,'And ever','galofuf','Royal Azalea',1,2.71,145,1,'2024-11-22'),(11,12,44062068,'Future Funk','JonathanGD','Whats a Future Funk?',3,12.86,1267,0,'2020-02-15');
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
INSERT INTO `personal_info` VALUES (8,'Alvaro','Aguado','aaguacas@myuax.com'),(12,'Pedro','Salinas','pedrosali92@gmail.com');
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'alvar','81dc9bdb52d04dc20036dbd8313ed055'),(12,'abogado007','e2fc714c4727ee9395f324cd2e7f331f');
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

-- Dump completed on 2024-11-22 11:38:04
