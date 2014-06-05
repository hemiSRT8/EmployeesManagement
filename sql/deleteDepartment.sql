DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `deleteDepartment`(IN departmentName VARCHAR(64))
  BEGIN
    SET @s = CONCAT('DELETE FROM department WHERE name = ', departmentName, ';');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//