DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `averageSalary`()
  BEGIN
    SELECT
      AVG(salary) AS average
    FROM employee;
  END//