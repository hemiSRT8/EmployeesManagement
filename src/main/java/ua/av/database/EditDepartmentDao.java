package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class EditDepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EditDepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    public boolean editDepartment(String oldDepartmentName, String newDepartmentName) {

        if (oldDepartmentName == null || newDepartmentName == null) {
            LOGGER.error("oldDepartmentName or newDepartmentName were null");
            return false;
        }

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call editDepartment(?,?)}");
            callableStatement.setString("oldDepartmentName", "'" + oldDepartmentName + "'");
            callableStatement.setString("newDepartmentName", "'" + newDepartmentName + "'");

            callableStatement.executeUpdate();

            return true;

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
                throw new BusinessException();
            }
        }
    }
}
