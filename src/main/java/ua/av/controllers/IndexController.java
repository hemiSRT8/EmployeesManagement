package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Employee;
import ua.av.utils.SortEmployees;

import java.util.List;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SelectEmployeesDao selectEmployeesDao;

    @RequestMapping(value = "/index.html")
    public ModelAndView mainPage(WebRequest request) {
        String sortType = request.getParameter("sortType");
        ModelMap modelMap = new ModelMap();

        List<Employee> managers = selectEmployeesDao.selectManagers();
        List<Employee> developers = selectEmployeesDao.selectDevelopers();
        List<Employee> cleaners = selectEmployeesDao.selectCleaners();

        if (sortType == null) { //Return default index.html page
            modelMap.addAttribute("managers", managers);
            modelMap.addAttribute("developers", developers);
            modelMap.addAttribute("cleaners", cleaners);
        } else if (sortType.compareTo("fullName") == 0) {
            SortEmployees sortEmployees = new SortEmployees();
            List<Employee> managersSortedByFullName = sortEmployees.sortByFullName(managers);
            List<Employee> developersSortedByFulLName = sortEmployees.sortByFullName(developers);
            List<Employee> cleanersSortedByFullName = sortEmployees.sortByFullName(cleaners);
            modelMap.addAttribute("managers", managersSortedByFullName);
            modelMap.addAttribute("developers", developersSortedByFulLName);
            modelMap.addAttribute("cleaners", cleanersSortedByFullName);

            return new ModelAndView("index", modelMap);
        } else if (sortType.compareTo("dateOfBirth") == 0) {
            SortEmployees sortEmployees = new SortEmployees();
            List<Employee> managersSortedByDateOfBirth = sortEmployees.sortByDateOfBirth(managers);
            List<Employee> developersSortedByDateOfBirth = sortEmployees.sortByDateOfBirth(developers);
            List<Employee> cleanersSortedByDateOfBirth = sortEmployees.sortByDateOfBirth(cleaners);
            modelMap.addAttribute("managers", managersSortedByDateOfBirth);
            modelMap.addAttribute("developers", developersSortedByDateOfBirth);
            modelMap.addAttribute("cleaners", cleanersSortedByDateOfBirth);

            return new ModelAndView("index", modelMap);
        } else if (sortType.compareTo("salary") == 0) {
            SortEmployees sortEmployees = new SortEmployees();
            List<Employee> managersSortedBySalary = sortEmployees.sortBySalary(managers);
            List<Employee> developersSortedBySalary = sortEmployees.sortBySalary(developers);
            List<Employee> cleanersSortedBySalary = sortEmployees.sortBySalary(cleaners);
            modelMap.addAttribute("managers", managersSortedBySalary);
            modelMap.addAttribute("developers", developersSortedBySalary);
            modelMap.addAttribute("cleaners", cleanersSortedBySalary);

            return new ModelAndView("index", modelMap);
        }

        LOGGER.info("Main page was loaded successfully");

        return new ModelAndView("index", modelMap);
    }
}



