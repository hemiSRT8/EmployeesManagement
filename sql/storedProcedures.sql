DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addCleaner`(
lastName nvarchar(50),
firstName nvarchar(50),
dateOfBirth date,
wage double,
bonus double,
penalty double,
salary double,
amountOfCleanedOffices int
)
BEGIN
START TRANSACTION;
SET AUTOCOMMIT=0;

insert into employee(lastName, firstName, dateOfBirth, wage, bonus, penalty,salary)
values (lastName,firstName,dateOfBirth,wage,bonus,penalty,salary);

insert into cleaner (id, amountOfCleanedOffices)
VALUES(LAST_INSERT_ID(), amountOfCleanedOffices);
COMMIT;
SET AUTOCOMMIT=1;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addDepartment`(IN departmentName VARCHAR(10000))
BEGIN
    SET @s = CONCAT('INSERT INTO department (name, amountOfEmployees) VALUES (' , departmentName , ',' , 0 , ')');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addDeveloper`(
lastName nvarchar(50),
firstName nvarchar(50),
dateOfBirth date,
wage double,
bonus double,
penalty double,
salary double,
linesOfCode int
)
BEGIN
START TRANSACTION;
SET AUTOCOMMIT=0;

insert into employee(lastName, firstName, dateOfBirth, wage, bonus, penalty,salary)
values (lastName,firstName,dateOfBirth,wage,bonus,penalty,salary);

insert into developer (id, linesOfCode)
VALUES(LAST_INSERT_ID(), linesOfCode);
COMMIT;
SET AUTOCOMMIT=1;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addEmployeesToDepartment`(IN employeeId LONG , IN departmentName CHAR(64))
BEGIN
    SET @s = CONCAT('INSERT INTO employeedepartment (employeeId, departmentName) VALUES (', employeeId ,',' , departmentName,');');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addManager`(
lastName nvarchar(50),
firstName nvarchar(50),
dateOfBirth date,
wage double,
bonus double,
penalty double,
salary double,
amountOfSales double,
percentageOfSales double
)
BEGIN
START TRANSACTION;
SET AUTOCOMMIT=0;

insert into employee(lastName, firstName, dateOfBirth, wage, bonus, penalty,salary)
values (lastName,firstName,dateOfBirth,wage,bonus,penalty,salary);

insert into manager (id, amountOfSales, percentageOfSales)
VALUES(LAST_INSERT_ID(), amountOfSales, percentageOfSales);
COMMIT;
SET AUTOCOMMIT=1;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `averageSalary`()
BEGIN
    SELECT AVG(salary) AS average FROM employee;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `calculateSalaryExpense`()
BEGIN
    SELECT SUM(salary) AS salaryExpense FROM employee;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteDepartment`(IN departmentName VARCHAR(64))
BEGIN
    SET @s = CONCAT('DELETE FROM department WHERE name = ' , departmentName);
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `editDepartment`(IN oldDepartmentName VARCHAR(64),IN newDepartmentName VARCHAR(64))
BEGIN
    SET @s = CONCAT('UPDATE department SET name = ' , newDepartmentName , ' WHERE name = ' , oldDepartmentName , ';');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `deleteEmployee`(id  LONG)
BEGIN
DELETE FROM employee WHERE employee.id = id;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `editEmployee`(IN tableName VARCHAR(64), IN id LONG , IN colName VARCHAR(64) , IN newValue VARCHAR(64))
BEGIN
    SET @s = CONCAT('UPDATE employee LEFT JOIN ',tableName,' ON employee.id =  ',tableName, '.id', ' SET ', colName , ' = ' , newValue ,' WHERE employee.id = ' ,id );
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `maxSalary`()
BEGIN
    SELECT MAX(salary) AS maxSalary FROM employee;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `minSalary`()
BEGIN
    SELECT MIN(salary) AS minSalary FROM employee;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectAllEmployees`()
BEGIN
  SELECT * FROM employee
		LEFT JOIN manager ON employee.id = manager.id
		LEFT JOIN developer ON employee.id = developer.id
		LEFT JOIN cleaner ON employee.id = cleaner.id;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectDepartments`()
BEGIN
  SELECT name FROM department;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectEmployeesDepartment`()
BEGIN
  SELECT * FROM employeedepartment;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectEmployeeDepartment`()
BEGIN
	SELECT * FROM employeedepartment;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectSingleEmployee`(IN tableName CHAR(64), IN id LONG)
BEGIN
    SET @s = CONCAT('SELECT * FROM employee LEFT JOIN ',tableName,' ON employee.id =  ',tableName, '.id', ' WHERE employee.id = ' ,id );
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//

DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `departmentSalaryExpense`(IN nameOfDepartment VARCHAR(64))
BEGIN
    SET @s = CONCAT('SELECT SUM(salary) AS salaryExpense FROM
						(SELECT * FROM employee WHERE employee.id IN
							(SELECT employeeId FROM employeedepartment WHERE departmentName = ' , nameOfDepartment , ')) AS employeesIds;');
    PREPARE stmt FROM @s;
    EXECUTE stmt;
    DEALLOCATE PREPARE stmt;
END//
