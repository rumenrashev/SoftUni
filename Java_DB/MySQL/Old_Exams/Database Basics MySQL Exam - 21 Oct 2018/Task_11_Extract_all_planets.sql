SELECT `planets`.`name` AS `planet_name`,
       `s`.`name`       AS `spaceport_name`

FROM `planets`
         JOIN `spaceports` s on `planets`.`id` = `s`.`planet_id`
         JOIN `journeys` j on `s`.`id` = `j`.`destination_spaceport_id`
WHERE `j`.`purpose` = 'Educational'
ORDER BY `s`.`name` DESC;
