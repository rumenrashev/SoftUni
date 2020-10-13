CREATE PROCEDURE udp_modify_user(address_variable VARCHAR(30),
                                 town_variable VARCHAR(30))
BEGIN
    UPDATE `users` AS `u`
        JOIN `addresses` AS `a`
        ON `u`.`id` = `a`.`user_id`
    SET `u`.`age` = `u`.`age` + 10
    WHERE address_variable = `a`.address
      AND `a`.`town` = town_variable;
END;
