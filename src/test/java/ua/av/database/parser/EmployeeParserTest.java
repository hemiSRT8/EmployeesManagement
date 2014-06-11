package ua.av.database.parser;

import org.junit.Before;
import org.junit.Test;
import ua.av.entities.*;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EmployeeParserTest {


    private ResultSet resultSet;
    private List<Employee> result;

    private long id;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private double wage;
    private double bonus;
    private double penalty;
    private double salary;

    @Before
    public void setup() {
        resultSet = mock(ResultSet.class);

        id = 1L;
        lastName = "Player";
        firstName = "Gamer";
        dateOfBirth = new Date(123);
        wage = 5.5;
        bonus = 4.4;
        penalty = 3.3;
        salary = 2.2;

        try {
            when(resultSet.next())
                    .thenReturn(true).thenReturn(false) //manager`s test
                    .thenReturn(true).thenReturn(false) //developer`s test
                    .thenReturn(true).thenReturn(false); //cleaner`s test

            when(resultSet.getLong("id")).thenReturn(id);
            when(resultSet.getString("lastName")).thenReturn(lastName);
            when(resultSet.getString("firstName")).thenReturn(firstName);
            when(resultSet.getDate("dateOfBirth")).thenReturn(dateOfBirth);
            when(resultSet.getDouble("wage")).thenReturn(wage);
            when(resultSet.getDouble("bonus")).thenReturn(bonus);
            when(resultSet.getDouble("penalty")).thenReturn(penalty);
            when(resultSet.getDouble("salary")).thenReturn(salary);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testManagerParser() {
        double amountOfSales = 10.10;
        double percentageOfSales = 11.11;

        try {
            when(resultSet.getString("profession")).thenReturn("manager");
            when(resultSet.getDouble("amountOfSales")).thenReturn(amountOfSales);
            when(resultSet.getDouble("percentageOfSales")).thenReturn(percentageOfSales);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result = EmployeeParser.parseEmployees(resultSet);
        Manager manager = (Manager) result.get(0);
        /**
         * Validation
         */
        assertTrue(result.size() == 1); //we send only 1 employee:D
        assertTrue(manager.getId() == id);
        assertEquals(manager.getLastName(), lastName);
        assertEquals(manager.getFirstName(), firstName);
        assertEquals(manager.getDateOfBirth(), dateOfBirth);
        assertTrue(manager.getWage() == wage);
        assertTrue(manager.getBonus() == bonus);
        assertTrue(manager.getPenalty() == penalty);
        assertTrue(manager.getSalary() == salary);
        assertTrue(manager.getAmountOfSales() == amountOfSales);
        assertTrue(manager.getPercentageOfSales() == percentageOfSales);
    }

    @Test
    public void testDeveloperParser() {
        int linesOfCode = 12;

        try {
            when(resultSet.getString("profession")).thenReturn("developer");
            when(resultSet.getInt("linesOfCode")).thenReturn(linesOfCode);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result = EmployeeParser.parseEmployees(resultSet);
        Developer developer = (Developer) result.get(0);
        /**
         * Validation
         */
        assertTrue(result.size() == 1); //we send only 1 employee:D
        assertTrue(developer.getId() == id);
        assertEquals(developer.getLastName(), lastName);
        assertEquals(developer.getFirstName(), firstName);
        assertEquals(developer.getDateOfBirth(), dateOfBirth);
        assertTrue(developer.getWage() == wage);
        assertTrue(developer.getBonus() == bonus);
        assertTrue(developer.getPenalty() == penalty);
        assertTrue(developer.getSalary() == salary);
        assertTrue(developer.getLinesOfCode() == linesOfCode);
    }

    @Test
    public void testCleanerParser() {
        int amountOfCleanedOffices = 13;

        try {
            when(resultSet.getString("profession")).thenReturn("cleaner");
            when(resultSet.getInt("amountOfCleanedOffices")).thenReturn(amountOfCleanedOffices);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        result = EmployeeParser.parseEmployees(resultSet);
        Cleaner cleaner = (Cleaner) result.get(0);
        /**
         * Validation
         */
        assertTrue(result.size() == 1); //we send only 1 employee:D
        assertTrue(cleaner.getId() == id);
        assertEquals(cleaner.getLastName(), lastName);
        assertEquals(cleaner.getFirstName(), firstName);
        assertEquals(cleaner.getDateOfBirth(), dateOfBirth);
        assertTrue(cleaner.getWage() == wage);
        assertTrue(cleaner.getBonus() == bonus);
        assertTrue(cleaner.getPenalty() == penalty);
        assertTrue(cleaner.getSalary() == salary);
        assertTrue(cleaner.getAmountOfCleanedOffices() == amountOfCleanedOffices);
    }
}
