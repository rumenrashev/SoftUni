# Exercises: Basic CRUD #
This document defines the exercise assignments for the MySQL course @ Software University.  
Download and get familiar with the soft_uni, diablo and geography database schemas and tables. 
You will use them in this and the following exercises to write queries. 

### Part I – Queries for SoftUni Database ###

### 1. Find All Information About Departments ###

Write a SQL query to find all available information about the departments. Sort the information by id. Submit your query statements as Prepare DB & run queries. 
Example 
department_id | name | manager_id 
 --- | --- | ---
1 | Engineering | 12 
2 | Tool Design | 4 
3 | Sales | 273 
… | … | … 

### 

### 2. Find all Department Names ### 
Write SQL query to find all department names. Sort the information by id. Submit your query statements as Prepare DB & run queries. 
Example 
name
--- 
Engineering 
Tool Design 
Sales 
… 

### 3. Find salary of Each Employee ###
Write SQL query to find the first name, last name and salary of each employee. Sort the information by id. Submit your query statements as Prepare DB & run queries. 
Example 
first_name | last_name  |salary 
---| --- | ---|
Guy | Gilbert | 12500.00 
Kevin | Brown | 13500.00 
Roberto | Tamburello | 43300.00 
… | … | … 

### 4. Find Full Name of Each Employee ###
Write SQL query to find the first, middle and last name of each employee. Sort the information by id. Submit your query statements as Prepare DB & run queries. 
Example 
first_name | middle_name | last_name 
--- | --- | --- 
Guy | R | Gilbert 
Kevin | F | Brown 
Roberto | NULL | Tamburello 
… | … | … 

### 5. Find Email Address of Each Employee ###
Write a SQL query to find the email address of each employee. (by his first and last name). Consider that the email domain is softuni.bg. Emails should look like "John.Doe@softuni.bg". The produced column should be named "full_ email_address".  Submit your query statements as Prepare DB & run queries. 
Example 
full_email_address 
| --- |
Guy.Gilbert@softuni.bg 
Kevin.Brown@softuni.bg 
Roberto.Tamburello@softuni.bg 
… 

### 6. Find All Different Employee's Salaries ###
Write a SQL query to find all different employee's salaries. Show only the salaries. Sort the information by id.  Submit your query statements as Prepare DB & run queries.  
Example
Salary 
| --- |
12500.00 
13500.00 
43300.00 
… 

### 7. Find all Information About Employees ### 
Write a SQL query to find all information about the employees whose job title is "Sales Representative". Sort the information by id. Submit your query statements as Prepare DB & run queries. 
Example
id |First Name | Last Name | Middle Name | Job Title | Dept ID | Mngr ID | Hire Date | salary | address_id 
--- | --- | --- | --- | --- | --- | --- | --- | --- | ---
275 | Michael | Blythe | G | Sales | Representative | 3 | 268 | … | 23100.00 | 60 
276 | Linda Mitchell | C | Sales Representative | 3 | 268 | … | 23100.00 | 170 
… | … | … | … | … | … | … | … | … | … 

