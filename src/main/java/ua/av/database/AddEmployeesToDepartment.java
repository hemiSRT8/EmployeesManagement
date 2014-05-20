package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import static java.lang.Long.valueOf;

@Component
public class AddEmployeesToDepartment {

    @Autowired
    private DataSource dataSource;

    public boolean addEmployeesToDepartment(WebRequest request) {
        Connection connection = null;

        String[] stringIdsArray = request.getParameterValues("employeeId");
        String[] departmentsArray = request.getParameterValues("department");

        if (stringIdsArray == null) {
            return false;
        } else if (departmentsArray == null) {
            return false;
        }

        Long[] idsAsLong = new Long[stringIdsArray.length];
        for (int i = 0; i < stringIdsArray.length; i++) {
            String stringId = stringIdsArray[i];
            if (stringId == null) {
                return false;
            }
            Long id = valueOf(stringId);
            idsAsLong[i] = id;
        }

        for (Long id : idsAsLong) {
            for (String department : departmentsArray) {
                try {
                    connection = dataSource.getConnection();
                    CallableStatement callableStatement = connection.prepareCall("{call addEmployeesToDepartment(?,?)}");
                    callableStatement.setLong("employeeId", id);
                    callableStatement.setString("departmentName", "'" + department + "'");
                    callableStatement.executeUpdate();
                } catch (SQLException e) {
                    return false;
                } finally {
                    try {
                        if (connection != null) {
                            connection.close();
                        }
                    } catch (SQLException e) {
                        throw new BusinessException();
                    }
                }
            }
        }
        return true;
    }
}
