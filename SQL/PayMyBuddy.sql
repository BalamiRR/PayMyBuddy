CREATE DATABASE  IF NOT EXISTS `payMyBuddy`;

USE `payMyBuddy`;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` INT NOT NULL,
  `createdAt` DATETIME(6) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniqueEmail` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` INT NOT NULL,
  `createdAt` DATETIME(6) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `userID` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_account_user` (`userID`),
  CONSTRAINT `fk_account_user` FOREIGN KEY (`userID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `friends`;
CREATE TABLE `friends` (
  `id` INT NOT NULL,
  `createdAt` DATETIME(6) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `friendID` INT DEFAULT NULL,
  `userID` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_friends_friendID` (`friendID`),
  KEY `fk_friends_userID` (`userID`),
  CONSTRAINT `fk_friends_userID` FOREIGN KEY (`userID`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_friends_friendID` FOREIGN KEY (`friendID`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` INT NOT NULL,
  `createdAt` DATETIME(6) NOT NULL,
  `updatedAt` DATETIME(6) DEFAULT NULL,
  `amount` DECIMAL(19,2) DEFAULT NULL,
  `charge` DECIMAL(19,2) DEFAULT NULL,
  `description` VARCHAR(255) DEFAULT NULL,
  `transacted` CHAR(1) DEFAULT NULL,
  `fromAccountID` INT DEFAULT NULL,
  `toAccountID` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_fromAccountID` (`fromAccountID`),
  KEY `fk_transaction_toAccountID` (`toAccountID`),
  CONSTRAINT `fk_transaction_toAccountID` FOREIGN KEY (`toAccountID`) REFERENCES `account` (`id`),
  CONSTRAINT `fk_transaction_fromAccountID` FOREIGN KEY (`fromAccountID`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
