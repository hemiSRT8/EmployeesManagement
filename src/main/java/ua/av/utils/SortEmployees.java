package ua.av.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.av.entities.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortEmployees {

    private static final Logger LOGGER = LoggerFactory.getLogger(SortEmployees.class);

    public List<Employee> sortByFullName(List<Employee> employeesToSort) {
        if (employeesToSort == null) {
            LOGGER.error("employeesToSort was null , empty list was returned");
            return new ArrayList<Employee>();
        }

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

        return employeesToSort;
    }

    public List<Employee> sortByDateOfBirth(List<Employee> employeesToSort) {
        if (employeesToSort == null) {
            LOGGER.error("employeesToSort was null , empty list was returned");
            return new ArrayList<Employee>();
        }

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

        return employeesToSort;
    }

    public List<Employee> sortBySalary(List<Employee> employeesToSort) {
        if (employeesToSort == null) {
            LOGGER.error("employeesToSort was null , empty list was returned");
            return new ArrayList<Employee>();
        }

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

        return employeesToSort;
    }
}
