SELECT `job_during_journey`
FROM `travel_cards`
         join `journeys` j on `j`.`id` = `travel_cards`.`journey_id`
WHERE `j`.`id` = (SELECT `id`
                  FROM `journeys`
                  ORDER BY (journey_end - journey_start) DESC
                  LIMIT 1)
GROUP BY `job_during_journey`
ORDER BY COUNT(`job_during_journey`)
LIMIT 1;