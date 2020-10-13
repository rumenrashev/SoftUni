CREATE FUNCTION udf_users_photos_count(username VARCHAR(30))
RETURNS INT
DETERMINISTIC
    BEGIN
RETURN (SELECT COUNT(`p`.`id`)
        FROM `users` AS `u`
                 LEFT JOIN `users_photos` AS `up`
                           ON `u`.id = `up`.user_id
                 LEFT JOIN `photos` AS `p`
                           ON `up`.photo_id = `p`.`id`
        WHERE `u`.`username` = username
        GROUP BY `u`.`id`);
END;