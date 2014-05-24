package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SelectSingleEmployeeDao;
import ua.av.entities.Employee;

import java.util.List;

import static java.lang.Long.*;

@Controller
public class InfoAboutEmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(InfoAboutEmployeeController.class);

    @Autowired
    SelectSingleEmployeeDao selectSingleEmployeeDao;

    @RequestMapping(value = "/infoAboutEmployee.html")
    public ModelAndView infoAboutEmployee(WebRequest request) {
        Long id = valueOf(request.getParameter("infoActionId"));
        String profession = request.getParameter("profession");

        List<Employee> employee = selectSingleEmployeeDao.selectSingleEmployee(id, profession);

        LOGGER.info("Info about employee with id: " + id + " page was load successfully");

        return new ModelAndView("infoAboutEmployee", "employee", employee);
    }
}
