DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectDepartments`()
  BEGIN
    SELECT
      name
    FROM department;
  END//