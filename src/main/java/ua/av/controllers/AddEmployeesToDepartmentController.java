package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.add.AddEmployeesToDepartment;
import ua.av.database.select.SelectDepartments;
import ua.av.database.select.SelectEmployees;
import ua.av.department.Department;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class AddEmployeesToDepartmentController {

    @RequestMapping(value = "/addEmployeesToDepartment.html")
    public ModelAndView addEmployeesToDepartment() {
        SelectDepartments selectDepartments = new SelectDepartments();
        SelectEmployees selectEmployees = new SelectEmployees();

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
        AddEmployeesToDepartment addEmployeesToDepartment = new AddEmployeesToDepartment();
        addEmployeesToDepartment.addEmployeesToDepartment(request);

        return new ModelAndView("redirect:/index.html");
    }
}
