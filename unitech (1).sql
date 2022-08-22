-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 22, 2022 at 07:56 AM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `unitech`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounts`
--

CREATE TABLE `accounts` (
  `id` bigint(20) NOT NULL,
  `balance` double NOT NULL,
  `status` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `accounts`
--

INSERT INTO `accounts` (`id`, `balance`, `status`, `user_id`) VALUES
(1, 4, 1, 1),
(2, 12, 1, 2),
(3, 3, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `currency`
--

CREATE TABLE `currency` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `rate` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `currency`
--

INSERT INTO `currency` (`id`, `created_date`, `code`, `rate`) VALUES
(1, '2022-08-22 09:56:00', 'AZN', 1.8005882732224012),
(2, '2022-08-22 09:56:00', 'TL', 18.160588273222398),
(3, '2022-08-22 09:56:00', 'AED', 3.770588273222401),
(4, '2022-08-22 09:56:00', 'USD', 1.1005882732224013);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pin` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `password`, `pin`) VALUES
(1, 'sha1:128000:72:e5EkD47NIS1f8aLq9RjmMZVs9CgumtjGSQTViP0cDM59m1+ffqbiPDV/hEt9EXPieSPlBsj34t6eWmkR5q9uIEyqVOO43w+V:DRM0sktpxUyyj7WeQFCzNVvs0jBtHrXsxV/xituj4ulhQzVdlQsZE9lWzJ9CIW80C26sPX/OcXPNPRrn0+0k8O89UDwrfCum', 'orxan'),
(2, 'sha1:128000:72:e5EkD47NIS1f8aLq9RjmMZVs9CgumtjGSQTViP0cDM59m1+ffqbiPDV/hEt9EXPieSPlBsj34t6eWmkR5q9uIEyqVOO43w+V:DRM0sktpxUyyj7WeQFCzNVvs0jBtHrXsxV/xituj4ulhQzVdlQsZE9lWzJ9CIW80C26sPX/OcXPNPRrn0+0k8O89UDwrfCum', 'samir'),
(3, 'sha1:128000:72:e5EkD47NIS1f8aLq9RjmMZVs9CgumtjGSQTViP0cDM59m1+ffqbiPDV/hEt9EXPieSPlBsj34t6eWmkR5q9uIEyqVOO43w+V:DRM0sktpxUyyj7WeQFCzNVvs0jBtHrXsxV/xituj4ulhQzVdlQsZE9lWzJ9CIW80C26sPX/OcXPNPRrn0+0k8O89UDwrfCum', 'rusif'),
(4, 'sha1:128000:72:e5EkD47NIS1f8aLq9RjmMZVs9CgumtjGSQTViP0cDM59m1+ffqbiPDV/hEt9EXPieSPlBsj34t6eWmkR5q9uIEyqVOO43w+V:DRM0sktpxUyyj7WeQFCzNVvs0jBtHrXsxV/xituj4ulhQzVdlQsZE9lWzJ9CIW80C26sPX/OcXPNPRrn0+0k8O89UDwrfCum', 'kamil');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounts`
--
ALTER TABLE `accounts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKnjuop33mo69pd79ctplkck40n` (`user_id`);

--
-- Indexes for table `currency`
--
ALTER TABLE `currency`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounts`
--
ALTER TABLE `accounts`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `currency`
--
ALTER TABLE `currency`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `accounts`
--
ALTER TABLE `accounts`
  ADD CONSTRAINT `FKnjuop33mo69pd79ctplkck40n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
