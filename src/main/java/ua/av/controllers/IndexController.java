package ua.av.controllers;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.connector.ConnectorJDBC;
import ua.av.database.select.SelectEmployees;
import ua.av.entities.Employee;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class IndexController {

    // private static Logger logger = Logger.getRootLogger();

    @RequestMapping("/index.html")
    public ModelAndView mainPage() {

        // logger.info("Index page is started!");
        SelectEmployees selectEmployees = new SelectEmployees();

        List<Employee> managers = selectEmployees.selectManagers();
        List<Employee> developers = selectEmployees.selectDevelopers();
        List<Employee> cleaners = selectEmployees.selectCleaners();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", managers);
        modelMap.addAttribute("developers", developers);
        modelMap.addAttribute("cleaners", cleaners);

        return new ModelAndView("index", modelMap);
    }
}

