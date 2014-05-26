package ua.av.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.entities.Employee;
import ua.av.exception.BusinessException;
import ua.av.utils.EmployeeParser;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Component
public class SelectSingleEmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(SelectSingleEmployeeDao.class);

    @Autowired
    private DataSource dataSource;

    public List<Employee> selectSingleEmployee(Long id, String profession) {
        ResultSet employeeResultSet;
        ResultSet departmentsResultSet;
        Connection connection = null;
        List<Employee> employees = new ArrayList<Employee>();
        EmployeeParser employeeParser = new EmployeeParser();

        if (id == null || profession == null) {
            LOGGER.error("id or profession were null , empty list of employees was returned");
            return employees;
        }

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectSingleEmployee(?,?)}");
            callableStatement.setString("tableName", profession);
            callableStatement.setLong("id", id);
            employeeResultSet = callableStatement.executeQuery();

            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();

            if (profession.compareTo("manager") == 0) {
                employees = employeeParser.parseManagers(employeeResultSet, departmentsResultSet);
            } else if (profession.compareTo("developer") == 0) {
                employees = employeeParser.parseDevelopers(employeeResultSet, departmentsResultSet);
            } else if (profession.compareTo("cleaner") == 0) {
                employees = employeeParser.parseCleaners(employeeResultSet, departmentsResultSet);
            } else {
                throw new BusinessException("Not instance of employee");
            }

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
                throw new BusinessException(e);
            }
        }
        LOGGER.info("Request about single employee was made , employee list size: {}", employees.size());
        return employees;
    }
}

