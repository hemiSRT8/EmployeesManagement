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
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", selectEmployeesDao.selectManagers());
        modelMap.addAttribute("developers", selectEmployeesDao.selectDevelopers());
        modelMap.addAttribute("cleaners", selectEmployeesDao.selectCleaners());
        modelMap.addAttribute("departments", selectDepartmentsDao.selectDepartmentsFromDatabase());

        return new ModelAndView("addEmployeesToDepartment", modelMap);
    }

    @RequestMapping(value = "/addEmployeesToDepartmentResult.html")
    public ModelAndView addEmployeesToDepartmentResult(WebRequest request) {

        return new ModelAndView("addEmployeesToDepartmentResult", "result",
                addEmployeesToDepartmentDao.addEmployeesToDepartment(request.getParameterValues("employeeId"),
                                                                     request.getParameterValues("department")));
    }
}
