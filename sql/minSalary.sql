DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `minSalary`()
  BEGIN
    SELECT
      MIN(salary) AS minSalary
    FROM employee;
  END//