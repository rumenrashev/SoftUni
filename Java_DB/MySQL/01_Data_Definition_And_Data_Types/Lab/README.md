# Lab: Data Definition and Data Types #

###1. Create Tables###	
When we create tables, we specify the database we want to add them to. This is done by using the "USE" clause.
Submit your solutions in JUDGE without the "USE {database name}" row.
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
