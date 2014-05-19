package ua.av.utils;


import ua.av.entities.Employee;
import ua.av.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployee {

    public static Employee searchById(long id, List<Employee> employees) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public static List<Employee> searchByLastName(String lastName, List<Employee> employees) {
        if (lastName == null) {
            throw new BusinessException("lastName is null");
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
        if (lastName == null) {
            throw new BusinessException("lastName is null");
        } else if (firstName == null) {
            throw new BusinessException("firstName is null");
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