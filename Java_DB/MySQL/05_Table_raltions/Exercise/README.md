# Exercises: Table Relations #
This document defines the exercise assignments for the MySQL course @ Software University. 

###    1. One-To-One Relationship ###
Create two tables as follows. Use appropriate data types.
persons
person_id | first_name | salary | passport_id
--- | --- | --- | ---
1 | Roberto | 43300.00 | 102 
2 | Tom | 56100.00 | 103
3 | Yana | 60200.00 | 101

passports
passport_id | passport_number
--- | ---
101 | N34FG21B
102 | K65LO4R7
103 | ZE657QP2

Insert the data from the example above.
<pre>
    • Alter table persons and make person_id a primary key. 
    • Create a foreign key between persons and passports by using the passport_id column. 
    • Think about which passport field should be UNIQUE.
    </pre>
Submit your queries by using "MySQL run queries & check DB" strategy.
