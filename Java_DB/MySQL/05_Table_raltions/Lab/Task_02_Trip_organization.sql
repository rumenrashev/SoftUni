SELECT `driver_id`,
       `vehicle_type`,
       (SELECT CONCAT_WS(  ' ',
           `first_name`,last_name) 
       FROM campers 
       WHERE driver_id = `id`)
FROM `vehicles`;