package ua.av.utils;

import org.junit.Before;
import org.junit.Test;
import ua.av.entities.*;

import java.util.*;

import static java.lang.Long.*;
import static org.junit.Assert.*;

public class EmployeeServiceTest {

    private Manager manager;
    private Developer developer;
    private Cleaner cleaner;
    private List<Employee> staff;

    @Before
    public void setup() {
        manager = new Manager();
        developer = new Developer();
        cleaner = new Cleaner();

        staff = new ArrayList<Employee>();

        staff.add(manager);
        staff.add(developer);
        staff.add(cleaner);
    }

    @Test
    public void testSortByProfession() {
        /**
         * After sorting
         */

        EmployeeService.sortByProfession(staff);

        assertTrue(staff.size() == 3);
        assertTrue(staff.get(0) instanceof Cleaner);
        assertTrue(staff.get(1) instanceof Developer);
        assertTrue(staff.get(2) instanceof Manager);

        staff.add(new Cleaner());
        assertTrue(staff.size() == 4);
        assertTrue(staff.get(staff.size() - 1) instanceof Cleaner); //last element is last added employee

        /**
         * After adding new employee and sorting again
         */

        EmployeeService.sortByProfession(staff);

        assertTrue(staff.size() == 4);
        assertTrue(staff.get(staff.size() - 1) instanceof Manager); //after sorting last is manger
    }

    @Test
    public void testSortByFullName() {
        manager.setLastName("A");
        manager.setFirstName("B");

        developer.setLastName("W");
        developer.setFirstName("V");

        cleaner.setLastName("A");
        cleaner.setFirstName("A");
        /**
         * After sorting
         */

        EmployeeService.sortByFullName(staff);

        assertTrue(staff.size() == 3);
        assertEquals(staff.get(0).getLastName(), "A");
        assertEquals(staff.get(0).getLastName(), "A");

        assertEquals(staff.get(1).getLastName(), "A");
        assertEquals(staff.get(1).getFirstName(), "B");

        assertEquals(staff.get(2).getLastName(), "W");
        assertEquals(staff.get(2).getFirstName(), "V");
    }

    @Test
    public void testSortByDateOfBirth() {
        manager.setDateOfBirth(new Date(10));
        developer.setDateOfBirth(new Date(15));
        cleaner.setDateOfBirth(new Date(5));

        /**
         * After sorting (From youngest to adults)
         */

        EmployeeService.sortByDateOfBirth(staff);

        assertTrue(staff.size() == 3);

        assertTrue(staff.get(0).getDateOfBirth().getTime() == 15);
        assertTrue(staff.get(0) instanceof Developer);

        assertTrue(staff.get(1).getDateOfBirth().getTime() == 10);
        assertTrue(staff.get(1) instanceof Manager);

        assertTrue(staff.get(2).getDateOfBirth().getTime() == 5);
        assertTrue(staff.get(2) instanceof Cleaner);
    }

    @Test
    public void testSortBySalary() {
        manager.setSalary(3);
        developer.setSalary(2);
        cleaner.setSalary(1);

        /**
         * After sorting
         */

        EmployeeService.sortBySalary(staff);

        assertTrue(staff.size() == 3);
        assertTrue(staff.get(0).getSalary() == 1);
        assertTrue(staff.get(1).getSalary() == 2);
        assertTrue(staff.get(2).getSalary() == 3);
    }

    @Test
    public void testSearchById() {
        manager.setId(1);
        developer.setId(2);
        cleaner.setId(3);

        /**
         * After searching
         */

        Employee employee;

        employee = EmployeeService.searchById(1, staff);
        assertTrue(employee.getId() == 1);

        employee = EmployeeService.searchById(2, staff);
        assertTrue(employee.getId() == 2);

        employee = EmployeeService.searchById(3, staff);
        assertTrue(employee.getId() == 3);

        Employee invalidEmployee = EmployeeService.searchById(555, staff);
        assertNull(invalidEmployee);
    }

    @Test
    public void testSearchByLastName() {
        manager.setLastName("aaa");
        developer.setLastName("bbb");
        cleaner.setLastName("ccc");

        /**
         * After searching
         */

        assertTrue(staff.size() == 3);

        List<Employee> result;

        result = EmployeeService.searchByLastName("aaa", staff);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0).getLastName(), "aaa");

        result = EmployeeService.searchByLastName("bbb", staff);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0).getLastName(), "bbb");

        result = EmployeeService.searchByLastName("ccc", staff);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0).getLastName(), "ccc");

        result = EmployeeService.searchByLastName("Not valid last name", staff);
        assertTrue(result.size() == 0);
    }

    @Test
    public void testSearchByFullName() {
        manager.setLastName("aaa");
        manager.setFirstName("aaA");

        developer.setLastName("aaa");
        developer.setFirstName("aaB");

        cleaner.setLastName("aaa");
        cleaner.setFirstName("aaC");

        /**
         * After searching
         */

        assertTrue(staff.size() == 3);

        List<Employee> result;

        result = EmployeeService.searchByFullName("aaa", "aaA", staff);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0).getLastName(), "aaa");
        assertEquals(result.get(0).getFirstName(), "aaA");

        result = EmployeeService.searchByFullName("aaa", "aaB", staff);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0).getLastName(), "aaa");
        assertEquals(result.get(0).getFirstName(), "aaB");

        result = EmployeeService.searchByFullName("aaa", "aaC", staff);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0).getLastName(), "aaa");
        assertEquals(result.get(0).getFirstName(), "aaC");

        result = EmployeeService.searchByFullName("Not valid lastName", "Not valid firstName", staff);
        assertTrue(result.size() == 0);
    }

    @Test
    public void testLinkDepartmentsToEmployees() {
        Map<Long, List<Department>> departmentsMap = new HashMap<Long, List<Department>>();
        Department department = new Department("It department");
        List<Department> departmentList = new ArrayList<Department>();

        Long managerId = valueOf(1);
        Long developerId = valueOf(3);
        Long cleanerId = valueOf(2);
        manager.setId(managerId);
        developer.setId(developerId);
        cleaner.setId(cleanerId);

        /**
         * After linking
         */

        departmentList.add(department);
        departmentsMap.put(managerId, departmentList);
        EmployeeService.linkDepartmentsToEmployees(staff, departmentsMap);
        assertTrue(manager.getDepartment().size() == 1);
        assertEquals(manager.getDepartment().get(0).getName(), "It department");

        department.setName("Economical Department");
        departmentsMap.put(developerId, departmentList);
        EmployeeService.linkDepartmentsToEmployees(staff, departmentsMap);
        assertTrue(developer.getDepartment().size() == 1);
        assertEquals(developer.getDepartment().get(0).getName(), "Economical Department");

        department.setName("Logistic department");
        departmentsMap.put(cleanerId, departmentList);
        EmployeeService.linkDepartmentsToEmployees(staff, departmentsMap);
        assertTrue(cleaner.getDepartment().size() == 1);
        assertEquals(cleaner.getDepartment().get(0).getName(), "Logistic department");
    }
}
