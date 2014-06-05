DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectEmployeeDepartment`()
  BEGIN
    SELECT
      *
    FROM employeedepartment;
  END//