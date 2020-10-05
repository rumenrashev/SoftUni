DELETE `colonists`
FROM `colonists`
         LEFT JOIN `travel_cards` tc on `colonists`.`id` = `tc`.`colonist_id`
WHERE `journey_id` IS NULL;