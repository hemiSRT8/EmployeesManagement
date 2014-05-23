package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AddEmployeeDao addEmployeeDao;

    @RequestMapping("/addEmployeeMenu.html")
    public String addEmployeeMenu() {
        return "addEmployeeMenu";
    }


    @RequestMapping(value = "/addEmployee.html")
    public ModelAndView addEmployee(WebRequest request) {
        String type = request.getParameter("type");
        List<String> employeeFields = new ArrayList<String>();

        employeeFields.add(type);

        if ("Manager".equals(type)) {
            employeeFields.add("Amount of sales");
            employeeFields.add("Percentage of sales");

        } else if ("Developer".equals(type)) {
            employeeFields.add("Lines of code");

        } else if ("Cleaner".equals(type)) {
            employeeFields.add("Amount of cleaned offices");
        }
        return new ModelAndView("addEmployee", "employeeFields", employeeFields);
    }

    @RequestMapping(value = "/addEmployeeResult.html", method = RequestMethod.POST)
    public ModelAndView addEmployeeResult(WebRequest request) {
        List<String> employeeFields = new ArrayList<String>();

        String type = request.getParameter("type");
        employeeFields.add(request.getParameter("type"));
        employeeFields.add(request.getParameter("lastName"));
        employeeFields.add(request.getParameter("firstName"));
        employeeFields.add(request.getParameter("dateOfBirth"));
        employeeFields.add(request.getParameter("wage"));
        employeeFields.add(request.getParameter("bonus"));
        employeeFields.add(request.getParameter("penalty"));
        employeeFields.add(request.getParameter("salary"));

        if ("Manager".equals(type)) {
            employeeFields.add(request.getParameter("Amount of sales"));
            employeeFields.add(request.getParameter("Percentage of sales"));
        } else if ("Developer".equals(type)) {
            employeeFields.add(request.getParameter("Lines of code"));
        } else if ("Cleaner".equals(type)) {
            employeeFields.add(request.getParameter("Amount of cleaned offices"));
        }

        return new ModelAndView("addEmployeeResult", "isEmployeeAdded", addEmployeeDao.addEmployee(employeeFields));
    }

}