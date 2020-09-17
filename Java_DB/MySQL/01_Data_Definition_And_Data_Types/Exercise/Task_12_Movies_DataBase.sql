CREATE TABLE `directors`(
	`id` INT AUTO_INCREMENT,
	`director_name` VARCHAR(50) NOT NULL,
    `notes` TEXT,
    
    PRIMARY KEY(`id`)
);

INSERT INTO `directors`(`director_name`,`notes`)
VALUES
	("name1","note1"),
	("name2","note2"),
	("name3","note3"),
	("name4","note4"),
	("name5","note5");

CREATE TABLE `genres` (
    `id` INT AUTO_INCREMENT,
    `genre_name` VARCHAR(50) NOT NULL,
    `notes` TEXT,
    PRIMARY KEY (`id`)
);

INSERT INTO `genres`(`genre_name`,`notes`)
VALUES
	("name1","note1"),
	("name2","note2"),
	("name3","note3"),
	("name4","note4"),
	("name5","note5");

CREATE TABLE `categories` (
    `id` INT AUTO_INCREMENT,
    `category_name` VARCHAR(50) NOT NULL,
    `notes` TEXT,
    PRIMARY KEY (`id`)
);

INSERT INTO `categories`(`category_name`,`notes`)
VALUES
	("name1","note1"),
	("name2","note2"),
	("name3","note3"),
	("name4","note4"),
	("name5","note5");

CREATE TABLE `movies`(
	 `id` INT NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(50) NOT NULL,
    `director_id` INT NOT NULL,
    `copyright_year` YEAR,
	`length` INT,
    `genre_id` INT,
    `category_id` INT,
    `rating` DOUBLE,
    `notes` TEXT,
    
    PRIMARY KEY(`id`)
);

INSERT INTO `movies`(`title`,`director_id`)
VALUES
	("title1",1),
	("title2",2),
	("title3",3),
	("title4",4),
	("title5",5);