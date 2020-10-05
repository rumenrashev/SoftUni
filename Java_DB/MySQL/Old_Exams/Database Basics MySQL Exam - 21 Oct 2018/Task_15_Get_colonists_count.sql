CREATE FUNCTION udf_count_colonists_by_destination_planet(planet_name VARCHAR(30))
    RETURNS INT
BEGIN
    DECLARE `c_count` INT;
    SET c_count := (
        SELECT count(c.id)
        FROM `colonists` c
                 JOIN `travel_cards` tc on `c`.`id` = `tc`.`colonist_id`
                 JOIN `journeys` j on `tc`.`journey_id` = `j`.`id`
                 JOIN `spaceports` s on `j`.`destination_spaceport_id` = `s`.`id`
                 JOIN `planets` p on `s`.`planet_id` = `p`.`id`
        WHERE `p`.`name`= `planet_name`
    );
    RETURN `c_count`;
END;