package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SelectSalaryInformationDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectSalaryInformationDao.class);

    @Autowired
    private DataSource dataSource;

    public double selectSalaryExpenseForEmployees() {
        Connection connection = null;
        ResultSet resultSet;
        double expense = -1;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call calculateSalaryExpense()}");
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                expense = resultSet.getDouble("salaryExpense");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        if (expense < 0) {
            LOGGER.error("Salary expense was less then 0");
        }

        LOGGER.info("Salary expense for all employees was selected, sum = {}", expense);
        return expense;
    }

    public double selectAverageSalaryOfEmployees() {
        Connection connection = null;
        ResultSet resultSet;
        double averageSalary = -1;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call averageSalary()}");
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                averageSalary = resultSet.getDouble("average");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        if (averageSalary < 0) {
            LOGGER.error("averageSalary was less then 0");
        }

        LOGGER.info("averageSalary of all employees was selected, value = {}", averageSalary);
        return averageSalary;
    }

    public double selectMaxSalaryOfEmployees() {
        Connection connection = null;
        ResultSet resultSet;
        double maxSalary = -1;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call maxSalary()}");
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                maxSalary = resultSet.getDouble("maxSalary");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        if (maxSalary < 0) {
            LOGGER.error("maxSalary was less then 0");
        }

        LOGGER.info("maxSalary of all employees was selected, value = {}", maxSalary);
        return maxSalary;
    }

    public double selectMinSalaryOfEmployees() {
        Connection connection = null;
        ResultSet resultSet;
        double minSalary = -1;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call minSalary()}");
            resultSet = callableStatement.executeQuery();
            if (resultSet.next()) {
                minSalary = resultSet.getDouble("minSalary");
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        if (minSalary < 0) {
            LOGGER.error("minSalary was less then 0");
        }

        LOGGER.info("minSalary of all employees was selected, value = {}", minSalary);
        return minSalary;
    }

    public Map<String, Double> selectSalaryExpenseForDepartment(Map<String, List<Long>> map) {
        LOGGER.info("Salary expense calculating for department started");

        Map<String, Double> result = new HashMap<String, Double>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call departmentSalaryExpense(?)}");

            for (String department : map.keySet()) {
                callableStatement.setString("nameOfDepartment", "'" + department + "'");
                ResultSet resultSet = callableStatement.executeQuery();
                while (resultSet.next()) {
                    result.put(department, resultSet.getDouble("salaryExpense"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
            }
        }

        LOGGER.info("Salary expense calculating for department finished");
        return result;
    }
}