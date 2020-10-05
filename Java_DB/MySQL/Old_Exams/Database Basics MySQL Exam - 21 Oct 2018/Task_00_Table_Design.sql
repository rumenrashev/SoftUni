DROP DATABASE IF EXISTS `colonial_journey_management_system_db`;

CREATE DATABASE `colonial_journey_management_system_db`;

USE `colonial_journey_management_system_db`;

CREATE TABLE `planets`
(
    `id`   INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(30) NOT NULL
);

CREATE TABLE `spaceships`
(
    `id`               INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`             VARCHAR(50) NOT NULL,
    `manufacturer`     VARCHAR(30) NOT NULL,
    `light_speed_rate` INT(11) DEFAULT 0
);

CREATE TABLE `colonists`
(
    `id`         INT(11) AUTO_INCREMENT PRIMARY KEY,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name`  VARCHAR(20) NOT NULL,
    `ucn`        CHAR(10)    NOT NULL UNIQUE,
    `birth_date` DATE        NOT NULL

);


CREATE TABLE `spaceports`
(
    `id`        INT(11) AUTO_INCREMENT PRIMARY KEY,
    `name`      VARCHAR(50) NOT NULL,
    `planet_id` INT(11)
);

CREATE TABLE `journeys`
(
    `id`                       INT(11) AUTO_INCREMENT PRIMARY KEY,
    `journey_start`            DATETIME                                                 NOT NULL,
    `journey_end`              DATETIME                                                 NOT NULL,
    `purpose`                  ENUM ('Medical', 'Technical', 'Educational', 'Military') NOT NULL,
    `destination_spaceport_id` INT(11),
    `spaceship_id`             INT(11)

);

CREATE TABLE `travel_cards`
(
    `id`                 INT(11) AUTO_INCREMENT PRIMARY KEY,
    `card_number`        CHAR(10)                                                 NOT NULL,
    `job_during_journey` ENUM ('Pilot', 'Engineer', 'Trooper', 'Cleaner', 'Cook') NOT NULL,
    `colonist_id`        INT(11),
    `journey_id`         INT(11)
);

ALTER TABLE `spaceports`
    ADD CONSTRAINT fk_spaceports_planets
        FOREIGN KEY (`planet_id`)
            REFERENCES `planets` (`id`);

ALTER TABLE `journeys`
    ADD CONSTRAINT fk_journeys_spaceports
        FOREIGN KEY (`destination_spaceport_id`)
            REFERENCES `spaceports` (`id`);

ALTER TABLE `journeys`
    ADD CONSTRAINT fk_journeys_spaceships
        FOREIGN KEY (`spaceship_id`)
            REFERENCES `spaceships` (id);

ALTER TABLE `travel_cards`
    ADD CONSTRAINT fk_travel_cards_journeys
        FOREIGN KEY (`journey_id`)
            REFERENCES `journeys` (id);

ALTER TABLE `travel_cards`
    ADD CONSTRAINT fk_travel_cards_colonists
        FOREIGN KEY (`colonist_id`)
            REFERENCES `colonists` (id);


