DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectSingleEmployee`(IN tableName CHAR(64), IN id LONG)
  BEGIN
    SET @s = CONCAT('SELECT * FROM employee LEFT JOIN ', tableName, ' ON employee.id =  ', tableName, '.id',
                    ' WHERE employee.id = ', id, ';');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//