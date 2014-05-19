package ua.av.entities;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.*;
import static org.junit.Assert.*;

public class EntitiesTest {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    @Test
    public void testEmployee() {
        Employee employee = new Manager();

        employee.setLastName("Khvorov");
        employee.setFirstName("Artem");
        employee.setId(69);
        assertEquals(employee.getLastName(), "Khvorov");
        assertEquals(employee.getFirstName(), "Artem");
        assertTrue(employee.getId() == 69);

        employee.setWage(5);
        employee.setBonus(4);
        employee.setPenalty(3);
        assertTrue(employee.calculateSalary() == 6); //wage + bonus - penalty

        assertNull(employee.getDateOfBirth());
        Calendar calendar = getInstance();
        calendar.set(Calendar.YEAR, 1990);
        calendar.set(Calendar.MONTH, AUGUST);
        calendar.set(Calendar.DAY_OF_MONTH, 7);
        employee.setDateOfBirth(calendar.getTime());
        assertNotNull(employee.getDateOfBirth());
        assertEquals(SIMPLE_DATE_FORMAT.format(employee.getDateOfBirth()), "07/08/1990");
    }

    @Test
    public void testEmployeeConstructor() {
        Calendar calendar = getInstance();
        calendar.set(Calendar.YEAR, 1989);
        calendar.set(Calendar.MONTH, JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 5);

        Developer developer = new Developer(1, "Ievleva", "Vita", calendar.getTime(), 10000, 500, 35, 0, 1000);

        assertTrue(developer.getId() == 1);
        assertEquals(developer.getFirstName(), "Vita");
        assertEquals(developer.getLastName(), "Ievleva");
        assertEquals(SIMPLE_DATE_FORMAT.format(developer.getDateOfBirth()), "05/01/1989");
        assertTrue(developer.getWage() == 10000);
        assertTrue(developer.getBonus() == 500);
        assertTrue(developer.getPenalty() == 35);
        assertTrue(developer.getSalary() == 0);
        assertTrue(developer.getLinesOfCode() == 1000);

        developer.setSalary(developer.calculateSalary()); //(wage + bonus + penalty) + linesOfCode / 100
        assertTrue(developer.getSalary() == 10475.0);
    }

    @Test
    public void testCalculateSalary() {
        Manager manager = new Manager();
        Developer developer = new Developer();
        Cleaner cleaner = new Cleaner();

        manager.setWage(500);
        manager.setBonus(100);
        manager.setPenalty(50);
        assertTrue(manager.calculateSalary() == 550);
        manager.setAmountOfSales(1000);
        manager.setPercentageOfSales(10);
        assertTrue(manager.calculateSalary() == 650);

        developer.setWage(300);
        developer.setBonus(50);
        developer.setPenalty(0);
        assertTrue(developer.calculateSalary() == 350);
        developer.setLinesOfCode(1000);
        assertTrue(developer.calculateSalary() == 360);

        cleaner.setWage(700);
        cleaner.setBonus(1);
        cleaner.setPenalty(50);
        assertTrue(cleaner.calculateSalary() == 651);
        cleaner.setAmountOfCleanedOffices(5);
        assertTrue(cleaner.calculateSalary() == 701);
    }
}
