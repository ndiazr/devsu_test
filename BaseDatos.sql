CREATE DATABASE `test`;

-- devsudb.clients definition

CREATE TABLE `clients` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `age` int NOT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `state` bit(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- devsudb.accounts definition

CREATE TABLE `accounts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `client_id` bigint NOT NULL,
  `initial_balance` double NOT NULL,
  `number` varchar(255) NOT NULL,
  `state` bit(1) NOT NULL,
  `type` varchar(255) NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgymog7firrf8bnoiig61666ob` (`client_id`),
  CONSTRAINT `FKgymog7firrf8bnoiig61666ob` FOREIGN KEY (`client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `accounts_chk_1` CHECK ((`initial_balance` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- devsudb.movements definition

CREATE TABLE `movements` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint NOT NULL,
  `amount` double NOT NULL,
  `balance` double DEFAULT NULL,
  `date` date NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK1a6nru7corjv5b2vidld4ef5r` (`account_id`),
  CONSTRAINT `FK1a6nru7corjv5b2vidld4ef5r` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`id`),
  CONSTRAINT `movements_chk_1` CHECK ((`amount` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
