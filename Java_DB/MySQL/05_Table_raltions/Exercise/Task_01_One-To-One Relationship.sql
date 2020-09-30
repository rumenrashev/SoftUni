CREATE TABLE `passports`(
    `passport_id` INT PRIMARY KEY AUTO_INCREMENT,
    `passport_number` VARCHAR(50) UNIQUE
);

CREATE TABLE `persons`(
    `person_id` INT AUTO_INCREMENT PRIMARY KEY ,
    `first_name` VARCHAR(50),
    `salary` DOUBLE,
    `passport_id` INT,
    FOREIGN KEY (`passport_id`)
                      REFERENCES `passports`(`passport_id`)
);