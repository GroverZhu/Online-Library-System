DROP DATABASE IF EXISTS `librarysystem`;

CREATE DATABASE `librarysystem` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE `librarysystem`;

CREATE TABLE `reader` (
  `reader_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reader_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `reader_password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `reader_email` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `state` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`reader_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000 CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `librarian` (
  `librarian_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `librarian_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `state` varchar(8) COLLATE utf8mb4_bin NOT NULL,
  `librarian_password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`librarian_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `administrator` (
  `administrator_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `administrator_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `administrator_password` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`administrator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `author` (
  `author_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `author_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `author_description` text COLLATE utf8mb4_bin,
  PRIMARY KEY (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `publisher` (
  `publisher_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `publisher_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `publisher_description` text COLLATE utf8mb4_bin,
  PRIMARY KEY (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

CREATE TABLE `book` (
  `isbn` varchar(13) COLLATE utf8mb4_bin NOT NULL,
  `book_price` decimal(10,2) NOT NULL,
  `book_name` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `book_description` text COLLATE utf8mb4_bin,
  `publisher_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`isbn`),
  KEY `publisher_id` (`publisher_id`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`publisher_id`) REFERENCES `publisher` (`publisher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `book_in_library` (
  `book_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `isbn` varchar(13) COLLATE utf8mb4_bin NOT NULL,
  `book_location` varchar(50) COLLATE utf8mb4_bin NOT NULL,
  `state` varchar(9) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`book_id`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `book_in_library_ibfk_1` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `writes` (
  `author_id` int(10) unsigned NOT NULL,
  `isbn` varchar(13) COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`author_id`,`isbn`),
  KEY `isbn` (`isbn`),
  CONSTRAINT `writes_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`author_id`),
  CONSTRAINT `writes_ibfk_2` FOREIGN KEY (`isbn`) REFERENCES `book` (`isbn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `borrow_cart` (
  `book_id` int(10) unsigned NOT NULL,
  `reader_id` int(10) unsigned NOT NULL,
  `submit_time`datetime,
  PRIMARY KEY (`book_id`,`reader_id`),
  KEY `reader_id` (`reader_id`),
  CONSTRAINT `borrow_cart_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book_in_library` (`book_id`),
  CONSTRAINT `borrow_cart_ibfk_2` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



CREATE TABLE `borrow_item` (
  `borrow_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reader_id` int(10) unsigned NOT NULL,
  `book_id` int(10) unsigned NOT NULL,
  `borrow_librarian_id` int(10) unsigned NOT NULL,
  `return_librarian_id` int(10) unsigned,
  `borrow_time` datetime NOT NULL,
  `return_time` datetime,
  PRIMARY KEY (`borrow_id`),
  KEY `reader_id` (`reader_id`),
  KEY `book_id` (`book_id`),
  KEY `borrow_librarian_id` (`borrow_librarian_id`),
  KEY `return_librarian_id` (`return_librarian_id`),
  CONSTRAINT `borrow_item_ibfk_1` FOREIGN KEY (`reader_id`) REFERENCES `reader` (`reader_id`),
  CONSTRAINT `borrow_item_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book_in_library` (`book_id`),
  CONSTRAINT `borrow_item_ibfk_3` FOREIGN KEY (`borrow_librarian_id`) REFERENCES `librarian` (`librarian_id`),
  CONSTRAINT `borrow_item_ibfk_4reader` FOREIGN KEY (`return_librarian_id`) REFERENCES `librarian` (`librarian_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;