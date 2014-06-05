DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `calculateSalaryExpense`()
  BEGIN
    SELECT
      SUM(salary) AS salaryExpense
    FROM employee;
  END//