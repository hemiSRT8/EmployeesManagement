package ua.av.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.av.entities.Department;
import ua.av.entities.Employee;

import java.util.*;

public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    public static List<Employee> sortByProfession(List<Employee> employeesToSort) {
        LOGGER.info("employee sort by profession started");
        Collections.sort(employeesToSort, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getClass().getCanonicalName().compareTo(o2.getClass().getCanonicalName());
            }
        });
        LOGGER.info("employees sort by profession finished");

        return employeesToSort;
    }

    public static List<Employee> sortByFullName(List<Employee> employeesToSort) {
        LOGGER.info("employee sort by full name started");
        Collections.sort(employeesToSort, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int compareLastNames = o1.getLastName().compareTo(o2.getLastName());

                if (compareLastNames == 0) {
                    return o1.getFirstName().compareTo(o2.getFirstName());
                } else {
                    return compareLastNames;
                }
            }
        });
        LOGGER.info("employees sort by full name finished");

        return employeesToSort;
    }

    public static List<Employee> sortByDateOfBirth(List<Employee> employeesToSort) {
        LOGGER.info("employee sort by date of birth started");
        Collections.sort(employeesToSort, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1.getDateOfBirth().before(o2.getDateOfBirth())) {
                    return 1;
                } else if (o1.getDateOfBirth().after(o2.getDateOfBirth())) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        LOGGER.info("employees sort by date of birth finished");

        return employeesToSort;
    }

    public static List<Employee> sortBySalary(List<Employee> employeesToSort) {
        LOGGER.info("employee sort by salary started");
        Collections.sort(employeesToSort, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                double result = o1.getSalary() - o2.getSalary();

                if (result > 0) {
                    return 1;
                } else if (result < 0) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        LOGGER.info("employees sort by salary finished");

        return employeesToSort;
    }

    public static Employee searchById(long id, List<Employee> employees) {
        LOGGER.info("employee search by id {} started", id);
        for (Employee e : employees) {
            if (e.getId() == id) {
                LOGGER.info("employee was found by id : {}", id);
                return e;
            }
        }

        LOGGER.info("employee search by id {} finished,no one was found", id);
        return null;
    }

    public static List<Employee> searchByLastName(String lastName, List<Employee> employees) {
        LOGGER.info("employee search by last name ({}) started", lastName);
        List<Employee> result = new ArrayList<Employee>();

        for (Employee e : employees) {
            if (lastName.equals(e.getLastName())) {
                result.add(e);
            }
        }
        LOGGER.info("employee search by last name ({}) finished", lastName);

        return result;
    }

    public static List<Employee> searchByFullName(String lastName, String firstName, List<Employee> employees) {
        LOGGER.info("employee search by full name ({} , {}) started", lastName, firstName);
        List<Employee> result = new ArrayList<Employee>();

        for (Employee e : employees) {
            if (lastName.equals(e.getLastName()) && firstName.equals(e.getFirstName())) {
                result.add(e);
            }
        }
        LOGGER.info("employee search by full name ({} , {}) finished", lastName, firstName);

        return result;
    }

    public static List<Employee> linkDepartmentsToEmployees(List<Employee> employees, Map<Long, List<Department>> departmentsMap) {
        List<Employee> result = new ArrayList<Employee>(employees);
        for (Employee employee : employees) {
            Long id = employee.getId();
            List<Department> departments = departmentsMap.get(id);
            employee.setDepartment(departments);
        }

        return result;
    }
}
