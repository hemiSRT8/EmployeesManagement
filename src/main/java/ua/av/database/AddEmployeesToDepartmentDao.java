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

import static java.lang.Long.valueOf;

@Component
public class AddEmployeesToDepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddEmployeesToDepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    public boolean addEmployeesToDepartment(String[] stringIdsArray, String[] departmentsArray) {
        Connection connection = null;

        if (stringIdsArray == null) {
            LOGGER.error("ids array was null");
            return false;
        } else if (departmentsArray == null) {
            LOGGER.error("departmentsArray was null");
            return false;
        }

        Long[] idsAsLong = new Long[stringIdsArray.length];
        for (int i = 0; i < stringIdsArray.length; i++) {
            String stringId = stringIdsArray[i];
            if (stringId == null) {
                LOGGER.error("id on index {} was null", i);
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
        LOGGER.info("Employee(s) was(were) added to department(s) successfully");
        return true;
    }
}
