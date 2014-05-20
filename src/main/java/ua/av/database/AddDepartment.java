package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class AddDepartment {

    @Autowired
    private DataSource dataSource;

    public boolean addDepartment(WebRequest request) {

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
                throw new BusinessException(e);
            }
        }
    }
}

