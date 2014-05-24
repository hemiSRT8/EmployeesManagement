package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectSingleEmployeeDao;
import ua.av.entities.Employee;

import java.util.List;

@Controller
public class InfoAboutEmployeeController {

    @Autowired
    SelectSingleEmployeeDao selectSingleEmployeeDao;

    @RequestMapping(value = "/infoAboutEmployee.html")
    public ModelAndView infoAboutEmployee(WebRequest request) {

        return new ModelAndView("infoAboutEmployee", "employee",
                selectSingleEmployeeDao.selectSingleEmployee(Long.valueOf(request.getParameter("infoActionId")),
                                                                          request.getParameter("profession")));
    }
}
