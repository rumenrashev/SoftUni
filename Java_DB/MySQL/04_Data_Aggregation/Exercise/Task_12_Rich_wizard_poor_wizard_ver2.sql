USE `gringotts`;

SELECT SUM(
                   `deposit_amount` -
                   (SELECT `deposit_amount`
                    FROM `wizzard_deposits` AS guest_wizard_table
                    WHERE host_wizzard_table.id < guest_wizard_table.id
                    LIMIT 1
                   ))
FROM wizzard_deposits AS `host_wizzard_table`;