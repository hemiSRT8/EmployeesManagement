package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
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
                                    managersResultSet.getLong(1),
                                    managersResultSet.getString(2), managersResultSet.getString(3),
                                    managersResultSet.getDate(4),
                                    managersResultSet.getDouble(5), managersResultSet.getDouble(6), managersResultSet.getDouble(7),
                                    managersResultSet.getDouble(8),
                                    managersResultSet.getDouble(10), managersResultSet.getDouble(11))
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
                    developers.add(new Developer(
                            developersResultSet.getLong(1),
                            developersResultSet.getString(2), developersResultSet.getString(3),
                            developersResultSet.getDate(4),
                            developersResultSet.getDouble(5), developersResultSet.getDouble(6), developersResultSet.getDouble(7),
                            developersResultSet.getDouble(8),
                            developersResultSet.getInt(10)));
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
                                    cleanersResultSet.getLong(1),
                                    cleanersResultSet.getString(2), cleanersResultSet.getString(3),
                                    cleanersResultSet.getDate(4),
                                    cleanersResultSet.getDouble(5), cleanersResultSet.getDouble(6), cleanersResultSet.getDouble(7),
                                    cleanersResultSet.getDouble(8),
                                    cleanersResultSet.getInt(10))
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