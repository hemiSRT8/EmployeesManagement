package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectFullDepartmentsInfo;

@Controller
public class ViewAllDepartmentsController {

    @RequestMapping(value = "/viewAllDepartments.html")
    public ModelAndView viewAllDepartments() {
        return new ModelAndView("viewAllDepartments", "departmentsMap", SelectFullDepartmentsInfo.selectFullDepartmentsInfo());
    }
}