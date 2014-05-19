package ua.av.controllers;

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

    @RequestMapping(value = "/addEmployeesToDepartment.html")
    public ModelAndView addEmployeesToDepartment() {
        List<Department> departments = SelectDepartments.selectDepartmentsFromDatabase();
        List<Employee> managers = SelectEmployees.selectManagers();
        List<Employee> developers = SelectEmployees.selectDevelopers();
        List<Employee> cleaners = SelectEmployees.selectCleaners();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", managers);
        modelMap.addAttribute("developers", developers);
        modelMap.addAttribute("cleaners", cleaners);
        modelMap.addAttribute("departments", departments);

        return new ModelAndView("addEmployeesToDepartment", modelMap);
    }

    @RequestMapping(value = "/addEmployeesToDepartmentResult.html")
    public ModelAndView addEmployeesToDepartmentResult(WebRequest request) {
        boolean result = AddEmployeesToDepartment.addEmployeesToDepartment(request);

        return new ModelAndView("addEmployeesToDepartmentResult", "result", result);
    }
}
