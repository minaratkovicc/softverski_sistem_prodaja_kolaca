/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - kolaci2
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`kolaci2` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `kolaci2`;

/*Table structure for table `kolac` */

DROP TABLE IF EXISTS `kolac`;

CREATE TABLE `kolac` (
  `KolacID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(120) NOT NULL,
  `Opis` varchar(300) DEFAULT NULL,
  `Cena` double NOT NULL,
  `Gluten` varchar(20) NOT NULL DEFAULT 'SADRZI',
  PRIMARY KEY (`KolacID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kolac` */

insert  into `kolac`(`KolacID`,`Naziv`,`Opis`,`Cena`,`Gluten`) values 
(1,'ruska kapa','Mekani biskvitni kolac punjen vanila kremom, uvaljan u kokos i preliven finom cokoladnom glazurom',55,'SADRZI'),
(2,'princes krofna','Vazdusasto testo punjeno bogatim ,laganim kremom od vanile i penastim slagom, posuto secerom u prahu',90,'NE SADRZI'),
(4,'indijanac','Kolac od mekog biaskvita sa visokim slojem cvrstog snega od belanaca,potpuno preliven cokoladom',75,'SADRZI'),
(5,'dubai kolac','Ekskluzivni desert sa hrskavim kadifom,bogatim kremom od pistaca i prelivom od mlecne cokolade',270,'SADRZI'),
(6,'krempita','Kultni desert od hrskavog lisnatog testa i debelog sloja laganog, vazdusastog krema od vanile',120,'SADRZI'),
(7,'dobos torta','Cuvena torta sa puno tankih kora, bogatim cokoladnim filom i prepoznatljivom glazurom od karamelizovanog secera',160,'SADRZI'),
(8,'baklava','Tradicionalni kolac od tankih kora sa bogatim filom od mlevenih oraha, natopljen slatkim secernim sirupom',100,'NE SADRZI'),
(9,'cizkejk','Osvezavajuci spoj podloge od mlevenog keksa, kremastog sira i kiselkastog preliva od crvenog voca',210,'SADRZI'),
(10,'sampita','Lagana poslastica na biskvitnoj podlozi sa ekstremno visokim slojem slatkog sama od belanaca',150,'SADRZI'),
(11,'veganski kolac','Lagana poslastica bez sastojaka zivotinjskog porekla, sa dodatkom sirovog kakaa i orasa',250,'NE SADRZI'),
(12,'tiramisu','Cuvena italijanska kremasta poslastica sa mascarpone sirom i kafom',270,'SADRZI'),
(13,'mafin vanila jagoda','Poslastica sa vanil testom i punjenjem od jagode',170,'SADRZI'),
(14,'tulumba','Tradicionalan kolac prepoznatljivog oblika preliven sirupom',95,'SADRZI');

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `KupacID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `Email` varchar(80) NOT NULL,
  `Telefon` varchar(20) NOT NULL,
  `MestoID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`KupacID`),
  KEY `MestoID` (`MestoID`),
  CONSTRAINT `kupac_ibfk_1` FOREIGN KEY (`MestoID`) REFERENCES `mesto` (`MestoID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `kupac` */

insert  into `kupac`(`KupacID`,`Ime`,`Prezime`,`Email`,`Telefon`,`MestoID`) values 
(1,'Glorija','Milikic','glorija@gmail.com','0623721283',2),
(2,'Nina','Petric','nina@gmail.com','0641291067',1),
(4,'Danica','Visnjic','danica@gmail.com','0638082874',1),
(6,'Dusica','Jankovic','dusica@gmail.com','0671237896',2),
(7,'Pavle','Milojevic','jopa@gmail.com','0613598726',1),
(8,'Vanja','Rikic','vanja@gmail.com','0653902134',8),
(9,'Nadja','Milanovic','nadja@gmail.com','0613456789',6),
(10,'Goran','Ristovic','goran@gmail.com','0646821921',4),
(12,'Magdalena','Gajic','magdalena@gmail.com','0645494444',3);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `MestoID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Naziv` varchar(80) NOT NULL,
  PRIMARY KEY (`MestoID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`MestoID`,`Naziv`) values 
(1,'Kraljevo'),
(2,'Nis'),
(3,'Beograd'),
(4,'Novi Sad'),
(5,'Krusevac'),
(6,'Jagodina'),
(7,'Sjenica'),
(8,'Loznica');

/*Table structure for table `poslasticar` */

DROP TABLE IF EXISTS `poslasticar`;

CREATE TABLE `poslasticar` (
  `PoslasticarID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `Ime` varchar(50) NOT NULL,
  `Prezime` varchar(50) NOT NULL,
  `KorisnickoIme` varchar(50) NOT NULL,
  `Lozinka` varchar(100) NOT NULL,
  PRIMARY KEY (`PoslasticarID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `poslasticar` */

insert  into `poslasticar`(`PoslasticarID`,`Ime`,`Prezime`,`KorisnickoIme`,`Lozinka`) values 
(1,'Kaja','Markovic','kaja','kaja'),
(2,'Sara','Gajic','sara','sara'),
(3,'Mina','Ratkovic','mina','mina');

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `RacunID` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `DatumVreme` datetime NOT NULL,
  `Status` varchar(20) NOT NULL DEFAULT 'AKTIVAN',
  `StornoOdRacunaID` bigint(10) DEFAULT NULL,
  `UkupanIznos` decimal(12,2) NOT NULL,
  `PoslasticarID` bigint(10) unsigned NOT NULL,
  `KupacID` bigint(10) unsigned NOT NULL,
  PRIMARY KEY (`RacunID`),
  KEY `PoslasticarID` (`PoslasticarID`),
  KEY `KupacID` (`KupacID`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`PoslasticarID`) REFERENCES `poslasticar` (`PoslasticarID`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`KupacID`) REFERENCES `kupac` (`KupacID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `racun` */

insert  into `racun`(`RacunID`,`DatumVreme`,`Status`,`StornoOdRacunaID`,`UkupanIznos`,`PoslasticarID`,`KupacID`) values 
(1,'2026-04-08 21:21:38','STORNIRAN',NULL,385.00,1,1),
(3,'2026-04-23 10:18:36','STORNO',1,-385.00,3,1),
(4,'2026-08-13 18:14:14','AKTIVAN',NULL,2670.00,3,4),
(5,'2026-04-23 11:56:44','AKTIVAN',NULL,920.00,3,7),
(6,'2026-08-05 14:09:41','AKTIVAN',NULL,750.00,1,8),
(7,'2026-08-05 14:11:04','AKTIVAN',NULL,1140.00,1,10),
(8,'2026-08-05 14:11:53','AKTIVAN',NULL,1740.00,1,6),
(9,'2026-08-05 14:26:22','AKTIVAN',NULL,900.00,2,9),
(10,'2026-08-11 21:22:03','AKTIVAN',NULL,2600.00,2,2),
(11,'2026-08-13 18:14:41','AKTIVAN',NULL,825.00,2,4),
(12,'2026-08-13 17:36:12','AKTIVAN',NULL,400.00,3,1),
(13,'2026-09-03 13:27:23','AKTIVAN',NULL,1710.00,2,8);

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `RacunID` bigint(10) unsigned NOT NULL,
  `Rb` int(11) NOT NULL,
  `KolacID` bigint(10) unsigned NOT NULL,
  `Kolicina` int(100) NOT NULL,
  `Cena` decimal(10,2) NOT NULL,
  `Iznos` decimal(12,2) NOT NULL,
  PRIMARY KEY (`RacunID`,`Rb`),
  KEY `KolacID` (`KolacID`),
  CONSTRAINT `stavkaracuna_ibfk_1` FOREIGN KEY (`KolacID`) REFERENCES `kolac` (`KolacID`),
  CONSTRAINT `stavkaracuna_ibfk_2` FOREIGN KEY (`RacunID`) REFERENCES `racun` (`RacunID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`RacunID`,`Rb`,`KolacID`,`Kolicina`,`Cena`,`Iznos`) values 
(1,1,1,7,55.00,385.00),
(3,1,1,-7,55.00,-385.00),
(4,1,5,8,270.00,2160.00),
(4,2,13,3,170.00,510.00),
(5,1,8,6,100.00,600.00),
(5,2,7,2,160.00,320.00),
(6,1,11,3,250.00,750.00),
(7,1,9,2,210.00,420.00),
(7,2,10,3,150.00,450.00),
(7,3,5,1,270.00,270.00),
(8,1,8,6,100.00,600.00),
(8,2,7,4,160.00,640.00),
(8,3,11,2,250.00,500.00),
(9,1,2,10,90.00,900.00),
(10,1,14,10,95.00,950.00),
(10,2,5,3,270.00,810.00),
(10,3,9,4,210.00,840.00),
(11,1,1,15,55.00,825.00),
(12,1,8,4,100.00,400.00),
(13,1,6,3,120.00,360.00),
(13,2,5,5,270.00,1350.00);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
