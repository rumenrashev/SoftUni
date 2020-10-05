SELECT c.`id` , CONCAT_WS(' ',first_name,last_name) as `full_name`
FROM `travel_cards` JOIN `colonists` c on c.id = `travel_cards`.`colonist_id`
WHERE `job_during_journey` = 'Pilot'
ORDER BY `id`;