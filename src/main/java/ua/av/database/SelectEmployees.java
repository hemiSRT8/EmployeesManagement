package ua.av.database;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.av.entities.*;
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


public class SelectEmployees {

    private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    private static final ConnectorJDBC connectorJDBC = (ConnectorJDBC) context.getBean("connectorJDBC");
    private static final DataSource dataSource = connectorJDBC.getDataSource();

    public static List<Employee> selectManagers() {

        List<Employee> managers = new ArrayList<Employee>();
        Map<Long, List<Department>> departmentsHashMap = new HashMap<Long, List<Department>>();

        ResultSet managersResultSet;
        ResultSet departmentsResultSet;

        Connection connection;

        /**
         * Select all managers and all departments
         */
        try {
            connection = dataSource.getConnection();
            CallableStatement managersCallableStatement = connection.prepareCall("{call selectManagers}");
            managersResultSet = managersCallableStatement.executeQuery();
            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Taking departments from result set and putting them to hash map
         */
        try {
            while (departmentsResultSet.next()) {
                List<Department> departmentsBox = new ArrayList<Department>();

                Department department = new Department(departmentsResultSet.getString("departmentName"));
                Long id = departmentsResultSet.getLong("employeeId");

                if (departmentsHashMap.containsKey(id)) {
                    departmentsHashMap.get(id).add(department);
                } else {
                    departmentsBox.add(department);
                    departmentsHashMap.put(id, departmentsBox);
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Creating managers from result set and assigning departments to them
         */
        try {
            if (managersResultSet != null) {
                while (managersResultSet.next()) {
                    Manager manager = new Manager();
                    Long managerId = managersResultSet.getLong("id");

                    manager.setId(managerId);
                    manager.setLastName(managersResultSet.getString("lastName"));
                    manager.setFirstName(managersResultSet.getString("firstName"));
                    manager.setDateOfBirth(managersResultSet.getDate("dateOfBirth"));
                    manager.setWage(managersResultSet.getDouble("wage"));
                    manager.setBonus(managersResultSet.getDouble("bonus"));
                    manager.setPenalty(managersResultSet.getDouble("penalty"));
                    manager.setSalary(managersResultSet.getDouble("salary"));
                    manager.setAmountOfSales(managersResultSet.getDouble("amountOfSales"));
                    manager.setPercentageOfSales(managersResultSet.getDouble("percentageOfSales"));


                    for (Long key : departmentsHashMap.keySet()) {
                        if (key == managerId) {
                            List<Department> departmentList = departmentsHashMap.get(key);
                            for (Department department : departmentList) {
                                manager.setDepartment(department);
                            }
                        }
                    }

                    managers.add(manager);
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return managers;
    }

    public static List<Employee> selectDevelopers() {

        List<Employee> developers = new ArrayList<Employee>();
        Map<Long, List<Department>> departmentsHashMap = new HashMap<Long, List<Department>>();

        ResultSet developersResultSet;
        ResultSet departmentsResultSet;

        Connection connection;

        /**
         * Select all developers and all departments
         */
        try {
            connection = dataSource.getConnection();
            CallableStatement developersCallableStatement = connection.prepareCall("{call selectDevelopers}");
            developersResultSet = developersCallableStatement.executeQuery();
            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Taking departments from result set and putting them to hash map
         */
        try {
            while (departmentsResultSet.next()) {
                List<Department> departmentsBox = new ArrayList<Department>();

                Department department = new Department(departmentsResultSet.getString("departmentName"));
                Long id = departmentsResultSet.getLong("employeeId");

                if (departmentsHashMap.containsKey(id)) {
                    departmentsHashMap.get(id).add(department);
                } else {
                    departmentsBox.add(department);
                    departmentsHashMap.put(id, departmentsBox);
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Creating developers from result set and assigning departments to them
         */
        try {
            if (developersResultSet != null) {
                while (developersResultSet.next()) {
                    Developer developer = new Developer();
                    Long developerId = developersResultSet.getLong("id");

                    developer.setId(developerId);
                    developer.setLastName(developersResultSet.getString("lastName"));
                    developer.setFirstName(developersResultSet.getString("firstName"));
                    developer.setDateOfBirth(developersResultSet.getDate("dateOfBirth"));
                    developer.setWage(developersResultSet.getDouble("wage"));
                    developer.setBonus(developersResultSet.getDouble("bonus"));
                    developer.setPenalty(developersResultSet.getDouble("penalty"));
                    developer.setSalary(developersResultSet.getDouble("salary"));
                    developer.setLinesOfCode(developersResultSet.getInt("linesOfCode"));

                    for (Long key : departmentsHashMap.keySet()) {
                        if (key == developerId) {
                            List<Department> departmentList = departmentsHashMap.get(key);
                            for (Department department : departmentList) {
                                developer.setDepartment(department);
                            }
                        }
                    }

                    developers.add(developer);
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return developers;
    }

    public static List<Employee> selectCleaners() {

        List<Employee> cleaners = new ArrayList<Employee>();
        Map<Long, List<Department>> departmentsHashMap = new HashMap<Long, List<Department>>();

        ResultSet cleanersResultSet;
        ResultSet departmentsResultSet;

        Connection connection;

        /**
         * Select all cleaners and all departments
         */
        try {
            connection = dataSource.getConnection();
            CallableStatement cleanersCallableStatement = connection.prepareCall("{call selectCleaners}");
            cleanersResultSet = cleanersCallableStatement.executeQuery();
            CallableStatement departmentsCallableStatement = connection.prepareCall("{call selectEmployeesDepartment}");
            departmentsResultSet = departmentsCallableStatement.executeQuery();
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Taking departments from result set and putting them to hash map
         */
        try {
            while (departmentsResultSet.next()) {
                List<Department> departmentsBox = new ArrayList<Department>();

                Department department = new Department(departmentsResultSet.getString("departmentName"));
                Long id = departmentsResultSet.getLong("employeeId");

                if (departmentsHashMap.containsKey(id)) {
                    departmentsHashMap.get(id).add(department);
                } else {
                    departmentsBox.add(department);
                    departmentsHashMap.put(id, departmentsBox);
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        }

        /**
         * Creating cleaners from result set and assigning departments to them
         */
        try {
            if (cleanersResultSet != null) {
                while (cleanersResultSet.next()) {
                    Cleaner cleaner = new Cleaner();
                    Long cleanerId = cleanersResultSet.getLong("id");

                    cleaner.setId(cleanerId);
                    cleaner.setLastName(cleanersResultSet.getString("lastName"));
                    cleaner.setFirstName(cleanersResultSet.getString("firstName"));
                    cleaner.setDateOfBirth(cleanersResultSet.getDate("dateOfBirth"));
                    cleaner.setWage(cleanersResultSet.getDouble("wage"));
                    cleaner.setBonus(cleanersResultSet.getDouble("bonus"));
                    cleaner.setPenalty(cleanersResultSet.getDouble("penalty"));
                    cleaner.setSalary(cleanersResultSet.getDouble("salary"));
                    cleaner.setAmountOfCleanedOffices(cleanersResultSet.getInt("amountOfCleanedOffices"));

                    for (Long key : departmentsHashMap.keySet()) {
                        if (key == cleanerId) {
                            List<Department> departmentList = departmentsHashMap.get(key);
                            for (Department department : departmentList) {
                                cleaner.setDepartment(department);
                            }
                        }
                    }

                    cleaners.add(cleaner);
                }
            }
        } catch (SQLException e) {
            throw new BusinessException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new BusinessException(e);
            }
        }

        return cleaners;
    }
}