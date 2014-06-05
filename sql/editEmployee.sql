DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `editEmployee`(IN tableName VARCHAR(64), IN id LONG,
                                                            IN colName   VARCHAR(64), IN newValue VARCHAR(64))
  BEGIN
    SET @s = CONCAT('UPDATE employee LEFT JOIN ', tableName, ' ON employee.id =  ', tableName, '.id', ' SET ', colName,
                    ' = ', newValue, ' WHERE employee.id = ', id, ';');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
  END//