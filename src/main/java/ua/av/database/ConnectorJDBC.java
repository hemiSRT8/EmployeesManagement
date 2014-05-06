package ua.av.database;

import ua.av.exception.BusinessException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectorJDBC {

    private static DataSource dataSource;

    static {
        try {
            dataSource = (DataSource) new InitialContext().lookup("java:comp/env/employeesJDBC");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new BusinessException("No ua.av.database connection", e);
        }
    }
}
