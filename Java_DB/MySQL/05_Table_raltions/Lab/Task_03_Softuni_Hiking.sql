SELECT
       `starting_point`,
       `end_point`,
       `leader_id`,
        (SELECT CONCAT_WS(' ',`first_name`,`last_name`) FROM campers WHERE leader_id = campers.id)
FROM routes;