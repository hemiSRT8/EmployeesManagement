package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.av.exception.BusinessException;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectFullDepartmentsInfo {

    public static Map<String, List<Long>> selectFullDepartmentsInfo() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
        DataSource dataSource = connectorJDBC.getDataSource();

        Connection connection = null;
        ResultSet departmentsResulSet;
        Map<String, List<Long>> departmentsHashMap = new HashMap<String, List<Long>>();

        try {
            connection = dataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall("{call selectFullDepartments}");
            departmentsResulSet = callableStatement.executeQuery();

            while (departmentsResulSet.next()) {
                String departmentName = departmentsResulSet.getString("departmentName");
                Long id = departmentsResulSet.getLong("employeeId");

                List<Long> departmentsEmployees = new ArrayList<Long>();

                if (departmentsHashMap.containsKey(departmentName)) {
                    departmentsHashMap.get(departmentName).add(id);
                } else {
                    departmentsEmployees.add(id);
                    departmentsHashMap.put(departmentName, departmentsEmployees);
                }
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

        return departmentsHashMap;
    }
}
