SELECT COUNT(*)
FROM `colonists`
         JOIN `travel_cards` tc on `colonists`.`id` = `tc`.`colonist_id`
         JOIN `journeys` j on j.`id` = `tc`.`journey_id`
WHERE purpose = 'Technical';