DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `deleteEmployee`(id LONG)
  BEGIN
    DELETE FROM employee
    WHERE employee.id = id;
  END//