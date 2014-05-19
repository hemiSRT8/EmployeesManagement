package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSalaryInformation {

    private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private static ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
    private static DataSource dataSource = connectorJDBC.getDataSource();

    public static double selectSalaryExpense() {
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
            throw new BusinessException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return expense;
    }

    public static double selectAverageSalary() {
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
            throw new BusinessException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return averageSalary;
    }

    public static double selectMaxSalary() {
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
            throw new BusinessException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return maxSalary;
    }

    public static double selectMinSalary() {
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
            throw new BusinessException(e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return minSalary;
    }
}