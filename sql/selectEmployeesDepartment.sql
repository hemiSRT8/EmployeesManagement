DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `selectEmployeesDepartment`()
  BEGIN
    SELECT
      *
    FROM employeedepartment;
  END//
