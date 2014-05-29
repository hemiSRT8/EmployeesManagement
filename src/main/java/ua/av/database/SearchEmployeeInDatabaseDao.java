package ua.av.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.av.entities.Employee;
import ua.av.utils.SearchEmployee;

import java.util.List;

@Component
public class SearchEmployeeInDatabaseDao {

    @Autowired
    private EmployeeCRUDDao employeeCRUDDao;

    public List<Employee> searchEmployee(String lastName) {
        return SearchEmployee.searchByLastName(lastName, employeeCRUDDao.selectAllEmployees());
    }
}
