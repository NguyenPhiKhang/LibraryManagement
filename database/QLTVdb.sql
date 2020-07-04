CREATE DATABASE  IF NOT EXISTS `qltv` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qltv`;
-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: qltv
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbaccount`
--

DROP TABLE IF EXISTS `tbaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbaccount` (
  `idaccount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `idper` int NOT NULL,
  PRIMARY KEY (`idaccount`),
  KEY `idper_idx` (`idper`),
  CONSTRAINT `idper` FOREIGN KEY (`idper`) REFERENCES `tbphanquyen` (`idper`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbaccount`
--

LOCK TABLES `tbaccount` WRITE;
/*!40000 ALTER TABLE `tbaccount` DISABLE KEYS */;
INSERT INTO `tbaccount` VALUES ('17520004','12345678',1),('17520029','12345678',1),('17520035','12345678',1),('17520040','12345678',1),('17520043','12345678',1),('17520057','12345678',1),('17520068','12345678',1),('17520081','12345678',1),('17520087','12345678',1),('17520096','12345678',1),('17520107','12345678',1),('17520134','12345678',1),('17520155','12345678',1),('17520156','12345678',1),('17520159','12345678',1),('17520184','12345678',1),('17520186','12345678',1),('17520187','12345678',1),('17520230','12345678',1),('17520267','12345678',1),('17520292','12345678',1),('17520308','12345678',1),('17520309','12345678',1),('17520323','12345678',1),('17520343','12345678',1),('17520345','12345678',1),('17520350','12345678',1),('17520372','12345678',1),('17520376','12345678',1),('17520433','12345678',1),('17520616','12345678',1),('admin','admin@123',2),('khang','12345',1),('khangnguyen','12345678',1),('thuthu','thuthu@123',3);
/*!40000 ALTER TABLE `tbaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbadmin`
--

DROP TABLE IF EXISTS `tbadmin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbadmin` (
  `idadmin` int NOT NULL,
  `idaccount` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `hoten` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `diachi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sdt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`idadmin`),
  KEY `tbadmin_idaccount_foreign` (`idaccount`),
  CONSTRAINT `tbadmin_idaccount_foreign` FOREIGN KEY (`idaccount`) REFERENCES `tbaccount` (`idaccount`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbadmin`
--

LOCK TABLES `tbadmin` WRITE;
/*!40000 ALTER TABLE `tbadmin` DISABLE KEYS */;
INSERT INTO `tbadmin` VALUES (1,'admin','Nguyễn Văn A','1990-12-22','TP.HCM','admin@gmail.com','0327559839'),(2,'thuthu','Nguyễn Văn B','1995-04-04','An Giang','admin1@gmail.com','01269482943');
/*!40000 ALTER TABLE `tbadmin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbbaocaosachtratre`
--

DROP TABLE IF EXISTS `tbbaocaosachtratre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbbaocaosachtratre` (
  `mabcstt` int NOT NULL,
  `ngay` date NOT NULL,
  PRIMARY KEY (`mabcstt`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbbaocaosachtratre`
--

LOCK TABLES `tbbaocaosachtratre` WRITE;
/*!40000 ALTER TABLE `tbbaocaosachtratre` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbbaocaosachtratre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbbaocaotheotheloai`
--

DROP TABLE IF EXISTS `tbbaocaotheotheloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbbaocaotheotheloai` (
  `mabaocaotheloai` int NOT NULL,
  `thang` int DEFAULT NULL,
  `nam` int DEFAULT NULL,
  `tongluotmuon` int DEFAULT NULL,
  PRIMARY KEY (`mabaocaotheloai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbbaocaotheotheloai`
--

LOCK TABLES `tbbaocaotheotheloai` WRITE;
/*!40000 ALTER TABLE `tbbaocaotheotheloai` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbbaocaotheotheloai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbctbaocaosachtratre`
--

DROP TABLE IF EXISTS `tbctbaocaosachtratre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbctbaocaosachtratre` (
  `mabcstt` int NOT NULL,
  `masach` int NOT NULL,
  `tensach` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ngaymuon` date DEFAULT NULL,
  `songaytratre` int DEFAULT NULL,
  PRIMARY KEY (`mabcstt`,`masach`),
  KEY `tbctbaocaosachtratre_masach_foreign` (`masach`),
  CONSTRAINT `tbctbaocaosachtratre_mabcstt_foreign` FOREIGN KEY (`mabcstt`) REFERENCES `tbbaocaosachtratre` (`mabcstt`) ON DELETE CASCADE,
  CONSTRAINT `tbctbaocaosachtratre_masach_foreign` FOREIGN KEY (`masach`) REFERENCES `tbsach` (`masach`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbctbaocaosachtratre`
--

LOCK TABLES `tbctbaocaosachtratre` WRITE;
/*!40000 ALTER TABLE `tbctbaocaosachtratre` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbctbaocaosachtratre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbctbaocaotheloai`
--

DROP TABLE IF EXISTS `tbctbaocaotheloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbctbaocaotheloai` (
  `mabaocaotheloai` int NOT NULL,
  `matheloai` int NOT NULL,
  `tentheloai` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `soluotmuon` int DEFAULT NULL,
  `tile` float DEFAULT NULL,
  PRIMARY KEY (`mabaocaotheloai`,`matheloai`),
  KEY `tbctbaocaotheloai_matheloai_foreign` (`matheloai`),
  CONSTRAINT `tbctbaocaotheloai_mabaocaotheloai_foreign` FOREIGN KEY (`mabaocaotheloai`) REFERENCES `tbbaocaotheotheloai` (`mabaocaotheloai`) ON DELETE CASCADE,
  CONSTRAINT `tbctbaocaotheloai_matheloai_foreign` FOREIGN KEY (`matheloai`) REFERENCES `tbtheloai` (`matheloai`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbctbaocaotheloai`
--

LOCK TABLES `tbctbaocaotheloai` WRITE;
/*!40000 ALTER TABLE `tbctbaocaotheloai` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbctbaocaotheloai` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbctphieumuon`
--

DROP TABLE IF EXISTS `tbctphieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbctphieumuon` (
  `maphieumuon` int NOT NULL,
  `masach` int NOT NULL,
  PRIMARY KEY (`maphieumuon`,`masach`),
  KEY `tbctphieumuon_masach_foreign` (`masach`),
  CONSTRAINT `tbctphieumuon_maphieumuon_foreign` FOREIGN KEY (`maphieumuon`) REFERENCES `tbphieumuon` (`maphieumuon`) ON DELETE CASCADE,
  CONSTRAINT `tbctphieumuon_masach_foreign` FOREIGN KEY (`masach`) REFERENCES `tbsach` (`masach`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbctphieumuon`
--

LOCK TABLES `tbctphieumuon` WRITE;
/*!40000 ALTER TABLE `tbctphieumuon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbctphieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbctphieutra`
--

DROP TABLE IF EXISTS `tbctphieutra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbctphieutra` (
  `maphieutra` int NOT NULL,
  `masach` int NOT NULL,
  `songaymuon` int DEFAULT NULL,
  `tienphat` int DEFAULT NULL,
  PRIMARY KEY (`maphieutra`,`masach`),
  KEY `tbctphieutra_masach_foreign` (`masach`),
  CONSTRAINT `tbctphieutra_maphieutra_foreign` FOREIGN KEY (`maphieutra`) REFERENCES `tbphieutra` (`maphieutra`) ON DELETE CASCADE,
  CONSTRAINT `tbctphieutra_masach_foreign` FOREIGN KEY (`masach`) REFERENCES `tbsach` (`masach`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbctphieutra`
--

LOCK TABLES `tbctphieutra` WRITE;
/*!40000 ALTER TABLE `tbctphieutra` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbctphieutra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbdocgia`
--

DROP TABLE IF EXISTS `tbdocgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbdocgia` (
  `madocgia` int NOT NULL AUTO_INCREMENT,
  `tendocgia` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `maloaidocgia` int NOT NULL,
  `ngaysinh` date NOT NULL,
  `diachi` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaylapthe` date NOT NULL,
  `ngayhethan` date DEFAULT NULL,
  `tinhtrangthe` int NOT NULL DEFAULT '1',
  `tongno` decimal(18,0) DEFAULT '0',
  `idaccount` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `sdt` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`madocgia`),
  KEY `tbdocgia_maloaidocgia_foreign` (`maloaidocgia`),
  KEY `tbdocgia_idaccount_foreign` (`idaccount`),
  CONSTRAINT `tbdocgia_maloaidocgia_foreign` FOREIGN KEY (`maloaidocgia`) REFERENCES `tbloaidocgia` (`maloaidocgia`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdocgia`
--

LOCK TABLES `tbdocgia` WRITE;
/*!40000 ALTER TABLE `tbdocgia` DISABLE KEYS */;
INSERT INTO `tbdocgia` VALUES (1,'Nguyễn Lương Duy',1,'1999-01-14','Quảng Nam','17520004@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520004','0339893353'),(2,'Phạm Tuấn Anh',1,'1999-01-14','Bình Dương','17520029@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520029','0868992467'),(3,'Huỳnh Trầm Bảo Chấn',1,'1999-10-06','Trà Vinh','17520035@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520035','0583335622'),(4,'Dương Thành Đạt',1,'1999-03-14','TpHCM','17520040@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520040','0903533820'),(5,'Nguyễn Tiến Đạt',1,'1999-06-15','TpHCM','17520043@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520043','0964726908'),(6,'Đoàn Thanh Hiền',1,'1999-06-29','Bình Phước','17520057@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520057','0349689771'),(7,'Nguyễn Phi Hùng',1,'1999-10-26','Quảng Bình','17520068@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520068','0776276497'),(8,'Phạm Phúc Khải',1,'1999-07-26','Trà Vinh','17520081@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520081','0389996685'),(9,'Nguyễn Hồng Khoa',1,'1999-06-18','Ninh Thuậ','17520087@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520087','0377337952'),(10,'Nguyễn Du Lịch',1,'1999-09-28','Phú Yê','17520096@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520096','0398821923'),(11,'Bùi Tuấn Minh',1,'1999-07-10','Tp HCM','17520107@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520107','01627162898'),(12,'Lê Quốc Phương',1,'1999-05-02','Bình Phước','17520134@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520134','0918286150'),(13,'Lê Huỳnh Thăng',1,'1999-04-02','Quảng Bình','17520155@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520155','0392425724'),(14,'Lê Quốc Thắng',1,'1999-12-19','tp.HCM','17520156@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520156','0937186594'),(15,'Trần Anh Thắng',1,'1999-08-08','Tây Ninh','17520159@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520159','0888227068'),(16,'Huỳnh Quốc Trung',1,'1999-11-29','Quảng Nam','17520184@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520184','0375475075'),(17,'Phạm Trung Trường',1,'1999-07-04','Nam Định','17520186@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520186','01626934867'),(18,'Cù Xuân Tùng',1,'1999-12-25','Hà Nội','17520187@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520187','0934251299'),(19,'Hà Thị Anh',1,'1999-01-24','Ninh Thuậ','17520230@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520230','0962591703'),(20,'Hồ Nguyên Bảo',1,'1999-12-22','Quảng Nam','17520267@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520267','0855765343'),(21,'Phạm Trần Chính',1,'1999-01-01','Lâm Đồng','17520292@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520292','0352556124'),(22,'Nguyễn Xuân Cường',1,'1999-11-03','Đăk Lăk','17520308@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520308','01639939364'),(23,'Phạm Duy Cường',1,'1999-11-20','Bình Định','17520309@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520309','01637492991'),(24,'Nguyễn Thành Danh',1,'1999-05-19','Quy Nhơ','17520323@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520323','0332198693'),(25,'Vương Thịnh Đạt',1,'1999-11-17','Bình Dương','17520343@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520343','0782369351'),(26,'Phan Phước Đính',1,'1999-09-26','tphcm','17520345@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520345','0916382571'),(27,'Nguyễn Văn Đông',1,'1999-08-22','Kon Tum','17520350@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520350','0365071855'),(28,'Đào Mạnh Dũng',1,'1999-02-24','Kiên Giang','17520372@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520372','0914367398'),(29,'Nguyễn Tiến Dũng',1,'1999-11-20','Hà Tĩnh','17520376@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520376','0396792508'),(30,'Vũ Tuấn Hải',1,'1999-07-23','Gia Lai','17520433@uit.edu.vn','2020-04-05','2022-04-05',1,0,'17520433','0814822188'),(31,'Nguyễn Phi Khang',1,'1999-07-24','Ham Tien','17520616@gm.uit.edu.vn','2020-05-14','2020-11-14',0,0,'17520616','0327559839'),(35,'phi khang nguyen',3,'2020-05-14','binh thuan','khangse616@gmail.com','2020-05-15','2020-11-15',0,0,'khangnguyen','234344444');
/*!40000 ALTER TABLE `tbdocgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbloaidocgia`
--

DROP TABLE IF EXISTS `tbloaidocgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbloaidocgia` (
  `maloaidocgia` int NOT NULL,
  `tenloaidocgia` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maloaidocgia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbloaidocgia`
--

LOCK TABLES `tbloaidocgia` WRITE;
/*!40000 ALTER TABLE `tbloaidocgia` DISABLE KEYS */;
INSERT INTO `tbloaidocgia` VALUES (1,'Sinh Viên'),(2,'Giảng Viên'),(3,'Nhân Viên Văn Phòng');
/*!40000 ALTER TABLE `tbloaidocgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbphanquyen`
--

DROP TABLE IF EXISTS `tbphanquyen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbphanquyen` (
  `idper` int NOT NULL,
  `nameper` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `codeaction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`idper`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbphanquyen`
--

LOCK TABLES `tbphanquyen` WRITE;
/*!40000 ALTER TABLE `tbphanquyen` DISABLE KEYS */;
INSERT INTO `tbphanquyen` VALUES (1,'Đọc Giả','DG','Chỉ được xem thông tin của mình'),(2,'Quản Lý','QL','Được phép quản lý toàn bộ hệ thống'),(3,'Thủ Thư','TT','Không được phép sửa quy định hệ thống');
/*!40000 ALTER TABLE `tbphanquyen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbphieumuon`
--

DROP TABLE IF EXISTS `tbphieumuon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbphieumuon` (
  `maphieumuon` int NOT NULL,
  `madocgia` int NOT NULL,
  `ngaymuon` date NOT NULL,
  `hantra` date NOT NULL,
  `tinhtrang` tinyint(1) NOT NULL,
  PRIMARY KEY (`maphieumuon`),
  KEY `tbphieumuon_madocgia_foreign_idx` (`madocgia`),
  CONSTRAINT `tbphieumuon_madocgia_foreign` FOREIGN KEY (`madocgia`) REFERENCES `tbdocgia` (`madocgia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbphieumuon`
--

LOCK TABLES `tbphieumuon` WRITE;
/*!40000 ALTER TABLE `tbphieumuon` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbphieumuon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbphieuphat`
--

DROP TABLE IF EXISTS `tbphieuphat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbphieuphat` (
  `maphieuphat` int NOT NULL,
  `madocgia` int DEFAULT NULL,
  `sotienthu` int DEFAULT NULL,
  `conlai` int DEFAULT NULL,
  PRIMARY KEY (`maphieuphat`),
  KEY `tbphieuphat_madocgia_foreign_idx` (`madocgia`),
  CONSTRAINT `tbphieuphat_madocgia_foreign` FOREIGN KEY (`madocgia`) REFERENCES `tbdocgia` (`madocgia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbphieuphat`
--

LOCK TABLES `tbphieuphat` WRITE;
/*!40000 ALTER TABLE `tbphieuphat` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbphieuphat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbphieutra`
--

DROP TABLE IF EXISTS `tbphieutra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbphieutra` (
  `maphieutra` int NOT NULL,
  `maphieumuon` int NOT NULL,
  `madocgia` int NOT NULL,
  `ngaytra` date NOT NULL,
  `tienphatkynay` int NOT NULL,
  PRIMARY KEY (`maphieutra`),
  KEY `tbphieutra_maphieumuon_foreign` (`maphieumuon`),
  KEY `tbphieutra_madocgia_foreign_idx` (`madocgia`),
  CONSTRAINT `tbphieutra_madocgia_foreign` FOREIGN KEY (`madocgia`) REFERENCES `tbdocgia` (`madocgia`),
  CONSTRAINT `tbphieutra_maphieumuon_foreign` FOREIGN KEY (`maphieumuon`) REFERENCES `tbphieumuon` (`maphieumuon`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbphieutra`
--

LOCK TABLES `tbphieutra` WRITE;
/*!40000 ALTER TABLE `tbphieutra` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbphieutra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbsach`
--

DROP TABLE IF EXISTS `tbsach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbsach` (
  `masach` int NOT NULL,
  `tensach` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `matheloai` int NOT NULL,
  `matacgia` int NOT NULL,
  `namxb` int NOT NULL,
  `nxb` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `ngaynhap` date NOT NULL,
  `trigia` int NOT NULL,
  `tinhtrang` tinyint(1) NOT NULL,
  `anhbia` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`masach`),
  KEY `tbsach_matheloai_foreign` (`matheloai`),
  KEY `tbsach_matacgia_foreign` (`matacgia`),
  CONSTRAINT `tbsach_matacgia_foreign` FOREIGN KEY (`matacgia`) REFERENCES `tbtacgia` (`matacgia`) ON DELETE CASCADE,
  CONSTRAINT `tbsach_matheloai_foreign` FOREIGN KEY (`matheloai`) REFERENCES `tbtheloai` (`matheloai`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbsach`
--

LOCK TABLES `tbsach` WRITE;
/*!40000 ALTER TABLE `tbsach` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbsach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbtacgia`
--

DROP TABLE IF EXISTS `tbtacgia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbtacgia` (
  `matacgia` int NOT NULL,
  `tentacgia` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`matacgia`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbtacgia`
--

LOCK TABLES `tbtacgia` WRITE;
/*!40000 ALTER TABLE `tbtacgia` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbtacgia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbthamso`
--

DROP TABLE IF EXISTS `tbthamso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbthamso` (
  `tuoitoida` int DEFAULT NULL,
  `tuoitoithieu` int DEFAULT NULL,
  `thoihanthe` int DEFAULT NULL,
  `soluongtgtoida` int DEFAULT NULL,
  `khoangcachxb` int DEFAULT NULL,
  `sosachmuontoida` int DEFAULT NULL,
  `thoigianmuontoida` int DEFAULT NULL,
  `tienphatmotngay` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbthamso`
--

LOCK TABLES `tbthamso` WRITE;
/*!40000 ALTER TABLE `tbthamso` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbthamso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbtheloai`
--

DROP TABLE IF EXISTS `tbtheloai`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbtheloai` (
  `matheloai` int NOT NULL,
  `tentheloai` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`matheloai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbtheloai`
--

LOCK TABLES `tbtheloai` WRITE;
/*!40000 ALTER TABLE `tbtheloai` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbtheloai` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-24 14:45:17
