# Lab: Data Definition and Data Types #

### 1.Create Tables ###	
When we create tables, we specify the database we want to add them to. This is done by using the "USE" clause.
##### Submit your solutions in JUDGE without the "USE {database name}" row. #####
Table "employees":
<pre>
•	id – INT, primary key, AUTO_INCREMENT;
•	first_name – VARCHAR, NOT NULL; 
•	last_name – VARCHAR, NOT NULL;  
</pre>
Create the "categories" and "products" tables analogically:
Table "categories":
<pre>
•	id – INT, primary key, AUTO_INCREMENT;
•	name – VARCHAR, NOT NULL; 
</pre>
Table "products":
<pre>
•	id –  INT, primary key, AUTO_INCREMENT;
•	name – VARCHAR, NOT NULL; 
•	category_id – INT, NOT NULL – it is not a foreign key for now.
</pre>

### 2. Insert Data in Tables ###
Inserting data can be done with a query too. To do that we use the "INSERT" clause. Populate the "employees" table with 3 test values.

### 3. Alter Tables ###
Altering the tables is done via the "ALTER TABLE" clause. Add a new column – "middle_name" to the "employees" table. 
 
### 4. Adding Constraints ###
Create the connection via foreign key between the "products" and "categories" tables that you've created earlier. Make "category_id" foreign key linked to "id" in the "categories" table. 
 
### 5. Modifying Columns ###
Change the property "VARCHAR(50)" to "VARCHAR(100)" to the "middle_name" column in "employees" table.
 
