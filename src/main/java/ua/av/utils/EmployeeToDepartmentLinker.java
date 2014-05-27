package ua.av.utils;

import org.springframework.stereotype.Component;
import ua.av.entities.Department;
import ua.av.entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EmployeeToDepartmentLinker {

    public List<Employee> linkDeparmentsToEmployees(List<Employee> employees, Map<Long, List<Department>> departmentsMap) {
        List<Employee> result = new ArrayList<Employee>(employees);
        for (Employee employee : employees) {
            Long id = employee.getId();
            List<Department> departments = departmentsMap.get(id);
            employee.setDepartment(departments);
            for (Department department : departments) {
                department.getListOfEmployees().add(employee);
            }
        }
        return result;
    }
}
