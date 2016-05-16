-- phpMyAdmin SQL Dump
-- version 4.1.7
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 25, 2015 at 06:23 PM
-- Server version: 5.6.15
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `hackathon`
--

-- --------------------------------------------------------

--
-- Table structure for table `participant`
--

CREATE TABLE IF NOT EXISTS `participant` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(25) DEFAULT NULL,
  `lastName` varchar(25) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `college` varchar(10) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `year` varchar(10) DEFAULT NULL,
  `appTypes` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Dumping data for table `participant`
--

INSERT INTO `participant` (`userId`, `firstName`, `lastName`, `email`, `college`, `gender`, `year`, `appTypes`) VALUES
(1, 'Charlie', 'Brown', NULL, NULL, NULL, 'Junior', NULL),
(4, 'Arturo', 'Lopez', 'arlopez@csumb.edu', 'Gavilan', 'M', 'Freshman', NULL),
(8, 'Arturo', 'Lopez', 'arlopez@csumb.edu', 'CSUMB', 'M', 'Freshman', 'Games Business');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
