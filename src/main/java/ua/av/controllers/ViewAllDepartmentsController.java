package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectDepartmentsDao;
import ua.av.database.SelectFullDepartmentsInfoDao;

@Controller
public class ViewAllDepartmentsController {

    @Autowired
    private SelectFullDepartmentsInfoDao selectFullDepartmentsInfoDao;
    @Autowired
    private SelectDepartmentsDao selectDepartmentsDao;

    @RequestMapping(value = "/viewAllDepartments.html")
    public ModelAndView viewAllDepartments() {
        ModelMap modelmap = new ModelMap();

        modelmap.addAttribute("departmentsMap", selectFullDepartmentsInfoDao.selectFullDepartmentsInfo()); //departments with employees
        modelmap.addAttribute("departmentsNamesOnly", selectDepartmentsDao.selectDepartmentsFromDatabase()); //only department's names

        return new ModelAndView("viewAllDepartments", modelmap);
    }
}