-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jan 19, 2021 at 05:07 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: library
--
CREATE DATABASE IF NOT EXISTS library DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE library;

-- --------------------------------------------------------

--
-- Table structure for table author
--

DROP TABLE IF EXISTS author;
CREATE TABLE author (
  id int(11) NOT NULL,
  name varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table author
--

INSERT INTO author (id, name) VALUES
(1, 'Author Test ONE'),
(2, 'Author Test TWO'),
(3, 'Test Insert'),
(4, 'Test Insert'),
(5, 'Test Insert'),
(6, 'Test Insert'),
(15, 'Test Insert'),
(16, 'Test Insert'),
(17, 'Test Insert'),
(18, 'TEST CREATE');

-- --------------------------------------------------------

--
-- Table structure for table book
--

DROP TABLE IF EXISTS book;
CREATE TABLE book (
  id int(11) NOT NULL,
  name varchar(255) NOT NULL,
  author_id int(11) DEFAULT NULL,
  category_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table book
--

INSERT INTO book (id, name, author_id, category_id) VALUES
(1, 'Book ONE', 1, 2),
(2, 'Book TWO', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table book_item
--

DROP TABLE IF EXISTS book_item;
CREATE TABLE book_item (
  id int(11) NOT NULL,
  book_id int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table book_item
--

INSERT INTO book_item (id, book_id) VALUES
(13, 1),
(11, 2),
(12, 2);

-- --------------------------------------------------------

--
-- Table structure for table borrow_history
--

DROP TABLE IF EXISTS borrow_history;
CREATE TABLE borrow_history (
  id int(11) NOT NULL,
  member_id int(11) DEFAULT NULL,
  staff_id int(11) DEFAULT NULL,
  book_item_id int(11) DEFAULT NULL,
  borrowed_at datetime NOT NULL DEFAULT current_timestamp(),
  returned_at datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table category
--

DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id int(11) NOT NULL,
  name varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table category
--

INSERT INTO category (id, name) VALUES
(1, 'Cate ONE'),
(2, 'Cate TWO');

-- --------------------------------------------------------

--
-- Table structure for table member
--

DROP TABLE IF EXISTS member;
CREATE TABLE member (
  id int(11) NOT NULL,
  full_name varchar(255) NOT NULL,
  phone varchar(255) DEFAULT NULL,
  email varchar(255) NOT NULL,
  gender enum('male','female','other') NOT NULL,
  is_active tinyint(1) DEFAULT 1,
  created_at timestamp NOT NULL DEFAULT current_timestamp(),
  updated_at timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  deleted_at timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table staff
--

DROP TABLE IF EXISTS staff;
CREATE TABLE staff (
  id int(11) NOT NULL,
  full_name varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  gender enum('male','female','other') NOT NULL,
  is_active tinyint(1) DEFAULT 1,
  created_at timestamp NOT NULL DEFAULT current_timestamp(),
  updated_at timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  deleted_at timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table warehouse_history
--

DROP TABLE IF EXISTS warehouse_history;
CREATE TABLE warehouse_history (
  id int(11) NOT NULL,
  staff_id int(11) DEFAULT NULL,
  book_item_id int(11) DEFAULT NULL,
  created_at datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table author
--
ALTER TABLE author
  ADD PRIMARY KEY (id);

--
-- Indexes for table book
--
ALTER TABLE book
  ADD PRIMARY KEY (id),
  ADD KEY author_id (author_id),
  ADD KEY category_id (category_id);

--
-- Indexes for table book_item
--
ALTER TABLE book_item
  ADD PRIMARY KEY (id),
  ADD KEY book_id (book_id);

--
-- Indexes for table borrow_history
--
ALTER TABLE borrow_history
  ADD PRIMARY KEY (id),
  ADD KEY member_id (member_id),
  ADD KEY staff_id (staff_id),
  ADD KEY book_item_id (book_item_id);

--
-- Indexes for table category
--
ALTER TABLE category
  ADD PRIMARY KEY (id);

--
-- Indexes for table member
--
ALTER TABLE member
  ADD PRIMARY KEY (id);

--
-- Indexes for table staff
--
ALTER TABLE staff
  ADD PRIMARY KEY (id);

--
-- Indexes for table warehouse_history
--
ALTER TABLE warehouse_history
  ADD PRIMARY KEY (id),
  ADD KEY staff_id (staff_id),
  ADD KEY book_item_id (book_item_id);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table author
--
ALTER TABLE author
  MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table book
--
ALTER TABLE book
  MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table book_item
--
ALTER TABLE book_item
  MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table borrow_history
--
ALTER TABLE borrow_history
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table category
--
ALTER TABLE category
  MODIFY id int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table member
--
ALTER TABLE member
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table staff
--
ALTER TABLE staff
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table warehouse_history
--
ALTER TABLE warehouse_history
  MODIFY id int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table book
--
ALTER TABLE book
  ADD CONSTRAINT book_ibfk_1 FOREIGN KEY (author_id) REFERENCES author (id),
  ADD CONSTRAINT book_ibfk_2 FOREIGN KEY (category_id) REFERENCES category (id);

--
-- Constraints for table book_item
--
ALTER TABLE book_item
  ADD CONSTRAINT book_item_ibfk_1 FOREIGN KEY (book_id) REFERENCES book (id);

--
-- Constraints for table borrow_history
--
ALTER TABLE borrow_history
  ADD CONSTRAINT borrow_history_ibfk_1 FOREIGN KEY (member_id) REFERENCES member (id),
  ADD CONSTRAINT borrow_history_ibfk_2 FOREIGN KEY (staff_id) REFERENCES staff (id),
  ADD CONSTRAINT borrow_history_ibfk_3 FOREIGN KEY (book_item_id) REFERENCES book_item (id);

--
-- Constraints for table warehouse_history
--
ALTER TABLE warehouse_history
  ADD CONSTRAINT warehouse_history_ibfk_1 FOREIGN KEY (staff_id) REFERENCES staff (id),
  ADD CONSTRAINT warehouse_history_ibfk_2 FOREIGN KEY (book_item_id) REFERENCES book_item (id);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;