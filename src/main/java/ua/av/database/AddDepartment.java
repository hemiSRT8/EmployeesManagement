package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.request.WebRequest;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class AddDepartment {

    public static boolean addDepartment(WebRequest request) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();
        Connection connection = null;

        String departmentName = request.getParameter("departmentName");

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call addDepartment(?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");
            callableStatement.executeUpdate();
            return true;
        } catch (MysqlDataTruncation e) {
            return false;
        } catch (MySQLIntegrityConstraintViolationException e) {
            return false;
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

