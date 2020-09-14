DROP DATABASE `Hotel`;

CREATE DATABASE `Hotel`;

USE `Hotel`;

CREATE TABLE `employees`(
	`id`			 INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name`	 VARCHAR(50) NOT NULL,
    `last_name` 	 VARCHAR(50) NOT NULL,
    `title` 		 VARCHAR(50),
    `notes` 		 TEXT
);

INSERT INTO `employees`(
	`first_name`,
	`last_name`,
	`title`,
	`notes`)
VALUES
	("Ivan","Ivanov","trainee","poor"),
	("Georgy","Georgiev","junior","ok"),
	("Peter","Petrov","senior","excellent");

CREATE TABLE `customers`(
	`account_number` 	INT UNIQUE PRIMARY KEY,
	`first_name` 		VARCHAR(50) NOT NULL,
    `last_name` 		VARCHAR(50) NOT NULL,
    `phone_number` 		INT,
    `emergency_name` 	VARCHAR(50),
    `emergency_number` 	INT,
    `notes` 			TEXT
);

INSERT INTO `customers`(
    `account_number`,
	`first_name`,
    `last_name`,
    `phone_number`,
    `emergency_name`,
    `emergency_number`,
    `notes`)
VALUES
	(1,"Ivan","Ivanov",088,"Gosho",087,"a"),
	(2,"Georgy","Georgiev",087,"Pesho",089,"b"),
	(3,"Peter","Petrov",089,"Vanio",088,"c");

CREATE TABLE `room_status`(
	`room_status` 	VARCHAR(50) UNIQUE NOT NULL PRIMARY KEY,
    `notes` 	  	TEXT
);

INSERT INTO `room_status`(`room_status`)
VALUES
	("ready"),
	("for cleaning"),
	("renovation");

CREATE TABLE `room_types`(
	`room_type` 	VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
    `notes` 		TEXT
);

INSERT INTO `room_types`(`room_type`)
VALUES
	("ordinary room"),
	("studio"),
	("apartment");

CREATE TABLE `bed_types`(
	`bed_type` 	VARCHAR(50) NOT NULL UNIQUE PRIMARY KEY,
    `notes` 	TEXT
    );
    
INSERT INTO `bed_types`(`bed_type`)
VALUES
	("single bed"),
	("bedchamber"),
	("sofa");

CREATE TABLE `rooms`(
	`room_number` 	INT NOT NULL UNIQUE PRIMARY KEY,
	`room_type` 	VARCHAR(50),
	`bed_type` 		VARCHAR(50),
    `rate` 			DOUBLE,
	`room_status` 	VARCHAR(50),
    `notes` 		TEXT
);

INSERT INTO `rooms`(
	`room_number`,
	`room_type`,
	`bed_type`,
    `rate`,
	`room_status`,
    `notes`
)
VALUES
(1,"single room","single bed","1.0","ready","a"),
(2,"studio","bedchamber","2.0","for cleaning","b"),
(3,"apartment","sofa","3.0","renovation","c");

CREATE TABLE `payments`(
	`id` 					INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`employee_id` 			INT ,
	`payment_date` 			DATE ,
    `account_number` 		INT,
    `first_date_occupied` 	DATE,
    `last_date_occupied` 	DATE,
	`total_days` 			INT,
    `amount_charged` 		DOUBLE,
    `tax_rate` 				DOUBLE,
    `tax_amount`			DOUBLE,
    `payment_total` 		DOUBLE,
	`notes` 				TEXT
);

INSERT INTO `payments`(
	`employee_id`,
	`payment_date`,
    `account_number`,
    `first_date_occupied`,
    `last_date_occupied`,
	`total_days`,
    `amount_charged`,
    `tax_rate` ,
    `tax_amount` ,
    `payment_total`,
	`notes` )
VALUES
(1,"2020-01-01",1,"2020-01-02","2020-01-03",1,1.0,2.0,3.0,4.0,"a"),
(2,"2020-01-01",1,"2020-01-02","2020-01-03",1,1.0,2.0,3.0,4.0,"a"),
(3,"2020-01-01",1,"2020-01-02","2020-01-03",1,1.0,2.0,3.0,4.0,"a");

CREATE TABLE `occupancies`(
	`id` 				INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`employee_id` 		INT ,
    `date_occupied` 	DATE ,
	`account_number` 	INT ,
    `room_number` 		INT ,
    `rate_applied` 		DOUBLE,
    `phone_charge` 		INT,
	`notes` 			TEXT
);

INSERT INTO `occupancies`(
	`employee_id`,
    `date_occupied` ,
	`account_number` ,
    `room_number` ,
    `rate_applied` ,
    `phone_charge` ,
	`notes` 
)
VALUES
	(1,"2020-01-02",1,1,1.0,087,"A"),
	(2,"2020-01-02",1,1,1.0,087,"A"),
	(3,"2020-01-02",1,1,1.0,087,"A");