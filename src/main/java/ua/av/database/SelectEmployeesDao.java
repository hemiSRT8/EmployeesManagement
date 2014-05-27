package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.database.parser.DepartmentParser;
import ua.av.entities.Department;
import ua.av.entities.Employee;
import ua.av.exception.BusinessException;
import ua.av.database.parser.EmployeeParser;
import ua.av.utils.EmployeeToDepartmentLinker;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class SelectEmployeesDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectEmployeesDao.class);

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EmployeeParser employeeParser;

    @Autowired
    private DepartmentParser departmentParser;

    @Autowired
    private EmployeeToDepartmentLinker employeeToDepartmentLinker;

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

            employees = employeeToDepartmentLinker.linkDeparmentsToEmployees(employeeParser.parseEmployees(employeesResultSet),
                    departmentParser.parseDepartments(departmentsResultSet));
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            throw new BusinessException(e);
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

        int employeesListSize = employees.size();
        if (employeesListSize > 0) {
            LOGGER.info("employees for main page was selected successfully,size was: {}", employeesListSize);
        }

        return employees;
    }
}