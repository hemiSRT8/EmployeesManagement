package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectEmployeesDao;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class IndexController {

    // private static Logger logger = Logger.getRootLogger();

    @Autowired
    private SelectEmployeesDao selectEmployeesDao;

    @RequestMapping("/index.html")
    public ModelAndView mainPage() {

        // logger.info("Index page is started!")

        List<Employee> managers = selectEmployeesDao.selectManagers();
        List<Employee> developers = selectEmployeesDao.selectDevelopers();
        List<Employee> cleaners = selectEmployeesDao.selectCleaners();

        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("managers", managers);
        modelMap.addAttribute("developers", developers);
        modelMap.addAttribute("cleaners", cleaners);

        return new ModelAndView("index", modelMap);
    }
}

