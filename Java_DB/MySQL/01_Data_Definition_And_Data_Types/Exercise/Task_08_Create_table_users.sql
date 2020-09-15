	CREATE TABLE `users`(
		`id` INT UNSIGNED AUTO_INCREMENT,
        `username` NVARCHAR(30) NOT NULL,
        `password` NVARCHAR(26) NOT NULL,
        `profile_picture` BLOB(900000),
		`last_login_time` TIMESTAMP,
        `is_deleted` BOOLEAN,
        
        PRIMARY KEY(`id`)
    );
    
    INSERT INTO `users`(`username`,`password`)
    VALUES
    ("first","pass1"),
    ("second","pass2"),
    ("third","pass3"),
    ("forth","pass4"),
    ("fifth","pass5");