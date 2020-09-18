SELECT 
    `first_name`, `last_name`
FROM
    `employees`
WHERE
    LOWER(`first_name`) LIKE 'Sa%'
ORDER BY `employee_id` ASC;