DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `deleteEmployeeFromDepartment`(IN employeeId     VARCHAR(128),
                                                                            IN departmentName VARCHAR(128))
  BEGIN
    SET @s = CONCAT('DELETE FROM employeedepartment WHERE employeeId = ', employeeId, ' AND departmentName = ',
                    departmentName, ';');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//