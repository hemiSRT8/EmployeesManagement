package ua.av.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Employee {

    private long id;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private double wage;
    private double bonus;
    private double penalty;
    private double salary;
    private List<Department> department = new ArrayList<Department>();

    protected Employee() {
        //default constructor
    }

    protected Employee(long id, String lastName, String firstName, Date dateOfBirth,
                       double wage, double bonus, double penalty, double salary) {

        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.wage = wage;
        this.bonus = bonus;
        this.penalty = penalty;
        this.salary = salary;
    }

    public double calculateSalary() {
        return wage + bonus - penalty;
    }

    @Override
    public String toString() {
        return "Last name : " + lastName + "\n" +
                "First name :" + firstName + "\n"
                + "Date of birth : " + dateOfBirth + "\n"
                + "Wage : " + wage + "\n"
                + "Bonus :" + bonus + "\n"
                + "Penalty : " + penalty + "\n"
                + "Salary : " + salary + "\n";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public List<Department> getDepartment() {
        return department;
    }

    public void setDepartment(List<Department> department) {
        this.department = department;
    }

    public void addDepartment(Department department) {
        this.department.add(department);
    }
}
