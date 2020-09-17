SELECT 
    CONCAT_WS(' ' ,`first_name`, `last_name`) AS `Full name`,
    TIMESTAMPDIFF(DAY, `born`, `died`) AS `Days lived`
FROM
    `authors`