    CREATE TABLE `people`(
		`id` INT UNSIGNED AUTO_INCREMENT,
        `name` VARCHAR(200) NOT NULL,
        `picture` BLOB(2000000),
        `height` DOUBLE(3,2),
        `weight` DOUBLE(3,2),
        `gender` ENUM('f','m') NOT NULL,
        `birthdate` DATE NOT NULL,
        `biography` LONGTEXT,
        
        PRIMARY KEY(`id`)
    );
    
        INSERT INTO `people`(`name`,`gender`,`birthdate`)
    VALUES
		("First",'m',"1993-01-02"),
		("Second",'m',"1993-01-02"),
		("Third",'m',"1993-01-02"),
		("Forth",'m',"1993-01-02"),
		("Fifth",'m',"1993-01-02");