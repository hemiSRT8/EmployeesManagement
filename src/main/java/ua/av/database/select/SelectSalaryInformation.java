package ua.av.database.select;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.av.database.connector.ConnectorJDBC;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSalaryInformation {

    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
    private DataSource dataSource = connectorJDBC.getDataSource();

    public double selectSalaryExpense() {
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
                e.printStackTrace();
            }
        }

        return expense;
    }

    public double selectAverageSalary() {
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
                e.printStackTrace();
            }
        }

        return averageSalary;
    }

    public double selectMaxSalary() {
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
                e.printStackTrace();
            }
        }

        return maxSalary;
    }

    public double selectMinSalary() {
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
                e.printStackTrace();
            }
        }

        return minSalary;
    }
}