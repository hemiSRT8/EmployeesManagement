package ua.av.database.parser;

import org.junit.Test;
import ua.av.entities.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class EmployeeParserTest {

    @Test
    public void testEmployeeParser() {
        ResultSet resultSet = mock(ResultSet.class);
        List<Employee> result;
        Manager manager = null;
        Date dateOfBirth = null;

        /**
         * Test manager`s parser
         */

        try {
            dateOfBirth = new Date(123);
            when(resultSet.next()).thenReturn(true).thenReturn(false);
            when(resultSet.getString("profession")).thenReturn("manager");
            when(resultSet.getLong("id")).thenReturn((long) 1);
            when(resultSet.getString("lastName")).thenReturn("Player");
            when(resultSet.getString("firstName")).thenReturn("Gamer");
            when(resultSet.getDate("dateOfBirth")).thenReturn(dateOfBirth);
            when(resultSet.getDouble("wage")).thenReturn(5.5);
            when(resultSet.getDouble("bonus")).thenReturn(4.4);
            when(resultSet.getDouble("penalty")).thenReturn(3.3);
            when(resultSet.getDouble("salary")).thenReturn(2.2);
            when(resultSet.getDouble("amountOfSales")).thenReturn(10.10);
            when(resultSet.getDouble("percentageOfSales")).thenReturn(11.11);

            result = EmployeeParser.parseEmployees(resultSet);

            if (result.size() == 1) {
                manager = (Manager) result.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(manager.getId() == 1);
        assertEquals(manager.getLastName(), "Player");
        assertEquals(manager.getFirstName(), "Gamer");
        assertEquals(manager.getDateOfBirth(), dateOfBirth);
        assertTrue(manager.getWage() == 5.5);
        assertTrue(manager.getBonus() == 4.4);
        assertTrue(manager.getPenalty() == 3.3);
        assertTrue(manager.getSalary() == 2.2);

        assertTrue(manager.getAmountOfSales() == 10.10);
        assertTrue(manager.getPercentageOfSales() == 11.11);

        /**
         * Test developer`s parser
         */

        Developer developer = null;

        try {
            when(resultSet.getString("profession")).thenReturn("developer");
            when(resultSet.getInt("linesOfCode")).thenReturn(12);
            when(resultSet.next()).thenReturn(true).thenReturn(false);

            result = EmployeeParser.parseEmployees(resultSet);

            if (result.size() == 1) {
                developer = (Developer) result.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(developer.getLinesOfCode() == 12);

        /**
         * Test cleaner`s parser
         */

        Cleaner cleaner = null;

        try {
            when(resultSet.getString("profession")).thenReturn("cleaner");
            when(resultSet.getInt("amountOfCleanedOffices")).thenReturn(13);
            when(resultSet.next()).thenReturn(true).thenReturn(false);

            result = EmployeeParser.parseEmployees(resultSet);

            if (result.size() == 1) {
                cleaner = (Cleaner) result.get(0);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertTrue(cleaner.getAmountOfCleanedOffices() == 13);
    }
}
