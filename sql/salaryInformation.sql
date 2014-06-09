DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `salaryInformation`()
  BEGIN
    SELECT
      MAX(salary) AS maxSalary,
      MIN(salary) AS minSalary,
      AVG(salary) AS averageSalary,
      SUM(salary) AS salaryExpense
    FROM employee;
  END//