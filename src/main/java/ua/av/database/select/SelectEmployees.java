package ua.av.database.select;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.av.database.connector.ConnectorJDBC;
import ua.av.entities.*;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class SelectEmployees {

    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
    private DataSource dataSource = connectorJDBC.getDataSource();

    public List<Employee> selectManagers() {
        List<Employee> managers = new ArrayList<Employee>();

        ResultSet managersResultSet;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectManagers}");
            managersResultSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }


        try {
            if (managersResultSet != null) {
                while (managersResultSet.next()) {
                    managers.add(
                            new Manager(
                                    managersResultSet.getLong("id"),
                                    managersResultSet.getString("lastName"),
                                    managersResultSet.getString("firstName"),
                                    managersResultSet.getDate("dateOfBirth"),
                                    managersResultSet.getDouble("wage"),
                                    managersResultSet.getDouble("bonus"),
                                    managersResultSet.getDouble("penalty"),
                                    managersResultSet.getDouble("salary"),
                                    managersResultSet.getDouble("amountOfSales"),
                                    managersResultSet.getDouble("percentageOfSales"))
                    );
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

        return managers;
    }

    public List<Employee> selectDevelopers() {
        List<Employee> developers = new ArrayList<Employee>();

        ResultSet developersResultSet;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectDevelopers}");
            developersResultSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }


        try {
            if (developersResultSet != null) {
                while (developersResultSet.next()) {
                    developers.add(
                            new Developer(
                                    developersResultSet.getLong("id"),
                                    developersResultSet.getString("lastName"),
                                    developersResultSet.getString("firstName"),
                                    developersResultSet.getDate("dateOfBirth"),
                                    developersResultSet.getDouble("wage"),
                                    developersResultSet.getDouble("bonus"),
                                    developersResultSet.getDouble("penalty"),
                                    developersResultSet.getDouble("salary"),
                                    developersResultSet.getInt("linesOfCode"))
                    );
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

        return developers;
    }

    public List<Employee> selectCleaners() {
        List<Employee> cleaners = new ArrayList<Employee>();

        ResultSet cleanersResultSet;
        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectCleaners}");
            cleanersResultSet = callableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }


        try {
            if (cleanersResultSet != null) {
                while (cleanersResultSet.next()) {
                    cleaners.add(
                            new Cleaner(
                                    cleanersResultSet.getLong("id"),
                                    cleanersResultSet.getString("lastName"),
                                    cleanersResultSet.getString("firstName"),
                                    cleanersResultSet.getDate("dateOfBirth"),
                                    cleanersResultSet.getDouble("wage"),
                                    cleanersResultSet.getDouble("bonus"),
                                    cleanersResultSet.getDouble("penalty"),
                                    cleanersResultSet.getDouble("salary"),
                                    cleanersResultSet.getInt("amountOfCleanedOffices"))
                    );
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

        return cleaners;
    }
}