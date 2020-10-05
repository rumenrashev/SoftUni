SELECT `s`.`name`, `sp`.`name`
FROM `spaceports` AS sp
         JOIN `journeys` j on `sp`.`id` = `j`.`destination_spaceport_id`
         JOIN `spaceships` s on `s`.`id` = `j`.`spaceship_id`
ORDER BY `light_speed_rate` DESC
LIMIT 1;