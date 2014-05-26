package ua.av.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.av.entities.Employee;
import ua.av.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployee {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchEmployee.class);

    public static Employee searchById(long id, List<Employee> employees) {
        if (employees == null) {
            LOGGER.error("lastName was null");
            throw new BusinessException();
        }

        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public static List<Employee> searchByLastName(String lastName, List<Employee> employees) {
        if (lastName == null || employees == null) {
            LOGGER.error("lastName or employees list were null , empty list was returned");
            return new ArrayList<Employee>();
        }

        List<Employee> result = new ArrayList<Employee>();

        for (Employee e : employees) {
            if (lastName.equals(e.getLastName())) {
                result.add(e);
            }
        }

        return result;
    }

    public static List<Employee> searchByFullName(String lastName, String firstName, List<Employee> employees) {
        if (lastName == null || firstName == null || employees == null) {
            LOGGER.error("lastName or firstName or employees were null , empty list was returned");
            return new ArrayList<Employee>();
        }

        List<Employee> result = new ArrayList<Employee>();

        for (Employee e : employees) {
            if (lastName.equals(e.getLastName()) && firstName.equals(e.getFirstName())) {
                result.add(e);
            }
        }

        return result;
    }
}