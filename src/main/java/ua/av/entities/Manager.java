package ua.av.entities;

import java.util.Date;

public class Manager extends Employee {

    private double amountOfSales;
    private double percentageOfSales;

    public Manager() {
        //default constructor
    }

    public Manager(long id, String lastName, String firstName, Date dateOfBirth,
                   double wage, double bonus, double penalty, double salary,
                   double amountOfSales, double percentageOfSales) {

        super(id, lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);
        this.amountOfSales = amountOfSales;
        this.percentageOfSales = percentageOfSales;
    }

    public Manager(String lastName, String firstName, Date dateOfBirth,
                   double wage, double bonus, double penalty, double salary,
                   double amountOfSales, double percentageOfSales) {

        super(lastName, firstName, dateOfBirth, wage, bonus, penalty, salary);
        this.amountOfSales = amountOfSales;
        this.percentageOfSales = percentageOfSales;
    }

    @Override
    public double calculateSalary() {
        return super.calculateSalary()
                + amountOfSales / 100 * percentageOfSales;
    }

    @Override
    public String toString() {
        return "Profession : manager" + "\n"
                + super.toString()
                + "Amount of sales : " + amountOfSales + "\n"
                + "Percentage of sales : " + percentageOfSales;
    }

    public double getAmountOfSales() {
        return amountOfSales;
    }

    public void setAmountOfSales(double amountOfSales) {
        this.amountOfSales = amountOfSales;
    }

    public double getPercentageOfSales() {
        return percentageOfSales;
    }

    public void setPercentageOfSales(double percentageOfSales) {
        this.percentageOfSales = percentageOfSales;
    }
}
