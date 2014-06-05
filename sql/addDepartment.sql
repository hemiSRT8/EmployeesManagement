DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `addDepartment`(IN departmentName VARCHAR(10000))
  BEGIN
    SET @s = CONCAT('INSERT INTO department (name, amountOfEmployees) VALUES (', departmentName, ',', 0, ');');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//


BEGIN
START TRANSACTION;
SET AUTOCOMMIT = 0;

INSERT INTO employee (lastName, firstName, dateOfBirth, wage, bonus, penalty, salary)
VALUES (lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);

INSERT INTO developer (id, linesOfCode)
VALUES (LAST_INSERT_ID(), linesOfCode);
COMMIT;
SET AUTOCOMMIT = 1;
END//