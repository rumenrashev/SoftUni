SELECT DISTINCT department_id,
       (
           SELECT DISTINCT salary FROM employees AS t2
           WHERE t1.department_id = t2.department_id
           ORDER BY salary DESC LIMIT 2, 1
       ) AS third_highest_salary FROM employees AS t1
HAVING third_highest_salary IS NOT NULL
ORDER BY department_id ASC;