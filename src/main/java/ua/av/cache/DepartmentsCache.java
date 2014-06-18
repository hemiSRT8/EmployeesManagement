package ua.av.cache;

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
import java.util.*;

import static java.lang.Long.*;

@Component
public class DepartmentsCache {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentsCache.class);

    @Autowired
    private DataSource dataSource;

    private List<Department> allDepartments;
    private Map<String, List<Long>> employeePlusDepartments;

    /**
     * Initialize
     */
    private void init() {
        fillAllDepartmentsList();
        fillEmployeePlusDepartmentsList();
    }

    private void fillAllDepartmentsList() {
        LOGGER.info("Get all departments started");

        allDepartments = new ArrayList<Department>();

        Connection connection = null;
        ResultSet departmentsResultSet;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectDepartments}");
            departmentsResultSet = callableStatement.executeQuery();

            if (departmentsResultSet != null) {
                while (departmentsResultSet.next()) {
                    allDepartments.add(new Department(departmentsResultSet.getString("name")));
                }
            } else {
                LOGGER.error("departmentsResultSet was null, empty list was returned");
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

        LOGGER.info("Departments were requested,size={}", allDepartments.size());
    }

    private void fillEmployeePlusDepartmentsList() {
        LOGGER.info("Select employee+department info started");

        Connection connection = null;
        ResultSet departmentsResultSet;
        employeePlusDepartments = new HashMap<String, List<Long>>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectEmployeeDepartment}");
            departmentsResultSet = callableStatement.executeQuery();

            while (departmentsResultSet.next()) {
                String departmentName = departmentsResultSet.getString("departmentName");
                Long id = departmentsResultSet.getLong("employeeId");

                List<Long> departmentsEmployees = new ArrayList<Long>();

                if (employeePlusDepartments.containsKey(departmentName)) {
                    employeePlusDepartments.get(departmentName).add(id);
                } else {
                    departmentsEmployees.add(id);
                    employeePlusDepartments.put(departmentName, departmentsEmployees);
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

        LOGGER.info("Select employee+department info finished,departments size={}", employeePlusDepartments.size());
    }

    public boolean addDepartmentToCache(String departmentName) {
        allDepartments.add(new Department(departmentName));
        employeePlusDepartments.put(departmentName, new ArrayList<Long>());

        return true;
    }

    public boolean removeDepartmentFromCache(String departmentName) {
        boolean result = false;

        Iterator iterator = allDepartments.iterator();

        while (iterator.hasNext()) {
            if (((Department) iterator.next()).getName().equals(departmentName)) {
                iterator.remove();
                LOGGER.info("department {} was successfully removed from allDepartments list (cache)");
                result = true;
                break;
            }
        }

        iterator = employeePlusDepartments.keySet().iterator();

        while (iterator.hasNext()) {
            if (iterator.next().equals(departmentName)) {
                iterator.remove();
                LOGGER.info("department {} was successfully removed from employeePlusDepartment list (cache)");
                result = true;
                break;
            }
        }

        return result;
    }

    public boolean renameDepartment(String oldDepartmentName, String newDepartmentName) {
        boolean result = false;

        for (Department department : allDepartments) {
            if (department.getName().equals(oldDepartmentName)) {
                department.setName(newDepartmentName);

                LOGGER.info("department {} in allDepartments list was renamed to {} (cache)", oldDepartmentName, newDepartmentName);
                result = true;
                break;
            }
        }

        for (String s : employeePlusDepartments.keySet()) {
            if (s.equals(oldDepartmentName)) {
                List<Long> tempList = employeePlusDepartments.get(s);
                employeePlusDepartments.remove(s);
                employeePlusDepartments.put(newDepartmentName, tempList);

                LOGGER.info("department {} in employeePlusDepartment list was renamed to {} (cache)", oldDepartmentName, newDepartmentName);
                result = true;
                break;
            }
            result = false;
        }

        return result;
    }

    public boolean removeEmployeeFromDepartment(Long id, String department) {
        employeePlusDepartments.get(department).remove(id);

        return true;
    }

    public void addEmployeeToDepartment(List<String> employeeIds, List<String> departmentIds) {
        for (String department : departmentIds) {
            if (employeePlusDepartments.containsKey(department)) {
                for (String id : employeeIds) {
                    employeePlusDepartments.get(department).add(parseLong(id));
                }
            }
        }
    }

    /**
     * Getters
     */
    public List<Department> getAllDepartments() {
        return allDepartments;
    }

    public Map<String, List<Long>> getEmployeePlusDepartments() {
        return employeePlusDepartments;
    }
}
