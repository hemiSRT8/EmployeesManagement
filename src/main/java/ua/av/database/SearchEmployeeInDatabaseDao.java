package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.entities.Employee;
import ua.av.utils.EmployeeService;

import java.util.List;

@Component
public class SearchEmployeeInDatabaseDao {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> searchEmployee(String lastName) {
        return EmployeeService.searchByLastName(lastName, employeeDao.getAllEmployees());
    }
}
