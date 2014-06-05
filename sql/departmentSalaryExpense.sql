DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `departmentSalaryExpense`(IN nameOfDepartment VARCHAR(64))
  BEGIN
    SET @s = CONCAT('SELECT SUM(salary) AS salaryExpense FROM
						(SELECT * FROM employee WHERE employee.id IN
							(SELECT employeeId FROM employeedepartment WHERE departmentName = ', nameOfDepartment,
                    ')) AS employeesIds;');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//