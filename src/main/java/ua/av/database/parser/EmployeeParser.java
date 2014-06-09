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

    public static List<Employee> parseEmployees(ResultSet resultSet) {
        List<Employee> allEmployeesList = new ArrayList<Employee>();

        if (resultSet != null) {
            LOGGER.info("Parsing employees started");
            try {
                while (resultSet.next()) {
                    String profession = resultSet.getString("profession");
                    if (profession.equals("manager")) {
                        allEmployeesList.add(parseManagers(resultSet));
                    } else if (profession.equals("developer")) {
                        allEmployeesList.add(parseDevelopers(resultSet));
                    } else if (profession.equals("cleaner")) {
                        allEmployeesList.add(parseCleaners(resultSet));
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
            }
            LOGGER.info("Parsing employees finished,size={}", allEmployeesList.size());
        }

        return allEmployeesList;
    }

    private static Manager parseManagers(ResultSet resultSet) {
        Manager manager = new Manager();
        parseGeneralFields(manager, resultSet);
        try {
            manager.setAmountOfSales(resultSet.getDouble("amountOfSales"));
            manager.setPercentageOfSales(resultSet.getDouble("percentageOfSales"));
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }

        return manager;
    }

    private static Developer parseDevelopers(ResultSet resultSet) {
        Developer developer = new Developer();
        parseGeneralFields(developer, resultSet);
        try {
            developer.setLinesOfCode(resultSet.getInt("linesOfCode"));
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }

        return developer;
    }

    private static Cleaner parseCleaners(ResultSet resultSet) {
        Cleaner cleaner = new Cleaner();
        try {
            cleaner.setAmountOfCleanedOffices(resultSet.getInt("amountOfCleanedOffices"));
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }
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

    public static Employee parseSingleEmployee(ResultSet resultSet, String profession) {
        try {
            if (resultSet != null) {
                resultSet.next();
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        }

        if ("manager".equals(profession)) {
            return parseManagers(resultSet);
        } else if ("developer".equals(profession)) {
            return parseDevelopers(resultSet);
        } else if ("cleaner".equals(profession)) {
            return parseCleaners(resultSet);
        } else {
            return null;
        }
    }
}