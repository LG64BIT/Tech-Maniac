-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 12, 2024 at 07:41 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tech_maniac`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` int(11) NOT NULL,
  `content` text NOT NULL,
  `user_id` int(11) NOT NULL,
  `review_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`id`, `content`, `user_id`, `review_id`, `created_at`) VALUES
(1, 'This sucks', 5, 1, '2023-11-29 09:59:01'),
(3, 'OOOOOO niceee!!!', 14, 1, '2023-11-30 09:59:01'),
(4, 'What a nice shit', 5, 6, '2024-11-05 06:34:33'),
(5, 'Why do you think that?', 14, 6, '2024-01-11 17:23:42'),
(6, 'Imam ju i odlicna je!', 14, 7, '2024-01-11 17:26:15'),
(7, 'Noice', 14, 8, '2024-01-11 17:30:56'),
(8, 'Niceee', 14, 7, '2024-01-11 17:31:06'),
(9, 'Testing in progress', 5, 9, '2024-01-12 11:37:28'),
(11, 'Mine is bigger! 😈', 15, 11, '2024-01-12 15:23:56');

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `likes` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`id`, `title`, `description`, `likes`, `user_id`, `created_at`) VALUES
(1, 'GTX 4080 ti', 'Best GPU ever, comment like suscrize', 0, 5, '2023-11-29 08:51:54'),
(6, 'GTX 4090 ti', 'Too expensive in my opinion', 7, 5, '2023-11-29 08:51:54'),
(7, 'GTX 4090 ti', 'Too expensive in my opinion', 7, 5, '2023-11-29 08:51:54'),
(8, 'GTX 4080 ti', 'Best GPU ever, comment like suscrize', 2, 5, '2023-11-29 08:51:54'),
(9, 'GTX 4080 ti', 'Best GPU ever, comment like suscrize', 1, 5, '2023-11-29 08:51:54'),
(10, 'Best buy PC', 'Best buy gaming PC build up to 500$', 0, 5, '2024-01-12 12:51:39'),
(11, 'RAM challenge', 'Whos got the biggest RAM?!?!', 1, 5, '2024-01-12 12:52:27'),
(12, 'test', 'test', 0, 5, '2024-01-12 12:54:59');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `bio` text DEFAULT NULL,
  `role` int(1) NOT NULL,
  `activity` tinyint(1) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `first_name`, `last_name`, `username`, `password`, `bio`, `role`, `activity`, `created_at`) VALUES
(5, 'Mirko', 'Maric', 'lg64bit', '$2a$10$.X2FbOATIoSPx2/aTiOpu.c6nPVJaTtsj1iyhIOJKxnidDdCyPJom', 'I love programming', 0, 1, '2023-11-29 16:53:30'),
(11, 'tester', 'testercic', 'tester', '$2a$10$Ab3MeNhkhgYI5IKSf71ucukvDn.jqPfDr2yhs.b8oa/Ywmbl3lD6G', 'I love programming', 1, 1, '2023-11-29 16:53:30'),
(13, 'asdf', '123', 'asdf123', '$2a$10$TsEfiKyEzXndApPOUoKSdOLeqzKlYwJGmdE79wdXB34fE3x7jHMxS', 'ja sam asdf123\nBitch', 1, 0, '2024-01-09 20:51:14'),
(14, 'Tea', 'Viljanac', 'tviljanac', '$2a$10$8Lcqwwwqgx1uPPbeNyEjvei1FFbmJC0GMMqgJVuQG5E6/xdA1Jiti', '', 1, 1, '2024-01-11 17:05:56'),
(15, 'Test', 'Tester', 'tester1', '$2a$10$3pUC2dz04S1Cf/0DnLSLw.mvHqXuEEZTkcc.0Or4B023BW7cUenHC', 'Testiram', 1, 0, '2024-01-12 15:23:10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
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
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
