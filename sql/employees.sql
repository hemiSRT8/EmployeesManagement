SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0;
SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0;
SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema employees
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `employees`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `employees`;

-- -----------------------------------------------------
-- Table `employees`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees`.`employee` (
  `id`          BIGINT      NOT NULL AUTO_INCREMENT,
  `lastName`    VARCHAR(45) NOT NULL,
  `firstName`   VARCHAR(45) NOT NULL,
  `dateOfBirth` DATE        NOT NULL,
  `wage`        DOUBLE      NOT NULL DEFAULT 0,
  `bonus`       DOUBLE      NOT NULL DEFAULT 0,
  `penalty`     DOUBLE      NOT NULL DEFAULT 0,
  `salary`      DOUBLE      NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employees`.`manager`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees`.`manager` (
  `id`                BIGINT NOT NULL,
  `amountOfSales`     DOUBLE NOT NULL DEFAULT 0,
  `percentageOfSales` DOUBLE NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`id` ASC),
  CONSTRAINT `FK_MANAGER_EMPLOYEE_ID`
  FOREIGN KEY (`id`)
  REFERENCES `employees`.`employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employees`.`developer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees`.`developer` (
  `id`          BIGINT NOT NULL,
  `linesOfCode` INT    NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `employee_id_UNIQUE` (`id` ASC),
  CONSTRAINT `FK_DEVELOPER_EMPLOYEE_ID`
  FOREIGN KEY (`id`)
  REFERENCES `employees`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employees`.`cleaner`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees`.`cleaner` (
  `id`                     BIGINT NOT NULL,
  `amountOfCleanedOffices` INT    NOT NULL DEFAULT 0,
  UNIQUE INDEX `employee_id_UNIQUE` (`id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_CLEANER_EMPLOYEE_ID`
  FOREIGN KEY (`id`)
  REFERENCES `employees`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employees`.`department`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees`.`department` (
  `name`              VARCHAR(20) NOT NULL,
  `amountOfEmployees` INT         NOT NULL DEFAULT 0,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `employees`.`employeedepartment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `employees`.`employeedepartment` (
  `employeeId`     BIGINT      NOT NULL,
  `departmentName` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`departmentName`, `employeeId`),
  CONSTRAINT `FK_DEPARTMENTEMPLOYEE_EMPLOYEE_ID`
  FOREIGN KEY (`employeeId`)
  REFERENCES `employees`.`employee` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `FK_DEPARTMENTEMPLOYEE_DEPARTMENT_NAME`
  FOREIGN KEY (`departmentName`)
  REFERENCES `employees`.`department` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
  ENGINE = InnoDB;


SET SQL_MODE = @OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS;
