-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Mar 26 Avril 2022 à 10:34
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `hotelmanagementdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE IF NOT EXISTS `client` (
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `id` varchar(45) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `birthDate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `client`
--

INSERT INTO `client` (`firstName`, `lastName`, `id`, `phoneNumber`, `birthDate`, `email`) VALUES
('htrhrt', 'TGHFH', '23', 1324565677, '2003-02-06', 'GGDGG'),
('dherj,', 'erjnernj', 'ed58966', 8206, '2006-06-09', 'rjnejn'),
('ygg', 'sacarina', 're589612', 608971589, '2022-04-13', 'mail@gmail.com'),
('Rel', 'Rel', 'tl589621', 608947885, '2022-04-13', 'rel.rel@gmail.com');

-- --------------------------------------------------------

--
-- Structure de la table `room`
--

CREATE TABLE IF NOT EXISTS `room` (
  `id` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `available` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  FULLTEXT KEY `type` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `room`
--

INSERT INTO `room` (`id`, `type`, `price`, `available`) VALUES
('c32', 'simple', 1000, 0),
('c34', 'luxury', 2000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `idTransaction` int(45) NOT NULL AUTO_INCREMENT,
  `idClient` varchar(45) NOT NULL,
  `idReceptionist` varchar(45) NOT NULL,
  `idRoom` varchar(45) NOT NULL,
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `nature` varchar(45) NOT NULL,
  `price` double NOT NULL DEFAULT '0',
  PRIMARY KEY (`idTransaction`),
  KEY `id_client` (`idClient`),
  KEY `id_receptionnist` (`idReceptionist`),
  KEY `room_fk` (`idRoom`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Contenu de la table `transaction`
--

INSERT INTO `transaction` (`idTransaction`, `idClient`, `idReceptionist`, `idRoom`, `date`, `nature`, `price`) VALUES
(9, '23', 'rec', 'c32', '2022-04-16 19:13:19', 'Reservation', 0),
(10, '23', 'rec', 'c32', '2022-04-16 19:15:33', 'Liberation', 1000),
(12, '23', 'rec', 'c32', '2022-04-16 19:36:41', 'Reservation', 0),
(13, '23', 'rec', 'c32', '2022-04-16 19:39:00', 'Liberation', 1000),
(14, '23', 'rec', 'c34', '2022-04-16 19:40:54', 'Reservation', 0),
(15, '23', 'rec', 'c34', '2022-04-16 19:41:16', 'Liberation', -2000),
(16, '23', 'rec', 'c34', '2022-04-16 19:42:05', 'Reservation', 0),
(17, '23', 'rec', 'c34', '2022-04-16 19:42:10', 'Liberation', -2000),
(18, '23', 'rec', 'c32', '2022-04-16 19:43:46', 'Reservation', 0),
(19, '23', 'rec', 'c32', '2022-04-16 19:43:59', 'Liberation', -1000),
(20, '23', 'rec', 'c32', '2022-04-16 19:44:16', 'Reservation', 0),
(21, '23', 'rec', 'c32', '2022-04-16 19:44:18', 'Liberation', 1000);

-- --------------------------------------------------------

--
-- Structure de la table `worker`
--

CREATE TABLE IF NOT EXISTS `worker` (
  `firstName` varchar(45) NOT NULL,
  `lastName` varchar(45) NOT NULL,
  `id` varchar(45) NOT NULL,
  `phoneNumber` int(11) NOT NULL,
  `birthDate` date NOT NULL,
  `email` varchar(45) NOT NULL,
  `function` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `worker`
--

INSERT INTO `worker` (`firstName`, `lastName`, `id`, `phoneNumber`, `birthDate`, `email`, `function`, `password`) VALUES
('admin', 'admin', 'admin', 0, '2022-04-19', '', 'admin', 'admin'),
('Kibalo Jules', 'Tinaka', 'eb416362', 608917921, '2000-01-01', 'julestnk.dev@gmail.com', 'receptionist', 'Enchantress***912'),
('Sil', 'Last', 'po859654', 608957852, '2022-04-29', 'rel.rel@gmail.com', 'admin', 'afeEbtrhER***65'),
('rec', 'rec', 'rec', 0, '2022-04-21', '', 'receptionist', 'rec');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `client_fk` FOREIGN KEY (`idClient`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `room_fk` FOREIGN KEY (`idRoom`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `worker_fk` FOREIGN KEY (`idReceptionist`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
