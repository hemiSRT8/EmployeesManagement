DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `addEmployeesToDepartment`(IN employeeId LONG, IN departmentName CHAR(64))
  BEGIN
    SET @s = CONCAT('INSERT INTO employeedepartment (employeeId, departmentName) VALUES (', employeeId, ',',
                    departmentName, ');');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//