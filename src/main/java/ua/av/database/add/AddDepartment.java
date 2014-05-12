package ua.av.database.add;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;
import ua.av.database.connector.ConnectorJDBC;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Integer.*;

public class AddDepartment {

    public void addDepartment(WebRequest request) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();
        Connection connection = null;

        String departmentName = request.getParameter("departmentName");
        int amountOfEmployees = valueOf(request.getParameter("amountOfEmployees"));

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call addDepartment(?,?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");
            callableStatement.setInt("amountOfEmployees", amountOfEmployees);
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

