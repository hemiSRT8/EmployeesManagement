package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Long.valueOf;

public class DeleteEmployee {

    public static boolean deleteEmployee(WebRequest request) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();
        Connection connection = null;

        long id = valueOf(request.getParameter("deleteEmployeeId"));

        try {
            connection = dataSource.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call deleteEmployee(?)}");
            callableStatement.setLong("id", id);

            callableStatement.executeUpdate();

            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                return false;
            }
        }
    }
}
