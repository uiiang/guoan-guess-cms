-- MySQL dump 10.13  Distrib 5.7.18, for osx10.12 (x86_64)
--
-- Host: localhost    Database: guoan1992
-- ------------------------------------------------------
-- Server version	5.7.18

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
-- Table structure for table `guess_result`
--

DROP TABLE IF EXISTS `guess_result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guess_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `away_result` varchar(255) DEFAULT NULL,
  `home_result` varchar(255) DEFAULT NULL,
  `match_id` varchar(255) DEFAULT NULL,
  `submit_time` bigint(20) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `guess_result`
--

LOCK TABLES `guess_result` WRITE;
/*!40000 ALTER TABLE `guess_result` DISABLE KEYS */;
/*!40000 ALTER TABLE `guess_result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league_team`
--

DROP TABLE IF EXISTS `league_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league_team` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `emblems` varchar(255) DEFAULT NULL,
  `stadium_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league_team`
--

LOCK TABLES `league_team` WRITE;
/*!40000 ALTER TABLE `league_team` DISABLE KEYS */;
INSERT INTO `league_team` VALUES (1,'北京中赫国安','北京','beijingguoan.png','北京工人体育场'),(2,'广州恒大淘宝','广州','guangzhouhengda.png','广州天河体育场 	'),(3,'江苏苏宁','南京','jiangsusuning.png','南京奥体中心'),(4,'上海上港','上海','shanghaishanggang.png','上海体育场'),(5,'上海绿地申花','上海','shanghaishenhua.png','上海虹口足球场'),(6,'广州富力','广州	','guangzhoufuli.png','广东省人民体育场'),(7,'河北华夏幸福','秦皇岛','hebeihuaxiaxingfu.png','秦皇岛奥体中心'),(8,'重庆当代力帆','重庆','chongqinglifan.png','重庆奥体中心'),(9,'延边富德','吉林','yanbianfude.png','延吉人民体育场'),(10,'天津亿利','天津','tianjintaida.png','天津奥体中心'),(11,'辽宁沈阳开新','沈阳','liaoninghongyuan.png','沈阳奥体中心'),(12,'长春亚泰','长春','changchunyatai.png','长春经开体育场'),(13,'河南建业','郑州','henanjianye.png','郑州航海体育场'),(14,'山东鲁能泰山','山东济南','shandongluneng.png','鲁能大球场'),(15,'天津权健','天津','tianjinquanjian.png','天津海河教育园区体育场'),(16,'贵州恒丰智诚','贵阳','guizhouzhicheng.png','贵阳奥体中心');
/*!40000 ALTER TABLE `league_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `league_team_match_schedules`
--

DROP TABLE IF EXISTS `league_team_match_schedules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `league_team_match_schedules` (
  `league_team_id` bigint(20) NOT NULL,
  `match_schedules_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_ovvo0mdqkx06jemdroyoinl3k` (`match_schedules_id`),
  KEY `FK6ste8mkaog444fg7nj10rmsnw` (`league_team_id`),
  CONSTRAINT `FK6ste8mkaog444fg7nj10rmsnw` FOREIGN KEY (`league_team_id`) REFERENCES `league_team` (`id`),
  CONSTRAINT `FKksgro0nv1ym4fn8o9ywgogset` FOREIGN KEY (`match_schedules_id`) REFERENCES `match_schedule` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `league_team_match_schedules`
--

LOCK TABLES `league_team_match_schedules` WRITE;
/*!40000 ALTER TABLE `league_team_match_schedules` DISABLE KEYS */;
/*!40000 ALTER TABLE `league_team_match_schedules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_info`
--

DROP TABLE IF EXISTS `match_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_info`
--

LOCK TABLES `match_info` WRITE;
/*!40000 ALTER TABLE `match_info` DISABLE KEYS */;
INSERT INTO `match_info` VALUES (1,'2017年中超联赛',0);
/*!40000 ALTER TABLE `match_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `match_schedule`
--

