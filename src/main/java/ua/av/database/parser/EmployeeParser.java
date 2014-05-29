package ua.av.database.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.av.entities.*;
import ua.av.exception.BusinessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeParser.class);

    public List<Employee> parseEmployees(ResultSet employees) {
        List<Employee> allEmployeesList = new ArrayList<Employee>();

        if (employees != null) {
            LOGGER.info("Parsing employees started");
            try {
                while (employees.next()) {
                    /**
                     * Managers
                     */
                    double amountOfSales = employees.getDouble("amountOfSales");
                    if (!employees.wasNull()) {
                        allEmployeesList.add(parseManagers(employees, amountOfSales));
                    }
                    /**
                     * Developers
                     */
                    int linesOfCode = employees.getInt("linesOfCode");
                    if (!employees.wasNull()) {
                        allEmployeesList.add(parseDevelopers(employees, linesOfCode));
                    }
                    /**
                     * Cleaners
                     */
                    int amountOfCleanedOffices = employees.getInt("amountOfCleanedOffices");
                    if (!employees.wasNull()) {
                        allEmployeesList.add(parseCleaners(employees, amountOfCleanedOffices));
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
                throw new BusinessException(e);
            }
            LOGGER.info("Parsing employees finished");
        }

        return allEmployeesList;
    }

    private Manager parseManagers(ResultSet managers, double amountOfSales) {

        Manager manager = new Manager();
        try {
            Long managerId = managers.getLong("id");
            manager.setId(managerId);
            manager.setLastName(managers.getString("lastName"));
            manager.setFirstName(managers.getString("firstName"));
            manager.setDateOfBirth(managers.getDate("dateOfBirth"));
            manager.setWage(managers.getDouble("wage"));
            manager.setBonus(managers.getDouble("bonus"));
            manager.setPenalty(managers.getDouble("penalty"));
            manager.setSalary(managers.getDouble("salary"));
            manager.setAmountOfSales(amountOfSales);
            manager.setPercentageOfSales(managers.getDouble("percentageOfSales"));

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }

        return manager;
    }

    private Developer parseDevelopers(ResultSet developers, int linesOfCode) {

        Developer developer = new Developer();
        try {
            Long developerId = developers.getLong("id");
            developer.setId(developerId);
            developer.setLastName(developers.getString("lastName"));
            developer.setFirstName(developers.getString("firstName"));
            developer.setDateOfBirth(developers.getDate("dateOfBirth"));
            developer.setWage(developers.getDouble("wage"));
            developer.setBonus(developers.getDouble("bonus"));
            developer.setPenalty(developers.getDouble("penalty"));
            developer.setSalary(developers.getDouble("salary"));
            developer.setLinesOfCode(linesOfCode);

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }

        return developer;
    }

    private Cleaner parseCleaners(ResultSet cleaners, int amountOfCleanedOffices) {

        Cleaner cleaner = new Cleaner();
        try {
            Long cleanerId = cleaners.getLong("id");

            cleaner.setId(cleanerId);
            cleaner.setLastName(cleaners.getString("lastName"));
            cleaner.setFirstName(cleaners.getString("firstName"));
            cleaner.setDateOfBirth(cleaners.getDate("dateOfBirth"));
            cleaner.setWage(cleaners.getDouble("wage"));
            cleaner.setBonus(cleaners.getDouble("bonus"));
            cleaner.setPenalty(cleaners.getDouble("penalty"));
            cleaner.setSalary(cleaners.getDouble("salary"));
            cleaner.setAmountOfCleanedOffices(amountOfCleanedOffices);

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            throw new BusinessException(e);
        }

        return cleaner;
    }
}
