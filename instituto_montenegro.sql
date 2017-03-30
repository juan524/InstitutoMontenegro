# Host: localhost  (Version 5.1.73-community)
# Date: 2017-03-20 18:46:44
# Generator: MySQL-Front 5.3  (Build 5.39)

/*!40101 SET NAMES latin1 */;

#
# Structure for table "administrador"
#

CREATE TABLE `administrador` (
  `codigoAdmin` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(15) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `nombres` varchar(50) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigoAdmin`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "administrador"
#

INSERT INTO `administrador` VALUES (1,'administrador1','admin123','juan','alape');

#
# Structure for table "estudiante"
#

CREATE TABLE `estudiante` (
  `documento` int(11) NOT NULL DEFAULT '0',
  `nombres` varchar(60) DEFAULT NULL,
  `apellidos` varchar(60) DEFAULT NULL,
  `grado` varchar(5) DEFAULT NULL,
  `sexo` varchar(2) DEFAULT NULL,
  `tipoPoblacion` varchar(50) DEFAULT NULL,
  `modeloPedagogico` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`documento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "estudiante"
#

INSERT INTO `estudiante` VALUES (1094932533,'juliana','valencia','F','F','n/a','tradicional'),(1094953069,'mateo ','cano','9-a','M','desplazado','Tradicional'),(1094960678,'kevin','giraldo','M','M','problema cognitivo','tradicional');

#
# Structure for table "instituto_montenegro"
#

CREATE TABLE `instituto_montenegro` (
  `Nit` int(11) NOT NULL AUTO_INCREMENT,
  `documentoEstudiante` int(11) DEFAULT NULL,
  `fechaIngreso` date DEFAULT NULL,
  `ultimoIngreso` date DEFAULT NULL,
  PRIMARY KEY (`Nit`),
  KEY `documento_Estudiante` (`documentoEstudiante`),
  CONSTRAINT `documento_Estudiante` FOREIGN KEY (`documentoEstudiante`) REFERENCES `estudiante` (`documento`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;

#
# Data for table "instituto_montenegro"
#

INSERT INTO `instituto_montenegro` VALUES (16,1094953069,'2017-03-17','2017-03-20'),(19,1094953069,'2017-03-18','2017-03-20'),(20,1094932533,'2017-03-19','2017-03-20'),(21,1094932533,'2017-03-20','2017-03-20');
