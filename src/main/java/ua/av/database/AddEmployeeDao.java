package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

@Component
public class AddEmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddDepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    public boolean addEmployee(Map<String, String> employeeFields) {
        if (employeeFields == null) {
            LOGGER.error("employeeFields was null");
            return false;
        }

        Connection connection = null;
        String profession = employeeFields.get("profession");
        String lastName = employeeFields.get("lastName");
        String firstName = employeeFields.get("firstName");

        try {
            CallableStatement callableStatement = null;
            connection = dataSource.getConnection();

            if ("Manager".equals(profession)) {
                callableStatement = connection.prepareCall("{call addManager(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setDouble("amountOfSales", Double.parseDouble(employeeFields.get("amountOfSales")));
                callableStatement.setString("percentageOfSales", employeeFields.get("percentageOfSales"));
            } else if ("Developer".equals(profession)) {
                callableStatement = connection.prepareCall("{call addDeveloper(?,?,?,?,?,?,?,?)}");
                callableStatement.setString("linesOfCode", employeeFields.get("linesOfCode"));
            } else if ("Cleaner".equals(profession)) {
                callableStatement = connection.prepareCall("{call addCleaner(?,?,?,?,?,?,?,?)}");
                callableStatement.setString("amountOfCleanedOffices", employeeFields.get("amountOfCleanedOffices"));
            }

            if (callableStatement != null) {
                callableStatement.setString("firstName", firstName);
                callableStatement.setString("lastName", lastName);
                callableStatement.setString("dateOfBirth", employeeFields.get("dateOfBirth"));
                callableStatement.setString("wage", employeeFields.get("wage"));
                callableStatement.setString("bonus", employeeFields.get("bonus"));
                callableStatement.setString("penalty", employeeFields.get("penalty"));
                callableStatement.setString("salary", employeeFields.get("salary"));

                callableStatement.executeUpdate();
                LOGGER.info("employee {} {} was added successfully", lastName, firstName);
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.error("Connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connecting closing", e);
                e.printStackTrace();
            }
        }

        return true;
    }

}