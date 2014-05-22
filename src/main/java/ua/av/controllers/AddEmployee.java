package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.entities.Cleaner;
import ua.av.entities.Developer;
import ua.av.entities.Employee;
import ua.av.entities.Manager;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;

@Controller
public class AddEmployee {

    @RequestMapping("/addEmployeeMenu.html")
    public String addEmployeeMenu() {
        return "addEmployeeMenu";
    }

    @RequestMapping(value = "/addEmployee.html")
    public ModelAndView addEmployee(WebRequest request) {
        Employee employee = null;
        String type = request.getParameter("type");

        if("Manager".equals(type)) {
            employee = new Manager(request.getParameter("lastName"),
                    request.getParameter("firstName"),
                    Date.valueOf(request.getParameter("dateOfBirth")),
                    parseDouble(request.getParameter("wage")),
                    parseDouble(request.getParameter("bonus")),
                    parseDouble(request.getParameter("penalty")),
                    parseDouble(request.getParameter("salary")),
                    parseDouble(request.getParameter("Amount of sales")),
                    parseDouble(request.getParameter("Percentage of sales")));

        } else if("Developer".equals(type)) {
            employee = new Developer(request.getParameter("lastName"),
                    request.getParameter("firstName"),
                    Date.valueOf(request.getParameter("dateOfBirth")),
                    parseDouble(request.getParameter("wage")),
                    parseDouble(request.getParameter("bonus")),
                    parseDouble(request.getParameter("penalty")),
                    parseDouble(request.getParameter("salary")),
                    Integer.parseInt(request.getParameter("Lines of code")));

        } else if("Cleaner".equals(type)) {
            employee = new Cleaner(request.getParameter("lastName"),
                    request.getParameter("firstName"),
                    Date.valueOf(request.getParameter("dateOfBirth")),
                    parseDouble(request.getParameter("wage")),
                    parseDouble(request.getParameter("bonus")),
                    parseDouble(request.getParameter("penalty")),
                    parseDouble(request.getParameter("salary")),
                    Integer.parseInt(request.getParameter("Amount of cleaned offices")));
        }
        return new ModelAndView ("addEmployee", "employeeFields", employee);
    }

}