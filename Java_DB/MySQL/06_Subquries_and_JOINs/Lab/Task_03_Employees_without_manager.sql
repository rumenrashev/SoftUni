USE `soft_uni`;

SELECT `employee_id`,
       `first_name`,
       `last_name`,
       `department_id`,
       `last_name`
FROM `employees`
WHERE `manager_id` IS NULL;