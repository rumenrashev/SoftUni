SELECT CONCAT(SUBSTR(`description`,1,30),'...') AS `summary`,
       `date` AS `date`
FROM `photos`
WHERE DAY(date) = 10
ORDER BY `date` DESC;