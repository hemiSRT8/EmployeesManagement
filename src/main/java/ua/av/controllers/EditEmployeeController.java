package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.EditEmployeeDao;
import ua.av.database.SelectSingleEmployeeDao;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Long.valueOf;

@Controller
public class EditEmployeeController {

    @Autowired
    private EditEmployeeDao editEmployeeDao;
    @Autowired
    private SelectSingleEmployeeDao selectSingleEmployeeDao;

    @RequestMapping(value = "/editEmployee.html", method = RequestMethod.POST)
    public ModelAndView editEmployee(WebRequest request) {
        ModelMap map = new ModelMap();

        String profession = request.getParameter("profession");
        long id = valueOf(request.getParameter("editEmployeeId"));

        if (profession.contains("class ua.av.entities.Manager")) {
            map.addAttribute("employee", selectSingleEmployeeDao.selectSingleEmployee(id, "manager"));
            map.addAttribute("profession", "manager");
            return new ModelAndView("editEmployee", map);
        } else if (profession.contains("class ua.av.entities.Developer")) {
            map.addAttribute("employee", selectSingleEmployeeDao.selectSingleEmployee(id, "developer"));
            map.addAttribute("profession", "developer");
            return new ModelAndView("editEmployee", map);
        } else if (profession.contains("class ua.av.entities.Cleaner")) {
            map.addAttribute("employee", selectSingleEmployeeDao.selectSingleEmployee(id, "cleaner"));
            map.addAttribute("profession", "cleaner");
            return new ModelAndView("editEmployee", map);
        } else {
            return new ModelAndView("redirect:/index.html");
        }
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

        boolean result = editEmployeeDao.editEmployee(id, professionOfEmployee, fieldsAndValues);

        return new ModelAndView("editEmployeeResult", "result", result);
    }
}