DROP TABLE IF EXISTS `match_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `match_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `away_result` int(11) NOT NULL,
  `away_team` varchar(255) DEFAULT NULL,
  `home_result` int(11) NOT NULL,
  `home_team` varchar(255) DEFAULT NULL,
  `match_level` varchar(255) DEFAULT NULL,
  `round_num` int(11) NOT NULL,
  `stadium_name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `match_date_time` datetime DEFAULT NULL,
  `away_emblems` varchar(255) DEFAULT NULL,
  `home_emblems` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `match_schedule`
--

LOCK TABLES `match_schedule` WRITE;
/*!40000 ALTER TABLE `match_schedule` DISABLE KEYS */;
INSERT INTO `match_schedule` VALUES (1,1,'北京中赫国安',2,'广州恒大淘宝','2017年中超联赛',1,'广州天河体育场 	',2,'2017-03-05 19:30:00','beijingguoan.png','guangzhouhengda.png'),(2,1,'北京中赫国安',1,'贵州恒丰智诚','2017年中超联赛',2,'贵阳奥体中心',2,'2017-03-11 19:30:00','beijingguoan.png','guizhouzhicheng.png'),(3,1,'上海绿地申花',2,'北京中赫国安','2017年中超联赛',3,'北京工人体育场',2,'2017-04-02 19:30:00','shanghaishenhua.png','beijingguoan.png'),(4,0,'河南建业',1,'北京中赫国安','2017年中超联赛',4,'北京工人体育场',2,'2017-04-07 19:30:00','henanjianye.png','beijingguoan.png'),(5,0,'北京中赫国安',1,'山东鲁能泰山','2017年中超联赛',5,'鲁能大球场',2,'2017-04-15 19:30:00','beijingguoan.png','shandongluneng.png'),(6,1,'天津权健',1,'北京中赫国安','2017年中超联赛',6,'北京工人体育场',2,'2017-04-21 19:30:00','tianjinquanjian.png','beijingguoan.png'),(7,4,'北京中赫国安',2,'辽宁沈阳开新','2017年中超联赛',7,'沈阳奥体中心',2,'2017-04-29 15:30:00','beijingguoan.png','liaoninghongyuan.png'),(8,0,'河北华夏幸福',0,'北京中赫国安','2017年中超联赛',8,'北京工人体育场',0,'2017-05-07 19:30:00','hebeihuaxiaxingfu.png','beijingguoan.png'),(9,0,'北京中赫国安',0,'延边富德','2017年中超联赛',9,'延吉人民体育场',0,'2017-05-13 15:30:00','beijingguoan.png','yanbianfude.png'),(10,0,'广州富力',0,'北京中赫国安','2017年中超联赛',10,'北京工人体育场',0,'2017-05-19 18:00:00','guangzhoufuli.png','beijingguoan.png'),(11,0,'北京中赫国安',0,'上海上港','2017年中超联赛',11,'上海体育场',0,'2017-05-27 19:30:00','beijingguoan.png','shanghaishanggang.png'),(12,0,'北京中赫国安',0,'重庆当代力帆','2017年中超联赛',12,'重庆奥体中心',0,'2017-06-02 19:30:00','beijingguoan.png','chongqinglifan.png'),(13,0,'天津亿利',0,'北京中赫国安','2017年中超联赛',13,'北京工人体育场',0,'2017-06-18 19:30:00','tianjintaida.png','beijingguoan.png');
/*!40000 ALTER TABLE `match_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `now_match_info`
--

DROP TABLE IF EXISTS `now_match_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `now_match_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `away_result` int(11) NOT NULL,
  `away_team` varchar(255) DEFAULT NULL,
  `away_win_num` int(11) NOT NULL,
  `draw_num` int(11) NOT NULL,
  `home_result` int(11) NOT NULL,
  `home_team` varchar(255) DEFAULT NULL,
  `home_win_num` int(11) NOT NULL,
  `join_num` int(11) NOT NULL,
  `match_date_time` datetime DEFAULT NULL,
  `match_db_id` bigint(20) DEFAULT NULL,
  `match_level` varchar(255) DEFAULT NULL,
  `round_num` int(11) NOT NULL,
  `stadium_name` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `away_emblems` varchar(255) DEFAULT NULL,
  `home_emblems` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `now_match_info`
--

LOCK TABLES `now_match_info` WRITE;
/*!40000 ALTER TABLE `now_match_info` DISABLE KEYS */;
INSERT INTO `now_match_info` VALUES (6,0,'河北华夏幸福',0,0,0,'北京中赫国安',0,0,'2017-05-07 19:30:00',8,'2017年中超联赛',8,'北京工人体育场',0,'hebeihuaxiaxingfu.png','beijingguoan.png');
/*!40000 ALTER TABLE `now_match_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stadium_info`
--

DROP TABLE IF EXISTS `stadium_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `stadium_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `english_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stadium_info`
--

LOCK TABLES `stadium_info` WRITE;
/*!40000 ALTER TABLE `stadium_info` DISABLE KEYS */;
INSERT INTO `stadium_info` VALUES (1,'贵阳奥体中心','贵阳',''),(2,'广东省人民体育场','广州',''),(3,'鲁能大球场','山东济南',''),(4,'上海体育场','上海',''),(5,'重庆奥体中心','重庆',''),(6,'郑州航海体育场','郑州',''),(7,'上海虹口足球场','上海',''),(8,'广州天河体育场 	','广州	',''),(9,'南京奥体中心','南京',''),(10,'延吉人民体育场','吉林',''),(11,'天津海河教育园区体育场','天津',''),(12,'秦皇岛奥体中心','秦皇岛',''),(13,'长春经开体育场','长春',''),(14,'北京工人体育场','北京',''),(15,'天津奥体中心','天津',''),(16,'沈阳奥体中心','沈阳','');
/*!40000 ALTER TABLE `stadium_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-30 10:31:35
