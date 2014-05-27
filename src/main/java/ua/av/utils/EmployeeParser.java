package ua.av.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.av.entities.*;
import ua.av.exception.BusinessException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeParser.class);

    public List<Employee> parseEmployees(ResultSet employees, ResultSet departments) {
        List<Employee> allEmployeesList = new ArrayList<Employee>();

        Map<Long, List<Department>> departmentsHashMap = new HashMap<Long, List<Department>>();
        departmentsHashMap = parseDepartments(departments, departmentsHashMap);

        if (employees != null) {
            try {
                while (employees.next()) {
                    /**
                     * Managers
                     */
                    double amountOfSales = employees.getDouble("amountOfSales");
                    if (!employees.wasNull()) {
                        allEmployeesList.add(parseManagers(employees, amountOfSales, departmentsHashMap));
                    }
                    /**
                     * Developers
                     */
                    int linesOfCode = employees.getInt("linesOfCode");
                    if (!employees.wasNull()) {
                        allEmployeesList.add(parseDevelopers(employees, linesOfCode, departmentsHashMap));
                    }
                    /**
                     * Cleaners
                     */
                    int amountOfCleanedOffices = employees.getInt("amountOfCleanedOffices");
                    if (!employees.wasNull()) {
                        allEmployeesList.add(parseCleaners(employees, amountOfCleanedOffices, departmentsHashMap));
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
                throw new BusinessException(e);
            }
        }

        return allEmployeesList;
    }

    private Manager parseManagers(ResultSet managers, double amountOfSales,
                                  Map<Long, List<Department>> departmentsHashMap) {

        Manager manager = new Manager();
        try {
            Long managerId = managers.getLong("id");
            manager.setId(managerId);
            manager.setLastName(managers.getString("lastName"));
            manager.setFirstName(managers.getString("firstName"));
            manager.setDateOfBirth(managers.getDate("dateOfBirth"));
            manager.setWage(managers.getDouble("wage"));
            manager.setBonus(managers.getDouble("bonus"));
            manager.setPenalty(managers.getDouble("penalty"));
            manager.setSalary(managers.getDouble("salary"));
            manager.setAmountOfSales(amountOfSales);
            manager.setPercentageOfSales(managers.getDouble("percentageOfSales"));

            for (Long key : departmentsHashMap.keySet()) {
                if (key.equals(managerId)) {
                    List<Department> departmentList = departmentsHashMap.get(key);
                    for (Department department : departmentList) {
                        manager.setDepartment(department);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            e.printStackTrace();
        }

        return manager;
    }

    private Developer parseDevelopers(ResultSet developers, int linesOfCode,
                                      Map<Long, List<Department>> departmentsHashMap) {

        Developer developer = new Developer();
        try {
            Long developerId = developers.getLong("id");
            developer.setId(developerId);
            developer.setLastName(developers.getString("lastName"));
            developer.setFirstName(developers.getString("firstName"));
            developer.setDateOfBirth(developers.getDate("dateOfBirth"));
            developer.setWage(developers.getDouble("wage"));
            developer.setBonus(developers.getDouble("bonus"));
            developer.setPenalty(developers.getDouble("penalty"));
            developer.setSalary(developers.getDouble("salary"));
            developer.setLinesOfCode(linesOfCode);

            for (Long key : departmentsHashMap.keySet()) {
                if (key.equals(developerId)) {
                    List<Department> departmentList = departmentsHashMap.get(key);
                    for (Department department : departmentList) {
                        developer.setDepartment(department);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            e.printStackTrace();
        }

        return developer;
    }

    private Cleaner parseCleaners(ResultSet cleaners, int amountOfCleanedOffices,
                                  Map<Long, List<Department>> departmentsHashMap) {

        Cleaner cleaner = new Cleaner();
        try {
            Long cleanerId = cleaners.getLong("id");

            cleaner.setId(cleanerId);
            cleaner.setLastName(cleaners.getString("lastName"));
            cleaner.setFirstName(cleaners.getString("firstName"));
            cleaner.setDateOfBirth(cleaners.getDate("dateOfBirth"));
            cleaner.setWage(cleaners.getDouble("wage"));
            cleaner.setBonus(cleaners.getDouble("bonus"));
            cleaner.setPenalty(cleaners.getDouble("penalty"));
            cleaner.setSalary(cleaners.getDouble("salary"));
            cleaner.setAmountOfCleanedOffices(amountOfCleanedOffices);

            for (Long key : departmentsHashMap.keySet()) {
                if (key.equals(cleanerId)) {
                    List<Department> departmentList = departmentsHashMap.get(key);
                    for (Department department : departmentList) {
                        cleaner.setDepartment(department);
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            throw new BusinessException(e);
        }


        return cleaner;
    }

    private Map<Long, List<Department>> parseDepartments(ResultSet departments, Map<Long, List<Department>> departmentsHashMap) {

        try {
            while (departments.next()) {
                List<Department> departmentsBox = new ArrayList<Department>();

                Department department = new Department(departments.getString("departmentName"));

                if (!departments.wasNull()) {
                    Long id = departments.getLong("employeeId");

                    if (departmentsHashMap.containsKey(id)) {
                        departmentsHashMap.get(id).add(department);
                    } else {
                        departmentsBox.add(department);
                        departmentsHashMap.put(id, departmentsBox);
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            throw new BusinessException(e);
        }

        return departmentsHashMap;
    }
}
