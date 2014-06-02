package ua.av.database;

import com.mysql.jdbc.MysqlDataTruncation;
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
import java.util.List;
import java.util.Map;

@Component
public class EmployeeDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    @Autowired
    private DataSource dataSource;
    @Autowired
    private DepartmentDao departmentDao;

    /**
     * Create employee
     */
    public boolean addEmployee(Map<String, String> employeeFields) {
        Connection connection = null;
        String profession = employeeFields.get("profession");

        try {
            CallableStatement callableStatement;
            connection = dataSource.getConnection();

            if ("Manager".equals(profession)) {
                callableStatement = connection.prepareCall("{call addManager(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setDouble("amountOfSales", Double.parseDouble(employeeFields.get("amountOfSales")));
                callableStatement.setString("percentageOfSales", employeeFields.get("percentageOfSales"));
            } else if ("Developer".equals(profession)) {
                callableStatement = connection.prepareCall("{call addDeveloper(?,?,?,?,?,?,?,?)}");
                callableStatement.setString("linesOfCode", employeeFields.get("linesOfCode"));
            } else if ("Cleaner".equals(profession)) {
                callableStatement = connection.prepareCall("{call addCleaner(?,?,?,?,?,?,?,?)}");
                callableStatement.setString("amountOfCleanedOffices", employeeFields.get("amountOfCleanedOffices"));
            } else {
                throw new IllegalArgumentException("Invalid employee type");
            }

            String lastName = employeeFields.get("lastName");
            String firstName = employeeFields.get("firstName");
            callableStatement.setString("firstName", firstName);
            callableStatement.setString("lastName", lastName);
            callableStatement.setString("dateOfBirth", employeeFields.get("dateOfBirth"));
            callableStatement.setString("wage", employeeFields.get("wage"));
            callableStatement.setString("bonus", employeeFields.get("bonus"));
            callableStatement.setString("penalty", employeeFields.get("penalty"));
            callableStatement.setString("salary", employeeFields.get("salary"));

            callableStatement.executeUpdate();
            LOGGER.info("employee {} {} was added successfully", lastName, firstName);
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception while connecting closing", e);
            }
        }

        return true;
    }

    /**
     * Read employee
     */

    public List<Employee> selectAllEmployees() {
        ResultSet employeesResultSet;
        ResultSet departmentsResultSet;

        List<Employee> employees = null;
        Connection connection = null;

        try {
            connection = dataSource.getConnection();

            CallableStatement employeesCallableStatement = connection.prepareCall("{call selectAllEmployees}");
            employeesResultSet = employeesCallableStatement.executeQuery();

            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();

            employees = EmployeeService.linkDepartmentsToEmployees(EmployeeParser.parseEmployees(employeesResultSet),
                    DepartmentParser.parseDepartments(departmentsResultSet));
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

        int employeesListSize = employees.size();
        if (employeesListSize > 0) {
            LOGGER.info("employees for main page was selected successfully,size:{}", employeesListSize);
        }

        return employees;
    }

    public Employee selectSingleEmployee(Long id, String profession) {
        Connection connection = null;
        Employee employee = null;

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectSingleEmployee (?,?)}");
            callableStatement.setLong("id", id);
            callableStatement.setString("tableName", profession);

            /**
             * Parse employees fields
             */
            employee = EmployeeParser.parseSingleEmployee(callableStatement.executeQuery(), profession);

            /**
             * Parse employees departments
             */
            Map<String, List<Long>> departments = departmentDao.selectEmployeeDepartment();
            for (String departmentName : departments.keySet()) {
                if (departments.get(departmentName).contains(id)) {
                    employee.addDepartment(new Department(departmentName));
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
                LOGGER.error("SQL exception", e);
            }
        }

        return employee;
    }

    /**
     * Update employee
     */

    public boolean editEmployee(Long id, String professionOfEmployee, Map<String, String> fieldsAndValues) {
        if (id == null) {
            LOGGER.error("id was null");
            return false;
        } else if (professionOfEmployee == null) {
            LOGGER.error("professionOfEmployee was null");
            return false;
        } else if (fieldsAndValues == null) {
            LOGGER.error("fieldsAndValues was null");
            return false;
        }

        Connection connection = null;

        for (String parameter : fieldsAndValues.keySet()) {
            String value = fieldsAndValues.get(parameter);

            if (parameter != null && value != null) {
                try {
                    connection = dataSource.getConnection();
                    CallableStatement callableStatement = connection.prepareCall("{call editEmployee(?,?,?,?)}");
                    callableStatement.setLong("id", id);
                    callableStatement.setString("tableName", professionOfEmployee);
                    callableStatement.setString("colName", parameter);
                    callableStatement.setString("newValue", "'" + value + "'");

                    if (value.equals("")) {
                        continue;
                    }

                    callableStatement.executeUpdate();

                } catch (MysqlDataTruncation e) {
                    LOGGER.error("SQL exception", e);
                    return false;
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
        }

        LOGGER.info("Employee with id {} was edited successfully", id);
        return true;
    }

    /**
     * Delete employee
     */

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
            }
        }
    }

}