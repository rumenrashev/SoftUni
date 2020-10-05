SELECT `p`.`name` AS `planet_name`, COUNT(*) AS `jorney_count`
FROM `journeys` AS j
         JOIN `spaceports` s on `s`.`id` = `j`.`destination_spaceport_id`
         JOIN `planets` p on `s`.`planet_id` = `p`.`id`
GROUP BY `p`.`id`
ORDER BY `jorney_count` DESC, `p`.`name`;
