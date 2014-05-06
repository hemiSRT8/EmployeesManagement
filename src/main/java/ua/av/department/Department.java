package ua.av.department;

import ua.av.entities.Employee;

import java.util.List;

public class Department {

    private String name;
    private List<Employee> listOfEmployees;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getListOfEmployees() {
        return listOfEmployees;
    }
}
