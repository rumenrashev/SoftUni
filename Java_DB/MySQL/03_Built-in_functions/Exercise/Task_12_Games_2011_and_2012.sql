SELECT 
    `name`, DATE_FORMAT(`start`,'%Y-%m-%d') AS `start`
FROM
    `games`
WHERE
    (SELECT YEAR(`start`)) BETWEEN 2011 AND 2012
ORDER BY `start` ASC , `name` ASC
LIMIT 50;