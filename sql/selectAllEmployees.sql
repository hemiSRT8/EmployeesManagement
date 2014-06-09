DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectAllEmployees`()
  BEGIN
    SELECT
      employee.*,
      amountOfSales,
      percentageOfSales,
      NULL      AS linesOfCode,
      NULL      AS amountOfCleanedOffices,
      'manager' AS profession
    FROM employee
      JOIN manager ON employee.id = manager.id
    UNION
    SELECT
      employee.*,
      NULL        AS amountOfSales,
      NULL        AS percentageOfSales,
      linesOfCode,
      NULL        AS amountOfCleanedOffices,
      'developer' AS profession
    FROM employee
      JOIN developer ON employee.id = developer.id
    UNION
    SELECT
      employee.*,
      NULL      AS amountOfSales,
      NULL      AS percentageOfSales,
      NULL      AS linesOfCode,
      amountOfCleanedOffices,
      'cleaner' AS profession
    FROM employee
      JOIN cleaner ON employee.id = cleaner.id;
  END//