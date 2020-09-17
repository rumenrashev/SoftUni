CREATE TABLE `categories`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`category` VARCHAR(50) NOT NULL,
    `daily_rate` DOUBLE,
    `weekly_rate` DOUBLE,
    `monthly_rate` DOUBLE,
    `weekend_rate` DOUBLE
);

INSERT INTO `categories`(`category`)
VALUES
("category1"),
("category2"),
("category3");

CREATE TABLE `cars`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `plate_number` VARCHAR(50) NOT NULL UNIQUE,
    `make` VARCHAR(50),
    `model` VARCHAR(50),
    `car_year` YEAR,
    `category_id` INT,
    `doors` INT,
    `picture` BLOB,
    `car_condition` VARCHAR(50),
    `available` BOOL
);

INSERT INTO `cars` (`plate_number`)
VALUES ("123"), ("234"), ("3456");

CREATE TABLE `employees`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(50) NOT NULL,
    `last_name` VARCHAR(50) NOT NULL,
    `title` VARCHAR(50),
    `notes` TEXT
);

INSERT INTO `employees`(`first_name`,`last_name`)
VALUES
("name1`","name1"),
("name2`","name2"),
("name3`","name4");

CREATE TABLE `customers`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `driver_licence_number` INT NOT NULL,
    `full_name` VARCHAR(100) NOT NULL,
    `address` VARCHAR(255),
    `city` VARCHAR(50),
    `zip_code` INT,
    `notes` TEXT
);

INSERT INTO `customers` (`driver_licence_number`,`full_name`)
VALUES
("123","name1 name1"),
("234","name2 name3"),
("456","name4 name5");

CREATE TABLE `rental_orders`(
	`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `employee_id` INT NOT NULL,
    `customer_id` INT NOT NULL,
    `car_id` INT NOT NULL,
    `car_condition` VARCHAR(50),
    `tank_level` DOUBLE,
    `kilometrage_start` INT,
    `kilometrage_end` INT,
	`total_kilometrage` INT,
    `start_date` DATE,
    `end_date` DATE,
    `total_days` INT,
    `rate_applied` DOUBLE,
    `tax_rate` DOUBLE,
    `order_status` VARCHAR(50),
	`notes` TEXT
);

INSERT INTO `rental_orders`(`employee_id`,`customer_id`,`car_id`)
VALUES
(1,2,3),
(3,4,5),
(6,7,8);