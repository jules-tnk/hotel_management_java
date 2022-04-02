-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 02 Avril 2022 à 15:42
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
('c32', 'simple', 1000, 1);

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `id_transaction` varchar(45) NOT NULL,
  `id_client` varchar(45) NOT NULL,
  `id_receptionnist` varchar(45) NOT NULL,
  `id_room` varchar(45) NOT NULL,
  `date` date NOT NULL,
  `nature` varchar(45) NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id_client`,`id_receptionnist`,`id_room`,`date`,`nature`),
  KEY `id_client` (`id_client`),
  KEY `id_receptionnist` (`id_receptionnist`),
  KEY `room_fk` (`id_room`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `transaction`
--

INSERT INTO `transaction` (`id_transaction`, `id_client`, `id_receptionnist`, `id_room`, `date`, `nature`, `price`) VALUES
('p45', 'tl589621', 'eb416362', 'c32', '2022-04-02', 'prise', 2000);

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
('Kibalo Jules', 'Tinaka', 'eb416362', 608917921, '2000-01-01', 'julestnk.dev@gmail.com', 'receptionnist', 'Enchantress***912');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `client_fk` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `room_fk` FOREIGN KEY (`id_room`) REFERENCES `room` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `worker_fk` FOREIGN KEY (`id_receptionnist`) REFERENCES `worker` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
