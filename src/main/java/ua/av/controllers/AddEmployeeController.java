package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.AddEmployeeDao;

import java.util.HashMap;
import java.util.Map;


@Controller
public class AddEmployeeController {

    @Autowired
    private AddEmployeeDao addEmployeeDao;

    @RequestMapping(value = "addManager.html")
    public ModelAndView addManager() {
        return new ModelAndView("addEmployee", "profession", "Manager");
    }

    @RequestMapping(value = "addDeveloper.html")
    public ModelAndView addDeveloper() {
        return new ModelAndView("addEmployee", "profession", "Developer");
    }

    @RequestMapping(value = "addCleaner.html")
    public ModelAndView addCleaner() {
        return new ModelAndView("addEmployee", "profession", "Cleaner");
    }

    @RequestMapping(value = "addEmployeeResult.html", method = RequestMethod.POST)
    public ModelAndView addEmployeeResult(WebRequest request) {
        Map<String, String> employeeFields = new HashMap<String, String>();

        String profession = request.getParameter("profession");

        employeeFields.put("profession", profession);
        employeeFields.put("lastName", request.getParameter("lastName"));
        employeeFields.put("firstName", request.getParameter("firstName"));
        employeeFields.put("dateOfBirth", request.getParameter("dateOfBirth"));
        employeeFields.put("wage", request.getParameter("wage"));
        employeeFields.put("bonus", request.getParameter("bonus"));
        employeeFields.put("penalty", request.getParameter("penalty"));
        employeeFields.put("salary", request.getParameter("salary"));

        if ("Manager".equals(profession)) {
            employeeFields.put("amountOfSales", request.getParameter("amountOfSales"));
            employeeFields.put("percentageOfSales", request.getParameter("percentageOfSales"));
        } else if ("Developer".equals(profession)) {
            employeeFields.put("linesOfCode", request.getParameter("linesOfCode"));
        } else if ("Cleaner".equals(profession)) {
            employeeFields.put("amountOfCleanedOffices", request.getParameter("amountOfCleanedOffices"));
        }

        boolean result = addEmployeeDao.addEmployee(employeeFields);

        return new ModelAndView("addEmployeeResult", "result", result);
    }

}