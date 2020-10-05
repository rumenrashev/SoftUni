SELECT `spaceships`.`name`, `spaceships`.`manufacturer`
FROM `spaceships`
         JOIN `journeys` j on `spaceships`.`id` = `j`.`spaceship_id`
         JOIN `travel_cards` tc on `j`.`id` = `tc`.`journey_id`
         JOIN `colonists` c on `tc`.`colonist_id` = `c`.`id`
WHERE `job_during_journey` = 'Pilot'
  AND `birth_date` > '1989/01/01'
ORDER BY `spaceships`.`name`;
