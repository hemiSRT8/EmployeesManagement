package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddEmployee {

    @RequestMapping(value = "addEmployee.html", method = RequestMethod.POST)
    public ModelAndView addEmployee(WebRequest request) {

        String type = request.getParameter("type");
        List<String> employeeFields = new ArrayList<String>();
        System.out.println("List of the specific employee fields is created.");

        if("manager".equals(type)) {
            employeeFields.add("Amount of sales");
            employeeFields.add("Percentage of sales");
            System.out.println(type + " is being added.");

        } else if("developer".equals(type)) {
            employeeFields.add("Lines of code");
            System.out.println(type + " is being added." );

        } else if("cleaner".equals(type)) {
            employeeFields.add("Amount of cleaned offices");
            System.out.println(type + " is being added.");
        }
        return new ModelAndView ("addEmployee", "employeeFields", employeeFields);
    }
}
