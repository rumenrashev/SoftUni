CREATE TABLE `peaks`(
    `id` INT AUTO_INCREMENT PRIMARY KEY ,
    `name` VARCHAR(50),
    `mountain_id` INT
);

CREATE TABLE `mountains`(
  `id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY ,
  `name` VARCHAR(50)
);

ALTER TABLE `peaks`
ADD CONSTRAINT
FOREIGN KEY (`mountain_id`)
REFERENCES `mountains`(`id`)
ON DELETE CASCADE ;