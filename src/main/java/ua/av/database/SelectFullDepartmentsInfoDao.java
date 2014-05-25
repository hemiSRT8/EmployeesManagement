package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SelectFullDepartmentsInfoDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectFullDepartmentsInfoDao.class);

    @Autowired
    private DataSource dataSource;

    public Map<String, List<Long>> selectFullDepartmentsInfo() {
        Connection connection = null;
        ResultSet departmentsResulSet;
        Map<String, List<Long>> departmentsHashMap = new HashMap<String, List<Long>>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectFullDepartments}");
            departmentsResulSet = callableStatement.executeQuery();

            while (departmentsResulSet.next()) {
                String departmentName = departmentsResulSet.getString("departmentName");
                Long id = departmentsResulSet.getLong("employeeId");

                List<Long> departmentsEmployees = new ArrayList<Long>();

                if (departmentsHashMap.containsKey(departmentName)) {
                    departmentsHashMap.get(departmentName).add(id);
                } else {
                    departmentsEmployees.add(id);
                    departmentsHashMap.put(departmentName, departmentsEmployees);
                }
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
                throw new BusinessException();
            }
        }

        LOGGER.info("Request about all departments was made , departments list size: {}", departmentsHashMap.size());
        return departmentsHashMap;
    }
}
