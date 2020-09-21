# Exercises: Data Aggregation # 
This document defines the exercise assignments for the MySQL course @ Software University. 

   Mr. Bodrog is a greedy small goblin. His most precious possession is a small database of the deposits in 
the wizard's world. Mr. Bodrog wants you to send him some reports.
Get familiar with the gringotts database. You will use it in the assignments below.

###    1.  Records' Count ###
Import the database and send the total count of records to Mr. Bodrog. Make sure nothing got lost.
Example:
count
| --- |
162
###    2.  Longest Magic Wand ###
Select the size of the longest magic wand. Rename the new column appropriately.
Example:
longest_magic_wand
| --- |
31

### 3. Longest Magic Wand Per Deposit Groups ###
    For wizards in each deposit group show the longest magic wand. Sort result by longest magic wand for each deposit group in increasing order, then by deposit_group alphabetically. Rename the new column appropriately.
    Example:
    deposit_group | longest_magic_wand
    --- | ---
    Human Pride | 30
    … | …
    
### 4. Smallest Deposit Group Per Magic Wand Size* ###
Select the deposit group with the lowest average wand size.
Example:
deposit_group
| --- |
Troll Chest

### 5.	 Deposits Sum ###
Select all deposit groups and its total deposit sum. Sort result by total_sum in increasing order.
Example:
deposit_group | total_sum
--- | ---
Blue Phoenix | 819598.73
… | …

### 6. Deposits Sum for Ollivander Family ###
Select all deposit groups and its total deposit sum but only for the wizards who has their magic wand crafted by Ollivander family. Sort result by deposit_group alphabetically.
Example:
deposit_group | total_sum
--- | ---
Blue Phoenix | 52968.96
Human Pride | 188366.86
… | …

### 8. Deposit Charge ###
Create a query that selects:
<pre>
•	Deposit group 
•	Magic wand creator
•	Minimum deposit charge for each group 
</pre>
Group by deposit_group and magic_wand_creator.
Select the data in ascending order by magic_wand_creator and deposit_group.

Example:
deposit_group | magic_wand_creator | min_deposit_charge
--- | --- | ---
Blue Phoenix | Antioch Peverell | 30.00
… | … | …

### 9. Age Groups ###
Write down a query that creates 7 different groups based on their age.
Age groups should be as follows:
<pre>
•	[0-10]
•	[11-20]
•	[21-30]
•	[31-40]
•	[41-50]
•	[51-60]
•	[61+]
</pre>
The query should return:
<pre>
•	Age groups
•	Count of wizards in it
</pre>
Sort result by increasing size of age groups.
Example:
age_group | wizard_count
--- | ---
[11-20] | 21
… | …





