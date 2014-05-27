package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddEmployeesToDepartmentDao;
import ua.av.database.SelectDepartmentsDao;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Department;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class AddEmployeesToDepartmentController {

    @Autowired
    private SelectEmployeesDao selectEmployeesDao;
    @Autowired
    private AddEmployeesToDepartmentDao addEmployeesToDepartmentDao;
    @Autowired
    private SelectDepartmentsDao selectDepartmentsDao;

    @RequestMapping(value = "/addEmployeesToDepartment.html")
    public ModelAndView addEmployeesToDepartment() {
        List<Department> departments = selectDepartmentsDao.selectDepartmentsFromDatabase();
        List<Employee> employees = selectEmployeesDao.selectAllEmployees();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("employees", employees);
        modelMap.addAttribute("departments", departments);

        return new ModelAndView("addEmployeesToDepartment", modelMap);
    }

    @RequestMapping(value = "/addEmployeesToDepartmentResult.html")
    public ModelAndView addEmployeesToDepartmentResult(WebRequest request) {
        String[] stringIdsArray = request.getParameterValues("employeeId");
        String[] departmentsArray = request.getParameterValues("department");

        boolean result = addEmployeesToDepartmentDao.addEmployeesToDepartment(stringIdsArray, departmentsArray);

        return new ModelAndView("addEmployeesToDepartmentResult", "result", result);
    }
}
