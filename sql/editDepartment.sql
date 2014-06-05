DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `editDepartment`(IN oldDepartmentName VARCHAR(64),
                                                              IN newDepartmentName VARCHAR(64))
  BEGIN
    SET @s = CONCAT('UPDATE department SET name = ', newDepartmentName, ' WHERE name = ', oldDepartmentName, ';');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//