DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `addManager`(
  lastName nvarchar(50),
firstName nvarchar(50),
dateOfBirth DATE,
wage DOUBLE,
bonus DOUBLE,
  penalty DOUBLE,
  salary DOUBLE,
  amountOfSales DOUBLE,
  percentageOfSales DOUBLE
)
BEGIN
START TRANSACTION;
SET AUTOCOMMIT = 0;

INSERT INTO employee (lastName, firstName, dateOfBirth, wage, bonus, penalty, salary)
VALUES (lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);

INSERT INTO manager (id, amountOfSales, percentageOfSales)
VALUES (LAST_INSERT_ID(), amountOfSales, percentageOfSales);
COMMIT;
SET AUTOCOMMIT = 1;
END//