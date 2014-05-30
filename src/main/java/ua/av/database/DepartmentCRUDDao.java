package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.entities.Department;

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
public class DepartmentCRUDDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentCRUDDao.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Create department
     */

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
            }
        }
    }

    /**
     * Read department
     */

    public Map<String, List<Long>> selectEmployeeDepartment() {
        Connection connection = null;
        ResultSet departmentsResulSet;
        Map<String, List<Long>> departmentsHashMap = new HashMap<String, List<Long>>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectEmployeeDepartment}");
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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        LOGGER.info("Request about all departments was made , departments list size: {}", departmentsHashMap.size());
        return departmentsHashMap;
    }

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
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                } else {
                    LOGGER.info("connection is null while closing");
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        return departmentList;
    }

    /**
     * Update department
     */

    public boolean editDepartment(String oldDepartmentName, String newDepartmentName) {

        if (oldDepartmentName == null) {
            LOGGER.error("oldDepartmentName was null");
            return false;
        } else if (newDepartmentName == null) {
            LOGGER.error("newDepartmentName was null");
        }

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call editDepartment(?,?)}");
            callableStatement.setString("oldDepartmentName", "'" + oldDepartmentName + "'");
            callableStatement.setString("newDepartmentName", "'" + newDepartmentName + "'");

            callableStatement.executeUpdate();

            LOGGER.info("{} department was renamed to {}", oldDepartmentName, newDepartmentName);
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
            }
        }
    }

    /**
     * Delete department
     */

    public boolean deleteDepartment(String departmentName) {
        if (departmentName == null) {
            LOGGER.error("departmentName was null");
            return false;
        }

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call deleteDepartment(?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");

            callableStatement.executeUpdate();

            LOGGER.info("{} department was deleted successfully", departmentName);
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
            }
        }
    }
}

