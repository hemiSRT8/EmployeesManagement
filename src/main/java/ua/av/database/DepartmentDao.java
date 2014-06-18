package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.cache.DepartmentsCache;
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
import java.util.List;
import java.util.Map;

@Component
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DepartmentsCache departmentsCache;

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

            LOGGER.info("Add new department finished (db),name={}", departmentName);

            boolean cacheResult = departmentsCache.addDepartmentToCache(departmentName);
            if (cacheResult) {
                LOGGER.info("Add new department finished (cache),name={}", departmentName);
            } else {
                LOGGER.info("Add new department failed (cache),name={}", departmentName);
            }

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
        return departmentsCache.getEmployeePlusDepartments();
    }

    public List<Department> getDepartmentsFromDatabase() {
        return departmentsCache.getAllDepartments();
    }

    public List<Employee> getDepartmentEmployeesList(String ids) {
        LOGGER.info("Selecting department's employees list started");

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

            LOGGER.info("{} department was renamed to {} (db)", oldDepartmentName, newDepartmentName);

            boolean cacheResult = departmentsCache.renameDepartment(oldDepartmentName, newDepartmentName);
            if (cacheResult) {
                LOGGER.info("{} department was renamed to {} (cache)", oldDepartmentName, newDepartmentName);
            } else {
                LOGGER.error("renaming department with old name {} to {} was failed", oldDepartmentName, newDepartmentName);
            }

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

            LOGGER.info("{} department was deleted successfully (db)", departmentName);

            boolean cacheResult = departmentsCache.removeDepartmentFromCache(departmentName);
            if (cacheResult) {
                LOGGER.info("{} department was deleted successfully (cache)", departmentName);
            } else {
                LOGGER.info("{} department deletion was failed (cache)", departmentName);
            }

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

            LOGGER.info("Delete employee from department finished(db),department name ={},employee id={}",
                    id, departmentName);

            boolean cacheResult = departmentsCache.removeEmployeeFromDepartment(id, departmentName);
            if (cacheResult) {
                LOGGER.info("Delete employee from department finished(cache),department name ={},employee id={}",
                        id, departmentName);
            } else {
                LOGGER.info("Delete employee from department was failed(cache),department name ={},employee id={}",
                        id, departmentName);
            }

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

