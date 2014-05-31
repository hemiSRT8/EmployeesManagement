package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EmployeeCRUDDao;
import ua.av.entities.Employee;
import ua.av.utils.EmployeeService;

import java.util.List;

@Controller
public class MainPageController {

    @Autowired
    private EmployeeCRUDDao employeeCRUDDao;

    @RequestMapping(value = "/index.html")
    public ModelAndView mainPage(WebRequest request) {
        String sortType = request.getParameter("sortType");
        ModelMap modelMap = new ModelMap();

        List<Employee> employees = employeeCRUDDao.selectAllEmployees();

        if (sortType == null) { //Shall return default index.html page
            modelMap.addAttribute("employees", employees);
        } else if (sortType.compareTo("fullName") == 0) {
            modelMap.addAttribute("employees", EmployeeService.sortByFullName(employees));
            return new ModelAndView("index", modelMap);

        } else if (sortType.compareTo("dateOfBirth") == 0) {
            modelMap.addAttribute("employees", EmployeeService.sortByDateOfBirth(employees));
            return new ModelAndView("index", modelMap);

        } else if (sortType.compareTo("salary") == 0) {
            modelMap.addAttribute("employees", EmployeeService.sortBySalary(employees));
            return new ModelAndView("index", modelMap);

        } else if (sortType.compareTo("profession") == 0) {
            modelMap.addAttribute("employees", EmployeeService.sortByClass(employees));
            return new ModelAndView("index", modelMap);
        }

        return new ModelAndView("index", modelMap);
    }
}



