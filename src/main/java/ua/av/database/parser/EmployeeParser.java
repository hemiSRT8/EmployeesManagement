package ua.av.database.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ua.av.entities.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeParser.class);

    public static List<Employee> parseEmployees(ResultSet employees) {
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
            }
            LOGGER.info("Parsing employees finished");
        }

        return allEmployeesList;
    }

    private static Manager parseManagers(ResultSet resultSet, double amountOfSales) {
        Manager manager = new Manager();
        parseGeneralFields(manager, resultSet);
        try {
            manager.setAmountOfSales(amountOfSales);
            manager.setPercentageOfSales(resultSet.getDouble("percentageOfSales"));

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }

        return manager;
    }

    private static Developer parseDevelopers(ResultSet resultSet, int linesOfCode) {
        Developer developer = new Developer();
        parseGeneralFields(developer, resultSet);
        developer.setLinesOfCode(linesOfCode);

        return developer;
    }

    private static Cleaner parseCleaners(ResultSet resultSet, int amountOfCleanedOffices) {
        Cleaner cleaner = new Cleaner();
        cleaner.setAmountOfCleanedOffices(amountOfCleanedOffices);
        parseGeneralFields(cleaner, resultSet);

        return cleaner;
    }

    private static void parseGeneralFields(Employee employee, ResultSet resultSet) {
        try {
            employee.setId(resultSet.getLong("id"));
            employee.setLastName(resultSet.getString("lastName"));
            employee.setFirstName(resultSet.getString("firstName"));
            employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            employee.setWage(resultSet.getDouble("wage"));
            employee.setBonus(resultSet.getDouble("bonus"));
            employee.setPenalty(resultSet.getDouble("penalty"));
            employee.setSalary(resultSet.getDouble("salary"));
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }
    }
}
