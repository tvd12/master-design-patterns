CREATE SCHEMA `book_market` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

use book_market;

CREATE TABLE IF NOT EXISTS `Author` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(120) NOT NULL,
	`code` VARCHAR(25),
    `level` VARCHAR(25),
	PRIMARY KEY (`id`),
	INDEX index_name (`name`),
	INDEX index_code (`code`),
	INDEX index_level (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Category` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(120) NOT NULL,
	PRIMARY KEY (`id`),
    INDEX index_name (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS `Book` (
	`id` BIGINT NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(120) NOT NULL,
	`authorId` BIGINT NOT NULL,
	`categoryId` BIGINT NOT NULL,
    `imageName` VARCHAR(120) NOT NULL,
	PRIMARY KEY (`id`),
    INDEX index_name (`name`),
    INDEX index_author_id (`authorId`),
    INDEX index_category_id (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
