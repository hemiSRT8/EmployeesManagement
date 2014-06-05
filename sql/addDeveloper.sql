DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `addDeveloper`(
  lastName nvarchar(50),
firstName nvarchar(50),
dateOfBirth DATE,
wage DOUBLE,
bonus DOUBLE,
  penalty DOUBLE,
  salary DOUBLE,
  linesOfCode INT
)