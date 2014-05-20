package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.entities.Department;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SelectDepartmentsDao {

    @Autowired
    private DataSource dataSource;

    public List<Department> selectDepartmentsFromDatabase() {

        Connection connection = null;
        ResultSet departmentsResulSet;

        List<Department> departmentList = new ArrayList<Department>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectDepartments}");
            departmentsResulSet = callableStatement.executeQuery();

            while (departmentsResulSet.next()) {
                departmentList.add(new Department(departmentsResulSet.getString("name")));
            }

        } catch (SQLException e) {
            throw new BusinessException();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return departmentList;
    }
}
