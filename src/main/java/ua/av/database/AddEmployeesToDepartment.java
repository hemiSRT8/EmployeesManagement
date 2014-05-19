package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Long.valueOf;

public class AddEmployeesToDepartment {

    public static boolean addEmployeesToDepartment(WebRequest request) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();
        Connection connection = null;

        String[] stringIdsArray = request.getParameterValues("employeeId");
        String[] departmentsArray = request.getParameterValues("department");

        if (stringIdsArray == null) {
            return false;
        } else if (departmentsArray == null) {
            return false;
        }

        Long[] idsAsLong = new Long[stringIdsArray.length];
        for (int i = 0; i < stringIdsArray.length; i++) {
            String stringId = stringIdsArray[i];
            if (stringId == null) {
                return false;
            }
            Long id = valueOf(stringId);
            idsAsLong[i] = id;
        }

        for (Long id : idsAsLong) {
            for (String department : departmentsArray) {
                try {
                    connection = dataSource.getConnection();
                    CallableStatement callableStatement = connection.prepareCall("{call addEmployeesToDepartment(?,?)}");
                    callableStatement.setLong("employeeId", id);
                    callableStatement.setString("departmentName", "'" + department + "'");
                    callableStatement.executeUpdate();
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
        return true;
    }
}
