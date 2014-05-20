package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DeleteDepartmentDao {

    @Autowired
    private DataSource dataSource;

    public boolean deleteDepartment(String departmentName) {
        if (departmentName == null) {
            return false;
        }

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call deleteDepartment(?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");

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
                throw new BusinessException();
            }
        }
    }
}
