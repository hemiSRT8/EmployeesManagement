package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.entities.Department;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SelectDepartmentsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectDepartmentsDao.class);

    @Autowired
    private DataSource dataSource;

    public List<Department> selectDepartmentsFromDatabase() {

        Connection connection = null;
        ResultSet departmentsResultSet;

        List<Department> departmentList = new ArrayList<Department>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectDepartments}");
            departmentsResultSet = callableStatement.executeQuery();

            if (departmentsResultSet == null) {
                LOGGER.error("departmentsResultSet was null");
            }

            if (departmentsResultSet != null) {
                while (departmentsResultSet.next()) {
                    departmentList.add(new Department(departmentsResultSet.getString("name")));
                }
            } else {
                LOGGER.error("departmentsResultSet was null, empty list was returned");
                return new ArrayList<Department>();
            }

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            throw new BusinessException();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
                throw new BusinessException(e);
            }
        }

        return departmentList;
    }
}
