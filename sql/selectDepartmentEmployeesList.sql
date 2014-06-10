DELIMITER $$

CREATE DEFINER =`root`@`localhost` PROCEDURE `selectDepartmentEmployeesList`(IN employeesId VARCHAR(255))
  BEGIN
    SET @s = CONCAT('
		SELECT employee.*, amountOfSales, percentageOfSales, NULL AS linesOfCode, NULL AS amountOfCleanedOffices,"manager" AS profession  
		FROM employee JOIN manager ON employee.id = manager.id
	UNION
		 SELECT
      employee.*,
      NULL        AS amountOfSales,
      NULL        AS percentageOfSales,
      linesOfCode,
      NULL        AS amountOfCleanedOffices,
      "developer" AS profession
    FROM employee
      JOIN developer ON employee.id = developer.id
UNION
SELECT
      employee.*,
      NULL      AS amountOfSales,
      NULL      AS percentageOfSales,
      NULL      AS linesOfCode,
      amountOfCleanedOffices,
      "cleaner" AS profession
	   FROM employee
      JOIN cleaner ON employee.id = cleaner.id
		WHERE employee.id IN (', (employeesId), ');'
    );
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END