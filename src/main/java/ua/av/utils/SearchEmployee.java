package ua.av.utils;


import ua.av.entities.Employee;
import ua.av.exception.BusinessException;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployee {

    private List<Employee> employees;

    public Employee searchById(long id) {
        for (Employee e : employees) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Employee> searchByLastName(String lastName) {
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

    public List<Employee> searchByFullName(String lastName, String firstName) {
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

    public List<Employee> searchByProfession(Class profession) {
        if (!profession.isInstance(Employee.class)) {
            throw new BusinessException("Parameter profession is not instance of the Employee's classes.");
        }

        List<Employee> result = new ArrayList<Employee>();

        for (Employee e : employees) {
            if (e.getClass().isInstance(profession)) {
                result.add(e);
            }
        }

        return result;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}