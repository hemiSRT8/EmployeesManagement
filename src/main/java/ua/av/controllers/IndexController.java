package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectEmployees;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class IndexController {

    // private static Logger logger = Logger.getRootLogger();

    @RequestMapping("/index.html")
    public ModelAndView mainPage() {

        // logger.info("Index page is started!");

        List<Employee> managers = SelectEmployees.selectManagers();
        List<Employee> developers = SelectEmployees.selectDevelopers();
        List<Employee> cleaners = SelectEmployees.selectCleaners();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", managers);
        modelMap.addAttribute("developers", developers);
        modelMap.addAttribute("cleaners", cleaners);

        return new ModelAndView("index", modelMap);
    }
}

