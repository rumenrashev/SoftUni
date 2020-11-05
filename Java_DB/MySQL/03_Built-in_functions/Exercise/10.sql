DELIMITER $$
CREATE FUNCTION udf_top_paid_employee_by_store(store_name VARCHAR(50))
RETURNS VARCHAR(255)
DETERMINISTIC
BEGIN
	RETURN(
		SELECT CONCAT_WS(' ' , first_name , CONCAT(middle_name,'.') , last_name ,
        'works in store for' ,( TIMESTAMPDIFF(year,	`hire_date`,'2020-10-18')) , 'years' )
        FROM `employees` AS `e` 
        JOIN `stores` AS `s`
        ON `e`.`store_id` = `s`.`id`
        WHERE `s`.`name` = store_name
        ORDER BY `salary` DESC
        LIMIT 1
    );
END $$
DELIMITER ;

SELECT udf_top_paid_employee_by_store('Stronghold') as 'full_info';

SELECT udf_top_paid_employee_by_store('Keylex') as 'full_info';



		SELECT CONCAT_WS(' ' , first_name , middle_name , last_name ,
        'works in store for' ,( TIMESTAMPDIFF(year,`hire_date`,'2020-10-18')) , 'years' )
        FROM `employees` AS `e` 
        JOIN `stores` AS `s`
        ON `e`.`store_id` = `s`.`id`
        WHERE `s`.`name` = 'Stronghold'
        HAVING MAX(`salary`);
   