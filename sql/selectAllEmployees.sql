DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectAllEmployees`()
  BEGIN
    SELECT
      *
    FROM employee
      LEFT JOIN manager ON employee.id = manager.id
      LEFT JOIN developer ON employee.id = developer.id
      LEFT JOIN cleaner ON employee.id = cleaner.id;
  END//