SELECT
     department_id,

    ROUND(AVG(`salary`),2) AS `Number of employees`

FROM `employees`
GROUP BY   `department_id`;