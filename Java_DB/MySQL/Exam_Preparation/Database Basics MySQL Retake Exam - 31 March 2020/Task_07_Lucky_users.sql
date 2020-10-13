SELECT CONCAT(`u`.`id`,' ',`u`.`username`) AS `id_username`,
       `email` AS `email`
FROM `users` AS `u`
         INNER JOIN `users_photos` AS `up`
                    ON `u`.`id` = `up`.`user_id`
         INNER JOIN `photos` AS `p`
                    ON `up`.photo_id = `p`.`id`
WHERE `u`.`id` = `p`.`id`
ORDER BY `u`.`id` ASC;