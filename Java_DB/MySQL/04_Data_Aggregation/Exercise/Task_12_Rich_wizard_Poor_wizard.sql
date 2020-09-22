
SELECT
     current_row.first_name ,
       next_row.`first_name`
FROM wizzard_deposits AS `current_row`,
     wizzard_deposits AS `next_row`
WHERE  next_row.id - current_row.id = 1;