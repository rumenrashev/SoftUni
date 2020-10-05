SELECT `journeys`.`id`      AS `id`,
       `p`.`name`           AS `planet_name`,
       `s`.`name`           AS `spaceport_name`,
       `journeys`.`purpose` AS `jorney_purpose`
FROM `journeys`
         JOIN `spaceports` s on `s`.`id` = `journeys`.`destination_spaceport_id`
         JOIN `planets` `p` on `p`.`id` = `s`.`planet_id`
ORDER BY (journey_end - journey_start) ASC
LIMIT 1;