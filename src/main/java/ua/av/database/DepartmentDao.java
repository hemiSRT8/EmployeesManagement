package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.database.parser.DepartmentParser;
import ua.av.database.parser.EmployeeParser;
import ua.av.entities.Department;
import ua.av.entities.Employee;
import ua.av.utils.EmployeeService;

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
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Create
     */
    public boolean addDepartment(String departmentName) {
        LOGGER.info("Add new department started,name={}", departmentName);

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call addDepartment(?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");
            callableStatement.executeUpdate();

            LOGGER.info("Add new department finished,name={}", departmentName);
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
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }
    }

    /**
     * Read
     */
    public Map<String, List<Long>> getEmployeeDepartment() {
        LOGGER.info("Select employee+department info started");

        Connection connection = null;
        ResultSet departmentsResultSet;
        Map<String, List<Long>> departmentsHashMap = new HashMap<String, List<Long>>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectEmployeeDepartment}");
            departmentsResultSet = callableStatement.executeQuery();

            while (departmentsResultSet.next()) {
                String departmentName = departmentsResultSet.getString("departmentName");
                Long id = departmentsResultSet.getLong("employeeId");

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
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        LOGGER.info("Select employee+department info finished,departments size={}", departmentsHashMap.size());
        return departmentsHashMap;
    }

    public List<Department> getDepartmentsFromDatabase() {
        LOGGER.info("Select departments started");

        Connection connection = null;
        ResultSet departmentsResultSet;

        List<Department> departmentList = new ArrayList<Department>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectDepartments}");
            departmentsResultSet = callableStatement.executeQuery();

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
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }

        LOGGER.info("Select departments finished,size={}", departmentList.size());
        return departmentList;
    }

    public List<Employee> getDepartmentEmployeesList(String ids) {
        LOGGER.info("Selecting department's employees list started,amount of employees in department={}", ids.length());

        List<Employee> employees = new ArrayList<Employee>();
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            CallableStatement employeesCallableStatement = connection.prepareCall("{call selectDepartmentEmployeesList(?)}");
            employeesCallableStatement.setString("employeesId", ids.substring(ids.indexOf("[") + 1, ids.lastIndexOf("]")));
            ResultSet employeesResultSet = employeesCallableStatement.executeQuery();

            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            ResultSet departmentsResultSet = departmentsCallableStatement.executeQuery();

            employees = EmployeeService.linkDepartmentsToEmployees(EmployeeParser.parseEmployees(employeesResultSet),
                    DepartmentParser.parseDepartments(departmentsResultSet));

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
            }
        }

        LOGGER.info("Selecting department's employees list finished , size={}", employees.size());
        return employees;
    }

    /**
     * Update
     */
    public boolean editDepartment(String oldDepartmentName, String newDepartmentName) {
        LOGGER.info("Department edit started,old name={},new name ={}", oldDepartmentName, newDepartmentName);

        if (oldDepartmentName == null) {
            LOGGER.error("oldDepartmentName was null");
            return false;
        } else if (newDepartmentName == null) {
            LOGGER.error("newDepartmentName was null");
            return false;
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
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }
    }

    /**
     * Delete
     */
    public void deleteDepartment(String departmentName) {
        if (departmentName == null) {
            LOGGER.error("departmentName was null");
            return;
        }

        LOGGER.info("Department deletion started");

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call deleteDepartment(?)}");
            callableStatement.setString("departmentName", "'" + departmentName + "'");

            callableStatement.executeUpdate();

            LOGGER.info("{} department was deleted successfully", departmentName);
        } catch (SQLException e) {
            LOGGER.error("Department {} deletion was failed due to SQL exception {}", departmentName, e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connection closing", e);
            }
        }
    }

    public void deleteEmployeeFromDepartment(Long id, String departmentName) {
        LOGGER.info("Delete employee from department started");
        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call deleteEmployeeFromDepartment(?,?)}");
            callableStatement.setString("employeeId", "'" + id + "'");
            callableStatement.setString("departmentName", "'" + departmentName + "'");
            callableStatement.executeQuery();

            LOGGER.info("Delete employee from department finished,department name ={},employee id={}",
                    id, departmentName);

            return;

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
            }
        }

        LOGGER.error("Deleting employee from department was failed,employee id={},department name={}", id, departmentName);
    }
}

