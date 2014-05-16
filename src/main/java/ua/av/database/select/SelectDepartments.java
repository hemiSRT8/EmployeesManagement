package ua.av.database.select;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.av.database.connector.ConnectorJDBC;
import ua.av.department.Department;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelectDepartments {

    public List<Department> selectDepartmentsFromDatabase() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();

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
                throw new BusinessException();
            }
        }

        return departmentList;
    }
}
