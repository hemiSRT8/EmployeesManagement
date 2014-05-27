package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Employee;
import ua.av.entities.Manager;
import ua.av.utils.SortEmployees;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private SelectEmployeesDao selectEmployeesDao;

    @RequestMapping(value = "/index.html")
    public ModelAndView mainPage(WebRequest request) {
        String sortType = request.getParameter("sortType");
        ModelMap modelMap = new ModelMap();

        List<Employee> employees = selectEmployeesDao.selectAllEmployees();

        if (sortType == null) { //Shall return default index.html page
            modelMap.addAttribute("employees", employees);
        } else if (sortType.compareTo("fullName") == 0) {
            SortEmployees sortEmployees = new SortEmployees();
            List<Employee> employeesSortedByFullName = sortEmployees.sortByFullName(employees);
            modelMap.addAttribute("employees", employeesSortedByFullName);

            return new ModelAndView("index", modelMap);

        } else if (sortType.compareTo("dateOfBirth") == 0) {
            SortEmployees sortEmployees = new SortEmployees();
            List<Employee> employeesSortedByDateOfBirth = sortEmployees.sortByDateOfBirth(employees);
            modelMap.addAttribute("employees", employeesSortedByDateOfBirth);

            return new ModelAndView("index", modelMap);

        } else if (sortType.compareTo("salary") == 0) {
            SortEmployees sortEmployees = new SortEmployees();
            List<Employee> employeesSortedBySalary = sortEmployees.sortBySalary(employees);
            modelMap.addAttribute("employees", employeesSortedBySalary);

            return new ModelAndView("index", modelMap);
        }

        return new ModelAndView("index", modelMap);
    }
}



