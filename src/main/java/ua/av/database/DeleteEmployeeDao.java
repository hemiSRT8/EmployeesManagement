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
public class DeleteEmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteEmployeeDao.class);

    @Autowired
    private DataSource dataSource;

    public boolean deleteEmployee(Long id) {

        if (id == null) {
            LOGGER.error("id was null");
            return false;
        }

        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call deleteEmployee(?)}");
            callableStatement.setLong("id", id);

            callableStatement.executeUpdate();

            LOGGER.info("Employee with id {} was deleted successfully", id);
            return true;

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.error("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
                throw new BusinessException();
            }
        }
    }
}
