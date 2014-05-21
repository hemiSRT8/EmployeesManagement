package ua.av.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddEmployeeDao;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AddEmployee {

    @RequestMapping("/addEmployeeMenu.html")
    public String addEmployeeMenu(WebRequest request) {
        return "addEmployeeMenu";
    }

    @RequestMapping(value = "/addEmployee.html")
    public ModelAndView addEmployee(WebRequest request) {
        String type = request.getParameter("type");
        List<String> employeeFields = new ArrayList<String>();

        employeeFields.add(type);

        if("Manager".equals(type)) {
            employeeFields.add("Amount of sales");
            employeeFields.add("Percentage of sales");

        } else if("Developer".equals(type)) {
            employeeFields.add("Lines of code");
            System.out.println(type + " is being added." );

        } else if("Cleaner".equals(type)) {
            employeeFields.add("Amount of cleaned offices");
        }
        return new ModelAndView ("addEmployee", "employeeFields", employeeFields);
    }

    @RequestMapping(value="/addEmployeeResult.html", method = RequestMethod.POST)
    public ModelAndView addEmployeeResult(WebRequest request) {
        int result = new AddEmployeeDao().addEmployee(request);

        return new ModelAndView("addEmployeeResult", "addResult", result);
    }
}