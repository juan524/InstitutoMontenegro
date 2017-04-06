# Host: localhost  (Version 5.1.73-community)
# Date: 2017-04-05 13:31:16
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


#
# Structure for table "imagen"
#

CREATE TABLE `imagen` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `documentoEstudiante` int(11) DEFAULT NULL,
  `nombre` varchar(30) DEFAULT NULL,
  `foto` blob,
  PRIMARY KEY (`Id`),
  KEY `documentoEstudiante` (`documentoEstudiante`),
  CONSTRAINT `documentoEstudiante` FOREIGN KEY (`documentoEstudiante`) REFERENCES `estudiante` (`documento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "imagen"
#


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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

#
# Data for table "instituto_montenegro"
#

