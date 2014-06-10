package ua.av.database.parser;

import org.junit.Test;
import ua.av.entities.*;

import java.sql.*;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class EmployeeParserTest {

    @Test
    public void testEmployeeParser() {
        ResultSet resultSet = mock(ResultSet.class);
        List<Employee> result;

        /**
         * Setup employee fields
         */

        Long id = 1L;
        String lastName = "Player";
        String firstName = "Gamer";
        Date dateOfBirth = new Date(123);
        double wage = 5.5;
        double bonus = 4.4;
        double penalty = 3.3;
        double salary = 2.2;

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

        /**
         * Test manager`s parser
         */

        Manager manager = null;
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
        assertTrue(result.size() == 1); //we send only 1 employee:D

        manager = (Manager) result.get(0);

        /**
         * Employee`s general fields validation + manager specific fields
         */

        assertTrue(manager.getId() == 1);
        assertEquals(manager.getLastName(), lastName);
        assertEquals(manager.getFirstName(), firstName);
        assertEquals(manager.getDateOfBirth(), dateOfBirth);
        assertTrue(manager.getWage() == wage);
        assertTrue(manager.getBonus() == bonus);
        assertTrue(manager.getPenalty() == penalty);
        assertTrue(manager.getSalary() == salary);

        assertTrue(manager.getAmountOfSales() == amountOfSales);
        assertTrue(manager.getPercentageOfSales() == percentageOfSales);

        /**
         * Test developer`s parser
         */

        Developer developer = null;
        int linesOfCode = 12;

        try {
            when(resultSet.getString("profession")).thenReturn("developer");
            when(resultSet.getInt("linesOfCode")).thenReturn(linesOfCode);

            result = EmployeeParser.parseEmployees(resultSet);
            assertTrue(result.size() == 1); //we send only 1 employee:D

            developer = (Developer) result.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(developer.getLinesOfCode() == linesOfCode);

        /**
         * Test cleaner`s parser
         */

        Cleaner cleaner = null;
        int amountOfCleanedOffices = 13;

        try {
            when(resultSet.getString("profession")).thenReturn("cleaner");
            when(resultSet.getInt("amountOfCleanedOffices")).thenReturn(amountOfCleanedOffices);

            result = EmployeeParser.parseEmployees(resultSet);
            assertTrue(result.size() == 1); //we send only 1 employee:D

            cleaner = (Cleaner) result.get(0);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(cleaner.getAmountOfCleanedOffices() == amountOfCleanedOffices);
    }
}
