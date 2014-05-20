package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import ua.av.entities.Employee;
import ua.av.utils.SearchEmployee;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEmployeeInDatabase {

    @Autowired
    private SelectEmployees selectEmployees;

    public List<Employee> searchEmployee(WebRequest request) {
        List<Employee> result = new ArrayList<Employee>();
        List<Employee> temp;
        String lastName = request.getParameter("lastName");

        /**
         * Search in managers
         */
        temp = SearchEmployee.searchByLastName(lastName, selectEmployees.selectManagers());
        if (!temp.isEmpty()) {
            for (Employee e : temp) {
                result.add(e);
            }
        }

        temp.clear();

        /**
         * Search in developers
         */
        temp = SearchEmployee.searchByLastName(lastName, selectEmployees.selectDevelopers());
        if (!temp.isEmpty()) {
            for (Employee e : temp) {
                result.add(e);
            }
        }

        temp.clear();

        /**
         * Search in cleaners
         */
        temp = SearchEmployee.searchByLastName(lastName, selectEmployees.selectCleaners());
        if (!temp.isEmpty()) {
            for (Employee e : temp) {
                result.add(e);
            }
        }

        return result;
    }
}
