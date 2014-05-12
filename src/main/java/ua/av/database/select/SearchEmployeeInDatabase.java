package ua.av.database.select;

import org.springframework.web.context.request.WebRequest;
import ua.av.entities.Employee;
import ua.av.utils.SearchEmployee;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeInDatabase {

    public List<Employee> searchEmployee(WebRequest request) {
        List<Employee> result = new ArrayList<Employee>();
        List<Employee> temp;
        String lastName = request.getParameter("lastName");
        SelectEmployees selectEmployees = new SelectEmployees();
        SearchEmployee searchEmployee = new SearchEmployee();

        /**
         * Search in managers
         */
        searchEmployee.setEmployees(selectEmployees.selectManagers());
        temp = searchEmployee.searchByLastName(lastName);
        if (!temp.isEmpty()) {
            for (Employee e : temp) {
                result.add(e);
            }
        }

        temp.clear();

        /**
         * Search in developers
         */
        searchEmployee.setEmployees(selectEmployees.selectDevelopers());
        temp = searchEmployee.searchByLastName(lastName);
        if (!temp.isEmpty()) {
            for (Employee e : temp) {
                result.add(e);
            }
        }

        temp.clear();

        /**
         * Search in cleaners
         */
        searchEmployee.setEmployees(selectEmployees.selectCleaners());
        temp = searchEmployee.searchByLastName(lastName);
        if (!temp.isEmpty()) {
            for (Employee e : temp) {
                result.add(e);
            }
        }

        return result;
    }
}
