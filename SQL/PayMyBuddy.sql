CREATE DATABASE  IF NOT EXISTS `payMyBuddy`;

USE `payMyBuddy`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NOT NULL,
  `updated_at` DATETIME(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqueEmail` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `created_at` DATETIME(6) NOT NULL,
  `updated_at` DATETIME(6) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_user_id` (`user_id`),
  CONSTRAINT `fk_account_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
  ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` INT NOT NULL,
  `created_at` DATETIME(6) NOT NULL,
  `updated_at` DATETIME(6) DEFAULT NULL,
  `friend_id` INT DEFAULT NULL,
  `user_id` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_friends_friend_id` (`friend_id`),
  KEY `fk_friends_user_id` (`user_id`),
  CONSTRAINT `fk_friends_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
	ON DELETE CASCADE,
  CONSTRAINT `fk_friends_friend_id` FOREIGN KEY (`friend_id`) REFERENCES `user` (`id`)
	ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` INT NOT NULL,
  `created_at` DATETIME(6) NOT NULL,
  `updated_at` DATETIME(6) DEFAULT NULL,
  `amount` DECIMAL(19,2) DEFAULT NULL,
  `charge` DECIMAL(19,2) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `status` ENUM('INITIATED', 'PROCESSING', 'SUCCESS', 'FAILED') DEFAULT 'INITIATED',
  `sender` INT NOT NULL,
  `receiver` INT NOT NULL,
  `account_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_sender` (`sender`),
  KEY `fk_transaction_receiver` (`receiver`),
  KEY `fk_transaction_account` (`account_id`),
  CONSTRAINT `fk_transaction_sender` FOREIGN KEY (`sender`) REFERENCES `user` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_transaction_receiver` FOREIGN KEY (`receiver`) REFERENCES `user` (`id`)
    ON DELETE CASCADE,
  CONSTRAINT `fk_transaction_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
    ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `transaction`;
DROP TABLE IF EXISTS `friends`;
DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `user`;