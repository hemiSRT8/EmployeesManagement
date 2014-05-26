package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
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
public class AddDepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddDepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    public boolean addDepartment(String departmentName) {

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call addDepartment(?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");
            callableStatement.executeUpdate();

            LOGGER.info("{} department was added successfully", departmentName);
            return true;

        } catch (MysqlDataTruncation e) {
            LOGGER.error("SQL exception: MySQL date format", e);
            return false;
        } catch (MySQLIntegrityConstraintViolationException e) {
            LOGGER.error("SQL exception: MySQLIntegrityConstraintViolationException", e);
            return false;
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
                throw new BusinessException(e);
            }
        }
    }
}

