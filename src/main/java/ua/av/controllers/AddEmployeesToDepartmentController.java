package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddEmployeesToDepartment;
import ua.av.database.SelectDepartments;
import ua.av.database.SelectEmployees;
import ua.av.entities.Department;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class AddEmployeesToDepartmentController {

    @Autowired
    private SelectEmployees selectEmployees;
    @Autowired
    private AddEmployeesToDepartment addEmployeesToDepartment;
    @Autowired
    private SelectDepartments selectDepartments;

    @RequestMapping(value = "/addEmployeesToDepartment.html")
    public ModelAndView addEmployeesToDepartment() {
        List<Department> departments = selectDepartments.selectDepartmentsFromDatabase();
        List<Employee> managers = selectEmployees.selectManagers();
        List<Employee> developers = selectEmployees.selectDevelopers();
        List<Employee> cleaners = selectEmployees.selectCleaners();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", managers);
        modelMap.addAttribute("developers", developers);
        modelMap.addAttribute("cleaners", cleaners);
        modelMap.addAttribute("departments", departments);

        return new ModelAndView("addEmployeesToDepartment", modelMap);
    }

    @RequestMapping(value = "/addEmployeesToDepartmentResult.html")
    public ModelAndView addEmployeesToDepartmentResult(WebRequest request) {
        String[] stringIdsArray = request.getParameterValues("employeeId");
        String[] departmentsArray = request.getParameterValues("department");

        boolean result = addEmployeesToDepartment.addEmployeesToDepartment(stringIdsArray, departmentsArray);

        return new ModelAndView("addEmployeesToDepartmentResult", "result", result);
    }
}
