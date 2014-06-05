DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `maxSalary`()
  BEGIN
    SELECT
      MAX(salary) AS maxSalary
    FROM employee;
  END//