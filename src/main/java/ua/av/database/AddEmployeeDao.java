package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.exception.BusinessException;
import ua.av.utils.AddEmployeeFeature;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static java.lang.Double.parseDouble;

@Component
public class AddEmployeeDao {
    @Autowired
    private DataSource dataSource;

    public boolean addEmployee(List<String> employeeFields) {
        Connection connection = null;
        String type = employeeFields.get(0);

        if(!AddEmployeeFeature.isValid(employeeFields)) {
            return false;
        }

        try {
            CallableStatement callableStatement = null;
            connection = dataSource.getConnection();

            if("Manager".equals(type)) {
                callableStatement = connection.prepareCall("{call addManager(?,?,?,?,?,?,?,?,?)}");
                callableStatement.setDouble(8, parseDouble(employeeFields.get(8)));
                callableStatement.setDouble(9, parseDouble(employeeFields.get(9)));

            } else if("Developer".equals(type)) {
                callableStatement = connection.prepareCall("{call addDeveloper(?,?,?,?,?,?,?,?)}");
                callableStatement.setDouble(8, parseDouble(employeeFields.get(8)));

            } else if("Cleaner".equals(type)) {
                callableStatement = connection.prepareCall("{call addCleaner(?,?,?,?,?,?,?,?)}");
                callableStatement.setDouble(8, parseDouble(employeeFields.get(8)));
            }

                callableStatement.setString(1, employeeFields.get(1));
                callableStatement.setString(2, employeeFields.get(2));
                callableStatement.setDate(3, Date.valueOf(employeeFields.get(3)));
                callableStatement.setDouble(4, parseDouble(employeeFields.get(4)));
                callableStatement.setDouble(5, parseDouble(employeeFields.get(5)));
                callableStatement.setDouble(6, parseDouble(employeeFields.get(6)));
                callableStatement.setDouble(7, parseDouble(employeeFields.get(7)));

            callableStatement.executeUpdate();


        } catch (SQLException e) {
            return false;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
