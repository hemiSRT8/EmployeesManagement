DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectDepartmentEmployeesList`(IN employeesId VARCHAR(255))
  BEGIN
    SET @s = CONCAT('SELECT * FROM employee
						LEFT JOIN manager ON employee.id = manager.id
						LEFT JOIN developer ON employee.id = developer.id
						LEFT JOIN cleaner ON employee.id = cleaner.id
							WHERE employee.id IN (', (employeesId), ');'
    );
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//