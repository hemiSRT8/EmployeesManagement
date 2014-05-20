package ua.av.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import ua.av.database.SearchEmployeeInDatabase;

@Controller
public class SearchEmployeeController {

    @Autowired
    private SearchEmployeeInDatabase searchEmployeeInDatabase;

    @RequestMapping(value = "/searchEmployee.html")
    public String searchEmployee() {
        return ("searchEmployee");
    }

    @RequestMapping(value = "searchEmployeeResult.html")
    public ModelAndView searchEmployeeResult(WebRequest request) {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute("foundEmployees", searchEmployeeInDatabase.searchEmployee(request));
        modelMap.addAttribute("lastName", request.getParameter("lastName"));

        return new ModelAndView("searchEmployeeResult", modelMap);
    }
}
