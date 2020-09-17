SELECT 
    `country_name`,
    `country_code`,
    CASE `currency_code`
        WHEN 'EUR' THEN 'Euro'
        ELSE 'Not Euro'
    END AS currency
FROM
    `countries`
ORDER BY `country_name`;