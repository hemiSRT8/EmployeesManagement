package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;
import ua.av.entities.Cleaner;
import ua.av.entities.Developer;
import ua.av.entities.Employee;
import ua.av.entities.Manager;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.Long.valueOf;

public class SelectSingleEmployee {

    public static Employee selectSingleEmployee(WebRequest request, String profession) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();

        long id = valueOf(request.getParameter("editEmployeeId"));

        ResultSet resultSet;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectSingleEmployee(?,?)}");
            callableStatement.setString("tableName", profession);
            callableStatement.setLong("id", id);
            resultSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Creating employee ,depends from incoming parameter - profession
         */

        Employee employee = null;

        if (profession.equalsIgnoreCase("Manager")) {
            employee = new Manager();
        } else if (profession.equalsIgnoreCase("Developer")) {
            employee = new Developer();
        } else if (profession.equalsIgnoreCase("Cleaner")) {
            employee = new Cleaner();
        }

        /**
         * Setting fields for employee
         */

        try {
            if (resultSet != null) {
                while (resultSet.next()) {
                    employee.setId(resultSet.getLong("id"));
                    employee.setLastName(resultSet.getString("lastName"));
                    employee.setFirstName(resultSet.getString("firstName"));
                    employee.setDateOfBirth(resultSet.getDate("dateOfBirth"));
                    employee.setWage(resultSet.getDouble("wage"));
                    employee.setBonus(resultSet.getDouble("bonus"));
                    employee.setPenalty(resultSet.getDouble("penalty"));
                    employee.setSalary(resultSet.getDouble("salary"));
                    if (employee instanceof Manager) {
                        ((Manager) employee).setAmountOfSales(resultSet.getDouble("amountOfSales"));
                        ((Manager) employee).setPercentageOfSales(resultSet.getDouble("percentageOfSales"));
                    } else if (employee instanceof Developer) {
                        ((Developer) employee).setLinesOfCode(resultSet.getInt("linesOfCode"));
                    } else if (employee instanceof Cleaner) {
                        ((Cleaner) employee).setAmountOfCleanedOffices(resultSet.getInt("amountOfCleanedOffices"));
                    }
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return employee;
    }
}

