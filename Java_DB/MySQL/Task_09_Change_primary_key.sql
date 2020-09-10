    ALTER TABLE `users`
		DROP PRIMARY KEY,
        ADD CONSTRAINT
        PRIMARY KEY(`id`,`username`);