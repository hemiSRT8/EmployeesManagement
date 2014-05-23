package ua.av.entities;

import java.util.Date;

public class Cleaner extends Employee {

    private int amountOfCleanedOffices;

    public Cleaner() {
        //default constructor
    }

    public Cleaner(long id, String lastName, String firstName, Date dateOfBirth,
                   double wage, double bonus, double penalty, double salary,
                   int amountOfCleanedOffices) {
        super(id, lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);
        this.amountOfCleanedOffices = amountOfCleanedOffices;
    }

    public Cleaner(String lastName, String firstName, Date dateOfBirth,
                   double wage, double bonus, double penalty, double salary,
                   int amountOfCleanedOffices) {
        super(lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);
        this.amountOfCleanedOffices = amountOfCleanedOffices;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary() + amountOfCleanedOffices * 10;
    }

    @Override
    public String toString() {
        return "Profession : cleaner" + "\n"
                + super.toString()
                + "Amount of cleaned offices : " + amountOfCleanedOffices;
    }

    public int getAmountOfCleanedOffices() {
        return amountOfCleanedOffices;
    }

    public void setAmountOfCleanedOffices(int amountOfCleanedOffices) {
        this.amountOfCleanedOffices = amountOfCleanedOffices;
    }
}
