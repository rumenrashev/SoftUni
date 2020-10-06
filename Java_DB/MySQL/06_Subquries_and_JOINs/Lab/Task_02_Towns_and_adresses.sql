SELECT `t`.`town_id`,
       `t`.`name`,
       `a`.`address_text`

FROM `towns` as `t`
         INNER JOIN `addresses` AS `a` ON `t`.town_id = `a`.town_id
WHERE `t`.`name` = 'Sofia'
   OR `t`.`name` = 'San Francisco'
   OR `t`.`name` = 'Carnation'
ORDER BY `t`.`town_id`, `a`.`address_id`;