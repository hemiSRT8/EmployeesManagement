package ua.av.database.add;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;
import ua.av.database.connector.ConnectorJDBC;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Long.*;

public class AddEmployeesToDepartment {

    public void addEmployeesToDepartment(WebRequest request) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();
        Connection connection = null;

        String[] idsAsString = request.getParameterValues("employeeId");
        String[] departments = request.getParameterValues("department");

        Long[] idsAsLong = new Long[idsAsString.length];
        for (int i = 0; i < idsAsString.length; i++) {
            Long id = valueOf(idsAsString[i]);
            idsAsLong[i] = id;
        }

        for (Long anIdsAsLong : idsAsLong) {
            for (String department : departments) {
                try {
                    connection = dataSource.getConnection();
                    CallableStatement callableStatement = connection.prepareCall("{call addEmployeesToDepartment(?,?)}");
                    callableStatement.setLong("employeeId", anIdsAsLong);
                    callableStatement.setString("departmentName", "'" + department + "'");
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
    }
}
