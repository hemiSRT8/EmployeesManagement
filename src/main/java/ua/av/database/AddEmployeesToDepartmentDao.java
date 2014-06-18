package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.cache.DepartmentsCache;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Long.valueOf;

@Component
public class AddEmployeesToDepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddEmployeesToDepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DepartmentsCache departmentsCache;

    public boolean addEmployeesToDepartment(List<String> employeeIds, List<String> departmentIds) {
        LOGGER.info("Add employees to departments started,employees size={},departments size={}",
                employeeIds.size(), departmentIds.size());

        Connection connection = null;

        for (String id : employeeIds) {
            for (String department : departmentIds) {
                try {
                    connection = dataSource.getConnection();
                    CallableStatement callableStatement = connection.prepareCall("{call addEmployeesToDepartment(?,?)}");
                    callableStatement.setLong("employeeId", valueOf(id));
                    callableStatement.setString("departmentName", "'" + department + "'");

                    callableStatement.executeUpdate();
                } catch (SQLException e) {
                    LOGGER.error("Error occurs while linking employee to department", e);
                    return false;
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        LOGGER.error("Unable to close connection", e);
                    }
                }
            }
        }

        LOGGER.info("Add employees to departments finished (db)");

        departmentsCache.addEmployeeToDepartment(employeeIds, departmentIds);
        LOGGER.info("Add employees to departments finished (cache)");

        return true;
    }
}
