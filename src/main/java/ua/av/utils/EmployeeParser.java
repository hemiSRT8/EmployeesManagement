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

    public List<Employee> parseManagers(ResultSet managers, ResultSet departments) {
        List<Employee> managersList = new ArrayList<Employee>();
        Map<Long, List<Department>> departmentsHashMap = parseDepartments(departments);

        if (managers != null) {
            try {
                while (managers.next()) {
                    double amountOfSales = managers.getDouble("amountOfSales");
                    if (!managers.wasNull()) {
                        Manager manager = new Manager();
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
                        managersList.add(manager);
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
                throw new BusinessException(e);
            }
        }

        return managersList;
    }

    public List<Employee> parseDevelopers(ResultSet developers, ResultSet departments) {
        List<Employee> developersList = new ArrayList<Employee>();
        Map<Long, List<Department>> departmentsHashMap = parseDepartments(departments);

        if (developers != null) {
            try {
                while (developers.next()) {
                    int linesOfCode = developers.getInt("linesOfCode");
                    if (!developers.wasNull()) {
                        Developer developer = new Developer();
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
                        developersList.add(developer);
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
                throw new BusinessException(e);
            }
        }

        return developersList;
    }

    public List<Employee> parseCleaners(ResultSet cleaners, ResultSet departments) {
        List<Employee> cleanersList = new ArrayList<Employee>();
        Map<Long, List<Department>> departmentsHashMap = parseDepartments(departments);

        if (cleaners != null) {
            try {
                while (cleaners.next()) {
                    int amountOfCleanedOffices = cleaners.getInt("amountOfCleanedOffices");
                    if (!cleaners.wasNull()) {
                        Cleaner cleaner = new Cleaner();
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
                        cleanersList.add(cleaner);
                    }
                }
            } catch (SQLException e) {
                LOGGER.error("SQL exception", e);
                throw new BusinessException(e);
            }
        }

        return cleanersList;
    }

    private Map<Long, List<Department>> parseDepartments(ResultSet departments) {
        Map<Long, List<Department>> departmentsHashMap = new HashMap<Long, List<Department>>();

        try {
            while (departments.next()) {
                List<Department> departmentsBox = new ArrayList<Department>();

                Department department = new Department(departments.getString("departmentName"));
                Long id = departments.getLong("employeeId");

                if (departmentsHashMap.containsKey(id)) {
                    departmentsHashMap.get(id).add(department);
                } else {
                    departmentsBox.add(department);
                    departmentsHashMap.put(id, departmentsBox);
                }
            }
        } catch (SQLException e) {
            LOGGER.error("SQL exception", e);
            throw new BusinessException(e);
        }
        return departmentsHashMap;
    }
}
