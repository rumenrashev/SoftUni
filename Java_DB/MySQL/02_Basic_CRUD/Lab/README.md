# Lab: Basic CRUD #

This document defines the lab assignments for the MySQL course @ Software University. 
Download and get familiar with the hotel database schemas and tables. You will use it in the following exercises to write queries.

### Problem 1: Select Employee Information ###
Write a query to select all employees and retrieve information about their id, first_name, last_name and job_title ordered by id.

Example
id | first_name ! last_name | job_title
--- | --- | --- | --- 
1 | John | Smith | Manager | 
2 | John | Johnson | Customer Service
3 | Smith | Johnson | Porter
… | … | … | …

### Problem 2: Select Employees with Filter ###
Write a query to select all employees (id, first_name and last_name (as full_name), job_title, salary) whose salaries are higher than 1000.00, ordered by id. Concatenate fields first_name and last_name into 'full_name'.
Example

id | first_name ! last_name | job_title | salary
--- | --- | --- | --- | ---
3 | Smith Johnson | Porter | 1100
4 | Peter Petrov | Front Desk Clerk | 1100
5 | Peter Ivanov | Sales | 1500.23
… | … | … | …


### Problem 3: Update Employees Salary ###
Update all employees' salaries whose job_title is "Manager" by adding 100. 
Retrieve information about salaries from table employees.


### Problem 4: Top Paid Employee ###
Write a query to create a view that selects all information about the top paid employee from the "employees" table in the hotel database.
Example


id | first_name ! last_name | job_title | salary
--- | --- | --- | --- | ---
8 | Pedro | Petrov | Front Desk Supervisor | 1 | 2100

### Problem 5: Select Employees by Multiple Filters ###
Write a query to retrieve information about employees, who are in department 4 and has a salary higher or equal to 1000. Order the information by id.
Example

id | first_name ! last_name | job_title | salary
--- | --- | --- | --- | ---
3 | Smith | Johnson | Porter | 4 | 1100
9 | Nikolay | Ivanov | Housekeeping | 4 | 1600

### Problem 6: Delete from Table ###
Write a query to delete all employees from the "employees" table who are in department 2 or 1. Order the information by id.
Example

id | first_name ! last_name | job_title | salary
--- | --- | --- | --- | ---
3 | Smith | Johnson | Porter | 4 | 1100
6 | Ivan | Petrov | Waiter | 3 | 990
7 | Jack | Jackson | Executive Chef | 3 | 1800
9 | Nikolay | Ivanov | Housekeeping | 4 | 1600




