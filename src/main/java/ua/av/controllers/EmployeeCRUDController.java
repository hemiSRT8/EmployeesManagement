package ua.av.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EmployeeCRUDDao;
import ua.av.entities.Employee;
import ua.av.utils.SearchEmployee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.valueOf;


@Controller
public class EmployeeCRUDController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeCRUDController.class);

    @Autowired
    private EmployeeCRUDDao employeeCRUDDao;

    /**
     * Create employee
     */
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

        boolean result = false;
        if (!CollectionUtils.isEmpty(employeeFields)) {
            result = employeeCRUDDao.addEmployee(employeeFields);
        } else {
            LOGGER.error("employeeFields was null");
        }

        return new ModelAndView("addEmployeeResult", "result", result);
    }

    /**
     * Read employee
     */

    @RequestMapping(value = "/infoAboutEmployee.html")
    public ModelAndView infoAboutEmployee(WebRequest request) {
        Long id = valueOf(request.getParameter("infoActionId"));
        List<Employee> employees = employeeCRUDDao.selectAllEmployees();
        Employee employee = SearchEmployee.searchById(id, employees);

        return new ModelAndView("infoAboutEmployee", "employee", employee);
    }

    /**
     * Update employee
     */

    @RequestMapping(value = "/editEmployee.html", method = RequestMethod.POST)
    public ModelAndView editEmployee(WebRequest request) {
        ModelMap map = new ModelMap();

        String profession = request.getParameter("profession");
        long id = valueOf(request.getParameter("editEmployeeId"));

        List<Employee> list = employeeCRUDDao.selectAllEmployees();

        map.addAttribute("employee", SearchEmployee.searchById(id, list));

        if (profession.equals("Manager")) {
            map.addAttribute("profession", "manager");
        } else if ((profession.equals("Developer"))) {
            map.addAttribute("profession", "developer");
        } else if ((profession.equals("Cleaner"))) {
            map.addAttribute("profession", "cleaner");
        }

        return new ModelAndView("editEmployee", map);
    }

    @RequestMapping(value = "/editEmployeeResult.html", method = RequestMethod.POST)
    public ModelAndView editEmployeeResult(WebRequest request) {

        Long id = valueOf(request.getParameter("id"));
        String professionOfEmployee = request.getParameter("type");

        Map<String, String> fieldsAndValues = new HashMap<String, String>();

        fieldsAndValues.put("lastName", request.getParameter("lastName"));
        fieldsAndValues.put("firstName", request.getParameter("firstName"));
        fieldsAndValues.put("dateOfBirth", request.getParameter("dateOfBirth"));
        fieldsAndValues.put("wage", request.getParameter("wage"));
        fieldsAndValues.put("bonus", request.getParameter("bonus"));
        fieldsAndValues.put("penalty", request.getParameter("penalty"));
        fieldsAndValues.put("salary", request.getParameter("salary"));

        if ("manager".equalsIgnoreCase(professionOfEmployee)) {
            fieldsAndValues.put("amountOfSales", request.getParameter("amountOfSales"));
            fieldsAndValues.put("percentageOfSales", request.getParameter("percentageOfSales"));
        } else if ("developer".equalsIgnoreCase(professionOfEmployee)) {
            fieldsAndValues.put("linesOfCode", request.getParameter("linesOfCode"));
        } else if ("cleaner".equalsIgnoreCase(professionOfEmployee)) {
            fieldsAndValues.put("amountOfCleanedOffices", request.getParameter("amountOfCleanedOffices"));
        }

        boolean result = employeeCRUDDao.editEmployee(id, professionOfEmployee, fieldsAndValues);

        return new ModelAndView("editEmployeeResult", "result", result);
    }

    /**
     * Delete employee
     */

    @RequestMapping(value = "/deleteEmployee.html", method = RequestMethod.POST)
    public ModelAndView deleteEmployee(WebRequest request) {
        long id = valueOf(request.getParameter("deleteEmployeeId"));

        boolean result = employeeCRUDDao.deleteEmployee(id);

        return new ModelAndView("deleteEmployeeResult", "result", result);
    }


}