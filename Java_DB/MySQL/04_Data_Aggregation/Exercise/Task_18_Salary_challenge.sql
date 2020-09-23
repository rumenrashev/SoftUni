USE `soft_uni`;
SELECT `first_name`, `last_name`, department_id
FROM `employees` AS t1
WHERE salary >
      (SELECT AVG(`salary`)
       FROM `employees` AS t2
       WHERE `t1`.`department_id` = `t2`.`department_id`)
ORDER BY `t1`.`department_id`, `t1`.`employee_id`
LIMIT 10;