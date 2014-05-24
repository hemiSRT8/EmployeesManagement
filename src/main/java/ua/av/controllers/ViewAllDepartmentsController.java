package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectDepartmentsDao;
import ua.av.database.SelectFullDepartmentsInfoDao;

@Controller
public class ViewAllDepartmentsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ViewAllDepartmentsController.class);

    @Autowired
    private SelectFullDepartmentsInfoDao selectFullDepartmentsInfoDao = new SelectFullDepartmentsInfoDao();
    @Autowired
    private SelectDepartmentsDao selectDepartmentsDao = new SelectDepartmentsDao();

    @RequestMapping(value = "/viewAllDepartments.html")
    public ModelAndView viewAllDepartments() {
        ModelMap modelmap = new ModelMap();

        modelmap.addAttribute("departmentsMap", selectFullDepartmentsInfoDao.selectFullDepartmentsInfo()); //departments with employees
        modelmap.addAttribute("departmentsNamesOnly", selectDepartmentsDao.selectDepartmentsFromDatabase()); //only department's names

        LOGGER.info("View all departments page was loaded successfully");
        return new ModelAndView("viewAllDepartments", modelmap);
    }
}