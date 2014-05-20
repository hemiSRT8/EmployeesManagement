package ua.av.database;

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
import java.util.List;

@Component
public class SelectEmployees {

    @Autowired
    private DataSource dataSource;

    private EmployeeParser employeeParser = new EmployeeParser();

    public List<Employee> selectManagers() {
        ResultSet managersResultSet;
        ResultSet departmentsResultSet;

        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement managersCallableStatement = connection.prepareCall("{call selectManagers}");
            managersResultSet = managersCallableStatement.executeQuery();

            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        return employeeParser.parseManagers(managersResultSet, departmentsResultSet);
    }

    public List<Employee> selectDevelopers() {
        ResultSet developersResultSet;
        ResultSet departmentsResultSet;

        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement managersCallableStatement = connection.prepareCall("{call selectDevelopers}");
            developersResultSet = managersCallableStatement.executeQuery();

            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        return employeeParser.parseDevelopers(developersResultSet, departmentsResultSet);
    }

    public List<Employee> selectCleaners() {
        ResultSet cleanersResultSet;
        ResultSet departmentsResultSet;

        Connection connection;

        try {
            connection = dataSource.getConnection();
            CallableStatement managersCallableStatement = connection.prepareCall("{call selectCleaners}");
            cleanersResultSet = managersCallableStatement.executeQuery();

            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        return employeeParser.parseCleaners(cleanersResultSet, departmentsResultSet);
    }
}