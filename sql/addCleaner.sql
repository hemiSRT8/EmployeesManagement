DELIMITER //
CREATE DEFINER =`root`@`localhost` PROCEDURE `addCleaner`(
  lastName nvarchar(50),
  firstName nvarchar(50),
  dateOfBirth DATE,
  wage DOUBLE,
  bonus DOUBLE,
  penalty DOUBLE,
  salary DOUBLE,
  amountOfCleanedOffices INT
)
BEGIN
START TRANSACTION;
SET AUTOCOMMIT = 0;

INSERT INTO employee (lastName, firstName, dateOfBirth, wage, bonus, penalty, salary)
VALUES (lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);

INSERT INTO cleaner (id, amountOfCleanedOffices)
VALUES (LAST_INSERT_ID(), amountOfCleanedOffices);
COMMIT;
SET AUTOCOMMIT = 1;
END//