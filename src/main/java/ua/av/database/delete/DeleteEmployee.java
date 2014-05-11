package ua.av.database.delete;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;
import ua.av.database.connector.ConnectorJDBC;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Long.*;

public class DeleteEmployee {

    public void deleteEmployee(WebRequest request) {
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}