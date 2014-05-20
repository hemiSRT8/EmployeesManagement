package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectDepartments;
import ua.av.database.SelectFullDepartmentsInfo;

@Controller
public class ViewAllDepartmentsController {

    @Autowired
    private SelectFullDepartmentsInfo selectFullDepartmentsInfo = new SelectFullDepartmentsInfo();
    @Autowired
    private SelectDepartments selectDepartments = new SelectDepartments();

    @RequestMapping(value = "/viewAllDepartments.html")
    public ModelAndView viewAllDepartments() {
        ModelMap modelmap = new ModelMap();

        modelmap.addAttribute("departmentsMap", selectFullDepartmentsInfo.selectFullDepartmentsInfo()); //departments with employees
        modelmap.addAttribute("departmentsNamesOnly", selectDepartments.selectDepartmentsFromDatabase()); //only department's names

        return new ModelAndView("viewAllDepartments", modelmap);
    }
}