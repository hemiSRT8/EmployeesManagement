package ua.av.entities;

import java.util.Date;

public class Developer extends Employee {

    private int linesOfCode;

    public Developer() {
        //default constructor
    }

    public Developer(long id, String lastName, String firstName, Date dateOfBirth,
                     double wage, double bonus, double penalty, double salary,
                     int linesOfCode) {

        super(id, lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);
        this.linesOfCode = linesOfCode;
    }

    public Developer(String lastName, String firstName, Date dateOfBirth,
                     double wage, double bonus, double penalty, double salary,
                     int linesOfCode) {

        super(lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);
        this.linesOfCode = linesOfCode;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary()
                + linesOfCode / 100;
    }

    @Override
    public String toString() {
        return "Profession : developer" + "\n"
                + super.toString()
                + "Lines of code :" + linesOfCode;
    }

    public int getLinesOfCode() {
        return linesOfCode;
    }

    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }
}
